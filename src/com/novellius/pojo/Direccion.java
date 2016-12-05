package com.novellius.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Direccion {
	/*@Autowired
	@Value("Encino")*/
	private String calle;
	/*@Autowired
	@Value("99999")*/
	private String cp;
	
	public Direccion(){
		
	}

	public Direccion(String calle, String cp) {
		this.calle = calle;
		this.cp = cp;
	}

	public String getCalle() {
		return calle;
	}
	
	@Autowired
	@Value("Encino")
	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCp() {
		return cp;
	}

	@Autowired
	public void setCp(@Value("99999") String cp) {
		this.cp = cp;
	}

	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", cp=" + cp + "]";
	}
	
	
}
