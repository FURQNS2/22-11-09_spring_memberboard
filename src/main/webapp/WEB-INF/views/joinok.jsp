<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 완료</title>
</head>
<body>
	<%
	//랩퍼 클래스로 강제형변환을 해서 값을 맞추면 된다.
	
		int checkId = Integer.parseInt((request.getAttribute("checkIdFlag").toString()));  // 오브젝트
		if(checkId == 1){
	%>
		<script language="javascript">
			alert("입력하신 아이디는 이미 사용중입니다.");
			history.go(-1);
		</script>
	<%
		}else 
	%>
			<h2>회원 가입을 축하드립니다!</h2>			
			<h2>${mname }님 반갑습니다!</h2>
	


	
</body>
</html>