package com.brightrich.dao.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.MtrackItemDao;
import com.brightrich.model.MtrackItem;

@Repository
public class MtrackItemDaoImpl extends AbstractDaoImpl<MtrackItem, Integer> implements MtrackItemDao {

    protected MtrackItemDaoImpl() {
        super(MtrackItem.class);
    }

    public void saveMtrackItem(MtrackItem item) {
        saveOrUpdate(item);
    }

    public List<MtrackItem> findMtrackItemsbyCompanyId(String companyId) {    	
        return findByCriteria(Restrictions.eq("companyId.id", Integer.parseInt(companyId)));
    }        
    
    public MtrackItem findMtrackItemById(String id) {
        return findById(Integer.parseInt(id));
    }

}
