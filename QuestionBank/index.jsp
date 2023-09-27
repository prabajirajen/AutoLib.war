<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>


<%@ page language="java"   import="java.util.*" session="true" buffer="12kb" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="BeanObject" scope="request"  class="Lib.Auto.QuestionBank.QuestionBankBean"  type="Lib.Auto.QuestionBank.QuestionBankBean">
</jsp:useBean>


<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Keywords
//
%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" ><!-- onload="load()"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);

	
	 
%>
<form name="qbmaster" method="get" action=./QuestionBankServlet>
<br><br><br>

 
 <h2>Question&nbsp;Bank</h2>

<table align="center" class="contentTable" width="60%">
<tr>
<td>
<table align="center" width="90">
<tr><td> &nbsp; </td></tr>
<tr>
<td Class="addedit">QB Code<td> <input type=text name=qcode size=18  maxlength=5 onKeyUp="return keywords_code_val();" ></td>


<td Class="addedit">Entry Date&nbsp;&nbsp;&nbsp;&nbsp;<td> <INPUT name=qdate size=10  onfocus=this.blur(); value="<%=dateString%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,qbmaster.qdate, \"dd-mm-yyyy\",\"<%=dateString%>\")'value='...' style='font-size:10 px'>")
				}
				</SCRIPT>
</td>

</tr>

<tr>

<td Class="addedit">University<td colspan=3> <select name="uniname" size="1" id="uniname"  style="width:300px">
<option  value="The Tamilnadu Dr.M.G.R Medical University">The Tamilnadu Dr.M.G.R Medical University</option></select></td>

</tr>



<tr>
<td Class="addedit" >Sub&nbsp;Name<td colspan=3> <select size="1" name="subname" style="width: 300px">
<option value="NO">SELECT</option>
<c:if test="${QBSubjectNameSearchList ne null }" >
<c:forEach items="${QBSubjectNameSearchList}" var="std" varStatus="loop">



<option value="<c:out value="${std.name}"/>"><c:out value="${std.name}"/></option>
</c:forEach>
</c:if>
</select></td>
</tr>




<tr>
<td Class="addedit" >Sub&nbsp;Code<td> <select size="1" name="subcode" style="width: 125px">
<option value="NO">SELECT</option>
<c:if test="${QBSubjectCodeSearchList ne null }" >
<c:forEach items="${QBSubjectCodeSearchList}" var="std" varStatus="loop">



<option value="<c:out value="${std.name}" />"><c:out value="${std.name}" /></option>



</c:forEach>
</c:if>
</select></td>


<td Class="addedit" >Semester</td><td> <select size="1" name="semester" style="width: 85px">
<option value="NO">SELECT</option>
<option  value="ODD">ODD</option>
<option  value="EVEN">EVEN</option>
</select></td>



</tr>









<tr><td Class="addedit" >Dept&nbsp;Name<td colspan="3"> <select size="1" name="dname"  style="width: 300px">
<option value="NO">SELECT</option>
<c:if test="${departmentSearchList ne null }" >
<c:forEach items="${departmentSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.name}" /></option>
</c:forEach>
</c:if>
</select></td></tr>

<tr>
<td Class="addedit" >Course&nbsp;Name<td colspan="3"> <select size="1" name="qcourse" style="width: 300px">
<option value="NO">SELECT</option>
<c:if test="${courseSearchList ne null }" >
<c:forEach items="${courseSearchList}" var="std" varStatus="loop">
<option value="<c:out value="${std.code}"/>"><c:out value="${std.desc}"/></option>
</c:forEach>
</c:if>
</select>
</td></tr>


<tr>


	<td Class="addedit" >Month<td> <select size="1" name="qmonth" style="width: 125px">
			<option value="NO">SELECT</option>
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
			</select>
			
			
<td Class="addedit" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Year<td><input type=text name=qyear size=5 maxlength="4" onKeyUp="return Year_val();" style="width: 75px">
			
</tr>
<tr><td Class="addedit" >Content<td colspan="3"> <input type=text name=content size=53 maxlength="50"></td></tr>
<tr><td Class="addedit" >Remarks 1<td colspan="4"> <input type=text name=remarks1 size=53 maxlength="50"></td></tr>
<tr><td Class="addedit" >Remarks 2<td colspan="3"> <input type=text name=remarks2 size=53 maxlength="50"></td></tr>



