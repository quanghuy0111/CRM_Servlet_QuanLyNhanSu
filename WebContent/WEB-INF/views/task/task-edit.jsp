<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cybersoft.java09.entity.*"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

<% String contextPath = request.getContextPath() ;%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link
	href="<%=contextPath %>/static/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Menu CSS -->
<link
	href="<%=contextPath %>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<!-- animation CSS -->
<link href="<%=contextPath %>/static/css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=contextPath %>/static/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="<%=contextPath %>/static/css/colors/blue-dark.css"
	id="theme" rel="stylesheet">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>
	<jsp:include page="../layout/navbar.jsp"></jsp:include>
	<!-- Page Content -->
	<% User user = (User)session.getAttribute("user");
	List<User> users = (List<User>)request.getAttribute("users");
	
			
		
		List<Job> jobs = (List<Job>)request.getAttribute("jobs");
		 	Task task = (Task)request.getAttribute("task");
		 	%>
	<h1><%= users.get(0) %></h1>
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Thêm mới công việc</h4>
				</div>
			</div>
			<!-- /.row -->
			<!-- .row -->
			<div class="row">
				<div class="col-md-2 col-12"></div>
				<div class="col-md-8 col-xs-12">
					<div class="white-box">
						<form class="form-horizontal form-material" method="POST"
							action="<%=contextPath%>/task-edit">

							<input type="hidden" class="form-control form-control-line"
								name="id" value="${task.id }">

							<div class="form-group">
								<label class="col-md-12">Dự án</label>
								<div class="col-md-12">
									<select class="form-control form-control-line" name="job">
										<c:forEach items="<%= jobs %>" var="item">

											<option value="${item.id }">${item.name}</option>

										</c:forEach>


									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12">Tên công việc</label>
								<div class="col-md-12">
									<input type="text" placeholder="Tên công việc"
										class="form-control form-control-line" name="taskName">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12">Người thực hiện</label>
								<div class="col-md-12">
									<select class="form-control form-control-line" name="user">
										<c:forEach items="<%= users %>" var="item">

											<option value="${item.id }">${item.fullName}</option>

										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12">Ngày bắt đầu</label>
								<div class="col-md-12">
									<input type="text" placeholder="dd/MM/yyyy"
										class="form-control form-control-line" name="startDate">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12">Ngày kết thúc</label>
								<div class="col-md-12">
									<input type="text" placeholder="dd/MM/yyyy"
										class="form-control form-control-line" name="endDate">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<button type="submit" class="btn btn-success">Lưu lại</button>
									<a href="<%=contextPath %>/task" class="btn btn-primary">Quay
										lại</a>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-2 col-12"></div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
		<footer class="footer text-center"> 2018 &copy; myclass.com </footer>
	</div>
	<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script
		src="<%=contextPath %>/static/plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=contextPath %>/static/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="<%=contextPath %>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script src="<%=contextPath %>/static/js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="<%=contextPath %>/static/js/waves.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<%=contextPath %>/static/js/custom.min.js"></script>
</body>

</html>