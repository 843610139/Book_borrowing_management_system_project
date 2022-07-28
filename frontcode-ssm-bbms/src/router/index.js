import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

export const menu = [
  {
    path: '/',
    redirect: 'login',
    meta: {
      menu: false
    }
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/login.vue')
  }
]
const routes = []

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),

  routes: menu
})
const router = createRouter()
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // 新路由实例matcer，赋值给旧路由实例的matcher，（相当于replaceRouter）
}

export default router
