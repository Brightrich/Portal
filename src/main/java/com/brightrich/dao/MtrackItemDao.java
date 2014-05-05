package com.brightrich.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.MtrackItem;

public interface MtrackItemDao extends AbstractDao<MtrackItem, Integer>{

	 public void saveMtrackItem(MtrackItem item);

	 public List<MtrackItem> findMtrackItemsbyCompanyId(String companyId);
	    
	    public MtrackItem findMtrackItemById(String id);
	
}
