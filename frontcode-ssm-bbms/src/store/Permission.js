import router, { asyncRouterMap, menu } from '@/router'

function hasPermission (roles, route) {
  if (route.meta && route.meta.role) {
    return roles.some(role => route.meta.role.indexOf(role) >= 0)
  } else {
    return true
  }
}

const Permission = {
  state: {
    routers: menu,
    addRouters: []
  },
  mutations: {
    setRouters: (state, routers) => {
      state.routers = routers
    }
  }
}
