<template>
    <div style="width:100%;">
        <div v-if="exists2" style="width:700px">
            <div style="flex-direction: column">
                <h2>Vec ste popunili upitnik!</h2>
                <button @click="showForm()" class="showButton">Pregled</button>
            </div>
        </div>
        <table v-show="!exists" id="tbl" style="width:30%;text-align:center;">
            <thead>
                <th>
                    <h2>UPITNIK O KORISNIKU</h2>
                </th>
            </thead>
            <tbody>
                <tr v-show="show1">
                    <td>
                        <input placeholder="Ime korisnika..." style="border-radius:2rem;" type="text" v-model="upitnik.ime">
                    </td>
                </tr>
                <tr v-show="show1">
                    <td>
                        <input placeholder="Ime roditelja..." type="text" v-model="upitnik.imeRoditelja">
                    </td>
                </tr>
                <tr v-show="show1">
                    <td>
                        <input placeholder="Prezime korisnika..." type="text" v-model="upitnik.prezime">
                    </td>
                </tr>
                <tr v-show="show1">
                    <td>
                        <input placeholder="JMBG korisnika..." type="text" v-model="upitnik.jmbg">
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <!-- <datepicker type="date" id="dtpckr" @pick="updateDatePicker" v-model="upitnik.datumRodjenja" style="margin-top:10px;margin-bottom:10px;">

                        </datepicker> -->

                        <datepicker v-model="upitnik.datumRodjenja"
                            format="YYYY-MM-DD"
                            style="width:100%;margin-top:10px;margin-bottom:10px;"
                            type="date"
                            placeholder="Select date"
                            id="dtpckr">
                        </datepicker>
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <select @change="test()" v-model="upitnik.pol" style="width:100%;margin-top:10px;margin-bottom:10px;">
                            <option value="musko">Musko</option>
                            <option value="zensko">Zensko</option>
                        </select>
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <input placeholder="Adresa stana korisnika..." type="text" v-model="upitnik.adresa">
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <input placeholder="Opstina stanovanja korisnika..." type="text" v-model="upitnik.opstina">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Mesto stanovanja korisnika..." type="text" v-model="upitnik.mesto">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Broj kucnog telefona korisnika..." type="text" v-model="upitnik.kucniTelefon">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Broj poslovnog telefona korisnika..." type="text" v-model="upitnik.poslovniTelefon">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Broj mobilnog telefona korisnika..." type="text" v-model="upitnik.mobTelefon">
                    </td>
                </tr>
                <tr v-show="show4">
                    <td>
                        <input placeholder="Naziv firme/obrazovne institucije u kojoj korisnik radi..." type="text" v-model="upitnik.firma_Ili_Skola">
                    </td>
                </tr>
                <tr v-show="show4">
                    <td>
                        <input placeholder="Zanimanje korisnika..." type="text" v-model="upitnik.zanimanje">
                    </td>
                </tr>
                <tr v-show="show4">
                    <td>
                        <input placeholder="Broj prethodnih donacija korisnika..." type="number" v-model="upitnik.brojPrethodnihDonacija">
                    </td>
                </tr>
                <tr v-show="show4" >
                    <td>
                        <button v-show="!exists" style=" height:40px; margin-top:10px; margin-bottom:10px;" class="btn btn-primary" @click="submitForm()">Submit</button>
                    </td>
                </tr>
            </tbody>
            <tfoot >
                <tr style="margin-top:100px;">
                    <td>
                        <button @click="show1Action()" class="btn btn-primary">
                            1
                        </button>
                        <button @click="show2Action()" class="btn btn-primary">
                            2
                        </button>
                        <button @click="show3Action()" class="btn btn-primary">
                            3
                        </button>
                        <button @click="show4Action()" class="btn btn-primary">
                            4
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div v-if="messages.successMessage" v-html="messages.successMessage" class="alert alert-success">

                        </div>
                        <div v-if="messages.errorMessage" v-html="messages.errorMessage" class="alert alert-danger">

                        </div>
                    </td>
                </tr>
            </tfoot>
        </table>

        <table v-show="exists&&prikaziFormu" id="tbl" style="width:30%;text-align:center;">
            <thead>
                <th>
                    <h2>UPITNIK O KORISNIKU</h2>
                </th>
            </thead>
            <tbody>
                <tr v-show="show1">
                    <td>
                        <input placeholder="Ime korisnika..." style="border-radius:2rem;" type="text" v-model="upitnik.ime">
                    </td>
                </tr>
                <tr v-show="show1">
                    <td>
                        <input placeholder="Ime roditelja..." type="text" v-model="upitnik.imeRoditelja">
                    </td>
                </tr>
                <tr v-show="show1">
                    <td>
                        <input placeholder="Prezime korisnika..." type="text" v-model="upitnik.prezime">
                    </td>
                </tr>
                <tr v-show="show1">
                    <td>
                        <input placeholder="JMBG korisnika..." type="text" v-model="upitnik.jmbg">
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <!-- <datepicker id="dtpckr" @pick="updateDatePicker" style="margin-top:10px;margin-bottom:10px;width:100%;">

                        </datepicker> -->
                        <datepicker  format="YYYY-MM-DD"
                            style="width:100%;margin-top:10px;margin-bottom:10px;"
                            type="date"
                            placeholder="Select date"
                            id="dtpckr">
                        </datepicker>
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <select @change="test()" v-model="upitnik.pol" style="width:100%;margin-top:10px;margin-bottom:10px;">
                            <option value="musko">Musko</option>
                            <option value="zensko">Zensko</option>
                        </select>
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <input placeholder="Adresa stana korisnika..." type="text" v-model="upitnik.adresa">
                    </td>
                </tr>
                <tr v-show="show2">
                    <td>
                        <input placeholder="Opstina stanovanja korisnika..." type="text" v-model="upitnik.opstina">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Mesto stanovanja korisnika..." type="text" v-model="upitnik.mesto">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Broj kucnog telefona korisnika..." type="text" v-model="upitnik.kucniTelefon">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Broj poslovnog telefona korisnika..." type="text" v-model="upitnik.poslovniTelefon">
                    </td>
                </tr>
                <tr v-show="show3">
                    <td>
                        <input placeholder="Broj mobilnog telefona korisnika..." type="text" v-model="upitnik.mobTelefon">
                    </td>
                </tr>
                <tr v-show="show4">
                    <td>
                        <input placeholder="Naziv firme/obrazovne institucije u kojoj korisnik radi..." type="text" v-model="upitnik.firma_Ili_Skola">
                    </td>
                </tr>
                <tr v-show="show4">
                    <td>
                        <input placeholder="Zanimanje korisnika..." type="text" v-model="upitnik.zanimanje">
                    </td>
                </tr>
                <tr v-show="show4">
                    <td>
                        <input placeholder="Broj prethodnih donacija korisnika..." type="number" v-model="upitnik.brojPrethodnihDonacija">
                    </td>
                </tr>
                <tr v-show="show4" >
                    <td>
                        <button style=" height:40px; margin-top:10px; margin-bottom:10px;" class="btn btn-warning" @click="editForm()">Edit</button>
                    </td>
                </tr>
            </tbody>
            <tfoot >
                <tr style="margin-top:100px;">
                    <td>
                        <button @click="show1Action()" class="btn btn-primary">
                            1
                        </button>
                        <button @click="show2Action()" class="btn btn-primary">
                            2
                        </button>
                        <button @click="show3Action()" class="btn btn-primary">
                            3
                        </button>
                        <button @click="show4Action()" class="btn btn-primary">
                            4
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div v-if="messages.successMessage" v-html="messages.successMessage" class="alert alert-success">

                        </div>
                        <div v-if="messages.errorMessage" v-html="messages.errorMessage" class="alert alert-danger">

                        </div>
                    </td>
                </tr>
            </tfoot>
        </table>
    
    </div>
