<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" import="Lib.Auto.MemberReport.MemberReportBean"%>
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
<body background="/AutoLib/soft.jpg"  onload="focuss()" >
<form name=member_report_find method=post action=./MemberReportSearch>
<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String flag=request.getParameter("flag");
String SQLstr="";
String Title="";
String t1="",t2="",t3="";
if (flag!=null){
if (flag.equals("dept")){
Title="Department Name";t1="Dept_Code";t2="Dept_Name";t3="Short_Desc";
}
else if (flag.equals("name")){
Title="Member Name";t1="Member_Code";t2="Member_Name";t3="Enroll_Date";
}
else if (flag.equals("group")){
Title="Group Name";t1="Group_Code";t2="Group_Name";t3="Remarks";
}
else if (flag.equals("course")){
Title="Course Name";t1="Course_Code";t2="Course_Name";t3="Short_Desc";
}

}

%>
<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
<h2 >Member Search</h2>

<div align=right><A href=""  onclick="window.close()">Back</A></div>
<p align="center">
<%=Title%>&nbsp;<input type=text name=name>&nbsp;<input type=submit Class="submitButton" value="Search">
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
	int j=1;
    %>
  <script language="JavaScript">
document.member_report_find.name.value="<%=request.getParameter("nam")%>";
document.member_report_find.name.focus();
</script>
<%

  if (flag.equals("name")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Member Code<th>Member Name</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				MemberReportBean view=(MemberReportBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getMname()%>","<%=view.getMcode()%>")'>
		<script language=javascript>
         document.write("<td>"+"<%=j++%>" +"</td>");					
		 document.write("<td>"+"<%=view.getMcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getMname()%>" +"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("dept")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Dept Name<th>Short Desc</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				MemberReportBean view=(MemberReportBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getDname()%>","<%=view.getDcode()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getDcode()%>" +"</td>");
         document.write("<td>"+"<%=j++%>" +"</td>");	
		 document.write("<td>"+"<%=view.getDname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDdesc()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("group")){

               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>S.No<th>Group Name<th>Remarks</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				MemberReportBean view=(MemberReportBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getGname()%>","<%=view.getGcode()%>")'>
		<script language=javascript>
//		 document.write("<td>"+"<%=view.getGcode()%>" +"</td>");
         document.write("<td>"+"<%=j++%>" +"</td>");	
		 document.write("<td>"+"<%=view.getGname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getRemarks()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
		}

		if (flag.equals("course")){
               sc=(ArrayList)request.getAttribute("search");

			 out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Course Code<th>Course Name<th>Remarks</tr>");
                    Iterator it=sc.iterator();
			while(it.hasNext()){
				MemberReportBean view=(MemberReportBean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getCname()%>","<%=view.getCcode()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getCcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getCname()%>" +"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getCdesc()%>"+"</td>");
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
function show(vname,vcode){
if(document.member_report_find.sflag.value=="name"){
window.opener.document.member_report.name.value=vname;
window.opener.document.member_report.mcode.value=vcode;
}
if(document.member_report_find.sflag.value=="dept"){
window.opener.document.member_report.dept.value=vname;
window.opener.document.member_report.dcode.value=vcode;
}
if(document.member_report_find.sflag.value=="group"){
window.opener.document.member_report.group.value=vname;
window.opener.document.member_report.ccode.value=vcode;
}
if(document.member_report_find.sflag.value=="course"){
window.opener.document.member_report.course.value=vname;
window.opener.document.member_report.gcode.value=vcode;
}
window.close();
}
function focuss(){
document.member_report_find.name.focus();
}
function search(){
document.member_report_find.submit();
}
</script>
