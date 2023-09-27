<%@ pagelanguage="java" session="true" buffer="12kb"  import="java.sql.*" import="Lib.Auto.Advanced.AdvancedReserve" %>
<jsp:useBean id="bean" scope="page" class="Lib.Auto.Advanced.Advancedbean"/>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename:view.jsp
//   Form name:vfrm
//%>
<!--
///////////////////////////////////////////////FORM CONTROLS//////////////////////////////////////////////////////////////////////// -->
<html>
<head>
<title>smple Search</title>
</head>
<body bgcolor="#d3d3d3" text=blue >
<input type=hidden name=membercode >
<input type=hidden name=memcode >
<input type=hidden name=vflag value="view">
   <center>
<%
String flag=null,sql=null;
java.sql.Statement st=null;
java.sql.ResultSet rs1=null;
String acc="",call="",tit="",sp="",yea="",locat="",sub="",key="",avai="",auth="",dept="",place="";
String pri="",edit="";
String accno="",author="",title="",mcode="",mname="",dept_name="",iss_date="",due_date="";
int sno=1;
flag=request.getParameter("flag");
if(flag.equalsIgnoreCase("display")){
acc=(String)request.getAttribute("acc");
java.sql.ResultSet rs=bean.getRs();
	if(rs.next()){
	accno=rs.getString("Access_No");
	mcode=rs.getString("member_code");
	mname=rs.getString("member_name");
	iss_date=getdate(rs.getString("issue_date"));
	due_date=getdate(rs.getString("due_date"));
	author=rs.getString("author_name");
	title=rs.getString("title");
	dept_name=rs.getString("dept_name");
	out.print("<form name=vfrm action=./AdvancedReserveMember target=_parent onsubmit='return validate()'>");
	out.print("<p><b><font size=4 color=#800000>ISSUED DETAILS ! ! !</font></b></p>");
	out.print( "</h1><TABLE  BORDER =1 cellspacing=0 bordercolor=orange>");
    out.print("<TR>");

if (accno!= "") {
out.print("<TD width=100><b><font color = #0000FF>Access No </td>");
out.print( "<td width=175>"+accno+"</td></tr>");
}

if (author != "") {
out.print( "<TD width=100><b><font color = #0000FF>Author</td>");
out.print( "<td width=175>" + author + "</td></tr>");
}

if (title != "") {
out.print( "<TD width=100><b><font color = #0000FF>Title</td>");
out.print( "<td width=175>" + title + "</td></tr>");
}

if (mcode != "") {
out.print("<TD width=100><b><font color = #0000FF>Member Code</td>");
out.print( "<td width=175>" +mcode+ "</td></tr>");
}

if (mname != "") {
out.print( "<TD width=125><b><font color = #0000FF  >Member Name</td>");
out.print( "<td width=175>"+ mname+ "</td> </tr>");
}

if (dept_name != "") {
out.print( "<TD width=125><b><font color = #0000FF  >Department</td>");
out.print( "<td width=175>" + dept_name  + "</td> </tr>");
}

if (iss_date!= "" ){
out.print( "<TD width=100><b><font color = #0000FF>Issue Date</td>");
out.print( "<td width=175>" + iss_date + "</td></tr>");
}

if (due_date!= "") {
out.print( "<TD width=100><b><font color = #0000FF>Due Date</td>");
out.print( "<td width=175>" + due_date+"</td></tr>");
}
out.print( "</table>");
}

out.print("<br><b>Enter UserId :&nbsp;&nbsp;<input type=text name=memcode  align=left>");
out.print("<input type=submit value=Reserve>");

out.print("<hr>");
out.print("<font color=red>");
//out.print(acc);
sql="select m.member_name,r.Member_Code as Memid from Member_mas M ,Reserve_Mas r where  r.member_code=m.member_code and r.Access_no='"+acc+"' and  doc_type='BOOK' ";
//rs = openrs( st,sql);
AdvancedReserve ob=new AdvancedReserve();
rs1=ob.RecList(sql,rs1,session);
out.print("<table border=1 cellspacing=0 width='60%'>");
out.print("<tr>");
out.print("<th>");
out.print("Priority");
out.print("</th>");
out.print("<th>");
out.print("Member Name");

out.print("</th>");
out.print("<th>");
out.print("Member ID");
out.print("</th>");
out.print("</th>");
out.print("</tr>");

out.print("<input type=hidden name = accessno value="+acc.trim()+">");
out.print("<input type=hidden name = membercode value="+mcode+">");
out.print("<center><font color=blue><h3>");
out.print("Reservation List");
out.print("</h3></font></center>");
while(rs1.next())
{
out.print("<tr>");
out.print("<td>");
out.print(sno);
out.print("</td>");
out.print("<td>");
out.print(rs1.getString("Member_name"));
out.print("</td>");
out.print("<td>");
out.print(rs1.getString("memID"));
out.print("</td>");
out.print("</tr>");
sno=sno+1;
}
out.print("</table>");
}
%>
  </table>
 </center>
 <html>
  <script language="JavaScript">
  function fn(){
  window.close();
     }
function validate()
{
 uid="<%=session.getAttribute("UserID")%>";
 rights="<%=session.getAttribute("UserRights")%>";
 scode=document.vfrm.membercode.value;
 	
 	if(scode==vfrm.memcode.value)
 	{
    alert('Already Issued to You..');	
	document.vfrm.memcode.focus();
	return false;
 	}
 
 if((vfrm.memcode.value==uid||rights=="1"||rights=="2") )
{
	return true;
	}
else	
{
     alert('Access Denied...');	
	 document.vfrm.memcode.focus();
	return false;
		}
  
	}
	
</script>
<%!
 String getdate(String datechk) {

    	   java.util.StringTokenizer stz_is=new java.util.StringTokenizer(datechk,"-");
		    int biy=Integer.parseInt(stz_is.nextToken());
		     int bim=Integer.parseInt(stz_is.nextToken());
		    int bid=Integer.parseInt(stz_is.nextToken());
	     	   String bissue_m=new Integer(bim).toString();
				    if(bissue_m.length()==1)
				    bissue_m="0"+bissue_m;
					String bissue_d=new Integer(bid).toString();
					if(bissue_d.length()==1)
				    bissue_d="0"+bissue_d;
					String BISSDATE=bissue_d+"/"+bissue_m+"/"+biy;
	return BISSDATE;
  }

%>	



	  
