<%@ include file="/Tree/demoFrameset.jsp"%>
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
<br><br><br><br><br>
 <P ALIGN="left"><BR>
 <h2>  </h2>
 <center>
  <h2> Change&nbsp;Password </h2>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
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
<!-- <input type=button name=Save Class="submitButton" value=Login onclick=LoginRec()>-->
<input type=reset name=Clear Class="submitButton"  Value=Clear >

</CENTER>
<input type=hidden name=flag value=chpwd>
</table>
</CENTER>
</CENTER>
<input type=hidden name=flag>
</table>
<CENTER><tr><td><br></td></tr>
<tr><td></td><td></td><td></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

<td Class="addedit">

<c:choose>
<c:when test="${UserRights eq 7}">
<a href="<%= request.getContextPath() %>/Home/HomeServlet" id="bodysearch">
<img src="<%= request.getContextPath() %>/iconImages/back.png" width="50" height="50" border="0" align="middle" title="Click here to go back.">
</a>
</c:when>
<c:otherwise>
<a href="index.jsp" id="bodysearch">
<img src="<%= request.getContextPath() %>/iconImages/back.png" width="50" height="50" border="0" align="middle" title="Click here to go back.">
</a>
<!--<a href="index.jsp" ><font face="veranda" color="#8B4513">User Account</font></a>-->
</c:otherwise>
</c:choose>

<td></td></tr>
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


<%
String valid=request.getParameter("check");
//out.print("valid  "+valid);
if(valid!=null){
if(request.getParameter("check")!=null){
	if(valid.equals("cpwd")){
		String  Autcode=(String)request.getAttribute("strobj");			
		 %>
		<script language="JavaScript">
		alert(""+"<%=Autcode%>");
		</script><%
		}			

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
	                document.Account.flag.value="chpwd";
		         	document.Account.submit();	
	}
	
}
function LoginRec()
{
document.Account.flag.value="Login";
document.Account.submit();	
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
