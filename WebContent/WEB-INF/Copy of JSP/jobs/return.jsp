<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Make Account</title>
</head>
<body>
	<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1><c:if test="${empty workers}">No Workers matched your criteria</c:if>
	<c:if test="not empty workers">These workers matched your criteria:</c:if></h1>
		
		<c:forEach items="${workers}" var='worker' varStatus="counter">
			${counter.count}: naam: ${worker.name} 
			<a href="${pageContext.request.contextPath}/jobs/detail?id=${worker.id}">Details</a>
			<a href="${pageContext.request.contextPath}/jobs/reviews?id=${worker.id}">Reviews</a>
		</c:forEach>
</body>
</html>