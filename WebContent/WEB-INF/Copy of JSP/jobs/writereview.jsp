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
	<h1>Write a review for ${worker.name}</h1>
	<c:url value="/jobs/reviews" var="succesurl" />
	<sf:form action="${succesurl}" method="post" commandName="reviewForm">
		<sf:label path="text">Write Your review:</sf:label><br/>
		<sf:textarea cols="60" rows="20" path="text" />
		<sf:errors path="text" />
		<br />
		<sf:hidden path="workerId" value="${worker.id}"/>
		<input type="submit" value="submit" />
	</sf:form>




</body>
</html>