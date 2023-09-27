<%

flag=request.getParameter("flag");
if (flag!=null)
{
sno=getParam( request, "slno");
if(flag.equals("search")){
sql=" select a.Sno, a.Ord_no, ord_date,"+
	" a.Sup_code, a.Bud_Code, a.Inv_No, inv_date , a.Amount, a.Dept_Code,"+
	" a.Year, a.Doc_Type, a.Cr_Deb, a.paid,  pay_date, a.Mode, a.Pay_Details,"+
	" a.Add1, a.add2,b.sp_code,b.sp_name ,c.dept_code,c.dept_name,d.bud_code,d.bud_head from " +
    " or_inv_Pay a,sup_Pub_Mas b,department_mas c,budget_mas d where sno ="+sno+
    " and a.sup_code=b.sp_code and a.bud_code=d.bud_code and a.dept_code=c.dept_code";
rs=st.executeQuery(sql);
if(rs.next()){
%><script>
var temp,i,opt;
document.ordinv.slno.value="<%=rs.getString("sno")%>";
document.ordinv.ordno.value="<%=rs.getString("ord_no")%>";
<%
ord_date=getorder(rs.getString("ord_date"));
%>
document.ordinv.orddate.value="<%=ord_date%>";
document.ordinv.scode.value="<%=rs.getString("sup_code")%>";
document.ordinv.bcode.value="<%=rs.getString("bud_code")%>";
document.ordinv.invno.value="<%=rs.getString("inv_no")%>";

<%
inv_date=getorder(rs.getString("Inv_Date"));
%>
document.ordinv.invdate.value="<%=inv_date%>";
document.ordinv.invamount.value="<%=rs.getString("amount")%>";
document.ordinv.dcode.value="<%=rs.getString("dept_code")%>";
document.ordinv.year.value="<%=rs.getString("year")%>";

temp="<%=rs.getString("doc_type")%>";

document.ordinv.docname.value=temp;

opt="<%=rs.getString("cr_Deb")%>";

if(opt=="Debit")
{document.ordinv.R1[0].checked=true;}
else
{document.ordinv.R1[1].checked=true;}

temp="<%=rs.getString("paid")%>";

document.ordinv.paid.value=temp;
<%
pay_date=getorder(rs.getString("pay_Date"));
%>
document.ordinv.paiddate.value="<%=pay_date%>";
temp="<%=rs.getString("mode")%>";

document.ordinv.mode.value=temp;

document.ordinv.paydetails.value="<%=rs.getString("pay_details")%>";
document.ordinv.add1.value="<%=rs.getString("add1")%>";
document.ordinv.add2.value="<%=rs.getString("add2")%>";
document.ordinv.sname.value="<%=rs.getString("sp_name")%>";
document.ordinv.dname.value="<%=rs.getString("dept_name")%>";
document.ordinv.bname.value="<%=rs.getString("bud_head")%>";
</script><%
}
else{
%><script>
alert("Record not found");
window.history.back();
document.ordinv.flag.value="new";
document.ordinv.submit();
//location.href="index.jsp";
</script>
<%}
}

if(flag.equals("new"))
{
sql="select max(sno) as no from or_inv_pay";
rs=st.executeQuery(sql);
if (rs.next())
%><script>document.ordinv.slno.value="<%=rs.getInt("no")+1%>";</script><%
}


if(flag.equals("save"))
{
sql="select * from or_inv_pay where sno="+sno;
rs=st.executeQuery(sql);
ord_date=request.getParameter("orddate");
inv_date=request.getParameter("invdate");
pay_date=request.getParameter("paiddate");
ord_no=getParam( request, "ordno");
sup_code=request.getParameter("scode");
bud_code=request.getParameter("bcode");
inv_no=getParam( request, "invno");
amount=getParam( request, "invamount");
dept_code=request.getParameter("dcode");
year=getParam( request, "year");
doc_type=request.getParameter("docname");

%><script>
 if(document.ordinv.R1[0].checked==true)
{document.ordinv.crdbflag.value="Debit";}
	if(document.ordinv.R1[1].checked==true)
{document.ordinv.crdbflag.value="Credit";}
</script><%

cr_dep=request.getParameter("crdbflag");
paid=request.getParameter("paid");
mode=request.getParameter("mode");
pay_details=getParam( request, "paydetails");
add1=getParam( request, "add1");
add2=getParam( request, "add2");

if (rs.next())
{
sql="UPDATE Or_Inv_Pay SET Sno="+sno+", Ord_no='"+ord_no+"',Ord_Date ='"+ord_date+"'"+
			",Sup_code="+sup_code+", Bud_Code="+bud_code +
			",Inv_No='"+inv_no+"',Inv_Date='"+inv_date+"'"+
			", Amount='"+amount+"',Dept_Code='"+dept_code+
			"',Year='"+year+"', Doc_Type='"+ doc_type+"',Cr_Deb='"+cr_dep+
			"', paid='"+ paid+"', Pay_date='"+pay_date+"', Mode='"+mode+
			"', Pay_Details='"+pay_details+"', Add1='"+ add1+"', add2='"+add2+"' WHERE  Sno ="+sno;
			st.execute(sql);
			%><script language="JavaScript">
			alert("Record Updated Successfully!");
		    history.back();
		    document.ordinv.flag.value="new";
            document.ordinv.submit();
		   // location.href="index.jsp";
			</script><%
	}
else
{
	sql="INSERT INTO Or_Inv_Pay ( Sno, Ord_no, Ord_Date, Sup_code, Bud_Code, Inv_No, Inv_Date, Amount, Dept_Code,"+
		 "Year, Doc_Type, Cr_Deb, paid, Pay_date, Mode, Pay_Details, Add1, add2)  VALUES "+
		 "("+sno+",'"+ord_no+"','"+ord_date+"',"+sup_code+","+bud_code+",'"+inv_no+"','"+inv_date+"','"+
	 	 amount+"',"+dept_code+",'"+year+"','"+doc_type+"','"+
	 	 cr_dep+"','"+paid+"','"+pay_date+"','"+mode+"','"+pay_details+"','"+add1+"','"+add2+"')";
		 st.execute(sql);

	%><script>
	alert("Record Inserted Successfully");
    history.back();
	document.ordinv.flag.value="new";
    document.ordinv.submit();
   // location.href="index.jsp";
	</script><%
}


}

if(flag.equals("delete"))
	{
	sql="delete from or_inv_pay where sno="+sno;
	st.execute(sql);
	%><script>
	alert("Record is deleted successfully");
    history.back();
	location.href="index.jsp";
	</script><%
	}

/*---------------------------------------JAVA SCRIPT USEING RECORD EXISTS CHECKING------------------------------------------------------------------------------------------------------*/
			 %><script language="JavaScript">
			function updatecheck(){

			<%
			if(sno.length()<1){sno="0";}
			String SQLstr="select * from or_inv_pay where sno="+sno;
			rs=st.executeQuery(SQLstr);
	        if (rs.next()){

		%>
			return true;
			<%}
			else
			{%>
			return false;
			<%}%>
		      }
		</script>
			<%

			if(flag.equalsIgnoreCase("cancel")){
	         %>
			<script language="JavaScript">
			document.ordinv.flag.value="new";
			document.ordinv.submit();
			</script>
			<%
	      }


}


