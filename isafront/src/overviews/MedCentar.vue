<template>
  <div style="width: 50%">
    <div style="margin-left: 10px">
      <h1>Podaci o centru</h1>

      <table style="margin-left: 10px" id="table_overview">
        <tr>
          <td>Naziv:</td>
          <td>
            <input type="text" readonly v-model="medCentar.naziv" />
          </td>
        </tr>
        <tr>
          <td>Adresa:</td>
          <td>
            <input type="text" readonly v-model="medCentar.adresa" />
          </td>
        </tr>
        <tr>
          <td>Radno vreme:</td>
          <td>
            <input type="text" readonly v-model="radnoVreme" />
          </td>
        </tr>
        <tr>
          <td>Ocena:</td>
          <td>
            <starrating read-only v-model="medCentar.ocena" />
          </td>
        </tr>
        <tr>
          <td>Krvna grupa:</td>
          <td>
            <select @change="selectGroup()" v-model="selectedGroup">
              <option value="1">A grupa</option>
              <option value="2">AB grupa</option>
              <option value="3">B grupa</option>
              <option value="4">Nulta grupa</option>
            </select>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <input
              style="width: 100%"
              placeholder="Stanje zaliha odabrane grupe izrazeno u litrima..."
              v-model="bloodReserve"
              type="text"
              readonly
            />
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <div v-if="medCentar.opis">
              <textarea
                style="width: 100%; height: 150px; overflow: auto"
                v-text="medCentar.opis"
                readonly
              >
              </textarea>
            </div>
            <div
              v-if="!medCentar.opis"
              v-text="'Kratak opis medicinskog centra...'"
            ></div>
          </td>
        </tr>
        <tr>
          <td colspan="2" style="text-align: center">
            <button class="termini-button" @click="showTermine()">
              Pregled termina
            </button>
            <button
              class="termini-button"
              @click="makeOrder()"
            >
              Narudzbina
            </button>
          </td>
        </tr>
        <tr v-if="showForm">
          <td colspan="2" style="text-align: center">
            <table id="table_overview2">
              <tbody>
                <tr>
                  <td colspan="2">NARUDZBENICA</td>
                </tr>
                <tr>
                  <td>A grupa:</td>
                  <td>
                    <input v-model="orderForm.kolicinaAGrupa"
                      placeholder="Unesite potrebnu zapreminu krvi A grupe u litrama..."
                      type="number"
                      min="0"
                    />
                  </td>
                </tr>
                <tr>
                  <td>B grupa:</td>
                  <td>
                    <input v-model="orderForm.kolicinaBGrupa"
                      placeholder="Unesite potrebnu zapreminu krvi B grupe u litrama..."
                      type="number"
                      min="0"
                    />
                  </td>
                </tr>
                <tr>
                  <td>AB grupa:</td>
                  <td>
                    <input v-model="orderForm.kolicinaABGrupa"
                      placeholder="Unesite potrebnu zapreminu krvi AB grupe u litrama..."
                      type="number"
                      min="0"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Nulta grupa:</td>
                  <td>
                    <input v-model="orderForm.kolicinaNultaGrupa"
                      placeholder="Unesite potrebnu zapreminu krvi Nulte grupe u litrama..."
                      type="number"
                      min="0"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Datum:</td>
                  <td>
                    <datepicker v-model="orderForm.datumIVreme" style="width:100%;">

                    </datepicker>
                  </td>
                </tr>
              </tbody>
              <tfoot>
                <tr>
                  <td colspan="2">
                    <button
                      style="margin-bottom: 20px; margin-top: 20px"
                      @click="orderBlood()"
                    >
                      Naruci
                    </button>
                  </td>
                </tr>
                <tr>
                  <td colspan="2">
                    <div v-if="messages.successMessage" v-html="messages.successMessage" class="alert alert-success"/>
                    <div v-if="messages.errorMessage" v-html="messages.errorMessage" class="alert alert-error"/>
                  </td>
                </tr>
              </tfoot>
            </table>
          </td>
        </tr>
        <tfoot>
          <tr>
            <td colspan="2"></td>
          </tr>
          <tr>
            <td colspan="2">
              <yandex-map
                :settings="settings"
                :coords="pocetneKoordinate"
                zoom="5"
                style="width: 100%; height: 600px"
                language="en-US"
              >
                <ymap-marker
                  v-for="(tmp, index) in lokacija"
                  :key="index"
                  :marker-id="index"
                  :coords="tmp.koordinateTemp"
                  :balloon-template="balloonTemplate"
                >
                </ymap-marker>

                <!-- <ymap-marker
                                    :marker-id="1" :coords="pocetneKoordinate" :balloon-template="balloonTemplate">
                                </ymap-marker> -->
              </yandex-map>
            </td>
          </tr>
        </tfoot>
      </table>
    </div>
  </div>
