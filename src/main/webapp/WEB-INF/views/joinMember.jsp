<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h2>회원가입</h2>
	<hr>
		<table border="1" width="600" cellspacing="0" cellpadding="0">
			<form action="checkId">
				<tr height="30">
					<th bgcolor="skyblue">아이디체크</th>
					<td>
						<input type="text" size="60" name="checkId">
						<input type="submit" size="60" value="중복확인">		
					</td>
				</tr>
			</form>
			
			<form action="joinok" method="post">
				<tr height="30">
					<th bgcolor="pink">회원아이디</th>
					<td>
						<input type="text" size="60" name="mid">
					</td>
				</tr>
				<tr height="40">
					<th bgcolor="pink">비밀번호</th>
					<td>
						<input type="text" size="60" name="mpw">
					</td>
				</tr>
				<tr height="40">
					<th bgcolor="pink">회원이름</th>
					<td>
						<input type="text" size="60" name="mname">
					</td>
				</tr>
				<tr height="40">
					<th bgcolor="pink">회원이메일</th>
					<td>
						<input type="text" size="60" name="memail">
					</td>
				</tr>
				<tr height="40">
					<td colspan="5" align="right">
						<input type="submit" value="회원가입">
						<input type="reset" value="취소">
					</td>
				</tr>
			</form>
		</table>
</body>
</html>