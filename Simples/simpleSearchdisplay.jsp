<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.io.*" import="java.util.*" session="true" buffer="12kb"
	import="Common.Security" import="java.util.ArrayList"%>
<%//Security.checkSecurity(1,session,response,request);%>
<jsp:useBean id="bean" scope="page"
	class="Lib.Auto.Advanced.Adsearchbean" />
	
	<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<style>
.hidden 
{
 display: none;
}
</style>

<script src="<%= request.getContextPath()%>/script/campusAjax.js"></script>

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
		} catch (Exception e) {
			out.println(e.toString());
		} finally {
			sc.clear();
		}
	%>

	<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>

	<!-- DON"T CHANGE THIS DIV TAG ID VALUE -->
	<div id="displayTag">

		<display:table name="sessionScope.SearchArrylist" id="Searchbean"
			pagesize="20" class="dataTable" sort="list" defaultsort="6"
			defaultorder="descending"
			requestURI="/Simples/simpleSearchdisplay.jsp" export="true">

			<display:setProperty name="basic.msg.empty_list" value="" />
			<display:setProperty name="export.excel" value="true" />
			<display:setProperty name="export.csv" value="true" />
			<display:setProperty name="export.pdf" value="false" />

			<display:setProperty name="export.excel.filename"
				value="SimpSearch.xls"></display:setProperty>
			<display:setProperty name="export.csv.filename"
				value="SimpSearch.csv"></display:setProperty>
			<display:setProperty name="export.pdf.filename"
				value="SimpSearch.pdf"></display:setProperty>


			<display:column property="accno" title="Acces No"
				maxLength="25">
			</display:column>

		<display:column property="title" title="Title" class="hidden" headerClass="hidden"> 
	    </display:column>

			<display:column title="Title" media="html" maxLength="300" sortable="true">
				<c:choose>
					<c:when
						test="${Searchbean.title ne ''}">

						<c:url var="fullViewURL" value="SimpleServlet">
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

<%-- 			<display:column property="title" sortable="true" title="Title" --%>
<%-- 				maxLength="10000" media="html"> --%>
<%-- 				<c:choose> --%>

<%-- 					<c:url var="SimpleServlet" value="SimpleServlet"> --%>
<%-- 						<c:param name="accno" value="${Searchbean.accno}" /> --%>
<%-- 						<c:param name="branch" value="${Searchbean.branch}" /> --%>
<%-- 					</c:url> --%>

<%-- 					<a href="${SimpleServlet}"> <font color="red" size="2"> --%>
<%-- 							<c:out value="${Searchbean.accno}"></c:out> --%>
<!-- 					</font> -->
<!-- 					</a> -->

<%-- 				</c:choose> --%>

<%-- 			</display:column> --%>

			<display:column property="author" sortable="true"
				title="Author&nbsp;Name" maxLength="100">
			</display:column>


<display:column property="edition" sortable="true"
				title="Edition" maxLength="25">
			</display:column>
			
			
			<display:column property="year" sortable="true"
				title="Year" maxLength="25">
			</display:column>
			
			
			
			<display:column property="branch"
				title="Campus" maxLength="45"
				sortable="true">
			</display:column>

			<display:column property="callno"
				title="Call No" maxLength="25">
			</display:column>

			<display:column property="location" title="Rack/Shelf" maxLength="50"
				sortable="true">
			</display:column>



			<%-- <display:column property="availability" title="Status" class="hidden"
				headerClass="hidden">
			</display:column> --%>
			<display:column title="Status" media="html">
				<c:choose>
					<c:when
						test="${Searchbean.availability eq 'ISSUED' || Searchbean.availability eq 'REFISSUED'}">

						<c:url var="userReserveURL" value="AccountServlet">
							<c:param name="reservecheck" value="${Searchbean.accno}" />
							<c:param name="document" value="${Searchbean.document}" />
						</c:url>

						<a href="${userReserveURL}"> <font color="red" size="2">
								<c:out value="${Searchbean.availability}"></c:out>
						</font>
						</a>

					</c:when>

					<c:otherwise>
						<c:out value="${Searchbean.availability}"></c:out>
					</c:otherwise>
				</c:choose>
			</display:column>
			
			<%-- <display:column title="ILL"  maxLength="25"
				sortable="true">
				
				<input type=button name="FindBranch" Value="&nbsp;ILL&nbsp;"  Class="submitButton" onclick="illReq()">
				
			</display:column> --%>

			<%-- <display:column property="document"  title="Document"  maxLength="25" > 	
	   </display:column> --%>


			<%-- <display:column   title="ILL"  maxLength="25" > 	
	   <c:out>
				<input type=button name="FindBranch" Value="ILL Request"  Class="submitButton"></c:out>
	   </display:column> --%>

			<display:setProperty name="basic.empty.showtable" value="true" />

		</display:table>


		<p align="center">
			<input type=button name=New Class="submitButton" Value="Back"
				onclick='gotoHome()'>
		</p>
		<script>
			function gotoHome() {
				location.href = "/AutoLib/Simples/SimpleServlet?flag=load";
			}
			
			function illReq()
			{
				alert('Please Contact your Librarian to borrow this book on Inter-Library Loan');
			}
		</script>

	</div>