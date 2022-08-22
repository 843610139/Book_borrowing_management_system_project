import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 1. 全局挂载
import Print from 'vue-print-nb'
/** 引入element ,必须有 */
import ElementUI, { Message } from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Print)
Vue.use(ElementUI);
Vue.config.productionTip = false
const whiteList = ['/login']
router.beforeEach(async (to, from, next) => {
  if (store.getters.getToken) {
    if (to.path === '/login') {
      // 如果已经登录 重定向到主页
       next()
    } else {

      // 确定用户是否通过getInfo获得了他的权限角色
      const hasRoles = store.state.roles && store.state.roles.length > 0
      if (hasRoles) {
        next()
      } else {
        try {
          // get user info
          // 注意： 角色必须是一个对象数组 例如 ['admin'] 或 ['developer', 'editor']
          const roles = []
          await store.dispatch('setRoles', store.getters.getToken)
          roles.push(store.getters.getRoles)
          // 根据角色生成可访问路由映射
          const accessRoutes = await store.dispatch('generateRoutes', roles)
          // 动态添加可访问路由

          router.addRoutes(accessRoutes)
          // hack方法 确保addRoutes已完成
          // 设置replace: true, 这样导航就不会留下历史记录
          next({...to, replace: true})
        } catch (error) {
          await store.dispatch('resetToken')
          Message.error(error || 'Has Error')
          next(`/login?redirect=${to.path}`)
        }
      }
    }
  } else {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
    } else {
      next(`/login?redirect=${to.path}`)
    }
  }
})

new Vue({
  router,
  store,

  render: h => h(App)
}).$mount('#app')
