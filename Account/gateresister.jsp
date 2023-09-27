<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<jsp:useBean id="bean" scope="request" class="Lib.Auto.Account.AccountBean"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>


<html>
 <head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head> 
<body onload="LoadId()">
<form name="Account" method="post" action=./AccountServlet>


 <P ALIGN="left"><BR>

 <center>
<table>

<tr><td Class="addedit">User&nbsp;Id<td><input type="text" name="userid" id="user" size="30" maxlength="30"></td>
<td Class="addedit">Purpose<td><td><input type="text" name="purpose" id="purpos" size="40" maxlength="150">
<td>
<c:set var="str" value="<%=bean.getPhoto1()%>"/>
<c:choose>
<c:when test="${str ne null}">

<td align="right" colspan="2" rowspan="4">
<img src="<%=request.getContextPath() %>/Account/photo.jsp" height="100px" width="100px" align=left/></td>
 
</c:when>
<c:otherwise>

<td align="right" colspan="2" rowspan="4">
<img src="<%=request.getContextPath() %>/images/noimage.jpg" height="100px" width="100px" align=left/></td>

</c:otherwise>
</c:choose>
</td>


<CENTER>



</CENTER>

<input type=hidden name=flag value=register>


</table>
<%
String strrply=bean.getauthor();
if(strrply!=null){
	 out.println("<html>");
	out.print("<head>");
	out.print("</head>");
	out.println("<BODY>");
	out.println("<table width='80%' >");
	out.print("<font color='#800000' size='3'>");
	out.print("<div Class='icon-ok'>");
   	out.println(""+strrply);
   	out.print("</div>");
   	out.print("</font>");
   	out.println("</table>");
   	out.println("</BODY>");
   	out.println("</html>");
}
%>
</form>
</body>
</html>
<div style="padding: 0px 0px 0px 0px;">
		<jsp:include page="userid.jsp" flush="true" />
	</div>

<script  language="javascript">
$('#user').on('keypress', function (e) 
{
    if(e.which === 13)
    {
     	document.Account.submit();
    }
});
$('#purpos').on('keypress', function (e) 
{
    if(e.which === 13)
    {
     	document.Account.submit();
    }
});

function LoadId(){
	
document.Account.userid.focus();
// var request;  
// var url="/AutoLib/Counter/RFIDServlet?flag=CardUID";  
  
// if(window.XMLHttpRequest){  
// request=new XMLHttpRequest();  
// }  
// else if(window.ActiveXObject){  
// request=new ActiveXObject("Microsoft.XMLHTTP");  
// }  
  
// try{  
// request.onreadystatechange=getInfo;  
// request.open("POST",url,true);  
// request.send();  
// }catch(e){alert("Unable to connect to server");}  
  
  
// function getInfo(){  
// if(request.readyState==4){  
// var val=request.responseText;  
// document.getElementById('user').value=val;
// document.Account.submit();
// }  
// }  
}
</script>