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
			action="pullDataBasedOnExamIdAndLocationSession" method="POST"
			data-parsley-validate="" id="demo-form">




			<div class="panel">
				<div class="panel-body">
					<h3 class="title-hero">
						<b>Non-Matched candidates List</b>
					</h3>
					<div class="example-box-wrapper">
						<table id="datatable" class="table table-striped table-bordered">
							<thead>
								<tr>
									<th>S.no</th>
									<th>Candidate Id</th>
									<th>Name</th>
									<th>Registration Photo</th>
									<th>Login Photo</th>
									<th>Count</th>
									<th>View</th>
								</tr>
							</thead>




							<tbody>
								<%
									List<Object[]> data = (List<Object[]>) request.getAttribute("list");
									if (data != null) {
										int index = 1;
										for (Object[] entity : data) {
								%>

								<tr>
									<td><%=index++%></td>
									<td><%=entity[0]%></td>
									<td><%=entity[1]%></td>
									<td><img src="data:image/png;base64,<%=entity[2]%>"
										height="120" width="120"></td>
									<td><img src="<%=entity[3]%>" height="120" width="120"></td>

									<td><%=entity[6]%></td>



									<td><a class="font-blue"
										href="veiwCandidatePhoto?candidateId=<%=entity[0]%>">View</a></td>



								</tr>
								<%
									}
									}
								%>


							</tbody>
						</table>
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
					/*	$("#createpost").validate({
					rules : {
						post_name : {
							required : true,
						},
						post_description : {
							required : true,
						},
						post_qualifications : {
							required : true,
						},
						no_of_vacancies : {
							required : true,
							minlength : 1,

							digits : true
						}

					},
					submitHandler : function(from) {
						alert('Post added successfully'); // for demo
						return true; // for demo
					}
					}); */

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
										"ordering" : true,
										"language" : {
											"search" : "Search"
										}
									});
				});
	</script>
</body>
</html>