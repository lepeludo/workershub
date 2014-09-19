<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="menu">
	<sec:authorize access="isAuthenticated()">
Username: <sec:authentication property="principal.username" />
		<p>
			<a href="<c:url value='/j_spring_security_logout' />"> Logout</a>
		</p>
		</sec:authorize>
		<sec:authorize access="hasRole('customer')">
			<a href="${pageContext.request.contextPath}/customer/index">CustomerZone</a>
		</sec:authorize>
		<sec:authorize access="hasRole('worker')">
			<a href="${pageContext.request.contextPath}/worker/index">WorkerZone</a>
		</sec:authorize>
		<sec:authorize access="hasRole('admin')">
			<a href="${pageContext.request.contextPath}/admin/index">AdminZone</a>
		</sec:authorize>
	
</div>