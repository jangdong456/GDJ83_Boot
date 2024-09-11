<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>My Page</h1>
	
	
	<sec:authentication property="principal" var="vo"/>
	<h3>${vo.username}</h3>
	<h3>${vo.name}</h3>
	<h3>=====================</h3>

	<h3><sec:authentication property="principal.email"/></h3> <!-- 이렇게 하면 El문 형식 안사용해도 바로 출력가능 -->
	<h3><sec:authentication property="name"/></h3>
	
	<a href="./update">회원수정</a>
</body>
</html>