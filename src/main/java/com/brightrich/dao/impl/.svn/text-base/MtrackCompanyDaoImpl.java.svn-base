package com.brightrich.dao.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brightrich.dao.MtrackCompanyDao;
import com.brightrich.model.MtrackCompany;

@Repository
public class MtrackCompanyDaoImpl extends AbstractDaoImpl<MtrackCompany, Integer> implements MtrackCompanyDao {

    protected MtrackCompanyDaoImpl() {
        super(MtrackCompany.class);
    }

    public void saveMtrackCompany(MtrackCompany mtrackCompany) {
        saveOrUpdate(mtrackCompany);
    }

    public List<MtrackCompany> findMtrackCompanysbyName(String mtrackCompanyName, MatchMode mode) {    	
        return findByCriteria(Restrictions.like("name", mtrackCompanyName, mode));
    }        
    
    public MtrackCompany findMtrackCompanyById(String mtrackCompanyId) {
        return findById(Integer.parseInt(mtrackCompanyId));
    }

}
