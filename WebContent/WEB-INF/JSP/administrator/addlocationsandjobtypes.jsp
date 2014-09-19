<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
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
<html>
<head>
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
	<c:url value="/administrator/addlocationsandjobtypes" var="url" />
	<div class="container">
		<h2>locations</h2>
		<ul class="list-group row">
			<c:forEach items="${locations}" var="location">

				<li class="list-group-item col-xs-4">${location.province}</li>
			</c:forEach>
		</ul>
	</div>
	<div class="container">
		<form action="${url}" method="post">
			<label>add a location</label> <input type="text"
				placeholder="location" name="province">
			<button type="submit" name="location">add location</button>
		</form>
	</div>
	<div class="container">
		<h2>jobtypes</h2>
		<ul class="list-group row">
			<c:forEach items="${jobTypes}" var="jobType">
				<li class="list-group-item col-xs-4">${jobType.jobDescription}</li>
			</c:forEach>
		</ul>
	</div>
	<div class="container">
		<form action="${url}" method="post" >
			<label>add a jobtype</label><input type="text" placeholder="jobtype" name="jobTypeDescription">
			<button type="submit" name="jobType">add jobtype</button>

		</form>
	</div>


</body>
</html>