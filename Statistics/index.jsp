<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"  import="Common.Security" %>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:sreport
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<%
  String check_date=request.getParameter("check");
  if(check_date!=null)
  {
  if(check_date.equals("date"))
  {
  %>
  <script language="javascript">
  alert("From date is greater than or equal to To date!...");
  </script>
  <%
  }
  check_date=null;
  }
  %>
<html>
<head>
<title>Auto Lib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" >



<form name="sreport" method="get" action=./StatisticsServlet>
    <br><br><br>
<h2>Statistics Report</h2>


<table align="center" class="contentTable" width="700">
<tr><td align="center">

<table align="center" >
<tr><td> &nbsp; </td></tr>


      <tr>
         <td Class="addedit">Department</td>
        <td><input type="text" name="txtdepartment" readonly="true" size="50">
		<input type="button" Class="submitButton" value="Find" name="Find_Dept" onclick="FindValue('Dept')"></td>
      </tr>
      
      <tr>
        <td Class="addedit">Subject</td>
        
        <td><input type="text" name="txtsubject" readonly="true" size="50">
		<input type="button" Class="submitButton" value="Find" name="Find_Sub" onclick="FindValue('Sub')"></td>
      </tr>
      <tr>
      
      
      

      <tr>
        <td Class="addedit">Publisher</td>
        <td><input type="text" name="txtpublisher" readonly="true"  size="50">
		<input type="button" Class="submitButton" value="Find" name="Find_Pub" onclick="FindValue('Pub')"></td>
      </tr>

<tr>
   <td Class="addedit">Supplier</td>
        <td> <input type="text" name="txtsupplier" readonly="true" size="50">
		<input type="button" Class="submitButton" value="Find" name="Find_Sup"  onclick="FindValue('Sup')"></td>
</tr>


				

<tr>
 <td  Class="addedit"><input type="radio" value="V1" checked name="R1" onclick=change()>Recd.Date</td>
<td Class="addedit">Dt.From&nbsp;&nbsp;&nbsp;&nbsp;<INPUT name=recfrom size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,sreport.recfrom, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>

&nbsp;&nbsp;&nbsp;&nbsp;Dt.To&nbsp;&nbsp;&nbsp;&nbsp;<INPUT name=recto size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,sreport.recto, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
				</tr>
				
				
				
				<tr>
				 
				
        <td Class="addedit"><input type="radio"  value="V2" name="R1" onclick=changes()>Acc.No</td>
        <td Class="addedit">From&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="txtfromacc" size="16" maxlength="10">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="txttoacc" size="16" maxlength="10" ></td>
			 	</tr>
				
					
				
<tr>
				 
				
        <td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Doc. Price</td>
        <td Class="addedit">From&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="fromPrice" size="16" maxlength="10" onkeyup="chekFromPrice()">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="toPrice" size="16" maxlength="10" onkeyup="chekToPrice()"></td>
			 	</tr>
				
			
<tr>
<td></td><td Class="addedit">Doc.&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select size="1" name="doctype" style="width: 118px">
            	<option value="ALL" selected>ALL</option>
            	<OPTION VALUE="BOOK">BOOK</OPTION>
                <option value="BOOK BANK">BOOK BANK</option>
                <option value="NON BOOK">NON BOOK</option>
                <option value="REPORT">REPORTS</option>            	
                <OPTION VALUE="THESIS">THESIS</OPTION>
           		<option value="STANDARD">STANDARD</option>                
                <option value="PROCEEDING">PROCEEDING</option>                
                <option value="BACK VOLUME">BACK VOLUME</option>
                
</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Type&nbsp;&nbsp;&nbsp;&nbsp;<select size="1" name="reporttype"  style="width: 118px">
            <option VALUE="1">Department</option>
            <option VALUE="2">Subject</option>
            <option VALUE="3">Publisher</option>
            <option VALUE="4">Supplier</option>
          </select>




</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
	<td></td>
	<td>
	<input type="radio" name="printType" value="pdf" checked>PDF
	<img src="<%= request.getContextPath() %>/iconImages/pdf.png" width="40" height="45" border="0" title="Print PDF">
	
	<input type="radio" name="printType" value="excel">Excel
	 <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
	</td>
	</tr>			
				
				
				
				
				
				
