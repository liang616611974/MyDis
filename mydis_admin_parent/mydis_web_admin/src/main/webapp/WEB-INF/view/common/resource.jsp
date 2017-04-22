<%@include file="taglib.jsp" %>

<c:set var="prjUrl" value="${pageContext.request.contextPath}"/>
<c:set var="resourceUrl" value="${pageContext.request.contextPath}/resource"/>
<c:set var="cssUrl" value="${pageContext.request.contextPath}/resource/css"/>
<c:set var="imgUrl" value="${pageContext.request.contextPath}/resource/img"/>
<c:set var="jsUrl" value="${pageContext.request.contextPath}/resource/js"/>
<c:set var="pluginUrl" value="${pageContext.request.contextPath}/resource/plugin"/>

<link rel="stylesheet" type="text/css" href="${pluginUrl}/ui/jquery-easyui-1.5/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="${pluginUrl}/ui/jquery-easyui-1.5/themes/icon.css">
<script type="text/javascript" src="${pluginUrl}/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pluginUrl}/ui/jquery-easyui-1.5/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${pluginUrl}/ui/jquery-easyui-1.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${pluginUrl}/expand/common/validator.js"></script>
<script type="text/javascript" src="${pluginUrl}/expand/easyui/validatebox_expand.js"></script>
<script type="text/javascript" src="${pluginUrl}/expand/easyui/form_property_expand.js"></script>
<script type="text/javascript" src="${jsUrl}/common/base.js"></script>
<script type="text/javascript" src="${jsUrl}/common/admin_ui_easyui.js"></script>
<script type="text/javascript" src="${jsUrl}/common/ajax.js"></script>







