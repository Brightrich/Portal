package com.brightrich.model;

import java.math.BigDecimal;
import java.math.BigInteger;
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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;


@Entity
@Table (name = "T_BILLING")
public class MtrackBilling extends BaseObject{

	private Integer id;
	private MtrackFleet fleetId;
	private Date billingDate;
	private BigDecimal totalBilling;
	private String billSummaryPath;
	private String callDetailPath;
	private Date createdDate;
	private String createddBy;
	private MtrackInvoice invoiceId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FLEET_ID", nullable = false)
	public MtrackFleet getFleetId() {
		return fleetId;
	}
	public void setFleetId(MtrackFleet fleetId) {
		this.fleetId = fleetId;
	}
	
	@Column(name = "BILLING_DATE")
	@Type(type="timestamp")
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	
	@Column(name = "TOTAL_BILLING")
	public BigDecimal getTotalBilling() {
		return totalBilling;
	}
	public void setTotalBilling(BigDecimal totalBilling) {
		this.totalBilling = totalBilling;
	}
	
	@Column(name = "BILL_SUMMARY_PATH")
	public String getBillSummaryPath() {
		return billSummaryPath;
	}
	public void setBillSummaryPath(String billSummaryPath) {
		this.billSummaryPath = billSummaryPath;
	}
	
	@Column(name = "CALL_DETAIL_PATH")
	public String getCallDetailPath() {
		return callDetailPath;
	}
	public void setCallDetailPath(String callDetailPath) {
		this.callDetailPath = callDetailPath;
	}
	
	@Column(name = "CREATED_DATE")
	@Type(type="timestamp")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "CREATED_BY")
	public String getCreateddBy() {
		return createddBy;
	}
	public void setCreateddBy(String createddBy) {
		this.createddBy = createddBy;
	}
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INVOICE_ID", nullable = true)
	public MtrackInvoice getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(MtrackInvoice invoiceId) {
		this.invoiceId = invoiceId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((billSummaryPath == null) ? 0 : billSummaryPath.hashCode());
		result = prime * result
				+ ((billingDate == null) ? 0 : billingDate.hashCode());
		result = prime * result
				+ ((callDetailPath == null) ? 0 : callDetailPath.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result
				+ ((createddBy == null) ? 0 : createddBy.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((totalBilling == null) ? 0 : totalBilling.hashCode());
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
		MtrackBilling other = (MtrackBilling) obj;
		if (billSummaryPath == null) {
			if (other.billSummaryPath != null)
				return false;
		} else if (!billSummaryPath.equals(other.billSummaryPath))
			return false;
		if (billingDate == null) {
			if (other.billingDate != null)
				return false;
		} else if (!billingDate.equals(other.billingDate))
			return false;
		if (callDetailPath == null) {
			if (other.callDetailPath != null)
				return false;
		} else if (!callDetailPath.equals(other.callDetailPath))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (createddBy == null) {
			if (other.createddBy != null)
				return false;
		} else if (!createddBy.equals(other.createddBy))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (totalBilling == null) {
			if (other.totalBilling != null)
				return false;
		} else if (!totalBilling.equals(other.totalBilling))
			return false;
		
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MtrackBilling [id=");
		builder.append(id);
		builder.append(", billingDate=");
		builder.append(billingDate);
		builder.append(", totalBilling=");
		builder.append(totalBilling);
		builder.append(", billSummaryPath=");
		builder.append(billSummaryPath);
		builder.append(", callDetailPath=");
		builder.append(callDetailPath);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", createddBy=");
		builder.append(createddBy);
		
		builder.append("]");
		return builder.toString();
	}
	
	public String toStringMulti(String name, String action, String format){    	
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append(format+ " " + name, action)
				.append("MtrackBilling [id ", id)
				.append("MSISDN ", fleetId.getMsisdn())
				.append("COMPANY NAME ", fleetId.getCompany().getName())
				.append("BILLING DATE ", billingDate)
				.append("TOTAL BILLING ", totalBilling)
				.append("BILLING SUMMARY FILE NAME ", billSummaryPath)
				.append("CALL DETAILS FILE NAME ", callDetailPath)
				.append("CREATED DATE ", createdDate)
				.append("CREATED BY ", createddBy)
				.toString();    	
    }
	
	
}
