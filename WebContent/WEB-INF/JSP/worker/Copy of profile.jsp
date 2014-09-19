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
	<h1>Welcome back ${worker.firstName}</h1>
	<h2>Your profile information looks like this:</h2>
	Your first name is: ${worker.firstName}

	<table class="table">
		<tr>
			<td>first name</td>
			<td>${worker.firstName}</td>
		</tr>
		<tr>
			<td>last name</td>
			<td>${worker.lastName}</td>
		</tr>
		<tr>
			<td>e-mail</td>
			<td>${worker.email}</td>
		</tr>
		<tr>
			<td>rating</td>
			<td>${worker.currentReviewStars}</td>
		</tr>
		<tr>
			<td>info</td>
			<td>${worker.extraInformation}</td>
		</tr>
		<tr>
			<td>locations</td><td>
			<c:forEach items="${worker.locations}" var="location">
				${location.province}<br/>
			</c:forEach></td>

		</tr>
		<tr>
			<td>jobtypes</td><td>
			<c:forEach items="${worker.jobTypes}" var="jobType">
				${jobType.jobDescription}<br/>
			</c:forEach></td>
		</tr>
		<tr>
			<td>phonenumbers</td><td>
			<c:forEach items="${worker.phoneNumbers}" var="phoneNumber">
				${phoneNumber}<br/>
			</c:forEach></td>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath}/worker/update">Update
		profile</a>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>