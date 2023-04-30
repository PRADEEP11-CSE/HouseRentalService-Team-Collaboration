package com.hrs.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrs.app.model.ReportOwnerModel;



@Repository
public interface ReportOwnerRepo extends JpaRepository<ReportOwnerModel, Long> {


	@Query( value = "select * from report_owners where user_mail = :email", nativeQuery = true)
	List<ReportOwnerModel> findMyAllReports(@Param("email") String email);

}
