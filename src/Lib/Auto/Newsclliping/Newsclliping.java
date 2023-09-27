package Lib.Auto.Newsclliping;

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




public class Newsclliping extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Newsclliping.class);

	
	
	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";

	String Keyword_Name = "", SameCode = "";

	int Author_Interface_code, Author_Mas_code;

	

	String indexPage = null;
	

	NewscllipingBean ob = new NewscllipingBean();

	

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
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID"))))); 
			PrintWriter out = response.getWriter();
		

			flag = request.getParameter("flag");

			if (flag.equals("new")) {
				log4jLogger.info("new===========flag====="+flag);
				ob = new NewscllipingBean();
				ob=ss.getNewsMax();
				request.setAttribute("BeanObject",ob);
				
				indexPage = "/Newsclliping/index.jsp?check=newNews";
				dispatch(request, response, indexPage);
			}
			if (flag.equals("update")) {
				log4jLogger.info("update===========flag====="+flag);
				ob.setNcode(Integer.parseInt(request.getParameter("code")));
				ob.setNname(request.getParameter("name").trim());
				ob.setNtype(request.getParameter("type"));
								
				ob.setNdate(Security.TodayDate());				
				ob.setPno(request.getParameter("pageno"));
				ob.setNtitle(request.getParameter("title"));
				ob.setNsubject(request.getParameter("subject"));
				ob.setNkey(request.getParameter("keywords"));
				ob.setNabstract(request.getParameter("abstract"));
				ob.setNcontent(request.getParameter("content"));
				ob.setBranchCode(branchID);
				
				int count=ss.getNewsClipUpdate(ob);
				indexPage = "/Newsclliping/index.jsp?check=UpdateKeywords";
				dispatch(request, response, indexPage);
				
			}
			
			if (flag.equals("search")) {
				log4jLogger.info("search===========flag====="+flag);
				ob = new NewscllipingBean();				
				
				ob=ss.getNewsSearch(Integer.parseInt(request.getParameter("code")),branchID);
				if(ob!=null){
					request.setAttribute("BeanObject", ob);
					
					indexPage = "/Newsclliping/index.jsp?check=searchKeywords";	
				}else{
					indexPage = "/Newsclliping/index.jsp?check=FailKeywords";
				}
				dispatch(request, response, indexPage);
			}
			if (flag.equals("delete")) {
				log4jLogger.info("delete===========flag====="+flag);
				ob = new NewscllipingBean();
				ob=ss.getNewsSearch(Integer.parseInt(request.getParameter("code")),branchID);

				if(ob!=null){
					request.setAttribute("BeanObject", ob);
					
					indexPage = "/Newsclliping/index.jsp?check=deleteCheck";	
				}else{
					indexPage = "/Newsclliping/index.jsp?check=FailKeywords";
				}
				dispatch(request, response, indexPage);
			}
			if(flag.equals("Confirmdete")){
				log4jLogger.info("Confirmdete===========flag====="+flag);
							
					int count=ss.getNewsClipDelete(Integer.parseInt(request.getParameter("code")));

					indexPage = "/Newsclliping/index.jsp?check=DeleteKeywords";
				
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("save")) {
				log4jLogger.info("save===========flag====="+flag);
				ob = new NewscllipingBean();
				ob.setNcode(Integer.parseInt(request.getParameter("code")));
				
				int News_Mas_code=ss.getNewsCliMas(Integer.parseInt(request.getParameter("code")));
				
				
				if(News_Mas_code>0){
					
					ob = new NewscllipingBean();
					ob.setNcode(Integer.parseInt(request.getParameter("code")));
					ob.setNname(request.getParameter("name").trim());
					ob.setNtype(request.getParameter("type"));
					ob.setPno(request.getParameter("pageno"));
					ob.setNtitle(request.getParameter("title"));
					ob.setNsubject(request.getParameter("subject"));
					ob.setNkey(request.getParameter("keywords"));
					ob.setNabstract(request.getParameter("abstract"));
					ob.setNcontent(request.getParameter("content"));
					ob.setBranchCode(branchID);
					
					request.setAttribute("BeanObject", ob);
					indexPage = "/Newsclliping/index.jsp?check=UpdateCheck";
				}
					
				else{
					
					ob = new NewscllipingBean();
					ob.setNcode(Integer.parseInt(request.getParameter("code")));
					ob.setNname(request.getParameter("name").trim());
					ob.setNtype(request.getParameter("type"));
					ob.setNdate(Security.TodayDate());
					
					ob.setPno(request.getParameter("pageno"));
					ob.setNtitle(request.getParameter("title"));
					ob.setNsubject(request.getParameter("subject"));
					ob.setNkey(request.getParameter("keywords"));
					ob.setNabstract(request.getParameter("abstract"));
					ob.setNcontent(request.getParameter("content"));
					ob.setBranchCode(branchID);
					
					
					int count=ss.getNewClipSave(ob);
					indexPage = "/Newsclliping/index.jsp?check=SaveKeyword";
				}
				dispatch(request, response, indexPage);
			}
               
                if (flag.equals("Keywords")) {
    				List KeywordsArrylist = null;
    				
    				KeywordsArrylist = new ArrayList();
    				ob = new NewscllipingBean();
    				
    				request.setAttribute("serarch", KeywordsArrylist);
    				indexPage = "/Keywords/search.jsp?check=ok";
    				dispatch(request, response, indexPage);

    			}
                

		} catch (Exception sss) {
			throw new ServletException(sss);
			//sss.printStackTrace();
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

	/* (non-Javadoc)
	 * @see Lib.Auto.Author.Author_Interface#newRecord()
	 */

}
