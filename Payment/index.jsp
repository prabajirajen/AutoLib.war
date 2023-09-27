<html>
<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language = "java" session="true"   buffer="12kb" import="java.util.*" import="Common.Security"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Payment.PaymentBean"/>

<%
ArrayList sc=new ArrayList();
ArrayList pc=new ArrayList();
ArrayList id=new ArrayList();//issue details
String message="",info="",issuedetails="";
String memcode="";
String delStat="";
if(request.getParameter("delStatus")!=null){
	delStat=request.getParameter("delStatus");
}
%>


<head>
<title>Auto Lib</title>
<meta http-equiv="pragma" content="no-cache"/>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
</head>
<body> 


<form name=Payment method="post" action=./PaymentServlet>


<br>
<br>

<h2>Payment&nbsp;Master</h2>


<table align="center" class="contentTable" width="750">
<tr><td> &nbsp; </td></tr>
<tr><td>

<table align="center" class="contentTable" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>
<td Class="addedit">Member&nbsp;Code</td>
<td><input type="text" name="user_no"  size=15  maxlength="15" onKeydown="Javascript: if (event.keyCode==13) user();">
</tr>

<tr>
<td Class="addedit">Member&nbsp;Name</td><td colspan="3" >
<input type="text" name="user_name"  size=25 maxlength="50" readonly=true></td>
</tr>


<tr>
<td Class="addedit">Department</td>
<td><input type="text" name="user_dept"  size=25 maxlength="50" readonly=true></td>
</tr>

<tr>
<td Class="addedit"></td><td colspan="3" >
<input type="hidden" name="user_course"  size=50 maxlength="50" readonly=true> </td>
</tr>
<tr><td> &nbsp; </td></tr>



</table>

</td>


<td>
<table align="center" class="contentTable" width="90%">
<tr><td>&nbsp;</td></tr>
<tr>
<td Class="addedit">Returned</td>
<td><input type="text" name="returned" size=10 maxlength="10" readonly=true>
<%-- <input type=button Class="submitButton" name=find Value="view" onclick="search('<%=bean.getMcode()%>')"> --%>
 
 
</tr>
<tr>
<td Class="addedit">Current Issue</td>
<td><input type="text" name="issue" size=10 maxlength="10" readonly=true></td>
</tr>
<tr>
<td Class="addedit">Current Renew</td>
<td><input type="text" name="renew" size=10 maxlength="10" readonly=true></td>
</tr>
<tr><td>&nbsp;</td></tr>
</table>




</td></tr>

<tr><td> &nbsp; </td></tr>


<tr>
<td>

<table align="center" class="contentTable" width="90%">
<tr><td> &nbsp; </td></tr>
<tr>


<td Class="addedit">Bill&nbsp;&nbsp;No</td><td colspan="3" >
<input type="text" name="bill_no"  size=10 maxlength="50" readonly=true></td></tr>

<tr><td Class="addedit">Date

<TD>
	<INPUT name=pdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Payment.pdate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
				
		 </td>
</tr>
<tr><td Class="addedit">Current&nbsp;&nbsp;Payment&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td><td colspan="3" >
<input type="text" name="current_amt"  size=10 maxlength="50" onKeyUp="return Payment_code_val();"></td></tr>



<tr><td> &nbsp; </td></tr>

</table>
</td>
<td>




<table align="center" class="contentTable" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Total&nbsp;&nbsp;Amount</td><td colspan="3" >
<input type="text" name="tot_amt"  size=10 maxlength="50" readonly=true></td></tr>
<tr><td Class="addedit">Paid&nbsp;&nbsp;&nbsp;Amount</td><td colspan="3" >
<input type="text" name="paid_amt"  size=10 maxlength="50" readonly=true></td></tr>
<tr><td Class="addedit">Balance&nbsp;Amount</td><td>
<input type="text" name="balance_amt"  size=10 maxlength="50" readonly=true></td></tr>
<tr><td> &nbsp; </td></tr>
</table>



<!-- ----------------------- -->
</td>
</tr>
<tr><td> &nbsp; </td></tr>
</table>




<P align=center>
<input type=hidden name=New Class="submitButton" Value=New onclick=new_no()>

<input type="button" name=del id="deleteAmt" Class="submitButton" Value=Delete onclick=deleteRec()>

<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=Reset name=Clear Class="submitButton" Value=Clear onclick=rsetpage()>
<input type=hidden name=flag>




<br>

</form>
</body>

<!--
//////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// -->
<%
String valid=request.getParameter("check");
String detail=request.getParameter("details");

