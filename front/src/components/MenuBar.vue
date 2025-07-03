<!-- 菜单栏 -->
<template>
  <div class="menu-bar">
    <ul class="menu">
      <li class="menu-item">
        <router-link to="/main">
          <img src="../imag/xiaomi.png" alt="Logo" class="logo" />
        </router-link>
      </li>
      
      <li class="menu-item">
        <router-link to="/main">首页</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/main/search?keyword=手机">手机</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/main/search?keyword=家居">家居</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/main/search?keyword=笔记本电脑">笔记本电脑</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/main/search?keyword=平板">平板</router-link>
      </li>
      <li class="menu-item">
        <router-link to="/main/search?keyword=智能穿戴">智能穿戴</router-link>
      </li>

      <!-- 右侧菜单 -->
      <div class="menu-right">
        <li class="menu-item">
          <router-link to="/main/buy/cart">
            购物车
          </router-link>
        </li>
        <li class="menu-item dropdown">
          <span v-if="username">{{ username }}  ﹀</span>
          <span v-else>用户栏  ﹀</span>
          <ul class="dropdown-menu">
            <li><a href="/main/user/orders">我的订单</a></li>
            <li><a href="/main/user/address">地址管理</a></li>
            <li><a href="#logout" @click="logout">退出登录</a></li>
          </ul>
        </li>
      </div>
    </ul>
  </div>
</template>

<script>
export default {
    name: 'MenuBar',
    data() {
        return{
            username: '',
        }
    },
    methods: {
        logout() {
            localStorage.removeItem('username')
            this.$router.push('/login')
        }
    },
    created() {
        this.username = localStorage.getItem('username')
    }
}
</script>

<style scoped>
 .menu-bar {
  background-color: #333;
  color: #fff;
  padding: 0;
  margin: 0;
  position: sticky;
  top: 0;
  z-index: 1000;
  display: flex;
  align-items: center;
  outline: none; /* 去掉聚焦时的边框 */
  user-select: none; /* 禁止文本被选中 */
}

.menu {
  list-style: none;
  display: flex;
  flex: 1; /* 占据剩余空间 */
  align-items: center;
  padding: 0;
  margin: 0;
}

.menu-item {
  padding: 15px 20px;
  font-size: 18px; /* 字体放大 */
  position: relative;
}

.menu-item a {
  color: #fff;
  text-decoration: none;
}

.menu-right {
  display: flex;
  align-items: center;
  /* 离左边尽可能远 */
  margin-left: auto;
}
.dropdown:hover .dropdown-menu {
  display: block;
}

.dropdown-menu {
  list-style: none;
  background-color: #444;
  padding: 10px 0;
  position: absolute;
  top: 100%;
  left: 0;
  display: none;
  min-width: 150px;
  border-radius: 4px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.dropdown-menu li {
  padding: 10px 20px;
}

.dropdown-menu li a {
  color: #fff;
  text-decoration: none;
  display: block;
}

.dropdown-menu li a:hover {
  background-color: #555;
}
</style>
