<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta charset="UTF-8">
<title>自助设备监控系统</title>
<link rel="stylesheet" type="text/css" href="resources/css/login1.css"> -->

<fmt:setLocale value="zh_CN" scope="session" />
<title><spring:message code="login.title" text="" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" type="image/ico"
	href="resources/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="resources/css/login.css"
	media="all" />

<script type="text/javascript">
	//添加键盘事件
	document.onkeydown = function() {
		if (event.keyCode == 13) {
			//登录按钮获取焦点;如果是用户名获取焦点会导致第一次回车时候密码为空
// 			$('loginButton').focus();
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
		var contnet = "forceLogin="
				+ $('forceLogin').checked+ "&username="
				+ encodeURIComponent(getValue($('username'))) + "&password="
				+ encodeURIComponent(getValue($('password'))) + "&"
				+ new Date();

		xmlHttpReq.open("POST", "api/login?" + contnet, true);

		xmlHttpReq.onreadystatechange = RequestCallBack;
		xmlHttpReq.send(null);
	}

	function RequestCallBack() {
		//一旦readyState值改变，将会调用该函数
		if (xmlHttpReq.readyState == 4) {
			if (xmlHttpReq.status == 200) {
				var myObject = eval('(' + xmlHttpReq.responseText + ')');
				if (myObject.isRegister ==false) {
					window.location.href = 'register.jsp';
				} else if (myObject.success == false) {
					//当用户名为空时直接提示用户
					if(getValue($('username'))=="<spring:message code='login.username' />"){
						document.getElementById("loginError").innerHTML = "用户名不能为空";
					}else{
						document.getElementById("loginError").innerHTML = myObject.message;
						document.getElementById("loginError").style.display = '';
					}
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

	window.onload = function(){
		$('username').focus();
	}
</script>
</head>
<script>  
  function change(obj){ 
	  obj.style.display = "none";  
	  if(obj.type=="text")  {       
		  document.getElementById('password').style.display = "block";     
		  document.getElementById('password').focus();//加上    
  }
  else{     
	  document.getElementById('txt').style.display = "block";    
	  }  
  }   
  </script> 
<body>
<div class="container">
	<div class="top"></div>
	<div class="bg"></div>
	<div class="main">
		<div class="left">
			<div class="left-content">
	
			</div>
		</div>
		<div class="right">
			<div class="loginForm">
				<div class="loginForm1">
					<div class="head"><spring:message code="login.title" text="" /></div>
					<div id="loginError" class="tips"></div>
					<div class="content">
						<div class="username">
							<div class="imgusername">
								
							</div>
							<div class="usernameinput">
										<input class="input_username"
											onblur="if(this.value==''){this.value='<spring:message code='login.username' />'; this.style.color='#999'}"
											onfocus="if(this.value=='<spring:message code='login.username' />')this.value=''; this.style.color='black'"
											value="<spring:message code='login.username' />"
											id="username" type="text" maxlength="20" />
									</div>
						</div>

						<div class="password">
							<div class="imgpassword">
								
							</div>
							<div class="passwordinput">
								<input class="input_password" id="txt" type="text"  value="<spring:message code='login.password' />" onfocus="change(this)" maxlength="20" style="color:#999"/>		
								<input class="input_password" id="password" type="password"   maxlength="20" onblur="if(this.value==''){change(this)}" style="display:none;"/>				
							</div>
						</div>
						<div  class="checkboxlogin">
								<input type="checkbox" name = 'forceLogin' class="checkboxlogin1" id='forceLogin'/><spring:message code='login.forceLoginIn' />
						</div>
							<input  class="login" id="loginButton"
									type="button" name="submit"
									value="<spring:message code='login.submit' />" onclick="ajax()">

								<div class="finally"></div>
					</div>	
				
				</div>	
			</div>
		</div>
	
	</div>
	<div class="footer">©深圳市怡化电脑股份有限公司&nbsp;【建议使用IE9.0+、FireFox、Google浏览器】</div>
	<div class="bottom">	
		<div class="bottom1"></div>
		<div class="bottom2"></div>
		<div class="bottom3"></div>
		<div class="bottom4"></div>
		<div class="bottom5"></div>
		<div class="bottom6"></div>
	</div>
</div>
</body>
</html>