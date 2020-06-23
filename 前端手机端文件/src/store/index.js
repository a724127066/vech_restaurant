import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

/* eslint-disable no-new */
const store = new Vuex.Store({
  plugins: [createPersistedState()],
  state:{
    active:[],
    storeId:"",
    store: {},
    user:{},
    orderType: "堂食",
    isGetData:false,
    isLoading:{},
    token:"",
    httpUrl:"http://haozheng.club:8894/user",
    isShowLoading:false,
    isLogin:false,
    goodsList:[],
    tableId: "1",
    count:{"Actual": 0,"Original": 0},
    shoppingCar:{"isHaving":false,"list":{}},
    noShoppingCar:{},
    tableList:[1],
    settlement:{},
    settlementCount: 0,
    payChoose: "支付宝",
    orderList:[],
  },
  mutations:{
    setActive(state,active){
      state.active = active
    },
    setOrderList(state,orderList){
      state.orderList = orderList
    },
    setPayChoose(state,payChoose){
      state.payChoose = payChoose
    },
    setsettlmentCount(state,settlementCount){
      state.settlementCount = settlementCount
    },
    setsettlment(state,settlement){
      state.settlement = settlement
    },
    setTableList(state,tableList){
      state.tableList = tableList
    },
    setIsGetData(state,isGetDate){
       state.isGetData = isGetDate
    },
    setCount(state,count){
       state.count = count
    },
    setShoppingCar(state,shoppingCar){
       state.shoppingCar = shoppingCar;
    },
    setNoShoppingCar(state,noShoppingCar){
      state.noShoppingCar =noShoppingCar
    },
    setTableId(state,tableId){
      state.tableId = tableId;
    },
    setGoodsList(state,goodsList){
      state.goodsList = goodsList;
    },
    setStore(state,store){
      state.store = store
    },
    setStoreId(state,id){
      state.storeId = id
    },
    login(state,user){
      this.state.user = user;
      this.state.isLogin = true;
    },
    setToken(state,token){
      this.state.isLogin = true;
      this.state.token = token;
    },

  },
  modules: {
  }
})

export default store
