package Lib.Auto.EBookSearch;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Common.Security;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EBookSelectView extends HttpServlet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag=null,SQL="";
	ArrayList DEATILS=new ArrayList ();
	java.sql.Connection con=null;
	java.sql.Statement st=null;
	java.sql.ResultSet rs=null;
	ResourceBundle rb =null;
	
	
	public void init() throws ServletException {
		String driver = getServletContext().getInitParameter("driver");
		String protocol = getServletContext().getInitParameter("protocol");

		if (driver == null || protocol == null) {
			throw new UnavailableException("Not Found");
		}

		try {
			rb = ResourceBundle.getBundle("LocalStrings");
		} catch (Exception e) {throw new ServletException(e);}
		
		try {
			
			Class.forName(driver);
			con =DriverManager.getConnection(
					protocol,
					rb.getString("Username"),
					rb.getString("Password"));
				} catch (Exception e) {
			throw new ServletException(e);

		}

	}
	
			public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

			performTask(request, response);

		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

			performTask(request, response);

		}
		public void performTask(
			HttpServletRequest request,
			HttpServletResponse response)  throws ServletException{

		
			try {
				
				HttpSession session = request.getSession(true);
				String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				flag=request.getParameter("flag");

				st=con.createStatement();

				out.println("<html>");
				out.print("<head>");
				out.print("</head>");
				out.println("<BODY background='/AutoLib/soft.jpg'>");
				out.print("<center>");
				out.print("<div align=right><a href=/AutoLib/EBookSearch/index.jsp><img border='0' src='/AutoLib/images/Back.gif'></a></div>");
				out.print("<form name=view>");
				

	if(flag!=null)
	{ 
		
	String s="";
	if(flag.equals("")){
	String[] itemselected;
	String code="";
	itemselected=request.getParameterValues("code");
		
	if(itemselected!=null){
	for(int i=0;i<itemselected.length;i++){
	code=itemselected[i];
	DEATILS.add(code);
	}
 
	Iterator it=DEATILS.iterator();
	String t="";
	while(it.hasNext()){
	t=(String)it.next();
	if(s.equals(""))
	s="'"+t+"'";
	else
	s=s+",'"+t+"'";
	}
	s="in ("+s+")";
	out.print("<font color='#800000' size='5'>EBook Search Result Full-View</font>");
	
	
	SQL="select * from ebook_mas_view  where Access_No "+s+"";
	rs=st.executeQuery(SQL);
	 while(rs.next()){
	 		 	
				out.print("<table border=1 bordercolor=blue width=480 height=202 cellspacing=0 cellpadding=0>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Access No"+"</font>"+"</b>"+"</td>");
				out.print("<td><b>&nbsp;"+rs.getString("access_no")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Call No"+"</font>"+"</b>"+"</td>");
				out.print("<td><b>&nbsp;"+rs.getString("call_no")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Title"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("title")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Edition"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Edition")+"</td>"+"</tr>");
				out.print("<tr>"+"<td>" +"<b>"+"<font color='#800000' size='3'>"+"Author"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("author_name")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Publisher"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("publisher")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Year"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("year_pub")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Pages"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("pages")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Subject"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Sub_Name")+"</td>"+"</tr>");
                out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Department"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("Dept_Name")+"</td>"+"</tr>");
				out.print("<tr>"+"<td >" +"<b>"+"<font color='#800000' size='3'>"+"Course"+"</font>"+"</b>"+"</td>");
				out.print("<td>&nbsp;"+rs.getString("TYPE")+"</td>"+"</tr>");
				out.print("</table>");
				out.print("<br><br>");		
				}
	 DEATILS.clear();
	
	
	}
	else
	{
//	  getServletConfig().getServletContext().getRequestDispatcher("/Simple/RecNot.html").include(request, response);
	out.print("<style=FILTER: glow(color=white,intensity=10); HEIGHT: 15px; weight: 45><b><font color=#800000 size=+4>Please Select Record(s)!!!</font></b><br><br><br><br><br><br>");
//	  out.print("<h3>Please Select Record(s)!!!!!!!</h3>");

	}
	}
		
	} 
	out.print("</center>");
	out.print("</form>");
	out.print("</body>");
	out.print("</html>");
			
				 } catch (Exception sss) {throw new ServletException(sss);}	
		  
		}


}



