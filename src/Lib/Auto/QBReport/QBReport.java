package Lib.Auto.QBReport;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
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
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Lib.Auto.Branch.BranchBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;
//import antlr.collections.List;
public class QBReport extends HttpServlet implements Serializable,ReportQueryUtill {
	private static Logger log4jLogger = Logger.getLogger(QBReport.class);
	private static final long serialVersionUID = 1L;
		java.sql.Connection con = null;
		java.sql.PreparedStatement Prest = null;
		String indexPage = null;
		String print="",reportType="",flag="",index="",status="",frmdt="",todt="",doc="",printType="";
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
				
				int rk = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
				LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
				BranchBean branch = ls.getBranchInfo(rk);	
				String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());
				
				

				log4jLogger.info(":::::::::::UserBranchID:::::::::"+rk);
				
				log4jLogger.info("Inside QB  Reports");
				
				con=SessionHibernateUtil.getSession().connection();
				
				CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
				AdminService ss1 = BusinessServiceFactory.INSTANCE.getAdminService();								
				ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
				ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
				
				response.setContentType("application/json");
				
				flag = request.getParameter("flag");
				printType = request.getParameter("printType");
				
				


			 if(request.getParameter("flag")!=null) {
			if (request.getParameter("flag").equals("load")) {
				
				List departmentList = ss1.getDepartmentList(rk);				
				request.setAttribute("departmentSearchList", departmentList);
				
				List courseList = ss1.getCourseList(rk);
				request.setAttribute("courseSearchList", courseList);
				
				List QBSubjectList = ss1.getQBSubjectList(rk);
				request.setAttribute("QBSubjectSearchList", QBSubjectList);
				indexPage = "/QBReport/index.jsp";
				dispatch(request, response, indexPage);
			}	
			 }
				
			
			 if(flag.equals("Report")){
					System.out.println("+++++++++++++++++++++++++++");
					String flag="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",strsql6="";
					
					log4jLogger.info("start===========Printing report=====");
						
					strsql="";
					strsql1="";
					strsql2="";
					strsql3="";
					strsql4="";
					strsql5="";
					strsql6="";
					
					
				if (!request.getParameter("dname").equals("NO")) {
					strsql1 = " and dept='" + request.getParameter("dname")
							+ "'";
				}
			 if (!request.getParameter("qcourse").equals("NO")) {

					strsql2 = " and Course='" + request.getParameter("qcourse")
							+ "'";

				}
			 if(!request.getParameter("subname").equals("NO")){
					strsql3= "and Sub_name='" + request.getParameter("subname") + "'";	
             }
					
			 if(!request.getParameter("subcode").equals("NO")){
					strsql4= " and Sub_code='" + request.getParameter("subcode") + "'";	
             }
				
			if(!request.getParameter("qyear").equals("")){
					strsql5= " and Year ='" + request.getParameter("qyear") + "'";	
            }
			if(!request.getParameter("qcode").equals("")){
				strsql6= " and Qb_code= '" + request.getParameter("qcode")+  "'";
			}
			
					 con=SessionHibernateUtil.getSession().connection();
		    	      	
		    	      	Map parameters = new HashMap();
		    	      	parameters.put("logo",base64Logo);
		    	      	
		    			String namedQuery=ReportQueryUtill.QB_Report_Query;
		    			StringBuffer sb = new StringBuffer();
		    			String filterQuery=null;
		    			sb.append(namedQuery);
						sb.append(" " + strsql1+strsql2+strsql3+strsql4+strsql5+strsql6);
						
						log4jLogger.info(":::::::::::::Report:::Type:::::::"+request.getParameter("printType"));
						reportType=request.getParameter("printType");
						System.out.println("======================================"+reportType);
						
						if(printType.equals("pdf"))
						{			
					   			        
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


							parameters.put("ReportTitle",ReportQueryUtill.QB_Report_Title);
							parameters.put("All_Query",sb.toString());				
					
					
							log4jLogger.info("SQL QUERY: " + sb);			
				
							InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/QB_Report.jasper");			
							JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
						
							JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);	
					
							JRAbstractExporter exporterPDF = new JRPdfExporter();	        
							exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);	        
							exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					
							response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.QB_Report_Title+".pdf");	
					
							response.setContentType("application/pdf");	        
							exporterPDF.exportReport(); 	               
						}
					
						 if(printType.equalsIgnoreCase("excel")) 
						{		
					
						List prepareSearchCriteriaLst = importExportXMLService.getQBReportList(sb.toString());
						
						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					     
						excelTitleMap.put("rptTitle", ReportQueryUtill.QB_Report_Title);
		
						Iterator Transfer = (prepareSearchCriteriaLst).iterator();
						QuestionExportRecord recordProcessor = new QuestionExportRecord(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(Transfer, recordProcessor,response.getOutputStream());
			        
				}
						
					
				}	
					
				
			} catch (Exception ss1) {
//				throw new ServletException(ss1);
				ss1.printStackTrace();
				
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