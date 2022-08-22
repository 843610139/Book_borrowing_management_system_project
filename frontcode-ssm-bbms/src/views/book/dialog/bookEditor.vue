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
              <el-form-item prop="bookName" label="书名">
                <el-input v-model="addData.bookName" placeholder="请输入书名" @blur="bookNameCheck"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="author" label="作者">
                <el-input v-model="addData.author" placeholder="请输入作者"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row :gutter="30">
            <el-col :span="12">
              <el-form-item prop="inventory" label="库存量">
                <el-input v-model="addData.inventory" placeholder="请输入库存量"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item prop="price" label="单价">
                <el-input v-model="addData.price" placeholder="请输入单价"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </el-card>
      <div style="text-align: right">
        <el-button size="mini" @click="closeDialog">取消</el-button>
        <el-button size="mini" @click="addBook">确认</el-button>
      </div>
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
    return {
      addData: {
        bookName: '',
        author: '',
        inventory: '',
        price: '',
        pageNum: '',
        pageSize: 10
      },
      rules: {
        bookName: [{ required: true, message: '请输入书名', trigger: 'blur'}],
        author: [{ required: true, message: '请输入作者', trigger: 'blur'}],
        inventory: [{ required: true, message: '请输入库存量', trigger: 'blur'}],
        price: [{ required: true, message: '请输入单价', trigger: 'blur'}]
      },
      formInline: {
        bookName: '',
        author: '',
        pageNum: 1,
        pageSize: 10
      },
      tableData: {
        bookName: ''
      },
      alreadyBook: '',
      isDisabled: '',
      flag: false,
      title: ''
    }
  },
  computed: {
    isShow: {
      // 新增图书弹窗是否显示
      get: function () {
        return this.option.isShow
      },
      set: function (newValue) {
        this.option.isShow = newValue
      }
    }
  },
  methods: {
    bookNameCheck () {
      this.loading = true
      commonAPI('queryBookList', this.formInline)
        .then(res => {
          this.loading = false
          this.tableData = res.data.rows
          for (let i = 0;i < this.tableData.length;i++){
            if (this.addData.bookName === this.tableData[i].bookName){
              this.$message.error('书籍已存在')
              this.alreadyBook = this.tableData[i].userName
              this.flag = true
              break
            }
          }
        })
    },
    openDialog () {
      this.title = this.option.title
      this.isDisabled = this.option.isDisabled
      this.addData = JSON.parse(JSON.stringify(this.option.tableData))
    },
    closeDialog () {
      this.$refs.addData.resetFields()
      this.flag = false
      this.option.isShow = false
      this.$emit('refreshTable')
    },
    addBook () {
      if (this.flag===false){
        this.$refs.addData.validate(valid => {
          if (valid){
            if (this.option.isDisabled === false){
              this.loading = true
              commonAPI('addBook', this.addData).then(res => {
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
              commonAPI('editBook', this.addData)
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
        this.$message.error('书名已存在')
      }
    }
  }

}
</script>

<style scoped>

</style>
