<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <fmt:setLocale value="en_US" scope="session"/>
  <title><spring:message code="login.title" text=""/></title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link rel="shortcut icon" type="image/ico" href="resources/images/logo.ico" />
  <link rel="stylesheet" type="text/css" href="resources/css/login.css" media="all" />

  <style type="text/css">
  	.registerInputText{
  		border-color: #333333;
  		border-style: solid;
  		border-width: 1px;
  		width: 80px;
  		background-color: #666999;
  		color: yellow;
  		font-weight: bolder;
  	}
  </style>
	<script type="text/javascript">
		//添加键盘事件
		onkeydown = function(event){
			if(event.keyCode == 13){
				ajax();
			}
		}

		var xmlHttpReq = null;
		function ajax() {

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
		    var contnet = "username="+form1.username.value+"&password="+form1.password.value+"&"+new Date();

		    xmlHttpReq.open("POST","api/login?"+contnet,true);

		    xmlHttpReq.onreadystatechange = RequestCallBack;
		    xmlHttpReq.send(null);
		}

	    function RequestCallBack(){
	        //一旦readyState值改变，将会调用该函数
	        if(xmlHttpReq.readyState == 4){
	            if(xmlHttpReq.status == 200){
	                var myObject = eval('(' + xmlHttpReq.responseText + ')');
	                if(myObject.isRegister == false){
	                	 window.location.href='register.jsp';
	                }else if(myObject.success==false){
						 document.getElementById("loginError").style.display='';
						 document.getElementById("loginError").innerHTML = myObject.message;
					}else{
						 window.location.href='index.jsp';
					}
	            }
	        }
	    }

	    function loadimage(){
	    	document.getElementById("randImage").src = "image.jsp?"+Math.random();
	    	}

	    function resetValue()
	    {
	    	form1.username.value="";
	    	form1.password.value="";
	    }

	    function imageOn(obj)
	    {
	    	obj.className="imageSelect";
	    }

	    function imageOut(obj)
	    {
	    	obj.className="";
	    }

	</script>
</head>
<form name="form1" method="post" action="api/login">
<body>
<div id="wrap">
	
	<div id="main">
		<div style="height:40px;top:254px;position:relative;width:500px;left:627px;" >
				<div id="loginError" class="form-message error" style="display:none"></div>
		</div>
		<div style="position:relative;top:255px;left:758px;overflow:hidden;width:300px;height:230px">
			<input id="username" type="text" style="width:275px;border:0;height:24px;font-size: 20px" />
			<div id="distance" style="height:50px;overflow:hidden" ></div>
			<input id="password" type="password" style="width:275px;border:0;height:24px;font-size: 20px"/>
			<div style="position:relative;left:50px;top:50px">
				<img id="loginBtn" src="resources/images/index/login.png" onclick="javascript:return ajax();" style="cursor: pointer;"></img>
				<img id="cancelBtn" src="resources/images/index/cancel.png" onclick="resetValue();" style="cursor: pointer;"></img>
			</div>
		</div>
	</div>
	<div id="foot">
		<center style="margin-top: 3px">&copy;深圳市怡化时代科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;建议使用IE 9.0、FireFox、Google 浏览器&nbsp;&nbsp;&nbsp;&nbsp;</center>
	</div>
</div>
<div style='display:none;'>
	<div id='updatePassword' style='padding:10px; background:#fff;position: relative;top: 0px' align="center">
		<form name="registerForm">
		<h3>密码修改</h3>
			当前账号&nbsp;&nbsp;：<input name="currentUser" type="text" class="registerInputText" style="width:155px" readonly="readonly"/><br/><br/>
			输入新密码：<input name="password1" type="password" class="registerInputText" style="width:155px"/><br/><br/>
			确认新密码：<input name="password2" type="password" class="registerInputText" style="width:155px"/><br/><br/>
			<br/>
			<input class="updatePasswordSubmitBtn" type="button" value="确定">
			<input class="udpatePasswordCloseBtn" type="button" value="关闭">
			<pre style="color: blue;">密码中只能包含数字或字母，或者_@.符号，长度8-20位</pre>
		</form>
		<div style="height:40px;" >
				<div id="updatePwdError" class="form-message error" style="display:none"></div>
				<div id="updatePwdSuccess" class="form-message success" style="display:none"></div>
		</div>
	</div>
</div>
</body>
</form>
</html>
