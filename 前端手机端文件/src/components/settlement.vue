<template>
    <div>
    <div style="background-color:#f7f7f7;min-height: calc(100vh);padding-top: 70px;padding-right: 20px;padding-left: 20px;">


<!--        头部用户信息-->
        <div>
        <span style="font-size: 20px">用户信息</span>
        <div style="border-radius: 10px;background-color: #ffffff;margin-top: 10px;padding: 10px 20px 20px;">
            <div><van-icon name="friends" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 用户名</span><span style="float: right;font-size: 17px;line-height: 30px;">{{this.$store.state.user.name}}</span></div>
            <van-divider />
            <div><van-icon name="phone" style="position: relative;top:5px;;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 电话</span><span style="float: right;font-size: 17px;line-height: 30px;">{{this.$store.state.user.phone}}</span></div>
            <van-divider />
            <div><van-icon name="bag" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 订单类型</span>   <span style="float: right;"> <van-icon name="arrow-down"  @click="orderTypeShow = true"  style="position: relative;top: 5px"/></span> <span @click="orderTypeShow = true" style="float: right;font-size: 17px;top: 2px;right: 7px;position: relative;">{{$store.state.orderType}}</span></div>
            <van-divider />

            <div v-if="this.$store.state.orderType === '外卖'">
                <van-icon name="location" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 地址</span>
                <br>

                <van-field placeholder="请输入用户名" v-model="address" style="padding-bottom: 0 !important;"/>

            </div>
            <van-divider v-if="this.$store.state.orderType === '外卖'" />


            <div v-if="this.$store.state.orderType === '堂食'">
                <van-icon name="location" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 桌子</span>   <span style="float: right;">
                <van-icon name="arrow-down"  @click="tableChooseShow"  style="position: relative;top: 5px"/></span> <span @click="tableChooseShow" style="float: right;font-size: 17px;top: 2px;right: 7px;position: relative;">{{this.$store.state.tableId}}号桌</span>
            </div>
            <van-divider v-if="this.$store.state.orderType === '堂食'" />
            <div @click="PayChooseShow" v-if="this.$store.state.payChoose === '微信'"><van-icon name="bill" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 支付方式</span>   <span style="float: right;"> <van-icon name="arrow-down"  style="position: relative;top: 5px"/></span> <van-icon name="wechat" style="position: relative;float: right;" size="29px" color="#2ba245"/><span style="float: right;font-size: 17px;top: 2px;right: 7px;position: relative;">微信</span></div>

            <div @click="PayChooseShow" v-if="this.$store.state.payChoose === '支付宝'"><van-icon name="bill" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 支付方式</span>   <span style="float: right;"> <van-icon name="arrow-down"  style="position: relative;top: 5px"/></span> <van-icon name="alipay" style="position: relative;float: right;" size="29px" color="#00a0e9" /><span style="float: right;font-size: 17px;top: 2px;right: 7px;position: relative;">支付宝</span></div>

        </div>
        </div>

<!--用户信息结束-->

<!--        开始订单信息-->

        <div style="margin-top: 20px;">
        <span style="font-size: 20px">订单信息</span>
        <div style="border-radius: 10px;;background-color: #ffffff;margin-top: 10px;padding: 10px 20px 15px;">
            <div><van-icon name="shop" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 店铺</span><span style="float: right;font-size: 17px;line-height: 30px;">{{this.$store.state.store.storeName}}</span></div>
            <van-divider />

            <div style="margin-top:10px;" >
                <div v-for="item in this.$store.state.settlement" :key="item.id">
                <div style="margin-top: 15px;height: 50px;" >
                    <img  :src="item.goodsImage" style="height: 40px;width: 40px;float: left;">
                    <div style="float: left;position: relative;left: 5px;top: 2px;">
                        <div>{{item.goodsName}}</div>
                        <div style="font-size: 10px;color: #727272;position: relative;top: 5px;">{{item.goodsDescribe}}</div>
                    </div>
                    <div style="float: right;position: relative;top: 14px;"><span style="font-size: 13px;">￥</span><span style="font-size: 22px;">{{item.goodsPrice}}</span></div>
                    <span style="float: right;position: relative;top: 23px;right: 10px;font-size: 13px;color: #727272;">× {{item.num}} </span>
                </div>
                    <van-divider />
                </div>
            </div>
            <div><van-icon name="balance-list" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 菜品总价</span><span style="float: right;font-size: 17px;line-height: 30px;">￥{{this.$store.state.count.Original}}</span></div>
            <van-divider />

            <div><van-icon name="coupon" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 优惠金额</span><span style="float: right;font-size: 17px;line-height: 30px;">-￥{{this.$store.state.count.Original - this.$store.state.count.Actual }}</span></div>
            <van-divider />

            <div><van-icon name="gold-coin" style="position: relative;top:5px;" size="21px" color="#5dd5c8" /><span style="font-size: 17px"> 总计</span><span style="float: right;font-size: 17px;line-height: 30px;">￥{{this.$store.state.count.Actual}}</span></div>

        </div>
        </div>

