package Lib.Auto.BulkFineUpload;


public class FineDataBean    {
	
	private String memberCode;
	private String memberName;
	private String payDate;		
	private String payMode;
	private String staffCode;
	private String document;
	
	private String remarks;
	private String addFiled1;
	private String addFiled2;
	
	private int billNo;
	private double payAmount= 0.0 ;
	private int branchCode;
	
	
	
	public String getAddFiled1() {
		return addFiled1;
	}
	public void setAddFiled1(String addFiled1) {
		this.addFiled1 = addFiled1;
	}
	public String getAddFiled2() {
		return addFiled2;
	}
	public void setAddFiled2(String addFiled2) {
		this.addFiled2 = addFiled2;
	}
	public int getBillNo() {
		return billNo;
	}
	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	/**
	 * @return Returns the branchCode.
	 */
	public int getBranchCode() {
		return branchCode;
	}
	/**
	 * @param branchCode The branchCode to set.
	 */
	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
		
		
	
	
}

