import Vue from 'vue'
import Vuex from 'vuex'
import { asyncRoutes, menu, resetRouter } from '@/router'
import login from '@/views/login/login'


Vue.use(Vuex)

// 通过递归过滤asyncRoutes
export function filterAsyncRoutes (routes, roles) {
  const res = []
  routes.forEach(route => {
    const tmp = {...route}
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}
function hasPermission (roles, route) {
  if (route.meta && route.meta.role) {
    // some()方法用于检测数组元素中的元素是否满足指定条件 (函数提供)
    return roles.some(role => route.meta.role.indexOf(role) >= 0)
  } else {
    return true
  }
}

export default new Vuex.Store({
  state: {
    roles: [],
    addRouters: [],
    routers: menu,
    token: localStorage.getItem('token') ? localStorage.getItem('token'):'',
    userName: localStorage.getItem('userName') ? localStorage.getItem('userName'):'',
    uId: localStorage.getItem('uid') ? localStorage.getItem('uid'):''
  },
  getters: {
    getUserName: function (state) {
      return state.userName
    },
    getToken: function (state) {
      return state.token
    },
    getRoutes: function (state) {
      return state.routers
    },
    getAddRouters: function (state) {
      return state.addRouters
    },
    getRoles: state => state.roles,
    getUId: state => state.uId
  },
  mutations: {
    changeUserName (state, userName) {
      state.userName = userName
    },
    saveToken (state, token) {
      state.token = token
    },
    setRoutes: (state, routers) => {
      state.addRouters = routers
        state.routers = menu.concat(routers)
    },
    setRoles: (state, roles) => {
      state.roles = roles
    },
    setUId: (state, uId) => {
      state.uId = uId
      localStorage.setItem(state.uId, uId)
    }
  },
  actions: {
    changeUserName (context, userName) {
      context.commit('changeUserName', userName)
    },
    saveToken (context, token) {
      context.commit('saveToken', token)
    },
    setRoles (context, roles) {
      context.commit('setRoles', roles)
    },
    generateRoutes ({ commit }, roles) {
      return new Promise(resolve => {
        let accessedRoutes
        accessedRoutes = filterAsyncRoutes(asyncRoutes, roles)
        commit('setRoutes', accessedRoutes)
        resolve(accessedRoutes)
      })
    },
    setUId (context, uId) {
      context.commit('setUId', uId)
    },
    resetToken ({ commit }) {
      return new Promise(resolve => {
        commit('saveToken', '')
        commit('setRoles', [])
        resetRouter()
        resolve()
      })
    }
  },
  modules: {

  }
})
