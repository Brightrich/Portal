package com.brightrich.service;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.MtrackInvoice;

public interface MtrackInvoiceService {

	public MtrackInvoice saveMtrackInvoice(MtrackInvoice invoice);

    public List<MtrackInvoice> findMtrackInvoicebyCompanyId(String companyId, MatchMode mode);        
    
    public MtrackInvoice findMtrackInvoiceById(String id);
    
    public MtrackInvoice findMtrackInvoiceNumber(String invoiceNo);
    
    public List<MtrackInvoice> findMtrackInvoicebyInvoiceNo(String invoiceNo, MatchMode mode);
    
    public List<MtrackInvoice> findInvoiceByCriteria(List<Object[]> criteriaMapper, HashMap<String,String> aliasMap);
	
}
