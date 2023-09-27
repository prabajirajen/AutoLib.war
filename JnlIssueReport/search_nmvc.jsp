<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.JnlIssueReport.JnlIssueReportBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<%@ include file="/Common.jsp" %>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->

<%
//
//   Filename: Search.jsp
//   Form name:order_Find
//%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="focuss()" >
<form name=jnlsearch method=post action=./JnlReportSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");

String SQLstr="";
String Title="";
String t1="",t2="",t3="";
if (flag!=null){
if (flag.equals("jnlfind")){
Title="Journal Name";t1="Jnl_Code";t2="Jnl_Name";t3="Frequency";
}
}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2>Journal&nbsp;Search</h2>
<div align=right><A href=""  onclick="window.close()">Back</A></div>
<center><%=Title%>&nbsp;<input type=text name=name>&nbsp;<input type=submit Class="submitButton" value="Search"></center>
<input type=hidden name=sflag value="<%=flag%>">
</form>
</body>
</html>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<%
String ck=request.getParameter("check");
ArrayList sc=new ArrayList();
try{
if(ck!=null){

  		if (flag.equals("jnlfind")){
  			int i=1;

               sc=(ArrayList)request.getAttribute("search");
   %>
  <script language="JavaScript">
document.jnlsearch.name.value="<%=request.getParameter("nam")%>";
document.jnlsearch.name.focus();
</script>
<%
			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Jnl Name<th>Frequency</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				JnlIssueReportBean view=(JnlIssueReportBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getJname()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getJcode()%>" +"</td>");
		 document.write("<td>"+"<%=i++%>" +"</td>");		
		 document.write("<td>"+"<%=view.getJname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getFreq()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		}
 }catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
%>

<script  language="javascript">
function show(vname){
if(document.jnlsearch.sflag.value=="jnlfind"){
window.opener.document.JIssueReport.jnlname.value=vname;
}
window.close();
}
function focuss(){
document.jnlsearch.name.focus();
}
function search(){
document.jnlsearch.submit();
}
</script>
