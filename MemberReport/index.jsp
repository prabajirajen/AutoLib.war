<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Member Report</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg">
<form name=member_report method="POST" action=./MemberReportServlet>

<jsp:useBean id="bean" scope="request" class="Lib.Auto.MemberTransfer.MemberTransRefBean" />
<br><br><br>

<h2>Member&nbsp;Report</h2>

 <table align="center" class="contentTable" width="55%">
 <tr><td>&nbsp;</td></tr>
<tr>
<td>
<table width="70%" align="center">
  <tr>
  
              <tr>
            <td Class="addedit">Mem. Code&nbsp;</td><td>From&nbsp;<input type=text name=mcodefrom SIZE="16">
            
            &nbsp;&nbsp;To<input type=text name=mcodeto SIZE="16">
            </td>
            </tr>
            
            
    <tr>
      <td Class="addedit">User Name</td><td>
      <input type="text" name="name" value="ALL" size="52" readonly>
      
      <input type="button" Class="submitButton" value="Find" name="name_find" onclick="FindValue('name')"></td>
    </tr>
    
<tr>
<td Class="addedit">Designation</td>
<td><select size="1" name="desig" style="width: 380px">
<option value="ALL">ALL</option>
<c:if test="${DesigSearchList ne null }" >
<c:forEach items="${DesigSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>


<tr><td Class="addedit">Group&nbsp;Name</td>
<td><select size="1" name="group" style="width: 380px">
<option value="ALL">ALL</option>
<c:if test="${distinctBranchWiseGroupSearchList ne null }" >
<c:forEach items="${distinctBranchWiseGroupSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>





<tr>
<td Class="addedit">Course&nbsp;Name</td>
<td><select size="1" name="course" style="width: 380px">
<option value="ALL">ALL</option>
<c:if test="${distinctBranchWiseCourseSearchList ne null }" >
<c:forEach items="${distinctBranchWiseCourseSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /> - <c:out value="${std.desc}"/></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>

<tr><td Class="addedit">Campus</td>
<td><select size="1" name="branch" style="width: 380px">
<!-- <option value="ALL">ALL</option> -->
<c:if test="${distinctBranchSearchList ne null }" >
<c:forEach items="${distinctBranchSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>


    
    
 </table>
 
 
 <table width="70%" align="center">
 
 
    <tr>
   
    
    <td Class="addedit">Year</td><td><select size="1" name="year"  style="width: 70px">
        <option selected value="ALL">ALL</option>
        <option value="1">1 Year</option>
        <option value="2">2 Year</option>
        <option value="3">3 Year</option>
        <option value="4">4 Year</option>
        
        </select></td>
        
         <td Class="addedit">Gender</td><td><select size="1" name="gender" style="width: 70px">
        <option selected value="ALL">ALL</option>
        <option value="MALE">Male</option>
        <option value="FEMALE">Female</option>
        
        </select></td>
         
        
      <td Class="addedit"> Status</td><td><select size="1" name="status" style="width: 70px">
        <option selected value="ALL">ALL</option>
        <option value="ACTIVE">Active</option>
        <option value="IN ACTIVE">In Active</option>
        </select></td>
    
        <td Class="addedit">Lock</td><td><select size="1" name="lock" style="width: 70px">
        <option selected value="ALL">ALL</option>
        <option value="NO">NO</option>
        <option value="YES">YES</option>
        <option value="CLEARANCE">CLEARANCE</option>
        </select></td>
    </tr>
  </table>
 
  
  
   <table align="center">
          <TR>
            <TD Class="addedit">Order By</TD>
            <TD>
               <SELECT SIZE="1" NAME="order1">
               <OPTION VALUE="member_name">UserName</OPTION>
               <OPTION VALUE="dept_code">Dept.name</OPTION>
               <OPTION VALUE="sex">Gender</OPTION>
               <OPTION VALUE="enroll_date">Enroll_Date</OPTION>
               <OPTION VALUE="expiry_date">Expiry_Date</OPTION>
               <OPTION VALUE="desig_code">Desig</OPTION>
               <OPTION VALUE="group_code">Group</OPTION>
               <OPTION VALUE="slock">Lock</OPTION>
               <OPTION VALUE="course_year">Year</OPTION>
               </SELECT>
            </TD>
           <TD>
               <SELECT SIZE="1" NAME="order2">
               <OPTION VALUE="NO">--------------</OPTION>
		      
		       <OPTION VALUE="member_name">UserName</OPTION>
               <OPTION VALUE="dept_code">Dept.name</OPTION>
               <OPTION VALUE="enroll_date">Enroll_Date</OPTION>
               <OPTION VALUE="expiry_date">Expiry_Date</OPTION>
               <OPTION VALUE="sex">Gender</OPTION>
               <OPTION VALUE="desig_code">Desig</OPTION>
               <OPTION VALUE="group_code">Group</OPTION>
               <OPTION VALUE="slock">Lock</OPTION>
               <OPTION VALUE="course_year">Year</OPTION>
               </SELECT>
            </TD>
            <TD>
               <SELECT SIZE="1" NAME="order3">
               <OPTION VALUE="NO">--------------</OPTION>
		      
		       <OPTION VALUE="member_name">UserName</OPTION>
               <OPTION VALUE="dept_code">Dept.name</OPTION>
               <OPTION VALUE="enroll_date">Enroll_Date</OPTION>
               <OPTION VALUE="expiry_date">Expiry_Date</OPTION>
               <OPTION VALUE="sex">Gender</OPTION>
               <OPTION VALUE="desig_code">Desig</OPTION>
               <OPTION VALUE="group_code">Group</OPTION>
               <OPTION VALUE="slock">Lock</OPTION>
               <OPTION VALUE="course_year">Year</OPTION>
               </SELECT>
            </TD>
            
            <TD>
               <SELECT SIZE="1" NAME="sorting">
               <OPTION VALUE="Asc">Ascending</OPTION>
		       <OPTION VALUE="Desc">Descending</OPTION>
               </SELECT>
            </TD>
             
         
