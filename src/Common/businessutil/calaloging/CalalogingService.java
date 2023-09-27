package Common.businessutil.calaloging;

import java.util.ArrayList;
import java.util.List;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Binding.BindingBean;
import Lib.Auto.Book.bookbean;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.City.CityBean;
import Lib.Auto.Course.CourseBean;
import Lib.Auto.Currency.CurrencyBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Designation.DesignationBean;
import Lib.Auto.EBook.EBookBean;
import Lib.Auto.EResource.EResourceBean;
import Lib.Auto.Group.GroupBean;
import Lib.Auto.Keywords.KeywordsBean;
import Lib.Auto.MResource.MResourceBean;
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
public interface CalalogingService {
	
//---------------Currency load----------------
	
	public List getCurrencyLoad();
	public String getNewAccNoLoad(String doctype,int branchID);
	
	
	
	
	
//  ----------------------Author----------	
	
	public AuthorBean getAuthorMax();
	
	public AuthorBean getAuthorSearch(int code);
	
	public int getAuthorUpdate(AuthorBean authorBean);
	
	public int getAuthorInterface(int code);
	
	public int getAuthorMas(int code);

	public int getAuthorDelete(int code);

	public int getAuthorSave(AuthorBean authorBean);
	
	public int getAuthorName(AuthorBean authorBean);
	
	public int getAuthorNameAuthorInterface(int code);
	
	public int getAuthorCode(int code);
	
	public List getAuthorSearchName(AuthorBean authorBean);
	
//  ----------------Suggestion Master-------------
		public SuggestionBean getSuggestionMax();
		public int getSuggestionSave(SuggestionBean sugBean);
		public SuggestionBean getSuggestionSearch(int sugNo);
		public int getSuggestionUpdate(SuggestionBean sugBean);
		public int getSuggestionDelete(int sugNo);
		public int getSuggestionCount(int sugCount);
	
//  -----------------Review Master------------------
		public ReviewBean getReviewMax();
		public int getReviewSave(ReviewBean reviewBean);
		public ReviewBean getReviewSearch(int reviewNo);
		public int getReviewUpdate(ReviewBean reviewBean);
		public int getReviewDelete(int reviewNo);
		public List getReviewDisplay(String accessNo);
		
	
	
//  ----------------------Subject----------
	
	public subjectbean getSubjectMax();
	
	public subjectbean getSubjectSearch(int code);
	
	public int getSubjectUpdate(subjectbean newBean);
	
	public int getSubjectInterface(int code);
	
	public int getSubjectMas(int code,int branchID);
	
	public int getSubjectDelete(int code);
	
	public int getSubjectName(subjectbean newBean);
	
	public int getSubjectSave(subjectbean newBean);
	
	public List getSubjectSearchName(subjectbean newBean);
	
	
	//--------------------------------------QuestionBankSubject Mas--------------------------
	
	public QBsubjectbean getQBSubjectMax();
	
    public int getQBSubjectSave(QBsubjectbean newBean);
    
	public int getQBSubjectName(QBsubjectbean newBean);
	
    public int getQBSubjectMas(int code,int branchID);
	
	public int getQBSubjectInterface(int code);
	
	public QBsubjectbean getQBSubjectSearch(int code);
	
	public int getQBSubjectUpdate(QBsubjectbean newBean);

	public int getQBSubjectDelete(int code);
	
	public List getQBSubjectSearchName(QBsubjectbean newBean);

//--------------------Department--------------------	
	
	public DepartmentBean getDeptMax();
	
	public DepartmentBean getDeptSearch(int code);
	
	public int getDeptInterface(int code);
	
	public int getDeptMas(int code,int branchID);
	
	public int getDeptDelete(int code);
	
	public int getDeptUpdate(DepartmentBean newBean);
	
	public int getDeptName(DepartmentBean newBean);
	
	public int getDeptSave(DepartmentBean newBean);
	
	public List getDeptSearchName(DepartmentBean newBean);
	
	
	//--------------------City--------------------	
	
	public CityBean getCityMax();
	
	public CityBean getCitySearch(int code);
	
	public int getCityInterface(int code);
	
	public int getCityMas(int code);
	
	public int getCityDelete(int code);
	
	public int getCityUpdate(CityBean newBean);
	
