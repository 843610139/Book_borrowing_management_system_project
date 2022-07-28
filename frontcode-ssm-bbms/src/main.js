import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 1. 全局挂载
import Print from 'vue-print-nb'
/** 引入element ,必须有 */
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

Vue.use(Print)
Vue.use(ElementUI);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
