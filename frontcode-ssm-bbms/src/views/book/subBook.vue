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
          <el-button @click="onSubmit"><i class="el-icon-search"></i>查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="danger" @click="resetting"><i class="el-icon-refresh"></i>重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card class="elMain" style="">
      <el-button @click="openDialog" type="primary" size="mini" style="margin-bottom: 10px"><i class="outline
el-icon-circle-plus-outline"></i>打印</el-button>
      <el-table v-loading="loading" :data="tableData" stripe border size="mini">
        <el-table-column  align="center" label="图书编号" prop="bId"></el-table-column>
        <el-table-column align="center" label="图书名" prop="bookName"></el-table-column>
        <el-table-column align="center" label="作者名" prop="author"></el-table-column>
        <el-table-column align="center" label="价格" prop="price"></el-table-column>
        <el-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="getDate(scope)">借阅</el-button>
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
    </el-footer>
    <printSubBook :option="printDialog"></printSubBook>
  </el-container>
</template>

<script>
import { commonAPI } from '@/api/commonAPI'
import printSubBook from '@/views/book/dialog/printSubBook'
export default {
  components: { printSubBook },
  data () {
    return {
      tableData: [],
      formQuery: {
        bId: '',
        bookName: '',
        author: '',
        inventory: '',
        price: '',
        pageNum: 1,
        pageSize: 10
      },
      pageSizes: [5, 10, 15, 20],
      pageTotal: 0,
      total: 0,
      subManage: {
        uId: '',
        bId: '',
        subDateTime: '',
        bookType: 1,
        returnType: 0 // 0是未归还 1是已归还
      },
      printDialog: {
        isShow: false,
        flag: '',
        tableData: [],
        title: ''
      },
      isShow: false
    }
  },
  methods: {
    handleSizeChange(val) {
      this.formQuery.pageSize = val
      this.formQuery.pageNum = 1
      this.getData()
    },
    handleCurrentChange (val) {
      this.formQuery.pageNum = val
      this.getData()
    },
    getData () {
      this.loading = true
      commonAPI('queryBookList', this.formQuery).then(
        res => {
          this.tableData = res.data.rows
          this.pageTotal = res.data.total
          this.loading = false
        }
      )
    },
    onSubmit () {
      this.getData()
    },
    resetting () {
      this.formQuery.bookName = ''
      this.formQuery.author = ''
      this.getData()
    },
    getDate (val) {
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

      commonAPI('queryBookSubList', {uId: this.$store.state.uId, bId: val.row.bId, pageNum: 1, pageSize: 10})
        .then(res => {
          console.log(res)
            let i = res.data.rows.length - 1
            //  先判断是否已经借过这本书并归还

            if (res.data.rows[i].bookType ===1){
              this.$message({
                message: '已借阅此书,上次借阅时间：'+ res.data.rows[i].subDateTime,
                type: 'warning'
              })
            } else if (val.row.inventory <= 0) {
              this.$message({
                message: '书籍没有库存',
                type: 'warning'
              })
            } else {
              this.subBook(val, time)
            }
      }).catch(error => {
        console.log('error')
        console.log(error)
        this.subBook(val, time)
      })
    },
    subBook (val, time) {
      this.loading = true
      // 用户Id
      this.subManage.uId = this.$store.getters.getUId
      // 图书Id
      this.subManage.bId = val.row.bId
      // 图书借阅时间
      this.subManage.subDateTime = time
      commonAPI('addSubBook', this.subManage).then(
        res => {
        // 减库存
          let total = val.row.inventory - 1
          // 更新库存
          commonAPI('updateInventory', {inventory: total, bId: val.row.bId}).then(
            res2 => {
              this.loading = false
              if (res2.data === 'ok') {
                this.$message({
                  message: '借阅成功，请记得归还',
                  type: 'success'
                })
              } else {
                this.$message({
                  message: '借阅失败',
                  type: 'warning'
                })
              }
            }
          )
          this.getData()
        }
      ).catch(error => {
        console.log('error')
        console.log(error)
        this.loading = false
        this.$notify({
          // 采用element UI 的一个错误显示效果模板
          title: '温馨提示',
          message: '借阅失败,请联系管理员',
          position: 'bottom-right',
          type: 'error'
        })
      })

    },
    openDialog(){
      this.printDialog.isShow= true
      this.printDialog.tableData = this.tableData
    }
  },
  created () {
    this.getData()
  }
}
</script>

<style scoped>

</style>
