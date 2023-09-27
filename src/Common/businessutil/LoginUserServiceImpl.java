package Common.businessutil;

import java.util.List;
import java.util.Map;

import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Branch.BranchBean;
import Login.User;

public class LoginUserServiceImpl implements LoginUserService {
	private LoginUserDao userDao;

	public LoginUserServiceImpl() {
	}
	
	public LoginUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(LoginUserDao userDao) {
		this.userDao = userDao;
	}
	
	public User getUser(String userId,String pwd,String branch) {
		return userDao.findById(userId,pwd,branch);
	}

	
	
	/**
	 * Gets TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * @return list
	 * 
	 */
	public List getTimeDate(String userId)
	{
		return userDao.findTimeDate(userId);
	}

	/**
	 * Save TimeDate
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveTimeDate(String userId)
	{
		userDao.saveTimeDate(userId);
	}

	/**
	 * Update TimeDate
	 * 
	 * @param userId
	 *            the user id
	 * 
	 */
	public void updateTimeDate(String userId)
	{
		userDao.updateTimeDate(userId);
	}
	

	/**
	 * Gets SelectUserInfo
	 * 
	 * @param userId
	 *            the user id
	 * @return list
	 * 
	 */	
	public List SelectUserInfo(String userId,String branchCode)
	{
		return userDao.SelectUserInfo(userId,branchCode);
	}
	
	
	public Map loadHomeEvent(int branch,String mcode)
	{
		return userDao.loadHomeEvent(branch,mcode);
	}
	
	public Map loadUserTransactionHome(String code,int branch)
	{
		return userDao.loadUserTransactionHome(code,branch);
	}
	
	public byte[] getUserImage(byte[] getUserImage,String mcode,int branch){
		
		return userDao.findUserImage(getUserImage,mcode,branch);
		
	}

	public BranchBean getBranchInfo(int branchID) {
		return userDao.getBranchInfo(branchID);
	}
	
}
