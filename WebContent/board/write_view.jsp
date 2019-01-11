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
		<form action="write.do" method="post">
			<input type="hidden" name="bId" >
			<tr>
				<td>이름</td>
				hello1212
				<td> <input type="text" name="bName" ></td>
			</tr>
			<tr>
				<td>제목</td>dasfjsdkl
				<td> <input type="text" name="bTitle" ></td>
			</tr>
			<tr>
				<td>내용</td>
				<td> <textarea rows="10" name="bContent"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; 
				<a href="/ex/list.do">목록보기</a> 
			</tr>
		</form>
	
	
	</table> 
</body>
</html>