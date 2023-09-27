
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<br>

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.JnlSearchArrylist" id="Searchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/JournalBrowse/journalSearchdisplay.jsp" export="true">
		
	    <display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="JnlSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="JnlSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="JnlSearch.pdf"></display:setProperty>
		
		
		
		<display:column property="jnlCode" href='JournalSearch' paramId="jnlcode" paramProperty="jnlCode" title="Code"  maxLength="25" > 		  
	    </display:column>
	   
	    <display:column property="jnlName"  sortable="true" title="Journal Name"  maxLength="200" >     	    
	    </display:column>
	    
		<display:column property="frequency" sortable="true" title="Frequency"  maxLength="25" >     
	    </display:column>
	   
	    <display:column property="country" sortable="true" title="Country"  maxLength="25" >   
	    </display:column>

		<display:column property="document" sortable="true" title="Type"  maxLength="25" > 	
	    </display:column>
			
    	<display:column property="language" title="Language"  maxLength="25">     	
		</display:column>

		<display:column property="issn"   title="ISSN"  maxLength="25">   
		</display:column>
		
		<display:column property="branch"   title="Campus"  maxLength="25" sortable="true">   
		</display:column>
		   
		   
		<display:setProperty name="basic.empty.showtable" value="true" />
		
		
	</display:table>

</div>



