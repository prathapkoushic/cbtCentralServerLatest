
<%@page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<!-- ChartJS -->
<script src="assets/chart.js/Chart.min.js"></script>

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

				<div class="example-box-wrapper">
					<div id="chart_div" style="width: 400; height: 300"></div>

					<%
						List<Object[]> data = (List<Object[]>) request.getAttribute("list");
						if (data != null) {
							int index = 1;
							for (Object[] entity : data) {
					%>



					<input class="date1" type="hidden" value="<%=entity[0]%>" /> <input
						class="input1" type="hidden" value="<%=entity[1]%>" />


					<%
						}
						}
					%>

					<input class="size" type="hidden" name=""
						value=<%=(int) request.getAttribute("size")%>>



				</div>
				<div class="col-md-6" style="padding: 15px">
					<div class="panel">
						<div class="panel-body">

							<div class="example-box-wrapper">
								<canvas id="chart"
									style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		/* 	function go() {
		 var data;
		 var chart;

		 google.charts.load('current', {
		 'packages' : [ 'corechart' ]
		 });
		 google.charts.setOnLoadCallback(drawChart);

		 function drawChart() {
		 data = new google.visualization.arrayToDataTable(values);

		 var options = {

		 hAxis : {
		 title : 'DayAndTime'
		 },
		 animation : {
		 duration : 1700,
		 easing : 'out',
		 startup : true,
		 },
		 lineWidth : 8,
		 colors : [ '#e2431e' ],
		 vAxis : {
		 title : 'Candidates Who loggedIn'
		 },
		 legend : 'none',
		 trendlines : {
		 0 : {}
		 }
		 // Draw a trendline for data series 0.

		 };

		 chart = new google.visualization.LineChart(document
		 .getElementById('chart_div'));
		 google.visualization.events.addListener(chart, 'select',
		 selectHandler);
		 chart.draw(data, options);
		 }

		 function selectHandler() {
		 var selectedItem = chart.getSelection()[0];
		 var value = data.getValue(selectedItem.row, 0);
		 alert('The user selected ' + value);
		 }

		 var count = Number(document.getElementsByClassName("size")[0].value);
		 var values = [];
		 var Header = [ 'DayAndTime', 'Candidates Who loggedIn' ];
		 values.push(Header);
		 var l1 = 0
		 if (Number(count) == 0) {
		 var temp = [];
		 temp.push(null);
		 temp.push(null);
		 values.push(temp);
		 }
		 for (var i = 0; i < count; i++) {

		 var val1 = Number(document.getElementsByClassName("input1")[l1].value);
		 var val2 = String(document.getElementsByClassName("date1")[l1].value);
		 var temp = [];
		 temp.push(val2);
		 temp.push(val1);
		 values.push(temp);
		 l1++;
		 }

		 } */

		var count = Number(document.getElementsByClassName("size")[0].value);
		var temp1Lable = [];
		var tempData = [];
		for (var i = 0; i < count; i++) {

			var val1 = Number(document.getElementsByClassName("input1")[l1].value);
			var val2 = String(document.getElementsByClassName("date1")[l1].value);

			temp1Lable.push(val2);
			tempData.push(val1);
			l1++;
		}

		var hourCtx = document.getElementById("chart").getContext('2d');
		var hourConfig = getConfig(temp1Lable, tempData,
				"rgba(151,187,205,0.5)", true, "HOURS", true, "VALUE", "Data",
				"line", "rgba(151,187,205,0.2)", "grey");

		var postChart_line = new Chart(hourCtx, hourConfig);
		postChart_line.legend.display = false;
		postChart_line.options.tooltips.enabled = true;
		function getConfig(label, data, color, isRequired_x, xAxesName,
				isRequired_y, yAxesName, graphLabel, type, axis_Color,
				label_color) {
			var config = {
				type : type,
				data : {
					labels : label,
					borderColor : "#fffff",
					datasets : [ {
						label : '',
						backgroundColor : color,// window.chartColors,
						borderColor : color,//window.chartColors.orange,
						data : data,
						fill : true

					} ]
				},
				options : {
					legend : {
						display : false,
						labels : {
							display : true
						}
					},
					maintainAspectRatio : false,
					responsive : true,
					scaleFontColor : "#FFFFFF",
					title : {
						display : false,
						text : 'Chart.js Line Chart'
					},
					tooltips : {
						enabled : true,
						mode : 'label'

					},
					hover : {
						mode : 'nearest',
						intersect : false
					},
					pointLabels : {
						fontFamily : 'Open Sans',
						fontSize : 10
					},
					scales : {
						xAxes : [ {
							display : isRequired_x,
							scaleLabel : {
								display : false,
								labelString : xAxesName,
								fontColor : axis_Color,
							},
							gridLines : {
								display : true,
								color : axis_Color
							},
							ticks : {
								autoSkip : true,
								maxTicksLimit : 12,
								fontColor : label_color
							}
						} ],
						yAxes : [ {
							display : isRequired_y,
							scaleLabel : {
								display : false,
								labelString : yAxesName,
								fontColor : axis_Color,
							},
							gridLines : {
								display : true,
								color : axis_Color,
								ticks : {
									reverse : true
								}
							},
							ticks : {
								beginAtZero : true,
								//stepSize:1,
								fontColor : label_color
							}
						} ]
					}
				}
			};
			return config;
		}
	</script>


</body>
</html>