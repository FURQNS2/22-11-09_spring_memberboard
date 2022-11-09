<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
	<h2>글 목록</h2>
	<hr>
	<table border="1" width="1000" cellspacing="0" cellpadding="0">
		<tr bgcolor="pink">
			<th>번호</th>
			<th>아이디</th>
			<th>글쓴이</th>
			<th width="600">글제목</th>
			<th>조회수</th>
			<th>게시일</th>
		</tr>
		
		<c:forEach items="${list }" var="fbdto">
		<tr>
			<td>${fbdto.fnum }</td>
			<td>${fbdto.fid }</td>
			<td>${fbdto.fname }</td>
			<td align="center">${fbdto.ftitle }</td>
			<td>${fbdto.fhit }</td>
			<td>${fbdto.fdate }</td>
		</tr>
		</c:forEach>
		
		<tr>
			<td colspan="6" align="right">
				<input type="button" value="글쓰기" onclick="javascript:window.location='writeForm'">
			</td>
			
		</tr>
		
	</table>
	
</body>
</html>