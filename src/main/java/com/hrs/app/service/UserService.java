package com.hrs.app.service;

import java.util.List;

import com.hrs.app.model.Appointment;
import com.hrs.app.model.Favourite;
import com.hrs.app.model.House;
import com.hrs.app.model.User;

public interface UserService {
	
	int saveUser(User user);
	
	User findUser(String email);
	
	User authenticateUser(User user);
	
	User findUserByUsername(String username);
	
	int validatePassword(User usermodel, String securityQuestion, String securityAnswer);
	
	void saveNewPassword(User usermodel);
	
	void deleteUser(Long id);
	
	List<House> getAllHouseDetails();
	
	void reserveHouse(Long houseId, Long userId);
	
	void saveAppointment(Appointment appointment);
	
	List<House> searchHouses(String searchKey);
	
	List<House> filterHouses(String city, String moveInDate);

	void savefavourites(Favourite favourite);

	List<House> findAllFavs(Long id);

}
