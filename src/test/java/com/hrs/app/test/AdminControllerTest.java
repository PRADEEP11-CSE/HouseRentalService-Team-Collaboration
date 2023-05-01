package com.hrs.app.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hrs.app.dao.AnnouncementRepo;
import com.hrs.app.dao.CouponRepo;
import com.hrs.app.dao.HouseRepo;
import com.hrs.app.model.Announcement;
import com.hrs.app.model.Coupon;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class AdminControllerTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private AnnouncementRepo announcementRepo;
	
	@Autowired
	private CouponRepo couponRepo ;
	
	
	@Autowired
	private HouseRepo houseRepo;
	

	
	
	
	
	@Test
	public void postAnnouncements() {
		
		Announcement announcement = new Announcement();
		
		announcement.setAnnouncementDescription("Lotus Community discounts on rent for next month");
		announcement.setAnnouncementTitle("Use NEWYEARSPECIAL coupon code while booking");
		announcement.setStartDate("2022-11-20");
		announcement.setStartTime("10:00");
		
		List<Announcement> announcementdetails = announcementRepo.findAll();
		
		assertThat(announcementdetails).isEmpty();
		
		try {
		announcementRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    List<Announcement> announcements = announcementRepo.findAll();

	    assertThat(announcements).hasSize(0);
	    
	    
	    Announcement announcement2 = new Announcement();
		
		announcement.setAnnouncementDescription("New year festivals starts from 11pm at swagruha appartments");
		announcement.setAnnouncementTitle("Invitation for Part tonight");
		announcement.setStartDate("2022-12-31");
		announcement.setStartTime("11");
		
		List<Announcement> announcementdetails2 = announcementRepo.findAll();
		
		assertThat(announcementdetails2).isEmpty();
		
		try {
			announcementRepo.save(null);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	    
	    List<Announcement> announcements3 = announcementRepo.findAll();

	    assertThat(announcements3).hasSize(0);
	    
	  }
	
	@Test
	public void postCoupons() {
		
		Coupon coupon = new Coupon();
		
		coupon.setCouponTitle("CHRISTMAS Coupon");
		coupon.setCouponCode("XMAS2022");
		coupon.setDiscountAmount("1000");
		coupon.setStartDate("24-12-2022");
		coupon.setEndDate("1-1-2023");
		
		List<Coupon> coupons = couponRepo.findAll();
		
		assertThat(coupons).isEmpty();
		
		couponRepo.save(coupon);
	    
	    List<Coupon> newCoupons = couponRepo.findAll();

	    assertThat(newCoupons).hasSize(1);
	    couponRepo.deleteAll();
	    
	    Coupon coupon1 = new Coupon();
		
		coupon1.setCouponTitle("DIWALI DHAMAKA COUPON");
		coupon1.setCouponCode("DHAMAKA2022");
		coupon1.setDiscountAmount("500");
		coupon1.setStartDate("24-10-2022");
		coupon1.setEndDate("25-11-2022");
		
		List<Coupon> coupons1 = couponRepo.findAll();
		
		assertThat(coupons1).isEmpty();
		
		couponRepo.save(coupon1);
	    
	    List<Coupon> newCoupons1 = couponRepo.findAll();

	    assertThat(newCoupons1).hasSize(1);
	    
	  }
	
	@Test
	public void dontPostCoupons() {
		
		Coupon coupon = new Coupon();
		
		coupon.setCouponTitle("CHRISTMAS Coupon");
		coupon.setCouponCode("XMAS2022");
		coupon.setDiscountAmount("1000");
		coupon.setStartDate("24-12-2022");
		coupon.setEndDate("1-1-2023");
		
		List<Coupon> coupons = couponRepo.findAll();
		
		assertThat(coupons).isEmpty();
		
		try {
		couponRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    List<Coupon> newCoupons = couponRepo.findAll();

	    assertThat(newCoupons).hasSize(0);

	    
	    Coupon coupon1 = new Coupon();
		
		coupon1.setCouponTitle("DIWALI DHAMAKA COUPON");
		coupon1.setCouponCode("DHAMAKA2022");
		coupon1.setDiscountAmount("500");
		coupon1.setStartDate("24-10-2022");
		coupon1.setEndDate("25-11-2022");
		
		List<Coupon> coupons1 = couponRepo.findAll();
		
		assertThat(coupons1).isEmpty();
		
		try {
			couponRepo.save(null);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	    
	    List<Coupon> newCoupons1 = couponRepo.findAll();

	    assertThat(newCoupons1).hasSize(0);
	    
	  }
	


}

