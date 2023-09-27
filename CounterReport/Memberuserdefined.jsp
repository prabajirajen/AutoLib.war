
<%@ page language="java" import="java.sql.*" %>

<jsp:useBean id="bean" scope="request" class="bean.DbCon" />
<jsp:setProperty name="bean" property="*"/>

<%
try 
{	
int sno=1,j=0,i=0;
int colwith =0,fieldcount=0;
String txtreporttype,txtaccessno,txtmembercode,txtdepartment,txtfdate,txttdate,txtgroup;
	String order1,order2,order3,str,strsql="",order,strsqlq="",condition="",txtcondition,txtreportfrom;
	String deptname="",groupname="",staff="",Reptfrom="",txtselectedfields,txtcourse,txtdoctype,coursename="";
	int subcount=0,fine=0;
	Connection con=null;
    ResultSet rs=null;
	Statement st=null;
	con=bean.getConnection();
    st=con.createStatement();

txtcondition=(String) session.getAttribute("condition");
%><script>alert("<%=txtcondition%>");</script><%
txtreporttype=(String) session.getAttribute("txtreporttype");
%><script>alert("<%=txtreporttype%>");</script><%
txtdepartment=(String) session.getAttribute("txtdepartment");
%><script>alert("<%=txtdepartment%>");</script><%
String ss[]=request.getParameterValues("selectedfields");

String temp=ss[0];
String rsvalue="";
String tokfield="";

for(i=1;i<ss.length;i++)
{
%><script>alert("<%=ss[i]%>");</script><%
temp=temp+","+ss[i];
}
fieldcount = i;
int flag =0;
int size =0;
String selectfields[]=new String[21];

for(i=0;i<=ss.length-1;i++){
/*-------------------------access_no----------------------------------------------*/
	if(ss[i].trim().equals("Access_No")) {
 	  if(flag==0){
		 tokfield = tokfield +" Access_No ";
		 flag=1;
		 size=size+70;
		 }
	  else{
		tokfield = tokfield+" , Access_No as AccessNo ";
		size=size+70;
		}
	}
/*-------------------------Year_Pub--------------------------------------------------------*/
if(ss[i].trim().equals("Member_Name")){
if(flag==0){

tokfield = tokfield+"Member_Name ";
flag=1;
size=size+200;
}
else
{
tokfield = tokfield+" , Member_Name ";
size=size+200;
}
}





/*-------------------------receivd date--------------------------------------------------------*/
  if (ss[i].trim().equals("Member_Code")){
if (flag==0){

tokfield = tokfield+"Member_Code ";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" , Member_Code ";
size=size+70;
}
}



/*-------------------------Tittle----------------------------------------------*/
	if (ss[i].trim().equals("Enroll_Date")){
	 if (flag == 0){
		tokfield = tokfield+" Enroll_Date ";
		flag=1;
		size=size+175;
}
	 else{
		tokfield = tokfield+" , Enroll_Date ";
		size=size+175;
		}
	 
}


/*-------------------------deptname-------------------------------------------------------*/

if(ss[i].trim().equals("Expiry_Date")){
if(flag == 0){

tokfield = tokfield+"Expiry_Date ";
flag=1;
size=size+175;
}
else
{
tokfield = tokfield+" , Expiry_Date ";
size=size+175;
}
}



/*-------------------------author_name----------------------------------------------*/
	if (ss[i].trim().equals( "Deposit_Amount")){
 	  if (flag==0){
		tokfield = tokfield+"Deposit_Amount ";
		flag=1;
		size=size+175;}
	  else{
		tokfield = tokfield+" , Deposit_Amount ";
		size=size+175;
	 }
	  
}


/*-------------------------editiio--------------------------------------------------------*/
if(ss[i].trim().equals("Designation")){
if (flag==0){
tokfield = tokfield+" Designation ";
flag=1;
size=size+175;
}
else
{
tokfield = tokfield+" , Designation ";
size=size+175;
}
}





/*-------------------------sub_name----------------------------------------------*/
	if (ss[i].trim().equals("Member_Add1")){
	 if (flag==0 ){
		tokfield = tokfield+"Member_Add1 ";
		flag=1;
		size=size+175;}
	 else{
		tokfield = tokfield+" , Member_Add1 ";
		size=size+175;
	}
	 
}


/*-------------------------avail-------------------------------------------------------*/
  if(ss[i].trim().equals("Member_Add2")){
    if (flag == 0){
    tokfield = tokfield+" Member_Add2 ";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" , Member_Add2 ";
size=size+70;
}
}



/*-------------------------sp_name----------------------------------------------*/
	if (ss[i].trim().equals("Member_City")){
		  if (flag==0){
		tokfield = tokfield+"Member_City";
		flag=1;
		size=size+175;}
	  else{
		tokfield = tokfield+" , Member_City";
		size=size+175;
	  }
	  }

	  
	  
/*-------------------------city--------------------------------------------------------*/
if(ss[i].trim().equals("Member_State")){
if (flag==0){
tokfield = tokfield+"Member_State ";
flag=1;
size=size+175;}
else
{
tokfield = tokfield+" , Member_State ";
size=size+175;
}
}

/*-------------------------keywords----------------------------------------------*/
if (ss[i].trim().equals("Member_Pincode")){
 if (flag==0){
  
 tokfield = tokfield+" Member_Pincode";
flag=1;
size=size+175;
}
else
{
tokfield = tokfield+" , Member_Pincode";
size=size+175;
}
}

/*-------------------------location-------------------------------------------------------*/
 if(ss[i].trim().equals("Location")){
if (flag==0){

tokfield = tokfield+" Location ";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" , Location ";
size=size+70;
}
}

/*-------------------------ISBN----------------------------------------------*/
if (ss[i].trim().equals("ISBN")){
if (flag==0){
tokfield = tokfield+" ISBN ";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" , ISBN ";
size=size+70;
}
}

/*-------------------------bprice-------------------------------------------------------*/
 if(ss[i].trim().equals("BPrice")){
   if(flag ==0){
tokfield = tokfield+"BPrice ";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" , BPrice";
size=size+70;
}
}


/*-------------------------type-----------------------------------------*/
 if (ss[i].trim().equals("Document")){
if (flag ==0 ){

tokfield = tokfield+"Document ";
flag=1;
size=size+70;
}
else{
tokfield = tokfield+" , Document ";
size=size+70;
}
}


/*-------------------------bprice-------------------------------------------------------*/
 if(ss[i].trim().equals("Supplier")){
if(flag ==0){
tokfield = tokfield+"Supplier";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" ,Supplier";
size=size+70;
}
}


/*-------------------------Invoice_No-----------------------------------------*/

 if(ss[i].trim().equals("Invoice_No")){
if (flag==0){

tokfield = tokfield+"Invoice_No ";
flag=1;
size=size+70;}
else{

tokfield = tokfield+" , Invoice_No ";
size=size+70;
}
}

/*-------------------------budget code-------------------------------------------------------*/
  if(ss[i].trim().equals("Budg_Code")){
 if (flag==0){
 
tokfield = tokfield+"Budg_Code ";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" , Budg_Code ";
size=size+70;
}
}

/*-------------------------pages----------------------------------------*/
  if(ss[i].trim().equals("pages")){
  if (flag==0){
 tokfield = tokfield+" Pages ";
flag=1;
size=size+70;
}
else
{
tokfield = tokfield+" , Pages ";
size=size+70;
}
}
/*-------------------------call_no----------------------------------------------*/
	if (ss[i].trim().equals("Call_No")){
		if (flag==0){
		tokfield = tokfield+"Call_No ";
		flag=1;
		size=size+70;}
	  else{
		tokfield = tokfield+" , Call_No ";
		size=size+70;
	  }	  
}
/*------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
}
String sql="select  "+tokfield+" from juserstat where 1<2 "+txtcondition;  //temp
//out.print(sql);
rs=st.executeQuery(sql);
if(!rs.next())
{
out.print( "<BR><BR><BR><BR><BR><BR><BR><CENTER><H2> NO DATA FOUND </center></h3>");}
else{
out.print( "<CENTER><H3> BOOKS USERDEFINED REPORT</center></h3>");}
out.print("<p align=right><B>sss</p>");

/*-----------------------------------------------VERTICAL--------------------------------------------------------------------------------------------------------------------------*/
if(txtreporttype.equals("VERTICAL"))
{
while(rs.next())
{
out.print( "<table border=0>");
out.print( "<tr><td width=35>"+sno+"</td>");
for( j=0;j<ss.length;j++) 
{
rsvalue=rs.getString(ss[j]);
out.print("<td><strong><font >"+ss[j]+"</font></td>" );
out.print("<td><strong>&nbsp;:&nbsp;</strong>"+rsvalue+"</font></td></tr>" );
out.print("<tr><td width=25></td>");
//}
}
out.print("</tr></table></center><br>");
sno =sno+1;
}

}

/*




if trim(sfields(i)) ="Member_Pincode" then
if flag=0 then
tokfield = tokfield&" Member_Pincode as Pincode"
flag=1
size=size+70
else
tokfield = tokfield&" , Member_Pincode as Pincode "
size=size+70
end if
end if

if trim(sfields(i)) ="Member_Email" then
if flag=0 then
tokfield = tokfield&" Member_Email as Email"
flag=1
size=size+70
else
tokfield = tokfield&" , Member_Email as Email"
size=size+70
end if
end if

if trim(sfields(i)) ="Profile" then
if flag =0 then
tokfield = tokfield&" Profile"
flag=1
size=size+70
else
tokfield = tokfield&" , Profile "
size=size+70
end if
end if

if trim(sfields(i)) ="Issue_Date" then
if flag=0 then
tokfield = tokfield&" convert(char(11),Issue_Date,103) as IssueDate"
flag=1
size=size+70
else
tokfield = tokfield&" , convert(char(11),Issue_Date,103) as IssueDate"
size=size+70
end if
end if

if trim(sfields(i)) ="Return_Date" then
if flag=0 then
tokfield = tokfield&" convert(char(11),Return_Date,103) as ReturnDate"
flag=1
size=size+70
else
tokfield = tokfield&" , convert(char(11),Return_Date,103) as ReturnDate"
size=size+70
end if
end if



if trim(sfields(i)) ="Fine_Amount" then
if flag=0 then
tokfield = tokfield&" Fine_Amount as Fine"
flag=1
size=size+70
else
tokfield = tokfield&" , Fine_Amount as Fine"
size=size+70
end if
end if



if trim(sfields(i)) ="Doc_Type" then
if flag=0 then
tokfield = tokfield&" Doc_Type as Doctype "
flag=1
size=size+70
else
tokfield = tokfield&" , Doc_Type as Doctype "
size=size+70
end if
end if


if trim(sfields(i)) ="Dept_Name" then
if flag=0 then
tokfield = tokfield&" Dept_Name as Department "
flag=1
size=size+70
else
tokfield = tokfield&" , Dept_Name as Department"
size=size+70
end if
end if

if trim(sfields(i)) ="Course_Name" then
if flag=0 then
tokfield = tokfield&" Course_Name  as Course"
flag=1
size=size+70
else
tokfield = tokfield&" , Course_Name  as Course"
size=size+70
end if
end if


if trim(sfields(i)) = "Title" then
if flag=0 then
tokfield = tokfield&" Title"
flag=1
size=size+300
else
tokfield = tokfield&" , Title"
size=size+300
end if
end if

if trim(sfields(i)) = "Issue_Type" then
if flag=0 then
tokfield = tokfield&" Issue_Type  as Issuetype"
flag=1
size=size+70
else
tokfield = tokfield&" , Issue_Type  as Issuetype"
size=size+70
end if
end if

next
size= cint(size)



''''''''

if txtreporttype = "Issue" then

strsqlq = "set dateformat dmy; select " &tokfield &"  from juserstat where 1<2  and Issue_Type='ISSUE' "&txtcondition

end if



if txtreporttype = "Renewal" then

strsqlq = "set dateformat dmy; select " &tokfield &"  from juserstat where 1<2 and Issue_Type = 'RENEW' "&txtcondition

end if


if txtreporttype = "Duereminder" then
tempdate = split(date,"/")
comdate = tempdate(1)&"/"&tempdate(0)&"/"&tempdate(2)
strsqlq = "set dateformat dmy; select " &tokfield &"  from juserstat where 1<2  "&txtcondition
end if



if txtreporttype = "Return" then
strsqlq = "set dateformat dmy; select " &tokfield &"  from jhistory where 1<2 "&txtcondition
end if


if txtreporttype = "Fine" then
strsqlq = "set dateformat dmy; select " &tokfield &"  from jhistory where 1<2  and Fine_Amount <> 0  "&txtcondition
end if

rs.open strsqlq
'response.write strsqlq
 if not rs.eof then
  columncount = rs.Fields.Count
  

if txtrtype <> "VERTICAL" then


 rowcount= 0
valu=""
 response.write"<font size=5><center>BOOK REPORT</center></font><p align=right><strong>Date : "&date


response.write"<table border=1 cellspacing=0 align=center width="&size&">"

 columncount = rs.Fields.Count
response.write" <tr><th height=50 widht= 25 >S.No</font></th>"

 with1 = 0
 fla=0
 pos=0
 for  i=0 to columncount-1
	 head=rs.fields(i).name
	   if head="AccessNo" then
        with1 = 70
        end if
      if head="Membername" then
        fla=1
        pos=i
        with1 = 200
      end if
      if head="Membercode" then
         with1 = 70
         end if

      if head="Enrolldate" then
        with1 = 175
         end if

      if head="Expirydate" then
        with1 = 175
         end if

      if head="Depositamount" then
        with1 = 175
         end if

      if head="Designation" then
        with1 = 175
	   end if

      if head="Address1" then
        with1 = 175
      end if
      if head= "Address2" then
        with1 = 175
		end if

      if head="City" then
        with1 = 70
       end if
       
      if head="State" then
         
        with1 = 70
         end if

      if head = "Pincode" then
        with1 = 70
         end if

      if head="Email" then
        with1 = 70
         end if

      if head="Profile" then
         with1 = 70
         end if

      if head="IssueDate" then
         with1 = 70
         end if

      if head="ReturnDate" then
         with1 = 70
         end if

      if head="Fine" then
         with1 = 70
         end if

      if head="Doctype" then
         with1 = 70
         end if

      if head="Department" then
        with1 = 70
         end if

      if head="Course" then
         with1 = 70
         end if

      if head="Title" then
         with1 = 300
        end if
        
         if head="Issuetype" then
         with1 = 70
        end if

    response.write"<th height=50 width="&with1&">"& head&"</font></th>"
next

response.write"</tr>"

 sno=0

 tot =0
do while not rs.eof
  sno=sno+1
response.write"<tr>"
response.write"<td>"&sno&"</font></td>"
i=o
for i=0 to columncount-1
response.write"<td>"&rs(i)&"</font></td>"
next
response.write"</tr>"  
if not rs.eof then
rs.movenext
end if
loop
response.write"</table>"
else
 response.write"<font size=5><center>BOOK REPORT</center></font><p align=right><strong>Date : "&date&"</p>"
sno=1
do while not rs.eof
response.write"<table border=0 colspace=0 >"

response.write"<tr><td width=30>"&sno&"</td>"
for i=0 to columncount-1
response.write"<td><b>"&rs.fields(i).name&"</td><td> <b> : </b>"&rs(i)&"</td></tr>"
response.write"<td></td>"

next

if not rs.eof then
rs.movenext
sno=sno+1
end if
response.write"</table><br>"
loop
end if
else
response.write "<font size=5><br><br><br><br><b><center>No Data Found"
end if */

/*-------------------------------DATABASE CONNECTION && STATEMENT IS CLOSED----------------------------------------------------------------------*/	
	if ( st != null ) st.close();
    if ( con != null ) con.close();
}  catch (Exception e) {

 out.println(e.toString());

  }%>

<HEAD>
<META NAME="GENERATOR" CONTENT="Microsoft FrontPage 4.0">
<META NAME="ProgId" CONTENT="FrontPage.Editor.Document">
</HEAD>

