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
	<c:url value="/messages/startthread" var="succesurl" />

	<sf:form action="${succesurl}" method="post"
		commandName="messageThreadForm">
		<sf:label path="subject">Subject</sf:label>
		<sf:input path="subject" />
		<sf:label path="text">Message</sf:label>
		<br />
		<sf:textarea cols="60" rows="20" path="text" />
		<sf:errors path="text" />
		<br />
		<sf:hidden path="workerId" value="${workerId}" />
		<input type="submit" value="submit" />
	</sf:form>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>