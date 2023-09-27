package Lib.Auto.JournalList;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

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
import Common.businessutil.LoginUserService;
import Common.businessutil.reports.ReportQueryUtill;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.OrdInvProcessing.orderbean;

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;



public class JournalList extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	DataSource datasource;
	
	String flag="",protocol="",strsql="",strsql1="",strsql2="",strsql3="",strsql4="",strsql5="",strsql6="";
	String indexPage = null;
	orderbean orderObject=new orderbean();
	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	
	private static Logger log4jLogger = Logger.getLogger(JournalList.class);
	
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
			
			response.setContentType("text/html");
           
			strsql="";
			strsql1="";
			strsql2="";
			strsql3="";
			strsql4="";
			strsql5="";
			
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
			
			LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
			BranchBean branch = ls.getBranchInfo(branchID);	
			String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());

			
			 if(branchID==0){
				 strsql = "";				
			 }else  {
				 strsql = " and Branch_Code="+branchID+" ";	 
			 }
			 
			 
           if(!request.getParameter("jmo").equals("NO")){
        	  strsql1=" and doc_type='"+request.getParameter("jmo")+"'";
              }
           if(!request.getParameter("country").equals("NO")){
              strsql2=" and country='"+request.getParameter("country")+"'";
              }
            
           if(!request.getParameter("frequency").equals("NO")){
              strsql3=" and frequency='"+request.getParameter("frequency")+"'";
              }
                  
           if(!request.getParameter("dept_name").equals("")){
              strsql4=" and department='"+request.getParameter("dept_name")+"'";
              }
           if(!request.getParameter("sub_name").equals("")){
              strsql5=" and sub_name='"+request.getParameter("sub_name")+"'";
              }
           
           
           
            //ReportService ss = BusinessServiceFactory.INSTANCE.getReportService();		
	      	//con=ss.getDBConnect();
           
             con=SessionHibernateUtil.getSession().connection();
	      	
	      	Map parameters = new HashMap(); 
	      	parameters.put("logo",base64Logo);
			String namedQuery=ReportQueryUtill.JNL_Query;
			StringBuffer sb = new StringBuffer();
			String filterQuery=null;
			
			sb.append(namedQuery);
				sb.append(" " +strsql1 +strsql2 +strsql3 +strsql4 +strsql5+strsql);
			   			        			
			
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
		
		
			parameters.put("ReportTitle",ReportQueryUtill.JNL_List_Title);   				
			parameters.put("All_Query",sb.toString());				
			log4jLogger.info("SQL QUERY: " + sb);		
			
			//InputStream inputStream1 = getClass().getResourceAsStream("/Report/Journal_List.jasper");
			InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Journal_List.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	
					
			JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			
						
			JRAbstractExporter exporterPDF = new JRPdfExporter();
	        exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
	        exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
	        response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.JNL_List_Title+".pdf");
	        response.setContentType("application/pdf");
	        exporterPDF.exportReport(); 

         	        
	}  catch (Exception e) {

	}
catch (Throwable theException) {
	
	}
finally{
	strsql="";
	strsql1="";
	strsql2="";
	strsql3="";
	strsql4="";
	strsql5="";
	strsql6="";

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
	
	public void dispatch(
			HttpServletRequest request,
			HttpServletResponse response,
			String indexPage)
			throws ServletException, IOException {
		   // response.sendRedirect(indexPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
			dispatch.forward(request, response);
		}

}
