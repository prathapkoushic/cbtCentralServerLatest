package com.ttipl.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "seating_plan")
public class SeatingPlan implements Serializable {

	private static final long serialVersionUID = 8227211296997003345L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "room_name")
	private String roomName;
	
	
	@Column(name = "candidateid")
	private String candidateid;

	
	
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
