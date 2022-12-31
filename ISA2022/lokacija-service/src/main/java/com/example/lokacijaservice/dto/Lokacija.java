package com.example.lokacijaservice.dto;

public class Lokacija {

	private Long lat;
	private Long lng;
	
	public Lokacija() {
		super();
	}
	public Lokacija(Long lat, Long lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public Long getLat() {
		return lat;
	}
	public void setLat(Long lat) {
		this.lat = lat;
	}
	public Long getLng() {
		return lng;
	}
	public void setLng(Long lng) {
		this.lng = lng;
	}
	
	
}
