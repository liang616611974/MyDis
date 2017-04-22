<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../../common/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>AdminUser列表页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  	<script type="text/javascript" src="${jsUrl}/home/admin/adminUser/adminUser_list.js"></script>
 	<style type="text/css">
		.ftitle{
			font-size: 14px;
			font-weight: bold;
			margin-bottom: 0px;
		}
		.fitem{
			margin-bottom: 10px;
		}
	</style>
  </head>
  
  <body>
  
  	<!-- 搜索栏开始 -->
	<div class="easyui-panel" style="width:100%;max-width:1240px;padding:20px 30px;">
		<label>用户名&nbsp：</label> <input id="username" class="easyui-textbox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>密码&nbsp：</label> <input id="password" class="easyui-textbox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>状态&nbsp：</label> <select id="status" class="easyui-combobox" editable="false" style="width: 200px;">
					<option value="">无</option>
					<option value="0">禁用</option>
					<option value="1">正常</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;
		<label>身分证号码&nbsp：</label> <input id="idcard" class="easyui-textbox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>手机号码&nbsp：</label> <input id="mobile" class="easyui-textbox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>电子邮箱&nbsp：</label> <input id="email" class="easyui-textbox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>创建用户主键&nbsp：</label> <input id="createUser" class="easyui-textbox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>创建时间开始：</label> <input id="createTimeBeginStr"  class="easyui-datetimebox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>创建时间结束：</label> <input id="createTimeEndStr"  class="easyui-datetimebox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>修改用户主键&nbsp：</label> <input id="modifyUser" class="easyui-textbox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>修改时间开始：</label> <input id="modifyTimeBeginStr"  class="easyui-datetimebox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>修改时间结束：</label> <input id="modifyTimeEndStr"  class="easyui-datetimebox" />&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="#" class="easyui-linkbutton" iconCls="icon-search" style="width:80px" onclick="doSearch()">搜索</a>
	</div>
	<!-- 搜索栏结束 -->
	
	<!-- 工具栏开始 -->
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAdminUser()">新建 AdminUser</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editAdminUser()">编辑 AdminUser</a> 
		<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeAdminUser()">移除 AdminUser</a>
	</div>
	<!-- 工具栏结束 -->
	
	<!--数据列表开始-->
  	<div id="dg"></div>
  	<!--数据列表结束-->
  	
  	<!-- 新增/修改对话框开始-->
	<div id="dlg_new_modify" class="easyui-dialog" style="width:400px;height:380px;padding:10px 20px" closed="true" buttons="#dlg_buttons_new_modify">
		<div class="ftitle">后台用户信息</div>
		<hr style="margin-bottom: 10px;" />
		<form id="fm" method="post">
			<!-- easyui控件内，value=''赋值无效，必须用其独有的方法赋值，form.load或setValue -->
			<input name="id" type="hidden" />
			<div class="fitem">
				<label>用户名&nbsp：</label> <input name="username" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>密码&nbsp：</label> <input name="password" class="easyui-passwordbox" required="true" />
			</div>
			<div class="fitem">
				<label>状态&nbsp：</label> <select name="status" class="easyui-combobox" editable="false"  style="width: 200px;">
					<option value="0">禁用</option>
					<option value="1">正常</option>
				</select>
			</div>
			<div class="fitem">
				<label>身分证号码&nbsp：</label> <input name="idcard" class="easyui-textbox"  validType="idcard"/>
			</div>
			<div class="fitem">
				<label>手机号码&nbsp：</label> <input name="mobile" class="easyui-textbox"  validType="mobile"/>
			</div>
			<div class="fitem">
				<label>电子邮箱&nbsp：</label> <input name="email" class="easyui-textbox"  validType="email"/>
			</div>
			<div class="fitem">
				<label>创建用户主键&nbsp：</label> <input name="createUser" class="easyui-textbox"  />
			</div>
			<div class="fitem">
				<label>创建时间&nbsp：</label> <input name="createTimeStr" class="easyui-datetimebox"  />
			</div>
			<div class="fitem">
				<label>修改用户主键&nbsp：</label> <input name="modifyUser" class="easyui-textbox"  />
			</div>
			<div class="fitem">
				<label>修改时间&nbsp：</label> <input name="modifyTimeStr" class="easyui-datetimebox"  />
			</div>
		</form>
	</div>
	<div id="dlg_buttons_new_modify">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveAdminUser()">保存</a>
		<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:adminUI.closeDialog('dlg_new_modify')">取消</a>
	</div>
	<!-- 新增/修改对话框结束-->

<!-- 注意：如果要value属性赋值(value="aaa")，就不能放到easyui的任何控件内，比如easyui-dialog, 否则赋值失败-->
<input id="token" type="hidden" value="${token}"/>
  </body>
</html>
