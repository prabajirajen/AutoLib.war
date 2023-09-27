package Common.businessutil.sms;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Common.businessutil.mail.MailQueryUtill;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class SmsDaoImpl extends BaseDBConnection implements SmsDao 
{
	
	private static Logger log4jLogger = Logger.getLogger(SmsDaoImpl.class);	
	static Log log = LogFactory.getLog(SmsDaoImpl.class);
	
	
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.PreparedStatement Prest1 = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	java.sql.Statement st = null;
    
	
	int a=0;	
	
	private String username="";
	private String password="";
	private String proxyName="",proxyStatus;
	private int proxyPort;
	private String from="CITLIB";	// Having Two senderid so that this line was hard coded
	
	Properties properties=new Properties();
	
       
 public int findSendSMS(String mobno,String message) {
	 
	 log4jLogger.info("================= start: [findSendSMS] ====================== " );
	         
	 	 try {   		
	 		 
	 		properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
	 		
	 		username = properties.getProperty("autolib.sms.username");	
	 		password = properties.getProperty("autolib.sms.password");
	 		
	 		proxyStatus = properties.getProperty("autolib.proxy.status");
	 		proxyName = properties.getProperty("autolib.proxy.host");
	 		proxyPort = Integer.parseInt(properties.getProperty("autolib.proxy.port"));
	 		 
	 		int TIMEOUT_VALUE = 15000;   // For 15 Seconds
	 		
	 		String url_str="http://49.50.69.90/api/smsapi.aspx?username="+ username +"&password="+ password +"&to="+ mobno +"&from="+ from +"&message="+URLEncoder.encode(message, "UTF-8");

	 		log4jLogger.info("%%%%%%%%%%%%%%%%%%"+url_str);
	 		URL u = new URL(url_str);
	 		
	 		HttpURLConnection connection;
	 		
	 		if(proxyStatus.equalsIgnoreCase("YES"))  // For Proxy Settings
	 		{
	 			 Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyName, proxyPort));  // For Proxy Settings
	 			 connection = (HttpURLConnection) u.openConnection(proxy); 
	 		}
	 		else  {
	 			 connection = (HttpURLConnection) u.openConnection();
	 		}		 		

	 		connection.setDoOutput(true);
	 		connection.setDoInput(true);
	 		
	 		connection.setConnectTimeout(TIMEOUT_VALUE);
	 		connection.setReadTimeout(TIMEOUT_VALUE);

	 		//log4jLogger.info("connection :::::::::::::::::"+connection);
	 		
	 		String res=connection.getResponseMessage();
	 		
	 		//log4jLogger.info("responce:::::::::::::::::: :"+res);

	 		int code = connection.getResponseCode () ;
	 		
	 		//log4jLogger.info("responcecode:::::::::::::::::: :"+code);
	 		
	 		if ( code == HttpURLConnection.HTTP_OK)
	 		{	
	 		  connection.disconnect() ;
	 		  log4jLogger.info("=============== Send Successfully ====="+message.length()+" To:"+mobno);	 		
	 		} 	 		
	        
	 		/**
	            // 2.Done code for SMS (Method:2)
	 		 
	 		String temp = "";  
	        //String content = "mobileno="+mobno+"&smsmsg="+message+"&uname="+username+"&pwd="+password+"&pid="+pid;  
	        //URL u = new URL("http://boancomm.net/boansms/boansmsinterface.aspx?");  
	 		
	 		String content = "username="+username+"&password="+password+"&to="+mobno+"&from="+from+"&message="+URLEncoder.encode(message, "UTF-8");
	        URL u = new URL("http://49.50.69.90/api/smsapi.aspx?"+content); 
	        
	        HttpURLConnection uc = (HttpURLConnection) u.openConnection();  
	        uc.setDoOutput(true);
	        uc.setDoInput(true);
	        
	        uc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.0.5) Gecko/2008120122 Firefox/3.0.5");  
	        uc.setRequestProperty("Content-Length", String.valueOf(content.length()));  
	        //uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");  */
	        //uc.setRequestProperty("Accept", "*/*");  
	        /**
	        uc.setRequestProperty("Accept-Charset", "UTF-8");
	        uc.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
	        uc.setRequestMethod("POST");  
	        uc.setInstanceFollowRedirects(false); // very important line :)  
	        
	        PrintWriter pw = new PrintWriter(new OutputStreamWriter(uc.getOutputStream()), true);  
	        pw.print(content);  
	        pw.flush();      // Send SMS 
	        pw.close();  
	        BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));  
	        while ( (temp = br.readLine()) != null ) {}  
	        String cookie = uc.getHeaderField("Set-Cookie");  
	  
	        log4jLogger.info("=============== Content Length ========"+content.length());
	        
	        */
	        
	        // Send SMS to each of the phone numbers  
	        u = null; 
	        
	        //uc = null;
	        
	        connection=null;  
	        
	        
	        a=1;
	 	     	  
	 	 }catch(Exception e) {
	 		 a=0;
	 		 e.printStackTrace();
	 		 log4jLogger.info("Error Code: "+e);
	 	 }
	        
              
		return a;
	}
 
 
 public int findDueReminderSMS(String code, int branch,String type)
 {
	 log4jLogger.info("================= start: [findDueReminderSMS] ====================== "+type );
	 
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
			
			if(type.equalsIgnoreCase("STAFF")){
				Prest = con.prepareStatement(DataQuery.Sms_DueReminder.toString() + sql + strsql + " and due_date<=DATE_ADD(CURDATE(),INTERVAL 180 DAY) and dsname not in('UG Student','PG Student') order by member_name asc");	
			}else{
				Prest = con.prepareStatement(DataQuery.Sms_DueReminder.toString() + sql + strsql + " and due_date<=DATE_ADD(CURDATE(),INTERVAL 30 DAY) and dsname in('UG Student','PG Student') order by member_name asc");
			}
			
			
			
			rs = Prest.executeQuery();
			while (rs.next()) {		
				
				boolean chk=Security_Counter.SMSValidator(rs.getString("member_phone").toString());				
				   if(chk==true) {
					   
				
					   
					   if(type.equalsIgnoreCase("STAFF")){
						   
						   Prest1 = con.prepareStatement(DataQuery.Email_DueReminder_Details.toString()+" and due_date<=DATE_ADD(CURDATE(),INTERVAL 180 DAY) and dsname not in('UG Student','PG Student') order by member_name asc ");
						   Prest1.setString(1,rs.getString("member_code").toString());
						   rs1 = Prest1.executeQuery();
						   
						   String namedQuery=MailQueryUtill.Duereminder_SMS_MessageText;
						   
						   StringBuffer sb = new StringBuffer();
						   sb.append("Dear Sir/Madam, ");
						   sb.append(namedQuery);
			    		   
						   String msg="";
						   
						   while (rs1.next()) {	
							   
							   msg = msg + Security.TextDate_member(rs1.getString("due_date")) + ":" + rs1.getString("access_no") + ", " ;						  
						   }
						   sb.append(msg);
						   sb.append("Regards, Librarian, CIT");
						   					   
						   res=findSendSMS(rs.getString("member_phone").toString(),sb.toString());
						   
						   count = count + res;  
					   }
					   
					   else if(type.equalsIgnoreCase("STUDENT"))
					   
					   {
						   
						   Prest1 = con.prepareStatement(DataQuery.Email_DueReminder_Details.toString()+"and due_date<=DATE_ADD(CURDATE(),INTERVAL 30 DAY) and dsname in('UG Student','PG Student') order by member_name asc");
						   Prest1.setString(1,rs.getString("member_code").toString());
						   rs1 = Prest1.executeQuery();
						   
						   String namedQuery=MailQueryUtill.Duereminder_SMS_MessageText;
						   
						   StringBuffer sb = new StringBuffer();
						   sb.append("Dear "+rs.getString("member_name")+", ");
						   sb.append(namedQuery);
			    		   
						   String msg="";
						   
						   while (rs1.next()) {	
							   
							   msg = msg + Security.TextDate_member(rs1.getString("due_date")) + ":" + rs1.getString("access_no") + ", " ;						  
						   }
						   sb.append(msg);
						   sb.append("Regards, Librarian, CIT");
						   					   
						   res=findSendSMS(rs.getString("member_phone").toString(),sb.toString());
						   
						   count = count + res;
					   }else{
						   
						   Prest1 = con.prepareStatement(DataQuery.Email_DueReminder_Details.toString());
						   Prest1.setString(1,rs.getString("member_code").toString());
						   rs1 = Prest1.executeQuery();
						   
						   String namedQuery=MailQueryUtill.Duereminder_SMS_MessageText;
						   
						   StringBuffer sb = new StringBuffer();
						   sb.append("Dear "+rs.getString("member_name")+", ");
						   sb.append(namedQuery);
			    		   
						   String msg="";
						   
						   while (rs1.next()) {	
							   
							   msg = msg + Security.TextDate_member(rs1.getString("due_date")) + ":" + rs1.getString("access_no") + ", " ;						  
						   }
						   sb.append(msg);
						   sb.append("Regards, Librarian, CIT");
						   					   
						   res=findSendSMS(rs.getString("member_phone").toString(),sb.toString());
						   
						   count = count + res;
						   
					   }
					   
					  
				   }else{
					  // count=0;  // Invalid Email ID
				   }
				
				
			}
			 log4jLogger.info("================= End: [findDueReminderSMS] ====================== " );
			 
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
 
 
 public int findAdvanceReminderSMS(int branch)
 {
	 log4jLogger.info("================= start: [findAdvanceReminderSMS] ====================== " );
	 
	 int count=0,res=0;
     String strsql=""; 	 
		
	 try {			
			if(branch > 0)
			{
			  strsql = "and Branch_Code="+branch;
			  
			  if(branch == 3)
			  {
				 from = "JEWLIB"; // For Jewwllery Division Provider
			  }
			}			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Sms_AdvanceDueReminder.toString() + strsql + " order by member_name asc");
			rs = Prest.executeQuery();
			
			while (rs.next()) {		
				
				boolean chk=Security_Counter.SMSValidator(rs.getString("member_phone").toString());				
				   if(chk==true) {					   
					   
					   Prest1 = con.prepareStatement(DataQuery.Sms_AdvanceDueReminderList.toString());
					   Prest1.setString(1,rs.getString("member_code").toString());
					   rs1 = Prest1.executeQuery();
					   
					   String namedQuery=MailQueryUtill.AdvanceDuereminder_SMS_MessageText;
					   
					   StringBuffer sb = new StringBuffer();
					   sb.append("Dear "+rs.getString("member_name")+", ");					   
					   sb.append(namedQuery);
		    		   
					   String msg="";
					   
					   while (rs1.next()) {					   				   
						   
						   msg = msg + Security.TextDate_member(rs1.getString("due_date")) + ":" + rs1.getString("access_no") + ", " ;						  
					   }
					   
					   sb.append(msg);
					   sb.append("Regards Librarian.");
					   
					   res=findSendSMS(rs.getString("member_phone").toString(),sb.toString());
					   
					   count = count + res;
				   }else{
					  // count=0;  // Invalid Email ID
				   }
				
				
			}
			
			log4jLogger.info("================= End: [findAdvanceReminderSMS] ====================== " );
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (rs1 != null) {
					rs1.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (Prest1 != null) {
					Prest1.close();
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
 
 
 
 public int findReserveReminderSMS(int branch)
 {
	 log4jLogger.info("================= start: [findReserveReminderSMS] ====================== " );
	 
	 int count=0,res=0;
     String strsql=""; 	 
		
	 try {			
			if(branch > 0)
			{
			  strsql = "and Branch_Code="+branch;
			  
			  if(branch == 3)
			  {
				 from = "JEWLIB"; // For Jewwllery Division Provider
			  }
			}			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Sms_ReserveReminderList.toString() + strsql + " AND smsflag IS NULL OR smsFlag=0 GROUP BY access_no,doc_type");
			rs = Prest.executeQuery();
			
			while (rs.next()) {	
				       res = 0;
					   
					   Prest1 = con.prepareStatement(DataQuery.Sms_ReserveReminderUpdate.toString());
					   Prest1.setString(1, Security.Counter_DateText());
					   Prest1.setString(2, rs.getString("member_code").toString());
					   Prest1.setString(3, rs.getString("Access_No").toString());
					   Prest1.setString(4, rs.getString("doc_type").toString());
					   res = Prest1.executeUpdate();	
					   
					   if( res > 0)
					   {
						   Prest = con.prepareStatement(DataQuery.DELETE_RESERVE_MAS_ALL.toString());		// DELETE ALL RESERVATION WHO MISSED THEIR TIME 		   					   
						   Prest.setString(1, Security.Counter_DateText());
						   Prest.executeUpdate();						   
						   
						   boolean chk=Security_Counter.SMSValidator(rs.getString("member_phone").toString());				
						   if(chk==true) {		   
							   
							   String namedQuery=MailQueryUtill.ReserveReminder_SMS_MessageText;
							   
							   StringBuffer sb = new StringBuffer();
							   sb.append("Dear "+rs.getString("member_name")+", ");	
							   sb.append(namedQuery);						 
							   
							   sb.append(Security.TextDate_member(rs.getString("res_date")) + ":" + rs.getString("Access_No") + ", ");
							   sb.append("Regards Librarian.");
							   
							   count=findSendSMS(rs.getString("member_phone").toString(),sb.toString());
							   							   
						   }						   						   
					   }				
			}
			
			log4jLogger.info("================= End: [findReserveReminderSMS] ====================== " );
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (Prest1 != null) {
					Prest1.close();
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

 
 
 

 }
 