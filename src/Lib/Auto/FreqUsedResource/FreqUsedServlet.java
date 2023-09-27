package Lib.Auto.FreqUsedResource;

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

import com.library.autolib.portal.dbconnectionutil.SessionHibernateUtil;
import com.library.autolib.portal.prototype.LibraryServiceFactory;


public class FreqUsedServlet extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static final long serialVersionUID = 1L;
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.ResultSet rs1 = null;
	DataSource datasource;
	
	String flag="",protocol="",strsql="",strsql1="",strsql2="",strsql3="";
	String indexPage = null;

	ResourceBundle rb = ResourceBundle.getBundle("LocalStrings");
	 private static Logger log4jLogger = Logger.getLogger(FreqUsedServlet.class);
		
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

			 con=SessionHibernateUtil.getSession().connection();
			
             int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));      // For Titan
			
             
             LoginUserService ls= LibraryServiceFactory.INSTANCE.getLoginUserService();
             BranchBean branch = ls.getBranchInfo(rk);	
             String base64Logo= Base64.getEncoder().encodeToString(branch.getLogo());

			 if(rk==0){
				 strsql3 = "";				
			 }else  {
				 strsql3 = " and Branch_Code="+rk;	 
			 }			
			
			strsql="";
            int recfrom=Integer.parseInt(request.getParameter("recfrom"));
            int recto_temp=Integer.parseInt(request.getParameter("recto"));
            int recto=recto_temp-recfrom;
            if(recfrom==1){
            recto++;	
            }
            if(recto<0){
            	recto=recto*(-1);
            }
            
            if(request.getParameter("r1").equals("frequency") && request.getParameter("txtdoctype").equals("ALL")){
                 strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + " group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("frequency") && !request.getParameter("txtdoctype").equals("ALL")){
            	strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + "  group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("access") && request.getParameter("txtdoctype").equals("ALL")){
                strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where access_no='"+request.getParameter("txtaccess").trim()+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + "  group by access_no,title,publisher,department order by count(*) desc";	
            }
            
            if(request.getParameter("r1").equals("access") && !request.getParameter("txtdoctype").equals("ALL")){
            	strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and access_no='"+request.getParameter("txtaccess").trim()+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + "  group by access_no,title,publisher,department order by count(*) desc";	
            }
            
            if(request.getParameter("r1").equals("title") && request.getParameter("txtdoctype").equals("ALL")){
                strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where title like '%" +request.getParameter("txttitle").trim()+ "%' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + "  group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("title") && !request.getParameter("txtdoctype").equals("ALL")){
            	strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and  title like '%" +request.getParameter("txttitle").trim()+ "%' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + "  group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("dept") && request.getParameter("txtdoctype").equals("ALL")){
                strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where department='"+request.getParameter("txtdept")+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + "  group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            if(request.getParameter("r1").equals("dept") && !request.getParameter("txtdoctype").equals("ALL")){
            	strsql="select Access_No,title,publisher,department,count(*) as total from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and department='"+request.getParameter("txtdept")+"' and issue_date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"' " + strsql3 + "  group by access_no,title,publisher,department order by count(*) desc limit "+recfrom+","+recto+"";	
            }
            
            
            
            if(request.getParameter("r1").equals("unused")){
            	if(request.getParameter("txtdoctype").equals("ALL")){
            		strsql="SELECT Access_No,title,SP_Name AS publisher,dept_name AS department FROM full_search where access_no not in (select access_no from counter_statistics_issue where issue_Date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"') " + strsql3 + " limit "+recfrom+","+recto+"";
            	}            	
            	else{
            		strsql="SELECT Access_No,title,SP_Name AS publisher,dept_name AS department FROM full_search where document='"+request.getParameter("txtdoctype")+"' and access_no not in (select access_no from counter_statistics_issue where document='"+request.getParameter("txtdoctype")+"' and issue_Date between '"+Security.TextDate_Insert(request.getParameter("fromdate"))+"' and '"+Security.TextDate_Insert(request.getParameter("todate"))+"') " + strsql3 + " limit "+recfrom+","+recto+"";	
            	}

            	log4jLogger.info("Inside Frequent Unused Resource Report");	  

            	try
            	{
            		Map<String, Object> parameters = new HashMap<String, Object>();
            		parameters.put("logo",base64Logo);
            		String namedQuery=strsql;
            		StringBuffer sb = new StringBuffer();

            		sb.append(namedQuery);


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

            		parameters.put("ReportTitle",ReportQueryUtill.Frequently_Resource_Title);   				
            		parameters.put("All_Query",sb.toString());				
            		log4jLogger.info("SQL QUERY: " + sb);		

            		InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Frequently_UnusedResource.jasper");
            		JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	

            		JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

            		JRAbstractExporter exporterPDF = new JRPdfExporter();
            		exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            		exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
            		response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Frequently_Pdf_Title+".pdf");
            		response.setContentType("application/pdf");
            		exporterPDF.exportReport();            

            	}catch(Exception e)
            	{
            		e.printStackTrace();
            	}

            }
            else{

            	log4jLogger.info("Inside Frequent Used Resource Report");	            	
            	try{
            		Map<String, Object> parameters = new HashMap<String, Object>(); 
            		parameters.put("logo",base64Logo);
            		String namedQuery=strsql;
            		StringBuffer sb = new StringBuffer();

            		sb.append(namedQuery);
            		//sb.append(" " +strsql1 +strsql2);


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

            		parameters.put("ReportTitle",ReportQueryUtill.Frequently_Resource_Title);   				
            		parameters.put("All_Query",sb.toString());				
            		log4jLogger.info("SQL QUERY: " + sb);		

            		InputStream inputStream1 = getServletContext().getResourceAsStream("/Report/Frequently_Resource.jasper");
            		JasperReport report = (JasperReport) JRLoader.loadObject(inputStream1);	

            		JasperPrint jasperPrint  = JasperFillManager.fillReport(report, parameters,con);			

            		JRAbstractExporter exporterPDF = new JRPdfExporter();
            		exporterPDF.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            		exporterPDF.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
            		response.setHeader("Content-Disposition", "attachment;filename="+ReportQueryUtill.Frequently_Pdf_Title+".pdf");
            		response.setContentType("application/pdf");
            		exporterPDF.exportReport();            


            	}catch(Exception e)
            	{
            		e.printStackTrace();
            	}

            }
			 
	}  catch (Exception e) {

	}
catch (Throwable theException) {
	
	}
finally{
	try{
		if(con!=null)
		{
			try {					
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null){
			rs.close();
			rs=null;
		}
		if(Prest!=null){
			Prest.close();
			Prest=null;
		}

	}catch(Exception e){
	e.printStackTrace();
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
