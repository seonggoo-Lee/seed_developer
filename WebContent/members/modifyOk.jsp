<%@page import="com.javalec.ex.MemberDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%  request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="dto" class="com.javalec.ex.MemberDto" />
<jsp:setProperty name="dto" property="*" />
    <%
    dto.setId((String)session.getAttribute("id"));
    MemberDao dao = MemberDao.getInstance();
    int ri = dao.updateMember(dto);
    
    if(ri == 1){
    %>
    <script>
    alert("정보를 수정하였습니다.");
    document.location.href="main.jsp";
    </script>
    <%
    } else {
    %>
    <script>
    alert("수정에 실패하였습니다.");
    document.location.href="modify.jsp";
    </script>
    <%
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