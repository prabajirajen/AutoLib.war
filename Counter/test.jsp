<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}

%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="bean" scope="request" class="Lib.Auto.Counter.CounterBean" type="Lib.Auto.Counter.CounterBean">
</jsp:useBean>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat"%>


<!--
//////////////////////////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<%
String flag="",details="";
%>
<html>
<head>
<title>AutoLib</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
<meta http-equiv="pragma" content="no-cache"/>
</head>
<body background="/AutoLib/soft.jpg" onload="load()"><!--onload="opt(1)"-->
<%
	java.util.Date d =new java.util.Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	String dateString = sdf.format(d);
	
%>


<!-- Style Sheet -->

<!-- Style Sheet -->
<form name="tagmerging" method="Post" action=./CounterServlet>
<h2><b>Tag&nbsp;Merging</b></h2>
<%
flag=request.getParameter("flag");
String TID=String.valueOf(request.getAttribute("AID"));
details=request.getParameter("details");
%>
<br>
  <div align="center">
    <center>
  <table align="center" class="contentTable" width="25%">
<td>
 <table align="center" width="25%">  
 <br>
 <tr><td  colspan="2"></td><td Class="addedit" colspan="3">Antenna&nbsp;ID&nbsp;:
<input type=radio value="1" name="antennaid" onclick="load()">1&nbsp;<input type=radio value="2" name="antennaid" onclick="load()">2&nbsp;<input type=radio value="3" name="antennaid" onclick="load()">3&nbsp;<input type=radio value="4" name="antennaid" onclick="load()">4&nbsp;
</td>
</tr>
<tr></tr>
<tr>
<!-- <td> -->
<!-- <input type="radio" value="1" name="R1">  -->
<!-- </td>  -->
<td Class="addedit">
RFID&nbsp;TagId:&nbsp;&nbsp;
</td>
<td><input type="text" name="TagId" id="tag">
</td>
<td  Class="addedit">
Book&nbsp;Acc.No:&nbsp;
</td>
<td><input type="text" name="AccNo">
</td>
</tr>

<tr>
<!-- <td> -->
<!-- <input type="radio" value="2" name="R1">  -->
<!-- </td>  -->
<!-- <td  Class="addedit"> -->
<!-- Card&nbsp;UID:&nbsp;&nbsp; -->
<!-- </td> -->
<!-- <td><input type="text" name="CardUid" id="card"> -->
<!-- </td> -->
<!-- <td  Class="addedit"> -->
<!-- Member&nbsp;Code:&nbsp; -->
<!-- </td> -->
<!-- <td><input type="text" name="mcode"> -->
<!-- </td> -->
<!-- <td></td> -->
</tr>
<tr></tr>
<tr></tr>
<tr></tr>
    </table>
    <tr>
<td colspan="3">
<center>
<input type="button" Class="submitButton"  value="RfidTagID" onclick="rfidtag()">
<!-- <input type="button" Class="submitButton"  value="CardUID" onclick="carduid()"> -->
<input type="button" Class="submitButton"  value="Merge" onclick="save()">
<input type="Reset" value="Reset" Class="submitButton" onclick="rsetpage()">
</center>
</td>
</tr>

<input type=hidden name=flag>
    </table>
    </center>
  </div>

</form>
</body>
</html>
<%

if(details!=null){
if(details.equals("mergedetails")){
/*---------------------------------------MERGE DETAILS----------------------------------------------------------------------------------------------------------------------------------------------------------*/
                    int i=1;
                    out.print("<b><center>Merge Details</center></b>");
                    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 width='50%'>");
                    out.print("<tr><th>S.No<th>Access&nbsp;No<th>TagId<th>Title</tr>");
            		%>
            		<script>
                    document.write("<td><b>"+"<%=i%>" +"</td>");
        		 	document.write("<td><b>"+"<%=bean.getAccno()%>" +"</td>");
        		 	document.write("<td><b>"+"<%=bean.getEpc_id()%>" +"</td>");
        		 	document.write("<td><b>"+"<%=bean.getTitle()%>" +"</td>");
        		 	</script><%
          }
 }
%>
<script language="javascript">
function load()
{
	var tjj=<%=TID%>;
	if(tjj!=null)
		{
			document.tagmerging.antennaid[tjj-1].checked=true;
		}
	rfidtag();
	
}
function home()
{
location.href="/AutoLib/Home.jsp";
}

function Logout()
{
location.href="/AutoLib/index.html";
}
function rfidtag()
{
	var request;
	antenaid=document.tagmerging.antennaid.value;
	var url="/AutoLib/Counter/RFIDServlet?flag=TagMerge&antenaid="+antenaid+"";
	if(window.XMLHttpRequest){  
	request=new XMLHttpRequest();  
	}  
	else if(window.ActiveXObject){  
	request=new ActiveXObject("Microsoft.XMLHTTP");  
	}  
	  
	try{  
	request.onreadystatechange=getInfo;  
	request.open("POST",url,true);  
	request.send();  
	}catch(e){alert("Unable to connect to server");}  
	function getInfo(){  
	if(request.readyState==4){  
	var val=request.responseText;  
	document.getElementById('tag').value=val;
	}  
	}
}
function carduid()
{
	var request;  
	var url="/AutoLib/Counter/CardServlet?flag=CardMerge";  
	  
	if(window.XMLHttpRequest){  
	request=new XMLHttpRequest();  
	}  
	else if(window.ActiveXObject){  
	request=new ActiveXObject("Microsoft.XMLHTTP");  
	}  
	  
	try{  
	request.onreadystatechange=getInfo;  
	request.open("POST",url,true); 
	request.send();  
	}catch(e){alert("Unable to connect to server");}  
	  
	  
	function getInfo(){  
	if(request.readyState==4){  
	var val=request.responseText;  
	document.getElementById('card').value=val;
	}  
	} 
}
function save()
{
	
	
		if(document.tagmerging.TagId.value!="" && document.tagmerging.AccNo.value!="")
		{
			document.tagmerging.flag.value="TagID";
			document.tagmerging.submit();
		}
		else
			alert("Insufficient Data");
	
	
}
function rsetpage(){

	document.tagmerging.flag.value="reset";
	document.tagmerging.submit();
	}
	
</script>
<%
if(flag!=null)
{
	if(flag.equalsIgnoreCase("TagSave"))
	{%>
		<script>
		alert("TagID and AccessNo Merged Successfully");
		</script><%
	}
	if(flag.equalsIgnoreCase("TagExists"))
	{%>
		<script>
		document.tagmerging.TagId.value = "<%=bean.getEpc_id()%>";
		document.tagmerging.AccNo.value = "<%=bean.getAccno()%>";
		var msg = confirm("TagID Already Merged... Do You want to Update?");
	 	if(msg)
	 	{	
	 		document.tagmerging.flag.value="TagIDUpdate";
			document.tagmerging.submit();
	 	}
		</script><%
	}
	if(flag.equalsIgnoreCase("CardSave"))
	{%>
	<script>
	alert("CardUID and MemberCode Merged Successfully");
	</script><%
		
	}
}
%>

