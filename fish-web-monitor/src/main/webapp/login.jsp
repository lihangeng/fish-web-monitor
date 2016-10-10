<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>自助设备监控系统</title>
<link rel="stylesheet" type="text/css" href="resources/css/login.css">
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
					<div class="head">自助设备监控系统</div>
					<div  class="tips">	
				密码连续输错五次,账户将被冻结,现已输错3次！现已输错3次！
					</div>
					<div class="content">
						<div class="username">
							<div class="imgusername">
								
							</div>
							<div class="usernameinput">
								<input class="input_username" onblur="if(this.value==''){this.value='请输入用户名'; this.style.color='#999'}" onfocus="if(this.value=='请输入用户名')this.value=''; this.style.color='black'" value="请输入用户名" id="username" type="text" maxlength="20" />
							</div>
						</div>

						<div class="password">
							<div class="imgpassword">
								
							</div>
							<div class="passwordinput">
								<input class="input_password" id="txt" type="text"  value="请输入密码" onfocus="change(this)" maxlength="20" style="color:#999"/>		
								<input class="input_password" id="password" type="password"   maxlength="20" onblur="if(this.value==''){change(this)}" style="display:none;"/>				
							</div>
						</div>
						<div  class="checkboxlogin">
								<input type="checkbox" name = 'forceLogin' class="checkboxlogin1" />强制登录
						</div>
						<!--<div  class="username"><input  class="txt1" name="username" type="text" id="username" placeholder="用户名"   size="10" ></div>
						<div  class="passname"><input  class="txt2" name="password" type="password" id="password" placeholder="密码"   size="10" ></div>
						<div  class="checkboxlogin">
							<input type="checkbox" name = 'forceLogin' class="checkboxlogin1" />强制登录
				        </div>-->
						<button  class="login">登 录</button>
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