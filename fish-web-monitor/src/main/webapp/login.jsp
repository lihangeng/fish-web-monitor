<%@ page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title><spring:message code="login.title"/></title>
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
	    	document.getElementsByName("username")[0].value="";
	    	document.getElementsByName("password")[0].value="";
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

<body>
<form name="form1" method="post" action="api/login">
<div id="wrap" style="visibility:visible;">
	<div id="top">
	</div>
	<div id="main">
		<div id="left" style="visibility: hidden">
        </div>
		<div id="right">
			<div id="right_top">
			</div>
			<div id="right_main" class="newbluefont1">
			<div style="height:40px;" >
				<div id="loginError" class="form-message error" style="display:none"></div>
			</div>
				<div style="position:relative;left:150px;background: transparent;">
					<spring:message code="login.username"/>：<input id="username" type="text" style="width: 155px" value="admin"/>
					<br/>
					<br/>
					<spring:message code="login.password"/>：<input id="password" type="password" style="width: 155px" value="admin"/>
					<br/><br/>
					<div style="position:relative;left:75px;">
						<img id="loginBtn" src="resources/images/index/login_01.gif" onclick="javascript:return ajax();" style="cursor: pointer;"></img>
						<img id="cancelBtn" src="resources/images/index/cancel_01.gif" onclick="resetValue();" style="cursor: pointer;"></img>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="foot">
		<center>&copy;<spring:message code="login.footer"/>&nbsp;&nbsp;&nbsp;&nbsp;</center>
	</div>
</div>

<div style='display:none;'>
	<div id='inline_content' style='padding:10px; background:#fff;position: relative;top: 30px' align="center">
		<form name="registerForm">
		<h3>系统注册</h3>
			序列号：	<input name="key" class="registerInputText"  type="text" readonly="readonly" style="width:100px">
			<span name="registerWait"><img src="resources/images/wait2.gif"></img><span>正在获取系统序列号...</span></span>
			<br></br>
			注册码：	<input name="serial1" class="registerInputText"  type="text" id="serial1">-
				    <input name="serial2" class="registerInputText"  type="text" id="serial2">-
				    <input name="serial3" class="registerInputText"  type="text" id="serial3">-
				    <input name="serial4" class="registerInputText"  type="text" id="serial4">-
				    <input name="serial5" class="registerInputText"  type="text" id="serial5">
			<br></br>
			<input class="registerSubmitBtn" type="button" value="注册">
			<input class="registerCloseBtn" type="button" value="关闭">
		</form>
		<div style="height:40px;" >
				<div id="registerError" class="form-message error" style="display:none"></div>
				<div id="registerSuccess" class="form-message success" style="display:none"></div>
		</div>
	</div>
	<script>
       function handle(){
       var val=document.getElementById('serial1').value;
       var val = val.replace(/(^\s*)|(\s*$)/g,"");
       if( val.length == 38)
        {
	       document.getElementById('serial1').value=val.substring(0,6);
	       document.getElementById('serial2').value=val.substring(7,13);
	       document.getElementById('serial3').value=val.substring(14,20);
	       document.getElementById('serial4').value=val.substring(21,27);
	       document.getElementById('serial5').value=val.substring(28,38);
        }
     }
        if(/msie/i.test(navigator.userAgent)) //ie浏览器
        {
          document.getElementById('serial1').onpropertychange = handle;
        }
       else
       {//非ie浏览器，比如Firefox
         document.getElementById('serial1').addEventListener("input", handle, false);
       }
 </script>
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
</form>
</body>
</html>