	public int getCityName(CityBean newBean);
	
	public int getCitySave(CityBean newBean);
	
	public List getCitySearchName(String newBean);
	
	
//	--------------------Member--------------------
	
	public MemberBean getMemberSearch(String code,int branchID);
	
	public int getMemberIdChange(String code,String changeCode,int branchID);
	
	public MemberBean getNewMcode(String code);
	
	public List getMemberMasSearch(MemberBean newbean);
	
	public List getDeptMasSearch(MemberBean newbean);
	
	public List getDesignationSearch(MemberBean newbean);
	
	public List getGroupSearch(MemberBean newbean);
	
	public List getCourseSearch(MemberBean newbean);
	
	public List getCitySearch(MemberBean newbean);
	
	public int getIssueMasCheck(String code);
	
	public int getMemberDelete(String code,int branchID);
	
	public int getMemberUpdate(MemberBean newbean);
	
	public int getMemberCheck(String code,int branchID);
	
	public MemberBean getMemberSave(MemberBean newbean);
	
	public int getMemberPhotoSave(PhotoBean bean);
	
//	--------------------BOOK--------------------
	public bookbean getBookSearch(String accno,int branchID,String doctype);
	
	public String getIssueCheck(String accno);
	
	public String getIssueHistoryCheck(String accno);
	
	public String getReserveMasCheck(String accno);
	
	public int getDeleteBook(String accno);
	
	public int getDeleteHistory(String accno);
	
	public int getDeleteReserve(String accno);
	
	public List getBookSearchTitle(String name,int branchID, String doc_type);
	
	public List getBookSearchAuthor(String name,int branchID);
	
	public List getBookSearchPub(String name,int branchID);
	
	public List getBookSearchSubject(String name,int branchID);
	
	public List getBookSearchCallNo(String name,int branchID);
	
	public List getBookSearchBranch(String name,int branchID);
	
	public List getBookSearchDept(String name,int branchID);
	
	public List getBookSearchBudget(String name,int branchID);
	
	public List getBookSearchKey(String name);
	
	public List getBookSearchSupplier(String name,int branchID);
	
	public int getBookSubCode(String subName,int branchID);
	
	public int getBookBranchCode(String branchName);
	
	public int getBookDeptCode(String deptName,int branchID);
	
	public int getBookSupplierCode(String supName,int branchID);
	
	public int getBookPubCode(String pubName,int branchID);
	
	public int getBookSave(bookbean newbean);
	
	public String getBookSaveInterfaceCheck(bookbean newbean);
	
	public int getBookUpdate(bookbean newbean);
	
	
	//Transfer / Re transfer Book
	
	public List getAccNoList(BookTransferBean newbean);
	public List getTransAccNoList(BookTransferBean newbean);
	public List getRandamAccNoList(String Query);
	
	
	
//	--------------------Course--------------------
	public CourseBean getCourseMax();
	
	public CourseBean getCourseSearch(int code);
	
	public int getCourseUpdate(CourseBean newBean);
	
    public int getCourseInterface(int code);
	
	public int getCourseMas(int code,int branchID);
	
	public int getCourseDelete(int code);
	
	public int getCourseName(CourseBean newBean);	
	
	public int getCourseSave(CourseBean newBean);
	
//	--------------------Designation--------------------	
	
	public DesignationBean getDesignationMax();
	
	public DesignationBean getDesignationSearch(int code);
	
	public int getDesigInterface(int code);
	
	public int getDesigMas(int code,int branchID);
	
	public int getDesigDelete(int code);
	
	public int getDesigName(DesignationBean newBean);
	
	public int getDesigSave(DesignationBean newBean);
	
	public int getDesigUpdate(DesignationBean newBean);
	
	public List getDesigSearchName(DesignationBean newBean);
	
//	--------------------PubSup--------------------	
	
	public PubSupBean getPubSupMax();
	
	public PubSupBean getPubSupSearch(int code);
	
    public int getPubSupInterface(int code);
	
	public int getPubSupMas(int code,int branchID);
	
	public int getPubSupDelete(int code);
	
	public int getPubSupName(PubSupBean newBean);
	
	public int getPubSupSave(PubSupBean newBean);
	
	public int getPubSupUpdate(PubSupBean newBean);
	
	public List getSupSearchName(PubSupBean newBean);
	
