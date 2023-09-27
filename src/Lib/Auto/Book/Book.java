package Lib.Auto.Book;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.calaloging.CalalogingService;
import Lib.Auto.Author.AuthorBean;
import Lib.Auto.Budget.BudgetBean;
import Lib.Auto.Department.DepartmentBean;
import Lib.Auto.Keywords.KeywordsBean;
import Lib.Auto.PubSup.PubSupBean;
import Lib.Auto.Subject.subjectbean;

import com.google.gson.Gson;

public class Book extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	private static Logger log4jLogger = Logger.getLogger(Book.class);

	String indexPage = null;

	String flag = null;

	int sub, sup, pub, dept, branch;

	int Book_copies = 1;

	String s = "",doctype="",reportType;

	String zero = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {

		performTask(request, response);

	}

	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
		try {
			
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			
			
			CalalogingService ss = BusinessServiceFactory.INSTANCE.getCalalogingService();
			int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
			
			
			
			
			 response.setContentType("application/json");
	            
	            flag = request.getParameter("flag");
				
				doctype=request.getParameter("doc_type");
				reportType=request.getParameter("reportType");
				
				try{
					String term = request.getParameter("accessNo");
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		            {
					   String doc = session.getAttribute("Document").toString();
					   //String report = session.getAttribute("Report").toString();
					//System.out.println("Data from ajax call " + term);
								    
					   ArrayList<bookbean> list = ss.getBookAutoAccessNoSearch(term,doc,branchID);
				       for(bookbean user: list){
						//System.out.println(user.getAccessNo());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		            }	
			}catch(Exception e){
				//e.printStackTrace();
			}

			try{
					String term = request.getParameter("callNo");
					
					if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
		             {
					//System.out.println("Data from ajax call " + term);
						String doc = session.getAttribute("Document").toString();
						//String report = session.getAttribute("Report").toString();
					   ArrayList<bookbean> list = ss.getBookAutoCallNoSearch(term,doc,branchID);
				       for(bookbean user: list){
						//System.out.println(user.getCallNo());
					}       

					String searchList = new Gson().toJson(list);
								
					response.getWriter().write(searchList);  
								
					//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
		             }	
			}catch(Exception e){
				//e.printStackTrace();
			}    		 

			
			
			
			try{
				String term = "";
				term = request.getParameter("title");

				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
				{
					log4jLogger.info("Data from ajax call 1" + term);
					// String doc = session.getAttribute("Document").toString();
					//String report = session.getAttribute("Report").toString();

					log4jLogger.info("Data from ajax call 2" + term);

					// log4jLogger.info("Data from ajax doc 3" + doc);

					log4jLogger.info("Data from ajax 4 :branchID " + branchID);
					ArrayList<bookbean> list = ss.getBookAutoTitleSearch(term,"BOOK",branchID);
					for(bookbean user: list){
						System.out.println(user.getTitle());
					}       

					String searchList = new Gson().toJson(list);

					response.getWriter().write(searchList);  

					log4jLogger.info("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList.toString());
				}	
			}catch(Exception e){
				
				
			
		}    		 

			try{
				String term = "";
				term = request.getParameter("author");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<AuthorBean> list = ss.getBookAutoAuthorSearch(term,branchID);
			       for(AuthorBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 

			try{
				String term = "";
				term = request.getParameter("pubName");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<PubSupBean> list = ss.getBookAutoPublisherSearch(term,branchID);
			       for(PubSupBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}  
			
			try{
				String term = "";
				term = request.getParameter("subName");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<subjectbean> list = ss.getBookAutoSubjectSearch(term,branchID);
			       for(subjectbean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}   
			
			try{
				String term = "";
				term = request.getParameter("deptName");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<DepartmentBean> list = ss.getBookAutoDeptSearch(term,branchID);
			       for(DepartmentBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    
			
			try{
				String term = "";
				term = request.getParameter("supName");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<PubSupBean> list = ss.getBookAutoSupplierSearch(term,branchID);
			       for(PubSupBean user: list){
					//System.out.println(user.getName());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 
			
			try{
				String term = "";
				term = request.getParameter("budName");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
							    
				   ArrayList<BudgetBean> list = ss.getBookAutoBudgetSearch(term,branchID);
			       for(BudgetBean user: list){
					//System.out.println(user.getBudHead());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//System.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    		 
			
			try{
				String term = "";
				term = request.getParameter("keywords");
				
				if(!term.equalsIgnoreCase(null) && !term.equalsIgnoreCase(""))
	             {
				//System.out.println("Data from ajax call " + term);
				   //String doc = session.getAttribute("Document").toString();			    
				   ArrayList<KeywordsBean> list = ss.getBookAutoKeywordsSearch(term,branchID);
			       for(KeywordsBean user: list){
					//System.out.println(user.getKname());
				}       

				String searchList = new Gson().toJson(list);
							
				response.getWriter().write(searchList);  
							
				//mSystem.out.println("Json Value@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+searchList);
	             }	
		}catch(Exception e){
			//e.printStackTrace();
		}    
			
			bookbean newbookbean = null;
			String access_no = "";
			String New_Access_no= "";
			
			
			flag = request.getParameter("flag");
			
			doctype=request.getParameter("doc_type");
			
			if (flag.equals("loadBook")) {
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				indexPage = "/Book/index.jsp";
				log4jLogger.info("Document new no :"+doctype);
				bookbean ob = new bookbean();
				
				if(doctype!=null && !doctype.isEmpty())
				{				 	
					
				  New_Access_no=ss.getNewAccNoLoad(doctype,branchID);				
				  ob.setAccessNo(New_Access_no);			
				  request.setAttribute("bean", ob);
				  indexPage = "/Book/index.jsp?check=newBook&doc_type="+doctype+"";
				}				
				
				dispatch(request, response, indexPage);
			} else {
				access_no = request.getParameter("accessNo");
			}

			if (flag.equals("New")) {
				log4jLogger.info("New===========flag=====" + flag +"5555"+doctype);
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss.getCurrencyLoad();				
				request.setAttribute("currency", AuthorArrylist);				
				//indexPage = "/Book/index.jsp?check=newBook";
				indexPage = "/Book/index.jsp";
				
                bookbean ob = new bookbean();
				
				if(doctype!=null && !doctype.isEmpty())
				{
				  //int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
				  
				  New_Access_no=ss.getNewAccNoLoad(doctype,branchID);				
				  ob.setAccessNo(New_Access_no);			
				  request.setAttribute("bean", ob);
				  indexPage = "/Book/index.jsp?check=newBook&doc_type="+doctype+"";
				}
				dispatch(request, response, indexPage);
			}

			if (flag.equals("search")) {
				
				log4jLogger.info("search===========flag=====" + flag);
				log4jLogger.info("===========access_no=====" + access_no);
				log4jLogger.info("===========shekoli doctype=====" + doctype);
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss.getCurrencyLoad();
				
				
				newbookbean = ss.getBookSearch(access_no,branchID,doctype);
				if (newbookbean != null) {
					request.setAttribute("currency", AuthorArrylist);
					request.setAttribute("bean", newbookbean);
					//indexPage = "/Book/index.jsp?check=SearchBook";
				      indexPage = "/Book/index.jsp?check=SearchBook&doc_type="+doctype+"";//SHEK
				      log4jLogger.info("3333333333333333333333333333333333333333333333333333333333"+indexPage);
				} else {
					request.setAttribute("currency", AuthorArrylist);
					indexPage = "/Book/index.jsp?check=FailureBook";
				}
				dispatch(request, response, indexPage);

			}
			
			if (flag.equals("searchnb")) {
				access_no=request.getParameter("addfield1");
				
							log4jLogger.info("search===========flag=====" + flag);
							log4jLogger.info("===========access_no=====" + access_no);
							log4jLogger.info("===========shekoli doctype=====" + doctype);
							
							List AuthorArrylist = new ArrayList();
							AuthorArrylist=ss.getCurrencyLoad();
							
							doctype="BOOKNONBOOK";  //this doctype is not a valid document type, this will be change as "BOOK" and "NON BOOK" in CalalogingDaoImpl.java for some reference
							newbookbean = ss.getBookSearch(access_no,branchID,doctype);
							if (newbookbean != null) {
								request.setAttribute("currency", AuthorArrylist);
								request.setAttribute("bean", newbookbean);
								
							      indexPage = "/Book/index.jsp?check=SearchBook&doc_type=NON BOOK";
							      
							} else {
								request.setAttribute("currency", AuthorArrylist);
								indexPage = "/Book/index.jsp?check=FailureBook";
							}
							dispatch(request, response, indexPage);

						}
						
			
			if (flag.equals("update")) {
				log4jLogger.info("BookUpdate===========flag=====" + flag);
				String uaccess = request.getParameter("accessNo").toUpperCase();
				bookbean ob = new bookbean();

				log4jLogger.info("singleBookUpdate===========flag=====" + flag);

				if (request.getParameter("Sub").equals("1")
						&& request.getParameter("subName").equalsIgnoreCase(
								"Nil")) {

					sub = Integer.parseInt(request.getParameter("Sub"));
				} else {
					sub = ss.getBookSubCode(request.getParameter("subName"),branchID);
				}

				if (request.getParameter("Branch").equals("1")
						&& request.getParameter("branchName").equalsIgnoreCase(
								"Nil")) {

					branch = Integer.parseInt(request.getParameter("Branch"));
				} else {
					branch = ss.getBookBranchCode(request
							.getParameter("branchName"));
				}

				if (request.getParameter("Dept").equals("1")
						&& request.getParameter("deptName").equalsIgnoreCase(
								"Nil")) {

					dept = Integer.parseInt(request.getParameter("Dept"));
				} else {
					dept = ss.getBookDeptCode(request.getParameter("deptName"),branchID);					
				}

				System.out
						.println("===========================supplier============================"
								+ request.getParameter("supName"));

				if (request.getParameter("Sup").equals("2")
						&& request.getParameter("supName").equalsIgnoreCase(
								"Nil")) {

					sup = Integer.parseInt(request.getParameter("Sup"));
				} else {
					sup = ss.getBookSupplierCode(request
							.getParameter("supName"),branchID);
				}
				if (request.getParameter("Pub").equals("1")
						&& request.getParameter("pubName").equalsIgnoreCase(
								"Nil")) {

					pub = Integer.parseInt(request.getParameter("Pub"));
				} else {
					pub = ss.getBookPubCode(request.getParameter("pubName"),branchID);
				}

				System.out
						.println("===========================bk update============================"
								+ sup);
				ob.setAccessNo(uaccess);
				ob.setTitle(request.getParameter("title"));
				ob.setAuthor(request.getParameter("author"));
				ob.setCallNo(request.getParameter("callNo"));
				ob.setOtherTitle(request.getParameter("otherTitle"));
				ob.setRole(request.getParameter("role"));
				ob.setStateRes(request.getParameter("stateRes"));
				ob.setEdition(request.getParameter("edition"));
				ob.setLanguage(request.getParameter("language"));
				ob.setPlace(request.getParameter("place"));
				ob.setYop(request.getParameter("yop"));
				ob.setPages(request.getParameter("pages"));
				ob.setSize(request.getParameter("size"));
				ob.setIllustration(request.getParameter("illustration"));
				ob.setIsbn(request.getParameter("isbn"));
				ob.setBprice(request.getParameter("bprice"));
				ob.setCopies(Book_copies);
				ob.setScript(request.getParameter("script"));
				ob.setLocation(request.getParameter("location"));
				ob.setAvail(request.getParameter("avail"));
				ob.setSubCode(sub);
				ob.setPubCode(pub);
				ob.setSupCode(sup);
				ob.setBranchCode(branch);
				ob.setDoc(request.getParameter("doc"));
				ob.setType(request.getParameter("type"));
				ob.setPhysical(request.getParameter("physical"));
				ob.setBinding(request.getParameter("binding"));
				ob.setKeywords(request.getParameter("keywords"));
				ob.setNotes(request.getParameter("notes"));
				ob.setSummary(request.getParameter("summary"));
				ob.setBibliography(request.getParameter("bibliography"));
				ob.setContents(request.getParameter("contents"));
				ob.setVolumeNo(request.getParameter("volumeNo"));
				ob.setPartNo(request.getParameter("partNo"));
				ob.setVolumeTitle(request.getParameter("volumeTitle"));
				ob.setVolumeRes(request.getParameter("volumeRes"));
				ob.setCorAut(request.getParameter("corAut"));
				ob.setCorAdd(request.getParameter("corAdd"));
				ob.setSerAut(request.getParameter("serAut"));
				ob.setSerName(request.getParameter("serName"));
				ob.setSerTitle(request.getParameter("serTitle"));
				ob.setIssn(request.getParameter("issn"));
				ob.setMeeting(request.getParameter("meeting"));
				ob.setSponsor(request.getParameter("sponsor"));
				ob.setVenue(request.getParameter("venue"));
				ob.setRcDate(Security.TextDate_Update(request
						.getParameter("rcDate")));
				ob.setInvNo(request.getParameter("invNo"));
				ob.setInvoiceDate(Security.TextDate_Update(request
						.getParameter("invoiceDate")));
				ob.setCurrency(request.getParameter("currency"));
				ob.setBcost(request.getParameter("bcost"));
				ob.setAcceptPrice(request.getParameter("acceptPrice"));
				ob.setDiscount(Double.parseDouble(request
						.getParameter("discount")));
				ob.setAddfield1(request.getParameter("addfield1"));
				ob.setAddfield2(request.getParameter("addfield2"));
				ob.setAddfield3(request.getParameter("addfield3"));
				ob.setAddfield4(request.getParameter("addfield4"));
				ob.setDeptCode(dept);
				ob.setPurchaseType(request.getParameter("purchaseType"));
				ob.setBudName(request.getParameter("budName"));
				ob.setBudgetCode(Integer.parseInt(request.getParameter("Bud")));
				ob.setOtherDate(Security.TextDate_Update(request
						.getParameter("otherDate")));
				ob.setauthorvalue(request.getParameter("Author_value"));

				int count = ss.getBookUpdate(ob);
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				
				indexPage = "/Book/index.jsp?check=BookUpdate";
				dispatch(request, response, indexPage);
			}
			if (flag.equals("save")) {
				log4jLogger.info("save===========flag=====" + flag);
				// out.print(request.getParameter("rcDate"));
				String uaccess = request.getParameter("accessNo").toUpperCase();

				int ncopy = Integer.parseInt(request.getParameter("copies"));
				if (ncopy > 1) {
					bookbean ob = new bookbean();

					ob.setAccessNo(uaccess);
					ob.setTitle(request.getParameter("title"));

					if ((request.getParameter("author") != null)
							&& (request.getParameter("author") != "")) {
						ob.setAuthor(request.getParameter("author"));
					} else {
						ob.setAuthor("Nil");
					}
					
					if(request.getParameter("callNo")!=null){
					ob.setCallNo(request.getParameter("callNo"));
					}else{
						ob.setCallNo("");
					}
					if(request.getParameter("otherTitle")!=null){
					ob.setOtherTitle(request.getParameter("otherTitle"));
					}else{
						ob.setOtherTitle("");
					}
					if(request.getParameter("role")!=null){
					ob.setRole(request.getParameter("role"));
					}else{
						ob.setRole("");
					}
					if(request.getParameter("stateRes")!=null){
					ob.setStateRes(request.getParameter("stateRes"));
					}else{
						ob.setStateRes("");
					}
					if(request.getParameter("edition")!=null){
					ob.setEdition(request.getParameter("edition"));
					}else{
						ob.setEdition("");
					}
					ob.setLanguage(request.getParameter("language"));
					
					if(request.getParameter("place")!=null){
					ob.setPlace(request.getParameter("place"));
					}else{
						ob.setPlace("");
					}
					if(request.getParameter("yop")!=null){
					ob.setYop(request.getParameter("yop"));
					}else{
						ob.setYop("");
					}
					if(request.getParameter("pages")!=null){
					ob.setPages(request.getParameter("pages"));
					}else{
						ob.setPages("");
					}
					if(request.getParameter("size")!=null){
					ob.setSize(request.getParameter("size"));
					}else{
						ob.setSize("");
					}
					if(request.getParameter("illustration")!=null){
					ob.setIllustration(request.getParameter("illustration"));
					}else{
						ob.setIllustration("");
					}
					if(request.getParameter("isbn")!=null){
					ob.setIsbn(request.getParameter("isbn"));
					}else{
						ob.setIsbn("");
					}
					if(request.getParameter("bprice")!=null){
					ob.setBprice(request.getParameter("bprice"));
					}
					else{
						ob.setBprice("0");
					}
					if(request.getParameter("copies")!=null){
					ob.setCopies(Integer.parseInt(request.getParameter("copies")));
					}else{
						ob.setCopies(1);
					}
					
					ob.setScript(request.getParameter("script"));
					ob.setLocation(request.getParameter("location"));
					ob.setAvail(request.getParameter("avail"));
					ob.setSubName(request.getParameter("subName"));
					ob.setSubCode(Integer.parseInt(request.getParameter("Sub")));
					ob.setPubName(request.getParameter("pubName"));
					ob.setPubCode(Integer.parseInt(request.getParameter("Pub")));
					ob.setSupName(request.getParameter("supName"));
					ob.setSupCode(Integer.parseInt(request.getParameter("Sup")));
					ob.setBranchName(request.getParameter("branchName"));
					ob.setBranchCode(Integer.parseInt(request
							.getParameter("Branch")));
					ob.setDoc(request.getParameter("doc"));
					ob.setType(request.getParameter("type"));
					ob.setPhysical(request.getParameter("physical"));
					ob.setBinding(request.getParameter("binding"));
					ob.setKeywords(request.getParameter("keywords"));
					ob.setNotes(request.getParameter("notes"));
					ob.setSummary(request.getParameter("summary"));
					ob.setBibliography(request.getParameter("bibliography"));
					ob.setContents(request.getParameter("contents"));
					ob.setVolumeNo(request.getParameter("volumeNo"));
					ob.setPartNo(request.getParameter("partNo"));
					ob.setVolumeTitle(request.getParameter("volumeTitle"));
					ob.setVolumeRes(request.getParameter("volumeRes"));
					ob.setCorAut(request.getParameter("corAut"));
					ob.setCorAdd(request.getParameter("corAdd"));
					ob.setSerAut(request.getParameter("serAut"));
					ob.setSerName(request.getParameter("serName"));
					ob.setSerTitle(request.getParameter("serTitle"));
					ob.setIssn(request.getParameter("issn"));
					ob.setMeeting(request.getParameter("meeting"));
					ob.setSponsor(request.getParameter("sponsor"));
					ob.setVenue(request.getParameter("venue"));
					ob.setRcDate(Security.TextDate_Update(request
							.getParameter("rcDate")));
					ob.setInvNo(request.getParameter("invNo"));
					ob.setInvoiceDate(Security.TextDate_Update(request
							.getParameter("invoiceDate")));
					ob.setCurrency(request.getParameter("currency"));
					ob.setBcost(request.getParameter("bcost"));
					ob.setAcceptPrice(request.getParameter("acceptPrice"));
					ob.setDiscount(Double.parseDouble(request
							.getParameter("discount")));
					ob.setAddfield1(request.getParameter("addfield1"));
					ob.setAddfield2(request.getParameter("addfield2"));
					ob.setAddfield3(request.getParameter("addfield3"));
					ob.setAddfield4(request.getParameter("addfield4"));
					ob.setDeptName(request.getParameter("deptName"));
					ob.setDeptCode(Integer.parseInt(request
							.getParameter("Dept")));
					ob.setPurchaseType(request.getParameter("purchaseType"));
					
					ob.setBudName(request.getParameter("budName"));
					ob.setBudgetCode(Integer.parseInt(request
							.getParameter("Bud")));
					ob.setOtherDate(Security.TextDate_Update(request
							.getParameter("otherDate")));

					request.setAttribute("no_of_copy", request
							.getParameter("copies"));
					
					List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);
					request.setAttribute("bean", ob);  // RK on 04-10-2013
					indexPage = "/Book/index.jsp?check=CopyBook";
					dispatch(request, response, indexPage);

				} else {
					newbookbean = ss.getBookSearch(uaccess,branchID,doctype);

					if (newbookbean != null) {
						
						int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
						if(newbookbean.getBranchCode()==rk || rk==0){
						
						bookbean ob = new bookbean();

						System.out
								.println("=====================update else========================="
										+ uaccess);
						
						
						

						ob.setAccessNo(uaccess);
						ob.setTitle(request.getParameter("title"));
						if ((request.getParameter("author") != null)
								&& (request.getParameter("author") != "")) {
							ob.setAuthor(request.getParameter("author"));
						} else {
							ob.setAuthor("Nil");
						}
						ob.setCallNo(request.getParameter("callNo"));
						ob.setOtherTitle(request.getParameter("otherTitle"));
						ob.setRole(request.getParameter("role"));
						ob.setStateRes(request.getParameter("stateRes"));
						ob.setEdition(request.getParameter("edition"));
						ob.setLanguage(request.getParameter("language"));
						ob.setPlace(request.getParameter("place"));
						ob.setYop(request.getParameter("yop"));
						ob.setPages(request.getParameter("pages"));
						ob.setSize(request.getParameter("size"));
						ob.setIllustration(request.getParameter("illustration"));
						ob.setIsbn(request.getParameter("isbn"));
						ob.setBprice(request.getParameter("bprice"));
						ob.setCopies(Book_copies);
						ob.setScript(request.getParameter("script"));
						ob.setLocation(request.getParameter("location"));
						ob.setAvail(request.getParameter("avail"));
						ob.setSubName(request.getParameter("subName"));
						ob.setSubCode(Integer.parseInt(request.getParameter("Sub")));
						ob.setPubName(request.getParameter("pubName"));
						ob.setPubCode(Integer.parseInt(request.getParameter("Pub")));
						ob.setSupName(request.getParameter("supName"));
						ob.setSupCode(Integer.parseInt(request.getParameter("Sup")));
						ob.setBranchName(request.getParameter("branchName"));
						ob.setBranchCode(Integer.parseInt(request.getParameter("Branch")));
						ob.setDoc(request.getParameter("doc"));
						ob.setType(request.getParameter("type"));
						ob.setPhysical(request.getParameter("physical"));
						ob.setBinding(request.getParameter("binding"));
						ob.setKeywords(request.getParameter("keywords"));
						ob.setNotes(request.getParameter("notes"));
						ob.setSummary(request.getParameter("summary"));
						ob.setBibliography(request.getParameter("bibliography"));
						ob.setContents(request.getParameter("contents"));
//						if(request.getParameter("contents")!=null || !request.getParameter("contents").isEmpty()){
//							ob.setContents(request.getParameter("contents"));//SHEK
//							}else{
//								ob.setContents("");
//							}
						
						
						//ob.setContents(request.getParameter("contents"));
						ob.setVolumeNo(request.getParameter("volumeNo"));
						ob.setPartNo(request.getParameter("partNo"));
						ob.setVolumeTitle(request.getParameter("volumeTitle"));
						ob.setVolumeRes(request.getParameter("volumeRes"));
						ob.setCorAut(request.getParameter("corAut"));
						ob.setCorAdd(request.getParameter("corAdd"));
						ob.setSerAut(request.getParameter("serAut"));
						ob.setSerName(request.getParameter("serName"));
						ob.setSerTitle(request.getParameter("serTitle"));
						ob.setIssn(request.getParameter("issn"));
						ob.setMeeting(request.getParameter("meeting"));
						ob.setSponsor(request.getParameter("sponsor"));
						ob.setVenue(request.getParameter("venue"));
						ob.setRcDate(Security.TextDate_Update(request.getParameter("rcDate")));
						ob.setInvNo(request.getParameter("invNo"));
						ob.setInvoiceDate(Security.TextDate_Update(request.getParameter("invoiceDate")));
						ob.setCurrency(request.getParameter("currency"));
						ob.setBcost(request.getParameter("bcost"));
						ob.setAcceptPrice(request.getParameter("acceptPrice"));
						ob.setDiscount(Double.parseDouble(request.getParameter("discount")));
						ob.setAddfield1(request.getParameter("addfield1"));
						ob.setAddfield2(request.getParameter("addfield2"));
						ob.setAddfield3(request.getParameter("addfield3"));
						ob.setAddfield4(request.getParameter("addfield4"));
						ob.setDeptName(request.getParameter("deptName"));
						ob.setDeptCode(Integer.parseInt(request.getParameter("Dept")));
						ob.setPurchaseType(request.getParameter("purchaseType"));
						ob.setBudName(request.getParameter("budName"));
						ob.setBudgetCode(Integer.parseInt(request.getParameter("Bud")));
						ob.setOtherDate(Security.TextDate_Update(request.getParameter("otherDate")));									
						
						request.setAttribute("bean", ob);
						//indexPage = "/Book/index.jsp?check=updateBook";
						indexPage = "/Book/index.jsp?check=updateBook&doc_Type="+doctype+"";	//shek	
					
						}else
						{
						  indexPage = "/Book/index.jsp?check=NotRightUser";						
						}
						
						List AuthorArrylist = new ArrayList();
						AuthorArrylist=ss.getCurrencyLoad();						
						request.setAttribute("currency", AuthorArrylist);
						dispatch(request, response, indexPage);
					}

					
					else {
						log4jLogger.info("singleBookInsert===========flag====="
								+ flag);

						if (request.getParameter("Sub").equals("1")
								&& request.getParameter("subName")
										.equalsIgnoreCase("Nil")) {

							sub = Integer.parseInt(request.getParameter("Sub"));
						} else {
							sub = ss.getBookSubCode(request
									.getParameter("subName"),branchID);
						}

						if (request.getParameter("Branch").equals("1")
								&& request.getParameter("branchName")
										.equalsIgnoreCase("Nil")) {

							branch = Integer.parseInt(request
									.getParameter("Branch"));
						} else {
							branch = ss.getBookBranchCode(request
									.getParameter("branchName"));
						}

						if (request.getParameter("Dept").equals("1")
								&& request.getParameter("deptName")
										.equalsIgnoreCase("Nil")) {

							dept = Integer.parseInt(request
									.getParameter("Dept"));
						} else {
							dept = ss.getBookDeptCode(request
									.getParameter("deptName"),branchID);
						}

						if (request.getParameter("Sup").equals("2")
								&& request.getParameter("supName")
										.equalsIgnoreCase("Nil")) {

							sup = Integer.parseInt(request.getParameter("Sup"));
						} else {
							sup = ss.getBookSupplierCode(request
									.getParameter("supName"),branchID);
						}
						if (request.getParameter("Pub").equals("1")
								&& request.getParameter("pubName")
										.equalsIgnoreCase("Nil")) {

							pub = Integer.parseInt(request.getParameter("Pub"));
						} else {
							pub = ss.getBookPubCode(request
									.getParameter("pubName"),branchID);
						}
						bookbean ob = new bookbean();
						ob.setAccessNo(uaccess);
						ob.setTitle(request.getParameter("title"));
						if ((request.getParameter("author") != null)
								&& (request.getParameter("author") != "")) {
							ob.setAuthor(request.getParameter("author"));
						} else {
							ob.setAuthor("Nil");
						}

						if(request.getParameter("callNo")!=null){
							ob.setCallNo(request.getParameter("callNo"));
							}else{
								ob.setCallNo("");
							}
						ob.setOtherTitle(request.getParameter("otherTitle"));
						ob.setRole(request.getParameter("role"));
						ob.setStateRes(request.getParameter("stateRes"));
						ob.setEdition(request.getParameter("edition"));
						ob.setLanguage(request.getParameter("language"));
						ob.setPlace(request.getParameter("place"));
						ob.setYop(request.getParameter("yop"));
						ob.setPages(request.getParameter("pages"));
						ob.setSize(request.getParameter("size"));
						ob.setIllustration(request.getParameter("illustration"));
						ob.setIsbn(request.getParameter("isbn"));
						ob.setBprice(request.getParameter("bprice"));
						ob.setCopies(Book_copies);
						ob.setScript(request.getParameter("script"));
						ob.setLocation(request.getParameter("location"));
						ob.setAvail(request.getParameter("avail"));
						ob.setSubCode(sub);
						ob.setPubCode(pub);
						ob.setSupCode(sup);
						ob.setBranchCode(branch);
						ob.setDoc(request.getParameter("doc"));
						ob.setType(request.getParameter("type"));
						ob.setPhysical(request.getParameter("physical"));
						ob.setBinding(request.getParameter("binding"));
						ob.setKeywords(request.getParameter("keywords"));
						ob.setNotes(request.getParameter("notes"));
						ob.setSummary(request.getParameter("summary"));
						ob.setBibliography(request.getParameter("bibliography"));
						if(request.getParameter("contents")!=null){
						ob.setContents(request.getParameter("contents"));
						}else{
							ob.setContents("");
						}
						
						ob.setVolumeNo(request.getParameter("volumeNo"));
						ob.setPartNo(request.getParameter("partNo"));
						ob.setVolumeTitle(request.getParameter("volumeTitle"));
						ob.setVolumeRes(request.getParameter("volumeRes"));
						ob.setCorAut(request.getParameter("corAut"));
						ob.setCorAdd(request.getParameter("corAdd"));
						ob.setSerAut(request.getParameter("serAut"));
						ob.setSerName(request.getParameter("serName"));
						ob.setSerTitle(request.getParameter("serTitle"));
						ob.setIssn(request.getParameter("issn"));
						ob.setMeeting(request.getParameter("meeting"));
						ob.setSponsor(request.getParameter("sponsor"));
						ob.setVenue(request.getParameter("venue"));
						ob.setRcDate(Security.TextDate_Update(request.getParameter("rcDate")));
						ob.setInvNo(request.getParameter("invNo"));
						ob.setInvoiceDate(Security.TextDate_Update(request.getParameter("invoiceDate")));
						ob.setCurrency(request.getParameter("currency"));
						ob.setBcost(request.getParameter("bcost"));
						ob.setAcceptPrice(request.getParameter("acceptPrice"));
						ob.setDiscount(Double.parseDouble(request.getParameter("discount")));
						ob.setAddfield1(request.getParameter("addfield1"));
						ob.setAddfield2(request.getParameter("addfield2"));
						ob.setAddfield3(request.getParameter("addfield3"));
						ob.setAddfield4(request.getParameter("addfield4"));
						ob.setDeptCode(dept);
						ob.setPurchaseType(request.getParameter("purchaseType"));					
						ob.setBudName(request.getParameter("budName"));
						ob.setBudgetCode(Integer.parseInt(request.getParameter("Bud")));
						ob.setOtherDate(Security.TextDate_Update(request.getParameter("otherDate")));
						ob.setauthorvalue(request.getParameter("Author_value"));

						int count = ss.getBookSave(ob);

						List AuthorArrylist = new ArrayList();
						AuthorArrylist=ss.getCurrencyLoad();
						request.setAttribute("currency", AuthorArrylist);
						indexPage = "/Book/index.jsp?check=SingleBookSave";
						dispatch(request, response, indexPage);
					}

				}
			}

			if (flag.equals("MoreCopy")) {
				log4jLogger.info("MoreBookInsert===========flag====="
						+ request.getParameter("accessNo"));

				String acno = request.getParameter("accessNo");
				int ncopy = Integer.parseInt(request.getParameter("copies"));
				// int init_length=acno.length();
				String Nums = "";
				String Chars = "";
				String bookaccno = "";
				String s = "";
				String zero = "";
				boolean temp = true;
				boolean isNumFound = false;
				if (Character.isDigit(acno.charAt(0)))
					isNumFound = true;
				log4jLogger.info("String check1===========flag====="
						+ acno.length());
				for (int l = 0; l < acno.length(); l++) {
					if (Character.isDigit(acno.charAt(l))) {

						char t = acno.charAt(l);
						log4jLogger.info("String tvalue===========flag====="
								+ t);
						if (t != '0' && temp == true) {
							// out.print("value of t"+t);

							s = s + String.valueOf(t);
							log4jLogger
									.info("String svalue===========flag====="
											+ s);
							
							int pos = acno.indexOf(s);
							log4jLogger
									.info("String position===========flag====="
											+ pos);

							s = acno.substring(pos, acno.length());

							temp = false;

						}
						Nums = Nums + acno.charAt(l);
					} else {
						Chars = Chars + acno.charAt(l);
					}
				}
				log4jLogger.info("String check2===========flag====="
						+ request.getParameter("accessNo"));
				String zeros = "";

				for (int n = 0; n < Nums.length(); n++) {
					if (String.valueOf(Nums.charAt(n)).equals("0"))
						zeros = zeros + Nums.charAt(n);
					else {
						s = Nums.substring(n, Nums.length());
						break;
					}
				}

				long Numm = Long.parseLong(Nums.substring(zeros.length(), Nums
						.length()));
				for (int v = 0; v < ncopy; v++) {

					if (isNumFound) {
						if (count(s, v)) {

							if (zeros.equals("")) {

							} else {
								zero = zeros.substring(0, zeros.length() - 1);
							}

							bookaccno = Chars + zero + (Numm + v);
						} else {

							bookaccno = zeros + (Numm + v) + Chars;
						}
					} else {
						if (count(s, v)) {

							if (zeros.equals("")) {

							} else {
								zero = zeros.substring(0, zeros.length() - 1);
							}

							bookaccno = Chars + zero + (Numm + v);

						} else {

							bookaccno = Chars + zeros + (Numm + v);

						}
					}

					newbookbean = ss.getBookSearch(bookaccno,branchID,doctype);
					if (newbookbean != null) {
							
						bookbean ob = new bookbean();

						ob.setAccessNo(bookaccno);
						ob.setTitle(request.getParameter("title"));
						ob.setAuthor(request.getParameter("author"));
						ob.setCallNo(request.getParameter("callNo"));
						ob.setOtherTitle(request.getParameter("otherTitle"));
						ob.setRole(request.getParameter("role"));
						ob.setStateRes(request.getParameter("stateRes"));
						ob.setEdition(request.getParameter("edition"));
						ob.setLanguage(request.getParameter("language"));
						ob.setPlace(request.getParameter("place"));
						ob.setYop(request.getParameter("yop"));
						ob.setPages(request.getParameter("pages"));
						ob.setSize(request.getParameter("size"));
						ob
								.setIllustration(request
										.getParameter("illustration"));
						ob.setIsbn(request.getParameter("isbn"));
						ob.setBprice(request.getParameter("bprice"));
						ob.setCopies(Book_copies);
						ob.setScript(request.getParameter("script"));
						ob.setLocation(request.getParameter("location"));
						ob.setAvail(request.getParameter("avail"));
						ob.setSubName(request.getParameter("subName"));
						ob.setSubCode(Integer.parseInt(request
								.getParameter("Sub")));
						ob.setPubName(request.getParameter("pubName"));
						ob.setPubCode(Integer.parseInt(request
								.getParameter("Pub")));
						ob.setSupName(request.getParameter("supName"));
						ob.setSupCode(Integer.parseInt(request
								.getParameter("Sup")));
						ob.setBranchName(request.getParameter("branchName"));
						ob.setBranchCode(Integer.parseInt(request
								.getParameter("Branch")));
						ob.setDoc(request.getParameter("doc"));
						ob.setType(request.getParameter("type"));
						ob.setPhysical(request.getParameter("physical"));
						ob.setBinding(request.getParameter("binding"));
						ob.setKeywords(request.getParameter("keywords"));
						ob.setNotes(request.getParameter("notes"));
						ob.setSummary(request.getParameter("summary"));
						ob
								.setBibliography(request
										.getParameter("bibliography"));
						ob.setContents(request.getParameter("contents"));
						ob.setVolumeNo(request.getParameter("volumeNo"));
						ob.setPartNo(request.getParameter("partNo"));
						ob.setVolumeTitle(request.getParameter("volumeTitle"));
						ob.setVolumeRes(request.getParameter("volumeRes"));
						ob.setCorAut(request.getParameter("corAut"));
						ob.setCorAdd(request.getParameter("corAdd"));
						ob.setSerAut(request.getParameter("serAut"));
						ob.setSerName(request.getParameter("serName"));
						ob.setSerTitle(request.getParameter("serTitle"));
						ob.setIssn(request.getParameter("issn"));
						ob.setMeeting(request.getParameter("meeting"));
						ob.setSponsor(request.getParameter("sponsor"));
						ob.setVenue(request.getParameter("venue"));
						ob.setRcDate(Security.TextDate_Update(request
								.getParameter("rcDate")));
						ob.setInvNo(request.getParameter("invNo"));
						ob.setInvoiceDate(Security.TextDate_Update(request
								.getParameter("invoiceDate")));
						ob.setCurrency(request.getParameter("currency"));
						ob.setBcost(request.getParameter("bcost"));
						ob.setAcceptPrice(request.getParameter("acceptPrice"));
						ob.setDiscount(Double.parseDouble(request
								.getParameter("discount")));
						ob.setAddfield1(request.getParameter("addfield1"));
						ob.setAddfield2(request.getParameter("addfield2"));
						ob.setAddfield3(request.getParameter("addfield3"));
						ob.setAddfield4(request.getParameter("addfield4"));
						ob.setDeptName(request.getParameter("deptName"));
						ob.setDeptCode(Integer.parseInt(request
								.getParameter("Dept")));
						ob
								.setPurchaseType(request
										.getParameter("purchaseType"));
						ob.setBudName(request.getParameter("budName"));
						ob.setBudgetCode(Integer.parseInt(request
								.getParameter("Bud")));
						ob.setOtherDate(Security.TextDate_Update(request
								.getParameter("otherDate")));

						List AuthorArrylist = new ArrayList();
						AuthorArrylist=ss.getCurrencyLoad();
						request.setAttribute("currency", AuthorArrylist);
						
						request.setAttribute("bean", ob);
						indexPage = "/Book/index.jsp?check=updateBook";
						dispatch(request, response, indexPage);

					} else {
						log4jLogger.info("BookInsert===========flag====="
								+ flag);

						if (request.getParameter("Sub").equals("1")
								&& request.getParameter("subName")
										.equalsIgnoreCase("Nil")) {

							sub = Integer.parseInt(request.getParameter("Sub"));
						} else {
							sub = ss.getBookSubCode(request
									.getParameter("subName"),branchID);
						}

						if (request.getParameter("Branch").equals("1")
								&& request.getParameter("branchName")
										.equalsIgnoreCase("Nil")) {

							branch = Integer.parseInt(request
									.getParameter("Branch"));
						} else {
							branch = ss.getBookBranchCode(request
									.getParameter("branchName"));
						}

						if (request.getParameter("Dept").equals("1")
								&& request.getParameter("deptName")
										.equalsIgnoreCase("Nil")) {

							dept = Integer.parseInt(request
									.getParameter("Dept"));
						} else {
							dept = ss.getBookDeptCode(request
									.getParameter("deptName"),branchID);
						}

						if (request.getParameter("Sup").equals("2")
								&& request.getParameter("supName")
										.equalsIgnoreCase("Nil")) {

							sup = Integer.parseInt(request.getParameter("Sup"));
						} else {
							sup = ss.getBookSupplierCode(request
									.getParameter("supName"),branchID);
						}
						if (request.getParameter("Pub").equals("1")
								&& request.getParameter("pubName")
										.equalsIgnoreCase("Nil")) {

							pub = Integer.parseInt(request.getParameter("Pub"));
						} else {
							pub = ss.getBookPubCode(request
									.getParameter("pubName"),branchID);
						}
						bookbean ob = new bookbean();
						ob.setAccessNo(bookaccno);
						ob.setTitle(request.getParameter("title"));
						ob.setAuthor(request.getParameter("author"));
						ob.setCallNo(request.getParameter("callNo"));
						ob.setOtherTitle(request.getParameter("otherTitle"));
						ob.setRole(request.getParameter("role"));
						ob.setStateRes(request.getParameter("stateRes"));
						ob.setEdition(request.getParameter("edition"));
						ob.setLanguage(request.getParameter("language"));
						ob.setPlace(request.getParameter("place"));
						ob.setYop(request.getParameter("yop"));
						ob.setPages(request.getParameter("pages"));
						ob.setSize(request.getParameter("size"));
						ob
								.setIllustration(request
										.getParameter("illustration"));
						ob.setIsbn(request.getParameter("isbn"));
						ob.setBprice(request.getParameter("bprice"));
						ob.setCopies(Book_copies);
						ob.setScript(request.getParameter("script"));
						ob.setLocation(request.getParameter("location"));
						ob.setAvail(request.getParameter("avail"));
						ob.setSubCode(sub);
						ob.setPubCode(pub);
						ob.setSupCode(sup);
						ob.setBranchCode(branch);
						ob.setDoc(request.getParameter("doc"));
						ob.setType(request.getParameter("type"));
						ob.setPhysical(request.getParameter("physical"));
						ob.setBinding(request.getParameter("binding"));
						ob.setKeywords(request.getParameter("keywords"));
						ob.setNotes(request.getParameter("notes"));
						ob.setSummary(request.getParameter("summary"));
						ob
								.setBibliography(request
										.getParameter("bibliography"));
						ob.setContents(request.getParameter("contents"));
						ob.setVolumeNo(request.getParameter("volumeNo"));
						ob.setPartNo(request.getParameter("partNo"));
						ob.setVolumeTitle(request.getParameter("volumeTitle"));
						ob.setVolumeRes(request.getParameter("volumeRes"));
						ob.setCorAut(request.getParameter("corAut"));
						ob.setCorAdd(request.getParameter("corAdd"));
						ob.setSerAut(request.getParameter("serAut"));
						ob.setSerName(request.getParameter("serName"));
						ob.setSerTitle(request.getParameter("serTitle"));
						ob.setIssn(request.getParameter("issn"));
						ob.setMeeting(request.getParameter("meeting"));
						ob.setSponsor(request.getParameter("sponsor"));
						ob.setVenue(request.getParameter("venue"));
						ob.setRcDate(Security.TextDate_Update(request
								.getParameter("rcDate")));
						ob.setInvNo(request.getParameter("invNo"));
						ob.setInvoiceDate(Security.TextDate_Update(request
								.getParameter("invoiceDate")));
						ob.setCurrency(request.getParameter("currency"));
						ob.setBcost(request.getParameter("bcost"));
						ob.setAcceptPrice(request.getParameter("acceptPrice"));
						ob.setDiscount(Double.parseDouble(request
								.getParameter("discount")));
						ob.setAddfield1(request.getParameter("addfield1"));
						ob.setAddfield2(request.getParameter("addfield2"));
						ob.setAddfield3(request.getParameter("addfield3"));
						ob.setAddfield4(request.getParameter("addfield4"));
						ob.setDeptCode(dept);
						ob
								.setPurchaseType(request
										.getParameter("purchaseType"));
						ob.setBudName(request.getParameter("budName"));
						ob.setBudgetCode(Integer.parseInt(request
								.getParameter("Bud")));
						ob.setOtherDate(Security.TextDate_Update(request
								.getParameter("otherDate")));
						ob.setauthorvalue(request.getParameter("Author_value"));

						int count = ss.getBookSave(ob);

						

					}

				}

				request.setAttribute("no_of_copy", request
						.getParameter("copies"));
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				
				indexPage = "/Book/index.jsp?check=MoreBookSave";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("delete")) {
				log4jLogger.info("===========flag=====" + flag);
				
				String book_mascode = "", book_issuecode = "", book_reservecode = "", book_issuehistorycode = "";
				book_mascode = request.getParameter("accessNo");
				
				newbookbean =new bookbean();
				newbookbean = ss.getBookSearch(book_mascode,branchID,doctype);

				if (newbookbean != null) {					
				int rk=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan
				if(newbookbean.getBranchCode()==rk || rk==0){			
				
				String issueaccno = ss.getIssueCheck(request
						.getParameter("accessNo"));

				if (issueaccno != "") {
					book_issuecode = issueaccno;
				}
				String issuehistoryaccno = ss.getIssueHistoryCheck(request
						.getParameter("accessNo"));

				if (issuehistoryaccno != "") {
					book_issuehistorycode = issuehistoryaccno;
				}
				String reserveaccno = ss.getReserveMasCheck(request
						.getParameter("accessNo"));

				if (reserveaccno != "") {
					book_reservecode = reserveaccno;
				}
				if (book_mascode.equals(book_issuecode)) {
					
					/**List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);*/
					indexPage = "/Book/index.jsp?check=ReferBook";
					//dispatch(request, response, indexPage);
				} else {

					int count = ss.getDeleteBook(request
							.getParameter("accessNo"));
					
					/**List AuthorArrylist = new ArrayList();
					AuthorArrylist=ss.getCurrencyLoad();
					request.setAttribute("currency", AuthorArrylist);*/
					indexPage = "/Book/index.jsp?check=DeleteBook";
					//dispatch(request, response, indexPage);
				}
				if (book_mascode.equals(book_issuehistorycode)) {
					int count = ss.getDeleteHistory(request
							.getParameter("accessNo"));
				}
				if (book_mascode.equals(book_reservecode)) {

					int count = ss.getDeleteReserve(request
							.getParameter("accessNo"));
				}
			}else		{
				indexPage = "/Book/index.jsp?check=NotRightUser";
			}
			}else       {
				indexPage = "/Book/index.jsp?check=FailureBook";
			}
				
				List AuthorArrylist = new ArrayList();
				AuthorArrylist=ss.getCurrencyLoad();
				request.setAttribute("currency", AuthorArrylist);
				dispatch(request, response, indexPage);
			}
		} catch (Exception sss) {
			//throw new ServletException(sss);

		}

		
		
		System.out.println("IndexPage:::::::::::::::::::::::::::::::::::"+indexPage);
	}

	public boolean count(String sa, int as) {
		int len = sa.length();
		int acc = Integer.parseInt(sa);
		int noc = as;
		int tot = acc + noc;
		String total = String.valueOf(tot);
		int len1 = total.length();
		if (len == len1) {
			return false;
		} else {
			return true;
		}
	}

	/***************************************************************************
	 * prepare the sql statement for execution
	 */
	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}

}
