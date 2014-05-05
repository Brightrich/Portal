package com.brightrich.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "MTRACK_FLEET")
public class MtrackFleet extends BaseObject{

	private Integer id;
	private MtrackCompany company;
	private String msisdn;
	private String doorNumber;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "FLEET_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public MtrackCompany getCompany() {
		return company;
	}
	public void setCompany(MtrackCompany company) {
		this.company = company;
	}
	
	@Column(name = "MSISDN")
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	@Column(name = "DOOR_NUMBER")
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((msisdn == null) ? 0 : msisdn.hashCode());
		result = prime * result
				+ ((doorNumber == null) ? 0 : doorNumber.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MtrackFleet other = (MtrackFleet) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (msisdn == null) {
			if (other.msisdn != null)
				return false;
		} else if (!msisdn.equals(other.msisdn))
			return false;
		if (doorNumber == null) {
			if (other.doorNumber != null)
				return false;
		} else if (!doorNumber.equals(other.doorNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MtrackFleet [id=");
		builder.append(id);
		builder.append(", msisdn=");
		builder.append(msisdn);
		builder.append(", doorNumber=");
		builder.append(doorNumber);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
