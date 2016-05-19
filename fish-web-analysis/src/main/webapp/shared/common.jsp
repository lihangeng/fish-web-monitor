<%@page import="com.yihuacomputer.common.FishCfg"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.yihuacomputer.fish.api.person.UserSession"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="UTF-8">

    <title>自助设备数据分析系统</title>
    <link rel="shortcut icon" type="image/ico" href="<%=request.getContextPath()%>/resources/images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/Index-all.css" media="all" />
<!-- 	<link rel="stylesheet" type="text/css" href="../ext/packages/charts/triton.css" media="all" />
 -->	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/ie9-hack.css" media="all" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/font-awesome.css" media="all" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ext-all.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ext/packages/charts/charts.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ux/cometd/cometd.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/ext/ux/cometd/ext-cometd.js"></script>
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
		var Ext = Ext || {};
		Ext.cxtPath = '<%=request.getContextPath()%>';
		var EwayLocale = {};
		Ext.BLANK_IMAGE_URL = Ext.cxtPath + '/resources/images/s.gif';
	  	Ext.themeModel = "new";
	  	Ext.Loader.setConfig({
	  		enabled : true,//动态加载
	        paths: {
	            'Ext.ux': '../ext/ux'
	        }
	      });
	  	
	  	ewayUser.language =locale;
		if(Ext.String.startsWith(locale,"zh")){
			Ext.Loader.loadScript(Ext.cxtPath+"/ext/locale/ext-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/eway-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/system-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/machine-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/monitor-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/version-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/fault-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/report-locale-zh_CN.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/param-locale-zh_CN.js");
		}else{
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/ext-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/eway-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/system-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/machine-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/monitor-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/version-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/fault-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/report-locale-en.js");
			Ext.Loader.loadScript(Ext.cxtPath+"/locale/en/param-locale-en.js");
		}
	</script>
	<!-- <script type="text/javascript" src="app.js"></script>
	<script type="text/javascript" src="../ext/patch.js"></script> -->
</head>
<body>
</body>
</html>

