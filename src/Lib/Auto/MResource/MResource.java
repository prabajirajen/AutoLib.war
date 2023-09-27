package Lib.Auto.MResource;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import org.apache.log4j.Logger;

import Common.DataQuery;
import Common.Security;


import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Currency.Currency;
import Lib.Auto.Currency.CurrencyBean;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.Context;
import javax.naming.InitialContext;



public class MResource extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(MResource.class);

	
	
	private static final long serialVersionUID = -8906987252709033668L;

	String accno = "", flag = "";

	String type = "", availability = "";

	
	String indexPage = null;
	

	MResourceBean ob = new MResourceBean();

	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			PrintWriter out = response.getWriter();
		

			flag = request.getParameter("flag");

			
			if (flag.equals("save")) {
				log4jLogger.info("save===========flag====="+flag);
				
				String Mresourcedate=Security.TextDate_member(request.getParameter("mdate"));
				String currentDateString_endate[]=Mresourcedate.split("-");					 
				  int cy1=Integer.parseInt(currentDateString_endate[0]); 
				  int cm1=Integer.parseInt(currentDateString_endate[1]); 
				  int cd1=Integer.parseInt(currentDateString_endate[2]); 
				  java.util.Calendar enr = new java.util.GregorianCalendar();
				  enr.set(cy1,cm1,cd1);				  
				  String enroll_date=currentDateString_endate[0]+"-"+currentDateString_endate[1]+"-"+currentDateString_endate[2];
				  
				  
				ob = new MResourceBean();
				ob.setAccessno(request.getParameter("accno"));
				ob.setDoctype(request.getParameter("type"));
				ob.setAvailability(request.getParameter("availability"));
				ob.setMdate(enroll_date);								
				
				MResourceBean bean = new MResourceBean();
				bean = ss.getMResourseMas(ob);   // For Titan
				
				int c = bean.getAccessno().length();
				int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
				
				if(bean.getDoctype().trim().equalsIgnoreCase("NOBOOK"))
				{
					indexPage = "/MResource/index.jsp?check=NotFound";
				}
				else if(bean.getBranch() != rk && rk != 0)   {
					indexPage = "/MResource/index.jsp?check=NotRightUser";
				}
				else if((c>0))   {
					
					ob = new MResourceBean();
					ob.setAccessno(request.getParameter("accno"));
					ob.setDoctype(request.getParameter("type"));
					ob.setAvailability(request.getParameter("availability"));
					ob.setMdate(enroll_date);
					request.setAttribute("BeanObject", ob);
					indexPage = "/MResource/index.jsp?check=UpdateCheck";
				}		
				else{
					
					ob = new MResourceBean();
					ob.setAccessno(request.getParameter("accno"));
					ob.setDoctype(request.getParameter("type"));
					ob.setAvailability(request.getParameter("availability"));
					ob.setMdate(enroll_date);					
					
					int count=ss.getMResourseSave(ob);
					
					indexPage = "/MResource/index.jsp?check=SaveMResource";
				}
				dispatch(request, response, indexPage);
			}
			if (flag.equals("delete")) 
			{
				String acc_no=request.getParameter("accno");
				int count = ss.getMResourseDelete(acc_no); 
				if(count==1)
				{
					indexPage = "/MResource/index.jsp?check=deleteMResource";
				}
				else
					indexPage = "/MResource/index.jsp?check=NotFound";
				dispatch(request, response, indexPage);
			}


		} catch (Exception sss) {
			//throw new ServletException(sss);
			sss.printStackTrace();
		} finally {
			try{
				

			}catch(Exception e){
			e.printStackTrace();
			}

		}

	}

	/** 
	 * Instance variable for SQL statement property
	 */

	/****************************************************************
	 *prepare the sql statement for execution
	 **/
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}



}
