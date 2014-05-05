package com.brightrich.service.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.TaxInvDao;
import com.brightrich.model.TaxInv;
import com.brightrich.service.TaxInvService;

@Service("taxInvService")
@Transactional(readOnly = true)
public class TaxInvServiceImpl implements TaxInvService {

	@Autowired 
	private TaxInvDao taxInvDao;
	
	@Transactional(readOnly = false)
	public void saveTaxInv(TaxInv attr) {
		// TODO Auto-generated method stub
		taxInvDao.saveOrUpdate(attr);
	}

	public List<TaxInv> findTaxInvByTaxNo(String taxno, MatchMode mode) {
		// TODO Auto-generated method stub
		return taxInvDao.findTaxInvByTaxNo(taxno, mode);
	}

	@Transactional(readOnly = false)
	public void updateTaxInvWithInvId(int invId, String taxNo) {
		 taxInvDao.updateTaxInvWithInvId(invId, taxNo);
	}

	public String getAvailableTaxInNum() {
		return taxInvDao.getAvailableTaxInNum();
	}

}
