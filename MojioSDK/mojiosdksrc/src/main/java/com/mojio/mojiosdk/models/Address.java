package com.mojio.mojiosdk.models;

public class Address {
	
	
	private String Address1;
	private String Address2;
	private String City;
	private String State;
	private String Zip;
	private String Country;
	
	public Address(){
		
	}

	public String getAddress1() {
		return Address1;
	}

	public void setAddress1(String address1) {
		Address1 = address1;
	}

	public String getAddress2() {
		return Address2;
	}

	public void setAddress2(String address2) {
		Address2 = address2;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getZip() {
		return Zip;
	}

	public void setZip(String zip) {
		Zip = zip;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

}
