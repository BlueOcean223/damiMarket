<template>
    <MenuBar/>
    <div class="order-page">
      <div class="order-content">
        <table class="order-table">
          <thead>
            <tr class="titleBar">
              <th>商品名称</th>
              <th>收货地址</th>
              <th>操作</th>
              <th>订单状况</th>
            </tr>
          </thead>
          <tbody>
            <OrderItem
              v-for="(item) in userOrders"
              :key="item.id"
              :item="item"
              @cancel="cancelOrder"
              @confirm="confirmOrder"
            />
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import OrderItem from '@/components/OrderItem.vue';
  import MenuBar from '@/components/MenuBar.vue';
  import { getCheckoutAddress } from '@/api/cart';
  import { getStatus, deleteOrder, putOrder,get } from '@/api/orders';
  
  export default {
    name: 'UserOrdersPage',
    components: { OrderItem, MenuBar },
    data() {
      return {
        username: localStorage.getItem('username'),
        userOrders: [],
      };
    },
    created() {
      this.fetchUserOrders();
    },
    methods: {
      async fetchUserOrders() {
        try {
          const res = await get(this.username);
          if (res.data.success === 1) {
            this.userOrders = res.data.data.map((order)=>({
                ...order,
                //追加收货地址和订单状态
                address: "",
                status: ""
            }));

           // 并行获取所有订单的收货地址
           const addressPromises = this.userOrders.map(async (order) => {
                if(!order.id){
                    console.error("订单没有商品id");
                    return;
                }
                const data = {
                    username: this.username,
                    product_id: order.id,
                };
                try {
                    const addressRes = await getCheckoutAddress(data);
                    if (addressRes.data.success) {
                        return { id: order.id, address: addressRes.data.data };
                    } else {
                        console.error("获取收货地址失败:", addressRes.data.message);
                        return { id: order.id, address: "地址获取失败" };
                    }
                } catch (error) {
                    console.error("获取收货地址请求失败:", error);
                    return { id: order.id, address: "地址获取失败" };
                }
            });

            // 等待所有地址请求完成
            const addresses = await Promise.all(addressPromises);
            addresses.forEach(({ id, address }) => {
                const order = this.userOrders.find(o => o.id === id);
                if (order) {
                    order.address = address;
                }
            });

            //获取订单状态
            const statusRes = await getStatus(this.username);
            var statusData = statusRes.data.data;
            var index = 0;
            this.userOrders.forEach((order)=>{
                order.status = statusData[index];
                index++;
            });
          } else {
            console.error('获取订单信息失败');
          }
        } catch (error) {
          console.error('请求错误:', error);
        }
      },
      async cancelOrder(orderId) {
        try {
          const data ={
            username: this.username,
            product_id: orderId,
          };
          await deleteOrder(data);
          this.userOrders = this.userOrders.filter((order) => order.id !== orderId);
        } catch (error) {
          console.error('取消订单失败:', error);
        }
      },
      async confirmOrder(orderId) {
        try {
          const data ={
              username: this.username,
              product_id: orderId,
          };
          const res = await putOrder(data);
          if(res.data.success){
            this.userOrders.forEach((order) => {
                if(order.id === orderId && order.status === '未完成'){
                    order.status = '完成';
                }
            });
          }else{
            console.error('确认收货失败,后端返回错误');
            console.log("最后发送的信息",{
                username: this.username,
                product_id: orderId,
            })
            console.error(res.data.message);
          }
        } catch (error) {
          console.error('确认收货失败:', error);
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .order-page {
    padding: 20px;
    display: flex;
    justify-content: center;
    outline: none; /* 去掉聚焦时的边框 */
    user-select: none; /* 禁止文本被选中 */
  }
  .order-content {
    width: 60%;
  }
  .order-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
  }
  .titleBar {
    border-bottom: 1px solid #ddd;
  }
  .order-table th,
  .order-table td {
    padding: 20px;
    text-align: center;
    border-bottom: 1px solid #ddd;
  }

  </style>
  