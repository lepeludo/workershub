<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<!-- Custom CSS and JS: navbar -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/simple-sidebar.css">
<script
	src="${pageContext.servletContext.contextPath}/styles/dist/js/jquery-1.11.0.js"></script>
<!-- Custom CSS: start under menubar -->
	<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/vertmenu.css">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/bootstrap.min.css">
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
	<!-- sidebar -->
	<div class="col-md-3 column margintop20">
		<ul class="nav nav-pills nav-stacked">
			<li class="active"><a href="#"><span
					class="glyphicon glyphicon-chevron-right"></span> Home</a></li>
			<sec:authorize access="not isAuthenticated()">
				<li><a
					href="${pageContext.request.contextPath}/customer/create"><span
						class="glyphicon glyphicon-chevron-right"></span> I'm a new
						customer</a></li>
				<li><a href="${pageContext.request.contextPath}/worker/create"><span
						class="glyphicon glyphicon-chevron-right"></span> I'm a new worker</a></li>
			</sec:authorize>
			<li><sec:authorize access="hasRole('customer')">
					<a href="${pageContext.request.contextPath}/customer/">CustomerZone</a>
				</sec:authorize> <sec:authorize access="hasRole('worker')">
					<a href="${pageContext.request.contextPath}/worker/">WorkerZone</a>
				</sec:authorize> <sec:authorize access="hasRole('admin')">
					<a href="${pageContext.request.contextPath}/admin/">AdminZone</a>
				</sec:authorize></li>
		</ul>
	</div>
	<!-- end sidebar -->

	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>