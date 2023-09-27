	package Common.businessutil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;

import Common.DataQuery;
import Common.Security;
import Lib.Auto.Branch.BranchBean;
import Login.User;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class LoginUserDaoImpl extends BaseDBConnection implements LoginUserDao {
	java.sql.Connection con = null;

	java.sql.PreparedStatement Prest = null;

	java.sql.ResultSet rs = null;

	public User findById(String userId, String pwd,String branch) {  // For Titan
		User user = null;
		try {
			con = getSession().connection();

			String sql = "select Login_ID, Login_Password, User_Rights, Login_Flag, Branch_Code from login_mas where Login_ID ='"
					+ userId + "' and Login_Password='" + pwd + "' AND branch_code='"+branch+"'";
			
/**			String sql = "select Login_ID, Login_Password, User_Rights, Login_Flag, Branch_Code from login_mas where Login_ID ='"
				+ userId + "' and Login_Password='" + pwd + "' and Branch_Code='" + branch + "'";  */			
           			
			Prest = con.prepareStatement(sql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("Login_ID"));
				user.setPassword(rs.getString("Login_Password"));
				user.setFirstName(rs.getString("User_Rights"));
				user.setLastName(rs.getString("Login_Flag"));
				user.setBranchID(rs.getInt("Branch_Code"));
			}
		} catch (Exception e) {
           e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}
	
	


	/**
	 * Find TimeDate
	 * 
	 * @param userId
	 *            user id.
	 * @return the List
	 */
	public List findTimeDate(String userId)
	{
		String query = getSession().getNamedQuery("UserSelectTimeDateQuery").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("userId", userId);
		return sql.list();
	}

	/**
	 * Save TimeDate
	 * 
	 * @param userId
	 *            the user id
	 */
	public void saveTimeDate(String userId)
	{
		String query = getSession().getNamedQuery("UserSaveTimeDateQuery").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("userId", userId);
		sql.executeUpdate();

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
		String queryUpdate = getSession().getNamedQuery("UserUpdateTimeDateQuery").getQueryString();
		SQLQuery sqlUpdate = getSession().createSQLQuery(queryUpdate);
		sqlUpdate.setParameter("userId", userId);
		sqlUpdate.executeUpdate();
	}

	
	/**
	 * Select SelectUserInfo
	 * 
	 * @param userId
	 *            the user id
	 * 
	 */
	public List SelectUserInfo(String userId,String branchCode)
	{
		String query = getSession().getNamedQuery("LibUserSelectQuery").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		sql.setParameter("userId", userId);
		sql.setParameter("branchCode", branchCode);
		return sql.list();
	}
	
	
	public Map loadHomeEvent(int branch,String mcode)
	{
		Map<Object, Object> resParam = new HashMap<Object, Object>();
		
		try {
			String strsql = "";	
			
			
				strsql = strsql + " and Branch_Code="+branch+" ";	 
			
			
			
			// For Library Collection
			con = getSession().connection();          			
			Prest = con.prepareStatement(DataQuery.TotCollection_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("TotalCollection", rs.getInt(1));				
			}
			
			String deptBranch = "";
			/**if (branch == 2) {
				deptBranch = " and branch_code=" + branch + " ";
			} else*/ 
				deptBranch = " and branch_code=" + branch + " ";//deptChanges
			
			
			// For Member Count
			con = getSession().connection();          			
			Prest = con.prepareStatement(DataQuery.TotMember_Count + deptBranch);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("TotalMember", rs.getInt(1));				
			}
			
			
			
			
			// For Due List  			
			Prest = con.prepareStatement(DataQuery.DueList_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("DueListCount", rs.getInt(1));				
			}	
			
			
			// For total Issue List  				
			Prest = con.prepareStatement(DataQuery.TotIssue_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("IssueListCount", rs.getInt(1));				
			}
			
			
			// For today Issue List  	
			Prest = con.prepareStatement(DataQuery.todayIssue_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayIssueListCount", rs.getInt(1));				
			}
			
			
		//------------------------------------------------	
			Prest = con.prepareStatement(DataQuery.todayTransSumAmount+strsql);//deptChanges
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayTransAmount", rs.getDouble(1));				
			}
			
			
			Prest = con.prepareStatement(DataQuery.todayPaymentSumAmount+" and p.branch_code="+branch);//deptChanges
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todaypaidAmount", rs.getDouble(1));				
			}
			
			Prest = con.prepareStatement(DataQuery.todayBalSumAmount+" and t.branch_code="+branch);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayBalAmount", rs.getDouble(1));				
			}else{
				resParam.put("todayBalAmount", "0.00");	
			}
			
			
			
			//-----------------------------------------
			
			
			
			
			
			
			
			// For total Return List  				
			Prest = con.prepareStatement(DataQuery.TotReturn_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("ReturnListCount", rs.getInt(1));				
			}
			
			
			// For today Return List  				
						Prest = con.prepareStatement(DataQuery.todayReturn_Count + strsql);
						rs = Prest.executeQuery();

						if (rs.next()) {
							
							resParam.put("todayReturnListCount", rs.getInt(1));				
						}
			
			
			
			// For total Renew List  					
			Prest = con.prepareStatement(DataQuery.TotRenew_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("RenewListCount", rs.getInt(1));				
			}
			
			// For today Renew List  					
			Prest = con.prepareStatement(DataQuery.todayRenew_Count + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayRenewListCount", rs.getInt(1));				
			}
			
			// For Active Gate Login
			Prest = con.prepareStatement(DataQuery.todayActiveGateLogin + strsql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				
				resParam.put("todayGateActiveLogin", rs.getInt(1));				
			}
			
			
			
			
			

			
			Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
					
					Prest.setString(1, mcode);
					Prest.setInt(2, branch);
					
					rs = Prest.executeQuery();
					if (rs.next()) {
						resParam.put("member_code", rs.getString("member_code"));
						resParam.put("member_name", rs.getString("member_name"));
						resParam.put("dsname", rs.getString("dsname"));//designation
						resParam.put("dname", rs.getString("dname"));//department
						resParam.put("expiry_date", Security.TextDate_member(rs.getString("expiry_date")));
					}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return resParam;		
	}
	
