package com.hrs.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrs.app.model.House;
import com.hrs.app.model.Lease;

public interface LeaseRepo extends JpaRepository<Lease, Long>{

	@Query( value = "select * from leases where id = :id", nativeQuery = true)
	Lease findLeaseById(@Param("id") Long id);

}
