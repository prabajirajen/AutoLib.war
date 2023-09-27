package Common.businessutil.reports;


public interface ReportQueryUtill
{
//	 Company address for Report (Report Parameters)
	
	
	/*public static final String COMPANY_NAME1="Central Library ( BIHER), Chennai";
	public static final String COMPANY_ADD1="Agharam Road,Selaiyur,Chennai, Tamil Nadu,India - 600073.";
	 
	
	public static final String COMPANY_NAME2="Bharath Institute of Science and Technology (BIST)";
	public static final String COMPANY_ADD2="Agharam Road,Selaiyur,Chennai, Tamil Nadu,India - 600073.";
	
	
	public static final String COMPANY_NAME3="Sri Lakshmi Narayana Institute of Medical Sciences (SLIMS)";
	public static final String COMPANY_ADD3="Agharam Road,Selaiyur,Chennai, Tamil Nadu,India - 600073.";
	
	
	public static final String COMPANY_NAME4="Sree Balaji Dental College & Hospital (SBDCH)";
	public static final String COMPANY_ADD4="Agharam Road,Selaiyur,Chennai, Tamil Nadu,India - 600073.";
	
	
	
	public static final String COMPANY_NAME5="Sree Balaji Medical College and Hospital (SBMCH)";
	public static final String COMPANY_ADD5="Agharam Road,Selaiyur,Chennai, Tamil Nadu,India - 600073.";*/
	

	// Care Insititue
	
	
	/*public static final String COMPANY_NAME1="CARE College of Engineering";
	public static final String COMPANY_ADD1="Kuttapatti, Srirangam, Tiruchirappalli, Tamil Nadu 620009";
	 
	
	public static final String COMPANY_NAME2="CARE College of Arts And Science";
	public static final String COMPANY_ADD2="Kuttapatti, Srirangam, Tiruchirappalli, Tamil Nadu 620009";
	
	
	public static final String COMPANY_NAME3="CARE School of Architecture";
	public static final String COMPANY_ADD3="Kuttapatti, Srirangam, Tiruchirappalli, Tamil Nadu 620009";
	
	
	public static final String COMPANY_NAME4="";
	public static final String COMPANY_ADD4="";
	
	
	public static final String COMPANY_NAME5="";
	public static final String COMPANY_ADD5="";*/
	
	
	
	
	
	// Maher Insititue
	
	
	/*public static final String COMPANY_NAME1="MEENAKSHI ACADEMY OF HIGHER EDUCATION AND RESEARCH";
	public static final String COMPANY_ADD1="West K.K. Nagar, Chennai, Tamil Nadu 600078";
	 
	
	public static final String COMPANY_NAME2="MEENAKSHI MEDICAL COLLEGE HOSPITAL & RESEARCH INSTITUTE";
	public static final String COMPANY_ADD2="Enathur, Karrapettai Post, Kanchipuram, Tamil Nadu 631552";
	
	
	public static final String COMPANY_NAME3="MEENAKSHI COLLEGE OF NURSING";
	public static final String COMPANY_ADD3="Alapakkam Main Road, Maduravoyal, Chennai, Tamil Nadu 600095";
	
	
	public static final String COMPANY_NAME4="MEENAKSHI AMMAL DENTAL COLLEGE AND HOSPITAL";
	public static final String COMPANY_ADD4="Alapakkam Main Road, Maduravoyal, Chennai, Tamil Nadu 600095";
	
	
	
	public static final String COMPANY_NAME5="ARULMIGU MEENAKSHI COLLEGE OF NURSING";
	public static final String COMPANY_ADD5="Enathur, Kanchipuram.Tamilnadu.";
	*/
	
	// kARPAGA VINAYAGA
	
	
	public static final String COMPANY_NAME1="Karpaga Vinayaga Institute Of Medical Sciences And Research Center";
	public static final String COMPANY_ADD1="Palayanoor, Maduranthakam, Tamil Nadu 603308";
	 
	
	public static final String COMPANY_NAME2="Karpaga Vinayaga Institute of Dental Sciences";
	public static final String COMPANY_ADD2="Padalam, Tamil Nadu 603308";
	
	
	public static final String COMPANY_NAME3="Karpaga Vinayaga College Of Nursing";
	public static final String COMPANY_ADD3="Kolambakkam, Tamil Nadu 603308";
	
	
	public static final String COMPANY_NAME4="";
	public static final String COMPANY_ADD4="";
	
