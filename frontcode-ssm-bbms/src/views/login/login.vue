<template>
  <div class="login-container">
    <el-form
    ref="loginForm"
    :model="loginForm"
    :rules="loginRules"
    class="login-form"
    autocomplete="on"
    label-position="left">
    <div class="title-container">
      <h3 class="title">登&nbsp;&nbsp;录&nbsp;&nbsp;界&nbsp;&nbsp;面</h3>
    </div>
    <el-form-item prop="userName">
      <span class="svg-container">
        <i class="el-icon-user-solid"></i>
      </span>
      <el-input
      ref="userName"
      v-model="loginForm.userName"
      placeholder="用户名"
      name="userName"
      type="text"
      tabindex="1"
      autocomplete="on"/>
    </el-form-item>
    <el-tooltip
    v-model="capsTooltip"
    content="大写开启"
    placement="right"
    manual
    >
      <el-form-item>
        <span class="svg-container">
          <i class="el-icon-lock"></i>
        </span>
        <el-input
        :key="passwordType"
        ref="password"
        v-model="loginForm.password"
        :type="passwordType"
        placeholder="密码"
        name="password"
        tabindex="2"
        autocomplete="on"
        @keyup.native="checkCapslock"
        @blur="capsTooltip = false"
        @keyup.enter.native="handleLogin"
        />
        <span
        class="show-pwd"
        @click="showPwd">
          <i class="el-icon-view"></i>
        </span>
      </el-form-item>
    </el-tooltip>
    <el-button
    :loading="loading"
    type="primary"
    style="width: 100%"
    @click="handleLogin">
    登录
    </el-button>
    </el-form>

  </div>
</template>

<script>
import { commonAPI } from '@/api/commonAPI'
import layout from '@/views/layout/Layout'
import crypto from 'crypto'
import Vue from 'vue'
export default {
  components: { [layout.name]: layout },
  data () {
    return {
      loginForm: {
        userName: '',
        password: '',
        passWord: ''
        // VerificationCode: ''
      },
      capsTooltip: false,
      passwordType: 'password',
      loading: false,
      loginRules: {
        userName: [
          {
            required: true,
            trigger: 'blur',
            message: '请填写用户名'
          }
        ],
        password: [
          {
            required: true,
            trigger: 'blur',
            message: '请填写密码'
          },
          {type: 'string', min: 6, message: '密码至少为6位', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    checkCapslock ({ shiftKey, key } = {}) {
      console.log('shiftKey', shiftKey)
      console.log('key', key)
      if (key && key.length === 1) {
        if (
          (shiftKey && (key >= 'a' && key <= 'z')) ||
          (!shiftKey && (key >= 'A' && key <= 'Z'))
        ) {
          this.capsTooltip = true
        } else {
          this.capsTooltip = false
        }
      }
      if (key === 'CapsLock' && this.capsTooltip === true) {
        this.capsTooltip = false
      }
    },
    showPwd (e) {
      console.log(e)
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      const e1 = e.target
      this.passwordType === ''
        ? e1.setAttribute('style', 'color: #409EFF')
        : e1.setAttribute('style', 'color: #c0c4cc')
    },
    handleLogin: function () {
      this.$refs.loginForm.validate(valid => {
    if(valid) {
      this.loading = true
      const md5 = crypto.createHash('md5')
      md5.update(this.loginForm.password)// 对括号内对象进行加密
      this.loginForm.passWord = md5.digest('hex')// 把加密结果赋予等号左边对象
    commonAPI('queryUser', this.loginForm)
      .then(res => {
        let data = res.data
    if (data != null && data.status === 'ok' ) {
      this.$message.success('登录成功');

      this.$store.commit('saveToken', res.data.token)
      this.$store.commit('changeUserName', this.loginForm.userName)
    setTimeout(() => {
    this.$router.push({ name: 'layoutYHGL' })
    }, 2000)
    } else {
      this.$message({
      message: '用户名或者密码错误',
      type: 'warning'
  })
      this.loading = false
  }
  })
      .catch(error => {
        this.loading = false
        this.$notify({
        title: '登录提示',
        message: error.message,
        position: 'bottom-right',
        type: 'error'
      })
    })
  } else {
    console.log('error submit!!')
    return false
  }
  })
  }
  }
}

</script>
<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>
<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 5px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 16px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .code {
    position: absolute;
    right: 0px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>



