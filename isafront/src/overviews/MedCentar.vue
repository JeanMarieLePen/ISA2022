<template>
    <div style="width:50%;">
        <div style="margin-left:10px">
            <h1>Podaci o centru</h1>

            <table style="margin-left:10px;" id="table_overview">
                <tr>
                    <td>
                        Naziv:
                    </td>
                    <td>
                        <input type="text" readonly v-model="medCentar.naziv">
                    </td>
                </tr>
                <tr>
                    <td>
                        Adresa:
                    </td>
                    <td>
                        <input type="text" readonly v-model="medCentar.adresa">
                    </td>
                </tr>
                <tr>
                    <td>
                        Radno vreme:
                    </td>
                    <td>
                        <input type="text" readonly v-model="radnoVreme">
                    </td>
                </tr>
                <tr>
                    <td>
                        Ocena:
                    </td>
                    <td>
                        <starrating read-only v-model="medCentar.ocena"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Krvna grupa:
                    </td>
                    <td>
                        <select @change="selectGroup()" v-model="selectedGroup">
                            <option value="1">A grupa</option>
                            <option value="2">AB grupa</option>
                            <option value="3">B grupa</option>
                            <option value="4">Nulta grupa</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input style="width:100%;" placeholder="Stanje zaliha odabrane grupe izrazeno u litrima..." v-model="bloodReserve" type="text" readonly>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div v-if="medCentar.opis">
                            <textarea style="width:100%;height:150px;overflow:auto" v-text="medCentar.opis" readonly>

                            </textarea>
                        </div>
                        <div v-if="!medCentar.opis" v-text="'Kratak opis medicinskog centra...'"></div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align:center">
                        <button class="termini-button" @click="showTermine()">
                            Pregled termina
                        </button>
                    </td>
                </tr>
                <tfoot>
                    <tr>
                        <td colspan="2">

                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <yandex-map 
                                :settings="settings"
                                :coords="pocetneKoordinate"
                                zoom="5"
                                style="width: 100%; height: 600px;"
                                language=en-US
                                >

                                <ymap-marker
                                    v-for="(tmp, index) in lokacija" :key="index" :marker-id="index" :coords="tmp.koordinateTemp" :balloon-template="balloonTemplate">
                                </ymap-marker>

                                <ymap-marker
                                    :marker-id="1" :coords="pocetneKoordinate" :balloon-template="balloonTemplate">
                                </ymap-marker>
                            </yandex-map> 
                        </td>
                    </tr>
                </tfoot>
            </table>
            
        </div>
    </div>
</template>

<script>

import dataService from '../services/dataService'
import StarRating from 'vue-star-rating'
import { yandexMap, ymapMarker } from 'vue-yandex-maps'
import { loadYmap } from 'vue-yandex-maps';
import 'regenerator-runtime/runtime'
import moment from 'moment'

export default {
    components:{
        starrating:StarRating,
        yandexMap, ymapMarker
    },
    setup() {
        
    },

    data(){
        return{
            settings : {
                apiKey: '5e04929d-e957-4fdd-b85c-08e60b3cb3f0',
                lang: 'en_US',
                coordorder: 'latlong',
                enterprise: false,
                version: '2.1',
                
            },
            pocetneKoordinate:[],
            lokacija:{

            },
            radnoVreme:'',
            selectedGroup:1,
            idCentra:'',
            medCentar:{

            },
            bloodReserve: 0,
        }
    },
    created(){
        this.idCentra = this.$route.params.id;
        navigator.geolocation.getCurrentPosition(geolocation => {
            this.pocetneKoordinate.push(geolocation.coords.latitude);
            this.pocetneKoordinate.push(geolocation.coords.longitude);
        });
        // dataService.getMedCentarById(this.idCentra).then(response =>{
        //     console.log("Dobavljen MedCentar: " + JSON.stringify(response.data));
        //     this.medCentar = response.data;
        //     this.bloodReserve = this.medCentar.aGrupa;
        // });
        
    },
    methods:{
        async getMedCentar(){
            return dataService.getMedCentarById(this.idCentra).then(response => {
                console.log("Dobavljen MedCentar: " + JSON.stringify(response.data));
                this.medCentar = response.data;
                this.bloodReserve = this.medCentar.aGrupa;
                this.radnoVreme = moment(this.medCentar.radnoVreme.pocetak).format("HH:MM") + '-' +moment(this.medCentar.radnoVreme.kraj).format("HH:MM")
                ymaps.geocode(this.medCentar.adresa).then(response => {
                    let tmpObjekat = {
                        koordinateTemp : response.getObjects.get(0).geometry.getCoordinates()
                    }
                    this.lokacija = tmpObjekat;
                })
            }); 
        },
        showTermine(){
            this.$router.push(`/termini/${this.medCentar.id}`);
        },
        selectGroup(){
            console.log("GRUPA: " + this.selectedGroup)
            if(this.selectedGroup == 1){
                this.bloodReserve = this.medCentar.aGrupa;
            }else if(this.selectedGroup == 2){
                this.bloodReserve = this.medCentar.abGrupa;
            }else if(this.selectedGroup == 3){
                this.bloodReserve = this.medCentar.bGrupa;
            }else if(this.selectedGroup == 4){
                this.bloodReserve = this.medCentar.nultaGrupa;
            }
            console.log("REZERVA: " + this.bloodReserve)
        }
    },
    computed:{
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
        await this.getMedCentar();
    }

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