package com.brightrich.controller.attribute;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.brightrich.model.User;

public class MtrackPageAttribute extends BasePageAttribute{
	
	private String msisdnSearch;	
	private String dateSelectedVal;
	private String totalBillingVal;
	private String billingSummaryVal;
	private String callDetailVal;
	private String msisdnSelectedVal;
	private String fleetIdSelectedVal;
	private String invoiceNumVal;
	private String message;
	
	
	private CommonsMultipartFile [] files;
	private CommonsMultipartFile[]  batchFile;

	//Upload batch attr
	private List<String> numList;
	private boolean isAutoInsertErr;
	private int indexBill;
	private int indexCall;
	
	
	
	
	
    public List<String> getNumList() {
		return numList;
	}

	public void setNumList(List<String> numList) {
		this.numList = numList;
	}

	public boolean isAutoInsertErr() {
		return isAutoInsertErr;
	}

	public void setAutoInsertErr(boolean isAutoInsertErr) {
		this.isAutoInsertErr = isAutoInsertErr;
	}

	public int getIndexBill() {
		return indexBill;
	}

	public void setIndexBill(int indexBill) {
		this.indexBill = indexBill;
	}

	public int getIndexCall() {
		return indexCall;
	}

	public void setIndexCall(int indexCall) {
		this.indexCall = indexCall;
	}

	public CommonsMultipartFile[] getBatchFile() {
		return batchFile;
	}

	public void setBatchFile(CommonsMultipartFile[] batchFile) {
		this.batchFile = batchFile;
	}

	public CommonsMultipartFile[] getFiles() {
        return files;
    }

    public void setFiles( CommonsMultipartFile[] files ) {
        this.files = files;
    }
	
	
	public String getDateSelectedVal() {
		return dateSelectedVal;
	}
	public void setDateSelectedVal(String dateSelectedVal) {
		this.dateSelectedVal = dateSelectedVal;
	}
	public String getTotalBillingVal() {
		return totalBillingVal;
	}
	public void setTotalBillingVal(String totalBillingVal) {
		this.totalBillingVal = totalBillingVal;
	}
	public String getBillingSummaryVal() {
		return billingSummaryVal;
	}
	public void setBillingSummaryVal(String billingSummaryVal) {
		this.billingSummaryVal = billingSummaryVal;
	}
	public String getCallDetailVal() {
		return callDetailVal;
	}
	public void setCallDetailVal(String callDetailVal) {
		this.callDetailVal = callDetailVal;
	}
	
	public String getMsisdnSearch() {
		return msisdnSearch;
	}
	public void setMsisdnSearch(String msisdnSearch) {
		this.msisdnSearch = msisdnSearch;
	}

	public String getMsisdnSelectedVal() {
		return msisdnSelectedVal;
	}

	public void setMsisdnSelectedVal(String msisdnSelectedVal) {
		this.msisdnSelectedVal = msisdnSelectedVal;
	}

	public String getFleetIdSelectedVal() {
		return fleetIdSelectedVal;
	}

	public void setFleetIdSelectedVal(String fleetIdSelectedVal) {
		this.fleetIdSelectedVal = fleetIdSelectedVal;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getInvoiceNumVal() {
		return invoiceNumVal;
	}

	public void setInvoiceNumVal(String invoiceNumVal) {
		this.invoiceNumVal = invoiceNumVal;
	}
	
	
	
	
}
