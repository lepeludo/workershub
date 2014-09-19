<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/bootstrap.min.css">
<script
	src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-brand-centered">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div class="navbar-brand navbar-brand-centered"><sec:authorize access="isAuthenticated()">
			 <sec:authentication property="principal.username" /></sec:authorize></div>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbar-brand-centered">
			<ul class="nav navbar-nav">
				<li><sec:authorize access="isAuthenticated()"><a href="<c:url value='/j_spring_security_logout' />"> Logout</a>
				</sec:authorize></li>
				<li><sec:authorize access="hasRole('customer')">
			<a href="${pageContext.request.contextPath}/customer/">CustomerZone</a>
		</sec:authorize>
		<sec:authorize access="hasRole('worker')">
			<a href="${pageContext.request.contextPath}/worker/">WorkerZone</a>
		</sec:authorize>
		<sec:authorize access="hasRole('administrator')">
			<a href="${pageContext.request.contextPath}/admin/">AdminZone</a>
		</sec:authorize><sec:authorize access="not isAuthenticated()">
			<a href="${pageContext.request.contextPath}/login">Log in</a>
		</sec:authorize></li>
				<li><a href="${pageContext.servletContext.contextPath}/">Home</a></li>
				<li><sec:authorize access="hasRole('worker')">
				<a href="${pageContext.request.contextPath}/messages/workerthreads">
				<c:if test="${sessionScope.unreadMessages>0}">${sessionScope.unreadMessages} messages</c:if>
				</a>
				</sec:authorize>
				<sec:authorize access="hasRole('customer')">
				<a href="${pageContext.request.contextPath}/messages/customerthreads">
				<c:if test="${sessionScope.unreadMessages>0}">${sessionScope.unreadMessages} messages</c:if>
				</a>
				</sec:authorize>
				</li>
			</ul>



