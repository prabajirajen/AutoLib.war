<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Journal_Artical.journalArtbean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:journal_Artical
//%>
<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body onload="focusText()"  background="/AutoLib/soft.jpg" >
<br><br><br>
<h2>Journal&nbsp;Articles</h2>
<form  name="jrl" method="get" action=./JournalArtServlet>
<br><br>

<center>
<table align="center" class="contentTable" width="65%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>

    <tr>
      <td Class="addedit">Journal</td>
   <td ><input name="jcode" size="12" maxlength=12 readonly >
   <input type="button" Value="Find" name="find_name" Class="submitButton" maxlength=150 onclick='Find_Value("Nam")' >
   <input name="jname" size="50" readonly>

	</td>
    </tr>
    <tr><td Class="addedit">Atl.No.</td>
   <td ><input name="atlno" size="12" maxlength=8 onKeyUp="return journal_code_val();">
   <input type="button" Value="Find" Class="submitButton" name="find_name" maxlength=150 onclick='Find_Value("Atl")' ></td>
</tr>
<tr><td Class="addedit">Title</td>
   <td ><input name="title" size="71" >
</td>
</tr>
<tr><td Class="addedit">Author</td>
   <td ><input name="author" size="71" >
</td>
</tr>

<tr>
   <td Class="addedit">BVol.No.</td>
   <td Class="addedit"><input name="bvolno" size="12"  maxlength=11 >&nbsp; J.Vol.No. <input name="jvolno" size="14"  maxlength=11 >&nbsp;
    Issue Nos.<input name="issueno" size="16"  maxlength=11>

</tr>



    <tr>
   <td Class="addedit">Year</td>
   <td Class="addedit"><input name="year" size="12"  maxlength=4 >&nbsp;
    Month&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input name="month" size="25" >
    </tr>
    <tr>
   <td Class="addedit">Page Nos.</td>
   <td Class="addedit"><input name="page" size="23" > Subject&nbsp;&nbsp; <input name="subject" size="30" readonly>
   <input type="button" Value="Find" Class="submitButton" name="find_name1" maxlength=150 onclick='Find_Value("Sub")' >
    </tr>
    <tr>
      <td Class="addedit"> Content&nbsp;Page</td>
      <td><input type=text name=content  maxlength=200 size="21" >
	</tr>
    <tr>
      <td Class="addedit" >Keywords </td>
   <td ><input name="key" size="71" >
</td>
    </tr>



    <tr>
      <td colspan="2" align="center"><center>
        	<input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
		<input type="button" value="Save"  Class="submitButton" name="save" onclick="SaveRecord()">
		<input type="button" value="Delete"  Class="submitButton" name="delete" onclick="DeleteRecord()">
		<input type="submit" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">
		<input type="reset" value="Clear" Class="submitButton" name="clear">
		<input type="hidden" name="flag" value="search">
                <input type="hidden" name="jnlflag">
                </center>
      </td>
    </tr>
</table>
</CENTER>
</td></table></center>
</form>
</body>
</html>
<%
String valid=request.getParameter("check");
if(valid!=null){
String jcode=request.getParameter("jcode");
String jname=request.getParameter("jname");
if(request.getParameter("check")!=null){
if(valid.equals("newJournal")){

 %>
 <script language="JavaScript">

document.jrl.atlno.value="<%=bean.getAtlno()%>";
document.jrl.jcode.value="<%=jcode%>";
document.jrl.jname.value="<%=jname%>";
document.jrl.title.focus();

</script>
<%
  }

  if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";


		document.jrl.atlno.value="<%=bean.getAtlno()%>";
		document.jrl.bvolno.value="<%=bean.getBvolno()%>";
		document.jrl.title.value="<%=bean.getTitle()%>";
		document.jrl.author.value="<%=bean.getAuthor()%>";
		document.jrl.jvolno.value="<%=bean.getVolno()%>";
		document.jrl.issueno.value="<%=bean.getIssueno()%>";
		document.jrl.year.value="<%=bean.getIssueyear()%>";
		document.jrl.month.value="<%=bean.getIssuemonth()%>";
		document.jrl.page.value="<%=bean.getPages()%>";
		document.jrl.subject.value="<%=bean.getSubject()%>";
		document.jrl.key.value="<%=bean.getKeywords()%>";
		document.jrl.content.value="<%=bean.getAbstracts()%>";


                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
				document.jrl.flag.value="update";

		         	document.jrl.submit();
		   }
			    </script>

			<%
			}


	if(valid.equals("UpdateJournal")){%>
            <script language="JavaScript">
	    document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
			alert("Record Updated Successfully!");
			document.jrl.flag.value="new";
			document.jrl.submit();
		   	</script><%
			}

			if(valid.equals("DeleteJournal"))
	{
	%>
            <script language="JavaScript">
	    document.jrl.jcode.value="<%=jcode%>";
	    document.jrl.jname.value="<%=jname%>";
	    alert("Record Deleted Successfully");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script><%
	}

			if(valid.equals("SaveJournal")){
%>             <script language="JavaScript">
			 document.jrl.jcode.value="<%=jcode%>";
			 document.jrl.jname.value="<%=jname%>";
			  alert("Record Inserted Successfully!");
			 document.jrl.flag.value="new";
			document.jrl.submit();
		     </script>
			<%
			}
