package Lib.Auto.RFIDCardReport;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
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
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.AccessionRegister.accessbean;

import com.ibm.icu.text.DecimalFormat;
import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;

public class RFIDCardReport extends HttpServlet implements ReportQueryUtill {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(RFIDCardReport.class);

	java.sql.Connection con = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}
			 //boolean checkData;
			String frmaccno = null, toaccno = null, frmdt = null, todt = null, flag = null, doc_type = null, flag1 = null;
			String indexPage ="";
			String firstStr = null, secondStr = null;
			int firstNum = 0, secondNum = 0, firstStrCount = 0;
			accessbean ob=new accessbean();
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE
					.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE
					.getExceImportExportService();
			
			
			 ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();
			 
			con = SessionHibernateUtil.getSession().connection();
			
			

			flag = request.getParameter("flag");
			flag1 = request.getParameter("flagExcel");

			
			doc_type = request.getParameter("Type");
			

			Map<String, Object> parameters = new HashMap<String, Object>();

			String namedQuery = ReportQueryUtill.RFIDCard;
			StringBuffer sb = new StringBuffer();
			String filterQuery = null;

			int rk = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
			
			log4jLogger.info(":::::::::::UserBranchID:::::::::"+rk);

//			if (rk == 0) {
//				filterQuery = "";
//			} else {
//				filterQuery = "and Branch_Code=" + rk;
//			}

			if (flag.equals("AccessNo_Wise")) {

				frmaccno = request.getParameter("From_Mcode");
				toaccno = request.getParameter("To_Mcode");
				if(frmaccno.equalsIgnoreCase("") && toaccno.equalsIgnoreCase(""))
					filterQuery = "";
				else
					filterQuery =  " and  member_code between '"+frmaccno+"' and '"+toaccno+"' ";

				sb.append(namedQuery);
				sb.append(" " + filterQuery);

				
			}
			
					
				
		//--------------------------------------------------------------------------
			
			

			if (flag1.equalsIgnoreCase("PdfReport")) {
				boolean checkData = rs.getCheckData(sb.toString());
				  if (checkData)
					{
					 //no data
						log4jLogger.info("----------------NO DATA FOUND-------------------");
					  indexPage = "/RFIDCardReport/index.jsp?check=NoData";
					  dispatch(request, response, indexPage);
					 
					}
				 
				 else{

					 
					 
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
			
			
				parameters.put("ReportTitle", ReportQueryUtill.Card_Title);
				parameters.put("All_Query", sb.toString());
				log4jLogger.info("namedQuery: " + sb);

				InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/RFIDCardReport.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);
				JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, con);
				JRAbstractExporter exporterPDF = new JRPdfExporter();
				
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
				response.setHeader("Content-Disposition","attachment;filename=" + ReportQueryUtill.Card_Title + ".pdf");
				response.setContentType("application/pdf");
				exporterPDF.exportReport();
				 }

			} 
//			else if(flag1.equalsIgnoreCase("ExcelReport")) {
//				
//
//				// For Excel Report.
//				boolean checkData = rs.getCheckData(sb.toString());
//				  if (checkData)
//					{
//					 //no data
//						log4jLogger.info("----------------NO DATA FOUND-------------------");
//					  indexPage = "/RFIDCardReport/index.jsp?check=NoData";
//					  dispatch(request, response, indexPage);
//					 
//					}else{
//						
//					
//				
//				List prepareSearchCriteriaLst = importExportXMLService.getBindingReportExcelList(sb.toString());
//
//				Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
//
//				if (flag.equals("AccessNo_Wise")) {
//					excelTitleMap.put("fromAccNo", frmaccno);
//					excelTitleMap.put("toAccNo", toaccno);
//					excelTitleMap.put("documentType", doc_type);
//
//				} else {
//					excelTitleMap.put("fromAccNo", frmdt);
//					excelTitleMap.put("toAccNo", todt);
//					excelTitleMap.put("documentType", doc_type);
//				}
//
//				Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
//				BindingExcelReport recordProcessor = new BindingExcelReport(excelTitleMap);
//				response.setContentType("text/csv");
//				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
//				csvImportExportService.Export(studentDataItr, recordProcessor,response.getOutputStream());
//
//			}
//				  
//			}

		}

		catch (Exception e) {

			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {

					e.printStackTrace();
				}
			}
		}

	}

	//String indexPage = "/AccessionRegister/index.jsp";

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
