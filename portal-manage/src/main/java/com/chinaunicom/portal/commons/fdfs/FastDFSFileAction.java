package com.chinauicom.portal.commons.fdfs;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinauicom.portal.commons.utils.UploadFile;


@Controller
@RequestMapping("/fastdfs")
public class FastDFSFileAction {
	
	@Resource(name = "uploadFile")
	private UploadFile uploadFile;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/uploadfile")
	@ResponseBody
	public Map<String,Object> uploadPic(HttpServletRequest request, MultipartFile upfile) {
		Map<String,Object> result = new HashMap<String, Object>();
		try {

			String file = uploadFile.uploadFile(upfile, "");
			
			result.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
            result.put("url",file);
            result.put("title", upfile.getOriginalFilename());
            result.put("original", upfile.getOriginalFilename());

		} catch (Exception e) {
			logger.error("上传文件到FastDFS出错:{}", e);
			result.put("state", "文件上传失败!");
            result.put("url","");
            result.put("title", "");
            result.put("original", "");
			
		}
		return result;
	}
}
