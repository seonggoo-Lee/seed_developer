<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
서버정보 
<%=application.getServerInfo() %><br>

서블릿정보
<%=application.getMajorVersion()%>.<%= application.getMinorVersion() %><br>

JSP정보
<%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br>

[출처] 서블릿 버전, jsp버전 보는방법|작성자 머스크멜론



</body>
</html>