<%@page import="org.springframework.core.env.Environment"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.ttipl.pojo.ExamLocationSess"%>
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

.color {
	position: relative;
	padding: 10px 15px;
	margin-bottom: -1px;
	background-color: #fff;
	border: 1px solid #ddd;
}

#datatable {
	margin-top: 0px !important;
}

td {
	padding: 5px !important;
}

.panel-body {
	font-size: 12px !important;
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
<meta http-equiv="refresh" content=120000>

</head>
<body>
	<jsp:include page='headerUI.jsp'>
		<jsp:param name="articleId" value="" />
	</jsp:include>
	<div id="page-title">
		<div class="panel">
			<div class="panel-body">
				<div class="title-hero">
					<b class="text-left">Candidate List</b> <span class="text-right "
						style="padding-left: 90vh !important;"> <b>Login
							Candidates count :</b></span> <span class="font-green font-size-20">${count}</span>
				</div>
				<div class="example-box-wrapper">
					<div></div>
					<%-- 	<form class="form-horizontal bordered-row" action="#" method="POST"
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



					</form> --%>

					<%-- 	<form action="CandidatelistUrl" method="post" id="form"
						class="form-horizontal bordered-row">
						<div class="form-group">
							<label class="col-sm-3 control-label">Select Exam Name</label>
							<div class="col-sm-6">
								<select class="form-control" name="examId" id="sel">
									<option value="">Select Exam Name</option>
									<c:forEach items="${values}" var="m">
										<option value="${m.id}">${m.exam_name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">Select Location
								Name</label>
							<div class="col-sm-6">
								<select id="cId" name="sessionId" class="form-control" required>
									<option value="">Select Session</option>
									<%
										List<ExamLocationSess> list = (List<ExamLocationSess>) request.getAttribute("session");
										if (list != null) {
											for (ExamLocationSess sessions : list) {
									%>
									<option value=<%=sessions.getId()%>>
										<%=sessions.getStart_time()%>-<%=sessions.getEnd_time()%>
									</option>
									<%
										}
										}
									%>
								</select>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-sm-4" for="selectpost"> </label>

							<div class="col-sm-5">
								<button onclick="" class="btn btn-primary btn-hover">Get
									Candidates</button>
							</div>
						</div>
					</form> --%>
					<%-- <div class="form-group row">
						<p class="font-size-20" style="text-align: center;">
							Login Candidates count : <span class="font-green font-size-20">${count}</span>
						</p>
					</div> --%>

					<!-- <div class="row pad15A center-div">
						<div class="col-md-12 ">
							<a href="#" class="color"><span class="badge bg-green">&nbsp;</span>
								&nbsp;Matched</a> <a href="#" class="color"> <span
								class="badge bg-red">&nbsp;</span>&nbsp;Not Matched
							</a> <a href="#" class="color"><span class="badge bg-yellow">&nbsp;</span>&nbsp;Unable
								Verify</a> <a href="#" class="color"><span class="badge bg-blue">&nbsp;</span>
								&nbsp;Not Verified Yet</a> <a href="#" class="color"><span
								class="badge bg-orange">&nbsp;</span>&nbsp;Records Not Found </a> <a
								href="#" class="color"><span class="badge bg-black">&nbsp;</span>&nbsp;Not
								Connected </a>
						</div>
					</div> -->

					<table id="datatable" class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>S.no</th>
								<th>Candidate Id</th>
								<th>Name</th>
								<th>Answered</th>
								<th>Ip Address</th>
								<th>Start Time</th>
								<th>Remaining Time</th>
								<th>Status</th>
								<th>Start/stop</th>
								<!-- <th>Stop</th> -->
							</tr>
						</thead>




						<tbody>
							<%
								List<Candidate> data = (List<Candidate>) request.getAttribute("candidateList");
								HashMap<String, Integer> candidateVerifiedList = (HashMap<String, Integer>) request
										.getAttribute("candidateVerifiedList");
								if (data != null) {
									int index = 1;
									for (Candidate entity : data) {
							%>

							<tr>
								<td><%=index++%></td>
								<td><a
									href="http://<%=request.getAttribute("photoVerification.api.ip")%>:<%=request.getAttribute("photoVerification.api.server.port")%>/PhotoVerificationAPI/getDetailedReport?candidateId=<%=entity.getCandidate_id()%>"
									target="blank"><%=entity.getCandidate_id()%> <%
 	if (candidateVerifiedList != null) {
 				if (!candidateVerifiedList.containsKey("status")) {
 					if (candidateVerifiedList.containsKey(entity.getCandidate_id())) {
 						if (candidateVerifiedList.get(entity.getCandidate_id()) == 0) {
 %> <span class="badge bg-red">&nbsp;</span> <%
 	} else if (candidateVerifiedList.get(entity.getCandidate_id()) == 1) {
 %> <span class="badge bg-green ">&nbsp;</span> <%
 	} else if (candidateVerifiedList.get(entity.getCandidate_id()) == 2) {
 %> <span class="badge bg-yellow ">&nbsp;</span> <%
 	} else {
 %> <span class="badge bg-blue">&nbsp;</span> <%
 	}
 					} else {
 %> <span class="badge bg-orange">&nbsp;</span> <%
 	}
 				} else {
 %> <span class="badge bg-black">&nbsp;</span></a></td>
								<%
									}
											}
								%>
								<td><%=entity.getCandidate_first_name()%></td>
								<td><%=entity.getCandidateCountAnswered()%></td>
								<td><%=entity.getCandidateIpAddress()%></td>
								<td><%=entity.getStartTime()%></td>
								<td><%=entity.getRemainingTime()%></td>

								<%
									boolean examISEnd = entity.getisExam_is_end();
											boolean isCandidateInQa = entity.isCandidateInQa();
								%>
								
								<%if(examISEnd == true && !(entity.getRemainingTime().equalsIgnoreCase("00:00:00")))
									{
									%>
									<td class="font-green">Stopped Temporarily</td>
								<td><a href="candStart=<%=entity.getCandidate_id()%>"><input
										type="hidden" id="edit" name="edit"
										value="<%=entity.getCandidate_id()%>">Restart</a></td>
									
									<%}
								
								else if (examISEnd == true && isCandidateInQa == true)
									{
								%>
								<td class="font-green">Completed</td>
								<td><a href="candStart=<%=entity.getCandidate_id()%>"><input
										type="hidden" id="edit" name="edit"
										value="<%=entity.getCandidate_id()%>">Restart</a></td>
								<%
									}
									
								
								
									else if (examISEnd == false && isCandidateInQa == true) {
								%><td class="font-yellow">Running...</td>
								<td><a href="candStop=<%=entity.getCandidate_id()%>"><input
										type="hidden" id="edit" name="edit"
										value="<%=entity.getCandidate_id()%>">Stop</a></td>
								<%
									} else {
								%>
								<td class="font-orange">Not Started</td>
								<td>--</td>

								<%
									}
								%>

								<%-- <%boolean flag= request.getAttribute(<%=entity.getisExam_is_end()%>);
								if
								%> --%>

								<%-- <c:set value="${<%=entity.getisExam_is_end()%> }" var="exam"
									scope="session"></c:set>
								<c:choose>
									<c:when test="${exam == 1}">

										<td><a href="candStart=<%=entity.getCandidate_id()%>"><input
												type="hidden" id="edit" name="edit"
												value="<%=entity.getCandidate_id()%>">Start/Restart</a></td>
									</c:when>
									<c:otherwise>
										<td><a href="candStop=<%=entity.getCandidate_id()%>"><input
												type="hidden" id="edit" name="edit"
												value="<%=entity.getCandidate_id()%>">Stop</a></td>
									</c:otherwise>
								</c:choose> --%>
								<%-- <td><a href="candStart=<%=entity[0]%>"><input
										type="hidden" id="edit" name="edit" value="<%=entity[0]%>">Start/Restart</a></td>
								<td><a href="candStop=<%=entity[0]%>"><input
										type="hidden" id="edit" name="edit" value="<%=entity[0]%>">Stop</a></td> --%>
								<%
									
								%>

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
		$(document)
				.ready(
						function() 
						{
							$('body').click(function()
									{
								$('#msg').hide();
							        });

							var table = $("#datatable").DataTable(
								    	{
										"responsive" : true,
										"autoWidth" : false,
										"aLengthMenu" : [
												[ 3, 5, 8, 10, 15, 20, 25, 50,
														100, -1 ],
												[ 3, 5, 8, 10, 15, 20, 25, 50,
														100, "ALL" ] ],
										"pageLength" : 50,
										"ordering" : true,
										"language" : {
											"search" : "Search"
										 }
									});

							$('#sel')
									.change(
											function() {

												$('#cId').empty();
												var examName = $(
														'#sel option:selected')
														.val();
												$
														.ajax({
															method : 'POST',
															url : 'findLocationAndSession',
															cache : false,
															data : {
																examId : examName
															},
															ContentType : 'json',

															success : function(
																	datar) {
																$('#cId')
																		.append(
																				"<option>"
																						+ "Select Location"
																						+ "</option>");
																var obj = JSON
																		.parse(datar);
																obj
																		.forEach(itr);

																function itr(item) 
																{
																	$('#cId')
																			.append(
																					"<option value="+item.id+">"
																							+ item.location_name
																							+ '('
																							+ item.start_time
																							+ '-'
																							+ item.end_time
																							+ ')'
																							+ "</option>");
																}

															},
															error : function(
																	jqxhr,
																	status,
																	exception) {
																alert(
																		exception,
																		status);
															}
														});
											});

						});

		
	</script>
</body>
</html>