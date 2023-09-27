package Lib.Auto.Payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;

import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Payment.PaymentBean;

public class Payment extends HttpServlet implements Serializable {
	 private static Logger log4jLogger = Logger.getLogger(Payment.class);

	 private static final long serialVersionUID = 1L;
	 
	 String indexPage=null,flag;
 
	 
		public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException {

			performTask(request, response);

		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

			performTask(request, response);

		}
	 
		public void performTask(
				HttpServletRequest request,
				HttpServletResponse response)throws ServletException {

			try {
				HttpSession session = request.getSession(true);
				String res = Security.checkSecurity(1, session, response, request);		
				if(res.equalsIgnoreCase("Failure"))
				{
					response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
					return;
				}
				
				PrintWriter out=response.getWriter();				
				CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
				PaymentBean paybean=new PaymentBean();	
				
				flag=request.getParameter("flag");
				
								
				log4jLogger.info(":::::::::flag value::::::::"+flag);
				int branchID=Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID"))));
				
				
				if(flag.equals("issueHistory")){// still not completed shek
					log4jLogger.info("issueHistory===flag====="+flag);
					paybean=new PaymentBean();	
					paybean=ss.getPaymentMember(request.getParameter("memCode").toString().trim(),branchID);
					indexPage="/Payment/search.jsp?check=issueHistoryDetails";
					dispatch(request, response, indexPage);
				}
				
				if(flag.equals("user")){
					
					log4jLogger.info("start=========== Retrive user in Payment Master =====");
					paybean=new PaymentBean();	
					
					paybean=ss.getPaymentMember(request.getParameter("user_no").toString().trim(),branchID);
				
					String ab=paybean.getMcode();
					request.setAttribute("bean",paybean);
					
					if(ab!="NIL")
					{
							
						int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
						//indexPage = "/Payment/index.jsp?check=userdetails&message=TRANSDETAILS&info=PAIDDETAILS&issuedetails=ISSUEDETAILS";
						if(rk<=2 || paybean.getBranch()==rk)
						{
					       indexPage = "/Payment/index.jsp?check=userdetails&message=TRANSDETAILS&info=PAIDDETAILS&issuedetails=ISSUEDETAILS";
					       
						}
						else if(paybean.getBranch()!=rk){	
						   indexPage = "/Payment/index.jsp?check=NotRightUser";
						}
					}
					else
					{						
						indexPage = "/Payment/index.jsp?check=FailMember";
					}
					
					dispatch(request, response, indexPage);
				}
				
				
				
				
				
				if(flag.equals("clear")) {
					log4jLogger.info("clear===========flag====="+flag);
					indexPage="/Payment/index.jsp";
					dispatch(request, response, indexPage);
				}
				
				if(flag.equals("new")){
					log4jLogger.info("start=========== Retrive Bill NO in Payment Master =====");
					int bno=0;
					bno=ss.getPaymentBill_no();
					
					
					if(bno>0)
					{						
					    indexPage = "/Payment/index.jsp?check=newbillno";
					}
					else
					{						
						indexPage = "/Payment/index.jsp?check=newbean";
					}
					dispatch(request, response, indexPage);
				}
				
				if(flag.equals("save")){
					log4jLogger.info("start=========== Save to Payment Master =====");
					int bno=0,pb=0;
				    paybean=new PaymentBean();
				    
				    paybean.setBill_No(Integer.parseInt(request.getParameter("bill_no")));
					paybean.setMcode(request.getParameter("user_no"));
				    paybean.setPdate(Security.getdate(request.getParameter("pdate")));
				    paybean.setCurrent_Amt(Double.parseDouble(request.getParameter("current_amt")));
				    paybean.setDept(String.valueOf(session.getAttribute("UserID")));
				    paybean.setBranch(branchID);
				
					bno=ss.getAddPayment(paybean);
					
					
					if(bno>0)
					{
						
						paybean=ss.getPaymentMember(request.getParameter("user_no").toString().trim(),branchID);			
						request.setAttribute("bean",paybean);						
						
					indexPage = "/Payment/index.jsp?check=userdetails&details=SavePayment&message=TRANSDETAILS&info=PAIDDETAILS";
					}
					else
					{						
						indexPage = "/Payment/index.jsp?check=newbean";
					}
					dispatch(request, response, indexPage);
				}
				if(flag.equals("DeletePayment")){
					log4jLogger.info("start=========== DeletePayment  Master =====");
					   boolean deleteCheck=false;
				     paybean=new PaymentBean();
				     paybean.setBill_No(Integer.parseInt(request.getParameter("bill_no")));
					 paybean.setMcode(request.getParameter("user_no"));
			         paybean.setCurrent_Amt(Double.parseDouble(request.getParameter("current_amt")));
					 paybean.setBranch(branchID);
					 String memCode=paybean.getMcode();
					 log4jLogger.info("start=========== PaymentBean::::: ====="+paybean.toString());
				 
				    deleteCheck=ss.deletePaymentClearance(paybean);
				    if(deleteCheck)	 
					//indexPage = "/Payment/index.jsp?delStatus=deleteSuccess&memberCode=memCode";
				    indexPage = "/Payment/PaymentServlet?flag=user&user_no="+memCode+"";
				    else
				    indexPage = "/Payment/index.jsp?check=deleteFailed";
					dispatch(request, response, indexPage);
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
}