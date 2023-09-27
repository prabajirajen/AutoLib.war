package Common;

public interface DataQuery {

	
	
	//---------------journal Subscription------------------------------------
	
		public static final String JournalSubscriptionMax ="SELECT MAX(issue_access_no) FROM journal_issues where 2>1 ";
		
		public static final String JournalSubscriptionSearch ="SELECT * FROM journal_subscription_view WHERE Subs_No=?";
		
		public static final String JournalIssuesSearch ="SELECT * FROM journal_issues WHERE Subs_No=? and issue_date>=? and issue_date<=?";
		
		public static final String JournalSubscriptionSearchfull ="SELECT subs_no,jnl_name,supplier FROM journal_subscription_view WHERE 2>1 ";
		
		public static final String updateJournalSubsFlag="UPDATE journal_subscription SET flag='COMPLETED' WHERE subs_no=? ";
				
		public static final String updateJournalSubscription="UPDATE journal_subscription SET jnl_code=?,sup_code=?,subs_from=?,subs_to=?,Frequency=?,noofissues=?,expected_days=?,flag=?,issue_year=?,issue_month=?,issue_volume=?,issue_no=?,cost=?,currency=?,price=?,discount=?,netPrice=?,budgcode=?";
		
		
		public static final String deleteJnlIssues="DELETE FROM journal_issues WHERE subs_no=? ";

		public static final String deleteJnlSubscription="DELETE FROM journal_subscription WHERE subs_no=? ";

		public static final String deleteJnlIssuesCheck="SELECT * FROM journal_issues WHERE subs_no=? AND availability='YES' ";
		
		
    //  Transaction mas Query	
	
	
	public static final String TotCollection_Count = "SELECT COUNT(*) FROM accession_reg where 2>1 ";
   //public static final String TotCollection_Count = "SELECT SUM(tot) FROM library_collection where 2>1 ";
	public static final String DueList_Count = "SELECT COUNT(*) FROM due_reminder where 2>1 ";
	public static final String TotMember_Count = "SELECT COUNT(*) FROM member_view where 2>1 ";
	
	
	public static final String TotIssue_Count = "SELECT COUNT(*) FROM issuedbooks where 2>1 ";    // Current Issue Count
	//public static final String TotIssue_Count = "SELECT COUNT(*) FROM issue_mas WHERE 2>1 ";
	
	
	public static final String TotReturn_Count = "SELECT COUNT(*) FROM  jissue_history where 2>1 ";//total issue
	//public static final String TotReturn_Count = "SELECT COUNT(*) FROM  issue_history where 2>1 ";//total issue
	public static final String TotRenew_Count = "SELECT COUNT(*) FROM renewbooks WHERE issue_type='RENEW'";//total renewal
	//public static final String TotRenew_Count = "SELECT COUNT(*) FROM issue_mas WHERE issue_type='RENEW'";//total renewal
	
	//public static final String todayRenew_Count = "SELECT COUNT(*) FROM renewbooks WHERE issue_type='RENEW' and issue_date=curdate() "; //today newal
	
	public static final String todayRenew_Count = "SELECT COUNT(*) FROM issue_history WHERE issue_type='RENEW' and return_date=curdate() "; //today newal
	//public static final String todayRenew_Count = "SELECT COUNT(*) FROM issue_mas WHERE issue_type='RENEW' and issue_date=curdate() "; //today newal
	
	public static final String todayReturn_Count = "SELECT COUNT(*) FROM  jissue_history where 2>1 and issue_type='RETURN' and return_date=curdate()  "; //for today return
	//public static final String todayReturn_Count = "SELECT COUNT(*) FROM  issue_history where 2>1 and issue_type='RETURN' and return_date=curdate()  "; //for today return
	
	public static final String todayIssue_Count = "SELECT COUNT(*) FROM issuedbooks where issue_date=curdate() and issue_type='ISSUE' "; //today issue
	//public static final String todayIssue_Count = "SELECT COUNT(*) FROM issue_mas where issue_date=curdate() and issue_type='ISSUE' "; //today issue
	
	
	public static final String todayActiveGateLogin= "SELECT IFNULL(COUNT(*),0) AS cnt FROM entry_log WHERE DATE(entry_time)=CURDATE() ";
	
	public static final String todayTransSumAmount = "select sum(trans_amount) as trans_amount from trans_book_view where trans_date=curdate() ";
	
	
	public static final String todayPaymentSumAmount = "select sum(p.amount) as paidAmount from payment_clearance p inner join member_view m on p.member_code=m.member_code where payment_date=curdate()  ";
	
	//public static final String todayBalSumAmount ="select 	sum(t.trans_amount) - sum(ifnull(p.amount, 0)) as bal from trans_mas t left outer join payment_clearance p on t.member_code=p.member_code inner join member_view m on  t.member_code=m.member_code where p.Payment_Date=curdate()  ";
	public static final String todayBalSumAmount=" SELECT t.trans_amount-IFNULL(p.amount,0) AS test FROM todaytransamount AS t LEFT OUTER JOIN todaypaymentamount AS p ON t.branch_code=p.branch_code where 2>1  ";

	//----------------------missing number query------------------------------
	public static final String MissingAccessionQuery_Acc_no="select access_no FROM accession_reg where access_no=? and document=?";
	public static final String MissingAccessionQuery_Acc_no_ALL="SELECT access_no from accession_reg where access_no=?";
	public static final String MissingAccessNo="insert into miss_no(access_no)values(?)";
	public static final String Missingdelete="delete from miss_no where 2>1";
	
	//--------------------Suggestion----------------------------
	
			public static final String SuggestionSQLStringCode = "select max(request_no) as maxno from suggestion_mas";
			public static final String SuggestionSELECT_STRING = "select request_no,member_code,request_for,request_date,request_details,remarks from suggestion_mas WHERE request_no = ?";
			public static final String SUGGESTION_DELETE = "delete from suggestion_mas where request_no=?";
			public static final String newSuggestion = "SELECT * FROM suggestion_mas ORDER BY request_date DESC LIMIT 1";
			public static final String SUGGESTIONDISPLAY_NAME = "select request_no,member_code,request_for,request_date,request_details,remarks from suggestion_mas where 2>1";
			public static final String SUGGESTION_COUNT = "SELECT COUNT(*) FROM suggestion_mas WHERE request_date = CURDATE()";
	
	//--------------------Review---------------------------------
	
			public static final String ReviewSQLStringCode = "select max(review_no) as maxno from review_mas";
			public static final String ReviewSELECT_STRING = "select review_no,access_no,member_code,review_date,review_title,review_desc,rating from review_mas WHERE review_no = ?";
			public static final String REVIEW_DELETE = "delete from review_mas where review_no=?";
			public static final String REVIEWSEARCH_NAME = "select review_no,access_no,member_code,review_date,review_title,review_desc,rating from review_mas where 2>1 and access_no=:AccessNo and branch_code=:branchCode";
			//public static final String REVIEWDISPLAY_NAME = "select review_no,access_no,member_code,review_date,review_title,review_desc,rating from review_mas where 2>1 and access_no=?";
			public static final String REVIEWDISPLAY_NAME = "select review_no,access_no,member_code,review_date,review_title,review_desc,rating from review_mas where 2>1";

	
	
	//Transfer Report
	
		public static final String TRANSFERED_DEPT_LOAD ="SELECT DISTINCT Transfered_Dept_Code,Transfered_Dept_name FROM Transfer_View where 2 >1 ";
		
	//author mas Query	

	public static final String AUTHORSQLStringCode = "select max(Author_code) as maxno from author_mas";

	public static final String AUTHORSELECT_STRING = "select Author_code,Author_name,short_desc,emailid,branch_code from author_mas  WHERE author_code = ? ";

	public static final String AUTHORINSERT_STRING = "insert into author_mas(Author_code,Author_name,short_desc,emailid,branch_code) values(?,?,?,?,?)";

	public static final String AUTHORUPDATE_STRING = "update author_mas set Author_name=?,short_desc=?, emailid=? where Author_code=?";

	public static final String AUTHORSELECT_STRING_AUTHORINTERFACE = "select author_code from author_interface where author_code=?";

	public static final String AUTHORDELETE_STRING = "delete from author_mas where author_code=?";

	public static final String AUTHORSame_Name = "select Author_name,Author_code from author_mas where Author_name=? AND branch_code=?";
	
	public static final String AUTHORSEARCH_NAME = "select Author_Code,Author_Name,Short_Desc  from author_mas where 2>1 ";
	//public static final String AUTHORSEARCH_NAME = "select Author_Code,Author_Name,Short_Desc  from author_mas where 2>1 and author_code between 1 and 50 order by author_name";
	
	public static final String AUTHORSEARCH_NAME_LIKE = "select Author_Code,Author_Name,Short_Desc  from author_mas where 2>1 ";

	public static final String SUBJECTSQLStringCode = "select max(Sub_code) as maxno from subject_mas";


	public static final String SUBJECTSELECT_STRING = "select Sub_code,Sub_name,short_desc,Call_No,Branch_Code from subject_mas  WHERE Sub_code = ? ";

	public static final String SUBJECTINSERT_STRING = "insert into subject_mas(Sub_code,Sub_name,short_desc) values(?,?,?)";

	public static final String SUBJECTUPDATE_STRING = "update subject_mas set Sub_name=?,short_desc=? where Sub_code=?";

	public static final String SUBJECTDeletecheck = "select sub_code from book_mas where sub_code=?";

	public static final String SUBJECTDELETE_STRING = "delete from subject_mas where sub_code=?";

	public static final String SUBJECTSame_Name = "select Sub_name,Sub_code from subject_mas where Sub_name=?";
	
	public static final String SUBJECTSEARCH_NAME="select * from subject_mas where 2>1 and Sub_code<>1 ";
	//public static final String SUBJECTSEARCH_NAME="select * from subject_mas where 2>1 and sub_code between 1 and 50 order by sub_name";
	
	public static final String SUBJECTSEARCH_NAME_LIKE="select * from subject_mas where 2>1 and Sub_code<>1 and Sub_Name like '";
	
	public static final String SUBJECTSEARCH_CALL_NO_LIKE="select * from subject_mas where 2>1 and Sub_code<>1 and Call_No like '";
	
	public static final String ATLSEARCH_NAME_LIKE="select atl_no,atl_title,atl_author from journal_articles where 2>1 and atl_title like '";
	
	public static final String ATLSEARCH_NAME="select atl_no,atl_title,atl_author from journal_articles where 2>1 and atl_no between 1 and 500 order by atl_title";
	
	//Department mas query
	
	public static final String DEPARTMENTSQLStringCode ="select max(Dept_code) as maxno from department_mas";
	
	public static final String DEPARTMENTSELECT_STRING ="select Dept_code,Dept_name,short_desc,branch_code from department_mas  WHERE Dept_code =? ";
	
	public static final String DEPARTMENTINSERT_STRING ="insert into department_mas(Dept_code,Dept_name,short_desc,branch_code) values(?,?,?,?)"; // For Titan
	
	public static final String DEPARTMENTUPDATE_STRING ="update department_mas set Dept_name=?,short_desc=? where Dept_code=?";
		
	public static final String DEPARTMENTDELETE_STRING ="delete from department_mas where Dept_code=?";
	
	public static final String DEPARTMENTDeletecheck ="select Dept_code from book_mas where Dept_code=?";
	
	public static final String MemberDEPARTMENTDeletecheck ="select Dept_code from member_mas where Dept_code=?";
	
	public static final String DEPARTMENTSame_Name ="select Dept_name,Dept_code from department_mas where Dept_name=? ";
	
	public static final String DEPTSEARCH_NAME = "select Dept_Code,Dept_Name,Short_Desc  from department_mas where 2>1 ";
	
	public static final String DEPTSEARCH_NAME_LIKE = "select Dept_Code,Dept_Name,Short_Desc  from department_mas where 2>1 and Dept_Code<>1 and Dept_name like '";
	
	//City mas query
	
	public static final String CitySQLStringCode ="select max(City_code) as maxno from City_mas";
	
	public static final String CitySELECT_STRING ="select City_code,City_name,State,Country,Pincode,short_desc from City_mas  WHERE City_code =?";
	
