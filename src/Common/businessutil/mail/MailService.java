package Common.businessutil.mail;

import java.util.List;

import Lib.Auto.Branch.BranchBean;

public interface MailService
{
	public int getSendEmail(String[] toAddress,String subject,String message,BranchBean bean);
	public int getForgetPassword(String email,BranchBean bean);	
	
	public List getDueReminderList(int branch,BranchBean bean);
	public int getDueReminderMail(String code, int branch,String type,BranchBean bean);
	
}