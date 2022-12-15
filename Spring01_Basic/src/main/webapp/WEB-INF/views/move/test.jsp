<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/move/test.jsp</title>
</head>
<body>
	<div class="container">
		<h3>페이지 이동 테스트</h3>
		<p>이 페이지는  /move/test 요청에 대해서 forward 이동된 jsp 페이지 입니다.</p>
		<ul>
			<li><a href="update">리다일렉트 이동하기</a></li>
			<li><a href="fortune">forward 이동하는 다른 방법</a></li>
			<li><a href="fortune2">forward 이동하는 다른 방법2</a></li>
			<li><a href="fortune3">forward 이동하는 다른 방법3</a></li>
		</ul>
	</div>
</body>
</html>