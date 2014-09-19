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
		<c:forEach items="${customers}" var="customer" varStatus="counter">
			<p /> ${counter.count}
	<p />${customer.firstName} ${customer.lastName} ${customer.email} ${customer.user.username}
		<input type="hidden" name="allCustomerIds" value="${customer.id}">
			<input type="checkbox" name="customerIds" value="${customer.id}"
				<c:if test="${customer.user.enabled}">checked="checked"</c:if>>
			<br />			
		</c:forEach>
		<input type="submit" value="enable/disable users">
	</form>
</body>
</html>