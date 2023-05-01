package com.hrs.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.hrs.app.dao.AnnouncementRepo;
import com.hrs.app.dao.BillRepo;
import com.hrs.app.dao.ComplaintRepo;
import com.hrs.app.dao.CouponRepo;
import com.hrs.app.dao.HouseRepo;
import com.hrs.app.dao.LeaseRepo;
import com.hrs.app.dao.ReportUserRepo;
import com.hrs.app.dao.UserRepo;
import com.hrs.app.model.Announcement;
import com.hrs.app.model.Bill;
import com.hrs.app.model.Complaint;
import com.hrs.app.model.Coupon;
import com.hrs.app.model.House;
import com.hrs.app.model.Lease;
import com.hrs.app.model.ReportUserModel;
import com.hrs.app.model.User;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AnnouncementRepo announcementRepo;
	
	@Autowired
	private BillRepo billRepo;
	
	@Autowired
	private ReportUserRepo reportUserRepo;
	
	@Autowired
	private CouponRepo couponRepo;
	
	@Autowired
	private HouseRepo houseRepo;
	
	@Autowired
	private ComplaintRepo complaintsRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private LeaseRepo leaseRepo;
	
	

	@Override
	public List<ReportUserModel> findAllUserReports() {
		// TODO Auto-generated method stub
		return reportUserRepo.findAll();
	}

	@Override
	public void addBill(Bill bill) {
		// TODO Auto-generated method stub
		billRepo.save(bill);
		
		
	}

	@Override
	public void addAnnouncement(Announcement announcement) {
		
		announcementRepo.save(announcement);
		
	}

	@Override
	public void addCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		couponRepo.save(coupon);
		
	}

	@Override
	public House getHouseDocument(Long id) {
		// TODO Auto-generated method stub
		return houseRepo.findHouseById(id);
	}
	

	public void verifyHouse(Long id) {
		// TODO Auto-generated method stub
		
		House house = houseRepo.findHouseById(id);
		
		house.setIsVerified("1");
		houseRepo.save(house);
		
		
	}

	@Override
	public List<Complaint> findAllComplaints() {
		// TODO Auto-generated method stub
		return complaintsRepo.findAll();
	}

	@Override
	public void removeComplaint(Long id) {
		// TODO Auto-generated method stub
		complaintsRepo.delete(complaintsRepo.findComplaintById(id));
		
	}

	@Override
	public void removeUser(Long id) {
		// TODO Auto-generated method stub
		userRepo.delete(userRepo.getUserById(id));
		
	}

	@Override
	public List<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return couponRepo.findAll();
	}

	@Override
	public Lease getLeaseDocument(Long id) {
		// TODO Auto-generated method stub
		return leaseRepo.findLeaseById(id);
	}

	@Override
	public void verifyLease(Long id) {
		// TODO Auto-generated method stub
		Lease lease =leaseRepo.findLeaseById(id);
		
		lease.setIsApproved("1");
		leaseRepo.save(lease);
		
	}
	
	public void removeReportUser(Long id) {
		// TODO Auto-generated method stub
		ReportUserModel report = reportUserRepo.findReportById(id);
		System.out.println("report mail==== "+report.getUserMail());
		User user = userRepo.findbyEmail(report.getUserMail());
		userRepo.deleteById(user.getId());
		reportUserRepo.deleteById(id);
		
		
	}

}
