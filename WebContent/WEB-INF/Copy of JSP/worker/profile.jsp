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
<body>
<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Welcome back ${worker.firstName}</h1>
	<h2>Your profile information looks like this:</h2>
	Your first name is: ${worker.firstName}
	<br /> Your last name is: ${worker.lastName}
	<br /> Your e-mail is: ${worker.email}
	<br /> Your provided extra information: ${worker.extraInformation}
	<br /> You work in the following locations:
	<p>
		<c:forEach items="${worker.locations}" var="location">
${location.province}<br />
		</c:forEach>
	</p>
	You will do the following jobs:
	<c:forEach items="${worker.jobTypes}" var="jobType">
${jobType.jobDescription}<br />
	</c:forEach>
	<a href="${pageContext.request.contextPath}/worker/update">Update profile</a>


</body>
</html>