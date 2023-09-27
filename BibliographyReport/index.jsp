<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.text.SimpleDateFormat"  %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Bibliography Report</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>


<body background="/AutoLib/soft.jpg">

<form method="POST" action="./BiblioServlet" name=Biblio>
<br>
  <h2 >Bibliography&nbsp;Report</h2>
<table align="center" class="contentTable" width="55%">


<tr>
<td>
<!-- <table border="0" width="55%" cellspacing="0" cellpadding="5" align="center"> -->
  
   <table border="0" width="55%"  align="center">
   
   
  <tr><td>&nbsp;</td></tr>
  
    <tr><td Class="addedit">Select Type</td>
    <td><select size="1" name="report_type" style="width:120px" onchange='opt()'>
           <option value="NO">SELECT</option>
          <option value="ACCESSION">AccNo Wise</option>
          <option value="Aut">AuthorName Wise</option>
          <option value="CALLNO">Call Number wise</option>
          <option value="Dept">Department Wise</option>
          <option value="Pub">Publisher Wise</option>
          <option value="RECEIVEDDATE">Purchase Date</option>
          <option value="Sub">Subject Wise</option>
          <option value="Sup">Supplier Wise</option>
          <option value="Bud">Budget Wise</option></select></td>
      
      <td Class="addedit">Availability</td>
      <td><select size="1" name="status" style="width:110px">
          <option value="ALL">ALL</option>  
          <option value="YES">YES</option>
          <option value="REFERENCE">REFERENCE</option>
          <option value="ISSUED">ISSUED</option>
          <option vlue="BINDING">BINDING</option>
          <option value="MISSING">MISSING</option>
          <option value="LOST">LOST</option>
          <option value="WITHDRAWN">WITHDRAWN</option>
          <option value="TRANSFERED">TRANSFERED</option>
        </select></td>
        
        <td Class="addedit">PurchaseType</td>
      <td><select size="1" name="giftPurchase" style="width:110px">
          <option value="ALL">ALL</option>  
          <option value="Gift">Gift</option>
          <option value="Purchase">Purchase</option>
          <option value="Others">Others</option>
        </select></td>
		
		
		<td Class="addedit">Document</td>
	 <td><select size="1" name="doc_type" style="width:110px">
	  <option  value="">ALL</option>
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
<td Class="addedit">Option</td>
<td colspan=3><input type="text" name="option_name" size="40" value="ALL" readonly>
<input type="button" Class="submitButton" value="Find" name="option1" onclick=FindValue(document.Biblio.report_type.value)></td>


</tr>
    
    
    <tr>
    <td Class="addedit">FromaccNo</td><td><input type="text" name="From_Accno" size="15" maxlength=10 disabled></td>
    <td Class="addedit">ToaccNo</td><td><input  type="text" name="To_Accno" size="15" maxlength=10 disabled></td>
      </tr>
      
      
      <tr>
        <td Class="addedit">FromDate</td><td><INPUT name=fromdt size=9  onfocus=this.blur(); value="<%=dateString%>" disabled>
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Biblio.fromdt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
        <td Class="addedit">ToDate</td><td><INPUT name=todt size=9  onfocus=this.blur(); value="<%=dateString%>" disabled>
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Biblio.todt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
      </tr>
    
        </table>
        
<p align="center">

<input type="button" Class="submitButton" value="Print" name="print" onclick="PrintPdf()">
 <input type="reset" Class="submitButton" value="Clear" name="clear">
  <a href="#" onclick="PrintExcel()">
   <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
</p>
			<input type="hidden" name="firstStr">
		  <input type="hidden" name="secondStr">
		  <input type="hidden" name="firstNum">
		  <input type="hidden" name="secondNum">
		  <input type="hidden" name="printType">
		  
     
      
     </td></tr></table></form></body></html>      
   

<%
String valid=request.getParameter("check");
//out.print("valid  "+valid);
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("Notoption")){
 %>
<script language="JavaScript">
alert("Choose AnyOne Option !!");
</script><%

}
}

if(valid.equals("NoData")){	%>
<script >
	alert("No Record Found");
	</script>

<%}


}
%>
<script language="JavaScript">


function PrintPdf(){
	document.Biblio.printType.value="PdfReport";
	Show_Report()
	
}

function PrintExcel(){
	document.Biblio.printType.value="ExcelReport";
	Show_Report()
}




function Show_Report()
{
if(document.Biblio.report_type.value=="NO")
{
alert("Invalid Selection !");
}else if(document.Biblio.report_type.value=="ACCESSION"){
	
	
	var firstNum = document.Biblio.From_Accno.value;
	firstNum = firstNum.replace(/[^0-9]+/ig,"");
	//alert(firstNum);//number
	var firstStr = document.Biblio.From_Accno.value;
	firstStr = firstStr.replace(/[0-9]+/ig,"");
	//alert(firstStr);//string value
	var secondNum = document.Biblio.To_Accno.value;
	secondNum = secondNum.replace(/[^0-9]+/ig,"");
	//alert(secondNum);//number
	var secondStr = document.Biblio.To_Accno.value;
	secondStr = secondStr.replace(/[0-9]+/ig,"");
	//alert(secondStr);//string value
	
	
	if(document.Biblio.From_Accno.value=="" || document.Biblio.To_Accno.value=="" || firstNum=="" || secondNum=="")
	{
	alert("Insufficient Data !");
	}

	else if(firstStr!=secondStr){
		alert("Enter correct Range of AccessNo !");
		
	}else{
		document.Biblio.firstStr.value=firstStr;
		document.Biblio.secondStr.value=secondStr;
	 	document.Biblio.firstNum.value=firstNum;
	 	document.Biblio.secondNum.value=secondNum;
		
		document.Biblio.submit();
	}
	
}else{
document.Biblio.submit();
}
}






function opt()
{
document.Biblio.option_name.value="";
var str=document.Biblio.report_type.value;

if(str=="ACCESSION")
{
document.Biblio.From_Accno.disabled=false;
document.Biblio.To_Accno.disabled=false;
document.Biblio.fromdt.disabled=true;
document.Biblio.todt.disabled=true;
}
else if(str=="RECEIVEDDATE")
{
document.Biblio.From_Accno.disabled=true;
document.Biblio.To_Accno.disabled=true;
document.Biblio.fromdt.disabled=false;
document.Biblio.todt.disabled=false;
}
else if(str=="CALLNO")
{
document.Biblio.From_Accno.disabled=false;
document.Biblio.To_Accno.disabled=true;
document.Biblio.fromdt.disabled=true;
document.Biblio.todt.disabled=true;
}
else
{
document.Biblio.fromdt.disabled=true;
document.Biblio.todt.disabled=true;
document.Biblio.From_Accno.disabled=true;
document.Biblio.To_Accno.disabled=true;
}
}
function FindValue(val){
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
</script>
