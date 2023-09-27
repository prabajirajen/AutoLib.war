package Lib.Auto.Author;
import Common.DataQuery;
public class AuthorBean {

    // ----------------------------------------------------- Instance Variables
    
	private   int code;
	private  String name=DataQuery.EMPTY_STRING;
	private  String desc="";
	private  String email="";
	
	private  int branchID;
	
	

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	/**
	 * @return code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	

	/**
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @param desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	}
