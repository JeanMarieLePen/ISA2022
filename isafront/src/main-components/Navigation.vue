<template>
    <div id="navigation">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary static-top">
            <a class="navbar-brand"  href="/"><h1><span id="pageTitle">TempNameApp</span></h1></a>
            <div class="collapse navbar-collapse">
                <ul class="navbar-nav mr-auto">
                    <li v-show="!loggedIn" class="nav-item active">
                        <router-link to='/home' class="nav-link" exact>Home</router-link>
                    </li>
                    <li v-show="loggedIn" class="nav-item active">
                        <router-link class="nav-link" to='/home' exact>Home</router-link>
                    </li>
                    <li class="nav-item active">
                        <router-link to='/about' class="nav-link" exact>About</router-link>
                    </li>
                </ul>
                <ul class="nav my-2 my-lg-0">
                    <!-- <li class="nav-item">
                        <label v-show="loggedIn" v-on:click="logOut()">AAAA</label>
                    </li> -->
                    <li class="nav-item">
                        <button class="btn btn-success" v-show="!loggedIn" @click="$router.push('/login')">Log in</button> 
                    </li>
                    <li class="nav-item">
                        <button class="btn btn-danger" v-show="loggedIn" v-on:click="logOut()">Log Out</button>
                    </li>
                </ul>
            </div>
        </nav>
    </div>
</template>
</template>

<script>

import axios from 'axios'
import {bus} from '../main'
export default {
    name:'Navigation',
    methods:{
        logOut:function(){
            if(confirm('Da li ste sigurni da zelite da se odjavite?')){
                if(localStorage.getItem('token')){
                    //ukoliko se token pronadje, znaci da je korisnik ulogovan
                    localStorage.removeItem('token');
                    axios.defaults.headers.common['Authorization'] = undefined;
                    this.loggedIn = false;
                    this.$router.push("/")
                    if(localStorage.getItem('parsToken')){
                        localStorage.removeItem('parsToken');
                    }
                }else{
                    //kada se obrise istorija pretrage npr
                    localStorage.removeItem('token');
                    axios.defaults.headers.common['Authorization'] = undefined;
                    this.loggedIn = false;
                    this.$router.push("/")

                }
            }
        }
    },
    data(){
        return{
            loggedIn: localStorage.getItem('token') ? true : false
        }
    },
    created(){
        bus.$on('loggedIn', (data) => {
            this.loggedIn = data;
        });
    }
}
</script>


<style scoped>

#pageTitle{
  margin-right: 100px;
  font-size: 30px;
  font-family: Verdana;
  color:oldlace
}
#navigation{
  color:#fff;
  min-height: 100px;
}
.nav-link{
  font-size: 20px;
}
</style>