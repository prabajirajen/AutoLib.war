<%@ page language="java" errorPage="/Error/ErrorPage.jsp"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />



<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Author
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">


</head>
<body>

<form name="Account" method="post" action=./AccountServlet>
<br><br><br><br><br><br><br><br><br>
 <P ALIGN="left"><BR>
 <h2>  </h2>
 <center>
<table>
<tr>
<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

</tr>
<tr><td Class="addedit">User&nbsp;ID<td><input type=text name="uid" size="30"  maxlength="20">

    
<tr><td Class="addedit">Old Password<td><input type=password name="pwd" size="30" maxlength="50">
   
<tr><td Class="addedit">New Password<td><input type=password name="newpwd" size="30" maxlength="50">

<tr><td Class="addedit">Confirm Password<td><input type=password name="cfmpwd" size="30" maxlength="50">
   
<tr><td colspan=3 align=center >
<CENTER>





<input type=button name=Save Class="submitButton" value=Change onclick=SaveRec()>
<input type=reset name=Clear Class="submitButton"  Value=Clear >

</CENTER>
<input type=hidden name=flag >
</table>
</CENTER>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String uchek=request.getParameter("check");
if(uchek!=null){
if(uchek.equals("usernotfound")){
	 %>
	<script language="JavaScript">
	alert("Invalid UserId/Password!!!");
	document.Account.uid.focus();
	</script><%

	}
}

%>


<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
<script language=javascript>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}



function SaveRec(){

            
			   if((document.Account.uid.value=="")||(document.Account.pwd.value=="")||(document.Account.newpwd.value=="")||(document.Account.cfmpwd.value=="")){
			    	
					alert("Insufficent Data");
					
			}		
	else if((document.Account.newpwd.value!= document.Account.cfmpwd.value))
	{
					alert("Not match Confirm Password!!! ");
	
	}
	else{
	                document.Account.flag.value="save";
		         	document.Account.submit();	
	}
	
}


function isWhitespace(str)
		{
	
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
			
		}
		

</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
