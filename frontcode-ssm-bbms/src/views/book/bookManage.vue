<template>
  <el-container>
    <el-card style="margin-top: 20px">
      <el-form :inline="true" :model="formQuery" class="demo-form-inline" size="mini" label-width="80px">
        <el-form-item label="书名">
          <el-input v-model="formQuery.bookName" placeholder="书名"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="formQuery.author" placeholder="作者"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit"><i class="el-icon-search"></i>查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" @click="resetting"><i class="el-icon-refresh"></i>重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="elMain" style="">
      <el-button type="primary" size="mini" style="margin-bottom: 10px" @click="bookManage()"><i class="outline
el-icon-circle-plus-outline"></i>新增</el-button>
      <el-table v-loading="loading" :data="tableData" stripe border size="mini">
        <el-table-column align="center" label="编号" prop="bId"></el-table-column>
        <el-table-column align="center" label="书名" prop="bookName"></el-table-column>
        <el-table-column align="center" label="作者" prop="author"></el-table-column>
        <el-table-column align="center" label="库存" prop="inventory"></el-table-column>
        <el-table-column align="center" label="单价" prop="price"></el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="bookManage(scope)">编辑</el-button>
            <el-button size="mini" :disabled="disabled" @click="delBook(scope)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-footer>
      <div style="padding: 15px 0;text-align: right;">
        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="1" :total="pageTotal"
                       :page-sizes="pageSizes" :page-size="formQuery.pageSize" layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
      </div>
      <bookEditor :option="editorBook" @refreshTable="getData"></bookEditor>
    </el-footer>
  </el-container>
</template>

<script>
import { commonAPI } from '@/api/commonAPI'
import bookEditor from '@/views/book/dialog/bookEditor'

export default {
  components: { bookEditor },
  data () {
    return {
      formQuery: {
        bId: '',
        bookName: '',
        author: '',
        inventory: '',
        price: '',
        pageNum: 1,
        pageSize: 10
      },
      tableData: [],
      pageSizes: [5, 10, 15, 20],
      subManage: {
        uId: Number,
        bId: Number,
        bookType: 1,
        subDateTime: ''
      },
      bookTotal: 0, // 1为借阅 2为归还
      pageTotal: 0,
      total: 0,
      editorBook: {
        title: '',
        isShow: false,
        tableData: [],
        isDisabled: false
      },
      disabled: false
    }
  },
  created () {
    this.getData()
  },
  methods: {

    getData () {
      this.loading = true
      commonAPI('queryBookList', this.formQuery).then(
        res => {
          console.log(res)
          this.loading = false
          this.tableData = res.data.rows
          this.pageTotal = res.data.total
        }
      )
    },
    handleSizeChange(val) {
      this.formQuery.pageSize = val
      this.formQuery.pageNum = 1
      this.getData()
    },
    handleCurrentChange (val) {
      this.formQuery.pageNum = val
        this.getData()
    },
    bookManage (val) {
      this.editorBook.isShow = true
      if (val) {
        this.editorBook.title = '编辑书籍'
        this.editorBook.tableData = val.row
          this.editorBook.isDisabled = true
      } else {
        this.editorBook.title = '新增书籍'
        this.editorBook.tableData = {}
        this.editorBook.isDisabled = false
      }
    },
    delBook (val) {
      this.loading = true
      let bid = val.row.bId
      console.log(val)
      commonAPI('delBook', {bId: bid}).then(
        res => {
          this.loading = false
          if (res.data === 'ok') {
            this.$message({
              message: '删除成功',
              type: 'success'
            })
            this.getData()
          } else {
            this.$message({
              message: '删除失败',
              type: 'warning'
            })
          }
        }
      )
    },
    onSubmit () {
      this.getData()
    },
    resetting () {
      this.formQuery.bookName =''
      this.formQuery.author = ''
      this.getData()
    }
  }
}
</script>

<style scoped>

</style>