message=request.getParameter("message");
info=request.getParameter("info");
issuedetails=request.getParameter("issuedetails");



if(detail!=null)
{
	if(detail.equals("SavePayment")){
		%>             <script language="JavaScript">

					 alert("Record Inserted Successfully!");
					
					 document.Payment.flag.value="user";
				     </script>		
					<%
					}	
}



if(valid!=null){
	
if(request.getParameter("check")!=null){

if(valid.equals("userdetails"))
{
	%>
	
 <script language="JavaScript">
	
	
	document.Payment.user_no.value="<%=bean.getMcode()%>";
	document.Payment.user_name.value="<%=bean.getMname()%>";
    document.Payment.user_dept.value="<%=bean.getDept()%>";
    document.Payment.user_course.value="<%=bean.getCourse()%>";
        
    document.Payment.tot_amt.value="<%=bean.getTot_Amt()%>";
	document.Payment.paid_amt.value="<%=bean.getPaid_Amt()%>";
	document.Payment.balance_amt.value="<%=bean.getBalance_Amt()%>";
	 
	document.Payment.bill_no.value="<%=bean.getBill_No()%>";	
	document.Payment.current_amt.value="";
	document.Payment.current_amt.focus();
	
	document.Payment.returned.value="<%=bean.getReturned() %>";	
	document.Payment.issue.value="<%=bean.getIssue() %>";
	document.Payment.renew.value="<%=bean.getRenew() %>";
	
	
	 document.Payment.flag.value="";
</script>
	 
	 <%
}

if(valid.equals("FailMember")){ 

	%>
	            <script language="JavaScript">
	            alert("Member Not Found");
				document.Payment.flag.value="clear";
			    document.Payment.submit();				
			   	</script><%
				}
if(valid.equals("NotRightUser")){
	  %>
	  	  <script language="JavaScript">
	  	  alert("Access denied !");			
	  	 </script>
	  <%
	  } 	    
if(valid.equals("newbillno")){
	%>         
	    <script language="JavaScript">

				 document.Payment.bill_no.value="<%=bean.getBill_No()%>";
				 document.Payment.current_amt.focus();
				 
	    </script>		
				<%
				}

}
}

if(message!=null)
{
	if(message.equals("TRANSDETAILS"))
	{
			out.print("<b><center>Fine Details</center></b>");
            out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='70%'>");
            out.print("<tr><th>Trans&nbsp;No<th>Trans&nbsp;Date&nbsp;&nbsp;&nbsp;<th>Access No<th>Trans Amount<th>Trans Head</tr>");

sc=(ArrayList)bean.getPaymentList();

for(int i=0;i<sc.size();i+=5){
%>
 <tr onmouseover=this.style.color='#ff9900' onmouseout=this.style.color='black' >
 <script language=javascript>
 document.write("<tr>");
 document.write("<td>"+"<%=sc.get(i)%>" +"</td>");
 document.write("<td>"+"<%=sc.get(i+1)%>" +"</td>");
 document.write("<td>"+"<%=sc.get(i+2)%>"+"</td>");
 document.write("<td>"+"<%=sc.get(i+3)%>"+"</td>");
 document.write("<td>"+"<%=sc.get(i+4)%>"+"</td>");
 document.write("</tr>");
</script>
<%
	}	
	out.print("</table><br>");
	sc.clear();	
	}
	

	
}


if(info!=null)
{
	if(info.equals("PAIDDETAILS"))
	{
      		out.print("<br>");
		    out.print("<b><center>Payment Details</center></b>");
            out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='70%'>");
            out.print("<tr><th>Bill No&nbsp;<th>Payment Date<th>Paid Amount<th>Pay Mode<th>Staff Code</tr>");

pc=(ArrayList)bean.getPaidList();

for(int i=0;i<pc.size();i+=5){
%>
 <tr onclick='showDelete("<%=pc.get(i)%>","<%=pc.get(i+1)%>","<%=pc.get(i+2)%>","<%=pc.get(i+3)%>","<%=pc.get(i+4)%>")'>
 <script language=javascript>
 /* document.write("<tr>");  */
 document.write("<td>"+"<%=pc.get(i)%>" +"</td>");
 document.write("<td>"+"<%=pc.get(i+1)%>" +"</td>");
 document.write("<td>"+"<%=pc.get(i+2)%>"+"</td>");
 document.write("<td>"+"<%=pc.get(i+3)%>"+"</td>");
 document.write("<td>"+"<%=pc.get(i+4)%>"+"</td>");
 document.write("</tr>");
</script>
<%
	}	
	out.print("</table><br>");
	pc.clear();	
	}
}

