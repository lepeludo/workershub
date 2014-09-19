<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<!-- Custom CSS: start under menubar -->
<!-- Latest compiled and minified CSS -->
<!-- Custom CSS: start under menubar -->
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
<title>Insert title here</title>
</head>

<body>
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
				<div class="navbar-brand navbar-brand-centered">
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.username" />
					</sec:authorize>
				</div>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-brand-centered">
				<ul class="nav navbar-nav">
					<li><sec:authorize access="isAuthenticated()">
							<a href="<c:url value='/j_spring_security_logout' />">Logout</a>
						</sec:authorize></li>
					<li><sec:authorize access="hasRole('customer')">
							<a href="${pageContext.request.contextPath}/customer/">CustomerZone</a>
						</sec:authorize> <sec:authorize access="hasRole('worker')">
							<a href="${pageContext.request.contextPath}/worker/">WorkerZone</a>
						</sec:authorize> <sec:authorize access="hasRole('admin')">
							<a href="${pageContext.request.contextPath}/admin/">AdminZone</a>
						</sec:authorize> <sec:authorize access="not isAuthenticated()">
							<a href="${pageContext.request.contextPath}/login">Log in</a>
						</sec:authorize></li>
					<li><a href="${pageContext.servletContext.contextPath}/">Home</a></li>
				</ul>
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
	<c:url value="/customer/docreate" var="succesurl" />
	<sf:form action="${succesurl}" method="post" commandName="customerForm">
		<div class="form-group">
			<sf:label path="firstName">first name:</sf:label>
			<div class="controls">
				<sf:input type="text" class="input-xlarge" path="firstName" />
				<sf:errors path="firstName" />
			</div>
		</div>
		<div class="form-group">
			<sf:label path="lastName">last name:</sf:label>
			<div class="controls">
				<sf:input type="text" class="input-xlarge" path="lastName" />
				<sf:errors path="lastName" />
			</div>
		</div>
		<div class="form-group">
			<sf:label path="email">E-Mail<small>(not visible to others)</small></sf:label>
			<div class="controls">
				<sf:input type="text" class="input-xlarge" path="email" />
				<sf:errors path="email" />
			</div>
		</div>
		<div class="form-group">
			<sf:label path="username">username</sf:label>
			<div class="controls">
				<sf:input type="text" class="input-xlarge" path="username" />
				<sf:errors path="username" />
			</div>
		</div>
		<div class="form-group">
			<sf:label path="password">password:</sf:label>
			<div class="controls">
				<sf:input type="text" class="input-xlarge" path="password" />
				<sf:errors path="password" />
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Create</button>
	</sf:form>
	<!-- /.container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
</body>
</html>
