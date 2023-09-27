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
public class CalalogingServiceImpl implements CalalogingService {
	
	private CalalogingDao calalogingDao;

	public CalalogingServiceImpl() {
	}
	//===============Currency Load================
	public List getCurrencyLoad() {
		
		return calalogingDao.findCurrencyLoad();
	}
	
	public String getNewAccNoLoad(String doctype,int branchID) {
		return calalogingDao.findNewAccNoLoad(doctype,branchID);
	}
	
	
//------------------Author---------------------	d

	
	public AuthorBean getAuthorMax() {
		return calalogingDao.findAuthorMax();
	}

	public AuthorBean getAuthorSearch(int code) {
		return calalogingDao.findAuthorSearch(code);
	}

	public int getAuthorUpdate(AuthorBean authorBean) {
		return calalogingDao.authorUpdate(authorBean);
	}

	public int getAuthorInterface(int code) {
		
		return calalogingDao.findAuthorInterface(code);
	}

public int getAuthorMas(int code) {
		
		return calalogingDao.findAuthorMas(code);
	}

public int getAuthorDelete(int code) {
	
	return calalogingDao.authorDelete(code);
}
	
public int getAuthorSave(AuthorBean authorBean) {
	
	return calalogingDao.authorSave(authorBean);
}
	
public int getAuthorName(AuthorBean authorBean) {
	
	return calalogingDao.findAuthorName(authorBean);
}

public int getAuthorNameAuthorInterface(int code) {
	
	return calalogingDao.findAuthorNameAuthorInterface(code);
}

public int getAuthorCode(int code) {
	
	return calalogingDao.findAuthorcode(code);
}

public List getAuthorSearchName(AuthorBean authorBean) {
	
	return calalogingDao.findAuthorMasName(authorBean);
}

//---------------Suggestion Master---------------------------
public SuggestionBean getSuggestionMax() {
	return calalogingDao.findSuggestionMax();
}

public int getSuggestionSave(SuggestionBean sugBean) {
	
	return calalogingDao.suggestionSave(sugBean);
}

public SuggestionBean getSuggestionSearch(int sugNo) {
	return calalogingDao.findSuggestionSearch(sugNo);
}

public int getSuggestionUpdate(SuggestionBean sugBean) {
	return calalogingDao.suggestionUpdate(sugBean);
}

public int getSuggestionDelete(int sugNo) {
	
	return calalogingDao.suggestionDelete(sugNo);
}

public int getSuggestionCount(int sugCount) {
	
	return calalogingDao.findSuggestionCount(sugCount);
}

//---------------Review Master---------------------------
public ReviewBean getReviewMax() {
	return calalogingDao.findReviewMax();
}

public int getReviewSave(ReviewBean reviewBean) {
	
	return calalogingDao.reviewSave(reviewBean);
}

public ReviewBean getReviewSearch(int reviewNo) {
	return calalogingDao.findReviewSearch(reviewNo);
}

public int getReviewUpdate(ReviewBean reviewBean) {
	return calalogingDao.reviewUpdate(reviewBean);
}

public int getReviewDelete(int reviewNo) {
	
	return calalogingDao.reviewDelete(reviewNo);
}

public List getReviewDisplay(String accessNo) {
	 return calalogingDao.findReviewDisplay(accessNo);
} 


//-------------------Subject----------------------------------

    public subjectbean getSubjectMax() {
	return calalogingDao.findSubjectMax();
}

    public subjectbean getSubjectSearch(int code) {
		return calalogingDao.findSubjectSearch(code);
	}

