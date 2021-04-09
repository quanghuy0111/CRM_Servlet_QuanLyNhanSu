<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="cybersoft.java09.dto.UserDto"%>
<!DOCTYPE html>
<html lang="en">

<head>

<% String contextPath = request.getContextPath() ;
	UserDto userDto = (UserDto)request.getAttribute("userDto");
%>


<script type="text/javascript">
window.onload = function () {

var chart = new CanvasJS.Chart("chartContainer", {
	theme: "light1", // "light2", "dark1", "dark2"
	animationEnabled: false, // change to true		
	title:{
		text: "Work Status"
	},
	data: [
	{
		// Change type to "bar", "area", "spline", "pie",etc.
		type: "column",
		dataPoints: [
			{ label: "Chưa thực hiện",  y: ${userDto.notDoneWorkPercent}  },
			{ label: "Đã thực hiện", y: ${userDto.pendingWorkPercent}  },
			{ label: "Đã hoàn thành", y: ${userDto.finishWorkPercent} },
			
		]
	}
	]
});
chart.render();

}
</script>


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="../plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link
	href="<%=contextPath %>/static/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Menu CSS -->
<link
	href="<%=contextPath %>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<!-- toast CSS -->
<link
	href="<%=contextPath %>/static/plugins/bower_components/toast-master/css/jquery.toast.css"
	rel="stylesheet">
<!-- morris CSS -->
<link
	href="<%=contextPath %>/static/plugins/bower_components/morrisjs/morris.css"
	rel="stylesheet">
<!-- animation CSS -->
<link href="<%=contextPath %>/static/css/animate.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=contextPath %>/static/css/style.css" rel="stylesheet">
<!-- color CSS -->
<link href="<%=contextPath %>/static/css/colors/blue-dark.css"
	id="theme" rel="stylesheet">
<link rel="stylesheet" href="<%=contextPath %>/static/css/custom.css">
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
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row bg-title">
				<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
					<h4 class="page-title">Dashboard</h4>
				</div>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- row -->
		<div class="row">
			<!--col -->
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<div class="white-box">
					<div class="col-in row">
						<div class="col-md-6 col-sm-6 col-xs-6">
							<i data-icon="E" class="linea-icon linea-basic"></i>
							<h5 class="text-muted vb">CHƯA BẮT ĐẦU</h5>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6">
							<h3 class="counter text-right m-t-15 text-danger">${userDto.notDoneWorkPercent}</h3>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="progress">
								<div class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
									style="width: 40%">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.col -->
			<!--col -->
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<div class="white-box">
					<div class="col-in row">
						<div class="col-md-6 col-sm-6 col-xs-6">
							<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
							<h5 class="text-muted vb">ĐANG THỰC HIỆN</h5>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6">
							<h3 class="counter text-right m-t-15 text-megna">${userDto.pendingWorkPercent}</h3>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="progress">
								<div class="progress-bar progress-bar-megna" role="progressbar"
									aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
									style="width: 40%">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.col -->
			<!--col -->
			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
				<div class="white-box">
					<div class="col-in row">
						<div class="col-md-6 col-sm-6 col-xs-6">
							<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
							<h5 class="text-muted vb">ĐÃ HOÀN THÀNH</h5>
						</div>
						<div class="col-md-6 col-sm-6 col-xs-6">
							<h3 class="counter text-right m-t-15 text-primary">${userDto.finishWorkPercent}</h3>
						</div>
						<div class="col-md-12 col-sm-12 col-xs-12">
							<div class="progress">
								<div class="progress-bar progress-bar-primary"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.col -->
		</div>
		<!-- /.row -->
		<!--row -->
		<div class="row">
			<div class="col-md-12">
				<div class="white-box">
					<div id="chartContainer" style="height: 370px; width: 100%;"></div>
					<script src="https://canvasjs.com/assets/script/canvasjs.min.js"> </script>
				</div>
			</div>
		</div>
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
	<!--Counter js -->
	<script
		src="<%=contextPath %>/static/plugins/bower_components/waypoints/lib/jquery.waypoints.js"></script>
	<script
		src="<%=contextPath %>/static/plugins/bower_components/counterup/jquery.counterup.min.js"></script>
	<!--Morris JavaScript -->
	<script
		src="<%=contextPath %>/static/plugins/bower_components/raphael/raphael-min.js"></script>
	<script
		src="<%=contextPath %>/static/plugins/bower_components/morrisjs/morris.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<%=contextPath %>/static/js/custom.min.js"></script>
	<script src="<%=contextPath %>/static/js/dashboard1.js"></script>
	<script
		src="<%=contextPath %>/static/plugins/bower_components/toast-master/js/jquery.toast.js"></script>
</body>

</html>