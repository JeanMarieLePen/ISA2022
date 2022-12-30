<template>

    <div>
        <l-map ref="mapElement" @click="secondMarker" :zoom="zoom" :center="center" style="height: 500px; width:100%">

            <l-tile-layer :url="url" :attribution="attribution">
                
            </l-tile-layer>
            <l-marker v-for="(tmp, index) in markers" :key="index" :lat-lng="tmp"/>
            <l-icon-default :image-path="path"/>
        </l-map>

    </div>
    
</template>



<script>
import { LMap, LTileLayer, LMarker, LIconDefault } from 'vue2-leaflet';
import L from 'leaflet';
import "leaflet/dist/leaflet.css";

import  'leaflet-routing-machine';
import "leaflet-routing-machine/dist/leaflet-routing-machine.css";

export default {
    methods:{
        secondMarker(e){
            if(this.markers.length > 1){
                this.markers.pop();
            }
            console.log(e);
            let tempMarker = {
                lat: e.latlng.lat,
                lng: e.latlng.lng
            }
            this.markers.push(tempMarker);

            const routingLayer = this.mapObject.Routing.control(this.waypoints);
            routingLayer.addTo(this.$refs['mapElement']);
        }
    },
    components:{
        
        LMap,
        LTileLayer,
        LMarker,
        LIconDefault
    },
    data(){
        return{
            markers: [],


            path:"/images",
            mapObject: null,
            zoom:10,
            url: "http://{s}.tile.osm.org/{z}/{x}/{y}.png",
            attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
            waypoints:[],
            center:{
                lat: 0,
                lng: 0,
            }
        }
    },
    created(){

    },
    mounted(){
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

        this.mapObject = this.$refs['mapElement'];

        
        // this.mapObject = L.map(this.mapId, {
        //     zoom: this.zoom,
        //     center: this.center,
        // });

        // L.tileLayer(this.osmUrl, {
        //     attribution: this.attribution
        // }).addTo(this.mapObject);
    }
}
</script>
