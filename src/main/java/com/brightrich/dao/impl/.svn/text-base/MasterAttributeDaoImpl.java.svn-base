package com.brightrich.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.MasterAttributeDao;
import com.brightrich.model.MasterAttribute;


@Repository
public class MasterAttributeDaoImpl extends AbstractDaoImpl<MasterAttribute, Integer> implements MasterAttributeDao{

	protected MasterAttributeDaoImpl() {
        super(MasterAttribute.class);
    }

    public void saveMasterAttribute(MasterAttribute attr) {
        saveOrUpdate(attr);
    }

    public List<MasterAttribute> findMasterAttributeByGroup(String groupName, MatchMode mode) {    	
        return findByCriteria(Restrictions.like("attrGroup", groupName, mode));
    }        
    
    public MasterAttribute findMasterAttributeById(String id) {
        return findById(Integer.parseInt(id));
    }
    
    public HashMap<String, String> selectMasterAttributeBasedOnGroupToMap(String[] arrCriteria){
    	Criteria criteria = getCurrentSession().createCriteria(MasterAttribute.class);
    	Disjunction disjunction = Restrictions.disjunction();
    	for(int i=0; i < arrCriteria.length; i++){
        	disjunction.add(Restrictions.eq("attrGroup", arrCriteria[i]));
        }
    	
    	List<MasterAttribute> attrList = criteria.list();
    	HashMap<String, String> attrMap = new HashMap<String, String>();
    	for(int x=0; x < attrList.size(); x++){
    		MasterAttribute attr = attrList.get(x);
    		attrMap.put(attr.getAttrName(), attr.getAttrValue());    		
    	}
    	
    	
    	return attrMap;
    	
    }
	
}
