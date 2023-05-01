package com.hrs.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "houses")
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String houseName;
	private String houseRent;
	private String houseOwnerMail;
	private String noOfBedrooms;
	private String noOfBathrooms;
	@Lob
	private byte[] document;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String housePhoto;
	private String doorNo;
	private String street;
	private String city;
	private String zipCode;
	private String houseType;
	private String parking;
	private String petFriendly;
	private String isBooked;
	private String isHide;
	private String isVerified;
	private String lawn;
	private String availableFrom;


}
