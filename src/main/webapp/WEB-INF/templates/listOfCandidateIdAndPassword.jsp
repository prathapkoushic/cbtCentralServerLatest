<%@page import="com.ttipl.pojo.Candidate"%>
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
		<div class="panel">
			<div class="panel-body">
				<h3 class="title-hero">
					<b>Candidate List</b>
				</h3>
				<div class="example-box-wrapper">
					<table id="datatable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>S.no</th>
								<th>Candidate Id</th>
								<th>Password</th>
							</tr>
						</thead>




						<tbody>
							<%
								List<Candidate> data = (List<Candidate>) request.getAttribute("values1");
								if (data != null) {
									int index = 1;
									for (Candidate entity : data) {
							%>

							<tr>
								<td><%=index++%></td>
								<td><%=entity.getCandidate_id()%></td>
								<td><%=entity.getPassword()%></td>


							

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
					$('body').click(function() {
						$('#msg').hide();
					});
					//setTimeout(myFunction, 300);

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