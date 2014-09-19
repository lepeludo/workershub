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
<body>
<jsp:include page="/WEB-INF/JSP/menu.jsp"></jsp:include>
	<h1>Fill each field</h1>
	<c:url value="/worker/docreate" var="succesurl" />
	<sf:form action="${succesurl}" method="post" commandName="workerForm">
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
		<sf:label path="extraInformation">extra information:</sf:label><br/>
		<sf:textarea rows="10" cols="80" path="extraInformation" />
		<sf:errors path="extraInformation" />
		<br />
		<p/>
		<sf:errors path="jobTypes" />
		
		
		<c:forEach items="${jobTypes}" var='jobtype'>
		<sf:label path="jobTypes" >${jobtype.jobDescription}</sf:label>
			<sf:checkbox path="jobTypes" value="${jobtype.id}"/><br/>
			
		</c:forEach><p/>
		
		
		<sf:errors path="locations" />
			<c:forEach items="${locations}" var='location'>
		<sf:label path="locations" >${location.country}, ${location.province}</sf:label>
			<sf:checkbox path="locations" value="${location.id}"/><br/>
		</c:forEach>
		
		<sf:label path="phoneNumbers" ></sf:label>
		<sf:errors path="phoneNumbers" />
		<c:forEach items="${workerForm.phoneNumbers}" varStatus="status">

			<sf:input path="phoneNumbers[${status.index}]" />
			<sf:errors path="phoneNumbers[${status.index}]" />

		</c:forEach>
		<input type="submit" value="submit" />
		<input type="submit" value="Add a phonenumber" name="addanumber" />
		<input type="submit" value="Remove phonenumbers" name="removenumbers" />
	</sf:form>

</body>
</html>