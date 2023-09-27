package Lib.Auto.Book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.io.File;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Member.MemberBean;



public class BookSearch extends HttpServlet {
	String protocol="",nm="",SQLString="",f1="",f2="",f3="";
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	private static Logger log4jLogger = Logger.getLogger(BookSearch.class);
	
	
	String indexPage = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
			HttpServletRequest request,
			HttpServletResponse response)  throws ServletException {


			try {
				HttpSession session = request.getSession(true);
				String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
				response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
	            
	            int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));    // For Titan
	            
	            nm=request.getParameter("name");
			    out.print("Name"+nm);
			    String value=request.getParameter("sflag");
			    String kar=request.getParameter("iflag");
			    
			    String authsave=request.getParameter("flag");
			    String doc_type=request.getParameter("docType");
			    out.print(value);
			    
			    
			    log4jLogger.info("=======new====flag====="+authsave);
			    
			  
			    
			    
			    
			    if(value.equals("accessNo"))
				  
			    {
			    	 log4jLogger.info("accessNo===========flag====="+nm);
                 ArrayList list=null;
                                                  
                 List AuthorArrylist = new ArrayList();
                 //int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
                 
                 AuthorArrylist=ss.getBookSearchTitle(nm,branchID,doc_type);
                 request.setAttribute("search", AuthorArrylist);
 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"&docType="+doc_type;
 				dispatch(request, response, indexPage);	
 			  
		 }
			    
			   		   
			    if(value.equals("Aut"))
			    	
			    		{
			    	log4jLogger.info("Author===========flag====="+nm);
			    			ArrayList list=null;
                            
			                 List AuthorArrylist = new ArrayList();
			 		    	 		    	 		    	
			                 AuthorArrylist=ss.getBookSearchAuthor(nm,branchID);
			                 request.setAttribute("search", AuthorArrylist);
			 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
			 				dispatch(request, response, indexPage);	
			    		}
	      
			    if(value.equals("Pub")){
			    	log4jLogger.info("Publisher===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchPub(nm,branchID);
	                request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    	
			    }
			    
			    if(value.equals("Sub")){
			    	log4jLogger.info("Subject===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchSubject(nm,branchID);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    
			    
			    if(value.equals("callNo")){
			    	log4jLogger.info("Call_No===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchCallNo(nm,branchID);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    
			    
			    
			    
			    
			    if(value.equals("Branch")){
			    	log4jLogger.info("Branch===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	                 
	                 //int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchBranch(nm,branchID);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    
			    if(value.equals("Dept")){
			    	log4jLogger.info("Dept===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchDept(nm,branchID);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    
			    if(value.equals("Bud")){
			    	log4jLogger.info("Budget===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchBudget(nm,branchID);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    if(value.equals("Key")){
			    	log4jLogger.info("Keywords===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchKey(nm);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    if(value.equals("Sup")){
			    	log4jLogger.info("Supplier===========flag====="+nm);
			    	ArrayList list=null;
                    
	                 List AuthorArrylist = new ArrayList();
	 		    	 		    	 		    	
	                 AuthorArrylist=ss.getBookSearchSupplier(nm,branchID);
	                 request.setAttribute("search", AuthorArrylist);
	 				indexPage ="/Book/search_nmvc.jsp?check=ok&flag="+value+"&name="+nm+"";
	 				dispatch(request, response, indexPage);	
			    }
			    
			}  catch (Exception e) {

		}
	catch (Throwable theException) {

	}
	finally
	{
		try{
			

		}catch(Exception e){
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
