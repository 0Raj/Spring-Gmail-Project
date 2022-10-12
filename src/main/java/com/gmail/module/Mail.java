package com.gmail.module;

import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Mail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int mailId;

	@JsonIgnore
	private ZonedDateTime timeStamp;

	@ManyToOne
	@JsonIgnoreProperties(value = { "firstName", "lastName", "mobileNumber", "dateOfBirth", "password", "role" })
	private User sender;

	@ManyToMany
	@JsonIgnoreProperties(value = { "firstName", "lastName", "mobileNumber", "dateOfBirth", "password", "role" })
	private List<User> recievers;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "content_id")
	private Content content;

	private String subject;

	private String body;

}
