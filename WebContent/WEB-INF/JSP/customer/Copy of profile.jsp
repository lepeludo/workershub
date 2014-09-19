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
	<div class="col-md-3 column margintop20">
    		<ul class="nav nav-pills nav-stacked">
  <li class="active"><a href="#"><span class="glyphicon glyphicon-chevron-right"></span> Home</a></li>
  <li><a href="${pageContext.request.contextPath}/customer/reviews"><span class="glyphicon glyphicon-chevron-right"></span> My Reviews</a></li>
  <li><a href="${pageContext.request.contextPath}/customer/update"><span class="glyphicon glyphicon-chevron-right"></span> Update profile</a></li>
</ul>
</div>
<div>
	<h2>Your profile information looks like this:</h2>
	<br/>Your first name is: ${customer.firstName}
	<br /> Your last name is: ${customer.lastName}
	<br /> Your e-mail is: ${customer.email}
	<a href="${pageContext.request.contextPath}/customer/reviews">My Reviews</a><br/>
	<a href="${pageContext.request.contextPath}/customer/update">Update profile</a>
</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>