public byte[] findUserImage(byte[] userImage,String mcode,int branch){
	
		
		
		try {
			Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			Prest.setString(1, mcode);
			Prest.setInt(2, branch);
			rs = Prest.executeQuery();
	
			if (rs.next()) {
				userImage=rs.getBytes("photo");
			}
				
		}catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		
		return userImage;
	}

	
	public Map loadUserTransactionHome(String code,int branch)
	{
		Map<Object, Object> resParam = new HashMap<Object, Object>();
		
		try {
			// For User Transaction History
			
		
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ACCOUNT_ISSUE_COUNT);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("UserIssueCount", rs.getString("issuecount"));
			}
			
			Prest = con.prepareStatement(DataQuery.ACCOUNT_RETURN_COUNT);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("UserReturnCount", rs.getString("returncount"));
			}
			
			Prest = con.prepareStatement(DataQuery.ACCOUNT_RESERVE_COUNT);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("UserReserveCount", rs.getString("reservecount"));
			}
			
			//shek
			
		Prest = con.prepareStatement(DataQuery.Account_Fine_Details);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				
				resParam.put("totalAmount", rs.getString("total_fine"));
				resParam.put("paidAmount", rs.getString("paid_amount"));
				resParam.put("balAmount", rs.getString("bal_amount"));
				
			}else{
				
				resParam.put("totalAmount","0");
				resParam.put("paidAmount","0");
				resParam.put("balAmount","0");
				
			}
			

			
			
			Prest = con.prepareStatement(DataQuery.libraryMessage);
			//Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				

				resParam.put("LibraryMessage", rs.getString(2));
				resParam.put("WhatsNew", rs.getString(3));
			}	
			
			
			
			
	Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
			
			Prest.setString(1, code);
			Prest.setInt(2, branch);
			rs = Prest.executeQuery();
			if (rs.next()) {
				resParam.put("member_code", rs.getString("member_code"));
				resParam.put("member_name", rs.getString("member_name"));
				resParam.put("dsname", rs.getString("dsname"));//designation
				resParam.put("dname", rs.getString("dname"));//department
				resParam.put("expiry_date", Security.TextDate_member(rs.getString("expiry_date")));
			}
				
				
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		
		
		return resParam;			
	}

	public BranchBean getBranchInfo(int branchID) {  // For Titan
		BranchBean branch = null;
		try {
			con = getSession().connection();

			String sql = "SELECT branch_code,branch_name,clg_name,address,email_id,address,`password`,logo,short_desc FROM branch_mas WHERE 2>1 AND branch_code='"+branchID+"'";
					
           			
			Prest = con.prepareStatement(sql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				branch = new BranchBean();
				branch.setCode(rs.getInt("branch_code"));
				branch.setName(rs.getString("branch_name"));
				branch.setClg_name(rs.getString("clg_name"));
				branch.setAddress(rs.getString("address"));
				branch.setEmail(rs.getString("email_id"));
				branch.setPassword(rs.getString("password"));
				branch.setLogo(rs.getBytes("logo"));
				branch.setDesc(rs.getString("short_desc"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return branch;
	}
	
	
	
	
}
