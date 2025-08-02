<template>
  <!-- 菜单栏 -->
  <MenuBar/>
   <!-- 商品详情 -->
  <div class="product-detail">
    <div class="container">
      <!-- 左侧图片 -->
      <div class="image-section">
        <img :src="product.img" alt="product-image" class="product-image" />
      </div>
      <!-- 右侧内容 -->
      <div class="info-section">
        <h1>{{ product.description }}</h1>
        <p class="price">价格：¥{{ product.price }}</p>
        <div class="address">
          <label>收货地址：</label>
          <select v-model="selectedAddress" class="address-select">
            <option v-for="addr in address" :key="addr" :value="addr">
                {{ addr }}
            </option>
           </select>
        </div>
        <div>
            <!-- 有信息时才显示该标签 -->
          <label v-if="versions.length > 0">选择版本：</label>
          <div class="options">
            <button
              v-for="version in versions"
              :key="version"
              @click="selectVersion(version)"
              :class="{ selected: selectedVersion === version }"
            >
            <!-- 添加选中效果  :class="{ selected: selectedVersion === version }" -->
              {{ version }}
            </button>
          </div>
        </div>
        <div>
          <label v-if="colors.length > 0">选择颜色：</label>
          <div class="options">
            <button
              v-for="color in colors"
              :key="color"
              @click="selectColor(color)"
              :class="{ selected: selectedColor === color }"
            >
              {{ color }}
            </button>
          </div>
        </div>
        <button class="add-to-cart" @click="addToCart">加入购物车</button>
      </div>
    </div>
  </div>
</template>

<script>
import MenuBar from '@/components/MenuBar.vue';
import { get } from '@/api/address.js';
import { checkIsInCart, add } from '@/api/cart';
import { getProducts } from '@/api/product';

export default {
    name: 'ProductDetail',
    components: { MenuBar },
    data() {
        return {
            product: {},
            address: [], //动态加载地址信息
            selectedAddress: '',
            versions: [], //动态加载版本信息
            colors: [], //动态加载颜色信息
            username: '',
            selectedVersion: null,
            selectedColor: null,
            staticData: {
                手机: {
                    versions: ['8GB+256GB', '8GB+512GB', '12GB+512GB', '12GB+1TB'],
                    colors: ['玄夜黑', '雪岩白','汐月蓝','山峦青','雪山粉']
                },
                笔记本电脑: {
                    versions: ['i5 8GB+256GB', 'i7 16GB+512GB','i7 32GB+1TB', 'i9 32GB+1TB'],
                    colors: []
                },
                平板:{
                    versions: ['8GB+128GB','8GB+256GB',  '12GB+512GB'],
                    colors: ['玄夜黑', '雪岩白','汐月蓝','山峦青']
                },
                智能穿戴:{
                    versions: [],
                    colors: ['子夜黑', '晴雪白','雨雾蓝', '暮光金']
                },
                家居:{
                    versions: [],
                    colors: ['子夜黑', '晴雪白','雨雾蓝', '暮光金']
                }
            }
        }
    },
    //载入数据
    created() {
        const productId = this.$route.query.product_id;
        // 获取用户名
        this.username = localStorage.getItem('username');
        if(productId) {
            this.fetchProduct(productId);
            this.fetchAddress(this.username);
        }else{
            alert('商品不存在');
            this.$router.push('/main');
        }
    },
    methods: {
        // 获取商品信息
        async fetchProduct(product_id) {
            try{
                const response = await getProducts(product_id);
                if(response.data.success) {
                    this.product = response.data.data;

                    // 根据商品类型设置版本和颜色
                    const keyword = this.product.keyword;
                    if(this.staticData[keyword]) {
                        this.versions = this.staticData[keyword].versions;
                        this.colors = this.staticData[keyword].colors;
                    }
                }else{
                    alert(response.data.message);
                }
            }catch(error) {
                alert(error.message);
            }
        },
        // 获取用户地址
        async fetchAddress(username){
            try{
                const response = await get(username);
                if(response.data.success) {
                    this.address = response.data.data;
                    this.selectedAddress = this.address[0];
                }else{
                    alert(response.data.message);
                }
            }catch(error) {
                alert(error.message);
            }
        },
        selectVersion(version) {
            this.selectedVersion = version;
        },
        selectColor(color) {
            this.selectedColor = color;
        },
        async addToCart() {
            // 检测是否填写了地址
            if(this.selectedAddress.length == 0) {
                alert("请先填写地址！");
                this.$router.push("/main/user/address");
                return;
            }
            // 检测商品是否已经购买过，如果已经购买过，则不能重复购买
            var data = {
                username: this.username,
                product_id: this.product.id,
            };
            const checkRes =await checkIsInCart(data);
            if(checkRes.data.success) {
                alert("该商品已在购物车中，请勿重复添加！");
                return;
            }else{
                try{
                    data = {
                        ...data,
                        address: this.selectedAddress,
                    };
                    const response = await add(data);
                    if(response.data.success) {
                        alert("商品已加入到购物车中!");
                    }else{
                        alert(response.data.message);
                    }
                }catch(error) {
                    alert(error.message);
                }
            }
            
        },
    },
}
</script>

<style scoped>
.product-detail {
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 40px;
  outline: none; /* 去掉聚焦时的边框 */
  user-select: none; /* 禁止文本被选中 */
}

.container {
  display: flex;
  max-width: 1500px;
  margin: 0 auto;
  gap: 60px;
}

.image-section {
  flex: 2;
}

.product-image {
  max-width: 90%;
  border: 1px solid #ccc;
  border-radius: 10px;
}

.info-section {
  flex: 2;
  display: flex;
  flex-direction: column;
  gap: 40px;
}
.options button {
  margin-right: 10px;
  padding: 10px 15px;
  border: 1px solid #ccc;
  background-color: white;
  cursor: pointer;
}

.options button.selected {
  border: 2px solid orange;
  font-weight: bold;
}

.add-to-cart {
  padding: 10px 20px;
  background-color: orange;
  border: none;
  color: white;
  cursor: pointer;
}
.price{
  color: #e91e63;
  font-weight: bold;
}
.address {
  font-size: 1rem;
}
.address-select {
  padding: 10px;
  font-size: 1rem;
  margin-left: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>