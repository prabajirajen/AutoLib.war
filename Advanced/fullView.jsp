<%@ page language="java"  session="true" buffer="12kb" import="Lib.Auto.Advanced.Adsearchbean" import="Lib.Auto.Review.ReviewBean" import="Common.Security" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/button_css.css" />
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/starrating.css"/>

<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">

<jsp:useBean id="beanobject" scope="request"  class="Lib.Auto.Author.AuthorBean"  type="Lib.Auto.Author.AuthorBean">
</jsp:useBean>
<html>
<%
   response.setHeader("Pragma", "No-cache");
   response.setHeader("Cache-Control", "no-cache");
   response.setDateHeader("Expires", 0);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Autolib Software Systems,Chennai</title>
<meta http-equiv="pragma" content="no-cache">
<link rel="stylesheet" type="text/css" href="/AutoLib/style.css">
</head>
<body >

<%

ArrayList sc=new ArrayList();
String accNo="";
sc=(ArrayList)request.getAttribute("AdsearchArrylist");

Iterator it=sc.iterator();
%>
<%
ReviewBean view3 = new ReviewBean();
%>

  <form method="get" name="query" action="./SimpleServlet"  ONSUBMIT="return validate(this)">
  <br>
<input type=hidden name=accNo>
 <table align="center">
 <tr><td>
 <center>
    <h2 >Full View</h2>
</center>
</td>
 </tr>
 </table>
   
    <%
    while(it.hasNext()){
    	Adsearchbean view=(Adsearchbean) it.next();
    %>
     
    <center>
   
    <table align="center">
            <tr>
              <td Class="addedit" align="left">Access&nbsp;No</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getAccno()%>     </td>
            </tr>
          
               <tr>
              <td Class="addedit" align="left">Title</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getTitle()%></td>
            </tr>
            
    <%if(!view.getEdition().equalsIgnoreCase("") && !view.getEdition().isEmpty() && !view.getEdition().equalsIgnoreCase(null)){%>
    	
     		<tr>
              <td Class="addedit" align="left">Edition</td>
              <td colspan="2" Class="addedit" align="left">:&nbsp;<%=view.getEdition() %>     </td>
            </tr>
    	
    <%}%>
            <tr>            
              <td Class="addedit" align="left">Author</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getAuthor()%></td>
            </tr>

   <%if(!view.getCallno().equalsIgnoreCase("") && !view.getCallno().isEmpty() && !view.getCallno().equalsIgnoreCase(null)){%>
            
	         <tr>
			<td Class="addedit" align="left">Call.No</td>			
			<td Class="addedit" align="left">:&nbsp;<%=view.getCallno()%></td>
	         </tr>
	          <%}%>
	         
           
            <tr>
              <td Class="addedit" align="left">Publisher</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getPlace()%>:<%=view.getPublisher()%>,<%=view.getYear()%></td>
             </tr>
           
	<%-- <%if(!view.getYear().equalsIgnoreCase("") && !view.getYear().isEmpty() && !view.getYear().equalsIgnoreCase(null)){%>
            <tr>
              <td Class="addedit" align="left">Year</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getYear()%></td>
             </tr>
            <%}%> --%>
            
            <tr>
              <td Class="addedit" align="left">Subject</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSubject()%></td>
            </tr>
            

            <tr>
              <td Class="addedit" align="left">Department</td>
            <td Class="addedit" align="left">:&nbsp;<%=view.getdepartment()%></td>
            </tr>
           


            <tr>
              <td Class="addedit" align="left">Document</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getdocument()%></td>
            </tr>
           
 	<%if(!view.getlocation().equalsIgnoreCase("") && !view.getlocation().isEmpty() && !view.getlocation().equalsIgnoreCase(null)){%>
   			<tr>
              <td Class="addedit" align="left">Location</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getlocation() %></td>
            </tr>
             <%}%>
            
 	<%if(!view.getAddField1().equalsIgnoreCase("") && !view.getAddField1().isEmpty() && !view.getAddField1().equalsIgnoreCase(null) && view.getdocument().equalsIgnoreCase("BOOK")){%>
            
             <tr>
              <td Class="addedit" align="left">AvResources</td>
              <td Class="addedit" align="left">:&nbsp;<%=view.getSize()%>&nbsp;: <%=view.getAddField1()%> </td>
            </tr>
 <%}%>

			<%String res=view.getavailability();
            if(res.equals("ISSUED")){ 
            %>
            <tr>
            <td Class="addedit" align="left">Availability</td>
                <td Class="addedit" align="left">:&nbsp;<%=view.getavailability()%>&nbsp;&nbsp;&nbsp;:<a href="AccountServlet?reservecheck=<%=view.getAccno()%>&document=<%=view.getdocument()%>">Reserve</a></td>           
            </tr>
                        
            <%
            }else{
            	%>
            <tr>
             <td Class="addedit" align="left">Availability</td>
             <td Class="addedit" align="left">:&nbsp;<%=view.getavailability()%></td>
            </tr>	
           	
           	<%}
            String cont=view.getcontent();
            if((!cont.equals(""))&&(!cont.equals(null)) && (!cont.isEmpty())){
            %>
            
            <tr>
            <td Class="addedit" align="left">Content</td>
             <td Class="addedit" align="left">:&nbsp;<a href="<%= request.getContextPath() %>/filePath/<%= view.getdocument() %>/<%=view.getcontent()%> " target=_base>Click to Contents</a></td>            
            </tr>
          
            <%}
            session.setAttribute("AccessNo",view.getAccno());
            session.setAttribute("Title",view.getTitle());
            }sc.clear();%>
            
            <tr>
              <td Class="addedit" align="left" style="color: blue;font-size: 13px">Campus</td>
              <td Class="addedit" align="left" style="color: blue;font-size: 13px">:&nbsp;<%=request.getParameter("branch")%></td>
            </tr>
			
			
			<tr>
            
            <td></td><td>

<b><input type=button name="FindBranch" Value="&nbsp;ILL&nbsp;Request"  Class="submitButton" onclick="illReq()">

    </td>
</tr>
           
<tr><td>
 <br>
<b><a href="<%= request.getContextPath() %>/Review/ReviewServlet?flag=new" style="color: #FF0000">Add Book Review</a></b>
    </td>
</tr>

<tr><td>
<b><a href="<%=request.getContextPath() %>/Review/ReviewDisplayServlet" style="color: #006400">See  Reviews</a></b>

    </td>
</tr>
</table>
<%
   Adsearchbean view = new Adsearchbean();
   String s = view.getAccno();
   if(s != null)
%> 

<%-- <p align="center"><b><a  href="<%= request.getContextPath() %>/Review/ReviewDisplayServlet" target="filterFrame" style="color: #006400" >See Book Reviews</a></b></p>
<IFRAME frameborder="0" style="width: 90%; height: 300px;" marginwidth="0" marginheight="0" scrolling="yes" vspace="0" hspace="0" name="filterFrame" src="/AutoLib/Review/ReviewContents.html">
 </IFRAME> --%>
<p align="center">
<input type=button name=New Class="submitButton"  Value=Back onclick='back()'>
</p>
<script>
function back()
{
	history.go(-1);
}

function illReq()
{
	alert('Please Contact your Librarian to borrow this book on Inter-Library Loan');
}
</script>
</form>
</body>
</html>