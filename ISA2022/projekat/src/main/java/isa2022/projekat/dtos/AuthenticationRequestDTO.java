package isa2022.projekat.dtos;

/**Klasa koja sluzi da modeluje dolazni zahtev za logovanje**/
public class AuthenticationRequestDTO {

//	atributi
	private String email;
	private String password;
//	konstruktori
	
	public AuthenticationRequestDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public AuthenticationRequestDTO() {
		super();
	}
//	geteri i seteri
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
