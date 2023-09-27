/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.Journal_ArticleSearch;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import java.io.IOException;
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

public class JournalAltSearch extends HttpServlet implements Serializable {
	
	private static Logger log4jLogger = Logger.getLogger(JournalAltSearch.class);
	private static final long serialVersionUID = 1L;
			
	String indexPage = null;
	List SearchArrylist;
	List AdsearchArrylist;
		
	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}
	
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			
			  if (request.getParameter("hid") != null&& request.getParameter("hid").equals("search")) {
			  
				  SQL_Query = "";
				  
				
				if (!Security.getParam(request, "name").equals("")) {					
						
						SQL_Query =	SQL_Query+ " and j.jnl_name like '%"	+ Security.getParam(request, "name")+ "%'";
				}				  
				  
				if (!Security.getParam(request, "title").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.atl_title like '%"	+ Security.getParam(request, "title")+ "%'";
				}
				
				if (!Security.getParam(request, "author").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.atl_author like '%"	+ Security.getParam(request, "author")+ "%'";
				}				
				
				if (!Security.getParam(request, "altno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.atl_no like '%"	+ Security.getParam(request, "altno")+ "%'";
				}								
				
				if (!Security.getParam(request, "year").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.issue_year like '%"	+ Security.getParam(request, "year")+ "%'";
				}								
				
				if (!Security.getParam(request, "month").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.issue_month like '%"	+ Security.getParam(request, "month")+ "%'";
				}				
				
				if (!Security.getParam(request, "bvolno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.bvol_no like '%"	+ Security.getParam(request, "bvolno")+ "%'";
				}								
				
				if (!Security.getParam(request, "volno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.vol_no like '%"	+ Security.getParam(request, "volno")+ "%'";
				}								
				
				if (!Security.getParam(request, "issueno").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and j.issue_no like '%"	+ Security.getParam(request, "issueno")+ "%'";
				}
				
				if (!Security.getParam(request, "subject").equals("")) {
					
					SQL_Query =SQL_Query+ " and j.atl_subject like '%"+ Security.getParam(request, "subject")+ "%'";
				}
				
				if (!Security.getParam(request, "abstract").equals("")) {
					
					SQL_Query =SQL_Query+ " and j.atl_abstract like '%"+ Security.getParam(request, "abstract")+ "%'";
				}
							
				if (!Security.getParam(request, "keywords").equals("")) {
					
					SQL_Query =SQL_Query+ " and j.atl_keywords like '%"+ Security.getParam(request, "keywords")+ "%'";
				}	
				
				if (!Security.getParam(request, "txtBranch").equals("NO")) 
                {
			
					SQL_Query =SQL_Query+ " and j.branch_Code =  '"+ Security.getParam(request, "txtBranch")+ "'";
				}
				
			log4jLogger.info("======================SQL_Query================="+SQL_Query);
				
			SearchArrylist=ss.getJournalArticleSearch(SQL_Query);
		    			
			session.setAttribute("SearchArrylist",SearchArrylist);		     
		 	indexPage = "/Journal_ArticleSearch/jnlAltsimpleSearch.jsp";
		 	dispatch(request, response, indexPage);

		}			  
			  
		if(request.getParameter("ncode")!=null){
			String accno=request.getParameter("ncode");
		    log4jLogger.info("=========== Inside Get Journal Article =========="+accno);
			SQL_Query="and ";
			SQL_Query=SQL_Query+"Atl_No"+" = '"+request.getParameter("ncode")+"'";
			  
			  SearchArrylist=ss.getJournalArticleSearch(SQL_Query);
			 
			request.setAttribute("SearchArrylist",SearchArrylist);
			indexPage = "/Journal_ArticleSearch/fullView.jsp";
			dispatch(request, response, indexPage);
	   }
		
		 if(request.getParameter("flag")!=null)  {
			   if(request.getParameter("flag").equals("load")){
					log4jLogger.info("start===========Branch List load=====");
					List BranchArrylist = new ArrayList();
					BranchArrylist=ss.getLoadBranchList();
					request.setAttribute("BranchList", BranchArrylist);
					indexPage = "/Journal_ArticleSearch/index.jsp";
					dispatch(request, response, indexPage);
				}
			  }
			 
	   
		} catch (Exception sss) {
			sss.printStackTrace();
			
		}
	}
	
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}


