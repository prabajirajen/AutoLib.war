
package Common.businessutil.calaloging;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Lib.Auto.AccessionRegister.accessbean;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Binding.BindingBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Currency.CurrencyBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.City.CityBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EResource.EResourceBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.MResource.MResourceBean;
import Lib.Auto.Keywords.KeywordsBean;
import Lib.Auto.Member.MemberBean;
import Lib.Auto.MsgMas.MsgMasBean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.Photo.PhotoBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.QBSubject.QBsubjectbean;
import Lib.Auto.QuestionBank.QuestionBankBean;
import Lib.Auto.Review.ReviewBean;
import Lib.Auto.Subject.subjectbean;
import Lib.Auto.Suggestion.SuggestionBean;
import Lib.Auto.Transfer_Books.BookTransferBean;
public interface CalalogingDao {

//--------Currency Load-----------	
	public List findCurrencyLoad();
	public String findNewAccNoLoad(String doctype,int branchID);
	
	
	
//--------Author-----------	
	public AuthorBean findAuthorMax();
	public AuthorBean findAuthorSearch(int code);
	public int authorUpdate(AuthorBean authorBean);
	public int findAuthorInterface(int code);
	public int findAuthorMas(int code);
	public int authorDelete(int code);
	public int authorSave(AuthorBean authorBean);
	public int findAuthorName(AuthorBean authorBean);
	public int findAuthorNameAuthorInterface(int code);
	public int findAuthorcode(int code);
	public List findAuthorMasName(AuthorBean authorBean);
	
	//--------------Suggestion Master-------------------
			public SuggestionBean findSuggestionMax();
			public int suggestionSave(SuggestionBean sugBean);
			public SuggestionBean findSuggestionSearch(int sugNo);
			public int suggestionUpdate(SuggestionBean sugBean);
			public int suggestionDelete(int sugNo);
			public int findSuggestionCount(int sugCount);
	
	//--------------Review Master-------------------
	public ReviewBean findReviewMax();
	public int reviewSave(ReviewBean reviewBean);
	public ReviewBean findReviewSearch(int reviewNo);
	public int reviewUpdate(ReviewBean reviewBean);
	public int reviewDelete(int reviewNo);
	public List findReviewDisplay(String accessNo);
	

	
//-------Subjct---------------------------	
	
	public subjectbean findSubjectMax();
	public subjectbean findSubjectSearch(int code);
	public int subjectUpdate(subjectbean newBean);
	public int findSubjectInterface(int code);
	public int findSubjectMas(int code,int branchID);
	public int subjectDelete(int code);
	public int findSubjectName(subjectbean newBean);
	public int subjectSave(subjectbean newBean);
	public List findSubjectMasName(subjectbean newBean);
	
	
//--------------------------------------QuestionBankSubject Mas--------------------------
	
	public QBsubjectbean findQBSubjectMax();
	public int QBsubjectSave(QBsubjectbean newBean);
	public int findQBSubjectName(QBsubjectbean newBean);
	public int findQBSubjectMas(int code,int branchID);
	public int findQBSubjectInterface(int code);
	public QBsubjectbean findQBSubjectSearch(int code);
	public int QBsubjectUpdate(QBsubjectbean newBean);
	public int QBsubjectDelete(int code);
	public List findQBSubjectMasName(QBsubjectbean newBean);
	
//---------Department------------------------	
	public DepartmentBean findDeptMax();
	public DepartmentBean findDeptSearch(int code);
	public int findDeptInterface(int code);
	public int findDeptMas(int code,int branchID);
	public int deptDelete(int code);
	public int deptUpdate(DepartmentBean newBean);
	public int findDeptName(DepartmentBean newBean);
	public int deptSave(DepartmentBean newBean);
	public List findDeptSearch(DepartmentBean newBean);
	
		
//---------City------------------------	
	public CityBean findCityMax();
	public CityBean findCitySearch(int code);
	public int findCityInterface(int code);
	public int findCityMas(int code);
	public int CityDelete(int code);
	public int CityUpdate(CityBean newBean);
	public int findCityName(CityBean newBean);
	public int CitySave(CityBean newBean);
	public List findCitySearch(String newBean);
	
//	--------------------Member--------------------
	
