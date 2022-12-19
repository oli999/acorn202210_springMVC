<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/users/loginform.jsp</title>
</head>
<body>
	<div class="container">
		<h3>로그인 폼</h3>
		<form action="${pageContext.request.contextPath}/users/login" method="post">
			<input type="text" name="id" placeholder="아이디 입력..."/>
			<button type="submit">로그인</button>
		</form>
	</div>
</body>
</html>