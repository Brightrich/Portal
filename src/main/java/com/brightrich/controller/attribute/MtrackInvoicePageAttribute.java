package com.brightrich.controller.attribute;

import java.util.List;

import com.brightrich.model.MtrackCompany;
import com.brightrich.model.MtrackInvoice;

public class MtrackInvoicePageAttribute extends BasePageAttribute{

	private String invoiceSearch;
	private List<MtrackInvoice> invoiceList;
	private List<MtrackCompany> companyList;
	private String billingMonth;
	private String billingYear;
	private String companySelected;
	
	
	public String getBillingMonth() {
		return billingMonth;
	}
	public void setBillingMonth(String billingMonth) {
		this.billingMonth = billingMonth;
	}
	public String getBillingYear() {
		return billingYear;
	}
	public void setBillingYear(String billingYear) {
		this.billingYear = billingYear;
	}
	public String getCompanySelected() {
		return companySelected;
	}
	public void setCompanySelected(String companySelected) {
		this.companySelected = companySelected;
	}
	public List<MtrackCompany> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<MtrackCompany> companyList) {
		this.companyList = companyList;
	}
	public String getInvoiceSearch() {
		return invoiceSearch;
	}
	public void setInvoiceSearch(String invoiceSearch) {
		this.invoiceSearch = invoiceSearch;
	}
	public List<MtrackInvoice> getInvoiceList() {
		return invoiceList;
	}
	public void setInvoiceList(List<MtrackInvoice> invoiceList) {
		this.invoiceList = invoiceList;
	}
	
	
	
	
}
