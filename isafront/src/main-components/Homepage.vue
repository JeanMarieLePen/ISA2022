<template>
    <div id="home" style="margin-bottom:100px; width:60%;">
        <div class="container">
            <h1>
                <h1 style="margin-top:10px;color:#35424a;">Welcome <span id='titleEffect'>{{user.username}}</span>!</h1>
                <hr style='background:#35424a;height:1px;'>
            </h1>
        </div>


        <div v-show="role === 'ADMINISTRATOR'">
            <div class="row">
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/search" class="nav-link" exact>
                            <img src="../assets/search-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pretraga bolnica</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Detaljna pretraga medicinskih centara i bolnica za donaciju krvi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/profile" class="nav-link" exact>
                            <img src="../assets/profile.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled profila</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o vasem profilu...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/upitnik" class="nav-link" exact>
                            <img src="../assets/form.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Upitnik</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Popunjavanje detalja upitnika o korisniku...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/hospitalsOverview" class="nav-link" exact>
                            <img src="../assets/hospitals.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled centara</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o svim centrima za donaciju krvi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

        <div v-show="role === 'REGISTROVANI_KORISNIK'">
            <div class="row">
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/search" class="nav-link" exact>
                            <img src="../assets/search-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pretraga ponude</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pretrazite detaljno ponudu svih proizvoda u ponudi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/profile" class="nav-link" exact>
                            <img src="../assets/profile.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled profila</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o vasem profilu...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/upitnik" class="nav-link" exact>
                            <img src="../assets/form.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Upitnik</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Popunjavanje detalja upitnika o korisniku...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/hospitalsOverview" class="nav-link" exact>
                            <img src="../assets/hospitals.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled centara</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o svim centrima za donaciju krvi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

        <div v-show="role === 'MEDICINSKO_OSOBLJE'">
            <div class="row">
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/search" class="nav-link" exact>
                            <img src="../assets/search-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pretraga ponude</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pretrazite detaljno ponudu svih proizvoda u ponudi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/profile" class="nav-link" exact>
                            <img src="../assets/profile.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled profila</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o vasem profilu...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/upitnik" class="nav-link" exact>
                            <img src="../assets/form.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Upitnik</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Popunjavanje detalja upitnika o korisniku...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/hospitalsOverview" class="nav-link" exact>
                            <img src="../assets/hospitals.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled centara</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o svim centrima za donaciju krvi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

        <div v-show="role === 'ADMINISTRATOR_USTANOVE'">
            <div class="row">
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/search" class="nav-link" exact>
                            <img src="../assets/search-icon.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pretraga ponude</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pretrazite detaljno ponudu svih proizvoda u ponudi...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-0 shadow" style="height:100%">
                        <router-link style="text-decoration: none; c" to="/profile" class="nav-link" exact>
                            <img src="../assets/profile.png" class="card-img-top" alt="...">
                            <div class="card-body text-center">
                                <h5 class="card-title mb-0">Pregled profila</h5>
                                <div class="card-text text-black-50" style="height:100px;">
                                    Pregledajte podatke o vasem profilu...
                                </div>
                            </div>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default{
    data(){
        return{
            user:{
                username:'',
            },
            role:'',
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
            this.user.username = tempUsername;
            console.log('temp username je : ' + tempUsername)
            console.log('temp role je : ' + tempRole);


            if(tempRole = 'ADMINISTRATOR'){;
                this.role = 'ADMINISTRATOR';
            }else if(tempRole = 'MEDICINSKO_OSOBLJE'){
                this.role = 'MEDICINSKO_OSOBLJE';
            }else if(tempRole = 'ADMINISTRATOR_USTANOVE'){
                this.role = 'ADMINISTRATOR_USTANOVE';
            }else if(tempRole = 'REGISTROVANI_KORISNIK'){
                this.role = 'REGISTROVANI_KORISNIK';
            }else{
                console.log('error');
                this.$router.push(`/login`); 
            }
            console.log('ULOGA JE: ' + this.role)
        }catch(error){
            console.log('greska: ' + error.message);
            localStorage.setItem('token', null);
            localStorage.setItem('parsToken', null);
        }
    }
}
</script>


<style scoped>

#titleEffect{
  color: red;
  font-weight: bold;
}
#home{
    text-align: center;
    width: 100%;
    display: inline-block;
}
</style>