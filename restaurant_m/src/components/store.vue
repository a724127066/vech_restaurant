<template>
    <div style="padding-top: 44px;min-height: calc(90vh);min-width: 340px;">
        <!--    头部店铺信息-->
            <div style="background:#5dd5c494;height: 130px;padding-top: 20px;">
                <img :src="this.$store.state.store.storeTxUrl" style="border-radius: 10px;height: 110px;width: 110px;float: left;margin-left: 15px;">

                <div style="color: #000000;float: left;margin-left: 10px;">
                    <van-icon name="shop"  style="position: relative;top:5px;" size="23" /><span style="font-size: 20px;"> {{this.$store.state.store.storeName}}</span><br>
                    <div style="position: relative;top: 3px;" @click="tableChooseShow"><span style="font-size: 16px;">{{this.$store.state.tableId}}号桌</span> <img src="../../static/images/sanjiaoxing.png" style="height: 17px;width: 17px;position: relative;top: 2px;"></div>
                    <span style="font-size: 13px;position: relative;top: 4px;">{{this.$store.state.store.storeDescribe}}</span>
                    <van-divider :style="{ color: '#000000', borderColor: '#000000'}"  />
                    <div style="position: relative;top: -16px;"> <img src="../../static/images/jian.png" style="height: 17px;width: 17px;position: relative;top: 5px;"> <span style="font-size: 13px;position: relative;top: 2px;">{{activeShow}}</span></div>
                </div>
            </div>

        <!--        选择桌子弹出层-->
        <van-popup style="z-index: 10000" v-model="tableShow"  position="bottom">
            <van-picker  :columns="this.$store.state.tableList" @change="onChangeTable" />
        </van-popup>

<!--        头部完成-->
<!--  主体开始      -->

        <van-tabs v-model="active" color="#5dd5c8">
<!--            点菜tab-->
            <van-tab title="点菜">
                <!--导航列表-->
                <div style="height:calc(85vh);width: calc(23vw);background:#F5F5F5;float: left">
                    <vue-scroll :ops="ops">
                        <van-sidebar v-model="activeKey" >
                            <div v-for="item in this.$store.state.goodsList"  :key="item.id" >
                                <van-sidebar-item :title="item.goodsType" @click="listScroll('goodType'+item.id)"  />
                            </div>
                            <!--无分类菜单-->
                            <div v-if="this.$store.state.goodsList.length === 0">
                                <van-sidebar-item title="暂无菜单分类"  />
                            </div>
                        </van-sidebar>
                        <br>
                        <br>
                        <br>
                    </vue-scroll>
                </div>



                    <!--                商品列表-->

                    <div style="width: calc(77vw);height: calc(85vh);float: left;">
                        <vue-scroll :ops="ops"   ref="goodsScroll">
                            <div v-for="item in this.$store.state.goodsList" :key="item.id" :id="'goodType'+item.id">
                                    <div style="height: 35px;line-height: 35px;padding-left: 17px;padding-top:10px;color: #333333;">
                                        <img src="../../static/images/caipin2.png" style="position: relative;top: 7px;height: 25px;width: 25px;"><span style="font-size: 21px;"> {{item.goodsType}}</span>
                                    </div>

