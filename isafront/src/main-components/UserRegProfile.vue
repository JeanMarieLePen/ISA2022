<template>
    <div style="margin-bottom:200px;">
        <div class="container" id='page-title'>
            <h1 style="margin-top:10px;color:#35424a;"><span id='titleEffect'>Pregled naloga</span></h1>
            <hr style='background:#35424a;height:1px'>
        </div>
        <div class="container">
            <form>
                <ul id="services" class="list-group">
                <!-- Osoba -->
                    <li class="list-group-item" >
                        <h5 class="header5">Tip korisnika</h5>
                        <h4>{{profile.tipKorisnika}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Korisnicko ime</h5>
                        <h4>{{profile.korIme}}</h4>
                    </li>
                    <li class="list-group-item">
                        <h5 class="header5">Email adresa</h5>
                        <h4>{{profile.email}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Ime</h5>
                        <h4>{{profile.ime}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Prezime</h5>
                        <h4>{{profile.prezime}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Adresa</h5>
                        <h4>{{profile.adresa}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Datum Rodjenja</h5>
                        <h4>{{profile.datumRodjenja}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Poslednja Donacija</h5>
                        <h4>{{profile.poslednjaDonacija}}</h4>
                    </li>


                    <li class="list-group-item" >
                        <h5 class="header5">Penali</h5>
                        <h4>{{profile.penali}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Loyalty poeni</h5>
                        <h4>{{profile.poeni}}</h4>
                    </li>
                    <li class="list-group-item" >
                        <h5 class="header5">Loyalty kategorija</h5>
                       <h4>{{profile.kategorija.naziv}}</h4>
                    </li>
                    
                </ul>

                <div id='buttonUpdate'>
                    <button :disabled='btnEnabled' type='button' class="btn btn-lg btn-success marg float-left" v-on:click='updateUser(userId)'> Update</button> 
                     <button type='button' class="btn btn-lg btn-danger marg float-right" v-on:click='deleteUser(this.userId)'> Delete</button> 
                </div>
                
            </form>
        </div>

    </div>

</template>

<script>

import DataService from '../services/dataService'
    export default{
        data(){
            return{
                profile:{
                    kategorija:{},
                },
                btnEnabled:false,
                userId : '',
                username : '',
                //Ostaviti proil objekat zbog mapiranja
                role:'',
                ownerId:'',
            }
        },
        created(){ console.log("PARAM : "+this.$route.params.id)
           // if(this.$route.params.id != 'undefined'){
           //     this.getUserProfileData(this.$route.params.id)
            //}
            if(JSON.parse(localStorage.getItem('token')) == null){
                this.$router.push(`/login`);
            }
            else{
                
                 let tempUsername = localStorage.getItem('parsToken').split(",")[0].split(':')[1];
                 tempUsername = tempUsername.slice(1, tempUsername.length - 1).trim();
                 this.username = tempUsername;
                 let tempId = localStorage.getItem('parsToken').split(",")[3].split(':')[1];
                 tempId = tempId.trim();
                 this.userId = tempId;
                 console.log("ID KORISNIKA: " + tempId)
                 this.getUserProfileData(tempId);
            }
        },
        methods:{
            getUserProfileData(id){
            DataService.getUser(id).then(response => {
                console.log("USER PROFILE: " + JSON.stringify(response.data));
                this.profile = response.data;
                })
            },
            updateUser(id){ 
                this.$router.push(`/profile/${id}/update`);
            },
            deleteUser(id){ 
                this.$router.push(`/deleteAccount/${id}`)
            //   DataService.deleteUserProfile(id).then(response => {
             //         this.profile = response.data;
            //     })
            },
        },
        computed:{
        id() {
            return this.$route.params.id; //preuzimam id usera na cijoj sam stranici za prikaz profila
        },  
        },
        
    }

</script>

<style scoped>

#titleEffect{
  color:#1E90FF;
  font-weight: bold;
}

#user-profile ul#services li{
    list-style: none;
    padding:15px;
    border-bottom: gray solid 1px;
    border-radius: 15px;
    margin-bottom: 3px; 
    background-color:#fff;
    width:100%;
}

/* #user-profile#buttonUpdate{
  float:left;
} */


.header5{
    color:#1E90FF;
    font-weight: bold;
}

.marg{
    margin-top:15px;
}

</style>