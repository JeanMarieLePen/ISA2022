import Vue from 'vue'
import AppMainFrame from './AppMainFrame.vue'
import VueRouter from 'vue-router'
import Routes from './routes'

import YmapPlugin from 'vue-yandex-maps'
export const bus = new Vue();

Vue.use(VueRouter);


Vue.config.productionTip=false;

const router = new VueRouter({
  mode:'history',
  routes:Routes
});


Vue.mixin({
  methods:{
    checkLoginStatus(){
      if(JSON.parse(localStorage.getItem('token'))==null){
        this.$router.push(`/login`);
      }
    },
    parseJwt(token){
      console.log("TOKEN: " + token)
      if(token != null){
        var base64Url = token.split('.')[1];
        var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
            return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        }).join(''));

        return JSON.parse(jsonPayload);
      }
    }
      
  }
});

new Vue({
  el: '#app',
  render: h => h(AppMainFrame),
  router:router
}).$mount('#app')
