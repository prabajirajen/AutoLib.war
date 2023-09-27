package Common.businessutil.circulation;

import java.util.List;

import Lib.Auto.Binding_Books.BookBindingBean;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Payment.PaymentBean;
import Lib.Auto.Transfer_Books.BookTransferBean;

public interface CirculationDao {
	
	
	public CounterBean findCounterMember(String code,int branchID);
	
	public int findCounterGroup(String group,int branchID);
	
	public List findIssueDetailsMember(String code, int branchID);
	
	public int findFineDetail(CounterMemberBean newbean);
	
	public CounterMemberBean findBookCountDoctype(String code);
	
	public CounterMemberBean findCounterBook(String code,String document,String mcode,int branchID);
	
	public CounterBean findCounterIssueCheck(String code, int branchID);
	
	public CounterBean findEpc(String epc);
	
	public CounterBean findCardUID(String UID);
	
	public int findIssueCheck(String code,String mcode,int branchID);
	
	public CounterBean findMemberLoad(String code,String code1, int branchID, String availability);
		
	public String findValidDate(String code);
	
	public String findAvailability(String accno);
	
	public String findMemberCode(String code ,int branchID);
	
	public CounterMemberBean findIssueMasCheck(String code,String document,int branchID);
	
	public String findReserveMasCheck(String code);
	
	public ReserveBean findReserveMssSelect(String code);
	
	public int findReserveMssDelete(CounterMemberBean newbean);
	
	public int findIssueMasInsert(CounterMemberBean newbean);
	
	public int findIssuedDetails(CounterMemberBean newbean);
	
	
	public CounterBean findDocmentReturn(CounterMemberBean newbean);
	
	
	public int findDocmentFine(CounterMemberBean newbean);
	
	public int findIssueMasSelect(CounterMemberBean newbean);
	
	public int findReserveMasSelect(CounterMemberBean newbean);
	
	public int findReserveMasSave(CounterMemberBean newbean);
	
	public int findReserveMasCount(String code);
	
	public int findMemberMasSelect(String code ,int branchID);
	
	public List findReserveDetailsMember(String code,int branchID);
	
	public List findReserveDetailsBook(String code);
	
	public CounterMemberBean findIssueMas(String code);
	
	public CounterBean findNumberofDays(CounterMemberBean newbean);
	
	public CounterMemberBean findDdate(CounterMemberBean newbean);
	
	public String findLeaveDate(String code);
	
	public CounterMemberBean findUpdateRenewMasNofine(CounterMemberBean newbean);
	
	public int findUpdateRenewMas(CounterMemberBean newbean);
	
	public CounterBean findFineCall(CounterMemberBean newbean);
	
	
	public int findUpdateRenewMasFine(CounterMemberBean newbean);
	
	public int findRenewCheck(CounterMemberBean newbean);
	
	//------------------Binding Books-----------------------
	
	public List findLoadBinderName();
	
	public int findBindingBooksSave(BookBindingBean newbean);
	
	public List findBindingDisplay(String query);
	
	public int findBindingBooksReturn(String name,int branchID);
	
	//------------------Transfer Books-----------------------
	
	public List findLoadDeptName(int branchID);
	
	public List findBranchName(int branchID);
	
	public BookTransferBean findTransferBooksSave(BookTransferBean newbean);
	
	public BookTransferBean findbulkTransferBooksSave(BookTransferBean newbean);
	
	public BookTransferBean findTransferMax();
	
	public List findTransferDisplay(BookTransferBean newbean);
	
	public List findTransferDisplay(int branchID);
	
	public int findTransferBooksReturn(String name,int branchID);
	
	public int findbulkTransferBooksReturn(String name,int branchID);
	
//------------------Member Paymet-----------------------
	
	public PaymentBean findPaymentMember(String code,int branchID);
	
	public PaymentBean findIssueHistoryDetails(String code);
	
	
	
	public int findPaymentBill_no();
	
	public int findAddPayment(PaymentBean bean);
	
	
	//------------------Transfer REPORT-----------------------
	
		
		public List findTransferedDeptName(int rk);

		public boolean deletePaymentClearance(PaymentBean paybean);
		
}
