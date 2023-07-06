package com.harshit.blogapi.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.harshit.blogapi.services.FileService;


@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		String name = file.getOriginalFilename();
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		String filePath = path + File.pathSeparator + fileName1;
		
		
		File f = new File(path);
		if(!f.exists())
			f.mkdir();
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return filePath;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		String filePath = path + File.pathSeparator + filename;
		InputStream is = new FileInputStream(filePath);
		
		return is;
	}

}