<!--        订单信息结束-->

        <br>
        <br>
        <div style="margin: auto;width: 145px;">版权所有：未奇科技</div>
        <br>
        <br>
        <br>
        <br>
        <br>




    </div>
        <!--底部结算栏-->
        <div v-if="!payChooseShow && !tableShow && !payShowButton"  style="background-color:#3B3B3C;height: 50px;position: fixed;bottom: 0px;width: 100%;z-index: 5000" >
            <span style="color: #ffffff;font-size: 25px;position: relative;top: 6px;left: 10px;">￥{{this.$store.state.count.Actual}}</span>

            <span style="color: #ffffff;font-size: 11px;position: relative;top: 6px;left: 20px;">已优惠 ￥{{this.$store.state.count.Original - this.$store.state.count.Actual}}</span>
            <van-button  @click="payShow" color="#5dd5c8" style="float: right;height: 50px;width: 110px;"><span style="position: relative;font-size: 17px;">支付</span></van-button>
        </div>





        <!--        选择桌子出层-->
        <van-popup style="z-index: 10000" v-model="tableShow"  position="bottom">
            <van-picker  :columns="this.$store.state.tableList" @change="onChangeTable"  />
        </van-popup>


        <!--        选择订单类型出层-->
        <van-popup style="z-index: 10000" v-model="orderTypeShow"  position="bottom">
            <van-picker  :columns="orderType" @change="changeOrderType"  />
        </van-popup>

        <!--        选择支付方式出层-->
        <van-popup style="z-index: 10000" v-model="payChooseShow"  position="bottom">
            <van-picker  :columns="this.payList" @change="onChangePay" />
        </van-popup>


<!--        支付弹出层-->
        <van-popup v-if="payShowButton" v-model="payShowButton"  position="bottom" style="width: 100%;padding-bottom: 50px;height: 450px;">
            <div style="padding-top: 15px;height: 35px;border-bottom: 1px solid #FAFAFA; ">
              <img @click="payShowButton = false" src="../../static/images/cuo.png" style="float: left;position: relative;left:20px;height: 21px;width: 21px;">
                <img src="../../static/images/help.png" @click="$toast.success('请咨询客服')" style="width: 25px;height: 25px;float: right;position: relative;right: 10px;">

                <div style="width: 120px;margin: auto;">
                <img src="../../static/images/pay.png" style="height: 25px;width: 25px;">
                <span style="position: relative;top: -6px;font-size: 20px;">付款详情</span>
               </div>
            </div>

            <div style="margin-top: 50px;">
                <div style="width: 160px;margin: auto;">
                <span style="font-size: 25px;">￥</span><span style="font-size: 50px;">{{this.$store.state.count.Actual.toFixed(2)}}</span>
                </div>
            </div>

            <div style="margin-top: 20px;font-size: 18px;height: 30px;padding-bottom: 10px;padding-left: 20px;padding-right: 20px;border-bottom: 1px solid #FAFAFA;">
                <span style="color: #959595">店铺信息</span><span style="float: right;">{{this.$store.state.store.storeName}}</span>
            </div>
            <div style="margin-top: 20px;font-size: 18px;height: 30px;padding-bottom: 10px;padding-left: 20px;padding-right: 20px;border-bottom: 1px solid #FAFAFA;">
                <div @click="changePay" v-if="this.$store.state.payChoose === '微信'"><span style="color: #959595"> 付款方式</span> <van-icon name="arrow-down"  style="position: relative;top: 5px;float: right;"/>   <span style="float: right;"> </span> <van-icon name="wechat" style="position: relative;float: right;" size="29px" color="#2ba245"/><span style="float: right;font-size: 17px;top: 2px;right: 7px;position: relative;">微信</span></div>

                <div @click="changePay"  v-if="this.$store.state.payChoose === '支付宝'"><span style="color: #959595"> 付款方式</span> <van-icon name="arrow-down"  style="position: relative;top: 5px;float: right;"/>  <span style="float: right;"> </span> <van-icon name="alipay" style="position: relative;float: right;" size="29px" color="#00a0e9" /><span style="float: right;font-size: 17px;top: 2px;right: 7px;position: relative;">支付宝</span></div>
            </div>

            <div style="margin-top: 70px;">
                <div @click="pay" style="height: 50px;width: calc(90vw);margin: auto;background-color: #00a0e9;border-radius: 5px;">
                    <span style="font-size: 20px;line-height:50px;position: relative;left: calc((100% - 100px)/2);color: #ffffff;">立 即 付 款</span>
                </div>
            </div>


            <div style="width: 150px;margin: 20px auto;">
                <van-icon name="alipay" style="position: relative;" size="25px" color="#00a0e9" /><span style="position: relative;top: -6px;font-size: 15px;font-weight: bold;color: #00a0e9"> 点餐就用点餐宝</span>
            </div>




        </van-popup>

    </div>
