package Lib.Auto.MemberReport;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
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
import javax.sql.DataSource;

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
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.MemberTransfer.MemberTransRefBean;
import Lib.Auto.OrdInvProcessing.orderbean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;

public class MemberReport extends HttpServlet implements Serializable,
COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;

	DataSource datasource;

	String flag = "",flag1="", order1="",order2="",order3="",order="",sorting="",mcodefrom="",mcodeto="";


	String indexPage = null;
	orderbean orderObject = new orderbean();


	private static Logger log4jLogger = Logger.getLogger(MemberReport.class);

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
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			ReportService ss1 = BusinessServiceFactory.INSTANCE.getReportService();

			ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();

			String namedQuery = ReportQueryUtill.Query_Member_Report;

			StringBuffer sb = new StringBuffer();
			String filterQuery = "";

			flag = request.getParameter("flag");
			flag1=request.getParameter("flag1");

			mcodefrom=request.getParameter("mcodefrom");
			mcodeto=request.getParameter("mcodeto");

			log4jLogger.info("--flag values------"+flag);
			log4jLogger.info("--flag1 values------"+flag1);

			int rk = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));
			
			LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(rk);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());

			log4jLogger.info(":::::::::::UserBranchID:::::::::"+rk);

			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();

			if(flag.equals("load")){

				log4jLogger.info("----------------Member Report Load Details----------------");


				MemberTransRefBean beanObject = new MemberTransRefBean();				
				beanObject.setBranchCode(rk);
				List<?> branchWiseList = ss1.getBranchList(beanObject);				
				request.setAttribute("distinctBranchSearchList", branchWiseList);

				List BranchWiseDesigList = ss1.getDesigList(beanObject);				
				request.setAttribute("DesigSearchList", BranchWiseDesigList);

				List BranchWiseGroupList = ss1.getGroupList(beanObject);
				request.setAttribute("distinctBranchWiseGroupSearchList", BranchWiseGroupList);


				List BranchWiseCourseList = ss1.getCourseList(beanObject);
				request.setAttribute("distinctBranchWiseCourseSearchList", BranchWiseCourseList);

				indexPage = "/MemberReport/index.jsp";	
				dispatch(request, response, indexPage);


			}


			if(flag.equals("print")){

				int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));

				log4jLogger.info("::::::::::::BranchCode:::::::::::::"+branchID);



				filterQuery = filterQuery+" and branch_code="+branchID;




				log4jLogger.info("----------------Inside Printing---------------");

				if (!mcodefrom.equals("") && !mcodeto.equals(""))
				{
					filterQuery = filterQuery + " and member_code between  '" +mcodefrom+"' AND '" +mcodeto+"' ";
				}

				if (!request.getParameter("name").equalsIgnoreCase("ALL") && !request.getParameter("name").isEmpty())

					filterQuery = filterQuery+" and member_name='"+request.getParameter("name")+"'";

				if (!request.getParameter("desig").equalsIgnoreCase("ALL") && !request.getParameter("desig").isEmpty())

					filterQuery = filterQuery+" and desig_code='"+request.getParameter("desig")+"'";

				if (!request.getParameter("group").equalsIgnoreCase("ALL") && !request.getParameter("group").isEmpty())

					filterQuery = filterQuery+" and group_code='"+request.getParameter("group")+"'";

				if (!request.getParameter("course").equalsIgnoreCase("ALL") && !request.getParameter("course").isEmpty())

					filterQuery = filterQuery+" and course_code='"+request.getParameter("course")+"'";

				if (!request.getParameter("branch").equalsIgnoreCase("ALL") && !request.getParameter("branch").isEmpty())

					filterQuery = filterQuery+" and branch_code='"+request.getParameter("branch")+"'";

				if (!request.getParameter("year").equalsIgnoreCase("ALL") && !request.getParameter("year").isEmpty())

					filterQuery = filterQuery+" and course_year='"+request.getParameter("year")+"'";

				if (!request.getParameter("gender").equalsIgnoreCase("ALL") && !request.getParameter("gender").isEmpty())

					filterQuery = filterQuery+" and sex='"+request.getParameter("gender")+"'";

				if (!request.getParameter("status").equalsIgnoreCase("ALL") && !request.getParameter("status").isEmpty()){

					if(request.getParameter("status").equals("ACTIVE"))

						filterQuery = filterQuery+ "and Expiry_Date>='" + Security.Counter_DateText()+ "'";
					else{
						filterQuery = filterQuery+ "and Expiry_Date<='" + Security.Counter_DateText()+ "'";
					}
				}

				if (!request.getParameter("lock").equalsIgnoreCase("ALL") && !request.getParameter("lock").isEmpty())

					filterQuery = filterQuery+" and slock='"+request.getParameter("lock")+"'";
				
				
				  filterQuery=filterQuery + " AND branch_code ='"+rk+"'" ;
				  


				order1 =request.getParameter("order1");
				order2 =request.getParameter("order2");
				order3 =request.getParameter("order3");
				sorting=request.getParameter("sorting");

				order="";
				if (!order1.equals("NO" )) order = order+order1;
				if (!order2.equals("NO" )) order = order+", "+order2;
				if (!order3.equals("NO"))  order = order+", "+order3;
				if (!sorting.equals("Asc")) order=order+" DESC";
				if (!sorting.equals("Desc"))order=order+" ASC";

				sb.append(namedQuery);
				sb.append(" " + filterQuery+" ORDER BY "+ order);

				boolean checkData = rs.getCheckData(sb.toString());// for check no data

				if (checkData){

					log4jLogger.info("-------All_Query-------Values"+sb.toString());

					log4jLogger.info("----------------NO DATA FOUND-------------------");
					indexPage = "/MemberReport/index.jsp?check=NoData";
					dispatch(request, response, indexPage);
				}

				else
				{// data is available
					con = SessionHibernateUtil.getSession().connection();		
					InputStream inputStream1 = null;
					Map<String, Object> parameters = new HashMap<String, Object>();
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

					parameters.put("All_Query", sb.toString());
					log4jLogger.info("-------All_Query-------Values"+sb.toString());


					if(flag1.equals("Details")){	//Active user Details
						if (request.getParameter("status").equalsIgnoreCase("IN ACTIVE")) {


							log4jLogger.info("---------------inside InActive Details Report-----------");
							inputStream1 = getServletContext().getResourceAsStream("/Report/Member_Report_Inactive.jasper");
							parameters.put("ReportTitle", ReportQueryUtill.Counter_MemberReports_Inactive);
							response.setHeader("Content-Disposition","attachment;filename="	+ ReportQueryUtill.Counter_MemberReports_Inactive + ".pdf");


						}else{


							//In Active user Details

							log4jLogger.info("---------------inside Active Details Report-----------");

							parameters.put("ReportTitle", ReportQueryUtill.Counter_MemberReports_Title);
							inputStream1 = getServletContext().getResourceAsStream("/Report/Member_Report.jasper");
							response.setHeader("Content-Disposition","attachment;filename="	+ ReportQueryUtill.Counter_MemberReports_Title + ".pdf");



						}
					}
					else if(flag1.equals("Excel"))
					{
						List prepareSearchCriteriaLst = importExportXMLService.getMemberReportExcelList(sb.toString());

						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

						//excelTitleMap.put("fromAccNo", "1");
						//excelTitleMap.put("toAccNo", "1");
						//excelTitleMap.put("documentType", "1");

						Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
						MemberReportExcel recordProcessor = new MemberReportExcel(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(studentDataItr, recordProcessor,response.getOutputStream());
					}					
					else{
						//Active user Statistics
						if (request.getParameter("status").equalsIgnoreCase("ACTIVE")) {
							log4jLogger.info("---------------inside Active Statistics Report-----------");
							parameters.put("ReportTitle", ReportQueryUtill.Counter_MemberReports_Active_Statistics_title);
							response.setHeader("Content-Disposition","attachment;filename="	+ ReportQueryUtill.Counter_MemberReports_Active_Statistics_title + ".pdf");
						}else{//In Active user Statistics
							log4jLogger.info("---------------inside InActive Statistics Report-----------");
							parameters.put("ReportTitle", ReportQueryUtill.Counter_MemberReports_Inactive);
							response.setHeader("Content-Disposition","attachment;filename="	+ ReportQueryUtill.Counter_MemberReports_Inactive + ".pdf");
						}
						inputStream1 = getServletContext().getResourceAsStream("/Report/Member_Report_Statistics.jasper");
					}



					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);
					JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, con);
					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT,jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM,response.getOutputStream());
					response.setContentType("application/pdf");
					exporterPDF.exportReport();


				}
			}



		} catch (Exception e) {
			e.printStackTrace();

		} catch (Throwable theException) {
			theException.printStackTrace();
		} finally {

			if (con != null) {
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
		// response.sendRedirect(indexPage);
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
