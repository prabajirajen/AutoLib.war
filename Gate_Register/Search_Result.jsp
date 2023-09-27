<%@ page language="java" session="true" buffer="12kb" import="java.sql.*,java.util.*" %>
<%@ include file="/Common.jsp" %>
<%
String cSec = checkSecurity(1, session, response, request);
if ("s1".equals(cSec) ) return;
%>
<form name="Acc_Report" method="post" >
<%
java.sql.Connection con=null;
java.sql.Statement st=null;
java.sql.ResultSet rs=null;
try{
String sErr = loadDriver();
con = cn();
st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
if ( ! sErr.equals("") ) {
 try {
   out.println(sErr);
 }
 catch (Exception e) {}
}
String Acc_no,Author,Title,Edition,Publisher,Place,Year,Price,NoPage,Callno,Subject,Supplier,Invoice;
String sql="",Flag_Status="",from_acc="",to_acc="",Received_Date,from_date,to_date,fromdate,todate;


from_date=getdate(request.getParameter("from"));
to_date=getdate(request.getParameter("to"));
Flag_Status=request.getParameter("flag");
from_acc=getParam( request, "From_Accno");
to_acc=getParam( request, "To_Accno");
fromdate=getholiday(from_date);
todate=getholiday(to_date);
if(Flag_Status.equals("ByAcc"))
{
fromdate=from_acc;
todate=to_acc;
}

%>
<center><h3> Accession Register</center></h3>
	<h4>From:<%=fromdate%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	To:<%=todate%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<%
out.print("<table border=1 cellpadding=0 cellspacing=0 >"+"<tr><th width='75'>"+"AccNo"+"</th><th>"+"Author"+"</th><th>"+"Title/Edition"+"</th><th>"+"Publisher/place"+"</th><th>"+"Year"+"</th><th>"+"Price"+"</th><th>"+"Page"+"</th><th>"+"Call_No/Subject"+"</th><th>"+"Supplier/Invoice"+"</th><th>"+"Date"+"</th></tr>");
if(Flag_Status!=null){
	if(Flag_Status.equals("ByAcc"))
	{
		sql="select * from Accession_Reg where access_no between '"+from_acc+"' and '"+to_acc+"'";
		}
	if(Flag_Status.equals("ByDate"))
	{
	//from_date=(String)session.getAttribute("fdate");
	//to_date=(String)session.getAttribute("tdate");
	sql="select * from Accession_reg where received_date between '"+from_date+"' and '"+to_date+"'";
	}
	//out.print(sql);
		rs=st.executeQuery(sql);
		while(rs.next())
		{
			Subject=rs.getString("sub_name");
			Acc_no=rs.getString("access_no");
			Title=rs.getString("title");
			Callno=rs.getString("call_no");
			Edition=rs.getString("Edition");
			Year=rs.getString("year_pub");
			Price=rs.getString("Bprice");
			Received_Date=getdate(rs.getString("Received_Date"));
			Invoice=rs.getString("invoice_no");
			Supplier=rs.getString("supplier");
			Publisher=rs.getString("publisher");
			Place=rs.getString("pubplace");
			Author=rs.getString("Author_name");
			NoPage=rs.getString("pages");
			out.print("<tr><td  width='75'>"+Acc_no+"</td><td width=100>"+Author+"</td><td width=100>"+Title+"Ed."+Edition+"</td><td width=100>"+Publisher+Place+"</td><td width=75>"+Year+"</td><td width=75>"+Price+"</td><td width=50>"+NoPage+"</td><td width=100>"+Callno+Subject+"</td><td width=100>"+Supplier+Invoice+"</td><td width=75>"+Received_Date+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");

}
}catch(Exception e){out.print("Error"+e);}
finally{
/*-------------------------------DATABASE CONNECTION && STATEMENT IS CLOSED----------------------------------------------------------------------*/	
if ( st != null ) st.close();
try{
if ( con != null ) con.close();
}catch(SQLException sl){out.println(sl.toString());}
  }
%>
</form>
