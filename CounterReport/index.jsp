<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Report.reportbean"/>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="Common.Security"%>
<%@ page import="Lib.Auto.Branch.BranchBean"%>

<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body>
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
%>


<FORM METHOD="get" ACTION="./CounterReportsAll" NAME="query">

<p align="center">
<br>
<h2>COUNTER REPORTS</h2>
</p>



    <div align="center">

  <table align="center" class="contentTable" width="55%">
 <tr>
<td>
<table align="center" width="100%">
<tr><td> &nbsp; </td></tr>

         
         
         <tr>
         
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Issue" NAME="reporttype" checked>Issue</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Return" NAME="reporttype">&nbsp;Return</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Renewal" NAME="reporttype">Renewal</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Reserve" NAME="reporttype">&nbsp;Reserve</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Resreminder" NAME="reporttype">Res.Reminder&nbsp;</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Duereminder" NAME="reporttype">Due Reminder</td>
            <td Class="addedit"><INPUT TYPE="radio" VALUE="Fine" NAME="reporttype">&nbsp;Fine</td>
          </tr>
          <tr><td> &nbsp; </td></tr>
          <tr><td colspan=14>--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------</td></tr>
       </table>
       <br>
       
       <table align="center">
  
          <tr>
            <td Class="addedit">Member Code</td>
            <td><INPUT TYPE="text" NAME="txtmembercode" SIZE="16"></td>
            <td Class="addedit">DocType</td>
            <td><SELECT SIZE="1" NAME="txtdoctype" style="width: 120px">

				<option value="ALL" selected>ALL</option>
				<OPTION VALUE="BOOK">BOOK</OPTION>
                <OPTION VALUE="THESIS">THESIS</OPTION>
                <option value="BOOK BANK">BOOK BANK</option>
                <option value="BACK VOLUME">BACK VOLUME</option>
                <option value="PROCEEDING">PROCEEDING</option>
                <option value="REPORT">REPORTS</option>
                <option value="NON BOOK">NON BOOK</option>
		        <option value="STANDARD">STANDARD</option>
                </SELECT></td>
          </tr>
          <tr>
            <td Class="addedit">Access No</td>

            <td Class="addedit">
            <INPUT TYPE="text" NAME="txtaccessno" SIZE="16">
              </td>
            
          <tr>
            <td Class="addedit">Date From</td>
<td><INPUT name=txtfdate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,query.txtfdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>

            <td Class="addedit">Date To</td>
