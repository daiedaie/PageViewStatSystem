/**
 * com.tydic.dbs.system.role.service.SysRoleService.java
 */
package com.chinauicom.portal.manage.staff.service;

import java.util.List;
import java.util.Map;

import com.chinauicom.portal.commons.Message;
import com.chinauicom.portal.commons.utils.Page;
import com.chinauicom.portal.manage.staff.domain.CultureStaffInfo;  

/**
 * 
 * @ClassName: CultureStaffInfoService 
 * @Description: TODO(好员工详情管理接口) 
 * @author 
 * @date 2017
 *
 */
public abstract interface CultureStaffInfoService {
 
	
	/**
     * 向数据库表中插入/修改一条角色记录
     * @param vo 与数据库中记录对应的值对象
     * @param flag 操作标识（0修改操作 1新增操作）
     * @return type : RpDir 返回插入操作是否成功
     * @throws Exception
     */
    public CultureStaffInfo save(CultureStaffInfo vo,  int flag) throws Exception;
    
    /**
     * 查找符合条件的所有数据库记录
     * @param pkId 与数据库中主键对应的值
     * @return type : 返回查询操作所有符合条件的记录的VO对象，操作失败返回null
     * @throws Exception
     */
	public CultureStaffInfo get(String pkId) throws Exception;
	/**
     * 根据角色编码删除角色权限关系数据
     * @param Id 
     * @return
     * @throws Exception
     */
    public CultureStaffInfo delete(CultureStaffInfo vo) throws Exception;

    public Page getPageByParamMap(Map params) throws Exception;
    
    public CultureStaffInfo getVoByParamMap(Map params) throws Exception;
 
	 /**
     * 根据标题首字母检索好员工信息列表</br>
     * 适用于下拉列表检索框数据获取
     * @param keyword
     * 				好员工标题首字母
     * @return 
     * @throws Exception
     */
	public List<CultureStaffInfo> searchStaffLstByKw(String keyword) throws Exception;
	

	/**
	 * 根据标题模糊检索好员工信息列表</br>
     * 适用于下拉列表检索框数据获取
	 * @param type 类型
	 * @param keyword 好员工标题
	 * @return
	 * @throws Exception
	 */
	public List<CultureStaffInfo> searchStaffLstLkTtl(String type,String keyword) throws Exception;
}
