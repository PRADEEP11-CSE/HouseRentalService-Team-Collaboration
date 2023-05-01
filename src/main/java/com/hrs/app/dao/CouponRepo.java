package com.hrs.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrs.app.model.Coupon;


public interface CouponRepo extends JpaRepository<Coupon, Long>{

}
