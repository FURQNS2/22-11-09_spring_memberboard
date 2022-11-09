<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 확인</title>
</head>
<body>
	<%
	//랩퍼 클래스로 강제형변환을 해서 값을 맞추면 된다.
	
		int checkId = Integer.parseInt((request.getAttribute("checkIdFlag").toString()));  // 오브젝트
		int checkPw = Integer.parseInt((request.getAttribute("checkPwFlag").toString()));  // 오브젝트
		
		if(checkId == 0){
	%>		
		<script language="javascript">
			alert("입력하신 아이디가 존재하지 않습니다.");
			history.go(-1);
			</script>
	
	<%	} else {				
			if(checkPw == 0){
	%>
				<script language="javascript">
				alert("비밀번호가 다릅니다.");
				history.go(-1);
				</script>
					
	<%      }else{ %>
			<h2>로그인을 축하드립니다!</h2>	
			<h2>${mid }	님 반갑습니다!</h2>	
			<h2>회원이름: ${mname }</h2>	
			<a href="writeForm">글쓰기</a>
			<a href="list">글 목록</a>
			<a href="logout">로그아웃</a>
					
	<% 
	    } 
	  }
	%>
		

	
</body>
</html>