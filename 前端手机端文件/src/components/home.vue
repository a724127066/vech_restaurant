<template>
    <div style="padding-top: 46px">
      <div style="text-align: center">
          <van-divider style="width: 75%;color: black;margin: 15px auto;font-size: 17px;border-color: #000000"><van-icon name="fire-o" color="#FF3030" />金牌商家</van-divider>
      </div>


      <div>


          <div v-for="n in storeList" :key="n.id">
                  <router-link :to="'/store/'+n.id">
                  <van-divider class="line"/>
                  <div style="margin-top: 10px;margin-left: 10px;height:100px;  " >
                  <img style="float: left;height: 95px;width: 100px;" :src="n.storeTxUrl">
                  <div style="padding-left: 110px;">
                    <span>{{n.storeName}}</span><br>
                    <van-rate v-model="n.rate" style="margin-top: 6px"  readonly /><br>
                    <span style="font-size: 12px;">{{n.storeDescribe}}</span><br>
                    <div style="margin-top: 4px;padding-right: 15px;">
                    <span style="color: #ff6600">{{n.businessHoursBegin}}</span>～<span></span><span style="font-size: 12px;">{{n.businessHoursEnd}}</span>
                    <span style="float: right;font-size: 12px;margin-top: 3px;">已售{{n.sellCount}}份</span>
                    </div>
                  </div>
                  </div>
                   </router-link>
          </div>
          <van-divider class="line" />
          <van-divider style="width: 75%;color: black;margin: 15px auto;font-size: 12px;border-color: #000000"><van-icon name="arrow-up"  />到底了</van-divider>

          <br>
          <br>
          <br>



      </div>

    </div>
</template>
<script>
    export default {
        name: "home",
        data(){
            return{
                storeList: null,
            }
        },

        methods:{
            getStoreList(){
                this.$axios.get(this.$store.state.httpUrl+"/getStoreList")
                .then( (res)=> {
                    this.storeList = res.data.data
                   // console.log(res)
                })
            },
        },
        mounted() {
            this.getStoreList()
            this.$store.commit("setIsGetData",false)
        },


    }
</script>

<style scoped>
    .line{
        border-color: #cbcbcb
    }

    a{color:black}
    a:hover{color:black}
</style>