	public static final String COMPANY_NAME5="";
	public static final String COMPANY_ADD5="";
	
	
	
	
// Accession Register Report
	
	public static final String Accession_Title="Accession-Report";
	
	public static final String Missing_Number_Title="Missing Number";
	
	public static final String Barcode_Title="Barcode";
	
	public static final String Binding_Title="Binding-Report";
	
	public static final String Tag_Title="RFIDTagReport";
	
	public static final String Card_Title="RFIDCardReport";
		
	public static final String AccessionQuery_Acc_no="SELECT access_no,DATE_FORMAT(received_date, '%d/%m/%Y') AS received_date,call_no,title,author_name,edition,publisher,year_pub,pages,supplier,invoice_no,DATE_FORMAT(invoice_date,'%d/%m/%Y') AS invoice_date,isbn,bprice,corp_author_name,series_author_name,volno,illustrator,other_title,add_field3,script,DEPT_NAME,availability,location,accepted_price FROM accession_reg WHERE 2>1 ";
	
	public static final String Barcode_Acc_no="SELECT CALL_NO,LEFT(AUTHOR_NAME,3) AS AUTHOR_NAME,ACCESS_NO,TITLE FROM ACCESSION_REG where 2>1 ";
	
	public static final String Miss_Accessno="select access_no from miss_no";
// Library Collection Report
	
	public static final String Library_Collection_Title="Library-Collection";
	public static final String Library_Collection_Query="SELECT document,volumes,title FROM library_collection_final ";	
	
// Counter Reports
	
	public static final String Counter_Reports_IssueTitle="Book-Issue-Report";
	public static final String Issue_Report_Chart="Issue-Report-Chart";
	public static final String Counter_Reports_Breakup_IssueTitle="Book-Issue-Report-Breakup";
	public static final String Counter_Reports_ReturnTitle="Book-Return-Report";
	public static final String Return_Report_Chart = "Return-Report-Chart";
	public static final String Counter_Reports_Breakup_ReturnTitle="Book-Return-Breakup-Report";
	public static final String Counter_Reports_RenewTitle="Book-Renew-Report";
	public static final String Counter_Reports_Breakup_RenewTitle="Book-Renew-Breakup-Report";
	public static final String Counter_Reports_ReserveTitle="Reserve-Report";
	public static final String Counter_Reports_Breakup_ReserveTitle="Reserve-Breakup-Report";
	public static final String Counter_Reports_ReserveReminderTitle="Reservation-Reminder";
	public static final String Counter_Reports_DueReminderTitle="Due-Reminder";
	public static final String Counter_Reports_Breakup_DueReminderTitle="Due-Reminder-Breakup";
	public static final String Counter_Reports_FineTitle="Library-Overdue";
	public static final String Counter_Breakup_Reports_FineTitle="Library-Breakup-Overdue";
	
