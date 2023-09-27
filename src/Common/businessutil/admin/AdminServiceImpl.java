package Common.businessutil.admin;
import java.util.ArrayList;
import java.util.List;

import Common.businessutil.admin.AdminDao;
import Common.businessutil.admin.AdminService;
import Lib.Auto.Fine.Finebean;
import Lib.Auto.Holiday.Holidaybean;
import Lib.Auto.Book_Import.BookDataBean;
import Lib.Auto.Book_Import.BookDataMessage;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.BulkFineUpload.FineDataBean;
import Lib.Auto.BulkFineUpload.FineDataMessage;
import Lib.Auto.BulkUpdate.BulkUpdateBean;
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



public class AdminServiceImpl implements AdminService {

	private AdminDao adminDao;

	public AdminServiceImpl() {
	}

	//Fine

	public Finebean getFineMax() {
		return adminDao.findFineMax();
	}

	public Finebean getFineSearch(int code,int branchID) {
		return adminDao.findFineSearch(code,branchID);
	}

	public int getFineDelete(int code) {
		return adminDao.findFineDelete(code);
	}

	public int getFineSave(Finebean newbean) {
		return adminDao.findFineSave(newbean);
	}

	public int getFineUpdate(Finebean newbean) {
		return adminDao.findFineUpdate(newbean);
	}

	public List getFineSearchName(String code,int branchID) {
		return adminDao.findFineSearchName(code,branchID);
	}

	public List getGroupSearchName(String code,int branchID) {
		return adminDao.findGroupSearchName(code,branchID);
	}

	//Holiday


	public List getHolidaySearch() {
		return adminDao.findHolidaySearch();
	}

	public int getHolidayDeleteAll() {
		return adminDao.findHolidayDeleteAll();
	}

	public int getHolidayDelete(String date) {
		return adminDao.findHolidayDelete(date);
	}

	public int getHolidaySave(Holidaybean newbean) {
		return adminDao.findHolidaySave(newbean);
	}

	//WeakEndHoliday
	public int getWeakEndHolidaySave(int code,int code1)
	{
		return adminDao.findWeakEndHolidaySave(code,code1);
	}

	public int getWeakEndHolidaySearch()
	{
		return adminDao.findWeakEndHolidaySearch();
	}


	// --------------- Suggestion ------------------

	public List getSuggestionList(SuggestionBean sugBean)
	{
		return adminDao.findSuggestionList(sugBean);
	}	

	// --------------- Book Review ------------------

	public List getReviewList(ReviewBean revBean)
	{
		return adminDao.findReviewList(revBean);
	} 				


	//Budget


	public BudgetBean getBudgetMax() {
		return adminDao.findBudgetMax();
	}

	public BudgetBean getBudgetSearch(int code,int branchID) {
		return adminDao.findBudgetSearch(code,branchID);
	}

	public int getBudgetInterface(String code) {
		return adminDao.findBudgetInterface(code);
	}
	public int getBudgetDelete(String code) {
		return adminDao.findBudgetDelete(code);
	}

	public BudgetBean getBudgetDeptSearchName(String code,int branchID) {
		return adminDao.findBudgetDeptSearchName(code,branchID);
	}

	public BudgetBean getBudgetSearchName(String code,int branchID) {
		return adminDao.findBudgetSearchName(code,branchID);
	}


	public int getBudgetSave(BudgetBean newbean) {
		return adminDao.findBudgetSave(newbean);
	}

	public int getBudgetUpdate(BudgetBean newbean) {
		return adminDao.findBudgetUpdate(newbean);
	}

	public BudgetBean getBudgetUpdateSAmount()    {
		return adminDao.findBudgetUpdateSAmount();
	}
	public List getLoadBranchList(int branch) {
		return adminDao.findLoadBranchList(branch);
	}


	//Group

	public GroupBean getGroupMax() {
		return adminDao.findGroupMax();
	}
	public GroupBean getGroupSearch(String code,int branchID) {
		return adminDao.findGroupSearch(code,branchID);
	}
	public int getGroupInterface(String code) {
		return adminDao.findGroupInterface(code);
	}
	public int getGroupNameInterface(String code,int branchID) {
		return adminDao.findGroupNameInterface(code,branchID);
	}
	public int getGroupDelete(String code) {
		return adminDao.findGroupDelete(code);
	}

	public int getGroupSave(GroupBean newbean) {
		return adminDao.findGroupSave(newbean);
	}

	public int getGroupUpdate(GroupBean newbean) {
		return adminDao.findGroupUpdate(newbean);
	}
	public List getGroupGenSearch(GroupBean newbean) {
		return adminDao.findGroupGenSearch(newbean);
	}

