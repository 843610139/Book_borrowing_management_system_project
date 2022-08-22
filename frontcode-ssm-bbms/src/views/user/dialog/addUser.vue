<template>
  <el-dialog
  :visible.sync="isShow"
  :title="title"
  center
  @close="closeDialog"
  @open="openDialog">
    <el-card>
      <el-form
      ref="addData" :model="addData" :rules="rules" label-width="100px" label-position="left">
      <el-row
      :gutter="30">
      <el-col
      :span="12">
      <el-form-item prop="userName" label="账号">
        <el-input v-model="addData.userName" placeholder="请输入账号" :disabled="isDisabled" @blur="userNameCheck"></el-input>
      </el-form-item>
      </el-col>
        <el-col :span="12">
          <el-form-item prop="password" label="密码">
            <el-input
              :key='passwordType'
              ref="password"
              v-model="addData.password"
              :type="passwordType"
              name="password"
              tabindex="2"
              autocomplete="on"
              placeholder="请输入密码"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item prop="uName" label="用户名">
              <el-input v-model="addData.uName" placeholder="请输入用户名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item prop="age" label="年龄">
              <el-input v-model="addData.age" placeholder="请输入年龄"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="30">
          <el-col :span="12">
            <el-form-item prop="uId" label="用户id">
              <el-input v-model="addData.uId" placeholder="用户id" :disabled="isDisabled"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
           <el-form-item prop="sex" label="性别">
            <el-select v-model="addData.sex">
              <el-option v-for="item in sexSelect" :key="item.key"
                         :label="item.value"
                         :value="item.key">
              </el-option>
            </el-select>
           </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
    <div style="text-align: right">
      <el-button size="mini" @click="closeDialog">取消</el-button>
      <el-button size="mini" @click="addUser">确认</el-button>
    </div>
  </el-dialog>
</template>

<script>
import {commonAPI} from '@/api/commonAPI'
import crypto from 'crypto'
export default {
  props: {
    option: Object
  },
  data () {
    const checkuser = (rule, value, callback) => {
      console.log(this.alreadyUser)
      if (value === this.alreadyUser) {
        callback(new Error('用户已存在'))
      } else {
        callback()
      }
    }
    return {
      passwordType: 'password',
      sexSelect: [
        { key: 0, value: '男' },
        { key: 1, value: '女'}
      ],
      tableData: {
        userName: ''
      },
      addData: {
        userName: '',
        passWord: '',
        password: '',
        uId: '',
        uName: '',
        age: '',
        sex: '',
        pageNum: '',
        pageSize: 10
      },
      formInline: {
        userName: '',
        sex: '',
        pageNum: 1,
        pageSize: 10
      },
      disabled: this.$store.getters.getToken !== 'admin',
      isDisabled: '',
      flag: false,
      alreadyUser: '',
      title: '',
      rules: {
        userName: [{ required: true, message: '请输入账号', trigger: 'blur'}],
        passWord: [{ required: true, message: '请输入密码', trigger: 'blur'},
          {required: true, message: '密码不能小于5位', trigger: 'change', min: 5}],
        uName: [{ required: true, message: '请输入用户名', trigger: 'blur'}],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur'}],
        uId: [{ required: true, message: '请输入UID', trigger: 'blur'}],
        sex: [{ required: true, message: '请输入性别', trigger: 'blur'}]
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
      this.title = this.option.title
      this.isDisabled = this.option.isDisabled
      this.addData = JSON.parse(JSON.stringify(this.option.tableData))
    },
    userNameCheck () {
      this.loading = true
        commonAPI('queryUserList', this.formInline)
          .then(res => {
        this.loading = false
          this.tableData = res.data.rows
          for (let i = 0;i < this.tableData.length;i++){
            if (this.addData.userName === this.tableData[i].userName){
              this.$message.error('账号已存在')
              this.alreadyUser = this.tableData[i].userName
              this.flag = true
              break
            }
          }
      })
    },
    closeDialog () {
      this.$refs.addData.resetFields()
      this.flag = false
      this.option.isShow = false
      this.$emit('refreshTable')
    },
    addUser () {
      if (this.flag===false){
        this.$refs.addData.validate(valid => {
          if (valid){
            const md5 = crypto.createHash('md5')
            md5.update(this.addData.password)// 对该对象进行MD5加密
            this.addData.passWord = md5.digest('hex')// 采用hex方式并把结果给等号左边
            if (this.option.isDisabled === false){
              this.loading = true
              commonAPI('addUser', this.addData).then(res => {
                  this.loading = false
                let data = res.data
                if (data === 'ok') {
                  this.option.isShow = false
                  this.$message({
                    message: '新增成功',
                    type: 'success'
                  })
                } else {
                  this.$message({
                    message: '新增失败',
                    type: 'warning'
                  })
                }
              })
            } else {
              this.loading = true
              commonAPI('updateUserByName', this.addData)
              .then(res => {
                this.loading = false
                let data = res.data
                if (data === 'ok') {
                  this.option.isShow = false
                  this.$message({
                    message: '修改成功',
                    type: 'success'
                  })
                } else {
                  this.$message({
                    message: '修改失败',
                    type: 'warning'
                  })
                }
              })
            }
          }
        })
      } else {
        this.$message.error('账号已存在')
      }
    }
  }
}
</script>

<style scoped>

</style>
