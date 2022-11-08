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
}

export default new DataService();