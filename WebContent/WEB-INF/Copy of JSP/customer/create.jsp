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
	<c:url value="/customer/docreate" var="succesurl" />
	<sf:form action="${succesurl}" method="post" commandName="customerForm">
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
		<sf:label path="username">username:</sf:label>
		<sf:input type="text" path="username" />
		<sf:errors path="username" />
		<br />
		<sf:label path="password">password:</sf:label>
		<sf:input type="text" path="password" />
		<sf:errors path="password" />
		<br />
		<input type="submit" value="submit" />
	</sf:form>

</body>
</html>