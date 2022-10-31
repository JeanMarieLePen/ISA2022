<template>
    <div>
        <navigation></navigation>
        <router-view></router-view>
        <app-footer></app-footer>
    </div>
</template>


<script>
import axios from 'axios'
import 'bootstrap/dist/css/bootstrap.css'
import Navigation from './main-components/Navigation.vue'
import Footer from './main-components/Footer.vue'

export default {
    name:'app',
    components:{
      'navigation':Navigation,
      'app-footer':Footer,
    },
    data(){
        return{

        }
    }
}
</script>


<style scoped>

</style>