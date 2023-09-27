<%@ pagelanguage="java" session="true" buffer="12kb" import="java.sql.*" import="java.text.*"%>
<%@ include file="/Common.jsp" %>
<!--
//////////////////////////////////////DATABASE CONNECTION//////////////////////////////////////////////////////////////////////// -->
<%
//
//   Filename: Index.jsp
//   Form name:Member
//
String cSec = checkSecurity(1, session, response, request);
if ("s1".equals(cSec) ) return;
%>
<jsp:useBean id="bean" scope="request" class="bean.DbCon"/>
<jsp:setProperty name="bean" property="*"/>
<html>
<head>
<title>Auto Lib</title>
<meta http-equiv="pragma" content="no-cache"/>
</head>
<form name="simple" action="report.jsp">
<input type="hidden" name="flag"> 
<%
java.sql.Connection con=null;
java.sql.Connection con1=null;
java.sql.Connection con2=null;
java.sql.Statement st1=null;
java.sql.Statement st2=null;
java.sql.Statement st3=null;
java.sql.ResultSet rs=null;
java.sql.ResultSet rs1=null;
java.sql.ResultSet rs2=null;
try{
String sErr = loadDriver();
if ( ! sErr.equals("") ) {
 try {
   out.println(sErr);
 }
 catch (Exception e) {}
}
con = cn();
st1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
con1 = cn();
st2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
con2 = cn();
st3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

String sub_name1="",dept_name1="",pub_name1="",sup_name1="";
String type="";
String sql1="",sql2="",sql3="",report_type="",from_v="",to_v="",strsql="",strsql2="",lblCaption="";
String auth="",depart="",pub="",sub="",sup="",acc="",tit="",callno="";
int price=0;
String fd,fm,fy,td,tm,ty;
int statistic_count=0,statistic_amount=0;
String dept="";
int number=0;
int amount=0;
int no_of_titles=0;
int total_amount=0;
int total_title=0;
int l=1;
int total_book=0;

int sub_total=0;
int grand_total=0;
int sub_price=0;
int grand_price=0;
if(request.getParameter("hid")!=null && request.getParameter("hid").equals("detail"))
{

	if(!request.getParameter("txtdepartment").equals(""))
	{
	sql1=sql1+ " and department = '" +String.valueOf(bean.TextBox(request.getParameter("txtdepartment")))+"'";

	}
	
	if(!request.getParameter("txtsubject").equals(""))
	{
	sql1=sql1+ " and subject ='" + String.valueOf(bean.TextBox(request.getParameter("txtsubject")))+"'";
	}
	if(!request.getParameter("txtpublisher").equals(""))
	{
	sql1=sql1+ " and publisher ='" + String.valueOf(bean.TextBox(request.getParameter("txtpublisher")))+"'";
	}
	if(!request.getParameter("txtsupplier").equals(""))
	{
	sql1=sql1+ " and supplier='" + String.valueOf(bean.TextBox(request.getParameter("txtsupplier")))+ "'";
	}
	
	if(request.getParameter("R1").equals("V1"))
	{
	from_v=request.getParameter("From_Y")+"-"+request.getParameter("From_M")+"-"+request.getParameter("From_D");
	to_v=request.getParameter("To_Y")+"-"+request.getParameter("To_M")+"-"+request.getParameter("To_D");
	sql2 = "where  Received_Date Between '" +from_v+ "' and '" +to_v+ "'";
	}

	if(request.getParameter("R1").equals("V2"))
	{
	from_v=request.getParameter("txtfromacc");
	to_v=request.getParameter("txttoacc");
	sql2=" where access_no between '"+from_v+"' and '"+to_v+"'"; 
	}
	
		
	if(request.getParameter("reporttype").equals("Department"))
    {
	%>
	<center><h3> Book Details (Department)</center></h3>
	<h4>From:<%=from_v%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=to_v%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<table border="1" align="Center" cellpadding=0 cellspacing=0>
		<td bgcolor="#C0C0C0" width="71" align="center">
		<b><font color="#FF0000">Sl No</font></b></td> 
		<td width=250 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Access No</font></b></td> 
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Author</font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Title</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">CallNo</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Publisher</font></b></td><br>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Subject</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Price</font></b></td>
		</th></tr>
<%
		strsql="Select distinct(department) as type,count(access_no)as number,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by department";
		//out.print(strsql);
		rs=st1.executeQuery(strsql);
			while (rs.next())
				{
					//dept_name1=rs.getString("type");
					
					dept_name1=rs.getString("type");
					String deptt_name1=String.valueOf(bean.TextBox(dept_name1));
					sub_total=rs.getInt("number");
					strsql="Select * from Advanced_Search  " +sql2+sql1 +" and department='"+deptt_name1+"'";
					out.print(strsql);
					rs1=st2.executeQuery(strsql);
					out.print("<tr><td colspan=7 align=left>"+"<b>Dept.Name:</b>"+dept_name1+"</td></tr>");
					sub_price=0;
					while(rs1.next())
						{

		auth=rs1.getString("Author");

		//depart=rs1.getString("Department");
		pub=rs1.getString("Publisher");
		sub=rs1.getString("Subject");
		sup=rs1.getString("Supplier");
		acc=rs1.getString("access_no");
		tit=rs1.getString("title");
		callno=rs1.getString("call_no");
		price=rs1.getInt("bcost");
		sub_price=sub_price+price;
		//total_amount=total_amount+amount;
		
		out.print("<tr>");
		out.print("<td>"+l+"</td>"+"<td>"+acc+"</td>"+"<td>"+auth+"</td>"+"<td >"+tit+"</td><td>"+callno+"</td><td>"+pub+"</td><td>"+sub+"</td><td align=right>"+price+"</td>");
		out.print("</tr>");
		l++;
		}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Sub Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=sub_total%>
</td>
<td align="right"><font size=3><b><%=sub_price%></td>
</tr> 
<%
grand_total=grand_total+sub_total;
grand_price=grand_price+sub_price;
}


%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Grand Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=grand_total%>

</td>
<td align="right"><font size=3><b><%=grand_price%></td>
</tr></table>
<%
}
	
	
	
	if(request.getParameter("reporttype").equals("Subject"))
   	{
    
    %>
	<center><h3> Book Details (Subject)</center></h3>
	<h4>From:<%=from_v%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=to_v%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<table border="1" align="Center" cellpadding=0 cellspacing=0>
		<td bgcolor="#C0C0C0" width="71" align="center">
		<b><font color="#FF0000">Sl No</font></b></td> 
		<td width=250 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Access No</font></b></td> 
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Author</font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Title</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">CallNo</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Publisher</font></b></td><br>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Department</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Price</font></b></td>
		</th></tr>
		
		<%
		strsql="Select distinct(subject) as type,count(access_no)as number,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by subject";
		//out.print(strsql);
		rs=st1.executeQuery(strsql);
			while (rs.next())
				{
				// sub_name1=rs.getString("type");
				 sub_name1=rs.getString("type");
					String subb_name1=String.valueOf(bean.TextBox(sub_name1));
					sub_total=rs.getInt("number");
					strsql="Select * from Advanced_Search  " +sql2+sql1 +" and subject='"+subb_name1+"'";
					
					rs1=st2.executeQuery(strsql);
					out.print("<tr><td colspan=7 align=left>"+"<b>Sub.Name:</b>"+sub_name1+"</td></tr>");
					while(rs1.next())
						{

		auth=rs1.getString("Author");

		depart=rs1.getString("Department");
		pub=rs1.getString("Publisher");
		
		//sub=rs1.getString("Subject");
		sup=rs1.getString("Supplier");
		acc=rs1.getString("access_no");
		tit=rs1.getString("title");
		callno=rs1.getString("call_no");
		price=rs1.getInt("bcost");
		sub_price=sub_price+price;
		//total_amount=total_amount+amount;
		
		out.print("<tr>");
		out.print("<td>"+l+"</td>"+"<td>"+acc+"</td>"+"<td>"+auth+"</td>"+"<td >"+tit+"</td><td>"+callno+"</td><td>"+pub+"</td><td>"+depart+"</td><td align=right>"+price+"</td>");
		out.print("</tr>");
		l++;
		}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Sub Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=sub_total%>
</td>
<td align="right"><font size=3><b><%=sub_price%></td>
</tr> 
<%
grand_total=grand_total+sub_total;
grand_price=grand_price+sub_price;
}


%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Grand Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=grand_total%>

</td>
<td align="right"><font size=3><b><%=grand_price%></td>
</tr></table>
<%
}



	if(request.getParameter("reporttype").equals("Publisher"))
    {

	%>
	<center><h3> Book Details (Publisher)</center></h3>
	<h4>From:<%=from_v%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=to_v%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<table border="1" align="Center" cellpadding=0 cellspacing=0>
		<td bgcolor="#C0C0C0" width="71" align="center">
		<b><font color="#FF0000">Sl No</font></b></td> 
		<td width=250 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Access No</font></b></td> 
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Author</font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Title</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">CallNo</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Department</font></b></td><br>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Subject</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Price</font></b></td>
		</th></tr>
		
		<%
		strsql="Select distinct(publisher) as type,count(access_no)as number,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by publisher";
		//out.print(strsql);
		rs=st1.executeQuery(strsql);
			while (rs.next())
				{
					sub_price=0;
					pub_name1=rs.getString("type");
					String pubb_name1=String.valueOf(bean.TextBox(pub_name1));
					sub_total=rs.getInt("number");
					strsql="Select * from Advanced_Search  " +sql2+sql1 +" and publisher='"+pubb_name1+"'";
					
					rs1=st2.executeQuery(strsql);
					out.print("<tr><td colspan=7 align=left>"+"<b>Pub.Name:</b>"+pub_name1+"</td></tr>");
					while(rs1.next())
						{

		auth=rs1.getString("Author");

		depart=rs1.getString("Department");
		pub=rs1.getString("Publisher");
		
		sub=rs1.getString("Subject");
		sup=rs1.getString("Supplier");
		acc=rs1.getString("access_no");
		tit=rs1.getString("title");
		callno=rs1.getString("call_no");
		price=rs1.getInt("bcost");
		sub_price=sub_price+price;
		out.print("<tr>");
		out.print("<td>"+l+"</td>"+"<td>"+acc+"</td>"+"<td>"+auth+"</td>"+"<td >"+tit+"</td><td>"+callno+"</td><td>"+depart+"</td><td>"+sub+"</td><td align=right>"+price+"</td>");
		out.print("</tr>");
		l++;
		}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Sub Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=sub_total%>
</td>
<td align="right"><font size=3><b><%=sub_price%></td>
</tr> 
<%
grand_total=grand_total+sub_total;
grand_price=grand_price+sub_price;
}


%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Grand Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=grand_total%>

</td>
<td align="right"><font size=3><b><%=grand_price%></td>
</tr></table>
<%
}
if(request.getParameter("reporttype").equals("Supplier"))
	{

	%>
	<center><h3> Book Details (Supplier)</center></h3>
	<h4>From:<%=from_v%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=to_v%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<table border="1" align="Center" cellpadding=0 cellspacing=0>
		<td bgcolor="#C0C0C0" width="71" align="center">
		<b><font color="#FF0000">Sl No</font></b></td> 
		<td width=250 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Access No</font></b></td> 
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Author</font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Title</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">CallNo</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Department</font></b></td><br>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Subject</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Price</font></b></td>
		</th></tr>
		
		<%
		strsql="Select distinct(supplier) as type,count(access_no)as number,sum(Bcost) as sum_amount from Book_Statistics  " + sql2+sql1 +" group by supplier";
		//out.print(strsql);
		rs=st1.executeQuery(strsql);
			while (rs.next())
				{
					sup_name1=rs.getString("type");
					String supp_name1=String.valueOf(bean.TextBox(sup_name1));
					sub_total=rs.getInt("number");
					strsql="Select * from Advanced_Search  " +sql2+sql1 +" and supplier='"+supp_name1+"'";
					
					rs1=st2.executeQuery(strsql);
					out.print("<tr><td colspan=7 align=left>"+"<b>Sup.Name:</b>"+sup_name1+"</td></tr>");
					while(rs1.next())
						{

		auth=rs1.getString("Author");

		depart=rs1.getString("Department");
		pub=rs1.getString("Publisher");
		
		sub=rs1.getString("Subject");
		sup=rs1.getString("Supplier");
		acc=rs1.getString("access_no");
		tit=rs1.getString("title");
		callno=rs1.getString("call_no");
		price=rs1.getInt("bcost");
		sub_price=sub_price+price;
		out.print("<tr>");
		out.print("<td>"+l+"</td>"+"<td>"+acc+"</td>"+"<td>"+auth+"</td>"+"<td >"+tit+"</td><td>"+callno+"</td><td>"+depart+"</td><td>"+sub+"</td><td align=right>"+price+"</td>");
		out.print("</tr>");
		l++;
		}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Sub Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=sub_total%>
</td>
<td align="right"><font size=3><b><%=sub_price%></td>
</tr> 
<%
grand_total=grand_total+sub_total;
grand_price=grand_price+sub_price;
}


%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Grand Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=grand_total%>

</td>
<td align="right"><font size=3><b><%=grand_price%></td>
</tr></table>
<%
	}

	
	
	if(request.getParameter("reporttype").equals("Department & Subject"))
	{ 
	%>
	<center><h3> Book Details (Department & Subject)</center></h3>
	<h4>From:<%=from_v%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=to_v%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<table border="1" align="Center" cellpadding=0 cellspacing=0>
		<td bgcolor="#C0C0C0" width="71" align="center">
		<b><font color="#FF0000">Sl No</font></b></td> 
		<td width=250 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Access No</font></b></td> 
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Author</font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Title</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">CallNo</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Publisher</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Price</font></b></td>
		</th></tr>	
<%

		strsql="Select distinct(department) from Book_Statistics  " + sql2+sql1;
		rs=st1.executeQuery(strsql);
		while(rs.next())
		{
		String dept_nameds=rs.getString("department");

					String deptt_nameds=String.valueOf(bean.TextBox(dept_nameds));
		
	    out.print("<tr><td colspan=7 align=left>"+"<b>Dept.Name:</b>"+dept_nameds+"</td></tr>");
		strsql="Select distinct(subject) as type,count(access_no)as number,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and department='"+deptt_nameds +"'"+" group by subject";
		//out.print(strsql);
		rs1=st2.executeQuery(strsql);
		
			while (rs1.next())
				{
					sub_name1=rs1.getString("type");
					
					String subb_name1=String.valueOf(bean.TextBox(sub_name1));
					sub_total=rs1.getInt("number");
					
					strsql="Select * from Advanced_Search  " +sql2+sql1 +" and subject='"+subb_name1+"'";
					//out.print(strsql);
					rs2=st3.executeQuery(strsql);
					
					out.print("<tr><td></td><td colspan=7 align=left>"+"<b>Sup.Name:</b>"+sub_name1+"</td></tr>");
					while(rs2.next())
						{

		auth=rs2.getString("Author");

		depart=rs2.getString("Department");
		pub=rs2.getString("Publisher");
		
		sub=rs2.getString("Subject");
		sup=rs2.getString("Supplier");
		acc=rs2.getString("access_no");
		tit=rs2.getString("title");
		callno=rs2.getString("call_no");
		price=rs2.getInt("bcost");
		sub_price=sub_price+price;
		out.print("<tr>");
		out.print("<td>"+l+"</td>"+"<td>"+acc+"</td>"+"<td>"+auth+"</td>"+"<td >"+tit+"</td><td>"+callno+"</td><td>"+depart+"</td><td>"+sub+"</td><td align=right>"+price+"</td>");
		out.print("</tr>");
		l++;
		}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Sub Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=sub_total%>
</td>
<td align="right"><font size=3><b><%=sub_price%></td>
</tr> 
<%
grand_total=grand_total+sub_total;
grand_price=grand_price+sub_price;
}

}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Grand Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=grand_total%>

</td>
<td align="right"><font size=3><b><%=grand_price%></td>
</tr></table>
<%
}
   
if(request.getParameter("reporttype").equals("Subject & Department"))
	{
	%>
	<center><h3> Book Details (Subject & Department)</center></h3>
	<h4>From:<%=from_v%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=to_v%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<table border="1" align="Center" cellpadding=0 cellspacing=0>
		<td bgcolor="#C0C0C0" width="71" align="center">
		<b><font color="#FF0000">Sl No</font></b></td> 
		<td width=250 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Access No</font></b></td> 
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Author</font></b></td>
		<td width=150 bgcolor="#C0C0C0" align="center">
		<b><font color="#FF0000">Title</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">CallNo</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Publisher</font></b></td>
		<td bgcolor="#C0C0C0" width="76" align="center">
		<b><font color="#FF0000">Price</font></b></td>
		</th></tr>	
<%

		strsql="Select distinct(subject) from Book_Statistics  " + sql2+sql1;
		rs=st1.executeQuery(strsql);
		while(rs.next())
		{
		String sub_nameds=rs.getString("subject");
		String subb_nameds=String.valueOf(bean.TextBox(sub_nameds));
	    out.print("<tr><td colspan=7 align=left>"+"<b>Sub.Name:</b>"+sub_nameds+"</td></tr>");
		strsql="Select distinct(department) as type,count(access_no)as number,sum(Bcost) as sum_amount from Book_Statistics " +sql2+sql1 +" and subject='"+subb_nameds +"'"+" group by department";
		
		rs1=st2.executeQuery(strsql);
		
		while (rs1.next())
				{
					dept_name1=rs1.getString("type");
					String deptt_name1=String.valueOf(bean.TextBox(dept_name1));
					sub_total=rs1.getInt("number");
					strsql="Select * from Advanced_Search  " +sql2+sql1 +" and department='"+deptt_name1+"'";
					
					rs2=st3.executeQuery(strsql);
					out.print("<tr><td></td><td colspan=7 align=left>"+"<b>Dept.Name:</b>"+dept_name1+"</td></tr>");
					while(rs2.next())
						{
		auth=rs2.getString("Author");
		depart=rs2.getString("Department");
		pub=rs2.getString("Publisher");
		sub=rs2.getString("Subject");
		sup=rs2.getString("Supplier");
		acc=rs2.getString("access_no");
		tit=rs2.getString("title");
		callno=rs2.getString("call_no");
		price=rs2.getInt("bcost");
		sub_price=sub_price+price;
		out.print("<tr>");
		out.print("<td>"+l+"</td>"+"<td>"+acc+"</td>"+"<td>"+auth+"</td>"+"<td >"+tit+"</td><td>"+callno+"</td><td>"+depart+"</td><td>"+sub+"</td><td align=right>"+price+"</td>");
		out.print("</tr>");
		l++;
		}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Sub Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=sub_total%>
</td>
<td align="right"><font size=3><b><%=sub_price%></td>
</tr> 
<%
grand_total=grand_total+sub_total;
grand_price=grand_price+sub_price;
}

}
%>
<tr ><td align="right" colspan="7" height="19">
<font size=3><b>Grand Total:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%=grand_total%>

</td>
<td align="right"><font size=3><b><%=grand_price%></td>
</tr></table>
<%
}
}
}catch(Exception e){out.println("Error" + e);}
%>
</form>
</html>
