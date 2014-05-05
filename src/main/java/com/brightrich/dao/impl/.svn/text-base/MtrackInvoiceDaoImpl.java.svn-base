package com.brightrich.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.MtrackInvoiceDao;
import com.brightrich.dao.expression.CustomSQLExpression;
import com.brightrich.model.MtrackInvoice;
import common.ConstantParameter;

@Repository
public class MtrackInvoiceDaoImpl extends AbstractDaoImpl<MtrackInvoice, Integer> implements MtrackInvoiceDao {

    protected MtrackInvoiceDaoImpl() {
        super(MtrackInvoice.class);
    }

    public MtrackInvoice saveMtrackInvoice(MtrackInvoice invoice) {
        saveOrUpdate(invoice);
        return invoice;
    }

    public List<MtrackInvoice> findMtrackInvoicebyCompanyId(String companyId, MatchMode mode) {    	
        return findByCriteria(Restrictions.like("companyId.id", companyId, mode));
    }
    
    public List<MtrackInvoice> findMtrackInvoicebyInvoiceNo(String invoiceNo, MatchMode mode) {    	
        return findByCriteria(Restrictions.like("invoiceNo", invoiceNo, mode));
    }
    
    public MtrackInvoice findMtrackInvoiceById(String id) {
        return findById(Integer.parseInt(id));
    }
    
    public MtrackInvoice findMtrackInvoiceNumber(String invoiceNo) {
    	List<MtrackInvoice> list = findByCriteria(Restrictions.eq("invoiceNo", invoiceNo));
    	
    	if(list.size() > 0){
    		return list.get(0);
    	}
    	return null;
    	
    }
    
    @SuppressWarnings("unchecked")
	public List<MtrackInvoice> findInvoiceByCriteria(List<Object[]> criteriaMapper, HashMap<String,String> aliasMap) {
    	//1 Type : (CUSTOM,ORIGINAL), 2 IF Type = CUSTOM (MODIFIER), 3 PROPERTY NAME, 4 VALUE  5 MATCHMODE
    	String type="",modifier="",propertyName="",matchMode="";
    	Object val=null;
    	
    	//Create alias for Nested Object
        Criteria criteria = getCurrentSession().createCriteria(MtrackInvoice.class);
        if(aliasMap !=null){
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
    
    
}
