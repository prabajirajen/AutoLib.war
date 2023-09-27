package Lib.Auto.Counter;
public class CounterBean {

	// ----------------------------------------------------- Instance Variables

	private String mcode = "";
	private String mname = "";
	private String group = "";
	private String desig = "";
	private String dept = "";
	private String validDate = "";
	private String course = "";
	private String major = "";
	private String year = "";
	private String img = "";
	private String slock = "";
	private static String epc = "";
	private static String epc2 = "";
	private static String epc3 = "";
	private static String epc4 = "";
	
	public static String getEpc2() {
		return epc2;
	}
	public static void setEpc2(String epc2) {
		CounterBean.epc2 = epc2;
	}
	public static String getEpc3() {
		return epc3;
	}
	public static void setEpc3(String epc3) {
		CounterBean.epc3 = epc3;
	}
	public static String getEpc4() {
		return epc4;
	}
	public static void setEpc4(String epc4) {
		CounterBean.epc4 = epc4;
	}
	public static String getEpc() {
		return epc;
	}
	public static void setEpc(String epc) {
		CounterBean.epc = epc;
	}
	private String accno = "";
	private String callNo = "";
	private String title = "";
	private String author = "";
	private String publisher = "";
	private String avail = "";
	private String doc = "";
	private String idate = "";
	private String ddate = "";
	private String rdate = "";
	private String fine = "";
	
	private java.util.ArrayList counterList = null;
	private java.util.ArrayList cunterList_RESERVE = null;
	private java.util.ArrayList reservelist = null;
	
	private int branchID = 0;
	private String branchName = "";
	private int countperiod = 0;

	//general
	private int geligible = 0;
	private int gissued = 0;
	private int gperiod = 0;
	private int grperiod = 0;
	// Book 
	private int beligible = 0;
	private int bissued = 0;
	private int bperiod = 0;
	private int brperiod = 0;
	//BookBank
	private int bbeligible = 0;
	private int bbissued = 0;
	private int bbperiod = 0;
	private int bbrperiod = 0;
	//NonBook
	private int nbeligible = 0;
	private int nbissued = 0;
	private int nbperiod = 0;
	private int nbrperiod = 0;
	//Journal
	private int jeligible = 0;
	private int jissued = 0;
	private int jperiod = 0;
	private int jrperiod = 0;
	//BackVol
	private int bveligible = 0;
	private int bvissued = 0;
	private int bvperiod = 0;
	private int bvrperiod = 0;
	//Thesis
	private int teligible = 0;
	private int tissued = 0;
	private int tperiod = 0;
	private int trperiod = 0;
	//Standard
	private int seligible = 0;
	private int sissued = 0;
	private int speriod = 0;
	private int srperiod = 0;
	//Proceeding
	private int peligible = 0;
	private int pissued = 0;
	private int pperiod = 0;
	private int prperiod = 0;
	//Report
	private int religible = 0;
	private int rissued = 0;
	private int rperiod = 0;
	private int rrperiod = 0;
	
	private int renewal = 0;
	private int reserve = 0;
	
	private int deptBranch = 0;

	private String calldate = "";
	private String callissdate = "";
	private String callduedate = "";
	private Double temp = 0.0;
	private String issue_check = "";
	private int doc_return=0;
	private String epc_id = "";
	public String getEpc_id() {
		return epc_id;
	}
	public void setEpc_id(String epc_id) {
		this.epc_id = epc_id;
	}
	public String getCarduid() {
		return carduid;
	}
	public void setCarduid(String carduid) {
		this.carduid = carduid;
	}
	private String carduid = "";
	private static byte[] photo1=null;
	
