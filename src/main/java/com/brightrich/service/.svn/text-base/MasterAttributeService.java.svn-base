package com.brightrich.service;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.MasterAttribute;

public interface MasterAttributeService {
	public void saveMasterAttribute(MasterAttribute attr);

    public List<MasterAttribute> findMasterAttributeByGroup(String groupName, MatchMode mode);
    
    public MasterAttribute findMasterAttributeById(String id);
    
    public HashMap<String, String> selectMasterAttributeBasedOnGroupToMap(String[] arrCriteria);
}
