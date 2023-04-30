package com.hrs.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrs.app.model.ReportHouseModel;



@Repository
public interface ReportHouseRepo extends JpaRepository<ReportHouseModel, Long> {

	@Query( value = "select * from reports where user_type = 'user'", nativeQuery = true)
	List<ReportHouseModel> findAllByUserType();

	@Query( value = "select * from reports where user_type = 'houseowner'", nativeQuery = true)
	List<ReportHouseModel> findAllByOwnerType();

	@Query( value = "select * from reports where id = :id", nativeQuery = true)
	ReportHouseModel findReportById(@Param("id") Long id);

}
