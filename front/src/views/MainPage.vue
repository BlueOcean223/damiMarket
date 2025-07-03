<template>
  <!-- 菜单栏 -->
  <MenuBar/>
  <!-- 走马灯视图 -->
  <div class="carousel-container">
    
    <el-carousel  height="450px"   trigger="hover" autoplay>
      <el-carousel-item v-for="(image, index) in images" :key="index">
        <img :src="image" alt="carousel-image" class="carousel-image" />
      </el-carousel-item>
    </el-carousel>
  </div>
  <!-- 商品展示区 -->
  <!-- 手机 -->
  <ProductShowcase title="手机" :items="phones" @add-to-cart="addToCart"/>
  <!-- 电视 -->
  <ProductShowcase title="家居" :items="tvs" @add-to-cart="addToCart"/>
  <!-- 平板 -->
  <ProductShowcase title="平板" :items="pads" @add-to-cart="addToCart"/>
  <!-- 笔记本电脑 -->
  <ProductShowcase title="笔记本电脑" :items="laptops" @add-to-cart="addToCart"/>
  <!-- 智能穿戴 -->
  <ProductShowcase title="智能穿戴" :items="watches" @add-to-cart="addToCart"/>

</template>

<script>
import ProductShowcase from '../components/ProductShowcase.vue';
import MenuBar from '../components/MenuBar.vue';
  export default {
    name: "MainPage",
    components: { ProductShowcase , MenuBar},
    data() {
      return {
        images:[
          "https://s1.xiaomiev.com/activity-outer-assets/0328/images/su7/su7_1.jpg",
          "https://s1.xiaomiev.com/activity-outer-assets/0328/images/Ultra_U/pc/ultra_1.jpg",
          "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/b5a4b9d2fe435f1de1c540caa61f7b40.jpg?w=2452&h=920",
          "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/8835531789bb48deb91eedffeff58518.jpg?w=2452&h=920",
          "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/d42065650d91b698228f87a01e42bd15.jpg?thumb=1&w=1226&h=460&f=webp&q=90",
          "https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/66972c75fdc4c4c9b72b7a772e8de261.jpg?thumb=1&w=1226&h=460&f=webp&q=90",
          "../imag/zhaoliyin1.jpg",
        ],
        phones:[
          {
            id: 1,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202410282146_5b401bb9f2f18d1a3217a222a8b7d0fd.png",
            description: "小米15",
            price: 4999
          },
          {
            id: 4,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202310251934_a644b98586dc18c0b207d8055049b608.png",
            description: "小米14",
            price: 4299
          },
          {
            id: 2,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202211292354_3561d2993d555486f6504a7c37b62439.png",
            description: "小米13",
            price: 3999
          },
          {
            id: 63,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202407221653_c5e018875fdfb98c4899ef36c465ebce.png",
            description: "Xiaomi MIX Flip 随身拍套装",
            price: 6498
          },
          {
            id: 17,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202411261112_32f501aebcdd8f7085557e72f351639f.png",
            description: "红米K80",
            price: 2499
          },
          {
            id: 514,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202410282152_ddf9e92d1097e241e9090e81baa36d2c.png",
            description: "小米15pro",
            price: 5999
          },
        ],
        tvs:[
          {
            id: 329,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202209071506_090ebb27c0f154c3039f2ebeb87c1684.png",
            description: "米家冰箱 墨羽岩",
            price: 1899
          },
          {
            id: 9,
            img: "https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/7773977D843E1AC364CAE8AAA58A56BD.png",
            description: "小米电视",
            price: 6666
          },
          {
            id: 14,
            img: "https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1729676859.83346337.jpg",
            description: "红米电视",
            price: 4566
          },
          {
            id: 395,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202409111423_14376678b779ae63e2063641fa0de659.png",
            description: "米家电饭煲N1 1.6L 奶油白",
            price: 169
          },
          {
            id: 231,
            img: "https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1605513074.35629508.jpg",
            description: "米家无线吸尘器2 ",
            price: 49
          },
          {
            id: 504,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202310081508_18b8d7deaaca3515a957de09c1e18703.png",
            description: "新风 1.5匹新1级 米家空调",
            price: 2999
          },
        ],
        laptops:[
          {
            id: 13,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202311281956_f87f4c37af45c2261e944f57d29e4a03.png",
            description: "红米笔记本",
            price: 3000
          },
          {
            id: 6,
            img: "https://gw.alicdn.com/imgextra/i1/2208924858327/O1CN01OPpKG02BNp35iV4P5_!!4611686018427385815-0-item_pic.jpg",
            description: "宏碁掠夺者",
            price: 5666
          },
          {
            id: 7,
            img: "https://img.alicdn.com/imgextra/i4/2218405373357/O1CN01efgpZt1afYZwQMSac_!!2218405373357.jpg_q50.jpg_.webp",
            description: "天选4",
            price: 6777
          },
          {
            id: 171,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202403041525_87e628a6619f343149706b97e222098c.png",
            description: "Redmi G Pro 游戏本 2024 i9 战舰灰",
            price: 9499
          },
          {
            id: 174,
            img: "https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1621682489.20295674.jpg",
            description: "小米笔记本 Pro 15 R5 16G 512 灰色",
            price: 3599
          },
          {
            id: 169,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202305181410_8e928f44b27f793c4c50789d9678d32a.png",
            description: "Redmi Book 14",
            price: 3399
          },
        ],
        pads:[
          {
            id: 10,
            img: "https://img.alicdn.com/imgextra/i1/2213261250814/O1CN01OMJzo61HsrNONDmdx_!!2213261250814.jpg_q50.jpg_.webp",
            description: "ipadAir4",
            price: 9999
          },
          {
            id: 3,
            img: "https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1730124920.44772463.png",
            description: "小米pad7",
            price: 3999
          },
          {
            id: 15,
            img: "https://gw.alicdn.com/imgextra/i1/2212825911385/O1CN0166OM0p1M6NWHe4wqO_!!2212825911385.png",
            description: "vivopad3",
            price: 3999
          },
          {
            id: 518,
            img: "https://gw.alicdn.com/bao/uploaded/i1/1917047079/O1CN01wPcknf22AEhlYPa9k_!!1917047079.png_.webp",
            description: "Apple/苹果 13 英寸 iPad Air",
            price: 6499
          },
          {
            id: 521,
            img: "https://consumer.huawei.com/content/dam/huawei-cbg-site/cn/mkt/launch/241126/plp/tablets/nav-matepad-pro-13-2.jpg",
            description: "HUAWEI MatePad Pro 13.2 英寸",
            price: 5199
          },
          {
            id: 529,
            img: "https://gw.alicdn.com/bao/uploaded/i2/370627083/O1CN01YNNs1E22C4KfkGOom_!!370627083.jpg",
            description: "三星/Samsung Galaxy Tab S10 Ultra",
            price: 8999
          },
        ],
        watches:[
          {
            id: 22,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202407172251_b11e7f4720196cbb9ae4b27707459a21.png",
            description: "Xiaomi Buds 5",
            price: 699
          },
          {
            id: 28,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202309211159_17fe0c43d4e970c047f2a69b73e15459.png",
            description: "Redmi Buds 5",
            price: 179
          },
          {
            id: 112,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202410291116_e42391fb8f8a6612ccb101148495fb27.png",
            description: "Xiaomi Watch S4",
            price: 999
          },
          {
            id: 115,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202310262032_d6242148bfa088bfb75dfadf38a14a63.png",
            description: "小米腕部血压记录仪 黑色",
            price: 1899
          },
          {
            id: 37,
            img: "https://cdn.cnbj1.fds.api.mi-img.com/nr-pub/202309072154_55406aec433d45cbee2608c30904513b.png",
            description: "小米有线耳机 黑色",
            price: 99
          },
          {
            id: 163,
            img: "https://cdn.cnbj0.fds.api.mi-img.com/b2c-shopapi-pms/pms_1635476917.02187409.jpg",
            description: "玺佳机械表•Z系列 黑色硅胶带",
            price: 1199
          },
        ],
      }
    },
    methods: {
      logout() {
        // 清除 token 并跳转回登录页面
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        this.$router.push("/login");
      },
      clearCart() {
        // 清空购物车
        this.cartCount = 0;
      },
    },
    // created() {
    //   this.username = localStorage.getItem("username");
    // }
  };
  </script>
  
<style scoped>
  .main-page-title {
    display: flex;
  }
  button {
    display: flex;
    margin: 20px auto;
    padding: 10px 20px;
    background-color: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  button:hover {
    background-color: #0056b3;
  }
  .carousel-container {
    width: 100%;
    max-width: 1300px;
    margin: 0 auto;
    padding: 20px;
    outline: none; /* 去掉聚焦时的边框 */
    user-select: none; /* 禁止文本被选中 */
  }

  .carousel-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
  </style>
  