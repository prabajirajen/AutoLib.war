/*
 * Created on Apr 27, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package Lib.Auto.Simples;

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
import Lib.Auto.Advanced.Adsearchbean;

import com.google.gson.Gson;

public class Simples extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Simples.class);
	String indexPage = null;
	/**
	 * 
	 */
	List SearchArrylist;
	List AdsearchArrylist;
	private static final long serialVersionUID = 1L;
		
	String flag = "",title1="";
	String SQL_Query = "", SQL_Query_Cnt = "",SQL_Query_Con="",SQL_Query_std = "",SQL_Query_cur="",SQL_Query_alpha="";
	
	public static final String SQLCNT =	"select count(access_no) as cnt from full_search where 2>1";	
	public static final String SQL_Query_view =	"select  * from full_search where 2>1";
	public static final String CD_VIEW ="select * from full_search where  remarks like '%+CD%' and access_no=?";
	
	Adsearchbean bean=new Adsearchbean();
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
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));    // For Titan
			
			 response.setContentType("application/json");
				
				try{
					String term = request.getParameter("Title");
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		            {
					System.out.println("Data from ajax call " + term);
								    
					   ArrayList<Searchbean> list = ss.getTitleSearch(term,branchID);
				       for(Searchbean user: list){
						//System.out.println(user.getTitle());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
												
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
					       	
					
		            }	
			}catch(Exception e){
				//e.printStackTrace();
			}  	
				
		//session.setAttribute("Title","Title");		


			try{
					String term = request.getParameter("Author");
					
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		             {
					//System.out.println("Data from ajax call " + term);
								    
					   ArrayList<Searchbean> list = ss.getAuthorSearch(term,branchID);
				       for(Searchbean user: list){
						//System.out.println(user.getAuthor());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		             }	
		           }
						catch(Exception e){
				//e.printStackTrace();
			}    		 

			try{
				String term = request.getParameter("subject");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<Searchbean> list = ss.getSubjectSearch(term,branchID);
			       for(Searchbean user: list){
					//System.out.println(user.getSubject());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 

			
		    String filterQuery = "";
			
			if(branchID==0){
				filterQuery = "";				
			}else  {
				filterQuery = " and branch_code="+branchID+" ";	 
			}
			
			  if (request.getParameter("hid") != null&& request.getParameter("hid").equals("search")) {
				  SQL_Query = "";
				  //SQL_Query = SQL_Query + filterQuery;
				  
				if (!Security.getParam(request, "Title").equals("")) {					
					
					SQL_Query =	SQL_Query+ " and title like '%"	+ Security.getParam(request, "Title")+ "%'";
				}
				if (!Security.getParam(request, "Call_no").equals("")) {		
					
					SQL_Query =SQL_Query+ " and Call_no like '"	+ Security.getParam(request, "Call_no")	+ "%'";
				}
		    	
				if (!Security.getParam(request, "Publisher").equals("")) {
					
					SQL_Query =SQL_Query+ " and sp_name like '%"+ Security.getParam(request, "Publisher")+ "%'";
				}
				
				if (!Security.getParam(request, "Keywords").equals("")) {
					
					SQL_Query =SQL_Query+ " and keywords like '%"+ Security.getParam(request, "Keywords")+ "%'";
				}
				
				if (!Security.getParam(request, "Author").equals("")) {
					
					SQL_Query =SQL_Query+ " and author_name like '%"+ Security.getParam(request, "Author")+ "%'";
				}
				
				if (!Security.getParam(request, "subject").equals("")) {
					
					SQL_Query =SQL_Query+ " and sub_name like '%"+ Security.getParam(request, "subject")+ "%'";
				}
				
				if (!Security.getParam(request, "Year").equals("")) {
					
					SQL_Query =SQL_Query+ " and year_pub =  '"+ Security.getParam(request, "Year")+ "'";
				}
							
				if (!Security.getParam(request, "acc_no").equals("")) {
					
					SQL_Query =SQL_Query+ " and Access_No ='"+ Security.getParam(request, "acc_no")+ "'";
				}
				
				
				if(!Security.getParam(request, "isbn").equals("")) {
					
					SQL_Query =SQL_Query+ " and isbn like  '%"+ Security.getParam(request, "isbn")+ "%'";
				}
				
				if(!Security.getParam(request, "location").equals("")) {
					
					SQL_Query =SQL_Query+ " and location like  '%"+ Security.getParam(request, "location")+ "%'";
				}
				
				if(!Security.getParam(request, "avail").equals("ALL")){
					
					SQL_Query =SQL_Query+ " and Availability like  '%"+ Security.getParam(request, "avail")+ "%'";
				}
				
				if(!Security.getParam(request, "edition").equals("")){
					SQL_Query =SQL_Query+ " and edition ='"+ Security.getParam(request, "edition")+ "'";
				}
				
                if (!Security.getParam(request, "txtBranch").equals("NO")) 
                {
			
					SQL_Query =SQL_Query+ " and branch_Code =  '"+ Security.getParam(request, "txtBranch")+ "'";
				}
//                if(Security.getParam(request, "txtBranch").equals("2"))
//				{
//					SQL_Query =SQL_Query+ " AND availability<>'LOST' AND availability<>'DAMAGED' AND availability<>'WITHDRAWN' AND availability<>'MISSING' ";
//				}
								
				bean.setsqlquery(SQL_Query);
				
			 SearchArrylist=ss.getSimpleSearch(SQL_Query);
				
			
			 session.setAttribute("SearchArrylist",SearchArrylist);
		     
		 	indexPage = "/Simples/simpleSearch.jsp";
		 	dispatch(request, response, indexPage);
		 	
					
			  }
			  
			  
			  if(request.getParameter("doc")!=null){
			    	log4jLogger.info("start===========adsearchdoc=====");
			    	
			    	String query="";   	
			  
			    	query=bean.getsqlquery();
			    				    	
			    	SQL_Query=query+"and document=";
			    	
			    	SQL_Query=SQL_Query+"'"+request.getParameter("doc")+ "'";
	    	
			    	SearchArrylist=ss.getSimpleSearch(SQL_Query);					
					
					session.setAttribute("SearchArrylist",SearchArrylist);
				     
				 	indexPage = "/Simples/simpleSearch.jsp";
				 	dispatch(request, response, indexPage);
			    	
			    }
			  
			  if(request.getParameter("accno")!=null){
			    log4jLogger.info("start===========adsearchaccno====="+request.getParameter("accno"));
				   String accno=request.getParameter("accno");
				   
				   SQL_Query="and ";
				   SQL_Query=SQL_Query+"access_no"+" = '"+request.getParameter("accno")+"' AND branch_name='"+request.getParameter("branch")+"'";
				   
				   				   
				   AdsearchArrylist=ss.getFullViewSearch(SQL_Query);
				   
				   
				   request.setAttribute("SearchArrylist",AdsearchArrylist);
				   indexPage = "/Simples/fullView.jsp";
				   dispatch(request, response, indexPage);			   
			   }
			  
			  if(request.getParameter("flag")!=null)  {
			   if(request.getParameter("flag").equals("load")){
					log4jLogger.info("start===========Branch List load=====");
					List BranchArrylist = new ArrayList();
					BranchArrylist=ss.getLoadBranchList();
					request.setAttribute("BranchList", BranchArrylist);
					indexPage = "/Simples/index.jsp";
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


