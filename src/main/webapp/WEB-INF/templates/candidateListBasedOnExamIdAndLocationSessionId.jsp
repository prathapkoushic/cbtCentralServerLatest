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
<!-- 
<!-- Favicons -->

<!-- 	<link rel="apple-touch-icon-precomposed" sizes="144x144"
		href="../../assets/images/icons/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114"
		href="../../assets/images/icons/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72"
		href="../../assets/images/icons/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed"
		href="../../assets/images/icons/apple-touch-icon-57-precomposed.png">
	<link rel="shortcut icon" href="../../assets/images/icons/favicon.png">



	<link rel="stylesheet" type="text/css"
		href="../../assets/bootstrap/css/bootstrap.css">


	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/animate.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/backgrounds.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/boilerplate.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/border-radius.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/grid.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/page-transitions.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/spacing.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/typography.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/utils.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/colors.css">

	ELEMENTS

	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/badges.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/buttons.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/content-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/dashboard-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/forms.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/images.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/info-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/invoice.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/loading-indicators.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/menus.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/panel-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/response-messages.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/responsive-tables.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/ribbon.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/social-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/tables.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/tile-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/elements/timeline.css">



	ICONS

	<link rel="stylesheet" type="text/css"
		href="../../assets/icons/fontawesome/fontawesome.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/icons/linecons/linecons.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/icons/spinnericon/spinnericon.css">


	WIDGETS

	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/accordion-ui/accordion.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/calendar/calendar.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/carousel/carousel.css">

	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/charts/justgage/justgage.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/charts/morris/morris.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/charts/piegage/piegage.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/charts/xcharts/xcharts.css">

	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/chosen/chosen.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/colorpicker/colorpicker.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/datatable/datatable.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/datepicker/datepicker.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/datepicker-ui/datepicker.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/daterangepicker/daterangepicker.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/dialog/dialog.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/dropdown/dropdown.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/dropzone/dropzone.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/file-input/fileinput.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/input-switch/inputswitch.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/input-switch/inputswitch-alt.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/ionrangeslider/ionrangeslider.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/jcrop/jcrop.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/jgrowl-notifications/jgrowl.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/loading-bar/loadingbar.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/maps/vector-maps/vectormaps.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/markdown/markdown.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/modal/modal.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/multi-select/multiselect.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/multi-upload/fileupload.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/nestable/nestable.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/noty-notifications/noty.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/popover/popover.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/pretty-photo/prettyphoto.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/progressbar/progressbar.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/range-slider/rangeslider.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/slidebars/slidebars.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/slider-ui/slider.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/summernote-wysiwyg/summernote-wysiwyg.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/tabs-ui/tabs.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/theme-switcher/themeswitcher.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/timepicker/timepicker.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/tocify/tocify.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/tooltip/tooltip.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/touchspin/touchspin.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/uniform/uniform.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/wizard/wizard.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/widgets/xeditable/xeditable.css">

	SNIPPETS

	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/chat.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/files-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/login-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/notification-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/progress-box.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/todo.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/user-profile.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/snippets/mobile-navigation.css">


	<link rel="stylesheet" type="text/css"
		href="../../assets/applications/mailbox.css">



	<link rel="stylesheet" type="text/css"
		href="../../assets/themes/admin/layout.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/themes/admin/color-schemes/default.css">

	Components theme

	<link rel="stylesheet" type="text/css"
		href="../../assets/themes/components/default.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/themes/components/border-radius.css">

	Admin responsive

	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/responsive-elements.css">
	<link rel="stylesheet" type="text/css"
		href="../../assets/helpers/admin-responsive.css">

	JS Core

	<script type="text/javascript"
		src="../../assets/js-core/jquery-core.js"></script>
	<script type="text/javascript"
		src="../../assets/js-core/jquery-ui-core.js"></script>
	<script type="text/javascript"
		src="../../assets/js-core/jquery-ui-widget.js"></script>
	<script type="text/javascript"
		src="../../assets/js-core/jquery-ui-mouse.js"></script>
	<script type="text/javascript"
		src="../../assets/js-core/jquery-ui-position.js"></script>
	<script type="text/javascript" src="../../assets/js-core/transition.js"></script>
	<script type="text/javascript" src="../../assets/js-core/modernizr.js"></script>
	<script type="text/javascript"
		src="../../assets/js-core/jquery-cookie.js"></script>








	<script type="text/javascript"
		src="../../assets/widgets/datatable/datatable.js"></script>
	<script type="text/javascript"
		src="../../assets/widgets/datatable/datatable-bootstrap.js"></script>
	<script type="text/javascript"
		src="../../assets/widgets/datatable/datatable-tabletools.js"></script>
	<script type="text/javascript"
		src="../../assets/widgets/datatable/datatable-reorder.js"></script>

	<script type="text/javascript">
		/* Datatables export */

		$(document)
				.ready(
						function() {
							var table = $('#datatable-tabletools').DataTable();
							var tt = new $.fn.dataTable.TableTools(table);

							$(tt.fnContainer())
									.insertBefore(
											'#datatable-tabletools_wrapper div.dataTables_filter');

							$('.DTTT_container').addClass('btn-group');
							$('.DTTT_container a').addClass(
									'btn btn-default btn-md');

							$('.dataTables_filter input').attr("placeholder",
									"Search...");

						});

		/* Datatables reorder */

		$(document).ready(function() {
			$('#datatable-reorder').DataTable({
				dom : 'Rlfrtip'
			});

			$('#datatable-reorder_length').hide();
			$('#datatable-reorder_filter').hide();

		});

		$(document).ready(function() {
			$('.dataTables_filter input').attr("placeholder", "Search...");
		});
	</script> -->

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

			<div class="form-group">
				<label class="col-sm-3 control-label">Select Exam Name</label>
				<div class="col-sm-6">
					<select class="form-control" name="examId" id="sel"
						required="required">
						<option value="" selected>Select Exam Name</option>
						<c:forEach items="${values}" var="m">
							<option value="${m.id}">${m.exam_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">Select Location Name</label>
				<div class="col-sm-6">
					<select id="cId" name="examLocationSessionId" class="form-control"
						required>
						<option value="">select location</option>
					</select>
				</div>
			</div>


			<div class="form-group">
				<label class="col-sm-3 control-label"></label>
				<div class="col-sm-6">
					<button id="submit" type="submit" class="btn btn-primary mgn-top">Pull</button>
				</div>
			</div>

		</form>
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
								<th>First Name</th>
								<th>Exam Name</th>
								<th>Location Name</th>
								<th>Post Name</th>
								<th>Start/Restart</th>
								<th>Stop</th>
							</tr>
						</thead>




						<tbody>
							<%
								List<Object[]> data = (List<Object[]>) request.getAttribute("value");
							if (data != null) {
								int index = 1;
								for (Object[] entity : data) {
							%>

							<tr>
								<td><%=index++%></td>
								<td id="view"><%=entity[1]%></td>
								<td id="view"><%=entity[2]%></td>
								<td id="view"><%=entity[3]%></td>
								<td id="view"><%=entity[4]%></td>
								<td><a href="candStart=<%=entity[0]%>"><input
										type="hidden" id="edit" name="edit" value="<%=entity[0]%>">Start/Restart</a></td>
								<td><a href="candStop=<%=entity[0]%>"><input
										type="hidden" id="edit" name="edit" value="<%=entity[0]%>">Stop</a></td>
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
										"ordering" : false
									});
				});
	</script>
	<script>
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
</body>
</html>