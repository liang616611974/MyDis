<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../../common/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>AdminUser详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  	
  	<style type="text/css">
	  	label{
	  		font-weight: bold;
	  	}
  	</style>
  	
  </head>
  
  <body>
  	<h2>AdminUser详情</h2>
	<div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="AdminUser详情" style="width:100%;max-width:400px;padding:30px 60px;">
		<div style="margin-bottom:20px">
			<label class="label-top">用户名 </label> : ${adminUser.username}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">密码 </label> : ${adminUser.password}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">状态：0-禁用，1-正常 </label> : ${adminUser.status}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">身分证号码 </label> : ${adminUser.idcard}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">手机号码 </label> : ${adminUser.mobile}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">电子邮箱 </label> : ${adminUser.email}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">创建用户主键 </label> : ${adminUser.createUser}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">创建时间 </label> : ${adminUser.createTimeStr}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">修改用户主键 </label> : ${adminUser.modifyUser}
		</div>
		<div style="margin-bottom:20px">
			<label class="label-top">修改时间 </label> : ${adminUser.modifyTimeStr}
		</div>
	</div>
  </body>
</html>
