package Common.businessutil.circulation;

import java.util.List;

import Lib.Auto.Binding_Books.BookBindingBean;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Transfer_Books.BookTransferBean;

public class CirculationServiceImpl implements CirculationService {
	
	private CirculationDao circulationDao;

	public CirculationServiceImpl() {
	}
//member
	
	public CounterBean getCounterMember(String code, int branchID) {
		return circulationDao.findCounterMember(code, branchID);
	}
	public int getCounterGroup(String group, int branchID) {
		return circulationDao.findCounterGroup(group,branchID);
	}
	
	public List getIssueDetailsMember(String group, int branchID) {
		return circulationDao.findIssueDetailsMember(group, branchID);
	}
	
	public int getFineDetail(CounterMemberBean newbean){
		return circulationDao.findFineDetail(newbean);
	}
	
	public CounterMemberBean getBookCountDoctype(String group) {
		return circulationDao.findBookCountDoctype(group);
	}
	
	public CounterMemberBean getCounterBook(String code,String document,String mcode,int branchID) {
		return circulationDao.findCounterBook(code,document,mcode,branchID);
	}
	
	public CounterBean getCounterIssueCheck(String code, int branchID) {
		return circulationDao.findCounterIssueCheck(code, branchID);
	}
	
	public CounterBean getEpc(String epcid) {
		return circulationDao.findEpc(epcid);
	}
	
	public CounterBean getCardUID(String UID) {
		return circulationDao.findCardUID(UID);
	}
	
	public int getIssueCheck(String code,String mcode,int branchID) {
		return circulationDao.findIssueCheck(code,mcode,branchID);
	}
	
	public CounterBean getMemberLoad(String group,String code, int branchID, String availability) {
		return circulationDao.findMemberLoad(group, code, branchID, availability);
	}
	
	public String getValidDate(String code) {
		return circulationDao.findValidDate(code);
	}
	
	public String getAvailability(String accno) {
		return circulationDao.findAvailability(accno);
	}
	
	public String getMemberCode(String code,int branchID) {
		return circulationDao.findMemberCode(code,branchID);
	}
	
	public CounterMemberBean getIssueMasCheck(String code,String document,int branchID) {
		return circulationDao.findIssueMasCheck(code,document,branchID);
	}
	
	
	public String getReserveMasCheck(String code) {
		return circulationDao.findReserveMasCheck(code);
	}
	
	public ReserveBean getReserveMssSelect(String code) {
		return circulationDao.findReserveMssSelect(code);
	}
	
	public int getReserveMssDelete(CounterMemberBean newbean) {
		return circulationDao.findReserveMssDelete(newbean);
	}
	
	public int getIssueMasInsert(CounterMemberBean newbean) {
		return circulationDao.findIssueMasInsert(newbean);
	}
	public int getIssuedDetails(CounterMemberBean newbean) {
		return circulationDao.findIssuedDetails(newbean);
	}
	
	
	public CounterBean getDocmentReturn(CounterMemberBean newbean) {
		return circulationDao.findDocmentReturn(newbean);
	}
	
	public int getDocmentFine(CounterMemberBean newbean) {
		return circulationDao.findDocmentFine(newbean);
	}
	
	public int getIssueMasSelect(CounterMemberBean newbean) {
		return circulationDao.findIssueMasSelect(newbean);
	}
	
	public int getReserveMasSelect(CounterMemberBean newbean) {
		return circulationDao.findReserveMasSelect(newbean);
	}
	
	public int getReserveMasSave(CounterMemberBean newbean) {
		return circulationDao.findReserveMasSave(newbean);
	}
	
	public int getReserveMasCount(String code) {
		return circulationDao.findReserveMasCount(code);
	}
	
	public int getMemberMasSelect(String code ,int branchID) {
		return circulationDao.findMemberMasSelect(code,branchID);
	}
	
	public List getReserveDetailsMember(String code,int branchID) {
		return circulationDao.findReserveDetailsMember(code,branchID);
	}
	
	public List getReserveDetailsBook(String code) {
		return circulationDao.findReserveDetailsBook(code);
	}
	
