<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
<head>
<title>Reviews</title>
</head>
<body>
	<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Reviews about ${worker.name}</h1>
	<c:url value="reviews" var="url"/>
	<form action="${url}" method="POST">
	<c:forEach items="${reviews}" var="review" varStatus="counter">
	${counter.count} delete review <input type="checkbox" name="deletes"
			value="${review.id}">
		<br />
		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
			value="${review.date}" />
		<p />review by: ${review.customer.firstName} ${review.customer.lastName}<p />
	${review.text}<p />
	</c:forEach>
	<input type="submit" value="delete reviews">
	</form>
</body>
</html>