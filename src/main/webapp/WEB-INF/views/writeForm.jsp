<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 글쓰기</title>
</head>
<body>
	<h2>자유게시판 글쓰기</h2>
	<hr>
	<table border="1" width="600" cellspacing="0" cellpadding="0">
		<form action="write">
			<tr>
				<th>아이디</th> 
				<td>${mid }</td>
			</tr>
			<tr>
				<th>이름</th> 
				<td>${mname }</td>
			</tr>
			<tr>
				<th>제목</th> 
				<td><input type="text" size="60" name="ftitle"></td>
			</tr>
			<tr>
				<th>내용</th> 
				<td><textarea rows="10" size="40" name="fcontent"></textarea></td>
			</tr>
			<tr> 
				<td colspan="2">
					<input type="submit" value="글입력">
					<input type="reset" value="취소">
				</td>
			</tr>
		</form>	
	</table>
	
</body>
</html>