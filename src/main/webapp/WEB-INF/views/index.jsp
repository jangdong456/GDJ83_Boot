<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>
	<img alt="" src="/images/2.gif"> <!-- resources/static폴더 에 있다.-->
	
	<spring:message code="hello"></spring:message>
	<spring:message code="hello2" text="기본값"></spring:message>
	
	<sec:authorize access="!isAuthenticated()">
		<h1>Login 하기 전</h1>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<h1>Login 성공</h1>
		<sec:authentication property="principal" var="member"/>
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","></spring:message>
		<c:forEach items="${member.vos}" var="r">
			<h3>${r.roleName}</h3>		
		</c:forEach>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN')">
		<h1>관리자 임돠</h1>
	</sec:authorize>
	
	<a href="/member/login"><button>로그인</button></a>
	<a href="/member/logout"><button>로그아웃</button></a>
	
</body>
</html>