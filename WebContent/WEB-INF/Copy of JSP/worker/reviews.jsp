<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<html>
<head>
<script type="text/javascript">

</script>
<title>Reviews</title>
</head>
<body>
	<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Reviews about ${worker.name}</h1>
	<c:url value="/worker/reviews" var="reviewurl" />
	<form action="${reviewurl}" method="POST">
		<c:forEach items="${reviews}" var="review" varStatus="counter">
	${counter.count} report as abusive<input type="checkbox" name="reports" value="${review.id}"
				<c:if test="${review.reported}">checked="checked"</c:if>><br />
			<fmt:formatDate type="both" dateStyle="long" timeStyle="long"
				value="${review.date}" />
			<p />review by: ${review.customer.firstName} ${review.customer.lastName}<p/>
	${review.text}<p />
		</c:forEach>
		<input type="submit" value="report abusive review"
		onclick="return window.confirm('Your report will be read by an administrator, but will still be visible. Are you sure you wish to proceed?')">
	</form>
</body>
</html>