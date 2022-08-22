<template>
  <el-container style="height: 100%" class="abc" :class="{active:isActive}">
      <el-header style="text-align: right; font-size: 12px">
      <!--头部-->
      <el-dropdown trigger="click" @command="handleCommand">
        <span class="el-dropdown-link">
          你好，{{uName}}<i class="el-icon-setting el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="a">修改密码</el-dropdown-item>
          <el-dropdown-item command="b">退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      </el-header>
    <el-container>
      <!--左侧栏-->
      <el-aside width="200px">
        <el-menu>
          <!--如果菜单(menu)是true 循环侧栏路由列表-->
          <template
          v-for="item in menuData"
          v-if="hiddlen(item)"
          >
            <!--这里必须设置index 相当于唯一标识这个菜单标签 否则菜单列表点击后随意展开-->
            <el-submenu
              :index="item.meta.funcNode"
              :key="item.path"
            >
              <template slot="title"><i :class="item.meta.icon"></i>{{item.meta.title}}</template>
<!--              如果菜单有孩子菜单 则循环孩子菜单-->
              <template
              v-for="itemC in item.children"
              v-if="item.children"
              >
                <el-menu-item
                  :index="itemC.meta.funcNode"
                  :key="itemC.path"
                  @click="clickMenu(itemC)"
                  ><i :class="itemC.meta.icon"></i>
                  <span>{{itemC.meta.title}}</span></el-menu-item>
              </template>
            </el-submenu>
          </template>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view  />
      </el-main>
    </el-container>
    <loginUser
    :option="dialogLogin"
    />
  </el-container>
</template>

<script>
import { commonAPI } from '@/api/commonAPI'
import loginUser from '@/views/layout/dialog/updatePassword'
export default {
  components: { loginUser },
data () {
return {
  /* 获取挂载的routes
  * $router相当于一个全局的路由对象 里面包含很多属性和子对象
  * option 是一个对象 有成员 1.data函数  2.methods对象 3.模板template 4.挂载元素el
  * 5.生命周期钩子 6.props属性 7.computed计算成员 8.watch监视成员
  * */
  breadList: [],
  menuData: this.$store.getters.getRoutes,
  userName: '',
  activeIndex2: '1',
  formInline: {
  userName: '',
  sex: '',
  pageNum: 1,
  pageSize: 10
},
dialogLogin: {
  isShow: false
},
  tableData: [],
  uName: '',
  isActive: true,
  bookName: '',
  messageList: []
}
},
created () {
  this.getData()
},

methods: {
  handleCommand (command) {
  if (command === 'a') {
    this.updatePassword()
  } else if (command === 'b') {
    this.exitLogin()
  }
  },
  clickMenu (item) {
    this.$router.push({path: item.path})
  },
  exitLogin () {
    this.$router.push({path: '/login'})
    this.$store.dispatch('resetToken')
  },
  updatePassword () {
    this.dialogLogin.isShow = true
  },
  getData: function () {
    this.loading = true
    this.formInline.userName = this.$store.getters.getUserName
    commonAPI('queryUserList', this.formInline)
    .then(res => {
    this.tableData = res.data.rows
    this.uName = this.$store.getters.getUserName
      for (let i = 0; i < res.data.rows.length; i++) {
        if (res.data.rows[i].userName === this.uName) {
          this.$store.commit('setUId', res.data.rows[i].uId)

        }
      }
      let getUid = this.$store.getters.getUId
    commonAPI('queryBookSubList', {uId: getUid, bookType: 1, returnType: 2, pageNum: 1, pageSize: 10})
    .then(res1 => {
    this.messageList = res1.data.rows
    let j = 0
    let length = this.messageList.length

    for (let i = 0; i < length; i++) {
      let getBId = this.messageList[i].bId
        commonAPI('queryBookList', {bId: getBId, pageNum: 1, pageSize: 10})
    .then(res2 => {
    this.bookName.push(res2.data.rows[0].bookName)
    if (j === length - 1) {
    if (this.messageList != null || this.messageList !== '') {
      this.loading = false
      for (let i = 0; i < this.bookName.length; i++) {
        this.$notify({
          title: '催还消息',
          message: '您借阅的 《' + this.bookName[i] + '》即将到需归还日期，请及时归还',
          type: 'warning',
          duration: 0,
          offset: 100 * i
        })
      }
    }
  }
    j++
})
}
})
})
this.bookName = []
},
  hiddlen (item) {
  if (item.meta.menu) {
    return true
  } else {
    return false
  }
  }
  }
  }
</script>
<style>
  .el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
  }
  .user-avatar {
  cursor: pointer;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  }
  .el-aside,
  .el-menu,
  .el-submenu,
  .el-menu-item {
  background-color: rgb(238, 241, 246);
  }
  body {
  margin: 0;
  }
</style>
