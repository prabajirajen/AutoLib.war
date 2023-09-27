package Common.businessutil.sms.schedule;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.mail.MailService;
import Common.businessutil.sms.SmsService;

public class SendDueReminderSMSJobforStaff implements Job
{
	private static Logger log4jLogger = Logger.getLogger(SendDueReminderSMSJobforStaff.class);
	SmsService ss=BusinessServiceFactory.INSTANCE.getSmsService();
	
	
	
	
	public void execute(JobExecutionContext context)
	throws JobExecutionException {		

		try 
		{			
			log4jLogger.info("================= start: [Automatic: Due Reminder for User] ====================== " );
			int count=0;
			ss.getDueReminderSMS("ALL",2,"STAFF");	
			log4jLogger.info("================= End: [Automatic: Due Reminder for User] ====================== EMail SENT TO:" +count);
		}catch(Exception e)  {
			e.printStackTrace();
		}
		
	}
	
}
