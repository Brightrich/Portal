package com.brightrich.controller.attribute;

public class TaxInvAttribute extends BasePageAttribute{
	private String taxno;
	private String taxnoadd;
	private String message;

	public String getTaxno() {
		return taxno;
	}

	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}

	public String getTaxnoadd() {
		return taxnoadd;
	}

	public void setTaxnoadd(String taxnoadd) {
		this.taxnoadd = taxnoadd;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
