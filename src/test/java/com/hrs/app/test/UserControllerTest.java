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

import com.hrs.app.dao.AppointmentRepo;
import com.hrs.app.dao.BookRepo;
import com.hrs.app.dao.MessageRepo;
import com.hrs.app.dao.ReportUserRepo;
import com.hrs.app.dao.ReserveRepo;
import com.hrs.app.dao.ReviewPropertyRepo;
import com.hrs.app.model.Appointment;
import com.hrs.app.model.Book;
import com.hrs.app.model.MessageModel;
import com.hrs.app.model.ReportOwnerModel;
import com.hrs.app.model.ReportUserModel;
import com.hrs.app.model.ReviewPropertyModel;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class UserControllerTest {
	
	@Autowired
	private AppointmentRepo appointmentRepo;
	
	
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private ReportUserRepo reportRepo;
	
	@Autowired
	private ReviewPropertyRepo reviewRepo;
	
	@Autowired
	private MessageRepo messageRepo;
	
	@Test
	public void bookAppointment() {
		
		Appointment appointment = new Appointment();
		
		appointment.setHouseId("12");
		appointment.setUserId("22");
		appointment.setVisitDate("2022-01-20");
		appointment.setVisitTime("12:40");
		
		List<Appointment> appointmentdetails = appointmentRepo.findAll();
		
		assertThat(appointmentdetails).isEmpty();
		
		appointmentRepo.save(appointment);
	    
	    List<Appointment> appointments = appointmentRepo.findAll();

	    assertThat(appointments).hasSize(1);
	    appointmentRepo.deleteAll();
	    
	    
	    Appointment appointment1 = new Appointment();
		
		appointment1.setHouseId("52");
		appointment1.setUserId("25");
		appointment1.setVisitDate("2021-04-20");
		appointment1.setVisitTime("12:30");
		
		List<Appointment> appointmentdetails1 = appointmentRepo.findAll();
		
		assertThat(appointmentdetails1).isEmpty();
		
		appointmentRepo.save(appointment1);
	    
	    List<Appointment> appointments1 = appointmentRepo.findAll();

	    assertThat(appointments1).hasSize(1);
	    
	  }
	
	
	@Test
	public void shoudlNotBookAppointment() {
		
		Appointment appointment = new Appointment();
		
		appointment.setHouseId("12");
		appointment.setUserId("22");
		appointment.setVisitDate("2022-01-20");
		appointment.setVisitTime("12:40");
		
		List<Appointment> appointmentdetails = appointmentRepo.findAll();
		
		assertThat(appointmentdetails).isEmpty();
		try {
		appointmentRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    List<Appointment> appointments = appointmentRepo.findAll();

	    assertThat(appointments).hasSize(0);
	    appointmentRepo.deleteAll();
	    
	    
	    Appointment appointment1 = new Appointment();
		
		appointment1.setHouseId("52");
		appointment1.setUserId("25");
		appointment1.setVisitDate("2021-04-20");
		appointment1.setVisitTime("12:30");
		
		List<Appointment> appointmentdetails1 = appointmentRepo.findAll();
		
		assertThat(appointmentdetails1).isEmpty();
		
		try {
			appointmentRepo.save(null);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	    
	    List<Appointment> appointments1 = appointmentRepo.findAll();

	    assertThat(appointments1).hasSize(0);
	    
	  }
	
	@Test
	public void bookHouse() {
		
		Book bookhouse = new Book();
		
		bookhouse.setHouseId("14");
		bookhouse.setUserId("23");
		bookhouse.setMoveInDate("2022-06-20");
		bookhouse.setCoupon("NEWNEW");
		
		List<Book> bookhousedetails = bookRepo.findAll();
		
		assertThat(bookhousedetails).isEmpty();
		
		bookRepo.save(bookhouse);
	    
	    List<Book> bookhouses = bookRepo.findAll();

	    assertThat(bookhouses).hasSize(1);
	    bookRepo.deleteAll();
	    Book bookhouse1 = new Book();
		
	    bookhouse1.setHouseId("11");
	    bookhouse1.setUserId("21");
	    bookhouse1.setMoveInDate("2022-03-20");
	    bookhouse1.setCoupon("GOODDAY");
		
		List<Book> bookhousedetails1 = bookRepo.findAll();
		
		assertThat(bookhousedetails1).isEmpty();
		
		bookRepo.save(bookhouse1);
	    
	    List<Book> bookhousedetails2 = bookRepo.findAll();

	    assertThat(bookhousedetails2).hasSize(1);
	    
	  }
	
	@Test
	public void shouldNotBookHouse() {
		
		Book bookhouse = new Book();
		
		bookhouse.setHouseId("14");
		bookhouse.setUserId("23");
		bookhouse.setMoveInDate("2022-06-20");
		bookhouse.setCoupon("NEWNEW");
		
		List<Book> bookhousedetails = bookRepo.findAll();
		
		assertThat(bookhousedetails).isEmpty();
		
		try {
		bookRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    List<Book> bookhouses = bookRepo.findAll();

	    assertThat(bookhouses).hasSize(0);
	    bookRepo.deleteAll();
	    Book bookhouse1 = new Book();
		
	    bookhouse1.setHouseId("11");
	    bookhouse1.setUserId("21");
	    bookhouse1.setMoveInDate("2022-03-20");
	    bookhouse1.setCoupon("GOODDAY");
		
		List<Book> bookhousedetails1 = bookRepo.findAll();
		
		assertThat(bookhousedetails1).isEmpty();
		
		try {
			bookRepo.save(null);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	    
	    List<Book> bookhousedetails2 = bookRepo.findAll();

	    assertThat(bookhousedetails2).hasSize(0);
	    
	  }
	
	@Test
	public void reportOwnerAndHouse() {
		
		ReportUserModel report = new ReportUserModel();
		
		report.setUserMail("vikas@gmail.com");
		
		report.setReason("worst houses");
		
		List<ReportUserModel> reportdetails = reportRepo.findAll();
		
		assertThat(reportdetails).isEmpty();
		
		reportRepo.save(report);
	    
	    List<ReportUserModel> reports = reportRepo.findAll();

	    assertThat(reports).hasSize(1);
	    reportRepo.deleteAll();
	    
	    ReportUserModel report1 = new ReportUserModel();
		
		report1.setUserMail("priya@gmail.com");
		
		report1.setReason("dirty houses");
		
		List<ReportUserModel> reportdetails1 = reportRepo.findAll();
		
		assertThat(reportdetails1).isEmpty();
		
		reportRepo.save(report1);
	    
	    List<ReportUserModel> reports2 = reportRepo.findAll();

	    assertThat(reports2).hasSize(1);
	    
	  }
	
	
	
	@Test
	public void reviewApplication() {
		
		ReviewPropertyModel review = new ReviewPropertyModel();
		
		review.setDescription("very good for housebooking");
		review.setRating("5");
		
		List<ReviewPropertyModel> reviewdetails = reviewRepo.findAll();
		
		assertThat(reviewdetails).isEmpty();
		
		reviewRepo.save(review);
	    
	    List<ReviewPropertyModel> reviews = reviewRepo.findAll();

	    assertThat(reviews).hasSize(1);
	    reviewRepo.deleteAll();
	    
	    ReviewPropertyModel review1 = new ReviewPropertyModel();
		
		review1.setDescription("good housebooking");
		review1.setRating("2");
		
		List<ReviewPropertyModel> reviewdetails1 = reviewRepo.findAll();
		
		assertThat(reviewdetails1).isEmpty();
		
		reviewRepo.save(review1);
	    
	    List<ReviewPropertyModel> reviews1 = reviewRepo.findAll();

	    assertThat(reviews1).hasSize(1);
	    
	  }
	
	@Test
	public void shouldNotReviewApplication() {
		
		ReviewPropertyModel review = new ReviewPropertyModel();
		
		review.setDescription("very good for housebooking");
		review.setRating("5");
		
		List<ReviewPropertyModel> reviewdetails = reviewRepo.findAll();
		
		assertThat(reviewdetails).isEmpty();
		
		try {
		reviewRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    List<ReviewPropertyModel> reviews = reviewRepo.findAll();

	    assertThat(reviews).hasSize(0);
	   
	    
	    ReviewPropertyModel review1 = new ReviewPropertyModel();
		
		review1.setDescription("good housebooking");
		review1.setRating("2");
		
		List<ReviewPropertyModel> reviewdetails1 = reviewRepo.findAll();
		
		assertThat(reviewdetails1).isEmpty();
		
		try {
			reviewRepo.save(null);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
	    
	    List<ReviewPropertyModel> reviews1 = reviewRepo.findAll();

	    assertThat(reviews1).hasSize(0);
	    
	  }
	
	
	
	
	@Test
	public void sendMessageToHouseOwner() {
		
		MessageModel message = new MessageModel();
		message.setOwnerMail("sneha@gmail.com");
		message.setUserMail("pallavi@gmail.com");
		message.setQuestion("how many years old house is this?");
		message.setAnswer("");
		
		List<MessageModel> messagedetail = messageRepo.findAll();
		assertThat(messagedetail).isEmpty();
		messageRepo.save(message);
		
		List<MessageModel> messages = messageRepo.findAll();
		assertThat(messages).hasSize(1);
		
		messageRepo.deleteAll();
		
		MessageModel message1 = new MessageModel();
		message1.setOwnerMail("kumar@gmail.com");
		message1.setUserMail("hitesh@gmail.com");
		message1.setQuestion("is there any water problem?");
		message1.setAnswer("");
		
		List<MessageModel> messagedetail1 = messageRepo.findAll();
		assertThat(messagedetail1).isEmpty();
		messageRepo.save(message1);
		
		List<MessageModel> messages1 = messageRepo.findAll();
		assertThat(messages1).hasSize(1);
	}
	
	@Test
	public void shouldNotsendMessageToHouseOwner() {
		
		MessageModel message = new MessageModel();
		message.setOwnerMail("sneha@gmail.com");
		message.setUserMail("pallavi@gmail.com");
		message.setQuestion("how many years old house is this?");
		message.setAnswer("");
		
		List<MessageModel> messagedetail = messageRepo.findAll();
		assertThat(messagedetail).isEmpty();
		try {
		messageRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		List<MessageModel> messages = messageRepo.findAll();
		assertThat(messages).hasSize(0);
		
		messageRepo.deleteAll();
		
		MessageModel message1 = new MessageModel();
		message1.setOwnerMail("kumar@gmail.com");
		message1.setUserMail("hitesh@gmail.com");
		message1.setQuestion("is there any water problem?");
		message1.setAnswer("");
		
		List<MessageModel> messagedetail1 = messageRepo.findAll();
		assertThat(messagedetail1).isEmpty();
		try {
			messageRepo.save(null);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		
		List<MessageModel> messages1 = messageRepo.findAll();
		assertThat(messages1).hasSize(0);
	}


}