	public static final String CityINSERT_STRING ="insert into City_mas(City_code,City_name,State,Country,Pincode,short_desc) values(?,?,?,?,?,?)";
	
	public static final String CityUPDATE_STRING ="update City_mas set City_name=?,short_desc=?,State=?,Country=?,Pincode=? where City_code=?";
		
	public static final String CityDELETE_STRING ="delete from City_mas where City_code=?";
	
	public static final String CityDeletecheck ="select City_code from book_mas where City_code=?";
	
	public static final String CitySame_Name ="select City_name,City_code from City_mas where City_name=?";
	
	public static final String CitySEARCH_NAME = "select City_code,City_name,State,Country,Pincode,short_desc  from City_mas where 2>1 and City_code between 1 and 100 order by City_name";
	
	public static final String CitySEARCH_NAME_LIKE = "select City_code,City_name,State,Country,Pincode,short_desc  from City_mas where 2>1 and City_name like '";
	
	//Binding Mas 
	
	public static final String Binder_Load = "select * from binder_mas";
	
	public static final String BindingSQLStringCode = "select max(binder_code) as maxno from binder_mas";
	
	public static final String BindingSELECT_STRING = "select * from binder_mas  WHERE binder_code = ?";
	
	public static final String BindingUPDATE_STRING = "update binder_mas set binder_name=?,short_desc=?,address1=?,address2=?,city=?,state=?,country=?,pincode=?,phone=?,fax=?,emailid=?,urlid=?  where binder_code= ?";
	
	public static final String BindingSame_Name = "select binder_name,binder_code from binder_mas where binder_name =? ";
	
	public static final String BindingINSERT_STRING = "insert into binder_mas values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String BindingDeletecheck = "select binder_code from binding_documents where binder_code=?";
	
	public static final String BindingDELETE_STRING = "delete from binder_mas where binder_code=?";
	
	public static final String BINDER_SEARCH ="select binder_code,binder_name,short_desc from binder_mas where 2>1   order by binder_name";
	
	public static final String BINDER_SEARCH_LIKE ="select binder_code,binder_name,short_desc from binder_mas where 2>1 and binder_name like '";
	
	public static final String BOOKMAS_STATUS = "select availability from book_mas where document=? and access_no=? and branch_code=? ";
	
	//public static final String SELECT_BINDDOC_MAS="select * from binding_documents where  access_no=?";
	public static final String SELECT_BINDDOC_MAS="select * from binding_view where  access_no=?";
	
	public static final String INSERT_BINDDOC_MAS="insert into binding_documents(binder_code,access_no,doc_type,sending_date,availability) values(?,?,?,?,?)";
	
	public static final String UPDATE_BOOK_STATUS="update book_mas set availability='BINDING' where  access_no=?";
	
	public static final String UPDATE_BOOK_STATUS_RETURN="update book_mas set availability='YES' where  access_no=?";
	
	public static final String UPDATE_REFERENCEBOOK_STATUS_RETURN="update book_mas set availability='REFERENCE' where  access_no=?";
	
	public static final String BINDING_DISPLAY="select * from  binding_view where 2>1 ";
	
	public static final String BINDING_DISPLAY_SELECT="select * from  binding_view where access_no=?";
	
	public static final String DELETE_BINDDOC_MAS="delete from binding_documents where access_no=?";
	
	public static final String INSERT_BINDDOC_RETURN="insert into binding_return(binder_name,access_no,doc_type,sending_date,return_date,availability) values(?,?,?,?,?,?)";
	
	//Pub_sup_Mas    

	public static final String PUBSUBSQLStringCode = "select max(Sp_code) as maxno from sup_pub_mas";
	
	public static final String PUBSUBSELECT_STRING = "select * from sup_pub_mas where sp_code=? ";
	
	public static final String PUBSUBUPDATE_STRING = "update sup_pub_mas set sp_name=?,short_desc=?,address1=?,address2=?,city=?,state=?,country=?,pincode=?,phone=?,fax=?,emailid=?,urlid=?,sp_type=?,branch_code=?   where sp_code= ?";
	
	public static final String PUBSUBDeletecheck = "select sup_code from book_mas where sup_code=?";
	
	public static final String PUBSUBDeletechecks = "select pub_code from book_mas where pub_code=?";
	
	public static final String PUBSUBDELETE_STRING = "delete from sup_pub_mas where sp_code=?";
	
	public static final String PUBSUBSame_Name = "select sp_name,sp_code from sup_pub_mas where sp_name =? and sp_type=?";
	
	public static final String PUBSUBDiff = "select * from sup_pub_mas where sp_code =? and sp_type=?";
	
	public static final String PUBSUBINSERT_STRING = "insert into sup_pub_mas values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String PUBSUBSEARCH_STRING_SUP ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_type='SUPPLIER' ";
	//public static final String PUBSUBSEARCH_STRING_SUP ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_code between 1 and 100 and sp_type='SUPPLIER' order by sp_name";
	public static final String PUBSUBSEARCH_STRING_LIKE_SUP ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_name like '";
	
	public static final String PUBSUBSEARCH_STRING_PUB ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_type='PUBLISHER' ";
	//public static final String PUBSUBSEARCH_STRING_PUB ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_code between 1 and 100 and sp_type='PUBLISHER' order by sp_name";
	
	public static final String PUBSUBSEARCH_STRING_LIKE_PUB ="select sp_code,sp_name,short_desc from sup_pub_mas where 2>1 and sp_name like '";
	
	

	//------------------Change Member Id---------------------------------
	
	public static final String updateMemberMas="UPDATE member_mas set member_code=? WHERE member_code=?";
	
	public static final String updateIssueMas="UPDATE issue_mas SET member_code=? WHERE member_code=?";
	
	public static final String updateIssueHistory="UPDATE issue_history SET member_code=? WHERE member_code=?";
	
	public static final String updateTransMas="UPDATE trans_mas SET member_code=? WHERE member_code=?";
	
	public static final String updatePaymentClearance="UPDATE payment_clearance SET member_code=? WHERE member_code=?";
	
	public static final String updateMiscellaneousMas="UPDATE miscellaneous_mas SET member_code=? WHERE member_code=?";
		
	public static final String updateRenewalTime="UPDATE renewal_time SET member_code=? WHERE member_code=?";
	
	public static final String updateEntryLog="UPDATE entry_log SET member_code=? WHERE member_code=?";
	
	public static final String updateReturnLog="UPDATE return_log SET member_code=? WHERE member_code=?";
	
	public static final String updateLibTimeManagement="UPDATE lib_timemanagement SET user_id=? WHERE user_id=?";
	
	public static final String updateLoginMas="UPDATE login_mas SET login_id=? WHERE login_id=?";
	
	public static final String updateReserveMas="UPDATE reserve_mas SET member_code=? WHERE member_code=?";
	
	
	//-------------------------------------------------
	
	
	
	
	//Member Mas
	
	
	public static final String MEMBERSELECT_STRING="select * from member_mas where member_code=?";
	
	public static final String MEMBERVIEWSELECT_STRING="select * from member_view where member_code=? and branch_code=? ";
	
	public static final String MEMBERFINDNEW_STRING="select * from member_mas where ";
	
	public static final String MEMBERNEW_STRING="SELECT CONCAT(LEFT(MEMBER_CODE,LENGTH(MAX(MEMBER_CODE))-3),LPAD(RIGHT(MAX(MEMBER_CODE),3)+1,'3','000')) AS MEMBER_CODE FROM MEMBER_MAS WHERE  ";
	
	public static final String countUserLogging="select count(*) as cnt from return_log where member_code=? ";
	
	
	public static final String MEMBERSEARCH_STRING_LIKE = "select member_code,member_name,designation_code from counter_member where 2>1 and member_name like '";
	
	public static final String MEMBERSEARCH_STRING = "select member_code,member_name,designation_code from counter_member where 2>1 ";
	
	public static final String ISSUEMASCHECK_STRING="select member_code from issue_MAS where member_code=?";
	
	public static final String MEMBERDELETE_STRING="delete from member_mas where member_code=? and branch_code=? ";
	
	public static final String MEMBERUPDATE_STRING="update member_mas set Member_name=?,Birth_date=?,enroll_date=?,expiry_date=?,deposit_amount=? ,designation_code=?,sex=?,member_add1=?,Branch_code=?,member_city=?,member_state=?,member_pincode=?,member_phone=?,member_email=?,dept_code=?,Course_code=?,group_code=?,remarks=?,Profile=?,slock=?,cyear=?,member_add1=?,member_add2=? where Member_code=? and branch_code=? ";  // For Titan
	
	public static final String MEMBERINSERT_STRING="insert into member_mas(Member_name,Birth_date,enroll_date,expiry_date,deposit_amount,designation_code,sex,member_add1,Branch_code,member_city,member_state,member_pincode,member_phone,member_email,dept_code,Course_code,group_code,remarks,Profile,slock,cyear,member_add2,Member_code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String MEMBER_PHOTO_UPDATE_STRING="update member_mas set photo=? where Member_code=? and branch_code=? ";
	
	public static final String MEMBERDESIG_CODE="select desig_code from designation_mas  where desig_name=?";
	
	public static final String MEMBERDEPT_CODE="select dept_code from department_mas  where dept_name=?";
	
	public static final String MEMBERCOURSE_CODE="select course_code from course_mas where course_name=? and course_major=?";
	
	public static final String MEMBERGROUP_CODE="select group_code from group_mas where group_name=? and branch_code=? ";
	
	// Member Email	or SMS
	
	public static final String MEMBEREMAIL_STRING="SELECT member_email,member_phone,member_name FROM mail_view where member_code=?";
	
	public static final String EMAILCheck_STRING="select member_code,member_name,login_password,member_email from mail_view where member_email=?";
	
	public static final String DueReminder_List = "select distinct member_code,member_name,member_email,member_phone,due_date from email_duereminder where 2>1 ";
	
	public static final String Email_DueReminder="select distinct member_email,member_code,member_name from email_duereminder where 2>1 ";
	
	public static final String Sms_DueReminder="select distinct member_phone,member_code,member_name from email_duereminder where 2>1 ";
	
	public static final String Email_DueReminder_Details="select * from email_duereminder where member_code = ?";
	
	public static final String Sms_AdvanceDueReminder="SELECT DISTINCT member_phone,member_code,member_name FROM advance_due_reminder WHERE 2>1 ";
	
	public static final String Sms_AdvanceDueReminderList="SELECT * FROM advance_due_reminder WHERE  member_code = ? ";
	
	
	public static final String Sms_ReserveReminderList="SELECT id,Access_no,title,member_code,member_name,res_date,doc_type,dept_name,smsFlag,SmsDate,member_phone FROM Reserve_Reminder_View WHERE 2>1 ";
	
	public static final String Sms_ReserveReminderUpdate="UPDATE reserve_mas set smsFlag=1,SmsDate=? WHERE 2>1 and member_code=? and Access_no=? and doc_type=? ";
	
	public static final String DELETE_RESERVE_MAS_ALL =	"delete from reserve_mas where smsFlag=1 and SmsDate<>?";
	
	//BOOK MAS
	
	public static final String SELECT_ACCESS ="select * from book_display_view  where access_no = ? and branch_code=? and document=? ";
	
	public static final String SELECT_INTERFACE ="select Access_no from author_interface where Access_no= ? and branch_code=? ";
	
	public static final String INSERT_INTERFACE ="insert into author_interface (author_code,access_no,priority,role,doc_type,branch_code) values (?,?,?,?,?,?)";
	
	public static final String DELETE_INTERFACE ="delete  from author_interface where access_no= ? and branch_code=?";
	
	public static final String DELETE_BOOK ="delete from book_mas where access_no= ?";
	
	public static final String DELETE_HISTORY ="delete from issue_history where access_no= ?";
	
	public static final String DELETE_RESERVE ="delete from reserve_mas where access_no= ?";
	
	public static final String SELECT_STRING ="select access_no from book_mas WHERE access_no = ? and branch_code = ?";
	
	public static final String SELECT_ISSUE ="select Access_no from issue_mas where Access_no= ?";
	
	public static final String SELECT_HISTORY ="select Access_no from issue_history where Access_no= ?";
	
