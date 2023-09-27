<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Tree/autolib.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />




<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link rel="stylesheet" href="mm_travel2.css" type="text/css" />


<script language="JavaScript" type="text/javascript">

function validate()
   {

if(t1())
{
alert("Please enter Username & Password");
document.form1.txtuserid.focus();
document.form1.action = "index.jsp";
document.form1.method = "post";
document.form1.submit();
return false;
}
else
{
document.form1.flag.value="login";
document.form1.libSubmit.disabled=true;
document.form1.submit();
return true;
}

}

function t1()
{
if(document.form1.txtuserid.value=="")
{
return true;
}
else
{
return false;
}
}

//--------------- LOCALIZEABLE GLOBALS ---------------
var d=new Date();
var monthname=new Array("January","February","March","April","May","June","July","August","September","October","November","December");
//Ensure correct for language. English is "January 1, 2004"
var TODAY = monthname[d.getMonth()] + " " + d.getDate() + ", " + d.getFullYear();
//---------------   END LOCALIZEABLE   ---------------
</script>

<style type="text/css">
#dateformat{
clear: both;
padding: 0;
width: 100%;
height: 2px;
line-height: 16px;
background: #104e8b ;
/*border-bottom: 1px solid #fff; /*Remove this to remove border between bar and tabs*/
text-align: center;
}
#footer {
	background:#104e8b;
	width:100%;
	height:15px;
	position:absolute;
	bottom:1%;
	left:0;
}
</style>

<script language = "Javascript">
window.history.forward(0)
</script>
</head>
<body class="front" onload="login()">

<form action="/AutoLib/Login" name="form1" method="get"  onsubmit="return validate()">
<table align="center">

<tr>
<td>&nbsp;</td>
</tr>



<c:if test="${UserBranchID eq 1}">			
			<tr><td align="center" id="clgtitle">Central Library (BIHER)</td></tr>

<tr><td align="center" id="clgsubtitle1"></font></td></tr>
</c:if>


<c:if test="${UserBranchID eq 2}">			
			<tr><td align="center" id="clgtitle">Bharath Institute of Science and Technology (BIST)</td></tr>

<tr><td align="center" id="clgsubtitle1"></font></td></tr>
</c:if>


<c:if test="${UserBranchID eq 3}">			
			<tr><td align="center" id="clgtitle">Sri Lakshmi Narayana Institute of Medical Sciences (SLIMS)</td></tr>

<tr><td align="center" id="clgsubtitle1"></font></td></tr>
</c:if>
<c:if test="${UserBranchID eq 4}">			
			<tr><td align="center" id="clgtitle">Sree Balaji Dental College & Hospital</td></tr>

<tr><td align="center" id="clgsubtitle1"></font></td></tr>
</c:if>

<c:if test="${UserBranchID eq 5}">			
			<tr><td align="center" id="clgtitle">Sree Balaji Medical College and Hospital (SBMCH)</td></tr>

<tr><td align="center" id="clgsubtitle1"></font></td></tr>
</c:if>



</table>





 <table align="center" border="0" width="100%" cellspacing="0" cellpadding="5" height="150">
    <tr><td>&nbsp;</td></tr>
    
    
 
    <tr>
          <td>
            <table  align="center"  width="25%" cellspacing="0" cellpadding="5" class="indexTab">
           
 
           
           
           
            <tr> <td width="100%" colspan="2" align="left"  bgcolor="#104e8b"><font color="white"><b>Login</b></font></td></tr>
            <tr>
            <td width="33%">User&nbsp;Name</td><td width="67%"><input type="text" name="txtuserid" value="WebOpac"/></td></tr>
            <tr>
            <td width="33%">Password</td><td width="67%"><input type="password" name="txtpasword" value="WebOpac"/></td>
            </tr>
            <tr>
            <td width="33%">Campus</td><td width="67%">
            <select name="txtbranch" size="1"  style="width: 150px">
            <option value="1" selected>DJ MBA</option>    
            </select></td>
            </tr>
				<tr>
               <td width="100%" colspan="2"  align="center">
               		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="libSubmit" class="submitButton" value="Login" />
           			<input type="reset"  name="clear" class="submitButton" value="Clear" onclick="return clearLogin()" /></td></tr>
           			<tr>
           			<td colspan="3" align="right"><a href="" onclick="FindForgotPWD()">Can't access your account?</a></td></tr>
           			
            </table></td></tr>
         
            
</table>
   <input type="hidden" name="flag"></input>
 	<div id="footer">

<table width="100%" align="center">
<tr><td width="100%" id="dateformat">
 <font size="2" face="verdana" color="white">
 <script language="JavaScript" type="text/javascript">document.write(TODAY)</script>
|&nbsp;Best viewed in <i>(IE 7,Firefox 30.0 & above)</i> with screen resolution of <i>(1024x768 & 1366x768)</i>. Powered by AutoLib Software Systems.</font></td></tr>
 </table>
 	</div>
 	 
</form>



</body>	

 
 


<script>

function login()
{
document.form1.flag.value="login";
  document.form1.submit();
}
function FindForgotPWD()
{
  winpopup=window.open("SendMail/Forgotpwd.jsp","popup","height=200,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no")
}

function clearLogin()
{
  document.form1.method = "post";
  document.form1.action = "index.jsp";
  document.form1.submit();
}

</script>



<script language="JavaScript">
<!-- for disable right click button shek 29-06-2015 -->
	function ieClicked() {
		if (document.all) {
			return false;
		}
	}
	function firefoxClicked(e) {
		if(document.layers||(document.getElementById&&!document.all)) {
			if (e.which==2||e.which==3) {
				return false;
			}
		}
	}
	if (document.layers){
		document.captureEvents(Event.MOUSEDOWN);
		document.onmousedown=firefoxClicked;
	}else{
		document.onmouseup=firefoxClicked;
		document.oncontextmenu=ieClicked;
	}
	document.oncontextmenu=new Function("return false")

</script>

</html>