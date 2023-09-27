
package Lib.Auto.Author;

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
import Common.businessutil.calaloging.CalalogingService;

import com.google.gson.Gson;


public class Author extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Author.class);

	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";
	
	String Auth_Name = "", SameCode = "";
	String sql="";
	String nam="";
    String filename="";
	int Author_Interface_code=0;
	int Author_Mas_code=0;
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

		try {	
			
			HttpSession session = request.getSession(true);			
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			PrintWriter out = response.getWriter();
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID"))))); 
			
			
			try{
				//if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	           // {
				String term = request.getParameter("name");
				
				System.out.println("Data from ajax call " + term);
							    
				   ArrayList<AuthorBean> list = ss.getAuthorAutoSearch(term,branchID);
			       for(AuthorBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	         //}    
		}catch(Exception e){
			e.printStackTrace();
		}  
			
			AuthorBean ob = null;
			flag = request.getParameter("flag");
			if (flag.equals("new")) {
				log4jLogger.info("start===========new====="+session.getAttribute("AccessNo"));
				ob = new AuthorBean();
				ob=ss.getAuthorMax();
				
				request.setAttribute("beanobject", ob);			
				indexPage = "/Author/index.jsp?check=newAuthor";
				dispatch(request, response, indexPage);
				

			}

			if (flag.equals("search")) {
				log4jLogger.info("start===========search=====");
				ob = new AuthorBean();
				ob=ss.getAuthorSearch(Integer.parseInt(request.getParameter("code")));
				if(ob!=null){
					request.setAttribute("beanobject", ob);
					
					indexPage = "/Author/index.jsp?check=searchAuthor";	
				}else{
					indexPage = "/Author/index.jsp?check=FailAuthor";
				}
				dispatch(request, response, indexPage);

			}

			if (flag.equals("update")) {
				log4jLogger.info("start===========update=====");
				ob = new AuthorBean();				
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				ob.setEmail(request.getParameter("email"));
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setBranchID(branchID);
				int count=ss.getAuthorUpdate(ob);
				request.setAttribute("beanobject", ob);				
				indexPage = "/Author/index.jsp?check=UpdateAuthor";				
				dispatch(request, response, indexPage);				
			}

			if (flag.equals("delete")) {		
				log4jLogger.info("start===========delete=====");
				ob = new AuthorBean();				
				ob=ss.getAuthorSearch(Integer.parseInt(request.getParameter("code")));				
				if(ob!=null){				
					request.setAttribute("beanobject", ob);
					indexPage = "/Author/index.jsp?check=deleteCheck";
				}else{
					request.setAttribute("beanobject", ob);
					indexPage = "/Author/index.jsp?check=RecordNot";	
				}
					dispatch(request, response, indexPage);
			}
			if (flag.equals("Confirmdete")) {
				log4jLogger.info("start===========Confirmdete=====");
				
				
				
				int Author_Interface_code=ss.getAuthorInterface(Integer.parseInt(request.getParameter("code")));			

				int Author_Mas_code=ss.getAuthorMas(Integer.parseInt(request.getParameter("code")));

				if (Author_Interface_code == Author_Mas_code) {
					indexPage = "/Author/index.jsp?check=ReferredAuthor";
				} else {
					
					int rk=Integer.parseInt(request.getParameter("code"));

					if(rk==1)
					{
						indexPage = "/Author/index.jsp?check=DefaultAuthor";
					}
					else
					{
					int count=ss.getAuthorDelete(Integer.parseInt(request.getParameter("code")));

					indexPage = "/Author/index.jsp?check=DeleteAuthor";
					}
				}
				dispatch(request, response, indexPage);

			}

		if (flag.equals("save"))
		{
			log4jLogger.info("start===========save=====");
				ob = new AuthorBean();
				ob.setName(request.getParameter("name"));
				ob.setDesc(request.getParameter("desc"));
				ob.setEmail(request.getParameter("email"));
				ob.setBranchID(branchID);
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				int Author_code=ss.getAuthorName(ob);
				int Author_Interface_code=ss.getAuthorNameAuthorInterface((Integer.parseInt(request.getParameter("code"))));
				int Author_code_check=ss.getAuthorCode((Integer.parseInt(request.getParameter("code"))));
			if(Author_code>0)
				{
 
						  ob.setName(request.getParameter("name"));
						  ob.setDesc(request.getParameter("desc"));
						  ob.setEmail(request.getParameter("email"));
						  ob.setCode(Author_code);
						  ob.setBranchID(branchID);
						  request.setAttribute("beanobject", ob);
				       indexPage = "/Author/index.jsp?check=CodeCompareAuthor";
				}

				else if(Author_Interface_code>0)
				{	
					  
					  ob = new AuthorBean();
				      ob.setName(request.getParameter("name"));
					  ob.setDesc(request.getParameter("desc"));
					  ob.setEmail(request.getParameter("email"));
					  ob.setCode(Integer.parseInt(request.getParameter("code")));
					  ob.setBranchID(branchID);
					  request.setAttribute("beanobject", ob);
				indexPage = "/Author/index.jsp?check=UpdateCheck";
				
				}
				
				else if(Author_code_check>0)
				{	
					
					  ob = new AuthorBean();
				      ob.setName(request.getParameter("name"));
					  ob.setDesc(request.getParameter("desc"));
					  ob.setEmail(request.getParameter("email"));
					  ob.setCode(Integer.parseInt(request.getParameter("code")));
					  ob.setBranchID(branchID);
					  request.setAttribute("beanobject", ob);
				indexPage = "/Author/index.jsp?check=Updatename";
				
				}

				else 
				{					ob = new AuthorBean();
									ob.setName(request.getParameter("name").trim());
									ob.setDesc(request.getParameter("desc"));
									ob.setEmail(request.getParameter("email"));
									ob.setCode(Integer.parseInt(request.getParameter("code")));	
									 ob.setBranchID(branchID);
									int count=ss.getAuthorSave(ob);
									indexPage = "/Author/index.jsp?check=SaveAuthor";									
				}							
			
				dispatch(request, response, indexPage);
						
				}
				
		
		
		

			if (flag.equals("Author")) {
				log4jLogger.info("start===========Author=====");
						List AuthorArrylist = new ArrayList();
				ob = new AuthorBean();
				AuthorBean ab = null;
				ab=new AuthorBean();
				
				if(request.getParameter("name").trim()!=null){
					ob.setName(request.getParameter("name").trim());
					ob.setBranchID(branchID);
					AuthorArrylist=ss.getAuthorSearchName(ob);
					request.setAttribute("serarch", AuthorArrylist);
			        indexPage = "/Author/search.jsp?check=ok&nam="+nam+"";
			    	dispatch(request, response, indexPage);
				}
				

			}
		
		
	
		}
		catch (Exception sss) {
			throw new ServletException(sss);
			//sss.printStackTrace();
		} finally {
			Author_Mas_code=0;
			Author_Interface_code=0;
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
