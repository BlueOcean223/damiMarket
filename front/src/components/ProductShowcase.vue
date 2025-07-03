<!-- 在主页中展示部分商品 -->
 <template>
    <div class="product-show">
    <h3>{{ title }}</h3>
    <div class="product-showcase">
      <div class="product-card"  v-for="item in items" :key="item.id" @click="viewDetails(item.id)">
        <img :src="item.img" alt="item-image" class="product-image" />
        <p>{{ item.description }}</p>
        <p class="price">价格：¥{{ item.price }}</p>
      </div>
    </div>
  </div>
 </template>

 <script>
export default {
    // 从父组件接收数据
    props: {
        title: {
            type: String,
            required: true // 必填项
        },
        items:{
            type: Array,
            required: true // 必填项
        }

    },
    methods: {
      viewDetails(id){
        //切换路由到商品详情页
        this.$router.push({
          path:'/main/buy/detail',
          query:{product_id:id}
        })
      },
    }
}

</script>

<style scoped>
  .product-show{
    max-width: 1300px; /* 限制内容区的最大宽度 */
    margin: 0 auto; /* 水平居中 */
    padding: 20px; /* 增加一些边距 */
    outline: none; /* 去掉聚焦时的边框 */
    user-select: none; /* 禁止文本被选中 */
  }
  .product-card {
    border: 2px solid #ddd;
    padding: 15px;
    border-radius: 8px;
    text-align: center;
    background-color: #fff;
  }
  .product-showcase{
    display: grid;
    grid-template-columns: repeat(3, 0.35fr); /* 三列布局 */
    gap: 30px;
    padding: 20px;
    justify-content: center;
    margin: 0 auto;
    flex-wrap: wrap;
  }
  .product-card .price {
    color: #e91e63;
    font-weight: bold;
  }
  .product-image {
    width: 40%;
    height: auto;
    margin-bottom: 8px;
  }
</style>