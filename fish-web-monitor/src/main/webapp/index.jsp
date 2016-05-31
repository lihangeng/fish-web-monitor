<%@page import="com.yihuacomputer.common.FishCfg"%>
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
	<link rel="shortcut icon" type="image/ico" href="resources/images/favicon.ico" />

	<link rel="stylesheet" type="text/css" href="ext/packages/theme-crisp/resources/theme-crisp-all.css" media="all" />
	<link rel="stylesheet" type="text/css" href="ext/packages/sencha-charts/crisp/charts-all.css" media="all" />
	<link rel="stylesheet" type="text/css" href="resources/css/fish-monitor.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/font-awesome.css" media="all" />

	<script type="text/javascript" src="ext/ext-all.js"></script>
	<script type="text/javascript" src="ext/packages/theme-crisp/theme-crisp.js"></script>
	<script type="text/javascript" src="ext/packages/sencha-charts/charts.js"></script>
	<script type="text/javascript" src="ext/ux/cometd/cometd.js"></script>
	<script type="text/javascript" src="ext/ux/cometd/ext-cometd.js"></script>
	<script type="text/javascript">
		var EwayUserObject = function(id,code,name,orgId,orgType,orgName,orgCode,personId){
		var me = this;
		this.id = id;
		this.code = code;
		this.name = name;
		this.orgId = orgId;
		this.orgType = orgType;
		this.orgName = orgName;
		this.orgCode = orgCode;
		this.personId = personId;
		return {
			getId : function(){
				return me.id;
			},
			setOrgName : function(orgName){
				me.orgName = orgName;
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
			},
			getPersonId : function(){
				return me.personId;
			}
		}
	}
	var locale='<%=FishCfg.locale%>';
	<%if (session.getAttribute("SESSION_USER") == null) {%>
		var ewayUser={getName:function(){
			return "";
		},getId:function(){
			return 1;
		}};
	<%} else {
		UserSession userSession = (UserSession) session.getAttribute("SESSION_USER");%>
		var ewayUser = new EwayUserObject(
			'<%=userSession.getUserId()%>',
			'<%=userSession.getUserCode()%>',
			'<%=userSession.getUserName()%>',
			'<%=userSession.getOrgId()%>',
			'<%=userSession.getOrgType() == null ? "" : String.valueOf(userSession.getOrgType().getId())%>',
			'<%=userSession.getOrgName()%>',
			'<%=userSession.getOrgCode()%>',
			'<%=userSession.getPersonId()%>');
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
	  	var EwayLocale = {};
	  	ewayUser.language =locale;
		if(Ext.String.startsWith(locale,"zh")){
			Ext.Loader.loadScript(Ext.cxtPath+"/ext/locale/locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/eway-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/system-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/machine-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/monitor-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/version-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/fault-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/report-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/param-locale-zh_CN.js");
		}else{
			Ext.Loader.loadScript(Ext.cxtPath+"/ext/locale/locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/eway-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/system-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/machine-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/monitor-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/version-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/fault-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/report-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/app/locale/en/param-locale-en.js");
		}
	</script>
	<script type="text/javascript" src="app.js"></script>
	<script type="text/javascript" src="ext/patch.js"></script>
</head>

<body>
	<div id="loading">
		<span class="title"><spring:message code="login.system.loading" /></span><span class="logo"></span>
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
