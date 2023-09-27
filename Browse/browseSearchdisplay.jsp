<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Advanced.Adsearchbean"/>

<style>
.hidden 
{
 display: none;
}
</style>

<%
ArrayList sc=new ArrayList();
try{
sc=(ArrayList)bean.getAl();
//out.print("<table>");
for(int i=0; i<sc.size();i++){
	%>
<tr>
	<script language=javascript>
	      document.write("<td ><a href='?doc=<%=sc.get(i)%>'>"+"<%=sc.get(i)%>" +"</a></td>");
	     document.write("<td >"+"(" +"</td>");
		 document.write("<td>"+"<%=sc.get(++i)%>" +"</td>");
		 document.write("<td >"+")" +"</td>");
		 
	 
		</script>	
	<%
}
}catch (Exception e) {out.println(e.toString());}
finally{
	 sc.clear();
}

%>


<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>


<!--<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />-->
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
<div id="displayTag">
	<display:table name="sessionScope.BrowseArrylist" id="Searchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Browse/browseSearchdisplay.jsp" export="true">
		
	    <display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="BrowSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="BrowSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="BrowSearch.pdf"></display:setProperty>
		
		
		
		<%-- <display:column property="accno" href='BrowseSearch' paramId="accno" paramProperty="accno" title="Access No"  maxLength="25" > 		  
	    </display:column>
	     --%>
	    <display:column property="accno" title="Access No" maxLength="25" > 		  
	    </display:column>
	   
	    <display:column property="title" title="Title" class="hidden" headerClass="hidden"> 
	    </display:column>
	    
	    
	    <display:column title="Title" media="html" maxLength="150" sortable="true">
				<c:choose>
					<c:when
						test="${Searchbean.title ne ''}">

						<c:url var="fullViewURL" value="BrowseSearch">
							<c:param name="accno" value="${Searchbean.accno}" />
							<c:param name="branch" value="${Searchbean.branch}" />
						</c:url>

						<a href="${fullViewURL}">
								<c:out value="${Searchbean.title}"></c:out>
						
						</a>

					</c:when>

					<c:otherwise>
						<c:out value="${Searchbean.title}"></c:out>
					</c:otherwise>
				</c:choose>
			</display:column>

	    
	    
    	<display:column property="author" sortable="true" title="Author Name"  maxLength="50">     	
		</display:column>
		
		<display:column property="branch"  title="Campus"  maxLength="45" sortable="true">     
 	   </display:column>
		
		<display:column property="callno"  title="Call no"  maxLength="25" >     
	    </display:column>
	   
	    <display:column property="location" title="Rack/Shelf" maxLength="50"
				sortable="true">
			</display:column>
			
		<display:column property="availability"   title="Status"  maxLength="25" sortable="true">   
		</display:column>
		
		<%-- <display:column title="ILL"  maxLength="25"
				sortable="true">
				
				<input type=button name="FindBranch" Value="&nbsp;ILL&nbsp;"  Class="submitButton" onclick="illReq()">
				
			</display:column> --%>
		
		<%-- <display:column property="document"  title="Document"  maxLength="25" > 	
	    </display:column> --%>
	   
		<display:setProperty name="basic.empty.showtable" value="true" />
		
		
	</display:table>
	
	<p align="center">
			<input type=button name=New Class="submitButton" Value="Back"
				onclick='gotoHome()'>
		</p>
		<script>
			function gotoHome() {
				location.href = "/AutoLib/Browse/BrowseSearch?flag=load";
			}
			
			function illReq()
			{
				alert('Please Contact your Librarian to borrow this book on Inter-Library Loan');
			}
		</script>

</div>



