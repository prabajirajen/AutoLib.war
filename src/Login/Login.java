package Login;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Login.UserIdbean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.search.SearchService;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.LoginUserService;

import com.library.autolib.portal.prototype.LibraryServiceFactory;


/**
 * @author selva
 *
 */
public class Login extends HttpServlet {
	
	UserIdbean newbean=new UserIdbean();
	String indexPage = null;
	
	private static Logger log4jLogger = Logger.getLogger(Login.class);
	String sLoginErr = "";
	String txtuserid = "";
	String txtpasword = "";
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException{
	
		log4jLogger.info("[Login:process init()] Inside");
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException {
		
		log4jLogger.info("[Login:process doGet()] Inside");
		
		
		HttpSession session = request.getSession(true);		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		

		try {
			
            SearchService rr = BusinessServiceFactory.INSTANCE.getSearchService(); // For Titan
			
			String flag="";
			flag=request.getParameter("flag");			
			
			if(flag.equals("load")){
				log4jLogger.info("start===========Login Branch List load=====");
				List BranchArrylist = new ArrayList();
				BranchArrylist=rr.getLoadBranchList();
				session.setAttribute("UserBranchList", BranchArrylist);
				response.sendRedirect("/AutoLib/index.jsp");
			}
			
			if(flag.equals("login")){
			
			String txtuserid = Security.getParam(request, "txtuserid");
			String txtpasword = Security.getParam(request, "txtpasword");
			String txtbranch = Security.getParam(request, "txtbranch"); //Security.getParam(request, "txtBranch");

			LoginUserService ss = LibraryServiceFactory.INSTANCE.getLoginUserService();
			
			User user = ss.getUser(txtuserid,txtpasword,txtbranch);
			
			
			
			System.out.println("txtbranch::::::::::::::::::"+txtbranch);
			
			if(user!=null){
				
				if( (!user.getUserId().equals(txtuserid)) || (!user.getPassword().equals(txtpasword)) )	{
					
					response.sendRedirect("/AutoLib/InvalidUser.jsp");
				}
				else if((user.getLastName().trim()).equalsIgnoreCase("NO"))
				{					
					response.sendRedirect("/AutoLib/InvalidUser.jsp");
				}
				else
				{
					BranchBean branch = ss.getBranchInfo(user.getBranchID());
					session.setAttribute("UserID", user.getUserId());
					session.setAttribute("UserRights", user.getFirstName());
					session.setAttribute("UserBranchID", user.getBranchID());
					//session.setAttribute("base64Logo",Base64.getEncoder().encodeToString(user.getLogo()));
					session.setAttribute("branchName", branch.getName().toString());
					session.setAttribute("instituteName", branch.getClg_name().toString());
					session.setAttribute("instituteAddress", branch.getAddress().toString());
					session.setAttribute("instituteEmail", branch.getEmail().toString());
					session.setAttribute("instituteEmailPassword", branch.getPassword().toString());
					//session.setAttribute("instituteLogo", session.getAttribute("instituteLogo"));
					
					session.setAttribute("instituteDesc", user.getBranchID());		
				
										
					List time = ss.getTimeDate(user.getUserId());
					
					int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
					
					/** --------------- Last Visiting Time --------------- */
					if (time != null && time.size() >= 1)
					{
						String dateTime = time.get(0).toString();
						String DT[] = dateTime.split(" ");
						String day = DT[0];
						String temp = DT[1];
						String date = day.split("-")[2] + "/" + day.split("-")[1] + "/" + day.split("-")[0];
						String lastVisitedTime = date + " " + temp;
						session.setAttribute("visitDateTime", lastVisitedTime);
						ss.updateTimeDate(user.getUserId());
					}
					else
					{
						session.setAttribute("visitDateTime", new Date());
						ss.saveTimeDate(user.getUserId());
					}
					
					
					List list = ss.SelectUserInfo(user.getUserId(),txtbranch);
					
					/** --------------- Last Visiting Member and Branch --------------- */
					if (list != null && list.size() >= 1)
					{
						Object[] obj = (Object[]) list.get(0);
						
						session.setAttribute("visitMember", obj[1].toString());   // Gets Member Name
						session.setAttribute("visitBranch", obj[2].toString());   // Gets Member Branch				
					}else {
				
						session.setAttribute("visitMember", user.getUserId());   // Gets Member Name
						session.setAttribute("visitBranch", "");   // Gets Member Branch
					}
					
					
					if(Integer.parseInt(user.getFirstName().toString().trim())!=7)
					{
						  byte[] userPhoto=null;
					    // Load Home Events
                        Map result = ss.loadHomeEvent(user.getBranchID(),user.getUserId());				
					
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
						  
						  session.setAttribute("member_code", result.get("member_code"));
							session.setAttribute("member_name", result.get("member_name"));
							session.setAttribute("dsname", result.get("dsname"));
							session.setAttribute("dname", result.get("dname"));
							session.setAttribute("expiry_date", result.get("expiry_date"));
							
							byte[] userImage=ss.getUserImage(userPhoto,user.getUserId(),branchID);// shek
							session.setAttribute("userImage", userImage);
						  
						  
					}
					else
					{
                        // Load transaction details for user 
						Map result = ss.loadUserTransactionHome(user.getUserId(),branchID);	
						  byte[] userPhoto=null;
						session.setAttribute("UserIssueCount", result.get("UserIssueCount"));
						session.setAttribute("UserReturnCount", result.get("UserReturnCount"));
						session.setAttribute("UserReserveCount", result.get("UserReserveCount"));
						
						session.setAttribute("totalAmount", result.get("totalAmount"));
						session.setAttribute("paidAmount", result.get("paidAmount"));
						session.setAttribute("balAmount", result.get("balAmount"));
					
						session.setAttribute("member_code", result.get("member_code"));
						session.setAttribute("member_name", result.get("member_name"));
						session.setAttribute("dsname", result.get("dsname"));
						session.setAttribute("dname", result.get("dname"));
						session.setAttribute("expiry_date", result.get("expiry_date"));
						
						byte[] userImage=ss.getUserImage(userPhoto,user.getUserId(),branchID);// for get user image 07-07-2015
						session.setAttribute("userImage", userImage);
						
						session.setAttribute("LibraryMessage", result.get("LibraryMessage"));
						session.setAttribute("WhatsNew", result.get("WhatsNew"));
						
						
					}					
					
				    //newbean.setstaffcode(user.getUserId()); //Stopped By RK on 13-09-2013 . For (Error In Counter transaction Module) Staff_Code taken the userid who have recently logged in.  
				    response.sendRedirect("new_index.jsp");
				}
				
		  }else{			    
				response.sendRedirect("/AutoLib/InvalidUser.jsp");
				log4jLogger.info("Invalid UserName Password..!!");
			 	//dispatch(request, response, indexPage);
			}			
		}
	
		} catch (Exception e) {
			//throw new ServletException(e);
			e.printStackTrace();
		}
	}
	/**
		 * Post Method of the servlet
	 * @param request request Object
	 * @param response response Object
	 * @return none
	 * @throws IOException
	 * @throws ServletException 
	 * @since 2004
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
