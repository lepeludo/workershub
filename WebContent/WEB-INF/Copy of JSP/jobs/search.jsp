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
	<h1>Fill each field</h1>
	<c:url value="/jobs/dosearch" var="url" />
	<sf:form action="${url}" method="post" commandName="workersForm">
		<sf:errors path="jobTypes" />
		<c:forEach items="${jobTypes}" var='jobtype'>
			<sf:label path="jobTypes">${jobtype.jobDescription}</sf:label>
			<sf:checkbox path="jobTypes" value="${jobtype.id}" />
		</c:forEach>
		<br />
		<sf:errors path="locations" />
		<c:forEach items="${locations}" var='location'>
			<sf:label path="locations">${location.country}, ${location.province}</sf:label>
			<sf:checkbox path="locations" value="${location.id}" />
			<br />
		</c:forEach>
		<p />
		<input type="submit" value="submit" />
	</sf:form>

</body>
</html>