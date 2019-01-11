<%@page import="com.javalec.ex.*"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% request.setCharacterEncoding("utf-8");; %>
 <jsp:useBean id="dto" class="com.javalec.ex.MemberDto"/>
 <jsp:setProperty name="dto" property="*" />
 <%
  dto.setrDate(new Timestamp(System.currentTimeMillis()));
  MemberDao dao = MemberDao.getInstance();
  if(dao.confirmId(dto.getId()) == MemberDao.MEMBER_EXISTENT){
 %>
  <script>
  alert("아이디가 이미 존재 합니다.");
  history.back();
  
  </script>
  <%
  }else {
	  int ri = dao.insertMember(dto);
	  if(ri == MemberDao.MEMBER_JOIN_SUCCESS){
		  session.setAttribute("id", dto.getId()); //회원가입된 사람의 아이디를 세션에 저장 // 가입 후 로그인시 자동입력기능
  %>
<script>
	alert("회원가입을 축하 합니다.");
	document.location.href = "login.jsp";
</script>
<%
	  } else {
  %>
    <script>
    alert("회원가입에 실패했습니다.");
    document.location.href="login.jsp";
    </script>
<%
	  }
  }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>