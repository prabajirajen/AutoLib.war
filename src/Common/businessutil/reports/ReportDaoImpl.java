package Common.businessutil.reports;

//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import Common.DataQuery;
import Lib.Auto.AccessionRegister.accessbean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.JnlIssueReport.JnlIssueReportBean;
import Lib.Auto.JournalList.JournalListBean;
import Lib.Auto.MemberReport.MemberReportBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Subject.subjectbean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;


public class ReportDaoImpl extends BaseDBConnection implements
ReportDao {
private static Logger log4jLogger = Logger.getLogger(ReportDaoImpl.class);
	
	java.sql.Connection con = null;	
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;	
	java.sql.Statement st = null;
	
//	For Report Connection
	java.sql.Connection con1 = null;	
	
	public java.sql.Connection findDBConnect()
	{
		try
		{
			//String url="jdbc:mysql://localhost:3306/autolib";			
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
		    //con1=DriverManager.getConnection(url,"root","admin");		
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		return con1;	
	}
	
	
	public List findMemberSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findMemberSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;
		String strsql="";

		try {
							
			if(newBean.getBranchCode() == 2)
			{
				strsql = " AND branch_code="+newBean.getBranchCode();
			}else if(newBean.getBranchCode() > 2)
			{
				strsql = " AND Dept_BranchCode="+newBean.getBranchCode();
			}
			
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {
				
					rs = st.executeQuery(DataQuery.MEMBERSEARCH_STRING+strsql);
				
			} else {

				rs = st.executeQuery(DataQuery.MEMBERSEARCH_STRING_LIKE
						+ newBean.getMname() + "%' "+strsql+" order by member_code");
				
			
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setMcode(rs.getString("member_Code"));
				newmemberBean.setMname(rs.getString("member_Name"));
				finalResults.add(newmemberBean);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	public List findDeptSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findDeptSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;
		String strsql = "";

		try {
			
			if(newBean.getBranchCode() > 0)
			{
				strsql = " and branch_code="+newBean.getBranchCode()+" ";
			}
			
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME+strsql+" order by dept_name");
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						+ newBean.getMname() + "%'"+strsql+" order by Dept_Name");			
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setDcode(rs.getInt("Dept_Code"));
				newmemberBean.setDname(rs.getString("Dept_Name"));
				newmemberBean.setDdesc(rs.getString("Short_Desc"));
				finalResults.add(newmemberBean);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	
	
	public List findGroupSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findGroupSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;
		String strsql="";

		try {
			if(newBean.getBranchCode()>2)
			{
				strsql = " and dept_branchCode="+newBean.getBranchCode()+" ";
			}
			
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {

				rs = st.executeQuery(DataQuery.GROUPSEARCH_NAME+strsql+"  order by group_name");
			} else {

				rs = st.executeQuery(DataQuery.GROUPSEARCH_NAME_LIKE
						+ newBean.getMname() + "%' "+strsql+" order by Group_Name");				
			
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setGcode(rs.getInt("Group_Code"));
				newmemberBean.setGname(rs.getString("Group_Name"));
				newmemberBean.setRemarks(rs.getString("Remarks"));
				finalResults.add(newmemberBean);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	
	public List findCourseSearch(MemberReportBean newBean) {
		log4jLogger.info("start===========findCourseSearch=====");
		MemberReportBean newmemberBean = null;
		List finalResults = null;

		try {
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getMname() == "") {

				rs = st.executeQuery(DataQuery.COURSESEARCH_NAME+ " order by Course_Name");
			} else {

				rs = st.executeQuery(DataQuery.COURSESEARCH_NAME_LIKE
						+ newBean.getMname() + "%' order by Course_Name");
				
			
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new MemberReportBean();
				newmemberBean.setCcode(rs.getInt("Course_Code"));
				newmemberBean.setCname(rs.getString("Course_Name"));
				//newmemberBean.setCdesc(rs.getString("Short_Desc"));
				finalResults.add(newmemberBean);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	
	//shek
	
	public List findDesigList(MemberTransRefBean newBean)
	{
		log4jLogger.info("start===========getDesignationList=====");
		String strsql = "";
		
			strsql = " AND branch_Code="+newBean.getBranchCode()+"  ";
		
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.distinctBranchwiseDesig+strsql+"Order by Designation ASC");		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			
			
			
			
			result.add(refBean);		
		}	
		
		return result;
	}
	
	public List findDepartmentList(MemberTransRefBean newBean)
	{
		log4jLogger.info("start===========findDepartmentList=====");
		String strsql = "";
		
			strsql = " AND branch_Code="+newBean.getBranchCode()+"  ";
		
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.distinctBranchwiseDepartment+strsql+"Order by dept_name ASC");		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			
			
			
			
			result.add(refBean);		
		}	
		
		return result;
	}
	
	
	public List findBranchList(MemberTransRefBean newBean)
	{
		log4jLogger.info("start===========getBranchList=====");
		String strsql = "";
		
		
			strsql = " AND branch_Code="+newBean.getBranchCode()+" ";
		
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.distinctBranchwise+strsql+" Order by Branch_name ASC");		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			//refBean.setDesc(obj[2].toString());
			
			result.add(refBean);		
		}	
		
		return result;
	}
	
	
	public List findGroupList(MemberTransRefBean newBean)
	{
		log4jLogger.info("start===========getGroupList=====");
		String strsql = "";
		
			strsql = " AND branch_Code="+newBean.getBranchCode()+" ";
		
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.distinctBranchwiseGroup+strsql);		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			//refBean.setDesc(obj[2].toString());
			
			result.add(refBean);		
		}	
		
		return result;
	}
	
	
	public List findCourseList(MemberTransRefBean newBean)
	{
		log4jLogger.info("start===========getCourseList=====");
		String strsql = "";
		
			strsql = " AND branch_Code="+newBean.getBranchCode()+" ";
		
		
		MemberTransRefBean refBean = null;
		List<Object> result = new ArrayList<Object>();
		
		SQLQuery sql = getSession().createSQLQuery(DataQuery.distinctBranchwiseCourse+strsql);		
		List list = sql.list();
		
		for(int i=0; i<list.size(); i++)
		{
			Object[] obj = (Object[]) list.get(i);
			refBean = new MemberTransRefBean();
			
			refBean.setCode(Integer.parseInt(obj[0].toString()));
			refBean.setName(obj[1].toString());
			refBean.setDesc(obj[2].toString());
			
			result.add(refBean);		
		}	
		
		return result;
	}
	
	
	
	
	
	
	
	
	//Journal List Report
	
	public List findDeptJnlSearch(JournalListBean newBean) {
		log4jLogger.info("start===========findDeptJnlSearch=====");
		JournalListBean newmemberBean = null;
		List finalResults = null;
		String strsql = "";

		try {
			if(newBean.getBranchCode() > 0)
			{
				strsql = " and branch_code="+newBean.getBranchCode()+" ";
			}
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getDname() == "") {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME+strsql);
			} else {

				rs = st.executeQuery(DataQuery.DEPTSEARCH_NAME_LIKE
						+ newBean.getDname() + "%'"+strsql+" order by Dept_Name");
				
			
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new JournalListBean();
				newmemberBean.setDcode(rs.getInt("Dept_Code"));
				newmemberBean.setDname(rs.getString("Dept_Name"));
				newmemberBean.setDdesc(rs.getString("Short_Desc"));
				finalResults.add(newmemberBean);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}
	public List findSubJnlSearch(JournalListBean newBean) {
		log4jLogger.info("start===========findSubJnlSearch=====");
		JournalListBean newmemberBean = null;
		List finalResults = null;
		String strsql = "";

		try {
			if(newBean.getBranchCode() > 0)
			{
				strsql = " and branch_code="+newBean.getBranchCode()+" ";
			}
			
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getDname() == "") {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME+strsql+" order by sub_name");
			} else {

				rs = st.executeQuery(DataQuery.SUBJECTSEARCH_NAME_LIKE
						+ newBean.getDname() + "%' "+strsql+" order by sub_Name");			
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new JournalListBean();
				newmemberBean.setScode(rs.getInt("Sub_Code"));
				newmemberBean.setSname(rs.getString("Sub_Name"));
				newmemberBean.setSdesc(rs.getString("Short_Desc"));
				finalResults.add(newmemberBean);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}
	
	//Journal Issue Report
	public List findJnlNameSearch(JnlIssueReportBean newBean) {
		log4jLogger.info("start===========findJnlNameSearch=====");
		JnlIssueReportBean newmemberBean = null;
		List finalResults = null;
		String strsql="";

		try {
			
			if(newBean.getBranchCode() > 0)
			{
				strsql = " AND branch_code="+newBean.getBranchCode()+" ";    // For Titan
			}
			
			con = getSession().connection();
			st = con.createStatement();

			if (newBean.getJname() == "") {

				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME +strsql+ " order by jnl_name");
			} else {

				rs = st.executeQuery(DataQuery.JOURNAL_SEARCH_NAME_LIKE
						+ newBean.getJname() + "%'"+strsql+" order by jnl_Name");
				
			
			}

			finalResults = new ArrayList();

			while (rs.next()) {
				newmemberBean = new JnlIssueReportBean();
				newmemberBean.setJcode(rs.getInt("Jnl_Code"));
				newmemberBean.setJname(rs.getString("Jnl_Name"));
		        newmemberBean.setFreq(rs.getString("doc_type"));
				finalResults.add(newmemberBean);

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}
	
	
	

public boolean findCheckData(String query) {
	
	
	boolean result=true;
	
	try {
	
	con = getSession().connection();
	
	String Query = query;
	Prest = con.prepareStatement(Query);
	
	rs = Prest.executeQuery();
	
	if(rs.next()){
		result = false;
	}else{
		result = true;
	}
	
	
	
}
catch (Exception e) {
	e.printStackTrace();
} finally {
	try {
		if (rs != null) {
			rs.close();
			rs = null;
		}
		if (Prest != null) {
			Prest.close();
			Prest = null;
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
}

return result;
}


			
public List findSearchBudgetList(BudgetBean newBean)
{
	log4jLogger.info("start===========getSearchBudgetList=====");
	String strsql = "";
	
		strsql = " AND branch_Code="+newBean.getBranchCode()+" ";
	
	BudgetBean refBean = null;
	List<Object> result = new ArrayList<Object>();
	
	SQLQuery sql = getSession().createSQLQuery(ReportQueryUtill.DistinctBudgetReportQuery+strsql);		
	List list = sql.list();
	
	for(int i=0; i<list.size(); i++)
	{
		Object[] obj = (Object[]) list.get(i);
		refBean = new BudgetBean();
		
		refBean.setBudCode(Integer.parseInt(obj[0].toString()));
	
		refBean.setBudHead(obj[1].toString());
		
		refBean.setDeptCode(Integer.parseInt(obj[2].toString()));
		refBean.setDeptname(obj[3].toString());
		
		
		result.add(refBean);		
	}	
	
	
	return result;
}




public List findtodayIssueListDetails(String filterQuery) {

	log4jLogger.info("start===========findtodayIssueListDetails====="
			+ filterQuery);
	StringBuffer sb = new StringBuffer();
	String namedQuery = getSession().getNamedQuery("todayIssueListQuery").getQueryString();
	if (filterQuery != null) {
		sb.append(namedQuery);
		sb.append(" " + filterQuery);
	} else {
		sb.append(namedQuery);
	}
	SQLQuery sql = getSession().createSQLQuery(sb.toString());

	return sql.list();

}


public List findtodayReturnListDetails(String filterQuery) {

	log4jLogger.info("start===========findtodayReturnListDetails====="
			+ filterQuery);
	StringBuffer sb = new StringBuffer();
	String namedQuery = getSession().getNamedQuery("todayReturnListQuery").getQueryString();
	if (filterQuery != null) {
		sb.append(namedQuery);
		sb.append(" " + filterQuery);
	} else {
		sb.append(namedQuery);
	}
	SQLQuery sql = getSession().createSQLQuery(sb.toString());

	return sql.list();

}


public List findtodayRenewalListDetails(String filterQuery) {

	log4jLogger.info("start===========findtodayRenewalListDetails====="
			+ filterQuery);
	StringBuffer sb = new StringBuffer();
	String namedQuery = getSession().getNamedQuery("todayRenewListQuery").getQueryString();
	if (filterQuery != null) {
		sb.append(namedQuery);
		sb.append(" " + filterQuery);
	} else {
		sb.append(namedQuery);
	}
	SQLQuery sql = getSession().createSQLQuery(sb.toString());

	return sql.list();

}



public List findtodayTransAmountDetails(String filterQuery) {

	log4jLogger.info("start===========findtodayTransAmountDetails====="
			+ filterQuery);
	StringBuffer sb = new StringBuffer();
	String namedQuery = getSession().getNamedQuery("todayTransAmountListQuery").getQueryString();
	if (filterQuery != null) {
		sb.append(namedQuery);
		sb.append(" " + filterQuery);
	} else {
		sb.append(namedQuery);
	}
	SQLQuery sql = getSession().createSQLQuery(sb.toString());

	return sql.list();

}


public List findtodayPaidDetails(String filterQuery) {

	log4jLogger.info("start===========findtodayPaidDetails====="
			+ filterQuery);
	StringBuffer sb = new StringBuffer();
	String namedQuery = getSession().getNamedQuery("todayPaidListQuery").getQueryString();
	if (filterQuery != null) {
		sb.append(namedQuery);
		sb.append(" " + filterQuery);
	} else {
		sb.append(namedQuery);
	}
	SQLQuery sql = getSession().createSQLQuery(sb.toString());

	return sql.list();

}



public int findAccessNo(accessbean newBean)
{
	log4jLogger.info("start===========Fetching Missing AccessNo From Database=====");
	int accessno = 0;
	String accno;
	try {
		con = getSession().connection();
		String doctype=newBean.getDoctype();
		if(!doctype.equals("ALL"))
		{
			Prest = con.prepareStatement(DataQuery.MissingAccessionQuery_Acc_no);
			Prest.setString(1, newBean.getAccessno());
			Prest.setString(2, newBean.getDoctype());
		}
		else
		{
			Prest = con.prepareStatement(DataQuery.MissingAccessionQuery_Acc_no_ALL);
			Prest.setString(1, newBean.getAccessno());
		}
		rs = Prest.executeQuery();
		if (rs.next()) {
		

			accno = rs.getString("access_no");
			accessno=1;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (Prest != null) {
				Prest.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return accessno;
}



public int findAccessNoSave(accessbean newBean)
{
	//log4jLogger.info("start===========Missing AccessNo Save=====");
	int count = 0;
	try {
		
		//getSession().save(newBean);
		//getSession().flush();
		
		
		con = getSession().connection();
		Prest = con.prepareStatement(DataQuery.MissingAccessNo);
		Prest.setString(1, newBean.getAccessno());
		count = Prest.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (Prest != null) {
				Prest.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return count;
}

public int findDeleteMisNo()
{
	log4jLogger.info("start===========Missing AccessNo Delete=====");
	int accessno = 0;
	try {
		con = getSession().connection();
		Prest = con.prepareStatement(DataQuery.Missingdelete);
		Prest.executeUpdate();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (Prest != null) {
				Prest.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return accessno;
}

// Auto Complete DB Report

public ArrayList<DepartmentBean> findDBReportDeptAutoSearch(String term) {
	log4jLogger.info("start===========findDBReportDeptAutoSearch=====");

	ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
	// PreparedStatement ps = null;
	// String data;

	try {
		con = getSession().connection();

		PreparedStatement ps = con
				.prepareStatement("SELECT DISTINCT(dept_name) from department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");

		ps.setString(1, "" + term + "%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			DepartmentBean user = new DepartmentBean();
			user.setName(rs.getString("dept_name"));

			list.add(user);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (Prest != null) {
				Prest.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return list;
}

public ArrayList<subjectbean> findDBReportSubjectAutoSearch(String term) {
	log4jLogger.info("start===========findDBReportSubjectAutoSearch=====");

	ArrayList<subjectbean> list = new ArrayList<subjectbean>();
	// PreparedStatement ps = null;
	// String data;

	try {
		con = getSession().connection();

		// PreparedStatement ps =
		// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
		PreparedStatement ps = con
				.prepareStatement("SELECT DISTINCT(sub_name) FROM subject_mas WHERE sub_name LIKE ? order by sub_name ASC limit 20");

		ps.setString(1, "" + term + "%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			subjectbean user = new subjectbean();

			user.setName(rs.getString("sub_name"));

			list.add(user);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (Prest != null) {
				Prest.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return list;
}

public ArrayList<PubSupBean> findDBReportPubAutoSearch(String term) {
	log4jLogger.info("start===========findDBReportPubAutoSearch=====");

	ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
	// PreparedStatement ps = null;
	// String data;

	try {
		con = getSession().connection();

		PreparedStatement ps = con
				.prepareStatement("SELECT sp_name,sp_type from sup_pub_mas WHERE sp_name LIKE ? AND sp_type = 'PUBLISHER' order by sp_name ASC limit 20");

		ps.setString(1, "" + term + "%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			PubSupBean user = new PubSupBean();

			user.setName(rs.getString("sp_name"));
			user.setType(rs.getString("sp_type"));

			list.add(user);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (Prest != null) {
				Prest.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return list;
}

public ArrayList<PubSupBean> findDBReportSupAutoSearch(String term) {
	log4jLogger.info("start===========findDBReportSupAutoSearch=====");

	ArrayList<PubSupBean> list = new ArrayList<PubSupBean>();
	// PreparedStatement ps = null;
	// String data;

	try {
		con = getSession().connection();

		PreparedStatement ps = con
				.prepareStatement("SELECT sp_name,sp_type from sup_pub_mas WHERE sp_name LIKE ? AND sp_type = 'SUPPLIER' order by sp_name ASC limit 20");

		ps.setString(1, "" + term + "%");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			PubSupBean user = new PubSupBean();

			user.setName(rs.getString("sp_name"));
			user.setType(rs.getString("sp_type"));

			list.add(user);
		}

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (Prest != null) {
				Prest.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	return list;
}


@Override
public ArrayList<DepartmentBean> findUniqueTitleReportDeptAutoSearch(
		String term, int rk) {
	log4jLogger
	.info("start===========findUniqueTitleReportDeptAutoSearch=====");

ArrayList<DepartmentBean> list = new ArrayList<DepartmentBean>();
// PreparedStatement ps = null;
// String data;

try {
con = getSession().connection();

PreparedStatement ps = con
		.prepareStatement("SELECT DISTINCT(dept_name) from department_mas WHERE branch_code='"+rk+"' AND dept_name LIKE ? order by dept_name ASC limit 20");

ps.setString(1, "" + term + "%");
ResultSet rs = ps.executeQuery();

while (rs.next()) {
	DepartmentBean user = new DepartmentBean();
	user.setName(rs.getString("dept_name"));

	list.add(user);
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
try {
	if (rs != null) {
		rs.close();
	}
	if (Prest != null) {
		Prest.close();
	}
	if (con != null) {
		con.close();
	}

} catch (Exception e) {
	e.printStackTrace();
}
}

return list;
}


@Override
public ArrayList<subjectbean> findUniqueTitleReportSubjectAutoSearch(
		String term, int rk) {
	log4jLogger
	.info("start===========findUniqueTitleReportSubjectAutoSearch=====");

ArrayList<subjectbean> list = new ArrayList<subjectbean>();
// PreparedStatement ps = null;
// String data;

try {
con = getSession().connection();

// PreparedStatement ps =
// con.prepareStatement("SELECT dept_code,dept_name,short_desc FROM department_mas WHERE dept_name LIKE ? order by dept_name ASC limit 20");
PreparedStatement ps = con
		.prepareStatement("SELECT DISTINCT(sub_name) FROM subject_mas WHERE branch_code='"+rk+"' AND sub_name LIKE ? order by sub_name ASC limit 20");

ps.setString(1, "" + term + "%");
ResultSet rs = ps.executeQuery();

while (rs.next()) {
	subjectbean user = new subjectbean();

	user.setName(rs.getString("sub_name"));

	list.add(user);
}

} catch (SQLException e) {
e.printStackTrace();
} finally {
try {
	if (rs != null) {
		rs.close();
	}
	if (Prest != null) {
		Prest.close();
	}
	if (con != null) {
		con.close();
	}

} catch (Exception e) {
	e.printStackTrace();
}
}

return list;
}





}




	
	

	
	
	





