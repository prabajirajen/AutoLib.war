package Common.businessutil;

import java.util.List;
import java.util.Map;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Branch.BranchBean;
import Login.User;

public interface LoginUserService {
	
	
	public User getUser(String userId,String pwd,String branch);
	
	/**
	 * Gets TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * @return list
	 * 
	 */
	public List getTimeDate(String userId);

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
	 * Init UpdateChangePassWord
	 * 
	 * @param user
	 *            the user
	 * @throws CampusBaseException
	 */
	
	
	/**
	 * Gets SelectUserInfo
	 * 
	 * @param userId
	 *            the user id
	 * @return list
	 * 
	 */
	public List SelectUserInfo(String userId,String txtbranch);
	
	public Map loadHomeEvent(int branch,String mcode);
	
	public Map loadUserTransactionHome(String Code,int branch);

	public byte[] getUserImage(byte[] userPhoto,String mcode,int branch);

	public BranchBean getBranchInfo(int branchID);
	
}
