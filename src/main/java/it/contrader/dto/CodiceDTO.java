package it.contrader.dto;

public class CodiceDTO {
	private int id;
	private String otp;
	
	public CodiceDTO(int id, String otp) {
		this.id=id;
		this.otp= otp;
		
	}
	
	public CodiceDTO(String otp) {
		this.otp= otp;
		
	}

	public int getId () {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public String getOtp () {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}


}
