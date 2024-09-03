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
	<h1>Qna List</h1>
	
	<table border="1">
		<thead>
			<tr>
				<th>Num</th>
				<th>Title</th>
				<th>Writer</th>
				<th>Date</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${list}" var="vo">
				<tr>
					<th>${vo.boardNum}</th>
					<th>${vo.boardTitle }</th>
					<th>${vo.boardWriter}</th>
					<th>${vo.createDate}</th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>