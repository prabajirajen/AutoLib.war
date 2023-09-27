<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<script language="JavaScript">
navHover = function() {
	var lis = document.getElementById("navmenu").getElementsByTagName("LI");
	for (var i=0; i<lis.length; i++) {
		lis[i].onmouseover=function() {
			this.className+=" iehover";
		}
		lis[i].onmouseout=function() {
			this.className=this.className.replace(new RegExp(" iehover\\b"), "");
		}
	}
}
if (window.attachEvent) window.attachEvent("onload", navHover);

</script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<style type="text/css">
#invertedtabsline {
	clear: both;
	padding: 0;
	width: 100%;
	height: 18px;
	line-height: 16px;
	background: #21436A; /* #B0C4DE  #4169E1 #8b0000 #5970B2*/
	border-bottom: 1px solid #fff;
	/*Remove this to remove border between bar and tabs*/
	text-align: right;
	font-weight: bold;
}

BODY {
	background: #E7F0FF; /* RK ---> background color for demoframeset.jsp */
	margin: 0;
	padding: 0
}

h1 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 16px;
	font-weight: bold;
	margin: 0;
	padding: 0;
}

hr {
	border: none;
	border-top: 1px solid #CCCCCC;
	height: 1px;
	margin-bottom: 25px;
}

a {
	color: #5F9EA0;
	text-decoration: none;
}

a:hover { /*  RK Background color for hyper link   */
	background:;
	color: #FFF;
}

.vist1 {
	font-family: verdana;
	font-size: 8pt;
	color: black;
}

/* Root = Horizontal, Secondary = Vertical */
ul#navmenu {
	margin: 0;
	border: 0px solid olive;
	padding: 0;
	width: 900px; /*For KHTML*/
	list-style: disc;
	height: 24px;
}

ul#navmenu li {
	margin: 0;
	border: 1px solid olive;
	padding: 0;
	float: left; /*For Gecko*/
	display: inline;
	list-style: disc;
	position: relative;
	height: 24px;
}

ul#navmenu ul {
	margin: 0;
	border: 0px solid olive;
	padding: 0;
	width: 160px;
	list-style: disc;
	display: none;
	position: absolute;
	top: 24px;
	left: 0;
}

ul#navmenu ul:after /*From IE 7 lack of compliance*/ {
	clear: both;
	display: block;
	font: 1px/0px serif;
	content: ".";
	height: 0;
	visibility: hidden;
}

ul#navmenu ul li {
	width: 162px;
	float: left; /*For IE 7 lack of compliance*/
	display: block !important;
	display: inline; /*For IE*/
}

/* Root Menu */
ul#navmenu a {
	border: 1px solid #FFF;
	border-right-color: #CCC;
	border-bottom-color: #CCC;
	padding: 0 6px;
	float: none !important; /*For Opera*/
	float: left; /*For IE*/
	display: block;
	background: #1E5C9B;
	color: #ffffff;
	font: bold 12px/25px Verdana, Arial, Helvetica, sans-serif;
	text-decoration: none;
	height: auto !important;
	height: 1%; /*For IE*/
}

/* Root Menu Hover Persistence */
ul#navmenu a:hover,ul#navmenu li:hover a,ul#navmenu li.iehover a {
	background: # 5EC6FA; /* RK ---> Top color  */
	color: #000;
}

/* 2nd Menu */
ul#navmenu li:hover li a,ul#navmenu li.iehover li a {
	float: none;
	background: #104e8b;
	color: #fff;
}

/* 2nd Menu Hover Persistence */
ul#navmenu li:hover li a:hover,ul#navmenu li:hover li:hover a,ul#navmenu li.iehover li a:hover,ul#navmenu li.iehover li.iehover a
	{
	background: #CCC;
	color: #000;
}

/* 3rd Menu */
ul#navmenu li:hover li:hover li a,ul#navmenu li.iehover li.iehover li a
	{
	background: #104e8b;
	color: #fff;
}

/* 3rd Menu Hover Persistence */
ul#navmenu li:hover li:hover li a:hover,ul#navmenu li:hover li:hover li:hover a,ul#navmenu li.iehover li.iehover li a:hover,ul#navmenu li.iehover li.iehover li.iehover a
	{
	background: #CCC;
	color: #FFF;
}

/* 4th Menu */
ul#navmenu li:hover li:hover li:hover li a,ul#navmenu li.iehover li.iehover li.iehover li a
	{
	background: #B0E2FF;
	color: #666;
}

/* 4th Menu Hover */
ul#navmenu li:hover li:hover li:hover li a:hover,ul#navmenu li.iehover li.iehover li.iehover li a:hover
	{
	background: #CCC;
	color: #FFF;
}

ul#navmenu ul ul,ul#navmenu ul ul ul {
	display: none;
	position: absolute;
	top: 0;
	left: 160px;
}

