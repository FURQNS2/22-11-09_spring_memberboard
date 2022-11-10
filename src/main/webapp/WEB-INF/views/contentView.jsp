<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>작성한 글 보기</h2>
	<hr>
	<table border="1" width="400" cellspacing="0" cellpadding="0">
		<tr bgcolor="pink">
			<th>번호</th>
			<td>${fbdto.fnum}</td>
		</tr>
		<tr>	
			<th>아이디</th>
			<td>${fbdto.fid }</td>		
		</tr>	
		<tr>
			<th>글쓴이</th>
			<td>${fbdto.fname }</td>
		</tr>	
		<tr>
			<th>글제목</th>
			<td>${fbdto.ftitle }</td>
		</tr>
		<tr>
			<th height="200">글내용</th>
			<td>${fbdto.fcontent }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${fbdto.fhit }</td>
		</tr>
		<tr>	
			<th>게시일</th>
			<td>${fbdto.fdate }</td>
		</tr>
		
		<% 	
			int idflag = Integer.parseInt((request.getAttribute("idflag").toString()));
			if(idflag == 1){
		%>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="javascript:window.location='modifyView?fnum=${fbdto.fnum}'">
				<input type="button" value="목록" onclick="javascript:window.location='list'">
			</td>
		</tr>
		<% }else{ %>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="목록" onclick="javascript:window.location='list'">
			</td>
		</tr>
		<% } %>
	</table>

</body>
</html>