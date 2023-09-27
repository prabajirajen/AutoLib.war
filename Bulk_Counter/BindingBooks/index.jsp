<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" session="true" buffer="12kb" import="java.sql.*" import="java.util.*" import="Lib.Auto.Binding.BindingBean" import="Common.Security" import="Lib.Auto.Binding_Books.BookBindingBean" %>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<jsp:useBean id="BeanObject" scope="request" class="Lib.Auto.Binding_Books.BookBindingBean"  type="Lib.Auto.Binding_Books.BookBindingBean">
</jsp:useBean>
<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<meta name="GENERATOR" content="Microsoft FrontPage 4.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<title>Binding&nbsp;Books</title>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body background="/AutoLib/soft.jpg">

<form method="POST" name="Book_Bindings" action="../Binding_Books/BindingAction">
 <br><br><br>
<%

ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("binding");

%>
<br><br>
<h2 >Binding&nbsp;Books</h2>
<table align="center" class="contentTable" width="45%">
<tr>
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
    <tr>
      <td Class="addedit">Binder Name</td>
      <td>
      <SELECT SIZE="1" id="txtBinderId" NAME="txtBinder" style="width:160px">
                <OPTION SELECTED VALUE="NO">SELECT</OPTION>
                                        <% 
			                           Iterator it=sc.iterator();
                                        while(it.hasNext()){
                                        	                                        	
                                        	BindingBean view=(BindingBean) it.next();                                        	
				                     
				                        int b_code=view.getCode();
				                        String b_name=view.getName();
				                       
				                       out.print("<option  value="+b_code+">" +b_name+"</option>");
                                       }
				                        %>

              </SELECT></td>

      <td Class="addedit"> Send&nbsp;Date</td>
        <TD>
	<INPUT name=SendDate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,Book_Bindings.SendDate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>
		 </td>
    </tr>
    <tr>
     <td Class="addedit">Acc.No</td>
      <td><input type="text" name="AccessNo" size="23" maxlength="15"  onKeydown="Javascript: if (event.keyCode==13) Save_Bind();">
      <td Class="addedit">Doc.type</td>
      <td><select name="doc" size="1" style="width:115px">
      <option  value="BOOK">BOOK</option>
      <option value="BOOK BANK">BOOK BANK</option>
	  <option value="NON BOOK">NON BOOK </option>      
	  <option value="REPORT">REPORT</option>
	  <option value="THESIS">THESIS</option>
	  <option value="STANDARD">STANDARD</option>
	  <option value="PROCEEDING">PROCEEDING</option>	  
	  <option value="BACK VOLUME">BACK VOLUME</option>
	  </select>
     
    </tr>
  </table>
 <tr><td>&nbsp;</td></tr> 
 
 
 </table>
 
 <p align="center">
      <input type="button" Class="submitButton" value="Display" name="display" onclick="Display_Binding()">
      <input type="button" value="Save" name="save" Class="submitButton" onclick="Save_Bind()">
      <input type="button" value="Return" name="return" Class="submitButton" onclick="Return_Binding()">
      <input type="button" Class="submitButton" value="Clear" name="clear" onclick="ClearRec()"></td>
     
  <input type="hidden" name="flag">
  <input type="hidden" name="index">
  </p>
</form>
</body>
</html>
<%
String valid=request.getParameter("check");
String binderCode=request.getParameter("binder");

