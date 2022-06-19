<%@page import="java.util.List"%>

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
	<div id="page-title">
		<div class="panel">
			<div class="panel-body">
				<h3 class="title-hero">
					<b>Labwise Candidate Details</b>
				</h3>
				<div class="example-box-wrapper">

					<%
						List<Object[]> data1 = (List<Object[]>) request.getAttribute("list");
						Object[] array = null;
						if (data1 != null) {
							if (!data1.isEmpty()) {
								array = data1.get(0);
							}
					%>

					<h3 class="title-hero">
						<%
							if (array != null) {
						%>
						<b>Total Number of Candidates in this location : <%=array[2]%></b>
						<%
							} else {
						%>
						<b>Total Number of Candidates in this location : 0</b>
						<%
							}
						%>

					</h3>

					<%
						}
					%>

					<table id="questions_tbl"
						class="table table-striped table-bordered">
						<thead>
							<tr>

								<th>Room Name</th>
								<th>Number of Candidates Per Room</th>


							</tr>
						</thead>




						<tbody>
							<%
								List<Object[]> data = (List<Object[]>) request.getAttribute("list");
								if (data != null) {
									for (Object[] entity : data) {
							%>


							<tr>

								<td><%=entity[0]%></td>
								<td><%=entity[1]%></td>

								<%
									}
									}
								%>
							</tr>
						</tbody>
					</table>


				</div>
			</div>
		</div>
	</div>


</body>
</html>