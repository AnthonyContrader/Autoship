package it.contrader.dto;

public class CodiceDTO {
	private int id;
	private int otp;
	
	public CodiceDTO(int id,int otp) {
		this.id=id;
		this.otp= otp;
		
	}

	public int getId () {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	public int getOtp () {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp=otp;
	}


}
