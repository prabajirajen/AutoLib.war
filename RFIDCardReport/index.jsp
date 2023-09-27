
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

	

<form name="RFIDCardReport" method="Post" action=./RFIDCardReportServlet>

<br><br><br>

<h2>RFIDCardReport</h2>
  <div align="center">
    
  <table align="center" class="contentTable" width="55%">
  <tr><td>
  
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

      <tr>
      <td  Class="addedit">Member_code From</td><td> <input type="text" name="From_Mcode" size="17" maxlength=10></td>
      <td  Class="addedit">Member_code To</td><td> <input  type="text" name="To_Mcode" size="17" maxlength=10></td>
      </tr>
      
      <tr>
        
        
      </tr>
      
      
      
      <tr>
      <td></td>
      
      
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

// function Print_ExcelReport()
// {
	

// if(document.RFIDCardReport.acc.checked==true)
// {
// 	var firstNum = document.RFIDCardReport.From_Mcode.value;
// 	firstNum = firstNum.replace(/[^0-9]+/ig,"");
// 	//alert(firstNum);//number
// 	var firstStr = document.RFIDCardReport.From_Mcode.value;
// 	firstStr = firstStr.replace(/[0-9]+/ig,"");
// 	//alert(firstStr);//string value
// 	var secondNum = document.RFIDCardReport.To_Mcode.value;
// 	secondNum = secondNum.replace(/[^0-9]+/ig,"");
// 	//alert(secondNum);//number
// 	var secondStr = document.RFIDCardReport.To_Mcode.value;
// 	secondStr = secondStr.replace(/[0-9]+/ig,"");
// 	//alert(secondStr);//string value
	
//   if(document.RFIDCardReport.From_Mcode.value=="" || document.RFIDCardReport.To_Mcode.value=="" || firstNum=="" || secondNum=="")
//   {
//      alert("Insufficient Data !");
//   }else if(firstStr!=secondStr){
// 		alert("Enter correct Range of AccessNo !");
		
//   }
//   else
//   {
// 	  document.RFIDCardReport.firstStr.value=firstStr;
// 		document.RFIDCardReport.secondStr.value=secondStr;
// 	 	document.RFIDCardReport.firstNum.value=firstNum;
// 	 	document.RFIDCardReport.secondNum.value=secondNum;
	 	
	 	
//     document.RFIDCardReport.flagExcel.value="ExcelReport";
//     document.RFIDCardReport.flag.value="AccessNo_Wise";
//     document.RFIDCardReport.submit();
//   }
// }
// else
// {
//    alert("Choose by AccessNo wise !");
// }

// }



function Print_Report()
{
	

	var firstNum = document.RFIDCardReport.From_Mcode.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.RFIDCardReport.From_Mcode.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.RFIDCardReport.To_Mcode.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.RFIDCardReport.To_Mcode.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value

	


if(firstStr!=secondStr){
	alert("Enter correct Range of AccessNo !");
	
}
else
{
	document.RFIDCardReport.firstStr.value=firstStr;
	document.RFIDCardReport.secondStr.value=secondStr;
 	document.RFIDCardReport.firstNum.value=firstNum;
 	document.RFIDCardReport.secondNum.value=secondNum;
 	
document.RFIDCardReport.flagExcel.value="PdfReport";
document.RFIDCardReport.flag.value="AccessNo_Wise";
document.RFIDCardReport.submit();
}


}


function chk(){

		  if(document.RFIDCardReport.From_Mcode.value=="")
		  {
		    alert("Insufficent Data");
			document.RFIDCardReport.From_Mcode.focus();
			return false;
			}
		    else if (document.RFIDCardReport.To_Mcode.value=="")
		    {
			document.RFIDCardReport.To_Mcode.focus();
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
