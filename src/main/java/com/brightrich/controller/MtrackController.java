package com.brightrich.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.brightrich.controller.attribute.GenerateInvoiceData;
import com.brightrich.controller.attribute.MtrackBillingHistoryData;
import com.brightrich.controller.attribute.MtrackBillingHistoryPageAttribute;
import com.brightrich.controller.attribute.MtrackData;
import com.brightrich.controller.attribute.MtrackInvoiceData;
import com.brightrich.controller.attribute.MtrackInvoicePageAttribute;
import com.brightrich.controller.attribute.MtrackPageAttribute;
import com.brightrich.model.MtrackBilling;
import com.brightrich.model.MtrackCompany;
import com.brightrich.model.MtrackFleet;
import com.brightrich.model.MtrackInvoice;
import com.brightrich.model.MtrackItem;
import com.brightrich.model.User;
import com.brightrich.service.MasterAttributeService;
import com.brightrich.service.MtrackBillingService;
import com.brightrich.service.MtrackCompanyService;
import com.brightrich.service.MtrackFleetService;
import com.brightrich.service.MtrackInvoiceService;
import com.brightrich.service.MtrackItemService;
import com.brightrich.service.TaxInvService;
import com.brightrich.service.UserService;
import com.brightrich.service.impl.TaxInvServiceImpl;
import com.google.gson.Gson;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;

import common.ConstantParameter;
import common.PdfMerger;
import common.PdfTools;
import common.Tools;


@Scope("session")
@Controller
public class MtrackController {	
	
	private static final String QUERY_MTRACK_PAGE = "mTrack/queryMTrack";
	private static final String QUERY_HISTORY_PAGE = "mTrack/queryBillingHistory";
	private static final String QUERY_INVOICE_PAGE = "mTrack/queryInvoice";
	
	private static final String SUCCESS_MESSAGE = "Billing data for customer with MSISDN number : XXX has been successfully saved.";
	private static final String DATA_EXTRACTED = "<u><b>Extracted Data</b></u> </br>Company: XXX <br>Billing Date : YYY <br> Billing Amount : ZZZ";
	private static final String ERROR_MESSAGE = "Failed to save billing data for customer with MSISDN number : XXX, Please contact your system administrator.";
	
	private static final String BATCH_SUCCESS_MESSAGE = "Batch Extraction has been successfully executed:.";
	private static final String BATCH_DATA_EXTRACTED = "<u><b>Extracted Data</b></u> </br>Number of Billing Summary : XXX <br>Number of Call Details : YYY <br>";
	private static final String BATCH_ERROR_MESSAGE = "Batch Extraction Failed for these number : ZZZ <br>Please contact your system administrator for more details.";
	
	private static final String INDEX_BILLING = "INDEX_BILLING";
	private static final String INDEX_CALL = "INDEX_CALL";
	private static final String IS_ERR = "IS_ERR";
	private static final String ERR_NUMBER_LIST = "ERR_NUMBER_LIST";
	
	@Value("${UPLOAD_DIR_INV}")
	private String UPLOAD_DIR_INV;
	
	@Value("${MTRACK_PDF_TSEL_PSWD}")
	private String MTRACK_PDF_TSEL_PSWD;
	
	@Value("${MTRACK_PDF_TSEL_ALTERNATE_PSWD}")
	private String MTRACK_PDF_TSEL_ALTERNATE_PSWD;
	
	StringBuilder sb = new StringBuilder(1024);
	
	private static final Logger log = Logger.getLogger(MtrackController.class);
	
	@Autowired
    private MtrackFleetService mtrackFleetService;
	
	@Autowired
    private MtrackBillingService mtrackBillingService;
	
	@Autowired
    private MtrackCompanyService mtrackCompanyService;
	
	@Autowired
	private MtrackInvoiceService mtrackInvoiceService;
	
	@Autowired
	private MasterAttributeService masterAttributeService;
	
	@Autowired
	private MtrackItemService mtrackItemService;
	
	@Autowired
	private TaxInvService taxInvService;
	
