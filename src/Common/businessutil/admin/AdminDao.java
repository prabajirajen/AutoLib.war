package Common.businessutil.admin;
import java.util.List;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Holiday.Holidaybean;
import Lib.Auto.Book_Import.BookDataBean;
import Lib.Auto.Book_Import.BookDataMessage;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.BulkFineUpload.FineDataBean;
import Lib.Auto.BulkFineUpload.FineDataMessage;
import Lib.Auto.BulkUpdate.BulkUpdateMsgBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Login.LoginBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.Member_Import.MemberDataBean;
import Lib.Auto.Member_Import.MemberDataMessage;
import Lib.Auto.Miscellaneous.MiscellaneousBean;
import Lib.Auto.QB_Import.QBDataBean;
import Lib.Auto.QB_Import.QBDataMessage;
import Lib.Auto.RefDueDays.RefDaysBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.Stock.BulkStockData;
import Lib.Auto.Stock.StockBean;
import Lib.Auto.Stock.StockDataMessage;
import Lib.Auto.Suggestion.SuggestionBean;
import Lib.Auto.TransMas.TransMasBean;
public interface AdminDao {
	
	
	//Fine
	public Finebean findFineMax();	
	public Finebean findFineSearch(int code,int branchID);	
	public int findFineDelete(int code);	
	public int findFineSave(Finebean newbean);	
	public int findFineUpdate(Finebean newbean);	
	public List findFineSearchName(String code,int branchID);	
	public List findGroupSearchName(String code,int branchID);
	
	//Holiday
	
	public List findHolidaySearch();	
	public int findHolidayDeleteAll();	
	public int findHolidayDelete(String date);	
	public int findHolidaySave(Holidaybean newbean);
	
   //WeakEndHoliday
	public int findWeakEndHolidaySave(int code,int code1);
	public int findWeakEndHolidaySearch();
	
//  --------------------- Suggestion -----------------
	
	public List findSuggestionList(SuggestionBean sugBean);	

//  --------------------- Book Review -----------------
	
	public List findReviewList(ReviewBean revBean);	
	
	//Budget
	
	public BudgetBean findBudgetMax();	
	public BudgetBean findBudgetSearch(int code,int branchID);
	public int findBudgetInterface(String code);
	public int findBudgetDelete(String code);
	public BudgetBean findBudgetDeptSearchName(String code,int branchID);
	public BudgetBean findBudgetSearchName(String code,int branchID);
	public int findBudgetSave(BudgetBean newbean);	
	public int findBudgetUpdate(BudgetBean newbean);
	
	public BudgetBean findBudgetUpdateSAmount();
	public List findLoadBranchList(int branch);
	
	//Group
	
	public GroupBean findGroupMax();	
	public GroupBean findGroupSearch(String code,int branchID);
	public int findGroupInterface(String code);
	public int findGroupNameInterface(String code,int branchID);
	public int findGroupDelete(String code);
	public int findGroupSave(GroupBean newbean);	
	public int findGroupUpdate(GroupBean newbean);
	public List findGroupGenSearch(GroupBean newbean);
	
	//Login
	public LoginBean findLoginSearch(String code, int branch);
	public int findLoginDelete(String code,int branchCode);
	public int findLoginSave(LoginBean newbean);	
	public int findLoginUpdate(LoginBean newbean);
	public LoginBean findLoginSearchName(String name, int branch);
	
	//Stock
	
	public StockBean findStock(String code,int branch_code);
	public StockBean findStockAll(int branch_code);
	public StockBean findStockMasSave(String code,int branch_code);
	public int findStockMasDeleteAll(int branch_code);
	public List findStockMasDisplay(StockBean newbean);
	public int findStockDeleteSingle(String code,int branch_code);
	
	public int findBulkStockSave(List details,int branch_code);
	public StockDataMessage findCheckStockAccessNo(BulkStockData newbean);
	
    //  Book Data Import
	
	public BookDataMessage findCheckAccessNo(BookDataBean newbean);
	public int findImportBookData(List details);	
	
    // Member Data Import
	
	public MemberDataMessage findCheckMemberCode(MemberDataBean newbean);
	public int findImportMemberData(List details);
	
	public int getBranchCode(String branch);
	public int getGroupCode(String groupName,int branchID);
	public int getBudgetCode(String budget,int branchID);
	
	
    // Fine Data Import	
	public FineDataMessage findFineCheck(FineDataBean newbean);
	public int findImportFineData(List details);

    //  ---------------------- Transaction Master ----------	
	
	public TransMasBean findTransCodeMax();	
	public TransMasBean findTransMasSave(TransMasBean transBean);
	public int findTransMasUpdate(TransMasBean transBean);	
	public TransMasBean findTransMasSearch(int code);
	public int findTransMasDelete(int code);
	
	
   //  ---------------------- Miscellaneous & Trans_Mas Master ----------		
		
	public MiscellaneousBean findMiscellaneousMax();
	public MiscellaneousBean findMiscellaneousSave(MiscellaneousBean transBean);
	public MiscellaneousBean findMiscellaneousMember(String code, int branchID);
	public List findMiscellaneousMemberView(String name, int branchID);
	
	public List findMiscellaneousTHead();
	public MiscellaneousBean findTransMasPayment(MiscellaneousBean transBean);
	public MiscellaneousBean findMiscellaneousSearch(int code);
	public int findMiscellaneousDelete(int code);
	public int findMiscellaneousUpdate(MiscellaneousBean transBean);
	
//  ---------------------- QB DEPT ----------	
	
	public List findQBDepartmentList(MemberTransRefBean newBean);
	
//  ---------------------- Member Transfer ----------		
	
	public List findDepartmentList(MemberTransRefBean newBean);
	
	public List findDesignationList(MemberTransRefBean newBean);
	
	
	public List findCourseList(MemberTransRefBean newBean);
	public List findQBSubjectList(MemberTransRefBean newBean);
	
	public List findQBSubjectNameList(MemberTransRefBean newBean);
	
	
	public List findGroupList(MemberTransRefBean newBean);
	
	public List findMemberList(MemberTransRefBean newBean);
	public int findMemberTransfer(MemberTransRefBean newBean);

//  ---------------------- Bulk Updation ----------	
	public List findBulkUpdateList(String query,String flag, int branchID);
	public int findBulkUpdateSave(BulkUpdateMsgBean newBean);	
	
	
	
	
	
	//------------------Bulk Member Update----------------------
	
	
	public List findBulkMemberUpdateList(String query,String flag, int branchID);
	public int findBulkMemberUpdateSave(BulkUpdateMsgBean newBean);
	
//  ---------------------- Reference Book Due Days ----------	
		
	public RefDaysBean getRefDueDays();	
	public int getduedaysSave(RefDaysBean newBean);	
	
	
	//QB Data Import
	
	public QBDataMessage findCheckQBCode(QBDataBean newbean);
	public int findImportQBData(List details);
	
	public List findDepartmentList();
	public List findCourseList();
	public List findGroupList();
	public List findQBSubjectList();
	
	public List findDepartmentList(int branchID);
	public List findCourseList(int branchID);
	public List findGroupList(int branchID);
	public List findQBSubjectList(int branchID);
	
	
}