<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>


<!-- Style Sheet -->
<form name="Gate_Register" method="Post" action=./AccountServlet>
<br><br><br>

<h2>Gate&nbsp;Register</h2>
  <div align="center">
    
  <table align="center" class="contentTable" width="40%">
<tr><td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>    


<tr>

<td Class="addedit">Member&nbsp;Code</td>
<td> <input type=text name=Code size=16 maxlength=15></td>

<td Class="addedit">Course Year</td>
<td><select name="Year" size="1" style="width: 110px">
<option value="ALL" selected >SELECT</option>
<option value="1" >I Year</option>
<option value="2">II Year</option>
<option value="3">III Year</option>
<option value="4">IV Year</option>
<option value="5">V Year</option>
<option value="6">Pass out</option>

</select>

</tr>



      <tr> <td Class="addedit">From Date</td>
       <td>
	<INPUT name=gate_from size=10  onfocus=this.blur(); value="<%=dateString%>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Gate_Register.gate_from, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
      <td Class="addedit">To Date</td>
       <td>
	<INPUT name=gate_to size=10  onfocus=this.blur(); value="<%=dateString%>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Gate_Register.gate_to, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
      </tr>  
<tr>

<tr>
<td Class="addedit">In&nbsp;(hh:mm)</td>
<td><select name="inTimeHour" size="1" style="width: 50px">

<option value="00" selected>00</option>
<option value="1">01</option>
<option value="2">02</option>
<option value="3">03</option>
<option value="4">04</option>
<option value="5">05</option>
<option value="6">06</option>
<option value="7">07</option>
<option value="8">08</option>
<option value="9">09</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>

</select>
&nbsp;&nbsp;&nbsp;
<select name="inTimeMinutes" size="1" style="width: 50px">

<option value="00" selected>00</option>
<option value="1">01</option>
<option value="2">02</option>
<option value="3">03</option>
<option value="4">04</option>
<option value="5">05</option>
<option value="6">06</option>
<option value="7">07</option>
<option value="8">08</option>
<option value="9">09</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
<option value="32">32</option>
<option value="33">33</option>
<option value="34">34</option>
<option value="35">35</option>
<option value="36">36</option>
<option value="37">37</option>
<option value="38">38</option>
<option value="39">39</option>
<option value="40">40</option>
<option value="41">41</option>
<option value="42">42</option>
<option value="43">43</option>
<option value="44">44</option>
<option value="45">45</option>
<option value="46">46</option>
<option value="47">47</option>
<option value="48">48</option>
<option value="49">49</option>
<option value="50">50</option>
<option value="51">51</option>
<option value="52">52</option>
<option value="53">53</option>
<option value="54">54</option>
<option value="55">55</option>
<option value="56">56</option>
<option value="57">57</option>
<option value="58">58</option>
<option value="59">59</option>
</select></td>


<td Class="addedit">Out&nbsp;(hh:mm)</td>
<td><select name="outTimeHour" size="1" style="width: 50px">

<option value="00">00</option>
<option value="1">01</option>
<option value="2">02</option>
<option value="3">03</option>
<option value="4">04</option>
<option value="5">05</option>
<option value="6">06</option>
<option value="7">07</option>
<option value="8">08</option>
<option value="9">09</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23" selected>23</option>

</select>
&nbsp;&nbsp;&nbsp;
<select name="outTimeMinutes" size="1" style="width: 50px">

