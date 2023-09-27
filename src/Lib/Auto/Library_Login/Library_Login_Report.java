
package Lib.Auto.Library_Login;

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
import Common.businessutil.search.SearchService;
import Lib.Auto.Account.AccountBean;
import Lib.Auto.Branch.BranchBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;


public class Library_Login_Report extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Library_Login_Report.class);

	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "";

	String Auth_Name = "", SameCode = "",reportType="";
	String sql="";
	String nam="";
	String filename="";
	int Author_Interface_code=0;
	int Author_Mas_code=0;
	String indexPage = null;
	List AccountArrylist;
	String SQL_Query = "";
	String frmdt="",todt="";
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);

	}	


	public synchronized void performTask(
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(true);

			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}			

			SearchService ss = BusinessServiceFactory.INSTANCE.getSearchService();
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			AccountBean ob=new AccountBean();
			flag = request.getParameter("flag");	

			int rk = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));

			log4jLogger.info(":::::::::::UserBranchID:::::::::"+rk);
			
			LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(rk);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());




			if(flag.equals("Gate_Report"))
			{
				log4jLogger.info("Inside Library_Login Detail ");	
				java.sql.Connection con = null;

				try{

					String deptname="",groupname="",txtmembercode="",strsql="";

					con=SessionHibernateUtil.getSession().connection();

					Map parameters = new HashMap();	
					parameters.put("logo",base64Logo);
					
					String namedQuery=ReportQueryUtill.Library_Login_Date;
					StringBuffer sb = new StringBuffer();
					String filterQuery=null;

					frmdt=Security.TextDate_member(request.getParameter("gate_from"));
					todt=Security.TextDate_member(request.getParameter("gate_to"));

					txtmembercode =request.getParameter("txtmembercode");
					deptname = request.getParameter("Dname");
					groupname = request.getParameter("Gname");

					if (!deptname.equals("") && deptname!=null)
					{
						strsql = strsql + " and dept_name = '" +deptname+"'";
					}
					if (!groupname.equals("") && groupname!=null)
					{
						strsql = strsql + " and group_name = '" +groupname+"'";
					}
					if (!txtmembercode.equals(""))
					{
						strsql = strsql + " and Member_Code = '" +txtmembercode+"'";
					}        	


					filterQuery="where last_visit between '"+frmdt+"' and '"+todt+"' "+strsql+" order by last_visit";			

					sb.append(namedQuery);
					sb.append(" " + filterQuery);	

					log4jLogger.info(":::::::::::::Report:::Type:::::::"+request.getParameter("printType"));
					reportType=request.getParameter("printType");

					if(reportType.equals("pdf"))
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


						parameters.put("ReportTitle",ReportQueryUtill.Library_Login_Title);
						parameters.put("All_Query",sb.toString());
						parameters.put("gate_from",Security.getdate(frmdt).replace('-','/'));
						parameters.put("gate_to",Security.getdate(todt).replace('-','/'));
						log4jLogger.info("namedQuery: " + sb);	

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Library_Login.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

						JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Library_Login_Title+".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport(); 
					}
					else 
					{		
						List prepareSearchCriteriaLst = importExportXMLService.getLibrary_Login_Excel(sb.toString());

						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

						excelTitleMap.put("fromAccNo", frmdt);
						excelTitleMap.put("toAccNo", todt);

						Iterator Library_Login_Excel = prepareSearchCriteriaLst.iterator();
						Library_Login_Excel recordProcessor = new Library_Login_Excel(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(Library_Login_Excel, recordProcessor,response.getOutputStream());

					}
				}catch(Exception e)
				{
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

			if(flag.equals("Statistics_Report"))
			{
				log4jLogger.info("Inside Gate Register Date Wise Statistics Report");	
				java.sql.Connection con = null;

				try{

					String deptname="",groupname="",gate_from="",gate_to="",txtmembercode="",strsql="";

					//ReportService ss1 = BusinessServiceFactory.INSTANCE.getReportService();			
					//con=ss1.getDBConnect();	

					con=SessionHibernateUtil.getSession().connection();

					Map parameters = new HashMap();	
					parameters.put("logo",base64Logo);
					String namedQuery=ReportQueryUtill.Library_Login_Statistics;
					StringBuffer sb = new StringBuffer();
					String filterQuery=null;

					frmdt=Security.TextDate_member(request.getParameter("gate_from"));
					todt=Security.TextDate_member(request.getParameter("gate_to"));


					log4jLogger.info("::::::::::::::::::from:::::;"+gate_from);
					log4jLogger.info("::::::::::::::::::to:::::;"+gate_to);

					txtmembercode =request.getParameter("txtmembercode");
					deptname = request.getParameter("Dname");
					groupname = request.getParameter("Gname");

					if (!deptname.equals("") && deptname!=null)
					{
						strsql = strsql + " and dept_name = '" +deptname+"'";
					}
					if (!groupname.equals("") && groupname!=null)
					{
						strsql = strsql + " and group_name = '" +groupname+"'";
					}
					if (!txtmembercode.equals(""))
					{
						strsql = strsql + " and Member_Code = '" +txtmembercode+"'";
					}        	


					filterQuery=strsql+" and last_visit between '"+gate_from+"' and '"+gate_to+"' GROUP BY last_visit ORDER BY last_visit ASC";


					sb.append(namedQuery);
					sb.append(" " + filterQuery);

					log4jLogger.info(":::::::::::::Report:::Type:::::::"+request.getParameter("printType"));
					reportType=request.getParameter("printType");

					if(reportType.equals("pdf"))
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

						parameters.put("ReportTitle",ReportQueryUtill.Library_Login_Statitics_Title);
						parameters.put("All_Query",sb.toString());	
						parameters.put("gate_from",Security.getdate(frmdt).replace('-','/'));
						parameters.put("gate_to",Security.getdate(todt).replace('-','/'));
						log4jLogger.info("namedQuery: " + sb);	

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Library_Login_Statistics.jasper");
						//			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Gate_Register_statistics-old.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

						JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Library_Login_Statitics_Title+".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport(); 

					}
					else
					{
						List prepareSearchCriteriaLst = importExportXMLService.getLibrary_Login_Excel_Statistics(sb.toString());

						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

						excelTitleMap.put("fromAccNo", gate_from);
						excelTitleMap.put("toAccNo", gate_to);

						Iterator Library_Login_Excel_Statistics = prepareSearchCriteriaLst.iterator();
						Library_Login_Excel_Statistics recordProcessor = new Library_Login_Excel_Statistics(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(Library_Login_Excel_Statistics, recordProcessor,response.getOutputStream());
					}

				}
				catch(Exception e)
				{
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

			if(flag.equals("All_Logout"))
			{
				log4jLogger.info("start===========All_Logout=====");
				int count;
				count = ss.getRegisterAllLogout();

				if(count > 0)
				{
					indexPage = "/Gate_Register/index.jsp?check=success";
				}
				else 
				{
					indexPage = "/Gate_Register/index.jsp?check=fail";
				}
				dispatch(request, response, indexPage);			
			}		

			if (flag.equals("chpwd"))
			{
				log4jLogger.info("start===========chpwd=====");
				ob = new AccountBean();
				ob.setuid(request.getParameter("uid"));
				ob.setpwd(request.getParameter("pwd"));
				ob.setnewpwd(request.getParameter("newpwd"));
				ob.setcfmpwd(request.getParameter("cfmpwd"));
				String retstr=ss.getChangePwd(ob);


				request.setAttribute("strobj",retstr);
				indexPage = "/Account/changepwd.jsp?check=cpwd";
				dispatch(request, response, indexPage);

			}
			if (flag.equals("save"))
			{
				log4jLogger.info("start===========save=====");
				ob = new AccountBean();
				ob=ss.getCheckAccount(request.getParameter("uid").trim(),request.getParameter("pwd").trim());

				if(ob.getuid()!=null)
				{
					session.setAttribute("OpacID", ob.getuid()); // By RK on 17-10-2013
					session.setAttribute("OpacPWD", ob.getauthor()); // By RK on 17-10-2013
					request.setAttribute("beanobject", ob);
					indexPage = "/Account/account.jsp";
				}
				else{

					indexPage = "/Account/index.jsp?check=usernotfound";
				}
				dispatch(request, response, indexPage);

			}
			if (flag.equals("back"))
			{
				log4jLogger.info("start===========BACK to Account Page=====");
				ob = new AccountBean();
				ob=ss.getCheckAccount((String.valueOf(session.getAttribute("OpacID")).trim()),(String.valueOf(session.getAttribute("OpacPWD")).trim()));

				if(ob.getuid()!=null)
				{			        
					request.setAttribute("beanobject", ob);
					indexPage = "/Account/account.jsp";
				}
				else{

					indexPage = "/Account/index.jsp?check=usernotfound";
				}

				dispatch(request, response, indexPage);
			}

			if (flag.equals("new"))
			{
				log4jLogger.info("start===========new=====");
				indexPage = "/Account/index.jsp";
				dispatch(request, response, indexPage);
			}



		}
		catch (Exception sss) {


		} finally {

		}

	}

	/** 
	 * Instance variable for SQL statement property
	 */

	/****************************************************************
	 *prepare the sql statement for execution
	 **/
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
					throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}



}
