<template>
    <div style="width:100%;">
            <div style="margin-left:20px;margin-bottom:30px;">
                <h1>Zakazivanje novog termina</h1>
            </div>
            <table style="margin-left:10px;" id="table_overview">
                <tr>
                    <td>
                        Datum:
                    </td>
                    <td colspan="1">
                        <datepicker v-model="odabraniDatum" type="format"  value-type="YYYY-MM-DD"></datepicker>
                    </td>
                </tr>
                <tr>
                    <td>Termin</td>
                    <td>
                        <datepicker v-model="pocetakTime" type="time" format="HH:mm:ss" value-type="format"></datepicker>-<datepicker v-model="krajTime" type="time" format="HH:mm:ss" value-type="format"></datepicker>
                    </td>
                </tr>
                <tr> 
                    <td>
                        <button @click="filtriraj()">Filtriraj</button>
                    </td>
                </tr>
            </table>
            <div v-show="listaCentara">
            <searchresults2 v-bind:listaCentara="listaCentara" v-bind:terminTemp="terminTemp"  v-bind:userId="userId"></searchresults2>
        </div>

    </div>
</template>
<script>
import VueDatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css'
import dataService from '../services/dataService'
import SearchResults2 from '../main-components/SearchResults2.vue'

export default{
        data(){
            return{
                messages:{
                    successResponse:''
                },
                odabraniDatum:'',
                pocetakTime:'',
                krajTime:'',
                listaCentara:[],
                terminTemp:{
                    pocetakTermina:'',
                    krajTermina:'',
                },
                userId:''

            }
        },
        created(){ console.log("PARAM : "+this.$route.params.id)
            dataService.getAllHospitals().then(response => {
            console.log("SVE USTANOVE: " + response.data.length);
            this.listaCentara = response.data;
        }).catch(error => {

        });
        },
        components:{
            datepicker:VueDatePicker,
            searchresults2:SearchResults2,
        },
        methods:{
           filtriraj(){
                this.terminTemp.pocetakTermina=this.odabraniDatum+"T"+this.pocetakTime; console.log(this.terminTemp.pocetakTermina);
                this.terminTemp.krajTermina=this.odabraniDatum+"T"+this.krajTime;       console.log(this.terminTemp.pocetakTermina);
                console.log(this.terminTemp);
                dataService.getSlobodne(this.terminTemp).then(response=>{this.listaCentara=response.data;}).catch(error => {});
                
           },
        },
        async mounted(){
        await dataService.getAllHospitals();
    },
        
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