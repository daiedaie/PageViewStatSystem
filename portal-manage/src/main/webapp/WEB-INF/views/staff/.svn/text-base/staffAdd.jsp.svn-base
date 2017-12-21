<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" contentType="text/html; charset=utf-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path1 = request.getContextPath();
String basePath1 = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort() + path1 + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
    <script type="text/javascript" src="js/ajaxfileupload.js"></script>
    <script type="text/javascript" src="js/validate/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/validate/additional-methods.min.js"></script>
    <script type="text/javascript" src="js/validate/messages_zh.js"></script>
    <script type="text/javascript" src="js/kkpager/kkpager.min.js"></script>
    <script type="text/javascript" src="js/modal/bootstrap-modal.js"></script>
    <script type="text/javascript" src="js/modal/bootstrap-modalmanager.js"></script>
    <script type="text/javascript" src="js/jbox/jquery.jBox-2.3.min.js"></script> 
    <script type="text/javascript" src="js/jbox/jquery.jbox-zh-cn.js"></script> 
    <script type="text/javascript" charset="utf-8" src="utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="utf8-jsp/ueditor.all.min.js"> </script> 
    <script type="text/javascript" charset="utf-8" src="utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="js/staff.js"></script>

</head>
<body>
<script type="text/javascript">
function getAllHtml() {
    return UE.getEditor('editor').getContent();
}

	var menuCode = "";
	var btnParam = [];
	//临时菜单功能容器{menuCode:btnParam}
	var tempBtnParam = {};
	var menuParams = "";
	var btnParams = "";
	
</script>
<script type="text/javascript">
	function buildParam(){
		$('input:checkbox[name=operateCodes]:checked').each(function(i){
			btnParam.push($(this).val());
		});
		tempBtnParam[menuCode] = btnParam;
		btnParam = [];
	}
</script>
<script type="text/javascript">
function checkStrInArray(str){
	if (tempBtnParam[menuCode]==undefined) return false;
	for(var i=0;i<tempBtnParam[menuCode].length;i++){
		if(tempBtnParam[menuCode][i] == str){
		return true;
		}
	}
	return false;
}

</script>

<!-------------------------CONT---------------------------->  
<div class="Edit-cont box-cont">
	<div class="panel panel-default">
         <div class="panel-header">
           <h4>好员工管理</h4>
         </div>
         <div class="panel-body">
         	<div class="panel panel-primary">
               <div class="panel-header">
                   <h4>新增好员工</h4>
               </div>
               <div class="panel-body">
               	<div class="user-form">
                	<form>
                       <div class="form-group">
                           <label class="form-label" for="">好员工标题：</label>
                            <input id="title" name="title" maxlength="100" type="text" class="input-text" value="好员工标题" onblur="jiaoyanTitle();">
                       </div> 
                       <div class="form-group">
                           <label class="form-label" for="">好员工发布人：</label>
                            <input id="issuer" name="issuer" maxlength="50" type="text" class="input-text" value="好员工发布人" onblur="jiaoyanIssuer();">
                       </div>
                       <div class="form-group">
                           <label class="form-label" for="">发布时间：</label>
                            <input id="postTime" name="postTime" maxlength="50" type="text" class="input-text"  value="yyyy-mm-dd"  onblur="jiaoyanPostTime();">
                       </div> 
                       <div class="form-group">
                           <label class="form-label" for="">好员工摘要：</label>
                            <textarea id="summary" name="summary" maxlength="150"  cols="20" class="input-text"   onblur="jiaoyanSummary();"></textarea>
                       </div>
                       <div class="form-group">
                           <label class="form-label" for="">好员工内容：</label>
                           <script id="editor" name="content" type="text/plain" style="width:824px;height:500px;">
								请在这里输入好员工内容。
						   </script>
                       </div>
                     
                

                        
                     </form>
                    </div> 
                  </div>
                </div>
            <div class="btn-wrap pl-20 mt-10">
				<a class="btn btn-primary ml-10" onclick="save();">保存</a>
				<a class="btn btn-secondary ml-10" href="javascript:history.go(-1);">取消</a>
            </div>
        </div> 
     </div>                                      
  </div>
  
  <script type="text/javascript">
  
  

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        if (action == 'uploadimage' || action == 'uploadfile' || action == 'uploadvideo') {
            
        	return '<%=basePath1 %>fastdfs/uploadfile.do';
        } else {
            return this._bkGetActionUrl.call(this, action);
        }
    };
    
</script>
</body>
</html>