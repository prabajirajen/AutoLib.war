package Common.businessutil.mail;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Email_Reminder.EmailBean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class MailDaoImpl extends BaseDBConnection implements MailDao 
{
	EmailBean bean = new EmailBean();
	public MailDaoImpl(BranchBean beanType){
		
		
		
	}
	
	private static Logger log4jLogger = Logger.getLogger(MailDaoImpl.class);
	
	int a=0;
	
	
	private static final String SMTP_HOST = "smtp.gmail.com";  
	//private static final String FROM_ADDRESS = "carelibrary.coe@gmail.com";
	//private static final String PASSWORD = "uvgovqfdbtpzhutw";  
	 
	// private static final String FROM_ADDRESS = "";
	// private static final String PASSWORD = "";  
	 
	 
	   private static final String FROM_NAME = "Librarian";

    java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;
	
       
    public int findSendEmail(String[] TO_ADDRESS,String subject,String message,BranchBean branch) {	 
	 
	 log4jLogger.info("=============== Inside MailDaoImpl ========");
	         
     try {  

         Properties props = new Properties();   
         
         
                 
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.host", "smtp.gmail.com");  
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.port", "587");
         
         Session session = Session.getInstance(props, new SocialAuth());  
         Message msg = new MimeMessage(session);  

   //      InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);  
         
         InternetAddress from = new InternetAddress(branch.getEmail(), FROM_NAME);
         
         msg.setFrom(from);  // From Address

         InternetAddress[] toAddresses = new InternetAddress[TO_ADDRESS.length];
         
         for (int i = 0; i < TO_ADDRESS.length; i++)
         { 
        	 toAddresses[i] = new InternetAddress(TO_ADDRESS[i]);
        	 log4jLogger.info("==== MAILID ==== "+toAddresses[i]);
         }
            
         
         log4jLogger.info("###########################################");
         
         msg.setRecipients(Message.RecipientType.TO, toAddresses);        // Set To: header field of the header.
         msg.setSubject(subject);
         msg.setContent(message, "text/html; charset=utf-8");   
         Transport transport=session.getTransport("smtp");
         
         
         transport.connect();
         log4jLogger.info("Transport connect successfully....");                          
         Transport.send(msg);
         transport.close();
         
         a=1;  
     } catch (UnsupportedEncodingException ex) {  
         a=0;
         ex.printStackTrace();

     } catch (MessagingException ex) {  
         a=0;  
         ex.printStackTrace();
     }  
              
		return a;
	} 
 

    class SocialAuth extends Authenticator {  
        
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
       	 
       	 findFromAddress();
       	 
       	 System.out.println(bean.getFromAddress()+"::::::::::getFromAddress");    //john.A
       	 System.out.println(bean.getPassword()+"::::::::::getPassword");
       	 System.out.println("Developer Notes: this is a version 22.01 so you have create a new table mail_util (app_password only it can be accepted)");  //john.A
            return new PasswordAuthentication(bean.getFromAddress(), bean.getPassword());  

        }  
    }
 
 

 
 public int findSendEmailNew(String toAddresses,String subject,String message,BranchBean branch) {
	 
	 
	 log4jLogger.info("=============== Inside findSendEmailNew ========");
	         
     try {  
    	 
         Properties props = new Properties();         
  
         props.put("mail.transport.protocol", "smtp");
         props.put("mail.smtp.host", "smtp.gmail.com");  
         props.put("mail.smtp.starttls.enable", "true");
         props.put("mail.smtp.auth", "true");
        
         props.put("mail.smtp.port", "587");

         Session session = Session.getInstance(props, new SocialAuth());  
         Message msg = new MimeMessage(session);  

        // InternetAddress from = new InternetAddress(FROM_ADDRESS, FROM_NAME);  
      
         InternetAddress from = new InternetAddress(branch.getEmail(), FROM_NAME);
         
         msg.setFrom(from);  // From Address
                   
         msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toAddresses));   

         msg.setSubject(subject);  // Set Subject: header field
               
         msg.setContent(message, "text/html; charset=utf-8");
         
         //Transport transport=session.getTransport("smtp");
         
         Transport transport=session.getTransport("smtp");
         
         
         transport.connect();
         
         
         log4jLogger.info("Transport connect successfully....");                          
         Transport.send(msg);
         transport.close();

         a=1;  
     } catch (UnsupportedEncodingException ex) {  
         a=0;
         ex.printStackTrace();

     } catch (MessagingException ex) {        
         a=0;  
         ex.printStackTrace();
     }  
              
		return a;
	} 
 
 
 
 
 
 public int findForgetPassword(String email,BranchBean bean)
 {
	 log4jLogger.info("==== Inside Forgot Password --> MailDaoImpl ====");
	 String mcode="",mpass="",emailid="",name="";
	 int count=0;
	 
	 try {
		 
	   con=getSession().connection();	 
	   Prest = con.prepareStatement(DataQuery.EMAILCheck_STRING);
	   Prest.setString(1, email);
	   rs=Prest.executeQuery();	   
	   
	if (rs.next()) {		
		
		   mcode =rs.getString("member_code");
		   name=rs.getString("member_name");
		   mpass =rs.getString("login_password");
		   emailid=rs.getString("member_email");	  		   
	   }
	else
	{
		count=0;
	}
	
	log4jLogger.info("MemberEmail value::::::::::::::"+emailid);
	   	   
	   if(!emailid.isEmpty() && !emailid.equals(""))
	   {	
		
		   
		   log4jLogger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		   
	     String[] strArray = new String[] {emailid};     
	     
	     String namedQuery=MailQueryUtill.Forgot_Message_Text;
		 StringBuffer sb = new StringBuffer();
		 
		    sb.append("Dear "+name+",<br><br>");    			
			sb.append(namedQuery);
			
			sb.append("<br>Login ID  :"+mcode+"<br>Password :"+mpass+"<br>");				
			sb.append("<br><br>"+MailQueryUtill.Regards_Text);
			
	    count=findSendEmail(strArray,MailQueryUtill.Forgot_Subject_Text,sb.toString(),bean);

	   }  
	
	 }catch(Exception e) {
		 e.printStackTrace();
	 }finally {
			try{
				if(con!=null){
					con.close();
					con=null;
				}			
				if(rs!=null){
					rs.close();
					rs=null;
				}
				if(Prest!=null){
					Prest.close();
					Prest=null;
				}
				}catch(Exception e){
					e.printStackTrace();
				}
	          }
	 
	 return count;
	 
 }
 
 
 public List findDueReminderList(int branch,BranchBean bean)  {
	
	 log4jLogger.info("Inside Due Reminder List --> E-Mail");
	 String strsql="";
	 
	 if(branch > 0)
	 {
		 strsql = "and Branch_Code="+branch;
	 } 
	 
	 SQLQuery sql = getSession().createSQLQuery(DataQuery.DueReminder_List.toString() + strsql + " ORDER BY due_date DESC");
		
	 return  sql.list(); 
 
 }
 
 public int findDueReminderMail(String code, int branch,String type,BranchBean branchBean)  {
		
	 log4jLogger.info("Inside Due Reminder MAIL --> E-Mail:"+code);
	 
	 int count=0,res=0;
	 String sql="";	 
     String strsql=""; 	 
		
	 try {			
			if(!code.equalsIgnoreCase("ALL"))
			{
			  sql="and member_code in(null" + code + ")";
			}			
			
			if(branch > 0)
			{
			  strsql = "and Branch_Code="+branch;
			}
			
			con = getSession().connection();
			
			log4jLogger.info("send email for ::::::::::"+type);
			if(type.equals("STUDENT")){
				
			Prest = con.prepareStatement(DataQuery.Email_DueReminder.toString() + sql + strsql + " and due_date<=DATE_ADD(CURDATE(),INTERVAL 7 DAY) and dsname in('UG Student','PG Student') ORDER BY due_date DESC");	
			}
			else if(type.equals("STAFF"))
			{
			Prest = con.prepareStatement(DataQuery.Email_DueReminder.toString() + sql + strsql + " and due_date<=DATE_ADD(CURDATE(),INTERVAL 30 DAY) and  dsname not in('UG Student','PG Student') ORDER BY due_date DESC");
			}else{
				//Prest = con.prepareStatement(DataQuery.Email_DueReminder.toString() + sql + strsql + "  and due_date<=DATE_ADD(CURDATE(),INTERVAL 7 DAY) ORDER BY due_date DESC");
				
				Prest = con.prepareStatement(DataQuery.Email_DueReminder.toString() + sql + strsql + " ORDER BY due_date DESC");
			}
			rs = Prest.executeQuery();
			while (rs.next()) {		
				boolean chk=Security_Counter.EmailValidator(rs.getString("member_email").toString());				
				   if(chk==true) {
					   log4jLogger.info("Type   :::::::   "+type);
					  
					   if(type.equalsIgnoreCase("STAFF")){
						   
						   Prest1 = con.prepareStatement(DataQuery.Email_DueReminder_Details.toString());
						   Prest1.setString(1,rs.getString("member_code").toString());
						   rs1 = Prest1.executeQuery();
						   
						   String namedQuery=MailQueryUtill.Duereminder_Message_TextStaff;
						   StringBuffer sb = new StringBuffer();
						   sb.append("Dear Sir/Madam,<br><br>");
						   sb.append(namedQuery);
			    			
			    			sb.append("<br><br>");
			    			sb.append("<table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");	    			    			
			    			sb.append("<tr bgcolor='#CCEEFF'><th><b><font color='#000000' size='1' face='Verdana'></b>Access No</th>");		    			
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Title</th>");
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Issue Date</th>");
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Due Date</th>");
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Document</th></tr>");
			    			
						   
						   while (rs1.next()) {				   
				    			
				    			sb.append("<tr bgcolor='#CCFFFF'><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("access_no") +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("title") +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ Security.TextDate_member(rs1.getString("issue_date")) +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ Security.TextDate_member(rs1.getString("due_date")) +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("doc_type") +"</td></tr>");	
				    			
						   }	
						   sb.append("</table><br><br>");
						   sb.append(MailQueryUtill.Regards_Text);
						   
						   res=findSendEmailNew(rs.getString("member_email").toString(),MailQueryUtill.Duereminder_Subject_Text,sb.toString(),branchBean);
						   
						   count = count + res;
					   }
					   else{
						   
						   
						   Prest1 = con.prepareStatement(DataQuery.Email_DueReminder_Details.toString());
						   Prest1.setString(1,rs.getString("member_code").toString());
						   rs1 = Prest1.executeQuery();
						   
						   String namedQuery=MailQueryUtill.Duereminder_Message_TextStudent;
						   StringBuffer sb = new StringBuffer();
						   sb.append("Dear "+rs.getString("member_name").toUpperCase()+",<br><br>");
						   sb.append(namedQuery);
			    			
			    			sb.append("<br><br>");
			    			sb.append("<table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");	    			    			
			    			sb.append("<tr bgcolor='#CCEEFF'><th><b><font color='#000000' size='1' face='Verdana'></b>Access No</th>");		    			
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Title</th>");
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Issue Date</th>");
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Due Date</th>");
			    			sb.append("<th><b><font color='#000000' size='1' face='Verdana'></b>Document</th></tr>");
			    			
						   
						   while (rs1.next()) {				   
				    			
				    			sb.append("<tr bgcolor='#CCFFFF'><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("access_no") +"</td><td align=left><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("title") +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ Security.TextDate_member(rs1.getString("issue_date")) +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ Security.TextDate_member(rs1.getString("due_date")) +"</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"+ rs1.getString("doc_type") +"</td></tr>");	
				    			
						   }	
						   sb.append("</table><br><br>");
						   sb.append(MailQueryUtill.Regards_Text);
						   
						   res=findSendEmailNew(rs.getString("member_email").toString(),MailQueryUtill.Duereminder_Subject_Text,sb.toString(),branchBean);
						   
						   count = count + res;
					   }
					   
					   
					   
					 
				   }
				   
			   
				   else{
					  // count=0;  // Invalid Email ID
				   }
				
				
			}
	 

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return count;
		
		
	
		 

 }
//::::::::::::::::findFromAddress;;;;;;;;;;;;;;;;;       
 
		 public EmailBean findFromAddress(){
			  
			  try {
		 		con = getSession().connection();
		 		st = con.createStatement();
		 		rs = Prest.executeQuery(MailQueryUtill.MAIL_UTIL);
		 		
		 		if (rs.next()) {
		 			
		 		  	bean.setFromAddress(rs.getString("email_id"));  //john
		 			bean.setPassword(rs.getString("password"));
		 				  		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		return bean;
		 
		 
		 
		}

}