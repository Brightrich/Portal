package com.brightrich.service.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brightrich.dao.MtrackItemDao;
import com.brightrich.model.MtrackItem;
import com.brightrich.service.MtrackItemService;


@Service("mtrackItemService")
@Transactional(readOnly = true)
public class MtrackItemServiceImpl implements MtrackItemService{

	@Autowired 
	private MtrackItemDao mtrackItemDao;

	@Transactional(readOnly = false)
	public void saveMtrackItem(MtrackItem item) {
		mtrackItemDao.saveMtrackItem(item);
	}

	public List<MtrackItem> findMtrackItemsbyCompanyId(String companyId) {
		return mtrackItemDao.findMtrackItemsbyCompanyId(companyId);
	}

	public MtrackItem findMtrackItemById(String id) {
		return mtrackItemDao.findMtrackItemById(id);
	}


}
