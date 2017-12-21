package com.chinauicom.portal.commons.utils;

import java.io.IOException;

import org.csource.common.NameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.chinauicom.portal.commons.fdfs.FastDFSFile;
import com.chinauicom.portal.commons.fdfs.FileManager;

public class UploadFileToFastdfs implements UploadFile {
	
	@Autowired
	private FileManager fileManager;

	@Override
	public String uploadFile(MultipartFile file, String fileType) throws IOException {
		
		// 获取文件后缀名 
	    String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
	    FastDFSFile fastDFSFile = new FastDFSFile(file.getBytes(),ext);
	    NameValuePair[] meta_list = new NameValuePair[4];
	    meta_list[0] = new NameValuePair("fileName", file.getOriginalFilename());
	    meta_list[1] = new NameValuePair("fileLength", String.valueOf(file.getSize()));
	    meta_list[2] = new NameValuePair("fileExt", ext);
	    meta_list[3] = new NameValuePair("fileAuthor", "portal");
	    //文件全名需要存入数据库中
	    String filePath = fileManager.upload(fastDFSFile,meta_list);
	    return filePath;
	}

}
