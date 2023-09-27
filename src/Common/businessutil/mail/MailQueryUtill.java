package Common.businessutil.mail;

public interface MailQueryUtill{
		
	
	// For Changing Mail Text , MailID, Password
	
	public static final String SMTP_HOST = "smtp.gmail.com";
	public static final String Company_Text = "";	
	public static final String FROM_ADDRESS = ""; 
	public static final String PASSWORD = "";	
	public static final String FROM_NAME = "KARPAGA";	
	
	public static final String MAIL_UTIL = "select email_id,password,branch_code from branch_mas where 2>1 limit 1 ";
	
	public static final String Regards_Text = "Thanks and with regards, <br>The Librarian, <br>KARPAGA VINAYAGA.";
	
	public static final String Issue_Subject_Text = "Issue - "+Company_Text;
	public static final String Issue_Message_Text = "The following book(s) has been issued to you, ";
			
	public static final String Return_Subject_Text = "Return - "+Company_Text;
	public static final String Return_Message_Text = "The following book(s) has been returned, ";
	
	public static final String Renew_Subject_Text = "Renewal - "+Company_Text;
	public static final String Renew_Message_Text = "The following book(s) has been renewed, ";
	
	public static final String Reserve_Subject_Text = "Reservation - "+Company_Text;
	public static final String Reserve_Message_Text = "The following book(s) has been reserved ";
	
	public static final String Forgot_Subject_Text = "Reset Password - "+Company_Text;
	public static final String Forgot_Message_Text = "Kindly reset your password.";
	
	public static final String Duereminder_Subject_Text = "Library - Due Reminder";
	public static final String Duereminder_Message_TextStudent = "Please return/renew the following document(s) which are borrowed from our Library. Fine will be levied after the due date as per our library rules. Kindly ignore this reminder, if you have already returned/renewed the document(s).";
	
	public static final String Duereminder_Message_TextStaff = "Please return/renew the following document(s) which are borrowed from our Library. Kindly ignore this reminder, if you have already returned/renewed the document(s).";
	
	//	public static final String Duereminder_SMS_MessageText = "PL Return the following book(s) which are due on (Dt:AcNo) ";
	public static final String Duereminder_SMS_MessageText = "Please return/renew the following book(s) which are due on (Dt:AcnNo) ";
	
	public static final String AdvanceDuereminder_SMS_MessageText = "Reminder-The following book(s) are due on (Dt:AcNo) ";
	
	public static final String ReserveReminder_SMS_MessageText = "Reminder-Reserved book is now available in library (Dt:AcNo) ";
	
}