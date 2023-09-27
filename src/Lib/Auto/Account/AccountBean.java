package Lib.Auto.Account;
import Common.DataQuery;
public class AccountBean {

    // ----------------------------------------------------- Instance Variables
    

	private  String uid=null;
	private  String pwd=null;
	private  String issuecount=null;
	private  String returncount=null;
	private  String reservecount=null;
	private  String uname=null;
	private  String accno=null;
	private  String title=null;
	private  String author=null;
	private  String issuedt=null;
	private  String returndt=null;
	private  String duedt=null;
	private  String dtype=null;
	private  String resdat=null;
	private  String availability=null;
	private  String newpwd=null;
	private  String cfmpwd=null;
	private  String fineamount="";
	
	
	
	
	
	
	
	private String billNo="";
	
	
	
	
 	
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getFineamount() {
		return fineamount;
	}

	public void setFineamount(String fineamount) {
		this.fineamount = fineamount;
	}

	private  int branch;
	
	private static byte[] photo1=null;

    public void setPhoto1(byte[] ad) {
		photo1 = ad;
	}

	public byte[] getPhoto1() {
		return photo1;
	}

//------------------dtype-------------------	
	
	public void setcfmpwd(String cfmpwd) {
		this.cfmpwd = cfmpwd;
	}

	public String getcfmpwd() {
		return cfmpwd;
	}
//------------------dtype-------------------	
	
	public void setnewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getnewpwd() {
		return newpwd;
	}
//------------------dtype-------------------	
	
	public void setavailability(String availability) {
		this.availability = availability;
	}

	public String getavailability() {
		return availability;
	}
//------------------dtype-------------------	
	
	public void setresdat(String resdat) {
		this.resdat = resdat;
	}

	public String getresdat() {
		return resdat;
	}
//------------------dtype-------------------	
	
	public void setdtype(String dtype) {
		this.dtype = dtype;
	}

	public String getdtype() {
		return dtype;
	}
//------------------duedt-------------------	
	
	public void setduedt(String duedt) {
		this.duedt = duedt;
	}

	public String getduedt() {
		return duedt;
	}
//------------------returndt-------------------	
	
	public void setreturndt(String returndt) {
		this.returndt = returndt;
	}

	public String getreturndt() {
		return returndt;
	}
//------------------issuedt-------------------	
	
	public void setissuedt(String issuedt) {
		this.issuedt = issuedt;
	}

	public String getissuedt() {
		return issuedt;
	}
//------------------title-------------------	
	
	public void setauthor(String author) {
		this.author = author;
	}

	public String getauthor() {
		return author;
	}
//------------------title-------------------	
	
	public void settitle(String title) {
		this.title = title;
	}

	public String gettitle() {
		return title;
	}

//------------------accno-------------------	
	
	public void setaccno(String accno) {
		this.accno = accno;
	}

	public String getaccno() {
		return accno;
	}

	
//------------------UID-------------------	
	
	public void setuid(String uid) {
		this.uid = uid;
	}

	public String getuid() {
		return uid;
	}

	
//-------------------PWD--------------------	
	public void setpwd(String pwd) {
		this.pwd = pwd;
	}
	public String getpwd() {
		return pwd;
	}
	
//	-------------------issuecount--------------------	
	public void setissuecount(String issuecount) {
		this.issuecount = issuecount;
	}
	public String getissuecount() {
		return issuecount;
	}
//	-------------------returncount--------------------	
	public void setreturncount(String returncount) {
		this.returncount = returncount;
	}
	public String getreturncount() {
		return returncount;
	}	
//	-------------------reservecount--------------------	
	public void setreservecount(String reservecount) {
		this.reservecount = reservecount;
	}
	public String getreservecount() {
		return reservecount;
	}		
//	-------------------uname--------------------	
	public void setuname(String uname) {
		this.uname = uname;
	}
	public String getuname() {
		return uname;
	}

	public int getBranch() {
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}		
	
	


	}
