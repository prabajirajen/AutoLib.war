package Lib.Auto.Course;



public  class CourseBean   {

    // ----------------------------------------------------- Instance Variables

    private int code;
	private String name = "";
	private String major = "";
	private int period;
	private String type = "";
	private String desc ="";
	private java.util.ArrayList al=null;
	
	private String branch = "";
	private int branchCode;


    

	/**
	 * @return
	 */
	public java.util.ArrayList getAl() {
		return al;
	}

	/**
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @return
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public int getPeriod() {
		return period;
	}

	/**
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param list
	 */
	public void setAl(java.util.ArrayList list) {
		al = list;
	}

	/**
	 * @param i
	 */
	public void setCode(int i) {
		code = i;
	}

	/**
	 * @param string
	 */
	public void setDesc(String string) {
		desc = string;
	}

	/**
	 * @param string
	 */
	public void setMajor(String string) {
		major = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param i
	 */
	public void setPeriod(int i) {
		period = i;
	}

	/**
	 * @param string
	 */
	public void setType(String string) {
		type = string;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	
	public int getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}
}
