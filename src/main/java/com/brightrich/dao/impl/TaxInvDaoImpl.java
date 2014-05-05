package com.brightrich.dao.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.TaxInvDao;
import com.brightrich.model.TaxInv;

@Repository
public class TaxInvDaoImpl extends AbstractDaoImpl<TaxInv, Integer> implements TaxInvDao{

	protected TaxInvDaoImpl() {
		super(TaxInv.class);
	}

	public void saveTaxInv(TaxInv attr) {
		// TODO Auto-generated method stub
		saveOrUpdate(attr);
	}

	public List<TaxInv> findTaxInvByTaxNo(String taxno, MatchMode mode) {
		// TODO Auto-generated method stub
		return findByCriteria(Restrictions.like("taxno", taxno, mode));
	}
	
	public void updateTaxInvWithInvId(int invId, String taxNo){
		String query = "update TaxInv t set t.invid.invoiceId = ? where t.taxno = ?";		
		getCurrentSession().createQuery(query).setInteger(0, invId).setString(1, taxNo).executeUpdate();
	}
	
	public String getAvailableTaxInNum(){
		String query = "select min(t.taxno) from TaxInv t where t.invid.invoiceId is null";
		List result = getCurrentSession().createQuery(query).list();
		
		if(result!=null && result.size() > 0){			
			if(null != result.get(0))			
			return result.get(0).toString();
		} 
		
		return null;		
	}
}
