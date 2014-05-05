package com.brightrich.controller;

import java.io.FileInputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.net.URL;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;

import org.springframework.util.FileCopyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

import com.brightrich.controller.attribute.BasePageAttribute;
import com.brightrich.service.UserService;

@Controller
public class MainController {
	
	private BasePageAttribute pageAttr;
	
	@Autowired
    private UserService userService;

	
	@RequestMapping("/")
	public ModelAndView homePage(ModelAndView mav){
		mav.setViewName("index");
		pageAttr = new BasePageAttribute();
		pageAttr.setPageId("home");
		mav.addObject("pageAttr", pageAttr);						
		return mav;
	}
	
	//Unused Mapping		
	/*@RequestMapping("portfolio")
	public ModelAndView portfolioPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Portfolio");
		pageAttr.setPageId(request.getServletPath().substring(1));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("portfolio");
		return mav;
	}*/
	
	@RequestMapping("blog")
	public ModelAndView blogPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Blog Big Image");
		pageAttr.setPageId(request.getServletPath().substring(1));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("blog");
		return mav;
	}
	
	@RequestMapping("careers")
	public ModelAndView elementsPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Careers");
		pageAttr.setPageId(request.getServletPath().substring(1));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("careers");
		return mav;
	}
	
	@RequestMapping("productsAndServices/mixed_delivery")
	public ModelAndView mixedDeliveryPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Product and Services");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("productsAndServices/mixed_delivery");
		return mav;
	}
	
	@RequestMapping("productsAndServices/consulting")
	public ModelAndView consultingPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Product and Services");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("productsAndServices/consulting");
		return mav;
	}
	
	@RequestMapping("productsAndServices/network_infra")
	public ModelAndView networkInfraPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Product and Services");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("productsAndServices/network_infra");
		return mav;
	}
	
	@RequestMapping("productsAndServices/service_and_solutions")
	public ModelAndView serviceAndSolutionsPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Product and Services");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("productsAndServices/service_and_solutions");
		return mav;
	}
	
	@RequestMapping("productsAndServices/it_infra")
	public ModelAndView itInfraPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Product and Services");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("productsAndServices/it_infra");
		return mav;
	}
	
	@RequestMapping("productsAndServices/econtact_call")
	public ModelAndView eContactCallPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Product and Services");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("productsAndServices/econtact_call");
		return mav;
	}
	
	@RequestMapping("productsAndServices/product_and_services")
	public ModelAndView productsAndServicesPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Product and Services");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("productsAndServices/product_and_services");
		return mav;
	}
	
	@RequestMapping(value = "/productsAndServices/econtact.pdf", method = RequestMethod.GET)
    public void handleFileDownload(HttpServletRequest req, HttpServletResponse res) {
        try {
            String fn = "/WEB-INF/jsp/productsAndServices/econtact.pdf";
            URL url = getClass().getResource(fn);
            
            System.out.println("Path " + req.getSession().getServletContext().getRealPath(fn));
            File f = new File(req.getSession().getServletContext().getRealPath(fn));
            System.out.println("Loading file "+fn+"("+f.getAbsolutePath()+")");
            if (f.exists()) {
                res.setContentType("application/pdf");
                res.setContentLength(new Long(f.length()).intValue());
                res.setHeader("Content-Disposition", "attachment; filename=econtact.pdf");
                FileCopyUtils.copy(new FileInputStream(f), res.getOutputStream());
            } else {
                System.out.println("File "+fn+"("+f.getAbsolutePath()+") does not exist");
            }
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
            e.printStackTrace();
        }
    }
	
	@RequestMapping("aboutUs/about_us")
	public ModelAndView aboutUsPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("About Us");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("aboutUs/about_us");
		return mav;
	}
	
	//Unused Mapping
	/*@RequestMapping("aboutUs/vision_mission")
	public ModelAndView visionMissionPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("About Us");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("aboutUs/vision_mission");
		return mav;
	}*/
	
	@RequestMapping("aboutUs/management_team")
	public ModelAndView managementTeamPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("About Us");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("aboutUs/management_team");
		return mav;
	}
	
	@RequestMapping("aboutUs/anticorruption_directive")
	public ModelAndView anticorruptionTeamPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("About Us");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("aboutUs/anticorruption_directive");
		return mav;
	}
	
	@RequestMapping("aboutUs/code_of_conduct")
	public ModelAndView codeOfConductPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("About Us");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("aboutUs/code_of_conduct");
		return mav;
	}
	
	@RequestMapping("aboutUs/strategic_partner")
	public ModelAndView strategicPartnerPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("About Us");
		pageAttr.setPageId(request.getServletPath().substring(1,request.getServletPath().lastIndexOf("/")));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("aboutUs/strategic_partner");
		return mav;
	}
	
	@RequestMapping("services")
	public ModelAndView servicesPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Services");
		pageAttr.setPageId(request.getServletPath().substring(1));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("services");
		return mav;
	}
	
	@RequestMapping("contact_us")
	public ModelAndView contactUsPage(ModelAndView mav, HttpServletRequest request){
		pageAttr = new BasePageAttribute();
		pageAttr.setTitle("Contact Us");
		pageAttr.setPageId(request.getServletPath().substring(1));
		mav.addObject("pageAttr", pageAttr);
		mav.setViewName("contact_us");
		return mav;
	}
	
	@RequestMapping("/main")
	public String mainPage(Model model){
		return "main";
	}
	
	@RequestMapping(value = "finance/common", method = RequestMethod.GET)
    public String getCommonPage(Principal principal) {
    	System.out.println("Received request to show common page");
    	User activeUser = (User) ((Authentication)principal).getPrincipal();
    	System.out.println("USER LOGIN = " + activeUser.getUsername());
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/commonpage.jsp
    	return "finance/commonpage";
	}
    
    /**
     * Handles and retrieves the admin JSP page that only admins can see
     * 
     * @return the name of the JSP page
     */
    @RequestMapping(value = "finance/admin", method = RequestMethod.GET)
    public String getAdminPage() {
    	System.out.println("Received request to show admin page");
    
    	// Do your work here. Whatever you like
    	// i.e call a custom service to do your business
    	// Prepare a model to be used by the JSP page
    	
    	// This will resolve to /WEB-INF/jsp/adminpage.jsp
    	return "finance/adminpage";
	}
	
	
	

}
