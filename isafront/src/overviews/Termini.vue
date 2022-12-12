<template>
    <div>
        <h1>Pregled termina centra</h1>
        <table>
            
            <tr>
                <td>
                    <button class="sort-button" style="margin-left:10px" id="buttonSort" @click="tempSort('datum')">Datum 
                        <img v-if='currentSortDir == "asc" && currentSort== "datum"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "datum" ' src='../assets/down-arrow1.1.png'>
                    </button>
                </td>
                <td>
                    <button class="sort-button" id="buttonSort" @click="tempSort('vreme')">Vreme 
                        <img v-if='currentSortDir == "asc" && currentSort== "vreme"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "vreme" ' src='../assets/down-arrow1.1.png'>
                    </button>
                </td>
            </tr>
            <tr style="height:30px;"/>

        </table>
        <div v-show="listaTermina.length > 0" class="row" style="margin-top:100px;margin-bottom:100px;">
            <div class="col-md-3" style="width:20%;margin:20px;height:300px;margin-bottom:200px;" v-for="(tempTermin, index) in sortEntities" v-bind:key="index">          
                <div style="width:90%;height:500px; " class="card">
                    <div style="text-align:center" class="card-title">
                        <h2>Termin: {{tempTermin.id}}</h2>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Pocetak: {{datumFormat(tempTermin.pocetakTermina)}}</p>
                        <p class="card-text">Kraj: {{datumFormat(tempTermin.krajTermina)}}</p>
                        <p class="card-text">Broj mesta: {{tempTermin.brojMesta}}</p>
                        <p class="card-text">Broj slobodnih mesta: {{tempTermin.brSlobodnihMesta}}</p>
                    </div>
                    <div style="height:150px;" class="card-footer">
                        <button @click="makeReservation(tempTermin, index)">Rezervisi</button>
                        <div style="margin:10px;" v-if="messages[index].successMessage" class="alert alert-success" v-html="messages[index].successMessage">

                        </div>
                        <div style="margin:10px;" v-if="messages[index].errorMessage" class="alert alert-danger" v-html="messages[index].errorMessage">

                        </div>
                    </div>
                </div>
                
            </div>
        </div>
        <div v-show="listaTermina.length == 0">
            <p>Trenutno nema slobodnih termina...</p>
        </div>
    </div>
</template>


<script>

import dataService from '../services/dataService'
import moment from 'moment'