	public List getPubSearchName(PubSupBean newBean);
	
	
//	--------------------Branch--------------------
	public BranchBean getBranchMax();
	
	public BranchBean getBranchSearch(int code);
	
    public int getBranchInterface(int code);
	
	public int getBranchMas(int code);
	
	public int getBranchDelete(int code);
	
	public int getBranchName(BranchBean newBean);
	
	public int getBranchSave(BranchBean newBean);
	
	public int getBranchUpdate(BranchBean newBean);
	
	public List getBranchSearchName(String name,int branchID);
	
//	--------------------Currency--------------------
	
	public CurrencyBean getCurrencyMax();
	
	public CurrencyBean getCurrencySearch(int code);
	
    public int getCurrencyInterface(int code);
	
	public int getCurrencyMas(int code);
	
	public int getCurrencyDelete(int code);
	
	public int getCurrencyName(CurrencyBean newBean);
	
    public int getCurrencySave(CurrencyBean newBean);
	
	public int getCurrencyUpdate(CurrencyBean newBean);
	
	public List getCurrencySearchName(CurrencyBean newBean);
	
//	--------------------Keywords--------------------
	
	public KeywordsBean getKeywordsMax();
	
	public KeywordsBean getKeywordsSearch(int code);
	
	public int getKeywordsDelete(int code);
	
	public int getKeywordsName(KeywordsBean newBean);
	
	public int getKeywordsMas(int code);
	
    public int getKeywordsSave(KeywordsBean newBean);
	
	public int getKeywordsUpdate(KeywordsBean newBean);
	
	public List getKeywordsSearchName(KeywordsBean newBean);
	
	//---------------Report-----------------------------
	
	//public byte[] getJasperReportToPdfDisply(JasperReport jasperReport,Map parameters);
	
	
	
	//------------Binding------------------------------
	
	public BindingBean getBindingMax();
	
	public BindingBean getBindingSearch(int code);
	
	public int getBindingDelete(BindingBean codebean);
	
	public int getBindingName(String Name);
	
	public int getBindingUpdate(BindingBean codebean);
	
	public int getBindingSave(BindingBean codebean);
	
	public List getBinderSearchName(String name);
	
	
	
	//--------------NewsCllipings----------------------------
	
	
     public NewscllipingBean getNewsMax();
	
	 public NewscllipingBean getNewsSearch(int code,int branchID);
	
	 public int getNewsCliMas(int code);
	 
	 public int getNewClipSave(NewscllipingBean codebean);
	 
	 public int getNewsClipDelete(int code);
	
	//public int getBindingName(String Name);
	
	 public int getNewsClipUpdate(NewscllipingBean codebean);
	
	//public int getBindingSave(BindingBean codebean);
	
	//public List getBinderSearchName(String name);*/
	
	
//	--------------EResource----------------------------
	
	
	public EResourceBean getEResourceMax();
	
	public int getEResourseMas(int code);
	
	public int getEResourseSave(EResourceBean codebean);
	 
	public EResourceBean getEResourseSearch(int code,int branchID);
	 
	public int getEResourceUpdate(EResourceBean codebean);
	 
	public int getEResourceDelete(int code);


//--------------MResource----------------------------

    public MResourceBean getMResourseMas(MResourceBean newbean);
    public int getMResourseSave(MResourceBean codebean);
    public int getMResourseDelete(String accno);


//  --------------Contents Upload----------------------------

    public boolean getCheckContentsNumber(String accno,String document,int branch);
    public void getUpdateContentFile(String accno,String document,String fileName);


//  --------------Question Bank ----------------------------
	
	
    public QuestionBankBean getQuestionBankMax();
    public int getQuestionBankUpdate(QuestionBankBean codebean);
    public QuestionBankBean getQuestionBankSearch(int code,int branchID);    
    public int getQuestionBankMas(int code);	 
	public int getQuestionBankSave(QuestionBankBean codebean);	 
	public int getQuestionBankDelete(int code);

	//-----------------message master--------------------
		public MsgMasBean getMsgMasMax();
		public int getMsgMas(int code,int branchID);
		public int getMsgMasSave(MsgMasBean newBean);
		public MsgMasBean getMsgMasSearch(int code,int branchID);
		public int getMsgMasDelete(int code,int branchID);
		public int getMsgMasUpdate(MsgMasBean newBean);
		public List getMsgMasSearchName(String newBean);
		

