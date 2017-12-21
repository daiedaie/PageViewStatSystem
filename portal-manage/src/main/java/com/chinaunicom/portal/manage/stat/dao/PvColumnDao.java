package com.chinauicom.portal.manage.stat.dao;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.chinauicom.portal.commons.dao.BaseDao; 
import com.chinauicom.portal.manage.stat.domain.PvColumn;

@Repository
public class PvColumnDao extends BaseDao { 

	
	/**
	 * 添加
	 * @param pvc 对象对应到数据库中的一条记录记录
	 * @return type : 
	 * @throws Exception
	 */
	public PvColumn insertByPvc(PvColumn pvc) throws Exception {
	    if(pvc == null) return null; 		
	    return (PvColumn)insert("PV.insertPvColumn", pvc);
    }

}


