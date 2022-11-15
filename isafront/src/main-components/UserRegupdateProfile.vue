<template>
   <div id="reg-profile-update" style="margin-bottom:200px;">
        <div id='main'>
			<div class="container" id='page-title'>
			    <h1 style="margin-top:10px;color:#1E90FF;">Izmena profila <span id='titleEffect'>{{this.profile.tipKorisnika}}</span></h1>
			    <hr style='background:#1E90FF;height:1px;'>
			</div>
            <div class="container">
                <fieldset class="form-group">
					<label><font color="#1E90FF">Email</font></label>
					<input type="text" class="form-control" v-model="profile.email" disabled />
				</fieldset>
                <div v-if='messages.errorUsername' id='testError' class="alert alert-danger" v-html="messages.errorUsername"></div>
                <fieldset class="form-group">
                    <label><font color="#1E90FF">Korisnicko ime</font></label>
                    <input type="text" class="form-control" v-model="profile.korIme"  disabled/>
                </fieldset>
                <div v-if='messages.errorFirstName' id='testError' class="alert alert-danger" v-html="messages.errorFirstName"></div>
                <fieldset class="form-group">
                    <label><font color="#1E90FF">Ime</font></label>
                    <input type="text" class="form-control" v-model="profile.ime" />
                </fieldset>
                <div v-if='messages.errorLastName' class="alert alert-danger" v-html="messages.errorLastName"></div>
                <fieldset class="form-group">
                    <label><font color="#1E90FF">Prezime</font></label>
                    <input type="text" class="form-control" v-model="profile.prezime" />
                </fieldset>
                <div style="margin-top:20px" v-if='messages.errorAdresa' class="alert alert-danger" v-html="messages.errorAdresa"></div>
                <fieldset class="form-group">
                    <label><font color="#1E90FF">Adresa</font></label>
                    <input type="text" class="form-control" v-model="profile.adresa" />
                </fieldset>
                <fieldset class="form-group">
                    <label><font color="#1E90FF">Datum Rodjenja</font></label>
                    <input type="text" class="form-control" v-model="profile.datumRodjenja" disabled/>
                </fieldset>
            </div>
                <hr>
                <h4><font color="#1E90FF">Promena lozinke</font></h4>
                    <hr>
                    <div v-if='messages.errorNotEqualOldPassword' class="alert alert-danger" v-html="messages.errorNotEqualOldPassword"></div>
                    <div v-if='messages.errorOldPass' class="alert alert-danger" v-html="messages.errorOldPass"></div>
                    <fieldset class="form-group">
					    <label><font color="#1E90FF">Stara lozinka</font></label>
					    <input type="password" class="form-control" v-model="changedPassword.oldPassword" placeholder="Unesite staru loznku..." />
				    </fieldset>
                    <div v-if='messages.errorNewPass' class="alert alert-danger" v-html="messages.errorNewPass"></div>
                    <fieldset class="form-group">
					    <label><font color="#1E90FF">Nova lozinka</font></label>
					    <input type="password" class="form-control" v-model="changedPassword.newPassword" placeholder="Unesite novu loznku..." />
				    </fieldset>
                    <div v-if='messages.errorNotEqualNewPassword' class="alert alert-danger" v-html="messages.errorNotEqualNewPassword"></div>
                    <div v-if='messages.errorRepNewPass' class="alert alert-danger" v-html="messages.errorRepNewPass"></div>
                    <fieldset class="form-group">
					    <label><font color="#1E90FF">Ponovite novu lozinku</font></label>
					    <input type="password" class="form-control" v-model="changedPassword.newPasswordRepeat" placeholder="Ponovite novu loznku..."  />
				    </fieldset>
                    <button :disabled='btnEnabled' type="button" class="btn btn-lg btn-success" v-on:click='updateProfile()'> Potvrdi </button>
                    
                    <div v-if='messages.errorResponse' class="alert alert-danger" v-html="messages.errorResponse"></div>
                    <div v-if='messages.successResponse' class="alert alert-success" v-html="messages.successResponse"></div>
                
        </div>
   </div>
</template>


<script>
    import { METHODS } from 'http';
