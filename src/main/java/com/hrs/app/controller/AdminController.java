package com.hrs.app.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hrs.app.model.Announcement;
import com.hrs.app.model.Bill;
import com.hrs.app.model.Complaint;
import com.hrs.app.model.Coupon;
import com.hrs.app.model.House;
import com.hrs.app.model.Lease;
import com.hrs.app.model.ReportUserModel;
import com.hrs.app.model.User;
import com.hrs.app.service.AdminService;
import com.hrs.app.service.OwnerService;
import com.hrs.app.service.UserService;


@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/admin")
	public String getAdminWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
       
		return "admin/welcomeadmin";
	}
	
	
	
	
	@GetMapping("/spamUsers")
	public String getSpamStudents(Model model) {
		
		List<ReportUserModel> userReports = adminService.findAllUserReports();
		model.addAttribute("reports", userReports);
		return "admin/spamusers";
		
	}
	

	
	@GetMapping("/announcement")
	public String getAnnouncementPage(Model model) {
		
		Announcement announcement = new Announcement();
		
		model.addAttribute("announcement", announcement);
		return "admin/addannouncement";
		
	}
	
	@GetMapping("/bills")
	public String getBillPage(Model model) {
		
		Bill bill = new Bill();
		
		model.addAttribute("bill", bill);
		return "admin/bill";
		
	}
	@PostMapping("/postBill")
	public String postBill(@ModelAttribute("bill") Bill bill) {
		
		adminService.addBill(bill);
		
		return "redirect:/admin";
		
	}
	
	
	@PostMapping("/postAnnouncement")
	public String postAnnouncement(@ModelAttribute("announcement") Announcement announcement, @RequestParam("image") MultipartFile annImage) {
		 try {			
			 announcement.setAnnouncementPhoto(Base64.getEncoder().encodeToString(annImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		adminService.addAnnouncement(announcement);
		
		return "redirect:/admin";
		
	}
	
	
	
	@GetMapping("/coupon")
	public String viewCouponPage(Model model) {
		
		Coupon coupon = new Coupon();
		model.addAttribute("coupon",coupon);
		return "admin/coupon";
		
	}
	
	@PostMapping("/postCoupon")
	public String updateFaq(@ModelAttribute("coupon") Coupon coupon) {
		
		adminService.addCoupon(coupon);
		
		return "redirect:/admin";
		
	}
	
	
	
	@GetMapping("/verify")
	public String viewVerifyPage(Model model) {
		
		
		List<House> houses = userService.getAllHouses();
		
		model.addAttribute("houses", houses);
		
		return "admin/verify";
		
	}
	
	@GetMapping("/verifyTransfer")
	public String verifyTransfer(Model model) {
		
		
		List<Lease> leases = userService.getAllLeases();
		
		model.addAttribute("leases", leases);
		
		return "admin/verifytransfer";
		
	}
	
	@GetMapping("/downloadDoc/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable(name="id") Long id) {
	    House document = adminService.getHouseDocument(id);
	    ByteArrayResource resource = new ByteArrayResource(document.getDocument());
	    	System.out.println(resource);
	      return ResponseEntity.ok()
	    		  .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "houseDoc" + "\"")
	              .contentType(MediaType.parseMediaType
	                    ("application/octet-stream"))
	    	        .body(resource);
	}
	
	@GetMapping("/downloadLeaseDoc/{id}")
	public ResponseEntity<Resource> downloadLeaseFile(@PathVariable(name="id") Long id) {
	    Lease document = adminService.getLeaseDocument(id);
	    ByteArrayResource resource = new ByteArrayResource(document.getLeaseDocument());
	    	System.out.println(resource);
	      return ResponseEntity.ok()
	    		  .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "LeaseDoc" + "\"")
	              .contentType(MediaType.parseMediaType
	                    ("application/octet-stream"))
	    	        .body(resource);
	}
	
	@GetMapping("/verifyHouse/{id}")
	public String VerifyHouse(Model model, @PathVariable(name="id") Long id) {
		
		
		adminService.verifyHouse(id);
		
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/verifyLease/{id}")
	public String VerifyLease(Model model, @PathVariable(name="id") Long id) {
		
		
		adminService.verifyLease(id);
		
		
		return "redirect:/admin";
		
	}
	
	@GetMapping("/viewComplaints")
	public String viewComplaints(Model model) {
		
		List<Complaint> complaints = adminService.findAllComplaints();
		model.addAttribute("complaints",complaints);
		return "admin/viewcomplaints";
		
	}
	
	@PostMapping("/removeComplaint/{id}")
	public String removeComplaint(Model model, @PathVariable("id") Long id) {
		System.out.println("id==== "+id);
		adminService.removeComplaint(id);
		return "redirect:/admin";
		
	}
	
	
	@GetMapping("/removeUser/{id}")
	public String removeSpamUsers(Model model, @PathVariable("id") Long id) {
		System.out.println("id==== "+id);
		adminService.removeReportUser(id);
		return "redirect:/admin";
		
	}
	
	
}