	public MemberBean findMemberSearch(String code,int branchID);
	public MemberBean findNewMcode(String code);
	public int findMemberIdChange(String code,String changeCode,int branchID);
	public List findMemberMasSearch(MemberBean newbean);
	public List findDeptMasSearch(MemberBean newbean);
	public List findDesignationSearch(MemberBean newbean);
	public List findGroupSearch(MemberBean newbean);
	public List findCourseSearch(MemberBean newbean);
	public List findCitySearch(MemberBean newbean);
	public int findIssueMasCheck(String code);
	public int memberDelete(String code,int branchID);
	public int memberUpdate(MemberBean newbean);
	public int findMemberCheck(String code,int branchID);
	public MemberBean memberSave(MemberBean newbean);	
	public int MemberPhotoSave(PhotoBean bean);
	
//	--------------------Book--------------------
	public bookbean findBookSearch(String accno,int branchID, String doctype);	
	public String findIssueCheck(String accno);	
	public String findIssueHistoryCheck(String accno);
	public String findReserveMasCheck(String accno);
	public int findDeleteBook(String accno);
	public int findDeleteHistory(String accno);
	public int findDeleteReserve(String accno);
	public List findBookSearchTitle(String name,int branchID, String doc_type);
	public List findBookSearchAuthor(String name,int branchID);
	public List findBookSearchPub(String name,int branchID);
	public List findBookSearchSubject(String name,int branchID);
	public List findBookSearchCallNo(String name,int branchID);
	public List findBookSearchBranch(String name,int branchID);
	public List findBookSearchDept(String name,int branchID);
	public List findBookSearchBudget(String name,int branchID);
	public List findBookSearchKey(String name);
	public List findBookSearchSupplier(String name,int branchID);
	public int findBookSubCode(String subName,int branchID);
	public int findBookBranchCode(String branchName);
	public int findBookDeptCode(String deptName,int branchID);
	public int findBookSupplierCode(String supName,int branchID);
	public int findBookPubCode(String pubName,int branchID);
	public int findBookSave(bookbean newbean);
	public String findBookSaveInterfaceCheck(bookbean newbean);
	public int findBookUpdate(bookbean newbean);
	
	
	//Transfer / Re transfer Book
	
	public List findAccNoList(BookTransferBean newbean);
	public List findTransAccNoList(BookTransferBean newbean);
	public List findRandamAccNoList(String Query);
//	--------------------Course--------------------
	
	public CourseBean findCourseMax();
	
	public CourseBean findCourseSearch(int code);
	
	public int CourseUpdate(CourseBean newBean);
	
	public int findCourseInterface(int code);
	
	public int findCourseMas(int code,int branchID);
	
	public int courseDelete(int code);
	
	public int courseName(CourseBean newBean);
	
	public int courseSave(CourseBean newBean);
//	--------------------Designation--------------------	
	
	public DesignationBean findDesignationMax();
	
	public DesignationBean findDesignationSearch(int code);
	
    public int findDesigInterface(int code);
	
	public int findDesigMas(int code,int branchID);
	
	public int desigDelete(int code);
	
	public int desigName(DesignationBean newBean);
	
	public int desigSave(DesignationBean newBean);
	
	public int desigUpdate(DesignationBean newBean);
	
	public List findDesigSearch(DesignationBean newBean);
	
//	--------------------PubSup--------------------
	public PubSupBean findPubSupMax();
	
	public PubSupBean findPubSupSearch(int code);
	
   public int findPubSupInterface(int code);
	
	public int findPubSupMas(int code,int branchID);
	
	public int pubSupDelete(int code);
	
	public int pubSupName(PubSupBean newBean);
	
	public int pubSupSave(PubSupBean newBean);
	
	public int pubSupUpdate(PubSupBean newBean);
	
	public List findSupSearch(PubSupBean newBean);
	
