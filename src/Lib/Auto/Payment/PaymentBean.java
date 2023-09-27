package Lib.Auto.Payment;

public class PaymentBean{
	
	private String mcode = "";
	private String mname = "";
	private String dept = "";
	private String course = "";
	
	
	
	
	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getRenew() {
		return renew;
	}

	public void setRenew(String renew) {
		this.renew = renew;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}



	private String issue="";
	private String renew="";
	private String returned="";
	
	
	
	

	private Double tot_amt;
	private Double paid_amt;
	private Double balance_amt;
	
	private int bill_no;
	private String pdate = "";
	private Double current_amt;
	private java.util.ArrayList PaymentList = null;
	
	private java.util.ArrayList issueHistoryList = null;
	
	
	public java.util.ArrayList getIssueHistoryList() {
		return issueHistoryList;
	}

	public void setIssueHistoryList(java.util.ArrayList issueHistoryList) {
		this.issueHistoryList = issueHistoryList;
	}



	private java.util.ArrayList issueDetail= null;
	
	private java.util.ArrayList PaidList = null;
	
	public java.util.ArrayList getIssueDetail() {
		return issueDetail;
	}

	public void setIssueDetail(java.util.ArrayList issueDetail) {
		this.issueDetail = issueDetail;
	}


	
	private int branch; 
		
	/**
	 * @return
	 */
	
	public java.util.ArrayList getPaymentList() {
		return PaymentList;
	}
	
	public java.util.ArrayList getPaidList() {
		return PaidList;
	}
	
	public String getMcode() {
		return mcode;
	}	


	public String getMname() {
		return mname;
	}



	public String getDept() {
		return dept;
	}


	public String getCourse() {
		return course;
	}
	
	/**
	 * @return
	 */
	public String getPdate() {
		return pdate;
	}
	
	public double getTot_Amt() {
		return tot_amt;
	}
	
	public double getPaid_Amt() {
		return paid_amt;
	}
	
	public double getBalance_Amt() {
		return balance_amt;
	}
	
	public int getBill_No() {
		return bill_no;
	}
	
	public double getCurrent_Amt() {
		return current_amt;
	}
	
	
	public void setPaymentList(java.util.ArrayList list) {
		PaymentList = list;
	}
	
	public void setPaidList(java.util.ArrayList list) {
		PaidList = list;
	}
	
	/**
	 * @param string
	 */
	public void setMcode(String string) {
		mcode = string;
	}


	public void setMname(String string) {
		mname = string;
	}


	public void setDept(String string) {
		dept = string;
	}


	public void setCourse(String string) {
		course = string;
	}
	
	public void setPdate(String string) {
		pdate = string;
	}

	
	public void setTot_Amt(double d) {
		tot_amt = d;
	}
	
	public void setPaid_Amt(double e) {
		paid_amt = e;
	}
	
	public void setBalance_Amt(double f) {
		balance_amt = f;
	}
	
	public void setBill_No(int g) {
		bill_no = g;
	}
	public void setCurrent_Amt(double h) {
		current_amt = h;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public Double getTot_amt() {
		return tot_amt;
	}

	public void setTot_amt(Double tot_amt) {
		this.tot_amt = tot_amt;
	}

	public Double getPaid_amt() {
		return paid_amt;
	}

	public void setPaid_amt(Double paid_amt) {
		this.paid_amt = paid_amt;
	}

	public Double getBalance_amt() {
		return balance_amt;
	}

	public void setBalance_amt(Double balance_amt) {
		this.balance_amt = balance_amt;
	}

	public int getBill_no() {
		return bill_no;
	}

	public void setBill_no(int bill_no) {
		this.bill_no = bill_no;
	}

	public Double getCurrent_amt() {
		return current_amt;
	}

	public void setCurrent_amt(Double current_amt) {
		this.current_amt = current_amt;
	}

	@Override
	public String toString() {
		return "PaymentBean [mcode=" + mcode + ", mname=" + mname + ", dept="
				+ dept + ", course=" + course + ", issue=" + issue + ", renew="
				+ renew + ", returned=" + returned + ", tot_amt=" + tot_amt
				+ ", paid_amt=" + paid_amt + ", balance_amt=" + balance_amt
				+ ", bill_no=" + bill_no + ", pdate=" + pdate
				+ ", current_amt=" + current_amt + ", PaymentList="
				+ PaymentList + ", issueHistoryList=" + issueHistoryList
				+ ", issueDetail=" + issueDetail + ", PaidList=" + PaidList
				+ ", branch=" + branch + "]";
	}
	
	
	
	
}

