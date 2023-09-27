<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Course.CourseBean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Course
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
</head>

<body background="/AutoLib/soft.jpg" >

<!-- Style Sheet -->

<form name=MResource method="post" action=./MResourceServlet>
<br><br><br>

<h2>Missing&nbsp;Book&nbsp;Entry</h2>
<center>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>
<td Class="addedit">Access&nbsp;No
<td >
  <input type=text name=accno size=15 maxlength=15 >
</td>


    <td Class="addedit">&nbsp;&nbsp;Doc.Type&nbsp;&nbsp;</td >
    <td><select name="type" size="1">
      <option value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="BACK VOLUME">BACK VOLUME</option>
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="PROCEEDING">PROCEEDING</option>
	  <option value="NON BOOK">NON BOOK </option>
	  <option value="CLIPPING">CLIPPING</option>
     
</select></td>
</tr>
<tr>
    <td Class="addedit">Availability&nbsp;&nbsp;</td >
    <td><select name="availability" size="1">
      <option value="LOST">LOST</option>
      <option value="MISSING">MISSING</option>
      <option value="WITHDRAWN">WITHDRAWN</option>
      <option value="DAMAGED">DAMAGED</option>
     
</select></td>

<td  colspan="1" Class="addedit">
&nbsp;&nbsp;&nbsp;Date


<TD colspan="2">
	<INPUT name=mdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,MResource.mdate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
				
		 </td></tr>

<tr>
<tr><td colspan=4 align=center >

<center>

<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
&nbsp;
<input type=button name=Delete Class="submitButton" value=Return onclick=DeleteRec()>&nbsp;
<input type=Reset name=Clear Class="submitButton" Value=Clear >

</center>
<input type=hidden name=flag >
</td>
</tr>
</table>
</center>
</td></table></center>
</form>
</body>
</html>

<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->

<%
String valid=request.getParameter("check");
if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("SaveMResource")){
 %>
  <script language="JavaScript">
			 alert("Record Inserted Successfully!");
       			document.MResource.accno.focus();
				document.MResource.flag.value="none";
				document.MResource.accno.value="";
		     </script>		
			<%
			}
if(valid.equals("deleteMResource")){
	 %>
	  <script language="JavaScript">
				 alert("Record Deleted Successfully!");
	       			document.MResource.accno.focus();
					document.MResource.flag.value="none";
					document.MResource.accno.value="";
			     </script>		
				<%
				}
if(valid.equals("NotRightUser")){  // For titan
	  %>
	  	  <script language="JavaScript">
	  	  alert("Access denied !");			
	  	 </script>
	  <%
	  } 	    
if(valid.equals("NotFound")){
	  %>
	  	  <script language="JavaScript">
	  	  alert("Record Not Found !");			
	  	 </script>
	  <%
	  } 	    
if(valid.equals("UpdateCheck")){
	 %>
	  <script language="JavaScript">
				 alert("Record Already Exist !");
	       			document.MResource.accno.focus();
					document.MResource.flag.value="none";
					document.MResource.accno.value="";
					document.MResource.reset();
			     </script>		
				<%	
}
}
}

%>

<script language=javascript>

function SaveRec(){
             document.MResource.method="post";
             
         if(chk_mc()){
			  								
				    document.MResource.flag.value="save";
		         	document.MResource.submit();

					
					}
					else
	{
	alert("Insufficent Data");
	}
					}
					
function DeleteRec(){
    document.MResource.method="post";
    
if(chk_mc()){
	  								
		    document.MResource.flag.value="delete";
        	document.MResource.submit();

			
			}
			else
{
alert("Insufficent Data");
}
			}

function chk_mc(){
var flag_cs;
var c;
var mc=document.MResource.accno.value;
if(mc=="")
{
				document.MResource.accno.focus();
				document.MResource.flag.value="none";
				document.MResource.accno.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }
 
 
function load(){
	document.MResource.accno.focus();

		 }

</script>

