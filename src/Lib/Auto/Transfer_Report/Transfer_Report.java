package Lib.Auto.Transfer_Report;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
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
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.reports.ReportQueryUtill;
import Lib.Auto.Branch.BranchBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;
public class Transfer_Report extends HttpServlet implements Serializable,ReportQueryUtill {
	private static Logger log4jLogger = Logger.getLogger(Transfer_Report.class);
	private static final long serialVersionUID = 1L;
		java.sql.Connection con = null;
		java.sql.PreparedStatement Prest = null;
		String indexPage = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}
	public void performTask(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		try {
				
				HttpSession session = request.getSession(true);
				String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
				log4jLogger.info("Inside Transfer  Reports");
				
				con=SessionHibernateUtil.getSession().connection();
				
				CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
				int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
				
				LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
				BranchBean branch = ls.getBranchInfo(rk);	
				String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());
				
				
			if (request.getParameter("flag").equals("load")) {
				log4jLogger.info("start===========load=====");
				java.util.List BindingArrylist = new ArrayList();
				BindingArrylist = ss.getTransferedDeptName(rk);

				request.setAttribute("binding", BindingArrylist);
				indexPage = "/Transfer_Report/index.jsp";
				dispatch(request, response, indexPage);
			}
				
				
				
				if(request.getParameter("flag").equals("print")){
					
					String flag="",strsql="",strsql1="",strsql2="",strsql3="",frmdt="",todt="";
					
					log4jLogger.info("start===========Printing report=====");
						
					strsql="";
					strsql1="";
					strsql2="";
					strsql3="";
					
					frmdt=Security.TextDate_member(request.getParameter("fromdate"));
					todt=Security.TextDate_member(request.getParameter("todate"));
					
				if (!request.getParameter("dept").equals("ALL")) {
					strsql1 = " and Transfered_Dept_Code='" + request.getParameter("dept")
							+ "'";
				}
				if (!request.getParameter("doc").equals("ALL")) {

					strsql2 = "and doc_type='" + request.getParameter("doc")
							+ "'";

				}
				if (request.getParameter("status").equals("TRANSFERED")) {

					strsql3 = "and order_date between '" + frmdt + "' and '"
							+ todt + "' and recovery IN('',NULL)";

				} else {

					strsql3 = "and recovery_date between '" + frmdt + "' and '"
							+ todt + "' and recovery='YES' ";
				}
				
				
				strsql2 = " and branch_code='" + rk+ "' ";
						 
					 con=SessionHibernateUtil.getSession().connection();
		    	      	
		    	      	Map parameters = new HashMap(); 
		    	      	parameters.put("logo",base64Logo);
		    	      	
		    			String namedQuery=ReportQueryUtill.Transfer_Report_Query;
		    			StringBuffer sb = new StringBuffer();
		    			String filterQuery=null;
		    			sb.append(namedQuery);
						sb.append(" " + strsql1+strsql2+strsql3);
					   			        
						 if(rk==1)
						 {
							 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
							 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
						 }
						 
						 else if(rk==2)
						 {
							 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME2);
							 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD2);
						 }
							 
							 else if(rk==3)
							 {
								 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME3);
								 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD3);
							 }
								 
								 else if(rk==4)
								 {
									 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME4);
									 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD4);
								 }
									 
									 else if(rk==5)
									 {
										 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME5);
										 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD5);
									 }
										 
									 else{
										 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
										 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
										 
									 }


					
					if (request.getParameter("status").equals("TRANSFERED")) {
						parameters.put("ReportTitle",ReportQueryUtill.Transfer_Reports_Title);
					}else{
						parameters.put("ReportTitle",ReportQueryUtill.Re_Transfer_Reports_Title);
					}
						
				
					
					parameters.put("From",frmdt);
					parameters.put("To",todt);
					parameters.put("All_Query",sb.toString());				
					
					
					log4jLogger.info("SQL QUERY: " + sb);			
				
					InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Transfered_Report.jasper");			
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
					
					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);	
					
					JRAbstractExporter exporterPDF = new JRPdfExporter();	        
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);	        
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					
					response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Transfer_Reports_Title+".pdf");	
					
					response.setContentType("application/pdf");	        
					exporterPDF.exportReport(); 
					
					
					}
				
				
				if(request.getParameter("flag").equals("statistics")){
					
                   String flag="",strsql="",strsql1="",strsql2="",strsql3="",frmdt="",todt="";
					
					log4jLogger.info("start===========Printing report=====");
						
					strsql="";
					strsql1="";
					strsql2="";
					strsql3="";
					
					frmdt=Security.TextDate_member(request.getParameter("fromdate"));
					todt=Security.TextDate_member(request.getParameter("todate"));
					
				if (!request.getParameter("dept").equals("ALL")) {
					strsql1 = " and Transfered_Dept_Code='" + request.getParameter("dept")
							+ "'";
				}
				if (!request.getParameter("doc").equals("ALL")) {

					strsql2 = "and doc_type='" + request.getParameter("doc")
							+ "'";

				}
				
				 con=SessionHibernateUtil.getSession().connection();
	    	      	
	    	      	Map parameters = new HashMap();       	      	
	    			String namedQuery=ReportQueryUtill.Transfer_Report_Statistics;
	    			StringBuffer sb = new StringBuffer();
	    			String filterQuery=null;
	    			sb.append(namedQuery);
					sb.append(" " + strsql1+strsql2+strsql3);
				   			        
					if(rk==1)
					 {
						 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
						 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
					 }
					 
					 else if(rk==2)
					 {
						 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME2);
						 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD2);
					 }
						 
						 else if(rk==3)
						 {
							 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME3);
							 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD3);
						 }
							 
							 else if(rk==4)
							 {
								 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME4);
								 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD4);
							 }
								 
								 else if(rk==5)
								 {
									 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME5);
									 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD5);
								 }
									 
								 else{
									 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
									 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
									 
								 }



				
				parameters.put("From",frmdt);
				parameters.put("To",todt);
				parameters.put("All_Query",sb.toString());
				
				}
				
				

				
				
				
			} catch (Exception sss) {
				//throw new ServletException(sss);
				
				sss.printStackTrace();
				
			} finally {
				
				try{					
					con.close();
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