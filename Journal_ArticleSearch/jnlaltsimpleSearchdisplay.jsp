<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>

<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.SearchArrylist" id="JournalAtlSearchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Journal_ArticleSearch/jnlaltsimpleSearchdisplay.jsp">
		
		<display:column property="atlno" title="Article No"  maxLength="30" href='JNLArticleSearchServlet' paramId="ncode" paramProperty="atlno"> 		   
	    </display:column>
		
		<display:column property="jname"  title="Name"  maxLength="105" sortable="true"> 		   
	    </display:column>
	    
    	<display:column property="atitle" title="Title"  maxLength="250" sortable="true"> 
		</display:column>
		
    	<display:column property="author" title="Author"  maxLength="80" sortable="true"> 
		</display:column>		
		
    	<display:column property="volno" title="Volume"  maxLength="80" sortable="true"> 
		</display:column>			

    	<display:column property="iyear" title="Year"  maxLength="80" sortable="true"> 
		</display:column>	
						
		<display:column property="asubject"  title="Subject"  maxLength="25" sortable="true">     
	   </display:column>  
	   
	   <display:column property="branch"  title="Campus"  maxLength="25" sortable="true">     
	   </display:column>
	   
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>	

</div>



