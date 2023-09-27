<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="Common.Security" import="java.util.*" import="Lib.Auto.Branch.BranchBean"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Journal.journalbean"/>

<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("UserBranchList");
%>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:journal
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

<form  name="jrl" method="get" action=./JournalServlet>
<br><br><br>

<h2>Journal&nbsp;Master</h2>
<center>
<table align="center" class="contentTable" width="70%">
<td>
<table border="0" width="68%" cellspacing="0" cellpadding="5" align="center">
  <tr>
<!--    <td Class="addedit" width="14%">Code</td>-->
    <td Class="addedit" width="14%" colspan="2"><input name="jcode" type="hidden" size="5" maxlength=8 onKeyUp="return journal_code_val();" readOnly>
    DocType</td>
    <td><select size="1" name="doc">
          <option selected value="JOURNAL">JOURNAL</option>
          <option value="MAGAZINE">MAGAZINE</option>
          <option value="NEWSLETTER">NEWSLETTER</option>
          <option value="OTHERS">OTHERS</option>
          </select></td><td Class="addedit" width="14%"></td>
    <td Class="addedit" >Name</td><td colspan="2">
    <input name="jname" size="25" >&nbsp;
    <input type="button" Value="Find" name="find_name" Class="submitButton" maxlength=150 onclick='Find_Value("Nam")' >
    </td>
  </tr>
  <tr>
    <td Class="addedit" width="14%" >Publisher<input name="jpub" type="hidden" size="15" value="1" ></td>
    <td width="28%" colspan="3"><input type="text" size="23" readonly="true" name="pname" value="NIL" >
    &nbsp;<input type="button" value="Find" name="find_pub" Class="submitButton" onclick='Find_Value("Pub")'></td>
    <td Class="addedit" width="14%">Frequency</td>
    <td width="30%" colspan="2"><select  name="jfreq">
          <option value="DAILY">DAILY</option>
          <option value="WEEKLY TWO">WEEKLY TWO</option>
          <option value="WEEKLY">WEEKLY</option>
          <option vlue="FORTNIGHTLY">FORTNIGHTLY</option>
          <option selected value="MONTHLY">MONTHLY</option>
          <option value="BIMONTHLY">BIMONTHLY</option>
          <option value="QUARTERLY">QUARTERLY</option>
          <option value="HALF YEARLY">HALF&nbsp;YEARLY</option>
          <option value="ANNUAL">ANNUAL</option>
          <option value="OTHERS">OTHERS</option>
        </select></td>
  </tr>
  <tr>
    <td Class="addedit" width="14%">Language</td>
    <td width="42%" colspan="3"><input name="jlang" size="32" maxlength=50></td>
    <td Class="addedit" width="14%">ISSN</td>
    <td width="30%" colspan="2"><input name="jissn" size="32" maxlength=50></td>
  </tr>
  <tr>
    <td Class="addedit" width="14%">Type</td>
    <td width="28%" colspan="2"><select size="1" name="jtype">
          <option selected value="PAYMENT">PAYMENT</option>
          <option value="EXCHANGE">EXCHANGE</option>
          <option value="GRATIS">GRATIS</option>
          <option value="SAMPLE">SAMPLE</option>
          <option value="OTHERS">OTHERS</option>

        </select></td>
    <td width="14%">&nbsp;</td>
    <td Class="addedit" width="14%">Country</td>
    <td width="30%" colspan="2"><select size="1" name="jcountry">
          <!-- <option selected value="INDIA">INDIA</option>
          <option value="USA">USA</option>
          <option value="UK">UK</option>
          <option value="GERMANY">GERMANY</option>
          <option value="KOREA">KOREA</option>
          <option value="JAPAN">JAPAN</option>
          <option value="SWEDEN">SWEDEN</option>
          <option value="HOLAND">HOLAND</option>
          <option value="NORWAY">NORWAY</option>
          <option value="CHINA">CHINA</option>
          <option value="FRANCE">FRANCE</option>
          <option value="ITALY">ITALY</option>
          <option value="OTHERS">OTHERS</option> -->
          
          <option value="NATIONAL">NATIONAL</option>
	<option value="INTERNATIONAL">INTERNATIONAL</option>
        </select></td>
  </tr>
  <tr>
    <td Class="addedit" width="14%">Deli.Mode</td>
    <td width="28%" colspan="3"><select size="1" name="jdeli">
          <option selected value="SEA MAIL">SEA&nbsp;MAIL</option>
          <option value="AIR MAIL">AIR&nbsp;MAIL</option>
          <option value="SURFACE MAIL">SURFACE&nbsp;MAIL</option>
          <option value="HAND DELIVERY">HAND&nbsp;DELIVERY</option>
          <option value="POST MAIL">POST&nbsp;MAIL</option>
          <option value="OTHERS">OTHERS</option>
        </select></td>
    <td Class="addedit" width="14%">Department</td><input type="hidden" name="dcode" value="1">
    <td width="14%"><input type="text" name="dname" value="NIL" readonly size="25" ></td>
    <td width="30%"><input type="button" value="Find" name="find_dept" Class="submitButton" onclick='Find_Value("Dept")'></td>
  </tr>
  <tr>
    <td Class="addedit" width="14%">Subject</td><input name="scode" type="hidden"  value="1" >
    <td width="28%" colspan="2"><input type="text" size="25" name="sname" value="NIL" readOnly=true ></td>
    <td width="14%"><input type="button" value="Find" name="find_sub" Class="submitButton" onclick='Find_Value("Sub")'></td>
    <td Class="addedit" width="14%">Remarks</td>
    <td width="15%" colspan="2"><input name="jremarks" maxlength=150 size="35"></td>
  </tr>

