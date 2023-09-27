<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}
%>


<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*,java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security" import="java.util.*" import="Lib.Auto.Branch.BranchBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<html>
<head>
<title>AutoLib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	String Message=null;

%>

</head>
<form name=frequentUser method="post" action=./frequentUserServlet>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.MemberTransfer.MemberTransRefBean" />
<br>
<br>
<h2>Frequent / UnUsed User</h2>

<table align="center" class="contentTable"  width="50%">
<tr>
<td>

<table align="center" width="70%">


<tr><td>&nbsp;</td></tr>



<tr>

<td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Member&nbsp;Code</td><td>
<input type=text name=Code size=16 maxlength=15></td>


 <td Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Year</td><td><select size="1" name="year"  style="width: 120px">
        <option selected value="ALL">ALL</option>
        <option value="1">1 Year</option>
        <option value="2">2 Year</option>
        <option value="3">3 Year</option>
        <option value="4">4 Year</option>
        
        </select></td>


</tr>



   <tr>
    
        <td  Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;FromDt.</td><td>
	<INPUT name=fromdt size=10  onfocus=this.blur(); value="<%=dateString%>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,frequentUser.fromdt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
				
        <td  Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ToDt.</td><td>
	<INPUT name=todt size=11  onfocus=this.blur(); value="<%=dateString%>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,frequentUser.todt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
      </tr>




   <tr><td>&nbsp;</td></tr>
<tr>
<td colspan=2 Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Designation List<select size="15" name="desig"  multiple style="width: 280px">
<option value="ALL">ALL</option>
<c:if test="${DesigSearchList ne null }" >
<c:forEach items="${DesigSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>
</td>


<td colspan=2 Class="addedit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Branch List<select size="15" name="branch" multiple style="width: 280px">
<option value="ALL">ALL</option>
<c:if test="${distinctBranchSearchList ne null }" >
<c:forEach items="${distinctBranchSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select>
</td>
</tr>







</tr>


<tr><td>&nbsp;</td></tr>

</table></td></tr></table>

<p align="center">
<input type="button" Class="submitButton" value="Frequent User" name="FrequentUser" onclick="frequentUser1()">
<input type="button" Class="submitButton" value="UnUsed User" name="UnUsedUser" onclick="unUsedUser()">
<input type="hidden" name="flag">
</p>

</form>
<br><br><br>

<script language="JavaScript">

function frequentUser1(){
	
document.frequentUser.flag.value="frequentUser";
	
	document.frequentUser.submit();
}

function unUsedUser(){
	
	document.frequentUser.flag.value="unUsedUser";
	
	document.frequentUser.submit();
}
</script>

