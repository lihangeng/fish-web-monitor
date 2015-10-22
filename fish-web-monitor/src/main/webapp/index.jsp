<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yihuacomputer.fish.api.person.UserSession"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title><spring:message code="login.title" /></title>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<link rel="shortcut icon" type="image/ico" href="resources/images/logo.ico" />

	<link rel="stylesheet" type="text/css" href="ext/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css" media="all" />
	<link rel="stylesheet" type="text/css" href="ext/packages/sencha-charts/sencha-charts-all.css" media="all" />
	<link rel="stylesheet" type="text/css" href="resources/css/fish-monitor.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/font-awesome.css" media="all" />

	<script type="text/javascript" src="ext/ext-all-debug.js"></script>
	<script type="text/javascript" src="ext/packages/ext-theme-crisp/build/ext-theme-crisp.js"></script>  
	<script type="text/javascript" src="ext/packages/sencha-charts/sencha-charts.js"></script>  
	<script type="text/javascript" src="ext/ux/cometd/cometd.js"></script>
	<script type="text/javascript" src="ext/ux/cometd/ext-cometd.js"></script>
	<script type="text/javascript">
		var EwayUserObject = function(id,code,name,orgId,orgType,orgName,orgCode){
		var me = this;
		this.id = id;
		this.code = code;
		this.name = name;
		this.orgId = orgId;
		this.orgType = orgType;
		this.orgName = orgName;
		this.orgCode = orgCode;
		return {
			getId : function(){
				return me.id;
			},
			getCode : function(){
				return me.code;
			},
			getName : function(){
				return me.name;
			},
			getOrgId : function(){
				return me.orgId;
			},
			getOrgType : function(){
				return me.orgType;
			},
			getOrgName : function(){
				return me.orgName;
			},
			getOrgCode : function(){
				return me.orgCode;
			}			
		}
	}

	<%if (session.getAttribute("SESSION_USER") == null) {%>
		window.location.href="login.jsp";
	<%} else {
		UserSession userSession = (UserSession) session.getAttribute("SESSION_USER");%>
		var ewayUser = new EwayUserObject(
			'<%=userSession.getUserId()%>',
			'<%=userSession.getUserCode()%>',
			'<%=userSession.getUserName()%>',
			'<%=userSession.getOrgId()%>',
			'<%=String.valueOf(userSession.getOrgType() == null
							? ""
							: userSession.getOrgType().getId())%>',
			'<%=userSession.getOrgName()%>',
			'<%=userSession.getOrgCode()%>');
		var test_userId = Math.random()+'';
	<%} %>

  	</script>
	<script type="text/javascript">
		Ext.BLANK_IMAGE_URL = 'resources/images/s.gif';
	  	Ext.themeModel = "new";
	  	Ext.Loader.setConfig({
	  		enabled : true,//动态加载
	        paths: {
	            'Ext.ux': 'ext/ux'
	        }
	      });
	  	Ext.cxtPath = '<%=request.getContextPath()%>';
	  	var Eway = Eway || {};
	  	
		if(Ext.String.startsWith(Ext.global.navigator.language,"zh")){
			Ext.Loader.loadScript(Ext.cxtPath+"/ext/locale/ext-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/eway-locale-zh_CN.js");
		}else{
			Ext.Loader.loadScript(Ext.cxtPath+"/ext/locale/ext-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/eway-locale-en.js");
		}
	</script>
	<script type="text/javascript" src="app.js"></script>
	<script type="text/javascript" src="ext/patch.js"></script>
</head>

<body>
	<div id="loading">
		<span class="title">努力加载中,请稍等...</span><span class="logo"></span>
	</div>
	<iframe id="downloadFileFromWeb" style="display: none"></iframe>
</body>
	<script type="text/javascript">
		document.onkeypress = forbidBackSpace;
		document.onkeydown = forbidBackSpace;
		function forbidBackSpace(e) {
			var ev = e || window.event;
			var obj = ev.target || ev.srcElement;
			var t = obj.type || obj.getAttribute('type');
			var vReadOnly = obj.readOnly;
			var vDisabled = obj.disabled;
			vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
			vDisabled = (vDisabled == undefined) ? true : vDisabled;
			var flag1 = ev.keyCode == 8
					&& (t == "password" || t == "text" || t == "textarea")
					&& (vReadOnly == true || vDisabled == true);
			var flag2 = ev.keyCode == 8 && t != "password" && t != "text"
					&& t != "textarea";
			if (flag2 || flag1) {
				return false
			}
			;
		}
	</script>
</html>
