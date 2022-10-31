import Vue from 'vue'
import AppMainFrame from './AppMainFrame.vue'
import VueRouter from 'vue-router'
import Routes from './routes'

export const bus = new Vue();

Vue.use(VueRouter);

Vue.config.productionTip=false;

const router = new VueRouter({
  mode:'history',
  routes:Routes
});

new Vue({
  el: '#app',
  render: h => h(AppMainFrame),
  router:router
}).$mount('#app')
