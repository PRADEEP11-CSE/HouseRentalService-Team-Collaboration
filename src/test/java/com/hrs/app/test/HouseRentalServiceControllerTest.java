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

import com.hrs.app.dao.UserRepo;
import com.hrs.app.model.User;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase
public class HouseRentalServiceControllerTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepo userRepo;
	
	@Test
	public void storeUser() {
		
		User user = new User();
		
		user.setEmail("pinky@gmail.com");
		user.setUsername("pinky");
		user.setPassword("pinky");
		user.setUsertype("user");
		
		List<User> userdata = userRepo.findAll();
		
		assertThat(userdata).isEmpty();
		
		userRepo.save(user);
	    
	    List<User> users = userRepo.findAll();

	    assertThat(users).hasSize(1);
	    userRepo.deleteAll();
	    
	    User user1 = new User();
		
		user1.setEmail("Gopal@gmail.com");
		user1.setUsername("gopal");
		user1.setPassword("krishna");
		user1.setUsertype("houseowner");
		
		List<User> userdata1 = userRepo.findAll();
		
		assertThat(userdata1).isEmpty();
		
		userRepo.save(user1);
	    
	    List<User> users1 = userRepo.findAll();

	    assertThat(users1).hasSize(1);
	    
	  }
	
	@Test
	public void ShouldNotStoreUser() {
		
		User user = new User();
		
		user.setEmail("krsihna@gmail.com");
		user.setUsername("vamshi");
		user.setPassword("vamsi");
		user.setUsertype("user");
		
		List<User> userdata = userRepo.findAll();
		
		assertThat(userdata).isEmpty();
		
		try {
			userRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    List<User> users = userRepo.findAll();

	    assertThat(users).hasSize(0);

	    
	    User user1 = new User();
		
		user1.setEmail("rana@gmail.com");
		user1.setUsername("kiran");
		user1.setPassword("kiran");
		user1.setUsertype("houseowner");
		
		List<User> userdata1 = userRepo.findAll();
		
		assertThat(userdata1).isEmpty();
		
		try {
			userRepo.save(null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	    
	    List<User> users1 = userRepo.findAll();

	    assertThat(users1).hasSize(0);
	    
	  }
	

}