	public List findPubSearch(PubSupBean newBean);
	
//	--------------------Branch--------------------
	public BranchBean findBranchMax();
	
	public BranchBean findBranchSearch(int code);
	
   public int findBranchInterface(int code);
	
	public int findBranchMas(int code);
	
	public int branchDelete(int code);
	
	public int branchName(BranchBean newBean);
	
	public int branchSave(BranchBean newBean);
	
	public int branchUpdate(BranchBean newBean);
	
	public List findBranchSearchName(String name,int branchID);
	
//	--------------------Currency--------------------	
	public CurrencyBean findCurrencyMax();
	
	public CurrencyBean findCurrencySearch(int code);
	
    public int findCurrencyInterface(int code);
	
	public int findCurrencyMas(int code);
	
	public int currencyDelete(int code);
	
	public int currencyName(CurrencyBean newBean);	
	
    public int currencySave(CurrencyBean newBean);
	
	public int currencyUpdate(CurrencyBean newBean);
	
	public List CurrencySearch(CurrencyBean newBean);
	
//	--------------------Keywords--------------------	
	public KeywordsBean findkeywordsMax();
	public KeywordsBean findKeywordsSearch(int code);
	public int keywordsDelete(int code);
	
	public int keywordsName(KeywordsBean newBean);
	public int findKeywordsMas(int code);
    public int keywordsSave(KeywordsBean newBean);
	
	public int keywordsUpdate(KeywordsBean newBean);
	public List keywordsSearchName(KeywordsBean newBean);
	
	//----------------Report-----------------
	//public byte[] getJasperReportToPdfDisplyDao(JasperReport jasperReport,Map parameters);
	
	
	
	//-----------------Binding--------------------
	
	public BindingBean findBindingMax();
	
	public BindingBean findBindingSearch(int code);
	
	public int BindingDelete(BindingBean codebean);
	
	public int findBindingName(String Name);
	
	public int findBindingUpdate(BindingBean codebean);
	
	public int fineBindingSave(BindingBean codebean);
	
	public List findBinderSearchName(String name);
	
	
	//-----------------NewsCllipings------------
	
	public NewscllipingBean findNewsMax();
	
	public NewscllipingBean findNewsSearch(int code,int branchID);
	
	public int findNewscliSearch(int code);
	
	public int fineNewClipSave(NewscllipingBean codebean);
	
	public int findNewsClipUpdate(NewscllipingBean codebean);
	
	public int NewsClipDelete(int code);
	
//	-----------------EResourse------------
	
	public EResourceBean findEResourceMax();
	
	public int findEResourseMas(int code);
	
	public int fineEResourseSave(EResourceBean codebean);
	
	public EResourceBean findEResourseSearch(int code,int branchID);
	
	public int findEResourceUpdate(EResourceBean codebean);
	
	public int EResourceDelete(int code);
	
//	-----------------MResourse------------
	
	public MResourceBean findMResourseMas(MResourceBean newbean);
	
	public int fineMResourseSave(MResourceBean codebean);
	
	public int fineMResourseDelete(String accno);
	
//	--------------Contents Upload----------------------------
	
	public boolean findCheckContentsNumber(String accno,String document,int branch);	
	public void findUpdateContentFile(String accno,String document,String fileName);	
	
	
//  --------------Question Bank ----------------------------
	
	public QuestionBankBean getQuestionBankMax();	
	public int getQuestionBankUpdate(QuestionBankBean codebean);	
	public QuestionBankBean getQuestionBankSearch(int code,int branchID);
    public int getQuestionBankMas(int code);	 
	public int getQuestionBankSave(QuestionBankBean codebean);	 
	public int getQuestionBankDelete(int code);
	
	//--------------------message mas-----------------------
	
