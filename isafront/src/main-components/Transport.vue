<template>
	<div>
		<!-- <button @click="sendRqst()">TEST</button> -->
        <button @click="send()">SEND COORDS</button>
        <button @click="stopSendingPosition()">STOP</button>
        <button id="connect" class="btn btn-default" type="submit" :disabled="connected == true" @click.prevent="connect">PRIJAVA</button>
        <button id="disconnect" class="btn btn-default" type="submit" :disabled="connected == false" @click.prevent="disconnect">PREKINI
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
            connected: false
		}
	},
	methods:{
        stopSendingPosition(){
            this.stopSending = true;
        },

        async send(){
            var counter = 0;
            var i = setInterval(() => {
                console.log("BROJ COORDINATES: " + this.coords.length)
                console.log("SALJE SE POZICIJA: " + this.coords[counter])
                counter++;
                if (this.stompClient && this.stompClient.connected && this.connected == true) {
                    // const msg = { name: this.coords[counter] };
                    let msg = this.coords[counter];
                    console.log("SALJE SE U OBLIKU : " + JSON.stringify(msg))
                    this.stompClient.send("/app/news", JSON.stringify(msg), {});
                    // this.stompClient.send("/app/news", JSON.stringify(msg), {});
                }
                // this.sendPosition(this.coords[counter]);
                if(counter == this.coords.length){
                    clearInterval(i);
                }
            }, 200);
        },
        // sendPosition(crds){
        //     console.log("Send message:" + crds);
        //     if (this.stompClient && this.stompClient.connected) {
        //         const msg = { name: crds };
        //         this.stompClient.send("/app/", JSON.stringify(msg), {});
        //     }
        // },
        connect() {
            this.socket = new SockJS("http://localhost:9090/mywebsockets");
            this.stompClient = Stomp.over(this.socket);
            this.stompClient.connect(
                {},
                frame => {
                this.connected = true;
                console.log(frame);
                this.stompClient.subscribe("/topic/news", tick => {
                    console.log(tick);
                    // this.received_messages.push(JSON.parse(tick.body).content);
                    console.log("SADRZAJ ODGOVORA: " + tick.body);
                    // this.received_messages.push(tick.body);
                    // this.received_messages.push((tick.body).content);
                    // console.log("BROJ ELEMENATA: " + this.received_messages.length);
                    // console.log(this.received_messages);
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
		sendRqst(){
			console.log("SALJU SE KOORDINATE: " + JSON.stringify(this.coords))
			dataService.sendCoords(this.coords).then(response => {
				console.log("POSLATE KOORDINATE");
			});
		}, 
        async getPorudzbina(){
            return dataService.getPorudzbinaById(this.$route.params.id).then(response => {
                this.center = response.data.lokacijaOd;
                this.destination = response.data.lokacijaDo;
            });
        }
	},
	async mounted(){
       
        var map = L.map(this.$refs['mapElement']).setView([this.center.lat, this.center.lng], this.zoom);
        var tileLayer = (L.tileLayer(this.url, { attribution: this.attribution})).addTo(map);
        var ambulanceIcon = L.icon({
				iconUrl: 'src/assets/ambulance.ico',
				iconSize:[64, 64]
			});
        var pinIcon = L.icon({
				iconUrl: 'src/assets/pin.png',
				iconSize:[64, 64]
			});
        
        await this.getPorudzbina();
        console.log("CENTAR: " + JSON.stringify(this.center))
        console.log("DESTINACIJA: " + JSON.stringify(this.destination))
        
        var marker = L.marker([this.center.lat, this.center.lng], {icon: ambulanceIcon}).addTo(map);
        this.markers.push(marker);
        var marker2 = L.marker([this.destination.lat, this.destination.lng], {icon: pinIcon}).addTo(map);
        this.markers.push(marker2);

        L.Routing.control({
            waypoints: [
                L.latLng(this.center.lat, this.center.lng),
                L.latLng(this.destination.lat, this.destination.lng)
            ]
        }).on('routesfound', (e) => {
            console.log(e)
            console.log(typeof(e))
            console.log("BROJ KOORDINATA: " + e.routes[0].coordinates.length);
            for(let i = 0; i < e.routes[0].coordinates.length; i++){
                this.coords.push(e.routes[0].coordinates[i]);
            }
            console.log("BROJ ELEMENATA NIZA: " + this.coords.length);
        }).addTo(map);


		// navigator.geolocation.getCurrentPosition(geolocation => {
        //     this.center = {
        //         lat: geolocation.coords.latitude,
        //         lng: geolocation.coords.longitude
        //     };
        //     this.waypoints.push(this.center);

        //     let bgCoords = {
        //         lat: 44.81,
        //         lng: 20.46
        //     }
        //     this.markers.push(bgCoords);
        //     this.waypoints.push(bgCoords);



		// 	var map = L.map(this.$refs['mapElement']).setView([this.center.lat, this.center.lng], this.zoom);
		// 	// this.map = L.map(this.$refs['mapElement']).setView([this.center.lat, this.center.lng], this.zoom);
		// 	// var tileLayer = (L.tileLayer(this.url, { attribution: this.attribution}));
		// 	// tileLayer.addTo(this.map);
		// 	var tileLayer = (L.tileLayer(this.url, { attribution: this.attribution})).addTo(map);
		// 	var ambulanceIcon = L.icon({
		// 		iconUrl: 'src/assets/ambulance.ico',
		// 		iconSize:[64, 64]
		// 	});

		// 	var pinIcon = L.icon({
		// 		iconUrl: 'src/assets/pin.png',
		// 		iconSize:[64, 64]
		// 	});
		// 	var marker = L.marker([this.center.lat, this.center.lng], {icon: ambulanceIcon}).addTo(map);
		// 	this.markers.push(marker);
		// 	var marker2 = L.marker([bgCoords.lat, bgCoords.lng], {icon: pinIcon}).addTo(map);
		// 	this.markers.push(marker2);

			
		// 	// map.on('click', function(e){
		// 	// 	console.log(e)
		// 	// 	marker3 = L.marker([e.latlng.lat, e.latlng.lng]).addTo(map);
		// 	// });

		// 	L.Routing.control({
		// 		waypoints: [
		// 			L.latLng(this.center.lat, this.center.lng),
		// 			L.latLng(bgCoords.lat, bgCoords.lng)
		// 		]
		// 	}).on('routesfound', (e) => {
		// 		console.log(e)
		// 		console.log(typeof(e))
		// 		console.log("BROJ KOORDINATA: " + e.routes[0].coordinates.length);
		// 		for(let i = 0; i < e.routes[0].coordinates.length; i++){
		// 			this.coords.push(e.routes[0].coordinates[i]);
		// 		}
		// 		// this.setArray(e.routes[0].coordinates);
		// 		// this.setArray(e.routes[0].coordinates);
		// 		console.log("BROJ ELEMENATA NIZA: " + this.coords.length);
		// 		// this.coords = e.routes[0].coordinates;
		// 		// e.routes[0].coordinates.forEach(function(coord, index){
		// 		// 	setTimeout(() => {
		// 		// 		marker.setLatLng([coord.lat, coord.lng]);
		// 		// 	}, 30 * index)
		// 		// })
		// 	}).addTo(map);
			
        // });
		
		
		// this.map = L.map('map').setView([28.2380, 83.9956], 11);
	}

}
</script>



<style scoped>

</style>