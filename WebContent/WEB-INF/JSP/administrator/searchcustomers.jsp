<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Search for customer</title>
</head>
<body>
	<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Search for a customer</h1>
	<c:url value="/administrator/searchcustomers" var="url" />
	<form action="${url}" method="get">
		<input type="text" name="keyword"> <input type="submit"
			value="search">
	</form>
	<c:url value="/administrator/enablecustomers" var="enableurl" />
	<form action="${enableurl}" method="post">
	<table class="table">
			<tr>
				<td>nr</td>
				<td>first name</td>
				<td>last name</td>
				<td>E-mail
				<td>Date account</td>
				<td>Last logged in</td>
				<td>x logged in</td>
				<td>enabled</td>
			</tr>
		<c:forEach items="${customers}" var="customer" varStatus="counter">
		<tr>
				<td>${counter.count}</td>
				<td>${customer.firstName} </td>
				<td>${customer.lastName} </td>
				<td>${customer.email}</td>
				<td>${customer.dateAccount}</td>
				<td>${customer.dateLastLogin}</td>
				<td>${numberOfLogins}</td>
				<td><input type="hidden" name="allCustomerIds" value="${customer.id}">
			<input type="checkbox" name="customerIds" value="${customer.id}"
				<c:if test="${customer.user.enabled}">checked="checked"</c:if>></td>
			</tr>
		</c:forEach>
		</table>
		<input type="submit" value="enable/disable users">
	</form>
</body>
</html>