    public int getSubjectUpdate(subjectbean newBean) {
		return calalogingDao.subjectUpdate(newBean);
	}


public int getSubjectInterface(int code) {
		
		return calalogingDao.findSubjectInterface(code);
	}

public int getSubjectMas(int code,int branchID) {
		
		return calalogingDao.findSubjectMas(code,branchID);
	}

    
public int getSubjectDelete(int code) {
	
	return calalogingDao.subjectDelete(code);
}
    
public int getSubjectName(subjectbean newBean) {
	
	return calalogingDao.findSubjectName(newBean);
} 
    
public int getSubjectSave(subjectbean newBean) {
	
	return calalogingDao.subjectSave(newBean);
}
    
public List getSubjectSearchName(subjectbean newBean) {
	
	return calalogingDao.findSubjectMasName(newBean);
} 
//--------------------------------------QuestionBankSubject Mas--------------------------

public QBsubjectbean getQBSubjectMax() {
	return calalogingDao.findQBSubjectMax();//111111111
}

public int getQBSubjectSave(QBsubjectbean newBean) {

return calalogingDao.QBsubjectSave(newBean);
}

public int getQBSubjectName(QBsubjectbean newBean) {

return calalogingDao.findQBSubjectName(newBean);//1111111111
}

public int getQBSubjectMas(int code,int branchID) {
	
	return calalogingDao.findQBSubjectMas(code,branchID);
}

public int getQBSubjectInterface(int code) {

return calalogingDao.findQBSubjectInterface(code);
}

public QBsubjectbean getQBSubjectSearch(int code) {
	return calalogingDao.findQBSubjectSearch(code);
}

public int getQBSubjectUpdate(QBsubjectbean newBean) {
	return calalogingDao.QBsubjectUpdate(newBean);
}

public int getQBSubjectDelete(int code) {

return calalogingDao.QBsubjectDelete(code);
}

public List getQBSubjectSearchName(QBsubjectbean newBean) {

return calalogingDao.findQBSubjectMasName(newBean);
} 



//---------------------Department----------------------------------


public DepartmentBean getDeptMax() {
	return calalogingDao.findDeptMax();
}

public DepartmentBean getDeptSearch(int code) {
	return calalogingDao.findDeptSearch(code);
}



public int getDeptInterface(int code) {
	
	return calalogingDao.findDeptInterface(code);
}

public int getDeptMas(int code,int branchID) {
	
	return calalogingDao.findDeptMas(code,branchID);
}

public int getDeptDelete(int code) {
	
	return calalogingDao.deptDelete(code);
}

public int getDeptUpdate(DepartmentBean newBean) {
	return calalogingDao.deptUpdate(newBean);
}
public int getDeptName(DepartmentBean newBean) {
	
	return calalogingDao.findDeptName(newBean);
} 

public int getDeptSave(DepartmentBean newBean) {
	
	return calalogingDao.deptSave(newBean);
}

public List getDeptSearchName(DepartmentBean newBean) {
	
	return calalogingDao.findDeptSearch(newBean);
} 


//---------------------City----------------------------------


public CityBean getCityMax() {
	return calalogingDao.findCityMax();
}

public CityBean getCitySearch(int code) {
	return calalogingDao.findCitySearch(code);
}



public int getCityInterface(int code) {
	
	return calalogingDao.findCityInterface(code);
}

public int getCityMas(int code) {
	
	return calalogingDao.findCityMas(code);
}

public int getCityDelete(int code) {
	
	return calalogingDao.CityDelete(code);
}

public int getCityUpdate(CityBean newBean) {
	return calalogingDao.CityUpdate(newBean);
}
public int getCityName(CityBean newBean) {
	
	return calalogingDao.findCityName(newBean);
} 

public int getCitySave(CityBean newBean) {
	
	return calalogingDao.CitySave(newBean);
}

public List getCitySearchName(String newBean) {
	
	return calalogingDao.findCitySearch(newBean);
} 


//--------------------Member-----------------------------------

public MemberBean getMemberSearch(String code,int branchID) {
	return calalogingDao.findMemberSearch(code,branchID);
}

public MemberBean getNewMcode(String code) {
	return calalogingDao.findNewMcode(code);
}


public int getMemberIdChange(String code,String changeCode,int branchID) {
	return calalogingDao.findMemberIdChange(code,changeCode,branchID);
}


public List getMemberMasSearch(MemberBean newbean) {
	return calalogingDao.findMemberMasSearch(newbean);
}
public List getDeptMasSearch(MemberBean newbean) {
	return calalogingDao.findDeptMasSearch(newbean);
}
/**
 * 
 */
public List getDesignationSearch(MemberBean newbean) {
	return calalogingDao.findDesignationSearch(newbean);
}

public List getGroupSearch(MemberBean newbean) {
	return calalogingDao.findGroupSearch(newbean);
}

public List getCourseSearch(MemberBean newbean) {
	return calalogingDao.findCourseSearch(newbean);
}

public List getCitySearch(MemberBean newbean) {
	return calalogingDao.findCitySearch(newbean);
}


public int getIssueMasCheck(String code) {
	return calalogingDao.findIssueMasCheck(code);
}

public int getMemberDelete(String code,int branchID) {
	return calalogingDao.memberDelete(code,branchID);
}

public int getMemberUpdate(MemberBean newbean) {
	return calalogingDao.memberUpdate(newbean);
}

public int getMemberCheck(String code,int branchID) {
	return calalogingDao.findMemberCheck(code,branchID);
}

public MemberBean getMemberSave(MemberBean newbean) {
	return calalogingDao.memberSave(newbean);
}

public int getMemberPhotoSave(PhotoBean bean) {
	return calalogingDao.MemberPhotoSave(bean);
}

//--------------------BOOK--------------------
public bookbean getBookSearch(String accno,int branchID,String doctype) {
	return calalogingDao.findBookSearch(accno,branchID,doctype);
}

public String getIssueCheck(String accno) {
	return calalogingDao.findIssueCheck(accno);
}
public String getIssueHistoryCheck(String accno) {
	return calalogingDao.findIssueHistoryCheck(accno);
}
public String getReserveMasCheck(String accno) {
	return calalogingDao.findReserveMasCheck(accno);
}
public int getDeleteBook(String accno) {
	return calalogingDao.findDeleteBook(accno);
}
public int getDeleteHistory(String accno) {
	return calalogingDao.findDeleteHistory(accno);
}
public int getDeleteReserve(String accno) {
	return calalogingDao.findDeleteReserve(accno);
}

public List getBookSearchTitle(String name,int branchID, String doc_type) {
	return calalogingDao.findBookSearchTitle(name,branchID,doc_type);
}
public List getBookSearchAuthor(String name,int branchID) {
	return calalogingDao.findBookSearchAuthor(name,branchID);
}
public List getBookSearchPub(String name,int branchID) {
	return calalogingDao.findBookSearchPub(name,branchID);
}
public List getBookSearchSubject(String name,int branchID) {
	return calalogingDao.findBookSearchSubject(name,branchID);
}

public List getBookSearchCallNo(String name,int branchID) {
	return calalogingDao.findBookSearchCallNo(name,branchID);
}


public List getBookSearchBranch(String name,int branchID) {
	return calalogingDao.findBookSearchBranch(name,branchID);
}
public List getBookSearchDept(String name,int branchID) {
	return calalogingDao.findBookSearchDept(name,branchID);
}
public List getBookSearchBudget(String name,int branchID) {
	return calalogingDao.findBookSearchBudget(name,branchID);
}
public List getBookSearchKey(String name) {
	return calalogingDao.findBookSearchKey(name);
}
public List getBookSearchSupplier(String name,int branchID) {
	return calalogingDao.findBookSearchSupplier(name,branchID);
}

public int getBookSubCode(String subName,int branchID) {
	return calalogingDao.findBookSubCode(subName,branchID);
}

public int getBookBranchCode(String branchName) {
	return calalogingDao.findBookBranchCode(branchName);
}
public int getBookDeptCode(String deptName,int branchID) {
	return calalogingDao.findBookDeptCode(deptName,branchID);
}
public int getBookSupplierCode(String supName,int branchID) {
	return calalogingDao.findBookSupplierCode(supName,branchID);
}
public int getBookPubCode(String pubName,int branchID) {
	return calalogingDao.findBookPubCode(pubName,branchID);
}
public int getBookSave(bookbean newbean) {
	return calalogingDao.findBookSave(newbean);
}
public String getBookSaveInterfaceCheck(bookbean newbean) {
	return calalogingDao.findBookSaveInterfaceCheck(newbean);
}
public int getBookUpdate(bookbean newbean) {
	return calalogingDao.findBookUpdate(newbean);
}

//Transfer / Re transfer Book

public List getAccNoList(BookTransferBean newbean) {
	return calalogingDao.findAccNoList(newbean);
}

public List getTransAccNoList(BookTransferBean newbean) {
	return calalogingDao.findTransAccNoList(newbean);
}

public List getRandamAccNoList(String Query) {
	return calalogingDao.findRandamAccNoList(Query);
}

//--------------------Course--------------------
public CourseBean getCourseMax() {
	return calalogingDao.findCourseMax();
}

public CourseBean getCourseSearch(int code) {
	return calalogingDao.findCourseSearch(code);
}

public int getCourseUpdate(CourseBean newBean) {
	return calalogingDao.CourseUpdate(newBean);
}

public int getCourseInterface(int code) {
	
	return calalogingDao.findCourseInterface(code);
}

public int getCourseMas(int code,int branchID) {
	
	return calalogingDao.findCourseMas(code, branchID);
}

public int getCourseDelete(int code) {
	
	return calalogingDao.courseDelete(code);
}

public int getCourseName(CourseBean newBean) {
	
	return calalogingDao.courseName(newBean);
}

public int getCourseSave(CourseBean newBean) {
	
	return calalogingDao.courseSave(newBean);
}

//--------------------Designation--------------------

public DesignationBean getDesignationMax() {
	return calalogingDao.findDesignationMax();
}
public DesignationBean getDesignationSearch(int code) {
	return calalogingDao.findDesignationSearch(code);
}


public int getDesigInterface(int code) {
	
	return calalogingDao.findDesigInterface(code);
}

public int getDesigMas(int code,int branchID) {
	
	return calalogingDao.findDesigMas(code,branchID);
}

public int getDesigDelete(int code) {
	
	return calalogingDao.desigDelete(code);
}

public int getDesigName(DesignationBean newBean) {
	
	return calalogingDao.desigName(newBean);
}

public int getDesigSave(DesignationBean newBean) {
	
	return calalogingDao.desigSave(newBean);
}

public int getDesigUpdate(DesignationBean newBean) {
	return calalogingDao.desigUpdate(newBean);
}

public List getDesigSearchName(DesignationBean newBean) {
	
	return calalogingDao.findDesigSearch(newBean);
} 

//--------------------PubSup--------------------

public PubSupBean getPubSupMax() {
	return calalogingDao.findPubSupMax();
}

public PubSupBean getPubSupSearch(int code) {
	return calalogingDao.findPubSupSearch(code);
}

public int getPubSupInterface(int code) {
	
	return calalogingDao.findPubSupInterface(code);
}

public int getPubSupMas(int code,int branchID) {
	
	return calalogingDao.findPubSupMas(code,branchID);
}
public int getPubSupDelete(int code) {
	
	return calalogingDao.pubSupDelete(code);
}

public int getPubSupName(PubSupBean newBean) {
	
	return calalogingDao.pubSupName(newBean);
}
public int getPubSupSave(PubSupBean newBean) {
	
	return calalogingDao.pubSupSave(newBean);
}

public int getPubSupUpdate(PubSupBean newBean) {
	return calalogingDao.pubSupUpdate(newBean);
}

public List getSupSearchName(PubSupBean newBean) {
	
	return calalogingDao.findSupSearch(newBean);
} 
public List getPubSearchName(PubSupBean newBean) {
	
	return calalogingDao.findPubSearch(newBean);
} 

//--------------------Branch--------------------

public BranchBean getBranchMax() {
	return calalogingDao.findBranchMax();
}

public BranchBean getBranchSearch(int code) {
	return calalogingDao.findBranchSearch(code);
}

public int getBranchInterface(int code) {
	
	return calalogingDao.findBranchInterface(code);
}

public int getBranchMas(int code) {
	
	return calalogingDao.findBranchMas(code);
}

public int getBranchDelete(int code) {
	
	return calalogingDao.branchDelete(code);
}

public int getBranchName(BranchBean newBean) {
	
	return calalogingDao.branchName(newBean);
}

public int getBranchSave(BranchBean newBean) {
	
	return calalogingDao.branchSave(newBean);
}

public int getBranchUpdate(BranchBean newBean) {
	return calalogingDao.branchUpdate(newBean);
}

public List getBranchSearchName(String name,int branchID) {
	
	return calalogingDao.findBranchSearchName(name,branchID);
} 

//--------------------Currency--------------------

public CurrencyBean getCurrencyMax() {
	return calalogingDao.findCurrencyMax();
}

public CurrencyBean getCurrencySearch(int code) {
	return calalogingDao.findCurrencySearch(code);
}

public int getCurrencyInterface(int code) {
	
	return calalogingDao.findCurrencyInterface(code);
}

public int getCurrencyMas(int code) {
	
	return calalogingDao.findCurrencyMas(code);
}

public int getCurrencyDelete(int code) {
	
	return calalogingDao.currencyDelete(code);
}

public int getCurrencyName(CurrencyBean newBean) {
	
	return calalogingDao.currencyName(newBean);
}
public int getCurrencySave(CurrencyBean newBean) {
	
	return calalogingDao.currencySave(newBean);
}

public int getCurrencyUpdate(CurrencyBean newBean) {
	return calalogingDao.currencyUpdate(newBean);
}

public List getCurrencySearchName(CurrencyBean newBean) {
	
	return calalogingDao.CurrencySearch(newBean);
} 

//--------------------Keywords--------------------

public KeywordsBean getKeywordsMax() {
	return calalogingDao.findkeywordsMax();
}

public KeywordsBean getKeywordsSearch(int code) {
	return calalogingDao.findKeywordsSearch(code);
}

public int getKeywordsDelete(int code) {
	
	return calalogingDao.keywordsDelete(code);
}

public int getKeywordsName(KeywordsBean newBean) {
	
	return calalogingDao.keywordsName(newBean);
}

public int getKeywordsMas(int code) {
	
	return calalogingDao.findKeywordsMas(code);
}

public int getKeywordsSave(KeywordsBean newBean) {
	
	return calalogingDao.keywordsSave(newBean);
}

public int getKeywordsUpdate(KeywordsBean newBean) {
	return calalogingDao.keywordsUpdate(newBean);
}

public List getKeywordsSearchName(KeywordsBean newBean) {
	
	return calalogingDao.keywordsSearchName(newBean);
} 


//----------------Report---------------------
/*public byte[] getJasperReportToPdfDisply(JasperReport jasperReport,Map parameters)
{
	return calalogingDao.getJasperReportToPdfDisplyDao(jasperReport,parameters);
}
*/

//---------------Binding--------------------

public BindingBean getBindingMax() {
	return calalogingDao.findBindingMax();
}

public BindingBean getBindingSearch(int code) {
	return calalogingDao.findBindingSearch(code);
}
public int getBindingDelete(BindingBean codebean) {
	
	return calalogingDao.BindingDelete(codebean);
}

public int getBindingName(String Name) {
	
	return calalogingDao.findBindingName(Name);
}


public int getBindingUpdate(BindingBean codebean) {
	
	return calalogingDao.findBindingUpdate(codebean);
}

public int getBindingSave(BindingBean codebean) {
	
	return calalogingDao.fineBindingSave(codebean);
}

public List getBinderSearchName(String name){
	
	return calalogingDao.findBinderSearchName(name);
} 


//--------------------NewsCllipings--------------


public NewscllipingBean getNewsMax() {
	return calalogingDao.findNewsMax();
}
public NewscllipingBean getNewsSearch(int code,int branchID) {
	return calalogingDao.findNewsSearch(code,branchID);
}


public int getNewsCliMas(int code) {
	return calalogingDao.findNewscliSearch(code);
}

public int getNewClipSave(NewscllipingBean codebean) {
	
	return calalogingDao.fineNewClipSave(codebean);
}

public int getNewsClipUpdate(NewscllipingBean codebean) {
	
	return calalogingDao.findNewsClipUpdate(codebean);
}
public int getNewsClipDelete(int code) {
	
	return calalogingDao.NewsClipDelete(code);
}

//--------------------EResourse--------------

public EResourceBean getEResourceMax() {
	return calalogingDao.findEResourceMax();
}

public int getEResourseMas(int code) {
	return calalogingDao.findEResourseMas(code);
}

public int getEResourseSave(EResourceBean codebean) {
	
	return calalogingDao.fineEResourseSave(codebean);
}

public EResourceBean getEResourseSearch(int code,int branchID) {
	return calalogingDao.findEResourseSearch(code,branchID);
}


public int getEResourceUpdate(EResourceBean codebean) {
	
	return calalogingDao.findEResourceUpdate(codebean);
}

public int getEResourceDelete(int code) {
	
	return calalogingDao.EResourceDelete(code);
}

//--------------------MResourse--------------
public MResourceBean getMResourseMas(MResourceBean newbean) {
	return calalogingDao.findMResourseMas(newbean);
}

public int getMResourseSave(MResourceBean codebean) {
	
	return calalogingDao.fineMResourseSave(codebean);
}

public int getMResourseDelete(String accno) {
	
	return calalogingDao.fineMResourseDelete(accno);
}

//--------------Contents Upload----------------------------

public boolean getCheckContentsNumber(String accno,String document,int branch)
{
	return calalogingDao.findCheckContentsNumber(accno,document,branch);
}

public void getUpdateContentFile(String accno,String document,String fileName)
{
	calalogingDao.findUpdateContentFile(accno,document,fileName);
}


//--------------Question Bank ----------------------------

public QuestionBankBean getQuestionBankMax()
{
	return calalogingDao.getQuestionBankMax();
}

public int getQuestionBankUpdate(QuestionBankBean codebean)
{
	return calalogingDao.getQuestionBankUpdate(codebean);
}

public QuestionBankBean getQuestionBankSearch(int code,int branchID)
{
	return calalogingDao.getQuestionBankSearch(code,branchID);
}

public int getQuestionBankMas(int code)
{
	return calalogingDao.getQuestionBankMas(code);
}

public int getQuestionBankSave(QuestionBankBean codebean)
{
	return calalogingDao.getQuestionBankSave(codebean);
}

public int getQuestionBankDelete(int code)
{
	return calalogingDao.getQuestionBankDelete(code);
}




public CalalogingDao getCalalogingDao() {
	return calalogingDao;
}




public void setCalalogingDao(CalalogingDao calalogingDao) {
	this.calalogingDao = calalogingDao;
}


//-------------------Message Master--------------------------


public MsgMasBean getMsgMasMax() {
	return calalogingDao.findMsgMasMax();
}

public int getMsgMas(int code,int branchID) {
	
	return calalogingDao.findMsgMas(code,branchID);
}


public int getMsgMasSave(MsgMasBean newBean) {
	
	return calalogingDao.MsgMasSave(newBean);
}

public MsgMasBean getMsgMasSearch(int code,int branchID) {
	return calalogingDao.findMsgMasSearch(code,branchID);
}

public int getMsgMasDelete(int code,int branchID) {
	
	return calalogingDao.MsgMasDelete(code,branchID);
}

public int getMsgMasUpdate(MsgMasBean newBean) {
	return calalogingDao.MsgMasUpdate(newBean);
}

public List getMsgMasSearchName(String newBean) {
	
	return calalogingDao.findMsgMasSearchName(newBean);
						  
}

public ArrayList<EBookBean> getEBookAutoAccessNoSearch(String term) {
	 
	return calalogingDao.findEBookAutoAccessNoSearch(term);
	}

public ArrayList<EBookBean> getEBookAutoCallNoSearch(String term) {
	 
	return calalogingDao.findEBookAutoCallNoSearch(term);
	}


public ArrayList<EBookBean> getEBookAutoTitleSearch(String term) {
	 
	return calalogingDao.findEBookAutoTitleSearch(term);
	}

public ArrayList<AuthorBean> getEBookAutoAuthorSearch(String term) {
	 
	return calalogingDao.findEBookAutoAuthorSearch(term);
	}

public ArrayList<PubSupBean> getEBookAutoPublisherSearch(String term) {
	 
	return calalogingDao.findEBookAutoPublisherSearch(term);
	}

public ArrayList<PubSupBean> getEBookAutoSupplierSearch(String term) {
	 
	return calalogingDao.findEBookAutoSupplierSearch(term);
	}

public ArrayList<subjectbean> getEBookAutoSubjectSearch(String term) {
	 
	return calalogingDao.findEBookAutoSubjectSearch(term);
	}

public ArrayList<DepartmentBean> getEBookAutoDeptSearch(String term) {
	 
	return calalogingDao.findEBookAutoDeptSearch(term);
	
	}

public ArrayList<PubSupBean> getBookAutoSupplierSearch(String term) {

	return calalogingDao.findBookAutoSupplierSearch(term);
}


public EBookBean getEBookMax() {
	return calalogingDao.findEBookMax();
}

public EBookBean getEBookSearch(String accessNo,int branchID) {
	return calalogingDao.findEBookSearch(accessNo,branchID);
}

public int getEBookSave(EBookBean ebookBean) {
	
	return calalogingDao.ebookSave(ebookBean);
}

public int getEBookUpdate(EBookBean ebookBean) {
	return calalogingDao.ebookUpdate(ebookBean);
}

public int getEBookDelete(String accessNo) {
	
	return calalogingDao.ebookDelete(accessNo);
}

public String getEBookName(EBookBean ebookBean) {
	
	return calalogingDao.findEBookName(ebookBean);
}

public String getEBookAccessNo(String accessNo,int branchID) {
	
	return calalogingDao.findEBookAccessNo(accessNo,branchID);
}

public List getEBookSearchList(EBookBean ob) {
	
	return calalogingDao.findEBookSearchList(ob);
} 

public List getEBookCallNoList(EBookBean ob) {
	
	return calalogingDao.findEBookCallNoList(ob);
} 

public List getEBookSearchTitle(String name,int branchID) {
	return calalogingDao.findEBookSearchTitle(name,branchID);
} 

/*public List getEBookSearchTitle(EBookBean ob) {
	return calalogingDao.findEBookSearchTitle(ob);
}*/	
	
public List getEBookSearchAuthor(String name,int branchID) {
	return calalogingDao.findEBookSearchAuthor(name,branchID);
}
public List getEBookSearchPub(String name,int branchID) {
	return calalogingDao.findEBookSearchPub(name,branchID);
}
public List getEBookSearchSup(String name,int branchID) {
	return calalogingDao.findEBookSearchSup(name,branchID);
}
public List getEBookSearchSubject(String name,int branchID) {
	return calalogingDao.findEBookSearchSubject(name,branchID);
}
public List getEBookSearchCallNo(String name,int branchID) {
	return calalogingDao.findEBookSearchCallNo(name,branchID);
}

public List getEBookSearchDept(String name,int branchID) {
	return calalogingDao.findEBookSearchDept(name,branchID);
}
@Override
public MemberBean getMemberClear(String member_code,int branchID) {
	 return calalogingDao.findMemberClear(member_code,branchID);
	
}
@Override
public ArrayList<AuthorBean> getAuthorAutoSearch(String term,int branchID) {
	 return calalogingDao.findAuthorAutoSearch(term,branchID);
}
@Override
public ArrayList<DepartmentBean> getDeptAutoSearch(String term,int branchID) {
	 return calalogingDao.findDeptAutoSearch(term,branchID);
}
@Override
public ArrayList<DesignationBean> getDesigAutoSearch(String term,int branchID) {
	 return calalogingDao.findDesigAutoSearch(term,branchID);
}
@Override
public ArrayList<subjectbean> getSubjectAutoSearch(String term, int branchID) {
	 return calalogingDao.findSubjectAutoSearch(term,branchID);
}
@Override
public ArrayList<CourseBean> getCourseAutoSearch(String term, int branchID) {
	return calalogingDao.findCourseAutoSearch(term,branchID);
}
@Override
public ArrayList<PubSupBean> getPubSupAutoSearch(String term, String type,
		int branchID) {
	return calalogingDao.findPubSupAutoSearch(term,type,branchID);
}
@Override
public ArrayList<bookbean> getBookAutoAccessNoSearch(String term, String doc,int branchID) {
	return calalogingDao.findBookAutoAccessNoSearch(term,doc,branchID);
}
@Override
public ArrayList<bookbean> getBookAutoCallNoSearch(String term, String doc,int branchID) {
	return calalogingDao.findBookAutoCallNoSearch(term,doc,branchID);
}
@Override
public ArrayList<bookbean> getBookAutoTitleSearch(String term, String doc,int branchID) {
	return calalogingDao.findBookAutoTitleSearch(term,doc,branchID);
}
@Override
public ArrayList<PubSupBean> getBookAutoPublisherSearch(String term,int branchID) {
	return calalogingDao.findBookAutoPublisherSearch(term,branchID);
}
@Override
public ArrayList<subjectbean> getBookAutoSubjectSearch(String term, int branchID) {
	return calalogingDao.findBookAutoSubjectSearch(term,branchID);
}
@Override
public ArrayList<DepartmentBean> getBookAutoDeptSearch(String term, int branchID) {
	return calalogingDao.findBookAutoDeptSearch(term,branchID);
}
@Override
public ArrayList<PubSupBean> getBookAutoSupplierSearch(String term, int branchID) {
	return calalogingDao.findBookAutoSupplierSearch(term,branchID);
}
@Override
public ArrayList<BudgetBean> getBookAutoBudgetSearch(String term, int branchID) {
	return calalogingDao.findBookAutoBudgetSearch(term,branchID);
}
@Override
public ArrayList<KeywordsBean> getBookAutoKeywordsSearch(String term,int branchID) {
	return calalogingDao.findBookAutoKeywordsSearch(term,branchID);
}
@Override
public ArrayList<AuthorBean> getBookAutoAuthorSearch(String term, int branchID) {
	return calalogingDao.findBookAutoAuthorSearch(term,branchID);
}
@Override
public ArrayList<CityBean> getMemberAutoCitySearch(String term, int branchID) {
	return calalogingDao.findMemberAutoCitySearch(term,branchID);
}
@Override
public ArrayList<CourseBean> getMemberAutoCourseSearch(String term, int branchID) {
	return calalogingDao.findMemberAutoCourseSearch(term,branchID);
}
@Override
public ArrayList<GroupBean> getMemberAutoGroupSearch(String term, int branchID) {
	return calalogingDao.findMemberAutoGroupSearch(term,branchID);
}
@Override
public ArrayList<DepartmentBean> getMemberAutoDeptSearch(String term,
		int branchID) {
	return calalogingDao.findMemberAutoDeptSearch(term,branchID);
}
@Override
public ArrayList<DesignationBean> getMemberAutoDesigSearch(String term,
		int branchID) {
	return calalogingDao.findMemberAutoDesigSearch(term,branchID);
}
@Override
public ArrayList<MemberBean> getMemberAutoNameSearch(String term, int branchID) {
	return calalogingDao.findMemberAutoNameSearch(term,branchID);
}
@Override
public ArrayList<MemberBean> getMemberAutoIdSearch(String term, int branchID) {
	return calalogingDao.findMemberAutoIdSearch(term,branchID);
}	
}
