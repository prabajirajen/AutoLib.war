
<%
	String URole = session.getAttribute("UserRights").toString().trim();
	if (URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5")
			|| URole.equalsIgnoreCase("6")
			|| URole.equalsIgnoreCase("7")) {
		response.sendRedirect("sessiontimeout.jsp");
	}
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb"
	import="Common.Security" import="java.util.*"
	import="Lib.Auto.Branch.BranchBean"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/button_css.css" />
<%
	//Security.checkSecurity(1,session,response,request);
%>
<jsp:useBean id="beanobject" scope="request"
	class="Lib.Auto.QBSubject.QBsubjectbean" />
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
	//
	//   Filename: Index.jsp
	//   Form name:Subject
	//
%>

<%
	ArrayList sc = new ArrayList();
	sc = (ArrayList) request.getAttribute("UserBranchList");
%>

<!--
//////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>Auto Lib</title>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>

</head>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache" />
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg" onload="load()">
	<form name="Subject" method="get" action="./QBSubjectServlet">
		<br> <br> <br>
		<P ALIGN="left">
			<BR>
		</P>
		<h2>Question Bank Subject&nbsp;Master</h2>

		<table align="center" class="contentTable" width="55%">
			<tr>
				<td>
					<table align="center" width="90%">

						<tr>
							<td>&nbsp;</td>
						</tr>


<!-- 						<tr><td Class="addedit">QB&nbsp;Code<td><input type=text name=code size=20 maxlength=20 onKeyUp="return subject_code_val();"  > -->
						<!--<input type=button name=find Value="Find" Class="submitButton" onclick=FindSubject('Subject')></td></tr>-->
						<!-- <tr><td><input type=hidden name=code size=20 maxlength=20 onKeyUp="return subject_code_val();" readOnly ></td></tr> -->
<tr><td><input type=hidden name=code size=20 maxlength=20 onKeyUp="return subject_code_val();" readOnly >
						<tr>
							<td Class="addedit">QBSubject&nbsp;Code</td>
							<td><input type="text" name="qbsubcode" size="42"
								maxlength="40">&nbsp;<input type="button" name="find"
								Value="Find" Class="submitButton" onclick="FindSubject('Subject')"><FONT
								color="red" size="4">*</FONT></td>
						</tr>
						<tr>
							<td Class="addedit">QBSubject&nbsp;Name</td>
							<td><input type="text" name="qbsubname" size="50"
								maxlength="50">
								</td>
						</tr>
						<tr>
							<td Class="addedit">ShortDesc&nbsp;</td>
							<td><input type="text" name="desc" size="50" maxlength="50"></td>
						</tr>

						<c:choose>
							<c:when test="${UserBranchID gt 0}">
								<tr>
									<td Class="addedit">Division</td>
									<td colspan="3"><SELECT size="1" name="txtBranch"
										style="width: 208px">
											<option value='<c:out value="${UserBranchID}" />'><c:out
													value="${visitBranch}" /></option>
									</SELECT></td>
								</tr>

							</c:when>
							<c:otherwise>
								<tr>
									<td Class="addedit">Division</td>
									<td colspan="3"><SELECT size="1" name="txtBranch"
										style="width: 208px">
											<%
												try {
															if (sc != null && !sc.isEmpty()) {
																Iterator it = sc.iterator();
																while (it.hasNext()) {

																	BranchBean view = (BranchBean) it.next();

																	int b_code = view.getCode();
																	String b_name = view.getName();

																	if (!b_name.equalsIgnoreCase("NIL")) {

																		out.print("<option  value=" + b_code + ">"
																				+ b_name + "</option>");

																	}
																}
															}
														} catch (Exception e) {
															e.printStackTrace();
														}
											%>
									</SELECT></td>
								</tr>
							</c:otherwise>
						</c:choose>



						<tr>
							<td colspan="2">
								<p align="center">
									<input type="button" name="New" Class="submitButton"
										Value="New" onclick="new_no()"> <input type="button"
										name="Save" Class="submitButton" value="Save"
										onclick="SaveRec()"> <input type="button"
										name="Delete" Class="submitButton" Value="Delete"
										onclick="DelRec()">
									<!--<input type=submit name=search Class="submitButton" Value=Search  onclick=SearchRec()>-->
									<input type="Reset" name="Clear" Class="submitButton"
										Value="Clear" onclick="new_no()"> <input type="hidden"
										name="flag">
								</p>
							</td>
						</tr>


					</table>
				</td>
			</tr>
		</table>
	</form>
