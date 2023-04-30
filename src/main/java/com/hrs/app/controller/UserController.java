package com.hrs.app.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrs.app.model.Announcement;
import com.hrs.app.model.Appointment;
import com.hrs.app.model.Book;
import com.hrs.app.model.Complaint;
import com.hrs.app.model.Coupon;
import com.hrs.app.model.Email;
import com.hrs.app.model.Favourite;
import com.hrs.app.model.House;
import com.hrs.app.model.Lease;
import com.hrs.app.model.Maintenance;
import com.hrs.app.model.MessageModel;
import com.hrs.app.model.ReportOwnerModel;
import com.hrs.app.model.ReviewPropertyModel;
import com.hrs.app.model.User;
import com.hrs.app.service.AdminService;
import com.hrs.app.service.MessageService;
import com.hrs.app.service.OwnerService;
import com.hrs.app.service.OwnerServiceImpl;
import com.hrs.app.service.UserService;
import com.hrs.app.service.UserServiceImpl;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("/user")
	public String getUserWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        model.addAttribute("role", userdata.getUsertype());
//        String base64EncodedImage = Base64.getEncoder().encodeToString(houseOwnerService.getHouse().getHousePhoto());
//        model.addAttribute("image", base64EncodedImage);
//        System.out.println(base64EncodedImage);
        List<House> houses = userService.getAllHouseDetails();
        model.addAttribute("houses", houses);
		return "user/welcomeuser";
	}
	
	@GetMapping("/viewHouse/{id}")
	public String viewHouse(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		House house = ownerService.getHouseById(id);
		
		
		model.addAttribute("house", house);
		
		model.addAttribute("role", userdata.getUsertype());
		return "user/viewsinglehouse";
	}
	
//	@GetMapping("/bookHouse/{id}")
//	public String bookHouse(@PathVariable(name="id") Long id, Model model, HttpSession session)
//	{
//		@SuppressWarnings("unchecked")
//        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
//
//		if(messages == null) {
//			model.addAttribute("errormsg", "Session Expired. Please Login Again");
//			return "home/error";
//		}
//        model.addAttribute("sessionMessages", messages);
//        User userdata = userService.findUser(messages.get(0));
//        model.addAttribute("role", userdata.getUsertype());
//        House houseDetails = ownerService.getHouseDetailsById(id);
//        model.addAttribute("houseid", id);
//        model.addAttribute("houseRent", houseDetails.getHouseRent());
//        Book book = new Book();
//        model.addAttribute("book", book);
//		return "user/bookhouse";
//	}
	
	@GetMapping("/reserveHouse/{id}")
	public String reserveHouse(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        
        User userdata = userService.findUser(messages.get(0));
        System.out.println(id);
        userService.reserveHouse(id, userdata.getId());
		return "redirect:/user";
	}
	
	@GetMapping("/appointment/{id}")
	public String appointment(@PathVariable(name="id") Long id, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        model.addAttribute("role", userdata.getUsertype());
        model.addAttribute("houseid", id);
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
		return "user/appointment";
	}
	
	@GetMapping("/filter")
	public String viewFiltersPage(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		model.addAttribute("role", userdata.getUsertype());
		List<House> houses = userService.getAllHouseDetails();
	    model.addAttribute("houses", houses);
		return "user/filter";
		
	}
	
	@PostMapping("/bookAppointment")
	public String bookAppointment(@Param("houseid") Long houseid,@ModelAttribute("appointment") Appointment appointment, Model model, HttpSession session) {
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        
        House house = ownerService.getHouseById(houseid);
        
        appointment.setHouseId(houseid.toString());
//        appointment.setHouseDetails(house.getHouseDetails());
        
        appointment.setUserId(userdata.getId().toString());
        appointment.setStatus("0");
        
        userService.saveAppointment(appointment);
        return "redirect:/user";
        
	}
	
	@PostMapping("/searchHouse")
	public String searchHouse(Model model, HttpSession session, @RequestParam("searchKey") String searchKey ) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        List<House> houses = userService.searchHouses(searchKey);
        model.addAttribute("houses", houses);
        model.addAttribute("role", userdata.getUsertype());
		return "user/welcomeuser";
	}
	
	@PostMapping("/applyFilters")
	public String applyFilters(Model model, HttpSession session, @RequestParam("city") String city,
			 @RequestParam("moveInDate") String moveInDate) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        List<House> houses = userService.filterHouses(city,moveInDate);
        model.addAttribute("houses", houses);
        model.addAttribute("role", userdata.getUsertype());
		return "user/filter";
	}
	
	@PostMapping("addToFavourites/{id}")
	public String addToFavourites(@PathVariable(name="id") String id, Model model, HttpSession session) {
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        
        Favourite favourite = new Favourite();
        
        favourite.setHouseId(id);
        favourite.setUserId(userdata.getId().toString());
        
        userService.savefavourites(favourite);
        return "redirect:/user";
        
	}
	
	@GetMapping("/viewFavourites")
	public String viewFavourites(Model model, HttpSession session) {
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<House> favs = userService.findAllFavs(userdata.getId());
		model.addAttribute("houses", favs);
		System.out.println("fffff");
		
		return "user/viewfavourites";
		
	}
	
	
	
