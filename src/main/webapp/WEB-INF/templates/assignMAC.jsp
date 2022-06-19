
<%@page import="com.ttipl.service.MAC"%>
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>

<style>
/* Loading Spinner */
#myProgress {
	width: 100%;
	background-color: #ddd;
	display: none;
}

#myBar {
	width: 0px;
	height: 30px;
	background-color: #4CAF50;
	text-align: center;
	line-height: 30px;
	color: white;
}

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
<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
<title>:: RRC ::</title>
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>

	<jsp:include page='headerUI.jsp'>
		<jsp:param name="articleId" value="" />
	</jsp:include>
	<div class="panel">
		<div class="panel-body">
			<h3 class="title-hero">
				<b>ASSIGN MAC</b>
			</h3>
			<div class="example-box-wrapper">
				<form class="form-horizontal bordered-row" action="#" method="POST"
					data-parsley-validate="" id="demo-form">
					<%
						 String clientIp = request.getRemoteAddr();
						String mac = MAC.getMac(clientIp);
					%>

					<div class="form-group ">
						<label class="control-label col-sm-12 text-center" id="msg">
						</label>


					</div>
					<div class="form-group row">
						<label class="control-label col-sm-4" for="selectpost"> IP
							ADDRESS: </label>

						<div class=" col-sm-5">
							<input type="text" value="<%=clientIp%>" disabled="disabled"
								class="form-control" id="ipAddress">
						</div>
					</div>
					<div class="form-group row">

						<label class="control-label col-sm-4">MAC ADDRESS:</label>
						<div class=" col-sm-5">
							<input type="text" value="<%=mac%>" disabled="disabled"
								class="form-control">
						</div>
					</div>
					<div class="form-group row">
						<div class=" col-sm-4"></div>
						<div class=" col-sm-5">
							<button type="button"
								onclick="checkUserIPAndUpdateMac('<%=mac%>','UPDATE');"
								class="btn btn-primary btn-hover" id="button">UPDATE</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>


</body>

<script>
	var clientIp = $('#ipAddress').val();
	$(document).ready(function() {

		checkUserIPAndUpdateMac("", "CHECK");

	});
	function checkUserIPAndUpdateMac(mac, type) {
		$.ajax({
			method : 'POST',
			url : 'checkUserIPAndUpdateMac',
			cache : false,
			data : {
				ipadrress : clientIp,
				type : type,
				mac : mac
			},
			ContentType : 'json',

			success : function(datar) {
				var data = '';

				if (datar.includes('msg')) {
					data = datar.split('msg')[0];
					$('#msg').addClass("font-green");
					$('#msg').removeClass("font-red");
					document.getElementById('msg').innerHTML = data;
					$('#button').attr('disabled', true);
				} else {
					data = datar;
					$('#msg').addClass("font-red");
					$('#msg').removeClass("font-green");
					document.getElementById('msg').innerHTML = data;
					$('#button').attr('disabled', false);
				}
			},
			error : function(jqxhr, status, exception) {
				alert(exception, status);
			}
		});

	}
</script>

</html>