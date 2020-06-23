<template>
    <div>
        <van-tabbar v-model="active" active-color="#FFC300" inactive-color="#000000">
            <van-tabbar-item icon="home-o">好店</van-tabbar-item>
            <van-tabbar-item icon="search">搜索</van-tabbar-item>
            <van-tabbar-item icon="friends-o">我的</van-tabbar-item>
        </van-tabbar>
    </div>
</template>

<script>
    export default {
        name: "VFoot",
        data() {
            return {
                active: 0
            }
        },
        created(){
            this.active = this.$route.meta.active;
        },
        watch:{
            $route(to,from,){
                this.active =  this.$route.meta.active
                console.log( this.$route.meta.active)
            }
        },
    }
</script>

<style scoped>




</style>