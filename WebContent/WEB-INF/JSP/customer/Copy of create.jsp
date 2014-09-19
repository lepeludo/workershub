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
	
	<c:url value="/customer/docreate" var="succesurl" />
	<sf:form action="${succesurl}" method="post" commandName="customerForm">
		<sf:label path="firstName">first name:</sf:label>
		<sf:input type="text" path="firstName" />
		<sf:errors path="firstName" />
		<br />
		<sf:label path="lastName">last name:</sf:label>
		<sf:input type="text" path="lastName" />
		<sf:errors path="lastName" />
		<br />
		<sf:label path="email">E-Mail:</sf:label>
		<sf:input type="text" path="email" />
		<sf:errors path="email" />
		<br />
		<sf:label path="username">username:</sf:label>
		<sf:input type="text" path="username" />
		<sf:errors path="username" />
		<br />
		<sf:label path="password">password:</sf:label>
		<sf:input type="text" path="password" />
		<sf:errors path="password" />
		<br />
		<input type="submit" value="submit" />
	</sf:form>
	
	<!-- /.container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../../dist/js/bootstrap.min.js"></script>
</body>
</html>
