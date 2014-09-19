<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<title>Make Account</title>
</head>
<body>
	<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>email message</h1>
	<c:url value="/administrator/email" var="url">
		<c:param name="email" value="${email}"></c:param>
	</c:url>
	<form action="${url}" method="post">
		<textarea rows="5" cols="80" id="message" name="message"></textarea>
		<button type="submit" class="btn btn-default">Send email</button>
	</form>
</body>
</html>