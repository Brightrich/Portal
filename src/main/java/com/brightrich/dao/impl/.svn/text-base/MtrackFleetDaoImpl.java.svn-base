package com.brightrich.dao.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.MtrackFleetDao;
import com.brightrich.model.MtrackFleet;

@Repository
public class MtrackFleetDaoImpl extends AbstractDaoImpl<MtrackFleet, Integer> implements MtrackFleetDao {

    protected MtrackFleetDaoImpl() {
        super(MtrackFleet.class);
    }

    public void saveMtrackFleet(MtrackFleet mtrackFleet) {
        saveOrUpdate(mtrackFleet);
    }

    public List<MtrackFleet> findMtrackFleetsbyMSISDN(String msisdn, MatchMode mode) {    	
        return findByCriteria(Restrictions.like("msisdn", msisdn, mode));
    }        
    
    public MtrackFleet findMtrackFleetById(String mtrackFleetId) {
        return findById(Integer.parseInt(mtrackFleetId));
    }
    
    public int countMtrackFleetsbyCompanyId(String id) {
    	
    	return findByCriteria(Restrictions.eq("company.id", Integer.parseInt(id))).size();    	
    }

    public List getMsisdnWithoutBilling(String month, String year, int companyId){
    	String query = "SELECT C.MSISDN FROM MTRACK_FLEET C WHERE C.COMPANY_ID = ? AND C.FLEET_ID NOT IN (SELECT B.FLEET_ID FROM T_BILLING A"+
    				   " INNER JOIN MTRACK_FLEET B ON A.FLEET_ID = B.FLEET_ID WHERE B.COMPANY_ID = ? AND MONTH(A.BILLING_DATE) = ?" +
    				   " AND YEAR(A.BILLING_DATE) = ?)";    	
    	List list = getCurrentSession().createSQLQuery(query)
    	.setInteger(0, companyId)
    	.setInteger(1, companyId)
    	.setString(2, month)
    	.setString(3, year).list();

    	return list;
    }
}
