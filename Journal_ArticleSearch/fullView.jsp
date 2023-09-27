<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Journal_ArticleSearch.JournalAtlSearchbean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

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
<body >

<%

ArrayList sc=new ArrayList();

sc=(ArrayList)request.getAttribute("SearchArrylist");

Iterator it=sc.iterator();
%>




  <form method="get" name="query" action="./SimpleServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>

 <center>
    <h2 >Full View</h2>
</center>
    
    <%
    while(it.hasNext()){
    	JournalAtlSearchbean view=(JournalAtlSearchbean) it.next();
    
    %>
    <center>
    <table >
            <tr>
              <td Class="addedit" align="left">Atl&nbsp;No</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getAtlno()%>     </td>
            </tr>
            <tr>
           </tr>           
            <tr>
              <td Class="addedit" align="left">Jnl&nbsp;Name</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getJname()%> </td>
            </tr>            
            <tr>
           </tr>
            <tr>            
              <td Class="addedit" align="left">Alt.&nbsp;Title</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getAtitle()%>
            </tr>
            <tr>
           </tr>
            <tr>            
              <td Class="addedit" align="left">Alt.&nbsp;Author</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getAuthor()%>
            </tr>
            <tr>
           </tr>           
	         <tr>
			<td Class="addedit" align="left">Subject</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getAsubject()%></td>
	         </tr>
            <tr>
           </tr>
	         <tr>
			<td Class="addedit" align="left">Volume&nbsp;No</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getVolno()%></td>
	         </tr>
            <tr>
           </tr>
	         <tr>
			<td Class="addedit" align="left">Issue&nbsp;No</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getIssueno()%></td>
	         </tr>
            <tr>
           </tr>
	         <tr>
			<td Class="addedit" align="left">Issue&nbsp;Year</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getIyear()%></td>
	         </tr>
            <tr>
           </tr>                                 
	         <tr>
			<td Class="addedit" align="left">Issue&nbsp;Month</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getImonth()%></td>
	         </tr>
            <tr>
           </tr>          

            <tr>
              <td Class="addedit" align="left">Keywords</td>
            <td Class="addedit" align="left">:&nbsp;<%=view.getAkeywords()%></td>
            </tr>
            
            <tr>
              <td Class="addedit" align="left" style="color: blue;font-size: 13px">Campus</td>
            <td Class="addedit" align="left" style="color: blue;font-size: 13px">:&nbsp;<%=view.getBranch()%></td>
            </tr>
            <tr>
           </tr>          
            
            <tr>
              <td Class="addedit" align="left">Content</td>
              
            <td Class="addedit">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/JOURNAL ARTICLE/<%=view.getContent()%> " target=_base>Click to Contents</a></td>
            </tr>
<% 
}
			
			sc.clear();

			%>

</table>
 </center>
    </form>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
    <link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</body>

</html>






