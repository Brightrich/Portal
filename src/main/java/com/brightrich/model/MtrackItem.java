package com.brightrich.model;

import java.math.BigDecimal;

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
@Table (name = "MTRACK_ITEM")
public class MtrackItem extends BaseObject{

	private Integer id;
	private MtrackCompany companyId;
	private String itemNumber;
	private String description;
	private BigDecimal mtrackFee;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public MtrackCompany getCompanyId() {
		return companyId;
	}
	public void setCompanyId(MtrackCompany companyId) {
		this.companyId = companyId;
	}
	
	@Column(name = "ITEM_NUMBER")
	public String getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name = "MTRACK_FEE")
	public BigDecimal getMtrackFee() {
		return mtrackFee;
	}
	public void setMtrackFee(BigDecimal mtrackFee) {
		this.mtrackFee = mtrackFee;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((itemNumber == null) ? 0 : itemNumber.hashCode());
		result = prime * result
				+ ((mtrackFee == null) ? 0 : mtrackFee.hashCode());
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
		MtrackItem other = (MtrackItem) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemNumber == null) {
			if (other.itemNumber != null)
				return false;
		} else if (!itemNumber.equals(other.itemNumber))
			return false;
		if (mtrackFee == null) {
			if (other.mtrackFee != null)
				return false;
		} else if (!mtrackFee.equals(other.mtrackFee))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MtrackItem [id=");
		builder.append(id);
		builder.append(", itemNumber=");
		builder.append(itemNumber);
		builder.append(", description=");
		builder.append(description);
		builder.append(", mtrackFee=");
		builder.append(mtrackFee);
		builder.append("]");
		return builder.toString();
	}
	
		
	
	
	
}
