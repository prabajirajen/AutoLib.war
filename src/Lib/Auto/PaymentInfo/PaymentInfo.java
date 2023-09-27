package Lib.Auto.PaymentInfo;


import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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

import Common.DataQuery;
import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.LoginUserService;
import Common.businessutil.importexportexcel.ExceImportExportService;
import Common.businessutil.importexportxml.ImportExportXMLService;
import Common.businessutil.reports.ReportQueryUtill;
import Lib.Auto.Branch.BranchBean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;





public class PaymentInfo extends HttpServlet implements Serializable {
	private static Logger log4jLogger = Logger.getLogger(PaymentInfo.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String flag=null;
	String indexPage = null;
	String frmdt=null,todt=null;	
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

		performTask(request, response);

	}
	public void performTask(
		HttpServletRequest request,
		HttpServletResponse response) throws ServletException {			
		
		try {
		HttpSession session = request.getSession(true);	
		String res = Security.checkSecurity(1, session, response, request);		
		if(res.equalsIgnoreCase("Failure"))
		{
			response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
			return;
		}		
		
		con=SessionHibernateUtil.getSession().connection();		
		flag=request.getParameter("flag");
		
		int branchID = (Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan			

		LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
		BranchBean branchCode = ls.getBranchInfo(branchID);	
		String base64Logo= Base64.getEncoder().encodeToString(branchCode.getLogo());

	
		if(flag.equals("user")){
			log4jLogger.info("start=========== Retrive user For  Payment Info Report =====");
			String Mcode=null;
			Mcode=request.getParameter("user_no").toString().trim();
			ArrayList DEATILS = new ArrayList();
			int count=0,branch=-1,deptBranch = 0;
			
			try {	
				
				Prest = con.prepareStatement(DataQuery.SELECT_MEMBER);
				Prest.setString(1, Mcode);
				rs = Prest.executeQuery();
				if (rs.next()) {
					DEATILS.add(rs.getString("member_code"));
					DEATILS.add(rs.getString("member_name"));
					DEATILS.add(rs.getString("dept_name"));
					DEATILS.add(rs.getString("course_name"));						
					count=1;
					branch=rs.getInt("Branch_Code");
					deptBranch=rs.getInt("Dept_BranchCode");					
				}
				request.setAttribute("MemberDetails",DEATILS);
				
			
			if(count>0)
			{
				
				if(branchID <= 2 )
				{
					if(branchID==branch || branchID==0)
					{
						indexPage = "/PaymentInfo/index.jsp?check=userdetails";
					}else  {
						indexPage = "/PaymentInfo/index.jsp?check=OtherBranchMember";
					}
				}
				else if(branchID > 2 ) {
					
					if(branchID==deptBranch)
					{
						indexPage = "/PaymentInfo/index.jsp?check=userdetails";
					}else  {
						indexPage = "/PaymentInfo/index.jsp?check=OtherBranchMember";
					}
					
				}			
			     
			}else
			{				
				indexPage = "/PaymentInfo/index.jsp?check=usernotfound";
			}
			
			
			dispatch(request, response, indexPage);
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
		
		
		
		
		

		if(flag.equals("PaidReport")){		
		
		try {
			log4jLogger.info("start=========== Payment Info Report =====");			
			
		Map parameters = new HashMap();	
		parameters.put("logo",base64Logo);
		String namedQuery=ReportQueryUtill.Paymentinfo_query;
		StringBuffer sb = new StringBuffer();
		String filterQuery=null;
		String Strsql="";
		String Mcode=null;
		
		Mcode=request.getParameter("user_no").toString().trim();
		frmdt=Security.TextDate_member(request.getParameter("fromdate"));
		todt=Security.TextDate_member(request.getParameter("todate"));
		
		if(branchID == 0){
			Strsql = "";				
		}else  {
			Strsql = Strsql + " and Branch_Code="+branchID;	 
		}
			
		 if(!request.getParameter("user_no").equals("") && (!request.getParameter("user_name").equals(""))){
			 
			 Strsql=Strsql+" and member_code='"+Mcode+"'";		 
		 }
		 
		 if(!frmdt.equals("") && (!todt.equals("")))
		 {			 
			 Strsql=Strsql+" and payment_date>='"+frmdt+"' and payment_date<='"+todt+"'";		 
		 }		
					
		 filterQuery=Strsql;
			 
	
		if (filterQuery != null)
		{
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
			sb.append(" "+"order by payment_date");
		}
		else
		{
			sb.append(namedQuery);
			sb.append(" "+"order by payment_date");
		}
		
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

		parameters.put("ReportTitle",ReportQueryUtill.Paymentinfo_Title);
		parameters.put("All_Query",sb.toString());				
		log4jLogger.info("namedQuery: " + sb);					
	
	//InputStream inputStream1 = getClass().getResourceAsStream("/Report/Payment_Report.jasper"); // It is working in JBOSS server but Not Working in Appache Tomcat Server.		
	InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Payment_Report.jasper");
	JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);			
				
	JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

	JRAbstractExporter exporterPDF = new JRPdfExporter();
    exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
    response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Paymentinfo_Title+".pdf");
    response.setContentType("application/pdf");
    exporterPDF.exportReport(); 

				
		
 }  catch (Exception  e) {		

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


        if(flag.equals("overallfinerpt"))
        {
    	    log4jLogger.info("start=========== Retrive OverAllFine Report =====");
    	    try
    	    {
    	    Map parameters = new HashMap();		
    		String namedQuery=ReportQueryUtill.FineOverAll_query;
    		StringBuffer sb = new StringBuffer();
    		
    		String filterQuery=null;
    		String Strsql="";
   		
    		if(branchID ==0 ){
    			Strsql = "";				
    		}else  {
    			Strsql = Strsql + " and Branch_Code="+branchID;	 
    		}   		    			
    					
    		    filterQuery=Strsql; 	
    		
    			sb.append(namedQuery);
    			sb.append(" " + filterQuery);
    			sb.append(" "+" order by dept_name");    		
    		
    		
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

    		parameters.put("ReportTitle",ReportQueryUtill.FineOverAll_Title);
    		parameters.put("All_Query",sb.toString());				
    		log4jLogger.info("namedQuery: " + sb);					
    	
    	//InputStream inputStream1 = getClass().getResourceAsStream("/Report/Payment_Report.jasper"); // It is working in JBOSS server but Not Working in Appache Tomcat Server.		
    	InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/FineOverAll_Report.jasper");
    	JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);			
    				
    	JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

    	JRAbstractExporter exporterPDF = new JRPdfExporter();
        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.FineOverAll_Title+".pdf");
        response.setContentType("application/pdf");
        exporterPDF.exportReport();    	    
    	    
        
		 } catch (Exception  e) {		

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
        
        
        if (flag.equalsIgnoreCase("ExcelReport"))
		{
			ImportExportXMLService importExportXMLService = BusinessServiceFactory.INSTANCE.getImportExportXMLService();
			ExceImportExportService csvImportExportService = BusinessServiceFactory.INSTANCE.getExceImportExportService();

        	String namedQuery=ReportQueryUtill.FineOverAll_query;
    		StringBuffer sb = new StringBuffer();
    		
    		String filterQuery=null;
    		String Strsql="";
   		
    		if(branchID == 0){
    			Strsql = "";				
    		}else  {
    			Strsql = Strsql + " and Branch_Code="+branchID;	 
    		}   		    			
    					
    		    filterQuery=Strsql; 	
    		
    			sb.append(namedQuery);
    			sb.append(" " + filterQuery);
    			sb.append(" "+" order by dept_name,member_name");    		
    		
    			
//      	For Excel Report.
    			
      		log4jLogger.info("SQL QUERY FOR EXCEL: " + sb);
            List prepareSearchCriteriaLst = importExportXMLService.getOverAllReportList(sb.toString());
			
			Map<Object, Object> excelTitleMap = new HashMap<Object, Object>();
				
			excelTitleMap.put("rptTitle", ReportQueryUtill.FineOverAll_Title);
					
			
			Iterator studentDataItr = prepareSearchCriteriaLst.iterator();				
			ReportOverAllExportRecord recordProcessor = new ReportOverAllExportRecord(excelTitleMap);

			response.setContentType("text/csv");
			response.setHeader("Content-Disposition", "attachment; filename=" + recordProcessor.getExcelFileName());
	        
			csvImportExportService.Export(studentDataItr, recordProcessor, response.getOutputStream());        		
      		
		}
      
        

		} catch (Exception sss) {
			throw new ServletException(sss);
			
		} finally {
			try{
				
			}catch(Exception e){
			e.printStackTrace();
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
