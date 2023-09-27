
package Lib.Auto.Email_Reminder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.mail.MailService;
import Common.businessutil.sms.SmsService;
import Lib.Auto.Branch.BranchBean;



public class Reminder extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Reminder.class);

	private static final long serialVersionUID = 1L;

		String indexPage = null,flag=null;
		int mailcount=0;
		List SearchArrylist;
	
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
						
			MailService ss = BusinessServiceFactory.INSTANCE.getMailService();
			SmsService sms=BusinessServiceFactory.INSTANCE.getSmsService();
			
			
			flag = request.getParameter("flag");
			
			int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
			
			BranchBean branchBean = new BranchBean();
			branchBean.setEmail(session.getAttribute("instituteEmail").toString());
			branchBean.setPassword(session.getAttribute("instituteEmailPassword").toString());
			branchBean.setClg_name(session.getAttribute("instituteName").toString());
			branchBean.setAddress(session.getAttribute("instituteAddress").toString());
						
			if (flag.equals("DueReminderList")) {
				log4jLogger.info("start===========DueReminderList=====");
			
				SearchArrylist=ss.getDueReminderList(rk,branchBean);			
				
				session.setAttribute("DueReminderList", SearchArrylist);			
				indexPage = "/Email_Reminder/DueReminder.jsp";
				dispatch(request, response, indexPage);				

			}
			
			if (flag.equals("DueReminderMail")) {
				log4jLogger.info("start===========DueReminderMail=====");
				
				String code=request.getParameter("flag1");
				
				mailcount=ss.getDueReminderMail(code, rk,"ALL",branchBean);			
			
				if(mailcount>0)	{		
				    indexPage = "/Email_Reminder/index.jsp?check=SaveSuccess&mailCount="+mailcount;
				}else {
					indexPage = "/Email_Reminder/index.jsp?check=SaveFail";
				}
				dispatch(request, response, indexPage);				

			}
			
			if (flag.equals("DueReminderSMS")) {
				log4jLogger.info("start===========DueReminderMail=====");
				
				String code=request.getParameter("flag1");
				
				mailcount=sms.getDueReminderSMS(code, rk,"ALL");			
			
				if(mailcount>0)	{		
				    indexPage = "/Email_Reminder/index.jsp?check=SaveSuccessSMS&mailCount="+mailcount;
				}else {
					indexPage = "/Email_Reminder/index.jsp?check=SaveFailSMS";
				}
				dispatch(request, response, indexPage);				

			}			
			
			
		
		}
		catch (Exception sss) {
			throw new ServletException(sss);
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