<c:choose>
<c:when test="${UserBranchID gt 0}">
<tr>
    <td Class="addedit">Division</td>
    <td  colspan="3" >
	 <SELECT size="1" name="txtBranch" style="width:208px">
	 <option value="<c:out value="${UserBranchID}" />"><c:out value="${visitBranch}" /></option>
	 </SELECT></td>
  </tr>
</c:when>  
<c:otherwise>
<tr>
    <td Class="addedit">Division</td>
    <td  colspan="3" >
	 <SELECT size="1" name="txtBranch" style="width:208px">
                                    <% 
                                    try {
                                        if(sc!=null && !sc.isEmpty()) {
			                            Iterator it=sc.iterator();
                                        while(it.hasNext()){
                                        	                                        	
                                        BranchBean view=(BranchBean) it.next();                                        	
				                     
				                        int b_code=view.getCode();
				                        String b_name=view.getName();
				                       
				                        if(!b_name.equalsIgnoreCase("NIL")){
				                    	   
				                             out.print("<option  value="+b_code+">" +b_name+"</option>");
				                    	  
				                        }
                                        } 
                                        }
                                        }catch(Exception e) 
                                        {
                                        	e.printStackTrace();
                                        }%>
                                       </SELECT></td>
  </tr>
</c:otherwise>
</c:choose>   

  
  <tr>
    <td width="100%" colspan="7" align="center"><center>
    <input type="button" value="New" Class="submitButton" name="new" onclick="NewRecord()">
		<input type="button" value="Save" Class="submitButton" name="save" onclick="SaveRecord()">
		<input type="button" value="Delete" Class="submitButton" name="delete" onclick="DeleteRecord()">
