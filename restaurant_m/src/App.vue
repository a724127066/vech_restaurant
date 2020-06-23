<template>
    <div id="app">
        <v-header v-if="isShowHeader"/>

        <transition :name="transitionName">
        <router-view/>
        </transition>
        <VFoot v-if="isShowFoot"/>


        <van-overlay z-index="9999" :show="this.$store.state.isShowLoading" @click="this.$store.state.isShowLoading = false">
            <div class="wrapper">
                <van-loading color="#1989fa">加载中...</van-loading>
            </div>
        </van-overlay>




    </div>
</template>

<script>
    import VHeader from "./components/VHeader";
    import VFoot from "./components/VFoot";

    export default {
        name: 'app',
        components: {
            VHeader,
            VFoot
        },
        data(){
            return{
                isShowHeader: false,
                isShowFoot: false,
                transitionName:'slide-left',
            }
        },
        watch:{
            $route(to,from){
                this.isShowHeader = this.$route.meta.isShowHeader;
                this.isShowFoot = this.$route.meta.isShowFoot;
                //如果to索引大于from索引,判断为前进状态,反之则为后退状态
                if(to.meta.index  > from.meta.index ){
                    //设置动画名称
                    this.transitionName = 'slide-left';
                }else{
                    this.transitionName = 'slide-right';
                }
            }
        },
    }
</script>

<style>

    .wrapper {
        display: flex;
        align-items: center;
        justify-content: center;
        height: 100%;
    }

    .slide-right-enter-active,
    .slide-right-leave-active,
    .slide-left-enter-active,
    .slide-left-leave-active {
        will-change: transform;
        transition: all 250ms;
        position: absolute;
    }
    .slide-right-enter {
        opacity: 0;
        transform: translate3d(-100%, 0, 0);
    }
    .slide-right-leave-active {
        opacity: 0;
        transform: translate3d(100%, 0, 0);
    }
    .slide-left-enter {
        opacity: 0;
        transform: translate3d(100%, 0, 0);
    }
    .slide-left-leave-active {
        opacity: 0;
        transform: translate3d(-100%, 0, 0);
    }

</style>


