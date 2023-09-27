<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.QBSearch.QuestionSearchBean" import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />


<div style="padding: 0px 0px 0px 0px;">
		<jsp:include page="simpleSearchdisplay.jsp" flush="true" />
	</div>
	
<%-- 	<%@ include file="/Tree/back.jsp"%> --%>

	
<p align="center">
<input type=button name=Back Class="submitButton"  Value=SearchPage onclick='gotoHome()'>
</p>
<script>
function gotoHome(){
		location.href="/AutoLib/QBSearch/QBSearchServlet?flag=load";
}
</script>