<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/ajax_form.jsp</title>
</head>
<body>
	<div class="container">
   		<form action="${pageContext.request.contextPath}/gallery/insert" method="post" id="insertForm">
      		<input type="hidden" name="imagePath" id="imagePath"/>
      		<div>
         		<label for="caption">설명</label>
         		<input type="text" name="caption" id="caption"/>
      		</div>
   		</form>
   		<form action="${pageContext.request.contextPath}/gallery/ajax_upload" method="post" id="ajaxForm" enctype="multipart/form-data">
      		<div>
         		<label for="image">이미지</label>
         		<input type="file" name="image" id="image" 
            		accept=".jpg, .jpeg, .png, .JPG, .JPEG"/>
      		</div>
   		</form>
   		<button id="submitBtn">등록</button>
   		<div class="img-wrapper">
      		<img />
   		</div>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/gura_util.js"></script>
	<script>
		//이미지를 선택했을 때, 실행할 함수 등록
		document.querySelector("#image").addEventListener("change", function(){
			//id 가 ajaxForm 인 form 을 ajax 전송 시킨다.
			const form = document.querySelector("#ajaxForm");
			//util 함수를 이용해서 ajax 전송
			ajaxFormPromise(form)
			.then(function(response){
				return response.json();
			})
			.then(function(data){
				//data : {imagePath:"xxx.jpg"} 형식의 obj
				console.log(data);
				//이미지 경로에 context Path 추가하기
				const path = "${pageContext.request.contextPath}/gallery/images/" + data.imagePath;
				//img 태그에 경로 추가
				document.querySelector(".img-wrapper img").setAttribute("src", path);
				//위의 form 의 input hidden 요소에 value 로 넣어서 db 에 저장
				document.querySelector("#imagePath").value = data.imagePath;
			});
		});
		
		document.querySelector("#submitBtn").addEventListener("click", function(){
			document.querySelector("#insertForm").submit();
		});
	</script>
</body>
</html>