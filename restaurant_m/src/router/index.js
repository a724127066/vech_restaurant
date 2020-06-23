import Vue from 'vue'
import VueRouter from 'vue-router'



const home = () => import(/* webpackChunkName: "home" */ '../components/home')
const store = () => import(/* webpackChunkName: "store" */ '../components/store')
const login = () => import(/* webpackChunkName: "login" */ '../components/login')
const register = () => import(/* webpackChunkName: "register" */ '../components/register')
const settlement = () => import(/* webpackChunkName: "settlement" */ '../components/settlement')

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component:home,
    meta:{"name":'首页',"needLogin":false,"isShowFoot":true,"isShowHeader":true,"isShowBack":false,"isFlexFoot": true,"isFlexHeader":true,"index":0,"active":0,"background":"background: #5dd5c8"}
  },
  {
    path: '/store',
    name: 'store1',
    component:store,
    meta:{"name":'商店',"needLogin":false,"isShowFoot":false,"isShowHeader":true,"isShowBack":false,"isFlexFoot": true,"isFlexHeader":true,index:1,active:0,"background":"background: #5dd5c8"}
  },
  {
    path: '/store/:storeId',
    name: 'store2',
    component:store,
    meta:{"name":'商店',"needLogin":false,"isShowFoot":false,"isShowHeader":true,"isShowBack":false,"isFlexFoot": true,"isFlexHeader":true,index:1,active:0,"background":"background: #5dd5c8"}
  },
  {
    path: '/store/:storeId/:tableId',
    name: 'store3',
    component:store,
    meta:{"name":'商店',"needLogin":false,"isShowFoot":false,"isShowHeader":true,"isShowBack":false,"isFlexFoot": true,"isFlexHeader":true,index:1,active:0,"background":"background: #5dd5c8"}
  },
  {
    path: '/store/:storeId/:tableId',
    name: 'store3',
    component:store,
    meta:{"name":'商店',"needLogin":false,"isShowFoot":false,"isShowHeader":true,"isShowBack":false,"isFlexFoot": true,"isFlexHeader":true,index:1,active:0,"background":"background: #5dd5c8"}
  },
  {
    path: '/login',
    name: 'login',
    component:login,
    meta:{"name":'登录',"needLogin":false,"isShowFoot":false,"isShowHeader":true,"isShowBack":true,"isFlexFoot": true,"isFlexHeader":true,"index":9,"active":0,"background":"background: #5dd5c8"}
  },
  {
    path: '/register',
    name: 'register',
    component:register,
    meta:{"name":'注册 ',"needLogin":false,"isShowFoot":false,"isShowHeader":true,"isShowBack":true,"isFlexFoot": true,"isFlexHeader":true,"index":10,"active":0,"background":"background: #5dd5c8"}
  },

  {
    path: '/settlement',
    name: 'settlement',
    component:settlement,
    meta:{"name":'确认订单',"needLogin":false,"isShowFoot":false,"isShowHeader":true,"isShowBack":true,"isFlexFoot": true,"isFlexHeader":true,"index":11,"active":0,"background":"background: #5dd5c8"}
  },

]

// eslint-disable-next-line no-new
const router = new VueRouter({
  base: process.env.BASE_URL,
  routes
})

export default router
