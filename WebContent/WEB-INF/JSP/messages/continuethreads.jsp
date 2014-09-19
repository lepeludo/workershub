<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/simple-sidebar.css">

<!-- Custom CSS: start under menubar -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/bootstrap.min.css">
<script
	src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/styles/dist/js/rating.js"></script>

<title>Make Account</title>
</head>

<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
<body>
	<ul class="nav navbar-nav navbar-right">
		<li><a href="#">Link</a></li>
		<li><a href="#">Link</a></li>
		<li><a href="#">Link</a></li>
	</ul>
	</div>
	<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
	</nav>
	<c:url value="/messages/continuethreads" var="url" />
	<form action="${url}" method="post">
		<c:forEach items="${messageThreads}" var="messageThread">
		<div class="col-md-6">
			<div class="well">
					<h2>${messageThread.subject}</h2>
					<p class="text-left text-muted"><small>by: ${messageThread.customer.firstName} ${messageThread.customer.lastName} </small><p>				
				<button type="submit" name="messageThreadId" class="btn btn-primary btn-lg"
					value="${messageThread.id}">view</button>			
				<sec:authorize access="hasRole('customer')">
					<c:set var="contains" value='0' />
					<c:forEach items="${messageThread.messages}" var="message">
						<c:if test="${not message.readBool && message.workerBool}">
								<c:set var="contains" value="${contains+1}" />
						</c:if>
					</c:forEach>
						<p class="text-right text-warning">${contains} unread messages</p>
			</sec:authorize>
				<sec:authorize access="hasRole('worker')">
					<c:set var="contains" value='0' />
					<c:forEach items="${messageThread.messages}" var="message">
						<c:if test="${not message.readBool && not message.workerBool}">
								<c:set var="contains" value="${contains+1}" />
							</c:if>
					</c:forEach>
						<p class="text-right text-warning" >${contains} unread messages</p>
				</sec:authorize>
			</div>
			</div>
		</c:forEach>
	</form>
	<sec:authorize access="hasRole('customer')">
		<c:url value="/messages/startthread" var="startthreadurl" />
		<form action="${startthreadurl}" method="get">
			<button type="submit" name="workerId" value="${workerId}">New
				subject</button>
		</form>
	</sec:authorize>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>