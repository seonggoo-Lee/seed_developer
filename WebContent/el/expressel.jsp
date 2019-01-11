<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="member" class="com.javalec.ex.MemberInfo" scope="page"/>
<jsp:setProperty property="name" name="member" value="홍길동"/>
<jsp:setProperty property="id" name="member" value="abc"/>
<jsp:setProperty property="pw" name="member" value="123"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	이름 : <jsp:getProperty property="name" name="member"/><br>
 	아디 : <jsp:getProperty property="id" name="member"/><br>
 	비번 : <jsp:getProperty property="pw" name="member"/><br>


<hr>
 	이름 : ${member.name }<br>
 	아디 : ${member.id }<br>
 	비번 : ${member.pw }<br>
</body>
</html>