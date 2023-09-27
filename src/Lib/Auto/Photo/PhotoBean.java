package Lib.Auto.Photo;

import java.util.Arrays;

public class PhotoBean    {
	
	private String memberid;
	private String fileName;	
	private byte[] photo;
	private int branchCode;
	

	public String getFileName() {
		return fileName;
	}

	public byte[] getPhoto() {
		return photo;
	}
	
	public String getMemberid() {
		return memberid;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public int getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(int branchCode) {
		this.branchCode = branchCode;
	}

	@Override
	public String toString() {
		return "PhotoBean [memberid=" + memberid + ", fileName=" + fileName
				+ ", photo=" + Arrays.toString(photo) + ", branchCode="
				+ branchCode + "]";
	} 
	
	
	
}