<template>
  <el-dialog
    :visible.sync="isShow"
    :title="title"
    center
    @close="closeDialog"
    @open="openDialog">
    <el-card class="elMain" style="">
      <el-button  type="primary" size="mini" style="margin-bottom: 10px"><i class="outline
el-icon-circle-plus-outline"></i>打印</el-button>
      <el-table v-loading="loading" :data="tableData" stripe border size="mini">
        <el-table-column  align="center" label="图书编号" prop="bId"></el-table-column>
        <el-table-column align="center" label="图书名" prop="bookName"></el-table-column>
        <el-table-column align="center" label="作者名" prop="author"></el-table-column>
        <el-table-column align="center" label="价格" prop="price"></el-table-column>
      </el-table>
    </el-card>
  </el-dialog>
</template>

<script>
import { commonAPI } from '@/api/commonAPI'

export default {
  props: {
    option: Object
  },
  data () {
    return {
      tableData: [],
      tableSubData: [],
      loading: false,
      title: '',
      formQuery: {
        uId: 0,
        bookType: 1,
        pageNum: 1,
        pageSize: 10
      },
      pageSizes: [5, 10, 15, 20],
      pageTotal: 0,
      total: 0,
      pageSize: 0
    }
  },
  methods: {
    handleSizeChange (val) {
      this.formQuery.pageSize = val
      this.formQuery.pageNum = 1
      this.getData()
    },
    handleCurrentChange (val) {
      this.formQuery.pageNum = val
      this.getData()
    },
    openDialog () {
      this.getData()
    },
    getData () {
      this.loading = true
      this.formQuery.uId = this.$store.state.uId
      commonAPI('queryBookSubList', this.formQuery).then(
        res => {
          this.loading = false
          this.tableData = res.data.rows
        }
      )
    },
    closeDialog () {
      this.tableData = []
      this.tableSubData = []
      this.option.isShow = false
    }
  },
  created () {
  this.getData()
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
  }
}
</script>

<style scoped>

</style>
