package Lib.Auto.JNL_Invoice_Report;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
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
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.serialcontrol.SerialcontrolService;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.JNL_Payment.JnlPaymentBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;


public class JNLInvoiceReport extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(JNLInvoiceReport.class);
	
	java.sql.Connection con = null;
	String indexPage = null;
	String flag = null;


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
			
			SerialcontrolService ss1 = BusinessServiceFactory.INSTANCE.getSerialcontrolService();
						
			JnlPaymentBean ob=new JnlPaymentBean();			
			
			flag = request.getParameter("flag");	
			
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
			
			LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(branchID);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());

			if(flag.equals("INVOICE"))
			{
				
				log4jLogger.info("==========Invoice No Search in JNLInvoiceReport ========"+flag);
				List PmtArrylist = new ArrayList();
				
				ob=new JnlPaymentBean();
				ob.setInvNo(request.getParameter("name"));
				ob.setAdd2("");
				
				PmtArrylist=ss1.getPaymentInvSearch(ob);				
																						
				request.setAttribute("search", PmtArrylist);
				indexPage ="/JNL_Invoice_Report/search_nmvc.jsp?check=ok&flag="+flag+"";
				dispatch(request, response, indexPage);
			}
					
			

			if(flag.equals("Print"))
			{				
				log4jLogger.info("=== Inside Print JNLInvoiceReport ===");
				
				String invno="",strsql="",txtstatus="";
				
				invno=request.getParameter("invno");
				txtstatus=request.getParameter("status");
				
				if (!invno.equals(""))
	        	{
					strsql="and invoice_no='"+invno+"'";
	        	}
	        	if (!txtstatus.equals("") && !txtstatus.equals("ALL"))
	        	{
	        		strsql = strsql + " and payment_flag = '" +txtstatus+"'";
	        	}
	            				
				
				con=SessionHibernateUtil.getSession().connection();
	     		
    	      	Map parameters = new HashMap(); 
    	      	parameters.put("logo",base64Logo);
    			String namedQuery=ReportQueryUtill.JNL_Invoice_Report;
    			StringBuffer sb = new StringBuffer();
    			String filterQuery=null;
    			
    			sb.append(namedQuery);
				sb.append(" " + strsql);				   			        			
    			
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

				parameters.put("ReportTitle",ReportQueryUtill.JNL_Invoice_Title);   				
				parameters.put("All_Query",sb.toString());				
				log4jLogger.info("SQL QUERY: " + sb);		
				
				InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/JNL_Invoice_Report.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		  
				JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);
				
				JRAbstractExporter exporterPDF = new JRPdfExporter();
		        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.JNL_Invoice_Title+".pdf");
		        response.setContentType("application/pdf");
		        exporterPDF.exportReport();
		       
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		finally {
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

	/***************************************************************************
	 * prepare the sql statement for execution
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