<!--		<input type="submit" value="Search" Class="submitButton" name="search" onclick="SearchRecord()">-->
		<input type="reset" value="Clear" Class="submitButton" name="clear" onclick="NewRecord()" >
		<input type="hidden" name="flag"  value="search">
                <input type="hidden" name="jnlflag"></center>
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
if(request.getParameter("check")!=null){
if(valid.equals("newJournal")){
 %>
 <script language="JavaScript">
document.jrl.jcode.value="<%=bean.getJcode()%>";
document.jrl.jcode.focus();
</script>
<%
  }

  if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		document.jrl.jcode.value="<%=bean.getJcode()%>";
                document.jrl.jname.value="<%=bean.getJname()%>";
                document.jrl.jissn.value="<%=bean.getJissn()%>";
		document.jrl.jfreq.value="<%=bean.getJfreq()%>";
		document.jrl.jcountry.value="<%=bean.getJcountry()%>";
		document.jrl.jlang.value="<%=bean.getJlang()%>";
		document.jrl.jdeli.value="<%=bean.getJdeli()%>";
		document.jrl.jtype.value="<%=bean.getJtype()%>";
		document.jrl.jremarks.value="<%=bean.getJremarks()%>";
		document.jrl.pname.value="<%=bean.getJpname()%>";
		document.jrl.dname.value="<%=bean.getJdname()%>";
		document.jrl.sname.value="<%=bean.getJsname()%>";
		document.jrl.doc.value="<%=bean.getDoc_Type()%>";
		document.jrl.txtBranch.value="<%=bean.getBranchCode()%>";

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
			alert("Record Updated Successfully!");
			document.jrl.flag.value="new";
			document.jrl.submit();
		   	</script><%
			}

			if(valid.equals("DeleteJournal"))
	{
	%>
            <script language="JavaScript">
	    alert("Record Deleted Successfully");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script><%
	}

			if(valid.equals("SaveJournal")){
%>             <script language="JavaScript">
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
        document.jrl.jissn.value="<%=bean.getJissn()%>";
		document.jrl.jfreq.value="<%=bean.getJfreq()%>";
		document.jrl.jcountry.value="<%=bean.getJcountry()%>";
		document.jrl.jlang.value="<%=bean.getJlang()%>";
		document.jrl.jdeli.value="<%=bean.getJdeli()%>";
		document.jrl.jtype.value="<%=bean.getJtype()%>";
		document.jrl.jremarks.value="<%=bean.getJremarks()%>";
		document.jrl.pname.value="<%=bean.getJpname()%>";
		document.jrl.dname.value="<%=bean.getJdname()%>";
		document.jrl.sname.value="<%=bean.getJsname()%>";
		document.jrl.doc.value="<%=bean.getDoc_Type()%>";
		document.jrl.txtBranch.value="<%=bean.getBranchCode()%>";		

</script>
<%
}
	if(valid.equals("FailureJournal")){
	%>
            <script language="JavaScript">
	    alert("Record Not Found");
	    document.jrl.flag.value="new";
	    document.jrl.submit();
	    </script><%
			}
	if(valid.equals("deleteCheck")){
		
		%>       
		            <script language="JavaScript">
        document.jrl.jcode.value="<%=bean.getJcode()%>";
        document.jrl.jname.value="<%=bean.getJname()%>";
        document.jrl.jissn.value="<%=bean.getJissn()%>";
		document.jrl.jfreq.value="<%=bean.getJfreq()%>";
		document.jrl.jcountry.value="<%=bean.getJcountry()%>";
		document.jrl.jlang.value="<%=bean.getJlang()%>";
		document.jrl.jdeli.value="<%=bean.getJdeli()%>";
		document.jrl.jtype.value="<%=bean.getJtype()%>";
		document.jrl.jremarks.value="<%=bean.getJremarks()%>";
		document.jrl.pname.value="<%=bean.getJpname()%>";
		document.jrl.dname.value="<%=bean.getJdname()%>";
		document.jrl.sname.value="<%=bean.getJsname()%>";
		document.jrl.doc.value="<%=bean.getDoc_Type()%>";
		document.jrl.txtBranch.value="<%=bean.getBranchCode()%>";
				
					msg=confirm("Are You Sure To Delete?");
					if(msg){
									
					 document.jrl.flag.value="Confirmdete";
				   	document.jrl.submit();	
					
					}
					</script>		
					<%
					}
	if(valid.equals("RefferdJournal")){
		%>
	            <script language="JavaScript">
		    alert("You can't delete since the Author has been referred in other masters");
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
	document.jrl.method="get";
	document.jrl.flag.value="new";
	document.jrl.submit();

}

function checkDivision()
{
if(document.jrl.pname.value=='NIL')
{
 alert("Choose right publisher name");
 return false;         
}else if(document.jrl.dname.value=='NIL')
{
 alert("Choose right department name");
 return false;         
}else if(document.jrl.sname.value=='NIL')
{
 alert("Choose right subject name");
 return false;         
}else {
  return true;         
}

}

function SaveRecord(){
document.jrl.method="get";
		if(chk()){
		if(checkDivision())  {
			document.jrl.flag.value="save";
		        document.jrl.submit();
		        }
			 }

	else
	{
	alert("Insufficent Data");
	}

}

function chk(){
var flag_s;
var i;
var sp=document.jrl.jname.value;
				if(sp=="")
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
						document.jrl.flag.value="delete";
						document.jrl.submit();
						
				}
}


function SearchRecord()
{
document.jrl.method="get";
var no=document.jrl.jcode.value;
	if(no=="")
	{
				document.jrl.jcode.focus();
				alert("Insufficent Data");
				document.jrl.flag.value="new";
				document.jrl.submit();
	}
	else
        {
document.jrl.flag.value="search";
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
function focusText()
{
document.jrl.jname.focus();
}
</script>


