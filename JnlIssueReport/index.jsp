<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" session="true" buffer="12kb" import="java.sql.*"  import="Common.Security" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Jnl Issue Report</title>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
</head>

<body background="/AutoLib/soft.jpg">

<form method="POST" action="./JnlIssueReport" name="JIssueReport">
<br><br><br>
  <h2 >Journal&nbsp;Issue&nbsp;Report</h2>
<table align="center" class="contentTable" width="75%">
<td>
<table border="0" width="60%" cellspacing="0" cellpadding="5" align="center">
  <tr>
    <tr>
      <td>Journal Name</td>
      <td><input type="text" name="jnlname" size="20" readonly=true><input type="button" Class="submitButton" value="Find" name="jnlfind" onclick=FindValue(name)></td>
    </tr>
    <tr>
      <td><input type=checkbox name="issuedate" >Issue&nbsp;Date</td>
      <td>From <INPUT name=fromdate size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,JIssueReport.fromdate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
          To
		 <INPUT name=todate size=15  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,JIssueReport.todate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT></td>
    </tr>
    <tr>
      <td></td>
      <td></td>
    </tr>
    <tr>
      <td></td>
            <td><input type="button" Class="submitButton" value="Print" name="print" onclick=check()>&nbsp;<input type="reset" Class="submitButton" value="Clear" name="clear"><input type="hidden" name="flag"></td>
    </tr>
  </table></td></table>
</form>

</body>

</html>
<script language="JavaScript">
function Print_Report()
{
		var jname=document.JIssueReport.jnlname.value;
		var fdate=document.JIssueReport.fromdate.value;
		var tdate=document.JIssueReport.todate.value;
		
			var date=document.JIssueReport.fromdate.value;
		  	var frdt=date.split("-");
		  	var fdate=frdt[2]+"-"+frdt[1]+"-"+frdt[0];	 
		  	
		  	var date1=document.JIssueReport.todate.value;
		  	var frdt1=date1.split("-");
		  	var tdate=frdt1[2]+"-"+frdt1[1]+"-"+frdt1[0];
		  	
		  	
					if ((document.JIssueReport.jnlname.value!="")&& (document.JIssueReport.issuedate.checked=="1"))
		
						{
						   	
	   							window.open("<%=request.getContextPath()%>/frameset?__report=/Report/jnl_issues_namedt.rptdesign&companyname="+"AutoLib Software Systems"+"&jtype="+jname+"&frmdate="+fdate+"&todate="+tdate);
		    	
						}
					else if(document.JIssueReport.jnlname.value!="")
		
						{
						   	
	   							window.open("<%=request.getContextPath()%>/frameset?__report=/Report/jnl_issues.rptdesign&companyname="+"AutoLib Software Systems"+"&jtype="+jname);
		    	
						}
					else if	(document.JIssueReport.issuedate.checked=="1")
					{
								window.open("<%=request.getContextPath()%>/frameset?__report=/Report/jnl_issues_dt.rptdesign&companyname="+"AutoLib Software Systems"+"&frmdate="+fdate+"&todate="+tdate);
					}

}



function check(){
if(document.JIssueReport.issuedate.checked==true){
   document.JIssueReport.flag.value="isdate";
}
else{
document.JIssueReport.flag.value="";
}
 document.JIssueReport.submit();
}



function FindValue(val){
winpopup=window.open("search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
}
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
</script>

