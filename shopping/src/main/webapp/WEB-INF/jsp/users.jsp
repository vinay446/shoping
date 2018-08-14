<%@ page language="java" contentType="text/html; utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);

	String username = (String) session.getAttribute("username");

	if (username == null)
		response.sendRedirect("/adminlogout?msg=Session Time out Please login again&req=dashboard");
	pageContext.setAttribute("username", username);
	pageContext.setAttribute("page","Profile");
%>
<head>
<meta charset="utf-8" />
<link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/resources/images/glovision.png"/>" />
<!-- Favicon-->
<link rel="icon" href="<c:url value="/resources/images/glovision.png"/>" type="image/x-icon" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Users Profile</title>
<meta
	content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0'
	name='viewport' />
<meta name="viewport" content="width=device-width" />
<!-- Bootstrap core CSS     -->
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet" />
<!--  Material Dashboard CSS    -->
<link href="<c:url value="/resources/css/material-dashboard.css?v=1.2.0"/>" rel="stylesheet" />
<!--     Fonts and icons     -->
<link
	href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons'
	rel='stylesheet' type='text/css'>
</head>

<body>
	<div class="wrapper">
		<%@include file="sidebar.jsp" %>
		<div class="main-panel">
			<%@include file="header.jsp" %>
			<div class="content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-8">
							<div class="card">
								<div class="card-header" data-background-color="purple">
									<h4 class="title">Edit Profile</h4>
									<p class="category">Complete your profile</p>
								</div>
								<div class="card-content">
									<form>
										<div class="row">
											<div class="col-md-5">
												<div class="form-group label-floating">
													<label class="control-label">Company (disabled)</label> <input
														type="text" class="form-control" disabled>
												</div>
											</div>
											<div class="col-md-3">
												<div class="form-group label-floating">
													<label class="control-label">Username</label> <input
														type="text" class="form-control">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">Email address</label> <input
														type="email" class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">Fist Name</label> <input
														type="text" class="form-control">
												</div>
											</div>
											<div class="col-md-6">
												<div class="form-group label-floating">
													<label class="control-label">Last Name</label> <input
														type="text" class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group label-floating">
													<label class="control-label">Adress</label> <input
														type="text" class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">City</label> <input
														type="text" class="form-control">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">Country</label> <input
														type="text" class="form-control">
												</div>
											</div>
											<div class="col-md-4">
												<div class="form-group label-floating">
													<label class="control-label">Postal Code</label> <input
														type="text" class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<div class="form-group">
													<label>About Me</label>
													<div class="form-group label-floating">
														<label class="control-label"> Lamborghini Mercy,
															Your chick she so thirsty, I'm in that two seat Lambo.</label>
														<textarea class="form-control" rows="5"></textarea>
													</div>
												</div>
											</div>
										</div>
										<button type="submit" class="btn btn-primary pull-right">Update
											Profile</button>
										<div class="clearfix"></div>
									</form>
								</div>
							</div>
						</div>
						<div class="col-md-4">
							<div class="card card-profile">
								<div class="card-avatar">
									<a href="#pablo"> <img class="img"
										src="http://localhost/shoppingcart/123.jpg" />
									</a>
								</div>
								<div class="content">
									<h6 class="category text-gray">CEO / Co-Founder</h6>
									<h4 class="card-title">Vinay </h4>
									<p class="card-content">Don't be scared of the truth
										because we need to restart the human foundation in truth And I
										love you like Kanye loves Kanye I love Rick Owensâ€™ bed
										design but the back is...</p>
									<a href="#pablo" class="btn btn-primary btn-round">Follow</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.jsp" %>
		</div>
	</div>
</body>
<!--   Core JS Files   -->
<script src="<c:url value="/resources/js/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
<script src="<c:url value="/resources/js/material.min.js"/>" type="text/javascript"></script>
<!--  Charts Plugin -->
<script src="<c:url value="/resources/js/chartist.min.js"/>"></script>
<!--  Dynamic Elements plugin -->
<script src="<c:url value="/resources/js/arrive.min.js"/>"></script>
<!--  PerfectScrollbar Library -->
<script src="<c:url value="/resources/js/perfect-scrollbar.jquery.min.js"/>"></script>
<!--  Notifications Plugin    -->
<script src="<c:url value="/resources/js/bootstrap-notify.js"/>"></script>
<!-- Material Dashboard javascript methods -->
<script src="<c:url value="/resources/js/material-dashboard.js?v=1.2.0"/>"></script>

</html>