package com.chinauicom.portal.commons.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.chinauicom.portal.commons.fdfs.FileManager;

public class DeleteFastdfsFile {
	
	@Autowired
	private FileManager fileManager;

	public String deleteFile(String fileName) throws IOException {
		
		String substr = fileName.substring(fileName.indexOf("group"));
	    String group = substr.split("/")[0];
	    String remoteFileName = substr.substring(substr.indexOf("/")+1);
	    int isSuccess=fileManager.deleteFile(group, remoteFileName);
	    String result=(isSuccess==0 ? "success":"false" ); 
	    return result;
	}

}
