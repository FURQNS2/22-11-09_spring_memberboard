<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
</head>
<body>
	<h2>글 수정하기</h2>
	<hr>
	
	<table border="1" width="700" cellspacing="0" cellpadding="0">
		<form action="modify">
			<input type="hidden" value="${fbdto.fnum}" name="fnum">
			<tr bgcolor="pink">
				<th width="100">번호</th>
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
				<td><input type="text" name="ftitle" value="${fbdto.ftitle }"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td><textarea rows="10" cols="45" name="fcontent">${fbdto.fcontent }</textarea></td>
			</tr>
			<tr>	
				<th>게시일</th>
				<td>${fbdto.fdate }</td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="수정완료" >
					<input type="button" value="삭제" onclick="javascript:window.location='delete?fnum=${fbdto.fnum}'">
					<input type="button" value="취소" onclick="javascript:window.history.back()">
				</td>
			</tr>
		</form>
	</table>

</body>
</html>