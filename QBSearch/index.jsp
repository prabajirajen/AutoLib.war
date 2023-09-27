
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp"
	import="java.util.*" import="Common.Security"
	 %>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />

<html>

<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">

</head>
<body>
<script language="javascript">
		function validate() {

			if ((query.Title.value == "") && (query.Author.value == "")
					&& (query.Call_no.value == "")
					&& (query.Publisher.value == "")
					&& (query.subject.value == "")
					&& (query.Keywords.value == "") && (query.Year.value == "")
					&& (query.acc_no.value == "") && (query.isbn.value == "")) {
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

<form method="get" name="query" action="./QBSearchServlet"
	ONSUBMIT="return validate(this)"><br>
<br>
<br>
<br>

<h2>Question Bank Search</h2>

<%if (request.getParameter("flags") != null) {

			%>
<h3>-- Record Not Found --</h3>
<%}

		%>

<table align="center" class="contentTable" width="45%">

<tr>
	<td>
	<table align="center" width="85%">
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		
<tr>

<td Class="addedit">Sub Name</td>
<td colspan=4><input type="text" name="subjectName" size="60" maxlength=60></td>
</tr>

		
<tr>
<td Class="addedit">Sub Code</td>
<td><input type="text" name="subject" size="17" maxlength=60></td>
	<td Class="addedit">Semester</td><td><select size="1" name="semester" style="width: 130px">
			
			<option value="ALL">ALL</option>
			<option value="ODD">ODD</option>
			<option value="EVEN">EVEN</option>
			
			</select>
		
</tr>


<tr>
			
		
			<td Class="addedit" >Course&nbsp;Name</td><td> <select size="1" name="qcourse" style="width: 130px">
				<option value="NO">ALL</option>
				<c:if test="${courseSearchList ne null }">
					<c:forEach items="${courseSearchList}" var="std" varStatus="loop">
						<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /> - <c:out value="${std.desc}" /></option>
					</c:forEach>
				</c:if>
			</select></td>
			
			<td Class="addedit" >Dept&nbsp;Name</td><td> <select size="1" name="dname"  style="width: 130px">
				<option value="NO">ALL</option>
				<c:if test="${departmentSearchList ne null }">
					<c:forEach items="${departmentSearchList}" var="std" varStatus="loop">
						<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
					</c:forEach>
				</c:if>
			</select></td>
			</tr>
		
		
		
		<tr>
			

			
			
			<td Class="addedit" >Month<td> <select size="1" name="months" style="width: 130px">
			<option value="NO">ALL</option>
			 <option value="JANUARY">JANUARY</option>
			<option value="FEBRUARY">FEBRUARY</option>
			   <option value="MARCH">MARCH</option>
			   <option value="APRIL">APRIL</option>
			     <option value="MAY">MAY</option>
		  	    <option value="JUNE">JUNE</option>
			    <option value="JULY">JULY</option>
			  <option value="AUGUST">AUGUST</option>
		   <option value="SEPTEMBER">SEPTEMBER</option>
		     <option value="OCTOBER">OCTOBER</option>
	        <option value="NOVEMBER">NOVEMBER</option>
		    <option value="DECEMBER">DECEMBER</option>
			</select></td>
			
			
			
		
		<td Class="addedit">Year</td>
		<td><input type="text" name="Year" size="17" maxlength=10></td>
		
		
		</tr>
		
		<tr>
							<c:if test="${BranchList ne null }">
								<td Class="addedit">Campus</td>
								<td><SELECT size="1" name="txtBranch"
									style="width: 250px">
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
						
						</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		
		
		<tr>
			<td colspan=5>
			<p align="center">
			<input type="hidden" name="flag"> <input type="submit" Class="submitButton" value="search" name="hid"> 
			<input type="reset" Class="submitButton" value="Clear" name="B2"></p></td></tr>
	
		
			
			</table></td></table></form>
		
			

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



		
		function Year_val() {//SHEK
			if((isNaN(document.query.Year.value))||(document.query.Year.value == ' ')) {
			document.query.Year.select();
			document.query.Year.value="";
			return false;
			   }
			}
		
		
	</script>

</body>

</html>

