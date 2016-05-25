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
<base href="<%=basePath%>"> 
<title>BeJson</title>
</head>
<body>
	<hr />
	<h2>JSON Files</h2>
	<s:iterator value="jsonfiles">
		<a href="<s:property value='value'/>"><font><s:property value="key"/></font></a>
	</s:iterator>
	
	<br/>
	<h2>MarkDown Files</h2>
	<s:iterator value="mdfiles">
		<a href="<s:property value='value'/>"><font><s:property value="key"/></font></a>
	</s:iterator>

	<br/>
	<hr/>
	<h2>add JSON</h2>
	<s:form action="jsonadd">
		<s:textarea label="JSON:" name="content" rows="5" cols="80"></s:textarea><br/>
		<s:textfield label="URI:" name="name"></s:textfield><br/>
		<s:submit value="提交" align="left"></s:submit>
	</s:form>
	
	<br/>
	<hr/>
	<h2>add MarkDown</h2>
	<s:form action="jsonmd">
		<s:textarea label="MD:" name="content" rows="5" cols="80"></s:textarea><br/>
		<s:textfield label="URI:" name="name"></s:textfield><br/>
		<s:submit value="提交" align="left"></s:submit>
	</s:form>

</body>
</html>