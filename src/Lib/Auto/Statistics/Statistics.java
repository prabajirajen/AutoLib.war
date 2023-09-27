package Lib.Auto.Statistics;
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
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Common.businessutil.reports.ReportService;
import Lib.Auto.Branch.BranchBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;


public class Statistics extends HttpServlet implements Serializable {
	/**
	 * 
	 */
	private static Logger log4jLogger = Logger.getLogger(Statistics.class);	
	private static final long serialVersionUID = 8672487184590862910L;

	String hid="";
	
String report_type="",columnName="";
String groupByQuery=" group by access_no,author_name,title,call_no,dept_name,publisher,bprice ORDER BY dept_name ASC";
String groupByQueryDetail=" group by access_no,author_name,title,call_no,dept_name,publisher,bprice ORDER BY dept_name,access_no ASC";
String indexPage ="";
String sqlQuery="",fromValue="",toValue="";
String orderQuery=" ORDER BY access_no ASC";

			
	singlecodecheck chkcode=new singlecodecheck();
	
	java.sql.Connection con=null;

	
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
			response.setContentType("text/html");
			
			
			log4jLogger.info("Inside Statistics Report ");			
			 ReportService ss=BusinessServiceFactory.INSTANCE.getReportService();
			 
			 con=SessionHibernateUtil.getSession().connection();		
		

	    ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
		ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			 
			 
			 
			 
			 String filterQuery = "";
			String frmAccNo="",toAccNo="";
			String reportName="",reportTitle="",reportType="";
			
			hid=request.getParameter("hid");
			 log4jLogger.info("::::::::flag value:::::::::::"+request.getParameter("hid"));
			 
		
			
			int branchCode=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan			
			
				filterQuery=filterQuery+" and branch_code="+branchCode;
				
				LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
				BranchBean branch = ls.getBranchInfo(branchCode);	
				String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());


			
		
		if(request.getParameter("R1").equals("V1")){
			
			fromValue=Security.TextDate_member(request.getParameter("recfrom"));
			toValue=Security.TextDate_member(request.getParameter("recto"));
			
			filterQuery=filterQuery+" and received_date between '"+Security.TextDate_member(request.getParameter("recfrom"))+"' and '"+Security.TextDate_member(request.getParameter("recto"))+"'";
		}
		if(request.getParameter("R1").equals("V2")){
			
			frmAccNo = request.getParameter("txtfromacc");
			toAccNo = request.getParameter("txttoacc");
			fromValue = request.getParameter("txtfromacc");
			toValue = request.getParameter("txttoacc");
		
			String firstStr = "", secondStr = "";
			int firstNum = 0, secondNum = 0, firstStrCount = 0;	
		
			if (Security.IsNumeric(frmAccNo) && Security.IsNumeric(toAccNo)) {
				firstNum = Integer.parseInt(request.getParameter("firstNum"));
				secondNum = Integer.parseInt(request.getParameter("secondNum"));

				firstStr = request.getParameter("firstStr");
				firstStrCount = firstStr.length() + 1;
				secondStr = request.getParameter("secondStr");
				filterQuery = filterQuery+ " and  access_no REGEXP '^[0-9]+$' and CONVERT(access_no,SIGNED) between '"+ frmAccNo + "' and '" + toAccNo+ "'";
			}else{

				firstNum = Integer.parseInt(request.getParameter("firstNum"));
				secondNum = Integer.parseInt(request.getParameter("secondNum"));

				firstStr = request.getParameter("firstStr");
				firstStrCount = firstStr.length() + 1;
				secondStr = request.getParameter("secondStr");
				filterQuery = filterQuery+" and access_no NOT REGEXP '[^0-9]+$' And Access_No Like '"+firstStr+"%' And CAST(SUBSTRING(Access_No,"+firstStrCount+") AS SIGNED) BETWEEN '"+firstNum+ "' AND '" + secondNum+ "'";	
				
			}

}
		
			if (!request.getParameter("txtdepartment").equalsIgnoreCase("") && !request.getParameter("txtdepartment").isEmpty()){
				filterQuery=filterQuery+" and dept_name='" +chkcode.getParam( request, "txtdepartment")+"'";
			}
			if(!request.getParameter("txtsubject").equals("") && !request.getParameter("txtsubject").isEmpty()){
				filterQuery=filterQuery+" and sub_name ='" +chkcode.getParam( request, "txtsubject")+"'";
			}
			if(!request.getParameter("txtpublisher").equals("") && !request.getParameter("txtpublisher").isEmpty()){
				filterQuery=filterQuery+" and publisher ='" +chkcode.getParam( request, "txtpublisher")+"'";
			}
			if(!request.getParameter("txtsupplier").equals("") && !request.getParameter("txtsupplier").isEmpty()){
				filterQuery=filterQuery+" and supplier='" +chkcode.getParam( request, "txtsupplier")+ "'";
			}
			if(!request.getParameter("fromPrice").equals("") && !request.getParameter("fromPrice").isEmpty() && !request.getParameter("toPrice").equals("") && !request.getParameter("toPrice").isEmpty()){
				filterQuery=filterQuery+" and bprice >='"+request.getParameter("fromPrice")+"' and bprice <='"+request.getParameter("toPrice")+"'";
			}
			if(!request.getParameter("doctype").equals("ALL") && !request.getParameter("doctype").isEmpty()){
				filterQuery=filterQuery+" and document='"+request.getParameter("doctype")+"'";
			}

	if(request.getParameter("reporttype")!=null && !request.getParameter("reporttype").isEmpty()){
        			
        			switch (Integer.parseInt(request.getParameter("reporttype"))){
        			case 1:
        				columnName="dept_name";//Department
        				report_type="Department";
        				break;
        			case 2:
        				columnName="sub_name";//Subject
        				report_type="Subject";
        				break;
        			case 3:
        				columnName="publisher";//Publisher
        				report_type="Publisher";
        				break;
        			case 4:
        				columnName="supplier";//Supplier
        				report_type="Supplier";
        				break;
        			default:
        				columnName="noColumn";
        				report_type="noReportType";
        				break;
        			}
        		}
        	

        		
        	
		if(request.getParameter("hid")!=null && request.getParameter("hid").equals("statis")){
			reportName="Statistics_Dept";
			reportTitle="Statistics-Report";
			//sqlQuery="Select distinct("+columnName+") as type,count(access_no)as number,count(distinct title,author_name) as uniquetitle,sum(bprice) as sum_amount,coalesce(sum(bprice),0)-coalesce(sum(accepted_price),0) as discount,coalesce(sum(accepted_price),0) as Net_Price from sorting_view where 2>1 "+filterQuery+" group by "+columnName;
			sqlQuery="Select distinct("+columnName+") as type,count(access_no)as number,count(distinct title,author_name) as uniquetitle,sum(bprice) as sum_amount,coalesce(sum(bprice),0)-coalesce(sum(accepted_price),0) as discount,coalesce(sum(accepted_price),0) as Net_Price from sorting_view where 2>1 "+filterQuery+" group by "+columnName;
		}
		else if(request.getParameter("hid")!=null && request.getParameter("hid").equals("details")){
			reportName="Statistics_Details";
			reportTitle="Statistics-Detailed-Report";
			sqlQuery="select access_no,author_name,title,call_no,dept_name,publisher,COALESCE(SUM(bprice),0.0)-COALESCE(SUM(accepted_price),0) AS discount,accepted_price from sorting_view where 2>1"+filterQuery+groupByQueryDetail;
		}else{
			
		}

		log4jLogger.info("finalQuery: " + sqlQuery);
		
	boolean checkData = ss.getCheckData(sqlQuery);
		  if (checkData)
			{
			 //no data
				log4jLogger.info("----------------NO DATA FOUND-------------------");
			  indexPage = "/Statistics/index.jsp?check=NoData";
			  dispatch(request, response, indexPage);
			}else{

					log4jLogger.info(":::::::::::::Report:::Type:::::::"+request.getParameter("printType"));
					reportType=request.getParameter("printType");
				
				if(reportType.equalsIgnoreCase("pdf")){
					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("logo",base64Logo);
					parameters.put("Report_Type",report_type);
					 if(branchCode==1)
					 {
						 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
						 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
					 }
					 
					 else if(branchCode==2)
					 {
						 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME2);
						 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD2);
					 }
						 
						 else if(branchCode==3)
						 {
							 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME3);
							 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD3);
						 }
							 
							 else if(branchCode==4)
							 {
								 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME4);
								 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD4);
							 }
								 
								 else if(branchCode==5)
								 {
									 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME5);
									 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD5);
								 }
									 
								 else{
									 parameters.put("CMP_NAME", ReportQueryUtill.COMPANY_NAME1);
									 parameters.put("CMP_ADD",Security.getBranchSession(session, response, request) + ReportQueryUtill.COMPANY_ADD1);
									 
								 }


					parameters.put("ReportTitle",reportTitle);
					parameters.put("All_Query",sqlQuery);				
		
					InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/"+reportName+".jasper");
					JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
					JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
		
					JRAbstractExporter exporterPDF = new JRPdfExporter();
					exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
					response.setHeader("Content-Disposition", "attachment;filename="+reportTitle+".pdf");
					response.setContentType("application/pdf");
					//response.setContentType("application/csv");
					exporterPDF.exportReport(); 
					
				}else{
					List<?> prepareSearchCriteriaLst=null;
					
					if(request.getParameter("hid").equalsIgnoreCase("statis")){
						 prepareSearchCriteriaLst = importExportXMLService.getStatisticsWiseReportList(sqlQuery);	
					}else{
						log4jLogger.info("::::::::: statistics detailed report export excel::::::::");
						prepareSearchCriteriaLst = importExportXMLService.getStatisticsDetailsReportList(sqlQuery);	
					}
					
					
					Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					excelTitleMap.put("fromAccNo", fromValue);
					excelTitleMap.put("toAccNo", toValue);
					excelTitleMap.put("DocType", request.getParameter("doctype"));
					excelTitleMap.put("ReportType", report_type);
					Iterator<?> statisticsDataItr = prepareSearchCriteriaLst.iterator();
					
					
					
					if(request.getParameter("hid").equalsIgnoreCase("statis")){
						
						StatisticsWiseExportRecord recordProcessor = new StatisticsWiseExportRecord(excelTitleMap);
						response.setContentType("text/csv");
						 //response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(statisticsDataItr, recordProcessor, response.getOutputStream());
						
					}else{
						StatisticsDetailsExportRecord recordProcessor = new StatisticsDetailsExportRecord(excelTitleMap);
						response.setContentType("text/csv");
						 //response.setContentType("application/vnd.ms-excel");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(statisticsDataItr, recordProcessor, response.getOutputStream());
						
					}
					
					
				}
						
   
	}
		  
			 }  catch (Exception e) {

					}
					finally{
					
					try{
						if(con!=null){
							con.close();
							con=null;
						}
						
						
						

					}catch(Exception e){
					e.printStackTrace();
					}

					}




	}
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}


}
