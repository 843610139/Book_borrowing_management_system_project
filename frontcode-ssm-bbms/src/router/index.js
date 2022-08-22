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
    component: () => import('@/views/login/login.vue'),
    meta: {
      menu: false
    }
  },
  {
  path: '/user',
    component: () => import('@/views/layout/Layout'),
    name: 'layoutYHGL',
    meta: {
    title: '用户管理',
      icon: 'el-icon-user',
      menu: true,
      funcNode: '1'
    },
    children: [
      {
        path: '/user/userList',
        name: 'UserList',
        component: () => import('@/views/user/UserList'),
        meta: {
          title: '用户列表',
          icon: 'el-icon-notebook-2',
          menu: true,
          funcNode: '1-1'
        }
      }
    ]
  }
]

export const asyncRoutes= [
  {
    path: '/book',
    name: 'book',
    component: () => import('@/views/layout/Layout'),
    meta: {
      title: '图书管理',
      icon: 'el-icon-reading',
      menu: true,
      funcNode: '3',
      role: ['admin', 'editor']
    },
    children: [
      {
        path: '/book/bookManage',
        name: 'bookManage',
        component: () => import('@/views/book/bookManage'),
        meta: {
          title: '图书管理',
          icon: 'el-icon-notebook-2',
          menu: true,
          funcNode: '3-1',
          role: ['admin']
        }
      },
      {
        path: '/book/subBook',
        name: 'subBook',
        component: () => import('@/views/book/subBook'),
        meta: {
          title: '图书借阅',
          icon: 'el-icon-notebook-1',
          menu: true,
          funcNode: '3-2',
          role: ['editor']
        }
      },
      {
        path: '/book/returnBookSub',
        name: 'returnBookSub',
        component: () => import('@/views/book/returnBookSub'),
        meta: {
          title: '图书归还',
          icon: 'el-icon-collection',
          menu: true,
          funcNode: '3-3',
          role: ['editor']
        }
      }
    ]
  }
]

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
