<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <fmt:setLocale value="zh_CN" scope="session"/>
  <title><spring:message code="login.title" text=""/></title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
   <link rel="shortcut icon" type="image/ico" href="resources/images/logo.ico" />
   <link rel="stylesheet" type="text/css" href="resources/css/login.css" media="all" />

	<script type="text/javascript">
		//添加键盘事件
		onkeydown = function(event){
			if(event.keyCode == 13){
				ajax();
			}
		}

		var xmlHttpReq = null;
		function $(id){
			return document.getElementById(id);
		}
		
		function getValue(inputElement){
			return inputElement.value;
		}
		
		
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
		    var contnet = "username="+getValue($('username'))+"&password="+getValue($('password'))+"&"+new Date();

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



	</script>
</head>
<form name="form1">
<body>
 <div  class="container">
	

  
		
  <div  class="head">
<img src="resources/images\logo.PNG">
	
  </div>

<div class="left">
	<div  class="left-content">
		<img src="resources/images\left.PNG" >
	</div>
</div>
<div class="right">
	<div class="right-content">
		<div  class="content">
			<div  class="text" align="center">
		

						<p>监 控 系 统 登 录</p>
						<div class="line">
							
						</div>
						
							<div id="loginError" class="form-message error" style="display:none"></div>
					
	                     <input  class="txt" id="username"  type="text" size="10"  style="margin-top:17px;" />
                   
						<input class="txt2" style="margin-top:25px;" id="password"  type="password" size="10" />
				
						 <input style="margin-top:30px;" class="login"  type="button" name="submit" value="登   录"  onclick="ajax()" > 
						 <div class="finally"></div>
				         		
		</div>
	</div>
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
