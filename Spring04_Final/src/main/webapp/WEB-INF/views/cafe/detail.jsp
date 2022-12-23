<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/detail.jsp</title>
</head>
<body>
	<div class="container">
		
		<%-- 만일 이전글(더 옛날글)의 글번호가 0 가 아니라면(이전글이 존재 한다면) --%>
		<c:if test="${dto.prevNum ne 0}">
			<a href="detail?num=${dto.prevNum }&condition=${condition}&keyword=${encodedK}">이전글</a>
		</c:if>
		
		<%-- 만일 다음글(더 최신글)의 글번호가 0 가 아니라면(다음글이 존재 한다면) --%>
		<c:if test="${dto.nextNum ne 0 }">
			<a href="detail?num=${dto.nextNum }&condition=${condition}&keyword=${encodedK}">다음글</a>
		</c:if>
		
		<%-- 만일 검색 키워드가 있다면 --%>
		<c:if test="${not empty keyword }">
			<p>
				<strong>${condition }</strong> 조건 
				<strong>${keyword }</strong> 검색어로 검색된 내용 자세히 보기
			</p>
		</c:if>
		<h3>글 상세 보기</h3>
		<table>
			<tr>
				<th>글번호</th>
				<td>${dto.num }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.title }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${dto.viewCount }</td>	
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.regdate }</td>
			</tr>
			<tr>
				<td colspan="2">
					<div>${dto.content }</div>
				</td>
			</tr>	
		</table>
		<c:if test="${sessionScope.id eq dto.writer }">
			<a href="updateform?num=${dto.num }">수정</a>
			<a href="javascript:" onclick="deleteConfirm()">삭제</a>
			<script>
				function deleteConfirm(){
					const isDelete=confirm("이 글을 삭제 하겠습니까?");
					if(isDelete){
						location.href="delete?num=${dto.num}";
					}
				}
			</script>
		</c:if>
	</div>
</body>
</html>