<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Advanced.Adsearchbean"/>


<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<style>
.hidden 
{
 display: none;
}
</style>
<%-- <script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script> --%>

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

<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->


<div id="displayTag">
	<display:table name="sessionScope.AdsearchArrylist" id="Adsearchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2"  defaultorder="ascending"
		requestURI="/Advanced/advancedSearchdisplay.jsp" export="true">
		

		
	    <display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="AdvSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="AdvSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="AdvSearch.pdf"></display:setProperty>
		
		
		
		<%-- <display:column property="accno" href='AdvancedServlet' paramId="accno" paramProperty="accno" title="Access No"  maxLength="25" > 		  
	    </display:column> --%>
	    
	    
	    <display:column property="accno" title="Access No"  maxLength="25" > 		  
	    </display:column>
	    
	    
		
		<display:column property="title" title="Title" class="hidden" headerClass="hidden"> 
	    </display:column>
	    
	    <display:column title="Title" media="html" maxLength="150" sortable="true">
				<c:choose>
					<c:when
						test="${Adsearchbean.title ne ''}">

						<c:url var="fullViewURL" value="AdvancedServlet">
							<c:param name="accno" value="${Adsearchbean.accno}" />
							<c:param name="branch" value="${Adsearchbean.branch}" />
						</c:url>

						<a href="${fullViewURL}">
								<c:out value="${Adsearchbean.title}"></c:out>
						
						</a>

					</c:when>

					<c:otherwise>
						<c:out value="${Adsearchbean.title}"></c:out>
					</c:otherwise>
				</c:choose>
			</display:column>
			
			
	   
	   <%--  <display:column property="title" sortable="true" title="Title"  maxLength="150" > 
	    </display:column> --%>
	    
    	<display:column property="author" sortable="true" title="Author Name"  maxLength="50"> 
		</display:column>
		
		
		<display:column property="branch"  title="Campus"  maxLength="45" sortable="true">     
	    </display:column>
		
		<display:column property="callno"  title="Call no"  maxLength="25" >     
	    </display:column>
	   
	    <display:column property="location"  title="Rack/Shelf"  maxLength="50" sortable="true">   
	    </display:column>
	
		<display:column property="availability"   title="Status"  maxLength="25" sortable="true">   
		</display:column>
				
		<%-- <display:column property="document"  title="Document"  maxLength="25" > 	
	    </display:column> --%>
	    
	   <%--  <display:column title="ILL"  maxLength="25"
				sortable="true">
				
				<input type=button name="FindBranch" Value="&nbsp;ILL&nbsp;"  Class="submitButton" onclick="illReq()">
				
			</display:column> --%>
	   
		<display:setProperty name="basic.empty.showtable" value="true" />
		
		
	</display:table>
	
	<p align="center">
			<input type=button name=New Class="submitButton" Value="Search Page"
				onclick='gotoHome()'>
		</p>

</div>








<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	window.location.href='/AutoLib/Advanced/AdvancedServlet?flag=load';
}
</script>


