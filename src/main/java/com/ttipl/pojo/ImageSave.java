package com.ttipl.pojo;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Image")
public class ImageSave
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="RegistrationId")
	private String registrationId;
	
	@Column(name="photo" , columnDefinition = "LONGTEXT")
	private String photo;
	
	@Column(name="fileData" , columnDefinition = "LONGTEXT")
	private String fileData;
	
	@Column(name="file")
	private File file;
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo) 
	{
		this.photo = photo;
	}
	public String getFileData() 
	{
		return fileData;
	}
	public void setFileData(String fileData) 
	{
		this.fileData = fileData;
	}
	public String getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	
	 
}
