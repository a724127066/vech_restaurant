import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/vant.js'
import animated from 'animate.css'
import hover from 'hover.css'
import axios from "axios";

Vue.use(animated)
Vue.use(hover)
Vue.config.productionTip = false


//添加请求拦截器
axios.interceptors.request.use(config => {
  //在发送请求之前做某事，比如说 设置loading动画显示
  store.state.isShowLoading = true
  if (store.state.token !== null) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
    config.headers.token = store.state.token;
  }
  return config
}, error => {
  //请求错误时做些事
  return Promise.reject(error)
})

//添加响应拦截器
axios.interceptors.response.use(response => {
  //对响应数据做些事，比如说把loading动画关掉
  store.state.isShowLoading = false
  return response
}, error => {
  //请求错误时做些事
  store.state.isShowLoading = false
  return Promise.reject(error)
})

Vue.prototype.$axios = axios







new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
