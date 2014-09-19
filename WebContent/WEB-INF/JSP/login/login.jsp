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
<div class="container">
	<div class="row">
		<div class="span4 offset4 well">
			<label>Please Sign In</label>
			<div class="alert alert-error">
				<a class="close" data-dismiss="alert" href="#">×</a>
				<c:if test="${param.error != null}">

					<p class="error">Login failed. Check if your username and
						password are correct.</p>

				</c:if>
			</div>
			<form name='f'
				action='${pageContext.request.contextPath}/j_spring_security_check'
				method='POST' accept-charset="UTF-8">
				<input type="text" id="username" class="span4" name="j_username"
					placeholder="Username"> <input type="password"
					id="password" class="span4" name="j_password" placeholder="Password">
				<label class="checkbox"> <input type='checkbox' name='_spring_security_remember_me'
					checked="checked" > Remember Me
				</label><br/>
				<button type="submit" name="submit" value="Login" class="btn btn-info btn-block">Sign
					in</button>
			</form>
			<c:url value="/forgotpassword" var="url"/>
			<br/>
			<br/>
			<form action="${url}">
				<button type="submit" name="forgotPassword" value="forgotPassword" class="btn btn-danger">Forgot password</button>	
			</form>
			
		</div>
	</div>
</div>
</body>
</html>
