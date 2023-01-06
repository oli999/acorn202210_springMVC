<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/insertform.jsp</title>
<style>
	textarea{
		width: 768px;
		height: 300px;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>새글 작성 폼입니다.</h3>
		<form action="insert" method="post">
			<div>
				<label for="title">제목</label>
				<input type="text" name="title" id="title"/>
			</div>
			<div>
				<label for="content">내용</label>
				<textarea name="content" id="content" rows="10"></textarea>
			</div>
			<button type="submit" onclick="submitContents(this)">저장</button>
		</form>
	</div>

	<!--
		[ SmartEditor Spring Boot 에서 사용 설정하는 방법 ] 
		
		1. SmartEditor 폴더를 static 폴더에 복사 붙여 넣기 한다.
		2. custom.properties 파일에 업로드된 이미지를 저장할 경로를 지정하고 해당 properties 파일을 읽어들인다.
		   ex) 
		   
		   - 클래스
		   @PropertySource("classpath:custom.properties")
		   
		   - properties 파일 
		   file.location = 저장할 절대경로
		   
		3. SmartEditorController 클래스를 사용가능한 src 경로에 넣어 둔다. 
		   해당 클래스 안에서 
		   @Value("${file.location}")  을 통해서 에디터에서 업로드한 이미지를 해당 경로에 저장하도록 설정되어 있다.
		   
		4. 에디터가 필요한 페이지에서 아래 경로에 있는 HuskyEZCreator.js 를 로딩 시킨다. 
		   static 폴더에 넣어 두었기 때문에 로딩경로는 아래의 예제와 같다.
		   
		5. <textarea id="content">   의 id 와  아래의 소스코드에 추가된 javascript 에 
			elPlaceHolder: "content"
			getById["content"]   
			
			"content" 이부분을 일치 시켜야 한다.
			
		6. 폼 제출은 반드시 onclick="submitContents(this)" 와 같이 submitContents( ) 함수를 호출해서 
		   제출해야 한다.
		   
		7. 서버에서는 폼전송되는 내용을 그대로 DB 에 저장했다가  
		   해당 내용 자세히 보기 할때는  <div>${content}</div>  처럼 그냥 div 에 출력하면 되고
		   해당 내용 수정폼에는   <textarea id="content">${content}</textarea>  처럼 textarea 의 
		   innerText 로 출력하면 된다.  단) 아래의 javascript 가 수정 폼에도 역시 있어야 한다.
		
		8. 필요한 의존 dependency 는 아래와 같다
		
	  	<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.4</version>
		</dependency>
	-->
	<script src="${pageContext.request.contextPath }/SmartEditor/js/HuskyEZCreator.js"></script>
	<script>
		var oEditors = [];
		
		//추가 글꼴 목록
		//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef: oEditors,
			elPlaceHolder: "content",
			sSkinURI: "${pageContext.request.contextPath}/SmartEditor/SmartEditor2Skin.html",	
			htParams : {
				bUseToolbar : true,				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,		// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				//aAdditionalFontList : aAdditionalFontSet,		// 추가 글꼴 목록
				fOnBeforeUnload : function(){
					//alert("완료!");
				}
			}, //boolean
			fOnAppLoad : function(){
				//예제 코드
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
			},
			fCreator: "createSEditor2"
		});
		
		function pasteHTML() {
			var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
			oEditors.getById["content"].exec("PASTE_HTML", [sHTML]);
		}
		
		function showHTML() {
			var sHTML = oEditors.getById["content"].getIR();
			alert(sHTML);
		}
			
		function submitContents(elClickedObj) {
			//SmartEditor 에 의해 만들어진(작성한글) 내용이 textarea 의 value 가 되도록 한다. 
			oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);	// 에디터의 내용이 textarea에 적용됩니다.
			
			// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.
			
			try {
				//폼 제출하기 
				elClickedObj.form.submit();
			} catch(e) {}
		}
		
		function setDefaultFont() {
			var sDefaultFont = '궁서';
			var nFontSize = 24;
			oEditors.getById["content"].setDefaultFont(sDefaultFont, nFontSize);
		}
	</script>		
</body>
</html>
