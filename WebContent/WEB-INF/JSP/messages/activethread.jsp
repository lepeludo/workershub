<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/simple-sidebar.css">

<!-- Custom CSS: start under menubar -->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/styles/dist/css/chat.css">
<script
	src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

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
	<c:url value="/messages/continuethreads" var="url" />
	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<div class="panel panel-primary">
					<div class="panel-body">
						<ul class="chat">
							<c:forEach items="${messages}" var="message">
								<c:if test="${not message.workerBool}">
									<li class="left clearfix"><span class="chat-img pull-left">
											<sec:authorize access="hasRole('worker')">
												<img src="http://placehold.it/50/55C1E7/fff&text=U"
													alt="User Avatar" class="img-circle" />
											</sec:authorize> <sec:authorize access="hasRole('customer')">
												<img src="http://placehold.it/50/FA6F57/fff&text=ME"
													alt="User Avatar" class="img-circle" />
											</sec:authorize>
									</span>
										<div class="chat-body clearfix">
											<div class="header">
												<strong class="primary-font">${messageThread.customer.firstName}
													${messageThread.customer.lastName}</strong> <small
													class="pull-right text-muted"> <span
													class="glyphicon glyphicon-time"></span>${message.date}</small> <small
													class="pull-right text-muted"><c:if
														test="${message.readBool}">message read </c:if></small>
											</div>
											<p class="chatbox-left">
												<br />${message.text}</p>
										</div></li>
								</c:if>
								<c:if test="${message.workerBool}">
									<li class="right clearfix"><span
										class="chat-img pull-right"> <sec:authorize
												access="hasRole('customer')">
												<img src="http://placehold.it/50/55C1E7/fff&text=U"
													alt="User Avatar" class="img-circle" />
											</sec:authorize> <sec:authorize access="hasRole('worker')">
												<img src="http://placehold.it/50/FA6F57/fff&text=ME"
													alt="User Avatar" class="img-circle" />
											</sec:authorize>
									</span>
										<div class="chat-body clearfix">
											<div class="header">
												<small class=" text-muted"><span
													class="glyphicon glyphicon-time"></span>${message.date}</small> <small
													class=" text-muted"><c:if
														test="${message.readBool}">message read </c:if></small> <strong
													class="pull-right primary-font">${messageThread.worker.firstName}
													${messageThread.worker.lastName}</strong>
											</div>
											<p class="chatbox-right">
												<br />${message.text}</p>
										</div></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<div class="panel-footer">
						<div class="input-group">
							<c:url value="/messages/continuethreads" var="url" />
							<sf:form action="${url}" method="post" commandName="messageForm">
								<sf:textarea id="btn-input" cols="70" rows="8" path="text"
									class="form-control input-sm"
									placeholder="Type your message here..." />
								<sf:errors path="text"></sf:errors>
								<input type="hidden" name="messageThreadId"
									value="${messageThread.id}">
								<span class="input-group-btn">
									<button type="submit" name="answer"
										class="btn btn-warning btn-sm" id="btn-chat">Send</button>
								</span>
							</sf:form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="${pageContext.servletContext.contextPath}/styles/dist/js/bootstrap.min.js"></script>
</body>
</html>