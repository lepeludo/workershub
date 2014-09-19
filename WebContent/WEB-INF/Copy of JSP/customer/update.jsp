<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update workerprofile</title>
</head>
<body>
<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Update page for ${customer.firstName}</h1>
	<h2>change your profile</h2>
	<c:url value="doupdate" var="updateurl" />
	<sf:form action="${updateurl}" method="post" commandName="updateCustomerForm">
		<sf:label path="firstName">first name:</sf:label>
		<sf:input type="text" path="firstName" />
		<sf:errors path="firstName" />
		<br />
		<sf:label path="lastName">last name:</sf:label>
		<sf:input type="text" path="lastName" />
		<sf:errors path="lastName" />
		<br />
		<sf:label path="email">E-Mail:</sf:label>
		<sf:input type="text" path="email" />
		<sf:errors path="email" />
		<br />
		<input type="submit" value="submit" />
	</sf:form>


</body>
</html>