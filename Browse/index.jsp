<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<title></title>
</head>

<body>
	<form name="top" action="./BrowseSearch" onsubmit="return validate()">

		<br> <br>
		<h2>Quick Search</h2>
		 <br> <br>
		<table align="center" class="contentTable" width="75%">
			<td>
				<table align="center" width="90%">
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;
					<tr>
						<td align=center><font face="Tahoma" size="2" color="#000080"
							class=disc><b>Browse&nbsp;:&nbsp;&nbsp;<b></font></td>
						<td><input tabindex="0" name="name" size="40" maxlength="100">
						</td>
						<td><b>&nbsp;Field&nbsp;:&nbsp;&nbsp;<b><select name="type">
										<option value="title">Title</option>
										<option value="author_name">Author</option>
										<option value="sub_name">Subject</option>
										<option value="sp_name">Publisher</option>
										<option value="Supplier">Supplier</option>
										<option value="isbn">ISBN</option>
										<option value="Dept_name">Department</option>
										<option value="Language">Language</option>
										<option value="availability">Status</option>
								</select></td>
						<td><b>&nbsp;Doc Type&nbsp;:&nbsp;&nbsp;<b><select name="doc_type">
										<option value="ALL">ALL</option>
										<option value="BOOK">BOOK</option>
										<option value="BOOK BANK">BOOK BANK</option>
										<option value="NON BOOK">NON BOOK</option>
										<option value="REPORT">REPORT</option>
										<option value="THESIS">THESIS</option>
										<option value="STANDARD">STANDARD</option>
										<option value="PROCEEDING">PROCEEDING</option>
										<option value="BACK VOLUME">BACK VOLUME</option>
								</select></td>
					<tr>
			<!--		<tr>
						<c:if test="${BranchList ne null }">
							<td><b>Campus&nbsp;:</b></td>
							<td colspan="5"><SELECT size="1" name="txtBranch"
								style="width: 260px">
									<option value="NO">ALL</option>
									<c:forEach items="${BranchList}" var="std" varStatus="loop">

										<c:choose>
											<c:when test="${std.code eq 2 }">
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
					</tr> -->
					
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
							<td Class="addedit">Location
							<input type="text" name="location" size="21"
								maxlength=150></td>
						</tr>

					<tr>
						<td>&nbsp;</td>
					</tr>

					<center>
						<td width="100%" colspan="5" align="center"><center>
								<input type="submit" Class="submitButton" value="Search">
								<input type="reset" Class="submitButton" value="Clear">
							</center></td>
						<td><input type="hidden" name="hid" value="search"></td>
					</center>

					</tr>

				</table>
			</td>
		</table>
	</form>

</body>
</html>

<script>
	function validate() {
		if (document.top.name.value == "") {
			alert("Enter Any Values");
			document.top.name.focus();
			return false;
		} else {
			if ((document.top.name.value.length) < 3) {
				alert("Atleast three letters Should Be Entered ");
				return false;
			}
			return true;
		}
	}
</script>

</body>

</html>
