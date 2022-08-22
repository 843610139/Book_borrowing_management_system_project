<template>
<el-container>
  <el-card class="elMain" style="">
  <el-table v-loading="loading" :data="newList" stripe border size="mini">
    <el-table-column align="center" label="图书名" prop="bookName"></el-table-column>
    <el-table-column align="center" label="作者名" prop="author"></el-table-column>
    <el-table-column  align="center" label="借阅时间" prop="subDateTime"></el-table-column>
    <el-table-column align="center" label="归还时间" prop="returnDateTime"></el-table-column>
    <el-table-column align="center" label="状态" prop="returnType">
      <template slot-scope="scope">
        <span v-if="scope.row.returnType===0">未归还</span>
        <span v-if="scope.row.returnType===1">已归还</span>
      </template>
    </el-table-column>
    <el-table-column align="center" label="操作">
      <template slot-scope="scope">
        <el-button size="mini" @click="getDate(scope)">归还</el-button>
      </template>
    </el-table-column>
    <el-footer>
      <div style="padding: 15px 0;text-align: right;">
        <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="1" :total="pageTotal"
                       :page-sizes="pageSizes" :page-size="formQuery.pageSize" layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>
      </div>
    </el-footer>
  </el-table>
  </el-card>
  <el-footer>
    <div style="padding: 15px 0;text-align: right;">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="1" :total="pageTotal"
                     :page-sizes="pageSizes" :page-size="formQuery.pageSize" layout="total, sizes, prev, pager, next, jumper">
      </el-pagination>
    </div>
  </el-footer>
</el-container>
</template>

<script>
import { commonAPI } from '@/api/commonAPI'
export default {
  data () {
    return {
      loading: false,
      newList: [],
      pageSize: 0,
      total: 0,
      currentPage: 1,
      tableData: [],
      tableSubData: {
        bookName: [],
        author: []
      },
      pageSizes: [5, 10, 15, 20],
      pageTotal: 0,
      formQuery: {
        bookName: '',
        uId: '',
        pageNum: 1,
        pageSize: 10,
        subDateTime: ''
      }
    }
  },
  methods: {
    getDate (scope) {
      let now = new Date()
      let year = now.getFullYear()
      let mouth = now.getMonth()
      let date = now.getDate()
      let hour = now.getHours()
      let minu = now.getMinutes()
      let sec = now.getSeconds()
      mouth += 1
      if (mouth < 10) mouth = '0'+mouth
      if (date < 10) date = '0'+date
      if (hour < 10) hour = '0'+hour
      if (minu < 10) minu = '0'+minu
      if (sec < 10) sec = '0'+sec
      var time = ''
      time = year+'-'+mouth+'-'+date+'-'+hour+':'+minu+':'+sec
      this.ReturnSubBook(scope, time)
    },
    ReturnSubBook (scope, time) {
      this.loading=true
      let getbId=scope.row.bId
      let getuId=scope.row.uId
      this.formQuery.returnDateTime = time
      commonAPI('returnSubBook', {uId: getuId, bId: getbId, returnType: 1, returnDateTime: this.formQuery.returnDateTime})
      .then(
      res2 => {
        this.loading =false
        if (res2.data === 'ok') {
          this.$message({
            message: '归还成功',
            type: 'success'
          })
          this.getData()
        } else {
          this.$message({
            message: '归还失败',
            type: 'warning'
          })
        }
      }
        )
    },
    getData() {
      this.loading = true
      this.formQuery.uId = this.$store.state.uId
      commonAPI('queryBookSubList', this.formQuery)
        .then(
        res => {
          console.log(res)
          this.loading = false
          this.pageTotal = res.data.total
          this.tableSubData = res.data.rows
          this.total = this.tableSubData.length
          this.newList = this.tableSubData
        })
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
    onSubmit () {

      this.getData()
    },
    resetting () {
      this.formQuery.bookName =''
      this.formQuery.sex = ''
      this.getData()
    }
  },
  created () {
    this.getData()
  }
}
</script>

<style scoped>

</style>
