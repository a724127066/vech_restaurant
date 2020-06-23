<template>
    <div style="padding-top: 46px;">
<!--        顶部注册按钮 -->
         <div style="float: right;margin-right: 20px;margin-top: 10px;">
          <router-link to="/register">   <van-button  style="color:#ffffff;background-color: #5dd5c8;border-radius: 7px;" size="small" >注册</van-button></router-link>
         </div>

<!--登录主体部分-->
        <div style="width: 73%;margin: 110px auto;">
            <img src="../../static/images/loginHeader.png" style="width: 150px;height: 100px;margin-left: calc((100% - 150px)/2)">
            <div style="height: 100px;border: solid #5dd5c8 1px;border-radius: 10px;">
                <div style="height: 50px;border-bottom: solid #5dd5c8 1px; ">
                    <van-icon name="manager-o"  color="#5dd5c8" size="28" style="margin-top: 10px;margin-left: 9px;"/>
                    <input type="text" v-model="account" placeholder="用户名" style="border:none;height: 30px;position: relative;left: 15px;top: -5px">
                </div>
                <div style="height: 50px;">
                    <van-icon name="description"   color="#5dd5c8" size="28" style="margin-top: 10px;margin-left: 9px;"/>


                    <input  :type="passwordType" v-model="password" placeholder="密码" style="border:none;height: 30px;position: relative;left: 15px;top: -5px">

                    <van-icon :name="passwordIcon" style="float:right;margin: 17px 15px;" size="20" @click="changePasswordType" />
                </div>


                <div style="width: 100%;margin: 30px auto">
                    <van-button  style="width: 100%;border-radius: 5px;" @click.native="login" color="linear-gradient(to right, #5dd5c8, #5dd5c494)">登录</van-button>
                </div>
                <div style="float: right;font-size: 16px;">
                    <span @click="noDev">无法登录？</span><br>
                    <span @click="noDev">忘记密码？</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import jwt_decode from 'jwt-decode'
    export default {
        name: "login",
        data(){
            return{
                account:"",
                password:"",
                passwordType: 'password',
                passwordIcon:"closed-eye",
            }
        },
        methods:{
            ckName(str){
                let patten = /^[a-zA-Z]\w{4,15}$/ig;
                if(!patten.test(str))
                {
                    return false;
                }
                return true;
            },
          changePasswordType(){
              if (this.passwordType === 'password'){
                 this.passwordType = 'text'
                  this.passwordIcon = "eye-o"
              }else{
                  this.passwordType = 'password'
                  this.passwordIcon = "closed-eye"
              }

          },
            noDev(){
              this.$toast.fail("此功能在开发")
            },

            login(){
                console.log(this.account)
                if (!this.ckName(this.account)){
                    this.$toast.fail("账号格式错误")
                }else if(this.password === null){
                    this.$toast.fail("密码为空")
                }else if(this.password.length < 6 || this.password.length > 20){
                    this.$toast.fail("密码格式错误")
                }
                else if(this.$store.state.isLogin){
                    this.$toast.fail("请不要重复登陆")
                }
                else{
                    let that = this;
                    this.$axios.defaults.headers.post['Content-Type'] = 'application/json';
                     this.$axios.post(this.$store.state.httpUrl+"/loginUser",JSON.stringify(
                        {"account": this.account,
                            "password":this.password,}
                    )).then(function (res) {
                        console.log(res.data)

                        if (!res.data.flag){
                            if (res.data.code === 20003){
                                that.$toast.fail("重复登陆")
                            }
                            if (res.data.code === 20001){
                                that.$toast.fail("账号不存在或密码错误")
                            }
                            if (res.data.code === 20005){
                                that.$toast.fail("账号不存在")
                            }
                        }else{

                            let token = res.data.data.token;
                            localStorage.setItem("token",token)
                            that.$store.commit("login",jwt_decode(token).data)
                            that.$store.commit("setToken",token)
                            that.$toast.success({message: '登录成功,'+ that.$store.state.user.name+'欢迎你的使用', type: 'success'});
                            that.$router.push("/store/"+that.$store.state.store.id)
                            // window.location.href='/';
                        }

                    }).catch(function(res){
                        console.log(res)
                        that.$toast.fail("网络错误")
                    })

                }
            },
        },
    }
</script>

<style scoped>

</style>
