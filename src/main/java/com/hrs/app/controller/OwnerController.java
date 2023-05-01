package com.hrs.app.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrs.app.model.Appointment;
import com.hrs.app.model.House;
import com.hrs.app.model.MessageModel;
import com.hrs.app.model.ReportUserModel;
import com.hrs.app.model.User;
import com.hrs.app.service.OwnerService;
import com.hrs.app.service.OwnerServiceImpl;
import com.hrs.app.service.UserService;
import com.hrs.app.service.UserServiceImpl;


@Controller
public class OwnerController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping("/owner")
	public String getHouseOwnerWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
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
		return "owner/welcomehouseowner";
	}
	
	@GetMapping("/createHouse")
	public String createHouse(Model model, HttpSession session) {
		
		House house = new House();
		model.addAttribute("house", house);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        model.addAttribute("role", userdata.getUsertype());
		return "owner/createhouse";
	}
	
	@PostMapping("/saveHouse")
	public String saveHouse(@ModelAttribute("house") House house, Model model, HttpSession session, 
			@RequestParam("doc") MultipartFile doc, @RequestParam("image") MultipartFile houseImage)
	{
		System.out.println("house created");
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        
        try {			
//			houseDocument.setDocument(Base64.getEncoder().encodeToString(doc.getBytes()));
			house.setDocument(doc.getBytes());
			house.setHousePhoto(Base64.getEncoder().encodeToString(houseImage.getBytes()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        
       
        
		house.setIsBooked("0");
		house.setIsHide("0");
		house.setIsVerified("1");
		house.setHouseOwnerMail(userdata.getEmail());
		
		ownerService.saveHouse(house);
		
		return "redirect:/owner";
	}
	
	@GetMapping("/viewHouses")
	public String viewHouses(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<House> houses = ownerService.getAllHousesDetailsByEmail(userdata.getEmail());
		model.addAttribute("houses", houses);
		model.addAttribute("houseOwnerMail", userdata.getEmail());
		model.addAttribute("role", userdata.getUsertype());
		return "owner/displayhouses";
	}
	
	@GetMapping("/viewSingleHouse/{id}")
	public String viewSingleHouse(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
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
		return "owner/displaysinglehouse";
	}
	
	@GetMapping("/editHouse/{id}")
	public String editHouse(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		House house = ownerService.getHouseById(id);
		
		House houseDetails = ownerService.getHouseDetailsById(id);
		
		model.addAttribute("house", house);
		
		model.addAttribute("role", userdata.getUsertype());

		return "owner/updatehouse";
	}
	
	@PostMapping("/updateHouse")
	public String updateHouse(@ModelAttribute("house") House house, Model model, HttpSession session,@RequestParam("image") MultipartFile houseImage)
	{
		System.out.println("house updated");
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		 try {			
				house.setHousePhoto(Base64.getEncoder().encodeToString(houseImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
		house.setHouseOwnerMail(userdata.getEmail());
		ownerService.updateHouse(house);
		
		
		return "redirect:/owner";
	}
	
	@PostMapping("/deleteHouse/{id}")
	public String deleteHouse(@PathVariable(name="id") Long id)
	{
		ownerService.deleteHouse(id);
		
		return "redirect:/owner";
	}
	

	@GetMapping("/viewAppointments")
	public String viewAppointments(Model model, HttpSession session)
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
        
        List<Appointment> appointments = ownerService.getAllAppointmentsByUserId(userdata.getEmail());
        
        model.addAttribute("appointments", appointments);
		return "owner/viewappointments";
	}
	
	@GetMapping("/viewMessages")
	public String viewMessagesPage(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<MessageModel> msgs = ownerService.findAllMessages(userdata.getEmail());
		model.addAttribute("msgs", msgs);
		return "owner/viewmsgs";
		
	}
	
	@GetMapping("/replyMsgPage/{id}")
	public String replyMsgPage(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		MessageModel msg = ownerService.getMsgById(id);
		model.addAttribute("msg", msg);
		model.addAttribute("role", userdata.getUsertype());

		return "owner/replymessage";
	}
	
	@PostMapping("/replyMsg")
	public String raiseTicket(@ModelAttribute("msg") MessageModel msg, HttpSession session, Model model )
	{
		System.out.println("raised Ticket");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		

		userService.saveMsg(msg);
		
		return "redirect:/owner";
	}
	
	@GetMapping("/reportUser")
	public String reportUser(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<User> users = ownerService.getAllUsers();
		System.out.println(users);
		model.addAttribute("users", users);
		ReportUserModel report = new ReportUserModel();
		model.addAttribute("report", report);

		return "owner/reportuser";
	}
	
	@PostMapping("/report")
	public String report(@ModelAttribute("report") ReportUserModel report, Model model)
	{
		System.out.println(report.getUserMail());
		
		if(report.getUserMail().equals("NA")) {
			model.addAttribute("errormsg", "Please Select User Email");
			return "home/error";
		}
		User userdata = userService.findUser(report.getUserMail());
		report.setOwnerMail(userdata.getEmail());
		ownerService.saveUserReport(report);
		
		return "redirect:/owner";
	}
	
	@GetMapping("/approveAppointment/{id}")
	public String approveAppointment(Model model, @PathVariable(name="id") Long id) {
		
		
		ownerService.approve(id);
		
		
		return "redirect:/owner";
		
	}
	
	@GetMapping("/rejectAppointment/{id}")
	public String rejectAppointment(Model model, @PathVariable(name="id") Long id) {
		
		
		ownerService.reject(id);
		
		
		return "redirect:/owner";
		
	}

}
