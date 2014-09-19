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
			<c:forEach items="${messages}" var="message">
			<c:if test="${not message.workerBool}">
			<p class="bg-success"><strong>from: ${messageThread.customer.firstName} ${messageThread.customer.lastName} on ${message.date}</strong><br/>
			${message.text}<br/>
			<c:if test="${message.readBool}">message read</c:if>
			</p>
			</c:if>
			<c:if test="${message.workerBool}">
			<p class="bg-warning"><strong>from:${messageThread.worker.firstName} ${messageThread.worker.lastName} on ${message.date}</strong><br/>
			${message.text}
			<c:if test="${message.readBool}">message read</c:if></p>
			</c:if>
		</c:forEach>
		<c:url value="/messages/continuethreads" var="url" />
	<sf:form action="${url}" method="post" commandName="messageForm">
	<sf:label path="text">enter your message</sf:label>
		<sf:textarea cols="60" rows="20" path="text" />
		<sf:errors path="text"></sf:errors>		
		<input type="hidden" name="messageThreadId" value="${messageThread.id}">
		<button type="submit" name="answer">answer</button>
	</sf:form>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>