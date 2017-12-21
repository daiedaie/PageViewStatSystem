package com.chinauicom.portal.manage.staff;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.LinkedList;
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
//import org.springframework.web.multipart.MultipartFile;

import com.chinauicom.portal.commons.utils.DateUtil;
import com.chinauicom.portal.commons.utils.DateUtils;
import com.chinauicom.portal.commons.utils.DateUtilsC;
import com.chinauicom.portal.commons.utils.Page;
import com.chinauicom.portal.commons.utils.UploadFile;
import com.chinauicom.portal.commons.Message;
import com.chinauicom.portal.commons.constant.WcsSessionConstant;
import com.chinauicom.portal.commons.define.WcsDefinition;
//import com.chinauicom.portal.commons.ssh.RemOpeFileUtils;
//import com.chinauicom.portal.manage.index.domain.IndexColumnInfo;
//import com.chinauicom.portal.manage.index.service.IndexColumnInfoService;
import com.chinauicom.portal.manage.staff.domain.CultureStaffInfo;
import com.chinauicom.portal.manage.staff.service.CultureStaffInfoService;

import com.chinauicom.portal.manage.page.PageCreatService;
import com.chinauicom.portal.manage.system.menu.mapper.SysFunOperate;
import com.chinauicom.portal.manage.system.menu.service.SysFunOperateService;
import com.chinauicom.portal.manage.system.operator.mapper.SysOperator;
//import com.google.gson.Gson;



/**
 * 
 * @ClassName: CultureStaffInfoAction
 * @Description: TODO(好员工管理控制类)
 * @author Daolin Zhang
 * @date 2017
 *
 */
@Controller
@RequestMapping("/staffInfo")
public class CultureStaffInfoAction {

	@Autowired
	private CultureStaffInfoService CultureStaffInfoService;
	
		
	@Autowired
	private SysFunOperateService sysFunOperateService;

	@Autowired
	private PageCreatService pageCreatService;


	@Resource(name = "uploadFile")
	private UploadFile uploadFile;
	
	@Value("${webpagePath}")
	private String webpagePath;

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	


	
	/**
	 * 删除好员工
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/delStaff.do")
	@ResponseBody
	public Message delStaff(HttpServletRequest request, CultureStaffInfo culturestaffinfo) {
		Message msg = new Message();
		try {		
			int pkId = culturestaffinfo.getId();
			if (pkId != 0) {
			// 删除好员工详细表
			culturestaffinfo.setStatus("0");
			CultureStaffInfoService.delete(culturestaffinfo);	
			}
			msg.setFlag(true);
			msg.setMsg("删除好员工成功");

		} catch (Exception e) {
			logger.error("删除好员工出错:{}", e);
			msg.setFlag(false);
			msg.setMsg("操作异常");
		}
		return msg;
	}

	/**
	 * 好员工信息新增、修改（0修改操作 1新增操作）
	 * 
	 * @throws Exception
	 */
	

