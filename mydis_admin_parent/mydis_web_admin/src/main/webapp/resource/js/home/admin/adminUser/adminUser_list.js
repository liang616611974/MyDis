var dgId = "dg";//数据列表对象id
var saveUrl;//保存对象的url
/** 初始化 */
$(function() {
	var dgParam = {
			title : "AdminUser列表",
			height : 560,
			url : base.getPrjUrl() + "/adminUser/listData.do",
			method : 'POST',
			//queryParams : {id:'1'},
			idField : "id",
			loadMsg : '数据加载中,请稍后……',
			striped : true,
			fitColumns : true,
			singleSelect : true,
			rownumbers : true,
			fitColumns : false,
			pagination : true,
			autoRowHeight : true,
			toolbar:"#toolbar",
			columns :[[
			   {
			       field:'_operate',
				   title:'操作',	
				   width : 100,	
				   formatter: function(value,row,index){				    	
					   var html = "&nbsp;&nbsp;<a href='javascript:void(0);' class='easyui-linkbutton' onclick='detail(\""+row.id+"\")' >查看详情</a>";
					   return html;
				   }
			   },
			   {
				   field : 'username',
				   title : '用户名 ',
				   width : 150		
			   }
			   ,
			   {
				   field : 'password',
				   title : '密码 ',
				   width : 150		
			   }
			   ,
			   {
			       field : 'status',
				   title : '状态 ',
				   width : 150,
				   formatter: function(value,row,index){
				   	if(value==0){
						return '禁用';
					}else if(value==1){
						return '正常';
					}else{
						return '未知';
					}
				  }		
			   }
			   ,
			   {
				   field : 'idcard',
				   title : '身分证号码 ',
				   width : 150		
			   }
			   ,
			   {
				   field : 'mobile',
				   title : '手机号码 ',
				   width : 150		
			   }
			   ,
			   {
				   field : 'email',
				   title : '电子邮箱 ',
				   width : 150		
			   }
			   ,
			   {
				   field : 'createUser',
				   title : '创建用户主键 ',
				   width : 150		
			   }
			   ,
			   {
				   field : 'createTimeStr',
				   title : '创建时间 ',
				   width : 150		
			   }
			   ,
			   {
				   field : 'modifyUser',
				   title : '修改用户主键 ',
				   width : 150		
			   }
			   ,
			   {
				   field : 'modifyTimeStr',
				   title : '修改时间 ',
				   width : 150		
			   }
			   
			]],
			onLoadSuccess : function(data) {
			},
			onLoadError : function() {
				alert("获取数据 失败");
			}
		};
	adminUI.datagrid(dgId,"show",dgParam);
});

/** 搜索 */
function doSearch(){
	adminUI.datagrid(dgId,"load",{
		username:adminUI.textbox("username","getValue")
		,
		password:adminUI.textbox("password","getValue")
		,
		status:adminUI.combobox("status","getValue")
		,
		idcard:adminUI.textbox("idcard","getValue")
		,
		mobile:adminUI.textbox("mobile","getValue")
		,
		email:adminUI.textbox("email","getValue")
		,
		createUser:adminUI.textbox("createUser","getValue")
		,
		createTimeBeginStr:adminUI.datetimebox("createTimeBeginStr","getValue"),
		createTimeEndStr:adminUI.datetimebox("createTimeEndStr","getValue")
		,
		modifyUser:adminUI.textbox("modifyUser","getValue")
		,
		modifyTimeBeginStr:adminUI.datetimebox("modifyTimeBeginStr","getValue"),
		modifyTimeEndStr:adminUI.datetimebox("modifyTimeEndStr","getValue")
		
	});
}

/** 详细 */
function detail(id){
	var url = base.getPrjUrl() + "/adminUser/detail.do?id=" + id;
	adminUI.addTabByParent("AdminUser详情", url);
}

/** 新建 */
function newAdminUser(){
	$("#fm").clear();
	adminUI.openDialog("dlg_new_modify","新建AdminUser");
	//设置保存url
	saveUrl = base.getPrjUrl() + "/adminUser/save.do"+"?token="+$("#token").val();
}

/** 保存 */
function saveAdminUser() {
	var fm = $("#fm");
	//1.校验
	if (!fm.validate())
		return false;
	//2.显示进度条
	adminUI.openProgress();
	//3.提交保存
	ajax.post(window.saveUrl, 'json', fm.serializeObject(), function(result) {
		if (result.flag) {
			adminUI.alertInfo("保存AdminUser成功");
		} else {
			adminUI.alertErr(result.msg);
		}
	}, function(xhr,textStatus){
		adminUI.closeProgress();
		adminUI.closeDialog("dlg_new_modify");
		adminUI.datagrid(dgId,"reload");
	});
}

/** 编辑 */
function editAdminUser(){
	var fm = $("#fm");
	//获取选中的行
	var row = adminUI.datagrid(dgId,'getSelected');
	if(row==null){
		adminUI.alertInfo("请选择要修改的行");
		return false;
	}
	fm.clear();
	//设置保存url
	saveUrl = base.getPrjUrl() + "/adminUser/modify.do";
	//打开编辑页面
	var url = base.getPrjUrl() + "/adminUser/edit.do";
	ajax.post(url,'json',{id:row.id},function(result){
		if(result.flag) {
			fm.load(result.data);
			adminUI.openDialog("dlg_new_modify", "编辑AdminUser");
		}else {
			adminUI.alertErr(result.msg);
		}
	});
	
}

/** 删除 */
function removeAdminUser() {
	var row = adminUI.datagrid(dgId,"getSelected");
	if(row==null){
		adminUI.alertInfo("请选择要删除的行");
		return false;
	}
	adminUI.confirm("删除","确认删除该条数据吗？",function(r){
		if(r){
			//1.定义需要的变量
			var url = base.getPrjUrl() + "/adminUser/remove.do";
			//2.显示进度条
			adminUI.openProgress();
			//3.提交保存
			ajax.post(url, 'json', {id:row.id}, function(result) {
				if (result.flag) {
					adminUI.alertInfo("删除AdminUser成功");
				} else {
					adminUI.alertErr(result.msg);
				}
			}, function(){
				adminUI.closeProgress();
				adminUI.datagrid(dgId,"reload");
			});
		}
	});
}
