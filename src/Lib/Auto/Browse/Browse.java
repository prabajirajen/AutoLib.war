package Lib.Auto.Browse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
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
import Common.businessutil.search.SearchService;
import Lib.Auto.Advanced.Adsearchbean;
import Lib.Auto.Simples.Searchbean;

import com.google.gson.Gson;

public class Browse extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Browse.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List BrowseArrylist;
	List AdsearchArrylist;
	String indexPage = null;
	Adsearchbean bean = new Adsearchbean();
	String flag = "", title1 = "";
	String SQL_Query = "", SQL_Query_Cnt = "", SQL_Query_Con = "",
			SQL_Query_std = "", SQL_Query_cur = "", SQL_Query_alpha = "";

	int i = 0, j = 0, k = 1, s = 0, len = 0;
	int rcount = 0;
	int pcount = 0;
	int top = 0, bottom = 1, txtno = 0;
	int book = 0;
	int non = 0;
	int report = 0;
	int std = 0;
	int thesis = 0;
	int journal = 0;
	int proceed = 0;
	int bookbank = 0;

	public static final String SQLCNT = "select count(access_no) as cnt from adv_search_browse where 2>1";
	public static final String Journal = "select * from journal_mas where 2>1";
	public static final String Journalatl = "select * from journal_articles where 2>1";
	public static final String SQL_Query_view = "select  * from adv_search_browse where 2>1";
	public static final String CD_VIEW = "select * from adv_search_browse where  remarks like '%+CD%' and access_no=?";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public synchronized void performTask(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}
			SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			 int branchID =(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID"))))); // For Titan
			
			response.setContentType("application/json");
			//System.out.println("dddddddddddddddddddddddddddddddddddddd  "+flag);
			
			try{
				String term = request.getParameter("name");
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase("") && !flag.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getQuickAutoSearch(term,flag);
			       for(Searchbean user: list){
					//System.out.println(user.getTitle());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }		
		}catch(Exception e){
			//e.printStackTrace();
		}  	
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			String filterQuery ="";
			 
			 if (branchID ==0) { 
				 filterQuery = ""; 
				 } 
			 else { 
				 filterQuery = " and branch_code="+ branchID + " "; }
		

			if (request.getParameter("hid") != null
					&& request.getParameter("hid").equals("search")) {

				if ((!Security.getParam(request, "type").equals(""))
						&& (Security.getParam(request, "doc_type")
								.equals("ALL"))) {

					if (!Security.getParam(request, "name").equals("")) {
						SQL_Query = SQL_Query + " and "
								+ Security.getParam(request, "type")
								+ " like '%"
								+ Security.getParam(request, "name") + "%'";

					}
				} else {
					if ((!Security.getParam(request, "type").equals(""))
							&& (!Security.getParam(request, "doc_type").equals(
									"ALL"))) {

						if (!Security.getParam(request, "name").equals("")) {
							SQL_Query = SQL_Query + " and "
									+ Security.getParam(request, "type")
									+ " like '%"
									+ Security.getParam(request, "name") + "%'"
									+ "and document ='"
									+ Security.getParam(request, "doc_type")
									+ "'";

						}
					}
				}

				// SQL_Query = SQL_Query + filterQuery;

				if (!Security.getParam(request, "txtBranch").equals("NO")) {

					SQL_Query = SQL_Query + " and branch_Code =  '"
							+ Security.getParam(request, "txtBranch") + "'";
				}

				bean.setsqlquery(SQL_Query);

				BrowseArrylist = ss.getBrowseSearch(SQL_Query);

				session.setAttribute("BrowseArrylist", BrowseArrylist);
				indexPage = "/Browse/browseSearch.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("doc") != null) {
				log4jLogger.info("start===========adsearchdoc=====");

				String query = "";

				query = bean.getsqlquery();

				SQL_Query = query + " and document=";

				SQL_Query = SQL_Query + "'" + request.getParameter("doc") + "'";

				BrowseArrylist = ss.getBrowseSearch(SQL_Query);

				session.setAttribute("BrowseArrylist", BrowseArrylist);
				indexPage = "/Browse/browseSearch.jsp";
				dispatch(request, response, indexPage);

			}

			if (request.getParameter("accno") != null) {
				log4jLogger.info("start===========browsesearchaccno=====");
				String accno = request.getParameter("accno");

				SQL_Query = "and ";
				SQL_Query = SQL_Query + "access_no" + " = '"
						+ request.getParameter("accno") + "' AND branch_name='"+request.getParameter("branch")+"'";

				AdsearchArrylist = ss.getFullViewSearch(SQL_Query);

				// session.setAttribute("AdsearchArrylist",AdsearchArrylist);
				request.setAttribute("AdsearchArrylist", AdsearchArrylist);
				indexPage = "/Browse/fullView.jsp";
				dispatch(request, response, indexPage);
			}

			if (request.getParameter("flag") != null) {
				if (request.getParameter("flag").equals("load")) {
					log4jLogger.info("start===========Branch List load=====");
					List BranchArrylist = new ArrayList();
					BranchArrylist = ss.getLoadBranchList();
					request.setAttribute("BranchList", BranchArrylist);
					indexPage = "/Browse/index.jsp";
					dispatch(request, response, indexPage);
				}
			}

		} catch (Exception sss) {
			sss.printStackTrace();

		}

		finally {
			SQL_Query = "";
			SQL_Query_Cnt = "";
			bottom = 1;
			book = 0;
			bookbank = 0;
			std = 0;
			proceed = 0;
			report = 0;
			thesis = 0;
			journal = 0;
			non = 0;
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
