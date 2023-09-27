
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="Lib.Auto.Account.AccountBean"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />
<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Account.AccountBean"  type="Lib.Auto.Account.AccountBean">
</jsp:useBean>

<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>

<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ include file="/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AccountArrylist" id="AccountBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2"  defaultorder="ascending"
		requestURI="/Account/reservedetailsdisplay.jsp">
		
		<c:set var="status" value="1"/>
		
		<display:column property="accno"  title="Access No"  maxLength="25" > 	
	  
	    </display:column>
	   
	    <display:column property="title"   title="Title"  maxLength="200" >     
	    
	    </display:column>
	    
    	<display:column property="author"  title="Author Name"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="resdat"  title="Reserve Date"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="availability"  title="Status"  maxLength="50">     
			
		</display:column>
				
		<display:column property="dtype"  title="Document"  maxLength="25" > 	
		
	    </display:column>
	    
	    <display:column value='Cancel' href='AccountServlet' paramId="resaccno" paramProperty="accno"   maxLength="25" > 	
	  
	    </display:column>	    
		
		<display:setProperty name="basic.empty.showtable" value="true" />
	    
	    </display:table>
</div>
<%
String valid=request.getParameter("check");
if(valid!=null){

   if(valid.equals("YES")){

 String value=(String)request.getAttribute("strobj");
   	
    out.println("<html>");
	out.print("<head>");
	out.print("</head>");
	out.println("<BODY>");
	out.println("<table width='25%' >");
	out.print("<font color='#800000' size='3'>");
	out.print("<div Class='icon-ok'>");
   	out.println(""+value);
   	out.print("</div>");
   	out.print("</font>");
   	out.println("</table>");
   	out.println("</BODY>");
   	out.println("</html>");   	 	
   }
}
%>