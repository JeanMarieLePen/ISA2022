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
                    <datepicker></datepicker>
                    <label>Do datuma:</label>
                    <datepicker></datepicker>
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
        <div>
            <button class="filter-button">NAZIV</button>
            <button class="filter-button">OCENA</button>
            <button class="filter-button">GRAD</button>
        </div>
        <div style="margin-top:100px;margin-left:30px;" v-if="listaCentara.length == 0">
            <p style="font-family:Verdana;font-size:30px;font-style: italic;">LISTA CENTARA JE PRAZNA</p>
        </div>
    </div>    
</template>




<script>

import VueDatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css';
export default{
    
    data(){
        return{
            searchEntity:{

            },
            listaCentara:[],
        }
    },
    created(){
        
    },
    components:{
        datepicker:VueDatePicker,
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