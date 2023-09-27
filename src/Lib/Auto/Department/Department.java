package Lib.Auto.Department;


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
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;

import com.google.gson.Gson;

public class Department extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Department.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag;
	String protocol="";
	String name="",code="",Dept_Name="",SameCode="";
	int Department_Mas_code=0;int Deptcode=0;
	int updateFlag;
	String nam="";
	DepartmentBean ob=new DepartmentBean();

	String indexPage = null;


	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)throws ServletException {

		
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
			AdminService as = BusinessServiceFactory.INSTANCE.getAdminService();
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));    // For Titan
			
			response.setContentType("application/json");
			
			 try{
					String term = request.getParameter("name");
					//System.out.println("Data from ajax call " + term);
							
				    //log4jLogger.info("::::::::::$$$$$$$$$$$$$$$$$$$::::::::::::::"+request.getParameter("flag"));
						
						
					//ArrayList<String> list = ss.getFrameWork(term);
				    ArrayList<DepartmentBean> list = ss.getDeptAutoSearch(term, branchID);
				    for(DepartmentBean user: list){
						//System.out.println(user.getName());
					} 

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
					
			}catch(Exception e){
				e.printStackTrace();
			} 
			
			DepartmentBean ob = null;
			
			flag = request.getParameter("flag");
			
			
			
			if(branchID == 0)  // Super Admin Rights only
			{
			 List BranchArrylist = new ArrayList();
			 BranchArrylist=as.getLoadBranchList(branchID);
			 request.setAttribute("UserBranchList", BranchArrylist);
			}
			
			
			if(flag.equals("new")){
				log4jLogger.info("start===========new=====");
				ob = new DepartmentBean();
				ob=ss.getDeptMax();
				
				request.setAttribute("DepartmentBean", ob);	
				indexPage = "/Department/index.jsp?check=newDept";
				dispatch(request, response, indexPage);
					}
			
	
						 
		if(flag.equals("search")){
			log4jLogger.info("start===========search=====");
			ob = new DepartmentBean();
			
			ob=ss.getDeptSearch(Integer.parseInt(request.getParameter("code")));
			if(ob!=null){
			request.setAttribute("DepartmentBean", ob);	
			indexPage = "/Department/index.jsp?check=searchDept";
			}
			else
			{
				indexPage = "/Department/index.jsp?check=FailDept";
			}
			
			dispatch(request, response, indexPage);
		}				
					
		
		if(flag.equals("update")){
			log4jLogger.info("start===========update=====");
			ob = new DepartmentBean();
			ob.setName(request.getParameter("name").trim());
			ob.setDesc(request.getParameter("desc"));
			ob.setBranchCode(Integer.parseInt(request.getParameter("txtBranch")));
			ob.setCode(Integer.parseInt(request.getParameter("code")));
			int count=ss.getDeptUpdate(ob);

			request.setAttribute("DepartmentBean", ob);	
			indexPage = "/Department/index.jsp?check=UpdateAuthor";
			dispatch(request, response, indexPage);
		}
		
		if(flag.equals("delete")){
			log4jLogger.info("start===========delete====="+request.getParameter("code"));
			ob = new DepartmentBean();
			
			ob=ss.getDeptSearch(Integer.parseInt(request.getParameter("code")));
			if(ob!=null){
			request.setAttribute("DepartmentBean", ob);	
	
				indexPage = "/Department/index.jsp?check=deleteCheck";
					} 
		 else 
					{
			     request.setAttribute("DepartmentBean", ob);	
				indexPage = "/Department/index.jsp?check=RecordNot";
					}
			dispatch(request, response, indexPage);
	}
		if(flag.equals("Confirmdete")){
			log4jLogger.info("start===========Confirmdete=====");
			int Dept_Interface_code=ss.getDeptInterface(Integer.parseInt(request.getParameter("code")));			

			int Dept_Mas_code=ss.getDeptMas(Integer.parseInt(request.getParameter("code")), branchID);

			if (Dept_Interface_code == Dept_Mas_code) {
				indexPage = "/Department/index.jsp?check=ReferredDept";
		} 
		else {
			
			int rk=Integer.parseInt(request.getParameter("code"));
			
			if(rk==1)
			{
				indexPage = "/Department/index.jsp?check=DefaultDepartment";
			}
			else
			{			
			       int count=ss.getDeptDelete(Integer.parseInt(request.getParameter("code")));
					indexPage = "/Department/index.jsp?check=DeleteDept";
			}
		}
				dispatch(request, response, indexPage);
			
		}

		if(flag.equals("save")){
			log4jLogger.info("start===========save=====");
			ob = new DepartmentBean();
			ob.setCode(Integer.parseInt(request.getParameter("code")));
			ob.setName(request.getParameter("name").trim());
			ob.setDesc(request.getParameter("desc"));
			ob.setBranchCode(Integer.parseInt(request.getParameter("txtBranch")));
			
			int Dept_code=ss.getDeptName(ob);
			int Dept_Interface_code=ss.getDeptInterface(Integer.parseInt(request.getParameter("code")));	
			int Dept_Mas_code=ss.getDeptMas(Integer.parseInt(request.getParameter("code")), branchID);

			if (Dept_code >0) {
				ob.setCode(Dept_code);
				ob.setName(request.getParameter("name").trim());
				ob.setDesc(request.getParameter("desc"));
				ob.setBranchCode(Integer.parseInt(request.getParameter("txtBranch")));
				
				request.setAttribute("DepartmentBean", ob);
				indexPage = "/Department/index.jsp?check=CodeCompareDept";

			} 
			else if (Dept_Interface_code>0)
			{
				ob.setCode(Integer.parseInt(request.getParameter("code")));
				ob.setName(request.getParameter("name"));
				ob.setDesc(request.getParameter("desc"));
				ob.setBranchCode(Integer.parseInt(request.getParameter("txtBranch")));
				
				request.setAttribute("DepartmentBean", ob);
				indexPage = "/Department/index.jsp?check=UpdateCheck";
			}

				else if (Dept_Mas_code >0)
				{
					ob.setCode(Integer.parseInt(request.getParameter("code")));
					ob.setName(request.getParameter("name"));
					ob.setDesc(request.getParameter("desc"));
					ob.setBranchCode(Integer.parseInt(request.getParameter("txtBranch")));
					
					request.setAttribute("DepartmentBean", ob);
					indexPage = "/Department/index.jsp?check=Updatename";		

				}				
					else
					 {	
						ob = new DepartmentBean();
						ob=ss.getDeptMax();		
						//ob.setCode(Integer.parseInt(request.getParameter("code")));
						ob.setName(request.getParameter("name").trim());
						ob.setDesc(request.getParameter("desc"));
						ob.setBranchCode(Integer.parseInt(request.getParameter("txtBranch")));
						
						int count=ss.getDeptSave(ob);
				indexPage = "/Department/index.jsp?check=SaveDept";
				
			}
			dispatch(request, response, indexPage);
		
		}
		
		if (flag.equals("Department")) {
			log4jLogger.info("start===========Department=====");
			List DepartmentArrylist = null;
			
			DepartmentArrylist = new ArrayList();
			ob = new DepartmentBean();
			ob.setName(request.getParameter("name"));
			ob.setBranchCode(branchID);
			
			DepartmentArrylist=ss.getDeptSearchName(ob);	

			request.setAttribute("serarch", DepartmentArrylist);
			
	     indexPage = "/Department/search.jsp?check=ok&nam="+nam+"";
		dispatch(request, response, indexPage);

		}
	
		}
		catch (Exception sss) {
			throw new ServletException(sss);
		}
					   
	}
	
		   
//	}
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}
	
	
	
	
}
