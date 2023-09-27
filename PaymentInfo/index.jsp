<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Budget.BudgetBean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>




<html>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<meta http-equiv="pragma" content="no-cache"/>
</head>

<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>

<body background="/AutoLib/soft.jpg">
<!-- Style Sheet -->
<form name=Payment method="post" action=./PaymentInfoServlet>

<br><br><br><br>
<h2>Payment&nbsp;Report</h2>

<br>
<table align="center" class="contentTable" width="40%">
<tr>
<td>
<table align="center" width="80%">


<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Member&nbsp;Code</td>
<td><input type="textbox" name="user_no"  size=15  maxlength="30" onKeydown="Javascript: if (event.keyCode==13) user();"></td></tr>


<tr><td Class="addedit">Member&nbsp;Name</td>
<td colspan=3><input type="textbox" name="user_name"  size=40 maxlength="50" readonly=true></td></tr>


<tr><td Class="addedit">Department</td>
<td colspan="3"><input type="textbox" name="user_dept"  size=40 maxlength="50" readonly=true></td></tr>

<tr><td Class="addedit">Course</td>
<td colspan="3"><input type="textbox" name="user_course"  size=40 maxlength="50" readonly=true></td></tr>




  <tr>
    <td Class="addedit">From&nbsp;Date</td>
    <td>
	<INPUT name=fromdate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Payment.fromdate, \"dd-mm-yyyy\",\"<%=dateString%>\")' value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>

    <td Class="addedit">To&nbsp;Date</td>
    <TD >
	<INPUT name=todate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Payment.todate, \"dd-mm-yyyy\",\"<%=dateString%>\")' value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		</TD></tr>
  <tr><td> &nbsp; </td></tr>
  
  
  </table></td></tr></table>

<P align=center>

<input type=button name=Report Class="submitButton" value=Report onclick="ShowReport()">
<input type=Reset name=Clear Class="submitButton" Value=Clear >
<input type=hidden name=flag>
	

<a href="#" onclick="overallrpt()">
		<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF"> <font color="RED">OverAllFine</font>
	</a>	
	
<a href="#" onclick="overallExcel()">
		<img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="45" border="0" title="Print Excel"> <font color="RED">OverAllFine</font>
	</a>
	



</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->


<%
ArrayList sc=new ArrayList();
ArrayList pc=new ArrayList();
String message="",info="";
%>

<%
String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("userdetails"))
{
	   sc=(ArrayList)request.getAttribute("MemberDetails");

for(int i=0;i<sc.size();i+=5){
%>
 <script language="JavaScript">
	
	document.Payment.user_no.value="<%=sc.get(i)%>";
	document.Payment.user_name.value="<%=sc.get(i+1)%>";
    document.Payment.user_dept.value="<%=sc.get(i+2)%>";
    document.Payment.user_course.value="<%=sc.get(i+3)%>";        
    document.Payment.flag.value="";
	
</script>
	 <%
}
sc.clear();
}

if(valid.equals("OtherBranchMember")){  // For Titan
	%>
    <script language="JavaScript">
    alert("Other Division Member !");
  	</script><%
}

if(valid.equals("usernotfound"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("Member Not Found");
	 
	 </script>
	 
<%
}

}
}

%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 


<script language="javascript">


function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}


function user()   
{         
var ab=document.Payment.user_no.value;

if(ab=="")
{
alert("Insufficient Data");

}else{

document.Payment.flag.value="user";
document.Payment.submit();

}

}

function overallrpt()
{
    document.Payment.flag.value="overallfinerpt";
    document.Payment.submit();
}

function overallExcel()
{
    document.Payment.flag.value="ExcelReport";
    document.Payment.submit();
}


function ShowReport()
{
document.Payment.flag.value="PaidReport";
document.Payment.submit();
}

function load(){
	document.Payment.user_no.focus();
	 
		 }	
		 
	

</script>
