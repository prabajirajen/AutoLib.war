package Lib.Auto.LibraryCollection;
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
import Common.businessutil.LoginUserService;
import Common.businessutil.reports.ReportQueryUtill;
import Lib.Auto.Branch.BranchBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;

public class LibraryCollection extends HttpServlet implements ReportQueryUtill,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(LibraryCollection.class);	

	java.sql.Connection con = null;

	String frmaccno,toaccno,strsql="",flag=null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {

		performTask(request, response);

	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		performTask(request, response);

	}	

	public void performTask(
			HttpServletRequest request,
			HttpServletResponse response)  throws ServletException, IOException {

		try {		
			log4jLogger.info("Inside Library Collection ");	

			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan	
			
			String instName=String.valueOf(session.getAttribute("instituteName"));
			
			log4jLogger.info("instName::::::::::::: " + session.getAttribute("UserRights"));
			
			LoginUserService ss = LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ss.getBranchInfo(rk);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());
			
			
			
			con=SessionHibernateUtil.getSession().connection();
			flag = request.getParameter("flag");

			

			if(rk==0){
				strsql = "";				
			}else  {
				strsql = " where Branch_Code="+rk;	 
			}

			String namedQuery=ReportQueryUtill.Library_Collection_Query;
			StringBuffer sb = new StringBuffer();
			String filterQuery=null;

			sb.append(namedQuery);
			sb.append(" " + strsql);


			//Security.getBranchSession(session,response,request);	
			if(flag.equalsIgnoreCase("pdf"))
			{
				Map parameters = new HashMap();						

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


				parameters.put("ReportTitle",ReportQueryUtill.Library_Collection_Title);		
				parameters.put("All_Query",sb.toString());
				parameters.put("logo",base64Logo);
				log4jLogger.info("base64Logo::::::::::::: " + session.getAttribute("base64Logo"));



				//parameters.put("logo",isLogo ? branch.getLogoAsFile() : null);

				log4jLogger.info("SQL QUERY: " + sb);	

				//InputStream inputStream1 = getClass().getResourceAsStream("/Report/Library_Collection.jasper"); // It is working in JBOSS server but Not Working in Appache Tomcat Server.				
				InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Library_Collection.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	

				JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

				JRAbstractExporter exporterPDF = new JRPdfExporter();
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Library_Collection_Title+".pdf");
				response.setContentType("application/pdf"); 
				exporterPDF.exportReport(); 
			}      
			if(flag.equalsIgnoreCase("chart"))
			{
				namedQuery="select * from library_collection_final" + " " + strsql;
				Map parameters = new HashMap();	

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


				parameters.put("All_Query",namedQuery);
				parameters.put("ReportTitle",ReportQueryUtill.Library_Collection_Title);
				parameters.put("logo",base64Logo);
				System.out.println(namedQuery);

				//InputStream inputStream1 = getClass().getResourceAsStream("/Report/Library_Collection.jasper"); // It is working in JBOSS server but Not Working in Appache Tomcat Server.				
				InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Library_Collection_barchart.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	

				JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

				JRAbstractExporter exporterPDF = new JRPdfExporter();
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Library_Collection_Title+".pdf");
				response.setContentType("application/pdf");
				exporterPDF.exportReport(); 

			}
			/**
	        JRAbstractExporter exporterXLS = new JRXlsExporter();
	        exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
	        exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	        exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	        exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
	        response.setContentType("application/xls");
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Library_Collection_Title+".xls");

	        exporterXLS.exportReport(); 
			 */

		} 
		catch(NumberFormatException e)
		{
			log4jLogger.info("getSession Error at NumberFormatException:");
			//e.printStackTrace();
			String indexPage = "/Tree/sessiontimeout.jsp";
			dispatch(request, response, indexPage);
		}
		catch (Exception  e) {		

			e.printStackTrace();
		}
		finally
		{
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



	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
					throws ServletException, IOException {
		// response.sendRedirect(indexPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