	public static final String SELECT_RESERVE ="select Access_no from reserve_mas where Access_no= ?";
	
	public static final String INSERT_STRING ="insert into book_mas(Access_No,Title,Author_Name,Call_No,other_title,Role,"+
 					"SRes,Edition,Language,place,Year_Pub,Pages,Size,Illustrator,ISBN,"+
 					"Bprice,NoOfCopy,Script,Location,Availability,Sub_Code,Sup_Code,"+
 					"Pub_Code,Branch_Code,Document,Type,Phy_Media,Binding,Keywords,Notes,"+
					"Summary,Biblio,Contents,VolNo,Part_No,Vtitle,Vres,Corp_Author_Name,"+
					 "Corp_Address,Series_Author_Name,ser_Name,Series_Title,ISSN,Meeting,"+
					 "Sponsor,Venue,Received_Date,Invoice_No,Invoice_Date,BCurrency,BCost,"+
					 "Accepted_Price,Discount,Add_Field1,Add_Field2,Add_Field3,Add_Field4,"+
					 "Dept_Code,Gift_Purchase,Budg_Code,MDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
	public static final String UPDATE_STRING ="UPDATE book_mas SET Access_No=?,Title=?,Author_Name=?,Call_No=?,other_title=?,Role=?,SRes=?,Edition=?,Language=?,place=?,Year_Pub=?,pages=?,Size=?,Illustrator=?,ISBN=?,Bprice=?,NoOfCopy=?,Script=?,Location=?,Availability=?,Sub_Code=?,Sup_Code=?,Pub_Code=?,Branch_Code=?,Document=?,Type=?,Phy_Media=?,Binding=?,Keywords=?,Notes=?,Summary=?,Biblio=?,Contents=?,VolNo=?,Part_No=?,Vtitle=?,Vres=?,Corp_Author_Name=?,Corp_Address=?,Series_Author_Name=?,ser_Name=?,Series_Title=?,ISSN=?,Meeting=?,Sponsor=?,Venue=?,Received_Date=?,Invoice_No=?,Invoice_Date=?,BCurrency=?,BCost=?,Accepted_Price=?,Discount=?,Add_Field1=?,Add_Field2=?,Add_Field3=?,Add_Field4=?,Dept_Code=?,Gift_Purchase=?,Budg_Code=?,MDate=? where access_no=? and branch_code=? ";
	
	public static final String INSERT_SORT ="insert into sort_book(Access_no,N1,N2,N3,branch_code)values(?,?,?,?,?);";
	
	public static final String INSERT_DELETE ="delete from sort_book where access_no=? and branch_code=?";

    public static final String BOOKSEARCH_NAME = "select access_no,title,author_name  from book_mas where 2>1";
	
	public static final String BOOKSEARCH_NAME_LIKE = "select access_no,title,author_name  from book_mas where 2>1 and title like '";
	
	 public static final String BOOKSEARCH_AuhtorNAME = "select *  from author_mas where 2>1 ";
		
	public static final String BOOKSEARCH_NAME_AuthorLIKE = "select *  from author_mas where 2>1 ";
	
	 public static final String BOOKSEARCH_PUBNAME = "select *  from sup_pub_mas where 2>1 and sp_code<>1 and  sp_type='PUBLISHER' ";
		
		public static final String BOOKSEARCH_NAME_PUBLIKE = "select *  from sup_pub_mas where 2>1 and sp_code<>1 and  sp_type='PUBLISHER' and SP_name like '";
		
      public static final String BOOKSEARCH_BRANCHNAME = "select *  from branch_mas where 2>1 and branch_code not in(1) ";
		
		public static final String BOOKSEARCH_NAME_BRANCHLIKE = "select *  from branch_mas where 2>1 and branch_code<>1 and  branch_name like '";
		
      public static final String BOOKSEARCH_BUDGETNAME = "select *  from budget_mas where 2>1 and Bud_code<>1  ";
		
		public static final String BOOKSEARCH_NAME_BUDGETLIKE = "select *  from budget_mas where 2>1 and Bud_code<>1 and  bud_head like '";
	
      public static final String BOOKSEARCH_KEYTNAME = "select *  from keywords_mas where 2>1  order by keyword_name";
		
		public static final String BOOKSEARCH_NAME_KEYLIKE = "select *  from keywords_mas where 2>1 and  keyword_name like '";		
		
      public static final String BOOKSEARCH_SUPNAME = "select *  from sup_pub_mas where 2>1 and  sp_type='SUPPLIER' ";
		
		public static final String BOOKSEARCH_NAME_SUPLIKE = "select *  from sup_pub_mas where 2>1 and  sp_type='SUPPLIER' and SP_name like '";
		
		public static final String BOOKSEARCH_CODE_SUBJECT="select sub_code from subject_mas where sub_name=?";
		
		public static final String BOOKSEARCH_CODE_SUBJECT_SAVE = "insert into subject_mas(Sub_code,Sub_name,branch_code) values(?,?,?)";
		
		public static final String BOOKSEARCH_CODE_BRANCH ="select branch_code from branch_mas where branch_name=? ";
		
		public static final String BOOKSEARCH_CODE_BRANCH_MAX ="select max(branch_code) as branch_max  from branch_mas";
		
		public static final String BOOKSEARCH_CODE_BRANCH_SAVE ="insert into branch_mas(branch_code,branch_name) values(?,?)";
		
		public static final String BOOKSEARCH_CODE_SUPPLIER ="select sp_code from sup_pub_mas where sp_type='SUPPLIER' and sp_name=?";
		
		public static final String BOOKSEARCH_CODE_SUPPLIER_SAVE ="insert into sup_pub_mas(sp_code,sp_name,sp_type,branch_code) values(?,?,?,?)";
		
		public static final String BOOKSEARCH_CODE_PUB ="select sp_code from sup_pub_mas where sp_type='PUBLISHER' and sp_name=?";
		
		public static final String BOOKSUPDATE_STATUS_CHECK ="select * from issue_mas where access_no=? and branch_code = ?";
		
	//Course Mas
		
		
	public static final String COURSESQLStringCode ="select max(Course_code) as maxno from course_mas";
	
	public static final String COURSESELECT_STRING ="select Course_code,Course_name,Course_major,Course_period,Course_type,short_desc from course_mas  WHERE Course_code = ?";
	   
	public static final String COURSEINSERT_STRING ="insert into course_mas(Course_code,Course_name,Course_major,Course_period,Course_type,short_desc) values(?,?,?,?,?,?)";
	
	
	public static final String COURSEINSERT_STRING_import ="insert into course_mas(Course_code,Course_name,Course_major,Course_period,Course_type,short_desc,branch_code) values(?,?,?,?,?,?,?)";
	   
	public static final String COURSEUPDATE_STRING ="update course_mas set Course_name=?,Course_major=?,Course_period=?,Course_type=?,short_desc=? where Course_code=?";
	   
	public static final String COURSEDeletecheck ="select Course_code from member_mas where Course_code=?";
	   
	public static final String COURSEDELETE_STRING ="delete from course_mas where Course_code=?";
	   
	public static final String COURSESame_Name ="select Course_name,Course_code from course_mas where Course_name=?";
	
	//DESIGNATION MAS
	public static final String DESIGNATIONSEARCH_NAME = "select desig_code,desig_name,short_desc from designation_mas where 2>1 and desig_code<>1";
	//public static final String DESIGNATIONSEARCH_NAME = "select desig_code,desig_name,short_desc from designation_mas where 2>1 and desig_code between 1 and 50 order by desig_name";
	
	public static final String DESIGNATIONSEARCH_NAME_LIKE = "select desig_code,desig_name,short_desc from designation_mas where 2>1 and desig_code<>1 and desig_name like '";
	
	
	public static final String DesigSQLStringCode = "select max(Desig_code) as maxno from designation_mas";
    
	public static final String DesigSELECT_STRING = "select Desig_code,Desig_name,short_desc,branch_code from designation_mas  WHERE Desig_code = ? ";
	   
	public static final String DesigINSERT_STRING = "insert into designation_mas(Desig_code,Desig_name,short_desc,Branch_Code) values(?,?,?,?)";
	   
	public static final String DesigUPDATE_STRING = "update designation_mas set Desig_name=?,short_desc=?,Branch_Code=? where Desig_code=?";
	   
	public static final String DesigDeletecheck = "select designation_code from member_mas where Designation_code=?";
	   
	public static final String DesigDELETE_STRING = "delete from designation_mas where Desig_code=?";
	   
	public static final String DesigSame_Name ="select Desig_name,Desig_code from designation_mas where Desig_name=? and Branch_Code=?";
	
	
	
	
	//GROUP MAS
	public static final String GROUPSEARCH_NAME = "select group_code,group_name,remarks from group_mas where 2>1 ";
	
	public static final String GROUPSEARCH_NAME_LIKE = "select group_code,group_name,remarks from group_mas where 2>1 AND group_code<>1 and group_name like '";
	
	
	
	//COURSE MAS
	
	public static final String COURSESEARCH_NAME = "select course_code,course_name,course_major from course_mas where 2>1 ";
	
	public static final String COURSESEARCH_NAME_LIKE = "select course_code,course_name,course_major from course_mas where 2>1 and course_code<>1 and course_name like '";
	
   //CITY MAS
	
	public static final String CITYSEARCH_NAME = "select city_name,state,pincode from city_mas where 2>1 and city_code between 1 and 100 order by city_name";
	
	public static final String CITYSEARCH_NAME_LIKE = "select city_name,state,pincode from city_mas where 2>1 and city_name like '";
	
	
	//BRANCH MAS
	
	public static final String BRANCHQLStringCode ="select max(branch_code) as maxno from branch_mas";
	
	public static final String BRANCHSELECT_STRING ="select branch_code,branch_name,short_desc from branch_mas where branch_code=?";
	
	public static final String BRANCHSELECT_STRING_INTERFACE = "select branch_code from book_mas where branch_code=?";
	
	public static final String BRANCHDELETE_STRING = "delete from branch_mas where branch_code=?";
		
	public static final String Branch_Load="select * from branch_mas ";
	
	public static final String BRANCHSame_Name ="select branch_code,branch_name,short_desc from branch_mas where branch_name=?";
	
	public static final String BRANCHSINSERT_STRING = "insert into branch_mas(branch_code,branch_name,short_desc) values(?,?,?)";
	
	public static final String BRANCHUPDATE_STRING = "update branch_mas set branch_name=?,short_desc=? where branch_code=?";
	
	public static final String BRANCHUPDATE_STRING_SARCH_LIKE ="select * from branch_mas where 2>1 and Branch_name like '";
	
	public static final String BRANCHUPDATE_STRING_SARCH ="select * from branch_mas where 2>1 ";
	//CURRENCY
	
	public static final String CURRENCYQLStringCode ="select max(code) as maxno from currency_mas";
	
	public static final String CURRENCYSELECT_STRING ="select * from currency_mas where code=?";
	
	public static final String CURRENCYSELECT_STRING_INTERFACE ="select bcurrency from book_mas where bcurrency=?";
	
	public static final String CURRENCYDELETE_STRING = "delete from currency_mas where code=?";
	
	public static final String CURRENCYSame_Name ="select * from currency_mas where Currency=?";
	
	public static final String CURRENCYINSERT_STRING = "insert into currency_mas(code,Currency,Indian_value,remarks) values(?,?,?,?)";
	   
	public static final String CURRENCYUPDATE_STRING = "update currency_mas set Currency=?,Indian_value=?,remarks=? where code=?";
	
   public static final String CURRENCYSEARCH_NAME = "select code,Currency,Indian_Value,remarks from currency_mas where 2>1 and code between 1 and 500 order by Currency";
	
	public static final String CURRENCYSEARCH_NAME_LIKE = "select code,Currency,Indian_Value,remarks from currency_mas where 2>1 and Currency like '";
	
	public static final String CURRENCYQLStringCode_LOAD ="select * from currency_mas";
	//KEYWORDS
	
	public static final String NEW_KEYWORDS="select max(keyword_code) as max from keywords_mas";
	
	public static final String SELECT_KEYWORDS="select * from keywords_mas where keyword_code=?";
	
