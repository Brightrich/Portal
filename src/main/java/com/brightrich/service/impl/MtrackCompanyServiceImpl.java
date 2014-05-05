package com.brightrich.service.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.MtrackCompanyDao;
import com.brightrich.model.MtrackCompany;
import com.brightrich.service.MtrackCompanyService;

@Service("mtrackCompanyService")
@Transactional(readOnly = true)
public class MtrackCompanyServiceImpl implements MtrackCompanyService{

	@Autowired
    private MtrackCompanyDao mtrackCompanyDao;
	
	public MtrackCompany findMtrackCompanyById(String id) {
		return mtrackCompanyDao.findMtrackCompanyById(id);
	}

	 @Transactional(readOnly = false)
	public void saveMtrackCompany(MtrackCompany company) {
		mtrackCompanyDao.saveMtrackCompany(company);
		
	}

	 @Transactional(readOnly = false)
	public void deleteCompany(String id) {
		MtrackCompany user = mtrackCompanyDao.findById(Integer.parseInt(id));
        if (user != null) {
        	mtrackCompanyDao.delete(user);
        }
		
	}

	public List<MtrackCompany> findMtrackCompany(String name, MatchMode mode) {
		return mtrackCompanyDao.findMtrackCompanysbyName(name, mode);
	}

}
