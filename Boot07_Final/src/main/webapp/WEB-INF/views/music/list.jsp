<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>/music/list.jsp</title>
</head>
<body>
	<div class="container">
		<h3>${id } 님의 음악 파일 목록 입니다.</h3>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>아티스트</th>
					<th>파일명</th>
					<th>업로드일자</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list }">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.title }</td>
						<td>${tmp.artist }</td>
						<td>${tmp.orgFileName }</td>
						<td>${tmp.regdate }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>