if(valid.equals("SuccessJournal")){
 %>
  <script language="JavaScript">

		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
		document.jrl.atlno.focus();

 </script>
<%
}
if(valid.equals("SuccessJournalAtl")){
%>
  <script language="JavaScript">

		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
		document.jrl.atlno.value="<%=bean.getAtlno()%>";
		document.jrl.bvolno.value="<%=bean.getBvolno()%>";
		document.jrl.title.value="<%=bean.getTitle()%>";
		document.jrl.author.value="<%=bean.getAuthor()%>";
		document.jrl.jvolno.value="<%=bean.getVolno()%>";
		document.jrl.issueno.value="<%=bean.getIssueno()%>";
		document.jrl.year.value="<%=bean.getIssueyear()%>";
		document.jrl.month.value="<%=bean.getIssuemonth()%>";
		document.jrl.page.value="<%=bean.getPages()%>";
		document.jrl.subject.value="<%=bean.getSubject()%>";
		document.jrl.key.value="<%=bean.getKeywords()%>";
		document.jrl.content.value="<%=bean.getAbstracts()%>";
		document.jrl.atlno.focus();

 </script>
<%
}
	if(valid.equals("FailureJournal")){%>
            <script language="JavaScript">
	    alert("Record Not Found");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script><%
			}





  }
  }
%>
<script>

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function NewRecord()
{
if(document.jrl.jcode.value=="" || document.jrl.jname.value=="")
		 {
		 alert("Please Select The Journal !");
		 document.jrl.flag.value="null";
		 }
		 else
		 {
	document.jrl.method="get";
	document.jrl.flag.value="new";
	document.jrl.submit();
	}

}

function SaveRecord(){
document.jrl.method="get";
		if((!isWhitespace(document.jrl.jname.value))&&(!isWhitespace(document.jrl.title.value))){
		
			document.jrl.flag.value="save";
		        document.jrl.submit();
			 }

	else
	{
	alert("Insufficent Data");
	}

}

function isWhitespace(str)
		{
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
		}
		
function chk(){
var flag_s;
var i;
var atl=document.jrl.title.value;
var sp=document.jrl.jname.value;
				if(sp=="" || atl=="")
				{
				document.jrl.jname.focus();
				document.jrl.flag.value="none";
				document.jrl.jname.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.jrl.jname.value.length;i++)
 	                      {
 	                       if(document.jrl.jname.value.charAt(i)==" ")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)
		                    {
		                   	document.jrl.jname.focus();
		                   	document.jrl.jname.value="";
			                return false;
		                      }
		                   else if (document.jrl.jcode.value==""){
		                 	document.jrl.jcode.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }



function DeleteRecord(){
document.jrl.method="get";
		if (document.jrl.jcode.value==""){
				document.jrl.jcode.focus();
				alert("Insufficent Data");
				document.jrl.flag.value="new";
				document.jrl.submit();
				}
			else{
				msg=confirm("Are You Sure To Delete");
					if(msg){
						document.jrl.flag.value="delete";
						document.jrl.submit();
						}
						else
						{
						//alert("Operation Cancelled..!");
						//document.jrl.flag.value="new";                         							                        	//document.jrl.action="index.jsp";
						//document.jrl.submit();
						}
				}
}

function SearchRecord()
{
document.jrl.method="get";
var no=document.jrl.atlno.value;
	if(no=="")
	{
				document.jrl.atlno.focus();
				alert("Insufficent Data");
				document.jrl.flag.value="new";
				document.jrl.submit();
	}
	else
        {
	document.jrl.flag.value="searchAtl";
	document.jrl.submit();
	}
}
function journal_code_val() {
if((isNaN(document.jrl.jcode.value))||(document.jrl.jcode.value == ' ')) {
document.jrl.jcode.select();
document.jrl.jcode.value="";

  }
}
function Find_Value(val)
{

winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,toolbar=no,status=yes,menubar=no,scrollbars=yes");
}

 function AddRecord()
	{
	document.jrl.content.value=document.jrl.Content1.value

        }

</script>


