<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원로그인</title>
</head>
<body>
	<h2>회원 로그인</h2>
	<hr>
	<form action="loginok" method="post">
		아이디: <input type="text" name="mid" size="60"> <br><br>
		비밀번호: <input type="text" name="mpw" size="60"> <br><br>
		<input type="submit" value="로그인" size="60">
		<input type="reset" value="취소" size="60">
 	</form>
	
</body>
</html>