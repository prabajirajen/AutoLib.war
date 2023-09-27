<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Advanced.Adsearchbean"
 import="Lib.Auto.Suggestion.SuggestionBean" import="Common.Security" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

    <link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
 
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Suggestion.SuggestionBean"  type="Lib.Auto.Suggestion.SuggestionBean">
</jsp:useBean>
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
	
	<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("suggestionSearchList");
%>
<%
Iterator it=sc.iterator();
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body>

  <form method="post" name="query" action="./SuggestionDisplayServlet"  ONSUBMIT="return validate(this)">
<table align=center>
<tr><td>  
 <center>
    <h2 >Suggestions</h2>
</center></td>
<%	
    while(it.hasNext()){
    	SuggestionBean view=(SuggestionBean) it.next();
    	
    %>

  <table>
                        
            <tr>
              <td Class="addedit" align="left">Member Code</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getMemberCode()%></td>
            </tr>
            
            <tr>
			<td Class="addedit" align="left">Request Date</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getRcDate()%></td>
	        </tr>
           
            <tr>            
              <td Class="addedit" align="left">Request For</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getDoc()%></td>
            </tr>
                        
            <tr>
              <td Class="addedit" align="left">Request Details</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSugName()%></td>
              
            </tr>

    	   <tr>
              <hr/>
           </tr>
            
           <%        
}		
 	sc.clear();

			%>
	 
</table>
</form>
</body>
</html>

<%@ include file="/Tree/back.jsp"%>