if(delStat.equalsIgnoreCase("deleteSuccess")){
String memCode=request.getParameter("memberCode");	
%>	
<script>  
alert('Delete Sucess..!!');
memCode
document.Payment.user_no.value=<%=memCode%>;
document.Payment.flag.value="user";
document.Payment.submit();
</script>
<%	
}


if(issuedetails!=null)
{
	if(issuedetails.equals("ISSUEDETAILS"))
	{
      		out.print("<br>");
		    out.print("<b><center>Issue Details</center></b>");
            out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='70%'>");
            out.print("<tr><th>&nbsp;&nbsp;&nbsp;Acc.No&nbsp;&nbsp;&nbsp;<th>Title<th>Author&nbsp;Name<th>&nbsp;&nbsp;IssueDate&nbsp;&nbsp;<th>&nbsp;&nbsp;DueDate&nbsp;&nbsp;</tr>");

id=(ArrayList)bean.getIssueDetail();

for(int i=0;i<id.size();i+=5){
%>
 <tr onmouseover=this.style.color='#ff9900' onmouseout=this.style.color='black' >
 <script language=javascript>
 document.write("<tr>");
 document.write("<td>&nbsp;"+"<%=id.get(i)%>" +"</td>");
 document.write("<td>&nbsp;"+"<%=id.get(i+1)%>" +"</td>");
 document.write("<td>&nbsp;"+"<%=id.get(i+2)%>"+"</td>");
 document.write("<td align=center>"+"<%=id.get(i+3)%>"+"</td>");
 document.write("<td align=center>"+"<%=id.get(i+4)%>"+"</td>");
//  document.write("</tr>");
</script>
<%
	}





	
	out.print("</table><br>");
	pc.clear();	
	}
}



%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 


<script language="javascript">

//shek



function search(val){
winpopup=window.open("search.jsp?memb_code="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}





function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}

function new_no(){
document.Payment.method="get";
document.Payment.flag.value="new";
}

function user()   
{         
var ab=document.Payment.user_no.value;

if(ab=="")
{
alert("Insufficient Data");

}else{

document.Payment.flag.value="user";
document.Payment.submit();

}

}

function SaveRec(){
             document.Payment.method="get";
			   if(chk()){

				    document.Payment.flag.value="save";
		         	document.Payment.submit();
					}
	else
	{
	alert("Insufficient Data");
	}
	
}


function chk(){

var sp=Number(document.Payment.current_amt.value);
var ca=Number(document.Payment.balance_amt.value);

if(sp=="")
{
				document.Payment.current_amt.focus();
				document.Payment.flag.value="none";
				document.Payment.current_amt.value="";
				return false;
				}
				else
				{
	                             
		            if (document.Payment.user_no.value==""){
		              	document.Payment.user_no.focus();
			            return false;
		              }
		             else if(document.Payment.bill_no.vallue=="") 
		             {
		             alert("Please select Bill No!");
			         return false;
		             }
		             else if(sp>ca || sp==0)
		             {
		              alert("Current Amount is Greter than the Payable Amount !");
		              document.Payment.current_amt.value="";
		              document.Payment.current_amt.focus();
		             return false;
		             }
        else{
		return true;
		}
     }
 }

function Payment_code_val() {
if((isNaN(document.Payment.current_amt.value))||(document.Payment.current_amt.value == ' ')) {
document.Payment.current_amt.select();
document.Payment.current_amt.value="";

  }
}


function ClearRec(){
	 document.Payment.user_no.value="";
	 document.Payment.user_name.value="";
     document.Payment.user_dept.value="";
     document.Payment.user_course.value="";
         
     document.Payment.tot_amt.value="";
	 document.Payment.paid_amt.value="";
	 document.Payment.balance_amt.value="";
	 
	 document.Payment.bill_no.value="";
	 document.Payment.current_amt.value="";
	 	 
	 document.Payment.flag.value="";
		 
}
function load(){
	document.Payment.user_no.focus();
		 }	
		 
function rsetpage(){
document.Payment.flag.value="clear";
document.Payment.submit();
}	

function showDelete(billNo,bDate,bAmount,bMode,bStaff){
	document.Payment.bill_no.value=billNo;
	document.Payment.user_no.value;
	document.Payment.current_amt.value=bAmount;
}
function deleteRec(){
document.Payment.flag.value="DeletePayment";
document.Payment.submit();
}
</script>
<!--
////////////////////////*if(document.frm.sel_search[0].checked==true){*////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// -->

</html>