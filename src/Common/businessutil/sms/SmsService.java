package Common.businessutil.sms;

import java.util.List;

public interface SmsService
{
	public int getSendSMS(String mobino,String message);
	public int getDueReminderSMS(String code, int branch,String type);	
	public int getAdvanceReminderSMS(int branch);
	public int getReserveReminderSMS(int branch);
	
}