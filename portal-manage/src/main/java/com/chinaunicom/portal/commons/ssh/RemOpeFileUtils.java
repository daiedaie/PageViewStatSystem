package com.chinauicom.portal.commons.ssh;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;

/**
 * 远程操作Linux服务器文件工具
 * @author 
 * @since 2017/6/28
 */
@Service
public class RemOpeFileUtils{
	@Value("${scp.ip}")
	String ip;
	@Value("${scp.port}")
	String port;
	@Value("${scp.username}")
	String username;
	@Value("${scp.password}")
	String password;
	@Value("${scp.targetrootdirectory}")
	String targetRootDirectory;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 将本地某文件复制到指定远程Linux服务器的指定目录
	 * 
	 * @author 
	 * 
	 * @since 2017/6/28
	 * 
	 * @param ip 
	 * 				远程服务器IP
	 * @param username 
	 * 				服务器登录用户
	 * @param password 
	 * 				服务器登录密码
	 * @param localfile 
	 * 				本地文件(例：/test/test.xml)
	 * @param remoteTargetDirectory 
	 * 				远程目录(例：/test)
	 * @throws Exception 
	 * 				IO异常或认证异常
	 */
	public void remCpFile(String localfile, String remoteTargetDirectory) 
					throws Exception {
		Connection conn = null;
		SCPClient scpClient = null;
		try {
			//连接
			conn = new Connection(ip,Integer.parseInt(port));
			conn.connect();
			//认证
			boolean isAuthed = conn.authenticateWithPassword(username, password);
			//认证成功
			if(isAuthed) {
				//创建SCP客户端 
				scpClient = conn.createSCPClient();
				//从本地复制文件到远程目录   
				logger.info("scp：{}：{}",localfile,targetRootDirectory + remoteTargetDirectory);
				scpClient.put(localfile, targetRootDirectory + remoteTargetDirectory, "0755"); //常规权限  0755 0644
				
			} else {
				throw new Exception("login exception,user or password error!");
			}
		} catch (IOException e) {
			logger.error("执行scp命令失败：", e);
			throw new IOException(e);
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	/**
	 * 在指定远程Linux服务器上执行命令</br>
	 * 不支持执行结果
	 * 
	 * @author 
	 * 
	 * @since 2017/6/28
	 * 
	 * @param ip 
	 * 				远程服务器IP
	 * @param username 
	 * 				服务器登录用户
	 * @param password 
	 * 				服务器登录密码
	 * @param cmd 
	 * 				标准Linux命令
	 * @throws Exception
	 * 				IO异常或认证异常
	 */
    public void execCommand(String cmd) 
					throws Exception {  
    	
        Connection conn = null;
        Session session = null; 
		try {
			//连接
			conn = new Connection(ip,Integer.parseInt(port));
			conn.connect();
			//认证
			boolean isAuthed = conn.authenticateWithPassword(username, password);
			//认证成功
			if(isAuthed) {
				session = conn.openSession();    
                session.execCommand(cmd); 
			} else {
				throw new Exception("login exception,user or password error!");
			}
		} catch (IOException e) {
			logger.error("执行命令失败：", e);
			throw new IOException(e);
		} finally {
			if (session != null) {
				session.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
          
    } 
    /**
     * 创建目录
     * @param dirPath 目录路径
     * @throws Exception
     */
    public void mkdir(String dirPath) throws Exception{
    	String targetDirPath =  targetRootDirectory + dirPath;
    	String cmd = "mkdir "  + targetDirPath;
    	logger.info(cmd);
    	
    	execCommand(cmd);
    }
	
}
