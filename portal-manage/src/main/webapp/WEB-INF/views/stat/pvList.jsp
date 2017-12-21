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
<title>统计管理页-集团网站内容管理平台</title>
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
<script type="text/javascript" charset="utf-8" src="js/news.js"></script>
 
</head>
<body style="min-height: 900px;">
<script type="text/javascript">
	$(function(){
		searchList();
	});

</script>
<input type="hidden" id="newsId" />
<!-- 获取列表 -->
<script type="text/javascript">
	var totalPage;
	var totalRecords;
	var page = 1;
	var param = {};
	function searchList(){
		page = 1;
  		var newsTitle = $("#newsTitle").val()==undefined?"":$("#newsTitle").val();
  		//var status = $("#status option:selected").val()==undefined?"":$("#status option:selected").val();
  		param["newsTitle"] = newsTitle.trim();
  		//param["status"] = status;
  		param["page"] = page;
  		var newsCategory = $("#newsCategory option:selected").val();
  		if(newsCategory != "")
  			param["newsCategory"] = newsCategory;
  		else
  			param["newsCategory"] = null;
  		getList();
	}
	
	function getList(){
  		$.ajax({
  			type: "POST",
  			url: "newsInfo/getNewsList.do",
  			data: param,
  			datatype: "json",
  			success: function(data){
  				if (data!=null) {
  					var newsList = data.rows;
  					totalPage = data.totalPage;
  					totalRecords = data.totalNumber;
  					$("#newsList").empty();
  					var temp = "<thead><tr><th width=70%>栏目</th><th width=30%>pv</th></tr></thead>";
  						temp+="<tr>"
  					    +"<td>"+newsList[x].newsTitle+"</td>"
  					  	+"<td>"+newsCategory+"</td>"
  					    +"<td>"+new Date(newsList[x].createTime).format("yyyy-MM-dd hh:mm:ss")+"</td>"			
  					+"</tr>"
  
  					$("#newsList").append(temp);
  					
  				}else{
  					
  				}
  			}
  		});
	}
</script>

<!-------------------------CONT---------------------------->  
<div class="List-cont box-cont">
	<div class="panel panel-default">
	    <div class="panel-header">
	      <h4>统计管理</h4>
	    </div>
		<div id="panel-body" class="panel-body">
        	<!--搜索模块-->
            <div class="search-form">
            	<form id="searchForm">
                 <div class="row cl">
                     <div class="col-sm-3">
                         <label class="form-label" for="">年（****）：</label><div class="formControls"><input id="newsTitle" name="newsTitle" type="text" class="input-text" ></div>
                     </div>
                     <div class="col-sm-3">
                         <label class="form-label" for="">月（**）：</label><div class="formControls"><input id="newsCategory" name="newsTitle" type="text" class="input-text" ></div>
                     </div>
                     <div class="col-sm-3 text-r">
                    <a onClick="searchList();" class="btn btn-primary ">查询</a>  
                   
                 </div>
                 </div>
                 
                 </form> 
               </div>
                
              </div>
            <!--列表-->
            <table id="newsList" class="table table-primary mt-30"></table>   
  	       </div> 
	  	 </div>                                      
        </div>       
<!--------------------------MODAL---------------------------->



</body>
</html>
