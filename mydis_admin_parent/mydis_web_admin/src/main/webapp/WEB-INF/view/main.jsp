<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="common/resource.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  
    
	<head>
		<title>MyDis 管理后台</title> 
		<meta http-equiv="pragma" content="no-cache"> 
		<meta http-equiv="cache-control" content="no-cache"> 
		<meta http-equiv="expires" content="0"> 
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${pluginUrl}/tree/zTree-3.5/css/zTreeStyle/zTreeStyle.css"/> 
		<link rel="stylesheet" type="text/css" href="${cssUrl}/main.css"/> 
		<script type="text/javascript" src="${pluginUrl}/tree/zTree-3.5/js/jquery.ztree.core-3.5.min.js"></script>
		<script type="text/javascript" src="${jsUrl}/main/main_menu.js"></script>
	</head>

	<body data-options="scroll:'no'" class="easyui-layout">

			<!-- north 上方开始 --> 
			<div data-options="region:'north',split:true,border:false" class="north">
				 <span style="padding-left:10px; font-size: 16px;">
        			<img src="${imgUrl}/main/main_sys_logo.png" width="20" height="20" align="absmiddle"/>&nbsp;XXX管理平台
        		</span> 
			</div> 
			<!-- north 上方结束 --> 

			<!-- west 左边开始 -->
			<div data-options="region:'west',title:'导航菜单',split:true,hide:true" class="west"> 
				<div id="nav" data-options="fit:true,border:false" class="easyui-accordion" > 
					<ul id="menuTree" class="ztree" style="width:230px; overflow:auto;"></ul>
				</div> 
			</div> 
			<!-- west 左边结束 --> 

			<!-- center 中间开始 --> 
			<div data-options="region:'center'" style=""> 
				<div id="tabs" class="easyui-tabs" data-options="fit:true"> 
					<div title="后台操作的界面" data-options="iconCls:'icon-home',closable:false" style="padding:10px;">后台操作界面的说明</div>
					<div align="center">这是内容。。。。。。。。。。。</div> 
				</div> 
			</div> 
			<!-- center 中间结束 --> 

			<!-- south 底部开始 --> 
			<div data-options="region:'south',split:true" style="height:25px; padding: 5px; background: #D2E0F2;"> 
				<div align="center">版权所有，翻版必究</div> 
			</div> 
			<!-- south 底部结束 --> 
	</body>
</html>
