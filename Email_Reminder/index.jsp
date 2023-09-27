<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>

<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

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

<!-- Style Sheet -->

<form name="Mail_Reminder" method="Post" action=./MailReminder>
<br><br><br>

<br><br><br>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="25%">
<td>
<table align="center" width="25%">    
      <tr>
      <h2>&nbsp;&nbsp;&nbsp;&nbsp;Email/SMS&nbsp;&nbsp;Reminder</h2><br>  </tr>      
          <p align="center">
<tr><td>  <input type="button" Class="submitButton" value="Due List" onclick=SendRec1()></td><td>
		  <input type="button" Class="submitButton" value="Send E-Mail" onclick=SendRec()>		 </td><td>
		  <input type="button" Class="submitButton" value="Send SMS" onclick=SendSms()>		 </td>
		  <input type="hidden" name="flag">	 
		  <input type="hidden" name="flag1" value="ALL">		   
      </tr>   
    </table></table>
    </center>
  </div>
<br>
</form>
</body>
</html>

<%
String valid=request.getParameter("check");
String mailcount=request.getParameter("mailCount");

if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("SaveSuccess"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("E-Mail Send Successfully. Count: "+<%= mailcount %>);
	 
	 </script>
	 
<%
}
if(valid.equals("SaveFail"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("E-Mail Sending Failed !");
	 
	 </script>
	 
<%
}

if(valid.equals("SaveSuccessSMS"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("SMS Send Successfully. Count: "+<%= mailcount %>);
	 
	 </script>
	 
<%
}
if(valid.equals("SaveFailSMS"))
{
	%>
	
	 <script language="JavaScript">
	 
	alert("SMS Sending Failed !");
	 
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
function SendRec() {
document.Mail_Reminder.flag.value="DueReminderMail";
document.Mail_Reminder.method="get";
document.Mail_Reminder.submit();
}
function SendRec1() {
document.Mail_Reminder.flag.value="DueReminderList";
document.Mail_Reminder.method="get";
document.Mail_Reminder.submit();
}

function SendSms()
{
document.Mail_Reminder.flag.value="DueReminderSMS";
document.Mail_Reminder.method="get";
document.Mail_Reminder.submit();
}

</script>