	public CounterMemberBean getIssueMas(String code) {
		return circulationDao.findIssueMas(code);
	}
	
	public CounterBean getNumberofDays(CounterMemberBean newbean) {
		return circulationDao.findNumberofDays(newbean);
	}
	
	public CounterMemberBean getDdate(CounterMemberBean newbean) {
		return circulationDao.findDdate(newbean);
	}
	
	public String getLeaveDate(String code) {
		return circulationDao.findLeaveDate(code);
	}
	
	public CounterMemberBean getUpdateRenewMasNofine(CounterMemberBean newbean) {
		return circulationDao.findUpdateRenewMasNofine(newbean);
	}
	
	
	public int getUpdateRenewMas(CounterMemberBean newbean) {
		return circulationDao.findUpdateRenewMas(newbean);
	}
	
	public CounterBean getFineCall(CounterMemberBean newbean) {
		return circulationDao.findFineCall(newbean);
	}
	
	
	public int getUpdateRenewMasFine(CounterMemberBean newbean) {
		return circulationDao.findUpdateRenewMasFine(newbean);
	}
	
	
	public int getRenewCheck(CounterMemberBean newbean) {
		return circulationDao.findRenewCheck(newbean);
	}
	
	
	//------------------Binding Books
	
	public List getLoadBinderName() {
		return circulationDao.findLoadBinderName();
	}
	
	public int getBindingBooksSave(BookBindingBean newbean) {
		return circulationDao.findBindingBooksSave(newbean);
	}
	
	public List getBindingDisplay(String query) {
		return circulationDao.findBindingDisplay(query);
	}
	
	public int getBindingBooksReturn(String accno,int branchID) {
		return circulationDao.findBindingBooksReturn(accno,branchID);
	}
	
	//--------------Transfer Books-----------------------------
	
	public List getLoadDeptName(int branchID) {
		return circulationDao.findLoadDeptName(branchID);
	}
	
	public List getBranchName(int branchID) {
		return circulationDao.findBranchName(branchID);
	}
	
	public BookTransferBean getTransferBooksSave(BookTransferBean newbean) {
		return circulationDao.findTransferBooksSave(newbean);
	}
	
	
	public BookTransferBean getbulkTransferBooksSave(BookTransferBean newbean) {
		return circulationDao.findbulkTransferBooksSave(newbean);
	}
	
	
	public BookTransferBean getTransferMax() {
		return circulationDao.findTransferMax();
	}
	
	public List getTransferDisplay(BookTransferBean newbean){
		return circulationDao.findTransferDisplay(newbean);
	}
	
	public List getTransferDisplay(int bcode){
		return circulationDao.findTransferDisplay(bcode);
	}
	
	public int getTransferBooksReturn(String accno,int branchID) {
		return circulationDao.findTransferBooksReturn(accno,branchID);
	}
	

	public int getbulkTransferBooksReturn(String accno,int branchID) {
		return circulationDao.findbulkTransferBooksReturn(accno,branchID);
	}
	
//	Payment member
	
	public PaymentBean getPaymentMember(String code,int branchID) {
		return circulationDao.findPaymentMember(code,branchID);
	}
	
	public PaymentBean getIssueHistoryDetails(String code) {
		return circulationDao.findIssueHistoryDetails(code);
	}
	
	public int getPaymentBill_no() {
		return circulationDao.findPaymentBill_no();
	}
	
	public int getAddPayment(PaymentBean bean) {
		return circulationDao.findAddPayment(bean);
	}
		
	public CirculationDao getCirculationDao() {
		return circulationDao;
	}

	public void setCirculationDao(CirculationDao circulationDao) {
		this.circulationDao = circulationDao;
	}

	
	//--------------Transfer REPORT-----------------------------

	
	public List getTransferedDeptName(int rk) {
		return circulationDao.findTransferedDeptName(rk);
	}

	public boolean deletePaymentClearance(PaymentBean paybean) {
		return circulationDao.deletePaymentClearance(paybean);
	}
	
	
	
	

	
}
