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
							<a href="<c:url value='/j_spring_security_logout' />"> Logout</a>
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
	<h1>Fill each field</h1>
	<c:url value="/worker/docreate" var="succesurl" />
	<sf:form action="${succesurl}" method="post" commandName="workerForm">
		<fieldset>
			<div id="legend">Register</div>
			<div class="control-group">
				<!-- Username -->
				<div class="control-group">
					<sf:label path="firstName" class="control-label">First Name</sf:label>
					<div class="controls">
						<sf:input path="firstName" type="text"
							placeholder="Your first name" class="input-xlarge" />
						<sf:errors class="red" path="firstName" />
						<p class="help-block">First name can contain any letters or
							numbers</p>
					</div>
				</div>
			</div>
			<div class="control-group">
				<sf:label path="lastName" class="control-label">Last Name</sf:label>
				<div class="controls">
					<sf:input path="lastName" type="text" placeholder="Your last name"
						class="input-xlarge" />
					<sf:errors class="red" path="lastName" />
					<p class="help-block">Last name can contain any letters or
						numbers</p>
				</div>
			</div>
			<div class="control-group">
				<!-- E-mail -->
				<sf:label path="email" class="control-label" for="email">E-mail</sf:label>
				<div class="controls">
					<sf:input type="text" path="email" id="email" name="email"
						placeholder="Your e-mail" class="input-xlarge" />
					<sf:errors class="red" path="email" />
					<p class="help-block">Please provide your E-mail</p>
				</div>
			</div>

			<div class="control-group">
				<!-- Password-->
				<sf:label path="password" class="control-label" for="password">Password</sf:label>
				<div class="controls">
					<sf:input path="password" id="password" name="password"
						placeholder="Your password" class="input-xlarge" />
					<p class="help-block">Password should be at least 4 characters</p>
					<sf:errors path="password" />
				</div>
			</div>


			<!-- 			<div class="control-group">
					Password
					<label class="control-label" for="password_confirm">Password
						(Confirm)</label>
					<div class="controls">
						<input type="password" id="password_confirm"
							name="password_confirm" placeholder="Retype your password"
							class="input-xlarge">
						<p class="help-block">Please confirm password</p>
					</div>
				</div> -->
			<div class="control-group">
				<!-- extra information -->
				<sf:label path="extraInformation" class="control-label"
					for="extraInformation">Info</sf:label>
				<div class="controls">
					<sf:textarea rows="10" cols="80" path="extraInformation"
						id="extraInformation" name="extraInformation"
						placeholder="Write additional information" class="input-xlarge" />
					<sf:errors class="red" path="extraInformation" />
					<p class="help-block">Provide additional information about your
						activities</p>
				</div>
			</div>

			<div class="control-group">
				<!-- phonenumbers -->
				<sf:label path="phoneNumbers" class="control-label"
					for="phoneNumbers"></sf:label>
				<div class="controls">
					<c:forEach items="${workerForm.phoneNumbers}" varStatus="status">

						<sf:input path="phoneNumbers[${status.index}]" />
						<sf:errors path="phoneNumbers[${status.index}]" />

					</c:forEach>
					<sf:errors path="phoneNumbers" />
					<p class="help-block">Enter your phonenumber</p>
				</div>
				<input type="submit" value="Add a phonenumber" name="addanumber" />
				<input type="submit" value="Remove phonenumbers"
					name="removenumbers" />
			</div>

			<div class="control-group">
				<!-- locations -->
				<sf:label path="locations" class="control-label" for="locations">Regions you service</sf:label>
				<div class="controls">

					<c:forEach items="${locations}" var='location'>
						<div class="checkbox">
							<label> <sf:checkbox path="locations"
									value="${location.id}" /> ${location.country},
								${location.province}
							</label>
						</div>
					</c:forEach>
					<sf:errors class="red" path="locations" />
					<p class="help-block">Mark the locations you service</p>
				</div>
			</div>

			<div class="control-group">
				<!-- locations -->
				<sf:label path="jobTypes">Types of jobs you perform</sf:label>
				<div class="controls">
					<c:forEach items="${jobTypes}" var='jobtype'>
						<div class="checkbox">
							<label><sf:checkbox path="jobTypes" value="${jobtype.id}" />${jobtype.jobDescription}
							</label>
						</div>
					</c:forEach>
					<sf:errors class="red" path="jobTypes" />
					<p class="help-block">Mark the types of jobs you'll peform</p>
				</div>
			</div>
			<div class="control-group">
				<!-- Button 
					<input class="btn btn-success" type="submit" value="submit">-->
				<div class="controls">
					<input type="submit" value="submit" />
				</div>
			</div>
		</fieldset>
	</sf:form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>