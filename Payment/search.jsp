<html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ page language="java"  session="true" buffer="12kb" import="Common.Security"
   	import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common/JstlTool.jsp"%>
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Payment.PaymentBean"/>

<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
   String memCode=request.getParameter("memb_code");
   out.println("ddddddddddd"+memCode);
%>


<%
ArrayList id=new ArrayList();

%>

<head>
<title>Auto Lib</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>

<body onload="focuss()">


<form name="issueHistory" method="post" action=./PaymentServlet>

<h2>Issue&nbsp;History</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<input type=hidden name=flag  value="issueHistory">
<input type=hidden name=memCode value="<%=memCode%>">
</form>
</body>
<%
String valid=request.getParameter("check");

ArrayList id=new ArrayList();


%>
<script  language="javascript">
function focuss(){
	document.issueHistory.submit();	
}
</script>
</html> 
