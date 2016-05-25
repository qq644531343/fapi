<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	设置爬虫配置:<br/><br/>
	<form action="sconfigSubmit" method="POST">
		缓存有效时间(分): &nbsp;&nbsp;&nbsp;<input type="text" value="${Catch_Avaible_Minutes }" name="Catch_Avaible_Minutes"><br/>
		默认编码:&nbsp;&nbsp;&nbsp;<input type="text" value=${defaultEncoding } name="defaultEncoding"><br/>
		缓存目录:&nbsp;&nbsp;&nbsp;<input type="text" value=${tempFileDir } name="tempFileDir"><br/>
		多重解析间隔(毫秒):&nbsp;&nbsp;&nbsp;<input type="text" value=${timeInterval } name="timeInterval"><br/>
		强制更新:&nbsp;&nbsp;&nbsp;是<input type="radio" name="forceUpdate" value="true" checked="${(forceUpdate=='true' ? 'checked' : '') }">&nbsp;
								  否<input type="radio" name="forceUpdate" value="false" checked="${(forceUpdate=='false' ? 'checked' : '') }">
		<br/><br/><input type="submit" value="提交">
	</form>
</body>
</html>