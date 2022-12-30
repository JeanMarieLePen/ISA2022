<template>
	<div>
        <button id="connect" class="btn btn-default" type="submit" :disabled="connected == true" @click.prevent="connect">Connect</button>
        <button id="disconnect" class="btn btn-default" type="submit" :disabled="connected == false" @click.prevent="disconnect">Disconnect
        </button>
		<div id="map" style="width:100%; height:800px;" ref="mapElement">
		</div>
	</div>
</template>



<script>
import dataService from '../services/dataService'
import L, { icon } from 'leaflet';
import 'leaflet-routing-machine';
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";
import { LMap, LTileLayer, LMarker, LIconDefault } from 'vue2-leaflet';


import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
	components:{
		LMap,
        LTileLayer,
        LMarker,
        LIconDefault
	},
	data(){
		return{
			coords:[],
			markers:[],
			zoom:10,
			url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
            waypoints:[],
			center:{
                lat: 0,
                lng: 0,
            },
            destination:{
                lat: 0,
                lng: 0,
            },
			map : null,


            stopSending: false,
            connected: false,
            received_coords:null,
		}
	},
	methods:{

        connect() {
            var ambulanceIcon = L.icon({
				iconUrl: 'src/assets/ambulance.ico',
				iconSize:[64, 64]
			});
            
            this.socket = new SockJS("http://localhost:8082/main-app-websockets");
            this.stompClient = Stomp.over(this.socket);
            var map = L.map(this.$refs['mapElement']).setView([this.center.lat, this.center.lng], this.zoom);
            var tileLayer = (L.tileLayer(this.url, { attribution: this.attribution})).addTo(map);
            var marker = L.marker([this.center.lat, this.center.lng], {icon: ambulanceIcon}).addTo(map);
            this.stompClient.connect(
                {},
                frame => {
                this.connected = true;
                console.log(frame);
                this.stompClient.subscribe("/topic/coords", tick => {
                    console.log(tick);
                    // this.received_messages.push(JSON.parse(tick.body).content);
                    console.log("SADRZAJ ODGOVORA: " + tick.body);
                    this.received_coords = JSON.parse(tick.body);
                    console.log(JSON.stringify(this.received_coords));

                    
                    setTimeout(() => {
						marker.setLatLng([this.received_coords.lat, this.received_coords.lng]);
					}, 15000)
                    
                });
                },
                error => {
                    console.log(error);
                    this.connected = false;
                }
            );
        },
        disconnect() {
            if (this.stompClient) {
                this.stompClient.disconnect();
            }
            this.connected = false;
        },
        tickleConnection() {
            this.connected ? this.disconnect() : this.connect();
        },
        async getPorudzbina(){
            return dataService.getPorudzbinaById(this.$route.params.id).then(response => {
                this.center = response.data.lokacijaOd;
                this.destination = response.data.lokacijaDo;
            });
        }
	},
	async mounted(){
        // await this.getPorudzbina();
        // this.waypoints.push(this.center);
        // this.waypoints.push(this.destination);
		navigator.geolocation.getCurrentPosition(geolocation => {
            this.center = {
                lat: geolocation.coords.latitude,
                lng: geolocation.coords.longitude
            };
            this.waypoints.push(this.center);

            let bgCoords = {
                lat: 44.81,
                lng: 20.46
            }
            this.markers.push(bgCoords);
            this.waypoints.push(bgCoords);

			
			
        });
		
		
		// this.map = L.map('map').setView([28.2380, 83.9956], 11);
	}

}
</script>



<style scoped>

</style>