if(binderCode!=null){
	%>
	<script>
	document.Book_Bindings.txtBinder.value=binderCode;
	</script>
	<%
}
// out.print("binderCode  "+binderCode);
if(valid!=null){
if(request.getParameter("check")!=null){

if(valid.equals("UpdateCheck")){
%>
                <script language="JavaScript">
		var i="<%=request.getParameter("ind")%>";
		document.Book_Bindings.txtBinder.options[i].selected=true;

		document.Book_Bindings.AccessNo.value="<%=BeanObject.getAccess_no()%>";
           <!-- document.Book_Bindings.txtBinder.value="<%=BeanObject.getBinderName()%>"; -->
                document.Book_Bindings.doc.value="<%=BeanObject.getDocument()%>";

		 document.Book_Bindings.SendDate.value="<%=BeanObject.getDate()%>";
                msg=confirm("Record Already Exists Are You Sure To Update?");
                 if(msg)
                   {
                                document.Book_Bindings.index.value=document.Book_Bindings.txtBinder.selectedIndex;                                     	document.Book_Bindings.flag.value="update";
				document.Book_Bindings.submit();

		            }
			    </script>

			<%
			}
			if(valid.equals("display")){
			
			try{   
				sc=(ArrayList)request.getAttribute("search");

		    out.print("<table border=1 bordercolor=#008000 align=center cellspacing=0 >");
		    out.print("<tr><th>Access&nbsp;No<th>Document<th>Date<th>Binder</tr>");
                    Iterator it1=sc.iterator();
                   
                    
                    
			while(it1.hasNext()){
				
				BookBindingBean view=(BookBindingBean) it1.next();
				 out.print("<td>"+view.getAccess_no()+"</td>");
				 out.print("<td>"+view.getDocument()+"</td>");
				 out.print("<td>"+view.getDate()+"</td>");
				 out.print("<td>"+view.getBinderName()+"</td>");
				 
				%>
		<tr onmouseover=this.style.color='red' onmouseout=this.style.color='black'>
		<script language=javascript>
		 document.write("<td>"+<%=view.getAccess_no()%>+"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDocument()%>"+"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getDate()%>"+"</td>");
		 document.write("<td>"+"&nbsp;<%=view.getBinderName()%>"+"</td>");
		 document.write("</tr>");
 		</script>
		<%
		}
		sc.clear();
	}catch (Exception e) {out.println(e.toString());}
   finally{
	sc.clear();
    }
    }

			if(valid.equals("updated")){
          %>
                <script language="JavaScript">

		alert("Record Updated Successfully......");
		clear();
		    </script>

			<%
			}
			if(valid.equals("deleted")){
          %>
                <script language="JavaScript">

		alert("Record Returned Successfully......");
		clear();

			    </script>

			<%
			}
			if(valid.equals("saved")){
				out.print("binderCode  "+binderCode);
				
%>

                <script type="text/javascript">

		alert("Record Entered Successfully......");
		var i="<%=request.getParameter("binder")%>";
		document.Book_Bindings.txtBinder.options[i].selected=true;

// 		clear();

			    </script>

			<%
			}
			if(valid.equals("Issuedbook")){
						%>
				        <script language="JavaScript">
						alert("Book is ISSUED......");
						clear();
					    </script>
						<%
							             }	
			if(valid.equals("Transfered")){
				%>
		        <script language="JavaScript">
				alert("Book is TRANSFERED......");
				clear();
			    </script>
				<%
				                        }
			if(valid.equals("Lost")){
				%>
		        <script language="JavaScript">
				alert("Book is LOST......");
				clear();
			    </script>
				<%
				                        }
			if(valid.equals("Missing")){
				%>
		        <script language="JavaScript">
				alert("Book is MISSING......");
				clear();
			    </script>
				<%
				                        }
			if(valid.equals("Withdrawn")){
				%>
		        <script language="JavaScript">
				alert("Book is WITHDRAWN......");
				clear();
			    </script>
				<%
				                        }			
			
			if(valid.equals("BindingBooks")){
				        %>
				        <script language="JavaScript">
						alert("Book is BINDING......");
						clear();
						</script>
						<%
							                 }	
			if(valid.equals("notPresent")){
                       %>
                      <script language="JavaScript">
		              alert("Record Not Present......");		
		              clear();
			          </script>

			<%
			}




}
}

%>
<script language="JavaScript">
 function show(val1,val2,val3,val4,val5){
 document.Book_Bindings.AccessNo.value=val2;
 document.Book_Bindings.doc.value=val3;
 document.Book_Bindings.SendDate.value=val4;
 document.Book_Bindings.txtBinder.options[document.Book_Bindings.txtBinder.selectedIndex].text=val5;
 }
  function Find_Access(val){
  winpopup=window.open("../BindingBooks/search_nmvc.jsp?flag="+val,"popup","height=400,width=600,top=100,left=100,status=yes,menubar=no,scrollbars=yes,toolbar=no");
  }
  function Save_Bind(){
  if(document.Book_Bindings.txtBinder.value=="NO" || document.Book_Bindings.AccessNo.value==""){
  alert("Binder Name or Access No can not be left blank");
  }else{
  document.Book_Bindings.index.value=document.Book_Bindings.txtBinder.selectedIndex;
  document.Book_Bindings.flag.value="save";
  document.Book_Bindings.submit();
  }
  }
  function ClearRec(){

  document.Book_Bindings.flag.value="clear";
  document.Book_Bindings.submit();
   document.Book_Bindings.reset();
  }
  function Display_Binding(){
  
  document.Book_Bindings.index.value=document.Book_Bindings.txtBinder.selectedIndex;
  document.Book_Bindings.flag.value="display";
  document.Book_Bindings.submit();
 
  }
  function Return_Binding(){
  if(document.Book_Bindings.AccessNo.value==""){
  alert("Please Specify Access No...");
  }else{
   document.Book_Bindings.flag.value="return";
  document.Book_Bindings.submit();
  }
  }
</script>


