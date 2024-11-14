package com.ecom.app.service;

import java.util.List;

import com.ecom.app.entity.File;

public interface FileServiceInterface {

	public Boolean saveFile(File file);
	
	public List<Object[]> getFileIdAndName();
	
	public void deleteFileById(Integer id);
	
	public File getFileById(Integer id);


}
