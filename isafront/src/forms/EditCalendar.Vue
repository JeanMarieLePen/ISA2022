<template>
    <div style="width:50%;">
        <div style="margin-left:10px">
            <h1>Podaci o centru</h1>

            <table style="margin-left:10px;" id="table_overview">
                <tr>
                    <td>
                        Naziv:
                    </td>
                    <td colspan="2">
                        <input type="text" readonly v-model="medCentar.naziv">
                    </td>
                </tr>
                <tr>
                    <td>
                        Adresa:
                    </td>
                    <td colspan="2">
                        <input type="text" readonly v-model="medCentar.adresa">
                    </td>
                </tr>
                <tr>
                    <td>
                        Radno vreme:
                    </td>
                    <td colspan="2">
                        <input type="text" readonly v-model="radnoVreme">
                    </td>
                </tr>
                <tr><td>NoviTermin:</td><td>Pocetak-Kraj</td><td>BrojOsoba</td></tr>
                <tr>
                    <td>
                        <datepicker v-model="odabraniDatum" @input="changedDate"></datepicker>
                        <button class="btn  btn-danger marg float-center">Dodaj Novi</button>
                    </td>
                    <td>
                        <timepicker v-model="pocetakTime" ></timepicker>
                        <timepicker v-model="krajTime" ></timepicker>
                    </td>
                    <td> <input type="number" v-model="brojOsoba"></td>
                    
                    
                   
                </tr>
                <tr>
                    <td>Termini:</td>
                    
                </tr>  
                
            </table>
            
        </div>
    </div>
</template>
<script>
import VueDatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css'
import DataService from '../services/dataService'
import VueTimePicker from 'vue2-timePicker'
    export default{
        data(){
            return{
                medCentar:{
                    id:'',
                    naziv:'',
                    adresa:'',
                    opis:'',
                    ocena:'',
                    aGrupa:'',
                    bGrupa:'',
                    abGrupa:'',
                    nultaGrupa:'',
                    radnoVreme:{
                        pocetak:'',
                        kraj:'',
                    },
                    termini:{},
                    listaZaposlenih:{}
                },
                radnoVreme:'',
                userId : '',
                username : '',
                //Ostaviti proil objekat zbog mapiranja
               // role:'',
               // ownerId:'',
               odabraniDatum:'',
               termin:{
                id:'',
                pocetak:'',
                kraj:''
               },
               terminList:{},
               p1:'',
               pocetakTime:'',
               krajTime:'',
               brojOsoba:'',

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
                 this.getUserBolnica(tempId);
            }
        },
        components:{
            datepicker:VueDatePicker,
            timepicker: VueTimePicker,

        },
        methods:{
            getUserBolnica(id){
            DataService.getMedCentarByOsoblje(id).then(response => {
                console.log("USER PROFILE: " + JSON.stringify(response.data));
                this.medCentar = response.data;
                this.p1 = new Date(this.medCentar.radnoVreme.pocetak)
                this.p2= new Date(this.medCentar.radnoVreme.kraj)
                this.radnoVreme=this.p1.getHours()+" : "+this.p1.getMinutes()+' - '+this.p2.getHours()+" : "+this.p2.getMinutes()
                })
            },
            changedDate(){

            }
            
        },
        computed:{
        id() {
            return this.$route.params.id; //preuzimam id usera na cijoj sam stranici za prikaz profila
        },  
        },
        
    }
</script>
<style scoped>
#table_overview{
        font-size: 20px;
        font-style: oblique;
        font-weight: 600;
    }
    #table_overview input{
        width:100%;
    }
    #table_overview select{
        width:100%;
    }
    
    #table_overview tr{
        margin-top: 5px;
        margin-bottom: 5px;
    }
    #table_overview tr:nth-child(even){
        background-color:cornflowerblue
    }
    #table_overview td:first-child{
        width:20%;
    }
    #table_overview td:last-child{
        width: 200px;
    }
    
    .termini-button{
        color: #0275d8;
        border: 2px solid cornflowerblue;
        background-color: white;
        font-weight: 500;
        width:200px;
        font-size: 15px;
        padding:10px;
        margin:10px;
        cursor: pointer;
        transition-duration: 0.4s;
    }
    .termini-button:hover{
        background-color: cornflowerblue;
        color: white;
    }
</style>