	//Login

	public LoginBean getLoginSearch(String code, int branch) {
		return adminDao.findLoginSearch(code, branch);
	}
	public int getLoginDelete(String code,int branchCode) {
		return adminDao.findLoginDelete(code,branchCode);
	}

	public int getLoginSave(LoginBean newbean) {
		return adminDao.findLoginSave(newbean);
	}

	public int getLoginUpdate(LoginBean newbean) {
		return adminDao.findLoginUpdate(newbean);
	}

	public LoginBean getLoginSearchName(String name, int branch) {
		return adminDao.findLoginSearchName(name, branch);
	}


	//Stock

	public StockBean getStock(String code,int branch_code) {
		return adminDao.findStock(code,branch_code);
	}

	public StockBean getStockAll(int branch_code) {
		return adminDao.findStockAll(branch_code);
	}

	public StockBean getStockMasSave(String code,int branch_code) {
		return adminDao.findStockMasSave(code,branch_code);
	}
	public int getStockMasDeleteAll(int branch_code) {
		return adminDao.findStockMasDeleteAll(branch_code);
	}
	public List getStockMasDisplay(StockBean newbean) {
		return adminDao.findStockMasDisplay(newbean);
	}

	public int getStockDeleteSingle(String code,int branch_code) {
		return adminDao.findStockDeleteSingle(code,branch_code);
	}

	// For Bulk Stock

	public int getBulkStockSave(List details,int branch_code)
	{
		return adminDao.findBulkStockSave(details,branch_code);		
	}

	public StockDataMessage getCheckStockAccessNo(BulkStockData newbean)
	{
		return adminDao.findCheckStockAccessNo(newbean);
	}

	// Book Data Import

	public BookDataMessage getCheckAccessNo(BookDataBean newbean)
	{
		return adminDao.findCheckAccessNo(newbean);
	}


	public int getImportBookData(List details)
	{
		return adminDao.findImportBookData(details);
	}


	// Member Data Import

	public MemberDataMessage getCheckMemberCode(MemberDataBean newbean)
	{
		return adminDao.findCheckMemberCode(newbean);
	}

	public int getImportMemberData(List details)
	{
		return adminDao.findImportMemberData(details);
	}


	public int getBranchCode(String branch)
	{
		return adminDao.getBranchCode(branch);
	}

	public int getGroupCode(String groupName,int branchID)
	{
		return adminDao.getGroupCode(groupName,branchID);
	}

	public int getBudgetCode(String budget,int branchID)
	{
		return adminDao.getBudgetCode(budget,branchID);
	}


	// Fine Data Import	
	public FineDataMessage getFineCheck(FineDataBean newbean)
	{
		return adminDao.findFineCheck(newbean);
	}
	public int getImportFineData(List details)
	{
		return adminDao.findImportFineData(details);
	}


	//  ---------------------- Transaction Master ----------	

	public TransMasBean getTransCodeMax() {
		return adminDao.findTransCodeMax();
	}	

	public TransMasBean getTransMasSave(TransMasBean transBean)  {

		return adminDao.findTransMasSave(transBean);
	}

	public int getTransMasUpdate(TransMasBean transBean)	
	{
		return adminDao.findTransMasUpdate(transBean);
	}
	public TransMasBean getTransMasSearch(int code)  {

		return adminDao.findTransMasSearch(code);
	}

	public int getTransMasDelete(int code)    {

		return adminDao.findTransMasDelete(code);
	}


	//  ---------------------- Miscellaneous & Trans_Mas Master ----------		

	public MiscellaneousBean getMiscellaneousMax()
	{
		return adminDao.findMiscellaneousMax();
	}

	public MiscellaneousBean getMiscellaneousSave(MiscellaneousBean transBean)
	{
		return adminDao.findMiscellaneousSave(transBean);
	}

	public MiscellaneousBean getMiscellaneousMember(String code, int branchID)
	{
		return adminDao.findMiscellaneousMember(code,branchID);
	}

	public List getMiscellaneousMemberView(String name, int branchID)
	{
		return adminDao.findMiscellaneousMemberView(name,branchID);
	}

	public List getMiscellaneousTHead()
	{
		return adminDao.findMiscellaneousTHead();
	}

	public MiscellaneousBean getTransMasPayment(MiscellaneousBean transBean)
	{
		return adminDao.findTransMasPayment(transBean);
	}

	public MiscellaneousBean getMiscellaneousSearch(int code)
	{
		return adminDao.findMiscellaneousSearch(code);
	}

	public int getMiscellaneousDelete(int code)
	{
		return adminDao.findMiscellaneousDelete(code);
	}

