<template>
    <div>
        <van-nav-bar style="background:#5dd5c8 " :title="title"    :left-arrow="isShowBack"  @click-left="onClickLeft" :fixed="this.$route.meta.isFlexHeader"/>
    </div>
</template>

<script>
    export default {
        name: "VHeader",
        data(){
            return{
                title:"首页",
                isShowBack : false,
            }
        },
        methods: {
            onClickLeft() {
                this.$router.go(-1);
            },
        },
        created(){
            this.isShowBack = this.$route.meta.isShowBack;
            this.title = this.$route.meta.name;
            if (this.title === '商店'){
                this.title = this.$store.state.store.name
            }
        },
        watch:{
            $route(){
                this.isShowBack = this.$route.meta.isShowBack;
                this.title = this.$route.meta.name;
                if (this.title === '商店'){
                    this.title = this.$store.state.store.storeName
                }

            }
        },
    }
</script>

<style scoped>
    .van-nav-bar .van-icon{
        color: #000000;
    }

</style>
