package com.brightrich.service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.MtrackInvoiceDao;
import com.brightrich.model.MtrackInvoice;
import com.brightrich.service.MtrackInvoiceService;

@Service("mtrackInvoiceService")
@Transactional(readOnly = true)
public class MtrackInvoiceServiceImpl implements MtrackInvoiceService {

	@Autowired private MtrackInvoiceDao mtrackInvoiceDao;
	
	@Transactional(readOnly=false)
	public MtrackInvoice saveMtrackInvoice(MtrackInvoice invoice) {
		return mtrackInvoiceDao.saveMtrackInvoice(invoice);
    }

	public List<MtrackInvoice> findMtrackInvoicebyCompanyId(String companyId,
			MatchMode mode) {
		// TODO Auto-generated method stub
		return mtrackInvoiceDao.findMtrackInvoicebyCompanyId(companyId, mode);
	}

	public MtrackInvoice findMtrackInvoiceById(String id) {
		// TODO Auto-generated method stub
		return mtrackInvoiceDao.findMtrackInvoiceById(id);
	}

	public MtrackInvoice findMtrackInvoiceNumber(String invoiceNo) {
		return mtrackInvoiceDao.findMtrackInvoiceNumber(invoiceNo);
	}

	public List<MtrackInvoice> findMtrackInvoicebyInvoiceNo(String invoiceNo,
			MatchMode mode) {
		// TODO Auto-generated method stub
		return mtrackInvoiceDao.findMtrackInvoicebyInvoiceNo(invoiceNo, mode);
	}
	
	public List<MtrackInvoice> findInvoiceByCriteria(List<Object[]> criteriaMapper, HashMap<String,String> aliasMap){
		return mtrackInvoiceDao.findInvoiceByCriteria(criteriaMapper, aliasMap);
	}
}
