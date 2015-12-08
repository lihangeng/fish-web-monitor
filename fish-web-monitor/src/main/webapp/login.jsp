<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<fmt:setLocale value="en_US" scope="session" />
<title><spring:message code="login.title" text="" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/ico"
	href="resources/images/logo.ico" />
<link rel="stylesheet" type="text/css" href="resources/css/login.css"
	media="all" />

<script type="text/javascript">
	//添加键盘事件
	onkeydown = function(event) {
		if (event.keyCode == 13) {
			ajax();
		}
	}

	var xmlHttpReq = null;

	function ajax() {

		if (window.ActiveXObject) {
			//IE5 以上是以 ActiveXObject 的方式
			//引入XMLHttpRequest对象的
			xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		} else if (window.XMLHttpRequest) {
			//除IE以外的浏览器
			//XMLHttpRequest是window的子对象
			//实例化一个 XMLHttpRequest 对象
			xmlHttpReq = new XMLHttpRequest();
		}
		var contnet = "username=" + getValue($('username')) + "&password="
				+ getValue($('password')) + "&" + new Date();

		xmlHttpReq.open("POST", "api/login?" + contnet, true);

		xmlHttpReq.onreadystatechange = RequestCallBack;
		xmlHttpReq.send(null);
	}

	function RequestCallBack() {
		//一旦readyState值改变，将会调用该函数
		if (xmlHttpReq.readyState == 4) {
			if (xmlHttpReq.status == 200) {
				var myObject = eval('(' + xmlHttpReq.responseText + ')');
				if (myObject.isRegister == false) {
					window.location.href = 'register.jsp';
				} else if (myObject.success == false) {
					document.getElementById("loginError").style.display = '';
					document.getElementById("loginError").innerHTML = myObject.message;
				} else if (myObject.userState == 1) {
					window.location.href = 'updatePwd.jsp?userCode='
							+ getValue($('username'));//_updatePassword();
				} else {
					window.location.href = 'index.jsp';
				}
			}
		}
	}

	function $(id) {
		return document.getElementById(id);
	}

	function getValue(inputElement) {
		return inputElement.value;
	}
	function show(element, flag) {
		if (flag)
			element.style.display = "";
		else
			element.style.display = "none";
	}
	function $Name(elementName) {
		return document.getElementsByName(elementName)[0];
	}
	function setValue(element, value) {
		element.value = value;
	}
	function setDivValue(element, value) {
		element.innerHTML = value;
	}
	function css(element, name, value) {
		element.style[name] = value
	}
	function divHidden() {
		show($("updatePasswordDiv"), false);
	}
	
	
	function textFocus() {
		$('username').focus();
	}
	
</script>
</head>
<form name="form1">
	<body onLoad="textFocus()">
		<div class="container">
			<div class="head">
				<img src="resources/images/logo.PNG">
			</div>
			<div class="left">
				<div class="left-content">
					<img src="resources/images/left.PNG">
				</div>
			</div>
			<div class="right">
				<div class="right-content">
					<div class="content">
						<div class="text" align="center">
							<p><spring:message code="login.sign" /></p>
							<div class="line"></div>
							<div id="loginError" class="form-message error" style="display: none"></div>
							<div style="width:241px;height:35px;border:1px solid #dedede;margin-top:15px;">
								<label class="laber1" ></label>
								<input  placeholder="<spring:message code='login.username' />" class ="txt" id="username" type="text"  maxlength=20 size="10" "
								/>
							</div>
							
							 <div style="width:241px;height:35px;border:1px solid #dedede;margin-top:25px;">
								<label class="laber2" ></label>
								 <input ;" placeholder="<spring:message code='login.password' />"
						 class="txt" id="password" type="password" maxlength=20 size="10" />
							</div>
							 
								 <input style="margin-top: 30px;" class="login"
								type="button" name="submit" value="<spring:message code='login.submit' />" onclick="ajax()">
							<div class="finally"></div>

						</div>
					</div>
				</div>
			</div>
			</div>
			    <div   class="bottom" >

<table align="center" border="0" cellpadding="0" spacpadding="0">
<tr >
	<td ><font style="font-size: 14px; color:#5e5e5f" ><spring:message code='login.footer' /></font>

</td>
</tr>
</table>

</div>

	</body>
</form>
</html>
