<%@page import="com.ttipl.pojo.Candidate"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>

<style>
/* Loading Spinner */
.spinner {
	margin: 0;
	width: 70px;
	height: 18px;
	margin: -35px 0 0 -9px;
	position: absolute;
	top: 50%;
	left: 50%;
	text-align: center
}

.spinner>div {
	width: 18px;
	height: 18px;
	background-color: #333;
	border-radius: 100%;
	display: inline-block;
	-webkit-animation: bouncedelay 1.4s infinite ease-in-out;
	animation: bouncedelay 1.4s infinite ease-in-out;
	-webkit-animation-fill-mode: both;
	animation-fill-mode: both
}

.spinner .bounce1 {
	-webkit-animation-delay: -.32s;
	animation-delay: -.32s
}

.spinner .bounce2 {
	-webkit-animation-delay: -.16s;
	animation-delay: -.16s
}

@
-webkit-keyframes bouncedelay { 0%,80%,100%{
	-webkit-transform: scale(0.0)
}

40






































































































%
{
-webkit-transform






































































































:






































































































scale






































































































(






































































































1






































































































.0






































































































)






































































































}
}
@
keyframes bouncedelay { 0%,80%,100%{
	transform: scale(0.0);
	-webkit-transform: scale(0.0)
}
40






































































































%
{
transform






































































































:






































































































scale






































































































(






































































































1






































































































.0






































































































)




































































































;
-webkit-transform






































































































:






































































































scale






































































































(






































































































1






































































































.0






































































































)






































































































}
}
</style>


<meta charset="UTF-8">

<title>:: RRC ::</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">


</head>
<body>
	<jsp:include page='headerUI.jsp'>
		<jsp:param name="articleId" value="" />
	</jsp:include>
	<div id="page-title">
		<div class="panel">
			<div class="panel-body">
				<h3 class="title-hero">
					<b>Candidate List</b>
				</h3>
				<div class="example-box-wrapper">
					<form class="form-horizontal bordered-row" action="#" method="POST"
						data-parsley-validate="" id="demo-form">

						<%
							String msg = (String) request.getAttribute("status");
						if (msg != null) {
						%>
						<div class="form-group" id="msg">
							<label class="control-label col-sm-12 text-center font-green"><%=msg%></label>

						</div>
						<%
							}
						%>



					</form>

					<form action="" method="post" id="form"
						class="form-horizontal bordered-row">
						<div class="form-group">
							<label class="col-sm-3 control-label">Enter Candidate ID</label>
							<div class="col-sm-6">
								<input type="number" class="form-control" name="candidateID"
									id="sel" required="required" placeholder="Enter Candidate ID">

							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Enter New IP </label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="newIp" id="cId"
									required="required" placeholder="Enter New ip">
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-sm-4" for="selectpost"> </label>

							<div class="col-sm-5">
								<button onclick="return clickOnsubmit()"
									class="btn btn-primary btn-hover">Change Ip</button>
							</div>
						</div>
					</form>

				</div>
			</div>


		</div>

	</div>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-tabletools.js"></script>
	<script type="text/javascript">
		function clickOnsubmit() {

			$('form').attr('action', 'changeIpUrl');

		}
	</script>

</body>
</html>