package isa2022.projekat.dtos;

import isa2022.projekat.model.data.ZahtevRezervacijaTermina;

public class ZahtevRezervacijaTerminaDTO {

		private RegKorisnikDTO korisnik;
		private String qrCode;
		private String status;
		
		public ZahtevRezervacijaTerminaDTO() {
			super();
		}
		public RegKorisnikDTO getKorisnik() {
			return korisnik;
		}
		public void setKorisnik(RegKorisnikDTO korisnik) {
			this.korisnik = korisnik;
		}
		public String getQrCode() {
			return qrCode;
		}
		public void setQrCode(String qrCode) {
			this.qrCode = qrCode;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		
	
}