	public static final String DELETE_KEYWORDS="delete from keywords_mas where keyword_code=?";
	
	public static final String SELECT_SAME_NAME="select keyword_code,keyword_name from keywords_mas where keyword_name=?";
	
	public static final String SAVE_KEYWORDS="insert into keywords_mas(keyword_code,keyword_name,short_desc) values(?,?,?)";
	
	public static final String UPDATE_KEYWORDS="update keywords_mas set keyword_name=?,short_desc=? where keyword_code=?";
	
	public static final String KEYWORDS_SEARCH_NAME_LIKE="select * from keywords_mas where 2>1 and keyword_name like '";
	
	public static final String KEYWORDS_SEARCH_NAME="select * from keywords_mas where 2>1 order by keyword_name";
	//public static final String KEYWORDS_SEARCH_NAME="select * from keywords_mas where 2>1 and  keyword_code between 1 and 50 order by keyword_name";
	
	
	//COUNTER
	
	public static final String SELECT_MEMBER =	"select * from counter_member  where member_code=? and branch_code=?";
	
	public static final String SELECT_GROUP ="select group_code from group_mas where group_name=? and branch_code=? ";
	
	//public static final String SELECT_ISSUE_MEMBER ="select * from issuedbooks where member_code=?";
	
	 public static final String SELECT_ISSUE_MEMBER ="select * from issued_count_books where member_code=? ";//SHEK
	
	//public static final String SELECT_JNLISSUE_MEMBER ="select * from issuedjournals where member_code=?";
	
	public static final String SELECT_BOOKCOUNT ="select count(*) as cnt from issue_mas where member_code=? and doc_type=?";
	
	public static final String checkTitleAlreadyIssued="SELECT b.title,b.author_name,b.edition FROM book_mas AS b INNER JOIN issuedbooks i ON b.title=i.title AND b.author_name=i.author_name  AND b.access_no=? AND i.member_code=?";
	
	public static final String SELECT_BOOK ="select * from counter_book_select where access_no=? and branch_code=?";
	
	public static final String SELECT_JNL_Issue ="SELECT Issue_Access_No,jnl_name,publisher,Availability,Doc_Type FROM f_journal_issues where Issue_Access_No=?";
	
	public static final String SELECT_ISSUEMAS ="select issue_mas.member_code,issue_mas.issue_date,issue_mas.due_date,issue_mas.issue_type,issue_mas.Doc_Type,member_mas.group_code from issue_mas,member_mas where issue_mas.member_code=member_mas.member_code and  access_no=? and issue_mas.branch_code=? ";
	
	public static final String SELECT_EPC ="select access_no from rfid_tag where epc_id=?";
	
	public static final String SELECT_UID ="select member_code from rfid_card where card_uid=?";
	
	public static final String SELECT_FINEMAS ="select * from fine_mas where group_code=? order by fine_id";
	
	public static final String valid_date ="select datediff(?,?) as no_of_days";
	
	public static final String rfid_availability ="SELECT access_no  FROM ISSUE_MAS where access_no=?";
	
	public static final String SELECT_MEMBER_MAS ="select member_code from member_mas  where member_code=? and branch_code=? ";
	
	public static final String SELECT_Issuemas ="select availability,document from book_mas where access_no=? and branch_code=?";
	
	public static final String SELECT_JNLIssuemas ="select availability,Doc_Type from f_journal_issues where Issue_Access_No=?";
	
	public static final String SELECT_RESERVE_PRI =	"select min(id) as mm from reserve_mas as a,member_mas as b where access_no=? and a.member_code=b.member_code";
	
	public static final String SELECT_RESERVE_PRI1 ="select a.member_code,a.id,b.member_name from reserve_mas as a,member_mas as b where access_no=? and a.member_code=b.member_code order by id";
	
	public static final String DELETE_RESERVE_MAS =	"delete from reserve_mas where member_code=? and access_no=?";
	
	public static final String SELECT_BOOKMAS =	"select availability from book_mas where access_no=? and branch_code=? ";
	
	public static final String SELECT_JNLMAS =	"select availability from f_journal_issues where Issue_Access_No=? and branch_code=? ";
	
	public static final String UPDATE_BOOKMAS ="update book_mas set availability='ISSUED' where access_no=? and branch_code=?";
	
	public static final String REF_UPDATE_BOOKMAS ="update book_mas set availability='REFISSUED' where access_no=? and branch_code=?";
	
	public static final String TRANS_UPDATE_BOOKMAS ="update book_mas set availability='TRANSISSUED' where access_no=? and branch_code=?";
	
	public static final String UPDATE_JNLISSUEMAS ="update journal_issues set availability='ISSUED' where Issue_Access_No=? and branch_code=?";
	
	public static final String SELECT_ISSUEMEMBER ="select member_code from issue_mas where access_no=?";
	
	public static final String SELECT_ISSUEBOOKS ="select * from issuedbooks where member_code=? ";
	
	public static final String SELECT_ISSUEBranchBOOKS ="select * from issuedbooks where member_code=? and branch_code=? ";
	
	
	
	public static final String SELECT_RETURN_BOOK =	"select a.* ,b.availability from issue_mas as a,book_mas as b where a.access_no=? and a.member_code=? and a.access_no=b.access_no  AND b.availability='ISSUED' ";
	
	public static final String SELECT_RETURN_JNLISSUE =	"select a.* ,b.availability from issue_mas as a,journal_issues as b where a.access_no=? and a.member_code=? and a.access_no=b.Issue_Access_No";
	
	public static final String UPDATE_RETURN_BOOK_MAS =	"update book_mas set availability='YES' where access_no=? and branch_code=?";
	
	public static final String REF_UPDATE_RETURN_BOOK_MAS =	"update book_mas set availability='REFERENCE' where access_no=? and branch_code=? ";
	
	public static final String TRANS_UPDATE_RETURN_BOOK_MAS =	"update book_mas set availability='TRANSFERED' where access_no=? and branch_code=? ";
	
	public static final String UPDATE_RETURN_JNLISSUE_MAS =	"update journal_issues set availability='YES' where Issue_Access_No=? and branch_code=? ";
	
	public static final String DELETE_RENEWAL =	"delete from renewal_time where access_no=? and member_code=? and doc_type=? and branch_code=?";
	
	public static final String INSERT_HISTORY =	"insert into issue_history(member_code,access_no,issue_date,due_date,return_date,fine_amount,staff_code,doc_type,issue_type,branch_code) values (?,?,?,?,?,?,?,?,?,?)";
	
	
	public static final String DELETE_ISSUEMAS ="delete from issue_mas where member_code=? and access_no=? and doc_type=? and branch_code=?";
	
	public static final String SELECT_ISSUEMAS_ONLY ="select * from issue_mas where  member_code=? and access_no=? and branch_code=? ";
	
	
	public static final String SELECT_RESERVEMAS =	"select * from reserve_mas where member_code=? and access_no=?";
	
	public static final String SELECT_MAX_RESERVEMAS ="select count(*) from reserve_mas where member_code=?";
	
	public static final String SELECT_RESERVEMAS_MAX =	"select max(ID) as maxno from reserve_mas";
	
	public static final String INSERT_RESERVEMAS ="insert into reserve_mas (id,member_code,access_no,doc_type,res_date) values (?,?,?,?,?)";
	
	public static final String RESERVEMAS_SELECT_MEMBER ="select * from member_reserve_view where member_code=? and branch_code=?";
	
	public static final String RESERVEMAS_SELECT_BOOK ="select * from member_reserve_view where access_no=? and branch_code=? ";
	
	public static final String SELECT_ISSUE_MAS_RENEW ="select issue_mas.member_code,issue_mas.issue_date,issue_mas.due_date,issue_mas.issue_type,issue_mas.Doc_Type,member_mas.group_code from issue_mas,member_mas where issue_mas.member_code=member_mas.member_code and  access_no=?";
	
	public static final String UPDATE_ISSUEMAS ="update issue_mas set due_date=?,issue_date=?,issue_type=?,staff_code=?,branch_code=? where member_code=? and access_no=? and branch_code=? ";
	
	public static final String SELECT_HOLIDAY_MAS_CHECK ="select Leave_date  from holiday_mas where leave_date=?";
	
	public static final String SELECT_REF_DUEDAYS =	"select due_days from ref_due_mas where 2>1 ";
	
	public static final String SAVE_REF_DUEDAYS = "insert into ref_due_mas(due_days) values(?); ";
	
	public static final String DELETE_REF_DUEDAYS = "delete from ref_due_mas where 2>1 ";
	
	
	//Fine
	
	public static final String NEW_FINE_MAX="select max(Fine_id) as maxno from fine_mas";
	
	public static final String NEW_FINE_SEARCH="select * from fine_mas where Fine_id=?";
	
	public static final String NEW_FINE_SEARCH_GROUPNAME="select group_name from group_mas where group_code=? ";
	
	public static final String FINE_DELETE="delete from fine_mas where Fine_id=?";
	
	public static final String FINE_SELECT_GROUPNAME="select group_code from group_mas where group_name =? ";
	
	public static final String FINE_SAVE="insert into fine_mas(fine_id,group_code,fine_period,fine_amount,period_type) values(?,?,?,?,?)";
	
	public static final String FINE_UPDATE="update fine_mas set group_code=?,fine_period=?,fine_amount=?,period_type=? where fine_id=?";
	
	//public static final String FINE_LISTSEARCH="select * from fine_mas ";
	public static final String FINE_LISTSEARCH="select * from fine_view where 2>1 ";
	
	public static final String FINE_LISTSEARCH_GROUP="select group_code,group_name,remarks from group_mas where 2>1 ";
	
	public static final String FINE_LISTSEARCH_GROUP_LIKE="select group_code,group_name,remarks from group_mas where 2>1 and Group_name like '";
	
	//Holiday
	public static final String LISTSEARCH_HOLIDAY="select leave_date,Add_day,Remarks from holiday_mas order by leave_date ";
	
	public static final String HOLIDAY_DELETE="delete from holiday_mas";
	
	public static final String HOLIDAY_DELETE_SINGLE="delete from holiday_mas where Leave_date=?";
	
	public static final String HOLIDAY_SELECT_SINGLE="select Leave_date from holiday_mas where Leave_date=?";
	
	
	
	//Budget
	
	public static final String NEW_BUDGET_MAX="select max(Bud_code) as maxno from budget_mas";
	
	public static final String BUDGET_SEARCH="select * from Budget_Full_View where Bud_code=? ";
	
	public static final String BUDGET_UpdateAll="CALL Budget_Update ";
	
	public static final String BUDGET_SEARCHNEW="select bud_code from budget_mas where bud_head=? ";
	
	public static final String BUDGET_SEARCH_DEPT="select dept_name from department_mas where dept_code=?";
	
	
	public static final String Budgetselect_dept_name="select dept_code from department_mas where dept_code=?";
	
	public static final String BUDGET_INTERFACE="select Budg_code from book_mas where Budg_code=?";
	
	public static final String BUDGET_DELETE="delete from budget_mas where Bud_code=?";
	
	public static final String BUDGET_DEPT_SEARCH_LIKE="select * from department_mas where 2>1 and dept_name like '";
	
	public static final String BUDGET_DEPT_SEARCH="select * from department_mas where 2>1 ";
	
   public static final String BUDGET_SEARCH_LIKE="select bud_code,bud_head,bud_amount from budget_mas where 2>1 and bud_head like '";
	
	public static final String BUDGET_SEARCH_LIST="select bud_code,bud_head,bud_amount from budget_mas where 2>1 and bud_code between 1 and 150 ";
	
	public static final String BUDGET_SEARCH_DEPT_CODE="select dept_code from department_mas where dept_name=?";
	
	public static final String BUDGET_DEPT_CODE_Search="select dept_code from department_mas where dept_code=?";
	