/* Do Not Move - Must Come Before display:block for Gecko */
ul#navmenu li:hover ul ul,ul#navmenu li:hover ul ul ul,ul#navmenu li.iehover ul ul,ul#navmenu li.iehover ul ul ul
	{
	display: none;
}

ul#navmenu li:hover ul,ul#navmenu ul li:hover ul,ul#navmenu ul ul li:hover ul,ul#navmenu li.iehover ul,ul#navmenu ul li.iehover ul,ul#navmenu ul ul li.iehover ul
	{
	display: block;
}

#clgtitle {
	font-family: Centaur;
	font-size: 25pt;
	color: #ffffff;
}

#titleDateTime {
	font-size: 10pt;
	color: #FDFCDC;
	font-weight: bold;
}

#titleAutolib {
	font-family: verdana;
	font-size: 8pt;
	color: #FEF1E9;
	font-weight: bold;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>


<script language="javascript">
	// Methods for the clock in the bar below the main tabs
	// Start
	function TimeTick()
	{
		time = time+1000;
		if(rTimer) 
		{
			clearTimeout(rTimer);
		}
		rTimer = setTimeout('TimeTick()', 1000);
	}
	function updateClock()
	{
		var date = new Date();
		//date.setTime(time);
		var min = date.getMinutes();
		var hrs = date.getHours();
		var sec = date.getSeconds();
		if(min <= 9) 
		{
			min = "0" + min;
		}
		if(hrs <= 9) 
		{
			hrs = "0" + hrs;
		}
		if(sec <= 9) 
		{
			sec = "0" + sec;
		}
		document.getElementById('Timer').innerHTML=date.getDate() + " " + monthMap[date.getMonth()] + " " + date.getFullYear() + ", "+ hrs+" : "+ min + " : "+sec;
		if(uTimer)
		{
			clearTimeout(uTimer);
		}
		uTimer = setTimeout('updateClock()', 1000);
	}
	
	var time = 0;
	var rTimer,uTimer;
	var monthMap = new Object();
	
	function displayClock(curTime) 
	{
		time = curTime;
		monthMap["0"] = "Jan";
		monthMap["1"] = "Feb";
		monthMap["2"] = "Mar";
		monthMap["3"] = "Apr";
		monthMap["4"] = "May";
		monthMap["5"] = "Jun";
		monthMap["6"] = "Jul";
		monthMap["7"] = "Aug";
		monthMap["8"] = "Sep";
		monthMap["9"] = "Oct";
		monthMap["10"] = "Nov";
		monthMap["11"] = "Dec";
		TimeTick();
		updateClock();
	}
</script>
<body>
	<script language="Javascript">
window.history.forward(0)
</script>
	<table border="0" cellpadding="0" cellspacing="0" bgcolor="#104E8B"
		height="10%" width="100%">
		<tr>
			<td align="center"><c:if test="${UserBranchID eq 1}">
					<img src="<%=request.getContextPath() %>/images/logo.png"
						width="42" height="42" border="0" />
				</c:if> <c:if test="${UserBranchID eq 2}">
					<img src="<%=request.getContextPath() %>/images/logo2.png"
						width="42" height="42" border="0" />
				</c:if> <c:if test="${UserBranchID eq 3}">
					<img src="<%=request.getContextPath() %>/images/logo3.png"
						width="42" height="42" border="0" />
				</c:if></td>

			<c:if test="${UserBranchID eq 1}">
				<td width="58%" align="left" id="clgtitle">&nbsp;Karpaga
					Vinayaga Institute Of Medical Sciences And Research Center</td>
			</c:if>

			<c:if test="${UserBranchID eq 2}">
				<td width="58%" align="left" id="clgtitle">&nbsp;Karpaga
					Vinayaga Institute of Dental Sciences</td>
			</c:if>


			<c:if test="${UserBranchID eq 3}">
				<td width="58%" align="left" id="clgtitle">&nbsp;Karpaga
					Vinayaga College Of Nursing</td>
			</c:if>



			<td width="33%" id="titleDateTime" align="right">Date&Time:
				&nbsp; <span id="Timer"></span> <script>displayClock();</script>&nbsp;&nbsp;
				<div id="titleAutolib">Powered by AutoLib Software Systems
					&nbsp;</div>
			</td>
			<td align="center"><img
				src="<%=request.getContextPath() %>/images/AutoLibFinLogo.bmp"
				width="40" height="35" border="0" /></td>
		</tr>
	</table>
	<div id="invertedtabsline">


		<font color="#FFEECA">Welcome &nbsp;&nbsp; :</font> <font
			color="#FFEECA">&nbsp;&nbsp;<c:out value="${visitMember}" />
			&nbsp;&nbsp;-&nbsp;&nbsp;<c:out value="${visitBranch}" />&nbsp;&nbsp;&nbsp;&nbsp;
		</font>

	</div>



</body>
</html>
