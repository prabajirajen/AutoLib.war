package Lib.Auto.BulkUpdate;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import Common.Security;
import Lib.Auto.Counter.COUNTER_QUERY;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;



public class BulkUpdate extends HttpServlet implements Serializable, COUNTER_QUERY {
	
	private static final long serialVersionUID = 1L;
	private static Logger log4jLogger = Logger.getLogger(BulkUpdate.class);
	
	String flag="",strsql="",strsql1="",strsql2="";
	String indexPage = null;	
	String frm_accno="",to_accno="",frm_dt="",to_dt="",optionValue="";	

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
						
			AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan			
			BulkUpdateMsgBean beanObject = new BulkUpdateMsgBean();
			
			String frmUpdate = "",toUpdate = "",frmValue = "",toValue = "";		
			
			flag = request.getParameter("flag");			
			
			if(flag.equalsIgnoreCase("search") && flag != null && !flag.isEmpty())
			{
				log4jLogger.info("start==============>flag:"+flag);
				
				frmUpdate = request.getParameter("frmUpdate");	
				toUpdate = request.getParameter("toUpdate");
				
			   if(!frmUpdate.equals("") && !frmUpdate.equals("NO") && !frmUpdate.equals("ACCESSION") && !frmUpdate.equals("RECEIVEDDATE") )
			   {
				   List resultList = ss.getBulkUpdateList(flag, frmUpdate, branchID);           
		           request.setAttribute("resultBulkList1", resultList);  
			   }			   
			   
			   if(!toUpdate.equals("") && !toUpdate.equals("NO") && !toUpdate.equals("ACCESSION") && !toUpdate.equals("CALLNO") && !toUpdate.equals("RECEIVEDDATE") && !toUpdate.equals("Title")  
			      && !toUpdate.equals("Edition") && !toUpdate.equals("YearPub") && !toUpdate.equals("Keywords") && !toUpdate.equals("Location") && !toUpdate.equals("VolumeNo") && !toUpdate.equals("ISBN") && !toUpdate.equals("BPrice") )
			   {				   
		           List resultList = ss.getBulkUpdateList(flag, toUpdate, branchID);
		           request.setAttribute("resultBulkList2", resultList); 
			   }

			   if(!frmUpdate.isEmpty() && !toUpdate.isEmpty())
			   {
				   beanObject = new BulkUpdateMsgBean();
				   beanObject.setFrmUpdate(frmUpdate);           
			       beanObject.setToUpdate(toUpdate);
			       
			       beanObject.setFrmValue(request.getParameter("frmValue"));
			       beanObject.setToValue(request.getParameter("toValue"));
			       
		           request.setAttribute("beanForm",beanObject);
		           indexPage = "/BulkUpdate/index.jsp";	
		           dispatch(request, response, indexPage);
			   }	   
			   
			}
			
			
			
			
			