import dataService from '../services/dataService';
import DataService from '../services/dataService'
import VueDatePicker from 'vue2-datepicker'

    export default{
        data(){
            return{
                btnEnabled:false,
                profile: {
                    kategorija:{},
                },
                ownerId : '',
			    changedPassword: {
                    oldPassword: '',
                    newPassword: '',
                    newPasswordRepeat: '',
                },
                messages:{
                    errorUsername:'',
                    errorFirstName: '',
                    errorLastName: '',
                    errorAdresa:'',
                    errorOldPass: '',
                    errorNewPass: '',
                    errorRepNewPass: '',
                    errorNotEqualOldPassword: '',
                    errorNotEqualNewPassword: '',
                    errorResponse: '',
                    successResponse: '',
                },
            }
        },
        created(){
            if(JSON.parse(localStorage.getItem('token')) == null){
           this.$router.push(`/login`);
            }
            else{
            //ukoliko jwt postoji onda ga parsiramo
            let parsToken = this.parseJwt(localStorage.getItem('token'));
            console.log('isparsirani token: ' + JSON.stringify(parsToken))
            localStorage.setItem('parsToken', JSON.stringify(parsToken));
            try{
                let tempUsername = localStorage.getItem('parsToken').split(",")[0].split(':')[1];
                tempUsername = tempUsername.slice(1, tempUsername.length - 1).trim();
                this.ownerUsername = tempUsername;
                let tempRole = localStorage.getItem('parsToken').split(",")[1].split(":")[1];
                tempRole = tempRole.slice(1, tempRole.length - 1).trim();
                let tempId = localStorage.getItem('parsToken').split(',')[3].split(':')[1];
                this.ownerId = tempId;
                try{
                    DataService.getUser(this.ownerId).then(response => {
                        console.log('dobavljen profil');
                        this.profile = response.data;
                    });
                }catch(error){
                    // this.profile = this.profileTemp;
                }

            }catch(error){
                console.log('greska: ' + error.message);
            }
            }    
        },
        methods:{
            parseJwt:function(token) {
            var base64Url = token.split('.')[1];
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));

            return JSON.parse(jsonPayload);
            },
            getAllPermissions:function(){
            DataService.getAllPermissions().then(response => {
                this.permissions = response.data;
                if(this.permissions.status != "aktivan"){
                    this.btnEnabled = true
                }
                console.log(JSON.stringify(this.permissions));
            });
            },
            getUserProfileData:function(){
            DataService.getUser().then(response => {
                this.profile = response.data;
                console.log("user: " + JSON.stringify(this.profile));
            })
            },
            updateProfile(){
                 // First name i last name se u paru gledaju da li su popunjeni
                // Ako su oba prazna u isto vreme ce za oba izbaciti error
                if (this.profile.korisnickoIme == '') {
                    this.messages.errorUsername = `<h4>Polje korisnicko ime ne moze biti prazno!</h4>`;
                    setTimeout(() => this.messages.errorUsername = '', 5000);
                }
                else if (this.profile.ime == '' && this.profile.prezime != '') {
                    his.messages.errorFirstName = `<h4>Polje ime ne moze biti prazno!</h4>`;
                    setTimeout(() => this.messages.errorFirstName = '', 5000);
                }
                else if (this.profile.ime != '' && this.profile.prezime == '') {
                    this.messages.errorLastName = `<h4>Polje prezime ne moze biti prazno!</h4>`;
                    setTimeout(() => this.messages.errorLastName = '', 5000);
                }
                else if (this.profile.ime == '' && this.profile.prezime == '') {
                    this.messages.errorFirstName = `<h4>Polje ime ne moze biti prazno!</h4>`;
                    this.messages.errorLastName = `<h4>Polje prezime ne moze biti prazno!</h4>`;
                    setTimeout(() => this.messages.errorFirstName = '', 5000);
                    setTimeout(() => this.messages.errorLastName = '', 5000);
                }
                else if (this.profile.adresa == '') {
                    this.messages.errorAdresa = `<h4> Polje adresa moze biti prazno!</h4>`;
                    setTimeout(() => this.errorAdresa = '', 5000);
                }

                 //Password check
                //Ako je unet old password, a nije uneto jedno od polja newPassword ili newPassword reapeat unosi se error
                else if (this.changedPassword.oldPassword !== '' && this.changedPassword.newPassword !== '' && this.changedPassword.newPasswordRepeat === '') {
                    this.messages.errorRepNewPass = `<h4>Morate ponoviti Vasu novu sifru!</h4>`;
                    setTimeout(() => this.messages.errorRepNewPass = '', 55000);
                }
                //Ako je unet old password, a nije uneto jedno od polja newPassword ili newPassword reapeat unosi se error
                else if (this.changedPassword.oldPassword !== '' && this.changedPassword.newPassword === '' && this.changedPassword.newPasswordRepeat !== '') {
                    this.messages.errorNewPass = `<h4>Morate uneti Vasu novu sifru!</h4>`;
                    setTimeout(() => this.messages.errorNewPass = '', 5000);
                }
                //Ako je unet old password, a nije uneto jedno od polja newPassword ili newPassword reapeat unosi se error
                else if (this.changedPassword.oldPassword !== '' && this.changedPassword.newPassword === '' && this.changedPassword.newPasswordRepeat === '') {
                    this.messages.errorNewPass = `<h4>Morate uneti Vasu novu sifru!</h4>`;
                    this.messages.errorRepNewPass = `<h4>Morate ponoviti Vasu novu sifru!</h4>`;
                    setTimeout(() => this.messages.errorNewPass = '', 5000);
                    setTimeout(() => this.messages.errorRepNewPass = '', 5000);
                }
                //Ako nije unet old password, a jesu novi ispisuje se error
                else if (this.changedPassword.oldPassword === '' && this.changedPassword.newPassword !== '' && this.changedPassword.newPasswordRepeat !== '') {
                    this.messages.errorOldPass = `<h4>Morate prvo uneti Vasu staru sifru!</h4>`;
                    setTimeout(() => this.messages.errorOldPass = '', 5000);
                }
                 //Ako su uneta sva 3 passworda, nema errora za prazna polja, ali se proverava validnost samog unosa
                else if (this.changedPassword.oldPassword !== '' && this.changedPassword.newPassword !== '' && this.changedPassword.newPasswordRepeat !== '') {
                    if (this.changedPassword.newPassword !== this.changedPassword.newPasswordRepeat) {
                        this.messages.errorNotEqualNewPassword = `<h4>Vase nove sifre se ne poklapaju! Molimo Vas pokusajte ponovo...</h4>`;
                        setTimeout(() => this.messages.errorNotEqualNewPassword = '', 3000);
                    }
                    else {
                        //ako je stara sifra dobro uneta, a nove se poklapaju,
                        //stara sifra se menja novom.
                        // this.profile.staraSifra = this.changedPassword.oldPassword;
                        // this.profile.novaSifra = this.changedPassword.newPassword;
                        let tempObjekat = {
                            staraSifra: this.changedPassword.oldPassword,
                            novaSifra: this.changedPassword.newPassword,
                        }
                        if(tempObjekat.novaSifra === '' || tempObjekat.novaSifra === null){
                            tempObjekat.novaSifra='';
                        }
                        if(tempObjekat.staraSifra === '' || tempObjekat.staraSifra === null){
                            tempObjekat.staraSifra='';
                        }
                        console.log("Korisnik: " + JSON.stringify(this.profile));
                        let objekat = {
                            korDTO: this.profile,
                            novaSifraDTO: tempObjekat
                        }
                        console.log("Objekat koji se salje na bek: " + JSON.stringify(objekat));
                        DataService.updateUserProfile(objekat).then(Response => {
                            this.messages.successResponse = `<h4>Vas profil je uspesno izmenjen!</h4>`;
                            setTimeout(() => this.messages.successResponse = '', 5000);
                            this.profile = Response.data;
                            setTimeout(() => { this.$router.push(`/home`)}, 5050);
                        })
                        .catch(error=>{
                            // && error.response.data.message === "Wrong password!"
                            if(error.response.status === 409 ){
                                this.messages.errorResponse = `<h4>Vaša stara sifra je netačna! Molimo Vas pokušajte ponovo...</h4>`;
                                setTimeout(()=>this.messages.errorResponse='', 5000);
                            }
                            else if (error.response.status === 500 || error.response.status === 404) {
                                this.messages.errorResponse = `<h4>Imali smo nekih problema na serveru,  molimo Vas pokušajte ponovo kasnije!</h4>`;
                                setTimeout(() => this.messages.errorResponse = '', 5000);
                            }
                        });
                    }
                }
                else{
                    let tempObjekat = {
                            staraSifra: '',
                            novaSifra: '',
                        }
                    let objekat = {
                            korDTO: this.profile,
                            novaSifraDTO: tempObjekat
                    }
                    DataService.updateUserProfile(objekat).then(response => {
                        his.messages.successResponse = `<h4>Vas profil je uspesno izmenjen!</h4>`;
                        setTimeout(() => this.messages.successResponse = '', 5000);
                        console.log(response.data)
                        this.profile = response.data;
                    })
                    .catch(error => {
                        if (error.response.status === 500 || error.response.status === 404) {
                            this.messages.errorResponse = `<h4>Imali smo nekih problema na serveru, molimo Vas pokušajte kasnije!</h4>`;
                            setTimeout(() => this.messages.errorResponse = '', 5000);
                        }
                    });
                }
            },
        },
        components:{
            datepicker:VueDatePicker,
        }
        
    }
    
</script>

<style scoped>

#titleEffect{
  color:#1E90FF;
  font-weight: bold;
}

#admin-profile-update .one-forth{
  width:24%;
  padding:10px;
  margin-bottom:15px
}

#admin-profile-update label{
    color:#35424a;
    display: block;
    margin: 20px 0 10px;
    font-size: 20px;
    font-weight: bold;
}

.header5{
    color:#1E90FF;
    font-weight: bold;
}

</style>