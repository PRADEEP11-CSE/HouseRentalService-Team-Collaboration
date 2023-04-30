package com.hrs.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrs.app.model.Maintenance;



@Repository
public interface MaintenanceRepo extends JpaRepository<Maintenance, Long> {

}
