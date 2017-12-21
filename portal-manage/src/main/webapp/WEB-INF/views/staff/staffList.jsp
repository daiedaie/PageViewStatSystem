<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>好员工管理页-集团网站内容管理平台</title>
<base href="<%=basePath1 %>" />

    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/core.css">
    <link rel="stylesheet" href="js/kkpager/kkpager_blue.css">
    <link rel="stylesheet" href="js/jbox/jbox.css">
    <script type="text/javascript" src="js/jquery-2.1.4.js"></script> 
    <script type="text/javascript" src="js/validate/jquery.validate.min.js"></script> 
    <script type="text/javascript" src="js/validate/additional-methods.min.js"></script> 
    <script type="text/javascript" src="js/validate/messages_zh.js"></script> 
    <script type="text/javascript" src="js/kkpager/kkpager.min.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modal.js"></script> 
    <script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script> 
    
<%-- main.js用于获取当前登录用户可操作功能数据 --%>
<script type="text/javascript" src="resources/easyui13/jquery.easyui.min.js"></script>
<script type="text/javascript" src="resources/easyui13/locale/easyui-lang-en.js"></script>
<script type="text/javascript" src="resources/common/easyui-expand.js"></script>
<script type="text/javascript" src="resources/common/easyui-validate.js"></script>

<script type="text/javascript" src="resources/js/system/main.js"></script>
<script type="text/javascript" src="resources/js/menu.js"></script>
<script type="text/javascript" charset="utf-8" src="js/staff.js"></script>
 
</head>
<body style="min-height: 900px;">
<script type="text/javascript">
	$(function(){
		searchList();
	});

</script>
<input type="hidden" id="id" />
<!-- 获取列表 -->
<script type="text/javascript">
	var totalPage;
	var totalRecords;
	var page = 1;
	var param = {};
	function searchList(){
		page = 1;
  		var title = $("#title").val()==undefined?"":$("#title").val();
  		//var status = $("#status option:selected").val()==undefined?"":$("#status option:selected").val();
  		param["title"] = title.trim();
  		//param["status"] = status;
  		param["page"] = page;
  		getList();
	}
	
	
	
	function changPage(){
		page = $(".curr").text()==undefined||$(".curr").text()==""?1:$(".curr").text();
  		param["page"] = page;
  		getList();
	}
	function getList(){
  		$.ajax({
  			type: "POST",
  			url: "staffInfo/getStaffList.do",
  			data: param,  
  			datatype: "json",
  			success: function(data){
  				if (data!=null) {
  					var staffList = data.rows;
  					totalPage = data.totalPage;
  					totalRecords = data.totalNumber;
  					$("#staffList").empty();
  					var temp = "<thead><tr><th width=30%>好员工标题</th><th width=40%>好员工链接</th><th width=10%>发布状态</th><th width=15%>操作</th></tr></thead>";
  					for(x in staffList){

  						var pubStatus = staffList[x].pubStatus==1?"发布":"未发布";
  						temp+="<tr>"
  					    +"<td>"+staffList[x].title+"</td>"
  					  +"<td>"+staffList[x].link+"</td>"
  					    +"<td>"+pubStatus +"</td>"
  					    +"<td>"
  					    +"<c:if test='${sessionScope.funOperate["STAFF_INFO_CHECK"] }'>"
						+"<a class=\"btn btn-op\" id=\"previewpage\" onclick=\"preview('"+staffList[x].id+"')\" target=\"_blank\" title=\"预览\"><i class=\"fa fa-search\"></i></a>"
						+"</c:if>"
						+"<c:if test='${sessionScope.funOperate["STAFF_INFO_UPDATE"] }'>"
						+"<a class=\"btn btn-op ml-10\" href=\"staffInfo/toPage.do?id="+staffList[x].id+"&operType=edit\" title=\"修改\"><i class=\"fa fa-edit\"></i></a>"
						+"</c:if>"
						+"<c:if test='${sessionScope.funOperate["STAFF_INFO_DELETE"] }'>"
						+"<a class=\"btn btn-op ml-10\" onClick=\"beforDel('"+staffList[x].id+"');\" data-toggle=\"modal\" href=\"#Modal-delete\" title=\"删除\"><i class=\"fa fa-trash\"></i></a>"
  					    +"</c:if>"
  					    +"<a class=\"btn btn-op ml-10\" id =\"publicpage\"  title=\"发布\" onclick=\"publish('"+staffList[x].id+"')\"><i class=\"fa fa-legal\"></i></a>"
  					    +"</td>"
  					+"</tr>"
  					}
  					$("#staffList").append(temp);
  					toPage();
  				}else{
  					
  				}
  			}
  		});
	}
</script>
<!-- 加载分页控件 -->
<script type="text/javascript">
	function toPage(){
		kkpager.total = totalPage;
		kkpager.totalRecords = totalRecords;
		kkpager.pno = page;
		kkpager.hasPrv = (kkpager.pno > 1)
		kkpager.hasNext = (kkpager.pno < kkpager.total);
		kkpager.generPageHtml({
		     pno : page,
		     mode : 'click', 
		     total : totalPage,  
		     totalRecords : totalRecords,
		     click : function(n){
		    	 this.selectPage(n);
		  		 changPage();
		     },
		     getHref : function(n){
		         return 'javascript:void(0)';
		     }
			 },true);
	}
</script>
<!-------------------------CONT---------------------------->  
<div class="List-cont box-cont">
	<div class="panel panel-default">
	    <div class="panel-header">
	      <h4>好员工管理</h4>
	    </div>
		<div id="panel-body" class="panel-body">
        	<!--搜索模块-->
            <div class="search-form">
            	<form id="searchForm">
                 <div class="row cl">
                     <div class="col-sm-3">
                         <label class="form-label" for="">好员工名称：</label>
                         <div class="formControls">
                                <input id="title" name="title" type="text" class="input-text" >
                         </div>
                     </div>
                     <div class="col-sm-3 text-r">
                    <a onClick="searchList();" class="btn btn-primary ">查询</a>  
                    <c:if test="${sessionScope.funOperate['STAFF_INFO_ADD'] }">
                    <a href="staffInfo/toPage.do?operType=add" class="btn btn-secondary  ">新增</a>
                    </c:if>
                 </div>
                 </div>
                 
                 </form> 
               </div>
               
               
                
              </div>
            <!--列表-->
            <table id="staffList" class="table table-primary mt-30"></table>   
            <!--分页-->
            <div id="kkpager"></div>
	       </div> 
	  	 </div>                                      
        </div>       
<!--------------------------MODAL---------------------------->

<!--删除对话框--->
<div id="Modal-delete" class="modal w300 hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 <div class="modal-header">
    <h3 id="myModalLabel">删除</h3><a class="close" data-dismiss="modal" aria-hidden="true" href="javascript:void();"><i class="fa fa-times"></i></a>
 </div>
  <div class="modal-body">
 <p>确定删除此名好员工？</p>
 </div>
  <div class="modal-footer text-c">
  <button class="btn btn-close" data-dismiss="modal" aria-hidden="true">关闭</button>
 <button onclick="del();" class="btn btn-primary">确定</button> 
 </div>
</div>

</body>
</html>
