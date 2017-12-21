/**
 * com.tydic.dbs.bo.SysRoleBo.java
 */
package com.chinauicom.portal.manage.staff.service.bo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.chinauicom.portal.commons.utils.DateUtil;
import com.chinauicom.portal.commons.utils.DateUtilsC;
import com.chinauicom.portal.commons.utils.Page;
import com.chinauicom.portal.commons.Message;
import com.chinauicom.portal.commons.define.WcsDefinition;
import com.chinauicom.portal.manage.issue.news.dao.IssueNewsInfoDao;
import com.chinauicom.portal.manage.issue.news.service.IssueNewsInfoService;
import com.chinauicom.portal.manage.news.domain.NewsInfo;
import com.chinauicom.portal.manage.staff.dao.CultureStaffInfoDao;
import com.chinauicom.portal.manage.staff.domain.CultureStaffInfo;
import com.chinauicom.portal.manage.staff.service.CultureStaffInfoService;

import com.chinauicom.portal.manage.system.menu.dao.SysFunOperateDao;
import com.chinauicom.portal.manage.system.menu.mapper.SysFunOperate;
import com.chinauicom.portal.manage.system.role.dao.SysRoleDao;
import com.chinauicom.portal.manage.system.role.dao.SysRoleMenuDao;
import com.chinauicom.portal.manage.system.role.mapper.SysOperRole;
import com.chinauicom.portal.manage.system.role.mapper.SysRole;
import com.chinauicom.portal.manage.system.role.mapper.SysRoleMenu;
import com.chinauicom.portal.manage.system.role.mapper.SysRoleOperate;
import com.chinauicom.portal.manage.system.role.service.SysRoleService;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 
 * @ClassName:  CultureStaffInfoBo 
 * @author 
 * @date 2017
 *
 */
@Service
public class CultureStaffInfoBo implements CultureStaffInfoService {
	@Autowired
	private CultureStaffInfoDao cultureStaffInfoDao; 
 
	/**
     * 向数据库表中插入/修改一条角色记录
     * @param vo 与数据库中记录对应的值对象
     * @param flag 操作标识（0修改操作 1新增操作）
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public CultureStaffInfo save(CultureStaffInfo vo,  int flag) throws Exception{
    	if(vo == null) return null;		
		if(flag == 0) {  
			
			return cultureStaffInfoDao.updateByVoNotNull(vo); 
			
		} else {		 
			
			return cultureStaffInfoDao.insertByVo(vo);
			
		}
    }
    /**
     * 删除符合条件的所有数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : boolean 返回删除操做是否成功，操作失败返回false
     */
    public CultureStaffInfo delete(CultureStaffInfo vo) throws Exception{
    	if(vo == null) return null; 
    	return cultureStaffInfoDao.updateByStatus(vo);
    }
    /**
     * 查找符合条件的所有数据库记录
     * @param pkid 与数据库中主键对应的值
     * @return type : 返回查询操作所有符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public CultureStaffInfo get(String pkId) throws Exception{
		if(pkId==null) return null;
     	return cultureStaffInfoDao.selectByPk(pkId);
	}
	 
    
    @Override
	public Page getPageByParamMap(Map params) throws Exception {
		if (params == null) return null;
		return cultureStaffInfoDao.queryForPage(params);
	}
    
    

	@Override
	public CultureStaffInfo getVoByParamMap(Map params) throws Exception {
		// TODO Auto-generated method stub
		return  cultureStaffInfoDao.queryForObject(params);
	}
	

	@Override
	public List<CultureStaffInfo> searchStaffLstByKw(String keyword) 
			throws Exception {
		return cultureStaffInfoDao.selStaffByTtlLtt(keyword);
	}
	@Override
	public List<CultureStaffInfo> searchStaffLstLkTtl(String type,String keyword) 
			throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		map.put("type", type);
		map.put("keyword", keyword);
		return cultureStaffInfoDao.selStaffLkTtl(map);
	}
	
	
	
	
	
}
