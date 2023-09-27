package Common.businessutil.mail;

import java.util.List;

import Lib.Auto.Branch.BranchBean;

public interface MailDao
{
	public int findSendEmail(String[] toaddress,String subject,String Messgae,BranchBean bean);
	
	public int findForgetPassword(String email,BranchBean bean);
	
	public List findDueReminderList(int branch,BranchBean bean);
	public int findDueReminderMail(String code, int branch,String type,BranchBean bean);
	
	
}