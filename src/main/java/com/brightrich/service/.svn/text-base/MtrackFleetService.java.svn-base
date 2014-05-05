package com.brightrich.service;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.MtrackFleet;

public interface MtrackFleetService {

	public void saveMtrackFleet(MtrackFleet mtrackFleet);
	public List<MtrackFleet> findMtrackFleetsbyMSISDN(String msisdn, MatchMode mode);
	public MtrackFleet findMtrackFleetById(String mtrackFleetId);
	public int countMtrackFleetsbyCompanyId(String id);
	public List getMsisdnWithoutBilling(String month, String year, int companyId);
}
