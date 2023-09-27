package Lib.Auto.Report;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
import Common.businessutil.reports.ReportService;
import Common.businessutil.search.SearchService;
import Lib.Auto.Branch.BranchBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;


public class ReportAll extends HttpServlet implements ReportQueryUtill{

	private static Logger log4jLogger = Logger.getLogger(ReportAll.class);


	private static final long serialVersionUID = 1L;
	String txtreporttype="",txtaccessno="",txtmembercode="",txtdepartment="",txtfdate="",txttdate="",txtgroup="",txtbranch="";
	String order1="",order2="",order3="",str="",strsql="",order="",strsqlq="",mcodefrom="",mcodeto="";
	String flag=null,flag1=null,curIssue="",deptname="",Chart_GroupQuery="",groupname="",staff="",Reptfrom="",txtcourse="",txtdoctype="",report_type="",coursename="", count_book="", fine_amount="",gettodayreport="" ;
	int sno=0, total=0;
	float fine=0,subcount=0;
	String protocol="";
	java.sql.Connection con=null;
	String SQL_Query = "";
	String indexPage = null;
	List ReportArrylist;


	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		performTask(request, response);
	}

	public void performTask(
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


			reportbean ob=new reportbean();
			ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();
			SearchService ss1 = BusinessServiceFactory.INSTANCE.getSearchService();

			log4jLogger.info("======================== Inside from Counter Reports ========================");

			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();

			flag = request.getParameter("flagExcel");	
			curIssue = request.getParameter("curIssue");
			StringBuffer excelsb = new StringBuffer();

			txtreporttype =Security.getParam(request,"reporttype");
			txtaccessno =Security.getParam(request,"txtaccessno");
			txtmembercode =Security.getParam(request,"txtmembercode");
			txtdoctype=Security.getParam(request,"txtdoctype");
			txtfdate =Security.getParam(request,"txtfdate");
			txttdate =Security.getParam(request,"txttdate");
			report_type=Security.getParam(request,"report_type");
			order1=Security.getParam(request,"order1");
			order2=Security.getParam(request,"order2");
			order3=Security.getParam(request,"order3");
			mcodefrom=Security.getParam(request,"mcodefrom");
			mcodeto=Security.getParam(request,"mcodeto");
			txtbranch=Security.getParam(request,"txtBranch");
			gettodayreport = Security.getParam(request,"gettodayreport");

			strsql = "";  

			deptname = Security.getParam(request,"Dname");
			groupname = Security.getParam(request,"Gname");

			strsql = "";  

			int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
			
			
			
			LoginUserService ls = LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(rk);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());
			//        	
			//        	if(branchID == 0){
			//        		strsql = " ";
			//        	}
			//        	
			//        	else{
			//        	
			strsql = strsql + " and branch_code=" + rk + " ";	
			//        	
			//        	}
			//        	if(txtreporttype.equals("Fine"))
			//        	{
			//        		
			//        		strsql = strsql + " and branch_code=" + branchID + " ";	
			//        		
			//        	}

			//        		
			//        		if(branchID == 2){
			//        			strsql = strsql + " and branch_code=" + branchID + " ";		//pending	
			//    			}else if(branchID > 2 ) {
			//    				strsql = strsql + " and Dept_BranchCode="+branchID;	 
			//    			}  
			//        	}
			//        	else {
			//        					
			//    			if(branchID==0){
			//    				strsql = "";				
			//    			}else  {
			//    				strsql = strsql + " and Branch_Code="+branchID;	 
			//    			}  
			//        	}     	

			if (!mcodefrom.equals("") && !mcodeto.equals(""))
			{
				strsql = strsql + " and member_code between  '" +mcodefrom+"' AND '" +mcodeto+"' ";
			}
			if (!deptname.equals(""))
			{
				strsql = strsql + " and dept_name = '" +deptname+"'";
			}
			if (!groupname.equals(""))
			{
				strsql = strsql + " and group_name = '" +groupname+"'";
			}     

			if (!txtmembercode.equals(""))
			{
				strsql = strsql + " and Member_Code like '" +txtmembercode+"%'";
			}
			if (!txtaccessno.equals(""))
			{
				strsql = strsql + " and access_no = '" +txtaccessno+"'";
			}
			if (!txtdoctype.equals("") && (!txtdoctype.equals("ALL")) && (!txtdoctype.equals("NO")) && (!txtreporttype.equals("Fine")))
			{
				strsql = strsql + " and doc_type = '" +txtdoctype+"'";
			}     

			System.out.println("-----------Branch code-----"+txtbranch);

			if (!txtbranch.equals("NO") && (!txtbranch.equals(""))) 
			{
				strsql = strsql + " and branch_code ='" +txtbranch+ "'";
			}


			// For Date for Issue , Return , Renew 

			if ((!txttdate.equals("")) &&  (!txtfdate.equals("")))
			{
				String Date_From=getdate1(request.getParameter("txtfdate"));
				String Date_To=getdate1(request.getParameter("txttdate"));
				if (txtreporttype.equals("Issue"))
				{
					strsql = strsql + " and Issue_Date between '"+Date_From+"' and '" +Date_To+"' AND issue_type!='RENEW' ";
				}

				if (txtreporttype.equals("Return"))
				{
					strsql = strsql + " and Return_Date between '"+Date_From+"' and '" +Date_To+"'";
				}   

				if (txtreporttype.equals("Renewal"))
				{
					strsql = strsql + " and Return_Date between '"+Date_From+"' and '" +Date_To+"'";
				}  

				if (txtreporttype.equals("Reserve"))
				{
					strsql = strsql + " and res_Date between '"+Date_From+"' and '" +Date_To+"'";
				} 

				if (txtreporttype.equals("Duereminder"))
				{
					strsql = strsql + " and Due_Date between '"+Date_From+"' and '" +Date_To+"'";
				}
				if (txtreporttype.equals("Fine"))
				{
					strsql = strsql + " and Trans_Date between '"+Date_From+"' and '" +Date_To+"'";
				}
				
			}

			order="";

			if(!order1.equals("NO"))
			{
				order = order+order1;        		
			}

			if(!order2.equals("NO" ) && (!order1.equals("NO"))) 
			{
				order = order+","+order2;        		
			}
			if(!order3.equals("NO") && (!order1.equals("NO"))) 
			{
				order = order+","+order3;        		
			}




			if(request.getParameter("flag")!=null)  {
				if(request.getParameter("flag").equals("load")){
					log4jLogger.info("start===========Branch List load=====");
					List BranchArrylist = new ArrayList();
					BranchArrylist=ss1.getLoadBranchList();
					request.setAttribute("BranchList", BranchArrylist);
					System.out.println("SEEEEE"+BranchArrylist.size());
					indexPage = "/CounterReport/index.jsp";
					dispatch(request, response, indexPage);
				}
			}



			//ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();		
			//con=ss.getDBConnect();	

			con=SessionHibernateUtil.getSession().connection();




			if(gettodayreport.equals("todayIssue")){
				log4jLogger.info("start===========todayIssueList=====");
				ob=new reportbean();
				SQL_Query=" AND branch_code='"+rk+"'";

				SQL_Query=SQL_Query+" and issue_type='ISSUE' and  issue_date=curdate() order by title asc";				

				ReportArrylist=ss.gettodayIssueListDetails(SQL_Query);		

				session.setAttribute("IssueReportArrylist",ReportArrylist);
				indexPage = "/Home/todayissuedetails.jsp";
				dispatch(request, response, indexPage);

			}








			if(gettodayreport.equals("todayReturn")){
				log4jLogger.info("start===========todayReturnList=====");
				ob=new reportbean();
				SQL_Query=" AND branch_code='"+rk+"'";

				SQL_Query=SQL_Query+"  and return_date=curdate() and issue_type='RETURN' order by title asc";				

				ReportArrylist=ss.gettodayReturnListDetails(SQL_Query);		

				session.setAttribute("ReturnReportArrylist",ReportArrylist);
				indexPage = "/Home/todayreturndetails.jsp";
				dispatch(request, response, indexPage);

			}




			if(gettodayreport.equals("todayRenewal")){
				log4jLogger.info("start===========todayReturnList=====");
				ob=new reportbean();
				SQL_Query=" AND a.branch_code='"+rk+"'";

				SQL_Query=SQL_Query+"  and i.return_date=curdate() and i.issue_type='RENEW' order by a.title asc";				

				ReportArrylist=ss.gettodayRenewalListDetails(SQL_Query);		

				session.setAttribute("RenewalReportArrylist",ReportArrylist);
				indexPage = "/Home/todayrenewaldetails.jsp";
				dispatch(request, response, indexPage);

			}



			if(gettodayreport.equals("todayamountdisplay")){
				log4jLogger.info("start===========todayamountdisplay=====");
				ob=new reportbean();
				SQL_Query=" AND branch_code='"+rk+"'";

				SQL_Query=SQL_Query+"  and return_date=curdate() and fine_amount<>'0.00'  order by title asc";

				ReportArrylist=ss.gettodayTransAmountDetails(SQL_Query);		

				session.setAttribute("TransAmountListReportArrylist",ReportArrylist);
				indexPage = "/Home/todayreturnfinedetails.jsp";
				dispatch(request, response, indexPage);

			}




			if(gettodayreport.equals("todaypaidamountdisplay")){
				log4jLogger.info("start===========todaypaidamountdisplay=====");
				ob=new reportbean();
				SQL_Query=" AND branch_code='"+rk+"'";

				SQL_Query=SQL_Query+"  and payment_date=curdate() order by bill_no";

				ReportArrylist=ss.gettodayPaidDetails(SQL_Query);		

				session.setAttribute("todayPaidListReportArrylist",ReportArrylist);
				indexPage = "/Home/todayreturnfinepaiddetails.jsp";
				dispatch(request, response, indexPage);

			}



			if(txtreporttype.equals("Issue"))
			{

				Map parameters = new HashMap();
				parameters.put("logo",base64Logo);
				String namedQuery;
				if(report_type.equalsIgnoreCase("breakup"))
				{
					namedQuery=ReportQueryUtill.Breakup_Issue_Report;
				}
				else if(curIssue.equalsIgnoreCase("YES"))
				{
					namedQuery=ReportQueryUtill.Query_CurrentIssue_Report;
				}else  {
					namedQuery=ReportQueryUtill.Query_Issue_Report;
					if(!flag.equalsIgnoreCase("chart"))
						namedQuery=ReportQueryUtill.Query_Issue_Report;
					else
					{
						namedQuery = ReportQueryUtill.Query_Issue_Chart;
						Chart_GroupQuery = "GROUP BY YEAR,MONTH(issue_date) ORDER BY MONTH(issue_date),YEAR";
					}
				}

				StringBuffer sb = new StringBuffer();
				String filterQuery=null;

				sb.append(namedQuery);

				if (strsql != null && (!strsql.equals("")))
				{    					
					sb.append(" " + strsql);
				}

				if(report_type.equalsIgnoreCase("breakup"))
				{
					sb.append(" "+"GROUP BY issue_date");
				}
				if(!flag.equalsIgnoreCase("chart"))
				{
					if(order !=null && (!order.equals("")))
					{
						sb.append(" "+"ORDER BY"+" " + order);

					}  
				}
				else
					sb.append(" "+Chart_GroupQuery);

				if (!flag.equalsIgnoreCase("ExcelReport"))
				{
					parameters.put("logo", base64Logo);
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

					if(!flag.equalsIgnoreCase("chart"))
					{
						if(report_type.equalsIgnoreCase("breakup")){
							parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_Breakup_IssueTitle);
						}else{
							parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_IssueTitle); 
						}
					}
					else
				    parameters.put("ReportTitle",ReportQueryUtill.Issue_Report_Chart);
					parameters.put("All_Query",sb.toString());
					
					log4jLogger.info("SQL QUERY: " + sb);		

					InputStream inputStream1;
					if(report_type.equalsIgnoreCase("breakup"))
					{
						inputStream1 = getServletContext().getResourceAsStream("/Report/counter_issue_breakup.jasper");
					}
					else if(curIssue.equalsIgnoreCase("YES"))
					{
						inputStream1 = getServletContext().getResourceAsStream("/Report/Current_Issue_Report.jasper");
					}else   
					{
						if(!flag.equalsIgnoreCase("chart"))
						{
							inputStream1 = getServletContext().getResourceAsStream("/Report/Issue_Report.jasper");
						}
						else
							inputStream1 = getServletContext().getResourceAsStream("/Report/Issue_BarChart.jasper");
					}

					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	

					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
					log4jLogger.info("------------------- Counter Reports -------------------");				
					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					if(report_type.equalsIgnoreCase("breakup"))
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_Breakup_IssueTitle+".pdf");
					else
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_IssueTitle+".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport(); 

				}else	{
					excelsb.append(sb.toString());
				}
			}


			if(txtreporttype.equals("Return"))
			{

				log4jLogger.info("Inside Return Counter Report");
				Map parameters = new HashMap(); 
				parameters.put("logo", base64Logo);
				String namedQuery;
				if(report_type.equalsIgnoreCase("breakup"))
					namedQuery=ReportQueryUtill.Breakup_Return_Report;
				else
				{
					if(!flag.equalsIgnoreCase("chart"))
						namedQuery=ReportQueryUtill.Query_Return_Report;
					else
					{
						namedQuery = ReportQueryUtill.Query_Return_Chart;
						Chart_GroupQuery = "GROUP BY YEAR,MONTH(return_date) ORDER BY MONTH(return_date),YEAR";
					}

				}
				StringBuffer sb = new StringBuffer();
				String filterQuery=null;

				sb.append(namedQuery);

				if (strsql != null && (!strsql.equals("")))
				{    					
					sb.append(" " + strsql);
				}
				if(report_type.equalsIgnoreCase("breakup"))
				{
					sb.append(" "+"GROUP BY return_date");
				}
				if(!flag.equalsIgnoreCase("chart"))
				{
					if(order !=null && (!order.equals("")))
					{
						sb.append(" "+"ORDER BY"+" " + order);

					}     
				}
				else
					sb.append(" "+Chart_GroupQuery);
				if (!flag.equalsIgnoreCase("ExcelReport"))
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

					if(!flag.equalsIgnoreCase("chart"))
					{
						if(report_type.equalsIgnoreCase("breakup"))
							parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_Breakup_ReturnTitle);
						else
							parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_ReturnTitle);   
					}
					else
					parameters.put("ReportTitle",ReportQueryUtill.Return_Report_Chart);
					parameters.put("All_Query",sb.toString());				
					log4jLogger.info("SQL QUERY FOR RETURN: " + sb);
					InputStream inputStream1;
					if(report_type.equalsIgnoreCase("breakup"))
						inputStream1 = getServletContext().getResourceAsStream("/Report/counter_return_breakup.jasper");
					else
					{
						if(!flag.equalsIgnoreCase("chart"))
						{
							inputStream1 = getServletContext().getResourceAsStream("/Report/Return_Report.jasper");
						}
						else
							inputStream1 = getServletContext().getResourceAsStream("/Report/Issue_BarChart.jasper");
					}
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					if(report_type.equalsIgnoreCase("breakup"))
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_Breakup_ReturnTitle+".pdf");
					else
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_ReturnTitle+".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport(); 

				}else	{
					excelsb.append(sb.toString());
				}        	
			}

			if(txtreporttype.equals("Renewal"))
			{

				log4jLogger.info("Inside Renew Counter Report");
				Map parameters = new HashMap(); 
				parameters.put("logo", base64Logo);
				String namedQuery;
				if(report_type.equalsIgnoreCase("breakup"))
					namedQuery=ReportQueryUtill.Breakup_Renewal_Report;
				else
					namedQuery=ReportQueryUtill.Query_Renew_Report;
				StringBuffer sb = new StringBuffer();
				String filterQuery=null;

				sb.append(namedQuery);

				if (strsql != null && (!strsql.equals("")))
				{    					
					sb.append(" " + strsql);
				}
				if(report_type.equalsIgnoreCase("breakup"))
				{
					sb.append(" "+"GROUP BY issue_date");
				}
				if(order !=null && (!order.equals("")))
				{
					sb.append(" "+"ORDER BY"+" " + order);

				}        			


				if (!flag.equalsIgnoreCase("ExcelReport"))
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

					if(report_type.equalsIgnoreCase("breakup"))
						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_Breakup_RenewTitle);
					else
						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_RenewTitle);   				
					parameters.put("All_Query",sb.toString());				
					log4jLogger.info("SQL QUERY FOR RETURN: " + sb);	
					InputStream inputStream1;
					if(report_type.equalsIgnoreCase("breakup"))
						inputStream1 = getServletContext().getResourceAsStream("/Report/counter_renewal_breakup.jasper");
					else
						inputStream1 = getServletContext().getResourceAsStream("/Report/Renew_Report.jasper");
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					if(report_type.equalsIgnoreCase("breakup"))
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_Breakup_RenewTitle+".pdf");
					else
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_RenewTitle+".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport(); 

				}else	{
					excelsb.append(sb.toString());
				}
			}

			if(txtreporttype.equals("Reserve"))
			{

				log4jLogger.info("Inside Reserve Counter Report");
				Map parameters = new HashMap();
				parameters.put("logo", base64Logo);
				String namedQuery;
				if(report_type.equalsIgnoreCase("breakup"))
					namedQuery=ReportQueryUtill.Breakup_Reserve_Report;
				else
					namedQuery=ReportQueryUtill.Query_Reserve_Report;
				StringBuffer sb = new StringBuffer();
				String filterQuery=null;

				sb.append(namedQuery);

				if (strsql != null && (!strsql.equals("")))
				{    					
					sb.append(" " + strsql);
				}
				if(report_type.equalsIgnoreCase("breakup"))
				{
					sb.append(" "+"GROUP BY res_date");
				}
				if(order !=null && (!order.equals("")))
				{
					sb.append(" "+"ORDER BY"+" " + order);

				}        			
				if (!flag.equalsIgnoreCase("ExcelReport"))
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

					if(report_type.equalsIgnoreCase("breakup"))
						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_Breakup_ReserveTitle);  
					else
						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_ReserveTitle);   				
					parameters.put("All_Query",sb.toString());				
					log4jLogger.info("SQL QUERY FOR RETURN: " + sb);	
					InputStream inputStream1;
					if(report_type.equalsIgnoreCase("breakup"))
						inputStream1 = getServletContext().getResourceAsStream("/Report/counter_reserve_breakup.jasper");
					else
						inputStream1 = getServletContext().getResourceAsStream("/Report/Reserve_Report.jasper");
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					if(report_type.equalsIgnoreCase("breakup"))
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_Breakup_ReserveTitle+".pdf");
					else
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_ReserveTitle+".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport(); 
				}
				else	{
					excelsb.append(sb.toString());
				}
			}

			if(txtreporttype.equals("Resreminder"))
			{

				log4jLogger.info("Inside Resreminder Counter Report");
				Map parameters = new HashMap(); 
				parameters.put("logo", base64Logo);
				String namedQuery=ReportQueryUtill.Query_ReserveReminder_Report;
				StringBuffer sb = new StringBuffer();
				String filterQuery=null;

				sb.append(namedQuery);  // For Reservation Reminder Only

				if (strsql != null && (!strsql.equals("")))
				{    					
					sb.append(" " + strsql);
				}
				if(order !=null && (!order.equals("")))
				{
					sb.append(" "+"ORDER BY"+" " + order);        				
				}        			

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

				parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_ReserveReminderTitle);   				
				parameters.put("All_Query",sb.toString());				
				log4jLogger.info("SQL QUERY FOR RETURN: " + sb);	

				InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/ReserveReminder_Report.jasper");
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

				JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

				JRAbstractExporter exporterPDF = new JRPdfExporter();
				exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_ReserveReminderTitle+".pdf");
				response.setContentType("application/pdf");
				exporterPDF.exportReport(); 

			}

			if(txtreporttype.equals("Duereminder"))
			{

				log4jLogger.info("Inside Duereminder Counter Report");
				Map parameters = new HashMap(); 
				parameters.put("logo", base64Logo);
				String namedQuery;
				if(report_type.equalsIgnoreCase("breakup"))
					namedQuery=ReportQueryUtill.Breakup_DueReminder_Report;
				else
					namedQuery=ReportQueryUtill.Query_DueReminder_Report;
				StringBuffer sb = new StringBuffer();
				String filterQuery=null;	

				sb.append(namedQuery); 

				if (strsql != null && (!strsql.equals("")))
				{        				
					sb.append(" " + strsql);
				}
				if(report_type.equalsIgnoreCase("breakup"))
				{
					sb.append(" "+"GROUP BY due_date");
				}
				if(order !=null && (!order.equals("")))
				{
					sb.append(" "+"ORDER BY"+" " + order);
				}        			
				if (!flag.equalsIgnoreCase("ExcelReport"))
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

					if(report_type.equalsIgnoreCase("breakup"))
						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_Breakup_DueReminderTitle);
					else
						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_DueReminderTitle);   				
					parameters.put("All_Query",sb.toString());				
					log4jLogger.info("SQL QUERY FOR RETURN: " + sb);	
					InputStream inputStream1;
					if(report_type.equalsIgnoreCase("breakup"))
						inputStream1 = getServletContext().getResourceAsStream("/Report/counter_duereminder_breakup.jasper");
					else
						inputStream1 = getServletContext().getResourceAsStream("/Report/DueReminder_Report.jasper");
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					if(report_type.equalsIgnoreCase("breakup"))
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_Breakup_DueReminderTitle+".pdf");
					else
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_DueReminderTitle+".pdf");
					response.setContentType("application/pdf");
					exporterPDF.exportReport(); 
				}
				else	{
					excelsb.append(sb.toString());
				}
			}

			if(txtreporttype.equals("Fine"))
			{
				if (!flag.equalsIgnoreCase("ExcelReport"))
				{
					if(report_type.equals("cumulative"))
					{
						log4jLogger.info("Inside Fine in Cumulative --->  Counter Report");
						Map parameters = new HashMap();
						parameters.put("logo", base64Logo);
						String namedQuery=ReportQueryUtill.Query_FineCumulative_Report;
						StringBuffer sb = new StringBuffer();
						String filterQuery=null;	

						sb.append(namedQuery);

						if (strsql != null && (!strsql.equals("")))
						{        				 
							sb.append(" " + strsql);
						}
						if(order !=null && (!order.equals("")))
						{
							sb.append(" "+"ORDER BY"+" " + order);

						}        			

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

						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_FineTitle);   				
						parameters.put("All_Query",sb.toString());				
						log4jLogger.info("SQL QUERY FOR RETURN: " + sb);	

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Trans_Cumulative_Report.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

						JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_FineTitle+".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport(); 

					}
					if(report_type.equals("listing"))
					{
						log4jLogger.info("Inside Fine in Listing --->  Counter Report");
						Map parameters = new HashMap();
						parameters.put("logo", base64Logo);
						String namedQuery=ReportQueryUtill.Query_BranchFineListing_Report;
						StringBuffer sb = new StringBuffer();



						sb.append(namedQuery); 

						if (strsql != null && (!strsql.equals("")))
						{        				
							sb.append(" " + strsql);
						}



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

						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_FineTitle);   				
						parameters.put("All_Query",sb.toString());				
						log4jLogger.info("SQL QUERY FOR RETURN: " + sb);	

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Trans_Listing_Report.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

						JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_FineTitle+".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport(); 

					}
					if(report_type.equals("breakup"))
					{
						log4jLogger.info("Inside Fine in BreakUp --->  Counter Report");
						Map parameters = new HashMap();
						parameters.put("logo", base64Logo);
						String namedQuery=ReportQueryUtill.Query_FineBreakup_Report;
						StringBuffer sb = new StringBuffer();
						String filterQuery=null;	

						sb.append(namedQuery); 

						if (strsql != null && (!strsql.equals("")))
						{        				
							sb.append(" " + strsql);
							sb.append(" " + "GROUP BY member_code");
						}



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

						parameters.put("ReportTitle",ReportQueryUtill.Counter_Reports_FineTitle);   				
						parameters.put("All_Query",sb.toString());				
						log4jLogger.info("SQL QUERY FOR RETURN: " + sb);	

						InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Trans_Breakup_Report.jasper");
						JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);		

						JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

						JRAbstractExporter exporterPDF = new JRPdfExporter();
						exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
						exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
						response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Counter_Reports_FineTitle+".pdf");
						response.setContentType("application/pdf");
						exporterPDF.exportReport(); 

					}

				}
				else
				{

					Map parameters = new HashMap(); 
					parameters.put("logo", base64Logo);
					String namedQuery=ReportQueryUtill.Query_FineBreakup_Report_Excel;
					StringBuffer sb = new StringBuffer();
					String filterQuery=null;	

					sb.append(namedQuery); 

					if (strsql != null && (!strsql.equals("")))
					{        				
						sb.append(" " + strsql);
						sb.append(" " + "GROUP BY trans_date");
					}
					excelsb.append(sb.toString());
					log4jLogger.info("Inside Fine in BreakUp For Excel--->  Counter Report"+excelsb.toString());
				}
			}


			if (flag.equalsIgnoreCase("ExcelReport"))
			{
				//        	      	 For Excel Report.
				log4jLogger.info("SQL QUERY FOR EXCEL: " + excelsb);
				if(report_type.equalsIgnoreCase("breakup"))
				{

					List prepareSearchCriteriaLst = importExportXMLService.getCounterBreakupReportList(excelsb.toString(),txtreporttype);

					Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

					if(txtreporttype.equals("Issue"))
					{   
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_Breakup_IssueTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);

					}else if(txtreporttype.equals("Return"))
					{
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_Breakup_ReturnTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);	

					}else if(txtreporttype.equals("Renewal"))
					{	
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_Breakup_RenewTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);					
					}
					else if(txtreporttype.equals("Reserve"))
					{	
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_Breakup_ReserveTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);					
					}
					else if(txtreporttype.equals("Duereminder"))
					{	
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_Breakup_DueReminderTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);					
					}
					else if(txtreporttype.equals("Fine"))
					{	
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Breakup_Reports_FineTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);					
					}

					Iterator studentDataItr = prepareSearchCriteriaLst.iterator();				
					ReportAllBreakup recordProcessor = new ReportAllBreakup(excelTitleMap);

					response.setContentType("text/csv");
					response.setHeader("Content-Disposition", "attachment; filename=" + recordProcessor.getExcelFileName());

					csvImportExportService.Export(studentDataItr, recordProcessor, response.getOutputStream());        		

				}
				else
				{
					List prepareSearchCriteriaLst = importExportXMLService.getCounterReportList(excelsb.toString(),txtreporttype);

					Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();

					if(txtreporttype.equals("Issue"))
					{   
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_IssueTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);

					}else if(txtreporttype.equals("Return"))
					{
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_ReturnTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);	

					}else if(txtreporttype.equals("Renewal"))
					{	
						excelTitleMap.put("rptFlag", txtreporttype);
						excelTitleMap.put("rptTitle", ReportQueryUtill.Counter_Reports_RenewTitle);
						excelTitleMap.put("fromAccNo", txtfdate);
						excelTitleMap.put("toAccNo", txttdate);
						excelTitleMap.put("documentType", txtdoctype);					
					}

					Iterator studentDataItr = prepareSearchCriteriaLst.iterator();				
					ReportAllExportRecord recordProcessor = new ReportAllExportRecord(excelTitleMap);

					response.setContentType("text/csv");
					response.setHeader("Content-Disposition", "attachment; filename=" + recordProcessor.getExcelFileName());

					csvImportExportService.Export(studentDataItr, recordProcessor, response.getOutputStream());        		
				}
			}

			System.out.println("=================="+indexPage);
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



	String getdate1(String datechk) {
		//datechk=datechk.substring(0,datechk.length()-13);
		java.util.StringTokenizer stz_ie=new java.util.StringTokenizer(datechk,"-");
		int bd=Integer.parseInt(stz_ie.nextToken());
		int bm=Integer.parseInt(stz_ie.nextToken());
		int by=Integer.parseInt(stz_ie.nextToken());
		String bissu_m=new Integer(bm).toString();
		if(bissu_m.length()==1)
			bissu_m="0"+bissu_m;
		String bissu_d=new Integer(bd).toString();
		if(bissu_d.length()==1)
			bissu_d="0"+bissu_d;
		String BISSDATE=by+"-"+bissu_m+"-"+bissu_d;
		return BISSDATE;
	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
					throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}


}

