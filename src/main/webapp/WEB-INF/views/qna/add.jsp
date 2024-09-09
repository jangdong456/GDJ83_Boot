<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Qna Add</h1>
<%-- 	<form action="" method="post" enctype="multipart/form-data"> --%>
<!-- 		<input type="text" name="boardTitle"> -->
<!-- 		<input type="text" name="boardWriter"> -->
<!-- 		<textarea rows="" cols="" name="boardText"></textarea> -->
		
<!-- 		<input type="file" name="attaches"> -->
<!-- 		<input type="file" name="attaches"> -->
<!-- 		<input type="file" name="attaches"> -->
<!-- 		<button>Add</button> -->
<%-- 	</form> --%>
	
	<form:form modelAttribute="qnaVO">
<!-- 		form:input은 무조건 text이다 타입이 정해져 있지 않다 -->
		<form:input path="boardWriter"/><br> <!--  path는 VO의 멤버변수명 즉, setter,getter이름 -->
		 <!-- 검증이 실패했을때 여기에 에러 메세지 띄운다
		  boardWriter가 검증이 실패했을때 에러 메세지를 띄운다.-->
		<form:errors path="boardWriter"></form:errors><br> 
		<form:input path="boardTitle"/><br>
		<form:errors path="boardTitle"></form:errors><br>
		<form:textarea path="boardText"/><br>	
<!-- 		form type="file" 이 처럼 없는 것들이 있는데 그럴땐 그냥 input태그를 사용하면 된다. -->
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<input type="file" name="attaches"><br>
		<button>Add</button><br>
		<form:button>Add</form:button>
	</form:form>
	
</body>
</html>