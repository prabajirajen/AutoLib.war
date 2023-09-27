package Common.businessutil.reports;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Util extends HttpServlet {
	
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		
		
		HttpSession session = request.getSession(true);
		
		
		int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
	}
	
	
	
	

}
