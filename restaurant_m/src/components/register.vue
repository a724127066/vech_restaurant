<template>
  <div style class="e">
    <div class="w">
      <van-cell-group style="margin:10px">
        <van-field
          v-model="userAccount"
          style="width:270px;left:20px;"
          label-width="100px"
          clearable
          label="用户名"
          placeholder="请输入账号"
        />
        <van-field
          v-model="userPassword"
          style="width:270px;left:20px;"
          label-width="100px"
          type="password"
          label="密码"
          placeholder="请输入密码"
        />
        <van-field
          v-model="checkPassword"
          style="width:270px;left:20px;"
          label-width="100px"
          type="password"
          label="确认密码"
          placeholder="请再次输入密码"
        />
        <van-field
                v-model="userName"
                style="width:270px;left:20px;"
                label-width="100px"
                clearable
                label="用户名"
                placeholder="请输入用户名"
        />
        <van-field
          v-model="userPhoneNum"
          style="width:270px;left:20px;"
          label-width="100px"
          clearable
          label="手机号"
          placeholder="请输入手机号"
        />
      </van-cell-group>

      <div>
        <van-button @click="register()" type="primary" style="margin-top:5px;width:87%;">注册</van-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "register",
  data() {
    return {
      userAccount:"",
      userName: "",
      userPassword: "",
      checkPassword: "",
      userPhoneNum: ""
    };
  },
  methods: {
    register() {
      if (this.userPassword === "" || this.userName === "" || this.userPhoneNum === "" || this.userAccount === "") {
        this.$toast.fail('请输入信息！');
      } else if (this.userPassword !== this.checkPassword) {
        this.$toast.fail("两次密码不相同，请再次输入！");
      } else {
        this.$axios.post(this.$store.state.httpUrl+"/addUser",{
            "account": this.userAccount,
            "password":this.userPassword,
            "name":this.userName,
            "phone":this.userPhoneNum,
        }).then((res)=>{
          console.log(res)
          if (res.data.flag){
            this.$toast.success("注册成功！");
            this.$router.push("/login");
          }else{
            if (res.data.code === "20001"){
              this.$toast.fail("注册失败！");
            }else if(res.data.code === "20004"){
              this.$toast.fail("账号已存在！");
            }else{
              this.$toast.fail("注册失败！");
            }
          }

        }).catch(function (res) {
          console.log(res)
        })

      }
    }
  }
};
</script>

<style scoped>
.e {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: rgb(160, 250, 215);
  text-align: center;
  display: flex;
  justify-content: space-around;
}
.w {
  border-radius: 30px;
  width: 310px;
  height: 280px;
  background-color: rgb(186, 231, 235);
  text-align: center;
  margin-top: 200px;
  border: 5px solid #8ad4cd;
}
.q {
  display: flex;
}
</style>
