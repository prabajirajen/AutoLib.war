/*
 *                 Autolib License Notice
 *
 * The contents of this file are subject to the Autolib  License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License.The Initial Developer of the Original Code is
 * Autolib Software Systems.
 * Portions Copyright 1998-2008.Autolib Software Systems All Rights Reserved.
 *
 *
 */
package Common.businessutil.importexportxml;

/**
 * @author Raja
 * @Date 06 Jan 2008
 * @version 1.0
 */
import java.io.Serializable;


/** The Interface HostelMaster. */
public class ImportExportXML implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The accessNo.*/
	private String accessNo;

	private String NoOfBooks;

	private String pages;

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

	private String GroupName;

	private String BinderName;

	private String SendingDate;

	private String RecdDate;

	private String InvoiceDate;

	private String TagId;

	private String Availability;

	private String QBCode;

	private String Semester;

	private String Course;

	private String SubCode;

	private String SubName;

	private String cyear;

	private String month;

	private String year;

	private String group;

	private String total;

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTotalMins() {
		return totalMins;
	}

	public void setTotalMins(String totalMins) {
		this.totalMins = totalMins;
	}

	private String courseName;


	private String totalMins;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCyear() {
		return cyear;
	}

	public void setCyear(String cyear) {
		this.cyear = cyear;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAvailability() {
		return Availability;
	}

	public String getQBCode() {
		return QBCode;
	}

	public void setQBCode(String qBCode) {
		QBCode = qBCode;
	}

	public String getSemester() {
		return Semester;
	}

	public void setSemester(String semester) {
		Semester = semester;
	}

	public String getCourse() {
		return Course;
	}

	public void setCourse(String course) {
		Course = course;
	}

	public String getSubCode() {
		return SubCode;
	}

	public void setSubCode(String subCode) {
		SubCode = subCode;
	}

	public String getSubName() {
		return SubName;
	}

	public void setSubName(String subName) {
		SubName = subName;
	}

	public void setAvailability(String availability) {
		Availability = availability;
	}

	public String getTagId() {
		return TagId;
	}

	public void setTagId(String tagId) {
		TagId = tagId;
	}

	public String getSendingDate() {
		return SendingDate;
	}

	public void setSendingDate(String sendingDate) {
		SendingDate = sendingDate;
	}

	public String getBinderName() {
		return BinderName;
	}

	public void setBinderName(String binderName) {
		BinderName = binderName;
	}

	public String getGroupName() {
		return GroupName;
	}

	public void setGroupName(String groupName) {
		GroupName = groupName;
	}

	public String getValidity() {
		return Validity;
	}

	public void setValidity(String validity) {
		Validity = validity;
	}

	private String Validity;

	public String getNoOfBooks() {
		return NoOfBooks;
	}

	public void setNoOfBooks(String noOfBooks) {
		NoOfBooks = noOfBooks;
	}

	public String getNoOfTitles() {
		return NoOfTitles;
	}

	public void setNoOfTitles(String noOfTitles) {
		NoOfTitles = noOfTitles;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

	public String getNetPrice() {
		return NetPrice;
	}

	public void setNetPrice(String netPrice) {
		NetPrice = netPrice;
	}

	private String NoOfTitles;

	private String Discount;

	private String NetPrice;

	/** The title.*/
	private String title;

	/** The callNo.*/
	private String callNo;

	/** The authorName.*/
	private String authorName;

	private String DesigName;

	private String intime;

	private String minused;

	public String getMinused() {
		return minused;
	}

	public void setMinused(String minused) {
		this.minused = minused;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public String getOuttime() {
		return outtime;
	}

	public void setOuttime(String outtime) {
		this.outtime = outtime;
	}

	private String outtime;

	public String getDesigName() {
		return DesigName;
	}

	public void setDesigName(String desigName) {
		DesigName = desigName;
	}

	/** The edition.*/
	private String edition;

	/** The publisherName.*/
	private String publisherName;

	/** The supplierName.*/
	private String supplierName;

	/** The yearPub.*/
	private String yearPub;

	/** The invoiceNo.*/
	private String invoiceNo;

	/** The isbnNo.*/
	private String isbnNo;

	/** The price.*/
	private String price;

	/** The memberCode.*/
	private String memberCode;

	/** The staffCode.*/
	private String staffCode;

	/** The issueDate.*/
	private String issueDate;

	/** The dueDate.*/
	private String dueDate;


	/** The returnDate.*/
	private String returnDate;

	/** The document.*/
	private String document;

	private String totfine;

	public String getTotfine() {
		return totfine;
	}

	public void setTotfine(String totfine) {
		this.totfine = totfine;
	}

	public String getBreakupdate() {
		return breakupdate;
	}

	public void setBreakupdate(String breakupdate) {
		this.breakupdate = breakupdate;
	}

	public String getBreakupcount() {
		return breakupcount;
	}

	public void setBreakupcount(String breakupcount) {
		this.breakupcount = breakupcount;
	}

	private String breakupdate;

	private String breakupcount;

	/** The memberName.*/
	private String memberName;


	/** The deptName.*/
	private String deptName;

	private String subjectName;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/** The fineAmount.*/
	private String fineAmount;

	/** The paidAmount.*/
	private String paidAmount;

	/** The balanceAmount.*/
	private String balanceAmount;


	private String budgetName;





	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	/**
	 * @return Returns the accessNo.
	 */
	public String getAccessNo() {
		return accessNo;
	}

	/**
	 * @param accessNo The accessNo to set.
	 */
	public void setAccessNo(String accessNo) {
		this.accessNo = accessNo;
	}

	/**
	 * @return Returns the authorName.
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName The authorName to set.
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	/**
	 * @return Returns the callNo.
	 */
	public String getCallNo() {
		return callNo;
	}

	/**
	 * @param callNo The callNo to set.
	 */
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}

	/**
	 * @return Returns the edition.
	 */
	public String getEdition() {
		return edition;
	}

	/**
	 * @param edition The edition to set.
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}

	/**
	 * @return Returns the invoiceNo.
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo The invoiceNo to set.
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	/**
	 * @return Returns the publisherName.
	 */
	public String getPublisherName() {
		return publisherName;
	}

	/**
	 * @param publisherName The publisherName to set.
	 */
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	/**
	 * @return Returns the supplierName.
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * @param supplierName The supplierName to set.
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return Returns the yearPub.
	 */
	public String getYearPub() {
		return yearPub;
	}

	/**
	 * @param yearPub The yearPub to set.
	 */
	public void setYearPub(String yearPub) {
		this.yearPub = yearPub;
	}

	/**
	 * @return Returns the isbnNo.
	 */
	public String getIsbnNo() {
		return isbnNo;
	}

	/**
	 * @param isbnNo The isbnNo to set.
	 */
	public void setIsbnNo(String isbnNo) {
		this.isbnNo = isbnNo;
	}

	/**
	 * @return Returns the price.
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * @param price The price to set.
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * @return Returns the document.
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document The document to set.
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	 * @return Returns the dueDate.
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate The dueDate to set.
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return Returns the issueDate.
	 */
	public String getIssueDate() {
		return issueDate;
	}

	/**
	 * @param issueDate The issueDate to set.
	 */
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	/**
	 * @return Returns the memberCode.
	 */
	public String getMemberCode() {
		return memberCode;
	}

	/**
	 * @param memberCode The memberCode to set.
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	/**
	 * @return Returns the returnDate.
	 */
	public String getReturnDate() {
		return returnDate;
	}

	/**
	 * @param returnDate The returnDate to set.
	 */
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	/**
	 * @return Returns the staffCode.
	 */
	public String getStaffCode() {
		return staffCode;
	}

	/**
	 * @param staffCode The staffCode to set.
	 */
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	/**
	 * @return Returns the balanceAmount.
	 */
	public String getBalanceAmount() {
		return balanceAmount;
	}

	/**
	 * @param balanceAmount The balanceAmount to set.
	 */
	public void setBalanceAmount(String balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 * @return Returns the deptName.
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * @param deptName The deptName to set.
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	/**
	 * @return Returns the fineAmount.
	 */
	public String getFineAmount() {
		return fineAmount;
	}

	/**
	 * @param fineAmount The fineAmount to set.
	 */
	public void setFineAmount(String fineAmount) {
		this.fineAmount = fineAmount;
	}

	/**
	 * @return Returns the memberName.
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName The memberName to set.
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return Returns the paidAmount.
	 */
	public String getPaidAmount() {
		return paidAmount;
	}

	/**
	 * @param paidAmount The paidAmount to set.
	 */
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getRecdDate() {
		return RecdDate;
	}

	public void setRecdDate(String recdDate) {
		RecdDate = recdDate;
	}

	public String getInvoiceDate() {
		return InvoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		InvoiceDate = invoiceDate;
	}

	private String departmentName;

	private String receivedDate;	

	private String location;

	private String language;

	private String issue_no;

	private String volno;

	private String acceptedPrice;

	private String purchaseType;

	private String keywords;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}



	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getIssue_no() {
		return issue_no;
	}

	public void setIssue_no(String issue_no) {
		this.issue_no = issue_no;
	}

	public String getVolno() {
		return volno;
	}

	public void setVolno(String volno) {
		this.volno = volno;
	}

	public String getAcceptedPrice() {
		return acceptedPrice;
	}

	public void setAcceptedPrice(String acceptedPrice) {
		this.acceptedPrice = acceptedPrice;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}




}
