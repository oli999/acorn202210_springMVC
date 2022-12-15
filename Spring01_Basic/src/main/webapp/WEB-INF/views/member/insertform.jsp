<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/member/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>회원추가 폼</h1>
		<form action="${pageContext.request.contextPath}/member/insert" method="post">
			번호 <input type="text" name="num" /><br />
			이름 <input type="text" name="name"/><br />
			주소 <input type="text" name="addr"/><br />
			<button type="submit">추가</button>
		</form>
			
		<h1>회원추가 폼2</h1>
		<form action="${pageContext.request.contextPath}/member/insert2" method="post">
			번호 <input type="text" name="num" /><br />
			이름 <input type="text" name="name"/><br />
			주소 <input type="text" name="addr"/><br />
			<button type="submit">추가</button>
		</form>	
				
		<h1>회원추가 폼3</h1>
		<form action="${pageContext.request.contextPath}/member/insert3" method="post">
			번호 <input type="text" name="num" /><br />
			이름 <input type="text" name="name"/><br />
			주소 <input type="text" name="addr"/><br />
			<button type="submit">추가</button>
		</form>			
	</div>
</body>
</html>