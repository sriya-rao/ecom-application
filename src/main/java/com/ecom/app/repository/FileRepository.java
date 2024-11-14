package com.ecom.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecom.app.entity.File;

public interface FileRepository extends JpaRepository<File, Integer>{

	@Query("SELECT id,fileName FROM File")
	public List<Object[]> getFileIdAndName();
	
	
}
