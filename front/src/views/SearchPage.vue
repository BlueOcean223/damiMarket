<template>
  <!-- 菜单栏 -->
  <MenuBar/>
  <!-- 展示商品 -->
    <div>
      <div class="products">
        <ProductCard
          v-for="product in paginatedProducts"
          :key="product.id"
          :product="product"
        />
      </div>
  <!-- 分页控制 -->
      <div class="pagination">
        <button @click="goToPreviousPage" :disabled="currentPage === 1">上一页</button>
        <span>第 {{ currentPage }} 页 / 共 {{ totalPages }} 页</span>
        <button @click="goToNextPage" :disabled="currentPage === totalPages">下一页</button>
      </div>
    </div>
  </template>
  
  <script>
  import ProductCard from "../components/ProductCard.vue";
  import MenuBar from "@/components/MenuBar.vue";
  import axios from "axios";
  
  export default {
    name: "SearchPage",
    components: { ProductCard , MenuBar},
    data() {
      return {
        products: [],// 搜索结果数组
        currentPage: 1, // 当前页码
        pageSize: 18, // 每页显示的商品数量
      };
    },
    computed: {
      // 当前页展示的商品
      paginatedProducts() {
        //通过起始索引，结束索引控制展示的商品数量
        const startIndex = (this.currentPage - 1) * this.pageSize;
        const endIndex = this.currentPage * this.pageSize;
        //对数组进行切片
        return this.products.slice(startIndex, endIndex);
      },
      // 总页数
      totalPages() {
        return Math.ceil(this.products.length / this.pageSize);
      },
    },
    // 获取搜索结果
    watch: {
      $route: {
        immediate: true,
        handler(to) {
          this.fetchData(to.query.keyword);
        },
      },
    },
    methods: {
      async fetchData(keyword) {
        try {
          const response = await axios.get("http://localhost:8080/api/search", {
            params: { keyword },
          });
          if (response.data.success) {
            this.products = response.data.data; // 将数据赋值给 products
          } else {
            this.products = []; // 清空 products
          }
          
          this.currentPage = 1;//切换其他搜索词时，重置到第一页
        } catch (error) {
          console.error("数据加载失败", error);
          this.products = []; // 清空 products
        }
      },
      // 分页控制
      goToPreviousPage(){
        if(this.currentPage > 1){
          this.currentPage--;
        }
      },
      goToNextPage(){
        if(this.currentPage < this.totalPages){
          this.currentPage++;
        }
      },
    },
  };
  </script>
  
  <style scoped>
  /* 商品展示 */
  .products {
    display: grid;
    grid-template-columns: repeat(3, 0.2fr); /* 三列布局 */
    gap: 20px;
    padding: 20px;
    justify-content: center;
    margin: 0 auto;
    flex-wrap: wrap;
  }
  /* 分页样式 */
  .pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 20px 0;
  }

  .pagination button {
    padding: 10px 20px;
    margin: 0 10px;
    border: 1px solid #ddd;
    background-color: #f5f5f5;
    cursor: pointer;
    border-radius: 5px;
  }

  .pagination button:disabled {
    cursor: not-allowed;
    background-color: #e0e0e0;
    color: #999;
  }

  .pagination span {
    font-size: 16px;
  }
  </style>
  