	public static final String BUDGET_SAVE="insert into budget_mas(bud_code,bud_head,dept_code,bud_amount,bud_samount,bud_from,bud_to,remarks,b_amount,b_samount,bb_amount,bb_samount,nb_amount,nb_samount,r_amount,r_samount,t_amount,t_samount,s_amount,s_samount,p_amount,p_samount,j_amount,j_samount,m_amount,m_samount,Branch_Code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	//public static final String BUDGET_UPDATE="update budget_mas set bud_head=?,dept_code=?,bud_amount=?,bud_samount=?,bud_from=?,bud_to=?,remarks=?,Branch_Code=? where bud_code=?";
	public static final String BUDGET_UPDATE="UPDATE Budget_Mas SET Bud_Head=?,Dept_Code=?,Bud_Amount=?,Bud_SAmount=?,Bud_From=?,Bud_To=?,Remarks=?,B_Amount=?,B_SAmount=?,BB_Amount=?,Bb_SAmount=?,NB_Amount=?,nB_SAmount=?,R_Amount=?,R_SAmount=?,T_Amount=?,T_SAmount=?,S_Amount=?,S_SAmount=?,P_Amount=?,P_SAmount=?,J_Amount=?,J_SAmount=?,M_Amount=?,M_SAmount=?,Branch_Code=? WHERE Bud_Code=?";

	public static final String BUDGET_Select_SAmount="SELECT Bud_Code,SUM(Accepted_Price) AS bamount FROM document_budgetview GROUP BY Bud_Code";
	
	public static final String BUDGET_Update_SAmount="UPDATE budget_mas SET Bud_SAmount=? WHERE Bud_Code=?";
	
	public static final String BUDGET_Update_AmountALL="";
	
	//Group
	
	
	public static final String NEW_GROUP_MAX="select max(Group_code) as maxno from group_mas";
	
	public static final String GROUP_SEARCH="select * from group_mas where Group_code=? ";
	
	public static final String GROUP_INTERFACE="select Group_code from member_mas where Group_code=?";
	
	public static final String GROUP_NAME_INTERFACE="select Group_code from group_mas where Group_Name=? ";
	
	public static final String GROUP_DELETE="delete from group_mas where Group_code =?";	
	
	//public static final String GROUP_SAVE="insert into group_mas(Group_Code,Group_Name,GEligible,BEligible,BBEligible,BVEligible,NBEligible,JEligible,SEligible,REligible,PEligible,TEligible,GLPeriod,BLPeriod,BBLPeriod,BVLPeriod,NBLPeriod,JLPeriod,SLPeriod,RLPeriod,PLPeriod,TLPeriod,Remarks,Renewal,STATUS,Branch_Code,GRPeriod,BRPeriod,BBRPeriod,BVRPeriod,NBRPeriod,JRPeriod,SRPeriod,RRPeriod,PRPeriod,TRPeriod,Reserve_Book_Max,DeptEligible,DeptPeriod,DeptRPeriod,Dept_BranchCode) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String GROUP_SAVE="insert into group_mas(Group_Code,Group_Name,GEligible,BEligible,BBEligible,BVEligible,NBEligible,JEligible,SEligible,REligible,PEligible,TEligible,GLPeriod,BLPeriod,BBLPeriod,BVLPeriod,NBLPeriod,JLPeriod,SLPeriod,RLPeriod,PLPeriod,TLPeriod,Remarks,Renewal,STATUS,Branch_Code,GRPeriod,BRPeriod,BBRPeriod,BVRPeriod,NBRPeriod,JRPeriod,SRPeriod,RRPeriod,PRPeriod,TRPeriod,Reserve_Book_Max) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	//public static final String GROUP_UPDATE="update group_mas set Group_Name=?,GEligible=?,BEligible=?,BBEligible=?,BVEligible=?,NBEligible=?,JEligible=?,SEligible=?,REligible=?,PEligible=?,TEligible=?,GLPeriod=?,BLPeriod=?,BBLPeriod=?,BVLPeriod=?,NBLPeriod=?,JLPeriod=?,SLPeriod=?,RLPeriod=?,PLPeriod=?,TLPeriod=?,Remarks=?,Renewal=?,STATUS=?,Branch_Code=?,GRPeriod=?,BRPeriod=?,BBRPeriod=?,BVRPeriod=?,NBRPeriod=?,JRPeriod=?,SRPeriod=?,RRPeriod=?,PRPeriod=?,TRPeriod=?,Reserve_Book_Max=?,DeptEligible=?,DeptPeriod=?,DeptRPeriod=?,Dept_BranchCode=? where Group_Code=? ";
	
	public static final String GROUP_UPDATE="update group_mas set Group_Name=?,GEligible=?,BEligible=?,BBEligible=?,BVEligible=?,NBEligible=?,JEligible=?,SEligible=?,REligible=?,PEligible=?,TEligible=?,GLPeriod=?,BLPeriod=?,BBLPeriod=?,BVLPeriod=?,NBLPeriod=?,JLPeriod=?,SLPeriod=?,RLPeriod=?,PLPeriod=?,TLPeriod=?,Remarks=?,Renewal=?,STATUS=?,Branch_Code=?,GRPeriod=?,BRPeriod=?,BBRPeriod=?,BVRPeriod=?,NBRPeriod=?,JRPeriod=?,SRPeriod=?,RRPeriod=?,PRPeriod=?,TRPeriod=?,Reserve_Book_Max=? where Group_Code=? ";
	
	public static final String GROUP_GEN_SEARCH_LIKE="select group_code,group_name,remarks from group_mas where 2>1 and  group_name like '";
	
	public static final String GROUP_GEN_SEARCH="select group_code,group_name,remarks from group_mas where 2>1 and  status='V1' ";
	
	public static final String GROUP_SPE_SEARCH="select group_code,group_name,remarks from group_mas where 2>1 and  status='V2' ";
	
	public static final String GROUP_GS_SEARCH="select group_code,group_name,remarks from group_mas where 2>1 and  status='V3' ";
	
	
	//Login
	
	public static final String LOGIN_SELECT_STRING = "select * from login_mas where Login_ID =? ";
		
	public static final String LOGIN_INSERT_STRING = "insert into login_mas values(?,?,?,?,?,?,?)";
				
	public static final String LOGIN_UPDATE_STRING = "update login_mas set Login_Password=?,Staff_Name=?,User_Rights=?,Login_Flag=?,Answare=? where Login_ID =?";  // For Titan
	
 	public static final String LOGIN_UPDATE_withBranchcode_STRING = "update login_mas set Login_Password=?,Staff_Name=?,User_Rights=?,Login_Flag=?,Branch_Code=?,Answare=? where Login_ID =? AND branch_code=? ";
	
	public static final String LOGIN_BRANCH_UPDATE_STRING = "update login_mas set Staff_Name=?,Branch_Code=?,Answare=? where Login_ID =? and branch_code=? ";
	
	public static final String LOGIN_DELETE_STRING = "delete from login_mas where Login_ID =? and branch_code=? ";
		
	public static final String LOGIN_Same_Name ="select Login_Password,Login_Id from login_mas where Login_Id=?";
			
	public static final String SEARCH_LOGIN_NAME_LIKE ="select * from login_mas where 2>1 and login_id like '";
	
	public static final String SEARCH_LOGIN_NAME ="select * from login_mas where 2>1 ";
	
	
	
	
	public static final String LOGIN_NEWPWD_UPDATE = "update login_mas set Login_Password=? where Login_ID =?";
	
	
	
	//Stock
	public static final String COUNT_STOCK_MAS ="select count(*)  from stock_report_book where doc_type=?";
	public static final String COUNT_STOCK_YES ="select count(*) from stock_view_yes where doc_type=?";
	public static final String COUNT_STOCK_MISSING ="select count(*) from stock_view_missing where doc_type=?";		
	public static final String COUNT_STOCK_LOST ="select count(*) from stock_view_lost where doc_type=?";
	public static final String COUNT_STOCK_WITHDRAWN ="select count(*) from stock_view_withdrawn where doc_type=?";
	public static final String COUNT_STOCK_ISSUED ="select count(*) from stock_view_issued where doc_type=?";
	public static final String COUNT_STOCK_BINDING ="select count(*) from stock_view_binding where doc_type=?";
	public static final String COUNT_STOCK_DAMAGED ="select count(*) from stock_view_damaged where doc_type=?";
	public static final String COUNT_STOCK_TRANSFER ="select count(*) from stock_view_transfer where doc_type=?";
	public static final String COUNT_STOCK_VERIFY_ISSUED ="select count(*) from stock_view_verify_issue where doc_type=?";
	
	public static final String COUNT_STOCK_MAS_ALL ="select count(*)  from stock_report_book";
	public static final String COUNT_STOCK_YES_ALL ="select count(*) from stock_view_yes";
	public static final String COUNT_STOCK_MISSING_ALL ="select count(*) from stock_view_missing";	
	public static final String COUNT_STOCK_LOST_ALL ="select count(*) from stock_view_lost";
	public static final String COUNT_STOCK_WITHDRAWN_ALL ="select count(*) from stock_view_withdrawn";
	public static final String COUNT_STOCK_ISSUED_ALL ="select count(*) from stock_view_issued";
	public static final String COUNT_STOCK_BINDING_ALL ="select count(*) from stock_view_binding";
	public static final String COUNT_STOCK_DAMAGED_ALL ="select count(*) from stock_view_damaged";
	public static final String COUNT_STOCK_TRANSFER_ALL ="select count(*) from stock_view_transfer";
	public static final String COUNT_STOCK_VERIFY_ISSUED_ALL ="select count(*) from stock_view_verify_issue";
	
	public static final String STOCK_SELECT="select * from stock_mas where access_no=?";
	
	public static final String STOCK_SAVE="insert into stock_mas (access_no)values(?)";
	
	public static final String STOCK_DELETE="delete from stock_mas";
	
	public static final String STOCK_DELETE_SINGLE="delete from stock_mas where access_no=?";
	
	public static final String STOCK_SELECT_BOOK="select availability from book_mas where access_no=?";
	
	//Journal
	
	public static final String JOURNAL_MAX ="select max(jnl_code) as maxno from journal_mas";
	
	public static final String JOURNAL_SEARCH ="select * from journal_mas where Jnl_Code=?";
	
	public static final String JOURNAL_SEARCH_PUB ="select publisher from publisher_mas where sp_code=?";
	
	public static final String JOURNAL_SEARCH_SUB ="select sub_name from subject_mas where sub_code=?";
	
	public static final String JOURNAL_SEARCH_DEPT ="select dept_name from department_mas where dept_code=?";
	
	public static final String JOURNAL_INTERFACE ="select * from journal_issues where Jnl_Code=?";
	
	public static final String JOURNAL_DELETE ="delete from journal_mas where Jnl_Code=?";	
	
	public static final String JOURNAL_INSERT ="insert into journal_mas(Jnl_Code,Jnl_Name,ISSN,Frequency,Sub_Code,Dept_Code,Pub_Code,Country,LANGUAGE,Deli_Mode,Jnl_Type,Remarks,Doc_Type,Branch_Code) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String JOURNAL_SAVE_DEPT ="select dept_code from department_mas where dept_name=?";
	
	public static final String JOURNAL_SAVE_PUB ="select sp_code from publisher_mas where publisher=?";
	
	public static final String JOURNAL_SAVE_SUB ="select sub_code from subject_mas where sub_name=?";
	
	public static final String JOURNAL_UPDATE ="update journal_mas set Jnl_Name=?,ISSN=?,Frequency=?,Sub_Code=?,Dept_Code=?,Pub_Code=?,Country=?,LANGUAGE=?,Deli_Mode=?,Jnl_Type=?,Remarks=?,Doc_Type=?,Branch_Code=? where Jnl_Code=? ";
	
	public static final String JOURNAL_SEARCH_NAME_LIKE ="select Jnl_Code,Jnl_Name,doc_Type from journal_mas where 2>1 and jnl_name like '";
	
	public static final String JOURNAL_SEARCH_NAME ="select Jnl_Code,Jnl_Name,doc_Type from journal_mas where 2>1 ";
	
	public static final String JOURNAL_SEARCH_Name ="select * from journal_mas where jnl_Name=?";
	
	
	//Journal Issues
	
			public static final String JOURNAL_ISSUES_MAX ="select MAX(issue_access_no) as maxno from journal_issues";
			
			//public static final String SELECT_STRING_Jnlissues ="select * from journal_issues where jnl_code=? order by issue_access_no";
			public static final String SELECT_STRING_Jnlissues ="select * from journal_issues where 2>1 and jnl_code=? and issue_date between? and ? ";
			
