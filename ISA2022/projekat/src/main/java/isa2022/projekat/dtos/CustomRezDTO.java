package isa2022.projekat.dtos;

public class CustomRezDTO {
	private Long id;
	private Long userId;
	private TerminMiniDTO terminTemp;
	public CustomRezDTO(Long id, Long userId, TerminMiniDTO terminTemp) {
		super();
		this.id = id;
		this.userId = userId;
		this.terminTemp = terminTemp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public TerminMiniDTO getTerminTemp() {
		return terminTemp;
	}
	public void setTerminTemp(TerminMiniDTO terminTemp) {
		this.terminTemp = terminTemp;
	}
	
	
}
