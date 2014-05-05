package com.brightrich.controller.attribute;

public class MtrackData{

	private String msisdn;
	private String company;
	private String doorNumber;
	private Integer fleetId;
			
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public Integer getFleetId() {
		return fleetId;
	}
	public void setFleetId(Integer fleetId) {
		this.fleetId = fleetId;
	}
	
	
	
}
