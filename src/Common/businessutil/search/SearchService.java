package Common.businessutil.search;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.Account.AccountBean;
import Lib.Auto.EBookSearch.EBookSearchBean;
import Lib.Auto.JournalBrowse.JournalSearchbean;
import Lib.Auto.NewArrivals.NewArrivalsBean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.QueryBuilder.QueryBuilderBean;
import Lib.Auto.Simples.Searchbean;
public interface SearchService {
	
	public List getSimpleSearch(String search);
	public List getAdvancedSearch(String search);
	public List getFullViewSearch(String search);
	public List getBrowseSearch(String search);
	public List getLoadBranchList();
	
//	----------------Journal Search-----------------------	
	public List getJournalSearch(String search,int branchID);	
	public List getJournalIssueSearch(String search);
	public List getJournalFullView(String search);
	
	
//----------------Account-----------------------
	public AccountBean getCheckAccount(String uid,String pwd,int branchID);
	public List getAccountDetails(String search);
	public List getAccountDetailsIssue(String search);
	
	
	public List getAccountPaidDetails(String search);
	
	
	
	public String getOnlineRenewSave(AccountBean newbean);
	public List getAccountDetailsReserve(String search);
	public String getOnlineReserveCancel(AccountBean newbean);
	public String getChangePwd(AccountBean newbean);
	public List getRegisterLoad(AccountBean newbean);
	public int getRegisterAllLogout(int branchID);	
	public AccountBean getRegisterEntry(String search,String purpose,int branchID);
	public List getIssueDetails(String code,String doc);
	public AccountBean getReserveCheck(AccountBean newbean);
	
	//-------------------------------------------------
	
	public List getNewsClipSimpleSearch(String search);
	
	public List getEResourceSimpleSearch(String search);
	
	public List getJournalArticleSearch(String search);	
	
	
//	 Query Builder

	public List getQueryBuilderSearch(QueryBuilderBean newBean);
	
	
	
//	 Question Bank Search
	
	public List getQBSearch(String search);
	public List getFullViewQBSearch(String search);
	
	

	//--------------------New Arrivals----------------------------
	
	
	public List getMonthYearList(NewArrivalsBean bean);
	public List getSubjectList();
	public List getNewArrivalSearchResult(NewArrivalsBean newbean);
	
	
	// Ebook
	
	public ArrayList<EBookSearchBean> getEBookAutoTitleSearch(String term,int branchID);
	public ArrayList<EBookSearchBean> getEBookAutoAuthorSearch(String term,int branchID);
	public ArrayList<EBookSearchBean> getEBookAutoSubjectSearch(String term,int branchID);
	
	public List getEBookSearch(String search);
	
	//Library Login
	
	public int getRegisterAllLogout();
	
	public AccountBean getCheckAccount(String uid,String pwd);
	
	
	public ArrayList<Searchbean> getTitleSearch(String term,int branchID);
	public ArrayList<Searchbean> getAuthorSearch(String term,int branchID);
	public ArrayList<Searchbean> getSubjectSearch(String term,int branchID);
	public ArrayList<Searchbean> getAdvAutoSearch(String term, String flag,int branchID);
	public ArrayList<NewscllipingBean> getNewsclippingSubjectAutoSearch(
			String term);
	public ArrayList<NewscllipingBean> getNewsclippingKeywordsAutoSearch(
			String term);
	public ArrayList<NewscllipingBean> getNewsclippingTypeAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsclippingNameAutoSearch(String term);
	public ArrayList<NewscllipingBean> getNewsclippingTitleAutoSearch(
			String term);
	public ArrayList<JournalSearchbean> getJournalAutoSearch(String term,
			int branchID);
	public ArrayList<Searchbean> getQuickAutoSearch(String term, String flag);
	public ArrayList<Searchbean> getQueryAutoSearch(String term, String flag);
	
	// Advanced Search New
	
	public List getAdvancedSearch(String sQL_Query, String string,
			String string2, String orderQuery);
	public List getFullView(String sQL_Query, String parameter,String branch);
	

}