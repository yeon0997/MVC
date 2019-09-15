<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.UserDataBean" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result</title>
</head>
<body>
	<h1>회원가입 결과 출력</h1>
	<%
		UserDataBean user2 = (UserDataBean)request.getAttribute("user");
	%>
	
	FirstName : <%= user2.getFirstname() %> <br>
	LastName : <%= user2.getLastname()%><br>
	Email : <%= user2.getEmail()%><br>
	
</body>
</html>