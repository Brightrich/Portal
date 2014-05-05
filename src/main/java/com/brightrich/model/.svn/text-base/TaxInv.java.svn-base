package com.brightrich.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table (name = "MTRACK_TAX_INVOICE")
public class TaxInv extends BaseObject{

	private String taxno;
	private MtrackInvoice invid;
	private String addedby;
	private Date addeddate;
	
	@Id
	@Column(name = "TAX_INV_NUMBER")
	public String getTaxno() {
		return taxno;
	}
	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVOICE_ID", updatable=false)
	public MtrackInvoice getInvid() {
		return invid;
	}
	public void setInvid(MtrackInvoice invid) {
		this.invid = invid;
	}
	
	@Column(name = "ADDED_BY")
	public String getAddedby() {
		return addedby;
	}
	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}
	
	@Column(name = "ADDED_DATE")
	@Type(type="timestamp")
	public Date getAddeddate() {
		return addeddate;
	}
	public void setAddeddate(Date addeddate) {
		this.addeddate = addeddate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((taxno == null) ? 0 : taxno.hashCode());
		result = prime * result + ((invid == null) ? 0 : invid.hashCode());
		result = prime * result
				+ ((addedby == null) ? 0 : addedby.hashCode());
		result = prime * result
				+ ((addeddate == null) ? 0 : addeddate.hashCode());
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
		TaxInv other = (TaxInv) obj;
		if (taxno == null) {
			if (other.taxno != null)
				return false;
		} else if (!taxno.equals(other.taxno))
			return false;
		if (invid == null) {
			if (other.invid != null)
				return false;
		} else if (!invid.equals(other.invid))
			return false;
		if (addedby == null) {
			if (other.addedby != null)
				return false;
		} else if (!addedby.equals(other.addedby))
			return false;
		if (addeddate == null) {
			if (other.addeddate != null)
				return false;
		} else if (!addeddate.equals(other.addeddate))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TAXINV [taxno=");
		builder.append(taxno);
		builder.append(", addedby=");
		builder.append(addedby);
		builder.append(", invid=");
		builder.append(invid);
		builder.append(", addeddate=");
		builder.append(addeddate);
		builder.append("]");
		return builder.toString();
	}
	
}
