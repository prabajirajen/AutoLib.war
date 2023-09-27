package Lib.Auto.BudgetReport;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
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
import Common.businessutil.reports.ReportService;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Budget.BudgetBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;

public class BudgetReport extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(BudgetReport.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String frmdt = null, todt = null, strsql = "";
	String flag = null, flag1 = null, doc_type = null;
	int deptCode = 0, budCode = 0;
	

	String indexPage = "";
	java.sql.Connection con = null;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			int branchID = (Integer.parseInt((String.valueOf(session
					.getAttribute("UserBranchID")))));
			
			LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(branchID);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());


			ReportService rs = BusinessServiceFactory.INSTANCE
					.getReportService();
			BudgetBean beanObject = new BudgetBean();
			beanObject.setBranchCode(branchID);
			//PrintWriter out = response.getWriter();

			flag = request.getParameter("flag");
			flag1 = request.getParameter("flag1");

			String namedQuery = ReportQueryUtill.Budget_Date_wise;
			StringBuffer sb = new StringBuffer();
			String filterQuery = null;

			log4jLogger
					.info("------------------Flag Value-----------------   :"
							+ flag);

			if (flag.equals("load"))

			{
				List BudgetSearchReport = rs.getSearchBudgetList(beanObject);
				request.setAttribute("budgetSearchList", BudgetSearchReport);
				indexPage = "/BudgetReport/index.jsp";
				dispatch(request, response, indexPage);
			}

			if (flag.equals("Print")) {

				Map<String, Object> parameters = new HashMap<String, Object>();
				
				parameters.put("logo",base64Logo);
				
				int rk = (Integer.parseInt((String.valueOf(session
						.getAttribute("UserBranchID"))))); // For Titan

				if (rk == 0) {
					strsql = "";
				} else {
					strsql = " and Branch_Code=" + rk;
				}

				frmdt = Security.TextDate_member(request
						.getParameter("fromdate"));
				todt = Security.TextDate_member(request.getParameter("todate"));

				filterQuery = " and bud_from >='" + frmdt + "' and bud_to <='"
						+ todt + "'" + strsql;

				if (!request.getParameter("budcode").equals("NO"))
					filterQuery = filterQuery + " and Bud_Code="
							+ Integer.parseInt(request.getParameter("budcode"))
							+ "";

				if (!request.getParameter("deptcode").equals("NO"))
					filterQuery = filterQuery
							+ " and dept_code="
							+ Integer
									.parseInt(request.getParameter("deptcode"))
							+ "";

				/*
				 * if(!request.getParameter("doctype").equals("ALL"))
				 * filterQuery =
				 * filterQuery+" and dept_code="+Integer.parseInt(request
				 * .getParameter("deptcode"))+"";
				 */
				sb.append(namedQuery);
				sb.append(" " + filterQuery);

				if (flag1.equals("Report")) {

					log4jLogger.info("eteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"
							+ sb.toString());
					boolean checkData = rs.getCheckData(sb.toString());
					if (checkData) {
						// no data
						log4jLogger
								.info("----------------NO DATA FOUND-------------------");
						indexPage = "/BudgetReport/index.jsp?check=NoData";
						dispatch(request, response, indexPage);
					}

					else {
						
						
						con = SessionHibernateUtil.getSession().connection();
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

						parameters.put("ReportTitle",
								ReportQueryUtill.Budget_Title);
						parameters.put("All_Query", sb.toString());

						log4jLogger.info("namedQuery: " + sb);

						InputStream inputStream1 = getServletContext()
								.getResourceAsStream(
										"/Report/Budget_Report_Full.jasper");

						JasperReport report = (JasperReport) JRLoader
								.loadObject(inputStream1);

						JasperPrint jasperPrint = JasperFillManager.fillReport(
								report, parameters, con);

						JRAbstractExporter exporterPDF = new JRPdfExporter();

						exporterPDF.setParameter(
								JRExporterParameter.JASPER_PRINT, jasperPrint);
						log4jLogger
								.info("99999999999999999999999999999999999999999999999999999");
						exporterPDF.setParameter(
								JRExporterParameter.OUTPUT_STREAM,
								response.getOutputStream());

						response.setHeader("Content-Disposition",
								"attachment;filename="
										+ ReportQueryUtill.Budget_Title
										+ ".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport();

					}

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

	/**
	 * Instance variable for SQL statement property
	 */

}
