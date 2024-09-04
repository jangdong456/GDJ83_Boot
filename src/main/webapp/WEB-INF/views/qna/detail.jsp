<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Detail Page</h1>
	<h3>${vo.boardTitle}</h3>
	<h3>${vo.boardWriter}</h3>
	<h3>${vo.boardText}</h3>
	<c:forEach items="${vo.ar}" var="f">
		<img src="/files/${board}/${f.fileName}">
	</c:forEach>
</body>
</html>