	public static final String Query_CurrentIssue_Report="SELECT Access_no,title,member_code,issue_date,due_date,doc_type,staff_code FROM issuedbooks where 2>1";
	public static final String Query_Issue_Chart="SELECT YEAR(issue_date) AS YEAR,LEFT(MONTHNAME(issue_date),3) AS MONTH,COUNT(access_no) AS COUNT FROM issue_transaction WHERE 2>1 AND issue_type='ISSUE' ";
	public static final String Query_Issue_Report="SELECT Access_no,title,member_code,issue_date,due_date,return_date,doc_type,staff_code,member_name,dept_name FROM issue_transaction where 2>1 ";
	public static final String Query_Return_Report="SELECT Access_no,title,member_code,issue_date,due_date,return_date,doc_type,member_name,dept_name,staff_code FROM jissue_history where 2>1";
	public static final String Query_Return_Chart="SELECT YEAR(return_date) AS YEAR,LEFT(MONTHNAME(return_date),3) AS MONTH,COUNT(access_no) AS COUNT FROM jissue_history WHERE 2>1";
	public static final String Query_Renew_Report="SELECT Access_no,title,member_code,issue_date,due_date,doc_type,member_name,dept_name,staff_code FROM renewbooks where issue_type='RENEW'";
	public static final String Query_Reserve_Report="SELECT Access_no,title,member_code,res_date,doc_type,member_name,dept_name FROM member_reserve_view where 2>1";
	public static final String Query_ReserveReminder_Report="SELECT Access_no,title,member_code,member_name,res_date,doc_type,dept_name FROM Reserve_Reminder_View where 2>1";
	public static final String Query_DueReminder_Report="SELECT Access_no,title,member_code,member_name,due_date,doc_type,dept_name FROM Due_Reminder where 2>1";
	public static final String Breakup_Issue_Report="SELECT issue_date,COUNT(*) AS issuecount FROM issue_transaction where 2>1";
	public static final String Breakup_Return_Report="SELECT return_date,COUNT(*) AS returncount FROM  jissue_history where 2>1";
	public static final String Breakup_Renewal_Report="SELECT issue_date AS renewal_date,COUNT(*) AS renewalcount FROM renewbooks WHERE issue_type='RENEW' ";
	public static final String Breakup_Reserve_Report="SELECT res_date AS reserve_date,COUNT(*) AS reservecount FROM member_reserve_view where 2>1";
	public static final String Breakup_DueReminder_Report="SELECT due_date AS due_date,COUNT(*) AS remindercount FROM Due_Reminder where 2>1";
	
	
	public static final String Query_FineCumulative_Report="SELECT Trans_No,Access_No,Issue_Date,Due_Date,Trans_Date,Trans_Amount,member_code,member_name,dept_name FROM trans_member_view where Trans_Head='OVERDUE'";
	public static final String Query_FineListing_Report="SELECT Member_Code,Member_Name,Trans_No,Trans_Date,Access_No,Due_Date,Trans_Amount,dept_name FROM trans_member_view  where Trans_Head='OVERDUE'";
	
	public static final String Query_BranchFineListing_Report="SELECT Member_Code,Member_Name,Trans_No,Trans_Date,Access_No,Due_Date,Trans_Amount,dept_name FROM trans_book_view  where Trans_Head='OVERDUE'";
	
	
	public static final String Query_FineBreakup_Report="SELECT SUM(trans_amount) AS Trans_Amount,member_code,member_name,dept_name FROM trans_member_view  where Trans_Head='OVERDUE'";
	
	public static final String Query_FineBreakup_Report_Excel="SELECT trans_date,COUNT(DISTINCT member_code) AS totstudent,SUM(trans_amount) AS totcount FROM trans_member_view where Trans_Head='OVERDUE'";
//	 Member Reports	
	public static final String Counter_MemberReports_Title="Member-Details";
	public static final String Counter_MemberReports_Inactive="Inactive-Member-Details";
	
	public static final String Counter_MemberReports_Active_Statistics_title="Active-Statistics-Details";
	public static final String Counter_MemberReports_InActive_Statistics_title="Inactive-Statistics-Details";
	
	
	
	
	public static final String Query_Member_Report="SELECT * from member_report_view where 2>1";
	
	public static final String Query_frequentUser="SELECT Member_Code,Member_Name,Desig_Name,Cyear,Branch_Name FROM Return_Log_View WHERE 2>1 ";
	
	
	

//	 No Dues Certificate		
	public static final String NoDues_Title="NO DUES CERTIFICATE";
	public static final String NoDuesReport_Title="NO-DUES";
	
//	 Gate Regiter Report
	public static final String GateReg_Title="Gate-Register";
	public static final String GateRegQuery_Date="SELECT * FROM return_log_view where 2>1 ";
	public static final String GateRegQuery_Statistics = "SELECT return_time AS return_date,COUNT(return_time) AS totalCount FROM return_log_view where 2>1 ";
	
// Statistics Report
	
