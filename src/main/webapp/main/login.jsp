<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta charset="UTF-8">
	<title>xxxx有限公司</title>

	<%@include file="../common/init.jsp" %>
	
	<script type="text/javascript" src="login.js"></script>
	<script>

	</script>
	<style>
	#title{
		text-align:center;
		position:absolute;
		top:25%;
		left:50%;
		width:380px;
		margin-left:-190px;
		color:white;
		
	}
	#title h1{
		font-family:sans-serif,"幼圆","黑体";
		
	}
	</style>
  </head>
  
  <body style="background-image: url(bg.jpg);background-position:center;background-repeat:no-repeat;background-attachment:fixed;">
  <div id="title">
   <h1 id="h1">xxxxx有限公司</h1>
   <h2 id="h2">人力资源管理系统</h2>
   <div>
  </body>
</html>