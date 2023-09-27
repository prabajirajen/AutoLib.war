<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("7"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<%@ page language="java" errorPage="/Error/ErrorPage.jsp" import="java.io.*" import="java.util.*" import="Lib.Auto.Miscellaneous.MiscellaneousBean" session="true" buffer="12kb" import="Common.Security"%>

<%
ArrayList sc=new ArrayList();
sc=(ArrayList)request.getAttribute("transactionmaster");
%>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/script/common.js" ></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/script/tamil.js" ></script>

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Miscellaneous.MiscellaneousBean"  type="Lib.Auto.Miscellaneous.MiscellaneousBean">
</jsp:useBean>


<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<title>AutoLib Software Systems</title>
<meta http-equiv="pragma" content="no-cache"/>
<script language="javascript" src="/AutoLib/popcalendar.js"></script>
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>


<body>

<form name="TransMas" method="post" action=./MiscellaneousServlet>
<br>
 <P ALIGN="left"><BR>
 <h2> Miscellaneous&nbsp;Charges </h2>
<table align="center" class="contentTable" width="45%">
<td>
<table align="center" width="90%">
<tr><td> &nbsp; </td></tr>
<tr><td Class="addedit">Ref.&nbsp;No.<td colspan="2">
<input type=text name=mrno size=7  maxlength=8 onKeyUp="return Ref_code_val();"  readOnly>  <!-- onKeydown="Javascript: if (event.keyCode==13) SearchRec();" -->
<input type=button name=Find_Member Class="submitButton" Value="Find" onclick=FindValue('MemberView')>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;<b>Date</b>
	<INPUT name=tdate size=10  onfocus=this.blur(); value="<%=Security.CalenderDate()%>">
				
				<SCRIPT language=javascript>
						if (!document.layers) {
						document.write("<input type=button onclick='popUpCalendar(this,TransMas.tdate, \"dd-mm-yyyy\",\"<%=Security.CalenderDate()%>\")'value='...' style='font-size:10 px'>")
						}
				</SCRIPT>				
</td></tr>
    
<tr><td Class="addedit">User&nbsp;ID<td><input type=text name=muserid size=18 maxlength="25" onKeydown="Javascript: if (event.keyCode==13) MemberSearch();"></td></tr>    

<tr><td Class="addedit">User&nbsp;Name<td><input type=text name=mname size=50 maxlength="30" readonly></td></tr>    

<tr><td Class="addedit">Course<td><input type=text name=mcourse size=50 maxlength="40" readonly></td></tr>    
    
<tr><td Class="addedit">Trans&nbsp;Head<td>

<select name="thead" size="1" id="thead" onchange="test();">
			                            <% 
			                           Iterator it=sc.iterator();
			                           
                                        while(it.hasNext()){
                                        	                                        	
                                        	MiscellaneousBean view=(MiscellaneousBean) it.next();                                        	
				                     
				                        String curr=view.getThead();
				                        String ind=view.getTamount();
				                        %>
				                       
				                       <%
				                       
				                       
				                       out.println("<option  value="+ind+">" +curr+"</option>");
                                       }
				                        %>
	 </select></td>
</tr>

<tr><td Class="addedit"><label id="quantity">Qty</label><td><input type=text name=mqty size=7 maxlength="12" onKeyUp="return Trans_quantity_val();"></td></tr>
   
<tr><td Class="addedit">Amount<td><input type=text name=tamount size=7 maxlength=8" onKeyUp="return Trans_amount_val();"></td></tr>
   
<tr><td Class="addedit">Remarks<td><input type=text name=tremarks size=50 maxlength="50"></td></tr>

<tr><td Class="addedit">Add. Field<td><input type=text name=tadd1 size=50 maxlength="50"></td></tr>
   
<tr><td colspan=3 align=center >
<CENTER>
<input type=button name=New Class="submitButton"  Value=New onclick=new_no() >
<input type=button name=Save Class="submitButton" value=Save onclick=SaveRec()>
<input type=button name=Delete Class="submitButton" Value=Delete onclick=DelRec()>
<!-- <input type=button name=search Class="submitButton"  Value=Search  onclick=SearchRec()> -->
<input type=button name=Clear Class="submitButton"  Value=Clear onclick=ClearRec()>
</CENTER>
<input type=hidden name=flag>
<input type=hidden name=lebelhead>
<tr><td> &nbsp; </td></tr>
</table>
</td>
</table>
</form>
</body>
</html>

<!--
////////////////////////////////////////////////JAVA SERVER PAGES SCRIPT ///////////////////////////////////////////////// --> 
<%
String valid=request.getParameter("check");

if(valid!=null){
if(request.getParameter("check")!=null){
             if(valid.equals("newMiscellaneousCode")){
             %>
               <script language="JavaScript">
                  document.TransMas.mrno.value="<%=beanobject.getMrno()%>";
                  document.TransMas.muserid.focus();
               </script>
             <%
}
            if(valid.equals("PayCheck")){
            		%>  
            <script language="JavaScript">    
                  document.TransMas.muserid.value="<%=beanobject.getMuserID()%>";
                  document.TransMas.mname.value="<%=beanobject.getMname()%>";
				  document.TransMas.tdate.value="<%=Security.getdate(beanobject.getTdate())%>";                  
                  document.TransMas.mcourse.value="<%=beanobject.getCourse()%>";
                  document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
             	  document.TransMas.lebelhead.value="<%=beanobject.getThead()%>";
             	  document.TransMas.mrno.value="<%=beanobject.getMrno()%>";
             	  document.TransMas.mqty.value="<%=beanobject.getQuantity()%>";

             	  
             if(confirm("Do you Want Collect Money ?"))
             {            	  
                 document.TransMas.method="post";
                 document.TransMas.flag.value="savepayment";
                 document.TransMas.submit();
             }
             else
             {
                    document.TransMas.flag.value="new";
    				document.TransMas.submit();
             }
             </script>	
<%
            }
              if(valid.equals("SaveMiscellaneous")){
	%>             <script language="JavaScript">
				 alert("Record Inserted Successfully!");
				 document.TransMas.flag.value="new";
				 document.TransMas.submit();
			     </script>		
				<%
				}
              
				if(valid.equals("FailSaveMiscellaneous")){%>
	            <script language="JavaScript">
				alert("Error Occured !");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
			   	</script><%
				}

				if(valid.equals("searchMember")){

					 %>
					  <script language="JavaScript">

					document.TransMas.muserid.value="<%=beanobject.getMuserID()%>";
					document.TransMas.mname.value="<%=beanobject.getMname()%>";					
					document.TransMas.mcourse.value="<%=beanobject.getCourse()%>";
					document.TransMas.mrno.value="<%=beanobject.getMrno()%>";					
				
					</script>
					<%
					}
				
				if(valid.equals("FailMember")){%>
	            <script language="JavaScript">
				alert("Member Not Found");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
			   	</script><%
				}
				
				if(valid.equals("searchMiscellaneous")){

					 %>
					  <script language="JavaScript">
					document.TransMas.mrno.value="<%=beanobject.getMrno()%>";		
				    document.TransMas.tdate.value="<%=beanobject.getTdate()%>";  									  
                    document.TransMas.muserid.value="<%=beanobject.getMuserID()%>";
					document.TransMas.mname.value="<%=beanobject.getMname()%>";					
					document.TransMas.mcourse.value="<%=beanobject.getCourse()%>";

					document.TransMas.thead.value="<%=beanobject.getThead()%>";
             	    document.TransMas.mqty.value="<%=beanobject.getQuantity()%>";				
					document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
					document.TransMas.tremarks.value="<%=beanobject.getTremarks()%>";
					document.TransMas.tadd1.value="<%=beanobject.getTaddfield1()%>";		
								
					</script>
					<%
					}
				if(valid.equals("FailSearch")){%>
	            <script language="JavaScript">
				alert("Record Not Found");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
			   	</script><%
				}			

				
				if(valid.equals("deleteCheck")){
					
					%>       
			     <script language="JavaScript">
				    document.TransMas.mrno.value="<%=beanobject.getMrno()%>";		
				    document.TransMas.tdate.value="<%=beanobject.getTdate()%>";  									  
                    document.TransMas.muserid.value="<%=beanobject.getMuserID()%>";
					document.TransMas.mname.value="<%=beanobject.getMname()%>";					
					document.TransMas.mcourse.value="<%=beanobject.getCourse()%>";

					document.TransMas.thead.value="<%=beanobject.getThead()%>";
             	    document.TransMas.mqty.value="<%=beanobject.getQuantity()%>";				
					document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
					document.TransMas.tremarks.value="<%=beanobject.getTremarks()%>";
					document.TransMas.tadd1.value="<%=beanobject.getTaddfield1()%>";
						
				        msg=confirm("Are You Sure To Delete?");
						if(msg){
						 document.TransMas.flag.value="Confirmdete";
						 document.TransMas.submit();	
						}
				 </script>		
					<%
						}
							
   			  				
   			if(valid.equals("DeleteMiscellaneous")){
   				
   				%>       
   				            <script language="JavaScript">
   							alert("Record Deleted Successfully!");
   							document.TransMas.flag.value="new";
   							document.TransMas.submit();
   						   </script>		
   							<%
   							}
   			
   			
   			if(valid.equals("UpdateCheck")){ 
   				%> 
   				  <script language="JavaScript">
   				  	document.TransMas.mrno.value="<%=beanobject.getMrno()%>";		
				    document.TransMas.tdate.value="<%=Security.getdate(beanobject.getTdate())%>";  									  
                    document.TransMas.muserid.value="<%=beanobject.getMuserID()%>";
					document.TransMas.mname.value="<%=beanobject.getMname()%>";					
					document.TransMas.mcourse.value="<%=beanobject.getCourse()%>";

					document.TransMas.thead.value="<%=beanobject.getThead()%>";
             	    document.TransMas.mqty.value="<%=beanobject.getQuantity()%>";				
					document.TransMas.tamount.value="<%=beanobject.getTamount()%>";
					document.TransMas.tremarks.value="<%=beanobject.getTremarks()%>";
					document.TransMas.tadd1.value="<%=beanobject.getTaddfield1()%>";	  				            
   					
   				    msg=confirm("Record Already Exists in code:"+"<%=beanobject.getMrno()%>"+",Do You Want update ?");
			
			                if(msg){
			                
			                     var el = document.getElementById('thead');
                                 var text = el.options[el.selectedIndex].innerHTML;
                                 document.TransMas.lebelhead.value=text;	
                    
                           			 document.TransMas.flag.value="update";
		                             document.TransMas.submit();				
			                       } 						           
   				   </script>	
   							 	
   							<%
   							}
   			
   			if(valid.equals("UpdateMiscellaneousMas")){%>
            <script language="JavaScript">
			alert("Record Updated Successfully!");
			document.TransMas.flag.value="new";
			document.TransMas.submit();
		   	</script><%
			}

}
}

%>
<!--
///////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 
<script language=javascript>
function test()
{

  var el = document.getElementById('thead');
  var text = el.options[el.selectedIndex].innerHTML;
  document.TransMas.lebelhead.value=text;	

 if(text == "LOSS OF RESOURCE")
 {
  document.getElementById('quantity').innerHTML = 'Access.No';
  //alert(document.TransMas.lebelhead.value);
 }else {
  document.getElementById('quantity').innerHTML = 'Qty';
  document.TransMas.tamount.value = document.TransMas.thead.value * document.TransMas.mqty.value;  
 }

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
document.TransMas.method="get";
document.TransMas.flag.value="new";
document.TransMas.submit();
}


function SearchRec(){
document.TransMas.method="post";
var no=document.TransMas.mrno.value;
if(no==""){
				document.TransMas.mrno.focus();
				alert("Insufficent Data");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
		  }else{
		       document.TransMas.flag.value="search";
			   document.TransMas.submit();			  
		}
	
}


function MemberSearch()
{
document.TransMas.method="post";
var no=document.TransMas.mrno.value;
if(no==""){
				//alert("First click New button !");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
		  }else if(document.TransMas.muserid.value=="")
		  {
		        document.TransMas.muserid.focus();
				alert("Insufficent Data");
				document.TransMas.flag.value="new";
				document.TransMas.submit();		  
		  }		  
		  else{
		       document.TransMas.flag.value="MemberSearch";
			   document.TransMas.submit();			  
		  }

}


function SaveRec(){
if(chk_mc()){
             document.TransMas.method="post";
			   if(document.TransMas.mname.value =="")   {
			      alert("Invalid User Details !");
			      document.TransMas.muserid.focus();
			   } else if(document.TransMas.mqty.value =="")    {
			      alert("Invalid Quantity Number !");
			      document.TransMas.mqty.focus();
			   } else if(isWhitespace(document.TransMas.tamount.value))  {
			      alert("Invalid Transaction Amount !");
			      document.TransMas.tamount.focus();
			   } else{
			   			   			   		
                    var el = document.getElementById('thead');
                    var text = el.options[el.selectedIndex].innerHTML;
                    document.TransMas.lebelhead.value=text;				   			   
                    
                msg=confirm("Are You Sure To Save?");
				if(msg){
			    	document.TransMas.flag.value="save";
		         	document.TransMas.submit();	
		         	}				
			}		
	
		}		
	else
	{	
	 alert("Insufficent Data");
	}
}

function chk_mc(){
var flag_cs;
var c;
var mc=document.TransMas.mrno.value;
if(mc=="")
{
				document.TransMas.mrno.focus();
				document.TransMas.flag.value="none";
				document.TransMas.mrno.value="";
				return false;
				}
				else{
		                    return true;
		                         } 

 }

function isWhitespace(str)
		{
	
			var re = /[\S]/g
			if (re.test(str)) return false;
			return true;
			
		}
		

function Ref_code_val() {
if((isNaN(document.TransMas.mrno.value))||(document.TransMas.mrno.value == ' ')) {
document.TransMas.mrno.select();
document.TransMas.mrno.value="";
    
  }
}

function Trans_quantity_val()  {
if((isNaN(document.TransMas.mqty.value))||(document.TransMas.mqty.value == ' ')) {
document.TransMas.mqty.select();
document.TransMas.mqty.value="";    
  }
  
  var el = document.getElementById('thead');
  var text = el.options[el.selectedIndex].innerHTML;
  document.TransMas.lebelhead.value=text;	

 if(text == "LOSS OF RESOURCE")
 {
  //document.getElementById('quantity').innerHTML = 'Access.No';
  //alert(document.TransMas.lebelhead.value);
  document.TransMas.tamount.value = document.TransMas.thead.value * 1;  
 }else {
  document.TransMas.tamount.value = document.TransMas.thead.value * document.TransMas.mqty.value;  
 }
  

}

function Trans_amount_val() {
if((isNaN(document.TransMas.tamount.value))||(document.TransMas.tamount.value == ' ')) {
document.TransMas.tamount.select();
document.TransMas.tamount.value="";
    
  }
}

function ClearRec(){ 
  
		 document.TransMas.flag.value="new";
		 document.TransMas.submit();

}



function DelRec(){
document.TransMas.method="post";
if (document.TransMas.mrno.value==""){
				document.TransMas.mrno.focus();
				alert("Insufficent Data");
				document.TransMas.flag.value="new";
				document.TransMas.submit();
				}
				else{
				  document.TransMas.flag.value="delete";
				  document.TransMas.submit();
				 }                         
				
}
			
function FindValue(val)
{
winpopup=window.open("search.jsp?flag="+val,"popup","height=400,width=600,left=100,top=100,scrollbars=yes,toolbar=no,status=yes,menubar=no");
}


    
</script> 
<!--
//////////////////////////////////////JAVA SCRIPT USEING CLIENT SIDE///////////////////////////////////////////////// --> 

