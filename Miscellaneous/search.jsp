<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="Lib.Auto.Miscellaneous.MiscellaneousBean" import="java.util.ArrayList,java.util.Iterator"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%//Security.checkSecurity(1,session,response,request);%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Search.jsp
//   Form name:Subject_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>AutoLib</title>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focuss()" background="/AutoLib/soft.jpg">
<form name="TranSrch" method="post" action=./MiscellaneousServlet > 
<%
String flag=request.getParameter("flag");
String SQLstr="";
String Title="";
String t1="",t2="";
String mainTitle="";
if (flag!=null){
if (flag.equals("MemberView")){
mainTitle="Member"; Title="Member&nbsp;Name";t1="Member&nbsp;Code";
%>
<script>document.TranSrch.flag.value="<%=flag%>";</script>
<%
}
}

%>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<h2><%=mainTitle%>&nbsp;Search</h2>
<div><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>
<input type=text name=name>
<input Class="submitButton" type=submit value="Search" >
</center>
<input type=hidden name=flag value="<%=flag%>">
</center></form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%

String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){
	
	int i = 1;
	sc=(ArrayList)request.getAttribute("searchMemberView");
             
%>
	     	     
 <script language="JavaScript">
   document.TranSrch.name.value="<%=request.getParameter("nam")%>";
   document.TranSrch.name.focus();
</script>

<%
if (flag.equals("MemberView")){		
		out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		out.print("<tr><th>S.No<th>"+t1+"</th><th>"+Title+"</th><th>Date</th><th>Amount</th></tr>");
		
		Iterator it=sc.iterator();
		while(it.hasNext()){
			MiscellaneousBean view=(MiscellaneousBean) it.next();
			  %>
					<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getMrno()%>")'>
					<script language=javascript>
            		 document.write("<td>"+"<%=i++%>" +"</td>");					
					 document.write("<td>"+"<%=view.getMuserID()%>" +"</td>");
					 document.write("<td>"+"<%=view.getMname()%>" +"</td>");
					 document.write("<td>"+"<%=view.getTdate()%>" +"</td>");
					 document.write("<td>"+"<%=view.getTamount()%>" +"</td>");
					 document.write("</tr>");
			 		</script>
					<%

					}
					}

}
 }catch (Exception e) {
	 e.printStackTrace();
 }finally{
    sc.clear();
 }
%>
<script  language="javascript">

function show(val){
  window.opener.document.TransMas.mrno.value=val;
  window.opener.document.TransMas.method="post";
  window.opener.document.TransMas.flag.value="search";
  window.opener.document.TransMas.action="./MiscellaneousServlet";
  window.opener.document.TransMas.submit();
  window.close();
}

function validate()
{
if(t1())
{
alert("Please enter the Name");
return false;
}
 else
{
return true;
}

}
   
function t1()
{
if(document.TranSrch.name.value=="")
{
return true;
}
else
{
return false;
}
}
function focuss(){
document.TranSrch.name.focus();
}
function search(){
document.TranSrch.submit();
}
</script>
