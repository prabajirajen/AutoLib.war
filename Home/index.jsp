<%
String URole=session.getAttribute("UserRights").toString().trim();
if( URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<html>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<body background="/AutoLib/soft.jpg">
<form name="Home" method="post" action=./HomeServlet>
<br>
<br>
<br>

<%
byte[] UserPhoto=null;
UserPhoto=(byte[])session.getAttribute("UserPhoto");
%>




<table align="right" width="75%"  border="0" cellpadding="0" cellspacing="0">

<tr><td>



<table width="90%" id="campusnewsheadBoard" border="0" align="right" cellpadding="1" cellspacing="1" >
<tr>
	<td width="50%">
	<table width="95%" id="campusnewsheadBoard" border="0" align="center" cellpadding="2" cellspacing="2" class="indexTab">
	<tr><td Class="addedit">User&nbsp;Id</td><td Class="addedit">:&nbsp;<c:out value="${member_code}"/></td>
	<td Class="addedit">Department:</td><td Class="addedit">:&nbsp;<c:out value="${dname}"/></td>
	
<c:set var="str" scope="session" value="${userImage}"/>
<c:choose>
<c:when test="${str ne null}">
<td align="right" rowspan="3">
<img src="<%=request.getContextPath() %>/Home/photo.jsp" height="75px" width="70px" align=left/></td>
</c:when>
<c:otherwise>
<td align="right" rowspan="3">
<img src="<%=request.getContextPath() %>/images/noimage.jpg" height="75px" width="70px" align=left/></td>
</c:otherwise>
</c:choose>
	</tr>

	<tr><td  Class="addedit">User&nbsp;Name</td><td Class="addedit">:&nbsp;<c:out value="${member_name}"/></td>
	<td  Class="addedit">Valid&nbsp;Date</td><td Class="addedit">:&nbsp;<c:out value="${expiry_date}"/></td>
	</tr>
	<tr><td Class="addedit">Designation</td><td Class="addedit">:&nbsp;<c:out value="${dsname}"/></td></tr>
	<tr><td>
	</td></tr>
	</table></td></tr></table>
	
	
	
	
	
	
	
	
	
	</td></tr></table>

<table width="100%"  border='0' cellpadding="0" cellspacing="0">

<tr>
<td>
<table width="98%" id="campusnewsheadBoard" border="0" align="center" cellpadding="1" cellspacing="1" >
		<tr>
		<br>
			<td width="50%">

				<table width="100%" id="campusnewsheadBoard" border="0" align="center" cellpadding="2" cellspacing="2" class="indexTab">
					<tr> <td colspan="2" align="center" bgcolor="#104E8B" id="campusnewsheadBoard">Total Collection and Transaction Details </td> </tr> 
					<tr>
						<td colspan="2" align="center" id="bodysearchBoard">
							<table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
								<tr>
								
                                 <td align="center">
										<c:if test="${TotalCollection eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view total collection.">
												<br>
												Total Collection (0)
											</a>
										</c:if>
										<c:if test="${TotalCollection ne 0}">
											<c:url var="libraryCollectionURL" value="LibCollectionServlet" >
                                        <c:param name="flag" value="pdf" />
											</c:url>
											<a href="<%= request.getContextPath() %>/LibraryCollection/<c:out value="${libraryCollectionURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view total collection.">
												<br>
												Total Collection (<c:out value="${TotalCollection}"/>)
											</a>
										</c:if>
									</td>
									
									
                                 <td align="center">
										<c:if test="${TotalMember eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view total user.">
												<br>
												Total Member (0)
											</a>
										</c:if>
										<c:if test="${TotalMember ne 0}">
											<c:url var="memberReportURL" value="MemberReportServlet">
											<c:param name="flag" value="print" />
											<c:param name="flag1" value="Details"/>
											
											
											<c:param name="name" value="ALL"/>
											
											<c:param name="name" value="ALL"/>
											<c:param name="desig" value="ALL"/>
											<c:param name="group" value="ALL"/>
											<c:param name="course" value="ALL"/>
											<c:param name="branch" value="ALL"/>
											<c:param name="year" value="ALL"/>
											<c:param name="gender" value="ALL"/>
											<c:param name="status" value="ALL"/>
											<c:param name="mcodefrom" value=""/>
											<c:param name="mcodeto" value=""/>
											
											<c:param name="lock" value="ALL"/>
											<c:param name="order1" value="Member_Name"/>
											<c:param name="order2" value="NO"/>
											<c:param name="order3" value="NO"/>
											<c:param name="sorting" value="Asc"/>
											<c:param name="status" value="ACTIVE"/>
											
											
												
												
												
												
																													
											</c:url>
											<a href="<%= request.getContextPath() %>/MemberReport/<c:out value="${memberReportURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/Member.png" width="35" height="35" border="0" align="middle" title="Click here to, view total user.">
												<br>
												Total Member (<c:out value="${TotalMember}"/>)
											</a>
										</c:if>
									</td>									
									


                                 <td align="center">
										<c:if test="${DueListCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryDue.png" width="35" height="35" border="0" align="middle" title="Click here to, view total due user.">
												<br>
												Due List (0)
											</a>
										</c:if>
										<c:if test="${DueListCount ne 0}">
											<c:url var="duereminderURL" value="CounterReportsAll">
												<c:param name="reporttype" value="Duereminder" />
												
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />
												<c:param name="flagExcel" value="pdfReport" />
												<c:param name="curIssue" value="NO" />
												<c:param name="gettodayreport" value="NO" />
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />
																							
																																			
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${duereminderURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryDue.png" width="35" height="35" border="0" align="middle" title="Click here to, view total due user.">
												<br>
												Due List (<c:out value="${DueListCount}"/>)
											</a>
										</c:if>
									</td>    
                </tr>
                
                <tr>
                                 <td align="center">
										<c:if test="${IssueListCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/issuedBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view total issue list." >
												<br>
												Issued List (0)
											</a>
										</c:if>
										<c:if test="${IssueListCount ne 0}">
											<c:url var="issueListURL" value="CounterReportsAll">
												<c:param name="reporttype" value="Issue" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />	
												<c:param name="flagExcel" value="pdfReport" />	
												<c:param name="curIssue" value="YES" />	
												<c:param name="gettodayreport" value="NO" />	
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />																																
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${issueListURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/issuedBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view total issue list." >
												<br>
												Issued List (<c:out value="${IssueListCount}"/>)
											</a>
										</c:if>
									</td>									


                                 <td align="center">
										<c:if test="${ReturnListCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/returnBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view total return list." >
												<br>
												Returned List (0)
											</a>
										</c:if>
										<c:if test="${ReturnListCount ne 0}">
											<c:url var="returnListURL" value="CounterReportsAll">
												<c:param name="reporttype" value="Return" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />		
												<c:param name="flagExcel" value="pdfReport" />	
												<c:param name="curIssue" value="NO" />	
												<c:param name="gettodayreport" value="NO" />
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />																				
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${returnListURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/returnBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view total return list." >
												<br>
												Returned List (<c:out value="${ReturnListCount}"/>)
											</a>
										</c:if>
									</td>									


                                 <td align="center">
										<c:if test="${RenewListCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/renewBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view total renew list." >
												<br>
												Renewed List (0)
											</a>
										</c:if>
										<c:if test="${RenewListCount ne 0}">
											<c:url var="renewListURL" value="CounterReportsAll">
												<c:param name="reporttype" value="Renewal" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />		
												<c:param name="flagExcel" value="pdfReport" />	
												<c:param name="curIssue" value="NO" />
												<c:param name="gettodayreport" value="NO" />	
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />																				
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${renewListURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/renewBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view total renew list." >
												<br>
												Renewed List (<c:out value="${RenewListCount}"/>)
											</a>
										</c:if>
									</td>									


									</tr></table></td></tr></table></td>
									
									
								<td>
							
								<table width="100%" id="campusnewsheadBoard" border="0" align="center" cellpadding="2" cellspacing="2" class="indexTab">
								<tr> <td colspan="2" align="center" bgcolor="#104E8B" id="campusnewsheadBoard">Today Transaction Details </td> </tr>
								<tr>
								
								<td colspan="2" align="center" id="bodysearchBoard">
								<table width="100%" border="0" align="center" cellpadding="5" cellspacing="0">
								<tr>
								<td align="center">
									
									<c:if test="${todayIssueListCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/issuedBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view today issue list." >
												<br>
												Issued List (0)
											</a>
										</c:if>
											<c:if test="${todayIssueListCount ne 0}">
											<c:url var="userIssueCountURL" value="CounterReportsAll">
											    <c:param name="gettodayreport" value="todayIssue"/>
											    <c:param name="reporttype" value="" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value=""/>
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />	
												<c:param name="flagExcel" value=""/>	
												<c:param name="curIssue" value=""/>	
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />
												
											   
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${userIssueCountURL}"/>" id="bodysearch">
												
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today issue details.">
												<br>
												Issued Books (<c:out value="${todayIssueListCount}"/>)
											</a>
										</c:if>
									
									
								</td>
										
										
								<td align="center">
								
								<c:if test="${todayReturnListCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/returnBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view today return list." >
												<br>
												Returned List (0)
											</a>
										</c:if>
										<c:if test="${todayReturnListCount ne 0}">
											<c:url var="userReturnCountURL" value="CounterReportsAll">
											    <c:param name="gettodayreport" value="todayReturn"/>
											    <c:param name="reporttype" value="" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />	
												<c:param name="flagExcel" value=""/>	
												<c:param name="curIssue" value=""/>	
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />
																													
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${userReturnCountURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/returnBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view total return list." >
												<br>
												Returned List (<c:out value="${todayReturnListCount}"/>)
											</a>
										</c:if>
								
								</td>
								
								
								  <td align="center">
								  
								  <c:if test="${todayRenewListCount eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/renewBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view today renew list." >
												<br>
												Renewed List (0)
											</a>
										</c:if>
										<c:if test="${todayRenewListCount ne 0}">
											<c:url var="userRenewalCountURL" value="CounterReportsAll">
											    <c:param name="gettodayreport" value="todayRenewal"/>
											    <c:param name="reporttype" value="" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />	
												<c:param name="flagExcel" value=""/>	
												<c:param name="curIssue" value=""/>	
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />																				
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${userRenewalCountURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/renewBook.png" width="35" height="35" border="0" align="middle" title="Click here to, view today renew list." >
												<br>
												Renewed List (<c:out value="${todayRenewListCount}"/>)
											</a>
										</c:if>
								  </td>
							
										</tr>
										
										
										<tr>
										
										<td align="center">
										
										<c:if test="${todayTransAmount eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today total amount">
												<br>
												TotalAmount (0.00)
											</a>
										</c:if>
										<c:if test="${todayTransAmount ne 0.00}">
											<c:url var="todayreturnFineURL" value="CounterReportsAll">
											   
											    <c:param name="gettodayreport" value="todayamountdisplay"/>
											     <c:param name="reporttype" value="" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />	
												<c:param name="flagExcel" value=""/>	
												<c:param name="curIssue" value=""/>	
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${todayreturnFineURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today total amount.">
												<br>
												TotalAmount (<c:out value="${todayTransAmount}"/>)
											</a>
										</c:if>
										</td>
										
										
										<td align="center">
										<c:if test="${todaypaidAmount eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today paid amount.">
												<br>
												Paid (0.00)
											</a>
										</c:if>
										<c:if test="${todaypaidAmount ne 0.00}">
									<c:url var="todaypaidURL" value="CounterReportsAll">
											   
											    <c:param name="gettodayreport" value="todaypaidamountdisplay"/>
											    <c:param name="reporttype" value="" />
												<c:param name="txtdoctype" value="" />
												<c:param name="txtaccessno" value="" />
												<c:param name="txtmembercode" value="" />
												<c:param name="txtfdate" value="" />
												<c:param name="txttdate" value="" />
												<c:param name="report_type" value="" />
												<c:param name="order1" value="NO" />
												<c:param name="order2" value="NO" />
												<c:param name="order3" value="NO" />												
												<c:param name="Dname" value="" />
												<c:param name="Gname" value="" />	
												<c:param name="flagExcel" value=""/>	
												<c:param name="curIssue" value=""/>	
												<c:param name="mcodefrom" value="" />
												<c:param name="mcodeto" value="" />
											</c:url>
											<a href="<%= request.getContextPath() %>/CounterReport/<c:out value="${todaypaidURL}"/>" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today paid amount.">
												<br>
												Paid (<c:out value="${todaypaidAmount}"/>)
											</a>
										</c:if>
										</td>
										
										 <%-- <td align="center">
										 
										 <c:if test="${todayBalAmount eq 0.00}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today balance amount.">
												<br>
												Balance (0.00)
											</a>
										</c:if>
										
										<c:if test="${todayBalAmount ne 0.00}">
										
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today balance amount.">
												<br>
												<font id="profileLink">Balance (<c:out value="${todayBalAmount}"/>)</font>
										
										</c:if>
										 </td> --%>
										 
										 
										 <td align="center">
										 
										 <c:if test="${todayGateActiveLogin eq 0}">
											<a href="#" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today balance amount.">
												<br>
												Gate Active Login (0)
											</a>
										</c:if>
										
										<c:if test="${todayGateActiveLogin ne 0}">
										<a href="<%= request.getContextPath() %>/Account/AccountServlet?flag=register" id="bodysearch">
												<img src="<%= request.getContextPath() %>/iconImages/LibraryCollection.png" width="35" height="35" border="0" align="middle" title="Click here to, view today balance amount.">
												<br>
												<font id="profileLink">Gate Active Login (<c:out value="${todayGateActiveLogin}"/>)</font>
										</a>
										</c:if>
										 </td>
										
										
										
										</tr>
										
								</table>
								</tr>
								
								</table>
								
								</td>	
									</tr></table></tr></table>
						
				<br>

				
				<br>
				<table width="98%" border="0" id="campusnewsheadBoard" align="center" cellpadding="0" cellspacing="0" class="indexTab" >
					<tr> <td colspan="2" align="center"  bgcolor="#104E8B" id="campusnewsheadBoard">Related Links</td> </tr> 
					<tr>
						<td colspan="2" align="center" id="bodysearch">
							<table width="90%" border="0" align="center" cellpadding="2" cellspacing="0">
								<tr>
								
								<c:choose>
								<c:when test="${UserRights ne 7 or UserRights ne 8}">									
								<td align="center">
									<a href="<%= request.getContextPath() %>/Account/" id="bodysearch" title="Click Here to see my transactions">
										<img src="<%= request.getContextPath() %>/iconImages/checkAccount.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">My Transaction</font>
									</a>
								</td>
								</c:when>
								</c:choose>	
																
								<td align="center">
									<a href="<%= request.getContextPath() %>/Simples/SimpleServlet?flag=load" id="bodysearch" title="Click Here to search resource">
										<img src="<%= request.getContextPath() %>/iconImages/searchBook.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Search</font>
									</a>
								</td>
								
								<td align="center">
									<a href="<%= request.getContextPath() %>/Browse/BrowseSearch?flag=load" id="bodysearch" title="Click Here to search resource">
										<img src="<%= request.getContextPath() %>/iconImages/searchBook.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Quick Search</font>
									</a>
								</td>									
								
								<c:choose>
								<c:when test="${UserRights le 2 or UserRights eq 6}">								
								<td align="center">
									<a href="<%= request.getContextPath() %>/Counter/" id="bodysearch" title="Click Here to transactions">
										<img src="<%= request.getContextPath() %>/iconImages/circulation.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Counter</font>
									</a>
								</td>								
								</c:when>																
								</c:choose>
								
								<c:choose>
								<c:when test="${UserRights eq 1}">								
								<td align="center">
									<a href="<%= request.getContextPath() %>/Email_Reminder/" id="bodysearch" title="Click Here to send E-Mail or SMS">
										<img src="<%= request.getContextPath() %>/iconImages/SendMessage.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Due Reminder</font>
									</a>
								</td>								
								</c:when>																
								</c:choose>
								
								
								<c:choose>
								<c:when test="${UserRights le 2 or UserRights eq 3}">																	
								<td align="center">
									<a href="<%= request.getContextPath() %>/Book/BookServlet?flag=loadBook&doc_type=BOOK" id="bodysearch" title="Click Here to save/edit book details">
										<img src="<%= request.getContextPath() %>/iconImages/catalogingBook.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Book</font>
									</a>
								</td>
								</c:when>
								</c:choose>
								
								<c:if test="${UserBranchID le 2}">	
								<c:choose>
								<c:when test="${UserRights le 2 or UserRights eq 3}">									
								<td align="center">
									<a href="<%= request.getContextPath() %>/Member/" id="bodysearch" title="Click Here to save/edit user details">
										<img src="<%= request.getContextPath() %>/iconImages/catalogingMember.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Member</font>
									</a>
								</td>
								</c:when>
								</c:choose>
								</c:if>

								<c:choose>
								<c:when test="${UserRights le 2 or UserRights eq 6}">	
								<td align="center">
									<a href="<%= request.getContextPath() %>/CounterReport/" id="bodysearch" title="Click Here to view trasaction report">
										<img src="<%= request.getContextPath() %>/iconImages/bookPrint.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Counter Report</font>
									</a>
								</td>
								</c:when>
								<c:otherwise>
								<td align="center">
									<a href="<%= request.getContextPath() %>/AccessionRegister/" id="bodysearch" title="Click Here to view trasaction report">
										<img src="<%= request.getContextPath() %>/iconImages/bookPrint.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Accession Report</font>
									</a>
								</td>
								</c:otherwise>
								</c:choose>
								
								<c:if test="${UserBranchID le 2}">	
								<c:choose>
								<c:when test="${UserRights le 2 or UserRights eq 3}">									
								<td align="center">
									<a href="<%= request.getContextPath() %>/Photo/" id="bodysearch" title="Click Here to save/edit photo">
										<img src="<%= request.getContextPath() %>/iconImages/photoSave.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Photo</font>
									</a>
								</td>	
								</c:when>
								</c:choose>
								</c:if>
								
								
								
								<c:choose>
								<c:when test="${UserRights ne 2}">	
								
								<td align="center">	
									<a href="<%= request.getContextPath() %>/EResourceSearch/" id="bodysearch" title="Click Here to E-Links">
										<img src="<%= request.getContextPath() %>/iconImages/eLink.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">E-Links</font>
									</a>
								</td>
								</c:when>
								</c:choose>	
								
								<c:choose>
								<c:when test="${UserRights ne 2}">	
								<td align="center">
							<a href="<%= request.getContextPath() %>/QBSearch/QBSearchServlet?flag=load" id="bodysearch" title="Click Here to search Question Bank">
							<img src="<%= request.getContextPath() %>/iconImages/questionbank.png" width="40" height="40" border="0" align="middle">
							<br><font id="profileLink">QuestionBank</font></a>	
							</td>
								</c:when>
								</c:choose>	
								
								<%-- <c:choose>
								<c:when test="${UserRights ne 2}">	
								<td align="center">
							<a href="<%= request.getContextPath() %>/Counter/test.jsp" id="bodysearch" title="Click Here to Tag RFID">
							<img src="<%= request.getContextPath() %>/iconImages/tagRfid.png" width="40" height="40" border="0" align="middle">
							<br><font id="profileLink">Tag Merging</font></a>	
							</td>
								</c:when>
								</c:choose> --%>
								
									
								<c:choose>
								<c:when test="${UserRights ne 2}">	
								<td align="center">
							<a href="<%= request.getContextPath() %>/Binding_Books/BindingAction?flag=load" id="bodysearch" title="Click Here to search Binding Books">
							<img src="<%= request.getContextPath() %>/iconImages/binding.png" width="80" height="40" border="0" align="middle">
							<br><font id="profileLink">Binding Books</font></a>	
							</td>
								</c:when>
								</c:choose>
								
								
								
								
								
								
								
								
								
								<c:choose>
								<c:when test="${UserRights le 1}">									
								<td align="center">
									<a href="<%= request.getContextPath() %>/Backup/" id="bodysearch" title="Click Here to take backup">
										<img src="<%= request.getContextPath() %>/iconImages/backup.png" width="35" height="35" border="0" align="middle" >
											<br>
									<font id="profileLink">Backup</font>
									</a>
								</td>
								</c:when>
								</c:choose>	

								</tr>
							</table>
							
							
							<br>
 
							
						</td>
					</tr>
					
					<table width="98%" border="0" id="campusnewsheadBoard" align="center" cellpadding="0" cellspacing="0" class="indexTab" >
							
							<tr> <td  align="center"  bgcolor="#104E8B" id="campusnewsheadBoard">Suggestion</td> </tr>
							
							<tr>
						<td colspan="2" align="center" id="bodysearch">
							<table width="95%" border="0" align="center" cellpadding="2" cellspacing="0">
							
		       
		       <tr>
		          <div align="center" id="notificationsDiv">
							
							
							
							
							</div>
		       </tr>
		       <tr>
	                  <td>
	               <%--   <c:url var="SuggestionURL" value="SuggestionDisplayServlet">
											    <c:param name="frmdt" value=""/>
											     <c:param name="todt" value="" />
												<c:param name="type" value="ALL" />
											</c:url>suggestion
	                  <a href="<%= request.getContextPath() %>/Suggestion/<c:out value="${SuggestionURL}"/>id="bodysearch">
<!-- 	                  </a> -->
	                    <p align="center"><b><a href="<%= request.getContextPath() %>/Suggestion/SuggestionDisplayServlet?flag=suggestion&type=ALL&frmdt=#&todt=#" style="color: #006400" >See Suggestions</a></b></p> --%>
	                    <p align="center"><b><a href="<%= request.getContextPath() %>/Suggestion/Searchindex.jsp" style="color: #006400" >See Suggestions</a></b></p>
					 </td>
				</tr>
				
						
			</table>
		</td>	
  </tr>
  </table>
					
				</table>									
									

											
</form>									
</body>
</html>

<%
session.setAttribute("TotalCollection", "0");
session.setAttribute("TotalMember", "0");
session.setAttribute("DueListCount", "0");	

session.setAttribute("IssueListCount", "0");
session.setAttribute("ReturnListCount", "0");
session.setAttribute("RenewListCount", "0");
%>