			public MsgMasBean findMsgMasMax();
			public int findMsgMas(int code,int branchID);
			public int MsgMasSave(MsgMasBean newBean);
			public MsgMasBean findMsgMasSearch(int code,int branchID);
			public int MsgMasDelete(int code,int branchID);
			public int MsgMasUpdate(MsgMasBean newBean);
			public List findMsgMasSearchName(String newBean);
			public ArrayList<EBookBean> findEBookAutoAccessNoSearch(String term);
			public ArrayList<EBookBean> findEBookAutoCallNoSearch(String term);
			public ArrayList<EBookBean> findEBookAutoTitleSearch(String term);
			public ArrayList<AuthorBean> findEBookAutoAuthorSearch(String term);
			public ArrayList<PubSupBean> findEBookAutoPublisherSearch(
					String term);
			public ArrayList<PubSupBean> findEBookAutoSupplierSearch(String term);
			public ArrayList<subjectbean> findEBookAutoSubjectSearch(String term);
			public ArrayList<DepartmentBean> findEBookAutoDeptSearch(String term);
			public ArrayList<PubSupBean> findBookAutoSupplierSearch(String term);
			public EBookBean findEBookMax();
			public int ebookSave(EBookBean ebookBean);
			public int ebookUpdate(EBookBean ebookBean);
			public EBookBean findEBookSearch(String accessNo,int branchID);
			public int ebookDelete(String accessNo);
			public List findEBookSearchList(EBookBean ob);
			public List findEBookCallNoList(EBookBean ob);
			
			
			public List findEBookSearchAuthor(String name,int branchID);
			public List findEBookSearchTitle(String name,int branchID);
			public List findEBookSearchPub(String name,int branchID);
			public List findEBookSearchSup(String name,int branchID);
			public List findEBookSearchSubject(String name,int branchID);
			public List findEBookSearchCallNo(String name,int branchID);
			public List findEBookSearchDept(String name,int branchID);
			public String findEBookName(EBookBean ebookBean);
			public String findEBookAccessNo(String accessNo,int branchID);
			public MemberBean findMemberClear(String member_code,int branchID);
			
			
			public ArrayList<AuthorBean> findAuthorAutoSearch(String term,int branchID);
			public ArrayList<DepartmentBean> findDeptAutoSearch(String term,int branchID);
			public ArrayList<DesignationBean> findDesigAutoSearch(String term,int branchID);
			public ArrayList<subjectbean> findSubjectAutoSearch(String term,int branchID);			
			public ArrayList<CourseBean> findCourseAutoSearch(String term,int branchID);
			public ArrayList<PubSupBean> findPubSupAutoSearch(String term,String type, int branchID);
			public ArrayList<bookbean> findBookAutoAccessNoSearch(String term,
					String doc, int branchID);
			public ArrayList<bookbean> findBookAutoCallNoSearch(String term,
					String doc, int branchID);
			public ArrayList<bookbean> findBookAutoTitleSearch(String term,
					String doc, int branchID);
			public ArrayList<PubSupBean> findBookAutoPublisherSearch(
					String term, int branchID);
			public ArrayList<DepartmentBean> findBookAutoDeptSearch(
					String term, int branchID);
			public ArrayList<PubSupBean> findBookAutoSupplierSearch(
					String term, int branchID);
			public ArrayList<BudgetBean> findBookAutoBudgetSearch(String term,
					int branchID);
			public ArrayList<KeywordsBean> findBookAutoKeywordsSearch(
					String term, int branchID);
			public ArrayList<AuthorBean> findBookAutoAuthorSearch(String term,
					int branchID);
			public ArrayList<subjectbean> findBookAutoSubjectSearch(
					String term, int branchID);
			public ArrayList<CityBean> findMemberAutoCitySearch(String term,
					int branchID);
			public ArrayList<CourseBean> findMemberAutoCourseSearch(
					String term, int branchID);
			public ArrayList<GroupBean> findMemberAutoGroupSearch(String term,
					int branchID);
			public ArrayList<DepartmentBean> findMemberAutoDeptSearch(
					String term, int branchID);
			public ArrayList<DesignationBean> findMemberAutoDesigSearch(
					String term, int branchID);
			public ArrayList<MemberBean> findMemberAutoNameSearch(String term,
					int branchID);
			public ArrayList<MemberBean> findMemberAutoIdSearch(String term,
					int branchID);
			
			
	
}
