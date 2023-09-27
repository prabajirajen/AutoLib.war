package Lib.Auto.QuestionBank;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.MemberTransfer.MemberTransRefBean;

public class QuestionBank extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(QuestionBank.class);

		
	private static final long serialVersionUID = -8906987252709033668L;

	String flag = "";
	String indexPage = null;
	QuestionBankBean ob = new QuestionBankBean();

	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			AdminService ss1 = BusinessServiceFactory.INSTANCE.getAdminService();
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
			PrintWriter out = response.getWriter();
		

			flag = request.getParameter("flag");
			
			MemberTransRefBean beanObject = new MemberTransRefBean();
			beanObject.setBranchCode(branchID);
			
			List departmentList = ss1.getQBDepartmentList(beanObject);				
			request.setAttribute("departmentSearchList", departmentList);
			
			List courseList = ss1.getCourseList(beanObject);
			request.setAttribute("courseSearchList", courseList);
			
			List QBSubjectList = ss1.getQBSubjectList(beanObject);
			request.setAttribute("QBSubjectCodeSearchList", QBSubjectList);
			
			
			List QBSubjectNameList = ss1.getQBSubjectNameList(beanObject);
			request.setAttribute("QBSubjectNameSearchList", QBSubjectNameList);
			
			
			
			
			

			if (flag.equals("new")) {
				log4jLogger.info("new===========flag====="+flag);
				ob = new QuestionBankBean();
				ob = ss.getQuestionBankMax();
				request.setAttribute("BeanObject",ob);
				
				indexPage = "/QuestionBank/index.jsp?check=newNews";
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("update")) {
				log4jLogger.info("update===========flag====="+flag);
				ob.setQcode(Integer.parseInt(request.getParameter("qcode")));
				ob.setQdate(Security.TextDate_Insert(request.getParameter("qdate").trim()));
				ob.setUniname(request.getParameter("uniname"));
				ob.setSubcode(request.getParameter("subcode"));
				ob.setSubname(request.getParameter("subname"));
				ob.setDname(request.getParameter("dname"));
				ob.setQcourse(request.getParameter("qcourse"));
				ob.setQyear(request.getParameter("qyear"));
				ob.setQmonth(request.getParameter("qmonth"));
				ob.setSemester(request.getParameter("semester"));
				ob.setRemarks1(request.getParameter("remarks1"));
				ob.setRemarks2(request.getParameter("remarks2"));
				ob.setContents(request.getParameter("content"));	
				int count=ss.getQuestionBankUpdate(ob);
				indexPage = "/QuestionBank/index.jsp?check=UpdateKeywords";
				dispatch(request, response, indexPage);
				
			}
			
			if (flag.equals("search")) {
				log4jLogger.info("search===========flag====="+flag);
				ob = new QuestionBankBean();				
				
				ob=ss.getQuestionBankSearch(Integer.parseInt(request.getParameter("qcode")),branchID);
				
				if(ob!=null){
					request.setAttribute("BeanObject", ob);
					
					indexPage = "/QuestionBank/index.jsp?check=searchKeywords";	
				}else{
					indexPage = "/QuestionBank/index.jsp?check=FailKeywords";
				}
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("delete")) {
				log4jLogger.info("delete===========flag====="+flag);
				ob = new QuestionBankBean();
				ob=ss.getQuestionBankSearch(Integer.parseInt(request.getParameter("qcode")),branchID);

				if(ob!=null){
					request.setAttribute("BeanObject", ob);
					
					indexPage = "/QuestionBank/index.jsp?check=deleteCheck";	
				}else{
					indexPage = "/QuestionBank/index.jsp?check=FailKeywords";
				}
				dispatch(request, response, indexPage);
			}
			
			if(flag.equals("Confirmdete")){
				log4jLogger.info("Confirmdete===========flag====="+flag);
							
					int count=ss.getQuestionBankDelete(Integer.parseInt(request.getParameter("qcode")));

					indexPage = "/QuestionBank/index.jsp?check=DeleteKeywords";
				
				dispatch(request, response, indexPage);
			}
			
			if (flag.equals("save")) {
				log4jLogger.info("save===========flag====="+flag);
				ob = new QuestionBankBean();
				ob.setQcode(Integer.parseInt(request.getParameter("qcode")));
				
				int News_Mas_code=ss.getQuestionBankMas(Integer.parseInt(request.getParameter("qcode")));
				
				log4jLogger.info("News_Mas_Values   +++++++++++++++++++++ :  "+News_Mas_code);
				
				
				if(News_Mas_code > 0){
					
					
					ob = new QuestionBankBean();

					ob.setQcode(Integer.parseInt(request.getParameter("qcode")));
					ob.setQdate(request.getParameter("qdate").trim());
					ob.setUniname(request.getParameter("uniname"));
					
					ob.setSubname(request.getParameter("subname"));				
					ob.setSubcode(request.getParameter("subcode"));

					ob.setDname(request.getParameter("dname"));
					ob.setQcourse(request.getParameter("qcourse"));
					ob.setQyear(request.getParameter("qyear"));
					ob.setQmonth(request.getParameter("qmonth"));
					ob.setSemester(request.getParameter("semester"));
					
					ob.setRemarks1(request.getParameter("remarks1"));
					ob.setRemarks2(request.getParameter("remarks2"));
					ob.setContents(request.getParameter("content"));
					ob.setBranchCode(branchID);
					request.setAttribute("BeanObject", ob);
					indexPage = "/QuestionBank/index.jsp?check=UpdateCheck";
				}
					
				else{
					
					ob = new QuestionBankBean();
					ob.setQcode(Integer.parseInt(request.getParameter("qcode")));
				
					ob.setQdate(Security.TextDate_Insert(request.getParameter("qdate").trim()));
					ob.setUniname(request.getParameter("uniname"));
 
					ob.setSubname(request.getParameter("subname"));
					ob.setSubcode(request.getParameter("subcode"));
					
					ob.setDname(request.getParameter("dname"));
					ob.setQcourse(request.getParameter("qcourse"));
					ob.setQyear(request.getParameter("qyear"));
					ob.setQmonth(request.getParameter("qmonth"));
					ob.setSemester(request.getParameter("semester"));
					
					ob.setRemarks1(request.getParameter("remarks1"));
					ob.setRemarks2(request.getParameter("remarks2"));
					ob.setContents(request.getParameter("content"));
					ob.setBranchCode(branchID);
					
					int count=ss.getQuestionBankSave(ob);
					indexPage = "/QuestionBank/index.jsp?check=SaveKeyword";
				}
				dispatch(request, response, indexPage);
			}
               

		
		
		
		} catch (Exception sss) {
			throw new ServletException(sss);

		} finally {
			try{
				

			}catch(Exception e){
			e.printStackTrace();
			}

		}

	}

	/** 
	 * Instance variable for SQL statement property
	 */

	/****************************************************************
	 *prepare the sql statement for execution
	 **/
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

	/* (non-Javadoc)
	 * @see Lib.Auto.Author.Author_Interface#newRecord()
	 */

}