<tr><td>&nbsp;</td>
</tr>

</table>
</td>
</tr>




</table>



<p align="center">
<input type=button name=New Class="submitButton" Value=New onclick=new_no() >
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton"  Value=Delete onclick=DelRec()>
<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>
<input type=button name=Clear Class="submitButton"  Value=Clear onclick=ClearRec()>
<input type=hidden name=flag>
</p>



</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String cSec = Security.checkSecurity(1, session, response, request);
String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){
if(valid.equals("newNews")){
 %>
<script language="JavaScript">
document.qbmaster.qcode.value="<%=BeanObject.getQcode()%>";
</script><%

}
if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
				document.qbmaster.qcode.value="<%=BeanObject.getQcode()%>";
                document.qbmaster.qdate.value="<%=BeanObject.getQdate()%>";				
                document.qbmaster.uniname.value="<%=BeanObject.getUniname()%>";
                document.qbmaster.subcode.value="<%=BeanObject.getSubcode()%>";
                document.qbmaster.subname.value="<%=BeanObject.getSubname()%>";
                document.qbmaster.dname.value="<%=BeanObject.getDname()%>";
                document.qbmaster.qcourse.value="<%=BeanObject.getQcourse()%>";
                document.qbmaster.qyear.value="<%=BeanObject.getQyear()%>";
                document.qbmaster.qmonth.value="<%=BeanObject.getQmonth()%>";
                document.qbmaster.semester.value="<%=BeanObject.getSemester()%>";
                document.qbmaster.remarks1.value="<%=BeanObject.getRemarks1()%>";
                document.qbmaster.remarks2.value="<%=BeanObject.getRemarks2()%>";
                document.qbmaster.content.value="<%=BeanObject.getContents() %>";
               
                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				    document.qbmaster.flag.value="update";
		         	document.qbmaster.submit();

		            }
			    </script>

			<%
			}
			if(valid.equals("searchKeywords")){
 %>
  <script language="JavaScript">

				document.qbmaster.qcode.value="<%=BeanObject.getQcode()%>";
				
                document.qbmaster.qdate.value="<%=BeanObject.getQdate()%>";				
                document.qbmaster.uniname.value="<%=BeanObject.getUniname()%>";

                document.qbmaster.subcode.value="<%=BeanObject.getSubcode()%>";
                document.qbmaster.subname.value="<%=BeanObject.getSubname()%>";
                
                document.qbmaster.dname.value="<%=BeanObject.getDname()%>";
                document.qbmaster.qcourse.value="<%=BeanObject.getQcourse()%>";
                document.qbmaster.qyear.value="<%=BeanObject.getQyear()%>";
                document.qbmaster.qmonth.value="<%=BeanObject.getQmonth()%>";
                document.qbmaster.semester.value="<%=BeanObject.getSemester()%>";
                
                document.qbmaster.remarks1.value="<%=BeanObject.getRemarks1()%>";
                document.qbmaster.remarks2.value="<%=BeanObject.getRemarks2()%>";
                document.qbmaster.content.value="<%=BeanObject.getContents() %>";
               
               </script>
<%
}
            if(valid.equals("FailKeywords")){%>
            <script language="JavaScript">
			alert("Record Not Found");
			document.qbmaster.flag.value="new";
			document.qbmaster.submit();
		   	</script><%
			}
			if(valid.equals("UpdateKeywords")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.qbmaster.flag.value="new";
			document.qbmaster.submit();
		   	</script><%
			}
			if(valid.equals("SaveKeyword")){
%>             <script language="JavaScript">
			 alert("Record Inserted Successfully!");
			 document.qbmaster.flag.value="new";
			 document.qbmaster.submit();
		     </script>
			<%
			}
			if(valid.equals("AlreadyExists")){

%>             <script language="JavaScript">
			 alert("Record already exists in : "+<%=request.getParameter("no")%>);
			 document.qbmaster.flag.value="new";
			 document.qbmaster.submit();
		     </script>
			<%
			}

			if(valid.equals("DeleteKeywords")){

%>
            <script language="JavaScript">
			alert("Record Deleted Successfully!");
			document.qbmaster.flag.value="new";
			document.qbmaster.submit();
		   </script>
			<%
			}


			if(valid.equals("deleteCheck")){

%>
            <script language="JavaScript">
				document.qbmaster.qcode.value="<%=BeanObject.getQcode()%>";
                document.qbmaster.qdate.value="<%=BeanObject.getQdate()%>";				
                document.qbmaster.uniname.value="<%=BeanObject.getUniname()%>";

                document.qbmaster.subcode.value="<%=BeanObject.getSubcode()%>";
                document.qbmaster.subname.value="<%=BeanObject.getSubname()%>";
                document.qbmaster.dname.value="<%=BeanObject.getDname()%>";
                document.qbmaster.qcourse.value="<%=BeanObject.getQcourse()%>";
                document.qbmaster.qyear.value="<%=BeanObject.getQyear()%>";
                document.qbmaster.qmonth.value="<%=BeanObject.getQmonth()%>";                
                
                document.qbmaster.remarks1.value="<%=BeanObject.getRemarks1()%>";
                document.qbmaster.remarks2.value="<%=BeanObject.getRemarks2()%>";
                document.qbmaster.content.value="<%=BeanObject.getContents() %>";
               

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			 document.qbmaster.flag.value="Confirmdete";
		   	 document.qbmaster.submit();

			}
			</script>
			<%
			}
}
}

