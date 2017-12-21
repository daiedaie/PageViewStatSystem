package com.chinauicom.portal.commons.utils;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFile {
	/**
	 * 上传文件
	 * @param file 文件
	 * @param fileType 文件类型 image、video、audio、file
	 * @return
	 */
	public String uploadFile(MultipartFile file , String fileType) throws IOException;
}
