<template>
	<div>
		<button @click="sendRqst()">TEST</button>
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
			map : null,
		}
	},
	methods:{
		sendRqst(){
			console.log("SALJU SE KOORDINATE: " + JSON.stringify(this.coords))
			dataService.sendCoords(this.coords).then(response => {
				console.log("POSLATE KOORDINATE");
			});
		}, 
	},
	async mounted(){
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

			var map = L.map(this.$refs['mapElement']).setView([this.center.lat, this.center.lng], this.zoom);
			// this.map = L.map(this.$refs['mapElement']).setView([this.center.lat, this.center.lng], this.zoom);
			// var tileLayer = (L.tileLayer(this.url, { attribution: this.attribution}));
			// tileLayer.addTo(this.map);
			var tileLayer = (L.tileLayer(this.url, { attribution: this.attribution})).addTo(map);
			var ambulanceIcon = L.icon({
				iconUrl: 'src/assets/ambulance.ico',
				iconSize:[64, 64]
			});

			var pinIcon = L.icon({
				iconUrl: 'src/assets/pin.png',
				iconSize:[64, 64]
			});
			var marker = L.marker([this.center.lat, this.center.lng], {icon: ambulanceIcon}).addTo(map);
			this.markers.push(marker);
			var marker2 = L.marker([bgCoords.lat, bgCoords.lng], {icon: pinIcon}).addTo(map);
			this.markers.push(marker2);

			
			map.on('click', function(e){
				console.log(e)
				marker3 = L.marker([e.latlng.lat, e.latlng.lng]).addTo(map);
			});

			L.Routing.control({
				waypoints: [
					L.latLng(this.center.lat, this.center.lng),
					L.latLng(bgCoords.lat, bgCoords.lng)
				]
			}).on('routesfound', (e) => {
				console.log(e)
				console.log(typeof(e))
				console.log("BROJ KOORDINATA: " + e.routes[0].coordinates.length);
				for(let i = 0; i < e.routes[0].coordinates.length; i++){
					this.coords.push(e.routes[0].coordinates[i]);
				}
				// this.setArray(e.routes[0].coordinates);
				// this.setArray(e.routes[0].coordinates);
				console.log("BROJ ELEMENATA NIZA: " + this.coords.length);
				// this.coords = e.routes[0].coordinates;
				e.routes[0].coordinates.forEach(function(coord, index){
					setTimeout(() => {
						marker.setLatLng([coord.lat, coord.lng]);
					}, 30 * index)
				})
			}).addTo(map);
			
        });
		
		
		// this.map = L.map('map').setView([28.2380, 83.9956], 11);
	}

}
</script>



<style scoped>

</style>
