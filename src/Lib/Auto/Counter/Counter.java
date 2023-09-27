package Lib.Auto.Counter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import Common.Security;
import Common.Security_Counter;
import Common.LibraryConstants;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Common.businessutil.rfid.RFIDService;
import Lib.Auto.Account.GateAntenna;
import Lib.Auto.Book.Book;

/**
 * @author Counter
 * 
 *         To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Counter extends HttpServlet implements Serializable, COUNTER_QUERY {
	private static Logger log4jLogger = Logger.getLogger(Counter.class);
	/**
	 * 
	 */

	/*
	 * Unwnated functions by RK 1. findIssuedDetails
	 */

	private static final long serialVersionUID = 1L;
	String availability = "", document = "";
	boolean iss = true;

	String indexPage = null;
	String protocol = "", min = "", flag = "", antenaid = "", temp1 = "",
			pri = "", no_days = "", temp2 = "", valid = "";
	String flag1 = "", flag2 = "";

	public Counter() {
		super();
	}

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
			if (res.equalsIgnoreCase("Failure")) {
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");
				return;
			}

			CirculationService ss = BusinessServiceFactory.INSTANCE
					.getCirculationService();
			PrintWriter out = response.getWriter();
			int Groups = 0;
			CounterBean counterbeanobject = new CounterBean();
			CounterMemberBean beanobject = new CounterMemberBean();
			CounterFineBean beanobject1 = new CounterFineBean();
			ReserveBean resbean = new ReserveBean();

			int branchID = (Integer.parseInt((String.valueOf(session
					.getAttribute("UserBranchID")))));
			String TID = request.getParameter("antennaid");
			request.setAttribute("AID", TID);
			flag = request.getParameter("flag");
			flag1 = request.getParameter("flag1");
			flag2 = request.getParameter("flag2");
			antenaid = request.getParameter("antenaid");
			RFIDBase rb = new RFIDBase();
			GateAntenna ga = new GateAntenna();

			RFIDService ss1 = BusinessServiceFactory.INSTANCE.getRFIDService();
			if (flag.equalsIgnoreCase("counter")) {
				rb.connect();
				rb.startRead();
				out.println(flag);
			}
			if (flag.equalsIgnoreCase("gate")) {
				ga.connect();
				ga.startRead();
				out.println(flag);
			}
			if (flag.equalsIgnoreCase("TagID")) {
				String tagid = request.getParameter("TagId");
				String accno = request.getParameter("AccNo");
				counterbeanobject.setEpc_id(tagid);
				counterbeanobject.setAccno(accno);
				int count = ss1.MergeTag(counterbeanobject);
				if (count == 2) {
					indexPage = "/Counter/test.jsp?flag=TagExists&details=mergedetails";
				} else {
					indexPage = "/Counter/test.jsp?flag=TagSave&details=mergedetails";
				}
				String title = ss1.tagtitle(counterbeanobject);
				counterbeanobject.setTitle(title);
				request.setAttribute("bean", counterbeanobject);

				dispatch(request, response, indexPage);
			}
			if (flag.equalsIgnoreCase("TagIDUpdate")) {
				String tagid = request.getParameter("TagId");
				String accno = request.getParameter("AccNo");
				counterbeanobject.setEpc_id(tagid);
				counterbeanobject.setAccno(accno);
				int count = ss1.MergeTagUpdate(counterbeanobject);
				String title = ss1.tagtitle(counterbeanobject);
				counterbeanobject.setTitle(title);
				request.setAttribute("bean", counterbeanobject);
				indexPage = "/Counter/test.jsp?flag=TagUpdate&details=mergedetails";
				dispatch(request, response, indexPage);
			}
			if (flag.equalsIgnoreCase("CardUID")) {
				String carduid = request.getParameter("CardUid");
				String mcode = request.getParameter("mcode");
				counterbeanobject.setCarduid(carduid);
				counterbeanobject.setMcode(mcode);
				int count = ss1.MergeCard(counterbeanobject);
				String mname = ss1.CardMname(counterbeanobject);
				counterbeanobject.setMname(mname);
				request.setAttribute("bean", counterbeanobject);
				indexPage = "/Counter/cardindex.jsp?flag=CardSave&details=mergedetails";
				dispatch(request, response, indexPage);
			}
			if (flag.equals("reset")) {
				log4jLogger.info("clear===========flag=====" + flag);
				indexPage = "/Counter/test.jsp";
				dispatch(request, response, indexPage);
			}
			if (flag.equals("resetcard")) {
				log4jLogger.info("clear===========flag=====" + flag);
				indexPage = "/Counter/cardindex.jsp";
				dispatch(request, response, indexPage);
			}

			// if (!flag.equalsIgnoreCase("clear")) {
			// if (request.getParameter("barcode").equals("ISSUE")) {
			// flag = "issue";
			// } else if (request.getParameter("barcode").equals("RETURN")) {
			// flag = "return";
			// } else if (request.getParameter("barcode").equals("RENEW")) {
			// flag = "renew";
			// } else if (request.getParameter("barcode").equals("RESERVE")) {
			// flag = "reserve";
			// } else if (request.getParameter("barcode").equals("RE-CANCEL")) {
			// flag = "rescancel";
			// }
			// }

			if (flag.equals("") || flag.isEmpty()) {
				log4jLogger.info("===========Empty Flag=====" + flag);

				counterbeanobject.setPhoto1(null);
				indexPage = "/Counter/index.jsp";
				dispatch(request, response, indexPage);

			}

			if (flag.equals("clear")) {
				log4jLogger.info("clear===========flag=====" + flag);

				counterbeanobject.setPhoto1(null);
				indexPage = "/Counter/index.jsp";
				dispatch(request, response, indexPage);
			}
			// --------------------------------------member-------------------------------------------------------------*/
			if (flag.equals("member")) {
				log4jLogger.info("member===========flag====="
						+ request.getParameter("mcode"));
				counterbeanobject = new CounterBean();

				counterbeanobject = ss.getCounterMember(
						request.getParameter("mcode").toString().trim(),
						branchID);

				if (!counterbeanobject.getGroup().trim().isEmpty()
						&& counterbeanobject.getGroup().trim() != null) { // For
																			// //
																			// Titan

					// if (counterbeanobject.getBranchID() == branchID ||
					// branchID == 0 || branchID == 2 ||
					// counterbeanobject.getDeptBranch() == branchID) { // RK
					// RECENT 26-11-2014

					if (counterbeanobject.getBranchID() == branchID
							|| branchID == 0 || branchID == 2 || branchID == 3
							|| counterbeanobject.getDeptBranch() == branchID) { // RK
																				// RECENT
																				// 26-11-2014

						Groups = ss.getCounterGroup(counterbeanobject
								.getGroup().trim(),branchID);

						String SLock = "", courseYear = "";
						SLock = counterbeanobject.getSLock().trim();
						courseYear = counterbeanobject.getYear().trim();
						if (SLock.trim().equals("YES")) {
							indexPage = "/Counter/index.jsp?Message=MemberLock";
							request.setAttribute("SLock", counterbeanobject
									.getMcode().trim());
						} else if (SLock.trim().equals("CLEARANCE")) {
							indexPage = "/Counter/index.jsp?Message=Clearance";
							request.setAttribute("SLock", counterbeanobject
									.getMcode().trim());
						} else if (courseYear.equals("6")) {
							request.setAttribute("MemberPassout",
									counterbeanobject.getMcode().trim());
							indexPage = "/Counter/index.jsp?Message=MemberPassout";
						} else {
							ArrayList issue_details = (ArrayList) ss
									.getIssueDetailsMember(request
											.getParameter("mcode").toString()
											.trim(), branchID);
							counterbeanobject.setCounterList(issue_details);

							ArrayList reserve_details1 = (ArrayList) ss
									.getReserveDetailsMember(request
											.getParameter("mcode").toString()
											.trim(), branchID);
							int reserve_details = reserve_details1.size();
							counterbeanobject
									.setCunterList_RESERVE(reserve_details1);

							request.setAttribute("bean", counterbeanobject);

							if ((Groups > 0) && (reserve_details > 0)) {

								indexPage = "/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS";

							} else if (Groups > 0) {

								indexPage = "/Counter/index.jsp?flag=member&detils=ISSUEDEATILS";
							} else {
								indexPage = "/Counter/index.jsp?Message=MemberNotFound";
							}
						}

					} else {
						counterbeanobject.setPhoto1(null);
						indexPage = "/Counter/index.jsp?Message=OtherBranchMember";
					}
				} else {
					indexPage = "/Counter/index.jsp?Message=MemberNotFound";
				}

				dispatch(request, response, indexPage);
			}

			// --------------------------------------BOOK-------------------------------------------------------------*/
			if (flag.equals("book")) {
				log4jLogger.info("book===========flag====="
						+ request.getParameter("accno"));

				beanobject = new CounterMemberBean();
				counterbeanobject = new CounterBean();

				beanobject = ss.getCounterBook(request.getParameter("accno")
						.toString().trim(), request.getParameter("doc")
						.toString().trim(), request.getParameter("mcode"),branchID);
				String acc_no = beanobject.getAccno();

				String Mem_code = request.getParameter("mcode").toString()
						.trim();
				String doc_type = beanobject.getDoc().toString().trim();

				if (acc_no != "" && acc_no != null) {

					if (beanobject.getBranchID() == branchID || branchID == 0
							|| doc_type.equalsIgnoreCase("JOURNAL")) {

						if (beanobject.getAvail().equals("ISSUED")
								|| beanobject.getAvail().equals("REFISSUED")
								|| beanobject.getAvail().equals("TRANSISSUED")) { // For
																					// Reference
																					// Book
																					// Issue

							int chk_code = ss.getIssueCheck(request
									.getParameter("accno").toString().trim(),
									request.getParameter("mcode").toString()
											.trim(),branchID);

							if (chk_code <= 0) {
								if (Mem_code != null && !Mem_code.isEmpty()) {
									counterbeanobject = ss.getMemberLoad(
											request.getParameter("mcode")
													.toString().trim(),
											doc_type, branchID,
											beanobject.getAvail());

									request.setAttribute("bean",
											counterbeanobject);

									indexPage = "/Counter/index.jsp?flag=book";
								} else {
									counterbeanobject = ss
											.getCounterIssueCheck(request
													.getParameter("accno")
													.toString().trim(),
													branchID);
									String issue_check = counterbeanobject
											.getIssue_Check();
									String memberCode = counterbeanobject
											.getMcode();

									request.setAttribute("bean",
											counterbeanobject);

									if (issue_check == "ITRUE") {
										counterbeanobject = new CounterBean();
										counterbeanobject = ss
												.getCounterMember(memberCode
														.toString().trim(),
														branchID);

										if (counterbeanobject.getSLock().trim()
												.equals("YES")) {
											indexPage = "/Counter/index.jsp?Message=MemberLock";
											request.setAttribute("SLock",
													counterbeanobject
															.getMcode().trim());
										} else {
											indexPage = "/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook&Reservedetils=RESERVEDEATILS";
										}
									} else {
										indexPage = "/Counter/index.jsp?flag=book";
									}
								}
							}

							else {
								counterbeanobject = ss.getCounterIssueCheck(
										request.getParameter("accno")
												.toString().trim(), branchID);
								String issue_check = counterbeanobject
										.getIssue_Check();
								String memberCode = counterbeanobject
										.getMcode();

								request.setAttribute("bean", counterbeanobject);

								if (issue_check == "ITRUE") {
									counterbeanobject = new CounterBean();
									counterbeanobject = ss.getCounterMember(
											memberCode.toString().trim(),
											branchID);

									if (counterbeanobject.getSLock().trim()
											.equals("YES")) {
										indexPage = "/Counter/index.jsp?Message=MemberLock";
										request.setAttribute("SLock",
												counterbeanobject.getMcode()
														.trim());
									} else {
										indexPage = "/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&load=loadbook&Reservedetils=RESERVEDEATILS";
									}
								} else {
									indexPage = "/Counter/index.jsp?flag=book";
								}
							}
						} else {
							if ((request.getParameter("mcode").equals(""))
									&& (counterbeanobject.getAvail()
											.equals("YES"))) {
								indexPage = "/Counter/index.jsp?load=loadbook&Reservedetils=RESERVEDEATILS";
							} else if (request.getParameter("mcode").equals("")) {
								indexPage = "/Counter/index.jsp?flag=book";
							}

							else { // Checked rk.

								counterbeanobject = ss.getMemberLoad(request
										.getParameter("mcode").toString()
										.trim(), doc_type, branchID,
										beanobject.getAvail());

								ArrayList issue_details = (ArrayList) ss
										.getIssueDetailsMember(request
												.getParameter("mcode")
												.toString().trim(), branchID);
								counterbeanobject.setCounterList(issue_details);
								ArrayList reerve_details1 = (ArrayList) ss
										.getReserveDetailsMember(request
												.getParameter("mcode")
												.toString().trim(), branchID);
								counterbeanobject
										.setCunterList_RESERVE(reerve_details1);
								int reserve_details = reerve_details1.size();
								request.setAttribute("bean", counterbeanobject);

								if (reserve_details > 0) {
									indexPage = "/Counter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS";
								} else {
									indexPage = "/Counter/index.jsp?flag=member&load=loadbook&detils=ISSUEDEATILS";
								}
							}
						}

					} else {
						log4jLogger
								.info("777777777777777 ===========Wrong Branch Book ================ 777777777777777");

						if (Mem_code != null && !Mem_code.isEmpty()) // For
																		// Titan
						{
							counterbeanobject = new CounterBean();
							counterbeanobject = ss.getMemberLoad(request
									.getParameter("mcode").toString().trim(),
									doc_type, branchID, beanobject.getAvail());

							ArrayList issue_details = (ArrayList) ss
									.getIssueDetailsMember(request
											.getParameter("mcode").toString()
											.trim(), branchID);
							counterbeanobject.setCounterList(issue_details);
							ArrayList reerve_details1 = (ArrayList) ss
									.getReserveDetailsMember(request
											.getParameter("mcode").toString()
											.trim(), branchID);
							counterbeanobject
									.setCunterList_RESERVE(reerve_details1);
							int reserve_details = reerve_details1.size();
							request.setAttribute("bean", counterbeanobject);

							indexPage = "/Counter/index.jsp?flag=member&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS&Message=OtherBranchBook";

						} else {
							indexPage = "/Counter/index.jsp?Message=OtherBranchBook1";
						}

					}
				} else {
					if (Mem_code != null && !Mem_code.isEmpty()) {

						counterbeanobject = ss.getMemberLoad(request
								.getParameter("mcode").toString().trim(),
								doc_type, branchID, beanobject.getAvail());

						int issue_details1 = counterbeanobject.getCountperiod();

						if (counterbeanobject.getMcode() != ""
								&& counterbeanobject.getMcode() != null) {

							ArrayList Issue_Dtls = (ArrayList) ss
									.getIssueDetailsMember(request
											.getParameter("mcode").toString()
											.trim(), branchID);
							counterbeanobject.setCounterList(Issue_Dtls);
						}
						request.setAttribute("bean", counterbeanobject);

						if (issue_details1 > 0) {
							if (counterbeanobject.getBranchID() == branchID
									|| branchID == 2
									|| counterbeanobject.getDeptBranch() == branchID
									|| branchID == 0) {
								log4jLogger
										.info("333333333333 ===========Inside Branch Issue Member ================ 33333333333");
								indexPage = "/Counter/index.jsp?flag=member&Message=BookNotbook1&detils=ISSUEDEATILS";
							} else {
								log4jLogger
										.info("33333333 =========== Wrong Branch Member ================ 3333333");
								counterbeanobject.setPhoto1(null);
								indexPage = "/Counter/index.jsp?Message=OtherBranchMember";
							}
						} else {
							indexPage = "/Counter/index.jsp?Message=BookNotbook1";
						}

					} else {
						indexPage = "/Counter/index.jsp?Message=BookNotbook";
					}

				}
				request.setAttribute("beanmember", beanobject);

				dispatch(request, response, indexPage);
			}

			// BOOK
			// ISSUE-----------------------------------------------------------------------------------//

			if (flag.equals("issue")) {
				log4jLogger.info("book issue counter===========flag====="
						+ flag);
				valid = Security_Counter.TextDate_Insert(request
						.getParameter("validDate"));
				String today = Security_Counter.TodayDate();
				String date_valid = ss.getValidDate(Security_Counter
						.TextDate_Insert(request.getParameter("validDate")));

				counterbeanobject = new CounterBean();

				if (date_valid != "") {
					no_days = date_valid;
				}
				String memb_code = ss.getMemberCode(request
						.getParameter("mcode"),branchID);

				if (memb_code != "") {
					iss = true;
				} else {
					indexPage = "/Counter/index.jsp?Message=validMem";
					iss = false;
				}

				int s = Integer.parseInt(no_days);

				if (s < 0) {
					indexPage = "/Counter/index.jsp?Message=valid";
				} else if (iss) {
					beanobject = new CounterMemberBean();
					beanobject = ss.getIssueMasCheck(
							request.getParameter("accno").toString().trim(),
							request.getParameter("doc").toString().trim(),branchID);

					if (beanobject.getAvail() != "") {
						availability = beanobject.getAvail();
						document = beanobject.getDoc();
					} else {
						indexPage = "/Counter/index.jsp?Message=booknot";
					}

					if (availability.equals("ISSUED")
							|| availability.equals("REFISSUED")
							|| availability.equals("TRANSISSUED")
							|| (!request.getParameter("doc").equals(document))) {
						indexPage = "/Counter/index.jsp?Message=alreadyissued";
					}
					/**
					 * if (availability.equals("ISSUED") ||
					 * (!request.getParameter("doc").equals(document))) {
					 * 
					 * indexPage = "/Counter/index.jsp?Message=alreadyissued"; }
					 */

					else {
						String reserve_check = ss.getReserveMasCheck(request
								.getParameter("accno"));
						if (reserve_check != "") {
							min = reserve_check;
						}
						beanobject = new CounterMemberBean();
						beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setAccno(request.getParameter("accno"));
						beanobject
								.setIdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("idate")));
						beanobject
								.setDdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("ddate")));
						beanobject.setTitle(request.getParameter("title"));
						beanobject.setAuthor(String.valueOf(session
								.getAttribute("UserID")));
						beanobject.setDoc(request.getParameter("doc"));
						beanobject.setMname(request.getParameter("mname"));
						beanobject.setBranchID(branchID);// deptChanges

						log4jLogger
								.info(":::::::::::::::BranchCode:::::::::::::"
										+ beanobject.getBranchID());
						resbean = new ReserveBean();
						resbean = ss.getReserveMssSelect(request
								.getParameter("accno"));

						if (resbean.getMcode() != "") {
							temp1 = resbean.getMcode();
							pri = resbean.getRcode();
							temp2 = resbean.getMname();
							if ((temp1.equalsIgnoreCase(request
									.getParameter("mcode")) && (pri.equals(min)))) // Ignore

							{

								beanobject.setMcode(request
										.getParameter("mcode"));
								beanobject.setAccno(request
										.getParameter("accno"));
								beanobject.setBranchID(branchID);
								

								int res_delete = ss
										.getReserveMssDelete(beanobject);
								int doc_issue = ss
										.getIssueMasInsert(beanobject);

								if (doc_issue > 0) {

									indexPage = "/Counter/index.jsp?Message=issued&detils=ISSUEDEATILS";
								}

							}

							else {
								request.setAttribute("temp1", temp1);
								request.setAttribute("temp2", temp2);
								int issue_details = ss
										.getIssuedDetails(beanobject);

								indexPage = "/Counter/index.jsp?Message=issue_error&detils=ISSUEDEATILS";
							}
						} else {

							int doc_issue = ss.getIssueMasInsert(beanobject);

							if (doc_issue > 0) {
								indexPage = "/Counter/index.jsp?Message=issued&detils=ISSUEDEATILS&avoid=error";
							}

						}
					}

				}

				counterbeanobject = ss.getCounterMember(
						request.getParameter("mcode").toString().trim(),
						branchID);
				ArrayList issue_details = (ArrayList) ss.getIssueDetailsMember(
						request.getParameter("mcode").toString().trim(),
						branchID);
				counterbeanobject.setCounterList(issue_details);

				request.setAttribute("bean", counterbeanobject);

				dispatch(request, response, indexPage);

			}

			// --------------------------------------RETURN------------------------------------------------------------*/

			if (flag.equals("return")) {
				log4jLogger.info("book return===========flag=====" + flag);
				counterbeanobject = new CounterBean();

				beanobject = new CounterMemberBean();
				beanobject.setMcode(request.getParameter("mcode"));
				beanobject.setAccno(request.getParameter("accno"));
				beanobject.setIdate(Security_Counter.TextDate_Insert(request
						.getParameter("idate")));
				beanobject.setDdate(Security_Counter.TextDate_Insert(request
						.getParameter("ddate")));
				beanobject.setRdate(Security_Counter.TextDate_Insert(request
						.getParameter("rdate")));
				beanobject.setTitle(request.getParameter("title"));
				beanobject.setAuthor(String.valueOf(session
						.getAttribute("UserID")));
				beanobject.setDoc(request.getParameter("doc"));
				beanobject.setMname(request.getParameter("mname"));
				beanobject.setTemp(Double.parseDouble(request
						.getParameter("fine")));
				
				beanobject.setBranchID(branchID);

				counterbeanobject = ss.getDocmentReturn(beanobject);

				int doc_return = counterbeanobject.getDoc_Return();

				if (doc_return > 0) {

					Double Temp = Double.parseDouble(request
							.getParameter("fine"));
					if (Temp.doubleValue() == 0.0) {

						indexPage = "/Counter/index.jsp?Message=return&detils=ISSUEDEATILS";
					} else {

						session.setAttribute("FINE", Temp);

						beanobject = new CounterMemberBean();
						beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setAccno(request.getParameter("accno"));
						beanobject
								.setIdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("idate")));
						beanobject
								.setDdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("ddate")));
						beanobject
								.setRdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("rdate")));
						beanobject.setTitle(request.getParameter("title"));
						beanobject.setAuthor(String.valueOf(session
								.getAttribute("UserID")));
						beanobject.setDoc(request.getParameter("doc"));
						beanobject.setMname(request.getParameter("mname"));
						beanobject.setTemp(Double.parseDouble(request
								.getParameter("fine")));
						beanobject.setBranchID(branchID);

						beanobject1 = new CounterFineBean();

						beanobject1.setMcode(request.getParameter("mcode"));
						beanobject1.setAccno(request.getParameter("accno"));
						beanobject1
								.setIdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("idate")));
						beanobject1
								.setDdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("ddate")));
						beanobject1
								.setRdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("rdate")));
						beanobject1.setTitle(request.getParameter("title"));
						beanobject1.setAuthor(String.valueOf(session
								.getAttribute("UserID")));
						beanobject1.setDoc(request.getParameter("doc"));
						beanobject1.setMname(request.getParameter("mname"));
						beanobject1.setTemp(Double.parseDouble(request
								.getParameter("fine")));
						beanobject1.setBranchID(branchID);

						request.setAttribute("beanobject", beanobject1);

						int doc_fine = ss.getDocmentFine(beanobject);

						indexPage = "/Counter/index.jsp?flag=freturn&detils=ISSUEDEATILS";
					}
				} else {
					indexPage = "/Counter/index.jsp?flag=noissue&detils=ISSUEDEATILS";
				}

				counterbeanobject = ss.getCounterMember(
						request.getParameter("mcode").toString().trim(),
						branchID);

				ArrayList issue_details = (ArrayList) ss.getIssueDetailsMember(
						request.getParameter("mcode").toString().trim(),
						branchID);
				counterbeanobject.setCounterList(issue_details);

				request.setAttribute("bean", counterbeanobject);

				dispatch(request, response, indexPage);

			}
			// -----------------------------------RESERVE----------------------------------------------------//
			if (flag.equals("reserve")) {
				log4jLogger.info("book reserve===========flag=====" + flag);
				counterbeanobject = new CounterBean();

				int cnt = 0;

				int select_member = ss.getMemberMasSelect(request
						.getParameter("mcode").toString().trim(),branchID);

				if (select_member > 0) {

					beanobject = new CounterMemberBean();
					beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(request.getParameter("accno"));
					int issue_only = ss.getIssueMasSelect(beanobject);

					if (issue_only > 0) {
						indexPage = "/Counter/index.jsp?Message=reser&detils=ISSUEDEATILS";
					} else {
						beanobject = new CounterMemberBean();
						beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setAccno(request.getParameter("accno"));
						int reserve_only = ss.getReserveMasSelect(beanobject);
						if (reserve_only > 0) {

							indexPage = "/Counter/index.jsp?Message=res_error&detils=ISSUEDEATILS";

						} else {
							int reserve_count = ss.getReserveMasCount(request
									.getParameter("mcode").trim());
							counterbeanobject = new CounterBean();
							counterbeanobject = ss.getCounterMember(request
									.getParameter("mcode").toString().trim(),
									branchID); // RK
							// Start
							// 17-09-2014

							if (reserve_count < counterbeanobject.getReserve()) {
								beanobject = new CounterMemberBean();
								beanobject.setMcode(request
										.getParameter("mcode"));
								beanobject.setAccno(request
										.getParameter("accno"));
								beanobject.setDoc(request.getParameter("doc"));
								beanobject.setRdate(Security_Counter
										.TextDate_Insert(request
												.getParameter("rdate")));
								int reserve_save = ss
										.getReserveMasSave(beanobject);
								indexPage = "/Counter/index.jsp?Message=reserved&Reservedetils=RESERVEDEATILS&detils=ISSUEDEATILS";
							} else {
								indexPage = "/Counter/index.jsp?Message=maxreserved&Reservedetils=RESERVEDEATILS&detils=ISSUEDEATILS";
							}
						}
					}
					counterbeanobject = ss.getCounterMember(request
							.getParameter("mcode").toString().trim(), branchID);

					ArrayList issue_details = (ArrayList) ss
							.getIssueDetailsMember(request
									.getParameter("mcode").toString().trim(),
									branchID);
					counterbeanobject.setCounterList(issue_details);
					ArrayList reerve_details = (ArrayList) ss
							.getReserveDetailsMember(
									request.getParameter("mcode").toString()
											.trim(), branchID);
					counterbeanobject.setCunterList_RESERVE(reerve_details);

					request.setAttribute("bean", counterbeanobject);
				} else {
					indexPage = "/Counter/index.jsp?Message=MemberNotFound";
				}
				dispatch(request, response, indexPage);
			}
			// ----------------------------------*-------Rescancel-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\\

			if (flag.equals("rescancel")) {
				log4jLogger.info("book rescancel===========flag=====" + flag);
				counterbeanobject = new CounterBean();

				beanobject = new CounterMemberBean();
				beanobject.setMcode(request.getParameter("mcode"));
				beanobject.setAccno(request.getParameter("accno"));
				int reserve_only = ss.getReserveMasSelect(beanobject);

				if (reserve_only > 0) {
					beanobject = new CounterMemberBean();
					beanobject.setMcode(request.getParameter("mcode"));
					beanobject.setAccno(request.getParameter("accno"));
					int res_delete = ss.getReserveMssDelete(beanobject);

					indexPage = "/Counter/index.jsp?Message=rescancel&detils=ISSUEDEATILS&Reservedetils=RESERVEDEATILS";
				} else {

					indexPage = "/Counter/index.jsp?Message=rescancel_error&detils=ISSUEDEATILS";
				}
				counterbeanobject = ss.getCounterMember(
						request.getParameter("mcode").toString().trim(),
						branchID);

				ArrayList issue_details = (ArrayList) ss.getIssueDetailsMember(
						request.getParameter("mcode").toString().trim(),
						branchID);
				counterbeanobject.setCounterList(issue_details);

				ArrayList reerve_details = (ArrayList) ss
						.getReserveDetailsMember(request.getParameter("mcode")
								.toString().trim(), branchID);
				counterbeanobject.setCunterList_RESERVE(reerve_details);
				request.setAttribute("bean", counterbeanobject);

				dispatch(request, response, indexPage);
			}

			// -------------------------- Payment Clearance
			// ----------------------------------------------------//

			if (flag.equals("savefine_payment")) {
				log4jLogger.info("Payment ===========flag=====" + flag);

				counterbeanobject = new CounterBean();
				beanobject = new CounterMemberBean();
				beanobject1 = new CounterFineBean();

				beanobject1.setMcode(request.getParameter("mcode"));
				beanobject1.setAccno(request.getParameter("accno"));
				beanobject1.setRdate(request.getParameter("rdate"));
				beanobject1.setDoc(request.getParameter("doc"));
				beanobject1.setTemp(Double.parseDouble(request
						.getParameter("fine")));
				beanobject1.setBranchID(branchID);

				beanobject.setMcode(beanobject1.getMcode());
				beanobject.setAccno(beanobject1.getAccno());
				beanobject.setRdate(beanobject1.getRdate());
				beanobject.setDoc(beanobject1.getDoc());
				beanobject.setTemp(beanobject1.getTemp());
				beanobject.setAuthor(String.valueOf(session
						.getAttribute("UserID")));
				beanobject.setBranchID(branchID);

				int issue_details1 = ss.getFineDetail(beanobject);

				if (issue_details1 > 0) {

					counterbeanobject = ss.getCounterMember(beanobject
							.getMcode().toString().trim(), branchID);
					ArrayList issue_details = (ArrayList) ss
							.getIssueDetailsMember(request
									.getParameter("mcode").toString().trim(),
									branchID);
					counterbeanobject.setCounterList(issue_details);
					request.setAttribute("bean", counterbeanobject);
					if (flag1.equalsIgnoreCase("renew")) {
						indexPage = "/Counter/index.jsp";
					} else
						indexPage = "/Counter/index.jsp?flag=rkfine&detils=ISSUEDEATILS";
				} else {
					indexPage = "/Counter/index.jsp?";
					// return;
				}

				dispatch(request, response, indexPage);
			}

			// --------------------------RENEW BOOK
			// ----------------------------------------------------//

			if (flag.equals("renew")) {

				log4jLogger.info("book renew===========flag=====" + flag);
				counterbeanobject = new CounterBean();

				String DueDate = "";
				String no_of_days = "";
				String sql = "";
				int no = 1;
				String ddate = "";
				String Rdate = "";
				String Ddate = "";
				int n = 0;
				int Holiday_Check = 0, cal = 0, Holiday = 0;
				double temp = 0;

				int select_member = ss.getMemberMasSelect(request
						.getParameter("mcode").toString().trim(),branchID);

				if (select_member > 0) {

					beanobject = new CounterMemberBean();

					beanobject.setMcode(request.getParameter("mcode")
							.toString().trim());
					beanobject.setAccno(request.getParameter("accno"));
					beanobject.setBranchID(branchID);
					
					log4jLogger.info("Branch Code" + beanobject.getBranchID());
					
					int renewcheck = ss.getRenewCheck(beanobject);

					if (renewcheck == 0) {

						counterbeanobject = ss.getCounterMember(request
								.getParameter("mcode").toString().trim(),
								branchID);
						ArrayList issue_details = (ArrayList) ss
								.getIssueDetailsMember(
										request.getParameter("mcode")
												.toString().trim(), branchID);
						counterbeanobject.setCounterList(issue_details);
						request.setAttribute("bean", counterbeanobject);

						indexPage = "/Counter/index.jsp?flag=noissue&detils=ISSUEDEATILS";
					} else if (renewcheck == 2) {

						counterbeanobject = ss.getCounterMember(request
								.getParameter("mcode").toString().trim(),
								branchID);
						ArrayList issue_details = (ArrayList) ss
								.getIssueDetailsMember(
										request.getParameter("mcode")
												.toString().trim(), branchID);
						counterbeanobject.setCounterList(issue_details);
						request.setAttribute("bean", counterbeanobject);

						indexPage = "/Counter/index.jsp?flag=reservedbook&detils=ISSUEDEATILS";
					} else {

						beanobject = ss.getIssueMas(request
								.getParameter("accno"));

						if (beanobject.getGroup() != "") {
							DueDate = beanobject.getDdate();
							Groups = Integer.parseInt(beanobject.getGroup());

						}

						java.util.StringTokenizer stz_du = new java.util.StringTokenizer(
								Security_Counter.TodayDate(), "-");
						int diy = Integer.parseInt(stz_du.nextToken());
						int dim = Integer.parseInt(stz_du.nextToken());
						int did = Integer.parseInt(stz_du.nextToken());
						Rdate = diy + "-" + dim + "-" + did;

						java.util.StringTokenizer stz_du1 = new java.util.StringTokenizer(
								DueDate, "-");
						int diy1 = Integer.parseInt(stz_du1.nextToken());
						int dim1 = Integer.parseInt(stz_du1.nextToken());
						int did1 = Integer.parseInt(stz_du1.nextToken());
						ddate = diy1 + "-" + dim1 + "-" + did1;

						beanobject.setRdate(request.getParameter("rdate"));
						beanobject.setDdate(request.getParameter("ddate"));
						beanobject.setMcode(request.getParameter("mcode"));
						beanobject.setDoc(request.getParameter("doc"));

						beanobject.setAvail(request.getParameter("avail")); // For
																			// Reference
																			// Issue

						beanobject.setBranchID(branchID);

						counterbeanobject = ss.getNumberofDays(beanobject);

						if (counterbeanobject.getAccno() != null) {

							no_of_days = counterbeanobject.getTitle();
							n = Integer.parseInt(no_of_days);
							log4jLogger.info("++++++++++++++++++++" + n);

							beanobject.setRissued(Groups);
							beanobject.setBissued(n);
							counterbeanobject = ss.getFineCall(beanobject); // Please
																			// add
																			// CounterBean
																			// here
																			// for
																			// fine
																			// Amount

							Double Temp = Double.parseDouble(request
									.getParameter("fine"));

							if (((n < 0) || (n == 0)) || (Temp == 0.0)) {

								n = counterbeanobject.getCountperiod();
								Ddate = Security_Counter
										.TextDate_Insert(counterbeanobject
												.getCalldate());

								beanobject.setRdate(Rdate);
								beanobject.setDdate(Ddate);
								beanobject.setMcode(request
										.getParameter("mcode"));
								beanobject.setDoc(request.getParameter("doc"));
								beanobject.setTperiod(Groups);
								beanobject.setAccno(request
										.getParameter("accno"));

								beanobject.setValidDate(Security_Counter
										.TextDate_Insert(request.getParameter(
												"ddate").toString()));
								
								beanobject.setBranchID(branchID);

								beanobject = ss
										.getUpdateRenewMasNofine(beanobject);

								// Changed by RK

								if (beanobject.getSperiod() > 0) {

									beanobject.setRdate(Rdate);
									beanobject.setDdate(Ddate);
									beanobject.setMcode(request
											.getParameter("mcode"));
									beanobject.setDoc(request
											.getParameter("doc"));
									beanobject.setTperiod(Groups);
									beanobject.setAccno(request
											.getParameter("accno"));
									beanobject.setTeligible(beanobject
											.getTeligible());
									beanobject.setIdate(request
											.getParameter("idate"));
									beanobject.setTitle(String.valueOf(session
											.getAttribute("UserID")));

									beanobject.setValidDate(Security_Counter
											.TextDate_Insert(request
													.getParameter("ddate")
													.toString()));
									beanobject.setBranchID(branchID);

									int renew = ss
											.getUpdateRenewMas(beanobject);

									indexPage = "/Counter/index.jsp?Message=renew&detils=ISSUEDEATILS";
								} else {
									indexPage = "/Counter/index.jsp?Message=nrenew&mcode=membercode";
								}

							} else {

								// Holiday=n;
								beanobject.setRissued(Groups);
								beanobject.setBissued(n);
								n = counterbeanobject.getCountperiod();

								Ddate = Security_Counter
										.TextDate_Insert(counterbeanobject
												.getCalldate());

								// counterbeanobject=ss.getFineCall(beanobject);
								// // Please add CounterBean here for fine
								// Amount
								// Temp = counterbeanobject.getTemp();

								session.setAttribute("FINE", Temp);

								String doctype = request.getParameter("doc");
								out.print("Doc Type" + doctype);

								beanobject.setRdate(Rdate);
								beanobject.setDdate(Ddate);
								beanobject.setMcode(request
										.getParameter("mcode"));
								beanobject.setDoc(request.getParameter("doc"));
								beanobject.setTperiod(Groups);
								beanobject.setAccno(request
										.getParameter("accno"));

								beanobject.setValidDate(Security_Counter
										.TextDate_Insert(request.getParameter(
												"ddate").toString()));

								beanobject = ss
										.getUpdateRenewMasNofine(beanobject);

								// Changed by RK

								if (beanobject.getSperiod() > 0) {

									beanobject.setRdate(Rdate);
									beanobject.setDdate(Ddate);
									beanobject.setMcode(request
											.getParameter("mcode"));
									beanobject.setDoc(request
											.getParameter("doc"));
									beanobject.setTperiod(Groups);
									beanobject.setAccno(request
											.getParameter("accno"));
									beanobject.setTeligible(beanobject
											.getTeligible());
									beanobject.setIdate(request
											.getParameter("idate"));
									beanobject.setTitle(String.valueOf(session
											.getAttribute("UserID")));
									beanobject.setTemp(Temp);

									beanobject.setValidDate(Security_Counter
											.TextDate_Insert(request
													.getParameter("ddate")
													.toString()));
									beanobject.setBranchID(branchID);

									int renew = ss
											.getUpdateRenewMasFine(beanobject);

									beanobject1 = new CounterFineBean();

									beanobject1.setMcode(request
											.getParameter("mcode"));
									beanobject1.setAccno(request
											.getParameter("accno"));
									beanobject1.setIdate(Security_Counter
											.TextDate_Insert(request
													.getParameter("idate")));
									beanobject1.setDdate(Security_Counter
											.TextDate_Insert(request
													.getParameter("ddate")));
									beanobject1.setRdate(Security_Counter
											.TextDate_Insert(request
													.getParameter("rdate")));
									beanobject1.setAuthor(String
											.valueOf(session
													.getAttribute("UserID")));
									beanobject1.setDoc(request
											.getParameter("doc"));
									beanobject1.setMname(request
											.getParameter("mname"));
									beanobject1.setTemp(Double
											.parseDouble(request
													.getParameter("fine")));

									request.setAttribute("beanobject",
											beanobject1);

									indexPage = "/Counter/index.jsp?flag=fwreturn&detils=ISSUEDEATILS";
								} else {
									indexPage = "/Counter/index.jsp?Message=nrenew&mcode=membercode";
								}

							}
						}
					}
					counterbeanobject = ss.getCounterMember(request
							.getParameter("mcode").toString().trim(), branchID);
					ArrayList issue_details = (ArrayList) ss
							.getIssueDetailsMember(request
									.getParameter("mcode").toString().trim(),
									branchID);
					counterbeanobject.setCounterList(issue_details);

					request.setAttribute("bean", counterbeanobject);
				} else {
					indexPage = "/Counter/index.jsp?Message=MemberNotFound";

				}

				dispatch(request, response, indexPage);

			}
			// -----------------------------------------------------------------END
			// TRY CATCH----------------//
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		System.out.println("IndexPage:::::::::::"+indexPage);

	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
}
