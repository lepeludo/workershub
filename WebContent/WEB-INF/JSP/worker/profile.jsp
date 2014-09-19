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
	<div class="col-md-3 column margintop20">
		<ul class="nav nav-pills nav-stacked">
			<li class="active"><a href="#"><span
					class="glyphicon glyphicon-chevron-right"></span> Home</a></li>
			<li><a
				href="${pageContext.request.contextPath}/worker/reviews"><span
					class="glyphicon glyphicon-chevron-right"></span> My Reviews</a></li>
			<li><a href="${pageContext.request.contextPath}/worker/update"><span
					class="glyphicon glyphicon-chevron-right"></span> Update profile</a></li>
		</ul>
	</div>
	<div class="container">
		<div class="row">

			<div
				class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">


				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">${worker.firstName}
							${worker.lastName}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-3 col-lg-3 " align="center"></div>

							<div class=" col-md-9 col-lg-9 ">
								<table class="table table-user-information">
									<tbody>
										<tr>
											<td>first name</td>
											<td>${worker.firstName}</td>
										</tr>
										<tr>
											<td>last name</td>
											<td>${worker.lastName}</td>
										</tr>
											<tr>
											<td>username</td>
											<td>${worker.user.username}</td>
										</tr>
										<tr>
											<td>e-mail</td>
											<td>${worker.email}</td>
										</tr>
										<tr>
											<td>rating</td>
											<td>${worker.currentReviewStars}</td>
										</tr>
										<tr>
											<td>info</td>
											<td>${worker.extraInformation}</td>
										</tr>
										<tr>
											<td>locations</td>
											<td><c:forEach items="${worker.locations}"
													var="location">
				${location.province}<br />
												</c:forEach></td>

										</tr>
										<tr>
											<td>jobtypes</td>
											<td><c:forEach items="${worker.jobTypes}" var="jobType">
				${jobType.jobDescription}<br />
												</c:forEach></td>
										</tr>
										<tr>
											<td>phonenumbers</td>
											<td><c:forEach items="${worker.phoneNumbers}"
													var="phoneNumber">
				${phoneNumber}<br />
												</c:forEach></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
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