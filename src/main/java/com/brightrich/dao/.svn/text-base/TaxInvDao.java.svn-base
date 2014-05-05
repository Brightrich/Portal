package com.brightrich.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.TaxInv;

public interface TaxInvDao extends AbstractDao<TaxInv, Integer>{
	public void saveTaxInv (TaxInv attr);
	public List<TaxInv> findTaxInvByTaxNo(String taxno, MatchMode mode);
	public String getAvailableTaxInNum();
	public void updateTaxInvWithInvId(int invId, String taxNo);
}