export default {
    components:{
    },
    computed:{
        sortEntities(){
            // let tempLista = this.listaTermina.filter(this.filterByName);
            let tempLista2 = this.sortiraj(this.listaTermina);
            return tempLista2;
        },
    },
    created(){
        let parsToken = this.parseJwt(localStorage.getItem('token'));
        console.log('isparsirani token: ' + JSON.stringify(parsToken))
        localStorage.setItem('parsToken', JSON.stringify(parsToken));
        try{
            let tempUsername = localStorage.getItem('parsToken').split(",")[0].split(':')[1];
            tempUsername = tempUsername.slice(1, tempUsername.length - 1).trim();
            let tempRole = localStorage.getItem('parsToken').split(",")[1].split(":")[1];
            tempRole = tempRole.trim();
            let tempId = localStorage.getItem('parsToken').split(",")[3].split(':')[1];
            tempId = tempId.trim();

            console.log("IZVUCENI ID JE: " + tempId)
            this.userId = tempId;

            this.getTermine();
        }catch(error){

        }
        
    },
    data(){
        return{
            currentSort : 'datum',
            currentSortDir : 'asc',

            userId:-1,
            listaTermina: [],
            messages:[],
        }
    },
    methods:{
        sortiraj(tmpLista){
            let tempList = [];
            if(this.currentSort == 'datum'){
                if(this.currentSortDir == 'asc'){
                    tempList = tmpLista.sort((a, b) => (a.pocetakTermina > b.pocetakTermina) ? 1 : -1);
                }
                else{
                    tempList = tmpLista.sort((a, b) => (a.pocetakTermina < b.pocetakTermina) ? 1 : -1)
                }
            }
            if(this.currentSort == 'vreme'){
                if(this.currentSortDir == 'asc'){
                    // let tempDatum = new Date();
                    // tempDatum.get
                    
                    tempList = tmpLista.sort((a, b) => {
                        // return (new Date(a.pocetakTermina).getHours > (new Date(b.pocetakTermina)).getHours() ? 1 : -1)
                        return (new Date(a.pocetakTermina).getHours > (new Date(b.pocetakTermina)).getHours() ? 1 : ((new Date(a.pocetakTermina).getHours()) == (new Date(b.pocetakTermina)).getHours() ? (new Date(a.pocetakTermina).getMinutes() > (new Date(b.pocetakTermina)).getMinutes() ? 1 : -1) : -1));
                        // if((new Date(a.pocetakTermina).getHours() == (new Date(b.pocetakTermina)).getHours() ? 1 : -1)){
                        //     console.log("SATI JEDNAKI");
                        //     (new Date(a.pocetakTermina).getMinutes() > (new Date(b.pocetakTermina)).getMinutes() ? 1 : -1)
                        // }
                    });
                }
                else{
                    // tempList =  tmpLista.sort((a, b) => (a.pocetakTermina.getHourOfDay() < b.pocetakTermina.getHourOfDay()) ? 1 : -1)
                    tempList = tmpLista.sort((a, b) => {
                        return (new Date(a.pocetakTermina).getHours() < (new Date(b.pocetakTermina)).getHours() ? 1 : -1)
                    });
                
                }
            }
            return tempList;
        },
        tempSort(s){
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            }
            this.currentSort = s;
        },
        makeReservation(termin, index){
            try{
                dataService.terminReserve(this.userId, termin.id).then(response => {
                    console.log("stigao odgovor");
                    if(response.status == 200){
                        this.messages[index].successMessage = '<h4>Uspesna rezervacija.</h4>';
                        setTimeout(() => this.messages[index].successMessage='', 3000);
                        setTimeout(() => this.getTermine2(), 3050);
                    }else if(response.status == 226){
                        this.messages[index].errorMessage = '<h4>Vec ste rezervisali.</h4>';
                        setTimeout(() => this.messages[index].errorMessage='', 3000);
                        setTimeout(() => this.getTermine2(), 3050);
                    }else if(response.status == 208){
                        this.messages[index].errorMessage = '<h4>Niste popunili obavezni upitnik.</h4>';
                        setTimeout(() => this.messages[index].errorMessage='', 3000);
                        setTimeout(() => this.getTermine2(), 3050);
                    }else if(response.status == 207){
                        this.messages[index].errorMessage = '<h5>Vec ste donirali krv u preth. 6 meseci.</h5>';
                        setTimeout(() => this.messages[index].errorMessage='', 3000);
                        setTimeout(() => this.getTermine2(), 3050);
                    }else{
                        this.messages[index].errorMessage = '<h4>Neuspesna rezervacija.</h4>';
                        setTimeout(() => this.messages[index].errorMessage='', 3000);
                        setTimeout(() => this.getTermine2(), 3050);
                    }
                });
            }catch(error){
                console.log("doslo je do greske: " + error);
                this.messages[index].errorMessage = "<h4>Neuspesna rezervacija.</h4>"
                setTimeout(() => this.messages[index].errorMessage='', 3000);
            }
             
                
        },
        datumFormat(datum){
            // console.log("DATUM PRE FORMATIRANJA: " + datum);
            let tempDatum = moment(datum).format("YYYY-MM-DD hh:mm")
            // console.log("DATUM POSLE FORMATIRANJA: " + tempDatum);
            return tempDatum;
        },
        getTermine2(){
            try{
                dataService.getTermineMedCentar(this.$route.params.id).then(response => {
                    console.log("Dobavljena lista termina:" + JSON.stringify(response.data[0]));
                    this.listaTermina = response.data;
                    this.messages = [];
                    for(let i = 0; i < this.listaTermina.length; i++){
                    let tempObjekat = new Object();
                    tempObjekat.successMessage = '';
                    tempObjekat.errorMessage = '';
                    this.messages.push(tempObjekat);
                }
                });
            }catch(error){

            }
            
        },
        getTermine(){
            try{
                dataService.getTermineMedCentar(this.$route.params.id).then(response => {
                    console.log("Dobavljena lista termina:" + JSON.stringify(response.data));
                    this.listaTermina = response.data;
                    for(let i = 0; i < this.listaTermina.length; i++){
                        let tempObjekat = new Object();
                        tempObjekat.successMessage = '';
                        tempObjekat.errorMessage = '';
                        this.messages.push(tempObjekat);
                    }
                });
            }catch(error){

            }
            
        },
    },  
    
}
</script>




<style scoped>
.sort-button{
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
.sort-button:hover{
    background-color: #0275d8;
    color: white;
}
</style>