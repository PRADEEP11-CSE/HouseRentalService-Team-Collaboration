package com.hrs.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrs.app.model.ReportUserModel;



@Repository
public interface ReportUserRepo extends JpaRepository<ReportUserModel, Long> {


	@Query( value = "select * from report_users where owner_mail = :email", nativeQuery = true)
	List<ReportUserModel> findMyAllReports(@Param("email") String email);

	@Query( value = "select * from report_users where id = :id", nativeQuery = true)
	ReportUserModel findReportById(Long id);

}
