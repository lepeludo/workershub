<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Welcome back ${customer.firstName}</h1>
	<h2>Your profile information looks like this:</h2>
	<br/>Your first name is: ${customer.firstName}
	<br /> Your last name is: ${customer.lastName}
	<br /> Your e-mail is: ${customer.email}
	<a href="${pageContext.request.contextPath}/customer/reviews">My Reviews</a><br/>
	<a href="${pageContext.request.contextPath}/customer/update">Update profile</a>


</body>
</html>