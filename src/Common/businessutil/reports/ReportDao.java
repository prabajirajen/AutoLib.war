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


public interface ReportDao {

	public List findMemberSearch(MemberReportBean ReportBean);

	public List findDeptSearch(MemberReportBean ReportBean);

	public List findGroupSearch(MemberReportBean ReportBean);

	public List findCourseSearch(MemberReportBean ReportBean);



	public List findDesigList(MemberTransRefBean newBean);

	public List findDepartmentList(MemberTransRefBean newBean);


	public List findBranchList(MemberTransRefBean newBean);
	public List findGroupList(MemberTransRefBean newBean);
	public List findCourseList(MemberTransRefBean newBean);






	//Journal List Report
	public List findDeptJnlSearch(JournalListBean ReportBean);

	public List findSubJnlSearch(JournalListBean ReportBean);

	//Journal Issue Report

	public List findJnlNameSearch(JnlIssueReportBean ReportBean);

	//For Report Connection

	public java.sql.Connection findDBConnect();

	public boolean  findCheckData(String query);

	public List findSearchBudgetList(BudgetBean newBean);


	public List findtodayIssueListDetails(String search);

	public List findtodayReturnListDetails(String search);

	public List findtodayRenewalListDetails(String search);

	public List findtodayTransAmountDetails(String search);

	public List findtodayPaidDetails(String search);


	//-------------------missing accessNo-------------------


	public int findAccessNo(accessbean newBean);
	public int findAccessNoSave(accessbean newBean);
	public int findDeleteMisNo();

	// AutoComplete DB Report

	public ArrayList<DepartmentBean> findDBReportDeptAutoSearch(String term);
	public ArrayList<subjectbean> findDBReportSubjectAutoSearch(String term);
	public ArrayList<PubSupBean> findDBReportPubAutoSearch(String term);
	public ArrayList<PubSupBean> findDBReportSupAutoSearch(String term);

	public ArrayList<DepartmentBean> findUniqueTitleReportDeptAutoSearch(
			String term, int rk);

	public ArrayList<subjectbean> findUniqueTitleReportSubjectAutoSearch(
			String term, int rk);



}