<template>
    <MenuBar/>
    <div class="cart-page">
      <div class="cart-content">
        <table class="cart-table">
          <thead>
            <tr class="titleBar">
              <th>
                <input type="checkbox" @change="toggleSelectAll" v-model="selectAll" /> 全选
              </th>
              <th>商品名称</th>
              <th class="price">单价</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
            <CartItem
              v-for="(item) in cartItems"
              :key="item.id"
              :item="item"
              :selected-items="selectedItems"
              @remove="removeItem"
              @update-selection="updateSelection"
            />
          </tbody>
        </table>
        <div class="cart-summary">
          <span>已选择 {{ selectedItems.length }} 件商品</span>
          <span class="total-price">合计总价: {{ totalPrice }} 元</span>
          <button @click="goToCheckout" 
           :disabled="selectedItems.length === 0" 
           :class="{ 'disabled-button': selectedItems.length === 0 }">
            去结算
          </button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { getCartItem, remove, checkout } from '@/api/cart.js';
  import CartItem from '@/components/CartItem.vue';
  import MenuBar from '@/components/MenuBar.vue';
  
  export default {
    name: 'CartPage',
    components: {  CartItem, MenuBar },
    data() {
      return {
        username: localStorage.getItem('username'), 
        cartItems: [],
        selectedItems: [],
        selectAll: false,
      };
    },
    created() {
      this.fetchCartItems();
    },
    methods: {
      async fetchCartItems() {
        try {
          const res = await getCartItem(this.username);
          if (res.data.success === 1) {
            this.cartItems = res.data.data;
            //确保购物车商品不重复
            this.cartItems = this.cartItems.reduce((uniqueItems, currentItem) => {
            if (!uniqueItems.some(item => item.id === currentItem.id)) {
                uniqueItems.push(currentItem);
            }
            return uniqueItems;
            }, []);

          } else {
            console.error('获取购物车商品失败');
          }
        } catch (error) {
          console.error('请求错误:', error);
        }
      },
      async removeItem(product_id) {
        try {
          const data = {
            username: this.username,
            product_id,
          };
          await remove(data);
          this.cartItems = this.cartItems.filter((item) => item.id !== product_id);
          this.updateTotalPrice();
        } catch (error) {
          console.error('删除商品失败:', error);
        }
      },
      toggleSelectAll() {
        this.selectedItems = this.selectAll ? this.cartItems.map((item) => item.id) : [];
      },
      async goToCheckout() {
        if(this.selectedItems.length === 0) {
            return;
        }
        try{
            // 遍历selectedItems，将每个商品id发送到后端
            for(const id of this.selectedItems) {
                const userBuyData = {
                    username: this.username,
                    product_id: id,
                };
     
                // 发送POST请求到后端
                await checkout(userBuyData);

                // 清除后端购物车中的商品
                this.removeItem(id);
            }

            // 结算成功后，移除选中的商品
            this.cartItems = this.cartItems.filter(item => !this.selectedItems.includes(item.id));
            this.selectedItems = [];// 清空已选中的商品
            alert('商品已经结算！祝您购物愉快');
        }catch (error) {
          console.error('结算失败:', error);
          alert('结算失败，请稍后再试');
        }
        
      },
      updateTotalPrice() {
        this.totalPrice = this.selectedItems.reduce((total, id) => {
          const item = this.cartItems.find((item) => item.id === id);
          return total + (item ? item.price : 0);
        }, 0);
      },
      updateSelection({ id, selected }) {
        if (selected) {
          if (!this.selectedItems.includes(id)) {
            this.selectedItems.push(id);
          }
        } else {
          this.selectedItems = this.selectedItems.filter((itemId) => itemId !== id);
        }
      },
    },
    watch: {
      selectedItems: 'updateTotalPrice',
    },
    computed: {
      totalPrice() {
        return this.selectedItems.reduce((total, id) => {
          const item = this.cartItems.find((item) => item.id === id);
          return total + (item ? item.price : 0);
        }, 0);
      },
    },
  };
  </script>
  
  <style scoped>
  .cart-page {
    padding: 20px;
    display: flex;
    justify-content: center;
    outline: none; /* 去掉聚焦时的边框 */
    user-select: none; /* 禁止文本被选中 */
  }
  .cart-content {
    width: 60%;
  }
  .cart-table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
  }
       
  .cart-summary {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .total-price {
    font-weight: bold;
    color: #ff4a00;
  }
  button {
    background-color: #ff4a00; /* 橙色背景 */
    color: white; /* 文字颜色为白色 */
    border: none;
    padding: 10px 20px; /* 调整按钮的大小 */
    font-size: 18px;
    cursor: pointer;
    border-radius: 5px; /* 圆角按钮 */
  }

  button:hover {
    background-color: #e03d00; /* 鼠标悬停时，稍微变暗的橙色 */
  }
  .disabled-button {
    background-color: #d3d3d3;
    color: #ffffff;
    cursor: not-allowed;
 }
 .titleBar{
    border-bottom: 1px solid #ddd; /* 增加底部的灰色横线 */
    font-size: 20px;/* 增加标题字体大小*/
 }
  </style>
  