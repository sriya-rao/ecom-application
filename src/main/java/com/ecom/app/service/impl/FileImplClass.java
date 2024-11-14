package com.ecom.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.app.entity.File;
import com.ecom.app.repository.FileRepository;
import com.ecom.app.service.FileServiceInterface;

@Service
public class FileImplClass implements FileServiceInterface{
	
	@Autowired
	FileRepository repository;

	@Override
	public Boolean saveFile(File file) {
        repository.save(file);
		return true;
	}

	@Override
	public List<Object[]> getFileIdAndName() {
		// TODO Auto-generated method stub
		return repository.getFileIdAndName();
	}
	
	public File getFileById(Integer id) {
		return repository.findById(id).get();
	}

	@Override
	public void deleteFileById(Integer id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
