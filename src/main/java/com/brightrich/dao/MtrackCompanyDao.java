package com.brightrich.dao;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.MtrackCompany;

public interface MtrackCompanyDao extends AbstractDao<MtrackCompany, Integer> {
    public void saveMtrackCompany(MtrackCompany mtrackCompany);
    public List<MtrackCompany> findMtrackCompanysbyName(String name, MatchMode mode);
    public MtrackCompany findMtrackCompanyById(String companyId);
}
