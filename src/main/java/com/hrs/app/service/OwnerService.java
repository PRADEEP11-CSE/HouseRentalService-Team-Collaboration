package com.hrs.app.service;

import java.util.List;

import com.hrs.app.model.Appointment;
import com.hrs.app.model.House;

public interface OwnerService {
	
	void saveHouse(House house);
	
	House getHouse();
	
	List<House> getAllHousesByEmail(String emailId);
	
	void deleteHouse(Long id);
	
	House getHouseById(Long id);
	
	List<House> getAllHousesDetailsByEmail(String email);
	
	House getHouseDetailsById(Long id);
	
	void updateHouse(House house);
	
	List<Appointment> getAllAppointmentsByUserId(String email);

}
