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
	<table width="1000">
		<tr>
			<th align="left">글 목록</th>
			<% 	
				int idflag = Integer.parseInt((request.getAttribute("idflag").toString()));
				if(idflag == 1){
			%>
				<td align="right">
				${sid }님 로그인 중
				<input type="button" value="로그아웃" onclick="javascript:window.location='logout'">
				</td>
			<% }else{ %>
				<td align="right">	
					<input type="button" value="로그인" onclick="javascript:window.location='login'">
				</td>
			<% } %>
		</tr>
	</table>
	
	<hr>
	총게시글 개수: ${boardCount }
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
			<td align="center">${fbdto.fnum }</td>
			<td align="center">${fbdto.fid }</td>
			<td>${fbdto.fname }</td>
			<td align="center"><a href="contentView?fnum=${fbdto.fnum }">${fbdto.ftitle }</a></td>
			<td align="center">${fbdto.fhit }</td>
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