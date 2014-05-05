package com.brightrich.service.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.MtrackFleetDao;
import com.brightrich.model.MtrackFleet;
import com.brightrich.service.MtrackFleetService;

@Service("mtrackFleetService")
@Transactional(readOnly = true)
public class MtrackFleetServiceImpl implements MtrackFleetService{

	@Autowired 
	private MtrackFleetDao mtrackFleetDao;

	@Transactional(readOnly = false)
	public void saveMtrackFleet(MtrackFleet mtrackFleet) {
		mtrackFleetDao.saveMtrackFleet(mtrackFleet);
	}

	public List<MtrackFleet> findMtrackFleetsbyMSISDN(String msisdn,
			MatchMode mode) {				
		return mtrackFleetDao.findMtrackFleetsbyMSISDN(msisdn, mode);
	}

	public MtrackFleet findMtrackFleetById(String mtrackFleetId) {		
		return mtrackFleetDao.findMtrackFleetById(mtrackFleetId);
	}
	
	public int countMtrackFleetsbyCompanyId(String id){
		return mtrackFleetDao.countMtrackFleetsbyCompanyId(id);
	}

	public List getMsisdnWithoutBilling(String month, String year, int companyId) {
		return mtrackFleetDao.getMsisdnWithoutBilling(month, year, companyId);
	}
}
