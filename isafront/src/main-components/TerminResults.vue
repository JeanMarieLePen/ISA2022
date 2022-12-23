<template>
    <div style="width:100%">
        <table>
            <tr>
                <td>
                    <button class="filter-button" style="margin-left:10px" id="buttonSort" @click="tempSort('pocetak')">Pocetak 
                        <img v-if='currentSortDir == "asc" && currentSort== "pocetak"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "pocetak" ' src='../assets/down-arrow1.1.png'>
                    </button>
                </td>
                <td>
                    <button class="filter-button" id="buttonSort" @click="tempSort('kraj')">Kraj 
                        <img v-if='currentSortDir == "asc" && currentSort== "kraj"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "kraj" ' src='../assets/down-arrow1.1.png'>
                    </button>
                </td>
                <td>
                    <button class="filter-button" id="buttonSort"  @click="tempSort('ocenaEntiteta')">Id
                        <img v-if='currentSortDir == "asc" && currentSort== "id"' src='../assets/up-arrow1.1.png'>
                        <img v-if='currentSortDir == "desc" && currentSort== "id" ' src='../assets/down-arrow1.1.png'>
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
    </div>

</template>
<script>


export default{
    components:{
    },
    props:[
        'listaTermina'
    ],
    data(){
        return{
            // listaCentara:[],
            currentSort : 'pocetak',
            currentSortDir : 'asc',
            inputFilter : '',
        }
    },
    methods:{
        tempSort(s){
            if (s === this.currentSort) {
                this.currentSortDir = this.currentSortDir === 'asc' ? 'desc' : 'asc';
            }
            this.currentSort = s;
        },
       
        sortiraj(tmpLista){
            let tempList = [];
            if(this.currentSort == 'pocetak'){
                if(this.currentSortDir == 'asc'){
                    tempList = tmpLista.sort((a, b) => (a.pocetak > b.pocetak) ? 1 : -1);
                }
                else{
                    tempList = tmpLista.sort((a, b) => (a.pocetak < b.pocetak) ? 1 : -1)
                }
            }
            if(this.currentSort == 'kraj'){
                if(this.currentSortDir == 'asc'){
                    tempList = tmpLista.sort((a, b) => (a.kraj > b.kraj) ? 1 : -1);
                }
                else{
                    tempList =  tmpLista.sort((a, b) => (a.kraj < b.kraj) ? 1 : -1)
                }
            }
            if(this.currentSort == 'id'){
                if(this.currentSortDir == 'asc'){
                    tempList = tmpLista.sort((a, b) => (a.id > b.id) ? 1 : -1);
                }
                else{
                    tempList = tmpLista.sort((a, b) => (a.id < b.id) ? 1 : -1);
                }
            }
            return tempList;
        }
    },
    created(){
        console.log("IMPORT")
    },
    computed:{
        sortEntities(){
           // let tempLista = this.listaCentara.filter(this.filterByName);
            let tempLista2 = this.sortiraj(this.listaTermina);
            return tempLista2;
        },
    }
    
}
</script>
<style scoped>
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
</style>