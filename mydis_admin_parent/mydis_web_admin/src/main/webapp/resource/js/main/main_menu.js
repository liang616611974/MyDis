var zTreeObj;
var setting = {
	view : {
		selectedMulti:false
	},
	callback : {
		beforeClick: zTreeBeforeClick
	}
};

/** 点击节点前操作 */
function zTreeBeforeClick(treeId, treeNode, clickFlag){
    if (treeNode.isParent) {
        zTreeObj.expandNode(treeNode);
        return false;
    }else {
        adminUI.addTab(treeNode.name, base.getPrjUrl() + treeNode.mUrl);
        return true;
    }
}

/** 页面初始化 */
$(document).ready(function() {
	var menuUrl = base.getPrjUrl() + "/menu/getMenuForMain.do";
	ajax.post(menuUrl,"json",null,function(result){
		if(result.flag){
			zTreeObj = $.fn.zTree.init($("#menuTree"), setting, eval(result.data));
		}else{
			adminUI.alertErr(result.msg);
		}
	});

});

