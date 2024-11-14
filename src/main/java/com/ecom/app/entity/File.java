package com.ecom.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "files_table")
public class File {

	@Id
	@Column(name = "file_id_col")
	private Integer id;
	
	@Column(name = "file_name_col")
	private String fileName;
	
	
	@Column(name = "file_data_col")
	@Lob
	private byte[] fileData;
	
	}
