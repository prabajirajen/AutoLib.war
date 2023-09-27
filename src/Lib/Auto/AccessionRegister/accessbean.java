package Lib.Auto.AccessionRegister;
import java.io.*;
import java.util.*;
import java.sql.*;

public  class accessbean   {

    // ----------------------------------------------------- Instance Variables
    static java.util.ArrayList al=null;

    public String getAccessno() {
		return accessno;
	}



	public void setAccessno(String accessno) {
		this.accessno = accessno;
	}



	public String getDoctype() {
		return doctype;
	}



	public void setDoctype(String doctype) {
		this.doctype = doctype;
	}



	private String accessno;
    private String doctype;


    // ----------------------------------------------------------- Properties


  public ArrayList getAl() {
	return al;
    }


    
    public void setAl(ArrayList al) {

        this.al = al;

    }

}
