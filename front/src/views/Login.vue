<template>
<div class="login-page">
  <!-- 标题模块 -->
      <div>
        <img src="../imag/xiaomi.png" alt="logo" class="title-img">
        <span class="title-text">大米商城</span>
      </div>

  <!-- 背景和登录框模块 -->
  <div class="login-body">
    <div class="login-painter">  
      <div class="login-container">  <!--用来包装登陆界面的盒子-->
        <!-- 动态显示登录和注册页面的组件 -->
        <div class="login-box" v-if="!showRegister && !showFrogetPassword"> <!--用来包装用户名和密码的盒子--> 
          <h2>用户登录</h2>
          <form @submit.prevent="handleLogin">
            <div class="form-group">
              <label for="username">用户名</label>
              <input type="text" id="username" v-model="username" placeholder="请输入用户名" required />
            </div>
            <div class="form-group">
              <label for="password">密码</label>
              <input type="password" id="password" v-model="password" placeholder="请输入密码" required />
            </div>
            <button type="submit">登录</button>
          </form>
          <button @click="registerFunction" class="register-button">注册</button>
          <button @click="forgetPasswordFunction" class="forget-password-button">忘记密码</button>
        </div>

        <!-- 注册表单 -->
          <div v-if="showRegister" class="register-box">
            <h2>用户注册</h2>
            <form @submit.prevent="handleRegister">
              <div class="form-group">
                <label for="reg-username">用户名</label>
                <input type="text" id="reg-username" v-model="regUsername" placeholder="请输入用户名" required />
              </div>
              <div class="form-group">
                <label for="reg-password">密码</label>
                <input type="password" id="reg-password" v-model="regPassword" placeholder="请输入密码" required />
              </div>
              <div class="form-group">
                <label for="confirm-password">确认密码</label>
                <input type="password" id="confirm-password" v-model="confirmPassword" placeholder="请再次输入密码" required />
              </div>
              <button type="submit">提交</button>
              <button @click="cancelFunction" type="button" class="cancel-button">取消</button>
            </form>
          </div>

          <!-- 忘记密码表单 -->
          <div v-if="showFrogetPassword" class="register-box">
            <h2>忘记密码</h2>
            <form @submit.prevent="handleForgot">
              <div class="form-group">
                <label for="reg-username">用户名</label>
                <input type="text" id="reg-username" v-model="username" placeholder="请输入用户名" required />
              </div>
              <div class="form-group">
                <label for="reg-password">新密码</label>
                <input type="password" id="forgot-password" v-model="password" placeholder="请输入新密码" required />
              </div>
              <div class="form-group">
                <label for="confirm-password">确认密码</label>
                <input type="password" id="confirm-password" v-model="confirmPassword" placeholder="请再次输入密码" required />
              </div>
              <button type="submit">提交</button>
              <button @click="cancelFunction" type="button" class="cancel-button">取消</button>
            </form>
          </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import { login, register, forgot } from '@/api/login.js'

