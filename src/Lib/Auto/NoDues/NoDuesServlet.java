package Lib.Auto.NoDues;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.LoginUserService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.reports.ReportQueryUtill;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Member.MemberBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;

public class NoDuesServlet extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	private static Logger log4jLogger = Logger.getLogger(NoDuesServlet.class);
	String indexPage = null;
	String flag="";
	String flag1="";
	
	
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
       
            flag=request.getParameter("Flag_Search");
            flag1=request.getParameter("flag");
            
            CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
            int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
            
            LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(branchID);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());

            
            if (flag.equals("search")) {			
            	log4jLogger.info("No Dues--------------search---------------"+request.getParameter("txtUserId"));
				MemberBean ob = ss.getMemberSearch(request.getParameter("txtUserId"),branchID);
				if (ob.getCode()!=null) {									
					request.setAttribute("BeanObject", ob);				
					indexPage = "/NoDues/index.jsp?check=memberYes";		
					
				} else {					
				    indexPage ="/NoDues/index.jsp?check=FailMember";				
				}
				dispatch(request, response, indexPage);
			}
            

        //ReportService ss1 = BusinessServiceFactory.INSTANCE.getReportService();		
      	//con=ss1.getDBConnect();
            
        con=SessionHibernateUtil.getSession().connection();
            
      	String filterQuery=request.getParameter("txtUserId");
      	
        if (flag1.equals("IssueCheck")) {
        
          MemberBean ob = ss.getMemberSearch(request.getParameter("txtUserId"),branchID);          
          if (ob.getCode()!=null) {
        		
           int User_check=0;
           User_check=ss.getIssueMasCheck(filterQuery);
           
           if(User_check>0)
           {      
        	   request.setAttribute("BeanObject", ob);		
        	   indexPage = "/NoDues/index.jsp?check=IssueYes";  	   
        	   
           }else{   
        	   request.setAttribute("BeanObject", ob);		
        	   indexPage = "/NoDues/index.jsp?check=IssueNo";  
           }
           
         }else {					
			    indexPage ="/NoDues/index.jsp?check=FailMember";				
		 }
          
           dispatch(request, response, indexPage);
        }
        
        if (flag1.equals("Print"))
        {  
                	
        MemberBean ob = ss.getMemberSearch(request.getParameter("txtUserId"),branchID);
        
        if (ob.getCode()!=null) {       	
        	int User_check=0;        
            User_check=ss.getIssueMasCheck(filterQuery);
        
        if(User_check>0)
        {   	
        	 request.setAttribute("BeanObject", ob);		
          	 indexPage = "/NoDues/index.jsp?check=IssueYes";  	   
          	 dispatch(request, response, indexPage);
          	 
        }else{          	  
        	
        	Map parameters = new HashMap(); 
        	parameters.put("logo",base64Logo);
			
        	if(branchID==1)
			 {
				 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
				 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
			 }
			 
			 else if(branchID==2)
			 {
				 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME2);
				 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD2);
			 }
				 
				 else if(branchID==3)
				 {
					 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME3);
					 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD3);
				 }
					 
					 else if(branchID==4)
					 {
						 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME4);
						 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD4);
					 }
						 
						 else if(branchID==5)
						 {
							 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME5);
							 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD5);
						 }
							 
						 else{
							 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
							 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
							 
						 }

			parameters.put("ReportTitle",ReportQueryUtill.NoDues_Title);   				
			parameters.put("Query_Param",filterQuery);				
						
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/NoDues_Report.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
					
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
						
			JRAbstractExporter exporterPDF = new JRPdfExporter();
	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.NoDuesReport_Title+".pdf");
	        response.setContentType("application/pdf");
	        exporterPDF.exportReport(); 
          	  
            }
            
            }else {					
			    indexPage ="/NoDues/index.jsp?check=FailMember";
			    dispatch(request, response, indexPage);
   		    }        
           
        }    
        
        
			 
	}  catch (Exception e) {
		e.printStackTrace();
	}
catch (Throwable theException) {
	
	}
finally{
	if(con!=null)
	{
		try {					
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

