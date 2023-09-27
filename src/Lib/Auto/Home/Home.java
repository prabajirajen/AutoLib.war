package Lib.Auto.Home;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.LoginUserService;

import com.library.autolib.portal.prototype.LibraryServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Home extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(Home.class);	
	String indexPage="";	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		performTask(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		performTask(request, response);

	}	
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException, IOException {
						
		try {		
	
			HttpSession session = request.getSession(true);				
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}			
		
			
			
			
			LoginUserService ss = LibraryServiceFactory.INSTANCE.getLoginUserService();
			
			int uRights=(Integer.parseInt((String.valueOf(session.getAttribute("UserRights")))));      // For Titan User Home Page
			
			if(uRights!=7)
			{
			  int branch=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
			  
			  log4jLogger.info("::::::::::::::::::BranchCode value:::::::::::"+branch);
			  
			  
			  String mcode = String.valueOf(session.getAttribute("UserID"));		
			  Map result = ss.loadHomeEvent(branch,mcode);				
			  byte[] userPhoto=null;
			  session.setAttribute("TotalCollection", result.get("TotalCollection"));
			  session.setAttribute("TotalMember", result.get("TotalMember"));
			  session.setAttribute("DueListCount", result.get("DueListCount"));	
			
			  session.setAttribute("IssueListCount", result.get("IssueListCount"));
			  session.setAttribute("ReturnListCount", result.get("ReturnListCount"));
			  session.setAttribute("RenewListCount", result.get("RenewListCount"));
			  
			  session.setAttribute("todayIssueListCount", result.get("todayIssueListCount"));
			  session.setAttribute("todayReturnListCount", result.get("todayReturnListCount"));
			  session.setAttribute("todayRenewListCount", result.get("todayRenewListCount"));
			  
			  
			  session.setAttribute("todayTransAmount", result.get("todayTransAmount"));
			  session.setAttribute("todaypaidAmount", result.get("todaypaidAmount"));
			  session.setAttribute("todayBalAmount", result.get("todayBalAmount"));
			  
			  session.setAttribute("todayGateActiveLogin", result.get("todayGateActiveLogin"));
			  

				session.setAttribute("member_code", result.get("member_code"));
				session.setAttribute("member_name", result.get("member_name"));
				session.setAttribute("dsname", result.get("dsname"));
				session.setAttribute("dname", result.get("dname"));
				session.setAttribute("expiry_date", result.get("expiry_date"));
				
				
				byte[] userImage=ss.getUserImage(userPhoto,mcode,branch);// for get user image 07-07-2015
				session.setAttribute("userImage", userImage);
				
			  
			  indexPage = "/Home/index.jsp";
			  dispatch(request, response, indexPage);
			}
			else
			{
              // For User Transaction History	
				 int branch=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
	
				String mcode = String.valueOf(session.getAttribute("UserID"));				
			  Map result = ss.loadUserTransactionHome(mcode,branch);	
			  byte[] userPhoto=null;
			  session.setAttribute("UserIssueCount", result.get("UserIssueCount"));
			  session.setAttribute("UserReturnCount", result.get("UserReturnCount"));
			  session.setAttribute("UserReserveCount", result.get("UserReserveCount"));
			  
			  
			  //shek
			  session.setAttribute("totalAmount", result.get("totalAmount"));
			  session.setAttribute("paidAmount", result.get("paidAmount"));
			  session.setAttribute("balAmount", result.get("balAmount"));
			  

				session.setAttribute("LibraryMessage", result.get("LibraryMessage"));
				session.setAttribute("WhatsNew", result.get("WhatsNew"));
				
				session.setAttribute("member_code", result.get("member_code"));
				session.setAttribute("member_name", result.get("member_name"));
				session.setAttribute("dsname", result.get("dsname"));
				session.setAttribute("dname", result.get("dname"));
				session.setAttribute("expiry_date", result.get("expiry_date"));
				
				
				byte[] userImage=ss.getUserImage(userPhoto,mcode,branch);// for get user image 07-07-2015
				session.setAttribute("userImage", userImage);
				
			  
			  indexPage = "/Home/userPage.jsp";
			  dispatch(request, response, indexPage);
			}
			
		} catch (Exception  e) {
			e.printStackTrace();
		 }		
	}		
		
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
		   // response.sendRedirect(indexPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
}
