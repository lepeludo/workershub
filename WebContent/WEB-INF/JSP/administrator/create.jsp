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
	<h1>Fill each field</h1>
	<c:url value="/administrator/docreate" var="succesurl" />
	<sf:form action="${succesurl}" method="post" commandName="administratorForm">
		<sf:label path="name">name:</sf:label>
		<sf:input type="text" path="name" />
		<sf:errors path="name" />
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