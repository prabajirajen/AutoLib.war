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

public interface SearchDao {
	
	
	public List findSimpleSearch(String search);
	public List findAdvancedSearch(String search);
	public List findFullViewSearch(String search);
	public List findBrowseSearch(String search);
	public List findLoadBranchList();
	
//	 ----------------- Journal Search ---------------
	public List findJournalSearch(String search,int branchID);
	public List findJournalIssueSearch(String search);	
	public List findJournalFullView(String search);		
	
//-------------------Account------------------	
	public AccountBean findCheckAccount(String uid,String pwd);
	public AccountBean findCheckAccount(String uid,String pwd,int branchID);
	public List findAccountDetails(String search);
	
	public List findAccountpaymentDetails(String search);
	
	
	public List findAccountDetailsIssue(String search);
	public String findOnlineRenewSave(AccountBean newbean);
	public List findAccountDetailsReserve(String search);
	public String findOnlineReserveCancel(AccountBean newbean);
	public String findChangePwd(AccountBean newbean);
	public List findRegisterLoad(AccountBean newbean);
	public int findRegisterAllLogout(int branchID);
	public AccountBean findRegisterEntry(String search,String purpose,int branchID);
	public List findIssueDetails(String code,String doc);
	public AccountBean findReserveCheck(AccountBean newbean);
	
	//------------------------------------------------
	
	public List findNewsClipSimpleSearch(String search);
	
	public List findEResourceSimpleSearch(String search);
	
	public List findJournalArticleSearch(String search);
	
	
	// Query Builder

		public List getQueryBuilderSearch(QueryBuilderBean newBean);

	
//	 Question Bank Search
	
	public List getQBSearch(String search);	
	public List findFullViewQBSearch(String search);
	

	//-----------------------NewArrivals-------------------
	
	
	public List findMonthYearList(NewArrivalsBean bean);
	public List findSubjectList();
	public List findNewArrivalSearchResult(NewArrivalsBean newbean);
	public ArrayList<EBookSearchBean> findEBookAutoTitleSearch(String term,int branchID);
	public ArrayList<EBookSearchBean> findEBookAutoAuthorSearch(String term,int branchID);
	public ArrayList<EBookSearchBean> findEBookAutoSubjectSearch(String term,int branchID);
	public List findEBookSearch(String search);
	public int findRegisterAllLogout();
	public ArrayList<Searchbean> findTitleSearch(String term,int branchID);
	public ArrayList<Searchbean> findAuthorSearch(String term,int branchID);
	public ArrayList<Searchbean> findSubjectSearch(String term,int branchID);
	public ArrayList<Searchbean> findAdvAutoSearch(String term,String flag,int branchID);
	public ArrayList<NewscllipingBean> findNewsclippingSubjectAutoSearch(
			String term);
	public ArrayList<NewscllipingBean> findNewsclippingKeywordsAutoSearch(
			String term);
	public ArrayList<NewscllipingBean> findNewsclippingTypeAutoSearch(
			String term);
	public ArrayList<NewscllipingBean> findNewsclippingNameAutoSearch(
			String term);
	public ArrayList<NewscllipingBean> findNewsclippingTitleAutoSearch(
			String term);
	public ArrayList<JournalSearchbean> findJournalAutoSearch(String term,
			int branchID);
	public ArrayList<Searchbean> findQuickAutoSearch(String term, String flag);
	public ArrayList<Searchbean> findQueryAutoSearch(String term, String flag);
	public List findFullView(String search, String document,String branch);
	public List findAdvancedSearch(String search, String document, String order);
	
}