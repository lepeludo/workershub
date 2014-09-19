<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
<h1> Index of worker pages</h1>
<a href="${pageContext.request.contextPath}/administrator/create">Add an administrator</a>
<a href="${pageContext.request.contextPath}/administrator/searchcustomers">Customer search</a>
<a href="${pageContext.request.contextPath}/administrator/searchworkerslocationtype">Worker search on location and jobtype</a>
<a href="${pageContext.request.contextPath}/administrator/reviews">Read reported reviews</a>
</body>
</html>