//	@GetMapping("/previousBookings")
//	public String previousBookings(Model model, HttpSession session)
//	{
//		@SuppressWarnings("unchecked")
//        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
//
//		if(messages == null) {
//			model.addAttribute("errormsg", "Session Expired. Please Login Again");
//			return "home/error";
//		}
//        model.addAttribute("sessionMessages", messages);
//        User userdata = userService.findUser(messages.get(0));
//        model.addAttribute("role", userdata.getUsertype());
//        
//        List<Book> bookings = userService.getAllBookingsByUserId(userdata.getId());
//        
//        model.addAttribute("bookings", bookings);
//		return "user/previousbookings";
//	}
	
	@GetMapping("/sendMessage")
	public String viewMessagePage(Model model, HttpSession session) {
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<User> owners = ownerService.getAllOwners();
		MessageModel msg = new MessageModel();
		model.addAttribute("msg", msg);
		model.addAttribute("owners", owners);
		model.addAttribute("role", userdata.getUsertype());
		return "user/sendmsg";
		
	}
	
	@PostMapping("/sendMsg")
	public String saveMessage(@ModelAttribute("msg") MessageModel msg, HttpSession session, Model model )
	{
		System.out.println("raised Ticket");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		msg.setUserMail(userdata.getEmail());
		msg.setAnswer("");

		userService.saveMsg(msg);
		
		return "redirect:/user";
	}
	
	@GetMapping("/viewReplies")
	public String viewMessagesPage(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<MessageModel> msgs = userService.findAllMessages(userdata.getEmail());
		model.addAttribute("msgs", msgs);
		model.addAttribute("role", userdata.getUsertype());
		return "user/viewmessages";
		
	}
	
	@PostMapping("/reportOwner")
	public String report(@ModelAttribute("report") ReportOwnerModel report, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		System.out.println("reported Owner");
		
		User user = userService.findUser(report.getUserMail());
		
		report.setUserMail(userdata.getEmail());
		ownerService.saveReport(report);
		
		return "redirect:/user";
	}
	
	@GetMapping("/reportOwners")
	public String reportOwners(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata =userService.findUser(messages.get(0));
		List<User> owners = userService.getAllOwners();
		model.addAttribute("owners", owners);
		ReportOwnerModel report = new ReportOwnerModel();
		model.addAttribute("report", report);
		

		return "user/reportowner";
	}
	
	
	@GetMapping("/requestMaintenance")
	public String reportMaintenance(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata =userService.findUser(messages.get(0));
		List<House> houses = userService.getAllBookedHouseDetails(userdata.getId());
		model.addAttribute("houses", houses);
		Maintenance maintenance = new Maintenance();
		model.addAttribute("maintenance", maintenance);
		

		return "user/requestmaintenance";
	}
	
	@PostMapping("/saveMaintenance")
	public String saveMaintenance(@ModelAttribute("maintenance") Maintenance maintenance, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		System.out.println("reported Owner");
		
		maintenance.setUserMail(userdata.getEmail());
		userService.saveMaintenance(maintenance);
		
		return "redirect:/user";
	}
	@GetMapping("/reviewProperty")
	public String reviewProperty(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<User> owners = userService.getAllOwners();
		model.addAttribute("owners", owners);
		ReviewPropertyModel property = new ReviewPropertyModel();
		model.addAttribute("property", property);
		List<House> houses = userService.getAllHouses();
		model.addAttribute("houses", houses);

		return "user/reviewproperty";
	}
	
	@PostMapping("/reviewHouseProperty")
	public String reviewHouseProperty(@ModelAttribute("property") ReviewPropertyModel property, Model model, HttpSession session)
	{
		System.out.println("reported Owner");
	
		
		userService.saveReviewProperty(property);
		
		return "redirect:/user";
	}
	
	@GetMapping("/raiseComplaint")
	public String raiseComplaint(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		Complaint complaint = new Complaint();
		model.addAttribute("complaint", complaint);
		List<House> houses = userService.getAllHouses();
		model.addAttribute("houses", houses);

		return "user/raisecomplaint";
	}
	
	@GetMapping("/usernotifications")
	public String notifications(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		List<Announcement> notifications = userService.getAllAnnouncements();
		model.addAttribute("notifications", notifications);
	
		return "user/viewnotifications";
	}
	
	@PostMapping("/saveComplaint")
	public String saveComplaint(@ModelAttribute("complaint") Complaint complaint, Model model, HttpSession session)
	{
		System.out.println("reported Owner");
	
		
		userService.saveComplaint(complaint);
		
		return "redirect:/user";
	}
	
	
	@GetMapping("/referFriend")
	public String referFriend(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
	
		return "user/referfriend";
	}
	

	@PostMapping("/refer")
	public String refer(@RequestParam("email") String email, Model model, HttpSession session)
	{
		int output = 0;
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		Coupon coupon = new Coupon();
		
		coupon.setCouponTitle("REFERRAL");
		coupon.setCouponCode("REFERBY"+userdata.getId().toString());
		coupon.setDiscountAmount("500");
		
		Date date = new Date();
		coupon.setStartDate(date.toString());
		coupon.setEndDate(date.toString());
		
		
		Email emailmodel = new Email();
		emailmodel.setMsgBody("You have been referred by "+ userdata.getEmail()+"coupon code is "+ coupon.getCouponCode());
		emailmodel.setRecipient(email);
		emailmodel.setSubject("Referral from House Rental Service");
		
		adminService.addCoupon(coupon);
		System.out.println("------------------body"+ emailmodel.getMsgBody()+"======="+ emailmodel.getRecipient());
		output = messageService.sendSimpleMail(emailmodel);
		
		System.out.println("------------------"+ output);
		if(output !=1) {
			model.addAttribute("errmsg", "User Email address not found.");
		}
		return "redirect:/user";
	}
}
