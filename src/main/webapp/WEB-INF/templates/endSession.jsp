<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
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
					<b>END SESSION</b>
				</h3>
				<label class=" control-label col-sm-12 font-green text-center">${msg} </label>
				<div class="example-box-wrapper">
					<form class="form-horizontal bordered-row"
						action="endSession" method="POST"
						data-parsley-validate="" id="demo-form">
						<table class="table table-bordered" border="1"
							style="width: 100%;">
							<tr>
								<th>INSTRUCTIONS:</th>
							</tr>
							<tr>
								<td><ol>
										<li class="pad10A">PLEASE MAKE SURE WHOLE CANDIDATE ANSWERS PUSHED OR
											NOT</li>

										<li  class="pad10A">PLEASE MAKE SURE WHOLE CANDIDATE IMAGES PUSHED OR NOT</li>
									</ol></td>
							</tr>

						</table>



						<div class="form-group">
							<label class="col-sm-3 control-label"></label>
							<div class="col-sm-6">
								<button id="submit" type="submit"
									class="btn btn-primary mgn-top">END SESSION</button>
							</div>
						</div>

					</form>
					<!-- <div class="form-group">
						<label class="col-sm-3 control-label">TO see Candidate
							List Click here :</label>
						<div class="col-sm-6">
							<a href="CandidateList"><button>Candidate List</button></a>
						</div>
					</div> -->
				</div>
			</div>
		</div>
	</div>
	<!-- <script>
		$(document)
				.ready(
						function() {
							$('#sel')
									.change(
											function() {
												debugger;
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

																function itr(
																		item) {
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
 -->
</body>
</html>