<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">

<display:table name="sessionScope.SearchArrylist" id="questionSearchBean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/QBSearch/simpleSearchdisplay.jsp" export="true" >
			
     	<display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="SimpSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="SimpSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="SimpSearch.pdf"></display:setProperty>
			
		<display:column property="qcode" sortable="true" title="QB&nbsp;Code"  href='QBSearchServlet' paramId="accno" paramProperty="qcode"  maxLength="10"> 
	   </display:column>
	   
	   <display:column property="subcode" sortable="true" title="Subject&nbsp;Code"  maxLength="50"> 
	   </display:column>
		
	   <display:column property="sname" sortable="true"  title="Subject Name"  maxLength="100" >     
 	   </display:column>
 	    
	   <display:column property="dname"  sortable="true" title="Dept Name"  maxLength="150" >     
	   </display:column>
				
	   <display:column property="qcourse" sortable="true"  title="Course Name"  maxLength="100">   
	   </display:column> 
	   
	   <display:column property="remarks2" sortable="true" title="Course Major"  maxLength="100">   
	   </display:column> 
	   
	  
	   <display:column property="qyear" sortable="true" title="Year"  maxLength="4">   
	   </display:column> 
	  
	   
	   <display:column property="qmonth" sortable="true" title="Month"  maxLength="10">   
	   </display:column> 
	   
	   
	     
	   <display:column property="remarks1"  sortable="true"  title="Semester"  maxLength="5">   
	   </display:column> 
	   
	   <display:column property="branch" sortable="true" title="Campus"  maxLength="10">   
	   </display:column> 
	   
	   <display:column value="view" href='QBSearchServlet' paramId="accno" paramProperty="qcode" title="Contents"  maxLength="10" > 		   
	   </display:column>
	     
	       <display:setProperty name="basic.empty.showtable" value="true" />
	   
	</display:table>

</div>

</script>


