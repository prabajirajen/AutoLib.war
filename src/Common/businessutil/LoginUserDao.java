package Common.businessutil;

import java.util.List;
import java.util.Map;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Branch.BranchBean;
import Login.User;

public interface LoginUserDao {
	
	public User findById(String userId,String pwd,String branch);
	


	/**
	 * Find TimeDate
	 * 
	 * @param userId
	 *            user id.
	 * @return the List
	 */
	public List findTimeDate(String userId);

	/**
	 * Save TimeDate
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveTimeDate(String userId);

	/**
	 * Update TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * 
	 */
	public void updateTimeDate(String userId);
	
	
	/**
	 * Find SelectUserInfo
	 * 
	 * @param userId
	 *            user id.
	 * @return the List
	 */
	public List SelectUserInfo(String userId,String branch);	
	
	public Map loadHomeEvent(int branch,String mcode);
	
	public Map loadUserTransactionHome(String code,int branch);
	
	public byte[] findUserImage(byte[] userImage,String mcode,int branch);



	public BranchBean getBranchInfo(int branchID);
	
}
