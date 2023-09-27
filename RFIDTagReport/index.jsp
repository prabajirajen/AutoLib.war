
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
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
<body background="/AutoLib/soft.jpg">
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	String Message=null;

%>

	

<form name="RFIDTagReport" method="Post" action=./RFIDTagReportServlet>

<br><br><br>

<h2>RFIDTagReport</h2>
  <div align="center">
    
  <table align="center" class="contentTable" width="55%">
  <tr><td>
  
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

      <tr>
      <td  Class="addedit"> <input type=radio value=byaccno checked name=acc>By&nbsp;Acc.&nbsp;No.</td>
      <td  Class="addedit">From</td><td> <input type="text" name="From_Accno" size="17" maxlength=10>
      <B>To</B> <input  type="text" name="To_Accno" size="17" maxlength=10></td>
      </tr>
      
      <tr>
        
        
      </tr>
      
      
      
      <tr>
      <td></td>
      
      <td  Class="addedit">Document</td>
      <td><select name="Type" size="1" style="width: 125px">
  	  <option selected value="ALL">ALL</option>
      <option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
 </select></td>
 
 </tr>
 <tr>
	<td></td><td></td>
	<td>
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>
 
      
      <tr>


        <td colspan="5">
        <p align="center">
		  <input type="button" Class="submitButton" value="Print" name="Access_Print" onclick="Print_Report()">
		  <input type="reset" Class="submitButton" value="Clear" name="Access_clear" onClick="refreshPage()">
<%-- 		  <a href="#" onclick="Print_ExcelReport()"> <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a> --%>
		  
		  <input type="hidden" name="flag">
		  <input type="hidden" name="flagExcel">		  
		  <input type="hidden" name="flagNo">
		  <input type="hidden" name="flagNotNumber">
		  
		  <input type="hidden" name="firstStr">
		  <input type="hidden" name="secondStr">
		  <input type="hidden" name="firstNum">
		  <input type="hidden" name="secondNum">
		  
		  
	
		  </p></td></tr></table></td></tr></table></div>


<%
String valid=request.getParameter("check");
if(valid!=null){

if(valid.equals("date")){
	
%>
            <script >
	alert("From date grater then or  equal to To date");
	</script>
			<%
			}

if(valid.equals("NoData")){	%>
  <script >
	alert("No Record Found");
	</script>

<%}


if(valid.equals("RecordNot")){
%>
            <script >
	alert("Record Not Found");
	history.back();
	</script>
			<%
			}
			if(valid.equals("NotValidRange")){
%>
            <script >
	alert("Not A Valid Range");
	history.back();
	</script>
			<%
			}
}%>

<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function Print_ExcelReport()
{
	

if(document.RFIDTagReport.acc.checked==true)
{
	var firstNum = document.RFIDTagReport.From_Accno.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.RFIDTagReport.From_Accno.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.RFIDTagReport.To_Accno.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.RFIDTagReport.To_Accno.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value
	
  if(document.RFIDTagReport.From_Accno.value=="" || document.RFIDTagReport.To_Accno.value=="" || firstNum=="" || secondNum=="")
  {
     alert("Insufficient Data !");
  }else if(firstStr!=secondStr){
		alert("Enter correct Range of AccessNo !");
		
  }
  else
  {
	  document.RFIDTagReport.firstStr.value=firstStr;
		document.RFIDTagReport.secondStr.value=secondStr;
	 	document.RFIDTagReport.firstNum.value=firstNum;
	 	document.RFIDTagReport.secondNum.value=secondNum;
	 	
    document.RFIDTagReport.flagExcel.value="pdf";
    document.RFIDTagReport.flag.value="AccessNo_Wise";
    document.RFIDTagReport.submit();
  }
}
else
{
   alert("Choose by AccessNo wise !");
}

}



function Print_Report()
{
	
	

if(document.RFIDTagReport.acc.checked==true)
{
	var firstNum = document.RFIDTagReport.From_Accno.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.RFIDTagReport.From_Accno.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.RFIDTagReport.To_Accno.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.RFIDTagReport.To_Accno.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value

	
if(document.RFIDTagReport.From_Accno.value=="" || document.RFIDTagReport.To_Accno.value=="" || firstNum=="" || secondNum=="")
{
alert("Insufficient Data !");
}

else if(firstStr!=secondStr){
	alert("Enter correct Range of AccessNo !");
	
}
else
{
	document.RFIDTagReport.firstStr.value=firstStr;
	document.RFIDTagReport.secondStr.value=secondStr;
 	document.RFIDTagReport.firstNum.value=firstNum;
 	document.RFIDTagReport.secondNum.value=secondNum;
 	
document.RFIDTagReport.flagExcel.value=document.RFIDTagReport.printType.value;
document.RFIDTagReport.flag.value="AccessNo_Wise";
document.RFIDTagReport.submit();
}
}

}


function chk(){

		  if(document.RFIDTagReport.From_Accno.value=="")
		  {
		    alert("Insufficent Data");
			document.RFIDTagReport.From_Accno.focus();
			return false;
			}
		    else if (document.RFIDTagReport.To_Accno.value=="")
		    {
			document.RFIDTagReport.To_Accno.focus();
			alert("Insufficent Data");
			return false;
		    }

         else{
		return true;
		}
}



</script>


</form>
</body>
</html>
