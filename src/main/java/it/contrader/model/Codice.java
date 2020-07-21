package it.contrader.model;

public class Codice {
	private int id;
	private String otp;
	
	public Codice(int id,String otp2) {
		this.id=id;
		this.otp= otp2;
		
	}

	public Codice( String otp2) {
		this.otp= otp2;
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
		this.otp=otp;
	}


}
