package Common.businessutil.sms;


public interface SmsDao
{
	public int findSendSMS(String mobino,String messgae);
	public int findDueReminderSMS(String code, int branch, String type);	
	public int findAdvanceReminderSMS(int branch);		
	public int findReserveReminderSMS(int branch);
	
}