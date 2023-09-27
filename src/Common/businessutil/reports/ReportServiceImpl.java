package Common.businessutil.reports;

import java.util.ArrayList;
import java.util.List;

import Common.Security;
import Lib.Auto.AccessionRegister.accessbean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.JnlIssueReport.JnlIssueReportBean;
import Lib.Auto.JournalList.JournalListBean;
import Lib.Auto.MemberReport.MemberReportBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Report.reportbean;
import Lib.Auto.Subject.subjectbean;



public class ReportServiceImpl implements ReportService {
	
	private ReportDao reportDao;

	public ReportServiceImpl() {
		
			}
	

	public List getMemberSearch(MemberReportBean ReportBean) {
		
		return reportDao.findMemberSearch(ReportBean);
	}

	public List getDeptSearch(MemberReportBean ReportBean) {
		
		return reportDao.findDeptSearch(ReportBean);
	}


	public List getGroupSearch(MemberReportBean ReportBean) {
		
		return reportDao.findGroupSearch(ReportBean);
	}



	public List getCourseSearch(MemberReportBean ReportBean) {
		
		return reportDao.findCourseSearch(ReportBean);
	}
	
	
	public List getDesigList(MemberTransRefBean newBean)
	{
		return reportDao.findDesigList(newBean);
	}
	
	
	public List getDepartmentList(MemberTransRefBean newBean)
	{
		return reportDao.findDepartmentList(newBean);
	}
	
	
	public List getBranchList(MemberTransRefBean newBean)
	{
		return reportDao.findBranchList(newBean);
	}
	public List getGroupList(MemberTransRefBean newBean)
	{
		return reportDao.findGroupList(newBean);
	}
	public List getCourseList(MemberTransRefBean newBean)
	{
		return reportDao.findCourseList(newBean);
	}
	
	
	
	
	

//Journal List Report
	
	public List getDeptJnlSearch(JournalListBean ReportBean) {
		
		return reportDao.findDeptJnlSearch(ReportBean);
	}

	public List getSubJnlSearch(JournalListBean ReportBean) {
		
		return reportDao.findSubJnlSearch(ReportBean);
	}
	
	//Journal Issue Report
	public List getJnlNameSearch(JnlIssueReportBean ReportBean) {
		
		return reportDao.findJnlNameSearch(ReportBean);
	}

	
	public java.sql.Connection getDBConnect()
	{
		return reportDao.findDBConnect();
	}
	
	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao ReportDao) {
		this.reportDao = ReportDao;
	}
	
	public boolean  getCheckData(String query) {
		return reportDao.findCheckData(query);
	}
	
	
	public List getSearchBudgetList(BudgetBean newBean)
	{
		return reportDao.findSearchBudgetList(newBean);
	}
	
	
	public List gettodayIssueListDetails(String search) {
		
		
		
		
		{
			List results=getReportDao().findtodayIssueListDetails(search);
			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					reportbean prt= new reportbean();
					prt.setUcode(obj[0].toString());
					prt.setUname(obj[1].toString());
					prt.setAccno(obj[2].toString());
					prt.setTitle(obj[3].toString());
					prt.setAuthorName(obj[4].toString());
					prt.setIssueDate(Security.getdate(obj[5].toString()));
					prt.setDueDate(Security.getdate(obj[6].toString()));
				    prt.setDocType(obj[7].toString());
				    prt.setStaffCode(obj[8].toString());
				    
					finalResults.add(prt);
				}
			}
			return finalResults;
		}
	}
	
	
public List gettodayReturnListDetails(String search) {
		
		
		
		
		{
			List results=getReportDao().findtodayReturnListDetails(search);
			List finalResults = null;
			if(results != null)
			{
				finalResults = new ArrayList();
				for(int i = 0; i < results.size(); i++)
				{
					Object[] obj = (Object[])results.get(i);
					reportbean prt= new reportbean();
					prt.setUcode(obj[0].toString());
					prt.setUname(obj[1].toString());
					prt.setAccno(obj[2].toString());
					prt.setTitle(obj[3].toString());
					prt.setAuthorName(obj[4].toString());
					prt.setIssueDate(Security.getdate(obj[5].toString()));
					prt.setDueDate(Security.getdate(obj[6].toString()));		
					prt.setReturnDate(Security.getdate(obj[7].toString()));
				    prt.setDocType(obj[8].toString());
				    prt.setStaffCode(obj[9].toString());
				    
					finalResults.add(prt);
				}
			}
			return finalResults;
		}
	}