	public static final String Statistics_Title="Statistics-Report";
	
//	 Frequent Accessed Resource  Report
	
	public static final String Frequently_Resource_Title="Library Frequently Used Resource";
	public static final String Frequently_Pdf_Title="Resource-Frequent";
	
//	 Budget  Report
		
		public static final String Budget_Title="Budget-Details";
		
		public static final String Budget_Date_wise="SELECT * FROM Budget_full_view where 2>1 ";

//	 Payment  Report
		
		public static final String Paymentinfo_Title="Payment-Details";
		
		public static final String Paymentinfo_query="SELECT * FROM payment_clear_view where 2>1 ";
		
// Fine OverALL Report		
		
		public static final String FineOverAll_Title="Fine-Details";
		
		public static final String FineOverAll_query="SELECT * FROM fine_allview where 2>1 ";
			
//	 Journal List  Report
		
		public static final String JNL_List_Title="Journal-Details";
		
		public static final String JNL_Query="SELECT * FROM f_journal_master where 2>1";
			
//	 Journal Issues  Report
		
		public static final String JNL_Issues_Title="Journal-Issues";
		
		public static final String JNL_Issues_Query="select * from f_journal_issues where 2>1 ";

//	 Bibliography Report
		
		public static final String Biblio_Title="Bibliography-Report";
		public static final String BiblioCallno_Title="Bibliography-Call_No";
		public static final String BiblioAccession_Title="Bibliography-Accession";
		public static final String BiblioRevd_Title="Bibliography-PurchaseDate";
		public static final String BiblioBudged_Title="Bibliography-Budget";
		public static final String BiblioAuthor_Title="Bibliography-AuthorName";
		public static final String BiblioDept_Title="Bibliography-Department";
		public static final String BiblioPub_Title="Bibliography-Publisher";
		public static final String BiblioSup_Title="Bibliography-Supplier";
		public static final String Bibliosubject_Title="Bibliography-Subject";
		
		public static final String Biblio_Query="SELECT access_no,author_name,title,edition,call_no,publisher,year_pub,dept_name,sub_name,supplier,budget,NetPrice,availability FROM bibliography where 1<2";			
		
//	 Unique Title  Report
		
		public static final String Unique_Title="Unique-Title";		
		//public static final String UniqueTitle_Query="select distinct title,author,count(*) as bcopy from title_temp group by title,author order by Title";
		
		public static final String UniqueTitle_Query = "select distinct title,author_name,count(*) as bcopy from accession_reg where 2>1 "; // shek
		
		
//		 Journal Enquiry Reports	
		public static final String JNL_Enquiry_Title="Enquiry-Details";	
	    public static final String JNL_Enquiry_Report="SELECT enq_no,enq_date,jnl_name,sp_name,subs_from,subs_to,vol_no,no_of_issues,address1,city,pincode FROM jnl_enquiry_fullview WHERE 2>1";
		
		
//	 Journal Order Reports	
		public static final String JNL_Order_Title="Order-Details";		
		public static final String JNL_Order_Report="SELECT jnl_order_no,jnl_order_date,enq_no,enq_date,jnl_name,sp_name,subs_from,subs_to,vol_no,no_of_issues,accepted_price,address1,city,pincode FROM jnl_order_fullview where 2>1";


//	 Journal Invoice Reports	
		public static final String JNL_Invoice_Title="Invoice-Details";		
		public static final String JNL_Invoice_Report="SELECT invoice_no,order_ref_no,sp_name,invoice_date,invoice_amount,payment_flag FROM jnl_invoice_fullview where 2>1";

		
//	 Journal Payment Reports	
		public static final String JNL_Payment_Title="Payment-Details";		
		public static final String JNL_Payment_Report="SELECT Payment_Ref_No,Payment_Sent_Date,Cheque_No,Cheque_Date,Tot_Amount,Trans_Details,Invoice_No,Invoice_Amount,Invoice_Date,sp_name,address1,city,pincode from jnl_payment_fullview where 2>1";
		
		
//	 Indent Order Reports	
		public static final String Indent_Order_Title="Order-Details";		
		public static final String Indent_Order_Report="SELECT Order_No,Order_Date,Author_Name,Title,Publisher,Copies,Edition,ISBN,YearPub,supplier,address1,city,pincode FROM indent_order_fullview where 2>1";

//	 Indent Payment Reports	
		public static final String Indent_Payment_Title="Payment-Details";		
		public static final String Indent_Payment_Report="SELECT Payment_Ref_No,Payment_Sent_Date,Cheque_No,Cheque_Date,Tot_Amount,Trans_Details,Invoice_No,Inv_Tot_Amount,Invoice_Date,supplier,address1,city,pincode FROM indent_payment_fullview where 2>1";

//	 Indent Details Reports	
		public static final String Indent_Detail_Title="Indent-Details";		
		public static final String Indent_Detail_Report="SELECT Indent_No,Indent_Date,Member_Name,Author_Name,Title,Pub_Name,YearPub,Edition,ISBN,No_Of_Copy FROM indent_details_mas where 2>1";

		
		//Transfered Report
		
