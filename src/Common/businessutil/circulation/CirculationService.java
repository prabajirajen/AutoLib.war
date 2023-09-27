package Common.businessutil.circulation;

import java.util.List;

import Lib.Auto.Binding_Books.BookBindingBean;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Transfer_Books.BookTransferBean;

public interface CirculationService {
	
	//Member
	public String getAvailability(String accno);
	
	public CounterBean getCounterMember(String code, int branchID);
	
	public int getCounterGroup(String group, int branchID);
	
	public List getIssueDetailsMember(String code, int branchID);
	
	public int getFineDetail(CounterMemberBean newbean);
	
	public CounterMemberBean getBookCountDoctype(String code);
	
	public CounterMemberBean getCounterBook(String code,String document,String mcode ,int branchID);
	
	public CounterBean getCounterIssueCheck(String code, int branchID);
	
	public CounterBean getEpc(String epcid);
	
	public CounterBean getCardUID(String UID);
	
	public int getIssueCheck(String code,String mcode,int branchID);
	
	public CounterBean getMemberLoad(String code,String code1, int branchID, String availability);
	
	public String getValidDate(String code);
	
	public String getMemberCode(String code,int branchID);
	
	public CounterMemberBean getIssueMasCheck(String code,String document,int branchID);
	
	public String getReserveMasCheck(String code);
	
	public ReserveBean getReserveMssSelect(String code);
	
	public int getReserveMssDelete(CounterMemberBean newbean);
	
	public int getIssueMasInsert(CounterMemberBean newbean);
	
	public int getIssuedDetails(CounterMemberBean newbean);
	
	public CounterBean getDocmentReturn(CounterMemberBean newbean);
	
	public int getDocmentFine(CounterMemberBean newbean);
	
	public int getIssueMasSelect(CounterMemberBean newbean);
	
	public int getReserveMasSelect(CounterMemberBean newbean);
	
	public int getReserveMasSave(CounterMemberBean newbean);
	
	public int getReserveMasCount(String code);
	
	public int getMemberMasSelect(String code,int branchID);
	
	public List getReserveDetailsMember(String code,int branchID);
	
	public List getReserveDetailsBook(String code);
	
	public CounterMemberBean getIssueMas(String code);
	
	public CounterBean getNumberofDays(CounterMemberBean newbean);
	
	public CounterMemberBean getDdate(CounterMemberBean newbean);
	
	public String getLeaveDate(String code);
	
	public CounterMemberBean getUpdateRenewMasNofine(CounterMemberBean newbean);
	
	public int getUpdateRenewMas(CounterMemberBean newbean);
	
	public CounterBean getFineCall(CounterMemberBean newbean);
	
	public int getUpdateRenewMasFine(CounterMemberBean newbean);
	
	public int getRenewCheck(CounterMemberBean newbean);
	
	
	//--------------Binding Books-----------------------------
	public List getLoadBinderName();
	
	public int getBindingBooksSave(BookBindingBean newbean);
	
	public List getBindingDisplay(String query);
	
	public int getBindingBooksReturn(String accno,int branchID);
	
	//--------------Transfer /Re transfer Books-----------------------------
	
	public List getLoadDeptName(int branchID);
	public List getBranchName(int branchID);
//	public List getBranchNamebean(BookTransferBean newbean);
	
	public BookTransferBean getTransferBooksSave(BookTransferBean newbean);
	public BookTransferBean getbulkTransferBooksSave(BookTransferBean newbean);
	
	public BookTransferBean getTransferMax();
	
	public List getTransferDisplay(int branchID);
	public List getTransferDisplay(BookTransferBean newbean);
	
	public int getTransferBooksReturn(String accno,int branchID);
	public int getbulkTransferBooksReturn(String accno,int branchID);
	
	
//------------------Member Paymet-----------------------
	
	public PaymentBean getPaymentMember(String code,int branchID);	
	
	public PaymentBean getIssueHistoryDetails(String code);
	
	public int getPaymentBill_no();
	public int getAddPayment(PaymentBean bean);
	
	
	//--------------Transfer REPORT-----------------------------
	
		
		public List getTransferedDeptName(int rk);

		public boolean deletePaymentClearance(PaymentBean paybean);

}