			public static final String SELECT_STRING_Jnlissues_ACCNO ="select * from journal_issues where issue_access_no=? ";
			
			/*public static final String JNL_ISSUES_INSERT_STRING =
				"INSERT INTO journal_issues( Issue_Access_No, Jnl_Code, Issue_year, Issue_Month, Issue_Volume,"+
			 	"Issue_No, Issue_Date, Received_Date, Availability, Remarks, content_page)"+
		 		"VALUES (?,?,?,?,?,?,?,?,?,?,?)";*/
			
			
			public static String JNL_ISSUES_INSERT_STRING =
											"INSERT INTO journal_issues"
											+ "(issue_access_no,jnl_code,subs_no,issue_year,issue_month,issue_volume,issue_no,"
											+ "part_no,issue_date,expected_date,received_date,availability,bvol_no,remarks,"
											+ "issue_amount,content_page) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			
			
			
			public static final String JNL_ISSUES_UPDATE_STRING ="UPDATE journal_issues SET  Jnl_Code =?, Issue_year =?, Issue_Month=?, Issue_Volume =?, Issue_No=?,Issue_Date=?,Received_date=?,Availability =?,Remarks=?,content_page=? WHERE Issue_Access_No=?";
			
			
			public static final String JOURNAL_SEARCH_CODE ="select jnl_code from journal_mas where Jnl_name=?";
			
			public static final String SELECT_STRING_Issues ="select * from journal_issues where jnl_code=? and issue_access_no=?";
			
			public static final String JNL_ISSUES_DELETE_STRING ="delete from journal_issues where jnl_code=? and issue_access_no=?";
	
	//Journal Atl
	
	public static final String JOURNAL_ATL_MAX ="select max(atl_no) as maxno from journal_articles";	
	
	public static final String JOURNAL_ATL_SEARCH ="select * from journal_articles where atl_no=?";
	
	public static final String JOURNAL_ATL_DELETE ="delete from journal_articles where atl_no=?";
	
	public static final String JOURNAL_ATL_SAVE ="insert into journal_articles(atl_no,jnl_code,jnl_name,bvol_no,atl_title,atl_author,vol_no,issue_no,issue_year,issue_month,atl_page_nos,atl_subject,atl_keywords,atl_abstract) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String JOURNAL_ATL_UPDATE ="update journal_articles set jnl_code=?,jnl_name=?,bvol_no=?,atl_title=?,atl_author=?,vol_no=?,issue_no=?,issue_year=?,issue_month=?,atl_page_nos=?,atl_subject=?,atl_keywords=?,atl_abstract=? where atl_no=?";

	
    //	Journal Enquiry Processing
	
	public static final String JOURNAL_EnquiryNo_Search ="SELECT sup_code,sp_name,enq_no FROM jnl_enquiry_fullview where 2>1 ";
	
    //	Journal Order Processing
	
	public static final String JOURNAL_OrderNo_Search ="SELECT sup_code,sp_name,jnl_order_no FROM jnl_order_fullview where 2>1 ";
	
	//  Journal Invoice Processing
	
	public static final String JNL_INV_MAX ="SELECT max(Inv_Ref_No) as maxno from journal_invoice_mas";
	
	public static final String JOURNAL_CheckList ="SELECT Jnl_Code,Jnl_Name,Doc_Type,Frequency FROM journal_mas where 2>1 ";
		
	//  Journal Payment Processing
	
	public static final String JNL_Payment_MAX ="SELECT max(Payment_Ref_No) as maxno from journal_Payment_mas";
	public static final String JNL_Invoice_CheckList ="SELECT invoice_no,sp_name,sup_code,invoice_amount FROM jnl_invoice_fullview where 2>1 ";
	public static final String JNL_Invoice_DelUpdate ="SELECT invoice_no FROM journal_invoice_mas WHERE payment_ref_no=? ";
	public static final String JNL_CheckPaidInvoice ="SELECT Payment_Ref_No FROM jnl_payment_fullview WHERE 2>1 ";
	
	
	//Journal Supplier Invoice Processing
	public static final String JNL_SupInv_CheckList ="SELECT Jnl_Order_No,jnl_order_date,sup_code,sp_name,jnl_code,jnl_name,subs_from,subs_to,vol_no,no_of_issues,currency,cost,inr_price,discount,accepted_price,Frequency FROM jnl_order_fullview  WHERE 2>1 ";
	public static final String JNL_SupInv_UpdatePendingJNL ="update journal_order_details set flag='process' WHERE 2>1 ";
	public static final String JNL_SupInv_Updatejnl ="update journal_order_details set flag='completed' WHERE 2>1 ";
	public static final String JNL_SupInv_SaveUpdateChk ="select jnl_code from journal_supinvoice_details WHERE sup_inv_no=? ";
	

	
	
	// Simple search
	
	public static final String SQL_Query_view =	"select  * from full_search where 2>1";
		
	// OrderInvProcessing
	
	public static final String ORDERSELECT_NEW ="select max(sno) as maxno from or_inv_pay";
	
	public static final String ORDERSELECT_SEARCH =
		"select a.Sno, a.Ord_no, ord_date,"+
	" a.Sup_code, a.Bud_Code, a.Inv_No, inv_date , a.Amount, a.Dept_Code,"+
	" a.Year, a.Doc_Type, a.Cr_Deb, a.paid,  pay_date, a.Mode, a.Pay_Details,"+
	" a.Add1, a.add2,b.sp_code,b.sp_name ,c.dept_code,c.dept_name,d.bud_code,d.bud_head from " +
    " or_inv_pay a,sup_pub_mas b,department_mas c,budget_mas d where sno = ? and a.sup_code=b.sp_code and a.bud_code=d.bud_code and a.dept_code=c.dept_code";
	
	public static final String ORDERSELECT_STRING =	"select * from or_inv_pay where sno = ?";
	
	public static final String ORDERSELECT_INSERT ="INSERT INTO or_inv_pay ( Sno, Ord_no, Ord_Date, Sup_code, Bud_Code, Inv_No, Inv_Date, Amount, Dept_Code,Year, Doc_Type, Cr_Deb, paid, Pay_date, Mode, Pay_Details, Add1, add2)  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String ORDERSELECT_UPDATE =	"UPDATE or_inv_pay SET  Ord_no=?,Ord_Date = ?,Sup_code= ?, Bud_Code= ?,Inv_No= ?,Inv_Date= ?, Amount= ?,Dept_Code= ?,Year= ?, Doc_Type= ?,Cr_Deb= ?, paid= ?, Pay_date= ?, Mode= ?, Pay_Details= ?, Add1= ?, add2= ? WHERE  Sno =?";
	
	public static final String ORDERSELECT_DELETE =	"delete from or_inv_pay where sno= ?";
	
	public static final String ORDER_SEARCH_Like ="select sno,ord_no,ord_date from or_inv_pay where 2>1 and sno like'";
	
	public static final String ORDER_SEARCH ="select sno,ord_no,ord_date from or_inv_pay where 2>1";
	
	public static final String ORDER_INV_SEARCH_Like ="select * from invoice_mas where 2>1 and inv_no like'";
	
	public static final String ORDER_INV_SEARCH ="select * from invoice_mas where 2>1";
	
	public static final String ORDER_SUP_NAME ="select supplier from supplier_mas where sp_code=?";

	
	// Acquisition  ---> Strat Indent Master
	
	public static final String MEMBERINDENTSELECT_STRING="select member_code,member_name,dsname from member_view";
	public static final String Indent_Title_No="select max(Title_No) as Title_No from indent_details_mas";
	public static final String Indent_Distinct_Title="SELECT DISTINCT(title) as title FROM book_mas";
	public static final String Indent_List="SELECT Member_Code,Member_Name,Indent_No FROM indent_details_mas ";
	
	// Indent Approval Form
	
	public static final String Indent_Approve_Select ="SELECT Indent_No,Title_Status,indent_Date,Approve_Date,Member_Code,Member_Name,Indent_Status,Title_No,Title,No_Of_Copy,Author_Name,Author_Code,Approved_Copy,Pending_Copy FROM indent_details_mas  WHERE Indent_No=:indtno";
	public static final String Indent_Approve_Update ="UPDATE indent_details_mas SET Title_Status='APPROVED',Indent_Status='APPROVED',Approve_Date=:appDate,Approved_Copy=:appCopy,Pending_Copy=:pndCopy where Title_No=:titleno and Indent_No=:indtno";
	public static final String Indent_NotApprove_Update ="UPDATE indent_details_mas SET Title_Status='PENDING',Indent_Status='PENDING',Approve_Date=:appDate,Approved_Copy=:appCopy,Pending_Copy=:pndCopy where Title_No=:titleno and Indent_No=:indtno";
	
    // Indent Order Form
	
	public static final String Indent_Order_CheckList ="SELECT Title_No,Title,Author_Name,Approved_Copy,Indent_No FROM indent_details_mas WHERE Title_Status='APPROVED' ";
	public static final String Indent_OrderDelete_Update ="UPDATE indent_details_mas SET Title_Status='APPROVED',Indent_Status='APPROVED' WHERE 2>1 ";	
	public static final String Indent_OrderNo="SELECT Order_No,Sup_Code,Supplier FROM indent_order_fullview WHERE 2>1 ";
	
    // Indent Invoice Form
	
	public static final String Indent_Inv_CheckList ="SELECT Title_no,Title,Author_Name,Copies,order_no,supplier,sup_code,Indent_No FROM indent_order_fullview  WHERE 2>1 ";
	public static final String Indent_Inv_DeleteUpdate ="UPDATE indent_details_mas SET Title_Status='ORDERED',Indent_Status='ORDERED' WHERE 2>1 ";

    // Indent Payment Form
	
	public static final String Indent_Payment_MAX ="SELECT max(Payment_Ref_No) as maxno from indent_Payment_mas";
	public static final String Indent_Invoice_CheckList ="SELECT invoice_no,supplier,sup_code,Inv_Tot_Amount FROM indent_summary_payment where 2>1 ";
	public static final String Indent_Invoice_DelUpdate ="SELECT invoice_no FROM indent_invoice_details WHERE payment_ref_no=? ";
	public static final String Indent_CheckPaidInvoice ="SELECT Payment_Ref_No FROM indent_payment_fullview WHERE 2>1 ";
	
	
	
	//Transfer Books
	
	public static final String TRANSFER_DEPT_LOAD ="select * from department_mas where 2>1 ";
	
	public static final String AccessNo_Select_Integer ="select access_no,title from book_mas where access_no REGEXP '^[0-9]+$' and branch_code=:branch_code and document=:document and availability in('YES','REFERENCE') and CONVERT(access_no,SIGNED) between :fromAccNo and :toAccNo ORDER BY CONVERT(access_no,SIGNED) ASC";
	
	public static final String AccessNo_Select_Integer_new ="select access_no,title from book_mas where access_no not in (select access_no from transfer_mas where Recovery='') and access_no REGEXP '^[0-9]+$' and branch_code=:branch_code and document=:document and availability in('YES','REFERENCE') and CONVERT(access_no,SIGNED) between :fromAccNo and :toAccNo ORDER BY CONVERT(access_no,SIGNED) ASC";
	
	public static final String AccessNo_Select_Char ="select access_no,title from book_mas where access_no not REGEXP '^[0-9]+$' and branch_code=:branch_code and document=:document and availability in('YES','REFERENCE') ";
	
	public static final String AccessNo_Transfer_Select ="SELECT B.Access_No,Title,Author_Name FROM Book_Mas AS B,Transfer_Mas T WHERE B.Access_No=T.Access_No AND T.Branch_Code=:branch_code AND T.Recovery=''";
	
	public static final String AccessNo_Transfer_Select_acc ="SELECT B.Access_No,Title,Author_Name FROM Book_Mas AS B,Transfer_Mas T WHERE B.Access_No=T.Access_No AND T.Branch_Code=:branch_code AND T.dept_code=:dept_code AND T.Recovery='' and T.access_no between :fromAccNo and :toAccNo ORDER BY CONVERT(T.access_no,SIGNED) ASC";
	
	
	
	
	//public static final String TRANSFER_SELECT ="select * from transfer_mas where access_no=? and recovery=''";
	public static final String TRANSFER_SELECT ="select * from transfer_view where access_no=? and branch_code=? and recovery=''";
	
