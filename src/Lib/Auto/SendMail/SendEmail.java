package Lib.Auto.SendMail;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import Common.Security_Counter;
import Common.businessutil.mail.MailService;
import Common.businessutil.sms.SmsService;
import Common.businessutil.BusinessServiceFactory;
import Lib.Auto.Branch.BranchBean;




public class SendEmail extends HttpServlet
{
	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(SendEmail.class);	
	int a;	
	String indexPage="",flag="";
	String message="Testing message from AutoLib",subject="Testing Message",FEmail="";
	String[] toaddress={"autolibsoft@yahoo.com","saravana_kks@rediffmail.com","karuppaiah.g.r@gmail.com","mshekoli@gmail.com"};;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		performTask(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		performTask(request, response);

	}	
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException, IOException {
		
		try {						
			
			MailService ss=BusinessServiceFactory.INSTANCE.getMailService();
			
			BranchBean branchBean = new BranchBean();
			
			flag=request.getParameter("flag");
			
			
			if(flag.equals("SendMail")) {	
			
			log4jLogger.info("===== Inside SendEmail Method ====");
									
			  a=ss.getSendEmail(toaddress,subject,message,branchBean);		
			
			if(a==1) {
				indexPage="/SendMail/mail.jsp?check=SaveSuccess";				
			}else{
				indexPage="/SendMail/mail.jsp?check=SaveFail";
			}
					
			}
			
			if(flag.equals("ForgetPWD")) {	
				
			log4jLogger.info("===== Inside ForgetPassword Method ====");	
				
			   FEmail=request.getParameter("emailid");
			   boolean chk=Security_Counter.EmailValidator(FEmail);	     
			   if(chk==true) {
			       a=ss.getForgetPassword(FEmail,branchBean);
			   }else{
				   a=0;  // Invalid Email ID
			   }
			   
			  if(a==1) {
					indexPage="/SendMail/Forgotpwd.jsp?check=SaveSuccess";			
				}else{
					indexPage="/SendMail/Forgotpwd.jsp?check=SaveFail";					
				}			  
			}
			
			// SENDING SMS
			
			SmsService ss1=BusinessServiceFactory.INSTANCE.getSmsService();
			
			if(flag.equals("SendSMS")) {	
				
				log4jLogger.info("===== Inside Sending SMS Method ====");	
				String mobi="9629019378";	
				String txtmsg="This is testing from passing parameters";
				
				boolean chk=Security_Counter.SMSValidator(mobi);	     
				if(chk==true) {
				  a=ss1.getSendSMS(mobi,txtmsg);
				}else{
					   a=0;  // Invalid Phone Number
				   }
				
				if(a==1) {
						indexPage="/SendMail/mail.jsp?check=SaveSMSSuccess";			
				}else{
						indexPage="/SendMail/mail.jsp?check=SaveSMSFail";					
				}			  
			}
			
			
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		dispatch(request,response,indexPage);
		
				
	}
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
	
	
}