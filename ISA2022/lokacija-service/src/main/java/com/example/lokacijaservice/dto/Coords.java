package com.example.lokacijaservice.dto;

import java.util.Collection;

public class Coords {

	private Collection<LatLng> listaLatLng;

	public Coords() {
		super();
	}

	public Coords(Collection<LatLng> listaLatLng) {
		super();
		this.listaLatLng = listaLatLng;
	}

	public Collection<LatLng> getListaLatLng() {
		return listaLatLng;
	}

	public void setListaLatLng(Collection<LatLng> listaLatLng) {
		this.listaLatLng = listaLatLng;
	}
	
	
}
