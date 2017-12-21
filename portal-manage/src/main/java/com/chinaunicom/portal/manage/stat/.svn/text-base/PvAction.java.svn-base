package com.chinauicom.portal.manage.stat;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chinauicom.portal.commons.utils.DateUtil;
import com.chinauicom.portal.commons.utils.DateUtils;
import com.chinauicom.portal.commons.utils.DateUtilsC;
import com.chinauicom.portal.commons.utils.Page;
import com.chinauicom.portal.commons.utils.UploadFile;
import com.chinauicom.portal.commons.Message;
import com.chinauicom.portal.commons.constant.WcsSessionConstant;
import com.chinauicom.portal.commons.define.WcsDefinition;
import com.chinauicom.portal.commons.ssh.RemOpeFileUtils;
import com.chinauicom.portal.manage.index.domain.IndexColumnInfo;
import com.chinauicom.portal.manage.index.service.IndexColumnInfoService;
import com.chinauicom.portal.manage.news.domain.NewsInfo;
import com.chinauicom.portal.manage.news.domain.NewsLeft;
import com.chinauicom.portal.manage.news.domain.NewsSelect;
import com.chinauicom.portal.manage.news.service.NewsInfoService;
import com.chinauicom.portal.manage.news.service.NewsLeftService;
import com.chinauicom.portal.manage.news.service.NewsSelectService;
import com.chinauicom.portal.manage.page.PageCreatService;
import com.chinauicom.portal.manage.system.menu.mapper.SysFunOperate;
import com.chinauicom.portal.manage.system.menu.service.SysFunOperateService;
import com.chinauicom.portal.manage.system.operator.mapper.SysOperator;
import com.google.gson.Gson;

/**
 * 
 * @ClassName: NewsInfoAction
 * @Description: TODO(好员工管理控制类)
 * @author jiangcheng
 * @date 2017
 *
 */
@Controller
@RequestMapping("/pvAction")
public class PvAction {

	@Autowired
	private NewsInfoService newsInfoService;

	@Autowired
	private NewsSelectService newsSelectService;


	@Autowired
	private SysFunOperateService sysFunOperateService;

	
	@Value("${webpagePath}")
	private String webpagePath;

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	/**
	 * 跳转到角色列表页面
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/toNewsList.do")
	public String toNewsList(HttpServletRequest request) {
		try {
			Map<String, String> statusHash = WcsDefinition.WcsCommonStatus.WCS_COMMON_STATUS_MAP;
			HttpSession session = request.getSession();
			SysOperator operator = (SysOperator) session.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
			List<SysFunOperate> funOperateList = sysFunOperateService
					.getHasAuthFunOperateByMenuCodeAndOperId("NEWS_LIST", operator.getOperId());
			request.setAttribute("statusHash", statusHash);
			request.setAttribute("funOperateList", funOperateList);
		} catch (Exception e) {
			logger.error("发布好员工信息出错:{}", e);
		}
		return "news/newsList";
	}

	/**
	 * 好员工列表页数据查询函数
	 * 
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/getNewsList.do")
	public @ResponseBody Map<String, Object> getNewsList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String newsTitle1 = request.getParameter("newsTitle");
			String newsCategory = request.getParameter("newsCategory");
			if (StringUtils.isNotEmpty(newsTitle1)) {
				newsTitle1 = newsTitle1.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
			}
			final String newsTitle = newsTitle1;
			final String status = request.getParameter("status");
			final String page = request.getParameter("page");

			params.put(Page.CURR_PAGE, Integer.parseInt(page));
			params.put(Page.PAGE_SIZE, Page.DEFAULT_PAGE_SIZE);
			if (StringUtils.isNotEmpty(newsTitle))
				params.put("newsTitle", newsTitle);
			if (StringUtils.isNotEmpty(status))
				params.put("status", status);
			if (StringUtils.isNotEmpty(newsCategory))
				params.put("newsCategory", newsCategory);
			List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
			Map<String, Object> hash = new HashMap<String, Object>();
			hash.put("createTime", "desc");
			orderBy.add(hash);
			params.put("orderBy", orderBy);

			Page newsPage = newsInfoService.getPageByParamMap(params);
			List<NewsInfo> newsList = newsPage.getList();

			map.put("totalNumber", newsPage.getTotalNumber());
			map.put("totalPage", newsPage.getTotalPage());
			map.put("rows", newsList);
		} catch (Exception e) {
			logger.error("获取好员工列表出错:", e);
		}
		return map;
	}
}
