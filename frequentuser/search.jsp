<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.frequentUser.FrequentUserBean" 
import="Common.Security" import="java.util.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.frequentUser.FrequentUserBean"  type="Lib.Auto.frequentUser.FrequentUserBean">
</jsp:useBean>


<div style="padding: 0px 0px 0px 0px;">
		<jsp:include page="searchdisplay.jsp" flush="true" />
	</div>
	
	<%@ include file="/Tree/back.jsp"%>

	