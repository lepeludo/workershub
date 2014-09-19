<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reviews</title>
</head>
<body>
	<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Reviews about ${worker.name}</h1>
	<c:forEach items="${reviews}" var="review" varStatus="counter">
	${counter.count}<br/>
		<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
			value="${review.date}" />
		<p />
	${review.text}<p/>
	</c:forEach>
</body>
</html>