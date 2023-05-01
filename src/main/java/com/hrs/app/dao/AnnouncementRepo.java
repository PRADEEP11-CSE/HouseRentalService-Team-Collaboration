package com.hrs.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.app.model.Announcement;


public interface AnnouncementRepo extends JpaRepository<Announcement, Long>{

}