<!--                                未添加菜品-->
                                <div v-if="item.goods.length === 0" style="padding-left: 40px;">
                                    <img src="../../static/images/noGoods.png" style="width: 200px;height: 160px"><br>
                                    <span style="position: relative;top: 5px;left: 40px;">商家未添加菜品</span>
                                </div>

                                <ul class="el_home">
                                    <!--                            菜品分类列表-->
                                    <li v-for="(goodsItem,index) in item.goods"  :key="goodsItem.id" >


                                        <div style="padding: 10px 10px 22px">
                                            <img  :src="goodsItem.goodsImage" style="height: 85px;width: 85px;float: left;" @click="clickGoods(goodsItem,index)">
                                            <div style="position: relative;left: 5px;">
                                                <div @click="clickGoods(goodsItem,index)"> <span>{{goodsItem.goodsName}}</span></div>
                                                <div @click="clickGoods(goodsItem,index)" style="margin-right: 6px;"> <span style="font-size: 10px;">{{goodsItem.goodsDescribe}}</span></div>
                                                <div @click="clickGoods(goodsItem,index)" style="font-size: 11px;color: #333333;margin-top: 3px;"> <span>月售：{{goodsItem.goodsSell}}</span><span style="margin-left: 10px;">点赞 :{{goodsItem.goodsGood}}</span></div>
                                                <div  style="margin-top: 4px;">
                                                    <div style="color: #FB4E44;font-size: 20px;" @click="clickGoods(goodsItem,index)">￥{{goodsItem.goodsPrice}}</div>
                                                    <div style="float: right;">
                                                        <div style="position: relative;top: -28px;">
                                                            <transition
                                                                    enter-active-class="bounceOut animated fadeInDownBig"
                                                                    leave-active-class="bounceIn animated fadeOutLeft"
                                                                    name="fade">
                                                            <img src="../../static/images/jianhao.png" style="height: 25px;width: 25px;position: relative;right: 20px;" v-if="$store.state.shoppingCar.list[goodsItem.id].num > 0"  @click="subShoppingCar(goodsItem)">
                                                            </transition>
                                                            <span style="line-height: 25px;position: relative;top: -6px;right: 10px;" v-if="$store.state.shoppingCar.list[goodsItem.id].num > 0"> {{$store.state.shoppingCar.list[goodsItem.id].num}}  </span>
                                                            <img @click="addShoppingCar(goodsItem)" src="../../static/images/jiahao.png" style="height: 30px;width: 30px;position: relative;top: 3px;">
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>


                                    </li>
                                </ul>
                            </div>

                            <div v-if="this.$store.state.goodsList.length === 0">
                                <img src="../../static/images/noHaving.png" style="width: 200px;height:130px;margin-top: 30px;margin-left: 35px;"><br>
                                <span style="position: relative;left: 60px;top: 10px">商家暂时无菜单</span>
                            </div>
                                    <br>
                                    <br>
                                    <br>
                            <span style="position: relative;left: calc( (100% - 125px)/2);">我可是有底线的~~~</span>
                            <br>
                            <br>
                            <br>

                            <br>
                            <br>

                        </vue-scroll>
                    </div>

<!--菜单列表结束-->
                <!--                底部购物车-->

                <div @click="clickShoppingCar()" style="background-color:#3B3B3C;height: 50px;position: fixed;bottom: 0px;width: 100%;z-index: 5000" v-if="!tableShow">
                    <img style="height: 55px;width: 55px;position: relative;top: -20px;left: 15px;" src="../../static/images/gouwuche2.png">
                    <!--                   购物车无商品-->
                    <van-button :disabled="true" color="#666666" style="float: right;height: 50px;width: 110px;" v-if="!this.$store.state.shoppingCar.isHaving">去结算</van-button>
                    <!--                   购物车有商品-->
                    <van-button @click.stop="settle()" color="#5DD5C8" style="float: right;height: 50px;width: 110px;" v-if="this.$store.state.shoppingCar.isHaving" >去结算</van-button>
                    <transition-group
                            enter-active-class="bounceOut animated fadeIn"
                            leave-active-class="bounceIn animated fadeOut"
                            name="fade">
                        <div style="float: right;color: #FFFFFF;margin-top: 3px;margin-right: 10px;" v-if="this.$store.state.shoppingCar.isHaving" :key="'k1'">
                            <span :key="'k2'">￥</span>  <span style="font-size: 25px;" :key="'k3'">{{$store.state.count.Actual}}&nbsp;&nbsp;&nbsp;&nbsp; </span>
                            <del :key="'k4'" style="color: #999999"> ￥{{$store.state.count.Original}}</del><br>
                            <span :key="'k5'" style="font-size: 10px;color: #cbcbcb;position: relative;top: -5px;">已优惠{{$store.state.count.Original - $store.state.count.Actual}}元</span>
                        </div>
                    </transition-group>

                </div>





            </van-tab>
<!--            订单tab-->
            <van-tab title="我的订单">
                <!--                未登录显示-->
                <div  style="width: 200px;margin: auto;" v-if="!this.$store.state.isLogin" >
                    <img src="../../static/images/welcome.png" style="width: 200px;"><br>
                    <span>你还没有登录,</span><router-link to="/login" class="loginSpan">点击登录~~</router-link><br>
                    <span style="position: relative;top: 250px;left: 20px;">版权所有：未奇科技</span>
                </div>

