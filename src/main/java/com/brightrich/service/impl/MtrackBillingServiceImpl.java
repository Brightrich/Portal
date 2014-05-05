package com.brightrich.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.MtrackBillingDao;
import com.brightrich.model.MtrackBilling;
import com.brightrich.service.MtrackBillingService;

@Service("mtrackBillingService")
@Transactional(readOnly = true)
public class MtrackBillingServiceImpl implements MtrackBillingService{

	@Autowired 
	private MtrackBillingDao mtrackBillingDao;
	
	@Transactional(readOnly = false)
	public MtrackBilling saveMtrackBilling(MtrackBilling mtrackBilling) {
		return mtrackBillingDao.saveMtrackBilling(mtrackBilling);		
	}

	public List<MtrackBilling> findMtrackBillingsbyMSISDN(String msisdn,
			MatchMode mode) {
		return mtrackBillingDao.findMtrackBillingsbyMSISDN(msisdn, mode);
	}

	public MtrackBilling findMtrackBillingById(String mtrackBillingId) {
		return mtrackBillingDao.findMtrackBillingById(mtrackBillingId);
	}

	public List<MtrackBilling> findBillingByCriteria(List<Object[]> criteriaMapper, HashMap<String,String> aliasMap) {		
		return mtrackBillingDao.findBillingByCriteria(criteriaMapper, aliasMap);
	}
	
	@Transactional(readOnly=false)
	public void updateMtrackBillingAfterInvoice(String month, String year, int companyId, int invoiceId){
		mtrackBillingDao.updateMtrackBillingAfterInvoice(month, year, companyId, invoiceId);
	}
	
	public MtrackBilling findBillingByMSISDNandBillingDate(String msisdn, Date billingDate){
		return mtrackBillingDao.findBillingByMSISDNandBillingDate(msisdn, billingDate);
	}

}