	public int getMiscellaneousUpdate(MiscellaneousBean transBean)
	{
		return adminDao.findMiscellaneousUpdate(transBean);
	}
	//	---------------------- QB Dept ----------

	public List getQBDepartmentList(MemberTransRefBean newBean)
	{
		return adminDao.findQBDepartmentList(newBean);
	}

	//  ---------------------- Member Transfer ----------		

	public List getDepartmentList(MemberTransRefBean newBean)
	{
		return adminDao.findDepartmentList(newBean);
	}

	public List getDesignationList(MemberTransRefBean newBean)
	{
		return adminDao.findDesignationList(newBean);
	}


	public List getCourseList(MemberTransRefBean newBean)
	{
		return adminDao.findCourseList(newBean);
	}

	public List getQBSubjectList(MemberTransRefBean newBean)
	{
		return adminDao.findQBSubjectList(newBean);
	}

	public List getQBSubjectNameList(MemberTransRefBean newBean)
	{
		return adminDao.findQBSubjectNameList(newBean);
	}



	public List getGroupList(MemberTransRefBean newBean)
	{
		return adminDao.findGroupList(newBean);
	}


	public List getMemberList(MemberTransRefBean newBean)
	{
		return adminDao.findMemberList(newBean);
	}

	public int getMemberTransfer(MemberTransRefBean newBean)
	{
		return adminDao.findMemberTransfer(newBean);
	}


	// --------------- Bulk Updation ---------------	

	public List getBulkUpdateList(String query, String flag, int branchID)
	{		
		List list = adminDao.findBulkUpdateList(query,flag,branchID);
		List<Object> finalResults = null;

		if(list != null && list.size() > 0)
		{
			finalResults = new ArrayList<Object>();			
			for(int i=0; i < list.size(); i++)
			{
				Object[] obj = (Object[]) list.get(i);				
				BulkUpdateBean bean = new BulkUpdateBean();

				//bean.setCode(Integer.parseInt(obj[0].toString()));
				bean.setCode(obj[0].toString());
				bean.setName(obj[1].toString());			
				finalResults.add(bean);
			}		
		}	
		return finalResults;
	}




	// --------------- Bulk Member Update ---------------	


	public List getBulkMemberUpdateList(String query, String flag, int branchID)
	{		
		List list = adminDao.findBulkMemberUpdateList(query,flag,branchID);
		List<Object> finalResults = null;

		if(list != null && list.size() > 0)
		{
			finalResults = new ArrayList<Object>();			
			for(int i=0; i < list.size(); i++)
			{
				Object[] obj = (Object[]) list.get(i);				
				BulkUpdateBean bean = new BulkUpdateBean();

				//bean.setCode(Integer.parseInt(obj[0].toString()));
				bean.setCode(obj[0].toString());
				bean.setName(obj[1].toString());			
				finalResults.add(bean);
			}		
		}	
		return finalResults;
	}






	public int getBulkUpdateSave(BulkUpdateMsgBean newBean)
	{
		return adminDao.findBulkUpdateSave(newBean);
	}

	//-------------------bulk member update------------------------


	public int getBulkMemberUpdateSave(BulkUpdateMsgBean newBean)
	{
		return adminDao.findBulkMemberUpdateSave(newBean);
	}



	//  ---------------------- Reference Book Due Days ----------	

	public RefDaysBean getRefDueDays()
	{
		return adminDao.getRefDueDays();
	}

	public int getduedaysSave(RefDaysBean newBean)
	{
		return adminDao.getduedaysSave(newBean);
	}


	public AdminDao getAdminDao() {
		return adminDao;
	}


	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	// QB Data Import

	public QBDataMessage getCheckQBCode(QBDataBean newbean)
	{
		return adminDao.findCheckQBCode(newbean);
	}


	public int getImportQBData(List details)
	{
		return adminDao.findImportQBData(details);

	}

	public List getDepartmentList()
	{
		return adminDao.findDepartmentList();
	}
	
	public List getCourseList()
	{
		return adminDao.findCourseList();
	}
	
	public List getGroupList()
	{
		return adminDao.findGroupList();
	}
	
	public List getQBSubjectList()
	{
		return adminDao.findQBSubjectList();
	}

	@Override
	public List getDepartmentList(int branchID) {
		return adminDao.findDepartmentList(branchID);
	}

	@Override
	public List getCourseList(int branchID) {
		return adminDao.findCourseList(branchID);
	}

	@Override
	public List getGroupList(int branchID) {
		return adminDao.findGroupList(branchID);
	}

	@Override
	public List getQBSubjectList(int branchID) {
		return adminDao.findQBSubjectList(branchID);
	}
	
	}