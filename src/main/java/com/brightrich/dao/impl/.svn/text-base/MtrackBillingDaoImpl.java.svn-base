package com.brightrich.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.MtrackBillingDao;
import com.brightrich.dao.expression.CustomSQLExpression;
import com.brightrich.model.MtrackBilling;
import common.ConstantParameter;

@Repository
public class MtrackBillingDaoImpl extends AbstractDaoImpl<MtrackBilling, Integer> implements MtrackBillingDao {
	
    protected MtrackBillingDaoImpl() {
        super(MtrackBilling.class);
    }

    public MtrackBilling saveMtrackBilling(MtrackBilling mtrackBilling) {
        saveOrUpdate(mtrackBilling);
        return mtrackBilling;
    }

    public List<MtrackBilling> findMtrackBillingsbyMSISDN(String msisdn, MatchMode mode) {    	
        return findByCriteria(Restrictions.like("fleetId.msisdn", msisdn, mode));
    }        
    
    public MtrackBilling findMtrackBillingById(String mtrackBillingId) {
        return findById(Integer.parseInt(mtrackBillingId));
    }
    
    @SuppressWarnings("unchecked")
	public List<MtrackBilling> findBillingByCriteria(List<Object[]> criteriaMapper, HashMap<String,String> aliasMap) {
    	//1 Type : (CUSTOM,ORIGINAL), 2 IF Type = CUSTOM (MODIFIER), 3 PROPERTY NAME, 4 VALUE  5 MATCHMODE
    	String type="",modifier="",propertyName="",matchMode="";
    	Object val=null;
    	
    	//Create alias for Nested Object
        Criteria criteria = getCurrentSession().createCriteria(MtrackBilling.class);
        if(aliasMap!=null){
        for (Map.Entry<String, String> alias : aliasMap.entrySet()) {
        	criteria.createAlias(alias.getKey(), alias.getValue());
        }
        }
        
        for(int i=0; i<criteriaMapper.size(); i ++){
        	Object[] objArr = criteriaMapper.get(i);
        	type = (String) objArr[0];
        	modifier = (String) objArr[1];
        	propertyName = (String) objArr[2];
        	val = objArr[3];
        	matchMode = (String) objArr[4];
        	
        	if(type.equals(ConstantParameter.CustomSQLExp.CUSTOM_TYPE)){
        			criteria.add(new CustomSQLExpression(propertyName,val, modifier, matchMode));
        	} else {
        		
        		if(val instanceof Integer){        			
        			criteria.add(Restrictions.eq(propertyName, val));        			
            	} else {
            		if(matchMode.equals(ConstantParameter.CustomSQLExp.MODE_EXACT)){
            			criteria.add(Restrictions.eq(propertyName, val.toString()));
            		} else {
            			criteria.add(Restrictions.like(propertyName, val.toString(), MatchMode.ANYWHERE));
            		}
            	}
        		
        	}
        	
        	
        }
        
                       
        return criteria.list();
    }
    
    public void updateMtrackBillingAfterInvoice(String month, String year, int companyId, int invoiceId){
    	String query = "update T_BILLING a SET a.INVOICE_ID = ? where MONTH(a.BILLING_DATE) = ? and YEAR(a.BILLING_DATE) = ? and a.FLEET_ID IN (select FLEET_ID from MTRACK_FLEET where COMPANY_ID = ?)";
    	
    	//Query qry =
    	getCurrentSession().createSQLQuery(query)
    	.setInteger(0, invoiceId)
    	.setString(1, month)
    	.setString(2, year)
    	.setInteger(3, companyId).executeUpdate();
    	//.executeUpdate();
    	//System.out.println("QUERY = "  + qry.getQueryString());    	
    }
    
    public MtrackBilling findBillingByMSISDNandBillingDate(String msisdn, Date billingDate){
    	Criteria criteria = getCurrentSession().createCriteria(MtrackBilling.class);    	
    	criteria.createAlias("fleetId", "f");
    	criteria.add(Restrictions.eq("f.msisdn", msisdn));
    	criteria.add(Restrictions.eqOrIsNull("billingDate", billingDate));
    	
    	List<MtrackBilling> list = criteria.list(); 
    	if(list.size()>0){
    		return (MtrackBilling)list.get(0);
    	} else {
    		return null;
    	}
    }
    
}
