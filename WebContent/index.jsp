<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>"> 
<title>首页</title>
</head>
<body>

<p align="center">欢迎来到首页</p>

<table width="50%" align="center">
	<tbody align="left">
		<tr>
			<td><a href="sayHello">所有信息</a></td>
			<td><a href="sayGood">现期表</a></td>
			<td><a href="sayinfo">价格信息表</a></td>
			<td><a href="sayPrice">价格表</a></td>
		</tr>
		<tr>
			<td><a href="sconfig">配置</a></td>
			<td><a href="saySync">同步数据</a></td>
			<td><a href="listjson.jsp">json格式化</a></td>
			<td><a href="logs">查看日志</a></td>
		</tr>
	</tbody>
</table>

</body>
</html>