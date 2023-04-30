package com.hrs.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrs.app.model.Complaint;
import com.hrs.app.model.House;
import com.hrs.app.model.ReviewPropertyModel;



@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Long> {

	@Query( value = "select * from complaints where id = :id", nativeQuery = true)
	Complaint findComplaintById(@Param("id") Long id);
	

}
