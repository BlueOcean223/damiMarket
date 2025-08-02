<!-- 商品展示卡片 -->
<template>
    <div class="product-card" @click="viewDetails">
      <img :src="product.img" alt="product-image" class="product-image" />
      <p>{{ product.description }}</p>
      <p class="price">价格：¥{{ product.price }}</p>
    </div>
</template>
  
  <script>
  export default {
    name: "ProductCard",
    props: {
      product: {
        type: Object,
        required: true,
      },
    },
    methods: {
      viewDetails(){
        //切换路由到商品详情页
        // this.$router.push({
        //   path:'/main/buy/detail',
        //   query:{product_id:this.product.id}
        // })
        const url = this.$router.resolve({
          path: "/main/buy/detail",
          query: { product_id: this.product.id },
        }).href;
        window.open(url, "_blank"); // 在新页面打开详情页
      },
    },
  };
  </script>
  
  <style scoped>
  .product-card {
    border: 2px solid #ddd;
    padding: 16px;
    border-radius: 8px;
    text-align: center;
    background-color: #fff;
    outline: none; /* 去掉聚焦时的边框 */
    user-select: none; /* 禁止文本被选中 */
  }

.product-card h3 {
  font-size: 18px;
  margin-bottom: 8px;
}

.product-card p {
  font-size: 14px;
  color: #666;
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
  