	@RequestMapping("/staffSaveOrUpdate.do")
	public @ResponseBody Message staffSaveOrUpdate(HttpServletRequest request, CultureStaffInfo cultureStaffinfo, String type) {

		Message msg = new Message();
		try {
			HttpSession session = request.getSession();
			SysOperator operator = (SysOperator) session.getAttribute(WcsSessionConstant.SESSION_OPERATOR);

			if (StringUtils.isNotEmpty(type) && type.equals("add")) {
				logger.info("增加好员工：{},{}", cultureStaffinfo.getTitle(), type);
				cultureStaffinfo.setCreater(operator.getOperId());
				cultureStaffinfo.setCreateTime(Calendar.getInstance().getTime());
				cultureStaffinfo.setPostTime(DateUtils.getHymdsByString(cultureStaffinfo.getPostTimeStr()));
				cultureStaffinfo.setPostYear(DateUtil.DateToYear(cultureStaffinfo.getPostTime()));
				cultureStaffinfo.setLink("");
				cultureStaffinfo.setStatus("1");
				cultureStaffinfo.setPubStatus("0");
				
					
				String summary = cultureStaffinfo.getSummary();
				if (summary.length() > 100) {
					summary = summary.substring(0, 100) + "...";
				}
				cultureStaffinfo.setSummary(summary);
				/*cultureStaffinfo.setLink("");
				cultureStaffinfo.setStatus("1");
				cultureStaffinfo.setPubStatus(0);*/
				CultureStaffInfoService.save(cultureStaffinfo, 1);
				msg.setFlag(true);
				msg.setMsg("新增好员工信息成功");
				msg.setType(cultureStaffinfo.getId() + "");
			} else if (StringUtils.isNotEmpty(type) && type.equals("edit")) {
				logger.info("修改好员工：{},{}", cultureStaffinfo.getTitle(), type);
				// 修改好员工
				cultureStaffinfo.setModifier(operator.getOperId());
				cultureStaffinfo.setModifyTime(Calendar.getInstance().getTime());
				cultureStaffinfo.setPostTime(DateUtils.getHymdsByString(cultureStaffinfo.getPostTimeStr()));
				cultureStaffinfo.setPostYear(DateUtil.DateToYear(cultureStaffinfo.getPostTime()));
				cultureStaffinfo.setPostMonth(DateUtil.DateToMonth(cultureStaffinfo.getPostTime()));
				
				String summary = cultureStaffinfo.getSummary();
				if (summary.length() > 100) {
					summary = summary.substring(0, 100) + "...";
				}
				cultureStaffinfo.setSummary(summary);

				CultureStaffInfoService.save(cultureStaffinfo, 0);
				msg.setFlag(true);
				msg.setMsg("修改好员工信息成功");
			} else if (StringUtils.isNotEmpty(type) && type.equals("preview")) {
				cultureStaffinfo = CultureStaffInfoService.get(Integer.toString(cultureStaffinfo.getId()));
				logger.info("预览好员工：{},{}", cultureStaffinfo.getTitle(), type);
				Map<String, Object> paramMap = new HashMap<String, Object>();
				String postTime = DateUtilsC.getTimeYMD(cultureStaffinfo.getPostTime());
				paramMap.put("id", cultureStaffinfo.getId());
				paramMap.put("title", cultureStaffinfo.getTitle());
				paramMap.put("postTime", postTime);
				paramMap.put("issuer", cultureStaffinfo.getIssuer());
				paramMap.put("content", cultureStaffinfo.getContent());


				return pageCreatService.creatPreviewPage(
						request.getSession().getServletContext().getRealPath(File.separator), "employee", "staffinfo",
						cultureStaffinfo.getPostTime(), paramMap);

		       
		      }else {
		    	// 发布type=pub
		    	  cultureStaffinfo = CultureStaffInfoService.get(Integer.toString(cultureStaffinfo.getId()));
					logger.info("发布好员工：{},{}", cultureStaffinfo.getTitle(), type);
					cultureStaffinfo.setStatus("1");
					cultureStaffinfo.setPubStatus("1");

					Map<String, Object> paramMap = new HashMap<String, Object>();
					String postTime = DateUtilsC.getTimeYMD(cultureStaffinfo.getPostTime());
					paramMap.put("id", cultureStaffinfo.getId());
					paramMap.put("title", cultureStaffinfo.getTitle());
					paramMap.put("postTime", postTime);
					paramMap.put("issuer", cultureStaffinfo.getIssuer());
					paramMap.put("content", cultureStaffinfo.getContent());

					//String columnName = issueNewsInfoService.getColumnNameByPageType(IssueNewsInfo.getIssueNewsCategory());
					String rootPath = request.getSession().getServletContext().getRealPath(File.separator);
					msg = pageCreatService.creatPage(rootPath, "employee", "staffinfo", cultureStaffinfo.getPostTime(), paramMap);

					// 生成页面成功
					if (msg.isFlag()) {
						String pageUrl = msg.getMsg();
						cultureStaffinfo.setLink(pageUrl);
						CultureStaffInfoService.save(cultureStaffinfo, 0);
						// 将文件上传到静态页面服务器上
						pageCreatService.mkDir(pageUrl);
						pageCreatService.remCpPage(pageUrl);
					}
					msg.setFlag(true);
					msg.setMsg("发布好员工成功");
		      }
		    }
			catch (Exception e) {
			logger.error("发布好员工信息出错:{}", e);
			msg.setFlag(false);
			msg.setMsg("操作异常");
		}
		return msg;
	}
	
	

