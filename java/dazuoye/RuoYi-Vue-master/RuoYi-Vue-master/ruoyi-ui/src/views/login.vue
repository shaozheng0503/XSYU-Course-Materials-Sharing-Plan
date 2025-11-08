<template>
  <div class="login-container">
    <div class="login-box animated fadeInUp">
      <div class="logo">
        <img src="@/assets/images/logo.png" alt="Logo" />
      </div>
      <h3 class="title">医保中药目录后台管理系统</h3>
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" @submit.native.prevent>
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="账号" prefix-icon="el-icon-user" @input="validateInput('username')"></el-input>
          <span v-if="usernameError" class="error-message">{{ usernameError }}</span>
        </el-form-item>
        <el-form-item prop="password">
          <el-input 
            v-model="loginForm.password" 
            type="password" 
            placeholder="密码" 
            prefix-icon="el-icon-lock"
            @keyup.enter.native="handleLogin"
            @input="validateInput('password')"
          ></el-input>
          <span v-if="passwordError" class="error-message">{{ passwordError }}</span>
        </el-form-item>
        <el-form-item prop="code" v-if="captchaEnabled">
          <el-input 
            v-model="loginForm.code" 
            placeholder="验证码" 
            style="width: 60%"
            prefix-icon="el-icon-key"
            @keyup.enter.native="handleLogin"
            @input="validateInput('code')"
          >
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" class="login-code-img" />
          </div>
          <span v-if="codeError" class="error-message">{{ codeError }}</span>
        </el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" class="remember-me">记住密码</el-checkbox>
        <el-button 
          :loading="loading" 
          type="primary" 
          class="login-btn"
          @click.native.prevent="handleLogin"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
         
        <div class="footer-links">
          <router-link to="/forgot-password">忘记密码？</router-link>
          <router-link to="/register">立即注册</router-link>
        </div>
      </el-form>
    </div>
    <footer class="el-login-footer">
      <p>&copy; 2024 陕西省西安市鄠邑区沣京大道18号</p>
    </footer>
    <div v-if="loading" class="loading-overlay">
      <el-loading :fullscreen="true" />
    </div>
  </div>
</template>
 
<script>
import { getCodeImg } from "@/api/login";
import Cookies from "js-cookie";
import { encrypt, decrypt } from '@/utils/jsencrypt';
 
export default {
  name: "Login",
  data() {
    return {
      codeUrl: "",
      loginForm: {
        username: "admin",
        password: "admin123",
        rememberMe: false,
        code: "",
        uuid: ""
      },
      loginRules: {
        username: [{ required: true, trigger: "blur", message: "请输入您的账号" }],
        password: [{ required: true, trigger: "blur", message: "请输入您的密码" }],
        code: [{ required: true, trigger: "change", message: "请输入验证码" }]
      },
      loading: false,
      captchaEnabled: true,
      register: false,
      redirect: undefined,
      usernameError: '',
      passwordError: '',
      codeError: ''
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created() {
    this.getCode();
    this.getCookie();
  },
  methods: {
    validateInput(field) {
      // 这里可以添加更复杂的验证逻辑，临时示例为非空验证
      if (field === 'username' && !this.loginForm.username) {
        this.usernameError = '账号不能为空';
      } else {
        this.usernameError = '';
      }
      if (field === 'password' && !this.loginForm.password) {
        this.passwordError = '密码不能为空';
      } else {
        this.passwordError = '';
      }
      if (field === 'code' && this.captchaEnabled && !this.loginForm.code) {
        this.codeError = '验证码不能为空';
      } else {
        this.codeError = '';
      }
    },
    getCode() {
      // 获取验证码图片并设置状态
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled;
        if (this.captchaEnabled) {
          this.codeUrl = "data:image/gif;base64," + res.img;
          this.loginForm.uuid = res.uuid;
        }
      });
    },
    getCookie() {
      // 从 cookie 中获取用户信息
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get('rememberMe');
      this.loginForm = {
        username: username || this.loginForm.username,
        password: password ? decrypt(password) : this.loginForm.password,
        rememberMe: rememberMe !== undefined ? Boolean(rememberMe) : false
      };
    },
    handleLogin() {
      // 验证表单是否通过
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, { expires: 30 });
            Cookies.set("password", encrypt(this.loginForm.password), { expires: 30 });
            Cookies.set('rememberMe', this.loginForm.rememberMe, { expires: 30 });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove('rememberMe');
          }
          this.$store.dispatch("Login", this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || "/" }).catch(()=>{});
          }).catch(() => {
            this.loading = false;
            if (this.captchaEnabled) {
              this.getCode();
            }
          });
        }
      });
    }
  }
};
</script>
 
<style scoped lang="scss">
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');
 
$main-color: #409EFF;
$background-color: #ECF5FF;
$shadow-color: rgba(0, 0, 0, 0.1);
 
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: $background-color;
  font-family: 'Roboto', sans-serif;
 
  .login-box {
    background-color: white;
    border-radius: 10px;
    box-shadow: 0 10px 20px $shadow-color;
    padding: 40px;
    width: 400px;
    text-align: center;
    animation: fadeIn 0.5s;
 
    .logo {
      margin-bottom: 20px;
 
      img {
        max-width: 100%;
        height: auto;
      }
    }
 
    .title {
      color: $main-color;
      margin-bottom: 20px;
      font-weight: 700;
      font-size: 1.5em;
    }
 
    .el-form-item {
      margin-bottom: 20px;
    }
 
    .remember-me {
      margin-bottom: 20px;
    }
 
    .login-btn {
      width: 100%;
      font-size: 16px;
      margin-top: 20px;
      background: $main-color;
      border: none;
       
      &:hover {
        background: darken($main-color, 10%);
      }
    }
 
    .footer-links {
      margin-top: 20px;
      display: flex;
      justify-content: space-between;
 
      a {
        color: $main-color;
        text-decoration: none;
        font-size: 0.9em;
 
        &:hover {
          text-decoration: underline;
        }
      }
    }
 
    .error-message {
      color: red;
      font-size: 0.8em;
      margin-top: 5px;
    }
  }
}
 
.el-login-footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  text-align: center;
  padding: 10px 0;
  background-color: $background-color;
   
  p {
    margin: 0;
    color: #606266;
  }
}
 
.loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.8);
  z-index: 999;
}
 
@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}
</style>