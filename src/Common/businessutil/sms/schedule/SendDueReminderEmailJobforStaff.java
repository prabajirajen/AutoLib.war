package Common.businessutil.sms.schedule;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.mail.MailService;
import Common.businessutil.sms.SmsService;

public class SendDueReminderEmailJobforStaff implements Job
{
	private static Logger log4jLogger = Logger.getLogger(SendDueReminderEmailJobforStaff.class);
	MailService ss=BusinessServiceFactory.INSTANCE.getMailService();
	
	
	
	
	public void execute(JobExecutionContext context)
	throws JobExecutionException {		

		try 
		{			
			log4jLogger.info("================= start: [Automatic: Due Reminder for User] ====================== " );
			int count=0;
			
		//	ss.getDueReminderMail("ALL",1,"ALL");	
			log4jLogger.info("================= End: [Automatic: Due Reminder for User] ====================== EMail SENT TO:" +count);
		}catch(Exception e)  {
			e.printStackTrace();
		}
		
	}
	
}