</template>

<script>
import dataService from "../services/dataService";
import StarRating from "vue-star-rating";
import { yandexMap, ymapMarker } from "vue-yandex-maps";
import { loadYmap } from "vue-yandex-maps";
import "regenerator-runtime/runtime";

import VueDatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css';
import moment from 'moment';

export default {
  components: {
    starrating: StarRating,
    yandexMap,
    ymapMarker,
    datepicker:VueDatePicker
  },
  setup() {},

  data() {
    return {
      settings: {
        apiKey: "5e04929d-e957-4fdd-b85c-08e60b3cb3f0",
        lang: "en_US",
        coordorder: "latlong",
        enterprise: false,
        version: "2.1",
      },
      pocetneKoordinate: [],
      lokacija: [],
      radnoVreme: "",
      selectedGroup: 1,
      idCentraDostavljac: null,
      medCentar: {},
      bloodReserve: 0,

      messages:{
        successMessage:'',
        errorMessage:''
      },

      idCentraNabavljac:null,
      adresaNabavljaca:null,
      coordsOd:null,
      userRole: '',
      showForm: false,
      userId: -1,
      orderForm: {
        id:null,
        kolicinaAGrupa: 0,
        kolicinaBGrupa: 0,
        kolicinaABGrupa: 0,
        kolicinaNultaGrupa: 0,
        datumIVreme: null,
        lokacijaOd:{
          lat:'',
          lng:''
        },
        lokacijaDo:{
          lat:'',
          lng:''
        },
      },
    };
  },
   async created() {
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
      } catch (error) {
        console.log("Greska: " + error.message);
      }
    }
    this.idCentraDostavljac = this.$route.params.id;
    navigator.geolocation.getCurrentPosition((geolocation) => {
      this.pocetneKoordinate.push(geolocation.coords.latitude);
      this.pocetneKoordinate.push(geolocation.coords.longitude);
    });
    
    // dataService.getMedCentarByWorkerId(this.userId).then(response => {
    //   console.log("DOBAVLJEN MEDCENTAR U KOM RADI RADNIK");
    //   this.adresaNabavljaca = response.data.adresa;
    //   console.log("ADRESA OD JE: " + this.adresaNabavljaca)
    //   await getCoordsFromAdresa(this.adresaNabavljaca);
    // });
    // dataService.getMedCentarById(this.idCentra).then(response =>{
    //     console.log("Dobavljen MedCentar: " + JSON.stringify(response.data));
    //     this.medCentar = response.data;
    //     this.bloodReserve = this.medCentar.aGrupa;
    // });
  },
  methods: {
    async getCoordsFromAdresa(){
      return dataService.getMedCentarByWorkerId(this.userId).then(response => {
        this.idCentraNabavljac = response.data.id;
        console.log("DOBAVLJEN MEDCENTAR U KOM RADI RADNIK");
        this.adresaNabavljaca = response.data.adresa;
        console.log("ADRESA OD JE: " + this.adresaNabavljaca)
        ymaps.geocode(this.adresaNabavljaca).then((res) => {
          console.log("ADRESA: " + this.adresaNabavljaca);
          let tmpObjekat = {
            koordinateTemp: res.geoObjects.get(0).geometry.getCoordinates(),
          };
          this.orderForm.lokacijaDo.lat = tmpObjekat.koordinateTemp[0];
          this.orderForm.lokacijaDo.lng = tmpObjekat.koordinateTemp[1];
          console.log("tmpObjekat: " + JSON.stringify(tmpObjekat));
          // this.lokacije.push(tmpObjekat);
          this.lokacija.push(tmpObjekat);
          console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
        });
      });
    },
    // async getCoordsFromAdresa(){
    //   return ymaps.geocode(this.adresaNabavljaca).then((res) => {
    //       console.log("ADRESA: " + this.adresaNabavljaca);
    //       let tmpObjekat = {
    //         koordinateTemp: res.geoObjects.get(0).geometry.getCoordinates(),
    //       };
    //       console.log("tmpObjekat: " + JSON.stringify(tmpObjekat));
    //       // this.lokacije.push(tmpObjekat);
    //       this.lokacija.push(tmpObjekat);
    //       console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
    //     });
    // },
    orderBlood() {
      console.log("NARUDZBINA: " + JSON.stringify(this.orderForm));
      dataService.orderBlood(this.idCentraDostavljac, this.idCentraNabavljac, this.orderForm).then(response => {
        console.log("NARUDZBINA POSLATA");
        if(response.status === 202){
          this.messages.successMessage = '<h4>Uspesno rezervisano.</h4>'
          setTimeout(() => {
            this.messages.successMessage= '';
          }, 4000);
        }else{
          this.messages.errorMessage = '<h4>Nije moguce rezervisati.</h4>'
          setTimeout(() => {
            this.messages.errorMessage= '';
          }, 4000);
        }
      });
    },
    makeOrder() {
      this.showForm = !this.showForm;
    },
    async getMedCentar() {
      return dataService.getMedCentarById(this.idCentraDostavljac).then((response) => {
        console.log("Dobavljen MedCentar: " + JSON.stringify(response.data));
        this.medCentar = response.data;
        this.bloodReserve = this.medCentar.aGrupa;
        this.radnoVreme =
          moment(this.medCentar.radnoVreme.pocetak).format("HH:MM") +
          "-" +
          moment(this.medCentar.radnoVreme.kraj).format("HH:MM");
        console.log("RADNO VREME: " + this.radnoVreme);

        ymaps.geocode(this.medCentar.adresa).then((res) => {
          console.log("ADRESA: " + this.medCentar.adresa);
          let tmpObjekat = {
            koordinateTemp: res.geoObjects.get(0).geometry.getCoordinates(),
          };
          this.orderForm.lokacijaOd.lat = tmpObjekat.koordinateTemp[0];
          this.orderForm.lokacijaOd.lng = tmpObjekat.koordinateTemp[1];
          console.log("tmpObjekat: " + JSON.stringify(tmpObjekat));
          // this.lokacije.push(tmpObjekat);
          this.lokacija.push(tmpObjekat);
          console.log("LOKACIJA: " + JSON.stringify(this.lokacija));
        });
        // ymaps.geocode(this.medCentar.adresa).then(response => {
        //     let tmpObjekat = {
        //         koordinateTemp : response.getObjects.get(0).geometry.getCoordinates()
        //     }
        //     console.log("tmpObjekat: " + JSON.stringify(tmpObjekat));
        //     this.lokacija.push(tmpObjekat);
        //     console.log("LOKACIJA: " + JSON.stringify(this.lokacija))
        // });
      });
    },
    showTermine() {
      this.$router.push(`/termini/${this.medCentar.id}`);
    },
    selectGroup() {
      console.log("GRUPA: " + this.selectedGroup);
      if (this.selectedGroup == 1) {
        this.bloodReserve = this.medCentar.aGrupa;
      } else if (this.selectedGroup == 2) {
        this.bloodReserve = this.medCentar.abGrupa;
      } else if (this.selectedGroup == 3) {
        this.bloodReserve = this.medCentar.bGrupa;
      } else if (this.selectedGroup == 4) {
        this.bloodReserve = this.medCentar.nultaGrupa;
      }
      console.log("REZERVA: " + this.bloodReserve);
    },
  },
  computed: {
    ulogaShow(){
        let temp = false;
        console.log("ULOGA JE: " + this.userRole)
        if(this.userRole === 'MEDICINSKO_OSOBLJE'){
            temp = true;
        }else{
          console.log("NISU JEDNAKI")  
        }
        console.log("TEMP JE: " + temp)
        return temp;
    },
    balloonTemplate() {
      return `
                <h1 class="red">Medicinski centar!</h1>
                <p>Adresa centra: ${this.medCentar.adresa}</p>
                <p>Ocena centra: ${this.medCentar.ocena}</p>
                <img src="http://via.placeholder.com/350x150">
            `;
    },
  },
  async mounted() {
    await loadYmap({ ...this.settings, format: "json", debug: true });
    await this.getMedCentar();
    await this.getCoordsFromAdresa();
  },
};
</script>



<style scoped>
#table_overview {
  font-size: 20px;
  font-style: oblique;
  font-weight: 600;
}
#table_overview input {
  width: 100%;
}
#table_overview select {
  width: 100%;
}

#table_overview tr {
  margin-top: 5px;
  margin-bottom: 5px;
}
#table_overview tr:nth-child(even) {
  background-color: cornflowerblue;
}
#table_overview td:first-child {
  width: 20%;
}
#table_overview td:last-child {
  width: 200px;
}

.termini-button {
  color: #0275d8;
  border: 2px solid cornflowerblue;
  background-color: white;
  font-weight: 500;
  width: 200px;
  font-size: 15px;
  padding: 10px;
  margin: 10px;
  cursor: pointer;
  transition-duration: 0.4s;
}
.termini-button:hover {
  background-color: cornflowerblue;
  color: white;
}
</style>