public List gettodayRenewalListDetails(String search) {
	
	
	
	{
		List results=getReportDao().findtodayRenewalListDetails(search);
		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				reportbean prt= new reportbean();
				prt.setUcode(obj[0].toString());
				prt.setUname(obj[1].toString());
				prt.setAccno(obj[2].toString());
				prt.setTitle(obj[3].toString());
				prt.setAuthorName(obj[4].toString());
				prt.setIssueDate(Security.getdate(obj[5].toString()));
				prt.setDueDate(Security.getdate(obj[6].toString()));
			    prt.setDocType(obj[7].toString());
			    prt.setStaffCode(obj[8].toString());
			    
				finalResults.add(prt);
			}
		}
		return finalResults;
	}
}


public List gettodayTransAmountDetails(String search) {
	
	
	
	{
		List results=getReportDao().findtodayTransAmountDetails(search);
		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				reportbean prt= new reportbean();
				prt.setUcode(obj[0].toString());
				prt.setUname(obj[1].toString());
				prt.setAccno(obj[2].toString());
				prt.setTitle(obj[3].toString());
				prt.setAuthorName(obj[4].toString());
				prt.setIssueDate(Security.getdate(obj[5].toString()));
				prt.setDueDate(Security.getdate(obj[6].toString()));
				prt.setReturnDate(Security.getdate(obj[7].toString()));
				prt.setFineAmount(obj[8].toString());
				prt.setDocType(obj[9].toString());
			    prt.setStaffCode(obj[10].toString());
			    
				finalResults.add(prt);
			}
		}
		return finalResults;
	}
}


public List gettodayPaidDetails(String search) {
	
	{
		List results=getReportDao().findtodayPaidDetails(search);
		List finalResults = null;
		if(results != null)
		{
			finalResults = new ArrayList();
			for(int i = 0; i < results.size(); i++)
			{
				Object[] obj = (Object[])results.get(i);
				reportbean prt= new reportbean();
				
				prt.setBillNo(Integer.parseInt(obj[0].toString()));
				prt.setUcode(obj[1].toString());
				prt.setUname(obj[2].toString());
				prt.setFineAmount(obj[3].toString());
				prt.setReturnDate(Security.getdate(obj[4].toString()));
				prt.setPaymode(obj[5].toString());
				prt.setStaffCode(obj[6].toString());
				prt.setDocType(obj[7].toString());
				
					finalResults.add(prt);
			}
		}
		return finalResults;
	}
}


//----------------------missing accessNo-------------------------


public int getAccessNo(accessbean newbean)
{
	return reportDao.findAccessNo(newbean);
}
public int getAccessNoSave(accessbean newbean)
{
	return reportDao.findAccessNoSave(newbean);
}
public int getDeleteMisNo()
{
	return reportDao.findDeleteMisNo();
}	


//AutoComplete DB Report

	public ArrayList<DepartmentBean> getDBReportDeptAutoSearch(String term) {
		 
		return reportDao.findDBReportDeptAutoSearch(term);
		}

	
	public ArrayList<subjectbean> getDBReportSubjectAutoSearch(String term) {
		 
		return reportDao.findDBReportSubjectAutoSearch(term);
		}

	public ArrayList<PubSupBean> getDBReportPubAutoSearch(String term) {
		 
		return reportDao.findDBReportPubAutoSearch(term);
		}

	public ArrayList<PubSupBean> getDBReportSupAutoSearch(String term) {
		 
		return reportDao.findDBReportSupAutoSearch(term);
		}


	public ArrayList<DepartmentBean> getUniqueTitleReportDeptAutoSearch(
			String term, int rk) {
		
		return reportDao.findUniqueTitleReportDeptAutoSearch(term,rk);
	}

	public ArrayList<subjectbean> getUniqueTitleReportSubjectAutoSearch(
			String term, int rk) {
		
		return reportDao.findUniqueTitleReportSubjectAutoSearch(term,rk);
	}

		
	}
	
	
	