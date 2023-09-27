
<%@ include file="/Tree/demoFrameset.jsp"%>

<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.EResourceSearch.EResourceSearchbean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Author.AuthorBean"  type="Lib.Auto.Author.AuthorBean">

</jsp:useBean>

<!-- #include file="logo.html" -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body	>

<%

ArrayList sc=new ArrayList();


try{


            sc=(ArrayList)request.getAttribute("SearchArrylist");
						out.print("<br>");
						out.print("<br>");
						out.print("<br>");
						out.print("<br>");
			 out.print("<table border=1 bordercolor=#104e8b width=700 align=center cellspacing=0 >");
		    out.print("<tr><th>Code<th>Content<th>Access Type<th>Site<th>Campus</tr>");
           Iterator it=sc.iterator();

			while(it.hasNext()){
				EResourceSearchbean view=(EResourceSearchbean) it.next();
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black' onclick='show("<%=view.getEcode()%>")'>
		<script language=javascript>
		 document.write("<td>"+"<%=view.getEcode()%>" +"</td>");
		 document.write("<td>"+"<%=view.getEtitle()%>" +"</td>");
		 document.write("<td>"+"<%=view.getEsubtitle()%>" +"</td>");
		 document.write("<td><a href='<%=view.getEsite()%>' target='_blank'>"+"<%=view.getEsite()%>"+"</a></td>");
		 
		 document.write("<td>"+"<%=view.getBranchName()%>" +"</td>");
		 document.write("</tr>");    
 		</script>	
		<%
			}

 }catch (Exception e) {e.printStackTrace();}
   finally{
   
    }
%>

 