<td><INPUT name=txttdate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,query.txttdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
          </tr>
          <tr>
  <td Class="addedit">Group</td>
    <td colspan=3><input type=text name=Gname size=40 readonly>
	<input type=button name=Find_Group Class="submitButton" Value="Find" onclick="FindValue('Group')"></td>
	</tr>
	<tr>
   <td Class="addedit">Department</td>
    <td colspan=50>  <input type=text name=Dname size=40 readonly>&nbsp;<input type=button name=Find_Member Class="submitButton" Value="Find" onclick=FindValue('Department')>
	   </td>	
          </tr>          
            <tr>
            <td Class="addedit">Mem.Code&nbsp;From&nbsp;</td><td><input type=text name=mcodefrom SIZE="16">
            </td>
            <td Class="addedit">&nbsp;&nbsp;To</td><td><input type=text name=mcodeto SIZE="16">
            </td>
            </tr>
            <tr>
            <c:if test="${BranchList ne null }">
								<td Class="addedit">Campus</td>
								<td><SELECT size="1" name="txtBranch"
									style="width: 202px">
										<c:forEach items="${BranchList}" var="std" varStatus="loop">

											<c:choose>
												<c:when test="${std.code eq UserBranchID}">
													<option value="<c:out value="${std.code}" />" selected><c:out
															value="${std.name}" /></option>
												</c:when>
												
											</c:choose>

										</c:forEach>
								</SELECT></td>
							</c:if>
            </tr>
            
       </table><br>
       
       
       
       <table align="center">
          <TR>
            <TD Class="addedit">Order By</TD>
            <TD>
               <SELECT SIZE="1" NAME="order1" ONCHANGE="order11()">
               <OPTION VALUE="NO">--------------</OPTION>
		       <OPTION VALUE="Access_No">Accessno</OPTION>
               <OPTION VALUE="Member_Code">Userid</OPTION>
               <OPTION VALUE="Issue_Date">Issue Date</OPTION>
               <OPTION VALUE="Due_Date">Due Date</OPTION>
               </SELECT>
            </TD>
            <TD>
      	       <SELECT SIZE="1" NAME="order2" ONCHANGE="order22()">
               <OPTION VALUE="NO">--------------</OPTION>
               <OPTION VALUE="Access_No">Accessno</OPTION>
               <OPTION VALUE="Member_Code">Userid</OPTION>
               <OPTION VALUE="Issue_Date">Issue Date</OPTION>
               <OPTION VALUE="Due_Date">Due Date</OPTION>
               </SELECT>
            </TD>
            <TD>
                <SELECT SIZE="1" NAME="order3" ONCHANGE="order33()">
                <OPTION VALUE="NO">--------------</OPTION>
                <OPTION VALUE="Access_No">Accessno</OPTION>
                <OPTION VALUE="Member_Code">Userid</OPTION>
                <OPTION VALUE="Issue_Date">Issue Date</OPTION>
                <OPTION VALUE="Due_Date">Due Date</OPTION></SELECT>
            </TD>
             <TD Class="addedit">Report Type</TD>
            <TD>
              	<SELECT SIZE="1" NAME="report_type" id="rpttpe">
                <OPTION VALUE="listing" SELECTED>Listing</OPTION>
                <OPTION VALUE="breakup">Breakup</OPTION>
                <OPTION VALUE="cumulative">Cumulative</OPTION>
                </SELECT>
            </TD>
		</TR>

        </TABLE><br>
       <p align="center">
       
    <INPUT TYPE="button" Class="submitButton" VALUE="Report" NAME="B1" onclick="Print_Report()">&nbsp;
    <INPUT TYPE="button" Class="submitButton" VALUE="Chart" NAME="B1" onclick="Print_Chart()">
 	<INPUT TYPE="reset" Class="submitButton" VALUE="Clear" NAME="B2" >&nbsp;&nbsp;

    <a href="#" onclick="Print_ExcelReport()" >
		<img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="45" border="0" title="Print Excel"> 
	</a>
    <input type="hidden" name="flagExcel">
    <input type="hidden" name="gettodayreport">
    <input type="hidden" name="curIssue" value="NO">	
    <input type="hidden" name="flag1"> 
    <input type="hidden" name="flag"> 	
	</p>
	
       
       </td></tr></table></div></FORM></body></html>

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

function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}

function Print_ExcelReport()
{

var radios = document.getElementsByName('reporttype');
window.rdValue = null;

for (var i=0; i<radios.length; i++) {
                var aRadio = radios[i];
                if (aRadio.checked) {
                    var foundCheckedRadio = aRadio;
                    rdValue = foundCheckedRadio.value;
                    break;
                }
                else rdValue = 'noRadioChecked';
            }             

 if(rdValue=='Issue' || rdValue=='Return' || rdValue=='Renewal' || rdValue=='Reserve'|| rdValue=='Duereminder' || rdValue=='Fine')
 {
	 if(document.getElementById('rpttpe').value=="breakup")
		 {
		 	
		 		document.query.flagExcel.value="ExcelReport";
			    document.query.submit();
		 		
		 }
	 else
		 {
		 alert("Choose only Breakup");
		 }
    
 }else {
    alert('Select only Issue / Return / Renewal/ Reserve/ Duereminder'); 
 }
 
}

function Print_Report()
{
    document.query.flagExcel.value="PdfReport";
    document.query.gettodayreport.value="nothing";
    document.query.submit();
}

function Print_Chart()
{
	var charttype=document.query.reporttype.value;
	if(charttype=='Issue' || charttype=='Return')
	{
		document.query.flagExcel.value="chart";
	    document.query.gettodayreport.value="nothing";
	    document.query.submit();
	}
	else
		alert("Choose Issue/Return only");
}
</script>


