<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" session="true" buffer="12kb" import="Common.Security" import="java.util.ArrayList"%>

<jsp:useBean id="bean" scope="page" class="Lib.Auto.frequentUser.FrequentUserBean"/>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/displaytag.css" />
<script  src="<%= request.getContextPath()%>/script/campusAjax.js"></script>


<%

ArrayList sc=new ArrayList();
try{
sc=(ArrayList)bean.getAl();
//out.print("<table>");
for(int i=0; i<sc.size();i++){
	%>
<tr>

	<script language=javascript>
	      document.write("<td><a href='?doc=<%=sc.get(i)%>'>"+"<%=sc.get(i)%>" +"</a></td>");
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

<div id="displayTag">
	<display:table name="sessionScope.SearchArrylist" id="Searchbean" pagesize="20" 
		class="dataTable" sort="list" defaultsort="2" defaultorder="ascending"
		requestURI="/Simples/simpleSearchdisplay.jsp" export="true">
		</display:table>
		
		
		<display:setProperty name="basic.msg.empty_list" value=""/>
		<display:setProperty name="export.excel" value="true" />
		<display:setProperty name="export.csv" value="true" />				
		<display:setProperty name="export.pdf" value="false" />
				
		<display:setProperty name="export.excel.filename" value="SimpSearch.xls"></display:setProperty>
		<display:setProperty name="export.csv.filename" value="SimpSearch.csv"></display:setProperty>
		<display:setProperty name="export.pdf.filename" value="SimpSearch.pdf"></display:setProperty>
		
		
       <display:column property="accno"  href='SimpleServlet' paramId="accno" paramProperty="accno" title="Access No"  maxLength="25" > 		   
	   </display:column>
	   
	   <display:column property="title"  sortable="true" title="Title"  maxLength="200" >      
	   </display:column>
	    
</div>
		


















