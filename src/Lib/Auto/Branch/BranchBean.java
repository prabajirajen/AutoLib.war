package Lib.Auto.Branch;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public  class BranchBean  implements Serializable{

    // ----------------------------------------------------- Instance Variables
    
	private static final long serialVersionUID = 1L;
	
	
	public BranchBean(){
		
	}	
	private int code;
	private String name="";
	private String desc="";
	
	private String clg_name;
	private String address="";
	private String email="";
	private String password="";
	private String short_desc="";
	private byte[] logo = null;
	
    public String getClg_name() {
		return clg_name;
	}



	public void setClg_name(String clg_name) {
		this.clg_name = clg_name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getShort_desc() {
		return short_desc;
	}



	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}



	public byte[] getLogo() {
		return logo;
	}



	public void setLogo(byte[] logo) {
		this.logo = logo;
	}



	//static String email = null;
	private java.util.ArrayList al=null;
	
   

    // ----------------------------------------------------------- Properties

    public int getCode() {
	
	return code;
    }


    
    public void setCode(int i) {

        code = i;

    }

//-------------------------------------
    
    public String getName() {

	return name;

    }


    
    public void setName(String string) {

        name = string;

    }
//------------------------------------------------

    
    public String getDesc() {

	return desc;

    }


    
    public void setDesc(String string) {

        desc = string;

    }


 //--------------------------------------   
  public ArrayList getAl() {
	
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
    
}
