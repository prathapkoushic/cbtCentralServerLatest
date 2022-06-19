package com.ttipl.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "candidate_seat_details")
public class CandidateSeatDetails implements Serializable {

	private static final long serialVersionUID = 7561960749183770263L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "candidate_id")
	private String candidateId;

	@Column(name = "room_name")
	private String roomName;

	@Column(name = "seatcode")
	private String seatcode;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getSeatcode() {
		return seatcode;
	}
	public void setSeatcode(String seatcode) {
		this.seatcode = seatcode;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
