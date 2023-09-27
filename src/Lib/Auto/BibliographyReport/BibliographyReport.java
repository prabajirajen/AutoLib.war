package Lib.Auto.BibliographyReport;

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
import Common.businessutil.reports.ReportService;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Counter.COUNTER_QUERY;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;


public class BibliographyReport extends HttpServlet implements Serializable, COUNTER_QUERY {
	
	
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.ResultSet rs = null;

	String flag="",option_type="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="";
	String indexPage = null,printType="";
	
	
	String frm_accno="",to_accno="",frm_dt="",to_dt="",call_no="";
	
	String firstStr = null, secondStr = null;
	int firstNum = 0, secondNum = 0, firstStrCount = 0;
	InputStream inputStream1 = null;

	
	 
	
	public static boolean IsNumeric(String string)
    {	 
	  try
	  {
		Integer.parseInt(string);
		return  true;
	  }catch(Exception e){
		return  false;
	  }			
    }
	
	private static Logger log4jLogger = Logger.getLogger(BibliographyReport.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response)  throws ServletException {


		try {
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
		
			log4jLogger.info("Inside from BibliographyReport Reports");
			
			strsql="";
			strsql1="";
			strsql2="";
			strsql3="";
			strsql4="";
			
			ReportService rs1=BusinessServiceFactory.INSTANCE.getReportService();
			
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();
			
			
			int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
			
            LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(rk);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());
			Map<String, Object> parameters = new HashMap<String, Object>(); 
			parameters.put("logo",base64Logo);
			
			flag=request.getParameter("report_type");
			printType=request.getParameter("printType");
			
			log4jLogger.info("Flag::::::::"+flag);
			log4jLogger.info("printType::::::::"+printType);
			
			
			
			   if(!flag.equals("") && !flag.equals("ACCESSION") && !flag.equals("CALLNO") && !flag.equals("RECEIVEDDATE") )
			   {
				   strsql5="";

	        	   option_type=request.getParameter("option_name");
	           
	          
	           if(flag.equals("Aut"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        		   strsql2=" and author_name like '%"+option_type+"%'";   
	        	   }
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Report.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioAuthor_Title); 
	        	
	        	   strsql5 = " ORDER BY Author_Name ASC ";
	        	   
	        	   
	           }
	           if(flag.equals("Dept"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and dept_name = '"+option_type+"'";
	        	   }
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Department.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioDept_Title);
	        	   if(!request.getParameter("status").toString().equalsIgnoreCase("YES")){
	        	   parameters.put("avail", request.getParameter("status").toString());
	        	   }else{
	        	   parameters.put("avail"," ALL BOOKS"); 
	        	   }
	        	   strsql5 = " ORDER BY Dept_Name ASC ";
	           }
	           if(flag.equals("Pub"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and publisher = '"+option_type+"'";
	        	   }
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Publisher.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioPub_Title);
	        	   strsql5 = " ORDER BY Publisher ASC ";
	           }
	           if(flag.equals("Sup"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and supplier = '"+option_type+"'";
	        	   }
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Supplier.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioSup_Title); 
	        	   strsql5 = " ORDER BY Supplier ASC ";
	        	   
	           }
	           if(flag.equals("Sub"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and sub_name = '"+option_type+"'";
	        	   }
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Subject.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.Bibliosubject_Title); 
	        	   
	        	   strsql5 = " ORDER BY Sub_Name ASC ";
	        	   
	        	   
	           }
	           if(flag.equals("Bud"))
	           {
	        	   strsql5="";
	        	   if(!option_type.isEmpty() && option_type != null){
	        	   strsql2=" and budget = '"+option_type+"'";
	        	   }
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Budget.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioBudged_Title); 
	        	   
	        	   strsql5 = " ORDER BY Access_no,Budget ASC ";
	           }
	           
	                   
			   }
			   
			   
			   
			   if(flag.equals("ACCESSION"))
	           {
				   strsql5="";
				   frm_accno=request.getParameter("From_Accno");
				   to_accno=request.getParameter("To_Accno");
				 
				 
				if (IsNumeric(request.getParameter("From_Accno"))
						&& IsNumeric(request.getParameter("To_Accno"))) {

					log4jLogger
							.info("inside Accession number wise numbers only");

					strsql1 = " and access_no REGEXP '^[0-9]+$' AND CAST(access_no AS SIGNED)  BETWEEN '"
							+ frm_accno + "' and '" + to_accno + "' ";
                  strsql=""; 
					strsql5 = " ORDER BY CAST(access_no AS SIGNED)";

				}

				else {
					strsql5="";
					log4jLogger.info("Inside STringData");

					firstNum = Integer.parseInt(request.getParameter("firstNum"));
					secondNum = Integer.parseInt(request.getParameter("secondNum"));

					firstStr = request.getParameter("firstStr");
					
					firstStrCount = firstStr.length() + 1;
					secondStr = request.getParameter("secondStr");
					
					strsql1 = " and  access_no NOT REGEXP '^[0-9]+$' AND ACCESS_NO LIKE '"+firstStr+"%' AND  CAST(SUBSTRING(access_no,'"+firstStrCount+"') AS SIGNED) BETWEEN '"+ firstNum + "' and '" + secondNum + "' ";
					
					strsql="";
					strsql5 = " ORDER BY LPAD(access_no, 25, '0');";

					
					
				}
				
				
				
					inputStream1 = getServletContext().getResourceAsStream(
							"/Report/Bibliography_Report.jasper");
					parameters.put("ReportTitle",
							ReportQueryUtill.BiblioAccession_Title);
				
			}
   
			   if(flag.equals("CALLNO"))
	           {
				   strsql5="";
				   if(!option_type.isEmpty() && option_type != null){
				   frm_accno=request.getParameter("From_Accno");
				   }
				   //to_accno=request.getParameter("To_Accno");
				   
	        	   strsql1=" and call_no like '%"+frm_accno+"%'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Report.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioCallno_Title);  
	        	   strsql5 = " ORDER BY Call_No ASC ;";
	           }
			   if(flag.equals("RECEIVEDDATE"))
	           {
				   strsql5="";
				   frm_dt=Security.TextDate_member(request.getParameter("fromdt"));
				   to_dt=Security.TextDate_member(request.getParameter("todt"));
				   
	        	   strsql1=" and received_date between '"+frm_dt+"' and '"+to_dt+"'";
	        	   inputStream1 = getServletContext().getResourceAsStream("/Report/Bibliography_Report.jasper");
	        	   parameters.put("ReportTitle",ReportQueryUtill.BiblioRevd_Title);
	        	   strsql5 = " ORDER BY Received_Date ASC ";
	           }
	        
	           if(!request.getParameter("status").equals("") && !request.getParameter("status").equals("ALL")){
	            	 
	                 strsql3=strsql3+" and availability='"+request.getParameter("status")+"'";
	           }
	           
	           
	           if(!request.getParameter("doc_type").equals("") && !request.getParameter("doc_type").equals("ALL")){
	            	 
	                 strsql3=strsql3+" and document='"+request.getParameter("doc_type")+"'";
	           }
	           


				if(!request.getParameter("giftPurchase").equalsIgnoreCase("ALL")){

				strsql3 =strsql3+" AND gift_purchase='"+request.getParameter("giftPurchase")+"'"; 
				}
				
		
					strsql4 = " and Branch_Code="+rk;	 
				
				
				
	           
    	      	
	            con=SessionHibernateUtil.getSession().connection();
    	      	     	      	
    			String namedQuery=ReportQueryUtill.Biblio_Query;
    			StringBuffer sb = new StringBuffer();
    			String filterQuery=null;
    			
    			sb.append(namedQuery);
				sb.append(" " + strsql1+strsql2+strsql3+strsql4+strsql5);
				   			        	
					
					boolean checkData = rs1.getCheckData(sb.toString());
					log4jLogger.info("SQL QUERY: " + sb);	
					  if (checkData)
						{
						 //no data
							log4jLogger.info("----------------NO DATA FOUND-------------------");
						  indexPage = "/BibliographyReport/index.jsp?check=NoData";
						  dispatch(request, response, indexPage);
						 
						}else{
							
							
					if(printType.equalsIgnoreCase("PdfReport")){
					
    			
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
    			parameters.put("All_Query",sb.toString());
    		
						
				
				JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
				JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
							
				JRAbstractExporter exporterPDF = new JRPdfExporter();
		        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
		        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Biblio_Title+".pdf");
		        response.setContentType("application/pdf");
		        exporterPDF.exportReport(); 
				
					}
					else
					{
						
						
						List prepareSearchCriteriaLst = importExportXMLService.getBibliographyReportList(sb.toString());
						
						Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
					
						//excelTitleMap.put("fromAccNo", "1");
						//excelTitleMap.put("toAccNo", "1");
						//excelTitleMap.put("documentType", "1");
						
						Iterator studentDataItr = prepareSearchCriteriaLst.iterator();
						BibliographyExportRecord recordProcessor = new BibliographyExportRecord(excelTitleMap);
						response.setContentType("text/csv");
						response.setHeader("Content-Disposition","attachment; filename=" + recordProcessor.getExcelFileName());
						csvImportExportService.Export(studentDataItr, recordProcessor,response.getOutputStream());
						
					}
		        
    			 
	}	
		}
		catch (Exception e) {
      e.printStackTrace();
	}
catch (Throwable theException) {
	
	}
		
		
	
		
finally{
	strsql="";
	strsql1="";
	strsql2="";
	strsql3="";
	strsql4="";	
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
	
	
	
	public void dispatch(HttpServletRequest request,HttpServletResponse response,String indexPage)throws ServletException, IOException {
		   // response.sendRedirect(indexPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}

}