</table></td></tr>
<tr><td>&nbsp;</td></tr>
</table>
      
      <p align="center">
		<input type="button" Class="submitButton" value="Statistics" name="Statistics" onclick="check_status('statis')">
		<input type="button" Class="submitButton" value="Details" name="Details" onclick="check_status('details')">
		<input type="Reset" Class="submitButton" value="Clear"  onclick="clear_text()">
		</p>
		  <input type="hidden" name="subno">
		  <input type="hidden" name="supno">
		  <input type="hidden" name="deptno">
		  <input type="hidden" name="pubno">
		  <input type="hidden" name="hid">
		  <input type="hidden" name="from_date">
		  <input type="hidden" name="to_date">
		  <input type="hidden" name="flagNo">
		  <input type="hidden" name="flagNotNumber">
		  
		   <input type="hidden" name="firstStr">
		  <input type="hidden" name="secondStr">
		  <input type="hidden" name="firstNum">
		  <input type="hidden" name="secondNum">
		 
<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("NoData")){	%>
<script >
	alert("No Record Found");
	</script>

<%}}}%>
<script language="JavaScript">
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function chekFromPrice(){
	
	if((isNaN(document.sreport.fromPrice.value))||(document.sreport.fromPrice.value == ' ')) {
		document.sreport.fromPrice.select();
		document.sreport.fromPrice.value="";
		}
	
}

function chekToPrice(){

	if((isNaN(document.sreport.toPrice.value))||(document.sreport.toPrice.value == ' ')) {
		document.sreport.toPrice.select();
		document.sreport.toPrice.value="";
		}
	
}




function check_status(val){
	
	if(document.sreport.R1[0].checked==true){
		//alert("you are choosing datewise");
		
		document.sreport.method="get";
		 	document.sreport.hid.value=val;
		 	document.sreport.submit();
		
		
		
	}else if(document.sreport.R1[1].checked==true){
		//alert("you are choosing accessNo wise");

	if(check()){
		
	
		
				var firstNum = document.sreport.txtfromacc.value;
				firstNum = firstNum.replace(/[^0-9]+/ig,"");
				//alert(firstNum);//number
				var firstStr = document.sreport.txtfromacc.value;
				firstStr = firstStr.replace(/[0-9]+/ig,"");
				//alert(firstStr);//string value
				var secondNum = document.sreport.txttoacc.value;
				secondNum = secondNum.replace(/[^0-9]+/ig,"");
				//alert(secondNum);//number
				var secondStr = document.sreport.txttoacc.value;
				secondStr = secondStr.replace(/[0-9]+/ig,"");
		 		//alert(secondStr);//string value
		
		 		if(firstStr!=secondStr){
		 					alert("Enter correct Range of AccessNo !");
		 					
		 				  }else{
		 					    document.sreport.firstStr.value=firstStr;
		 						document.sreport.secondStr.value=secondStr;
		 					 	document.sreport.firstNum.value=firstNum;
		 					 	document.sreport.secondNum.value=secondNum;

		 					 	document.sreport.method="get";
		 					 	document.sreport.hid.value=val;
		 					 	document.sreport.submit();
		 					  
		 		 		  }	
		
	}
	
	}
	
}




function check()
 {
if(document.sreport.R1[1].checked==true)
{
if(document.sreport.txtfromacc.value=="" || document.sreport.txttoacc.value=="")

	return false;
	else
		return true;

}
else
{
return true;
}
}

function change(){
document.sreport.R1[1].checked=false;
document.sreport.txtfromacc.disabled=true;
document.sreport.txttoacc.disabled=true;
document.sreport.recfrom.disabled=false;
document.sreport.recto.disabled=false;
document.sreport.txtfromacc.value="";
document.sreport.txttoacc.value="";

}
function changes(){
document.sreport.R1[0].checked=false;
document.sreport.recfrom.readonly=true;
document.sreport.recto.readonly=true;
document.sreport.txtfromacc.disabled=false;
document.sreport.txttoacc.disabled=false;

}
function clear_text()
{
document.sreport.txtdepartment.value="";
document.sreport.txtpublisher.value="";
document.sreport.txtsupplier.value="";
document.sreport.txtsubject.value="";
document.sreport.txtfromacc.value="";
document.sreport.txttoacc.value="";

}
function FindValue(val){
winpopup=window.open("search.jsp?flag="+val ,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}
</script>

</form>
</body>

</html>
