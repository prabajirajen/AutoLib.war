package Lib.Auto.RFIDTagReport;

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

public class RFIDTagReport extends HttpServlet implements ReportQueryUtill {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(RFIDTagReport.class);

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

			String namedQuery = ReportQueryUtill.RFIDTag;
			StringBuffer sb = new StringBuffer();
			String filterQuery = null;

			int rk = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
			
			log4jLogger.info(":::::::::::UserBranchID:::::::::"+rk);

			if (rk == 0) {
				filterQuery = "";
			} else {
				filterQuery = "and Branch_Code=" + rk;
			}

			if (flag.equals("AccessNo_Wise")) {

				frmaccno = request.getParameter("From_Accno");
				toaccno = request.getParameter("To_Accno");
				
				
				
				if (Security.IsNumeric(frmaccno) && Security.IsNumeric(toaccno)) {
				
					
					log4jLogger.info("inside Accession number wise numbers only");
					
					firstNum = Integer.parseInt(request.getParameter("firstNum"));
					secondNum = Integer.parseInt(request.getParameter("secondNum"));

					firstStr = request.getParameter("firstStr");
					firstStrCount = firstStr.length() + 1;
					secondStr = request.getParameter("secondStr");
					frmaccno = request.getParameter("From_Accno");
					toaccno = request.getParameter("To_Accno");
					
					log4jLogger.info("FROM ACCESS NO"+frmaccno);
					
					log4jLogger.info("TO ACCESS NO"+toaccno);
					
					if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery+" and Document='"+request.getParameter("Type")+"'";

					filterQuery = filterQuery+ " and  access_no REGEXP '^[0-9]+$' and CONVERT(access_no,SIGNED) between '"+ frmaccno + "' and '" + toaccno+ "' order by CONVERT(access_no,SIGNED) ASC";

				} else {
					
					firstNum = Integer.parseInt(request.getParameter("firstNum"));
					secondNum = Integer.parseInt(request.getParameter("secondNum"));

					firstStr = request.getParameter("firstStr");
					firstStrCount = firstStr.length() + 1;
					secondStr = request.getParameter("secondStr");
					frmaccno = request.getParameter("From_Accno");
					toaccno = request.getParameter("To_Accno");

					
					log4jLogger.info("inside Accession number Alpha numeric values");

					if (!request.getParameter("Type").equalsIgnoreCase("ALL"))
						filterQuery = filterQuery+" and Document='"+request.getParameter("Type")+"'";


					filterQuery = filterQuery+" and NOT access_no REGEXP '[^0-9]+$' And Access_No Like '"+firstStr+"%' And CAST(SUBSTRING(Access_No,"+firstStrCount+") AS SIGNED) BETWEEN '"+firstNum+ "' AND '" + secondNum+ "' ORDER BY LPAD(ACCESS_NO,20,'0')";

				}

				sb.append(namedQuery);
				sb.append(" " + filterQuery);
				
				
			}
			
					
				
		//--------------------------------------------------------------------------
			
			

			if (flag1.equalsIgnoreCase("pdf")) {
				boolean checkData = rs.getCheckData(sb.toString());
				  if (checkData)
					{
					 //no data
						log4jLogger.info("----------------NO DATA FOUND-------------------");
					  indexPage = "/RFIDTagReport/index.jsp?check=NoData";
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
			
			
				parameters.put("ReportTitle", ReportQueryUtill.Tag_Title);
				parameters.put("All_Query", sb.toString());
				log4jLogger.info("namedQuery: " + sb);

				InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/RFIDTagReport.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);
				JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters, con);
				JRAbstractExporter exporterPDF = new JRPdfExporter();
				
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
				response.setHeader("Content-Disposition","attachment;filename=" + ReportQueryUtill.Tag_Title + ".pdf");
				response.setContentType("application/pdf");
				exporterPDF.exportReport();
				 }

			} 
			else if(flag1.equalsIgnoreCase("excel")) {
				

				// For Excel Report.
				boolean checkData = rs.getCheckData(sb.toString());
				  if (checkData)
					{
					 //no data
						log4jLogger.info("----------------NO DATA FOUND-------------------");
					  indexPage = "/RFIDTagReport/index.jsp?check=NoData";
					  dispatch(request, response, indexPage);
					 
					}else{
						
					
				
				List prepareSearchCriteriaLst = importExportXMLService.getRFIDTagList(sb.toString());
				Iterator tagexcel = prepareSearchCriteriaLst.iterator();
				RFIDTagExcel recordProcessor = new RFIDTagExcel();
				response.setContentType("text/csv");
				response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
				csvImportExportService.Export(tagexcel, recordProcessor,response.getOutputStream());

			}
				  
			}

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


	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
