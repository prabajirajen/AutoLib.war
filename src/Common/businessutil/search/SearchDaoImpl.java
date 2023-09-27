package Common.businessutil.search;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

import Common.DataQuery;
import Common.Security_Counter;
import Lib.Auto.Account.AccountBean;
import Lib.Auto.Account.OnlineRenewBean;
import Lib.Auto.Advanced.Adsearchbean;
import Lib.Auto.Branch.BranchBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.EBookSearch.EBookSearchBean;
import Lib.Auto.JournalBrowse.JournalSearchbean;
import Lib.Auto.NewArrivals.NewArrivalsBean;
import Lib.Auto.Newsclliping.NewscllipingBean;
import Lib.Auto.QueryBuilder.QueryBuilderBean;
import Lib.Auto.Simples.Searchbean;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class SearchDaoImpl extends BaseDBConnection implements SearchDao {
	private static Logger log4jLogger = Logger.getLogger(SearchDaoImpl.class);

	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.Statement st = null;

	public static final String SQLCNT = "select count(access_no) as cnt from full_search where 2>1";
	public static final String SQL_Query_view = "select  * from full_search where 2>1";
	public static final String CD_VIEW = "select * from full_search where  remarks like '%+CD%' and access_no=?";

	public List findSimpleSearch(String filterQuery) {

		log4jLogger.info("start===========findSimpleSearch=====" + filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("simpleSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		searchDocmentCount(filterQuery);
		return sql.list();
	}

	public List findAdvancedSearch(String filterQuery) {

		log4jLogger.info("start===========findAdvancedSearch====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("advancedSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		searchDocmentCount(filterQuery);

		return sql.list();

	}

	public List findFullViewSearch(String filterQuery) {

		log4jLogger.info("start===========findFullViewSearch====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("fullViewSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		return sql.list();
	}

	public void searchDocmentCount(String query) {
		log4jLogger.info("start===========searchDocmentCount=====");
		Adsearchbean bean = new Adsearchbean();
		ArrayList finalResults = new ArrayList();

		ArrayList ser = new ArrayList();
		String SQLString = "", f3 = "";
		try {

			con = getSession().connection();
			st = con.createStatement();
			if (query == "") {
				rs = st.executeQuery(DataQuery.SARECH_COUNT_DOC);
			} else {
				rs = st.executeQuery(DataQuery.SARECH_COUNT_DOC_LIKE

				+ query + " GROUP BY document");
			}

			while (rs.next()) {
				ser.add(rs.getString("document"));
				ser.add(rs.getString("doc_count"));
			}

			bean.setAl(ser);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public List findBrowseSearch(String filterQuery) {

		log4jLogger.info("start===========findBrowseSearch=====" + filterQuery);
		StringBuffer sb = new StringBuffer();
		//String namedQuery = getSession().getNamedQuery("browseSearchQuery")
		
		String namedQuery = "SELECT  access_no,call_no,title,author_name,location,availability,document,branch_name FROM full_search where 2>1 ";
				
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		searchDocmentCount(filterQuery);

		return sql.list();

	}

	// ----------------- Journal Search ---------------

	public List findJournalSearch(String filterQuery, int branchID) {

		log4jLogger
				.info("start===========findJournalSearch=====" + filterQuery);
		String strsql = "";

		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("JNLBrowseSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
			sb.append(" " + strsql);
		} else {
			sb.append(namedQuery);
			sb.append(" " + strsql);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}

	public List findJournalIssueSearch(String filterQuery) {
		log4jLogger.info("start===========findJournalIssueSearch====="
				+ filterQuery);

		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("JNLIssueSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}

	public List findJournalFullView(String filterQuery) {
		log4jLogger.info("start===========findJournalFullView====="
				+ filterQuery);

		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession()
				.getNamedQuery("JNLFullviewSearchQuery").getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		return sql.list();
	}

	// ----------------------Binding Books---------------------------------

	public List findLoadBranchList() {
		log4jLogger.info("start===========findLoadBranchList=====");

		BranchBean newbean = null;
		List finalResults = new ArrayList();
		String strsql = "";
		strsql = " order by branch_code asc";

		try {
			con = getSession().connection();
			st = con.createStatement();

			rs = st.executeQuery(DataQuery.Branch_Load+strsql);

			while (rs.next()) {
				newbean = new BranchBean();
				newbean.setCode(rs.getInt("Branch_Code"));
				newbean.setName(rs.getString("Branch_Name"));
				finalResults.add(newbean);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return finalResults;
	}

	public AccountBean findCheckAccount(String uid, String pwd, int branchID) {
		log4jLogger.info("start===========findCheckAccount=====");
		AccountBean bean = new AccountBean();

		int count = 0;
		String strsql = "";

		try {

			if (branchID > 0) {
				strsql = " and branch_code=?";
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ACCOUNT_CHECK + strsql);
			Prest.setString(1, uid);
			Prest.setString(2, pwd);
			if (branchID > 0) {
				Prest.setInt(3, branchID);
			}
			rs = Prest.executeQuery();

			if (rs.next()) {

				if ((!rs.getString("Login_ID").equals(uid))
						|| (!rs.getString("Login_Password").equals(pwd))) {
					log4jLogger
							.info("++++++ Invalid User ID or Password in User Account Checking ++++++");
				} else if (rs.getString("Login_Flag").equalsIgnoreCase("No")) {
					log4jLogger
							.info("++++++ User ID has been locked in User Account Checking ++++++");
				} else {

					bean.setuname(rs.getString("Staff_Name"));
					bean.setuid(rs.getString("Login_ID"));
					bean.setauthor(rs.getString("Login_Password"));

					Prest = con.prepareStatement(DataQuery.ACCOUNT_ISSUE_COUNT);
					Prest.setString(1, uid);
					rs = Prest.executeQuery();
					if (rs.next()) {

						bean.setissuecount(rs.getString("issuecount"));
					}

					Prest = con
							.prepareStatement(DataQuery.ACCOUNT_RETURN_COUNT);
					Prest.setString(1, uid);
					rs = Prest.executeQuery();
					if (rs.next()) {

						bean.setreturncount(rs.getString("returncount"));
					}

					Prest = con
							.prepareStatement(DataQuery.ACCOUNT_RESERVE_COUNT);
					Prest.setString(1, uid);
					rs = Prest.executeQuery();
					if (rs.next()) {

						bean.setreservecount(rs.getString("reservecount"));
					}
					count = 1;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bean;

	}
	
	public List findAccountDetails(String filterQuery) {

		log4jLogger.info("start===========findAccountDetails====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("accountSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}
	

	public List findAccountDetailsIssue(String filterQuery) {

		log4jLogger.info("start===========findAccountDetailsIssue====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"accountIssueSearchQuery").getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}
	
	
	
	
	
	
	//shek
	public List findAccountpaymentDetails(String filterQuery) {

		log4jLogger.info("start===========findAccountDetailsIssue====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"accountPaidDetailsQuery").getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}
	//shek
	
	
	

	public String findOnlineRenewSave(AccountBean newbean) {
		log4jLogger.info("start===========findOnlineRenewSave=====");
		AccountBean bean = new AccountBean();
		OnlineRenewBean counterbeanobject = new OnlineRenewBean();

		int n = 0;
		int count = 0;
		String doc_type = "";
		String group_code = "";
		String duedate = "";
		String issuedate = "";
		String Rdate = "";
		String Ddate = "";
		String retstring = "";
		int days = 0;
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement("select document from book_mas where access_no='"
							+ newbean.getaccno() + "'");
			rs = Prest.executeQuery();
			if (rs.next()) {

				doc_type = rs.getString("document");
			}

			con = getSession().connection();
			Prest = con
					.prepareStatement("select group_code from member_mas where member_code='"
							+ newbean.getuid() + "'");
			rs = Prest.executeQuery();
			if (rs.next()) {

				group_code = rs.getString("group_code");

			}
			con = getSession().connection();
			Prest = con
					.prepareStatement("select issue_date,due_date from issue_mas where member_code='"
							+ newbean.getuid()
							+ "' and access_no='"
							+ newbean.getaccno() + "'");
			rs = Prest.executeQuery();
			if (rs.next()) {

				duedate = Security_Counter.Counter_DateGet(rs
						.getDate("due_date"));
				issuedate = rs.getString("issue_date");
			}

			counterbeanobject = MEMBER_LOAD_ONLINE_RENEW(newbean.getuid(),
					doc_type);

			java.util.StringTokenizer stz_du = new java.util.StringTokenizer(
					Security_Counter.TodayDate(), "-");
			int diy = Integer.parseInt(stz_du.nextToken());
			int dim = Integer.parseInt(stz_du.nextToken());
			int did = Integer.parseInt(stz_du.nextToken());
			Rdate = diy + "-" + dim + "-" + did;

			Prest = con.prepareStatement("select datediff('" + Rdate + "','"
					+ duedate + "') as no_of_days");
			rs = Prest.executeQuery();
			if (rs.next()) {

				String no_of_days = rs.getString("no_of_days");
				n = Integer.parseInt(no_of_days);

			}

			if ((n < 0) || (n == 0)) {

				int time = 0;
				int renew = 0;

				con = getSession().connection();
				Prest = con
						.prepareStatement("select time_renew from renewal_time where access_no='"
								+ newbean.getaccno()
								+ "' and member_code='"
								+ newbean.getuid() + "' ");
				rs = Prest.executeQuery();
				if (rs.next()) {
					time = Integer.parseInt(rs.getString("time_renew"));

					time++;

				} else {
					time = 1;
					Prest = con
							.prepareStatement("insert into renewal_time(member_code,access_no,group_code,doc_type,time_renew) values ('"
									+ newbean.getuid()
									+ "','"
									+ newbean.getaccno()
									+ "','"
									+ group_code
									+ "','" + doc_type + "','" + time + "')");
					Prest.executeUpdate();
				}

				// sql ="select renewal from group_mas where group_code='" +
				// newbean.getTperiod() + "' ";

				Prest = con
						.prepareStatement("select renewal from group_mas where group_code='"
								+ group_code + "' ");
				rs = Prest.executeQuery();
				if (rs.next()) {
					renew = rs.getInt("renewal");

				}

				if (time > renew) {
					retstring = "You Can't Renew!!!";

				} else {

					con = getSession().connection();
					Prest = con
							.prepareStatement("update renewal_time set time_renew='"
									+ time
									+ "' where access_no= '"
									+ newbean.getaccno()
									+ "'and member_code='"
									+ newbean.getuid() + "' ");
					Prest.executeUpdate();

					Prest = con.prepareStatement(DataQuery.SELECT_BOOKMAS);
					Prest.setString(1, newbean.getaccno());
					rs = Prest.executeQuery();
					if (rs.next()) {
						String avail = rs.getString("availability");
						if (avail.equals("ISSUED")) {
							rs.absolute(1);
							Prest = con
									.prepareStatement("update book_mas set availability='ISSUED' where access_no='"
											+ newbean.getaccno() + "'");
							Prest.executeUpdate();

							n = counterbeanobject.getCountperiod();

							int a = 0;

							a = n * 1;

							java.util.StringTokenizer stz = new java.util.StringTokenizer(
									Security_Counter.TodayDate(), "-");
							diy = Integer.parseInt(stz.nextToken());
							dim = Integer.parseInt(stz.nextToken());
							did = Integer.parseInt(stz.nextToken());
							Rdate = diy + "-" + dim + "-" + did;

							con = getSession().connection();
							Prest = con.prepareStatement("select adddate('"
									+ Rdate + "', '" + a + "') as days");
							rs = Prest.executeQuery();
							if (rs.next()) {
								Ddate = rs.getString("days");

							}

							/**
							 * boolean Rflag = true; while (Rflag == true) {
							 * 
							 * Prest =
							 * con.prepareStatement("select date_format('"
							 * +Ddate+"','%w') as days");
							 * rs=Prest.executeQuery();
							 * 
							 * if (rs.next()) {
							 * 
							 * days=Integer.parseInt(rs.getString("days"));
							 * 
							 * counterbeanobject.setAuthor(String.valueOf(days))
							 * ;
							 * 
							 * 
							 * if (days == 0) { a=1; con =
							 * getSession().connection(); Prest =
							 * con.prepareStatement
							 * ("select adddate('"+Ddate+"', '"+a+"') as days");
							 * rs=Prest.executeQuery(); if (rs.next()) {
							 * Ddate=rs.getString("days"); }
							 * 
							 * 
							 * } if (days == 6) { a=2; con =
							 * getSession().connection(); Prest =
							 * con.prepareStatement
							 * ("select adddate('"+Ddate+"', '"+a+"') as days");
							 * rs=Prest.executeQuery(); if (rs.next()) {
							 * Ddate=rs.getString("days"); }
							 * 
							 * 
							 * }
							 * 
							 * 
							 * 
							 * con = getSession().connection(); Prest =
							 * con.prepareStatement
							 * (DataQuery.SELECT_HOLIDAY_MAS_CHECK);
							 * Prest.setString(1, Ddate);
							 * rs=Prest.executeQuery(); String leave_date=""; if
							 * (rs.next()) {
							 * 
							 * leave_date=rs.getString("Leave_date");
							 * 
							 * 
							 * a=1; con = getSession().connection(); Prest =
							 * con. prepareStatement("select adddate('"+Ddate+
							 * "', '" +a+"') as days"); rs=Prest.executeQuery();
							 * if (rs.next()) { Ddate=rs.getString("days");
							 * Rflag = true; }
							 * 
							 * 
							 * }else { Rflag = false; }
							 * 
							 * }
							 * 
							 * }
							 */

							int Holiday_Count = 0, First_Holiday = 0, Second_Holiday = 0, Wday = 0; // RK
																									// Start
																									// 17-09-2014

							boolean Rflag = true;
							while (Rflag == true) {

								Prest = con
										.prepareStatement("select date_format('"
												+ Ddate + "','%w') as days");
								rs = Prest.executeQuery();

								if (rs.next()) {
									String day = rs.getString("days");
									int name_of_day = Integer.parseInt(day);
									counterbeanobject.setAuthor(day);

									Prest = con
											.prepareStatement("select Leave_date  from holiday_mas where leave_date='"
													+ Ddate + "'");
									rs = Prest.executeQuery();
									if (rs.next()) {

										a = 1;

										Prest = con
												.prepareStatement("select adddate('"
														+ Ddate
														+ "', '"
														+ a
														+ "') as days");

										rs = Prest.executeQuery();

										if (rs.next()) {
											Ddate = rs.getString("days");
											Rflag = true;

										}

									} else {
										Rflag = false;
									}
									// For WeekEnd Holiday Master

									Prest = con
											.prepareStatement("select DAYOFWEEK('"
													+ Ddate + "') as weekday");
									rs = Prest.executeQuery();
									if (rs.next()) {

										Wday = rs.getInt("weekday");
									}
									Prest = con
											.prepareStatement("Select Day_ID from weekEnd_Holyday_Mas");
									rs = Prest.executeQuery();
									if (rs.next()) {
										Prest = null;
										boolean b = rs.last();
										if (b) {
											Holiday_Count = rs.getRow();
										}
										rs.first();

										if (Holiday_Count == 1) {
											First_Holiday = rs.getInt("Day_ID");
											if (Wday == First_Holiday) {
												Prest = con
														.prepareStatement("select adddate('"
																+ Ddate
																+ "', 1) as days");
											}
										}
										if (Holiday_Count == 2) {
											First_Holiday = rs.getInt("Day_ID");
											rs.next();
											Second_Holiday = rs
													.getInt("Day_ID");

											if (Second_Holiday != 7) {
												if (Wday == First_Holiday) {
													Prest = con
															.prepareStatement("select adddate('"
																	+ Ddate
																	+ "', 2) as days");
												} else if (Wday == Second_Holiday) {
													Prest = con
															.prepareStatement("select adddate('"
																	+ Ddate
																	+ "', 1) as days");
												}
											} else {
												if (Wday == Second_Holiday) {
													if (First_Holiday == 1) {
														Prest = con
																.prepareStatement("select adddate('"
																		+ Ddate
																		+ "',2) as days");
													} else {

														Prest = con
																.prepareStatement("select adddate('"
																		+ Ddate
																		+ "', 1) as days");
													}

												} else if (Wday == First_Holiday) {
													if (Second_Holiday == 7) {

														Prest = con
																.prepareStatement("select adddate('"
																		+ Ddate
																		+ "', 1) as days");

													} else {

														Prest = con
																.prepareStatement("select adddate('"
																		+ Ddate
																		+ "', 2) as days");
													}
												}

											}
										}

										if (Prest != null) {
											rs = Prest.executeQuery();

											if (rs.next()) {
												Ddate = rs.getString("days");
											}
										}

										Prest = con
												.prepareStatement("select Leave_date  from holiday_mas where leave_date='"
														+ Ddate + "'");
										rs = Prest.executeQuery();
										if (rs.next()) {
											Rflag = true;
										} else {
											Rflag = false;
										}

									}

								}

							}

							con = getSession().connection();
							Prest = con
									.prepareStatement(DataQuery.UPDATE_ISSUEMAS);
							Prest.setString(1, Ddate);
							Prest.setString(2, Rdate);
							Prest.setString(3, "RENEW");
							Prest.setString(4, newbean.gettitle()); // For Staff
																	// Code
							Prest.setString(5, newbean.getuid());
							Prest.setString(6, newbean.getaccno());
							Prest.executeUpdate();

							Prest = con
									.prepareStatement(DataQuery.INSERT_HISTORY);
							Prest.setString(1, newbean.getuid());
							Prest.setString(2, newbean.getaccno());
							Prest.setString(3, issuedate);
							Prest.setString(4, (String.valueOf(duedate)));
							Prest.setString(5, Rdate);
							Prest.setDouble(6, 0.0);
							Prest.setObject(7, newbean.gettitle());
							Prest.setString(8, doc_type);
							Prest.setString(9, doc_type);
							Prest.executeUpdate();

							retstring = "Document Successfully Renewed!!!";

						}
					}

				}

			} else {

				retstring = "You Can't Renew!!!";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return retstring;

	}

	public void RETURN_UPDATE_RENEW_NOFINE(CounterMemberBean newbean)
			throws SQLException, ParseException {
		log4jLogger.info("start===========ONLINE RENEW SAVE=====");
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.UPDATE_ISSUEMAS);
			Prest.setString(1, newbean.getDdate());
			Prest.setString(2, newbean.getRdate());
			Prest.setString(3, "RENEW");
			Prest.setString(4, newbean.getTitle()); // For Staff Code
			Prest.setString(5, newbean.getMcode());
			Prest.setString(6, newbean.getAccno());
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.INSERT_HISTORY);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			Prest.setString(3,
					Security_Counter.TextDate_Insert(newbean.getIdate()));
			Prest.setString(4, newbean.getRdate());
			Prest.setString(5, newbean.getDdate());
			Prest.setDouble(6, 0.0);
			Prest.setObject(7, newbean.getTitle()); // For Staff Code
			Prest.setString(8, newbean.getDoc());
			Prest.setString(9, newbean.getDoc());
			Prest.executeUpdate();

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * public OnlineRenewBean MEMBER_LOAD_ONLINE_RENEW(String loadmemcode,
	 * String doctype) { int member_period = 0; int Groups=0; OnlineRenewBean
	 * counterbeanobject=new OnlineRenewBean();
	 * 
	 * try{ con = getSession().connection(); Prest =
	 * con.prepareStatement(DataQuery.SELECT_MEMBER); Prest.setString(1,
	 * loadmemcode); rs = Prest.executeQuery(); if (rs.next()) {
	 * 
	 * Groups = rs.getInt("group_code"); String status = rs.getString("status");
	 * if (status.equals("V1")) {
	 * 
	 * counterbeanobject.setGeligible(rs.getInt("gelg")); member_period =
	 * rs.getInt("gper"); counterbeanobject.setGperiod(member_period);
	 * 
	 * } else if (status.equals("V2")) {
	 * 
	 * if (doctype.equals("BOOK")) {
	 * counterbeanobject.setBbeligible(rs.getInt("bbelg")); member_period =
	 * rs.getInt("bper"); counterbeanobject.setBbperiod(member_period);
	 * 
	 * } else if (doctype.equals("BOOK BANK")) {
	 * counterbeanobject.setBbeligible(rs.getInt("bbelg")); member_period =
	 * rs.getInt("bbper"); counterbeanobject.setBbperiod(member_period);
	 * 
	 * } else if (doctype.equals("NON BOOK")) {
	 * 
	 * counterbeanobject.setNbeligible(rs.getInt("nbelg")); member_period =
	 * rs.getInt("nbper"); counterbeanobject.setNbperiod(member_period);
	 * 
	 * } else if (doctype.equals("JOURNAL")) {
	 * 
	 * counterbeanobject.setJeligible(rs.getInt("jelg")); member_period =
	 * rs.getInt("jlper"); counterbeanobject.setJperiod(member_period);
	 * 
	 * } else if (doctype.equals("BACK VOLUME")) {
	 * 
	 * counterbeanobject.setBveligible(rs.getInt("bvelg")); member_period =
	 * rs.getInt("bvper"); counterbeanobject.setBvperiod(member_period);
	 * 
	 * } else if (doctype.equals("THESIS")) {
	 * 
	 * counterbeanobject.setTeligible(rs.getInt("telg")); member_period =
	 * rs.getInt("tper"); counterbeanobject.setTperiod(member_period);
	 * 
	 * } else if (doctype.equals("STANDARD")) {
	 * 
	 * counterbeanobject.setSeligible(rs.getInt("selg")); member_period =
	 * rs.getInt("sper"); counterbeanobject.setSperiod(member_period);
	 * 
	 * } else if (doctype.equals("PROCEEDING")) {
	 * 
	 * counterbeanobject.setPeligible(rs.getInt("pelg")); member_period =
	 * rs.getInt("pper"); counterbeanobject.setPperiod(member_period);
	 * 
	 * } else if (doctype.equals("REPORT")) {
	 * 
	 * counterbeanobject.setReligible(rs.getInt("relg")); member_period =
	 * rs.getInt("rper"); counterbeanobject.setRperiod(member_period);
	 * 
	 * } else { String STOP = "STOP"; }
	 * 
	 * 
	 * } else if (status.equals("V3")) {
	 * 
	 * counterbeanobject.setGeligible(rs.getInt("gelg")); member_period =
	 * rs.getInt("gper"); counterbeanobject.setGperiod(member_period);
	 * 
	 * if (doctype.equals("BOOK")) {
	 * counterbeanobject.setBbeligible(rs.getInt("bbelg")); member_period =
	 * rs.getInt("bper"); counterbeanobject.setBbperiod(member_period);
	 * 
	 * } else if (doctype.equals("BOOK BANK")) {
	 * counterbeanobject.setBbeligible(rs.getInt("bbelg")); member_period =
	 * rs.getInt("bbper"); counterbeanobject.setBbperiod(member_period);
	 * 
	 * } else if (doctype.equals("NON BOOK")) {
	 * 
	 * counterbeanobject.setNbeligible(rs.getInt("nbelg")); member_period =
	 * rs.getInt("nbper"); counterbeanobject.setNbperiod(member_period);
	 * 
	 * } else if (doctype.equals("JOURNAL")) {
	 * 
	 * counterbeanobject.setJeligible(rs.getInt("jelg")); member_period =
	 * rs.getInt("jlper"); counterbeanobject.setJperiod(member_period);
	 * 
	 * } else if (doctype.equals("BACK VOLUME")) {
	 * 
	 * counterbeanobject.setBveligible(rs.getInt("bvelg")); member_period =
	 * rs.getInt("bvper"); counterbeanobject.setBvperiod(member_period);
	 * 
	 * } else if (doctype.equals("THESIS")) {
	 * 
	 * counterbeanobject.setTeligible(rs.getInt("telg")); member_period =
	 * rs.getInt("tper"); counterbeanobject.setTperiod(member_period);
	 * 
	 * } else if (doctype.equals("STANDARD")) {
	 * 
	 * counterbeanobject.setSeligible(rs.getInt("selg")); member_period =
	 * rs.getInt("sper"); counterbeanobject.setSperiod(member_period);
	 * 
	 * } else if (doctype.equals("PROCEEDING")) {
	 * 
	 * counterbeanobject.setPeligible(rs.getInt("pelg")); member_period =
	 * rs.getInt("pper"); counterbeanobject.setPperiod(member_period);
	 * 
	 * } else if (doctype.equals("REPORT")) {
	 * 
	 * counterbeanobject.setReligible(rs.getInt("relg")); member_period =
	 * rs.getInt("rper"); counterbeanobject.setRperiod(member_period);
	 * 
	 * } else { String STOP_WORK = "_WORKSTOP"; }
	 * 
	 * } counterbeanobject.setCountperiod(member_period);
	 * 
	 * } } catch (Exception e) {
	 * 
	 * } finally { try { if (rs != null) { rs.close(); } if (Prest != null) {
	 * Prest.close(); } if (con != null) { con.close(); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } } return
	 * counterbeanobject; }
	 */

	public OnlineRenewBean MEMBER_LOAD_ONLINE_RENEW(String loadmemcode,
			String doctype) // RK start 17-09-2014
	{
		int member_period = 0;
		int Groups = 0;
		OnlineRenewBean counterbeanobject = new OnlineRenewBean();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER);
			Prest.setString(1, loadmemcode);
			rs = Prest.executeQuery();
			if (rs.next()) {

				Groups = rs.getInt("group_code");
				String status = rs.getString("status");
				if (status.equals("V1")) {

					counterbeanobject.setGeligible(rs.getInt("gelg"));
					member_period = rs.getInt("grper");
					counterbeanobject.setGperiod(rs.getInt("gper"));

				} else if (status.equals("V2")) {

					if (doctype.equals("BOOK")) {
						counterbeanobject.setBbeligible(rs.getInt("bbelg"));
						member_period = rs.getInt("brper");
						counterbeanobject.setBbperiod(rs.getInt("bper"));

					} else if (doctype.equals("BOOK BANK")) {
						counterbeanobject.setBbeligible(rs.getInt("bbelg"));
						member_period = rs.getInt("bbrper");
						counterbeanobject.setBbperiod(rs.getInt("bbper"));

					} else if (doctype.equals("NON BOOK")) {

						counterbeanobject.setNbeligible(rs.getInt("nbelg"));
						member_period = rs.getInt("nbrper");
						counterbeanobject.setNbperiod(rs.getInt("nbper"));

					} else if (doctype.equals("JOURNAL")) {

						counterbeanobject.setJeligible(rs.getInt("jelg"));
						member_period = rs.getInt("jrper");
						counterbeanobject.setJperiod(rs.getInt("jlper"));

					} else if (doctype.equals("BACK VOLUME")) {

						counterbeanobject.setBveligible(rs.getInt("bvelg"));
						member_period = rs.getInt("bvrper");
						counterbeanobject.setBvperiod(rs.getInt("bvper"));

					} else if (doctype.equals("THESIS")) {

						counterbeanobject.setTeligible(rs.getInt("telg"));
						member_period = rs.getInt("trper");
						counterbeanobject.setTperiod(rs.getInt("tper"));

					} else if (doctype.equals("STANDARD")) {

						counterbeanobject.setSeligible(rs.getInt("selg"));
						member_period = rs.getInt("srper");
						counterbeanobject.setSperiod(rs.getInt("sper"));

					} else if (doctype.equals("PROCEEDING")) {

						counterbeanobject.setPeligible(rs.getInt("pelg"));
						member_period = rs.getInt("prper");
						counterbeanobject.setPperiod(rs.getInt("pper"));

					} else if (doctype.equals("REPORT")) {

						counterbeanobject.setReligible(rs.getInt("relg"));
						member_period = rs.getInt("rrper");
						counterbeanobject.setRperiod(rs.getInt("rper"));

					} else {
						String STOP = "STOP";
					}

				} else if (status.equals("V3")) {

					counterbeanobject.setGeligible(rs.getInt("gelg"));
					member_period = rs.getInt("grper");
					counterbeanobject.setGperiod(rs.getInt("gper"));

					if (doctype.equals("BOOK")) {
						counterbeanobject.setBbeligible(rs.getInt("bbelg"));
						member_period = rs.getInt("brper");
						counterbeanobject.setBbperiod(rs.getInt("bper"));

					} else if (doctype.equals("BOOK BANK")) {
						counterbeanobject.setBbeligible(rs.getInt("bbelg"));
						member_period = rs.getInt("bbrper");
						counterbeanobject.setBbperiod(rs.getInt("bbper"));

					} else if (doctype.equals("NON BOOK")) {

						counterbeanobject.setNbeligible(rs.getInt("nbelg"));
						member_period = rs.getInt("nbrper");
						counterbeanobject.setNbperiod(rs.getInt("nbper"));

					} else if (doctype.equals("JOURNAL")) {

						counterbeanobject.setJeligible(rs.getInt("jelg"));
						member_period = rs.getInt("jrper");
						counterbeanobject.setJperiod(rs.getInt("jlper"));

					} else if (doctype.equals("BACK VOLUME")) {

						counterbeanobject.setBveligible(rs.getInt("bvelg"));
						member_period = rs.getInt("bvrper");
						counterbeanobject.setBvperiod(rs.getInt("bvper"));

					} else if (doctype.equals("THESIS")) {

						counterbeanobject.setTeligible(rs.getInt("telg"));
						member_period = rs.getInt("trper");
						counterbeanobject.setTperiod(rs.getInt("tper"));

					} else if (doctype.equals("STANDARD")) {

						counterbeanobject.setSeligible(rs.getInt("selg"));
						member_period = rs.getInt("srper");
						counterbeanobject.setSperiod(rs.getInt("sper"));

					} else if (doctype.equals("PROCEEDING")) {

						counterbeanobject.setPeligible(rs.getInt("pelg"));
						member_period = rs.getInt("prper");
						counterbeanobject.setPperiod(rs.getInt("pper"));

					} else if (doctype.equals("REPORT")) {

						counterbeanobject.setReligible(rs.getInt("relg"));
						member_period = rs.getInt("rrper");
						counterbeanobject.setRperiod(rs.getInt("rper"));

					} else {
						String STOP_WORK = "_WORKSTOP";
					}

				}
				counterbeanobject.setCountperiod(member_period);

			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return counterbeanobject;
	}

	public void DAYSCAL(int period) throws SQLException, IOException {
		log4jLogger
				.info("DAYSCAL===================== ====================method begin");

		OnlineRenewBean counterbeanobject = new OnlineRenewBean();
		String sql = "";
		int no = 1;
		int a;
		java.util.Date Ddate = new Date();
		a = 0;

		a = period;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement("select adddate('"
					+ Security_Counter.Counter_DateText() + "', '" + a
					+ "') as days");

			rs = Prest.executeQuery();
			if (rs.next()) {
				Ddate = rs.getDate("days");

			}
			boolean Rflag = true;
			while (Rflag == true) {

				Prest = con.prepareStatement("select date_format('" + Ddate
						+ "','%w') as days");
				rs = Prest.executeQuery();

				if (rs.next()) {
					String day = rs.getString("days");
					int name_of_day = Integer.parseInt(day);
					if (name_of_day == 0) {

						a = 1;

						Prest = con.prepareStatement("select adddate('" + Ddate
								+ "', '" + a + "') as days");
						rs = Prest.executeQuery();
						if (rs.next()) {
							Ddate = rs.getDate("days");

						}
					}

					if (name_of_day == 6) {

						a = 2;

						Prest = con.prepareStatement("select adddate('" + Ddate
								+ "', '" + a + "') as days");

						rs = Prest.executeQuery();
						if (rs.next()) {
							Ddate = rs.getDate("days");

						}
					}

					Prest = con
							.prepareStatement("select Leave_date  from holiday_mas where leave_date='"
									+ Ddate + "'");
					rs = Prest.executeQuery();
					if (rs.next()) {

						a = 1;

						Prest = con.prepareStatement("select adddate('" + Ddate
								+ "', '" + a + "') as days");

						rs = Prest.executeQuery();

						if (rs.next()) {
							Ddate = rs.getDate("days");
							Rflag = true;
						}

					} else {
						Rflag = false;
					}

				}

			}

			counterbeanobject.setCalldate(Security_Counter
					.Counter_DateSet(Ddate));

		} catch (SQLException e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public List findAccountDetailsReserve(String filterQuery) {

		log4jLogger.info("start===========findAccountDetailsReserve====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"accountReserveSearchQuery").getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}

	public String findOnlineReserveCancel(AccountBean newbean) {
		log4jLogger.info("start===========findOnlineReserveCancel=====");
		AccountBean bean = new AccountBean();
		String delrec = "";

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_RESERVE_MAS);
			Prest.setString(1, newbean.getuid());
			Prest.setString(2, newbean.getaccno());
			Prest.executeUpdate();

			delrec = "Reservation Cancelled Successfully";

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return delrec;

	}

	public String findChangePwd(AccountBean newbean) {
		log4jLogger.info("start===========findChangePwd=====");

		AccountBean bean = new AccountBean();
		int chpwd = 0;
		String curpwd = "";
		String retstring = "", strsql = "";

		try {

			if (newbean.getBranch() > 0) {
				strsql = " and branch_code=" + newbean.getBranch() + " ";
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.LOGIN_Same_Name + strsql);
			Prest.setString(1, newbean.getuid());
			rs = Prest.executeQuery();
			if (rs.next()) {
				curpwd = rs.getString("Login_Password");

				if (curpwd.equals(newbean.getpwd())) {

					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.LOGIN_NEWPWD_UPDATE);
					Prest.setString(2, newbean.getuid());
					Prest.setString(1, newbean.getnewpwd());
					Prest.executeUpdate();

					retstring = "Successfully Changed your password!!!";

				} else {

					retstring = "Not match your old password!!!";

				}

			} else {

				retstring = "Invalid User!!!";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return retstring;

	}

	public List findRegisterLoad(AccountBean newbean) {

		log4jLogger.info("start===========findRegisterLoad=====");
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"registerLoadSearchQuery").getQueryString();

		sb.append(namedQuery);
		
		/**if (newbean.getBranch() == 0) {
		} else {
			sb.append(" where Branch_Code=" + newbean.getBranch());
		}*/
		
//		if(newbean.getBranch() == 2 )
//		{
			sb.append(" where branch_code=" + newbean.getBranch() +" order by in_time desc" );
		//}
		
//		else if(newbean.getBranch() > 2 ) {
//			sb.append(" where Dept_BranchCode=" + newbean.getBranch()+" order by in_time desc ");
//		}

		SQLQuery sql = getSession().createSQLQuery(sb.toString());

		return sql.list();

	}

	public int findRegisterAllLogout(int branchID) {
		log4jLogger
				.info("============= Inside findRegisterAllLogout() ============");
		int count = 0;
		String query = getSession().getNamedQuery("callEntryLog")
				.getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		List list = sql.list();

		if (list != null && !list.isEmpty()) {
			String saveQuery = getSession().getNamedQuery("saveReturnLog")
					.getQueryString();
			SQLQuery logSave = getSession().createSQLQuery(saveQuery);

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[]) list.get(i);

				logSave.setParameter("member", obj[0].toString());
				logSave.setParameter("entryTime", obj[1].toString());
				logSave.setParameter("returnTime", obj[1].toString());
				logSave.setParameter("min", 1);//for all logout  pending
				logSave.setParameter("inTime", obj[2].toString());
				logSave.setParameter("outTime", obj[2].toString());
				logSave.setParameter("branchCode", branchID);
				logSave.setParameter("purpose", obj[3].toString());
				
				
				
				count = logSave.executeUpdate();
			}

			String deleteQuery = getSession().getNamedQuery("deleteEntryLog")
					.getQueryString();
			SQLQuery logDelete = getSession().createSQLQuery(deleteQuery);
			count = logDelete.executeUpdate();
		}
		log4jLogger
				.info("============= End findRegisterAllLogout() ============"
						+ count);
		return count;
	}

	public AccountBean findRegisterEntry(String search,String purpose, int branchID) {
		log4jLogger.info("start===========findChangePwd=====");

		AccountBean bean = new AccountBean();
		int chpwd = 0;
		String curpwd = "";
		String retstring = "", strsql = "";
		String memb_name="";
		int loginCount=0;
		
		

		try {

				strsql = " and branch_code=" + branchID + " ";
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ENTRY_GATERESISTER_CHECK
					+ strsql);
			Prest.setString(1, search);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {
				String memb_code = rs.getString("member_code");
				String ent_date = String.valueOf(rs.getDate("entry_time"));
					   memb_name =rs.getString("member_name");
				String purpos = rs.getString("purpose");
				String intime = "";
				String outtime = "";
				con = getSession().connection();
				Prest = con
						.prepareStatement("select DATE_FORMAT(entry_time,'%H:%i')as in_time from entry_log where member_code='"
								+ memb_code + "' AND branch_code='"+branchID+"'");
				rs = Prest.executeQuery();
				if (rs.next()) {
					intime = rs.getString("in_time");
				}
				con = getSession().connection();
				Prest = con
						.prepareStatement("select DATE_FORMAT(NOW(),'%H:%i')as out_time ");
				rs = Prest.executeQuery();
				if (rs.next()) {
					outtime = rs.getString("out_time");
				}
				con = getSession().connection();
				Prest = con
						.prepareStatement("SELECT TIMESTAMPDIFF(MINUTE,entry_time,NOW()) as min_used from entry_log where member_code='"
								+ memb_code + "' and branch_code='"+branchID+"'");
				rs = Prest.executeQuery();
				if (rs.next()) {

					con = getSession().connection();
					Prest = con
							.prepareStatement(DataQuery.ENTRY_GATERESISTER_RETURN);
					Prest.setString(1, search.toUpperCase());
					Prest.setString(2, ent_date);
					Prest.setString(3, Security_Counter.Counter_DateText());
					Prest.setInt(4, rs.getInt("min_used"));
					Prest.setString(5, intime);
					Prest.setString(6, outtime);
					Prest.setInt(7, branchID);
					Prest.setString(8, purpos);
					
					Prest.executeUpdate();

					con = getSession().connection();
					Prest = con
							.prepareStatement(DataQuery.ENTRY_GATERESISTER_DELETE);
					Prest.setString(1, search);
					Prest.executeUpdate();
					retstring = "Thank You "+memb_name.toUpperCase()+". Logged out Successfully, You Have Used "+rs.getInt("min_used")+" Minutes";

					con = getSession().connection(); // For Member Out Photo to
														// Display
					Prest = con
							.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING);
					Prest.setString(1, search);
					Prest.setInt(2, branchID);
					rs = Prest.executeQuery();
					if (rs.next()) {

						bean.setPhoto1(rs.getBytes("photo"));
					}

				}
			} else {
				
				//shek
				
				log4jLogger.info(":::::::::::::000000:::::::::"+search);
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.countUserLogging);
				Prest.setString(1, search);
				rs = Prest.executeQuery();
				if (rs.next()) {
					loginCount=rs.getInt("cnt");
					
					if(loginCount==0){
					loginCount=1;
					}else{
						loginCount=rs.getInt("cnt");
						loginCount=loginCount+1;
						
							}
					log4jLogger.info("::::::::::::::::::   "+loginCount);
				}else{
					loginCount=0;
				}
				
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.MEMBERVIEWSELECT_STRING
						+ strsql);
				Prest.setString(1, search);
				Prest.setInt(2, branchID);
				
				rs = Prest.executeQuery();
				if (rs.next()) {
					con = getSession().connection();
					Prest = con
							.prepareStatement("insert into entry_log values('"
									+ search.toUpperCase()
									+ "',NOW(),DATE_FORMAT(NOW(),'%H:%i'),'"+purpose+"',"+branchID+")");
					Prest.executeUpdate();
					retstring = "Welcome "+rs.getString("member_name").toUpperCase()+" You Have Logged In Successfully. -- Login Count: <b>"+loginCount;
					bean.setPhoto1(rs.getBytes("photo"));
				}
				
				
				else {
					retstring = "Invalid User!!!";
				}
			}
			bean.setauthor(retstring);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return bean;

	}

	// ---------------------------

	public List findNewsClipSimpleSearch(String filterQuery) {

		log4jLogger.info("start===========findNewsClipSimpleSearch====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"NewsClipsimpleSearchQuery").getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		return sql.list();
	}

	public List findEResourceSimpleSearch(String filterQuery) {

		log4jLogger.info("start===========findEResourceSimpleSearch=====");
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"EResourcesimpleSearchQuery").getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
			sb.append(" ORDER BY w.CODE ASC");
		} else {
			sb.append(namedQuery);
			sb.append(" ORDER BY w.CODE ASC");
		}

		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		return sql.list();
	}

	public List findJournalArticleSearch(String filterQuery) {

		log4jLogger.info("start===========findJournalArticleSearch====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery(
				"JournalArticleSearchQuery").getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		return sql.list();
	}

	// For Online Reservation

	public List findIssueDetails(String accno, String doc) {

		log4jLogger.info("start===========findIssueDetails=====" + accno
				+ " And " + doc);

		SQLQuery sql = getSession().createSQLQuery(
				DataQuery.SELECT_ISSUEBOOKCheck);
		sql.setParameter("accessno", accno);
		sql.setParameter("doctype", doc);
		return sql.list();
	}

	public AccountBean findReserveCheck(AccountBean newbean) {
		log4jLogger.info("start===========findReserveCheck====="+ newbean.getuid() + newbean.getdtype() + newbean.getaccno()+ newbean.getresdat());
		
		AccountBean bean = new AccountBean();
		int count = 0;
		int bookBranchID=0,memberBranchID=0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ACCESS);
			Prest.setString(1, newbean.getaccno());
			rs = Prest.executeQuery();
			if(rs.next()){
				bookBranchID=Integer.parseInt(rs.getString("branch_code"));//2
			}
			
			
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER);
			Prest.setString(1, newbean.getuid());
			rs = Prest.executeQuery();
			if (rs.next()) {

				count = rs.getInt("breserve"); // RK Start 17-09-2014
				memberBranchID=Integer.parseInt(rs.getString("branch_code"));//2
				
				//3  2
					 //if((bookBranchID!=memberBranchID) || bookBranchID!=2){
//				
//						     if((bookBranchID!=memberBranchID) && bookBranchID!=2){
//						 
//						 
//						 bean.setauthor("Other Division(s) Documents cannot be Reserved by you!.");
//						}else{
					
				
				log4jLogger.info("BookBranchID"+bookBranchID);
				log4jLogger.info("MemberBranchID"+memberBranchID);
				
				if(bookBranchID==2 || bookBranchID==memberBranchID){
					
				

				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.SELECT_ISSUEMAS_ONLY);
				Prest.setString(1, newbean.getuid());
				Prest.setString(2, newbean.getaccno());
				rs = Prest.executeQuery();
				if (rs.next()) {
					bean.setauthor("Document Already Issued To You!!");
					
				}
				else{

					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.SELECT_RESERVEMAS);
					Prest.setString(1, newbean.getuid());
					Prest.setString(2, newbean.getaccno());
					rs = Prest.executeQuery();
					if (rs.next()) {
						bean.setauthor("Document Already Reserved!!");
					} else {

						int Res_Count = 0;
						con = getSession().connection();
						Prest = con
								.prepareStatement(DataQuery.SELECT_MAX_RESERVEMAS);
						Prest.setString(1, newbean.getuid());
						rs = Prest.executeQuery();
						if (rs.next()) {
							Res_Count = rs.getInt(1);
						}
						if (Res_Count < count) {

							AccountBean beanobject = new AccountBean();
							
							beanobject = findReserveMasSave(newbean);

							if (beanobject != null) {

								bean.setuid(beanobject.getuid());
								bean.setuname(beanobject.getuname());
								bean.setaccno(beanobject.getaccno());
								bean.settitle(beanobject.gettitle());
								bean.setavailability(beanobject.getavailability());

								String emailid = "", phone = "", name = "";

								con = getSession().connection();
								Prest = con
										.prepareStatement(DataQuery.MEMBEREMAIL_STRING); // For
																							// Email
								Prest.setString(1, bean.getuid());
								rs = Prest.executeQuery();

								if (rs.next()) {

									emailid = rs.getString("member_email");
									phone = rs.getString("member_phone");
									name = rs.getString("member_name");

								}

							/*	if (!emailid.isEmpty() && !emailid.equals("")) // For
																				// Online
																				// Reservation
																				// E-Mail
								{
									// log4jLogger.info("Inside Online Reservation Email "+emailid);

									boolean chk = Security_Counter
											.EmailValidator(emailid);

									if (chk == true) {

										String[] strArray = new String[] { emailid };

										MailDaoImpl Mail = new MailDaoImpl();

										String namedQuery = MailQueryUtill.Reserve_Message_Text;
										StringBuffer sb = new StringBuffer();

										sb.append("Dear " + name + ",<br><br>");
										sb.append(namedQuery);

										sb.append("<br><br>");
										sb.append("<table border=1 width=680 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>");
										sb.append("<tr bgcolor='#CCEEFF'><th align=left><b><font color='#000000' size='1' face='Verdana'></b>Access No</th>");
										sb.append("<th align=left><b><font color='#000000' size='1' face='Verdana'></b>Title</th>");
										sb.append("<th align=left><b><font color='#000000' size='1' face='Verdana'></b>Reserve Date</th>");
										sb.append("<th align=left><b><font color='#000000' size='1' face='Verdana'></b>Queue</th>");
										sb.append("<th align=left><b><font color='#000000' size='1' face='Verdana'></b>Document</th></tr>");

										sb.append("<tr bgcolor='#CCFFFF'><td align=left width=70><font color='a62121' size='1' face='Verdana'>"
												+ bean.getaccno()
												+ "</td><td align=left><font color='a62121' size='1' face='Verdana'>"
												+ bean.gettitle()
												+ "</td><td align=left width=75><font color='a62121' size='1' face='Verdana'>"
												+ Security
														.TextDate_member(newbean
																.getresdat())
												+ "</td><td align=left width=60><font color='a62121' size='1' face='Verdana'>"
												+ bean.getavailability()
												+ "</td><td align=left width=70><font color='a62121' size='1' face='Verdana'>"
												+ newbean.getdtype()
												+ "</td></tr>");
										sb.append("</table><br><br>");

										sb.append(MailQueryUtill.Regards_Text);

										Mail.findSendEmail(
												strArray,
												MailQueryUtill.Reserve_Subject_Text,
												sb.toString());

									} else {
										log4jLogger.info("Invalid Emailid");
									}

								}*/

								/**
								 * if(!phone.isEmpty() && !phone.equals("") &&
								 * phone.length()==10) // For Issue SMS {
								 * log4jLogger
								 * .info("Inside Issue Master SMS "+phone);
								 * boolean
								 * chk=Security_Counter.SMSValidator(phone);
								 * 
								 * if(chk==true) { SmsDaoImpl sms=new
								 * SmsDaoImpl();
								 * 
								 * String namedQuery=MailQueryUtill.
								 * Reserve_Message_Text; StringBuffer sb = new
								 * StringBuffer();
								 * 
								 * sb.append(namedQuery); sb.append("UserID: " +
								 * bean
								 * .getuid()+" , Book No: "+bean.getaccno());
								 * sb.append(", "+"ReserveDate: "+Security.
								 * TextDate_member
								 * (newbean.getresdat())+" , Queue: "
								 * +bean.getavailability()+". Thanks, AutoLib");
								 * 
								 * sms.findSendSMS(phone,sb.toString()); }else {
								 * log4jLogger.info("Invalid Phone Number"); }
								 * 
								 * }
								 */

								bean.setauthor("DONE");

							} else {
								bean.setauthor("ERROR OCCURED");
							}

						} else {
							bean.setauthor("You Cannot be Reserved More Than "
									+ count + " Resources!!");
						}
					}
				}
			}
				else{
				 bean.setauthor("Other division(s) documents cannot be reserved by you!.");
			}
			
		}
			else {
				bean.setauthor("INVALID USER / Member Not Found!!");
			}

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return bean;

	}

	public AccountBean findReserveMasSave(AccountBean newbean) {
		log4jLogger.info("start===========findOnlineReserveMasSave====="
				+ newbean.getuid() + newbean.getdtype() + newbean.getaccno()
				+ newbean.getresdat());
		AccountBean beanobject = new AccountBean();
		int select_code = 0;

		int cnt = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_RESERVEMAS_MAX);
			rs = Prest.executeQuery();
			if (rs.next()) {

				cnt = rs.getInt("maxno");
				cnt = cnt + 1;

				select_code = 1;

			}

			Prest = con.prepareStatement(DataQuery.INSERT_RESERVEMAS);
			Prest.setInt(1, cnt);
			Prest.setString(2, newbean.getuid());
			Prest.setString(3, newbean.getaccno());
			Prest.setString(4, newbean.getdtype());
			Prest.setString(5, newbean.getresdat());
			Prest.executeUpdate();

			Prest = con
					.prepareStatement("select * from member_reserve_view where access_no='"
							+ newbean.getaccno() + "' ORDER BY id");
			rs = Prest.executeQuery();

			int order = 0;
			if (rs.next()) {
				do {
					order = order + 1;

					if (rs.getString("member_code").equals(newbean.getuid())) {

						beanobject.setuid(rs.getString("member_code"));
						beanobject.setuname(rs.getString("member_name"));
						beanobject.setaccno(rs.getString("access_no"));
						beanobject.settitle(rs.getString("title"));
						String strI = "" + order;
						beanobject.setavailability(strI);

						break;
					}
				} while (rs.next());
			} else {
				order = 1;
			}

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return beanobject;
	}

	// Query Builder

	public List getQueryBuilderSearch(QueryBuilderBean newBean) {
		log4jLogger.info("start===========getQueryBuilderSearch====="
				+ newBean.getQueryText());

		List<Object> finalResults = new ArrayList<Object>();
		;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(newBean.getQueryText());
			rs = Prest.executeQuery();

			while (rs.next()) {
				QueryBuilderBean resultBean = new QueryBuilderBean();

				for (int i = 1; i < newBean.getListColumn().length; i++) {

					if (newBean.getListColumn()[i]
							.equalsIgnoreCase("access_no")) {
						resultBean.setAccessNo(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("author_name")) {
						resultBean.setAuthorName(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("title")) {
						resultBean.setTitle(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("call_no")) {
						resultBean.setCallNo(rs.getString(newBean
								.getListColumn()[i]));
					}

					else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("dept_name")) {
						resultBean.setDepartment(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("sub_name")) {
						resultBean.setSubject(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("sp_name")) {
						resultBean.setPublisher(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("availability")) {
						resultBean.setAvailability(rs.getString(newBean
								.getListColumn()[i]));
					}

					else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("isbn")) {
						resultBean
								.setIsbn(rs.getString(newBean.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("year_pub")) {
						resultBean
								.setYear(rs.getString(newBean.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("bprice")) {
						resultBean.setPrice(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("received_date")) {
						resultBean.setReceivedDate(Security_Counter
								.Counter_DateSet(rs.getDate(newBean
										.getListColumn()[i])));
					}

					else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("edition")) {
						resultBean.setEdition(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("location")) {
						resultBean.setLocation(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("keywords")) {
						resultBean.setKeywords(rs.getString(newBean
								.getListColumn()[i]));
					} else if (newBean.getListColumn()[i]
							.equalsIgnoreCase("language")) {
						resultBean.setLanguage(rs.getString(newBean
								.getListColumn()[i]));
					}
					resultBean.setListColumn(newBean.getListColumn());
				}

				finalResults.add(resultBean);
			}

			log4jLogger
					.info("================= End: [getQueryBuilderSearch] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;
	}
	
	
	public List getQBSearch(String filterQuery) {

		log4jLogger.info("start===========getQBSearch=====" + filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = DataQuery.SEARCH_QUESTION_BANK_FULLVIEW;
		
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		
		return sql.list();
	}	

	
	public List findFullViewQBSearch(String filterQuery) {

		log4jLogger.info("start===========findFullViewQBSearch====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = DataQuery.SEARCH_QUESTION_BANK;
		
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		return sql.list();
	}
	
	
	//--------------------------new arrivals------------------------------
	
	public List findMonthYearList(NewArrivalsBean bean){
		log4jLogger.info("start===========findMonthYearList=====");
		String doc="",Query="";
		
		if(!bean.getDocument().equalsIgnoreCase("")){
			Query=Query+" and document='"+bean.getDocument()+"'";
		}
		List finalResults = null;
		try{
			con = getSession().connection();
			st = con.createStatement();

			Prest = con.prepareStatement(DataQuery.GetYear+Query+" GROUP BY (YEAR(RECEIVED_DATE)),(MONTH(RECEIVED_DATE)) ORDER BY RECEIVED_DATE  DESC LIMIT 12");
			rs = Prest.executeQuery();
			
			finalResults = new ArrayList();
			
			
			while (rs.next()) {
				bean = new NewArrivalsBean();
				bean.setMonth(rs.getString("max_month"));
				bean.setYear(rs.getString("max_year"));
				finalResults.add(bean);
				
			}

			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
					return finalResults;
	}
	
	
	public List findSubjectList(){
		log4jLogger.info("start===========findSubjectList=====");
	
	NewArrivalsBean refBean = null;
	List<Object> result = new ArrayList<Object>();
	
	SQLQuery sql = getSession().createSQLQuery(DataQuery.SubjectList);		
	List list = sql.list();
	
	for(int i=0; i<list.size(); i++)
	{
		Object[] obj = (Object[]) list.get(i);
		refBean = new NewArrivalsBean();
	
		
		refBean.setMonth((obj[0].toString()));
		//refBean.setSubjectName(obj[1].toString());

		
		String subjectName=(obj[1].toString());
		subjectName = subjectName.substring(0,1).toUpperCase() + subjectName.substring(1).toLowerCase();
		refBean.setSubjectName(subjectName);
		
		result.add(refBean);		
	}	
	
	return result;
	}
	
	public List findNewArrivalSearchResult(NewArrivalsBean newbean){
		String string=newbean.getReceivedDate();
        String last4="",first2="";
        last4 = string.substring(string.length() - 4);
        first2=string.substring(0, 2);
    
        log4jLogger.info("dddddddddddddddddddddddddddddddddddddddddddddd"+newbean.getSubjectName());
	
		List<Object> result = new ArrayList<Object>();
		SQLQuery sql = getSession().createSQLQuery(DataQuery.NewArrivalList);	
					
		sql.setParameter("document",newbean.getDocument());
		sql.setParameter("receivedDate",last4+"-"+first2+"%");
		sql.setParameter("title","%"+newbean.getKeyword1()+"%");
		sql.setParameter("author_name","%"+newbean.getKeyword2()+"%");
		
		sql.setParameter("sub_name","%"+newbean.getSubjectName()+"%");
		
		
		
		List list = sql.list();

		for(int i=0; i<list.size(); i++)
		{
			
			Object[] obj = (Object[]) list.get(i);
			NewArrivalsBean bean=null;
			bean = new NewArrivalsBean();
			
		String author="",
				sres="",
				title="",
				publisher="",
				pubPlace="",
				subname="",
				deptname="",
				location="";
		
			
			
			bean.setAccessno(obj[0].toString());//ACCESS_NO
			bean.setIsbn(obj[5].toString());//isbn
			bean.setYearPub(obj[9].toString());//yearPub
			bean.setDocument(obj[16].toString());//document
			bean.setContents(obj[17].toString());//contents
			bean.setAvailability(obj[18].toString());//Availability
			bean.setEditon(obj[4].toString());//editon
			bean.setNotes(obj[12].toString());//notes
			bean.setPages(obj[13].toString());//pages
			bean.setCallNo(obj[6].toString());//callno
			bean.setReceivedDate(obj[15].toString());//received_date
		    
			
			
			author  = (obj[1].toString());//author_name
				if(!author.isEmpty()){
					author = author.substring(0,1).toUpperCase() + author.substring(1).toLowerCase();
				}
				bean.setKeyword1(author);
		 
			sres  = (obj[2].toString());//statement of responsibilits
				if(!sres.isEmpty()){
					sres = sres.substring(0,1).toUpperCase() + sres.substring(1).toLowerCase();
				}
				bean.setSres(sres);
			
			title=(obj[3].toString());//title
				if(!title.isEmpty()){
					title = title.substring(0,1).toUpperCase() + title.substring(1).toLowerCase();
				}
				bean.setKeyword2(title);
		
			publisher=(obj[7].toString());//publisher
				if(!publisher.isEmpty()){
					publisher = publisher.substring(0,1).toUpperCase() + publisher.substring(1).toLowerCase();
				}
				bean.setPublisher(publisher);
			
				pubPlace=(obj[8].toString());//place
				if(!pubPlace.isEmpty()){
					pubPlace = pubPlace.substring(0,1).toUpperCase() + pubPlace.substring(1).toLowerCase();	
				}
				bean.setPubplace(pubPlace);
				
			subname=(obj[10].toString());//subject
				if(!subname.isEmpty()){
					subname = subname.substring(0,1).toUpperCase() + subname.substring(1).toLowerCase();
				}
				bean.setSubjectName(subname);
		
			deptname  = (obj[11].toString());//dept_name
				if(!deptname.isEmpty()){
					deptname = deptname.substring(0,1).toUpperCase() + deptname.substring(1).toLowerCase();
				}
				bean.setDeptName(deptname);
				
			location=(obj[14].toString());//location
				if(!location.isEmpty()){
					location = location.substring(0,1).toUpperCase() + location.substring(1).toLowerCase();
				}
				bean.setLocation(location);
			
			result.add(bean);		
		}	
		
		
		return result;
		
	
	}

	
	
	public ArrayList<EBookSearchBean> findEBookAutoTitleSearch(String term,int branchID) {
		log4jLogger.info("start===========findEBookAutoTitleSearch=====");

		ArrayList<EBookSearchBean> list = new ArrayList<EBookSearchBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(title) FROM ebook_mas_view WHERE title LIKE ? and branch_code=? order by title ASC limit 20");

			ps.setString(1, "" + term + "%");
			ps.setInt(2,branchID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EBookSearchBean user = new EBookSearchBean();
				// user.setAccno(rs.getString("access_no"));
				user.setTitle(rs.getString("title"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<EBookSearchBean> findEBookAutoAuthorSearch(String term,int branchID) {
		log4jLogger.info("start===========findEBookAutoAuthorSearch=====");

		ArrayList<EBookSearchBean> list = new ArrayList<EBookSearchBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Author_Name) FROM ebook_mas_view WHERE Author_Name LIKE ? and branch_code=? order by Author_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ps.setInt(2,branchID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EBookSearchBean user = new EBookSearchBean();
				// user.setAccno(rs.getString("access_no"));
				user.setAuthorName(rs.getString("Author_Name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public ArrayList<EBookSearchBean> findEBookAutoSubjectSearch(String term,int branchID) {
		log4jLogger.info("start===========findEBookAutoSubjectSearch=====");

		ArrayList<EBookSearchBean> list = new ArrayList<EBookSearchBean>();
		// PreparedStatement ps = null;
		// String data;

		try {
			con = getSession().connection();

			// PreparedStatement ps =
			// con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con
					.prepareStatement("SELECT DISTINCT(Sub_Name) FROM ebook_mas_view WHERE Sub_Name LIKE ? and branch_code=? order by Sub_Name ASC limit 20");

			ps.setString(1, "" + term + "%");
			ps.setInt(2,branchID);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EBookSearchBean user = new EBookSearchBean();
				// user.setAccno(rs.getString("access_no"));
				user.setSubName(rs.getString("Sub_Name"));

				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List findEBookSearch(String filterQuery) {

		log4jLogger.info("start===========findEBookSearch=====" + filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("ebookSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString())
				.addScalar("access_no", Hibernate.STRING)
				.addScalar("title", Hibernate.STRING)
				.addScalar("author_name", Hibernate.STRING)
				.addScalar("call_no", Hibernate.STRING)
				.addScalar("role", Hibernate.STRING)
				.addScalar("edition", Hibernate.STRING)
				.addScalar("year_pub", Hibernate.STRING)
				.addScalar("pages", Hibernate.STRING)
				.addScalar("sub_code", Hibernate.STRING)
				.addScalar("Sub_Name", Hibernate.STRING)
				.addScalar("pub_code", Hibernate.STRING)
				.addScalar("publisher", Hibernate.STRING)
				.addScalar("dept_code", Hibernate.STRING)
				.addScalar("Dept_Name", Hibernate.STRING)
				.addScalar("branch_code", Hibernate.STRING)
				.addScalar("document", Hibernate.STRING)
				.addScalar("TYPE", Hibernate.STRING)
				.addScalar("received_date", Hibernate.STRING)
				.addScalar("content", Hibernate.STRING)
				.addScalar("url", Hibernate.STRING)
				.addScalar("isbn", Hibernate.STRING)
				.addScalar("keywords", Hibernate.STRING)
				.addScalar("branch_name", Hibernate.STRING);

		// searchDocmentCount(filterQuery);
		return sql.list();
	}

	@Override
	public AccountBean findCheckAccount(String uid, String pwd) {
		log4jLogger.info("start===========findCheckAccount=====");
		AccountBean bean=new AccountBean();
		
		int count=0;
	
		try {
		
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ACCOUNT_CHECK);
			Prest.setString(1, uid);
			Prest.setString(2, pwd);
			rs = Prest.executeQuery();
			
			if (rs.next()) {
				
			 if( (!rs.getString("Login_ID").equals(uid)) || (!rs.getString("Login_Password").equals(pwd)) )
			 {					
				 log4jLogger.info("++++++ Invalid User ID or Password in User Account Checking ++++++");
			 }
			 else if(rs.getString("Login_Flag").equalsIgnoreCase("No"))
			 {
				 log4jLogger.info("++++++ User ID has been locked in User Account Checking ++++++");
			 }
			 else
			 {				
				
				bean.setuname(rs.getString("Staff_Name"));
				bean.setuid(rs.getString("Login_ID"));
				bean.setauthor(rs.getString("Login_Password"));
	

				Prest = con.prepareStatement(DataQuery.ACCOUNT_ISSUE_COUNT);
				Prest.setString(1, uid);
				rs = Prest.executeQuery();
				if (rs.next()) {
					
					bean.setissuecount(rs.getString("issuecount"));
				}
				
				Prest = con.prepareStatement(DataQuery.ACCOUNT_RETURN_COUNT);
				Prest.setString(1, uid);
				rs = Prest.executeQuery();
				if (rs.next()) {
					
					bean.setreturncount(rs.getString("returncount"));
				}
				
				Prest = con.prepareStatement(DataQuery.ACCOUNT_RESERVE_COUNT);
				Prest.setString(1, uid);
				rs = Prest.executeQuery();
				if (rs.next()) {
					
					bean.setreservecount(rs.getString("reservecount"));
				}				
				count=1;
			 }
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return bean;

		
	}

	@Override
	public int findRegisterAllLogout() {
		log4jLogger.info("============= Inside findRegisterAllLogout() ============");
		int count=0;		
		String query = getSession().getNamedQuery("callEntryLog").getQueryString();
		SQLQuery sql = getSession().createSQLQuery(query);
		List list = sql.list();
		
		if(list != null && !list.isEmpty()) 
		{
			String saveQuery = getSession().getNamedQuery("saveReturnLog").getQueryString();
			SQLQuery logSave = getSession().createSQLQuery(saveQuery);
			
			for(int i = 0; i < list.size(); i++)
			{				
				Object[] obj = (Object[]) list.get(i);
				
				String out_time = "";
				String min_used = "";
				String member_code =  obj[0].toString();

				try {
					con = getSession().connection();
					Prest = con.prepareStatement("select DATE_FORMAT(NOW(),'%H:%i')as out_time ");	//getting out_time
					rs=Prest.executeQuery();
					if(rs.next()){
						 out_time=rs.getString("out_time");
					
					}
				
					con = getSession().connection();
					Prest = con.prepareStatement("SELECT TIMESTAMPDIFF(MINUTE,entry_time,NOW()) as min_used from entry_log where member_code='"+member_code+"'");	//Calculating minutes spent in library
					rs=Prest.executeQuery();
					if(rs.next()){
						min_used=rs.getString("min_used");
					}
					}
				catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null) {
							rs.close();
						}
						if (Prest != null) {
							Prest.close();
						}
						if (con != null) {
							
							con.close();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				logSave.setParameter("member", obj[0].toString());
				logSave.setParameter("entryTime", obj[1].toString());
				logSave.setParameter("returnTime", Security_Counter.Counter_DateText());
				logSave.setParameter("min", min_used);
				logSave.setParameter("inTime", obj[2].toString());
				logSave.setParameter("outTime", out_time);	
				count = logSave.executeUpdate();
			}
			
			String deleteQuery = getSession().getNamedQuery("deleteEntryLog").getQueryString();
			SQLQuery logDelete = getSession().createSQLQuery(deleteQuery);
			count = logDelete.executeUpdate();			
		}	
		
		log4jLogger.info("============= End findRegisterAllLogout() ============"+count);
		return count;
	}

	@Override
	public ArrayList<Searchbean> findTitleSearch(String term,int branchID) {
		log4jLogger.info("start===========findTitleSearch=====");
		
		ArrayList<Searchbean> list = new ArrayList<Searchbean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT title FROM full_search WHERE title LIKE ? and branch_code=? order by title ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ps.setInt(2, branchID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Searchbean user = new Searchbean();
				//user.setAccno(rs.getString("access_no"));
				user.setTitle(rs.getString("title"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<Searchbean> findAuthorSearch(String term,int branchID) {
		log4jLogger.info("start===========findAuthorSearch=====");
		
		ArrayList<Searchbean> list = new ArrayList<Searchbean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT Author_Name FROM full_search WHERE Author_Name LIKE ? and branch_code=? order by Author_Name ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ps.setInt(2, branchID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Searchbean user = new Searchbean();
				//user.setAccno(rs.getString("access_no"));
				user.setAuthor(rs.getString("Author_Name"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<Searchbean> findSubjectSearch(String term,int branchID) {
		log4jLogger.info("start===========findSubjectSearch=====");
		
		ArrayList<Searchbean> list = new ArrayList<Searchbean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT Sub_Name FROM full_search WHERE Sub_Name LIKE ? and branch_code=? order by Sub_Name ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ps.setInt(2, branchID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Searchbean user = new Searchbean();
				//user.setAccno(rs.getString("access_no"));
				user.setSubject(rs.getString("Sub_Name"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	
	public ArrayList<Searchbean> findAdvAutoSearch(String term,String flag,int branchID) {
		log4jLogger.info("start===========findAdvAutoSearch=====");
		
		ArrayList<Searchbean> list = new ArrayList<Searchbean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			//PreparedStatement ps = con.prepareStatement("SELECT DISTINCT "+flag+" FROM full_search WHERE "+flag+" LIKE ? order by "+flag+" ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT "+flag+" FROM full_search WHERE "+flag+" LIKE ? and branch_code=? order by "+flag+" ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ps.setInt(2, branchID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Searchbean user = new Searchbean();
				//user.setAccno(rs.getString("access_no"));
				user.setTitle(rs.getString(flag));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<NewscllipingBean> findNewsclippingSubjectAutoSearch(
			String term) {
		log4jLogger.info("start===========findNewsclippingSubjectAutoSearch=====");
		
		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT(Newspaper_subject) FROM newsclipping_mas WHERE Newspaper_subject LIKE ? order by Newspaper_subject ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NewscllipingBean user = new NewscllipingBean();
				//user.setAccno(rs.getString("access_no"));
				user.setNsubject(rs.getString("Newspaper_subject"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<NewscllipingBean> findNewsclippingKeywordsAutoSearch(
			String term) {
		log4jLogger.info("start===========findNewsclippingKeywordsAutoSearch=====");
		
		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT(Newspaper_key) FROM newsclipping_mas WHERE Newspaper_key LIKE ? order by Newspaper_key ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NewscllipingBean user = new NewscllipingBean();
				//user.setAccno(rs.getString("access_no"));
				user.setNkey(rs.getString("Newspaper_key"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<NewscllipingBean> findNewsclippingTypeAutoSearch(
			String term) {
		log4jLogger.info("start===========findNewsclippingTypeAutoSearch=====");
		
		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT(Newspaper_type) FROM newsclipping_mas WHERE Newspaper_type LIKE ? order by Newspaper_type ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NewscllipingBean user = new NewscllipingBean();
				//user.setAccno(rs.getString("access_no"));
				user.setNtype(rs.getString("Newspaper_type"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<NewscllipingBean> findNewsclippingNameAutoSearch(
			String term) {
		log4jLogger.info("start===========findNewsclippingNameAutoSearch=====");
		
		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT(Newspaper_name) FROM newsclipping_mas WHERE Newspaper_name LIKE ? order by Newspaper_name ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NewscllipingBean user = new NewscllipingBean();
				//user.setAccno(rs.getString("access_no"));
				user.setNname(rs.getString("Newspaper_name"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<NewscllipingBean> findNewsclippingTitleAutoSearch(
			String term) {
		log4jLogger.info("start===========findNewsclippingTitleAutoSearch=====");
		
		ArrayList<NewscllipingBean> list = new ArrayList<NewscllipingBean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT(Newspaper_title) FROM newsclipping_mas WHERE Newspaper_title LIKE ? order by Newspaper_title ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				NewscllipingBean user = new NewscllipingBean();
				//user.setAccno(rs.getString("access_no"));
				user.setNtitle(rs.getString("Newspaper_title"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<JournalSearchbean> findJournalAutoSearch(String term,
			int branchID) {
		log4jLogger.info("start===========findJournalAutoSearch=====");
		
		ArrayList<JournalSearchbean> list = new ArrayList<JournalSearchbean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
							
			//PreparedStatement ps = con.prepareStatement("SELECT DISTINCT "+flag+" FROM journal_view WHERE "+flag+" LIKE ? order by "+flag+" ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT(Jnl_Name) FROM journal_mas WHERE Jnl_Name LIKE ? and branch_code=? order by Jnl_Name ASC limit 20");
							
			ps.setString(1, "" +term+ "%");
			ps.setInt(2, branchID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				JournalSearchbean user = new JournalSearchbean();
				
				user.setJnlName(rs.getString("Jnl_Name"));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<Searchbean> findQuickAutoSearch(String term, String flag) {
		log4jLogger.info("start===========findQuickAutoSearch=====");
		
		ArrayList<Searchbean> list = new ArrayList<Searchbean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			//PreparedStatement ps = con.prepareStatement("SELECT * FROM full_search WHERE title like ? order by title ASC limit 20");
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT "+flag+" FROM full_search WHERE "+flag+" LIKE ? order by "+flag+" ASC limit 20");
							
			ps.setString(1, "" +term+ "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Searchbean user = new Searchbean();
				//user.setAccno(rs.getString("access_no"));
				user.setTitle(rs.getString(flag));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	@Override
	public ArrayList<Searchbean> findQueryAutoSearch(String term, String flag) {
		log4jLogger.info("start===========findQueryAutoSearch=====");
		
		ArrayList<Searchbean> list = new ArrayList<Searchbean>();
		//PreparedStatement ps = null;
		//String data;
		
		try {
			con = getSession().connection();
			
			PreparedStatement ps = con.prepareStatement("SELECT DISTINCT "+flag+" FROM full_search WHERE "+flag+" LIKE ? order by "+flag+" ASC limit 20");
			
			ps.setString(1, "" +term+ "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Searchbean user = new Searchbean();
				//user.setAccno(rs.getString("access_no"));
				user.setTitle(rs.getString(flag));
								
				list.add(user);
			}
						
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public List findFullView(String filterQuery,String document,String branch) 
	{

		log4jLogger.info("start===========findFullViewSearch====="
				+ filterQuery);
		StringBuffer sb = new StringBuffer();
		String namedQuery = getSession().getNamedQuery("fullViewSearchQuery")
				.getQueryString();
		if (filterQuery != null) {
			sb.append(namedQuery);
			sb.append(" " + filterQuery);
		} else {
			sb.append(namedQuery);
		}
		SQLQuery sql = getSession().createSQLQuery(sb.toString());
		return sql.list();
		}

	public List findAdvancedSearch(String filterQuery,String document,String OrderQuery) 
	{
		
	log4jLogger.info("start===========findAdvancedSearch====="+filterQuery);
	log4jLogger.info("start===========findAdvancedSearchOrderQuery====="+OrderQuery);
	StringBuffer sb = new StringBuffer();
	String namedQuery = "";
	if(document.equalsIgnoreCase("Article"))
	{
		namedQuery = getSession().getNamedQuery("articleSearchQuery").getQueryString();
	}
	else if(document.equalsIgnoreCase("EBOOK"))
	{
		namedQuery = getSession().getNamedQuery("ebookSimpleSearchQuery").getQueryString();
	}
	else
		
		
	{
		namedQuery = getSession().getNamedQuery("advancedSearchQuery").getQueryString();
		
		log4jLogger.info("start===========&&&&&&&&&&&&&&&&&&&&&");
	}
	if(filterQuery != null)
	{
		sb.append(namedQuery);
		sb.append(" " + filterQuery + OrderQuery);
	}
	else
	{
		sb.append(namedQuery);
	}
	SQLQuery sql = 	getSession().createSQLQuery(sb.toString());
	if(document.equalsIgnoreCase("Article"))
	{
		sql.addScalar("access_no");
		sql.addScalar("call_no");
		sql.addScalar("title");
		sql.addScalar("author_name");
		sql.addScalar("edition");
		sql.addScalar("location");
		sql.addScalar("availability");
		sql.addScalar("document");
		sql.addScalar("year_pub");
		sql.addScalar("Bprice");
		sql.addScalar("place");
		sql.addScalar("binding");
		sql.addScalar("Dept_Name");
		sql.addScalar("phy_media");
		sql.addScalar("sp_name");
		sql.addScalar("volno");
		sql.addScalar("sub_name");
		sql.addScalar("jnl_name");
		sql.addScalar("issue_no");
		sql.addScalar("atl_page_nos");

		
	}
	else if(document.equalsIgnoreCase("EBOOK"))
	{
		sql.addScalar("access_no");
		sql.addScalar("call_no");
		sql.addScalar("title");
		sql.addScalar("author_name");
		sql.addScalar("edition");
		sql.addScalar("location");
		sql.addScalar("availability");
		sql.addScalar("document");
		sql.addScalar("year_pub");
		sql.addScalar("Bprice");
		sql.addScalar("place");
		sql.addScalar("sp_name");
		sql.addScalar("volno");
	}
	else{
		
		log4jLogger.info("start===========UUUUUUUUUUUUUUUUU");
		sql.addScalar("access_no");
		sql.addScalar("call_no");
		sql.addScalar("title");
		sql.addScalar("author_name");
		sql.addScalar("edition");
		sql.addScalar("location");
		sql.addScalar("availability");
		sql.addScalar("document");
		sql.addScalar("year_pub");
		sql.addScalar("Bprice");
		sql.addScalar("place");
		sql.addScalar("Dept_Name");
		sql.addScalar("sp_name");
		sql.addScalar("volno");
		sql.addScalar("sub_name");
		sql.addScalar("gift_purchase");
		sql.addScalar("invoice_no");
		sql.addScalar("phy_media");
		sql.addScalar("binding");
		sql.addScalar("add_field1");
		sql.addScalar("add_field2");
		sql.addScalar("add_field3");
		sql.addScalar("branch_name");
	}
	searchDocmentCount(filterQuery);
	
	return  sql.list();
	
	
}


}
