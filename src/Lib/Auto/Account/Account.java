
package Lib.Auto.Account;

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
import Common.Security_Counter;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.LoginUserService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Common.businessutil.search.SearchService;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.MemberTransfer.MemberTransRefBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;

public class Account extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(Account.class);

	private static final long serialVersionUID = -8906987252709033668L;

	String protocol = "", flag = "",printType="";

	String Auth_Name = "", SameCode = "";
	String sql="";
	String nam="";
	String filename="";
	int Author_Interface_code=0;
	int Author_Mas_code=0;
	String indexPage = null;
	List AccountArrylist;
	String SQL_Query = "";
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
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan

			LoginUserService ls = LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(branchID);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());

			
			AccountBean ob=new AccountBean();
			flag = request.getParameter("flag");			
			printType = request.getParameter("printType");

			ReportService ss1 = BusinessServiceFactory.INSTANCE.getReportService();
			ReportService rs=BusinessServiceFactory.INSTANCE.getReportService();

			int rk = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));

			log4jLogger.info(":::::::::::UserBranchID:::::::::"+rk);

			if(request.getParameter("issueuserid")!=null)	{
				log4jLogger.info("start===========issueuserid====="+request.getParameter("issueuserid"));
				ob=new AccountBean();
				ob.setuid(request.getParameter("issueuserid"));
				SQL_Query="and member_code =";
				SQL_Query=SQL_Query+"'"+request.getParameter("issueuserid")+"'"+" order by issue_date asc";				
				AccountArrylist=ss.getAccountDetailsIssue(SQL_Query);		

				session.setAttribute("AccountArrylist",AccountArrylist);
				indexPage = "/Account/issuedetails.jsp";
				dispatch(request, response, indexPage);

			}

			if(request.getParameter("returnuserid")!=null)	{
				log4jLogger.info("start===========returnuserid====="+request.getParameter("returnuserid"));

				SQL_Query="and member_code = ";
				SQL_Query=SQL_Query+"'"+request.getParameter("returnuserid")+"'"+" order by return_date desc ";				
				AccountArrylist=ss.getAccountDetails(SQL_Query);		

				session.setAttribute("AccountArrylist",AccountArrylist);
				indexPage = "/Account/retrundetails.jsp";
				dispatch(request, response, indexPage);

			}


			if(request.getParameter("returnuserfineid")!=null)	{
				log4jLogger.info("start===========returnuserid====="+request.getParameter("returnuserid"));

				SQL_Query="and member_code = ";
				SQL_Query=SQL_Query+"'"+request.getParameter("returnuserfineid")+"'"+"and fine_amount<>'0.00' order by return_date desc ";				
				AccountArrylist=ss.getAccountDetails(SQL_Query);		

				session.setAttribute("AccountArrylist",AccountArrylist);
				indexPage = "/Account/returnfinedetails.jsp";
				dispatch(request, response, indexPage);

			}





			if(request.getParameter("userpaidamtid")!=null)	{
				log4jLogger.info("start===========returnuserid====="+request.getParameter("userpaidamtid"));

				SQL_Query="and member_code = ";
				SQL_Query=SQL_Query+"'"+request.getParameter("userpaidamtid")+"'"+" order by payment_date desc ";				
				AccountArrylist=ss.getAccountPaidDetails(SQL_Query);		

				session.setAttribute("AccountArrylist",AccountArrylist);
				indexPage = "/Account/finepaiddetails.jsp";
				dispatch(request, response, indexPage);

			}

			if(request.getParameter("reserveuserid")!=null)	{
				log4jLogger.info("start===========reserveuserid====="+request.getParameter("reserveuserid"));
				ob=new AccountBean();
				ob.setuid(request.getParameter("reserveuserid"));
				SQL_Query="and member_code =";
				SQL_Query=SQL_Query+"'"+request.getParameter("reserveuserid")+"'";				
				AccountArrylist=ss.getAccountDetailsReserve(SQL_Query);		

				session.setAttribute("AccountArrylist",AccountArrylist);
				indexPage = "/Account/reservedetails.jsp";
				dispatch(request, response, indexPage);

			}
			if(request.getParameter("accno")!=null)	{
				ob=new AccountBean();
				log4jLogger.info("start===========accno====="+request.getParameter("accno"));			
				ob.setaccno(request.getParameter("accno"));

				String opacID = String.valueOf(session.getAttribute("OpacID"));

				ob.setuid(opacID); // By RK on 07-10-2013
				ob.settitle(opacID);

				if(opacID.equalsIgnoreCase("null"))
				{
					ob.setuid(String.valueOf(session.getAttribute("UserID").toString()));
					ob.settitle(String.valueOf(session.getAttribute("UserID").toString()));
				}			

				String retstring=ss.getOnlineRenewSave(ob);

				SQL_Query="and member_code =";
				SQL_Query=SQL_Query+"'"+ob.getuid()+"'";				
				AccountArrylist=ss.getAccountDetailsIssue(SQL_Query);		

				session.setAttribute("AccountArrylist",AccountArrylist);
				request.setAttribute("strobj",retstring);

				indexPage = "/Account/issuedetails.jsp?check=YES";
				//indexPage = "/Account/issuedetailsdisplay.jsp?check=YES";
				dispatch(request, response, indexPage);

			}

			if(request.getParameter("resaccno")!=null)	{
				ob=new AccountBean();
				log4jLogger.info("start===========resaccno====="+request.getParameter("resaccno"));		

				ob.setaccno(request.getParameter("resaccno"));
				String opacID = String.valueOf(session.getAttribute("OpacID"));

				ob.setuid(opacID); // By RK on 07-10-2013

				if(opacID.equalsIgnoreCase("null"))
				{
					ob.setuid(String.valueOf(session.getAttribute("UserID").toString()));
				}			

				String retstring=ss.getOnlineReserveCancel(ob);			

				SQL_Query="and member_code =";
				SQL_Query=SQL_Query+"'"+ob.getuid()+"'";	

				AccountArrylist=ss.getAccountDetailsReserve(SQL_Query);			

				session.setAttribute("AccountArrylist",AccountArrylist);
				request.setAttribute("strobj",retstring);
				indexPage = "/Account/reservedetailsdisplay.jsp?check=YES";
				dispatch(request, response, indexPage);


			}

			// For Online Reservation

			if((request.getParameter("reservecheck")!=null) && (request.getParameter("document")!=null))	{
				log4jLogger.info("start===========reservecheck====="+request.getParameter("reservecheck")+" And "+request.getParameter("document"));

				String accno=request.getParameter("reservecheck").toString();
				String doc=request.getParameter("document").toString();

				AccountArrylist=ss.getIssueDetails(accno,doc);		

				session.setAttribute("IssueArrylist",AccountArrylist);			
				indexPage = "/Simples/ReserveView.jsp";
				dispatch(request, response, indexPage);

			}		


			if(flag.equals("Reserve"))	{

				log4jLogger.info("start=========== OnlineReservation =====");

				ob = new AccountBean();
				ob=ss.getCheckAccount(request.getParameter("ruid").trim(),request.getParameter("rpwd").trim(),branchID);

				if(ob.getuid()!=null)
				{

					ob=new AccountBean();

					ob.setaccno(request.getParameter("raccno"));
					ob.setdtype(request.getParameter("rdtype"));
					ob.setuid(request.getParameter("ruid"));
					ob.setresdat(Security_Counter.TodayDate());

					if(!ob.getaccno().isEmpty() && ob.getaccno()!=null)
					{ 		

						ob=ss.getReserveCheck(ob);

					}else{
						ob.setauthor("Error Occured");
					}

					if(ob.getauthor().equals("DONE"))
					{
						request.setAttribute("bean",ob);
						indexPage = "/Simples/ReserveDone.jsp";		    		
					}
					else{
						request.setAttribute("bean",ob);

						String accno=request.getParameter("raccno").toString();
						String doc=request.getParameter("rdtype").toString();							
						AccountArrylist=ss.getIssueDetails(accno,doc);				
						session.setAttribute("IssueArrylist",AccountArrylist);

						indexPage = "/Simples/ReserveView.jsp";
					}	    	

				}
				else{

					String accno=request.getParameter("raccno").toString();
					String doc=request.getParameter("rdtype").toString();							
					AccountArrylist=ss.getIssueDetails(accno,doc);				
					session.setAttribute("IssueArrylist",AccountArrylist);

					indexPage = "/Simples/ReserveView.jsp?check=usernotfound";
				}
				dispatch(request, response, indexPage);

			}


			if (flag.equals("register"))
			{

				log4jLogger.info("start===========register====="+request.getParameter("userid"));
				SQL_Query="and member_code =";
				SQL_Query=SQL_Query+"'"+request.getParameter("userid")+"'";			

				ob=new AccountBean();			
				String usercode=request.getParameter("userid");
				String purpose=request.getParameter("purpose");

				if(usercode!=null && !usercode.isEmpty()) {			
					ob=ss.getRegisterEntry(request.getParameter("userid"),purpose,branchID);			
				}
				request.setAttribute("bean",ob);

				 rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
				ob.setBranch(rk);

				AccountArrylist=ss.getRegisterLoad(ob);				
				session.setAttribute("AccountArrylist",AccountArrylist);		

				indexPage = "/Account/gateresister.jsp";
				dispatch(request, response, indexPage);

			}


			if(flag.equals("Gate_Report"))
			{
				String inTimeHour="",inTimeMinutes="";
				String outTimeHour="",OutTimeMinutes="";


				log4jLogger.info("Inside Gate Register Date Wise Report");

				java.sql.Connection con = null;

				try{
					con=SessionHibernateUtil.getSession().connection();

					/*if(branchID == 2 )
			{
				sql = " and branch_code=" + branchID + " ";
			}else if(branchID > 2 ) {
				sql = " and Dept_BranchCode=" + branchID + " ";
			}*/

					//sql = " and branch_code=" + branchID + " ";

					Map<String, Object> parameters = new HashMap<String, Object>();	
					parameters.put("logo",base64Logo);

					String namedQuery=ReportQueryUtill.GateRegQuery_Date;

					StringBuffer sb = new StringBuffer();
					String filterQuery=null;


					String gate_from=Security.TextDate_member(request.getParameter("gate_from"));
					String gate_to=Security.TextDate_member(request.getParameter("gate_to"));

					inTimeHour=request.getParameter("inTimeHour");
					inTimeMinutes=request.getParameter("inTimeMinutes");

					outTimeHour=request.getParameter("outTimeHour");
					OutTimeMinutes=request.getParameter("outTimeMinutes");

					filterQuery = " and in_time between '"+inTimeHour+":"+inTimeMinutes+":00' and '"+outTimeHour+":"+OutTimeMinutes+":00' ";

					filterQuery=filterQuery+" and return_time between '"+ gate_from +"' and '"+ gate_to +"'"+ sql;

					if (!request.getParameter("Code").equalsIgnoreCase("ALL") && !request.getParameter("Code").isEmpty()){
						filterQuery = filterQuery+" and member_code='"+request.getParameter("Code")+"'";
					}
					if (!request.getParameter("desig").equalsIgnoreCase("ALL") && !request.getParameter("desig").isEmpty()){
						filterQuery = filterQuery+" and designation_code='"+request.getParameter("desig")+"'";
					}
					if (!request.getParameter("dept").equalsIgnoreCase("ALL") && !request.getParameter("dept").isEmpty()){
						filterQuery = filterQuery+" and dept_code='"+request.getParameter("dept")+"'";		
					}
					if (!request.getParameter("group").equalsIgnoreCase("ALL") && !request.getParameter("group").isEmpty()){
						filterQuery = filterQuery+" and group_code='"+request.getParameter("group")+"'";
					}
					if (!request.getParameter("course").equalsIgnoreCase("ALL") && !request.getParameter("course").isEmpty()){
						filterQuery = filterQuery+" and course_code='"+request.getParameter("course")+"'";
					}
					/*if (!request.getParameter("branch").equalsIgnoreCase("ALL") && !request.getParameter("branch").isEmpty()){

			}*/
					if (!request.getParameter("Year").equalsIgnoreCase("ALL") && !request.getParameter("Year").isEmpty()){
						filterQuery = filterQuery+" and cyear='"+request.getParameter("Year")+"'";
					}
					filterQuery = filterQuery+" and branchcode='"+branchID+"'";// for branch Library Login
					log4jLogger.info(":::::::::::::::::::"+filterQuery);
					sb.append(namedQuery);
					sb.append(" " + filterQuery);
					if(printType.equals("pdf"))
					{
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
						parameters.put("ReportTitle",ReportQueryUtill.GateReg_Title);
						parameters.put("All_Query",sb.toString());				
						log4jLogger.info("namedQuery: " + sb);	

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Gate_Register.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

						JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.GateReg_Title+".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport(); 


					}
					else
					{
						List prepareSearchCriteriaLst = importExportXMLService.getGateRegisterReportList(sb.toString());

						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

						//excelTitleMap.put("fromAccNo", "1");
						//excelTitleMap.put("toAccNo", "1");
						//excelTitleMap.put("documentType", "1");

						Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
						GateRegisterExportRecord recordProcessor = new GateRegisterExportRecord(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(studentDataItr, recordProcessor,response.getOutputStream());
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
				String inTimeHour="",inTimeMinutes="";
				String outTimeHour="",OutTimeMinutes="";


				log4jLogger.info("Inside Gate Register Statistics Wise Report");

				java.sql.Connection con = null;

				try{
					con=SessionHibernateUtil.getSession().connection();

					/*if(branchID == 2 )
			{
				sql = " and branch_code=" + branchID + " ";
			}else if(branchID > 2 ) {
				sql = " and Dept_BranchCode=" + branchID + " ";
			}*/

					//sql = " and branch_code=" + branchID + " ";

					Map<String, Object> parameters = new HashMap<String, Object>();		
					parameters.put("logo",base64Logo);
					String namedQuery=ReportQueryUtill.GateRegQuery_Statistics;

					StringBuffer sb = new StringBuffer();
					String filterQuery=null;


					String gate_from=Security.TextDate_member(request.getParameter("gate_from"));
					String gate_to=Security.TextDate_member(request.getParameter("gate_to"));

					inTimeHour=request.getParameter("inTimeHour");
					inTimeMinutes=request.getParameter("inTimeMinutes");

					outTimeHour=request.getParameter("outTimeHour");
					OutTimeMinutes=request.getParameter("outTimeMinutes");

					filterQuery = " and in_time between '"+inTimeHour+":"+inTimeMinutes+":00' and '"+outTimeHour+":"+OutTimeMinutes+":00' ";

					filterQuery=filterQuery+" and return_time between '"+ gate_from +"' and '"+ gate_to +"'"+ sql;

					if (!request.getParameter("Code").equalsIgnoreCase("ALL") && !request.getParameter("Code").isEmpty()){
						filterQuery = filterQuery+" and member_code='"+request.getParameter("Code")+"'";
					}
					if (!request.getParameter("desig").equalsIgnoreCase("ALL") && !request.getParameter("desig").isEmpty()){
						filterQuery = filterQuery+" and designation_code='"+request.getParameter("desig")+"'";
					}
					if (!request.getParameter("dept").equalsIgnoreCase("ALL") && !request.getParameter("dept").isEmpty()){
						filterQuery = filterQuery+" and dept_code='"+request.getParameter("dept")+"'";		
					}
					if (!request.getParameter("group").equalsIgnoreCase("ALL") && !request.getParameter("group").isEmpty()){
						filterQuery = filterQuery+" and group_code='"+request.getParameter("group")+"'";
					}
					if (!request.getParameter("course").equalsIgnoreCase("ALL") && !request.getParameter("course").isEmpty()){
						filterQuery = filterQuery+" and course_code='"+request.getParameter("course")+"'";
					}
					/*if (!request.getParameter("branch").equalsIgnoreCase("ALL") && !request.getParameter("branch").isEmpty()){
				filterQuery = filterQuery+" and branchcode='"+request.getParameter("branch")+"'";// for branch Library Login
			}*/
					if (!request.getParameter("Year").equalsIgnoreCase("ALL") && !request.getParameter("Year").isEmpty()){
						filterQuery = filterQuery+" and cyear='"+request.getParameter("Year")+"'";
					}

					filterQuery = filterQuery+" and branchcode='"+branchID+"'";

					filterQuery = filterQuery+" GROUP BY return_time ORDER BY return_time ASC";
					log4jLogger.info(":::::::::::::::::::"+filterQuery);
					sb.append(namedQuery);
					sb.append(" " + filterQuery);
					if(printType.equals("pdf"))
					{
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
						parameters.put("ReportTitle",ReportQueryUtill.GateReg_Title);
						parameters.put("All_Query",sb.toString());				
						log4jLogger.info("namedQuery: " + sb);	

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Gate_Register_statistics.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

						JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.GateReg_Title+".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport(); 


					}
					else
					{
						List prepareSearchCriteriaLst = importExportXMLService.getGateRegisterReportStatisticsList(sb.toString());

						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

						//excelTitleMap.put("fromAccNo", "1");
						//excelTitleMap.put("toAccNo", "1");
						//excelTitleMap.put("documentType", "1");

						Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
						GateRegisterStatisticsExport recordProcessor = new GateRegisterStatisticsExport(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(studentDataItr, recordProcessor,response.getOutputStream());
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








			if(flag.equals("All_Logout"))
			{
				log4jLogger.info("start===========All_Logout=====");
				int count;
				count = ss.getRegisterAllLogout(branchID);

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
				ob.setBranch(branchID);
				String retstr=ss.getChangePwd(ob);


				request.setAttribute("strobj",retstr);
				indexPage = "/Account/changepwd.jsp?check=cpwd";
				dispatch(request, response, indexPage);

			}
			if (flag.equals("save"))
			{
				log4jLogger.info("start===========save=====");
				ob = new AccountBean();
				ob=ss.getCheckAccount(request.getParameter("uid").trim(),request.getParameter("pwd").trim(),branchID);

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
				ob=ss.getCheckAccount((String.valueOf(session.getAttribute("OpacID")).trim()),(String.valueOf(session.getAttribute("OpacPWD")).trim()),branchID);

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



			//shek

			if(flag.equalsIgnoreCase("gateRegisterReportLoad")){

				MemberTransRefBean beanObject = new MemberTransRefBean();
				beanObject.setBranchCode(rk);

				List BranchWiseDesigList = ss1.getDesigList(beanObject);				
				request.setAttribute("DesigSearchList", BranchWiseDesigList);
				
				List BranchWiseDepartmentList = ss1.getDepartmentList(beanObject);				
				request.setAttribute("DepartmentSearchList", BranchWiseDepartmentList);
				
				List BranchWiseGroupList = ss1.getGroupList(beanObject);
				request.setAttribute("distinctBranchWiseGroupSearchList", BranchWiseGroupList);
				
				List BranchWiseCourseList = ss1.getCourseList(beanObject);
				request.setAttribute("distinctBranchWiseCourseSearchList", BranchWiseCourseList);
				
				List<?> branchWiseList = ss1.getBranchList(beanObject);				
				request.setAttribute("distinctBranchSearchList", branchWiseList);
				indexPage = "/Gate_Register/index.jsp";
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
