package Lib.Auto.Binding_Books;

import java.io.IOException;
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
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;

public class BindingBooksAction extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger
			.getLogger(BindingBooksAction.class);
	private static final long serialVersionUID = 1L;

	String flag = "", protocol = "", binder = "", DISPLAY_BINDDOC_MAS = "",
			strsql = "", strsql1 = "", strsql2 = "";

	StringBuffer sb = new StringBuffer();

	String indexPage = null;
	ResourceBundle rb = null;
	BookBindingBean ob = new BookBindingBean();

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
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			// PrintWriter out=response.getWriter();

			CirculationService ss = BusinessServiceFactory.INSTANCE
					.getCirculationService();
			int branchID = (Integer.parseInt((String.valueOf(session
					.getAttribute("UserBranchID"))))); // For Titan

			if (request.getParameter("flag").equals("clear")) {
				log4jLogger.info("start===========clear=====");
				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadBinderName();
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/BindingBooks/index.jsp";
				dispatch(request, response, indexPage);

			}
			if (request.getParameter("flag").equals("load")) {
				log4jLogger.info("start===========load=====");
				List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getLoadBinderName();
				request.setAttribute("binding", BindingArrylist);
				indexPage = "/BindingBooks/index.jsp";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("save")) {
				log4jLogger.info("start===========save=====");

				ob.setAccess_no(request.getParameter("AccessNo"));
				ob.setBinderName(request.getParameter("txtBinder"));
				ob.setDocument(request.getParameter("doc"));
				ob.setDate(Security.TextDate_Insert(request
						.getParameter("SendDate")));
				ob.setBranchCode(branchID);

				int count = ss.getBindingBooksSave(ob);

				if (count == 1) {

					indexPage = "/BindingBooks/index.jsp?check=Issuedbook";

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

				} else if (count == 2) {

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					indexPage = "/BindingBooks/index.jsp?check=BindingBooks";
				} else if (count == 4) {

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					indexPage = "/BindingBooks/index.jsp?check=notPresent";
				} else if (count == 5) {

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					indexPage = "/BindingBooks/index.jsp?check=Transfered";
				} else if (count == 6) {

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					indexPage = "/BindingBooks/index.jsp?check=Lost";
				} else if (count == 7) {

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					indexPage = "/BindingBooks/index.jsp?check=Missing";
				} else if (count == 8) {

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					indexPage = "/BindingBooks/index.jsp?check=Withdrawn";
				} else {

					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					indexPage = "/BindingBooks/index.jsp?check=saved";
				}
				indexPage = indexPage + "&binder="
						+ request.getParameter("txtBinder");
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag").equals("display")) {

				try {

					log4jLogger.info("start===========display=====");
					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);

					if (!request.getParameter("index").equals(null)
							&& !request.getParameter("index").equalsIgnoreCase(
									"No")) {
						strsql = " and binder_code ='"
								+ request.getParameter("index").toString()
								+ "'";
					}
					if (!request.getParameter("doc").equals(null)
							&& !request.getParameter("doc").equalsIgnoreCase(
									"No")) {
						strsql1 = " and doc_type ='"
								+ request.getParameter("doc").toString() + "'";
					}

					strsql2 = " and branch_code=" + branchID + " ";

					sb.append(strsql);
					sb.append(strsql1);
					sb.append(strsql2);

					List BindingDisplayArrylist = new ArrayList();
					BindingDisplayArrylist = ss
							.getBindingDisplay(sb.toString());

					request.setAttribute("search", BindingDisplayArrylist);

					indexPage = "/BindingBooks/index.jsp?check=display";
					dispatch(request, response, indexPage);

				} catch (Exception Ex) {

				} finally {

					try {
						strsql = "";
						strsql1 = "";
						strsql2 = "";
						sb = new StringBuffer();

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}

			if (request.getParameter("flag").equals("return")) {
				log4jLogger.info("start===========return=====");
				int count = ss.getBindingBooksReturn(
						request.getParameter("AccessNo"), branchID);

				if (count > 0) {
					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					indexPage = "/BindingBooks/index.jsp?check=deleted";

				} else {
					List BindingArrylist = new ArrayList();
					BindingArrylist = ss.getLoadBinderName();
					request.setAttribute("binding", BindingArrylist);
					indexPage = "/BindingBooks/index.jsp?check=notPresent";

				}
				dispatch(request, response, indexPage);
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
		System.out.println("======indexPage======" + indexPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
