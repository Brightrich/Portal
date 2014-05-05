package com.brightrich.service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.MasterAttributeDao;
import com.brightrich.model.MasterAttribute;
import com.brightrich.service.MasterAttributeService;

@Service("masterAttributeService")
@Transactional(readOnly = true)
public class MasterAttributeServiceImpl implements MasterAttributeService{

	@Autowired
	private MasterAttributeDao masterAttributeDao;
	
	public void saveMasterAttribute(MasterAttribute attr) {
		masterAttributeDao.saveMasterAttribute(attr);
	}

	public List<MasterAttribute> findMasterAttributeByGroup(String groupName,
			MatchMode mode) {
		return masterAttributeDao.findMasterAttributeByGroup(groupName, mode);
	}

	public MasterAttribute findMasterAttributeById(String id) {
		return masterAttributeDao.findMasterAttributeById(id);
	}

	public HashMap<String, String> selectMasterAttributeBasedOnGroupToMap(String[] arrCriteria) {
		// TODO Auto-generated method stub
		return masterAttributeDao.selectMasterAttributeBasedOnGroupToMap(arrCriteria);
	}

}