<!--                订单为空显示-->
                <div v-if="$store.state.orderList === null && this.$store.state.isLogin">
                    <div style="float: right;margin-top: 3px;margin-right: 15px;">
                        <span>{{this.$store.state.user.name}} </span>
                        <span style="font-size: 12px;color: cornflowerblue;" @click="signOut"> 退出登录</span>
                    </div>
                    <div  style="width: 200px;margin: 10px auto auto;">
                        <img src="../../static/images/welcome.png" style="width: 200px;"><br>
                        <span>你没有点单，</span><span  @click="active = 0" class="loginSpan">快点餐吧~~</span>
                    </div>

                    <div style="margin: 250px auto auto;width: 160px;">
                      版权所有：未奇科技
                    </div>
                </div>


                <div v-if="$store.state.orderList !== null && this.$store.state.isLogin" style="background-color: #f7f7f7;min-height: 79vh">
                    <div style="float: right;margin-top: 5px;margin-right: 15px;">
                        <span>{{this.$store.state.user.name}} </span>
                        <span style="font-size: 12px;color: cornflowerblue;" @click="signOut"> 退出登录</span>
                    </div>
                    <div>

                        <div style="padding-right: 20px;padding-top: 40px;padding-left: 20px;" v-for="item in this.$store.state.orderList" :key="item.order.id">
                               <div style="border-radius: 10px;background-color: #ffffff;padding: 15px 20px 20px;">
                                    <div>
                                        <van-icon name="shop" style="position: relative;top:5px;" size="21px" color="#5dd5c8" />&nbsp;{{item.order.storeName}}<div @click="active = 0" style="position: relative;top: -2px;border-radius:5px;height: 30px;width: 80px;float: right;background-color: #00a0e9;color: #ffffff;text-align: center;line-height: 30px;font-size:14px;" >再来一单</div></div>
                                   <van-divider />

                                   <div style="height: 26px;">
                                       <div v-if="item.order.payChoose === '微信'" >
                                           <div style="float: right;position: relative;top: -8px;">

                                               <span v-if="item.order.orderStatus === 1" style="font-size: 12px;float:left;">正在制作</span>

                                               <span v-if="item.order.orderStatus === 2" style="font-size: 12px;float:left;">等待上菜</span>

                                               <span v-if="item.order.orderStatus === 3" style="font-size: 12px;float:left;">已完成</span>


                                               <span style="font-size: 12px;float: right;" v-if="item.order.orderType === 1"> {{item.order.orderTable}}号桌</span>   <span style="float: right;"> </span>

                                               <span style="font-size: 12px;float: right;" v-if="item.order.orderType === 2"> 外卖</span>   <span style="float: right;"> </span>


                                               <br>
                                               <span style="font-size: 12px;">{{new Date(item.order.orderTimeBegin).toLocaleDateString().replace(/\//g, "-") + " " + new Date(item.order.orderTimeBegin).toTimeString().substr(0, 8)}}</span>
                                           </div>
                                           <van-icon name="wechat" style="position: relative;" size="29px" color="#2ba245" />
                                           <span style="font-size: 17px;top: -9px;right: -5px;position: relative;">微信</span>
                                       </div>

                                       <div  v-if="item.order.payChoose === '支付宝'">
                                           <div style="float: right;position: relative;top: -8px;">

                                           <span v-if="item.order.orderStatus === 1" style="font-size: 12px;float:left;">正在制作</span>

                                           <span v-if="item.order.orderStatus === 2" style="font-size: 12px;float:left;">等待上菜</span>

                                           <span v-if="item.order.orderStatus === 3" style="font-size: 12px;float:left;">已完成</span>

                                               <span style="font-size: 12px;float: right;" v-if="item.order.orderType === 1"> {{item.order.orderTable}}号桌</span>   <span style="float: right;"> </span>

                                               <span style="font-size: 12px;float: right;" v-if="item.order.orderType === 2"> 外卖</span>   <span style="float: right;"> </span>


                                           <br>
                                           <span style="font-size: 12px;">{{new Date(item.order.orderTimeBegin).toLocaleDateString().replace(/\//g, "-") + " " + new Date(item.order.orderTimeBegin).toTimeString().substr(0, 8)}}</span>
                                           </div>
                                           <van-icon name="alipay" style="position: relative;" size="29px" color="#00a0e9" />
                                           <span style="font-size: 17px;top: -9px;right: -5px;position: relative;">支付宝</span>
                                       </div>
                                   </div>
                                    <van-divider />



                                   <div style="margin-top: 20px;height: 50px;" v-for="goods in item.orderGoodsList" :key="goods.id">
                                       <img  :src="goods.goodsImage" style="height: 40px;width: 40px;float: left;">
                                       <div style="float: left;position: relative;left: 5px;top: 2px;">
                                           <div>{{goods.goodsName}}</div>
                                           <div style="font-size: 10px;color: #727272;position: relative;top: 5px;">{{goods.goodsDescribe}}</div>
                                       </div>
                                       <div style="float: right;position: relative;top: 14px;"><span style="font-size: 13px;">￥</span><span style="font-size: 22px;">{{goods.goodsPrice}}</span></div>
                                       <span style="float: right;position: relative;top: 23px;right: 10px;font-size: 13px;color: #727272;">× {{goods.goodsNum}} </span>
                                   </div>
                                   <van-divider />

                                   <div v-if="item.order.orderType === 2"><van-icon name="location" style="position: relative;top:5px;" size="18px" color="#5dd5c8" /><span style="font-size: 14px"> 地址:</span><span style="float: right;font-size: 14px;line-height: 30px;">{{item.order.remake}}</span></div>
                                   <van-divider v-if="item.order.orderType === 2" />

                                   <div><van-icon name="balance-list" style="position: relative;top:5px;" size="18px" color="#5dd5c8" /><span style="font-size: 14px"> 菜品总价</span><span style="float: right;font-size: 14px;line-height: 30px;">￥{{item.order.orderCountOriginal}}</span></div>
                                   <van-divider />

                                   <div><van-icon name="coupon" style="position: relative;top:5px;" size="18px" color="#5dd5c8" /><span style="font-size: 14px"> 优惠金额</span><span style="float: right;font-size: 14px;line-height: 30px;">-￥{{item.order.orderCountOriginal - item.order.orderCountActual}}</span></div>
                                   <van-divider />

                                   <div><van-icon name="gold-coin" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 总计</span><span style="float: right;font-size: 17px;line-height: 30px;">￥{{item.order.orderCountActual}}</span></div>





                               </div>
                        </div>

<!--                        订单结束-->



                        <div style="margin: 100px auto auto;width: 190px;">
                            版权所有：未奇科技
                        </div>

                        <br>
                        <br>




                    </div>

                </div>



            </van-tab>
        </van-tabs>


<!--购物车弹出层-->
        <van-popup
                v-model="shopCarShow"
                position="bottom" style="width: 100%;padding-bottom: 50px;max-height: 250px;">
            <div style="height: 40px;background-color: #F4F4F4;line-height: 40px;padding-left: 20px;padding-right: 20px; ">
                <span style="font-size: 12px;">购物车</span>  <span  @click="cleanShoppingCar" style="font-size: 12px;float: right;"> <van-icon name="delete" style="font-size: 15px;position: relative;top: 3px;" /> 清空购物车</span>
            </div>


            <div>
                <div style="padding-left: 20px;padding-right: 20px;" v-for=" (item,index) in this.$store.state.shoppingCar.list" :key="index">

                    <div style="border-bottom: solid 1px #E4E4E4;height: 60px;" v-if="item.num > 0">
                        <div style="width: 150px;position: relative;top: 22px;">
                            {{item.goods.goodsName}}
                        </div>
                        <div style="float: right;margin-top: -5px;" >
                            <span style="color: #FB4E44;position: relative;right: 30px;top: -8px;font-size:20px;">￥{{item.goods.goodsPrice*item.num}}</span>
                            <img src="../../static/images/jianhao.png" style="height: 25px;width: 25px;position: relative;right: 20px;"  @click="subShoppingCar(item.goods)">
                            <span style="line-height: 25px;position: relative;top: -6px;right: 10px;">  {{$store.state.shoppingCar.list[item.goods.id].num}}  </span>
                            <img @click="addShoppingCar(item.goods)" src="../../static/images/jiahao.png" style="height: 30px;width: 30px;position: relative;top: 3px;">
                        </div>
                    </div>
                </div>
            </div>
        </van-popup>


        <!--        点击商品弹出层-->
        <van-popup v-model="goodsShow" :key="'v1'">
            <div style="height: 45vh;width: 80vw;" :key="'v2'">
                <div style="height: 84%;width: 100%">
                    <img  :src="goodsShowItems.goodsImage" style="height: 100%;width: 100%" :key="'v3'">
                    <div :key="'v4'" style="width: calc(100% - 40px);float: left;padding: 10px 20px 10px 20px;background:#ffffff;position: absolute;bottom: 16%;">
                        <span :key="'v5'">{{goodsShowItems.goodsName}}</span><br>
                        <span :key="'v6'" style="font-size: 10px;color: #666666">月售：{{goodsShowItems.goodsSell}}</span><span :key="'v8'" style="color: #666666;font-size: 10px;margin-left: 20px;">赞: {{goodsShowItems.goodsGood}}</span><br>
                        <span :key="'v7'" style="font-size: 10px;color: #666666">{{goodsShowItems.goodsDescribe}}</span>
                    </div>
                </div>
                <div v-if="this.goodsShow">
                <div style="height: calc(16% - ((16% - 35px)/2));padding-top:calc((16% - 35px)/2);width: 100%;background-color: #FAFAFA;position: absolute;bottom: 0">
                    <span style="font-size: 30px;color:  #FB4E44;position: relative;left: 20px;">￥{{goodsShowItems.goodsPrice}}</span>

                    <van-button @click="addShoppingCar(goodsShowItems)"  v-if="$store.state.shoppingCar.list[goodsShowItems.id].num === 0" style="float: right;height: 30px;line-height: 30px;margin-right: 20px;margin-top: 5px;" round color="#5dd5c8" >加入购物车</van-button>

                    <div  v-if="this.$store.state.shoppingCar.list[goodsShowItems.id].num > 0" style="float:right;margin-right: 20px;">
                        <img  src="../../static/images/jianhao.png" style="height: 25px;width: 25px;position: relative;right: 20px;"   @click="subShoppingCar(goodsShowItems)">
                        <span style="line-height: 25px;position: relative;top: -6px;right: 10px;" >  {{this.$store.state.shoppingCar.list[goodsShowItems.id].num}}   </span>
                        <img  @click="addShoppingCar(goodsShowItems)"  src="../../static/images/jiahao.png" style="height: 30px;width: 30px;position: relative;top: 3px;">
                    </div>
                </div>
                </div>
                <div>
                </div>
            </div>
        </van-popup>

    </div>
</template>

<script>
    import vueScroll from "vuescroll";
    import "vuescroll/dist/vuescroll.css";


    export default {
        name: "store",
        components: {
            vueScroll
        },
        methods:{
            //时间戳工具
            getLocalTime(nS) {
                    return new Date(parseInt(nS) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
            },
            //退出登录
            signOut(){
                this.$dialog.confirm({title:'退出',massage:'确定退出？'}).then(()=>{
                    this.$toast.success('退出成功，'+this.$store.state.user.name);
                    localStorage.setItem('token','token');
                    this.$store.state.isLogin = false;
                    this.$store.state.user = {};
                    this.active = 1
                })
            },
            //清空购物车
            cleanShoppingCar(){
                this.getGoods()
                this.$store.state.count.Original = 0
                this.$store.state.count.Actual = 0
                this.shopCarShow = false;
                this.$toast.success("清空购物车成功")

            },
            //购物车逻辑
            addShoppingCar(goods){
                let tempShoppingCar = this.$store.state.shoppingCar
                let tempCount = this.$store.state.count
                tempShoppingCar.isHaving = true
                tempShoppingCar.list[goods.id].num += 1
                tempCount.Original += goods.goodsPrice;




                //折扣计算
                let activeCount = [];
                if (this.$store.state.active.length > 0){
                    for (let i = 0; i < this.$store.state.active.length; i++){
                        if (tempCount.Original  >= this.$store.state.active[i].activePrice ){

                            if (this.$store.state.active[i].activeType === 1){
                                activeCount.push(tempCount.Original - this.$store.state.active[i].activeContent)
                            }
                            if (this.$store.state.active[i].activeType === 2){
                                activeCount.push(Math.floor(tempCount.Original * (this.$store.state.active[i].activeContent/10))*100/100)
                            }
                        }
                    }
                }
                if ( activeCount.length === 0){
                    tempCount.Actual = tempCount.Original
                }else {
                    activeCount.sort(function (a, b) {return a-b;});
                    tempCount.Actual = activeCount[0]
                }


                this.$store.commit("setShoppingCar",tempShoppingCar)
                this.$store.commit("setCount",tempCount)
               // console.log(this.$store.state.shoppingCar)
            },
            subShoppingCar(goods){
                let tempShoppingCar = this.$store.state.shoppingCar
                let tempCount = this.$store.state.count
                if(tempShoppingCar.list[goods.id].num > 0){
                    tempShoppingCar.list[goods.id].num -= 1
                    tempCount.Original -= goods.goodsPrice;

                    //折扣计算
                    let activeCount = [];
                    if (this.$store.state.active.length > 0){
                        for (let i = 0; i < this.$store.state.active.length; i++){
                            if (tempCount.Original  >= this.$store.state.active[i].activePrice ){

                                if (this.$store.state.active[i].activeType === 1){
                                    activeCount.push(tempCount.Original - this.$store.state.active[i].activeContent)
                                }
                                if (this.$store.state.active[i].activeType === 2){
                                    activeCount.push(Math.floor(tempCount.Original * (this.$store.state.active[i].activeContent/10))*100/100)
                                }
                            }

                        }
                    }
                    if ( activeCount.length === 0){
                        tempCount.Actual = tempCount.Original
                    }else {
                        activeCount.sort(function (a, b) {return a-b;});
                        tempCount.Actual = activeCount[0]
                    }

                }
                if (tempCount.Original <= 0.01){
                    tempShoppingCar.isHaving = false
                    this.shopCarShow = false;
                }
                this.$store.commit("setShoppingCar",tempShoppingCar)
                this.$store.commit("setCount",tempCount)
            },
            //结算逻辑
            settle(){
                this.$router.push("/settlement")
            },
            //点击购物车弹出层逻辑
            clickShoppingCar(){
                let temp =  Object.keys(this.$store.state.shoppingCar.list)
                if (temp.length > 0){
                    if (this.shopCarShow){
                        this.shopCarShow = false;
                    }else{
                        this.shopCarShow = true;
                    }

                }else{
                    this.$toast.fail("购物车无商品哦~~~")
                }
            },
            //点击商品弹出层逻辑
            clickGoods(goodsItems){
                this.goodsShow = true;
                this.goodsShowItems = goodsItems;
            },
            //选择桌面弹出层弹出
            tableChooseShow(){
                this.tableShow = true;
            },
            //选择table
            onChangeTable(picker,index){
                this.$store.commit("setTableId",index)
            },
            //商品列表滚动
            listScroll(id){
                this.$refs['goodsScroll'].scrollIntoView('#'+id, 700);
            },
            getStore(){
                this.$axios.get(this.$store.state.httpUrl+"/getStore?id="+this.$route.params.storeId).then((res)=>{
                    // console.log(res.data)
                    if (res.data.flag){
                        this.$store.commit("setStore",res.data.data)
                        let tempTableList = []
                        for(let i = 0;i< this.$store.state.store.storeTableNum;i++){
                            tempTableList.push(i+1)
                        }
                        this.$store.commit("setTableList",tempTableList)
                    }else {
                        if (res.data.code === 20005){
                            this.$toast.fail("店铺不存在")
                        }else{
                            this.$toast.fail("出现错误，请重试")
                        }
                        this.$router.push("/")
                    }
                })
            },
            getGoods(){
                this.$axios.get(this.$store.state.httpUrl+"/getGoods?id="+this.$route.params.storeId)
                    .then((res)=>{
                     //   console.log(res.data)
                        if(res.data.flag){
                            this.$store.commit("setGoodsList",res.data.data.goodsList)
                            let tempShoppingCar = {"isHaving":false,"list":{}}
                            for (let i = 0; i < this.$store.state.goodsList.length; i++){
                                for (let j = 0; j < this.$store.state.goodsList[i].goods.length; j++){
                                    tempShoppingCar.list[this.$store.state.goodsList[i].goods[j].id] = {"num":0,"goods":this.$store.state.goodsList[i].goods[j]}
                                }
                            }
                            this.$store.commit("setShoppingCar",tempShoppingCar)
                        }else{
                            this.$store.commit("setGoodsList",[])
                        }
                      })
            },
            getOrder(){
                this.$axios.get(this.$store.state.httpUrl+"/order/getOrderUser?storeId="+this.$store.state.store.id)
                .then((res)=>{
                   // console.log(res.data.data)
                    this.$store.commit("setOrderList",res.data.data)
                })
            },
            getActive(){
                this.$axios.get(this.$store.state.httpUrl+"/getActiveUser?id="+ this.$store.state.store.id)
                    .then((res)=>{
                        this.$store.state.active = res.data.data;
                        if (this.$store.state.active.length === 0){
                            this.activeShow = "暂时没有活动，请咨询前台"
                        }else{
                            for (let i = 0; i < 2 && i < this.$store.state.active.length; i++){
                                if (this.$store.state.active[i].activeType === 1){
                                    this.activeShow += " 满"+this.$store.state.active[i].activePrice+"减"+this.$store.state.active[i].activeContent
                                }
                                if (this.$store.state.active[i].activeType === 2){
                                    this.activeShow += " 满"+this.$store.state.active[i].activePrice+"打"+this.$store.state.active[i].activeContent+"折"
                                }
                            }
                            if (this.$store.state.active.length > 2){
                                this.activeShow += "等"
                            }
                        }
                    })
            }
        },
        mounted() {
            this.getActive();
            let storeId = 1;
            if(this.$store.state.store.length !== 0 || this.$store.state.store !== null){
                       storeId =  this.$store.state.store.id;
            }
            this.getStore();
            if (storeId  !== this.$store.state.id){
                this.$store.state.orderList = [];
                this.$store.state.goodsList = [];
                this.$store.state.tableId = 1;
                this.$store.state.count = {"Actual": 0,"Original": 0};
                this.$store.state.shoppingCar = {"isHaving":false,"list":{}};
                this.$store.state.tableList = [];
                this.$store.state.settlement = {};
                this.$store.state.settlementCount = {};
            }
            if (!this.$store.state.shoppingCar.isHaving){
                this.getGoods();
            }
            if (this.$store.state.isLogin){
                this.getOrder();
            }
        },
        data(){
            return{
                shopCarShow:false,
                tableShow: false,
                goodsShow:false,
                goodsShowItems:{},
                Catalog:[],
                activeKey: 0,
                active: 0,
                activeShow: "",
                ops: {// 在这里设置配置
                    vuescroll: {},
                    scrollPanel: {},
                    rail:{},
                    bar: {
                        hoverStyle: true,
                        onlyShowBarOnScroll: true, //是否只有滚动的时候才显示滚动条
                        background: '#000000',   //颜色
                    },
                },
            }
        },
        created() {
            //获取storeID
            if (this.$route.params.storeId == null){
                this.$router.push("/")
            }

        },
        watch:{
            $route(){
                if (this.$route.component === 'store'){
                    let storeId = 1;
                    this.getStore();
                    if (storeId  !== this.$store.state.id){
                        this.$store.state.orderList = [];
                        this.$store.state.goodsList = [];
                        this.$store.state.tableId = 1;
                        this.$store.state.count = {"Actual": 0,"Original": 0};
                        this.$store.state.shoppingCar = {"isHaving":false,"list":{}};
                        this.$store.state.tableList = [];
                        this.$store.state.settlement = {};
                        this.$store.state.settlementCount = {};
                    }
                }
            },
        }
    }
</script>

<style scoped>
    .el_home {
        width: 100%;
        height: 100%;color: #727272
    }

    .van-sidebar-item--select{
        width: calc(23vw);
        background-color: white !important;
        border-color:#5dd5c8;
    }

    .van-sidebar-item{
        width: calc(23vw);
        background-color: #F5F5F5;
    }
    .loginSpan{color:#5dd5c8}
    .loginSpan:hover{color:#5dd5c8}
</style>
