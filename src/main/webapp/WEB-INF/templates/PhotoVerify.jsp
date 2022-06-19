<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
		<p style="text-align: center; color: green; size: 15px;">${status }</p>
		<form class="form-horizontal bordered-row"
			action="photoMatchedResponse" method="POST" data-parsley-validate=""
			id="demo-form">




			<div class="panel">
				<div class="panel-body">
					<h3 class="title-hero">
						<b>Candidate List</b>
					</h3>
					<%
						List<Object[]> dataList = (List<Object[]>) request.getAttribute("candidate");
						if (dataList != null) {
							boolean flag = true;
							for (Object[] data : dataList) {
					%>

					<table class="table table-striped table-bordered">
						<%
							if (flag) {
										flag = false;
						%>
						<thead>
							<tr>

								<th><b>Candidate Id :</b> <%=data[0]%><input type="hidden"
									value="<%=data[0]%>" name="candidateId"></th>
								<th><b>Name :</b> <%=data[1]%></th>

							</tr>
							<tr>

								<th>Registration Photo</th>
								<th>Login Photo</th>
							</tr>
						</thead>
						<%
							}
						%>
						<tbody>
							<tr>
								<td><img src="data:image/png;base64,<%=data[2]%>"
									height="260" width="320"></td>
								<td><img src="<%=data[3]%>" height="260" width="320"></td>
							</tr>
							<%
								}
								}
							%>
						</tbody>
					</table>
					<div class="form-group">

						<div class="col-md-6 text-right">
							<b>Is Matched? (Yes/No) :</b>
						</div>

						<div class="col-md-6">
							<input type="radio" value="Yes" name="status" required="required">
							Yes <input type="radio" value="No" name="status"
								required="required"> No
						</div>
					</div>
					<!-- <div class="form-group">

						<div class="col-md-6 text-right">
							<b>Remarks :</b>
						</div>

						<div class="col-md-6">
							<select type= value="Yes" name="status" required="required">
							Yes <input type="radio" value="No" name="status"
								required="required"> No
						</div>
					</div> -->
					<div class=" form-group">

						<div class="col-md-6 text-right">
							<button class="btn btn-primary btn-hover" type="submit"
								value="submit">submit</button>
						</div>

					</div>
				</div>
			</div>
		</form>
	</div>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-bootstrap.js"></script>
	<script type="text/javascript"
		src="assets/widgets/datatable/datatable-tabletools.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					var table = $("#datatable")
							.DataTable(
									{
										"responsive" : true,
										"autoWidth" : false,
										"aLengthMenu" : [
												[ 3, 5, 8, 10, 15, 20, 25, 50,
														100, -1 ],
												[ 3, 5, 8, 10, 15, 20, 25, 50,
														100, "ALL" ] ],
										"pageLength" : 8,
										"ordering" : false
									});
				});
	</script>

</body>
</html>