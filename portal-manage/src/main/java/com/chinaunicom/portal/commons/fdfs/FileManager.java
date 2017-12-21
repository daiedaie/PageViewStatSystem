package com.chinauicom.portal.commons.fdfs;

import java.io.File;
import java.io.IOException;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <strong>类概要： FastDFS Java客户端工具类</strong> <br>
 * <strong>创建时间： 2016-9-26 上午10:26:48</strong> <br>
 * 
 * @Project springmvc-main(com.wl.bean)
 * @author Wang Liang
 * @version 1.0.0
 */
@Service
public class FileManager {

    private static final long serialVersionUID = 1L;
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;
    
    @Value("${fdfs.protocol}")
	private String PROTOCOL;
	
	@Value("${fdfs.separator}")
	private String SEPARATOR;
	
	@Value("${fdfs.tracker_ngnix_addr}")
	private  String TRACKER_NGNIX_ADDR;
	
	@Value("${fdfs.tracker_ngnix_port}")
	private  String TRACKER_NGNIX_PORT;
	
	@Value("${fdfs.client_config_file}")
	private String CLIENT_CONFIG_FILE;
	
	@Value("${fdfs.web_url}")
	private String WEB_URL;

    private  void init() {
        try {
            String classPath = new File(FileManager.class.getResource("/").getFile()).getCanonicalPath();

            String fdfsClientConfigFilePath = classPath + File.separator + CLIENT_CONFIG_FILE;
            ClientGlobal.init(fdfsClientConfigFilePath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();

            storageClient = new StorageClient(trackerServer, storageServer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <strong>方法概要： 文件上传</strong> <br>
     * <strong>创建时间： 2016-9-26 上午10:26:11</strong> <br>
     * 
     * @param FastDFSFile
     *            file
     * @return fileAbsolutePath
     * @author Wang Liang
     */
    public  String upload(FastDFSFile file,NameValuePair[] valuePairs) {
        String[] uploadResults = null;
        try {
        	if(storageClient == null)
        		init();
        	uploadResults = storageClient.upload_file(file.getContent(),file.getExt(), valuePairs);
        
        		
        } catch (Exception e) {
            e.printStackTrace();
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        String fileAbsolutePath = WEB_URL
                + SEPARATOR + groupName
                + SEPARATOR + remoteFileName;
        return fileAbsolutePath;
    }
    
    
    
    /**
     * <strong>方法概要： 文件下载</strong> <br>
     * <strong>创建时间： 2016-9-26 上午10:28:21</strong> <br>
     * 
     * @param String
     *            groupName
     * @param String
     *            remoteFileName
     * @return returned value comment here
     * @author Wang Liang
     */
    public  ResponseEntity<byte[]> download(String groupName,
            String remoteFileName) {
        byte[] content = null;
        HttpHeaders headers = new HttpHeaders();
        try {
        	if(storageClient == null)
        		init();
            content = storageClient.download_file(groupName, remoteFileName);
        	
            headers.setContentDispositionFormData("attachment",  new String(remoteFileName.getBytes("UTF-8"),"iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
    }
    
    /**
     *  @Title: deleteFile  
     *  @Description: TODO 删除文件
     *  @param groupName 组名：group1
     *  @param remoteFileName 文件名称： M00/00/00/CgGDo1lkkgyAbetdAACcj4vj100747.jpg
     *  @return 0：成功；1：出错；2：文件不存在；
     */
    public int deleteFile(String groupName, String remoteFileName) {  
      int i;
	try {
		if(storageClient == null)
    		init();
		i = storageClient.delete_file(groupName, remoteFileName);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return 1;
	}  
      return i;
    }
}