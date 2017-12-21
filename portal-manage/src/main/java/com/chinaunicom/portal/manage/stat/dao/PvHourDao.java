package com.chinauicom.portal.manage.stat.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.chinauicom.portal.commons.dao.BaseDao; 

import com.chinauicom.portal.manage.stat.domain.PvHour;

@Repository
public class PvHourDao extends BaseDao { 

	
	/**
	 * 添加
	 * @param pvh 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public PvHour insertByPvh(PvHour pvh) throws Exception {
	    if(pvh == null) return null; 		
	    return (PvHour)insert("PV.insertPvHour", pvh);
    }

}


/**public interface PvHourDao {
	
	public List<PvHour> getHour(String key);

}  */
