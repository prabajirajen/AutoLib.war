package Common.businessutil.sms.schedule;

import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

import org.quartz.impl.StdSchedulerFactory;

public class ScheduleAction implements ServletContextListener
{
	private static Logger log4jLogger = Logger.getLogger(ScheduleAction.class);
	
	public void contextDestroyed(ServletContextEvent arg0)
    {
		log4jLogger.info("Stopping Scheduler Application successfully");
    }
    
    public void contextInitialized(ServletContextEvent arg0) 
    {
    	log4jLogger.info("Initializing Application successfully");
       
       try{    	   
    	  
    	Properties properties=new Properties();
		properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
			
		String tbackup = properties.getProperty("autolib.backup.timings");	
		String tchekdb = properties.getProperty("autolib.chekdb.timings");
		
		
//		String duemailstudent = properties.getProperty("autolib.duemailstudent.timings");
		String duemail = properties.getProperty("autolib.duemail.timings");
		/*
		String duesmsstaff = properties.getProperty("autolib.duesmsstaff.timings");
		String duesmsstudent = properties.getProperty("autolib.duesmsstudent.timings");
		*/
		
	
		JobDetail job = new JobDetail();
       	job.setName("backupDBName");
       	job.setJobClass(BackupJob.class);
       	
       	JobDetail checkDBJob = new JobDetail();
       	checkDBJob.setName("checkDBJobName");
       	checkDBJob.setJobClass(StableConnectionSchedule.class);

       	JobDetail sendduemailjob = new JobDetail();
       	sendduemailjob.setName("sendDueMailforStaffName");
       	sendduemailjob.setJobClass(SendDueReminderEmailJobforStaff.class);
      	
      	JobDetail sendduemailstudentjob = new JobDetail();
       	sendduemailstudentjob.setName("sendDueMailforStudentName");
       	sendduemailstudentjob.setJobClass(SendDueReminderEmailJobforStudent.class);
    	/*
       	JobDetail senddueSMSstaffjob = new JobDetail();
       	senddueSMSstaffjob.setName("sendDueSMSforStaffName");
       	senddueSMSstaffjob.setJobClass(SendDueReminderSMSJobforStaff.class);
      	
       	JobDetail senddueSMSstudentjob = new JobDetail();
       	senddueSMSstudentjob.setName("sendDueSMSforStudentName");
       	senddueSMSstudentjob.setJobClass(SendDueReminderSMSJobforStudent.class);
      	
      	*/
      	

     	CronTrigger trigger = new CronTrigger();
       	trigger.setName("backupDBName");
       	trigger.setCronExpression(tbackup);
       	
       	CronTrigger checkDBTrigger = new CronTrigger();
       	checkDBTrigger.setName("checkDBJobName");
       	checkDBTrigger.setCronExpression(tchekdb);
       	

      	CronTrigger dueSendMailTrigger = new CronTrigger();
      	dueSendMailTrigger.setName("sendDueMailforStaffName");
      	dueSendMailTrigger.setCronExpression(duemail);
      	
       	CronTrigger dueSendMailstudentTrigger = new CronTrigger();
       	dueSendMailstudentTrigger.setName("sendDueMailforStudentName");
//       	dueSendMailstudentTrigger.setCronExpression(duemailstudent);
    	

       	/*
      	CronTrigger dueSendSMSstaffTrigger = new CronTrigger();
      	dueSendSMSstaffTrigger.setName("sendDueSMSforStaffName");
      	dueSendSMSstaffTrigger.setCronExpression(duesmsstaff);
      	
    	
      	CronTrigger dueSendSMSstudentTrigger = new CronTrigger();
      	dueSendSMSstudentTrigger.setName("sendDueSMSforStudentName");
      	dueSendSMSstudentTrigger.setCronExpression(duesmsstudent);
       	*/
       	//schedule it
       	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
       	scheduler.start();
       	
       	scheduler.scheduleJob(job, trigger);       	
       	scheduler.scheduleJob(checkDBJob, checkDBTrigger);
       	
        scheduler.scheduleJob(sendduemailjob, dueSendMailTrigger);
        /*	scheduler.scheduleJob(sendduemailstudentjob, dueSendMailstudentTrigger);
    	
    	scheduler.scheduleJob(senddueSMSstaffjob, dueSendSMSstaffTrigger);
    	scheduler.scheduleJob(senddueSMSstudentjob, dueSendSMSstudentTrigger);
       	*/
       	
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
     }   
    
   
}

