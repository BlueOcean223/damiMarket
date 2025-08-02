import { createRouter, createWebHistory } from "vue-router";
import LoginPage from "../views/Login.vue";
import MainPage from "../views/MainPage.vue";
import SearchPage from "../views/SearchPage.vue";
import ProductDetail from "../views/ProductDetail.vue";
import CartPage from "@/views/CartPage.vue";
import MyAddressPage from "@/views/MyAddressPage.vue";
import UserOrdersPage from "@/views/UserOrdersPage.vue";

const routes = [
  { path: "/" , redirect: "/login"},//默认路由
  { path: "/login", component: LoginPage },
  { path: "/main",component: MainPage},
  { path: "/main/search", component: SearchPage },
  { path: "/main/buy/detail",component: ProductDetail },
  { path: "/main/buy/cart", component: CartPage },
  { path: "/main/user/address", component: MyAddressPage },
  { path: "/main/user/orders", component: UserOrdersPage },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 路由守卫，确保用户未登录时无法访问其他页面
// router.beforeEach((to, from, next) => {
//     const isLogin = localStorage.getItem("username");
//     if (isLogin == null && to.path !== "/login") {
//       next("/login");
//     } else if(isLogin != null && to.path === "/login") {
//       next("/main");
//     }else{
//       next();
//     }
//   });

export default router;
