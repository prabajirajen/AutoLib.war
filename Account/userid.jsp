<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="Lib.Auto.Account.AccountBean"  import="java.util.*"  session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
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

<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
   

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.AccountArrylist" id="RegisterBean" pagesize="20" 
		class="dataTable"  requestURI="/Account/userid.jsp">
		
		<c:set var="status" value="1"/>
		
		<display:column property="gmembercode"  title="Member Code"  maxLength="25" > 	
	  
	    </display:column>
	   
	    <display:column property="gmembername"  sortable="true" title="Member Name"  maxLength="200" >     
	    </display:column>
	    
	    <display:column property="gdesignation"  title="Designation"  maxLength="25" > 	
	    </display:column>
	    
	    
<%-- 	    <display:column property="gcoursename"  title="Course Name"  maxLength="25" > 	 --%>
<%-- 	    </display:column> --%>
	    
	    	    <display:column property="gcoursemajor"  title="Course Major"  maxLength="25" > 	
	    </display:column>
    	
		<display:column property="gentrytime"  sortable="true" title="Entry Time"  maxLength="50"> 
    	
		</display:column>
		
		
		
	    

	    

	    
		
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>


</div>


