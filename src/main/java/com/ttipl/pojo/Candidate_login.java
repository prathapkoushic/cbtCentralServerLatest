package com.ttipl.pojo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "candidate_login")
public class Candidate_login
{
	@Id
	private String candidate_id;
	private LocalDateTime login_time;
	private String ipaddress;
	private int candidate_login_id;
	private String ip_address;

}