export default {
  name: 'LoginPage',
  data() {
    return {
      username: '',
      password: '',
      showRegister: false,
      showFrogetPassword: false,
      regUsername: '',
      regPassword: '',
      confirmPassword: '',
    };
  },
  methods: {
    //登录请求
    async handleLogin() {
      if (this.username && this.password) {
        try {
          const data = {
            username: this.username,
            password: this.password
          };
          const response = await login(data);
          if (response.data.success) {
            alert(response.data.message);
            //保存用户名
            localStorage.setItem("username", this.username);
            // 跳转到商城主页面
            this.$router.push("/main");
          } else {
            alert(response.data.message);
          }
        } catch (error) {
          console.error("登录失败:", error);
          alert("登录失败，请稍后再试");
        }
      } else {
        alert("请输入完整的用户名和密码");
      }
    },
    //注册请求
    async handleRegister() {
      if(this.regPassword!= this.confirmPassword){
        alert("两次输入的密码不一致");
        this.confirmPassword = '';
        return;
      }  
      try{
        const data = {
          username: this.regUsername,
          password: this.regPassword
        };
    
        const response = await register(data);
        if(response.data.success){
          alert("注册成功！");
          this.showRegister = false;
        }else if(response.data.message == "该用户已存在，请重新输入"){
          alert("该用户已存在，请重新输入");
        }else{
          alert(response.data.message);
        }
        this.regUsername = '';
        this.regPassword = '';
        this.confirmPassword = '';
      }catch(error){
        console.error("注册失败:", error);
        alert("注册失败，请稍后再试");
      }
    },
    //忘记密码请求
    async handleForgot(){
      if(this.password!= this.confirmPassword){
        alert("两次输入的密码不一致");
        this.password = '';
        this.confirmPassword = '';
        return;
      }
      try{
        const data = {
          username: this.username,
          password: this.password
        };
        const response = await forgot(data);
        if(response.data.success){
          alert("重置密码成功！");
          this.showFrogetPassword = false;
        }else{
          alert(response.data.message);
        }
        this.username = '';
        this.password = '';
        this.confirmPassword = '';
      }catch(error){
        console.error("重置密码失败:", error);
        alert("重置密码失败，请稍后再试");
      }
    },
    registerFunction(){
      this.showRegister = true;
      this.username = '';
      this.password = '';
    },
    cancelFunction(){
      this.showRegister = false;
      this.showFrogetPassword = false;
      this.regUsername = '';
      this.regPassword = '';
      this.confirmPassword = '';
      this.username = '';
      this.password = '';
    },
    forgetPasswordFunction(){
      this.showFrogetPassword = true;
      this.username = '';
      this.password = '';
    },
    mounted () {
      // 加载时先清除本地存储
      localStorage.clear();
    }
  }
};
</script>

<style scoped>
.login-page{
  display: flex;
  flex-direction: column;
  height: 92vh;
}
.login-header{
  height: 8vh; /* 标题部分高度 */
  background-color: white; /* 设置标题背景为白色 */
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 添加阴影 */
}
.login-body{
  flex: 0.8; /* 占据剩余空间 */
  background-image: url('../imag/sysuBackground.png');
  background-size: cover;
  background-position: center;
  display: flex;
  align-items: center;
  justify-content: center;
}
/* 整个页面容器设置背景图片 */
.login-painter {
  margin: 0;
  padding: 0;
  width: 100%;
  height: 92vh;
  background-image: url('../imag/sysuBackground.png');
  background-size: cover; /* 确保图片覆盖整个页面 */
  background-position: center; /* 背景图片居中 */
  background-repeat: no-repeat; /* 防止图片重复 */
}
.login-container {
  
  display: flex;
  /* 盒子中的元素水平居中 */
  justify-content: center; 
  /* 盒子中的元素垂直居中 */
  align-items: center;
  /* 盒子大小 */
  width: 100%;
  height: 90vh;
  /* background-color: #f5f5f5; */
}
.title-img {
  height: 40px; /* 调整图标大小 */
  margin-right: 30px;
  margin-left: 50px; /* 调整图标与文字之间的间距 */
}

.title-text {
  font-size: 40px;
  font-weight: bold;
  color: #333;
  
}
.titleimg-box {
  height: 30px; /* 设置与文字相同的高度 */
  margin-right: 30px; /* 图片与文字之间的间距 */
}
.login-box {
  background: white;
  padding: 20px 40px;
  /* 方圆弧形边框 */
  border-radius: 8px;
  /* 盒子边框阴影 */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  /* 盒子宽度 */
  width: 300px;
  text-align: center;
}

h2 {
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  margin-bottom: 30px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-sizing: border-box;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
.register-box {
  background: white;
  padding: 20px 40px;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  width: 300px;
  text-align: center;
  margin-top: 20px;
}

.register-button {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.register-button:hover {
  background-color: #218838;
}

.cancel-button {
  width: 100%;
  padding: 10px;
  background-color: #7a7172;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}

.cancel-button:hover {
  background-color: black;
}
.forget-password-button {
  width: 100%;
  padding: 10px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}
.forget-password-button:hover{
  background-color: #218838;
}

</style>
