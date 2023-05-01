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

import com.hrs.app.dao.HouseRepo;
import com.hrs.app.dao.MessageRepo;
import com.hrs.app.dao.ReportUserRepo;
import com.hrs.app.model.House;
import com.hrs.app.model.MessageModel;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class OwnerControllerTest {
	
	
	@Autowired
	private MessageRepo messageRepo;
	
	@Autowired
	private HouseRepo houseRepo;
	
	
	@Test
	public void shouldPostHouse() {
		
		House house = new House();
		
		
		house.setHouseOwnerMail("satya@gmail.com");
		house.setHouseType("2bhk");
		
		List<House> housedetails = houseRepo.findAll();
		
		assertThat(housedetails).isEmpty();
		
		houseRepo.save(house);
		
		List<House> houses = houseRepo.findAll();
		
		assertThat(houses).hasSize(1);
		houseRepo.deleteAll();
		
		House house1 = new House();
		
		
		house1.setHouseOwnerMail("vamsi@gmail.com");
		house1.setHouseType("4bhk");
		
		List<House> housedetails1 = houseRepo.findAll();
		
		assertThat(housedetails1).isEmpty();
		
		houseRepo.save(house1);
		
		List<House> houses1 = houseRepo.findAll();
		
		assertThat(houses1).hasSize(1);
	}
	
	
	@Test
	public void shouldNotPostHouse() {
		
		House house = new House();
		
		
		house.setHouseOwnerMail("vikas@gmail.com");
		house.setHouseType("2bhk");
		
		List<House> housedetails = houseRepo.findAll();
		
		assertThat(housedetails).isEmpty();
		
		try {
		houseRepo.save(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		List<House> houses = houseRepo.findAll();
		
		assertThat(houses).hasSize(0);
		houseRepo.deleteAll();
		
		House house1 = new House();
		
		
		house1.setHouseOwnerMail("vishal@gmail.com");
		house1.setHouseType("2bhk");
		
		List<House> housedetails1 = houseRepo.findAll();
		
		assertThat(housedetails1).isEmpty();
		
		
		try {
		houseRepo.save(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		List<House> houses1 = houseRepo.findAll();
		
		assertThat(houses1).hasSize(0);
	}
	
	
	
	
	
	
	@Test
	public void shouldReplyToUser() {
		
		MessageModel message = new MessageModel();
		message.setUserMail("guna@gmail.com");
		message.setOwnerMail("lalit@gmail.com");
		message.setQuestion("is it pet friendly");
		message.setAnswer("yes");
		
		List<MessageModel> messagedetail = messageRepo.findAll();
		assertThat(messagedetail).isEmpty();
		messageRepo.save(message);
		
		List<MessageModel> messages = messageRepo.findAll();
		assertThat(messages).hasSize(1);
		
		messageRepo.deleteAll();
		
		MessageModel message1 = new MessageModel();
		message1.setUserMail("surya@gmail.com");
		message1.setOwnerMail("vamsi@gmail.com");
		message1.setQuestion("is swimming pool?");
		message1.setAnswer("yes");
		
		List<MessageModel> messagedetail1 = messageRepo.findAll();
		assertThat(messagedetail1).isEmpty();
		messageRepo.save(message1);
		
		List<MessageModel> messages1 = messageRepo.findAll();
		assertThat(messages1).hasSize(1);
	}
	
	@Test
	public void shouldNotReplyToStudent() {
		
		MessageModel message = new MessageModel();
		message.setUserMail("pavam@gmail.com");
		message.setOwnerMail("varma@gmail.com");
		message.setQuestion("isthere any supermarket nearby?");
		message.setAnswer("yes");
		
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
		message1.setUserMail("sudha@gmail.com");
		message1.setOwnerMail("varun@gmail.com");
		message1.setQuestion("is there water facility in this?");
		message1.setAnswer("yes");
		
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