	private MtrackPageAttribute savedAttr;
	private MtrackBillingHistoryPageAttribute savedHistAttr;
	private MtrackInvoicePageAttribute savedInvAttr;
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/queryMTrack")
	public ModelAndView queryMTrack(ModelAndView mav, HttpServletRequest request){
		
		System.out.println("FROM PROPS FILE = " + UPLOAD_DIR_INV);
		
		mav.setViewName(QUERY_MTRACK_PAGE);
		savedAttr = new MtrackPageAttribute();
		savedAttr.setTitle("MTrack Project Tracking");
		savedAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));						
		mav.addObject("pageAttr", savedAttr);
		return mav;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/queryMTrackData")
	public @ResponseBody
	String queryMTrackData(@ModelAttribute("pageAttr") MtrackPageAttribute pageAttr, HttpServletRequest request) {
		
		System.out.println("Searching Fleet by MSISDN = " + pageAttr.getMsisdnSearch());
		List<MtrackFleet> fleetList = mtrackFleetService.findMtrackFleetsbyMSISDN(pageAttr.getMsisdnSearch(), MatchMode.ANYWHERE);
		List<MtrackData> fleetDataList = new ArrayList<MtrackData>();
		for(MtrackFleet mf : fleetList){
			MtrackData data = new MtrackData();
			data.setCompany(mf.getCompany().getName());
			data.setFleetId(mf.getId());
			data.setMsisdn(mf.getMsisdn());
			data.setDoorNumber(mf.getDoorNumber());
			fleetDataList.add(data);
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(fleetDataList);
		
		System.out.println("RESULT = " + result);
		 
		return result;
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/insertMtrackBilling")
	public
	ModelAndView insertMTrackBilling(@ModelAttribute("pageAttr") MtrackPageAttribute pageAttr, HttpServletRequest request, Principal principal) throws Exception, IOException {
		
		ModelAndView mav = new ModelAndView();
		savedAttr = new MtrackPageAttribute();
		String page="";
		int index = 0;
		System.out.println("MASUKKKK");
		String billSummaryPath = "", callDetailPath = "", billSummaryFileName="", callDetailFileName=""; 
		
		if (pageAttr.getFiles() != null && pageAttr.getFiles().length > 0) {
			for (CommonsMultipartFile aFile : pageAttr.getFiles()){
				
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				System.out.println("Path " + ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_ORI);
				String dirOri = ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_ORI;
				if (!aFile.getOriginalFilename().equals("")) {
					aFile.transferTo(new File(dirOri +"/"+ aFile.getOriginalFilename()));
					
					if(aFile.getOriginalFilename().contains(ConstantParameter.MtrackFileUploadPath.BILLING_SUMMARY_PREFIX) || index == 0){
						//String dirBill = request.getSession().getServletContext().getRealPath(ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL);
						String dirBill = ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL;
						billSummaryPath = dirBill + aFile.getOriginalFilename();
						page = PdfTools.encryptPdf(dirOri +"/"+ aFile.getOriginalFilename(), billSummaryPath);
						billSummaryFileName =aFile.getOriginalFilename(); 
						index = 1;
					} else if(aFile.getOriginalFilename().contains(ConstantParameter.MtrackFileUploadPath.CALL_DETAIL_PREFIX) || index > 0){
						//String dirCall = request.getSession().getServletContext().getRealPath(ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_CALL);
						String dirCall = ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_CALL;
						callDetailPath = dirCall + aFile.getOriginalFilename();
						PdfTools.encryptPdf(dirOri +"/"+ aFile.getOriginalFilename(), callDetailPath);
						callDetailFileName = aFile.getOriginalFilename();
					}
				}
			}
		}
		
		
		String message="";
		MtrackBilling billing = null;
		
		try{
			HashMap<String, String> dataExtract = PdfTools.extractData(page);
			Date billingDate = Tools.ddmmmyyyy.parse(dataExtract.get(ConstantParameter.MtrackFileUploadPath.DATE_VALUE));
			MtrackFleet fleet = mtrackFleetService.findMtrackFleetById(pageAttr.getFleetIdSelectedVal()); 
			
			MtrackBilling existedBilling = mtrackBillingService.findBillingByMSISDNandBillingDate(fleet.getMsisdn(), billingDate);
									
			if(existedBilling != null){
				billing = existedBilling;
			} else {
				billing = new MtrackBilling();
			}
			
			billing.setBillSummaryPath(billSummaryFileName);
			billing.setCallDetailPath(callDetailFileName);
			billing.setCreatedDate(new Date());
			billing.setCreateddBy(principal.getName());		
			billing.setInvoiceId(null);
			System.out.println("FLEET ID = " + pageAttr.getFleetIdSelectedVal());
			billing.setFleetId(fleet);
			
			//Auto extraction						
			billing.setBillingDate(billingDate);
			billing.setTotalBilling(new BigDecimal(dataExtract.get(ConstantParameter.MtrackFileUploadPath.AMOUNT_VALUE)));
			
			
			billing = mtrackBillingService.saveMtrackBilling(billing);
			
			message = SUCCESS_MESSAGE.replace("XXX", billing.getFleetId().getMsisdn());
			String extractData = DATA_EXTRACTED.replace("XXX", billing.getFleetId().getCompany().getName()).replace("YYY",Tools.monddyyyy.format(billing.getBillingDate()))
					.replace("ZZZ", billing.getTotalBilling().toString());
			message = message + "<br>" + extractData; 
			
			
	        
	        sb.append(billing.toStringMulti("Action", "Create new Billing Succeed", Tools.sdfLog.format(new Date())));
	        log.info(sb.toString());
		} catch (Exception e){
			e.printStackTrace();
			message = ERROR_MESSAGE.replace("XXX", billing.getFleetId().getMsisdn());
			sb.append(billing.toStringMulti("Action", "Create new Billing Failed", Tools.sdfLog.format(new Date()))).append("\n").append("ERROR MESSAGE = " + e.getMessage());
			log.error(sb.toString());
		}
		
		
		
		savedAttr.setMessage(message);
		mav.setViewName(QUERY_MTRACK_PAGE);
		savedAttr.setTitle("MTrack Project Tracking");
		savedAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));						
		mav.addObject("pageAttr", savedAttr);
		return mav;
		 
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/queryBillingHistory")
	public ModelAndView queryBillingHistory(ModelAndView mav, HttpServletRequest request){
		mav.setViewName(QUERY_HISTORY_PAGE);
		savedHistAttr = new MtrackBillingHistoryPageAttribute();
		savedHistAttr.setCompanyList(mtrackCompanyService.findMtrackCompany("", MatchMode.ANYWHERE));		
		savedHistAttr.setTitle("MTrack Billing History");
		savedHistAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));						
		mav.addObject("pageAttr", savedHistAttr);
		return mav;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/queryMTrackHistory")
	public @ResponseBody 
	String queryBillingHistory(@ModelAttribute("pageAttr") MtrackBillingHistoryPageAttribute pageAttr, HttpServletRequest request){
		
		boolean eligibleForInvoice=true;
		
		savedHistAttr.setBillingMonth(pageAttr.getBillingMonth());
		savedHistAttr.setBillingYear(pageAttr.getBillingYear());
		savedHistAttr.setCompanySelected(pageAttr.getCompanySelected());
		
		
		HashMap<String,String> mapAlias = new HashMap<String, String>();
		mapAlias.put("fleetId", "f");
		//mapAlias.put("f.company", "c");
		List<Object[]> criteriaMapper = new ArrayList<Object[]>();//1 Type : (CUSTOM,ORIGINAL), 2 IF Type = CUSTOM (MODIFIER), 3 PROPERTY NAME, 4 VALUE  5 MATCHMODE		
		
		if(pageAttr.getMsisdnSearch()!=null && !pageAttr.getMsisdnSearch().equals("")){			
			//mapCriteria.put("f.msisdn", pageAttr.getMsisdnSearch());
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.ORIGINAL_TYPE;
			objArr[1] = "";
			objArr[2] = "f.msisdn";
			objArr[3] = pageAttr.getMsisdnSearch();
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_LIKE;
			eligibleForInvoice = false;
			criteriaMapper.add(objArr);
		}
		
		if(pageAttr.getBillingMonth()!=null && !pageAttr.getBillingMonth().equals("")){
			//mapCriteria.put("MONTH(billingDate)", pageAttr.getBillingMonth());
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.CUSTOM_TYPE;
			objArr[1] = "MONTH";
			objArr[2] = "billingDate";
			objArr[3] = pageAttr.getBillingMonth();
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_EXACT;
			criteriaMapper.add(objArr);
		} else {
			eligibleForInvoice = false;
		}
		
		if(pageAttr.getBillingYear()!=null && !pageAttr.getBillingYear().equals("")){
			//mapCriteria.put("YEAR(billingDate)", pageAttr.getBillingYear());
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.CUSTOM_TYPE;
			objArr[1] = "YEAR";
			objArr[2] = "billingDate";
			objArr[3] = pageAttr.getBillingYear();
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_EXACT;
			criteriaMapper.add(objArr);
		} else {
			eligibleForInvoice = false;
		}
		
		if(pageAttr.getCompanySelected()!=null && !pageAttr.getCompanySelected().equals("")){
			//mapCriteria.put("f.company.id", Integer.parseInt(pageAttr.getCompanySelected()));
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.ORIGINAL_TYPE;
			objArr[1] = "";
			objArr[2] = "f.company.id";
			objArr[3] = Integer.parseInt(pageAttr.getCompanySelected());
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_EXACT;
			criteriaMapper.add(objArr);
		} else {
			eligibleForInvoice = false;
		}
		
		
		//System.out.println("SIZE array = " + criteriaMapper.length);
		List<MtrackBilling> billingList = mtrackBillingService.findBillingByCriteria(criteriaMapper, mapAlias);
		List<MtrackBillingHistoryData> billingDataList = new ArrayList<MtrackBillingHistoryData>();
		List<String> billSummaryList = new ArrayList<String>();
		List<String> callDetailList = new ArrayList<String>();
		
		int fleetNum=-1;
		
		if(billingList.size()>0)
		savedHistAttr.setCompanySelVal(billingList.get(0).getFleetId().getCompany());
		
		if(eligibleForInvoice){
			
			List listUnbilled = mtrackFleetService.getMsisdnWithoutBilling(pageAttr.getBillingMonth(), pageAttr.getBillingYear(), Integer.parseInt(pageAttr.getCompanySelected()));
			for(int i =0; i < listUnbilled.size(); i++){
				System.out.println("NUMBER = " + listUnbilled.get(i));
			}
			//fleetNum = mtrackFleetService.countMtrackFleetsbyCompanyId(pageAttr.getCompanySelected());
			//fleetNum = (fleetNum == billingList.size()) ? 0 : -1;
			savedHistAttr.setIncompleteBillingStatement(listUnbilled);
			fleetNum = (listUnbilled.size() == 0) ? 0 : -1;
		}
		
		for(MtrackBilling mb : billingList){
			MtrackBillingHistoryData data = new MtrackBillingHistoryData();
			data.setBillingDate(Tools.dateToStr.format(mb.getBillingDate()));
			data.setBillSummaryPath(mb.getBillSummaryPath());
			billSummaryList.add(ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL+mb.getBillSummaryPath());
			
			//The call details might not be required
			if(mb.getCallDetailPath() != null && !mb.getCallDetailPath().equals("")){
			callDetailList.add(ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_CALL+mb.getCallDetailPath());			
			data.setCallDetailPath(mb.getCallDetailPath());
			} else {
				data.setCallDetailPath("N/A");
			}
			
			data.setMsisdn(mb.getFleetId().getMsisdn());
			data.setCompanyName(mb.getFleetId().getCompany().getName());
			data.setDoorNumber(mb.getFleetId().getDoorNumber() != null ? mb.getFleetId().getDoorNumber() : "N/A");
			//System.out.println("ANGKA YANG MAU DI FORMAT = " + mb.getTotalBilling().toString());
			data.setTotalBilling(Tools.formatrupiah(mb.getTotalBilling().toString()));
			
			if(mb.getInvoiceId() != null && (mb.getInvoiceId().getInvoiceId() > 0)){
				data.setInvoiceNo(mb.getInvoiceId().getInvoiceNo());
				data.setInvoiceId(mb.getInvoiceId().getInvoiceId().toString());
				data.setInvoiceFilePath(mb.getInvoiceId().getInvoiceFilePath());
				data.setInvoiceDate(Tools.strToDate.format(mb.getInvoiceId().getInvoiceDate()));
				data.setTaxNum(mb.getInvoiceId().getTaxInvNumber());
			} else {
				data.setInvoiceNo("null");
				data.setInvoiceId("null");
				data.setInvoiceFilePath("null");
				data.setInvoiceDate("null");
				data.setTaxNum("null");
			}
			data.setFleetCount(String.valueOf(fleetNum));
			billingDataList.add(data);
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(billingDataList);
		savedHistAttr.setBillSummaryFileList(billSummaryList);
		savedHistAttr.setCallDetailFileList(callDetailList);
		savedHistAttr.setBillingList(billingList);
		System.out.println("RESULT = " + result);
			
		return result;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/queryMTrackHistoryUnbilled")
	public @ResponseBody 
	String queryBillingHistoryUnbilled(@ModelAttribute("pageAttr") MtrackBillingHistoryPageAttribute pageAttr, HttpServletRequest request){
		
		
		System.out.println("GET UNBILLED");
		List listUnbilled = savedHistAttr.getIncompleteBillingStatement();
		
		Gson gson = new Gson();
		String result = gson.toJson(listUnbilled);
		System.out.println("RESULT = " + result);
			
		return result;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/mTrack/openpdf", method = RequestMethod.GET)
    public void handleFileDownload(HttpServletRequest req, HttpServletResponse res) {
        try {        	
            String fn = req.getParameter("fn");
            String ftype = req.getParameter("ftype");
            PdfTools.openPdf(fn, res, ftype);
            /*String path = "";
            
            if(fn!=null && fn.startsWith(ConstantParameter.MtrackFileUploadPath.BILLING_SUMMARY_PREFIX)){
            	path = ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL+fn;
            	System.out.println("Path = " + path);
            } else {
            	path = ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_CALL+fn;
            	System.out.println("Path = " + path);
            }
            
                        
            File f = new File(path);
            System.out.println("Loading file "+path);
            if (f.exists()) {
                res.setContentType("application/pdf");
                res.setContentLength(new Long(f.length()).intValue());
                //res.setHeader("Content-Disposition", "attachment; filename="+fn);
                FileCopyUtils.copy(new FileInputStream(f), res.getOutputStream());
            } else {
                System.out.println("File "+fn+"("+f.getAbsolutePath()+") does not exist");
            }*/
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
            e.printStackTrace();
        }
    }
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/generateInvoiceData")
	public @ResponseBody 
	String generateInvoiceData(@ModelAttribute("pageAttr") MtrackBillingHistoryPageAttribute pageAttr, HttpServletRequest request, Principal principal, HttpServletResponse res) throws Exception{
		
		String result="";
		Gson gson = new Gson();
		
		BigDecimal totalInvoice = new BigDecimal("0");
				
		MtrackInvoice invoice = null;
		GenerateInvoiceData data = new GenerateInvoiceData();
		
		invoice = mtrackInvoiceService.findMtrackInvoiceNumber(pageAttr.getInvoiceNumberVal());
		if(invoice==null){
			invoice = new MtrackInvoice();			
			String taxInvNum = taxInvService.getAvailableTaxInNum();
			invoice.setTaxInvNumber(taxInvNum);						
		}
		
		if(invoice.getTaxInvNumber() != null){
		
		List<MtrackBilling> billingList = savedHistAttr.getBillingList();
		for(MtrackBilling bill : billingList){
			totalInvoice = totalInvoice.add(bill.getTotalBilling());
		}
		
		
		invoice.setTotalInvoice(totalInvoice);
		invoice.setCompanyId(savedHistAttr.getCompanySelVal());
		invoice.setInvoiceDate(Tools.strToDate.parse(pageAttr.getInvoiceDateVal()));
		invoice.setInvoiceNo(pageAttr.getInvoiceNumberVal());
		invoice.setCreatedBy(principal.getName());
		invoice.setCreatedDate(new Date());
		
				
		
		MtrackItem item = mtrackItemService.findMtrackItemsbyCompanyId(invoice.getCompanyId().getId().toString()).get(0);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
    	String[] attr = new String[2];
    	attr[0]=ConstantParameter.MtrackInvoiceAttr.BR_PROFILE;
    	attr[1]=ConstantParameter.MtrackInvoiceAttr.MTRACK_PROFILE;
    	HashMap<String,String> attrMap = masterAttributeService.selectMasterAttributeBasedOnGroupToMap(attr);
    	
    	
    	//Count total price
    	BigDecimal basePrice = item.getMtrackFee().multiply(new BigDecimal(billingList.size()));
    	System.out.println("BASE PRICE COUNT = " + basePrice);
    	BigDecimal subtotal = invoice.getTotalInvoice().add(basePrice);
    	BigDecimal vat = (subtotal.multiply(new BigDecimal("10"))).divide(new BigDecimal("100"));
    	BigDecimal total = subtotal.add(vat);
		
		invoice.setTotalPrice(total);
    	
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.SUBTOTAL, subtotal.toString());
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.VAT, vat.toString());
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.TOTAL, total.toString());
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.NUM_OF_UNIT, String.valueOf(billingList.size()));
    	attrMap.put(ConstantParameter.MtrackInvoiceAttr.MTRACK_BASE_PRICE, basePrice.toString());
		
		map.put(ConstantParameter.MtrackInvoiceAttr.INVOICE, invoice);
    	map.put(ConstantParameter.MtrackInvoiceAttr.ATTRIBUTE, attrMap);
    	map.put(ConstantParameter.MtrackInvoiceAttr.ITEM, item);
    	
    	
    	String fn = PdfTools.createPdfDoc(map);
    	invoice.setInvoiceFilePath(fn);
		
    	    	
    	try{
    		invoice = mtrackInvoiceService.saveMtrackInvoice(invoice);
			
    		//Update TaxInv
    		taxInvService.updateTaxInvWithInvId(invoice.getInvoiceId(), invoice.getTaxInvNumber());
    		
    		//Update MtrackBilling
    		mtrackBillingService.updateMtrackBillingAfterInvoice(savedHistAttr.getBillingMonth(), savedHistAttr.getBillingYear(), invoice.getCompanyId().getId(), invoice.getInvoiceId());
		
		    sb.append(invoice.toStringMulti("Action", "Create new Invoice Succeed", Tools.sdfLog.format(new Date()))).append("\n");
		    log.info(sb.toString());
		    
		    data.setState("SUCCESS");
		    data.setInvfileName(invoice.getInvoiceFilePath());
		    data.setInvoiceDate(Tools.dateToStr.format(invoice.getInvoiceDate()));
		    data.setInvoiceNum(invoice.getInvoiceNo());
		    data.setTaxNum(invoice.getTaxInvNumber());
		    
    	} catch(Exception e){
    		    		
    		e.printStackTrace();
    		sb.append(invoice.toStringMulti("Action", "Create new Invoice Failed", Tools.sdfLog.format(new Date()))).append("\n").append("ERROR MESSAGE = " + e.getMessage());
    		log.error(sb.toString());
    		
    		data.setState("ERROR");
		    data.setErrorMsg(e.getMessage());
    	}
    	
		} else {
			data.setErrorMsg("No Tax Invoice Number available. Please insert new Tax Invoice Number first.");
			data.setState("ERROR");
			
		}
		//res = PdfTools.openPdf(fn, res, "invoice");
		result = gson.toJson(data);		
		return result;
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/generateInvoice")
	public void generateInvoice(@ModelAttribute("pageAttr") MtrackBillingHistoryPageAttribute pageAttr, HttpServletRequest request, Principal principal, HttpServletResponse res) throws Exception{
		
		System.out.println("Invoice Number : " + pageAttr.getInvoiceNumberVal());
		System.out.println("Invoice Date : " + pageAttr.getInvoiceDateVal());
		
		BigDecimal totalInvoice = new BigDecimal("0");
				
		MtrackInvoice invoice = null;
		
		invoice = mtrackInvoiceService.findMtrackInvoiceNumber(pageAttr.getInvoiceNumberVal());
		if(invoice==null){
			invoice = new MtrackInvoice();
			
			String taxInvNum = taxInvService.getAvailableTaxInNum();
			//SET INV TO TAX_INV_NUM
			invoice.setTaxInvNumber(taxInvNum);						
		}
		
		List<MtrackBilling> billingList = savedHistAttr.getBillingList();
		for(MtrackBilling bill : billingList){
			totalInvoice = totalInvoice.add(bill.getTotalBilling());
		}
		
		
		invoice.setTotalInvoice(totalInvoice);
		invoice.setCompanyId(savedHistAttr.getCompanySelVal());
		invoice.setInvoiceDate(Tools.strToDate.parse(pageAttr.getInvoiceDateVal()));
		invoice.setInvoiceNo(pageAttr.getInvoiceNumberVal());
		invoice.setCreatedBy(principal.getName());
		invoice.setCreatedDate(new Date());
		
				
		
		MtrackItem item = mtrackItemService.findMtrackItemsbyCompanyId(invoice.getCompanyId().getId().toString()).get(0);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
    	String[] attr = new String[2];
    	attr[0]=ConstantParameter.MtrackInvoiceAttr.BR_PROFILE;
    	attr[1]=ConstantParameter.MtrackInvoiceAttr.MTRACK_PROFILE;
    	HashMap<String,String> attrMap = masterAttributeService.selectMasterAttributeBasedOnGroupToMap(attr);
    	
    	
    	//Count total price
    	BigDecimal basePrice = item.getMtrackFee().multiply(new BigDecimal(billingList.size()));
    	System.out.println("BASE PRICE COUNT = " + basePrice);
    	BigDecimal subtotal = invoice.getTotalInvoice().add(basePrice);
    	BigDecimal vat = (subtotal.multiply(new BigDecimal("10"))).divide(new BigDecimal("100"));
    	BigDecimal total = subtotal.add(vat);
		
		invoice.setTotalPrice(total);
    	
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.SUBTOTAL, subtotal.toString());
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.VAT, vat.toString());
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.TOTAL, total.toString());
		attrMap.put(ConstantParameter.MtrackInvoiceAttr.NUM_OF_UNIT, String.valueOf(billingList.size()));
    	attrMap.put(ConstantParameter.MtrackInvoiceAttr.MTRACK_BASE_PRICE, basePrice.toString());
		
		map.put(ConstantParameter.MtrackInvoiceAttr.INVOICE, invoice);
    	map.put(ConstantParameter.MtrackInvoiceAttr.ATTRIBUTE, attrMap);
    	map.put(ConstantParameter.MtrackInvoiceAttr.ITEM, item);
    	
    	
    	String fn = PdfTools.createPdfDoc(map);
    	invoice.setInvoiceFilePath(fn);
		
    	try{
    		invoice = mtrackInvoiceService.saveMtrackInvoice(invoice);
			
    		//Update TaxInv
    		taxInvService.updateTaxInvWithInvId(invoice.getInvoiceId(), invoice.getTaxInvNumber());
    		
    		//Update MtrackBilling
    		mtrackBillingService.updateMtrackBillingAfterInvoice(savedHistAttr.getBillingMonth(), savedHistAttr.getBillingYear(), invoice.getCompanyId().getId(), invoice.getInvoiceId());
		
		    sb.append(invoice.toStringMulti("Action", "Create new Invoice Succeed", Tools.sdfLog.format(new Date()))).append("\n");
		    log.info(sb.toString());
    	} catch(Exception e){
    		    		
    		e.printStackTrace();
    		sb.append(invoice.toStringMulti("Action", "Create new Invoice Failed", Tools.sdfLog.format(new Date()))).append("\n").append("ERROR MESSAGE = " + e.getMessage());
    		log.error(sb.toString());
    	}
    	
		res = PdfTools.openPdf(fn, res, "invoice");
    	
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping("mTrack/queryInvoiceHistory")
	public ModelAndView queryInvoiceHistory(ModelAndView mav, HttpServletRequest request){
		mav.setViewName(QUERY_INVOICE_PAGE);
		savedInvAttr = new MtrackInvoicePageAttribute();
		savedInvAttr.setCompanyList(mtrackCompanyService.findMtrackCompany("", MatchMode.ANYWHERE));		
		savedInvAttr.setTitle("MTrack Invoice History");
		savedInvAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));						
		mav.addObject("pageAttr", savedInvAttr);
		return mav;
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/queryInvoice")
	public @ResponseBody 
	String queryInvoice(@ModelAttribute("pageAttr") MtrackInvoicePageAttribute pageAttr, HttpServletRequest request) {
		
		savedInvAttr.setBillingMonth(pageAttr.getBillingMonth());
		savedInvAttr.setBillingYear(pageAttr.getBillingYear());
		savedInvAttr.setCompanySelected(pageAttr.getCompanySelected());
				
		List<Object[]> criteriaMapper = new ArrayList<Object[]>();//1 Type : (CUSTOM,ORIGINAL), 2 IF Type = CUSTOM (MODIFIER), 3 PROPERTY NAME, 4 VALUE  5 MATCHMODE		
		
		if(pageAttr.getInvoiceSearch()!=null && !pageAttr.getInvoiceSearch().equals("")){						
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.ORIGINAL_TYPE;
			objArr[1] = "";
			objArr[2] = "invoiceNo";
			objArr[3] = pageAttr.getInvoiceSearch();
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_LIKE;			
			criteriaMapper.add(objArr);
		}
		
		if(pageAttr.getBillingMonth()!=null && !pageAttr.getBillingMonth().equals("")){			
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.CUSTOM_TYPE;
			objArr[1] = "MONTH";
			objArr[2] = "invoiceDate";
			objArr[3] = pageAttr.getBillingMonth();
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_EXACT;
			criteriaMapper.add(objArr);
		}
		
		if(pageAttr.getBillingYear()!=null && !pageAttr.getBillingYear().equals("")){			
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.CUSTOM_TYPE;
			objArr[1] = "YEAR";
			objArr[2] = "invoiceDate";
			objArr[3] = pageAttr.getBillingYear();
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_EXACT;
			criteriaMapper.add(objArr);
		} 
		
		if(pageAttr.getCompanySelected()!=null && !pageAttr.getCompanySelected().equals("")){			
			Object[] objArr = new Object[5];
			objArr[0] = ConstantParameter.CustomSQLExp.ORIGINAL_TYPE;
			objArr[1] = "";
			objArr[2] = "companyId.id";
			objArr[3] = Integer.parseInt(pageAttr.getCompanySelected());
			objArr[4] = ConstantParameter.CustomSQLExp.MODE_EXACT;
			criteriaMapper.add(objArr);
		} 
		
		System.out.println("Searching Invoice by Number = " + pageAttr.getInvoiceSearch());
		List<MtrackInvoice> list = mtrackInvoiceService.findInvoiceByCriteria(criteriaMapper, null);
		
		List<MtrackInvoiceData> invoiceDataList = new ArrayList<MtrackInvoiceData>();
		for(MtrackInvoice mi : list){
			MtrackInvoiceData data = new MtrackInvoiceData();
			data.setCompany(mi.getCompanyId().getName());
			data.setInvoiceDate(Tools.dateToStr.format(mi.getInvoiceDate()));
			data.setInvoiceFilePath(mi.getInvoiceFilePath());
			data.setInvoiceId(mi.getInvoiceId().toString());
			data.setInvoiceNo(mi.getInvoiceNo());
			data.setTotalInvoice(Tools.formatrupiah(mi.getTotalPrice().toString()));
			data.setInvoiceTaxNum(mi.getTaxInvNumber());
			invoiceDataList.add(data);
		}
		
		Gson gson = new Gson();
		String result = gson.toJson(invoiceDataList);
		
		System.out.println("RESULT = " + result);
		 
		return result;
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/getcompiledpdf")
	public ResponseEntity<byte[]> getCompiledPdf(@ModelAttribute("pageAttr") MtrackInvoicePageAttribute pageAttr, HttpServletRequest request) throws DocumentException, IOException {
		
		List<String> listOfFiles = new ArrayList<String>(); 
		
		if(request.getParameter("ftype").equals("billSum")){		
			listOfFiles = savedHistAttr.getBillSummaryFileList();
		}else {
			listOfFiles = savedHistAttr.getCallDetailFileList();		
		}		
				
		byte[] contents = PdfTools.concatPDFFiles(listOfFiles, "");		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename = "output.pdf";
	    //headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	    return response;
		
		
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/getcompressedfiles")
	public ResponseEntity<byte[]> getCompressedFiles(@ModelAttribute("pageAttr") MtrackInvoicePageAttribute pageAttr, HttpServletRequest request) throws DocumentException, IOException {
		
		List<String> listOfFiles = new ArrayList<String>(); 
		
		String invId = request.getParameter("invId");		
	    List<Object[]> objList = new ArrayList<Object[]>();
	    
	    Object[] objArr = new Object[5];	    
		objArr[0] = ConstantParameter.CustomSQLExp.ORIGINAL_TYPE;
		objArr[1] = "";
		objArr[2] = "invoiceId.invoiceId";
		objArr[3] = Integer.parseInt(invId);
		objArr[4] = ConstantParameter.CustomSQLExp.MODE_EXACT;			
		objList.add(objArr);
	    	    	   
		List<MtrackBilling> billingList = mtrackBillingService.findBillingByCriteria(objList, null);
		MtrackInvoice inv = billingList.get(0).getInvoiceId();
		HashMap<String, String> mapFiles = new HashMap<String, String>();
		mapFiles.put(inv.getInvoiceFilePath(), ConstantParameter.MtrackInvoiceAttr.UPLOAD_DIR_INV+billingList.get(0).getInvoiceId().getInvoiceFilePath());
		
		for(MtrackBilling mb : billingList){
			mapFiles.put(mb.getBillSummaryPath(), ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL+mb.getBillSummaryPath());
			mapFiles.put(mb.getCallDetailPath(), ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_CALL+mb.getCallDetailPath());
		}
		
		
		byte[] contents = Tools.zipFiles(mapFiles);	
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/zip"));
	    String filename = inv.getCompanyId().getName()+"-"+inv.getInvoiceNo()+".zip";
	    headers.setContentDispositionFormData(filename, filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	    return response;
		
		
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value="mTrack/batchMtrackBilling")
	public
	ModelAndView batchMTrackBilling(@ModelAttribute("pageAttr") MtrackPageAttribute pageAttr, HttpServletRequest request, Principal principal) throws Exception, IOException {
		
		
		
		ModelAndView mav = new ModelAndView();
		savedAttr = new MtrackPageAttribute();
		String upDir = "";
		
		System.out.println("===START=== AT ==== " + new Date());
		boolean isErr = false;
		List<String> numList = new ArrayList<String>();		
		int indexBill = 0,indexCall = 0;
		
		if (pageAttr.getBatchFile() != null) {
				
			for (CommonsMultipartFile aFile : pageAttr.getBatchFile()){
				//CommonsMultipartFile aFile = pageAttr.getBatchFile();
				
				System.out.println("Saving file: " + aFile.getOriginalFilename());
				System.out.println("Path " + ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_ORI);
				
				/*String dirOri = ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_ORI;
				
				
				if (!aFile.getOriginalFilename().equals("")) {
					upDir = dirOri +"/"+ aFile.getOriginalFilename();
					aFile.transferTo(new File(upDir));									
				}*/
				
				PdfReader reader = null;
				reader = PdfTools.createPdfReaderFromBytes(aFile.getBytes(), MTRACK_PDF_TSEL_PSWD, MTRACK_PDF_TSEL_ALTERNATE_PSWD);	
				HashMap<String,HashMap<String,String>> map = PdfTools.mtrackBatchPdfProcess(reader, ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL, ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_CALL);				
									
				pageAttr = batchAutoInsert(map, principal.getName(), pageAttr);
				
				numList.addAll(pageAttr.getNumList());
				if(!isErr){
					isErr = pageAttr.isAutoInsertErr();
				}
				
				indexBill = indexBill + pageAttr.getIndexBill();
				indexCall = indexCall + pageAttr.getIndexCall();
				
			}
		}							
				
		String message = BATCH_SUCCESS_MESSAGE;
		String extractData = BATCH_DATA_EXTRACTED.replace("XXX", String.valueOf(indexBill)).replace("YYY",String.valueOf(indexCall));
		message = message + "<br>" + extractData; 
		if(isErr){
			String errNumber = "";
						
			for(int i=0; i<numList.size();i++){
				errNumber = errNumber + "<br>"+numList.get(i);
			}
			errNumber = errNumber + "<br>";
			message = message +"<br>"+BATCH_ERROR_MESSAGE.replace("ZZZ", errNumber);
		}
		
		savedAttr.setMessage(message);
		mav.setViewName(QUERY_MTRACK_PAGE);
		savedAttr.setTitle("MTrack Project Tracking");
		savedAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));						
		mav.addObject("pageAttr", savedAttr);
		
		System.out.println("===END=== AT ==== " + new Date());
		
		return mav;
		 
		
	}
	
	@RequestMapping(value="mTrack/uploadDD", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response, Principal principal) {
 
		 String result = "OK";
		 //1. build an iterator
		 Iterator<String> itr =  request.getFileNames();
		 MultipartFile mpf = null;

		 //2. get each file
		 while(itr.hasNext()){
			 
			 //2.1 get next MultipartFile
			 mpf = request.getFile(itr.next()); 
				 
			 try {							
				
				PdfReader reader = null;
				reader = PdfTools.createPdfReaderFromBytes(mpf.getBytes(), MTRACK_PDF_TSEL_PSWD, MTRACK_PDF_TSEL_ALTERNATE_PSWD);	
				HashMap<String,HashMap<String,String>> map = PdfTools.mtrackBatchPdfProcess(reader, ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_BILL, ConstantParameter.MtrackFileUploadPath.UPLOAD_DIR_CALL);				
				MtrackPageAttribute attr = new MtrackPageAttribute();
				List<String> numList = savedAttr.getNumList();
				
				attr = batchAutoInsert(map, principal.getName(),attr); 
				
				savedAttr.setIndexBill(savedAttr.getIndexBill() + attr.getIndexBill());
				savedAttr.setIndexCall(savedAttr.getIndexCall() + attr.getIndexCall());							
				
				if(!savedAttr.isAutoInsertErr()){
					savedAttr.setAutoInsertErr(attr.isAutoInsertErr());
				}
				
				//Apply error number to session list
				if(numList==null || numList.size() < 1){
					savedAttr.setNumList(attr.getNumList());
				} else {
					
					for(int x=0; x < attr.getNumList().size(); x++){
						numList.add(attr.getNumList().get(x));
					}					
					
					savedAttr.setNumList(numList);
				}
				
				if(attr.isAutoInsertErr()){
					result = "ERROR";
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				result = "ERROR";
			}
			 			 
		 }
		 
		return result;
 
	}
	
	public MtrackPageAttribute batchAutoInsert(HashMap<String,HashMap<String,String>> map, String username, MtrackPageAttribute attr){
		
		boolean isErr = false;
		List<String> numList = new ArrayList<String>();
		MtrackBilling billing = null;
		int indexBill = 0,indexCall = 0;
		
		//Auto insert
		for (Map.Entry<String, HashMap<String,String>> mapData : map.entrySet()) {
			try{
			
			HashMap<String,String> innerMap = mapData.getValue();
			Date billingDate = Tools.ddmmmyyyy.parse(innerMap.get(ConstantParameter.MtrackFileUploadPath.INNER_MAP_DATE));
			
			System.out.println("FIND BY MSISDN = " + mapData.getKey());
			
			MtrackFleet fleet = mtrackFleetService.findMtrackFleetsbyMSISDN(mapData.getKey(), MatchMode.EXACT).get(0);
			MtrackBilling existedBilling = mtrackBillingService.findBillingByMSISDNandBillingDate(fleet.getMsisdn(), billingDate);
		
			if(existedBilling != null){
				billing = existedBilling;
			} else {
				billing = new MtrackBilling();
			}
			
			billing.setCreatedDate(new Date());
			billing.setCreateddBy(username);		
			billing.setInvoiceId(null);
			//System.out.println("FLEET ID = " + pageAttr.getFleetIdSelectedVal());
			billing.setFleetId(fleet);
			
			//Auto extraction
			billing.setBillSummaryPath(innerMap.get(ConstantParameter.MtrackFileUploadPath.INNER_MAP_BILSUM_FNAME));				
			billing.setCallDetailPath(innerMap.get(ConstantParameter.MtrackFileUploadPath.INNER_MAP_CALLDET_FNAME));				
			billing.setBillingDate(billingDate);
			billing.setTotalBilling(new BigDecimal(innerMap.get(ConstantParameter.MtrackFileUploadPath.INNER_MAP_AMOUNT)));
			
			billing = mtrackBillingService.saveMtrackBilling(billing);
			
			sb.append(billing.toStringMulti("Action", "Create new Billing Succeed", Tools.sdfLog.format(new Date())));
			log.info(sb.toString());
			
			if(billing.getBillSummaryPath()!=null && !billing.getBillSummaryPath().equals("")){
				indexBill = indexBill + 1;
			}
			
			if(billing.getCallDetailPath()!=null && !billing.getCallDetailPath().equals("")){
				indexCall = indexCall + 1;
			}
			
		
			} catch (Exception e){
				isErr = true;
				e.printStackTrace();
				
				numList.add(mapData.getKey());														
			}
			
		}
		
		attr.setNumList(numList);
		attr.setAutoInsertErr(isErr);
		attr.setIndexBill(indexBill);
		attr.setIndexCall(indexCall);
		
		return attr;
	}
	
	@RequestMapping(value="mTrack/getinfo", method = RequestMethod.POST)
	public @ResponseBody String getInfo(HttpServletRequest request, HttpServletResponse response) {
		
		String message = BATCH_SUCCESS_MESSAGE;
		String extractData = BATCH_DATA_EXTRACTED.replace("XXX", String.valueOf(savedAttr.getIndexBill())).replace("YYY",String.valueOf(savedAttr.getIndexCall()));
		message = message + "<br>" + extractData; 
		if(savedAttr.isAutoInsertErr()){
			String errNumber = "";
						
			for(int i=0; i<savedAttr.getNumList().size();i++){
				errNumber = errNumber + "<br>"+savedAttr.getNumList().get(i);
			}
			errNumber = errNumber + "<br>";
			message = message +"<br>"+BATCH_ERROR_MESSAGE.replace("ZZZ", errNumber);
		}
		
		savedAttr.setIndexBill(0);
		savedAttr.setIndexCall(0);
		savedAttr.setAutoInsertErr(false);
		savedAttr.setNumList(null);
		
		return message;
		
	}
	
}
