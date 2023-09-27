<html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" 
 import="java.sql.*"
 import="java.util.*" 
 import="Lib.Auto.Transfer_Books.BookTransferBean" 
 import="Lib.Auto.Binding.BindingBean"
  import="Common.Security"
   import="Lib.Auto.Binding_Books.BookBindingBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

 <jsp:useBean id="BeanObject" scope="request" class="Lib.Auto.Transfer_Books.BookTransferBean"
   type="Lib.Auto.Transfer_Books.BookTransferBean"> 

</jsp:useBean>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Transfer&nbsp;Books</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg"> <!--onload="load()"-->


<form method="POST" name="Transfer_Report" action="../Transfer_Report/Transfer_ReportServlet">
 <br><br><br>								        

<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("binding");
%>
<h2 >Transfer&nbsp;/&nbsp;Re-transfer Reports</h2>

<table align="center" class="contentTable" width="40%">

<tr><td>&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>

<tr><td><table align="center" width="80%">


<tr>
<td Class="addedit">From&nbsp;Date</td>

<td><input name=fromdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">

<SCRIPT language=javascript>
if (!document.layers)
 {
document.write("<input type=button onclick='popUpCalendar(this,Transfer_Report.fromdate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
 	 }
	</SCRIPT>						
 		 </td>

		 <td Class="addedit">To&nbsp;Date</td>
        <td><INPUT name=todate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Transfer_Report.todate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td></tr>
				
	
	<tr><td Class="addedit">Doc.type</td>
    
    <td><select name="doc" size="1" style="width:115px">
      <option  value="ALL">ALL</option>
      <option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>          
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>          
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
	  </select>
	</td>
	
	<td Class="addedit">Status</td>
	
	<td><select name="status" size="1" style="width:115px">

      <option value="TRANSFERED">TRANSFERED</option>
      <option value="Re-TRANSFERED">Re-TRANSFERED</option>
      </select>
	</td>
	</tr>
	
	
	
	<tr> 
	
	<td Class="addedit">DeptName</td>
      <td colspan=3><SELECT SIZE="1" NAME="dept" style="width:325px">


<OPTION SELECTED VALUE="ALL">ALL</OPTION>
<% 
Iterator it=sc.iterator();
 while(it.hasNext()){
    BindingBean view=(BindingBean) it.next();                                        	
	 int dept_code=view.getCode();
		     String dept_name=view.getName();
		
		     out.print("<option  value="+dept_code+">" +dept_name+"</option>");
		    
		    
     }
  %>
</SELECT>
              </td>
             </tr>
	
	
	</table>
	
	<p align="center">
	
	      <input type="button" value="Print" name="New" Class="submitButton" onclick="Print()">
	     <!--  <input type="button" value="Statistics" name="New" Class="submitButton" onclick="Statistics()"> -->
    	  <input type="button" value="Clear" Class="submitButton" name="clear" onclick="ClearRec()">
	
			<input type="hidden" name="flag">
 			 <input type="hidden" name="index">
 			 </p>
	</td></tr>
	
	<tr><td>&nbsp;</td></tr>
	</table>
	
	
	
	</form></body>
	
<script language="JavaScript">
	function ClearRec() {
		
		
		document.Transfer_Report.flag.value = "load";
		document.Transfer_Report.submit();
	}

	function Print() {
		document.Transfer_Report.flag.value = "print";
		document.Transfer_Report.submit();

	}
	function Statistics() {
		document.Transfer_Report.flag.value = "statistics";
		document.Transfer_Report.submit();

	}
	
</script>
</html>
