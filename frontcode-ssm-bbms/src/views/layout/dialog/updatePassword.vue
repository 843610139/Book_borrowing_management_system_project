<template>
  <el-dialog
  :visible.sync="isShow"
  title="修改密码"
  center
  @open="openDialog"
  @close="closeDialog"
  >
  <el-form
    ref="loginData"
    :model="loginData"
    label-width="130px"
    :rules="rules"
  >
  <el-form-item label="新密码: " prop="password">
    <el-input v-model="loginData.password" type="password" placeholder="请输入密码"
              autocomplete="on" name="password"></el-input>
  </el-form-item>
    <el-form-item
    label="请再输入密码" prop="passWord">
    <el-input v-model="loginData.passWord" type="password" placeholder="请输入密码"
              autocomplete="on" name="passWord"></el-input>
    </el-form-item>
  <el-form-item style="text-align: right">
    <el-button
    type="primary"
    @click="login">修改密码</el-button>
    <el-button
      @click="resetForm">重置密码</el-button>
  </el-form-item>
    </el-form>
  </el-dialog>
</template>
<script>
import { commonAPI } from '@/api/commonAPI'
import crypto from 'crypto'
export default {
  props: {
    option: Object
  },
  data () {
    const validatePwd = (rule, value, callback) => {
      if (value !== this.loginData.password) {
        callback(new Error('俩次密码不一样'))
      } else {
        callback()
      }
    }
   return {
      title: '',
     loginData: {
        userName: '',
       password: '',
       passWord: ''
     },
     flag: false,
     msg: '',
     rules: {
        userName: [{ required: true, message: '请输入用户名', trigger: 'blur'}],
       password: [{ required: true, message: '请输入密码', trigger: 'blur' },
                  { min: 6, message: '不得少于6个字符', trigger: 'blur'}],
       passWord: [{ required: true, validator: validatePwd }]
     }
   }
  },
  computed: {
    isShow: {
      // 修改用户弹窗是否显示
      get: function () {
        return this.option.isShow
      },
      set: function (newValue) {
        this.option.isShow = newValue
      }
    }
  },
  methods: {
    openDialog () {

      this.loginData.userName = this.$store.getters.getUserName

    },
    closeDialog () {
      this.option.isShow = false
      this.$refs.loginData.resetFields()
      this.regiestData = {
        userName: '',
        password: ''
      }
    },
    resetForm () {
      this.$refs.loginData.resetFields()
    },
    login () {

      this.$refs.loginData.validate(valid => {
        if (valid) {
          this.$confirm('此操作将修改密码, 是否继续？', '提示', {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning' })
          .then(() => {
            this.option.isShow = false
            const md5 = crypto.createHash('md5')
            md5.update(this.loginData.passWord)
            this.loginData.passWord = md5.digest('hex')
            commonAPI('updateUserPassword', this.loginData).then(res => {
              let data = res.data
              if (data === 'ok') {
                this.$message({
                  type: 'success',
                  message: '修改成功'
                })
              } else {
                this.$message({
                  type: 'warning',
                  message: '修改失败'
                })
              }
            })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消修改'
            })
          })
        }
      })

    }
  }
}




</script>