	public static final String INSERT_TRANSFER_MAS="insert into transfer_mas(order_no,access_no,branch_code,dept_code,doc_type,order_date,recovery,recovery_date) values(?,?,?,?,?,?,?,?)";
	
	public static final String INSERT_BULK_TRANSFER_MAS="insert into transfer_mas(access_no,branch_code,dept_code,doc_type,order_date,recovery,recovery_date)";
	
	public static final String UPDATE_BOOK_STATUS_TRANSFER="update book_mas set location=?,branch_code=?,availability='TRANSFERED' where  access_no=?";
	
	public static final String UPDATE_bulk_BOOK_STATUS_TRANSFER="update book_mas set location=?,branch_code=? where ";
	
	public static final String TRANSFER_DEPT_NAME ="select * from department_mas where dept_code=? ";
	
	public static final String TRANSFER_BRANCH_NAME ="select * from branch_mas where branch_code=?";
	
	public static final String TRANSFER_ORD_Code = "select max(order_no) as maxno from transfer_mas";
	
	public static final String TRANSFER_VIEW = "select * from transfer_view where recovery=''";
	
	public static final String UPDATE_BOOK_STATUS_TRANSFER_RETURN="update book_mas set availability='YES',location='',branch_code=? where access_no=?";
	
	public static final String UPDATE_BOOK_STATUS_bulk_TRANSFER_RETURN="update book_mas set availability='YES',location='',branch_code=? where ";
	
	public static final String UPDATE_TRANSFER_MAS_RETURN="update transfer_mas set recovery='YES',recovery_date=? where  access_no=?";
	
	public static final String UPDATE_bulk_TRANSFER_MAS_RETURN="update transfer_mas set recovery='YES',recovery_date=? where ";
	
	//	OrderInvProcessing
	
	//public static final String JNL_ISS_SELECT_NEW =
	//"select max(issue_access_no) as maxno from journal_issues where jnl_code=?";
	public static final String JNL_ISS_SELECT_NEW =	"select count(*) as maxno from journal_issues where jnl_code=?";
	
	
	public static final String EMPTY_STRING="";
	
	//Search
	public static final String SARECH_COUNT_DOC ="SELECT COUNT(*) as doc_count,document FROM full_search GROUP BY document";
	
	public static final String SARECH_COUNT_DOC_LIKE ="SELECT COUNT(*) as doc_count,document FROM full_search where 2>1 ";
	
	
	//Account
	//public static final String ACCOUNT_CHECK ="SELECT *  FROM login_mas where user_rights='7' and login_id=? and login_password=? ";
	
	public static final String ACCOUNT_CHECK ="SELECT *  FROM login_mas where login_id=? and login_password=? ";
	
	public static final String ACCOUNT_ISSUE_COUNT ="SELECT count(*)as issuecount  FROM issue_mas where member_code=? ";
	
	public static final String ACCOUNT_Branch_ISSUE_COUNT ="SELECT count(*)as issuecount  FROM issue_mas where member_code=? and branch_code=? ";
	
	
	public static final String ACCOUNT_RETURN_COUNT ="SELECT count(*) as returncount FROM issue_history where member_code=? ";
	
	public static final String ACCOUNT_RESERVE_COUNT ="SELECT count(*) as reservecount  FROM reserve_mas where member_code=? ";
	
	public static final String ACCOUNT_RETURN_COUNT_BranchWise ="SELECT count(*) as returncount FROM jissue_history where member_code=? ";
	
	
	
	
	public static final String Account_Fine_Details=" select * from fine_allview where 2>1 and member_code=? ";
	
	

	// For Online Reservation
	public static final String SELECT_ISSUEBOOKCheck ="SELECT member_code,member_name,access_no,title,due_date,doc_type FROM issuedbooks where access_no = :accessno and doc_type = :doctype";
	
	
	//Gate Resister
	public static final String ENTRY_GATERESISTER_CHECK ="SELECT member_code,entry_time,member_name,purpose FROM entry_log_view where member_code=? and branch_code=? ";
	//public static final String ENTRY_GATERESISTER_CHECK ="SELECT member_code,entry_time FROM entry_log where member_code=? ";
	
	public static final String ENTRY_GATERESISTER_DELETE ="delete FROM entry_log where member_code=? ";
	
	//public static final String ENTRY_GATERESISTER_RETURN ="insert into return_log values(?,?,?,?,?,?)";
	
	public static final String ENTRY_GATERESISTER_RETURN ="insert into return_log(member_code,entry_time,return_time,min_used,in_time,out_time,branchCode,purpose)values(?,?,?,?,?,?,?,?)";
	
	
	public static final String ENTRY_GATERESISTER_Enter ="insert into entry_log values(?,?)";
	
//==================NewsCllipings================
	
	public static final String NEWSSELECT_NEW ="select max(newspaper_code) as maxno from newsclipping_mas";
	
	public static final String SELECT_NEWSMAS="select * from newsclipping_mas where newspaper_code=? and branch_code=? ";
	

	public static final String Delete_NEWSMAS="delete from newsclipping_mas where newspaper_code=?";

//	==================EResourse================
	
	public static final String EResourse_NEW ="select max(code) as maxno from web_mas";
	
	public static final String selectEResourse_NEW ="select *  from web_mas where code=? AND branch_code=? ";
	
	public static final String Delete_EResource="delete from web_mas where code=?";
	
//	==================MResourse================
	
	public static final String selectMResourse_NEW ="select *  from missing_mas where access_no=?";
	
	public static final String selectBookResourse_NEW ="select *  from Book_mas where access_no=? and document=?";
	
	public static final String MResourceINSERT_STRING="insert into missing_mas(access_no,doc_type,status,mdate) values(?,?,?,?)";
	
	public static final String MResourceSelect_STRING="Select * from missing_mas where access_no=? ";
	
	public static final String MResourceDelete_STRING="Delete from missing_mas where access_no=? ";
	
	public static final String MResourceUpdate_STRING="Update book_mas set availability='YES' where access_no=? ";

	public static final String selectBookResourse_Update ="update book_mas set availability=? where access_no=?";
	
//	Fine Details
	public static final String Fine_Trans_mas ="insert into trans_mas(Trans_Date,Trans_Head,member_Code,Access_No,Issue_Date,Due_Date,Trans_Amount,Remarks,branch_code) values(?,?,?,?,?,?,?,?,?)";
	
	public static final String Fine_Payment_Clearance ="insert into payment_clearance(Bill_No,Member_Code,Amount,Payment_Date,Pay_mode,staff_code,Access_No,Doc_type,branch_code) values(?,?,?,?,?,?,?,?,?)";
	
	public static final String Max_BillNo_Payment="select max(Bill_No)+1 as billno from payment_clearance where 2>1";
	
	public static final String Delete_User_Payment ="delete from payment_clearance where bill_no=? and member_code=? and branch_code=? ";
	
	
//	Payment Details for user
	public static final String Tot_Trans_Amt ="SELECT SUM(trans_amount) AS Total_Amt FROM trans_book_view WHERE member_code=? ";
		
	public static final String Tot_Paid_Amt ="SELECT SUM(amount) AS Paid_Amt FROM payment_clearance WHERE member_code=? ";
	
	public static final String select_trans ="SELECT * FROM trans_mas WHERE member_code=? order by trans_no";
	
	public static final String select_transBook_view ="SELECT * FROM trans_book_view WHERE member_code=? and branch_code=? order by trans_no";
	
	
	
	
	public static final String select_payment ="SELECT * FROM payment_clearance WHERE member_code=? and branch_code=? order by bill_no";
	
	public static final String select_payment_view ="SELECT * FROM payment_clearance WHERE member_code=? order by bill_no";
	
	
	public static final String SelectIssueHisotry="select * from issue_history where 2>1 and member_code=? order by return_date desc ";
	
	
	
	
	
	
	
//	Contents Upload
	public static final String Content_BookMas ="SELECT Access_no FROM book_mas WHERE Access_No=:accno and Document=:document ";
	public static final String Content_Update_BookMas ="UPDATE book_mas SET Contents=:contents WHERE Access_No=:accno and Document=:document ";

	public static final String Content_JNLIssueMas ="SELECT Issue_Access_No FROM journal_issues WHERE Issue_Access_No=:accno ";	
	public static final String Content_Update_JNLIssueMas ="UPDATE journal_issues SET content_page=:contents WHERE Issue_Access_No=:accno ";
		
	public static final String Content_NewsClipMas ="SELECT Newspaper_code FROM newsclipping_mas WHERE Newspaper_code=:accno ";	
	public static final String Content_Update_NewsClipMas ="UPDATE newsclipping_mas SET Newspaper_content=:contents WHERE Newspaper_code=:accno ";	

	public static final String Content_JNLArticleMas ="SELECT Atl_No FROM journal_articles WHERE Atl_No=:accno ";	
	public static final String Content_Update_JNLArticleMas ="UPDATE journal_articles SET Atl_Abstract=:contents WHERE Atl_No=:accno ";	
	
	public static final String Content_QUSTIONBANK_Mas ="SELECT QB_Code FROM question_bank WHERE QB_Code=:accno ";
	public static final String Content_Update_QUSTIONBANK ="UPDATE question_bank SET Contents=:contents WHERE QB_Code=:accno ";
	public static final String Content_EBOOK_Mas ="SELECT access_no FROM ebook_mas WHERE access_no=:accno ";
	public static final String Content_Update_EBOOK ="UPDATE ebook_mas SET Content=:contents WHERE access_no=:accno ";
	
	
	
//  Transaction mas Query	

	public static final String TransMasMaxCode = "select max(Trans_Code) as maxno from transaction_mas";
	public static final String TransHeadSelect = "SELECT Trans_Head,Amount FROM transaction_mas";
	
//  ---------------------- Miscellaneous & Trans_Mas Master ----------				

	public static final String MiscellaneousMaxCode = "select max(Ref_No) as maxno from miscellaneous_mas";
	public static final String Mis_TransMasMaxCode = "select max(Trans_No) as maxno from trans_mas";
	public static final String Trans_SelectMember = "SELECT member_code,member_name,cname,cmajor FROM member_view where member_code=?";
	public static final String MiscellaneousTransNo = "select Trans_No,Payment_No,Pay_Flag from miscellaneous_mas where Ref_No=?";

	
// Member Transfer Query
		
	//public static final String Member_Transfer_Select ="select member_Code,member_name from member_mas where dept_code=:deptCode and course_code=:courseCode and group_code=:groupCode and cyear=:courseYear ";	
	
	
	
	public static final String Member_Transfer_Select ="select member_Code,member_name,cyear from member_mas where 2>1   ";
	
	
	
	
	public static final String Member_Transfer_SaveQurey ="update member_mas set cyear=:courseYear where 2>1 ";
				
	//---------------------------Bulk Member Update----------------------------
	
	
	public static final String BulkMemberUpdate_Desig = "select desig_code,desig_name from designation_mas where 2>1 ";
	
	public static final String BulkMemberUpdate_Dept = "select dept_code,dept_name from department_mas where 2>1 ";
	
	public static final String BulkMemberUpdate_Group = "select group_code,group_name from group_mas where 2>1 ";
	
	public static final String BulkMemberUpdate_Course = "select course_code,course_major from course_mas where 2>1 ";
	
	//public static final String BulkMemberUpdate_Slock = "select distinct slock,slock from member_mas where 2>1 ";
	
	public static final String BulkMemberUpdate_Slock = "select distinct slock,slock from tbl_slock where 2>1 ";
	
	//public static final String BulkMemberUpdate_CourseYear = "select distinct cyear,cyear from member_mas where 2>1 ";
		
	public static final String BulkMemberUpdate_CourseYear = "select distinct cyear,cyear from tbl_year where 2>1 ";
	
	public static final String BulkMemberUpdate_Division = "select branch_code,branch_name from branch_mas where 2>1 ";
	
	public static final String BulkMemberUpdate_Sex = "select distinct sex,sex from member_mas where 2>1 ";
	
