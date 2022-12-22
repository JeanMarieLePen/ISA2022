import axios from "axios"


const API_URL = "http://localhost:8082";

(function() {
    let token = localStorage.getItem('token');
    axios.defaults.headers.common['Access-Control-Allow-Headers'] = 'Authorization';
    if(token){
        axios.defaults.headers.common['Authorization'] = 'Bearer ' + token.slice(1, token.length-1);
        console.log("TOKEN SMESTEN U AUTHORIZATION");
    }else{
        axios.defaults.headers.common['Authorization'] = null;
        
    }
})();


class DataService{

    login(User){
        return axios.post(`${API_URL}/authenticate`, User);
    }
    register(User){
        return axios.post(`${API_URL}/registerUser`, User);
    }
    registerOsoblje(User){
        return axios.post(`${API_URL}/registerStuff`, User);
    }

    getAllHospitals(){
        return axios.get(`${API_URL}/medcentar/getAll`);
    }
    submitUpitnik(upitnik, id){
        return axios.post(`${API_URL}/regkorisnik/submitUpitnik/${id}`, upitnik)
    }
    checkUpitnik(id){
        return axios.get(`${API_URL}/regkorisnik/checkUpitnik/${id}`);
    }
    editUpitnik(upitnik, id){
        return axios.put(`${API_URL}/regkorisnik/editUpitnik/${id}`, upitnik);
    }
    getCentarQuery(searchEntity){
        return axios.post(`${API_URL}/medcentar/pretraga`, searchEntity);
    }
    getUser(id){
        return axios.get(`${API_URL}/regkorisnik/${id}`);
    }
    updateUserProfile(objekat){
        return axios.put(`${API_URL}/regkorisnik/`, objekat);
    }
    getMedCentarById(id){
        return axios.get(`${API_URL}/medcentar/getById/${id}`);
    }
    getTermineMedCentar(id){
        return axios.get(`${API_URL}/medcentar/getTermineById/${id}`);
    }
    terminReserve(userId, terminId){
        return axios.put(`${API_URL}/medcentar/terminReserve/${userId}/${terminId}`);
    }
    getTermineByKorisnik(userId){
        return axios.get(`${API_URL}/regkorisnik/getTermini/${userId}`);
    }
    cancelTermin(id, userId){
        return axios.put(`${API_URL}/medcentar/cancelTermin/${id}/${userId}`);
    }
    getMedCentarByOsoblje(id){
        return axios.get(`${API_URL}/medcentar/getByOsoblje/${id}`);
    }
    updateMedCentarTermin(id,medCentar){
        return axios.put(`${API_URL}/medcentar/${id}/updateTermin`,medCentar);
    }
    getSlobodne(tempTermin){
        return axios.put(`${API_URL}/medcentar/terminFilter`,tempTermin);
    }
    customRezervisi(body){
        return axios.post(`${API_URL}/medcentar/customRezervacija`,body)
    }
}

export default new DataService();