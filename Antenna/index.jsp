<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}

%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Counter.CounterBean" type="Lib.Auto.Counter.CounterBean">
</jsp:useBean>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<%
String flag="",details="";
%>
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" onload="load()"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	
%>


<!-- Style Sheet -->

<!-- Style Sheet -->
<form name="antennastart" method="Post" action=./CounterServlet>
<br><br><br>
<h2><b>RFID&nbsp;Connection</b></h2>
<%
flag=request.getParameter("flag");
details=request.getParameter("details");
%>
<br>
  <div align="center">
    <center>
    <table align="center" class="contentTable" width="25%">
<td>
 <table align="center" width="25%"> 
 <br>
    <tr>
    <td><input type="radio" name="antradio" value="counter"></td><td><font size="5">Counter&nbsp;Antenna</font></td> 
    </tr>
    <tr>
    <td><input type="radio" name="antradio" value="gate"></td><td><font size="5">Gate&nbsp;Antenna</font></td>
    </tr>
    <tr></tr>
    <tr>
    <td></td><td><input type="button" value="START" Class="submitButton" onclick="antenna()"></td>
    </tr>
    </table>
    <br>
    </td>
    </table>
    </center>
  </div>

</form>
</body>
</html>


<script>
function antenna()
{

	var request;
	var antenna = document.antennastart.antradio.value;
	var url="/AutoLib/Counter/CounterServlet?flag="+antenna+"";
	if(window.XMLHttpRequest){  
	request=new XMLHttpRequest();  
	}  
	else if(window.ActiveXObject){  
	request=new ActiveXObject("Microsoft.XMLHTTP");  
	}  
	  
	try{  
	request.onreadystatechange=getInfo;  
	request.open("POST",url,true);  
	request.send();  
	}catch(e){alert("Unable to connect to server");}  
	  
	  
	function getInfo(){  
	if(request.readyState==4){  
	var val=request.responseText;  
	alert("Antenna Started");
	
	}  
	}
}
</script>