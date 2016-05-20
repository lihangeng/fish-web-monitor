<%@page language="java" contentType="text/html; charset=utf-8"pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<fmt:setLocale value="zh_CN" scope="session" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="login.title" text="" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/ico"
	href="resources/images/logo.ico" />
<link rel="stylesheet" type="text/css" href="resources/css/login.css"
	media="all" />
</head>
<style type="text/css">
.container1 {
	width: 1296px;
}
.content{
width:1296px;
float:left;
border:0;
}
input {
	color: #000000;
	height: 25px;
	width: 200px;
}
.txtupdate {
	border: 1px solid #dedede;
}
.xinghao {
	color: red;
}
.sure {
	background-color: #1070ca;
	border: none;
	height: 37px;
	width: 243px;
	color: #ffffff;
	font-size: 19px;
	font-family: Fixedsys;
	font-weight: bold;
}

.line {
	border-top: 1px solid #1070ca;
	width: 100%;
	height: 25px;
	float: left;
}

.xiugaimima {
	font-size: 20px;
	font-family: Fixedsys;
}

input:focus {
	border: 1px solid #1070ca;
}

.head {
	width: 627px;
	padding-left: 20px;
	float: left;
}

.headline {
	padding-top: 20px;
	width: 647px;
	float: left;
}
</style>
<body>
	
	<script type="text/javascript">
		<%if(request.getParameter("userCode")==null){%>
			 	window.location.href='login.jsp';
		<%}%>
	</script>
	<div class="container1">
		<div class="head">
			<img src="./resources/images/logo.png">
		</div>
		<div class="headline">
			<p class="xiugaimima"><spring:message code="login.updatePwd" /></p>
		</div>
	</div>
	<div class="line"></div>
	<div class="content" align="center">
			<table align="center" border="0" cellpadding="0" cellspacing="10">
				<tr>
					<td align="right"><spring:message code="login.updatePwd.userCode" /></td>
					<td><input class="txtupdate" name="userCode" type="text" size="15" readonly=true value=<%=(request.getParameter("userCode")==null?"":request.getParameter("userCode"))%> /></td>
				</tr>
				<tr>
					<td align="right"><span class="xinghao">*</span><spring:message code="login.updatePwd.oldPassWd" /></td>
					<td><input class="txtupdate" name="srcPw" type="password" size="15" maxlength=20 /></td>
				</tr>
				<tr>
					<td align="right"><span class="xinghao">*</span> <spring:message code="login.updatePwd.newPassWd" /></td>
					<td><input class="txtupdate" name="newPw" type="password" size="15" maxlength=20 /></td>
				</tr>
				<tr>
					<td align="right"><span class="xinghao">*</span> <spring:message code="login.updatePwd.newPassWdAgain" /></td>
					<td><input class="txtupdate" name="newPwAgin" type="password" size="15" maxlength=20 /></td>
				</tr>
			</table>
			<div align="center">
			
				<input class="sure" type="submit" value="<spring:message code='login.updatePwd.confirm' />" style="margin-top: 20px;" onclick="updatePasswordSubmitFn()"/>
				<div>
					<div id="updatePwdError" class="form-message error" style="display:none;width:200px;"></div>
					<div id="updatePwdSuccess" class="form-message success" style="display:none"></div>
				</div>
			</div>
	
<script type="text/javascript">
//添加键盘事件
	onkeydown = function(event) {
		if (event.keyCode == 13) {
			updatePasswordSubmitFn();
		}
	}
	function $(id){
		return document.getElementById(id);
	}
	
	function getValue(inputElement){
		return inputElement.value;
	}
	function show(element,flag){
		if(flag)
			element.style.display="";
		else
			element.style.display="none";
	}
	function $Name(elementName){
		return document.getElementsByName(elementName)[0];
	}
	function setValue(element,value){
		element.value=value;
	}
	function setDivValue(element,value){
		element.innerHTML =value;
	}
	function updatePasswordSubmitFn(){
		var currentUser = getValue($Name("userCode"));
		var password1 =  getValue($Name("newPwAgin"));
		var password2 = getValue($Name("newPw")); 
		var oldPassword = getValue($Name('srcPw'))
		var div = $('updatePwdError');
		show(div,false);
// 		login.updatePwd.tips.oldPassWd=请输入原始密码！
// 		login.updatePwd.tips.passWd=请输入密码！
// 		login.updatePwd.tips.passWdLengthRule=密码长度不符规则！
// 		login.updatePwd.tips.passWdRule=密码不符规则！
// 		login.updatePwd.tips.passWdDiff=两次密码不一样！
		if(oldPassword.trim().length==0){
			setDivValue(div,"<spring:message code='login.updatePwd.tips.oldPassWd' />");
			show(div,true);
			return false;
		}
		if(password1.trim().length==0||password2.trim().length==0){
			setDivValue(div,"<spring:message code='login.updatePwd.tips.passWd' />");
			show(div,true);
			return false;
		}
		var pattern = /[^\w-'=\\[\];,.?"`~!@#$%^&*()_+|{}:"<>/]/;
		if(password1.length < 8 || password1.length>20){
			setDivValue(div,"<spring:message code='login.updatePwd.tips.passWdLengthRule' />");
			show(div,true);
			return false;
		}
		if(pattern.test(password1)){
			setDivValue(div,"<spring:message code='login.updatePwd.tips.passWdRule' />");
			show(div,true);
			return false;
		}
		if(password1.trim()!= password2.trim()){
			setDivValue(div,"<spring:message code='login.updatePwd.tips.passWdDiff' />");
			show(div,true);
			return false;
		}
		if(password1.trim()==oldPassword.trim()){
			setDivValue(div,"<spring:message code='login.updatePwd.tips.passWdSame' />");
			show(div,true);
			return false;
		}
		if(window.ActiveXObject){
	        //IE5 以上是以 ActiveXObject 的方式
	        //引入XMLHttpRequest对象的
	        xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	    }else if(window.XMLHttpRequest){
	        //除IE以外的浏览器
	        //XMLHttpRequest是window的子对象
	        //实例化一个 XMLHttpRequest 对象
	        xmlHttpReq = new XMLHttpRequest();
		}
	    var contnet = "username="+currentUser+"&password="+oldPassword+"&newPassword="+password1;
	    xmlHttpReq.open("POST","api/login/updatePassword?"+contnet,true);
	    xmlHttpReq.onreadystatechange = RequestUpdateCallBack;
	    xmlHttpReq.send(null);
	}
	function RequestUpdateCallBack(){
	//一旦readyState值改变，将会调用该函数
    if(xmlHttpReq.readyState == 4){
        if(xmlHttpReq.status == 200){
            var myObject = eval('(' + xmlHttpReq.responseText + ')');
            if(myObject.success){
				 show($("updatePwdSuccess"),true);
				 setDivValue($("updatePwdSuccess"), myObject.message);
			  	 window.location.href='login.jsp';
			}else{
				 show($("updatePwdError"),true);
				 setDivValue($("updatePwdError"), myObject.errorMsg);
			}
        }
    }
	}
</script>
</body>
</html>