	public int getPageSize(int pageCount) {
		int staffPageSize = 0;
		if (pageCount <= 5 && pageCount > 0) {
			staffPageSize = 1;
		} else {
			if (pageCount % 5 == 0) {
				staffPageSize = pageCount / 5;
			} else {
				staffPageSize = pageCount / 5 + 1;
			}
		}
		return staffPageSize;
	}




	/**
	 * 好员工新增、编辑、详情查询页面跳转
	 * 
	 * @param request
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access" })
	@RequestMapping("/toPage.do")
	public String toPage(HttpServletRequest request, String operType, String id, String page) {
		Map<String, String> statusHash = WcsDefinition.WcsCommonStatus.WCS_COMMON_STATUS_MAP;
		try {
			request.setAttribute("statusHash", statusHash);

			/* 角色类型 */
			Map<String, String> roleTypeMap = WcsDefinition.WcsRoleType.WCS_ROLE_TYPE_MAP;
			Map<String, String> staffRoleTypeMap = new HashMap<String, String>();
			for (String key : roleTypeMap.keySet()) {
				if (!key.equals(WcsDefinition.WcsRoleType.WCS_ROLE_TYPE_SYSTEM)) {
					staffRoleTypeMap.put(key, roleTypeMap.get(key));
				}
			}
			if (StringUtils.isNotEmpty(operType) && operType.equals("add")) {
				// 好员工新增
				return "staff/staffAdd";
			} else if (StringUtils.isNotEmpty(operType) && operType.equals("edit")) {
				CultureStaffInfo cultureStaffInfo = CultureStaffInfoService.get(id);
				cultureStaffInfo.setPostTimeStr(DateUtils.getYmdByDate2(cultureStaffInfo.getPostTime()));
				request.setAttribute("cultureStaffInfo", cultureStaffInfo);
				// 好员工编辑
				return "staff/staffEdit";
			} else {
				// 好员工查询
				// SysRole role = sysRoleService.get(roleCode);
				// request.setAttribute("role", role);
				return "staff/staffDetail";
			}
		} catch (Exception e) {
			logger.error("页面跳转出错:{}", e);
		}
		return null;
	}

	/**
	 * 跳转到角色列表页面
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/toStaffList.do")
	public String toStaffList(HttpServletRequest request) {
		try {
			Map<String, String> statusHash = WcsDefinition.WcsCommonStatus.WCS_COMMON_STATUS_MAP;
			HttpSession session = request.getSession();
			SysOperator operator = (SysOperator) session.getAttribute(WcsSessionConstant.SESSION_OPERATOR);
			List<SysFunOperate> funOperateList = sysFunOperateService
					.getHasAuthFunOperateByMenuCodeAndOperId("STAFF_INFO", operator.getOperId());
			request.setAttribute("statusHash", statusHash);
			request.setAttribute("funOperateList", funOperateList);
		} catch (Exception e) {
			logger.error("发布好员工信息出错:{}", e);
		}
		return "staff/staffList";
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
	@RequestMapping("/getStaffList.do")
	public @ResponseBody Map<String, Object> getStaffList(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
	   
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String Title1 = request.getParameter("title");
			if (StringUtils.isNotEmpty(Title1)) {
				Title1 = Title1.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
			}
			final String Title = Title1;
			final String status = request.getParameter("status");
			final String page = request.getParameter("page");

			params.put(Page.CURR_PAGE, Integer.parseInt(page));
			params.put(Page.PAGE_SIZE, Page.DEFAULT_PAGE_SIZE);
			if (StringUtils.isNotEmpty(Title))
				params.put("title", Title);
			if (StringUtils.isNotEmpty(status))
				params.put("status", status);
			
			
			
			List<Map<String, Object>> orderBy = new ArrayList<Map<String, Object>>();
			Map<String, Object> hash = new HashMap<String, Object>();
			hash.put("createTime", "desc");
			orderBy.add(hash);
			params.put("orderBy", orderBy);

			Page staffPage = CultureStaffInfoService.getPageByParamMap(params);
			List<CultureStaffInfo> staffList = staffPage.getList();
			map.put("totalNumber", staffPage.getTotalNumber());
			map.put("totalPage", staffPage.getTotalPage());
			map.put("rows", staffList);
		} catch (Exception e) {
			logger.error("获取好员工列表出错:", e);
		}
		return map;
	}
	
}
