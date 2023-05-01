package com.hrs.app.service;

import java.util.List;

import com.hrs.app.dao.LeaseRepo;
import com.hrs.app.model.Announcement;
import com.hrs.app.model.Bill;
import com.hrs.app.model.Complaint;
import com.hrs.app.model.Coupon;
import com.hrs.app.model.House;
import com.hrs.app.model.Lease;
import com.hrs.app.model.ReportUserModel;

public interface AdminService {

	List<ReportUserModel> findAllUserReports();

	void addBill(Bill bill);

	void addAnnouncement(Announcement announcement);

	void addCoupon(Coupon coupon);

	House getHouseDocument(Long id);

	void verifyHouse(Long id);

	List<Complaint> findAllComplaints();

	void removeComplaint(Long id);

	void removeUser(Long id);

	List<Coupon> getAllCoupons();

	Lease getLeaseDocument(Long id);

	void verifyLease(Long id);
	
	void removeReportUser(Long id);
	

}
