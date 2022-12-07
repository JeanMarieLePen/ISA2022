<template>
    <div style="width:100%">
        <table>
            
            <tr>
                <td>
                    <button class="filter-button" style="margin-left:10px" id="buttonSort" @click="tempSort('nazivEntiteta')">Naziv 
                        <img v-if='currentSortDir == "asc" && currentSort== "nazivEntiteta"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "nazivEntiteta" ' src='../assets/down-arrow1.1.png'>
                    </button>
                </td>
                <td>
                    <button class="filter-button" id="buttonSort" @click="tempSort('lokacijaEntiteta')">Lokacija 
                        <img v-if='currentSortDir == "asc" && currentSort== "lokacijaEntiteta"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "lokacijaEntiteta" ' src='../assets/down-arrow1.1.png'>
                    </button>
                </td>
                <td>
                    <button class="filter-button" id="buttonSort"  @click="tempSort('ocenaEntiteta')">Ocena
                        <img v-if='currentSortDir == "asc" && currentSort== "ocenaEntiteta"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "ocenaEntiteta" ' src='../assets/down-arrow1.1.png'>
                    </button>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <input style="margin-left:10px; width:100%" v-model="inputFilter" placeholder="Filter rezultata pretrage...">
                </td>
            </tr>
            <tr style="height:30px;"/>

        </table>
        <section style="margin-left: 10px; margin-bottom:100px;">
            <div>
            <!-- if we are 3 cards wide start a new row -->
                <div class="row">
                    <div class="col-md-3" v-bind:key="index" v-for="(tempHospital, index) in sortEntities.slice(0,15)">
                        <div style="width:90%;height:250px; margin-bottom:100px;" class="card">
                            <!-- <img class="card-img-top" :src="product.thumbnailUrl" alt="card image collar"> -->
                            <div class="card-body">
                                <h5 class="card-title">Ustanova {{tempHospital.naziv}}</h5>
                                <p class="card-text">Ustanova {{tempHospital.naziv}}</p>
                                <p class="card-text">{{tempHospital.adresa}}</p>
                                <starrating style="margin-bottom:10px" read-only v-model="tempHospital.ocena" :star-size="20"/>
                                
                            </div>
                            <div style="margin-bottom:30px;margin-top:50px;" class="card-foot">
                            <button style="margin-left:30px;" v-on:click="hospitalDetails(tempHospital.id)" class="btn btn-primary">Detalji</button>
                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section style="margin-top:200px;margin-left: 10px; margin-bottom:100px;">
             <yandex-map 
            :settings="settings"
            :coords="koordinate"
            zoom="5"
            style="width: 50%; height: 600px;"
            language=en-US
            >

            <ymap-marker
                v-for="(tmp, index) in lokacije" :key="index" :marker-id="index" :coords="tmp.koordinateTemp" :balloon-template="balloonTemplate">
            </ymap-marker>

            <ymap-marker
                :marker-id="1" :coords="koordinate" :balloon-template="balloonTemplate">
            </ymap-marker>
        </yandex-map>   

        </section>
    </div>
</template>



<script>

import StarRating from 'vue-star-rating'
import dataService from '../services/dataService';
import { yandexMap, ymapMarker } from 'vue-yandex-maps'
import { loadYmap } from 'vue-yandex-maps';
import 'regenerator-runtime/runtime'

export default{
    components:{
        starrating:StarRating,
        yandexMap, ymapMarker
    },  
    data(){
        return{
            listaCentara:[],
            currentSort : 'nazivEntiteta',
            currentSortDir : 'asc',
            inputFilter : '',

            koordinate:[],
            settings : {
                apiKey: '5e04929d-e957-4fdd-b85c-08e60b3cb3f0',
                lang: 'en_US',
                coordorder: 'latlong',
                enterprise: false,
                version: '2.1',
                
            },
            centri:[],
            lokacije:[],
            center: { 
                lat: 39.7837304, 
                lng: -100.4458825 
            },

        }
    },
    methods:{
        hospitalDetails(id){
            this.$router.push(`/medCentar/${id}`);
        },
        tempSort(s){
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            }
            this.currentSort = s;
        },
        filterByName(rst){
            if(this.inputFilter!= 0){
                return(rst.naziv.toLowerCase().indexOf(this.inputFilter.toLocaleLowerCase()) > -1 || rst.adresa.toLowerCase().indexOf(this.inputFilter.toLowerCase()) > -1);
            }
            return true;
        },
        sortiraj(tmpLista){
            let tempList = [];
            if(this.currentSort == 'nazivEntiteta'){
                if(this.currentSortDir == 'asc'){
                    tempList = tmpLista.sort((a, b) => (a.naziv > b.naziv) ? 1 : -1);
                }
                else{
                    tempList = tmpLista.sort((a, b) => (a.naziv < b.naziv) ? 1 : -1)
                }
            }
            if(this.currentSort == 'lokacijaEntiteta'){
                if(this.currentSortDir == 'asc'){
                    tempList = tmpLista.sort((a, b) => (a.adresa > b.adresa) ? 1 : -1);
                }
                else{
                    tempList =  tmpLista.sort((a, b) => (a.adresa < b.adresa) ? 1 : -1)
                }
            }
            if(this.currentSort == 'ocenaEntiteta'){
                if(this.currentSortDir == 'asc'){
                    tempList = tmpLista.sort((a, b) => (a.ocena > b.ocena) ? 1 : -1);
                }
                else{
                    tempList = tmpLista.sort((a, b) => (a.ocena < b.ocena) ? 1 : -1);
                }
            }
            return tempList;
        },
        async getHospitals(){
            return dataService.getAllHospitals().then(response => {
                this.centri = response.data;
                console.log("DOBAVLJENE BOLNICE: " + this.centri.length); 
                for(let i = 0; i < this.centri.length; i++){
                    // console.log("Adresa: " + this.centri[i].adresa);
                    ymaps.geocode(this.centri[i].adresa).then(res => {
                        let tmpObjekat = {
                            koordinateTemp : res.geoObjects.get(0).geometry.getCoordinates()
                        }
                        this.lokacije.push(tmpObjekat);
                        console.log(JSON.stringify(this.distance));
                    });
                }
            });
        }
    },
    created(){
        dataService.getAllHospitals().then(response => {
            console.log("Status zahteva: " + response.status);
            console.log("JEDNA BOLNICA: " + JSON.stringify(response.data[0]))
            this.listaCentara = response.data;

            navigator.geolocation.getCurrentPosition(geolocation => {
                this.center = {
                    lat: geolocation.coords.latitude,
                    lng: geolocation.coords.longitude
                };
                this.koordinate.push(geolocation.coords.latitude);
                this.koordinate.push(geolocation.coords.longitude);
            });
        });
    },
    computed:{
        sortEntities(){
            let tempLista = this.listaCentara.filter(this.filterByName);
            let tempLista2 = this.sortiraj(tempLista);
            return tempLista2;
        },
        balloonTemplate() {
            return `
                <h1 class="red">Medicinski centar!</h1>
                <p>I am here: ${this.koordinate}</p>
                <img src="http://via.placeholder.com/350x150">
            `;
        }
    },
    async mounted(){
        await loadYmap({ ...this.settings, format:'json', debug: true});
        await this.getHospitals();
    }
}
</script>


<style scoped>
.filter-button{
    color: #0275d8;
    border: 2px solid #0275d8;
    background-color: white;
    font-weight: 500;
    width:130px;
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
</style>