package Lib.Auto.Designation;


public  class DesignationBean   {

    // ----------------------------------------------------- Instance Variables
    
     private int code;
	 private String name;
	 private String desc;
	 private java.util.ArrayList al=null;
	 
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
	public String getName() {
		return name;
	}

	/**
	 * @param list
	 */
	public void setAl(java.util.ArrayList list) {
		al = list;
	}

	/**
	 * @param string
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
	public void setName(String string) {
		name = string;
	}

	public int getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}

   }