<option value="00">00</option>
<option value="1">01</option>
<option value="2">02</option>
<option value="3">03</option>
<option value="4">04</option>
<option value="5">05</option>
<option value="6">06</option>
<option value="7">07</option>
<option value="8">08</option>
<option value="9">09</option>
<option value="10">10</option>
<option value="11">11</option>
<option value="12">12</option>
<option value="13">13</option>
<option value="14">14</option>
<option value="15">15</option>
<option value="16">16</option>
<option value="17">17</option>
<option value="18">18</option>
<option value="19">19</option>
<option value="20">20</option>
<option value="21">21</option>
<option value="22">22</option>
<option value="23">23</option>
<option value="24">24</option>
<option value="25">25</option>
<option value="26">26</option>
<option value="27">27</option>
<option value="28">28</option>
<option value="29">29</option>
<option value="30">30</option>
<option value="31">31</option>
<option value="32">32</option>
<option value="33">33</option>
<option value="34">34</option>
<option value="35">35</option>
<option value="36">36</option>
<option value="37">37</option>
<option value="38">38</option>
<option value="39">39</option>
<option value="40">40</option>
<option value="41">41</option>
<option value="42">42</option>
<option value="43">43</option>
<option value="44">44</option>
<option value="45">45</option>
<option value="46">46</option>
<option value="47">47</option>
<option value="48">48</option>
<option value="49">49</option>
<option value="50">50</option>
<option value="51">51</option>
<option value="52">52</option>
<option value="53">53</option>
<option value="54">54</option>
<option value="55">55</option>
<option value="56">56</option>
<option value="57">57</option>
<option value="58">58</option>
<option value="59" selected>59</option>
</select></td>

</tr>


</tr>

<tr><td Class="addedit" >Designation<td colspan="3"> <select size="1" name="desig"  style="width: 350px">
<option value="ALL">SELECT</option>
<c:if test="${DesigSearchList ne null }" >
<c:forEach items="${DesigSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select></td></tr>


<tr><td Class="addedit" >Department<td colspan="3"> <select size="1" name="dept"  style="width: 350px">
<option value="ALL">SELECT</option>
<c:if test="${DepartmentSearchList ne null }" >
<c:forEach items="${DepartmentSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select></td></tr>


<tr><td Class="addedit" >Group<td colspan="3"> <select size="1" name="group"  style="width: 350px">
<option value="ALL">SELECT</option>
<c:if test="${distinctBranchWiseGroupSearchList ne null }" >
<c:forEach items="${distinctBranchWiseGroupSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select></td></tr>


<tr><td Class="addedit" >Course<td colspan="3"> <select size="1" name="course"  style="width: 350px">
<option value="ALL">SELECT</option>
<c:if test="${distinctBranchWiseCourseSearchList ne null }" >
<c:forEach items="${distinctBranchWiseCourseSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.desc}" /></option>
</c:forEach>
</c:if>
</select></td></tr>




<tr><td Class="addedit" >Campus<td colspan="3"> <select size="1" name="branch"  style="width: 350px">
<!-- <option value="ALL">SELECT</option> -->
<c:if test="${distinctBranchSearchList ne null }" >
<c:forEach items="${distinctBranchSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select></td>

</tr>

<tr><td>&nbsp;</td></tr>
	<tr>
	<td></td>
	<td colspan="5">
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>	






    
    <tr><td> &nbsp; </td></tr>    
    </table></td></tr></table></div>  
    <p align="center">
    <input type="button" Class="submitButton" value="Statistics" name="gate_statistics" onclick="Statistics_Report()" >
    <input type="button" Class="submitButton" value="Detail" name="gate_Print" onclick="Print_Report()" >
		  <input type="button" Class="submitButton" value="All Logout" name="all_logout" onclick="All_Logout()">
		  <input type="reset" Class="submitButton" value="Clear" name="gate_clear">
		  <input type="hidden" name="flag">	
		  <input type="hidden" name="flagExcel">	  
    </p>
		
		
		
		

</form>
</body>
</html>

<%
String uchek=request.getParameter("check");
if(uchek!=null){
if(uchek.equals("success")){
	 %>
	<script language="JavaScript">
	   alert("All Logout Successfully !");
	</script><%
	}else  {
  %>
	<script language="JavaScript">
	   alert("Failed to Logout All !");
	</script><%
  }
}
%>

<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function Print_Report()
{
			document.Gate_Register.flag.value="Gate_Report";
			document.Gate_Register.method="post";
			document.Gate_Register.submit();
}
function Statistics_Report()
{
			document.Gate_Register.flag.value="Statistics_Report";
			document.Gate_Register.method="post";
			document.Gate_Register.submit();
}
function All_Logout()
{
			document.Gate_Register.flag.value="All_Logout";
			document.Gate_Register.method="post";
			document.Gate_Register.submit();
}

</script>


