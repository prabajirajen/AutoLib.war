package Common.businessutil.reports;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.AccessionRegister.accessbean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.JnlIssueReport.JnlIssueReportBean;
import Lib.Auto.JournalList.JournalListBean;
import Lib.Auto.MemberReport.MemberReportBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Subject.subjectbean;




public interface ReportService {
	
	//Member Report
	public List getMemberSearch(MemberReportBean ReportBean);
	
	public List getDeptSearch(MemberReportBean ReportBean);
	
	public List getGroupSearch(MemberReportBean ReportBean);
	
	public List getCourseSearch(MemberReportBean ReportBean);
	
	
	
	
	public List getDesigList(MemberTransRefBean newBean);
	
	public List getDepartmentList(MemberTransRefBean newBean);
	
	public List getBranchList(MemberTransRefBean newBean);
	public List getGroupList(MemberTransRefBean newBean);
	public List getCourseList(MemberTransRefBean newBean);
	
	
	
	
	public List gettodayIssueListDetails(String search);
	
	public List gettodayReturnListDetails(String search);
	
	public List gettodayRenewalListDetails(String search);
	
	public List gettodayTransAmountDetails(String search);
	
	public List gettodayPaidDetails(String search);
	
	
	
	//Journal List
	
	public List getDeptJnlSearch(JournalListBean ReportBean);
	
	public List getSubJnlSearch(JournalListBean ReportBean);
	
	//Journal Issue Report
	
	public List getJnlNameSearch(JnlIssueReportBean ReportBean);

	//	For Report Connection
	public java.sql.Connection getDBConnect();
	
	public boolean getCheckData(String query);
	
	//budget report
	
	public List getSearchBudgetList(BudgetBean newBean);
	

	//-----------------missing accessNo------------------------
	
	public int getAccessNo(accessbean NewBean);
	public int getAccessNoSave(accessbean NewBean);
	public int getDeleteMisNo();
	

	//  DB Report Auto Complete
	
	public ArrayList<DepartmentBean> getDBReportDeptAutoSearch(String term);
	public ArrayList<subjectbean> getDBReportSubjectAutoSearch(String term);
	public ArrayList<PubSupBean> getDBReportPubAutoSearch(String term);
	public ArrayList<PubSupBean> getDBReportSupAutoSearch(String term);

	public ArrayList<DepartmentBean> getUniqueTitleReportDeptAutoSearch(
			String term, int rk);

	public ArrayList<subjectbean> getUniqueTitleReportSubjectAutoSearch(
			String term, int rk);
			
	
	
}