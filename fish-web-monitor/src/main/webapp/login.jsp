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
					}else if(myObject.userState==1){
						_updatePassword();
					}else{
						 window.location.href='index.jsp';
					}
	            }
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
		function css(element,name,value){
			element.style[name]=value
		}
		function divHidden(){
			show($("updatePasswordDiv"),false);
		}
	  	//密码修改
	  	function _updatePassword(){

			var divError = $('updatePwdError');
			setValue(divError,"");
			show(divError,false);
	  		var div = $("updatePasswordDiv");
	  		show(div,true);
	  		setValue($Name("currentUser"),getValue($("username")));
	  		setValue($Name("password1"),"");
	  		setValue($Name("password2"),"");
	  		css(div,"padding","10px");
	  		css(div,"background","fff");
	  		css(div,"position","absolute");
	  		css(div,"top","0px");
	  		css(div,"margin-left","798px");
	  		css(div,"margin-top","141px");
	  		css(div,"width","282px");
	  		css(div,"height","400px");
	  	}

	    

	  //密码修改提交
		function updatePasswordSubmitFn(){
			var currentUser = getValue($Name("currentUser"));
			var password1 =  getValue($Name("password1"));
			var password2 = getValue($Name("password2")); 
			var oldPassword = getValue($('password'))
			var div = $('updatePwdError');
			if(password1.trim().length==0||password2.trim().length==0){
				setDivValue(div,'请输入密码!');
				show(div,true);
				return;
			}
			var pattern = /[^\w-'=\\[\];,.?"`~!@#$%^&*()_+|{}:"<>/]/;
			if(password1.length < 8 || password1.length>20){
				setDivValue(div,'密码长度不符规则!');
				show(div,true);
				return;
			}
			if(pattern.test(password1)){
				setDivValue(div,'密码不符规则!');
				show(div,true);
				return;
			}
			if(password1.trim()!= password2.trim()){
				setDivValue(div,'两次密码不一样!');
				show(div,true);
				return;
			}
			if(password1.trim()==oldPassword){
				setDivValue(div,'输入的新密码与旧密码相同，不可修改');
				show(div,true);
				return;
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
					  	 window.location.href='index.jsp';
					}else{
						 show($("updatePwdError"),true);
						 setDivValue($("updatePwdError"), myObject.message);
					}
	            }
	        }
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
   <div id='updatePasswordDiv' style='display:none;'>
	<div id='updatePassword' style='padding:10px; background:#fff;position: relative;top: 0px' align="center">
		<form name="registerForm">
		<h3>密码修改</h3>
			当前账号&nbsp;：<input name="currentUser" type="text" class="registerInputText" style="width:155px" readonly="readonly"/><br/><br/>
			输入新密码：<input name="password1" type="password" class="registerInputText" style="width:155px"/><br/><br/>
			确认新密码：<input name="password2" type="password" class="registerInputText" style="width:155px"/><br/><br/>
			<br/>
			<input class="updatePasswordSubmitBtn" type="button" value="确定" onclick="updatePasswordSubmitFn()">
			<input class="udpatePasswordCloseBtn" type="button" value="关闭" onclick="divHidden()">
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
