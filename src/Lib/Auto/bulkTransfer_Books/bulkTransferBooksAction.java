package Lib.Auto.bulkTransfer_Books;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import Common.Security;
import Common.SplitAccessNoBean;// for split accessno
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Transfer_Books.BookTransferBean;


public class bulkTransferBooksAction extends HttpServlet implements
		Serializable {
	private static Logger log4jLogger = Logger
			.getLogger(bulkTransferBooksAction.class);
	private static final long serialVersionUID = 1L;

	String flag = "";
	String indexPage = null;

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
		
		System.out.println("==========txtBinder============"+request.getParameter("txtBinder"));

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			PrintWriter out = response.getWriter();
			CirculationService ss = BusinessServiceFactory.INSTANCE
					.getCirculationService();
			CalalogingService ss1 = BusinessServiceFactory.INSTANCE
					.getCalalogingService();

			int branchID = (Integer.parseInt((String.valueOf(session
					.getAttribute("UserBranchID"))))); // For Titan

			BookTransferBean ob = new BookTransferBean();
			BookTransferBean newbean = new BookTransferBean();
			
			
			
			//Random wise Transfer
			
			if(request.getParameter("flag").equals("RndNumber")){
				String Query="select access_no,title from book_mas where access_no in ('"+request.getParameter("randomNumberList")+"') and availability in('YES','REFERENCE')";
				ob = new BookTransferBean();
				ob.setordno(Integer.parseInt(request.getParameter("ordno")));
				ob.setdeptName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request
						.getParameter("SendDate")));
				log4jLogger.info("start===========RandamSearch====");
				List AccNoList=ss1.getRandamAccNoList(Query);
				request.setAttribute("AccessNoSearchList", AccNoList);
				request.setAttribute("AccessNoSearchListSize", AccNoList.size());

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getBranchName(branchID);
				request.setAttribute("binding", BindingArrylist);

				request.setAttribute("BeanObject", ob);

				indexPage = "/bulkTransferBooks/index.jsp?check=displayAccessNo";
				dispatch(request, response, indexPage);
			}
			
			if(request.getParameter("flag").equals("reTransRndNumber")){
				String Query="select access_no,title from transfer_view where dept_code like '"+request.getParameter("txtBinder")+"' and access_no in ('"+request.getParameter("randomNumberList")+"') and recovery=''";
				ob = new BookTransferBean();
				ob.setordno(Integer.parseInt(request.getParameter("ordno")));
				ob.setdeptName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request
						.getParameter("SendDate")));
				log4jLogger.info("start===========Re-TransferRandamSearch====");
				List AccNoList=ss1.getRandamAccNoList(Query);
				request.setAttribute("AccessNoSearchList", AccNoList);
				request.setAttribute("AccessNoSearchListSize", AccNoList.size());

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getBranchName(branchID);
				request.setAttribute("binding", BindingArrylist);

				request.setAttribute("BeanObject", ob);

				indexPage = "/bulkTransferBooks/index.jsp?check=displayAccessNo";
				dispatch(request, response, indexPage);
			}
			

			if (request.getParameter("flag").equals("search")) {

				ob = new BookTransferBean();
				ob.setBranchCode(branchID);
				
				log4jLogger.info("start===========SEARCH====");

				ob.setAccess_no(request.getParameter("fromAccessNo"));
				ob.setToaccess_no(request.getParameter("toAccessNo"));
				ob.setordno(Integer.parseInt(request.getParameter("ordno")));
				ob.setdeptName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request
						.getParameter("SendDate")));

				List AccNoList = ss1.getAccNoList(ob);
				
				request.setAttribute("AccessNoSearchList", AccNoList);
				request.setAttribute("AccessNoSearchListSize", AccNoList.size());

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getBranchName(branchID);
				request.setAttribute("binding", BindingArrylist);

				request.setAttribute("BeanObject", ob);

				indexPage = "/bulkTransferBooks/index.jsp?check=displayAccessNo";
				dispatch(request, response, indexPage);
			}
			
			
			
		

			if (request.getParameter("flag").equals("transferBookSearch")) {
				log4jLogger.info("start===========transfer Book SEARCH====");

				ob.setAccess_no(request.getParameter("fromAccessNo"));
				ob.setToaccess_no(request.getParameter("toAccessNo"));
				ob.setBranchCode(branchID);
				ob.setdeptcode(Integer.parseInt(request.getParameter("txtBinder")));
				

				List TransAccNoList = ss1.getTransAccNoList(ob);

				request.setAttribute("AccessNoSearchList", TransAccNoList);
				request.setAttribute("AccessNoSearchListSize",
						TransAccNoList.size());

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getBranchName(branchID);
				request.setAttribute("binding", BindingArrylist);

				ob.setdeptName(request.getParameter("txtBinder"));
				request.setAttribute("BeanObject", ob);

				indexPage = "/bulkTransferBooks/index.jsp?check=displayAccessNo";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("load")) {
				log4jLogger.info("start===========load=====");
				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName(branchID);
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/bulkTransferBooks/index.jsp";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("new")) {
				log4jLogger.info("start===========new=====");

				ob = ss.getTransferMax();
				request.setAttribute("BeanObject", ob);

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName(branchID);
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/bulkTransferBooks/index.jsp?check=newordno";
				dispatch(request, response, indexPage);
			}
			if (request.getParameter("flag").equals("bulksave")) {

				log4jLogger.info("start===========bulksave=====");
				String sss = request.getParameter("flag1");
				ob.setordno(Integer.parseInt(request.getParameter("ordno")));
				ob.setAccess_no(request.getParameter("AccessNo"));
				ob.setdeptName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request
						.getParameter("SendDate")));
				ob.setBranchCode(branchID);
				ob.setAuthor(request.getParameter("flag1"));// for bulk
															// access_no

				log4jLogger.info("Bulk AccessNo Values" + ob.getAuthor());

				newbean = ss.getbulkTransferBooksSave(ob);

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName(branchID);
				request.setAttribute("binding", BindingArrylist);

				indexPage = "/bulkTransferBooks/index.jsp?check=saved";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("display")) {
				log4jLogger.info("start===========display=====");
				
				BookTransferBean ob1 = new BookTransferBean();
				ob1.setdeptcode(Integer.parseInt(request.getParameter("txtBinder")));
				ob1.setAccess_no(request.getParameter("fromAccessNo"));
				ob1.setToaccess_no(request.getParameter("toAccessNo"));

				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadDeptName(branchID);
				request.setAttribute("binding", BindingArrylist);

				List BindingDisplayArrylist = new ArrayList();
				BindingDisplayArrylist = ss.getTransferDisplay(ob1);
				request.setAttribute("search", BindingDisplayArrylist);

				indexPage = "/bulkTransferBooks/index.jsp?check=display";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("bulkreturn")) {
				log4jLogger.info("start===========bulk transfer return====="
						+ request.getParameter("flag1"));

				int count = ss.getbulkTransferBooksReturn(
						request.getParameter("flag1"), branchID);

				if (count > 0) {
					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadDeptName(branchID);
					request.setAttribute("binding", BindingArrylist);
					indexPage = "/bulkTransferBooks/index.jsp?check=deleted";
					dispatch(request, response, indexPage);
				}
			}
		} catch (Exception sss) {
			throw new ServletException(sss);

		} finally {

			try {
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * Instance variable for SQL statement property
	 */

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
