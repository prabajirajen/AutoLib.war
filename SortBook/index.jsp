<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("2") || URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
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
<body background="/AutoLib/soft.jpg"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

%>


<!-- Style Sheet -->

<form name="Sort_Book" method="Post" action=./SaveSortBookServlet>
<br><br><br>

<br><br><br>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="25%">
<td>
<table align="center" width="25%">    
      <tr>
      <h2>Sort&nbsp;Book</h2><br>        
          <p align="center">
		  <input type="submit" Class="submitButton" value="Save" >
		  <input type="hidden" name="flag">		  		  
	   </td>
      </tr>   
    </table></table></table>
    </center>
  </div>

</form>
</body>
</html>

<%
String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("SaveSuccess"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("Sort Book Saved Successfully !");
	 
	 </script>
	 
<%
}

}
}

%>

<script language="javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

</script>


