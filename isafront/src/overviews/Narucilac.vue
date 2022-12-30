<template>
    <div>
        <div v-if="narudzbine.length==0">
            <h2 style="font-size:40px;font-style:italic;margin:30px;">Trenutno nemate narudzbina</h2>
        </div>
        <div class="row">
            <table id="tbl_order" style="margin-left:30px;" class="col-md-3" v-for="(temp, index) in narudzbine" :key="index">
                <tr>
                    <td>
                        Narudzbina ID: 
                    </td>
                    <td colspan="2">{{temp.id}}</td>
                </tr>
                <tr>
                    <td>
                        Lokacija OD: 
                    </td>
                    <td>{{temp.lokacijaOd.lat}}</td>
                    <td>{{temp.lokacijaOd.lng}}</td>
                </tr>
                <tr>
                    <td>
                        Lokacija DO: 
                    </td>
                    <td>{{temp.lokacijaDo.lat}}</td>
                    <td>{{temp.lokacijaDo.lng}}</td>
                </tr>
                <tr>
                    <td>Datum/Vreme:</td>
                    <td>
                        {{formDate(temp.datumIVreme)}}
                    </td>
                    <td>
                        {{formVreme(temp.datumIVreme)}}
                    </td>
                </tr>
                <tr>
                    <td colspan="3" style="text-align:center;">
                        <button @click="delivery(temp.id)" style="margin-top:5px;margin-bottom:5px;">Kretanje</button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</template>



<script>
import dataService from '../services/dataService'
import moment from 'moment'
export default {
    data(){
        return{
            userId:'',
            userRole:'',
            narudzbine:[],
        }
    },
    methods:{
        delivery(idPorudzbine){
            this.$router.push(`/path/${idPorudzbine}`);
        },
        formDate(datum){
            return moment(datum).format('YYYY-MM-DD');
        },
        formVreme(datum){
            return moment(datum).format('hh:mm');
        },
        getNarudzbineByWorkersId(){
            dataService.getNarudzbineByWorkersId(this.userId).then(response =>{
                console.log("DOBAVLJENE NARUDZBINE OVOG CENTRA: " + response.data.length);
                this.narudzbine = response.data;
            });      
        }
    },
    created(){
        if (localStorage.getItem("token").toString() !== "null") {
            let parsToken = this.parseJwt(localStorage.getItem("token"));
            console.log("isparsirani token: " + JSON.stringify(parsToken));
            localStorage.setItem("parsToken", JSON.stringify(parsToken));

            try {
                let tempUsername = localStorage
                .getItem("parsToken")
                .split(",")[0]
                .split(":")[1];
                tempUsername = tempUsername.slice(1, tempUsername.length - 1).trim();
                let tempRole = localStorage
                .getItem("parsToken")
                .split(",")[1]
                .split(":")[1];
                tempRole = tempRole.trim();
                let tempId = localStorage
                .getItem("parsToken")
                .split(",")[3]
                .split(":")[1];
                tempId = tempId.trim();
                console.log("ULOGA JE: " + tempRole)
                console.log("IZVUCENI ID JE: " + tempId);
                this.userId = tempId;
                this.userRole = tempRole;

                this.getNarudzbineByWorkersId();
            } catch (error) {
                console.log("Greska: " + error.message);
            }
        }
    }   
}
</script>



<style scoped>
#tbl_order>tr:nth-child(odd){
    background-color: cornflowerblue;
}
#tbl_order td:first-child {
  width: 40%;
}
#tbl_order{
  font-size: 20px;
  font-style: oblique;
  font-weight: 600;
}

</style>