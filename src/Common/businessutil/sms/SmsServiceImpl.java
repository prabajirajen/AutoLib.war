package Common.businessutil.sms;

import java.util.List;

public class SmsServiceImpl implements SmsService
{
	private SmsDao smsDao;
	
	public  SmsServiceImpl(){
		
	}
	
	public int getSendSMS(String mobino,String message){
		return smsDao.findSendSMS(mobino,message);
	}
	
	public int getDueReminderSMS(String code, int branch, String type)
	{
		return smsDao.findDueReminderSMS(code,branch,type);
	}
	
	public int getAdvanceReminderSMS(int branch)
	{
		return smsDao.findAdvanceReminderSMS(branch);
	}
	
	public int getReserveReminderSMS(int branch)
	{
		return smsDao.findReserveReminderSMS(branch);
	}
	
	public SmsDao getSmsDao(){
		return smsDao;
	}
	
	public void setSmsDao(SmsDao smsDao){
		this.smsDao=smsDao;
	}
	
	
}