<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="500" clellpadding="0" cellspacing="0" border="1">
		<form action="modify.do" method="post">
			<input type="hidden" name="bId" value="${content_view.bId }">
			<tr>
				<td>번호</td>hdfdas
				<td> ${content_view.bId }</td>
			</tr>
			<tr>
				<td>히트</td>asdklfas
				<td> ${content_view.bHit }</td>
			</tr>
			<tr>
				<td>이름</td>
				<td> <input type="text" name="bName" value="${content_view.bName }"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td> <input type="text" name="bTitle" value="${content_view.bTitle }"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td> <textarea rows="10" name="bContent">${content_view.bContent }</textarea></td>
			</tr>
			<tr>
				<td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp; 
				<a href="/ex/list.do">목록보기</a> &nbsp;&nbsp; <a href="/ex/delete.do?bId=${content_view.bId }">삭제</a>&nbsp;&nbsp;
				<a href="/ex/reply_view.do?bId=${content_view.bId }">답변</a>
			</tr>
		</form>
	
	
	</table> 
</body>
</html>