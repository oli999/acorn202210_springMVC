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
		<h4>선택한 곡 : <i id="selectedSong"></i></h4>
		<audio id="myAudio"  controls></audio>
		<table>
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>아티스트</th>
					<th>파일명</th>
					<th>업로드일자</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list }">
					<tr>
						<td>${tmp.num }</td>
						<td>${tmp.title }</td>
						<td>${tmp.artist }</td>
						<td><a href="javascript:loadMusic(${tmp.num })">${tmp.orgFileName }</a></td>
						<td>${tmp.regdate }</td>
						<td>
							<a href="delete?num=${tmp.num}">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		function loadMusic(num){
			// num 에 해당하는 음악 정보를 서버에서 받아온다(페이지 전환 없이)
			fetch("${pageContext.request.contextPath}/music/detail?num="+num)
			.then((res)=>{
				//json 문자열을 서버에서 응답하기 때문에 res.json() 을 리턴하면
				return res.json();
			})
			.then((data)=>{
				//data 는 json 문자열에서 javascript 객체로 변환된 값이다.
				document.querySelector("#selectedSong").innerText=data.title;
				//로딩할 음원의 src 구성하기
				const src="${pageContext.request.contextPath}/resources/upload/"+data.saveFileName;
				//구성된 src 를 audio 요소의 src 에 부여하고 로딩하기
				const myAudio=document.querySelector("#myAudio");
				myAudio.setAttribute("src", src);
				myAudio.load();
			});
		}
	</script>
</body>
</html>