</body>

<!-- //////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->






<%
	String valid = request.getParameter("check");
	if (valid != null) {
		if (request.getParameter("check") != null) {
			if (valid.equals("newSubject")) {
%>
<script language="JavaScript" type="text/javascript">

document.Subject.code.value="<%=beanobject.getQbcode()%>";
document.Subject.qbsubcode.focus();
</script>
<%
	}

			if (valid.equals("searchSubject")) {
%>
<script language="JavaScript" type="text/javascript">
  
document.Subject.code.value="<%=beanobject.getQbcode()%>";
document.Subject.qbsubcode.value="<%=beanobject.getQbsubcode()%>";
document.Subject.qbsubname.value="<%=beanobject.getQbsubname()%>";
document.Subject.desc.value="<%=beanobject.getQbsubdesc()%>";
document.Subject.txtBranch.value="<%=beanobject.getQbbranchcode()%>";

</script>
<%
	}
			if (valid.equals("FailSubject")) {
%>
<script language="JavaScript" type="text/javascript">
	    alert("Record Not Found");
	      document.Subject.flag.value="new";
	    document.Subject.submit();
	    </script>
<%
	}

			if (valid.equals("SaveSubject")) {
%>
<script language="JavaScript" type="text/javascript">
			 alert("Record Inserted Successfully!");

			 document.Subject.flag.value="new";
			 document.Subject.submit();
		     </script>
<%
	}

			if (valid.equals("UpdateSubject")) {
%>
<script language="JavaScript" type="text/javascript">
			alert("Record Updated Successfully!");

			document.Subject.flag.value="new";
			document.Subject.submit();
		   	</script>
<%
	}

			if (valid.equals("UpdateCheck")) {
%>
<script language="JavaScript" type="text/javascript">
                

                document.Subject.code.value="<%=beanobject.getQbcode()%>";
                document.Subject.qbsubcode.value="<%=beanobject.getQbsubcode()%>";
                document.Subject.qbsubname.value="<%=beanobject.getQbsubname()%>";
                document.Subject.desc.value="<%=beanobject.getQbsubdesc()%>";
                document.Subject.txtBranch.value="<%=beanobject.getQbbranchcode()%>";
                
                
		           
               
                msg=confirm("Subject has been referred in other masters, Are You Sure To Update?");
                 if(msg)
                   {
				document.Subject.flag.value="update";
		         	document.Subject.submit();
		   }
			    </script>

<%
	}

			if (valid.equals("Updatename")) {
%>
<script language="JavaScript" type="text/javascript">

		                document.Subject.code.value="<%=beanobject.getQbcode()%>";
		                document.Subject.qbsubcode.value="<%=beanobject.getQbsubcode()%>";
		                document.Subject.qbsubname.value="<%=beanobject.getQbsubname()%>";
		                document.Subject.desc.value="<%=beanobject.getQbsubdesc()%>";
		                document.Subject.txtBranch.value="<%=beanobject.getQbbranchcode()%>";
		                		                
		                msg=confirm("Record Already Exists Are You Sure To Update?");
		               
		                 if(msg)
		                   {
						document.Subject.flag.value="update";
				         	document.Subject.submit();
				   }
					    </script>

<%
	}

			if (valid.equals("deleteCheck")) {
%>
<script language="JavaScript" type="text/javascript">

           	document.Subject.code.value="<%=beanobject.getQbcode()%>";
           	document.Subject.qbsubcode.value="<%=beanobject.getQbsubcode()%>";
           	document.Subject.qbsubname.value="<%=beanobject.getQbsubname()%>";
           	document.Subject.desc.value="<%=beanobject.getQbsubdesc()%>";
           	document.Subject.txtBranch.value="<%=beanobject.getQbbranchcode()%>";         

			msg=confirm("Are You Sure To Delete?");
			if(msg){
			document.Subject.flag.value="Confirmdelete";
		   	document.Subject.submit();

			}
			</script>
<%
	}

			if (valid.equals("RecordNot")) {
%>
<script language="JavaScript" type="text/javascript">
            

            document.Subject.code.value="<%=beanobject.getQbcode()%>";
            document.Subject.qbsubcode.value="<%=beanobject.getQbsubcode()%>";
            document.Subject.qbsubname.value="<%=beanobject.getQbsubname()%>";
            document.Subject.desc.value="<%=beanobject.getQbsubdesc()%>";
            document.Subject.txtBranch.value="<%=beanobject.getQbbranchcode()%>";
            
            
				
                alert("Record Not Present!!!");
                document.Subject.flag.value="new";
                document.Subject.submit();
                
			</script>
<%
	}

			if (valid.equals("ReferredSubject")) {
%>
<script type="text/javascript">alert("You can't delete since the Subject has been referred in other masters");

			 document.Subject.flag.value="new";
			 document.Subject.submit();
			</script>
<%
	}

			if (valid.equals("DeleteSubject")) {
%>
<script language="JavaScript" type="text/javascript">
			alert("Record Deleted Successfully!");

			document.Subject.flag.value="new";
			document.Subject.submit();
		   </script>
<%
	}

			if (valid.equals("DefaultSubject")) {
%>
<script language="JavaScript" type="text/javascript">
							alert("Default Subject Cannot be Deleted!");

							document.Subject.flag.value="new";
							document.Subject.submit();
						   </script>
<%
	}

			if (valid.equals("CodeCompareSubject")) {
%>
<script type="text/javascript">

            document.Subject.code.value="<%=beanobject.getQbcode()%>";
            document.Subject.qbsubcode.value="<%=beanobject.getQbsubcode()%>";
            document.Subject.qbsubname.value="<%=beanobject.getQbsubname()%>";
            document.Subject.desc.value="<%=beanobject.getQbsubdesc()%>";
            document.Subject.txtBranch.value="<%=beanobject.getQbbranchcode()%>";
                                
			msg=confirm("Subject name Already Exists ,Do You Want update other than subject name ?");
			
			if(msg)
		                   {
						document.Subject.flag.value="update";
				         	document.Subject.submit();
				   }
			
			</script>
			<%
	}

		}
	}
