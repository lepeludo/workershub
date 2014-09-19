<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	<h1>Detail Page</h1>
	<h2>${worker.name}</h2>

	<div class="counter">${counter.count}</div>
	<br />naam: ${worker.name}
	<br />${worker.email}<br />telefoonnummers:
	<c:forEach items="${worker.phoneNumbers}" var="phoneNumber">
				${phoneNumber}<br />
	</c:forEach>
	extra information provided by worker:
	<br />${worker.extraInformation}<br />

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>