%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->
<script language=javascript>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){
document.qbmaster.method="get";
document.qbmaster.flag.value="new";
document.qbmaster.submit();
}

function SearchRec(){
document.qbmaster.method="post";
var no=document.qbmaster.qcode.value;
if(no==""){
				document.qbmaster.qcode.focus();
				alert("Insufficent Data");
				document.qbmaster.flag.value="new";
				document.qbmaster.submit();

		  }else{
		       document.qbmaster.flag.value="search";
			   document.qbmaster.submit();
			  
		}
	
}


function SaveRec(){
if(chk_mc()){

             document.qbmaster.method="post";
			   if(!isWhitespace(document.qbmaster.subcode.value)){
				   
				   
				   if(document.qbmaster.subname.value=='NO')
				   {
	       			 alert("Please select Subject name");
				     return;//shek
				   }
				   
				   
				   if(document.qbmaster.subcode.value=='NO')
				   {
	       			 alert("Please select Subject code");
				     return;//shek
				   }
				   
				   
				   
				   else if(document.qbmaster.dname.value=='NO')
				   {
				     alert("Please select department name");
				     return;
				   }
				   
				   
			   
			   if(document.qbmaster.qcourse.value=='NO')
			   {
       			 alert("Please select course name");
			     return;
			   }
			   
			   if(document.qbmaster.qmonth.value=='NO')
			   {
       			 alert("Please select Month");
			     return;//shek
			   }
			 
			   

			   if(document.qbmaster.semester.value=='NO')
			   {
       			 alert("Please select Semester!");
			     return;//shek
			   }
			   
		
			   
			   
			   else {
			     document.qbmaster.flag.value="save";
		         document.qbmaster.submit();					
			   }
			       	
			}		
	else
	{
	alert("Insufficient Data");
	}
	}		
	else
	{
	alert("Insufficient Data");
	}
}
 function chk_mc(){
var flag_cs;
var c;
var mc=document.qbmaster.qcode.value;
if(mc=="")
{
				document.qbmaster.qcode.focus();
				document.qbmaster.flag.value="none";
				document.qbmaster.qcode.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }

function isWhitespace(str)
		{
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
		}



function Year_val() {//SHEK
	if((isNaN(document.qbmaster.qyear.value))||(document.qbmaster.qyear.value == ' ')) {
	document.qbmaster.qyear.select();
	document.qbmaster.qyear.value="";
	return false;
	   }
	}




function FindKeywords()
{
winpopup=window.open("search.jsp","popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function keywords_code_val() {
if((isNaN(document.qbmaster.qcode.value))||(document.qbmaster.qcode.value == ' ')) {
document.qbmaster.qcode.select();
document.qbmaster.qcode.value="";
    
  }
}
function ClearRec(){ 
		
document.qbmaster.flag.value="new";
document.qbmaster.submit();
				
}
function DelRec(){
document.qbmaster.method="post";
if (document.qbmaster.qcode.value==""){
				document.qbmaster.qcode.focus();
				alert("Insufficent Data");
				document.qbmaster.flag.value="new";
				document.qbmaster.submit();
				}
				else{
				  document.qbmaster.flag.value="delete";
				  document.qbmaster.submit();
				 }                         
				
}

</script>
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
