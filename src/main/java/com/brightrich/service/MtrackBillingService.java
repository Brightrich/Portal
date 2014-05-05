package com.brightrich.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.MtrackBilling;

public interface MtrackBillingService {

	public MtrackBilling saveMtrackBilling(MtrackBilling mtrackBilling);
	public List<MtrackBilling> findMtrackBillingsbyMSISDN(String msisdn, MatchMode mode);
	public MtrackBilling findMtrackBillingById(String mtrackBillingId);
	public List<MtrackBilling> findBillingByCriteria(List<Object[]> criteriaMapper, HashMap<String,String> aliasMap);
	public void updateMtrackBillingAfterInvoice(String month, String year, int companyId, int invoiceId);
	public MtrackBilling findBillingByMSISDNandBillingDate(String msisdn, Date billingDate);
}
