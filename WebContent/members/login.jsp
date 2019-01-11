<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:if test="${sessionScope.VaildMem != null }">
    <jsp:forward page="main.jsp"></jsp:forward>
    </c:if>
    <!-- 만약 ValidMem 가 있다면 로그인 하지말고 main.jsp 페이지로 이동해라 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="loginOk.jsp" method="post">
 	아이디: <input type="text" name="id" value=<c:if test="${sessionScope.id != null }">${sessionScope.id }</c:if>><br>
	비밀번호: <input type="password" name="pw"><br>	
	<input type="submit" value="로그인" > &nbsp;&nbsp; <input type="button" value="회원가입" onclick="javascript:window.location='join.jsp'">
</form>
</body>
</html>