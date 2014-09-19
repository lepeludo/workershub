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
	
	
	<c:url value="/administrator/searchworkerslocationtype" var="url" />
	<sf:form action="${url}" method="post" commandName="workersForm">
		<sf:errors path="jobTypes" />
		<c:forEach items="${jobTypes}" var='jobtype'>
			<sf:label path="jobTypes">${jobtype.jobDescription}</sf:label>
			<sf:checkbox path="jobTypes" value="${jobtype.id}" />
		</c:forEach>
		<br />
		<sf:errors path="locations" />
		<c:forEach items="${locations}" var='location'>
			<sf:label path="locations">${location.country}, ${location.province}</sf:label>
			<sf:checkbox path="locations" value="${location.id}" />
			<br />
		</c:forEach>
		<p />
		<input type="submit" value="submit" id="locationtype" />
	</sf:form>

	<c:url value="/administrator/searchworkerslocationtype" var="url" />
	<form action="${url}" method="get">
		<input type="text" name="keyword"> <input type="submit"
			value="search">
	</form>

	<c:url value="/administrator/enabledisableworkers"
		var="enabledisableurl" />
	<c:if test="${not empty workers}">
		<h2>These workers matched your criteria:</h2>
		<form action="${enabledisableurl}" method="POST">
			<table class="table">
				<tr>
					<td>nr</td>
					<td>first name</td>
					<td>last name</td>
					<td>E-mail</td>
					<td>Date account</td>
					<td>Last logged in</td>
					<td>x logged in</td>					
					<td>n ratings</td>
					<td>avg of ratings</td>
					<td>enabled</td>
				</tr>
				<c:forEach items="${workers}" var='worker' varStatus="counter">
					<tr>
						<td>${counter.count}</td>
						<td>${worker.firstName}</td>
						<td>${worker.lastName}</td>
						<td>${worker.email}</td>
						<td>${worker.dateAccount}</td>
						<td>${worker.dateLastLogin}</td>
						<td>${worker.numberOfLogins}</td>
						<td>${worker.reviewStars.size()}</td>
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
						<td><input type="checkbox" name="workerIds"
							value="${worker.id}"
							<c:if test="${worker.user.enabled}">checked="checked"</c:if>></td>
						<td><a
							href="${pageContext.request.contextPath}/jobs/detail?id=${worker.id}">Details</a></td>
						<td><a
							href="${pageContext.request.contextPath}/jobs/reviews?id=${worker.id}">Reviews</a></td>
						<td><a
							href="${pageContext.request.contextPath}/administrator/email/?email=${worker.email}">Send E-mail</a></td>
						
					</tr>
					<input type="hidden" name="allWorkerIds" value="${worker.id}">
				</c:forEach>
			</table>
			<input type="submit" value="disable/enable workers">
		</form>
	</c:if>

</body>
</html>