
<%@page import="com.ibm.icu.text.SimpleDateFormat"%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/style1.css"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-2.2.4.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchNewsClipping.js"></script>

</head>
<body >
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	String Message=null;

%>



<script language="javascript">
function validate()
{


if ((query.Title.value=="")&&(query.Author.value=="")&& (query.Call_no.value=="")&& (query.Publisher.value=="")&&(query.subject.value=="")&&(query.Keywords.value=="")&&(query.Year.value=="")&&(query.acc_no.value=="")&&(query.isbn.value==""))
{
alert("Please Enter Valid information !");
return false;
}
if(query.acc_no.value.length!=""){

var len =3;
query.acc_no.value.length=len;
}
if (((query.Title.value.length)+(query.Author.value.length)+(query.Call_no.value.length)+(query.Publisher.value.length)+(query.subject.value.length)+(query.Keywords.value.length)+(query.Year.value.length)+(query.acc_no.value.length)+(query.isbn.value.length))<3)
{

alert("Please Enter Minimum three letters !");

return false;

}

}

function lenvalidate()
{
if(query.acc_no.value!=""){

query.acc_no.value.length=3
}

if (((query.Title.value.length)+(query.Author.value.length)+(query.Call_no.value.length)+(query.Publisher.value.length)+(query.Subject.value.length)+(query.Keywords.value.length)+(query.Year.value.length)+(query.acc_no.value.length)+(query.isbn.value.length))<3)
{

alert("Please Enter Minimum three letters !");

return false;
}
return true;


}

</script>

  <form method="get" name="query" action="./NewsClipSearchServlet"  ONSUBMIT="return validate(this)">
  <br><br><br><br>

    <h2>News Clipping Search</h2>

     <%
   if(request.getParameter("flags")!=null)
   {
   %>
<h3> -- Record Not Found --</h3>
   <%
   }
   %>
   <center>
  <table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

            <tr>
              <td Class="addedit">Name</td>
              <td colspan="2"  >
            <input type="text" name="name" size="63" maxlength="40" id="searchName" class="searchNewsClipping" onkeyup="showName(this.value);" ></td>
            </tr>
            <tr>
              <td Class="addedit">Type</td>
              <td colspan="2"  >
            <input type="text" name="type" size="63" maxlength="40" id="searchType" class="searchNewsClipping" onkeyup="showType(this.value);"></td>
            </tr>
            <tr>
              <td Class="addedit">Title</td>
              <td colspan="2"  >
            <input type="text" name="title" size="63" maxlength="40" id="searchTitle" class="searchNewsClipping" onkeyup="showTitle(this.value);"></td>
            </tr>
            <tr>
              <td Class="addedit">Subject</td>
              <td colspan="2"  >
            <input type="text" name="subject" size="63" maxlength="40" id="searchSubject" class="searchNewsClipping" onkeyup="showSubject(this.value);"></td>
            </tr>
            <tr>
              <td Class="addedit">Abstract</td>
              <td colspan="2"  >
            <input type="text" name="abstract" size="63" maxlength="40" ></td>
            </tr>
            <tr>
              <td Class="addedit">Keywords</td>
              <td colspan="2"  >
            <input type="text" name="keywords" size="63" maxlength="40" id="searchKeywords" class="searchNewsClipping" onkeyup="showKeywords(this.value);"></td>
            </tr>
            
            
             <tr>
   
        <td  Class="addedit">From</td><td>
	<INPUT name=fromdt size=11  onfocus=this.blur(); value="<%=dateString%>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,query.fromdt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
				
        <td  Class="addedit">To</td><td>
	<INPUT name=todt size=11  onfocus=this.blur(); value="<%=dateString%>" >
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,query.todt, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
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

<tr><td colspan=3 >
<center>
<input type="submit" Class="submitButton" value="search" name="hid" >
<input type="reset" Class="submitButton" value="Clear" name="B2" ></center>
</table></center></td></table></center>


    </form>



<script language='javascript'>

function search()
{
location.href="index.asp"
}

function adv()
{
location.href="/AutoLib/Advanced/index.jsp"
}

function home()
{
location.href="/AutoLib/Home.jsp";
}
function newarrival()
{
location.href="newarrivalhome.asp"
}

function account()
{
location.href="/portal/admin.html";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

</script>

</body>

</html>

