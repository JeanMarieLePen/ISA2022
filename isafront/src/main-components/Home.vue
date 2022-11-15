<template>
    <div id="div1" class="home_class" style="margin-bottom:100px;">
        <section id="sec1">
            <h2>
                Trazite brz i jednostavan nacin da pronadjete lokaciju centara za donaciju krvi? <span id="AppBold">TempNameApp</span> je aplikacija za Vas!
            </h2>
            <p style="font-size:25px;">
                <b><center>U svega nekoliko sekundi pretrazite ponudu stranice i pronadjite uslugu koja najbolje odgovara vasim zahtevima.</center></b>
            </p>

        </section>

        <section id="newsletter" style="height:100px;" class="bg-primary">
            <div class="container" >
                <div style="display:flex; align-items: center;">
                    <h2>Niste registrovani? Registrujte se klikom na dugme...</h2>
                    <form>
                        <router-link to='/register' class="nav-link" exact> 
                            <button type='submit' class="btn btn-success my-0">Registracija</button> 
                        </router-link>
                    </form>
                </div>
                
            </div>
        </section>
    </div>
</template>


<script>

export default{
    data(){
        return{

        }
    },
    // created(){
    //     if(localStorage.getItem('token') == null){
    //         bus.$emit('loggedIn', false);
    //     }
    // }
    methods:{
        goToSearch(){
            this.$router.push('/search')
        }
    }
}

</script>


<style scoped>

.home_class{
    background-color: #f4f4f4;
    color:#fff;
    text-align: center;
    /* height: 100%; */
    /* background-image:url('../assets/hospital.jpg');
    background-size: 100% 100%;
    background-repeat: no-repeat; */
    min-height: 600px;
}

/* div:first-child{
    height: 100vh;
} */
#div1{
    height: 90vh;
}
/* .home_class div:first-child{
    height: 10vh;
} */

#sec1{
    background-image:url('../assets/hospital.jpg');
    background-size: 100% 100%;
    min-height: 600px;
    /* background-size: cover; */
    background-repeat: no-repeat;
    height: 80%;
    margin-bottom:5px;
}

#AppBold{
 color:#428ae7;
 font-weight: bold;
}


</style>