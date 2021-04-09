<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>
<%@ page import="cybersoft.java09.entity.Job"%>

<!DOCTYPE html>
<html lang="en">
<%
	String contextPath = request.getContextPath();
%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=contextPath%>/static/plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link
	href="<%=contextPath%>/static/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Menu CSS -->
<link
	href="<%=contextPath%>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<!-- animation CSS -->
<link href="<%=contextPath%>/static/css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=contextPath%>/static/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="<%=contextPath%>/static/css/colors/blue-dark.css"
	id="theme" rel="stylesheet">
<link rel="stylesheet" href="<%=contextPath%>/static/css/custom.css">
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
	<% List<Job> jobs = (List<Job>)request.getAttribute("jobs"); %>
	
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Danh sách dự án</h4>
				</div>
				<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
					<a href="<%=contextPath%>/groupwork-add"
						class="btn btn-sm btn-success">Thêm mới</a>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /row -->
			<div class="row">
				<div class="col-sm-12">
					<div class="white-box">
						<div class="table-responsive">
							<table class="table" id="example">
								<thead>
									<tr>
										<th>STT</th>
										<th>Tên Dự Án</th>
										<th>Ngày Bắt Đầu</th>
										<th>Ngày Kết Thúc</th>
										<th>Hành Động</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="<%=jobs %>" var="item">
									
										<tr>
											<td>${item.id }</td>
											<td>${item.name }</td>
											<td>${item.startDateToString() }</td>
											<td>${item.endDateToString() }</td>
											<td><a href="<%=contextPath %>/groupwork-edit?id=${item.id}" class="btn btn-sm btn-primary">Sửa</a> <a
												href="<%=contextPath %>/groupwork-delete?id=${item.id}" class="btn btn-sm btn-danger">Xóa</a> <a
												href="<%=contextPath%>/groupwork-details?id=${item.id}"
												class="btn btn-sm btn-info">Xem</a></td>
										</tr>
									
									
									</c:forEach>

									
										
								</tbody>
							</table>
						</div>
					</div>
				</div>
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
		src="<%=contextPath%>/static/plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=contextPath%>/static/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="<%=contextPath%>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script src="<%=contextPath%>/static/js/jquery.slimscroll.js"></script>
	<script src="<%=contextPath%>/static/js/jquery.dataTables.js"></script>
	<!--Wave Effects -->
	<script src="<%=contextPath%>/static/js/waves.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<%=contextPath%>/static/js/custom.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</body>

</html>