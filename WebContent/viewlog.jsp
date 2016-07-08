<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <base href="<%=basePath%>">  --%>
<title>系统运行日志</title>
</head>
<body>
	<h2>运行日志</h2>
	<s:iterator value="htmlfiles">
		<a href="<s:property value='value'/>"><font><s:property value="key"/></font></a>
	</s:iterator>
	
	<h2>Tomcat日志</h2>
	
	<s:iterator value="logfiles">
		<a href="<s:property value='value'/>"><font><s:property value="key"/></font></a>
	</s:iterator>
	
</body>
</html>