%>
<script>

function NewRecord()
{
document.ordinv.method="post";
document.ordinv.flag.value="new";
document.ordinv.action="index.jsp";
document.ordinv.submit();
}

function SaveRecord()
{
if(document.ordinv.slno.value=="" || document.ordinv.ordno.value=="" || document.ordinv.invno.value==""
	|| document.ordinv.scode.value=="" || document.ordinv.bcode.value=="" || document.ordinv.invamount.value==""
	|| document.ordinv.dcode.value=="")
	{alert("Insufficiant Data");}
	else{

     if(check()){
    document.ordinv.method="post";
	document.ordinv.action="index.jsp";
	document.ordinv.submit();
	}
	}

	}
function DeleteRecord()
{
document.ordinv.method="post";
document.ordinv.flag.value="delete";
		if (document.ordinv.slno.value==""){
				document.ordinv.slno.focus();
				alert("Insufficent Data");
				document.ordinv.flag.value="none";
				location.href="index.jsp";
				return false;
				}else{
			     if(updatecheck()){
				 if(confirm("Are You Sure To Delete")){

						                               document.ordinv.action="index.jsp";
						                               document.ordinv.submit();
								                         }


			                                    }

							                       else
							                                  {
							                                    alert("Record Not Present!!!");
							                                  }
			  }

}


function SearchRecord()
	{
	var no=document.ordinv.slno.value;
	document.ordinv.method="post";
	if(no=="")
	{
	alert("Insufficiant Data");
	document.ordinv.flag.value="none";
	}
	else{
	document.ordinv.flag.value="search";
	document.ordinv.action="index.jsp";
	document.ordinv.submit();
	}


	}
function optcheck(val)
{
//alert(val);
if(val=="Debit")
document.ordinv.crdbflag.value="Debit";
else
document.ordinv.crdbflag.value="Credit";
}


function check()
{
   if(updatecheck()){
                 msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
                    document.ordinv.flag.value="save";
				 }
				 else
	                 {
	                          document.ordinv.flag.value="cancel";
			 	         }


		  }
		  else
            {
              document.ordinv.flag.value="save";
             }
			 return true;


}

function transfer()
{
 if(document.ordinv.slno.value=="" || document.ordinv.invno.value=="" ){
 alert("Please Select the Record");
 history.back();
document.ordinv.flag.value="new";
document.ordinv.submit();
 }
 else
 {
 if(updatecheck()){

  document.ordinv.action="transfer.jsp";
  document.ordinv.submit();
  }else
  {alert("Record Not Present!!! Please Save Your Record");}
 }



}


function Check_Numeric(boxval,opt)
{
if((isNaN(boxval))||(boxval == ' ') ||(boxval.substring(boxval.length-1)=='.'))
    {	var str=boxval;
		var i=str.length;
		boxval=str.substring(0,i-1);
		if(opt=='order'){document.ordinv.Order_Y.value=boxvol;}
		if(opt=='inv'){document.ordinv.Inv_Y.value=boxval; }
		if(opt=='paid'){document.ordinv.Paid_Y.value=boxval;}
    }
}

function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100 status=yes menubar=no scrollbars=yes");
}
function onfocus(){

document.ordinv.slno.focus();
}

</script>

