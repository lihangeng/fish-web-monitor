<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<fmt:setLocale value="en_US" scope="session" />
	<title><spring:message code="login.title" /></title>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<link rel="shortcut icon" type="image/ico" href="resources/images/logo.ico" />

  <style type="text/css">
  	.registerInputText{
  		border-color: #333333;
  		border-style: solid;
  		border-width: 1px;
  		width: 100px;
  		background-color: #666999;
  		color: yellow;
  		font-weight: bolder;
  	}
  </style>
	<script type="text/javascript">

		//添加键盘事件
		onkeydown = function(event){
			if(event.keyCode == 13){
				reg();
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

		    xmlHttpReq.open("GET","api/system/register",true);

		    xmlHttpReq.onreadystatechange = RequestCallBack;
		    xmlHttpReq.send(null);
		}

	    function RequestCallBack(){
	        //一旦readyState值改变，将会调用该函数
	        if(xmlHttpReq.readyState == 4){
	            if(xmlHttpReq.status == 200){
	                var myObject = eval('(' + xmlHttpReq.responseText + ')');
					if(myObject.success==true){
						 document.getElementsByName("key")[0].value=myObject.key;
						 document.getElementById("regWait").style.display="none";
					}else{
						 window.location.href='index.jsp';
					}
	            }
	        }
	    }

	    function reg() {

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
            var serial = form1.serial1.value+"-"+form1.serial2.value+"-"+form1.serial3.value+"-"+form1.serial4.value+"-"+form1.serial5.value;
		    var contnet = "key="+form1.keyId.value+"&serial="+serial;

		    xmlHttpReq.open("POST","api/system/register?"+contnet,true);

		    xmlHttpReq.onreadystatechange = RequestCallBackReg;
		    xmlHttpReq.send(null);
		}

	    function RequestCallBackReg(){
	        //一旦readyState值改变，将会调用该函数
	        if(xmlHttpReq.readyState == 4){
	            if(xmlHttpReq.status == 200){
	                var myObject = eval('(' + xmlHttpReq.responseText + ')');
					if(myObject.success==true){
						window.location.href='login.jsp';
					}else{
						 document.getElementById("registerError").style.display='';
						 document.getElementById("registerError").innerHTML = myObject.message;
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
<body  onload="ajax()" >
<form name="form1">
<div>
	<div id='inline_content' style='padding:10px; background:#fff;position: relative;top: 30px' align="center">
		<h3><spring:message code="login.system.register" /></h3>
			<spring:message code="login.system.serialNo" />	<input name="key" id="keyId" class="registerInputText"  type="text" readonly="readonly" style="width:100px">
			<span name="registerWait" id ="regWait"><img src="resources/images/wait2.gif"></img><span><spring:message code="login.system.gainRegisterNo" /></span></span>
			<br></br>
			<spring:message code="login.system.registerNo" />	<input name="serial1" class="registerInputText"  type="text" id="serial1">-
				    <input name="serial2" class="registerInputText"  type="text" id="serial2">-
				    <input name="serial3" class="registerInputText"  type="text" id="serial3">-
				    <input name="serial4" class="registerInputText"  type="text" id="serial4">-
				    <input name="serial5" class="registerInputText"  type="text" id="serial5">
			<br></br>
			<input class="registerSubmitBtn" type="button" value="<spring:message code="login.system.registerButton" />" onclick="javascript:return reg();">
		</form>
		<div style="height:40px;" >
				<div id="registerError" class="form-message error" style="display:none"></div>
				<div id="registerSuccess" class="form-message success" style="display:none"></div>
		</div>
	</div>
</div>
</form>
</body>
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
</html>
