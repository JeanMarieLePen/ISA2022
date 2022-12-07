<template>
    <div>
        <h1>Pregled zakazanih termina</h1>
        <div v-if="listaTermina.length > 0" class="row">
            <div class="col-md-3" style="width:20%; margin:20px; height:300px;" v-for="(t, index) in listaTermina" v-bind:key="index">
                <div style="text-align:center" class="card-title">
                    <h2>Termin: {{t.id}}</h2>
                </div>
                <div class="card-body">
                    <p class="card-text">Pocetak: {{datumFormat(t.pocetakTermina)}}</p>
                    <p class="card-text">Kraj: {{datumFormat(t.krajTermina)}}</p>
                    <p class="card-text">Broj mesta: {{t.brojMesta}}</p>
                    <p class="card-text">Broj slobodnih mesta: {{t.brSlobodnihMesta}}</p>
                    <p class="card-text">Preostalo vreme: {{calcTime(t)}}</p>
                </div>
                <div class="card-footer" style="height:150px;">
                    <button @click="cancelTermin(t, index)">Otkazi</button>
                    <div style="margin:10px;" v-if="messages[index].successMessage" v-html="messages[index].successMessage" class="alert alert-success">
                    
                    </div>
                     <div style="margin:10px;" v-if="messages[index].errorMessage" v-html="messages[index].errorMessage" class="alert alert-danger">

                     </div>
                </div>
            </div>
        </div>
        <div v-if="listaTermina.length==0">
            Trenutno nemate zakazanih termina...
        </div>
    </div>
</template>


<script>

import dataService from '../services/dataService'
import moment from 'moment'

export default {
    data(){
        return{
            listaTermina:[],
            userId:-1,
            messages:[],
        }
    },
    methods:{
        datumFormat(datum){
            return moment(datum).format("YYYY-mm-ddd hh:mm")
        },
        calcTime(termin){
            console.log("Pocetak: " + termin.krajTermina)
            const date1 = new Date(termin.krajTermina);
            const date2 = new Date();
            const diffTime = date2.getTime() - date1.getTime();
            const diffDays = Math.floor(Math.abs(diffTime / (1000 * 3600 * 24)));
            const diffHours = Math.floor(Math.abs(diffTime / (1000 * 3600)));
            console.log("diffTime: " + diffTime)
            console.log("diffDays: " + diffDays)
            console.log("diffHours: " + diffHours)
            return diffDays + ' dana(' + diffHours + ' sati)';
        },
        cancelTermin(termin, index){
            try{
                dataService.cancelTermin(termin.id, this.userId).then(response => {
                    console.log("Otkazan termin");
                    if(response.status === 200){
                        this.messages[index].successMessage = '<h4>Uspesno otkazan termin.</h4>';
                        setTimeout(() => {
                            this.messages[index].successMessage = '';
                        }, 4000);
                        setTimeout(() => {
                            this.getTermine();
                        }, 4050);
                    }else{
                        this.messages[index].errorMessage = '<h4>Doslo je do greske.</h4>';
                        setTimeout(() => {
                            this.messages[index].errorMessage = '';
                        }, 4000);
                        setTimeout(() => {
                            this.getTermine();
                        }, 4050);
                    }
                });
            }catch(error){

            }
        },
        getTermine(){
            dataService.getTermineByKorisnik(this.userId).then(response => {
                console.log("Dobavljena lista termina");
                this.listaTermina = response.data;
                this.messages = [];
                for(let i = 0; i < this.listaTermina.length; i++){
                    let tempObjekat = new Object();
                    tempObjekat.successMessage = '';
                    tempObjekat.errorMessage = '';
                    this.messages.push(tempObjekat);
                }
            });
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
    }
}
</script>




<style scoped>

</style>