</template>

<script>
    export default {
        name: "Settlement",
        data(){
            return{
                tableShow:false,
                payChooseShow:false,
                payShowButton:false,
                orderTypeShow:false,
                address:"",
                payList:["支付宝","微信"],
                orderType:["堂食","外卖"]
            }
        },
        methods:{
            changePay(){
                if (this.$store.state.payChoose === "微信"){
                    this.$store.commit("setPayChoose","支付宝")
                }else{
                    this.$store.commit("setPayChoose","微信")
                }
            },
            changeOrderType(){
                if (this.$store.state.orderType === "堂食"){
                    this.$store.state.orderType = "外卖"
                }else{
                    this.$store.state.orderType = "堂食"
                }
            },
            //支付及订单提交逻辑
            pay(){
                let temp;
                if (this.$store.state.orderType === "外卖"){
                     temp = 2
                }else{
                    temp = 1
                }
                let order = {
                    "storeId": this.$store.state.store.id,
                    "userId": this.$store.state.user.id,
                    "storeName":this.$store.state.store.storeName,
                    "userName":this.$store.state.user.name,
                    "userPhone":this.$store.state.user.phone,
                    "orderTable":this.$store.state.tableId,
                    "payChoose":this.$store.state.payChoose,
                    "orderCountActual":this.$store.state.count.Actual,
                    "orderCountOriginal":this.$store.state.count.Original,
                    "orderType":temp,
                    "remake":this.address,
                }
                for(let i = 0; i < this.$store.state.settlement.length; i++){
                    this.$store.state.settlement[i]['goodsNum'] = this.$store.state.settlement[i].num
                }
                this.$axios.post(this.$store.state.httpUrl+"/order/addOrder",{
                    "orderUser": order,
                    "orderGoods": this.$store.state.settlement,
                }).then(()=>{
                    this.payShowButton = false;
                    this.$store.state.showLoading = true;
                    let tempShoppingCar = {"isHaving":false,"list":{}}
                    for (let i = 0; i < this.$store.state.goodsList.length; i++){
                        for (let j = 0; j < this.$store.state.goodsList[i].goods.length; j++){
                            tempShoppingCar.list[this.$store.state.goodsList[i].goods[j].id] = {"num":0,"goods":this.$store.state.goodsList[i].goods[j]}
                        }
                    }
                    this.$store.state.count.Original = 0
                    this.$store.state.count.Actual = 0
                    this.$store.commit("setShoppingCar",tempShoppingCar)
                    setTimeout(()=>{
                        this.$store.state.showLoading = false;
                        this.$toast.success(this.$store.state.payChoose+"支付成功")
                        this.$router.push("/store/"+this.$store.state.store.id)
                    },1000)
                }).catch((res)=>{
                    console.log(res)
                    this.payShowButton = true
                    this.$toast.fail("网络错误")
                })

            },
            //支付弹出层
            payShow(){
                this.payShowButton = true
            },
            //桌子弹出层
            tableChooseShow(){
                this.tableShow = true;
            },
            //选择table
            onChangeTable(picker,index){
                console.log(index)
                this.$store.commit("setTableId",index)
            },
            //支付方式弹出
            PayChooseShow(){
                this.payChooseShow = true;
            },
            //选择支付方式
            onChangePay(picker,index){
                this.$store.commit("setPayChoose",index)
            },
            //数据处理
            dealData(){
                let tempSettlement = []
                let tempShoppingCar = this.$store.state.shoppingCar
                let tempCount = 0
                for(let key in tempShoppingCar.list){
                    let tempGoods = {}
                    if(tempShoppingCar.list[key].num > 0){
                        tempGoods = tempShoppingCar.list[key].goods
                        tempGoods['num'] = tempShoppingCar.list[key].num
                        tempCount = tempCount + tempShoppingCar.list[key].num*tempShoppingCar.list[key].goods.goodsPrice
                        tempSettlement.push(tempGoods)
                    }
                }
                this.$store.commit("setsettlment",tempSettlement)
                this.$store.commit("setsettlmentCount",tempCount)
                console.log(tempSettlement)
                console.log(tempCount)
            }
        },
        mounted() {
            this.dealData()
        },
        created() {
            if (!this.$store.state.shoppingCar.isHaving){
                this.$toast.fail("购物车无商品")
                this.$router.go(-1)
            }
            if (!this.$store.state.isLogin){
                 this.$router.push("/login")
                 this.$toast.fail("请先登录")
            }
        }

    }
</script>

<style scoped>
    .van-divider{
        margin: 10px 0 !important;
    }

</style>
