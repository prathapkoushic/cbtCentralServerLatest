<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script type="text/javascript">
	
</script>
<style>
.progress-title {
	font-size: 18px;
	font-weight: 700;
	color: #000;
	margin: 0 0 30px;
}

.progress {
	height: 17px;
	background: rgba(0, 0, 0, 0.1);
	border-radius: 15px;
	margin-bottom: 30px;
	overflow: visible;
	position: relative;
}

.progress .progress-bar {
	border-radius: 15px;
	box-shadow: none;
	position: relative;
	animation: animate-positive 2s;
}

.progress .progress-icon, .progress .progress-value {
	width: 50px;
	height: 50px;
	border-radius: 50%;
	line-height: 40px;
	background: #fff;
	border: 7px solid #1f75c4;
	font-size: 15px;
	font-weight: 600;
	color: #1f75c4;
	position: absolute;
	top: -17px;
	right: -5px;
}

.progress .progress-icon {
	right: auto;
	left: -5px;
}

.progress.orange .progress-icon, .progress.orange .progress-value {
	border: 7px solid #f7810e;
	color: #f7810e;
}

.progress.pink .progress-icon, .progress.pink .progress-value {
	border: 7px solid #f2438f;
	color: #f2438f;
}

.progress.green .progress-icon, .progress.green .progress-value {
	border: 7px solid #08a061;
	color: #08a061;
}

@
-webkit-keyframes animate-positive { 0%{
	width: 0;
}

}
@
keyframes animate-positive { 0%{
	width: 0;
}

}
#myProgress {
	width: 100%;
	background-color: #ddd;
	display: none;
}

#myBar {
	width: 0px;
	height: 40px;
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

40%
{
-webkit-transform








:scale








(1
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
	
<h3 style="color:red">${msg}</h3>
</body>
</html>