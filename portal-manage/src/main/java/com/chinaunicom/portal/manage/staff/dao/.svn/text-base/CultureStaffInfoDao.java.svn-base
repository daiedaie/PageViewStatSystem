package com.chinauicom.portal.manage.staff.dao;

import java.util.List;
import java.util.Map;

import com.chinauicom.portal.commons.dao.BaseDao;
import com.chinauicom.portal.commons.utils.BaseVO;
import com.chinauicom.portal.commons.utils.Page; 
import com.chinauicom.portal.manage.staff.domain.CultureStaffInfo;  

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * @file  CultureStaffInfoDao.java
 * @author 
 * @version 0.1
 * @CultureStaffInfo新闻详情类
 */
@Repository
public class CultureStaffInfoDao extends BaseDao {
	/**
	 * 好员工添加
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public CultureStaffInfo insertByVo(CultureStaffInfo vo) throws Exception {
	    if(vo == null) return null; 		
	    return (CultureStaffInfo)insert("CULTURE_STAFF_INFO.insertByVo", vo);
    }
	/**
	 * 新闻修改
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public CultureStaffInfo updateByVoNotNull(CultureStaffInfo vo) throws Exception {
		if(vo == null) return null;
		update("CULTURE_STAFF_INFO.updateByVoNotNull", vo);
		return vo;
	}
	/**
	 * 新闻修改
	 * @param vo 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public CultureStaffInfo updateByStatus(CultureStaffInfo vo) throws Exception {
		if(vo == null) return null;
		update("CULTURE_STAFF_INFO.updateByStatus", vo);
		return vo;
	}
	
	/**
     * 搜索数据库中与传入的主键对应的记录
     * @param pkid 与数据库主键对应
     * @return type : 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception
     */
    public CultureStaffInfo selectByPk(String pkId) throws Exception {
    	if(pkId ==null) return null;
    	return (CultureStaffInfo)queryForObject("CULTURE_STAFF_INFO.selectByPk", pkId);
    }
     
	/**
	 * 分页查询新闻，根据新闻标题模糊查询
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Page queryForPage(@SuppressWarnings("rawtypes") Map params) throws Exception {
		if(params ==null) return null;
		return queryForPage("CULTURE_STAFF_INFO.selectByStaffTitle", params, null);
	}
	
	public CultureStaffInfo queryForObject(@SuppressWarnings("rawtypes") Map params) throws Exception {
    	if(params ==null) return null;
    	return (CultureStaffInfo)queryForObject("CULTURE_STAFF_INFO.selectByStaffTitle", params);
    }
	
	/**
 	 * 根据新闻标题首字母查询新闻信息列表
     * @param keyword
     * 				新闻标题首字母
 	 * @return
 	 * @throws Exception
 	 */
 	@SuppressWarnings("unchecked")
 	public List<CultureStaffInfo> selStaffByTtlLtt(String keyword) 
 			throws Exception {
 		if (keyword == null) 
 			return null;
 		return queryForList("CULTURE_STAFF_INFO.selStaffByTtlLtt", keyword);
 		
 	}
 	
 	/**
 	 * 根据新闻标题模糊查询新闻信息列表
     * @param keyword
     * 				新闻标题
 	 * @return
 	 * @throws Exception
 	 */
 	@SuppressWarnings("unchecked")
 	public List<CultureStaffInfo> selStaffLkTtl(Map<String,String> info) 
 			throws Exception {
 		if (info == null) 
 			return null;
 		return queryForList("CULTURE_STAFF_INFO.selStaffLkTtl", info);
     	}
 	
 	
}

	
	/**
     * 搜索数据库中查询 列表页最新5条新闻
     * @param pkid 与数据库主键对应
     * @return type : 返回查询操作所有符合条件的记录的VO对象集合，操作失败返回null
	 * @throws Exception

    public CultureStaffInfo selectNewsListByPk(@SuppressWarnings("rawtypes") Map params) throws Exception {
    	if(params ==null) return null;
    	return (CultureStaffInfo)queryForObject("NEWS_INFO.selectNewsListByPk", params);
    }  */
    
    
 	/**
 	 * 查询新闻列表页前5条新闻
 	 * @param params
 	 * @return
 	 * @throws Exception
 	
 	@SuppressWarnings("unchecked")
	public List<NewsInfo> selectNewsList(Map<String, Object> sqlMap) throws Exception {
 		if(sqlMap ==null) return null;
 		return queryForList("NEWS_INFO.selectList",sqlMap);
 	} */
 	
 	/**
 	 * 查询删除是否为当月最后一条新闻
 	 * @param String
 	 * @return int
 	 * @throws Exception
 
 	public int selectCountByPostYearMon(Map<String, Object> sqlMap) throws Exception {
 		if(sqlMap ==null) return 0;
 		return (int)queryForInt("NEWS_INFO.selectCountByPostYearMon",sqlMap);
 	}
 	
 	public int selectAllCount(Map<String, Object> sqlMap) throws Exception {
 		if(sqlMap ==null) return 0;
 		return (int)queryForInt("NEWS_INFO.selectAllCount",sqlMap);
 	}	 */
 	/**
 	 * 获取最新的两条新闻
 	 * @param type 类型
 	 * @return
 	 * @throws Exception
 	
 	@SuppressWarnings("unchecked")
 	public List<NewsInfo> selectLatestTowNews(String type) throws Exception {
 		if(type ==null) return null;
 		return queryForList("NEWS_INFO.selectLatestTowNews",type);
 	} */
 	
 	