</TR></table></td></tr>


   <td>&nbsp;</td>
  
 
 

</table>
<p align="center">
<input type="hidden" name="flag">
  <input type="hidden" name="flag1">  
<input type="submit" Class="submitButton" value="Details" name="Details" onclick="Print_Details()">
<!--   <input type="submit" Class="submitButton" value="Statistics" name="Statistics" onclick="Print_Statistics()"> -->
  <input type="reset" Class="submitButton" value="Clear" name="clear">
<a href="#" onclick="PrintExcel()">
   <img src="<%= request.getContextPath() %>/images/xls.gif" width="35" height="40" border="0" title="Print Excel"></a>
</form></body>


<%
String valid=request.getParameter("check");
if(valid!=null){
	if(valid.equals("NoData")){	%>
	  <script >
		alert("No Record Found");
		document.member_report.flag.value="load";
		
		document.member_report.submit();
		</script>

	<%
	}
	
}
	%>
	




<script language="JavaScript">
function FindValue(val)
{

winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
function print_view(){
document.member_report.action="./MemberReportServlet";
 document.member_report.submit();
}
function count(){
var name1=document.member_report.name.value;
var dept1=document.member_report.dept.value;
var group1=document.member_report.group.value;
var course1=document.member_report.course.value;
var status1=document.member_report.status.value;
winpopup=window.open("countrep.jsp?name="+name1+"&dept="+dept1+"&group="+group1+"&course="+course1+"&status="+status1+"","popup","height=450,width=700,top=100,left=100,status=yes,menubar=yes,scrollbars=yes,toolbar=no");
}

function Print_Details(){
	document.member_report.flag.value="print";
	document.member_report.flag1.value="Details";
	document.member_report.submit();
}

function Print_Statistics(){
	document.member_report.flag.value="print";
	document.member_report.flag1.value="Statistics";
	document.member_report.submit();
}
function PrintExcel()
{
	document.member_report.flag.value="print";
	document.member_report.flag1.value="Excel";
	document.member_report.submit();
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
</html>
