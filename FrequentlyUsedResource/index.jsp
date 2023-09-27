<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.sql.*" import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.FreqUsedResource.FreqUsedBean"/>
<html>
<head>
<title>Auto Lib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg">

<form name="freqused" action="./FreqUsedResource">
<br><br><br>
<h2>Frequently Used Resource</h2>

  <table align="center" class="contentTable" width="45%">
  <tr>
<td>
<table align="center" width="60%">


<tr><td> &nbsp; </td></tr>


  <tr>
    <td Class="addedit"><input type="radio" value="frequency"  name="r1" onclick="change_resource()">Frequently  Accessed Resource</td>
    <td Class="addedit"><input type="radio" name="r1" value="unused" onclick="change_resource()">Unused Resource</td>
   </tr>
   <tr><td>&nbsp;&nbsp;</td></tr>
  </table>
  <table align="center">
  
  <tr>
    <td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="r1" value="access" onclick="change_access()">Acc.No</td>
    <td><input type="text" name="txtaccess" size=16 disabled></td>
    
    <td Class="addedit">Doc.Type</td><td>
    <SELECT SIZE="1" NAME="txtdoctype" style="width:120px">

				<OPTION VALUE="BOOK">BOOK</OPTION>
				<OPTION VALUE="BOOK BANK">BOOK BANK</OPTION>
                <option value="NON BOOK">NON BOOK</option>		
                <option value="REPORT">REPORTS</option>		
                <OPTION VALUE="THESIS">THESIS</OPTION>
		        <option value="STANDARD">STANDARD</option>
                <option value="PROCEEDING">PROCEEDING</option>		                
                <option value="BACK VOLUME">BACK VOLUME</option>
                <option value="ALL" selected>ALL</option>
			    </SELECT>
</td></tr>


  <tr>

     <TD Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Date From</td><td><INPUT name=fromdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,freqused.fromdate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>

	<td Class="addedit">Date To</td><td><INPUT name=todate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,freqused.todate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
  </tr>
  
  <tr>
  <td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Limit From</td><td><input type="text" name="recfrom" size="16" maxlength="5" value="1"></td>
  <td Class="addedit">&nbsp;&nbsp;Limit To</td><td><input type="text" name="recto" size="16" maxlength="5" value="20"></td>
  </tr>
  
  
  
  <tr>
    <td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="r1" value="title" onclick="change_title()">Title</td>
    <td colspan=4><input type="text" name="txttitle" size="45" disabled></td>
  </tr>
  <tr><td>&nbsp;</td></tr>
  
  </table></td></tr></table>

<p align="center">
	<input type="button" Class="submitButton"  value="Print" name="print" onclick="Show_report()">
    <input type="reset" value="Clear" name="clear" Class="submitButton"  onclick="change_resource()">
</p>
</form></body></html>
 <%
			
				java.sql.ResultSet rs_Dept=bean.getDept_al();
			                   %>


<script language="JavaScript">
function Show_report()
{
if((document.freqused.r1[0].checked==true) || (document.freqused.r1[1].checked==true) || (document.freqused.r1[2].checked==true) || (document.freqused.r1[3].checked==true))
{
 document.freqused.submit();
}
else
{
alert("Select any one Choice !");
}
}

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function rec_code_val() {
if(isNaN(document.freqused.recfrom.value) || isNaN(document.freqused.recto.value) || document.freqused.recfrom.value=='' || document.freqused.recto.value=='') {
document.freqused.action="index.jsp";
  }
}
function change_access(){
  txt_clear();
 document.freqused.txtaccess.disabled=false;
 document.freqused.txttitle.disabled=true;
}
function change_title(){
 txt_clear();
 document.freqused.txtaccess.disabled=true;
 document.freqused.txttitle.disabled=false;
}
function change_dept(){
 txt_clear();
 document.freqused.txtaccess.disabled=true;
 document.freqused.txttitle.disabled=true;
}
function change_resource(){
  txt_clear();
 document.freqused.txtaccess.disabled=true;
 document.freqused.txttitle.disabled=true;
}
function txt_clear(){
 document.freqused.txtaccess.value="";
 document.freqused.txttitle.value="";
}

</script>

