package Common.businessutil.admin;
import java.util.List;

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
public interface AdminService {
	
	
	//Fine
	public Finebean getFineMax();	
	public Finebean getFineSearch(int code,int branchID);	
	public int getFineDelete(int code);	
	public int getFineSave(Finebean newbean);	
	public int getFineUpdate(Finebean newbean);	
	public List getFineSearchName(String code,int branchID);
	public List getGroupSearchName(String code,int branchID);	
	
	//Holiday
		
	public List getHolidaySearch();	
	public int getHolidayDeleteAll();	
	public int getHolidayDelete(String date);	
	public int getHolidaySave(Holidaybean newbean);
	
    //WeakEndHoliday
	public int getWeakEndHolidaySave(int code,int code1);
	public int getWeakEndHolidaySearch();
	
//  --------------------- Suggestion -------------------
	
	public List getSuggestionList(SuggestionBean sugBean);
	
//  --------------------- Book Review -------------------
	
	public List getReviewList(ReviewBean revBean);	
	
	//Budget
	
	public BudgetBean getBudgetMax();		
	public BudgetBean getBudgetSearch(int code,int branchID);	
	public int getBudgetInterface(String code);	
	public int getBudgetDelete(String code);
	public BudgetBean getBudgetDeptSearchName(String code,int branchID);
	public BudgetBean getBudgetSearchName(String code,int branchID);
	public int getBudgetSave(BudgetBean newbean);
	public int getBudgetUpdate(BudgetBean newbean);
	
	public BudgetBean getBudgetUpdateSAmount();	
	public List getLoadBranchList(int branch);
	//Group
	
	public GroupBean getGroupMax();
	public GroupBean getGroupSearch(String code,int branchID);
	public int getGroupInterface(String code);	
	public int getGroupNameInterface(String code,int branchID);	
	public int getGroupDelete(String code);
	public int getGroupSave(GroupBean newbean);
	public int getGroupUpdate(GroupBean newbean);
	public List getGroupGenSearch(GroupBean newbean);
	
	
	//Login
	public LoginBean getLoginSearch(String code, int branch);
	public int getLoginDelete(String code,int rk);
	public int getLoginSave(LoginBean newbean);
	public int getLoginUpdate(LoginBean newbean);
	public LoginBean getLoginSearchName(String name,int branch);
	
	//Stock
	
	public StockBean getStock(String code,int branch_code);
	public StockBean getStockAll(int branch_code);
	public StockBean getStockMasSave(String code,int branch_code);
	public int getStockMasDeleteAll(int branch_code);
	public List getStockMasDisplay(StockBean newbean);
	public int getStockDeleteSingle(String code,int branch_code);	
	
	public int getBulkStockSave(List details,int branch_code);
	public StockDataMessage getCheckStockAccessNo(BulkStockData newbean);
	
    // Book Data Import
	
	public BookDataMessage getCheckAccessNo(BookDataBean newbean);
	public int getImportBookData(List details);
	
    // Member Data Import
	
	public MemberDataMessage getCheckMemberCode(MemberDataBean newbean);
	public int getImportMemberData(List details);
	
	public int getBranchCode(String branch);
	public int getGroupCode(String groupName,int branchID);
	public int getBudgetCode(String budget,int branchID);
	
    // Fine Data Import	
	public FineDataMessage getFineCheck(FineDataBean newbean);
	public int getImportFineData(List details);
	
    //  ---------------------- Transaction Master ----------	
	
	public TransMasBean getTransCodeMax();	
	public TransMasBean getTransMasSave(TransMasBean transBean);	
	public int getTransMasUpdate(TransMasBean transBean);	
	public TransMasBean getTransMasSearch(int code);
	public int getTransMasDelete(int code);
	
	
//  ---------------------- Miscellaneous & Trans_Mas Master ----------	
	
	public MiscellaneousBean getMiscellaneousMax();		
	public MiscellaneousBean getMiscellaneousSave(MiscellaneousBean transBean);	
	public MiscellaneousBean getMiscellaneousMember(String code,int branchID);
	public List getMiscellaneousMemberView(String name,int branchID);
	
	public List getMiscellaneousTHead();	
	public MiscellaneousBean getTransMasPayment(MiscellaneousBean transBean);
	public MiscellaneousBean getMiscellaneousSearch(int code);
	public int getMiscellaneousDelete(int code);
	public int getMiscellaneousUpdate(MiscellaneousBean transBean);		
//	---------------------- Question Bank Department ----------	
	public List getQBDepartmentList(MemberTransRefBean newBean);
	
	// QB Data Import
	
	public QBDataMessage getCheckQBCode(QBDataBean bookData);
	public int getImportQBData(List details);
	
	
//  ---------------------- Member Transfer ----------		
	
	public List getDepartmentList(MemberTransRefBean newBean);
	
	
	public List getDesignationList(MemberTransRefBean newBean);
	
	public List getCourseList(MemberTransRefBean newBean);
	
	public List getQBSubjectList(MemberTransRefBean newBean);
	
	
	public List getQBSubjectNameList(MemberTransRefBean newBean);
	
	
	public List getGroupList(MemberTransRefBean newBean);
	
	public List getMemberList(MemberTransRefBean newBean);
	public int getMemberTransfer(MemberTransRefBean newBean);
		
//  ---------------------- Bulk Updation ----------	
	
	public List getBulkUpdateList(String query, String flag,int branchID);	
	public int getBulkUpdateSave(BulkUpdateMsgBean newBean);	
	
	//------------------bulk member Update----------------------
	
	
	public List getBulkMemberUpdateList(String query, String flag, int branchID);	
	
	public int getBulkMemberUpdateSave(BulkUpdateMsgBean newBean);
	
	
//  ---------------------- Reference Book Due Days ----------	
	
	public RefDaysBean getRefDueDays();	
	public int getduedaysSave(RefDaysBean newBean);
	
	
	
	
	public List getDepartmentList();
	public List getCourseList();
	public List getGroupList();
	public List getQBSubjectList();
	
	public List getDepartmentList(int branchID);
	public List getCourseList(int branchID);
	public List getGroupList(int branchID);
	public List getQBSubjectList(int branchID);
	
	
}
