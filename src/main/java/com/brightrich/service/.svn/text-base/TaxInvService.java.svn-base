package com.brightrich.service;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.TaxInv;

public interface TaxInvService {
	public void saveTaxInv (TaxInv attr);
	public List<TaxInv> findTaxInvByTaxNo(String taxno, MatchMode mode);
	public void updateTaxInvWithInvId(int invId, String taxNo);
	public String getAvailableTaxInNum();
}
