package com.brightrich.service;

import java.util.List;

import org.hibernate.criterion.MatchMode;

import com.brightrich.model.MtrackCompany;

public interface MtrackCompanyService {
	MtrackCompany findMtrackCompanyById(String id);
    void saveMtrackCompany(MtrackCompany company);
    void deleteCompany(String id);
    List<MtrackCompany> findMtrackCompany(String name, MatchMode mode);
}