</template>


<script>

import VueDatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css';
import moment from 'moment'
import dataService from '../services/dataService'

export default{
    components:{
        datepicker:VueDatePicker,
    },
    methods:{
        showForm(){
            this.exists2 = !this.exists2;
            this.prikaziFormu = true;
        },
        editForm(){
            dataService.editUpitnik(this.upitnik, this.userId).then(response => {
                console.log("Poslata forma: " + JSON.stringify(this.upitnik));
                if(response.status === 200){
                    this.messages.successMessage = '<h2>Uspesno ste popunili formular.</h2>'
                    setTimeout(() => {
                        this.messages.successMessage = '';
                    }, 4000);
                    setTimeout(() => {
                        this.$router.push(`/home`);
                    }, 4050);
                }else{
                    this.messages.errorMessage = '<h2>Doslo je do greske.</h2>';
                    setTimeout(() => {
                        this.messages.errorMessage = '';
                    }, 4000);
                }
            })
        },
        submitForm(){
            this.formatDatum();
            dataService.submitUpitnik(this.upitnik, this.userId).then(response => {
                console.log("Poslata forma: " + JSON.stringify(this.upitnik));
                if(response.status === 200){
                    this.messages.successMessage = '<h2>Uspesno ste popunili formular.</h2>'
                    setTimeout(() => {
                        this.messages.successMessage = '';
                    }, 4000);
                    setTimeout(() => {
                        this.$router.push(`/home`);
                    }, 4050);
                    
                }else{
                    this.messages.errorMessage = '<h2>Doslo je do greske.</h2>';
                    setTimeout(() => {
                        this.messages.errorMessage = '';
                    }, 4000);
                }
            });
        },
        show1Action(){
            this.show1 = true;
            this.show2 = false;
            this.show3 = false;
            this.show4 = false;
        },
        show2Action(){
            this.show1 = false;
            this.show2 = true;
            this.show3 = false;
            this.show4 = false;
        },
        show3Action(){
            this.show1 = false;
            this.show2 = false;
            this.show3 = true;
            this.show4 = false;
        },
        show4Action(){
            this.show1 = false;
            this.show2 = false;
            this.show3 = false;
            this.show4 = true;
        },
        formatDatum(){
            let tempDatum = moment(this.upitnik.datumRodjenja).format('YYYY-MM-DD');
            this.upitnik.datumRodjenja = tempDatum;
            console.log("Datum posle formatiranja: " + tempDatum);
        },
        updateDatePicker(val1){
            console.log("Odabran datum: " + val1)
            let tempDatum = moment(val1).format('YYYY-MM-DD');
            this.upitnik.datumRodjenja = tempDatum;
        },
        test(){
            console.log("Selektovan pol: " + this.upitnik.pol)
        },
        parseJwt:function(token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));

            return JSON.parse(jsonPayload);
        }
    },
    data(){
        return{
            prikaziFormu : false,
            messages:{
                successMessage:'',
                errorMessage:'',
            },
            userId:-1,
            show1:true,
            show2:false,
            show3:false,
            show4:false,
            // private String ime;
            // private String prezime;
            // private String imeRoditelja;
            // private String jmbg;
            // private String datumRodjenja;
            // private String pol;
            // private String adresa;
            // private String opstina;
            // private String mesto;
            // private String kucniTelefon;
            // private String poslovniTelefon;
            // private String mobTelefon;
            // private String firma_Ili_Skola;
            // private String zanimanje;
            // private int brojPrethodnihDonacija;
            upitnik:{
                // ime:'',
                // prezime:'',
                // imeRoditelja:'',
                // jmbg:'',
                // datumRodjenja:'',
                // pol:'', adresa:'', opstina:'', mesto:'', kucniTelefon:'', poslovniTelefon:'', mobTelefon:'', firma_Ili_Skola:'', zanimanje:'', brojPrethodnihDonacija:0
            },
            exists: false,
            exists2: false,
        }
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

            try{
                dataService.checkUpitnik(this.userId).then(response => {
                    // console.log("UPITNIK POSTOJI: " + response.data);
                    if(response.status === 200){
                        console.log("Upitik postoji");
                        this.exists = response.data;
                        this.exists2 = response.data;
                        this.upitnik = response.data;
                    }
                    
                });
            }catch(error){

            }
        }catch(error){
            console.log('greska: ' + error.message);
            localStorage.setItem('token', null);
            localStorage.setItem('parsToken', null);
        }
    }
}

</script>


<style scoped>

.showButton{
    background-color: white;
    color: #0275d8;
    border: 2px solid #0275d8;
    cursor: pointer;
    transition-duration: 0.4s;
}
.showButton:hover{
    background-color:#0275d8;
    color: white;
}

#dtpckr{
    display: flex;
    justify-content: left;
}


div{
  display: flex;
  justify-content: center;
}
#tbl{
    /* overflow:scroll; */
    margin-bottom: 100px;
}
#tbl td>input{
    border-radius: 2rem;
    margin-bottom: 10px;
    margin-top:10px;
    width:100%;
}
</style>