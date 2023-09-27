
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored ="false" %>

<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.DueReminderList" id="reminderBean" pagesize="200" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Email_Reminder/DueReminderdisplay.jsp">

		<display:column>
				<input type="checkbox" id="selectedIds" name="selectedIds[]" value='<c:out value="${reminderBean.mcode}"/>'>
		</display:column>   
				   
		<display:column property="mcode"  title="Member Code"  maxLength="25" > 	
	   
	   </display:column>
	   
	    <display:column property="mname"  sortable="true" title="Member Name"  maxLength="100" >     
	    
	    </display:column>
	    
    	<display:column property="memail" sortable="true" title="Member Email"  maxLength="50"> 
    	
		</display:column>
		
		<display:column property="mphone" title="Member Phone"  maxLength="30"> 
    	
		</display:column>
		   
	    <display:column property="dueDate" sortable="true" title="Due Date"  maxLength="20"> 
    	
		</display:column>
		
		<display:setProperty name="basic.empty.showtable" value="true" />
	</display:table>

</div>



