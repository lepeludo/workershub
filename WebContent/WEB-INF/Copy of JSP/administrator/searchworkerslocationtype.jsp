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
	<h1>
		<c:if test="${empty workers}">No Workers matched your criteria</c:if>
		<c:if test="not empty workers">These workers matched your criteria:</c:if>
	</h1>
	<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Fill each field</h1>
	<c:url value="/administrator/searchworkerslocationtype" var="url" />
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
		<input type="submit" value="submit" id="locationtype"/>
	</sf:form>
	
	<c:url value="/administrator/searchworkerslocationtype" var="url" />
	<form action="${url}" method="get">
		<input type="text" name="keyword"> <input type="submit"
			value="search">
	</form>
	
	<c:url value="/administrator/enabledisableworkers" var="enabledisableurl" />
	<form action="${enabledisableurl}" method="POST">
		<c:forEach items="${workers}" var='worker' varStatus="counter">
			${counter.count}: naam: ${worker.name} 
			<a
				href="${pageContext.request.contextPath}/jobs/detail?id=${worker.id}">Details</a>
			<a
				href="${pageContext.request.contextPath}/jobs/reviews?id=${worker.id}">Reviews</a>
			<input type="hidden" name="allWorkerIds" value="${worker.id}">
			<input type="checkbox"  name="workerIds" value="${worker.id}" <c:if test="${worker.user.enabled}">checked="checked"</c:if>>
			<br/>
		</c:forEach>
		<input type="submit" value="disable/enable workers">
	</form>
	

</body>
</html>