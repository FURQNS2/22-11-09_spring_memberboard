<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID Check</title>
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
		}else {
	%>
		<script language="javascript">
			alert("사용가능한 아이디 입니다.");
			history.go(-1);
		</script>
	<% } %>	

</body>
</html>