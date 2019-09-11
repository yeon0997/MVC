<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.UserDataBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>simpleMVC</title>
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입 정보</h1>
	<%
		UserDataBean user2 = (UserDataBean)request.getAttribute("user");
	%> <!-- 컨트롤러에서 값을 받아옴 -->
	
	FirstName : <%=user2.getFirstname() %><br>
	LastName : <%=user2.getLastname() %><br>
	Email : <%=user2.getEmail() %>
	
</body>
</html>