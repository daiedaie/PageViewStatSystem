/**
 * com.tydic.commons.utils.FileUtils.java
 */
package com.chinauicom.portal.commons.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

 /**
 * @file  UploadFileToDisk.java
 * @version 0.1
 * @todo 将文件上传到服务器上
 */
public class UploadFileToDisk implements UploadFile{

	@Value("${webpagePath}")
	private String webpagePath;
	
	private String resoucePath = "/resource";
	/**
	 * 上传文件
	 * @param file 文件
	 * @param fileType 文件类型 image、video、audio、file
	 * @return
	 */
	public String uploadFile(MultipartFile file , String fileType) throws IOException{ 
			//文件存储的绝对路径
			String filePath = webpagePath +  resoucePath  + File.separator + fileType + File.separator;
			String fileName = file.getOriginalFilename();
			int i=fileName.lastIndexOf("."); 
			String fileNewName = new Date().getTime()+""+(int)(Math.random()*1000000+100000);
			fileNewName += "." + fileName.substring(i+1); 
			File newFile = new File(filePath+fileNewName);			
			BufferedOutputStream stream;
			if(!newFile.getParentFile().exists()){
				newFile.getParentFile().mkdirs();
			}
			stream = new BufferedOutputStream(new FileOutputStream(newFile));
			stream.write(file.getBytes());
			stream.close();
			//文件的访问路径
			String fileUrl = resoucePath + "/" + fileType + "/" + fileNewName;
			return fileUrl;
		
	}
	
}