			if(flag.equalsIgnoreCase("update") && flag != null && !flag.isEmpty())
			{
				log4jLogger.info("start==============>flag:"+flag);
				
				frmUpdate = request.getParameter("frmUpdate");	
				toUpdate = request.getParameter("toUpdate");
				frmValue = request.getParameter("frmValue");
				toValue = request.getParameter("toValue");
				
				
				if( !frmUpdate.equals("") && !frmUpdate.equals("NO") )
				{					   
					if(frmUpdate.equals("Aut"))  // will be update author_interface table in future.
			        {						
			        	strsql1 = " and author_code = "+frmValue;
			        }
					else if(frmUpdate.equals("Dept"))  			        
					{	
			        	strsql1 = " and dept_code = "+frmValue;
			        }
					else if(frmUpdate.equals("Pub"))
			        {			        	
			        	strsql1 = " and pub_code = "+frmValue;		        	
			        }
					else if(frmUpdate.equals("Sup"))
			        {						
			        	strsql1 = " and sup_code = "+frmValue;		        	
			        }
					else if(frmUpdate.equals("Sub"))
			        {						
			        	strsql1 = " and sub_code = "+frmValue;		        	
			        }
					else if(frmUpdate.equals("Bud"))
			        {						
			        	strsql1 = " and budg_code = "+frmValue;		        	
			        }
					
					else if(frmUpdate.equals("CALLNO"))
			        {						
						strsql1 = " and call_no = '"+frmValue+"'";
			        }
					else if(frmUpdate.equals("Title"))
			        {
						strsql1 = " and Title = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Edition"))
			        {
			        	strsql1 = " and Edition = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("YearPub"))
			        {
			        	strsql1 = " and Year_Pub = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Keywords"))
			        {
			        	strsql1 = " and Keywords = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Language"))
			        {
			        	strsql1 = " and Language = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("Location"))
			        {
			        	strsql1 = " and Location = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("VolumeNo"))
			        {						
						strsql1 = " and VolNo = '"+frmValue+"'";
			        }
			        else if(frmUpdate.equals("ISBN"))
			        {						
						strsql1 = " and ISBN = '"+frmValue+"'";
			        }
					
					
					
					/**if(frmUpdate.equals("ACCESSION"))
					{*/
						 frm_accno = request.getParameter("From_Accno");
						 to_accno = request.getParameter("To_Accno");		
						 						 
						 if(!frm_accno.isEmpty() && !to_accno.isEmpty() && frm_accno!="" && to_accno!="")
						 {
							if(Security.IsNumeric(frm_accno) && Security.IsNumeric(to_accno))    // For Checking Numeric or Alpha Numeric Series
							{
								if(frmUpdate.equals("Aut"))  // will be update author_interface table in future.
						        {
									strsql1 = strsql1 +";"+ " and access_no between "+frm_accno+" and "+to_accno;		// There is no ' (single quatation)
						        }else {
						        	strsql1 = strsql1 + " and access_no between "+frm_accno+" and "+to_accno;		// There is no ' (single quatation)
						        }
							}
							else{
								if(frmUpdate.equals("Aut"))
								{
									strsql1 = strsql1 +";"+ " and access_no between '"+frm_accno+"' and '"+to_accno+"'";
								}else {
									strsql1 = strsql1 + " and access_no between '"+frm_accno+"' and '"+to_accno+"'";
								}
							}			   
						 }
						 
					//}					
					
					
					if(frmUpdate.equals("RECEIVEDDATE"))
			        {
						frm_dt = Security.TextDate_member(request.getParameter("fromdt"));
						to_dt = Security.TextDate_member(request.getParameter("todt"));
						
						strsql1 = " and received_date between '"+frm_dt+"' and '"+to_dt+"'";
			        }	
					
					/**if(frmUpdate.equals("Availability"))
			        {
						if(!request.getParameter("status").equals("") && !request.getParameter("status").equals("ALL"))
						{
							strsql1=" and availability ='"+request.getParameter("status")+"'";
						}
			        } */					
				}
				
				
				
				if(!toUpdate.equals("") && !toUpdate.equals("NO") && !toUpdate.equals("ACCESSION"))
				{					   
					if(toUpdate.equals("Aut"))  // will be update author_interface table in future.
			        {
						strsql2 = " and author_code = "+toValue;
			        }
					else if(toUpdate.equals("Dept"))  			        
					{	
						strsql2 = " dept_code= "+toValue;
			        }
					else if(toUpdate.equals("Pub"))
			        {			        	
						strsql2 = " pub_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Sup"))
			        {						
						strsql2 = " sup_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Sub"))
			        {						
						strsql2 = " sub_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Bud"))
			        {						
						strsql2 = " budg_code = "+toValue;		        	
			        }
					else if(toUpdate.equals("Language"))
			        {
			        	strsql2 = " Language = '"+toValue+"'";
			        }
					
					
					else if(toUpdate.equals("CALLNO"))
			        {	
						optionValue=request.getParameter("optionValue");
						strsql2 = " call_no = '"+optionValue+"'";
			        }
					else if(toUpdate.equals("Title"))
			        {
						optionValue=request.getParameter("optionValue");
						strsql2 = " Title = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("Edition"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " Edition = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("YearPub"))
			        {
			        	optionValue=request.getParameter("optionValue").toString().trim();
			        	strsql2 = " Year_Pub = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("Keywords"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " Keywords = '"+optionValue+"'";
			        }			        
			        else if(toUpdate.equals("Location"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " Location = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("VolumeNo"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " VolNo = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("ISBN"))
			        {
			        	optionValue=request.getParameter("optionValue");
			        	strsql2 = " ISBN = '"+optionValue+"'";
			        }
			        else if(toUpdate.equals("BPrice"))
			        {	
						optionValue=request.getParameter("optionValue").toString().trim();
						strsql2 = " Bprice = "+optionValue+", Accepted_price = "+optionValue+", BCurrency = 1, BCost = "+optionValue;
			        }
					
					if(toUpdate.equals("RECEIVEDDATE"))
			        {
						frm_dt = Security.TextDate_member(request.getParameter("fromdt"));												
						strsql2 = " received_date = '"+frm_dt+"'";
			        }
				}				
				
				beanObject = new BulkUpdateMsgBean();
				beanObject.setBranchCode(branchID);
				beanObject.setFrmUpdate(frmUpdate);           
			    beanObject.setToUpdate(toUpdate);			       
			    beanObject.setFrmValue(strsql1);
			    beanObject.setToValue(strsql2);
				
			    int count = ss.getBulkUpdateSave(beanObject);	
			    log4jLogger.info("start==============>resultDone:"+count);
			    
		        request.setAttribute("resultDone", count);
		        indexPage = "/BulkUpdate/index.jsp";
		        dispatch(request, response, indexPage);
			}
			
			
    	      	
	               			 
	}  catch (Exception e) {
      e.printStackTrace();
	}
catch (Throwable theException) {
	
	}
finally{
	strsql="";
	strsql1="";
	strsql2="";		
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
