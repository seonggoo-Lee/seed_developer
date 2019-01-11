<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action ="objelOk.jsp" method = "get">
	아디 : <input type="text" name="id"><br>
	비번 : <input type="password" name="pw"><br>
	<input type="submit" value="login">
	</form>
	
	<%
	application.setAttribute("application_name", "application_value");
	session.setAttribute("session_name", "session_value");
	pageContext.setAttribute("page_name", "page_value");
	request.setAttribute("request_name", "request_value"); 

	%>
	
	
	
</body>
</html>