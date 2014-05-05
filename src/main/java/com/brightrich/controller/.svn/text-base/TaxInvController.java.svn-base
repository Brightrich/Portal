package com.brightrich.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.brightrich.controller.attribute.TaxInvAttribute;
import com.brightrich.controller.attribute.TaxInvData;
import com.brightrich.model.TaxInv;
import com.brightrich.service.TaxInvService;
import com.google.gson.Gson;


@Controller
public class TaxInvController {
	private static final Logger log = Logger.getLogger(TaxInvController.class);
	
	private TaxInvAttribute pageAttr;
	
	@Autowired
    private TaxInvService taxinvservice;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/taxinvno")
	public ModelAndView taxInvPage(ModelAndView mav, HttpServletRequest request, Principal principal){
		pageAttr = new TaxInvAttribute();
		pageAttr.setTitle("Tax Invoice No.");
		pageAttr.setPageId(request.getServletPath().substring(1));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("mTrack/taxinvno");
		return mav;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/queryTaxInvData")
	public @ResponseBody
	String queryTaxInvData(@ModelAttribute("pageAttr") TaxInvAttribute pageAttr, HttpServletRequest request) {
		
		List<TaxInv> attr = taxinvservice.findTaxInvByTaxNo(pageAttr.getTaxno(), MatchMode.ANYWHERE);
		List<TaxInvData> TaxInvDataList = new ArrayList<TaxInvData>();
		for(TaxInv mf : attr){
			TaxInvData data = new TaxInvData();
			data.setTaxno(mf.getTaxno());
			if(null!=mf.getInvid())
			data.setInvid(mf.getInvid().getInvoiceNo());
			data.setAddeddate(mf.getAddeddate());
			data.setAddedby(mf.getAddedby());
			TaxInvDataList.add(data);
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(TaxInvDataList);
		return result;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/insertTaxInvData")
	public
	ModelAndView insertTaxInvData(@ModelAttribute TaxInvAttribute pageAttr, HttpServletRequest request, Principal principal) throws Exception, IOException {
		boolean checktaxno = false;
		ModelAndView mav = new ModelAndView();
		if(null!=pageAttr.getTaxnoadd()&&pageAttr.getTaxnoadd().trim().equals("")){
			pageAttr.setMessage("Tax Invoice Number cannot be empty.");
		}else{
			try{
				List<TaxInv> attr = taxinvservice.findTaxInvByTaxNo(pageAttr.getTaxnoadd(), MatchMode.ANYWHERE);
				if(attr.size()==0){
					checktaxno = true;
				}
				if(checktaxno){
					TaxInv data = new TaxInv();
					data.setTaxno(pageAttr.getTaxnoadd());
					data.setAddedby(principal.getName());
					taxinvservice.saveTaxInv(data);
					pageAttr.setMessage("Tax Invoice Number Successfully Added.");
				}else{
					pageAttr.setMessage("Tax Invoice Number Already Exist.");
				}
			}
			catch(Exception e){
				pageAttr.setMessage("Failed to add. "+e);
			}
		}
		pageAttr.setTitle("Tax Invoice No.");
		pageAttr.setPageId(request.getServletPath().substring(1));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("mTrack/taxinvno");
		return mav;
		
	}
	
}
