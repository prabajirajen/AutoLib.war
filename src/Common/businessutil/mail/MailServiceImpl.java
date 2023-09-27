package Common.businessutil.mail;

import java.util.ArrayList;
import java.util.List;

import Common.Security;
import Common.Security_Counter;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Email_Reminder.ReminderBean;


public class MailServiceImpl implements MailService
{
	private MailDao mailDao;
	
	public  MailServiceImpl(){
		
	}
	
	
	
	public int getSendEmail(String[] toaddress,String subject,String message,BranchBean bean){
		return mailDao.findSendEmail(toaddress,subject,message,bean);
	}
	
	public int getForgetPassword(String Email,BranchBean bean) {
		return mailDao.findForgetPassword(Email,bean);
	}
	
	public List getDueReminderList(int branch,BranchBean bean)
	{
		List results=mailDao.findDueReminderList(branch,bean);
		
		List finalResults = null;		 
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{				
				Object[] obj = (Object[])results.get(i);
				ReminderBean prt= new ReminderBean();								
				prt.setMcode(obj[0].toString());
				prt.setMname(obj[1].toString());
				prt.setMemail(obj[2].toString());
				prt.setMphone(obj[3].toString());
				prt.setDueDate(Security.getdate(obj[4].toString()));
				
				finalResults.add(prt);
			}
		}
		return finalResults;
	
	}
	
	public int getDueReminderMail(String code, int branch,String type,BranchBean bean)
	{
			return mailDao.findDueReminderMail(code, branch,type,bean);
	
	}
	
	
	
	
	
	
	
	
	
	
	public MailDao getMailDao(){
		return mailDao;
	}
	
	public void setMailDao(MailDao mailDao){
		this.mailDao=mailDao;
	}
	
	
}