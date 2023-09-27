
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.util.*" import="Common.Security"
	import="Lib.Auto.Branch.BranchBean"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<!-- #include file="logo.html" -->
<html>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui-flick.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchAll2.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/datepicker2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchEBookMasAll.js"></script>

</head>
<body>
	<script language="javascript">
		function validate() {

			if ((query.Title.value == "") && (query.Author.value == "")
					&& (query.Call_no.value == "")
					&& (query.Publisher.value == "")
					&& (query.subject.value == "")
					&& (query.Keywords.value == "") && (query.Year.value == "")
					&& (query.acc_no.value == "") && (query.isbn.value == "")) && (query.location.value == "")) {
				alert("Please Enter Valid information !");
				return false;
			}
			if (query.acc_no.value.length != "") {

				var len = 3;
				query.acc_no.value.length = len;
			}

			if ((query.Title.value.length != "")
					|| (query.Author.value.length != "")
					|| (query.Publisher.value.length != "")
					|| (query.subject.value.length != "")
					|| (query.Keywords.value.length != "")
					|| (query.Year.value.length != "")) {
				if (((query.Title.value.length) + (query.Author.value.length)
						+ (query.Publisher.value.length)
						+ (query.subject.value.length)
						+ (query.Keywords.value.length) + (query.Year.value.length)) < 3) {
					alert("Please Enter Minimum three letters !");
					return false;
				}
			}

		}

		function lenvalidate() {
			if (query.acc_no.value != "") {

				query.acc_no.value.length = 3
			}

			if (((query.Title.value.length) + (query.Author.value.length)
					+ (query.Call_no.value.length)
					+ (query.Publisher.value.length)
					+ (query.Subject.value.length)
					+ (query.Keywords.value.length) + (query.Year.value.length)
					+ (query.acc_no.value.length) + (query.isbn.value.length)) < 3) {

				alert("Please Enter Minimum three letters !");

				return false;
			}
			return true;

		}
	</script>

	<form method="get" name="query" action="./SimpleServlet"
		ONSUBMIT="return validate(this)">
		<br> <br> <br> <br>

		<h2>Simple Search</h2>

		<%
			if (request.getParameter("flags") != null) {
		%>
		<h3>-- Record Not Found --</h3>
		<%
			}
		%>
		
			<table align="center" class="contentTable" width="45%">
			<tr>
				<td>
					<table align="center" width="90%">
						<tr>
							<td>&nbsp;</td>
						</tr>

						<tr>
							<td Class="addedit">Title</td>
							<td colspan="2"><input type="text" name="Title" size="69"
								maxlength=300 id="searchTitle" class="search" onkeyup="showTitle(this.value);"></td>
						</tr>
						<tr>
							<td Class="addedit">Author</td>
							<td><input type="text" name="Author" size="30" maxlength=255 id="searchAuthor" class="search" onkeyup="showAuthor(this.value);"></td>
							<td Class="addedit">Call.No&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="Call_no"
								size="21" maxlength=20></td>

						</tr>
						<tr>
							<td Class="addedit">Publisher</td>
							<td><input type="text" name="Publisher" size="30"
								maxlength=25></td>
							<td Class="addedit">Acc.No&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="acc_no"
								size="21" maxlength=20></td>
						</tr>
						<tr>
							<td Class="addedit">Subject</td>
							<td><input type="text" name="subject" size="30" maxlength=25 id="searchSubject" class="search" onkeyup="showSubject(this.value);"></td>
							<td Class="addedit">ISBN&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="text"
								name="isbn" size="21" maxlength=25></td>
						</tr>
						<tr>
							<td Class="addedit">Keyword</td>
							<td><input type="text" name="Keywords" size="30"
								maxlength=150></td>
							<td Class="addedit">Year&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
								type="text" name="Year" size="21" maxlength=4
								onKeyUp="return Year_val();"></td>
						</tr>

						<tr>
							<c:if test="${BranchList ne null }">
								<td Class="addedit">Campus</td>
								<td><SELECT size="1" name="txtBranch"
									style="width: 210px">
										<option value="NO">ALL</option>
										<c:forEach items="${BranchList}" var="std" varStatus="loop">

											<c:choose>
												<c:when test="${std.code eq UserBranchID }">
													<option value="<c:out value="${std.code}" />" selected><c:out
															value="${std.name}" /></option>
												</c:when>
												<c:otherwise>
													<option value="<c:out value="${std.code}" />"><c:out
															value="${std.name}" /></option>
												</c:otherwise>
											</c:choose>

										</c:forEach>
								</SELECT></td>
							</c:if>
							<td Class="addedit">Location
							<input type="text" name="location" size="21"
								maxlength=150></td>
						</tr>
						
						<tr>
						<td  Class="addedit">Availability</td>
<td>
 
 <select name="avail" size="1" style="width: 125px">
  	  <option selected value="ALL">ALL</option>
  	  <option value="YES">YES</option>
      <option value="REFERENCE">REFERENCE</option>
      <option value="DISPLAY">DISPLAY</option>            
      <option value="MISSING">MISSING</option>
      <option value="WITHDRAWN">WITHDRAWN</option>
      <option value="LOST">LOST</option>            
      <option value="DAMAGED">DAMAGED</option>
      <option value="ISSUED">ISSUED</option>
      <option value="BINDING">BINDING</option>
	  <option value="TRANSFERED">TRANSFERED</option>
	  </select></td>
	  <td Class="addedit">Edition&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
								type="text" name="edition" size="21" maxlength=4></td>
						</tr>
						
						<tr>
							<td>&nbsp;</td>
						</tr>
						
</table></td></tr>
<tr>

<td>
<center><font color="#FA1A1A">Enter the search string in any one of the field(s) eg: management</font></center>
</td></tr>


</table>

							
<p align="center">	
<input type="hidden" name="flag"> 
<input type="submit" Class="submitButton" value="search" name="hid">
<input type="reset" Class="submitButton" value="Clear" name="B2">
</p>		


	</form>



	<script language='javascript'>
		function search() {
			location.href = "index.asp"
		}

		function adv() {
			location.href = "/AutoLib/Advanced/index.jsp"
		}

		function home() {
			location.href = "/AutoLib/Home.jsp";
		}
		function newarrival() {
			location.href = "newarrivalhome.asp"
		}

		function account() {
			location.href = "/portal/admin.html";
		}

		function Logout() {
			location.href = "/AutoLib/index.html";
		}

		function Year_val() {
			if ((isNaN(document.query.Year.value))
					|| (document.query.Year.value == ' ')) {
				document.query.Year.select();
				document.query.Year.value = "";
			}
		}
	</script>
</body>

</html>