	/**
	 * @return the mcode
	 */
	public String getMcode() {
		return mcode;
	}
	/**
	 * @param mcode the mcode to set
	 */
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	/**
	 * @return the mname
	 */
	public String getMname() {
		return mname;
	}
	/**
	 * @param mname the mname to set
	 */
	public void setMname(String mname) {
		this.mname = mname;
	}
	/**
	 * @return the group
	 */
	public String getGroup() {
		return group;
	}
	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}
	/**
	 * @return the desig
	 */
	public String getDesig() {
		return desig;
	}
	/**
	 * @param desig the desig to set
	 */
	public void setDesig(String desig) {
		this.desig = desig;
	}
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/**
	 * @return the validDate
	 */
	public String getValidDate() {
		return validDate;
	}
	/**
	 * @param validDate the validDate to set
	 */
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	/**
	 * @return the major
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * @param major the major to set
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}
	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * @return the slock
	 */
	public String getSLock() {
		return slock;
	}
	/**
	 * @param slock the slock to set
	 */
	public void setSLock(String slock) {
		this.slock = slock;
	}
	/**
	 * @return the accno
	 */
	public String getAccno() {
		return accno;
	}
	/**
	 * @param accno the accno to set
	 */
	public void setAccno(String accno) {
		this.accno = accno;
	}
	/**
	 * @return the callNo
	 */
	public String getCallNo() {
		return callNo;
	}
	/**
	 * @param callNo the callNo to set
	 */
	public void setCallNo(String callNo) {
		this.callNo = callNo;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}
	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	/**
	 * @return the avail
	 */
	public String getAvail() {
		return avail;
	}
	/**
	 * @param avail the avail to set
	 */
	public void setAvail(String avail) {
		this.avail = avail;
	}
	/**
	 * @return the doc
	 */
	public String getDoc() {
		return doc;
	}
	/**
	 * @param doc the doc to set
	 */
	public void setDoc(String doc) {
		this.doc = doc;
	}
	/**
	 * @return the idate
	 */
	public String getIdate() {
		return idate;
	}
	/**
	 * @param idate the idate to set
	 */
	public void setIdate(String idate) {
		this.idate = idate;
	}
	/**
	 * @return the ddate
	 */
	public String getDdate() {
		return ddate;
	}
	/**
	 * @param ddate the ddate to set
	 */
	public void setDdate(String ddate) {
		this.ddate = ddate;
	}
	/**
	 * @return the rdate
	 */
	public String getRdate() {
		return rdate;
	}
	/**
	 * @param rdate the rdate to set
	 */
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}
	/**
	 * @return the fine
	 */
	public String getFine() {
		return fine;
	}
	/**
	 * @param fine the fine to set
	 */
	public void setFine(String fine) {
		this.fine = fine;
	}
	/**
	 * @return the counterList
	 */
	public java.util.ArrayList getCounterList() {
		return counterList;
	}
	/**
	 * @param counterList the counterList to set
	 */
	public void setCounterList(java.util.ArrayList counterList) {
		this.counterList = counterList;
	}
	/**
	 * @return the cunterList_RESERVE
	 */
	public java.util.ArrayList getCunterList_RESERVE() {
		return cunterList_RESERVE;
	}
	/**
	 * @param cunterList_RESERVE the cunterList_RESERVE to set
	 */
	public void setCunterList_RESERVE(java.util.ArrayList cunterList_RESERVE) {
		this.cunterList_RESERVE = cunterList_RESERVE;
	}
	/**
	 * @return the reservelist
	 */
	public java.util.ArrayList getReservelist() {
		return reservelist;
	}
	/**
	 * @param reservelist the reservelist to set
	 */
	public void setReservelist(java.util.ArrayList reservelist) {
		this.reservelist = reservelist;
	}
	/**
	 * @return the branchID
	 */
	public int getBranchID() {
		return branchID;
	}
	/**
	 * @param branchID the branchID to set
	 */
	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}
	/**
	 * @return the branchName
	 */
	public String getBranchName() {
		return branchName;
	}
	/**
	 * @param branchName the branchName to set
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	/**
	 * @return the countperiod
	 */
	public int getCountperiod() {
		return countperiod;
	}
	/**
	 * @param countperiod the countperiod to set
	 */
	public void setCountperiod(int countperiod) {
		this.countperiod = countperiod;
	}
	/**
	 * @return the geligible
	 */
	public int getGeligible() {
		return geligible;
	}
	/**
	 * @param geligible the geligible to set
	 */
	public void setGeligible(int geligible) {
		this.geligible = geligible;
	}
	/**
	 * @return the gissued
	 */
	public int getGissued() {
		return gissued;
	}
	/**
	 * @param gissued the gissued to set
	 */
	public void setGissued(int gissued) {
		this.gissued = gissued;
	}
	/**
	 * @return the gperiod
	 */
	public int getGperiod() {
		return gperiod;
	}
	/**
	 * @param gperiod the gperiod to set
	 */
	public void setGperiod(int gperiod) {
		this.gperiod = gperiod;
	}
	/**
	 * @return the grperiod
	 */
	public int getGrperiod() {
		return grperiod;
	}
	/**
	 * @param grperiod the grperiod to set
	 */
	public void setGrperiod(int grperiod) {
		this.grperiod = grperiod;
	}
	/**
	 * @return the beligible
	 */
	public int getBeligible() {
		return beligible;
	}
	/**
	 * @param beligible the beligible to set
	 */
	public void setBeligible(int beligible) {
		this.beligible = beligible;
	}
	/**
	 * @return the bissued
	 */
	public int getBissued() {
		return bissued;
	}
	/**
	 * @param bissued the bissued to set
	 */
	public void setBissued(int bissued) {
		this.bissued = bissued;
	}
	/**
	 * @return the bperiod
	 */
	public int getBperiod() {
		return bperiod;
	}
	/**
	 * @param bperiod the bperiod to set
	 */
	public void setBperiod(int bperiod) {
		this.bperiod = bperiod;
	}
	/**
	 * @return the brperiod
	 */
	public int getBrperiod() {
		return brperiod;
	}
	/**
	 * @param brperiod the brperiod to set
	 */
	public void setBrperiod(int brperiod) {
		this.brperiod = brperiod;
	}
	/**
	 * @return the bbeligible
	 */
	public int getBbeligible() {
		return bbeligible;
	}
	/**
	 * @param bbeligible the bbeligible to set
	 */
	public void setBbeligible(int bbeligible) {
		this.bbeligible = bbeligible;
	}
	/**
	 * @return the bbissued
	 */
	public int getBbissued() {
		return bbissued;
	}
	/**
	 * @param bbissued the bbissued to set
	 */
	public void setBbissued(int bbissued) {
		this.bbissued = bbissued;
	}
	/**
	 * @return the bbperiod
	 */
	public int getBbperiod() {
		return bbperiod;
	}
	/**
	 * @param bbperiod the bbperiod to set
	 */
	public void setBbperiod(int bbperiod) {
		this.bbperiod = bbperiod;
	}
	/**
	 * @return the bbrperiod
	 */
	public int getBbrperiod() {
		return bbrperiod;
	}
	/**
	 * @param bbrperiod the bbrperiod to set
	 */
	public void setBbrperiod(int bbrperiod) {
		this.bbrperiod = bbrperiod;
	}
	/**
	 * @return the nbeligible
	 */
	public int getNbeligible() {
		return nbeligible;
	}
	/**
	 * @param nbeligible the nbeligible to set
	 */
	public void setNbeligible(int nbeligible) {
		this.nbeligible = nbeligible;
	}
	/**
	 * @return the nbissued
	 */
	public int getNbissued() {
		return nbissued;
	}
	/**
	 * @param nbissued the nbissued to set
	 */
	public void setNbissued(int nbissued) {
		this.nbissued = nbissued;
	}
	/**
	 * @return the nbperiod
	 */
	public int getNbperiod() {
		return nbperiod;
	}
	/**
	 * @param nbperiod the nbperiod to set
	 */
	public void setNbperiod(int nbperiod) {
		this.nbperiod = nbperiod;
	}
	/**
	 * @return the nbrperiod
	 */
	public int getNbrperiod() {
		return nbrperiod;
	}
	/**
	 * @param nbrperiod the nbrperiod to set
	 */
	public void setNbrperiod(int nbrperiod) {
		this.nbrperiod = nbrperiod;
	}
	/**
	 * @return the jeligible
	 */
	public int getJeligible() {
		return jeligible;
	}
	/**
	 * @param jeligible the jeligible to set
	 */
	public void setJeligible(int jeligible) {
		this.jeligible = jeligible;
	}
	/**
	 * @return the jissued
	 */
	public int getJissued() {
		return jissued;
	}
	/**
	 * @param jissued the jissued to set
	 */
	public void setJissued(int jissued) {
		this.jissued = jissued;
	}
	/**
	 * @return the jperiod
	 */
	public int getJperiod() {
		return jperiod;
	}
	/**
	 * @param jperiod the jperiod to set
	 */
	public void setJperiod(int jperiod) {
		this.jperiod = jperiod;
	}
	/**
	 * @return the jrperiod
	 */
	public int getJrperiod() {
		return jrperiod;
	}
	/**
	 * @param jrperiod the jrperiod to set
	 */
	public void setJrperiod(int jrperiod) {
		this.jrperiod = jrperiod;
	}
	/**
	 * @return the bveligible
	 */
	public int getBveligible() {
		return bveligible;
	}
	/**
	 * @param bveligible the bveligible to set
	 */
	public void setBveligible(int bveligible) {
		this.bveligible = bveligible;
	}
	/**
	 * @return the bvissued
	 */
	public int getBvissued() {
		return bvissued;
	}
	/**
	 * @param bvissued the bvissued to set
	 */
	public void setBvissued(int bvissued) {
		this.bvissued = bvissued;
	}
	/**
	 * @return the bvperiod
	 */
	public int getBvperiod() {
		return bvperiod;
	}
	/**
	 * @param bvperiod the bvperiod to set
	 */
	public void setBvperiod(int bvperiod) {
		this.bvperiod = bvperiod;
	}
	/**
	 * @return the bvrperiod
	 */
	public int getBvrperiod() {
		return bvrperiod;
	}
	/**
	 * @param bvrperiod the bvrperiod to set
	 */
	public void setBvrperiod(int bvrperiod) {
		this.bvrperiod = bvrperiod;
	}
	/**
	 * @return the teligible
	 */
	public int getTeligible() {
		return teligible;
	}
	/**
	 * @param teligible the teligible to set
	 */
	public void setTeligible(int teligible) {
		this.teligible = teligible;
	}
	/**
	 * @return the tissued
	 */
	public int getTissued() {
		return tissued;
	}
	/**
	 * @param tissued the tissued to set
	 */
	public void setTissued(int tissued) {
		this.tissued = tissued;
	}
	/**
	 * @return the tperiod
	 */
	public int getTperiod() {
		return tperiod;
	}
	/**
	 * @param tperiod the tperiod to set
	 */
	public void setTperiod(int tperiod) {
		this.tperiod = tperiod;
	}
	/**
	 * @return the trperiod
	 */
	public int getTrperiod() {
		return trperiod;
	}
	/**
	 * @param trperiod the trperiod to set
	 */
	public void setTrperiod(int trperiod) {
		this.trperiod = trperiod;
	}
	/**
	 * @return the seligible
	 */
	public int getSeligible() {
		return seligible;
	}
	/**
	 * @param seligible the seligible to set
	 */
	public void setSeligible(int seligible) {
		this.seligible = seligible;
	}
	/**
	 * @return the sissued
	 */
	public int getSissued() {
		return sissued;
	}
	/**
	 * @param sissued the sissued to set
	 */
	public void setSissued(int sissued) {
		this.sissued = sissued;
	}
	/**
	 * @return the speriod
	 */
	public int getSperiod() {
		return speriod;
	}
	/**
	 * @param speriod the speriod to set
	 */
	public void setSperiod(int speriod) {
		this.speriod = speriod;
	}
	/**
	 * @return the srperiod
	 */
	public int getSrperiod() {
		return srperiod;
	}
	/**
	 * @param srperiod the srperiod to set
	 */
	public void setSrperiod(int srperiod) {
		this.srperiod = srperiod;
	}
	/**
	 * @return the peligible
	 */
	public int getPeligible() {
		return peligible;
	}
	/**
	 * @param peligible the peligible to set
	 */
	public void setPeligible(int peligible) {
		this.peligible = peligible;
	}
	/**
	 * @return the pissued
	 */
	public int getPissued() {
		return pissued;
	}
	/**
	 * @param pissued the pissued to set
	 */
	public void setPissued(int pissued) {
		this.pissued = pissued;
	}
	/**
	 * @return the pperiod
	 */
	public int getPperiod() {
		return pperiod;
	}
	/**
	 * @param pperiod the pperiod to set
	 */
	public void setPperiod(int pperiod) {
		this.pperiod = pperiod;
	}
	/**
	 * @return the prperiod
	 */
	public int getPrperiod() {
		return prperiod;
	}
	/**
	 * @param prperiod the prperiod to set
	 */
	public void setPrperiod(int prperiod) {
		this.prperiod = prperiod;
	}
	/**
	 * @return the religible
	 */
	public int getReligible() {
		return religible;
	}
	/**
	 * @param religible the religible to set
	 */
	public void setReligible(int religible) {
		this.religible = religible;
	}
	/**
	 * @return the rissued
	 */
	public int getRissued() {
		return rissued;
	}
	/**
	 * @param rissued the rissued to set
	 */
	public void setRissued(int rissued) {
		this.rissued = rissued;
	}
	/**
	 * @return the rperiod
	 */
	public int getRperiod() {
		return rperiod;
	}
	/**
	 * @param rperiod the rperiod to set
	 */
	public void setRperiod(int rperiod) {
		this.rperiod = rperiod;
	}
	/**
	 * @return the rrperiod
	 */
	public int getRrperiod() {
		return rrperiod;
	}
	/**
	 * @param rrperiod the rrperiod to set
	 */
	public void setRrperiod(int rrperiod) {
		this.rrperiod = rrperiod;
	}
	/**
	 * @return the renewal
	 */
	public int getRenewal() {
		return renewal;
	}
	/**
	 * @param renewal the renewal to set
	 */
	public void setRenewal(int renewal) {
		this.renewal = renewal;
	}
	/**
	 * @return the reserve
	 */
	public int getReserve() {
		return reserve;
	}
	/**
	 * @param reserve the reserve to set
	 */
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	/**
	 * @return the calldate
	 */
	public String getCalldate() {
		return calldate;
	}
	/**
	 * @param calldate the calldate to set
	 */
	public void setCalldate(String calldate) {
		this.calldate = calldate;
	}
	/**
	 * @return the callissdate
	 */
	public String getCallissdate() {
		return callissdate;
	}
	/**
	 * @param callissdate the callissdate to set
	 */
	public void setCallissdate(String callissdate) {
		this.callissdate = callissdate;
	}
	/**
	 * @return the callduedate
	 */
	public String getCallduedate() {
		return callduedate;
	}
	/**
	 * @param callduedate the callduedate to set
	 */
	public void setCallduedate(String callduedate) {
		this.callduedate = callduedate;
	}
	/**
	 * @return the temp
	 */
	public Double getTemp() {
		return temp;
	}
	/**
	 * @param temp the temp to set
	 */
	public void setTemp(Double temp) {
		this.temp = temp;
	}
	/**
	 * @return the issue_check
	 */
	public String getIssue_Check() {
		return issue_check;
	}
	/**
	 * @param issue_check the issue_check to set
	 */
	public void setIssue_Check(String issue_check) {
		this.issue_check = issue_check;
	}
	/**
	 * @return the doc_return
	 */
	public int getDoc_Return() {
		return doc_return;
	}
	/**
	 * @param doc_return the doc_return to set
	 */
	public void setDoc_Return(int doc_return) {
		this.doc_return = doc_return;
	}
	/**
	 * @return the photo1
	 */
	public byte[] getPhoto1() {
		return photo1;
	}
	/**
	 * @param photo1 the photo1 to set
	 */
	public void setPhoto1(byte[] photo1) {
		CounterBean.photo1 = photo1;
	}
	/**
	 * @return the deptBranch
	 */
	public int getDeptBranch() {
		return deptBranch;
	}
	/**
	 * @param deptBranch the deptBranch to set
	 */
	public void setDeptBranch(int deptBranch) {
		this.deptBranch = deptBranch;
	}
	

}