		public static final String Transfer_Report_Query="Select * from Transfer_View where 2>1 ";
		public static final String Transfer_Report_Statistics ="SELECT transfered_branch_name AS Division, COUNT(access_no) AS Transfered,COUNT(CASE WHEN recovery='YES' THEN 1 END ) AS Retransfered  FROM transfer_view";
		public static final String Transfer_Reports_Title = "Transfer Report";
		public static final String Re_Transfer_Reports_Title = "Re-Transfer Report";
		
		public static final String DistinctBudgetReportQuery="SELECT DISTINCT Bud_Code,Bud_Head,Dept_Code,Dept_Name FROM Budget_Full_View Where 2>1 ";
		
		//Binding_Report
		public static final String Binding_Acc_no="SELECT Access_No,Title,Binder_Name,Sending_Date,Doc_type FROM binding_view where 2>1 ";
		
		//RFID Tag & Card Report
		
		public static final String RFIDTag =" Select * from rfid_tag_view where 2>1 ";
		
		public static final String RFIDCard =" Select * from rfid_card_view where 2>1 ";
		
		
		//QB Report
		
		// Question Bank Report

		public static final String QB_Report_Title = "QB_Report";
		public static final String QB_Report_Query = "Select qb_code,sub_code,sub_name,dept_name,concat(course_name,'-',course_major) As course_name,year,semester,month from question_bank_fullview where 2>1 ";
		
		
		// DataBase (Custom) Reports
		public static final String Database_Reports_RPTTitle = "DataBase-Report";
		
		
		// Suggestion Report
		public static final String Suggestion_Title = "Suggestion-Report";
		public static final String Suffestioninfo = "SELECT * FROM suggestion_view where 2>1 ";
		
		
		// SpineLabel

		public static final String SpineLabelQuery = "SELECT call_no,LEFT(author_name,3) AS author_name,access_no,year_pub FROM accession_reg where";
		public static final String SpineLabel_Title = "Spine-Label";
		
		// Library_Login Report
		public static final String Library_Login_Title = "Library-Login-Details";
		public static final String Library_Login_Statitics_Title = "Library-Login-Statistics";
		public static final String Library_Login_Date = "SELECT * FROM library_login_view";
		public static final String Library_Login_Statistics = "SELECT last_visit,COUNT(*) AS totalCount FROM library_login_view WHERE 2>1 ";


		// Frequent Member Resource Report

		public static final String Frequently_Member = "Library Frequently Unused Member";
		public static final String Frequently_Pdf_Member = "Member-Frequent";

		public static final String Frequently_Member_Title = "Library Frequently Used Member";
		public static final String Frequently_Pdf_Member_Title = "Member-Frequent";

		public static final String Best_User = "Best_User_Member";
		public static final String Best_Used_Member = "Best_User_Award";

		public static final String Frequent_Member_Title = "Frequently-Borrowers";
		public static final String Frequently_USER_Title = "Frequently-Library-Users";
		public static final String Member_unused = "Not-Borrowed-Members";

}
