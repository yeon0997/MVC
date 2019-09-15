<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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
	List<UserDataBean> userList = (List<UserDataBean>) request.getAttribute("userList");

	%>
	
	<% for(UserDataBean m : userList){
      
   %>

	FIRSTNEME : <%=m.getFirstname() %>
	LASTNAME : <%=m.getLastname() %>
	EMAIL : <%=m.getEmail() %>

 <%
 
   }
  %>


	
</body>
</html>