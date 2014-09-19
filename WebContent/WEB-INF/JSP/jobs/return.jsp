<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<!-- Custom CSS: start under menubar -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/indextemplate.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script
	src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<title>Index page</title>
</head>
<jsp:include page="/WEB-INF/JSP/menu.jsp" />
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
	<!-- /.container -->

	<table class="table">
		<tr>
			<td>nr.</td>
			<td>first name</td>
			<td>last name</td>
			<td>rating</td>
		</tr>
		<c:forEach items="${workers}" var='worker' varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td>${worker.firstName}</td>
				<td>${worker.lastName}</td>
				<td class="rating">
				<c:if test="${worker.currentReviewStars>=0}">
				<c:forEach begin='1' end="${worker.currentReviewStars}" varStatus="counter">
					<span>&#9733</span>
				</c:forEach>
				<c:forEach begin='${worker.currentReviewStars}' end="4" varStatus="counter">
					<span>&#9734</span>
				</c:forEach>
				</c:if>
				</td>
				<td>
				<a
					href="${pageContext.request.contextPath}/jobs/detail?id=${worker.id}">Details</a></td><td>
				<a
					href="${pageContext.request.contextPath}/jobs/reviews?id=${worker.id}">Reviews</a></td><td>
					<a
					href="${pageContext.request.contextPath}/messages/checkthreads?workerId=${worker.id}">Send a message</a></td>
			<tr />
		</c:forEach>

	</table>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>