	public static final String BulkMemberUpdate_Save_Query = "update member_mas set ";
	
	
// Bulk Updation Query	
		
	public static final String BUpdate_Author = "select author_code,author_name from author_mas where 2>1 ";
	public static final String BUpdate_Department = "select dept_code,dept_name from department_mas where 2>1 ";
		
	public static final String BUpdate_Supplier = "select sp_code,sp_name from sup_pub_mas where 2>1 and sp_type='SUPPLIER' ";
	public static final String BUpdate_Publisher = "select sp_code,sp_name from sup_pub_mas where 2>1 and sp_type='PUBLISHER' ";
		
	public static final String BUpdate_Subject = "select Sub_code,Sub_name from subject_mas where 2>1 ";	
	public static final String BUpdate_Budget = "select bud_code,bud_head from budget_mas where 2>1 ";
		
	public static final String BUpdate_Title = "select DISTINCT title,title from book_mas where 2>1 ";
	public static final String BUpdate_Edition = "select DISTINCT edition,edition from book_mas where 2>1 ";
		
	public static final String BUpdate_YearPub = "SELECT DISTINCT year_pub,year_pub from book_mas where 2>1 ";
	public static final String BUpdate_Keywords = "SELECT DISTINCT keywords,keywords FROM book_mas where 2>1 ";
		
	public static final String BUpdate_Language = "SELECT DISTINCT LANGUAGE,LANGUAGE FROM book_mas where 2>1 ";
	public static final String BUpdate_Location = "SELECT DISTINCT location,location FROM book_mas where 2>1 ";
		
	public static final String BUpdate_CallNo = "select DISTINCT call_no,call_no from book_mas where 2>1 ";
	public static final String BUpdate_VolNo = "select DISTINCT VolNo,VolNo from book_mas where 2>1 ";
		
	public static final String BUpdate_ISBN = "select DISTINCT ISBN,ISBN from book_mas where 2>1 ";
	public static final String BUpdate_Accno ="select access_no from book_mas ";
		
	public static final String BUpdate_AutIntSelect ="select author_code from author_interface ";
	public static final String BUpdate_AutInter ="update author_interface set author_code=:autcode ";
		
	public static final String BUpdate_Save_Query = "update book_mas set ";
	

//	--------------Question Bank ----------------------------
	
	public static final String QUESTION_BANK_NEW = "select max(QB_Code) as maxno from question_bank";
	public static final String QUESTION_BANK_DELETE = "delete from question_bank where QB_Code=?";
	public static final String SELECT_QBSTRING = "SELECT qb_code FROM question_bank WHERE qb_code= ?";
	public static final String SELECT_QUESTION_BANK = "select * from question_bank where QB_Code=? and branch_code=? ";
	public static final String SEARCH_QUESTION_BANK = "SELECT QB_Code,DATE,University,Sub_Name,Sub_Code,dept_name,course_name,course_major,YEAR,MONTH,semester,Add_Field1,Add_Field2,Contents,branch_name FROM  question_bank_fullview WHERE 2>1 ";
	public static final String SEARCH_QUESTION_BANK_FULLVIEW = "SELECT QB_Code,sub_code,sub_name,dept_name,course_name,course_major,MONTH,YEAR,semester,contents,branch_name FROM question_bank_fullview WHERE 2>1 ";
	public static final String QBSUBJECTSEARCHLIST = "SELECT QB_Code,QBSub_Code,QBSub_Name FROM QBSubject_Mas  WHERE 2>1 ";
	
	
	
	public static final String QBSubjectCodeSearchList = "SELECT distinct QBSub_Code as QBSub_Code,QBSub_Code as QBSub_Code FROM QBSubject_Mas  WHERE 2>1 ";
	
	
	public static final String QBSubjectNameSearchList = "select distinct qbsub_name as qbsub_name,qbsub_name as qbsub_name from qbsubject_mas  WHERE 2>1 ";
	
	
	
	

//---------------------QuestionBank Subject Mas---------------------------------------
	
	public static final String QBSUBJECTSQLStringCode = "select max(QB_code) as maxno from qbsubject_mas";	
	public static final String QBSUBJECTSELECT_STRING = "SELECT QB_Code,QBSub_Code,QBSub_Name,QBSub_Desc,Branch_Code FROM QBSubject_Mas WHERE QB_Code=?";	
	public static final String QBSUBJECTDELETE_STRING = "delete from QBsubject_mas where QB_Code=?";
	public static final String QBSUBJECTSame_Name = "SELECT QB_Code,QBSub_Code,QBSub_Name FROM QBSubject_Mas where QBSub_Code=?";
	public static final String QBSUBJECTDeletecheck = "SELECT * FROM question_bank WHERE QB_Subject_Code= ?";
	public static final String QBSUBJECTSEARCH_NAME="select * from qbsubject_mas where 2>1";
	public static final String QBSUBJECTSEARCH_NAME_LIKE="select * from qbsubject_mas where 2>1 and QBSub_Name like '";
	
	
	
	public static final String QB_Sub_Code_Delete_Check="SELECT QB_Code,Sub_Code FROM Question_Bank WHERE Sub_Code IN(SELECT QBSub_Code FROM QBSubject_Mas WHERE QBSub_Code=?";
	public static final String QB_Sub_Name_Delete_Check="SELECT QB_Code,Sub_Name FROM Question_Bank WHERE Sub_Name IN(SELECT QBSub_Name FROM QBSubject_Mas WHERE QBSub_Name=?";
	
	
	
// QuestionBank Import
	
	 public static final String QBSubject_Name="SELECT COUNT(*) as cnt FROM qbsubject_mas WHERE qbsub_code= ? AND qbsub_name=?";
	 public static final String Select_QbCode="SELECT qb_code FROM qbsubject_mas WHERE qbsub_code=? and qbsub_name= ? limit 1";	
	public static final String Qbsubjectmas_Insert="Insert into qbsubject_mas (qb_code,qbsub_code,qbsub_name,qbsub_desc) values (?,?,?,?)";
	
	
	//----------------------Member Report-----------------------------------------
	public static final String distinctBranchwiseDesig="SELECT DISTINCT(desig_code) AS desig_code,Designation AS Desig FROM Member_Report_View WHERE 2>1 ";
	public static final String distinctBranchwise="SELECT DISTINCT(Branch_Code) AS Branch_Code,Branch_Name AS Branch_Name FROM Member_Report_View WHERE 2>1 ";
	public static final String distinctBranchwiseGroup="SELECT DISTINCT(Group_Code) AS Group_Code,Group_Name AS Group_Name FROM Member_Report_View WHERE 2>1 ";
	public static final String distinctBranchwiseCourse="SELECT DISTINCT(Course_Code) AS Course_Code,Course_Name AS Course_Name,Course_major AS Course_major FROM Member_Report_View WHERE 2>1 ";
	
	public static final String distinctBranchwiseDepartment="select distinct dept_code,dept_name from Member_Report_View WHERE 2>1 ";
	
	//-------------------------------------------QB DEPT-------------------------------------------------------
	
	public static final String QBDeptList = "select distinct dept_code,dept_name from department_mas where 2>1 ";
	
	//-------------------------------------------member Transfer-------------------------------------------------------
	
	public static final String MemberDeptList = "select distinct dept_code,dname from member_view where 2>1 ";
	public static final String MemberDesigList = "select distinct designation_code,dsname from member_view where 2>1 ";
	

	//-----------------------get year new arrival
	public static final String GetYear = "SELECT DISTINCT  LPAD(MAX(MONTH(RECEIVED_DATE)),2,0) AS max_month,MAX(YEAR(RECEIVED_DATE)) AS max_year FROM ACCESSION_REG WHERE 2>1 ";
	public static final String SubjectList = "SELECT DISTINCT s.sub_code,s.sub_name FROM subject_mas s INNER JOIN book_mas b ON s.sub_code=b.sub_code WHERE  received_date BETWEEN  DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR) AND CURDATE() ORDER BY sub_name";
	public static final String NewArrivalList = "SELECT access_no,author_name,sres,title,edition,isbn,call_no,sp_name,place,year_pub,sub_name,dept_name,notes,pages,location,received_date,document,contents,availability FROM new_arrivals_view WHERE 2>1 and  document=:document and received_date like :receivedDate and title like :title and author_name like :author_name and sub_name like :sub_name";
	//--------------message master------------------------
	
		public static final String MsgMasSQLStringCode ="select max(msg_code) as maxno from message_mas";
		public static final String MsgSELECT_STRING ="SELECT msg_code,lib_msg,whats_new,msg_date FROM message_mas where msg_code=? and branch_code=? ";
		public static final String MsgMasDELETE_STRING ="delete from message_mas where msg_code=? AND branch_code=? ";
		public static final String MsgMasSEARCH_NAME = "SELECT msg_code,lib_msg,whats_new,msg_date FROM message_mas WHERE 2>1 ORDER BY msg_date ";
		public static final String libraryMessage = "SELECT * FROM message_mas ORDER BY msg_date DESC LIMIT 1";
		
		//::::::::::::::::::::::::::::::::::::::::::::TagMerging::::::::::::::::::::::::::::
		
		public static final String Select_TAG =	"Select epc_id from rfid_tag where epc_id=?";
		public static final String INSERT_TAG =	"insert into rfid_tag(epc_id,access_no) values (?,?)";
		public static final String Update_TAG =	"Update rfid_tag set access_no=? where epc_id=?";
		public static final String Select_CARD =	"Select card_uid from rfid_card where card_uid=?";
		public static final String INSERT_CARD =	"insert into rfid_card(card_uid,member_code) values (?,?)";
		public static final String Update_CARD =	"Update rfid_card set member_code=? where card_uid=?";
		public static final String Select_tag_title =	"Select title from book_mas where access_no=?";
		public static final String Select_Card_Mname =	"Select member_name from member_mas where member_code=?";
		
		
		//-------------------------ebook_mas query------------------------------------
		
		public static final String EBOOKSQLStringCode = "select max(access_no) as maxno from ebook_mas";

		//public static final String EBOOKSELECT_STRING = "select access_no,call_no,received_date,title,edition,author_name,role,publisher,year,pages,subject,department,course,content from ebook_mas  WHERE access_no = ?";
	    public static final String EBOOKSELECT_STRING = "select access_no,title,author_name,call_no,role,edition,year_pub,pages,sub_code,Sub_Name,pub_code,publisher,dept_code,Dept_Name,branch_code,document,TYPE,received_date,content,price,invoice_no,invoice_date,url,sup_code,supplier,isbn,keywords,type,gift_purchase from ebook_mas_view WHERE access_no = ? and branch_code=? ";
		//public static final String EBOOKSELECT_STRING = "select * from ebook_mas where access_no=?";
		public static final String EBOOK_DELETE = "delete from ebook_mas where access_no=?";
		
		public static final String EBookSame_Name = "select author_name,access_no from ebook_mas where author_name=?";
		
		public static final String EBookSearchName_LIKE = "select access_no,price,title,author_name  from ebook_mas where 2>1 and title like '";
			
		public static final String EBookSearchName="SELECT access_no,price,title,author_name FROM ebook_mas where 2>1 ";
		
		public static final String EBOOKSELECT_STRING_AUTHORINTERFACE = "select access_no from author_interface where author_code=?";

		public static final String EBookCallNoSearch="SELECT call_no,subject FROM ebook_mas where 2>1 order by call_no";
		
		public static final String EBookCallNoSearch_LIKE = "select call_no,subject  from ebook_mas where 2>1 and call_no like '";
		
		public static final String SELECT_EBOOKSTRING ="select access_no from ebook_mas WHERE access_no = ?";
		
		public static final String SELECT_EBOOKBRANCH ="select branch_name from branch_mas where branch_name = ?";
		
		public static final String SUBJECTCALL_NOSEARCH_NAME="select * from subject_mas where 2>1 and call_no<>'' order by sub_name";
				
		public static final String MEMBERCLEAR_PHOTO="update  member_mas  set photo=NULL where member_code=? and branch_code=? ";
				
		public static final String Maxsups_date_query ="SELECT jnl_code,jnl_name,MAX(subs_from)AS subs_from,MAX(subs_to)AS subs_to FROM journal_subscription_view WHERE jnl_name=?";

}



