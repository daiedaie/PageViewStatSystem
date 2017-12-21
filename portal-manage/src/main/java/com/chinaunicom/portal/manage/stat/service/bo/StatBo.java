package com.chinauicom.portal.manage.stat.service.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.apache.commons.lang.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chinauicom.portal.manage.stat.service.PvService;
import com.fasterxml.jackson.core.sym.Name;
import com.chinauicom.portal.manage.stat.dao.PvColumnDao;
import com.chinauicom.portal.manage.stat.dao.PvHourDao;
import com.chinauicom.portal.manage.stat.domain.PvColumn;
import com.chinauicom.portal.manage.stat.domain.PvHour;
import com.chinauicom.portal.commons.redis.RedisAPI;

import com.chinauicom.portal.commons.utils.DateUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="PvService")
public class StatBo implements PvService {

	/*
	 * key : pv:yyyyMMdd
	 * 
	 */

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PvColumnDao pvColumnDao;
	@Autowired
	private PvHourDao pvHourDao;

	public void getColumn() throws Exception {
		logger.info("栏目PV数据导入开始：{}",DateUtil.DateToString5(new Date()));
		
		Date data = DateUtil.beforeDay(new Date(), 1);
		getColumnByDay(DateUtil.DateToString2(data));
		
		logger.info("栏目PV数据导入完成：{}",DateUtil.DateToString5(new Date()));
	}



	public void pvHour() throws Exception {
		logger.info("时间PV数据导入开始：{}",DateUtil.DateToString5(new Date()));
		Date data = DateUtil.beforeDay(new Date(), 1);
		pvHourByDay(DateUtil.DateToString2(data));
		logger.info("时间PV数据导入完成：{}",DateUtil.DateToString5(new Date()));
	}

	/*
	 * public PvColumn save(PvColumn pvc) throws Exception{ if(pv == null)
	 * return null; return PvColumnDao.insertByPvc(pvc);
	 * 
	 * }
	 */

	/*
	 * public PvColumn save(PvColumn pvc, int flag) throws Exception { if(pvc ==
	 * null) return null; return PvColumnDao.insertByPvc(pvc); }
	 */

	/*public PvColumn save(PvColumn pvc, int flag) throws Exception {
		if (pvc == null)
			return null;
		return pvColumnDao.insertByPvc(pvc);
	}

	public PvHour save(PvHour pvh, int flag) throws Exception {
		if (pvh == null)
			return null;
		return pvHourDao.insertByPvh(pvh);
	} */



	@Override
	public void getColumnByDay(String day) throws Exception {
		String key = "pv:" + day;
		// key --> year month day
		Map<String, String> pc = RedisAPI.hgetall(key);
		List<PvColumn> pagePvs = new ArrayList<PvColumn>();
		PvColumn indexPv = new PvColumn();

		indexPv.setKey(key);
		indexPv.setUrl("/");
		indexPv.setColumn("/index.html");
		indexPv.setPv(0);
		indexPv.setYear(key.substring(3, 7));
		indexPv.setMonth(key.substring(7, 9));
		indexPv.setDay(key.substring(9, 11));

		for (Map.Entry<String, String> entry : pc.entrySet()) {
			String url = entry.getKey();
			int pv = Integer.parseInt(entry.getValue());
			logger.info(url + ":" + pv);
			String[] str = url.split("/");
			// 将/? /. /index均统计为首页地址
			// indexOf()的用法：返回字符中indexof（string）中字串string在父串中首次出现的位置，从0开始！没有返回-1；方便判断和截取字符串！
			if (url.equals("/index.html") || url.indexOf("/?") == 0 || url.indexOf("/#") == 0 
					|| url.equals("/") || url.indexOf("www.chinaunicom") == 0 || str.length < 2) {
				indexPv.setPv(indexPv.getPv() + pv);
			} else {
				PvColumn pvColumn = new PvColumn();
				pvColumn.setPv(pv);
				pvColumn.setUrl(url.length() > 256?url.substring(0, 256):url);
				// 分割后取数据
				pvColumn.setKey(key);
				pvColumn.setColumn(str[1].length()>128?str[1].substring(0, 128):str[1]);
				pvColumn.setYear(key.substring(3, 7));
				pvColumn.setMonth(key.substring(7, 9));
				pvColumn.setDay(key.substring(9, 11));
				// 添加数据
				pvColumnDao.insertByPvc(pvColumn);

			}
		}
		pvColumnDao.insertByPvc(indexPv);
		
	}



	@Override
	public void pvHourByDay(String day) throws Exception {
		String[] hour = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
				"15", "16", "17", "18", "19", "20", "21", "22", "23" };
		String k = "pv:" + day;
		for (int j = 0; j < hour.length; j++) {
			String key = k + hour[j];
			String pvStr = RedisAPI.get(key);
			if (pvStr == null) {
				pvStr = "0";
			}

			PvHour pvHour = new PvHour();
			pvHour.setKey(key);
			pvHour.setYear(key.substring(3, 7));
			pvHour.setMonth(key.substring(7, 9));
			pvHour.setDay(key.substring(9, 11));
			pvHour.setHour(key.substring(11, 13));
			pvHour.setPv(Integer.parseInt(pvStr));
			pvHourDao.insertByPvh(pvHour);

		}
		
	}

	/*
	 * public List<PagePv> getHour(String key,String field) { String key2 =
	 * "pv:"+DateUtil.DateToString7(new Date()); String pv =
	 * RedisAPI.hget(key2,field); Map<String, String> ph =
	 * RedisAPI.hgetall(key2); List <PagePv> pagePvs = new ArrayList<PagePv>();;
	 * for (Map.Entry<String, String> entry : ph.entrySet()){ PagePv ppp= new
	 * PagePv(); ppp.setPv(Integer.parseInt(pv));
	 * ppp.setKey(Integer.parseInt(key)); ppp.setField(Integer.parseInt(field));
	 * pagePvs.add(ppp);
	 * 
	 * } return pagePvs;
	 * 
	 * }
	 */

	/*
	 * public PagePv getHour(String key,String field) { String key2 =
	 * "pv"+DateUtil.DateToString2(new Date()); String pv =
	 * RedisAPI.hget(key2,field); Map<String, String> p =
	 * RedisAPI.hgetall(key2);
	 * 
	 * for (Map.Entry<String, String> entry : p.entrySet()){ PagePv pagePv = new
	 * PagePv(); pagePv.setPv(Integer.parseInt(pv));
	 * pagePv.setKey(Integer.parseInt(key));
	 * pagePv.setField(Integer.parseInt(field)); return pagePv; } return null;
	 * 
	 * }
	 */

}
