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
@Table (name = "T_INVOICE")
public class MtrackInvoice extends BaseObject{

	private Integer invoiceId;
	private MtrackCompany companyId;
	private BigDecimal totalInvoice;
	private String invoiceNo;
	private Date invoiceDate;
	private String createdBy;
	private Date createdDate;
	private String invoiceFilePath;
	private BigDecimal totalPrice;
	private String taxInvNumber;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "INVOICE_ID")
	public Integer getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Integer id) {
		this.invoiceId = id;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COMPANY_ID", nullable = false)
	public MtrackCompany getCompanyId() {
		return companyId;
	}
	public void setCompanyId(MtrackCompany companyId) {
		this.companyId = companyId;
	}
	
	@Column(name = "TOTAL_INVOICE")
	public BigDecimal getTotalInvoice() {
		return totalInvoice;
	}
	public void setTotalInvoice(BigDecimal totalInvoice) {
		this.totalInvoice = totalInvoice;
	}
	
	@Column(name = "INVOICE_NUMBER")
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@Column(name = "INVOICE_DATE")
	@Type(type="timestamp")
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "CREATED_DATE")
	@Type(type="timestamp")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	@Column(name="INVOICE_FILE_PATH")
	public String getInvoiceFilePath() {
		return invoiceFilePath;
	}
	public void setInvoiceFilePath(String invoiceFilePath) {
		this.invoiceFilePath = invoiceFilePath;
	}
	
	
	@Column(name = "TOTAL_PRICE")
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	@Column(name = "TAX_INV_NUMBER")
	public String getTaxInvNumber() {
		return taxInvNumber;
	}
	public void setTaxInvNumber(String taxInvNumber) {
		this.taxInvNumber = taxInvNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result
				+ ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
		result = prime * result
				+ ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
		result = prime * result
				+ ((invoiceNo == null) ? 0 : invoiceNo.hashCode());
		result = prime * result
				+ ((totalInvoice == null) ? 0 : totalInvoice.hashCode());
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
		MtrackInvoice other = (MtrackInvoice) obj;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		if (invoiceDate == null) {
			if (other.invoiceDate != null)
				return false;
		} else if (!invoiceDate.equals(other.invoiceDate))
			return false;
		if (invoiceNo == null) {
			if (other.invoiceNo != null)
				return false;
		} else if (!invoiceNo.equals(other.invoiceNo))
			return false;
		if (totalInvoice == null) {
			if (other.totalInvoice != null)
				return false;
		} else if (!totalInvoice.equals(other.totalInvoice))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MtrackInvoice [invoiceId=");
		builder.append(invoiceId);
		builder.append(", totalInvoice=");
		builder.append(totalInvoice);
		builder.append(", invoiceNo=");
		builder.append(invoiceNo);
		builder.append(", invoiceDate=");
		builder.append(invoiceDate);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append("]");
		return builder.toString();
	}
	
	public String toStringMulti(String name, String action, String format){    	
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append(format+ " " + name, action)
				.append("MtrackInvoice id ", invoiceId)
				.append("COMPANY NAME " + companyId.getName())
				.append("INVOICE NO ", invoiceNo)
				.append("INVOICE DATE ", invoiceDate)
				.append("TOTAL INVOICE ", totalInvoice)
				.append("TOTAL PRICE " + totalPrice)				
				.append("INVOICE FILE PATH " + invoiceFilePath)
				.append("CREATED DATE ", createdDate)
				.append("CREATED BY ", createdBy)
				.toString();    	
    }
	
	
}
