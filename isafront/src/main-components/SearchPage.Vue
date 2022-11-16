<template>
    <div style="width:100%;">
        <div style="margin-left:20px;margin-bottom:30px;">
            <h1>PRETRAGA CENTARA ZA DONACIJU</h1>
        </div>
        <div style="width:60%;">
            <div class="search-row">
                <div style="display:inline-block;">
                    <input style="border-radius: 10rem;" type="text" placeholder="Unesite naziv ustanove..." v-model="searchEntity.naziv"/>
                    <input style="border-radius: 10rem;" type="text" placeholder="Unesite adresu(deo adrese)..." v-model="searchEntity.adresa">
                    <input style="border-radius: 10rem;" type="number" placeholder="Unesite ocenu centra..." v-model="searchEntity.ocena">
                </div>
            </div>
            <div class="search-row"> 
                <div style="display:inline-block;">
                    <label>Od datuma:</label>
                    <datepicker v-model="searchEntity.odDatuma"></datepicker>
                    <label>Do datuma:</label>
                    <datepicker v-model="searchEntity.doDatuma" ></datepicker>
                </div>
            </div>
            <div class="search-row">
                <div style="dispaly:inline-block">
                    <button @click="search()" class="search-button">Pretrazi</button>
                    <button @click="ponistiPretragu()" class="anull-button">Ponisti</button>
                </div>
                
            </div>
        </div>
        <div style="margin-left:20px;margin-bottom:30px;">
            <h2>REZULTATI PRETRAGE</h2>
        </div>
        <!-- <div>
            <button class="filter-button">NAZIV</button>
            <button class="filter-button">OCENA</button>
            <button class="filter-button">GRAD</button>
        </div> -->
        <div style="margin-top:100px;margin-left:30px;" v-if="listaCentara.length == 0">
            <p style="font-family:Verdana;font-size:30px;font-style: italic;">LISTA CENTARA JE PRAZNA</p>
        </div>
        <!-- <div v-if="listaCentara.length > 0">
            <div v-for="(tempCentar, index) in listaCentara" v-bind:key="index">
                <p>Centar: {{tempCentar.naziv}}</p>
            </div>
        </div> -->

        <div v-show="listaCentara">
            <searchresults v-bind:listaCentara="listaCentara"></searchresults>
        </div>
    </div>    
</template>




<script>

import VueDatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css'
import dataService from '../services/dataService'
import SearchResults from './SearchResults.vue'
import moment from 'moment'
import { yandexMap, ymapMarker } from 'vue-yandex-maps'
import { loadYmap } from 'vue-yandex-maps';
import 'regenerator-runtime/runtime'
export default{
    
    data(){
        return{
            searchEntity:{
              naziv:'',
              adresa:'',
              ocena:'',
              odDatuma:'',
              doDatuma:'',
                
            },
            listaCentara:[],
            defaultDatum:null,
            browserLocation:[],
            settings : {
                apiKey: '5e04929d-e957-4fdd-b85c-08e60b3cb3f0',
                lang: 'en_US',
                coordorder: 'latlong',
                enterprise: false,
                version: '2.1',
                
            },
        }
    },
    created(){

        navigator.geolocation.getCurrentPosition(geolocation => {
            console.log(geolocation.coords.latitude);
            console.log(geolocation.coords.longitude);

            this.browserLocation.push(geolocation.coords.latitude);
            this.browserLocation.push(geolocation.coords.longitude);
            
        });
        console.log("Trenutna lokacija: "+this.browserLocation);
        dataService.getAllHospitals().then(response => {
            console.log("SVE USTANOVE: " + response.data.length);
            this.listaCentara = response.data;
        }).catch(error => {

        });
        this.setDefaultDates();        
            
    },
    components:{
        datepicker:VueDatePicker,
        searchresults:SearchResults,
        yandexMap, ymapMarker
    },
    async mounted(){
        await loadYmap({ ...this.settings, format:'json', debug: true});
        await dataService.getAllHospitals();
    },
    methods:{
        search(){
            if(this.searchEntity.ocena === ''){
                this.searchEntity.ocena= -1;
            }
            dataService.getCentarQuery(this.searchEntity).then(response=>{
            console.log("Pretraga QUERY");

                    console.log(JSON.stringify(response.data));
                    if(response.data == ''){
                        console.log("ODGOVOR NULL");
                        this.listaCentara = [];
                    }else{
                        console.log("Broj Pronadjenih:"+response.data.length);
                        this.listaCentara = response.data;

                        for(let i = 0; i < this.listaCentara.length; i++){
                        // console.log("Adresa: " + this.centri[i].adresa);
                        ymaps.geocode(this.listaCentara[i].adresa).then(res => {
                        let tmpObjekat = {
                            koordinateTemp : res.geoObjects.get(0).geometry.getCoordinates()
                        }
                        console.log(JSON.stringify(tmpObjekat));

                        let dst = this.getDistanceBetweenPoints(this.browserLocation[0], this.browserLocation[1], tmpObjekat.koordinateTemp[0], tmpObjekat.koordinateTemp[1]);
                        this.listaCentara[i].razdaljina=Number(dst.toFixed(2))//dst;
                        console.log("Razdaljina:"+dst);
                        console.log(JSON.stringify(this.listaCentara[i]));
                    });
                        
                    }
                }
            });
        },
        ponistiPretragu(){
            this.searchEntity.naziv="";
            this.searchEntity.adresa="";
            this.searchEntity.ocena=1;
            this.searchEntity.odDatuma="";
            this.searchEntity.doDatuma="";
        },
        odDatuma(){
            console.log('odabran datum: ' + this.searchEntity.odDatuma)
            // var date1 = moment(this.reservation.kraj).format("YYYY-MM-DDTHH:mm:ss")
            var tempDate = moment(this.searchEntity.odDatuma).format('YYYY-MM-DDTHH:mm:ss');
            console.log('odabran datum nakon formiranja: ' + tempDate)
            this.searchEntity.odDatuma = tempDate;
        },
        doDatuma(){
            console.log('odabran datum: ' + this.searchEntity.doDatuma)
            // var date1 = moment(this.reservation.kraj).format("YYYY-MM-DDTHH:mm:ss")
            var tempDate = moment(this.searchEntity.doDatuma).format('YYYY-MM-DDTHH:mm:ss');
            console.log('odabran datum nakon formiranja: ' + tempDate)
            this.searchEntity.doDatuma = tempDate;
        }, 
        setDefaultDates(){
            this.defaultDatum= moment(new Date()).format("YYYY-MM-DDTHH:mm:ss");
            let temp = new Date();
            let godina = temp.getFullYear();
            let mesec = temp.getMonth();
            let datum = temp.getDate() + 7;
            temp = new Date(godina, mesec, datum);
            this.doDatuma = moment(temp).format("YYYY-MM-DDTHH:mm:ss");
             console.log("GORNJA GRANICA: " + this.doDatuma);
        },
        //metoda koja po formuli izracunava razdaljinu u kilometrima; vraca razdaljinu
        getDistanceBetweenPoints(lat1,lon1,lat2,lon2){
            var R = 6371; // Radius of the earth in km
            var dLat = this.deg2rad(lat2-lat1);  // deg2rad below
            var dLon = this.deg2rad(lon2-lon1); 
            var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(this.deg2rad(lat1)) * Math.cos(this.deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
            var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
            var d = R * c; // Distance in km
            return d;
        },

        //pomocna metoda za pretvaranje stepeni u radijane
        deg2rad(deg){
            return deg * (Math.PI/100)
        },
    }
}

</script>



<style scoped>

.search-button{
    border-radius: 10rem;
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    width:150px;
    font-size: 15px;
    padding:10px;
    margin:10px;
    cursor: pointer;
    transition-duration: 0.4s;
}
.search-button:hover{
    background-color: #0275d8;
    color: white;
}

.anull-button:hover{
    background-color: #0275d8;
    color: white;
}

.anull-button{
    border-radius: 10rem;
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    width:150px;
    font-size: 15px;
    padding:10px;
    margin:10px;
    cursor: pointer;
    transition-duration: 0.4s;
}

.filter-button{
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    font-weight: 500;
    width:100px;
    font-size: 15px;
    padding:10px;
    margin:10px;
    cursor: pointer;
    transition-duration: 0.4s;
}
.filter-button:hover{
    background-color: #0275d8;
    color: white;
}

.search-row{
    margin-bottom: 15px;
    margin-top:15px;
    margin-left:20px;
    
    
}
</style>