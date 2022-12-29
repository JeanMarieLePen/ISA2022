package isa2022.projekat.model.data;

public class Lokacija {

	private double lat;
	private double lng;
	
	public Lokacija() {
	}
	public Lokacija(double lat, double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
}
