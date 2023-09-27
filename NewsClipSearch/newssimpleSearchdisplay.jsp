<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.NewsClipSearch.NewsSearchbean"/>

<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.SearchArrylist" id="NewsSearchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/NewsClipSearch/newssimpleSearchdisplay.jsp">
		
		<display:column property="ncode" href='NewsClipSearchServlet' paramId="ncode" paramProperty="ncode" title="Code"  maxLength="20" > 	
	   
	   </display:column>
		
		<display:column property="nname" href='NewsClipSearchServlet' paramId="ncode" paramProperty="ncode" title="Name"  maxLength="105" sortable="true"> 	
	   
	   </display:column>
	   
	    <display:column property="ntype"  title="Type"  maxLength="100" sortable="true">     
	    
	    </display:column>
	    
    	<display:column property="ntitle" title="Title"  maxLength="250" sortable="true"> 
    	
		</display:column>
				
		<display:column property="nsubject"  title="Subject"  maxLength="25" sortable="true">     
	   </display:column> 
	   
	   <display:column property="branch"  title="Campus"  maxLength="25" sortable="true">     
	   </display:column>  
	   
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>	

</div>