%>


<script language="javascript" type="text/javascript">

function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){
document.Subject.method="get";
document.Subject.flag.value="new";
document.Subject.submit();
}

function SaveRec(){
if(chk_mc()){
document.Subject.method="post";
		if(!isWhitespace(document.Subject.qbsubname.value)){
			document.Subject.flag.value="save";
		        document.Subject.submit();
			 }

	else
	{
	alert("Insufficent Data");
	}
 }

	else
	{
	alert("Insufficent Data");
	}
}
function chk_mc(){
var flag_cs;
var c;
var mc=document.Subject.code.value;
if(mc=="")
{
				document.Subject.code.focus();
				document.Subject.flag.value="none";
				document.Subject.code.value="";
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

function chk(){
var flag_s;
var i;
var sp=document.Subject.qbsubname.value;
				if(sp=="")
				{
				document.Subject.qbsubname.focus();
				document.Subject.flag.value="none";
				document.Subject.qbsubname.value="";
				return false;
				}
				else
				{
	                 for(i=0;i<document.Subject.qbsubname.value.length;i++)
 	                      {
 	                       if(document.Subject.qbsubname.value.charAt(i)=="")
 	                          {flag_s=0; }
 	                                else{flag_s=1;}
	                       }
		                  if (flag_s==0)
		                    {
		                   	document.Subject.qbsubname.focus();
		                   	document.Subject.qbsubname.value="";
			                return false;
		                      }
		                   else if (document.Subject.code.value==""){
		                 	document.Subject.code.focus();
			                return false;
		                      }
        else{
		return true;
		}
     }
 }


function SearchRec(){
document.Subject.method="get";
var no=document.Subject.code.value;
if(no==""){
				document.Subject.code.focus();
				alert("Insufficent Data");
				document.Subject.flag.value="new";
				document.Subject.submit();

			}
		 else
		 { 
		       document.Subject.flag.value="search";
			   document.Subject.submit();
			   
		 }
		
}
		
				function DelRec(){
				document.Subject.method="get";
				if (document.Subject.code.value==""){
				document.Subject.code.focus();
				alert("Insufficent Data");
				document.Subject.flag.value="new";
				document.Subject.submit();
				}
				else{
				  document.Subject.flag.value="delete";
				  document.Subject.submit();
				 }

}

function check()
{
 if(updatecheck()){
         if(confirm("Record Already Exists Are You Sure To Update?"))
           {
           document.Subject.flag.value="save";
           }
            else
	        {
	         document.Subject.flag.value="cancel";
	         }
	 }
           else
            {
              document.Subject.flag.value="save";
             }
			 return true;
}

function subject_code_val() {
if((isNaN(document.Subject.code.value))||(document.Subject.code.value == ' ')) {
document.Subject.code.select();
document.Subject.code.value="";
return false;
   }
}
function FindSubject(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}
function load(){
	document.Subject.name.focus();

		 }
</script>



</html>