		// Ebook
		
		//--------------------Autocomplete-------------------
		
		public ArrayList<EBookBean> getEBookAutoAccessNoSearch(String term);
		public ArrayList<EBookBean> getEBookAutoCallNoSearch(String term);
		public ArrayList<EBookBean> getEBookAutoTitleSearch(String term);
		public ArrayList<AuthorBean> getEBookAutoAuthorSearch(String term);
		public ArrayList<PubSupBean> getEBookAutoPublisherSearch(String term);
		public ArrayList<PubSupBean> getEBookAutoSupplierSearch(String term);
		public ArrayList<subjectbean> getEBookAutoSubjectSearch(String term);
		public ArrayList<DepartmentBean> getEBookAutoDeptSearch(String term);
		public ArrayList<PubSupBean> getBookAutoSupplierSearch(String term);
		
		
		public EBookBean getEBookMax();
		
		public EBookBean getEBookSearch(String accessNo,int branchID);
		public int getEBookSave(EBookBean ebookBean);
		public int getEBookUpdate(EBookBean ebookBean);
		public int getEBookDelete(String accessNo);
		public String getEBookName(EBookBean ebookBean);
		public String getEBookAccessNo(String accessNo,int branchID);
		public List getEBookSearchList(EBookBean ob);
		public List getEBookCallNoList(EBookBean ob);
		
		public List getEBookSearchTitle(String name,int branchID);
		//public List getEBookSearchTitle(EBookBean ob);
		public List getEBookSearchAuthor(String name,int branchID);
		public List getEBookSearchPub(String name,int branchID);
		public List getEBookSearchSup(String name,int branchID);
		public List getEBookSearchSubject(String name,int branchID);
		public List getEBookSearchCallNo(String name,int branchID);
		public List getEBookSearchDept(String name,int branchID);
		public MemberBean getMemberClear(String member_code,int branchID);
		
		
		public ArrayList<AuthorBean> getAuthorAutoSearch(String term,int branchID);
		public ArrayList<DepartmentBean> getDeptAutoSearch(String term,int branchID);		
		public ArrayList<DesignationBean> getDesigAutoSearch(String term,int branchID);
		public ArrayList<subjectbean> getSubjectAutoSearch(String term,int branchID);
		public ArrayList<CourseBean> getCourseAutoSearch(String term,int branchID);
		public ArrayList<PubSupBean> getPubSupAutoSearch(String term,String type, int branchID);
		public ArrayList<bookbean> getBookAutoAccessNoSearch(String term,
				String doc, int branchID);
		public ArrayList<bookbean> getBookAutoCallNoSearch(String term,
				String doc, int branchID);
		public ArrayList<bookbean> getBookAutoTitleSearch(String term,
				String doc, int branchID);
		public ArrayList<PubSupBean> getBookAutoPublisherSearch(String term,
				int branchID);
		public ArrayList<subjectbean> getBookAutoSubjectSearch(String term,
				int branchID);
		public ArrayList<DepartmentBean> getBookAutoDeptSearch(String term,
				int branchID);
		public ArrayList<PubSupBean> getBookAutoSupplierSearch(String term,
				int branchID);
		public ArrayList<BudgetBean> getBookAutoBudgetSearch(String term,
				int branchID);
		public ArrayList<KeywordsBean> getBookAutoKeywordsSearch(String term,
				int branchID);
		public ArrayList<AuthorBean> getBookAutoAuthorSearch(String term,
				int branchID);
		public ArrayList<CityBean> getMemberAutoCitySearch(String term,
				int branchID);
		public ArrayList<CourseBean> getMemberAutoCourseSearch(String term,
				int branchID);
		public ArrayList<GroupBean> getMemberAutoGroupSearch(String term,
				int branchID);
		public ArrayList<DepartmentBean> getMemberAutoDeptSearch(String term,
				int branchID);
		public ArrayList<DesignationBean> getMemberAutoDesigSearch(String term,
				int branchID);
		public ArrayList<MemberBean> getMemberAutoNameSearch(String term,
				int branchID);
		public ArrayList<MemberBean> getMemberAutoIdSearch(String term,
				int branchID);
	
	

}
