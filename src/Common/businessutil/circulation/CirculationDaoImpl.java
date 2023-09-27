package Common.businessutil.circulation;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import Common.DataQuery;
import Common.Security;
import Common.Security_Counter;
import Common.businessutil.mail.MailDaoImpl;
import Common.businessutil.mail.MailQueryUtill;
import Common.businessutil.sms.SmsDaoImpl;
import Lib.Auto.Binding.BindingBean;
import Lib.Auto.Binding_Books.BookBindingBean;
import Lib.Auto.Counter.COUNTER_QUERY;
import Lib.Auto.Counter.CounterBean;
import Lib.Auto.Counter.CounterMemberBean;
import Lib.Auto.Counter.ReserveBean;
import Lib.Auto.Login.UserIdbean;
import Lib.Auto.Transfer_Books.BookTransferBean;
import Lib.Auto.Payment.PaymentBean;
import Login.User;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;

public class CirculationDaoImpl extends BaseDBConnection implements
		CirculationDao {

	private static Logger log4jLogger = Logger
			.getLogger(CirculationDaoImpl.class);

	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.Statement st = null;

	CounterBean counterbeanobject = new CounterBean();

	public User findById(String userId, String pwd) {
		User user = null;
		try {
			con = getSession().connection();
			String sql = "select Login_ID, User_Rights from login_mas where Login_ID ='"
					+ userId + "' and Login_Password='" + pwd + "'";
			Prest = con.prepareStatement(sql);
			rs = Prest.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getString(1));
				user.setPassword(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (Prest != null) {
					Prest.close();
					Prest = null;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	public CounterBean findCounterMember(String code, int branchID) {
		log4jLogger.info("findCounterMember(Strin code) method begin" + code);
		String group_name = "";

		log4jLogger.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + branchID);

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER);
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {

				counterbeanobject.setMcode(rs.getString("member_code"));
				counterbeanobject.setMname(rs.getString("member_name"));
				counterbeanobject.setPhoto1(rs.getBytes("photo")); // For Image
																	// By RK
				String group = rs.getString("group_name");
				counterbeanobject.setGroup(group);
				counterbeanobject.setDesig(rs.getString("desig_name"));
				counterbeanobject.setDept(rs.getString("dept_name"));
				counterbeanobject.setValidDate(Security_Counter
						.Counter_DateGet(rs.getDate("expiry_date")));

				counterbeanobject.setImg(rs.getString("photo"));
				counterbeanobject.setCourse(rs.getString("course_name"));
				counterbeanobject.setMajor(rs.getString("course_major"));
				counterbeanobject.setYear(rs.getString("cyear"));
				counterbeanobject.setSLock(rs.getString("SLock"));

				/*if (branchID!=0) // For Super Admin && Librarian login
				{*/

					log4jLogger.info("if branchid<=3 true inside code "
							+ branchID);

					counterbeanobject.setGeligible(rs.getInt("gelg"));
					counterbeanobject.setGperiod(rs.getInt("gper"));

					counterbeanobject.setBeligible(rs.getInt("belg"));
					counterbeanobject.setBperiod(rs.getInt("bper"));
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					counterbeanobject.setBbperiod(rs.getInt("bbper"));

					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					counterbeanobject.setNbperiod(rs.getInt("nbper"));
					counterbeanobject.setJeligible(rs.getInt("jelg"));
					counterbeanobject.setJperiod(rs.getInt("jlper"));
					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					counterbeanobject.setBvperiod(rs.getInt("bvper"));
					counterbeanobject.setTeligible(rs.getInt("telg"));
					counterbeanobject.setTperiod(rs.getInt("tper"));
					counterbeanobject.setSeligible(rs.getInt("selg"));
					counterbeanobject.setSperiod(rs.getInt("sper"));
					counterbeanobject.setPeligible(rs.getInt("pelg"));
					counterbeanobject.setPperiod(rs.getInt("pper"));
					counterbeanobject.setReligible(rs.getInt("relg"));
					counterbeanobject.setRperiod(rs.getInt("rper"));

					counterbeanobject.setGrperiod(rs.getInt("grper")); // By RK
					counterbeanobject.setBrperiod(rs.getInt("brper"));
					counterbeanobject.setBbrperiod(rs.getInt("bbrper"));
					counterbeanobject.setNbrperiod(rs.getInt("nbrper"));
					counterbeanobject.setJrperiod(rs.getInt("jrper"));
					counterbeanobject.setBvrperiod(rs.getInt("bvrper"));
					counterbeanobject.setTrperiod(rs.getInt("trper"));
					counterbeanobject.setSrperiod(rs.getInt("srper"));
					counterbeanobject.setPrperiod(rs.getInt("prper"));
					counterbeanobject.setRrperiod(rs.getInt("rrper"));
				//}

//				 else {
//
//					log4jLogger.info("if branchid<=3 false inside code "
//							+ branchID);
//
//					// By RK RECENT 26-11-2014
//					counterbeanobject.setGeligible(rs.getInt("DeptEligible"));
//					counterbeanobject.setGperiod(rs.getInt("DeptPeriod"));
//					counterbeanobject.setGrperiod(rs.getInt("DeptRPeriod"));
//					counterbeanobject.setDeptBranch(rs
//							.getInt("Dept_BranchCode"));
//				}

				counterbeanobject.setBranchID(rs.getInt("branch_code")); // For
																			// Titan
				counterbeanobject.setBranchName(rs.getString("branch_name"));
				counterbeanobject.setReserve(rs.getInt("breserve"));

				group_name = group;
				bookcount_doctype(counterbeanobject.getMcode(), branchID); // Check
																			// by
																			// rk
			}
		} catch (Exception e) {
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

		return counterbeanobject;
	}

	public int findCounterGroup(String group,int branchID) {
		log4jLogger.info("start===========findCounterGroup=====");
		int group_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_GROUP);
			Prest.setString(1, group);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {
				group_code = rs.getInt("group_code");
			}
		} catch (Exception e) {
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

		return group_code;
	}

	public List findIssueDetailsMember(String code, int branchID) {
		log4jLogger.info("start===========findIssueDetailsMember=====");

		int group_code = 0;
		CounterBean counterbeanobject = new CounterBean();
		// String document="JOURNAL";

		ArrayList<String> DEATILS = new ArrayList();
		try {
			String strsql = "";
			if (branchID > 0) {
				strsql = " and branch_code=" + branchID;
			}

			Prest = con
					.prepareStatement(DataQuery.SELECT_ISSUE_MEMBER + strsql);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			while (rs.next()) {
				DEATILS.add(rs.getString("Access_no"));
				DEATILS.add(rs.getString("title"));
				DEATILS.add(rs.getString("author_name"));
				DEATILS.add(rs.getString("Availability"));
				DEATILS.add(rs.getString("Member_Code"));
				DEATILS.add(Security_Counter.Counter_DateGet(rs
						.getDate("issue_date")));
				DEATILS.add(Security_Counter.Counter_DateGet(rs
						.getDate("due_date")));
				DEATILS.add(rs.getString("Issue_Type"));
				DEATILS.add(rs.getString("Staff_Code"));
				DEATILS.add(rs.getString("Doc_Type"));

				group_code = 1;
			}
			// }

		} catch (Exception e) {
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

		return DEATILS;
	}

	public CounterMemberBean findBookCountDoctype(String mctcode) {
		log4jLogger.info("start===========findBookCountDoctype=====");
		int group_code = 0;

		CounterMemberBean counterbeanobject = new CounterMemberBean();

		try {
			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BOOK");
			rs = Prest.executeQuery();

			if (rs.next()) {
				counterbeanobject.setBissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "NON BOOK");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setNbissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BOOK BANK");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setBbissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "STANDARD");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setSissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BACK VOLUME");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setBvissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "REPORT");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setRissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "THESIS");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setTissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "PROCEEDING");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setPissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(DataQuery.SELECT_BOOKCOUNT);
			Prest.setString(1, mctcode);
			Prest.setString(2, "JOURNAL");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setJissued(rs.getInt("cnt"));
			}

		} catch (Exception e) {
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

		return counterbeanobject;
	}

	public CounterMemberBean findCounterBook(String code, String document,
			String mcode,int branchID) {
		log4jLogger.info("findCounterBook(Strin code) method begin" + code);
		String acc_no = "";
		CounterMemberBean beanobject = new CounterMemberBean();
		try {
			con = getSession().connection();

			if (document.equalsIgnoreCase("JOURNAL")) {
				log4jLogger
						.info("--------------INSIDE JOURAL findCounterBook -----------------");
				Prest = con.prepareStatement(DataQuery.SELECT_JNL_Issue);
				Prest.setString(1, code);
				rs = Prest.executeQuery();
				if (rs.next()) {
					acc_no = rs.getString("Issue_Access_No");
					beanobject.setAccno(rs.getString("Issue_Access_No"));
					beanobject.setTitle(rs.getString("jnl_name"));
					beanobject.setAuthor("");
					beanobject.setCallNo("");
					beanobject.setPublisher(rs.getString("publisher"));
					String BOOK_AVL = rs.getString("Availability");
					beanobject.setAvail(BOOK_AVL);
					String doctype = rs.getString("Doc_Type");
					beanobject.setDoc(doctype);
				}
			} else {

				Prest = con.prepareStatement(DataQuery.checkTitleAlreadyIssued);
				Prest.setString(1, code);
				Prest.setString(2, mcode);

				rs = Prest.executeQuery();
				if (rs.next()) {
					beanobject.setTitleCheck("true");// true//shek
					
					log4jLogger.info("111111 this Book:::::::::::::::::::::::::::::::::::::TRUE");
					
				} else {
					beanobject.setTitleCheck("false");// true//shek
					
					log4jLogger.info("2222 this Book:::::::::::::::::::::::::::::::::::::FALSE");
					
				}

				log4jLogger
						.info("AlreadIssued this Book:::::::::::::::::::::::::::::::::::::"
								+ beanobject.getTitleCheck());

				Prest = con.prepareStatement(DataQuery.SELECT_BOOK);
				Prest.setString(1, code);
				Prest.setInt(2, branchID);
				rs = Prest.executeQuery();
				if (rs.next()) {
					acc_no = rs.getString("access_no");
					beanobject.setAccno(rs.getString("access_no"));
					beanobject.setTitle(rs.getString("title"));
					beanobject.setAuthor(rs.getString("author_name"));
					beanobject.setCallNo(rs.getString("call_no"));
					beanobject.setPublisher(rs.getString("publisher"));
					String BOOK_AVL = rs.getString("Availability");
					beanobject.setAvail(BOOK_AVL);
					String doctype = rs.getString("document");
					beanobject.setDoc(doctype);
					beanobject.setBranchID(rs.getInt("Branch_Code")); // For
																		// Titan
					
					
					
					log4jLogger.info("333333 this Book:::::::::::::::::::::::::::::::::::::true");
					
				}
			}

		} catch (Exception e) {
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

		return beanobject;
	}

	public int findIssueCheck(String code, String mcode,int branchID) {
		log4jLogger.info("findIssueCheck(Strin code,Sting mcode) method begin"
				+ code + "--" + mcode);
		// CounterBean counterbeanobject = null;
		int chk_code = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUEMAS_ONLY);
			Prest.setString(1, mcode);
			Prest.setString(2, code);
			Prest.setInt(3, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {

				chk_code = 1;

			}
		} catch (Exception e) {
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
		return chk_code;
	}

	public CounterBean findCounterIssueCheck(String code, int branchID) {
		log4jLogger.info("findCounterIssueCheck(Strin code) method begin"
				+ code);
		String acc_no = "";
		String duedate = "";

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUEMAS);
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {

				String loadmemcode = rs.getString("member_code");
				String issuedate = Security_Counter.Counter_DateFormat(rs
						.getDate("issue_date"));
				String duedate1 = Security_Counter.Counter_DateFormat(rs
						.getDate("due_date"));
				duedate = Security_Counter.Counter_DateGet(rs
						.getDate("due_date"));
				String issuetype = rs.getString("issue_type");
				String doctype_Issue = rs.getString("doc_type");
				int Groups = Integer.parseInt(rs.getString("group_code"));

				counterbeanobject.setCallduedate(duedate1); // RK Check 1
				counterbeanobject.setCallissdate(issuedate); // RK Check
				DAYSCALRETURN(duedate, Groups);
				ISSUEDETAILSMEMBER(loadmemcode, branchID);
				RESERVEDETAILS(code);
				bookcount_doctype(loadmemcode, branchID);
				MEMBER_LOAD_ISSUE(loadmemcode, doctype_Issue, branchID);

				acc_no = "ITRUE";
				counterbeanobject.setIssue_Check(acc_no);

			}
		} catch (Exception e) {
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

		return counterbeanobject;
	}

	public CounterBean findEpc(String epc) {
		log4jLogger.info("findEPC ID ------------------------ method begin"
				+ epc);
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_EPC);
			Prest.setString(1, epc);
			rs = Prest.executeQuery();
			if (rs.next()) {

				counterbeanobject.setAccno(rs.getString("access_no"));
				log4jLogger
						.info("finD ACCESS_NO ------------------------ method begin"
								+ rs.getString("access_no"));

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

	public CounterBean findCardUID(String UID) {
		log4jLogger.info("findCardUID  ------------------------ method begin"
				+ UID);
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_UID);
			Prest.setString(1, UID);
			rs = Prest.executeQuery();
			if (rs.next()) {

				counterbeanobject.setMcode(rs.getString("member_code"));
				log4jLogger
						.info("finD member_code ------------------------ method begin"
								+ rs.getString("member_code"));

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

	public void DAYSCALRETURN(String DueDate, int Groups) throws SQLException {
		log4jLogger.info("DAYSCALRETURN===================== method begin");

		String no_of_days = "";
		String sql = "";
		int no = 1;
		String ddate = "";
		String Rdate = "";
		String Ddate = "";
		int Holiday_Check = 0, cal = 0, Holiday = 0;
		double temp = 0;
		ArrayList DEATILS = new ArrayList();

		java.util.StringTokenizer stz_du = new java.util.StringTokenizer(
				DueDate, "-");
		int diy = Integer.parseInt(stz_du.nextToken());
		int dim = Integer.parseInt(stz_du.nextToken());
		int did = Integer.parseInt(stz_du.nextToken());
		ddate = diy + "-" + dim + "-" + did;
		try {
			con = getSession().connection();
			/*Prest = con.prepareStatement("select datediff('"
					+ Security_Counter.Counter_DateText() + "','" + ddate
					+ "') as no_of_days");*/

	//		Holiday Update After Issued Book - COVID 19
			
			
			Prest = con.prepareStatement("SELECT  * , date_diffrence-holiday_count AS no_of_days FROM " 
					+ "((SELECT DATEDIFF(NOW(),'"+ddate+"') AS date_diffrence) AS date_diffrence ,"
					+ "(SELECT COUNT(*) AS holiday_count FROM holiday_mas "
					+ "WHERE DATE(leave_date) BETWEEN '"+ddate+"' AND NOW() ) AS holiday_count"
					+ ") ;");
			
			
			rs = Prest.executeQuery();

			if (rs.next()) {
				no_of_days = rs.getString("no_of_days");

				int n = Integer.parseInt(no_of_days);

				if (n < 0) {

					counterbeanobject.setTemp(new Double(temp));
				}
				Holiday = n;

			}

			FINE_CALL(Groups, Holiday);
		} catch (Exception e) {
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

	public void FINE_CALL(int gd, int Holiday) throws SQLException {

		log4jLogger.info("FINE_CALL==================method begin");

		double temp = 0;
		int cal = 0;
		ArrayList DEATILS = new ArrayList();
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_FINEMAS);
			Prest.setInt(1, gd);
			rs = Prest.executeQuery();
			while (rs.next() && (Holiday > 0)) {
				int period = rs.getInt("Fine_Period");
				float amt = rs.getFloat("Fine_Amount");
				String type = rs.getString("period_type");

				if (type.equals("DAILY")) {
					if (Holiday < period) {
						temp = temp + (Holiday * amt);
						Holiday = Holiday - period;

					} else if (Holiday >= period) {
						temp = temp + (period * amt);

						Holiday = Holiday - period;

					}
				}

				else if (type.equals("WEEKLY")) {

					if (Holiday < period) {
						if (Holiday % 7 == 0) {
							temp = temp + ((Holiday / 7) * amt);
							Holiday = Holiday - period;
						} else if (Holiday % 7 >= 0) {
							temp = temp + (((Holiday / 7) + 1) * amt);
							Holiday = Holiday - period;
						}

					} else if (Holiday >= period) {
						if (period % 7 == 0) {
							temp = temp + ((period / 7) * amt);
							Holiday = Holiday - period;
						} else if (period % 7 >= 0) {
							temp = temp + (((period / 7) + 1) * amt);
							Holiday = Holiday - period;
						}

					}

				} else if (type.equals("MONTHLY")) {

					if (Holiday < period) {
						if (Holiday % 30 == 0) {
							temp = temp + ((Holiday / 30) * amt);
							Holiday = Holiday - period;
						} else if (Holiday % 30 >= 0) {
							temp = temp + (((Holiday / 30) + 1) * amt);
							Holiday = Holiday - period;
						}

					} else if (Holiday >= period) {
						if (period % 30 == 0) {
							temp = temp + ((period / 30) * amt);
							Holiday = Holiday - period;
						} else if (period % 30 >= 0) {
							temp = temp + (((period / 30) + 1) * amt);
							Holiday = Holiday - period;
						}

					}

				} else if (type.equals("YEARLY")) {

					if (Holiday < period) {
						if (Holiday % 365 == 0) {
							temp = temp + ((Holiday / 365) * amt);
							Holiday = Holiday - period;
						} else if (Holiday % 365 >= 0) {
							temp = temp + (((Holiday / 365) + 1) * amt);
							Holiday = Holiday - period;
						}

					} else if (Holiday >= period) {
						if (period % 365 == 0) {
							temp = temp + ((period / 365) * amt);
							Holiday = Holiday - period;
						} else if (period % 365 >= 0) {
							temp = temp + (((period / 365) + 1) * amt);
							Holiday = Holiday - period;
						}

					}
				}

			}

			counterbeanobject.setTemp(new Double(temp));
		} catch (Exception e) {
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

	public void ISSUEDETAILSMEMBER(String ssuemcode, int branchID)
			throws SQLException, ServletException {

		log4jLogger
				.info("ISSUEDETAILSMEMBER===================== ====================method begin");
		ArrayList DEATILS = new ArrayList();

		try {
			String strsql = "";
			if (branchID > 0) {
				strsql = " and branch_code=" + branchID;
			}

			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.SELECT_ISSUE_MEMBER + strsql);
			Prest.setString(1, ssuemcode);
			rs = Prest.executeQuery();

			while (rs.next()) {
				DEATILS.add(rs.getString("Access_no"));
				DEATILS.add(rs.getString("title"));
				DEATILS.add(rs.getString("author_name"));
				DEATILS.add(rs.getString("Availability"));
				DEATILS.add(rs.getString("Member_Code"));
				DEATILS.add(Security_Counter.Counter_DateGet(rs
						.getDate("issue_date")));
				DEATILS.add(Security_Counter.Counter_DateGet(rs
						.getDate("due_date")));
				DEATILS.add(rs.getString("Issue_Type"));
				DEATILS.add(rs.getString("Staff_Code"));
				DEATILS.add(rs.getString("Doc_Type"));

			}

			/**
			 * Prest = con.prepareStatement(DataQuery.SELECT_JNLISSUE_MEMBER);
			 * // 04-03-2014 Prest.setString(1, ssuemcode); rs =
			 * Prest.executeQuery();
			 * 
			 * while (rs.next()) {
			 * 
			 * DEATILS.add(rs.getString("Issue_Access_No"));
			 * DEATILS.add(rs.getString("jnl_name")); DEATILS.add("");
			 * DEATILS.add(rs.getString("Availability"));
			 * DEATILS.add(rs.getString("Member_Code"));
			 * DEATILS.add(Security_Counter
			 * .Counter_DateGet(rs.getDate("issue_date")));
			 * DEATILS.add(Security_Counter
			 * .Counter_DateGet(rs.getDate("due_date")));
			 * DEATILS.add(rs.getString("Issue_Type"));
			 * DEATILS.add(rs.getString("Staff_Code"));
			 * DEATILS.add(rs.getString("Doc_Type"));
			 * 
			 * }
			 */

			counterbeanobject.setCounterList(DEATILS);

		} catch (Exception e) {
			throw new ServletException(e);
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

	public void RESERVEDETAILS(String ssueaccno) throws SQLException {
		log4jLogger
				.info("RESERVEDETAILS===================== ====================method begin");
		ArrayList RCRESERVE = new ArrayList();
		try {
			con = getSession().connection();
			Prest = con
					.prepareStatement("select * from member_reserve_view where access_no='"
							+ ssueaccno + "'");
			rs = Prest.executeQuery();
			while (rs.next()) {
				String r1 = rs.getString("ID");
				String r2 = rs.getString("Member_Code");
				String r3 = rs.getString("Access_No");
				String r4 = rs.getString("Doc_Type");
				String r5 = Security_Counter.Counter_DateGet(rs
						.getDate("Res_Date"));
				String r6 = rs.getString("Member_Name");

				RCRESERVE.add(r1);
				RCRESERVE.add(r2);
				RCRESERVE.add(r3);
				RCRESERVE.add(r4);
				RCRESERVE.add(r5);
				RCRESERVE.add(r6);

			}
			counterbeanobject.setCunterList_RESERVE(RCRESERVE);
		} catch (Exception e) {
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

	public void bookcount_doctype(String mctcode, int branchID)
			throws SQLException, IOException, ServletException {

		try {
			String strsql = "";
			if (branchID > 0) {
				strsql = " and branch_code=" + branchID;
			}

			con = getSession().connection();
			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BOOK");
			rs = Prest.executeQuery();

			if (rs.next()) {
				counterbeanobject.setBissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "NON BOOK");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setNbissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BOOK BANK");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setBbissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "STANDARD");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setSissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "BACK VOLUME");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setBvissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "REPORT");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setRissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "THESIS");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setTissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "PROCEEDING");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setPissued(rs.getInt("cnt"));
			}

			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_BOOKCOUNT
					+ strsql);
			Prest.setString(1, mctcode);
			Prest.setString(2, "JOURNAL");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setJissued(rs.getInt("cnt"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			//throw new ServletException(e);
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

	public void MEMBER_LOAD_ISSUE(String loadmemcode, String doctype,
			int branchID) throws SQLException, IOException,
			javax.servlet.ServletException {
		log4jLogger.info("MEMBER_LOAD_ISSUE====method begin ");

		int period_all = 0;
		String status = "";
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(COUNTER_QUERY.SELECT_MEMBER);
			Prest.setString(1, loadmemcode);
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setMcode(rs.getString("member_code"));
				counterbeanobject.setMname(rs.getString("member_name"));
				counterbeanobject.setGroup(rs.getString("group_name"));
				counterbeanobject.setDesig(rs.getString("desig_name"));
				counterbeanobject.setPhoto1(rs.getBytes("photo")); // For Image
																	// By RK
				counterbeanobject.setDept(rs.getString("dept_name"));
				counterbeanobject.setValidDate(Security_Counter
						.Counter_DateGet(rs.getDate("expiry_date")));
				counterbeanobject.setImg(rs.getString("photo"));
				counterbeanobject.setCourse(rs.getString("course_name"));
				counterbeanobject.setMajor(rs.getString("course_major"));
				counterbeanobject.setYear(rs.getString("cyear"));

				counterbeanobject.setSLock(rs.getString("SLock"));
				counterbeanobject.setBranchID(rs.getInt("branch_code")); // For
																			// Titan
				counterbeanobject.setBranchName(rs.getString("branch_name"));

				/*if (branchID <= 2) {*/

					status = rs.getString("status");
					if (status.equals("V1")) {

						counterbeanobject.setGeligible(rs.getInt("gelg"));
						period_all = rs.getInt("gper");
						counterbeanobject.setGperiod(period_all);

						counterbeanobject.setNbeligible(0);
						counterbeanobject.setNbperiod(0);
						counterbeanobject.setJeligible(0);
						counterbeanobject.setJperiod(0);
						counterbeanobject.setBveligible(0);
						counterbeanobject.setBvperiod(0);
						counterbeanobject.setTeligible(0);
						counterbeanobject.setTperiod(0);
						counterbeanobject.setSeligible(0);
						counterbeanobject.setSperiod(0);
						counterbeanobject.setPeligible(0);
						counterbeanobject.setPperiod(0);
						counterbeanobject.setReligible(0);
						counterbeanobject.setRperiod(0);
						counterbeanobject.setBbeligible(0);
						counterbeanobject.setBbperiod(0);
						counterbeanobject.setBeligible(0);
						counterbeanobject.setBperiod(0);

					} else if (status.equals("V2")) {
						counterbeanobject.setGeligible(0);
						if (doctype.equals("BOOK")) {
							counterbeanobject.setBeligible(rs.getInt("belg"));
							period_all = rs.getInt("bper");
							counterbeanobject.setBperiod(period_all);

						} else if (doctype.equals("BOOK BANK")) {
							counterbeanobject.setBbeligible(rs.getInt("bbelg"));
							period_all = rs.getInt("bbper");
							counterbeanobject.setBbperiod(period_all);

						} else if (doctype.equals("NON BOOK")) {

							counterbeanobject.setNbeligible(rs.getInt("nbelg"));
							period_all = rs.getInt("nbper");
							counterbeanobject.setNbperiod(period_all);

						} else if (doctype.equals("JOURNAL")) {

							counterbeanobject.setJeligible(rs.getInt("jelg"));
							period_all = rs.getInt("jlper");
							counterbeanobject.setJperiod(period_all);
						} else if (doctype.equals("BACK VOLUME")) {

							counterbeanobject.setBveligible(rs.getInt("bvelg"));
							period_all = rs.getInt("bvper");
							counterbeanobject.setBvperiod(period_all);
						} else if (doctype.equals("THESIS")) {
							counterbeanobject.setTeligible(rs.getInt("telg"));
							period_all = rs.getInt("tper");
							counterbeanobject.setTperiod(period_all);

						} else if (doctype.equals("STANDARD")) {

							counterbeanobject.setSeligible(rs.getInt("selg"));
							period_all = rs.getInt("sper");
							counterbeanobject.setSperiod(period_all);

						} else if (doctype.equals("PROCEEDING")) {

							counterbeanobject.setPeligible(rs.getInt("pelg"));
							period_all = rs.getInt("pper");
							counterbeanobject.setPperiod(period_all);

						} else if (doctype.equals("REPORT")) {

							counterbeanobject.setReligible(rs.getInt("relg"));
							period_all = rs.getInt("rper");
							counterbeanobject.setRperiod(period_all);
						} else {
							String STOP = "STOP";
						}

					} else if (status.equals("V3")) {

						counterbeanobject.setGeligible(rs.getInt("gelg"));
						period_all = rs.getInt("gper");
						counterbeanobject.setGperiod(period_all);

						if (doctype.equals("BOOK")) {
							counterbeanobject.setBeligible(rs.getInt("belg"));
							period_all = rs.getInt("bper");
							counterbeanobject.setBperiod(period_all);

						} else if (doctype.equals("BOOK BANK")) {
							counterbeanobject.setBbeligible(rs.getInt("bbelg"));
							period_all = rs.getInt("bbper");
							counterbeanobject.setBbperiod(period_all);

						} else if (doctype.equals("NON BOOK")) {

							counterbeanobject.setNbeligible(rs.getInt("nbelg"));
							period_all = rs.getInt("nbper");
							counterbeanobject.setNbperiod(period_all);

						} else if (doctype.equals("JOURNAL")) {

							counterbeanobject.setJeligible(rs.getInt("jelg"));
							period_all = rs.getInt("jlper");
							counterbeanobject.setJperiod(period_all);

						} else if (doctype.equals("BACK VOLUME")) {

							counterbeanobject.setBveligible(rs.getInt("bvelg"));
							period_all = rs.getInt("bvper");
							counterbeanobject.setBvperiod(period_all);

						} else if (doctype.equals("THESIS")) {

							counterbeanobject.setTeligible(rs.getInt("telg"));
							period_all = rs.getInt("tper");
							counterbeanobject.setTperiod(period_all);

						} else if (doctype.equals("STANDARD")) {

							counterbeanobject.setSeligible(rs.getInt("selg"));
							period_all = rs.getInt("sper");
							counterbeanobject.setSperiod(period_all);

						} else if (doctype.equals("PROCEEDING")) {

							counterbeanobject.setPeligible(rs.getInt("pelg"));
							period_all = rs.getInt("pper");
							counterbeanobject.setPperiod(period_all);

						} else if (doctype.equals("REPORT")) {

							counterbeanobject.setReligible(rs.getInt("relg"));
							period_all = rs.getInt("rper");
							counterbeanobject.setRperiod(period_all);
						} else {
							String STOP_WORK = "_WORKSTOP";
						}

					}
					counterbeanobject.setGperiod(rs.getInt("gper"));
					counterbeanobject.setBeligible(rs.getInt("belg"));
					counterbeanobject.setBperiod(rs.getInt("bper"));
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					counterbeanobject.setBbperiod(rs.getInt("bbper"));
					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					counterbeanobject.setNbperiod(rs.getInt("nbper"));
					counterbeanobject.setJeligible(rs.getInt("jelg"));
					counterbeanobject.setJperiod(rs.getInt("jlper"));
					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					counterbeanobject.setBvperiod(rs.getInt("bvper"));
					counterbeanobject.setTeligible(rs.getInt("telg"));
					counterbeanobject.setTperiod(rs.getInt("tper"));
					counterbeanobject.setSeligible(rs.getInt("selg"));
					counterbeanobject.setSperiod(rs.getInt("sper"));
					counterbeanobject.setPeligible(rs.getInt("pelg"));
					counterbeanobject.setPperiod(rs.getInt("pper"));
					counterbeanobject.setReligible(rs.getInt("relg"));
					counterbeanobject.setRperiod(rs.getInt("rper"));

					counterbeanobject.setGrperiod(rs.getInt("grper")); // By RK
																		// Start
																		// 17-09-2014
					counterbeanobject.setBrperiod(rs.getInt("brper"));
					counterbeanobject.setBbrperiod(rs.getInt("bbrper"));
					counterbeanobject.setNbrperiod(rs.getInt("nbrper"));
					counterbeanobject.setJrperiod(rs.getInt("jrper"));
					counterbeanobject.setBvrperiod(rs.getInt("bvrper"));
					counterbeanobject.setTrperiod(rs.getInt("trper"));
					counterbeanobject.setSrperiod(rs.getInt("srper"));
					counterbeanobject.setPrperiod(rs.getInt("prper"));
					counterbeanobject.setRrperiod(rs.getInt("rrper"));

					counterbeanobject.setReserve(rs.getInt("breserve"));

					/*} else // RK RECENT 26-11-2014
				{
					counterbeanobject.setGeligible(rs.getInt("DeptEligible"));
					counterbeanobject.setGperiod(rs.getInt("DeptPeriod"));
					counterbeanobject.setGrperiod(rs.getInt("DeptRPeriod"));
					counterbeanobject.setDeptBranch(rs
							.getInt("Dept_BranchCode"));

					counterbeanobject.setReserve(rs.getInt("breserve"));
					period_all = rs.getInt("DeptPeriod");
				}*/

				DAYSCAL(period_all);

			}

		} catch (SQLException e) {
			throw new ServletException(e);
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

	public void DAYSCAL(int period) throws SQLException, IOException {
		log4jLogger
				.info("DAYSCAL===================== ====================method begin"
						+ period);

		String sql = "";
		int no = 1;
		int a;
		int Holiday_Count = 0, First_Holiday = 0, Second_Holiday = 0, Wday = 0;
		;

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
					// For WeekEnd Holiday Master

					Prest = con.prepareStatement("select DAYOFWEEK('" + Ddate
							+ "') as weekday");
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
								Prest = con.prepareStatement("select adddate('"
										+ Ddate + "', 1) as days");
							}
						}
						if (Holiday_Count == 2) {
							First_Holiday = rs.getInt("Day_ID");
							rs.next();
							Second_Holiday = rs.getInt("Day_ID");

							if (Second_Holiday != 7) {
								if (Wday == First_Holiday) {
									Prest = con
											.prepareStatement("select adddate('"
													+ Ddate + "', 2) as days");
								} else if (Wday == Second_Holiday) {
									Prest = con
											.prepareStatement("select adddate('"
													+ Ddate + "', 1) as days");
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
								Ddate = rs.getDate("days");
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

			counterbeanobject.setCalldate(Security_Counter
					.Counter_DateSet(Ddate));

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

	public CounterBean findMemberLoad(String code, String doc, int branchID,
			String availability) {
		log4jLogger.info("findMemberLoad(Strin code) method begin" + code);

		int acc_no = 0;
		String duedate = "";
		int member_period = 0;
		int Groups = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER);
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setMcode(rs.getString("member_code"));
				counterbeanobject.setMname(rs.getString("member_name"));
				counterbeanobject.setGroup(rs.getString("group_name"));
				counterbeanobject.setDesig(rs.getString("desig_name"));
				counterbeanobject.setDept(rs.getString("dept_name"));
				counterbeanobject.setValidDate(Security_Counter
						.Counter_DateGet(rs.getDate("expiry_date")));
				counterbeanobject.setImg(rs.getString("photo"));
				counterbeanobject.setCourse(rs.getString("course_name"));
				counterbeanobject.setMajor(rs.getString("course_major"));
				counterbeanobject.setYear(rs.getString("cyear"));
				counterbeanobject.setPhoto1(rs.getBytes("photo")); // For Image
																	// By RK

				counterbeanobject.setBranchID(rs.getInt("Branch_Code")); // For
																			// Titan
				counterbeanobject.setBranchName(rs.getString("branch_name"));
				counterbeanobject.setSLock(rs.getString("SLock"));

				/*if (branchID <= 3) // For Super Admin && Librarian login
				{*/

					String doctype = doc;
					Groups = rs.getInt("group_code");
					String status = rs.getString("status");
					if (status.equals("V1")) {

						counterbeanobject.setGeligible(rs.getInt("gelg"));
						member_period = rs.getInt("gper");
						counterbeanobject.setGperiod(member_period);

						counterbeanobject.setNbeligible(0);
						counterbeanobject.setNbperiod(0);
						counterbeanobject.setJeligible(0);
						counterbeanobject.setJperiod(0);
						counterbeanobject.setBveligible(0);
						counterbeanobject.setBvperiod(0);
						counterbeanobject.setTeligible(0);
						counterbeanobject.setTperiod(0);
						counterbeanobject.setSeligible(0);
						counterbeanobject.setSperiod(0);
						counterbeanobject.setPeligible(0);
						counterbeanobject.setPperiod(0);
						counterbeanobject.setReligible(0);
						counterbeanobject.setRperiod(0);
						counterbeanobject.setBbeligible(0);
						counterbeanobject.setBbperiod(0);
						counterbeanobject.setBeligible(0);
						counterbeanobject.setBperiod(0);

					} else if (status.equals("V2")) {
						counterbeanobject.setGeligible(0);
						if (doctype.equals("BOOK")) {
							counterbeanobject.setBeligible(rs.getInt("belg"));
							member_period = rs.getInt("bper");
							counterbeanobject.setBperiod(member_period);

						} else if (doctype.equals("BOOK BANK")) {
							counterbeanobject.setBbeligible(rs.getInt("bbelg"));
							member_period = rs.getInt("bbper");
							counterbeanobject.setBbperiod(member_period);

						} else if (doctype.equals("NON BOOK")) {

							counterbeanobject.setNbeligible(rs.getInt("nbelg"));
							member_period = rs.getInt("nbper");
							counterbeanobject.setNbperiod(member_period);

						} else if (doctype.equals("JOURNAL")) {

							counterbeanobject.setJeligible(rs.getInt("jelg"));
							member_period = rs.getInt("jlper");
							counterbeanobject.setJperiod(member_period);

						} else if (doctype.equals("BACK VOLUME")) {

							counterbeanobject.setBveligible(rs.getInt("bvelg"));
							member_period = rs.getInt("bvper");
							counterbeanobject.setBvperiod(member_period);

						} else if (doctype.equals("THESIS")) {

							counterbeanobject.setTeligible(rs.getInt("telg"));
							member_period = rs.getInt("tper");
							counterbeanobject.setTperiod(member_period);

						} else if (doctype.equals("STANDARD")) {

							counterbeanobject.setSeligible(rs.getInt("selg"));
							member_period = rs.getInt("sper");
							counterbeanobject.setSperiod(member_period);

						} else if (doctype.equals("PROCEEDING")) {

							counterbeanobject.setPeligible(rs.getInt("pelg"));
							member_period = rs.getInt("pper");
							counterbeanobject.setPperiod(member_period);

						} else if (doctype.equals("REPORT")) {

							counterbeanobject.setReligible(rs.getInt("relg"));
							member_period = rs.getInt("rper");
							counterbeanobject.setRperiod(member_period);

						} else {
							String STOP = "STOP";
						}

					} else if (status.equals("V3")) {

						counterbeanobject.setGeligible(rs.getInt("gelg"));
						member_period = rs.getInt("gper");
						counterbeanobject.setGperiod(member_period);

						if (doctype.equals("BOOK")) {
							counterbeanobject.setBeligible(rs.getInt("belg"));
							member_period = rs.getInt("bper");
							counterbeanobject.setBperiod(member_period);

						} else if (doctype.equals("BOOK BANK")) {
							counterbeanobject.setBbeligible(rs.getInt("bbelg"));
							member_period = rs.getInt("bbper");
							counterbeanobject.setBbperiod(member_period);

						} else if (doctype.equals("NON BOOK")) {

							counterbeanobject.setNbeligible(rs.getInt("nbelg"));
							member_period = rs.getInt("nbper");
							counterbeanobject.setNbperiod(member_period);

						} else if (doctype.equals("JOURNAL")) {

							counterbeanobject.setJeligible(rs.getInt("jelg"));
							member_period = rs.getInt("jlper");
							counterbeanobject.setJperiod(member_period);

						} else if (doctype.equals("BACK VOLUME")) {

							counterbeanobject.setBveligible(rs.getInt("bvelg"));
							member_period = rs.getInt("bvper");
							counterbeanobject.setBvperiod(member_period);

						} else if (doctype.equals("THESIS")) {

							counterbeanobject.setTeligible(rs.getInt("telg"));
							member_period = rs.getInt("tper");
							counterbeanobject.setTperiod(member_period);

						} else if (doctype.equals("STANDARD")) {

							counterbeanobject.setSeligible(rs.getInt("selg"));
							member_period = rs.getInt("sper");
							counterbeanobject.setSperiod(member_period);

						} else if (doctype.equals("PROCEEDING")) {

							counterbeanobject.setPeligible(rs.getInt("pelg"));
							member_period = rs.getInt("pper");
							counterbeanobject.setPperiod(member_period);

						} else if (doctype.equals("REPORT")) {

							counterbeanobject.setReligible(rs.getInt("relg"));
							member_period = rs.getInt("rper");
							counterbeanobject.setRperiod(member_period);

						} else {
							String STOP_WORK = "_WORKSTOP";
						}

					}
					counterbeanobject.setCountperiod(member_period);

					counterbeanobject.setGperiod(rs.getInt("gper"));
					counterbeanobject.setBeligible(rs.getInt("belg"));
					counterbeanobject.setBperiod(rs.getInt("bper"));
					counterbeanobject.setBbeligible(rs.getInt("bbelg"));
					counterbeanobject.setBbperiod(rs.getInt("bbper"));
					counterbeanobject.setNbeligible(rs.getInt("nbelg"));
					counterbeanobject.setNbperiod(rs.getInt("nbper"));
					counterbeanobject.setJeligible(rs.getInt("jelg"));
					counterbeanobject.setJperiod(rs.getInt("jlper"));
					counterbeanobject.setBveligible(rs.getInt("bvelg"));
					counterbeanobject.setBvperiod(rs.getInt("bvper"));
					counterbeanobject.setTeligible(rs.getInt("telg"));
					counterbeanobject.setTperiod(rs.getInt("tper"));
					counterbeanobject.setSeligible(rs.getInt("selg"));
					counterbeanobject.setSperiod(rs.getInt("sper"));
					counterbeanobject.setPeligible(rs.getInt("pelg"));
					counterbeanobject.setPperiod(rs.getInt("pper"));
					counterbeanobject.setReligible(rs.getInt("relg"));
					counterbeanobject.setRperiod(rs.getInt("rper"));

					counterbeanobject.setGrperiod(rs.getInt("grper")); // By RK
																		// Start
																		// 17-09-2014
					counterbeanobject.setBrperiod(rs.getInt("brper"));
					counterbeanobject.setBbrperiod(rs.getInt("bbrper"));
					counterbeanobject.setNbrperiod(rs.getInt("nbrper"));
					counterbeanobject.setJrperiod(rs.getInt("jrper"));
					counterbeanobject.setBvrperiod(rs.getInt("bvrper"));
					counterbeanobject.setTrperiod(rs.getInt("trper"));
					counterbeanobject.setSrperiod(rs.getInt("srper"));
					counterbeanobject.setPrperiod(rs.getInt("prper"));
					counterbeanobject.setRrperiod(rs.getInt("rrper"));

					counterbeanobject.setReserve(rs.getInt("breserve"));

			//	} 
				/*else // RK RECENT 26-11-2014
				{*/
					/*counterbeanobject.setGeligible(rs.getInt("DeptEligible"));
					counterbeanobject.setGperiod(rs.getInt("DeptPeriod"));
					counterbeanobject.setGrperiod(rs.getInt("DeptRPeriod"));
					counterbeanobject.setDeptBranch(rs
							.getInt("Dept_BranchCode"));*/

					counterbeanobject.setReserve(rs.getInt("breserve"));
				//	member_period = rs.getInt("DeptPeriod");
					counterbeanobject.setCountperiod(member_period);
				//}

				if (availability.equalsIgnoreCase("REFERENCE")) {
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.SELECT_REF_DUEDAYS);
					rs = Prest.executeQuery();
					if (rs.next()) {
						member_period = rs.getInt("due_days");
						counterbeanobject.setCountperiod(member_period);
					}
				}

				DAYSCAL(member_period);

				bookcount_doctype(counterbeanobject.getMcode(), branchID); // Check
																			// by
																			// rk

			}

		} catch (Exception e) {
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

		return counterbeanobject;
	}

	public String findValidDate(String vdate) {
		log4jLogger.info("start===========findValidDate=====");
		String date_valid = "";

		String today = Security_Counter.TodayDate();
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.valid_date);
			Prest.setString(1, vdate);
			Prest.setString(2, today);
			rs = Prest.executeQuery();
			if (rs.next()) {
				date_valid = rs.getString("no_of_days");
			}
		} catch (Exception e) {
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

		return date_valid;
	}

	public String findAvailability(String accno) {
		log4jLogger.info("start===========findValidDate=====" + accno);
		String availability = "";

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.rfid_availability);
			Prest.setString(1, accno);
			rs = Prest.executeQuery();
			if (rs.next()) {
				availability = rs.getString(1);
			}
		} catch (Exception e) {
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

		return availability;
	}

	public String findMemberCode(String mcode,int branchID) {
		log4jLogger.info("start===========findMemberCode=====");
		String memb_code = "";

		String today = Security_Counter.TodayDate();
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER_MAS);
			Prest.setString(1, mcode);
			Prest.setInt(2, branchID);

			rs = Prest.executeQuery();
			if (rs.next()) {
				memb_code = rs.getString("member_code");
			}
		} catch (Exception e) {
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

		return memb_code;
	}

	public CounterMemberBean findIssueMasCheck(String code, String document,int branchID) {
		log4jLogger.info("start===========findIssueMasCheck=====");

		CounterMemberBean beanobject = new CounterMemberBean();
		// String document="JOURNAL"; // 04-03-2014
		try {

			con = getSession().connection();
			if (document.equalsIgnoreCase("JOURNAL")) {
				log4jLogger
						.info("------------------ Inside Journal ----------findIssueMasCheck-------------");
				Prest = con.prepareStatement(DataQuery.SELECT_JNLIssuemas);
				Prest.setString(1, code);

				rs = Prest.executeQuery();
				if (rs.next()) {
					beanobject.setAvail(rs.getString("availability"));
					beanobject.setDoc(rs.getString("Doc_Type"));

				}
			} else {
				Prest = con.prepareStatement(DataQuery.SELECT_Issuemas);
				Prest.setString(1, code);
				Prest.setInt(2, branchID);

				rs = Prest.executeQuery();
				if (rs.next()) {
					beanobject.setAvail(rs.getString("availability"));
					beanobject.setDoc(rs.getString("Document"));

				}
			}
		} catch (Exception e) {
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

		return beanobject;
	}

	public String findReserveMasCheck(String code) {
		log4jLogger.info("start===========findIssueMasCheck=====");

		String reserve_check = "";

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_RESERVE_PRI);
			Prest.setString(1, code);

			rs = Prest.executeQuery();
			if (rs.next()) {
				reserve_check = rs.getString("mm");
			}
		} catch (Exception e) {
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

		return reserve_check;
	}

	public ReserveBean findReserveMssSelect(String code) {
		log4jLogger.info("start===========findReserveMssSelect=====");

		ReserveBean resbean = new ReserveBean();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_RESERVE_PRI1);
			Prest.setString(1, code);

			rs = Prest.executeQuery();
			if (rs.next()) {
				resbean = new ReserveBean();
				resbean.setMcode(rs.getString("member_code"));
				resbean.setRcode(rs.getString("id"));
				resbean.setMname(rs.getString("member_name"));
			}
		} catch (Exception e) {
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

		return resbean;
	}

	public int findReserveMssDelete(CounterMemberBean newbean) {
		log4jLogger.info("start===========findReserveMssDelete=====");

		int del_code = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_RESERVE_MAS);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			Prest.executeUpdate();
			if (rs.next()) {
				del_code = 1;
			}
		} catch (Exception e) {
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

		return del_code;
	}

	public int findIssueMasInsert(CounterMemberBean newbean) {
		log4jLogger.info("start===========findIssueMasInsert=====");

		int insert_code = 0;
		String emailid = "", phone = "", name = "";

		String document = newbean.getDoc().toString();

		ArrayList sers = new ArrayList();

		try {

			con = getSession().connection();

			if (document.equalsIgnoreCase("JOURNAL")) {
				Prest = con.prepareStatement(DataQuery.SELECT_JNLMAS);
			} else {
				Prest = con.prepareStatement(DataQuery.SELECT_BOOKMAS);
			}

			Prest.setString(1, newbean.getAccno());
			Prest.setInt(2, newbean.getBranchID());
			rs = Prest.executeQuery();
			if (rs.next()) {
				String avail = rs.getString("availability");

				if (avail.equals("YES") || avail.equals("REFERENCE")
						|| avail.equals("TRANSFERED")) {
					try {
						rs.absolute(1);
						if (document.equalsIgnoreCase("JOURNAL")) {
							Prest = con
									.prepareStatement(DataQuery.UPDATE_JNLISSUEMAS);
						}

						else {

							if (avail.equals("REFERENCE")) // For REFERENCE Book
															// Issued
							{
								Prest = con
										.prepareStatement(DataQuery.REF_UPDATE_BOOKMAS);
							}

							else if (avail.equals("TRANSFERED")) {
								Prest = con
										.prepareStatement(DataQuery.TRANS_UPDATE_BOOKMAS);
							}

							else {
								Prest = con
										.prepareStatement(DataQuery.UPDATE_BOOKMAS);
							}
						}

						Prest.setString(1, newbean.getAccno());
						Prest.setInt(2, newbean.getBranchID());
						Prest.executeUpdate();

					} catch (Exception e) {
						e.printStackTrace();
						log4jLogger.info("Update Book Master Error<> " + e);
					}

				}

				// con = getSession().connection();
				Prest = con.prepareStatement(COUNTER_QUERY.INSERT_STRING);
				Prest.setString(1, newbean.getMcode());
				Prest.setString(2, newbean.getAccno());
				Prest.setString(3, newbean.getIdate());
				Prest.setString(4, newbean.getDdate());
				Prest.setString(5, "ISSUE");
				Prest.setString(6, newbean.getAuthor());
				// Prest.setObject(6, staffbean.getstaffcode());
				Prest.setString(7, newbean.getDoc());
				Prest.setInt(8, newbean.getBranchID());// deptChanges

				Prest.executeUpdate();

				insert_code = 1;

				if (true) {

					sers.add(newbean.getMcode());
					sers.add(newbean.getMname());
					sers.add(newbean.getAccno());
					sers.add(newbean.getTitle());
					sers.add(newbean.getIdate());
					sers.add(newbean.getDdate());
				}

				/**
				 * Prest = con.prepareStatement(DataQuery.MEMBEREMAIL_STRING);
				 * // For Email Prest.setString(1, newbean.getMcode());
				 * rs=Prest.executeQuery(); if (rs.next()) {
				 * 
				 * emailid=rs.getString("member_email");
				 * phone=rs.getString("member_phone");
				 * name=rs.getString("member_name");
				 * 
				 * }
				 * 
				 * 
				 * if(!emailid.isEmpty() && !emailid.equals("")) // For Issue
				 * E-Mail {
				 * log4jLogger.info("Inside Issue Master Email "+emailid);
				 * 
				 * boolean chk=Security_Counter.EmailValidator(emailid);
				 * 
				 * if(chk==true) {
				 * 
				 * String[] strArray = new String[] {emailid};
				 * 
				 * MailDaoImpl Mail=new MailDaoImpl();
				 * 
				 * String namedQuery=MailQueryUtill.Issue_Message_Text;
				 * StringBuffer sb = new StringBuffer();
				 * 
				 * sb.append("Dear "+name+",<br>
				 * <br>
				 * "); sb.append(namedQuery);
				 * 
				 * sb.append("<br>
				 * <br>
				 * "); sb.append("
				 * <table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>
				 * "); sb.append("
				 * <tr bgcolor='#CCEEFF'>
				 * <th align=left><b><font color='#000000' size='1'
				 * face='Verdana'></b>Access No</th>"); sb.append("
				 * <th align=left><b><font color='#000000' size='1'
				 * face='Verdana'></b>Title</th>"); sb.append("
				 * <th align=left><b><font color='#000000' size='1'
				 * face='Verdana'></b>Issue Date</th>"); sb.append("
				 * <th align=left><b><font color='#000000' size='1'
				 * face='Verdana'></b>Due Date</th>"); sb.append("
				 * <th align=left><b><font color='#000000' size='1'
				 * face='Verdana'></b>Document</th>
				 * </tr>
				 * ");
				 * 
				 * sb.append("
				 * <tr bgcolor='#CCFFFF'>
				 * <td align=left width=70><font color='a62121' size='1'
				 * face='Verdana'>"+ newbean.getAccno() +"</td>
				 * <td align=left><font color='a62121' size='1'
				 * face='Verdana'>"+ newbean.getTitle() +"</td>
				 * <td align=left width=70><font color='a62121' size='1'
				 * face='Verdana'>"+
				 * Security.TextDate_member(newbean.getIdate()) +"</td>
				 * <td align=left width=70><font color='a62121' size='1'
				 * face='Verdana'>"+
				 * Security.TextDate_member(newbean.getDdate()) +"</td>
				 * <td align=left width=70><font color='a62121' size='1'
				 * face='Verdana'>"+ newbean.getDoc() +"</td>
				 * </tr>
				 * "); sb.append("
				 * </table>
				 * <br>
				 * <br>
				 * ");
				 * 
				 * sb.append(MailQueryUtill.Regards_Text);
				 * 
				 * Mail.findSendEmail(strArray,MailQueryUtill.Issue_Subject_Text
				 * ,sb.toString());
				 * 
				 * }else { log4jLogger.info("Invalid Emailid"); }
				 * 
				 * }
				 * 
				 * if(!phone.isEmpty() && !phone.equals("") &&
				 * phone.length()==10) // For Issue SMS {
				 * log4jLogger.info("Inside Issue Master SMS "+phone); boolean
				 * chk=Security_Counter.SMSValidator(phone);
				 * 
				 * if(chk==true) { SmsDaoImpl sms=new SmsDaoImpl();
				 * 
				 * String namedQuery=MailQueryUtill.Issue_Message_Text;
				 * StringBuffer sb = new StringBuffer();
				 * 
				 * sb.append(namedQuery); sb.append("UserID: " +
				 * newbean.getMcode()+" , Book No: "+newbean.getAccno());
				 * sb.append
				 * (", "+"IssueDate: "+newbean.getIdate()+" , DueDate: "
				 * +newbean.getDdate()+". Thanks, AutoLib");
				 * 
				 * sms.findSendSMS(phone,sb.toString()); }else {
				 * log4jLogger.info("Invalid Phone Number"); }
				 * 
				 * }
				 */

			}
		} catch (Exception e) {
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
		return insert_code;
	}

	public int findIssuedDetails(CounterMemberBean newbean) {
		log4jLogger.info("start===========findIssuedDetails=====");

		int del_code = 0;

		String md = newbean.getMcode();
		ArrayList DEATILS = new ArrayList();
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUEMEMBER);
			Prest.setString(1, newbean.getAccno());
			Prest.executeQuery();

			if (rs.next()) {
				md = rs.getString("member_code");
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUEBOOKS);
			Prest.setString(1, md);
			rs = Prest.executeQuery();

			while (rs.next()) {
				String a1 = rs.getString("Access_no");
				String a2 = rs.getString("title");
				String a3 = rs.getString("author_name");
				String a4 = rs.getString("Availability");
				String a5 = rs.getString("Member_Code");
				String a6 = Security_Counter
						.getdate(rs.getString("issue_date"));
				String a7 = Security_Counter.getdate(rs.getString("due_date"));
				String a8 = rs.getString("Issue_Type");
				String a9 = rs.getString("Staff_Code");
				String a10 = rs.getString("Doc_Type");
				DEATILS.add(a1);
				DEATILS.add(a2);
				DEATILS.add(a3);
				DEATILS.add(a4);
				DEATILS.add(a5);
				DEATILS.add(a6);
				DEATILS.add(a7);
				DEATILS.add(a8);
				DEATILS.add(a9);
				DEATILS.add(a10);
			}
		} catch (Exception e) {
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

		return del_code;
	}

	public CounterBean findDocmentReturn(CounterMemberBean newbean) {
		log4jLogger.info("start===========findDocmentReturn=====");

		int doc_return = 0;
		String document = newbean.getDoc().toString();

		ArrayList sers = new ArrayList();

		try {
			con = getSession().connection();

			if (document.equalsIgnoreCase("JOURNAL")) {
				Prest = con.prepareStatement(DataQuery.SELECT_RETURN_JNLISSUE);
			} else {
				Prest = con.prepareStatement(DataQuery.SELECT_RETURN_BOOK);
			}

			Prest.setString(1, newbean.getAccno());
			Prest.setString(2, newbean.getMcode());
			//Prest.setInt(3, newbean.getBranchID());
			rs = Prest.executeQuery();
			if (rs.next()) {

				String avail = rs.getString("availability");

				if (avail.equals("ISSUED") || avail.equals("REFISSUED")
						|| avail.equals("TRANSISSUED")) {

					rs.absolute(1);
					con = getSession().connection();

					if (document.equalsIgnoreCase("JOURNAL")) {
						Prest = con
								.prepareStatement(DataQuery.UPDATE_RETURN_JNLISSUE_MAS);
					} else {
						if (avail.equals("REFISSUED")) // For REFERENCE Book
														// Return by RK on
														// 12-07-2014
						{
							Prest = con
									.prepareStatement(DataQuery.REF_UPDATE_RETURN_BOOK_MAS);
						}

						else if (avail.equals("TRANSISSUED")) {
							Prest = con
									.prepareStatement(DataQuery.TRANS_UPDATE_RETURN_BOOK_MAS);
						}

						else {
							Prest = con
									.prepareStatement(DataQuery.UPDATE_RETURN_BOOK_MAS);
						}
					}

					Prest.setString(1, newbean.getAccno());
					Prest.setInt(2, newbean.getBranchID());
					Prest.executeUpdate();

					Prest = con.prepareStatement(DataQuery.SELECT_ISSUEMAS);
					Prest.setString(1, newbean.getAccno());
					Prest.setInt(2, newbean.getBranchID());
					rs = Prest.executeQuery();

					doc_return = 1;
					counterbeanobject.setDoc_Return(doc_return);

					if (rs.next()) {
						DAYSCALRETURN(Security_Counter.Counter_DateGet(rs
								.getDate("due_date")), Integer.parseInt(rs
								.getString("group_code")));

						Double Temp = newbean.getTemp();
						if (Temp.doubleValue() == 0.0) {

							RETURN_UPDATE(newbean);
							if (true) {
								sers.add(newbean.getMcode());
								sers.add(newbean.getMname());
								sers.add(newbean.getAccno());
								sers.add(Temp);
								sers.add(newbean.getDdate());
								sers.add(newbean.getRdate());
								counterbeanobject.setReservelist(sers);

							}
						}

					}
				}

			}
		} catch (Exception e) {
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

		return counterbeanobject;
	}

	public void RETURN_UPDATE(CounterMemberBean newbean) {
		log4jLogger.info("start===========RETURN_UPDATE=====");
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.DELETE_ISSUEMAS);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			Prest.setString(3, newbean.getDoc());
			Prest.setInt(4, newbean.getBranchID());
			
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.INSERT_HISTORY);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			Prest.setString(3, newbean.getIdate());
			Prest.setString(4, newbean.getDdate());
			Prest.setString(5, newbean.getRdate());
			Prest.setDouble(6, 0.0);
			Prest.setString(7, newbean.getAuthor());
			Prest.setString(8, newbean.getDoc());
			Prest.setString(9, "RETURN");
			Prest.setInt(10, newbean.getBranchID());
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.DELETE_RENEWAL);
			Prest.setString(1, newbean.getAccno());
			Prest.setString(2, newbean.getMcode());
			Prest.setString(3, newbean.getDoc());
			Prest.setInt(4, newbean.getBranchID());
			Prest.executeUpdate();

			/**
			 * String emailid="",phone="",name="";
			 * 
			 * Prest = con.prepareStatement(DataQuery.MEMBEREMAIL_STRING);
			 * Prest.setString(1, newbean.getMcode()); rs=Prest.executeQuery();
			 * if (rs.next()) {
			 * 
			 * emailid=rs.getString("member_email");
			 * phone=rs.getString("member_phone");
			 * name=rs.getString("member_name");
			 * 
			 * }
			 * 
			 * if(!emailid.isEmpty() && !emailid.equals("")) // For Return
			 * E-Mail { log4jLogger.info("Inside Return Master Email "+emailid);
			 * 
			 * boolean chk=Security_Counter.EmailValidator(emailid);
			 * 
			 * if(chk==true) {
			 * 
			 * String[] strArray = new String[] {emailid};
			 * 
			 * MailDaoImpl Mail=new MailDaoImpl();
			 * 
			 * String namedQuery=MailQueryUtill.Return_Message_Text;
			 * StringBuffer sb = new StringBuffer();
			 * 
			 * sb.append("Dear "+name+",<br>
			 * <br>
			 * "); sb.append(namedQuery);
			 * 
			 * sb.append("<br>
			 * <br>
			 * "); sb.append("
			 * <table border=1 width=700 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>
			 * "); sb.append("
			 * <tr bgcolor='#CCEEFF'>
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Access No</th>"); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Title</th>"); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Issue Date</th>"); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Due Date</th>"); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Return Date</th>"); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Document</th>
			 * </tr>
			 * ");
			 * 
			 * sb.append("
			 * <tr bgcolor='#CCFFFF'>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ newbean.getAccno() +"</td>
			 * <td align=left><font color='a62121' size='1' face='Verdana'>"+
			 * newbean.getTitle() +"</td>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ Security.TextDate_member(newbean.getIdate()) +"
			 * </td>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ Security.TextDate_member(newbean.getDdate()) +"
			 * </td>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ Security.TextDate_member(newbean.getRdate()) +"
			 * </td>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ newbean.getDoc() +"</td>
			 * </tr>
			 * "); sb.append("
			 * </table>
			 * <br>
			 * <br>
			 * ");
			 * 
			 * sb.append(MailQueryUtill.Regards_Text);
			 * 
			 * Mail.findSendEmail(strArray,MailQueryUtill.Return_Subject_Text,sb
			 * .toString());
			 * 
			 * }else { log4jLogger.info("Invalid Emailid"); }
			 * 
			 * }
			 * 
			 * if(!phone.isEmpty() && !phone.equals("") && phone.length()==10)
			 * // For Return SMS {
			 * log4jLogger.info("Inside Return Master SMS "+phone); boolean
			 * chk=Security_Counter.SMSValidator(phone);
			 * 
			 * if(chk==true) { SmsDaoImpl sms=new SmsDaoImpl();
			 * 
			 * String namedQuery=MailQueryUtill.Return_Message_Text;
			 * StringBuffer sb = new StringBuffer();
			 * 
			 * sb.append(namedQuery); sb.append("UserID: " +
			 * newbean.getMcode()+" , Book No: "+newbean.getAccno());
			 * sb.append(", "
			 * +"IssueDate: "+newbean.getIdate()+" , DueDate: "+newbean
			 * .getDdate(
			 * )+" , ReturnDate: "+newbean.getRdate()+". Thanks, AutoLib ");
			 * 
			 * sms.findSendSMS(phone,sb.toString()); }else {
			 * log4jLogger.info("Invalid Phone Number"); }
			 * 
			 * }
			 */

		} catch (Exception e) {
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

	public int findDocmentFine(CounterMemberBean newbean) {
		log4jLogger.info("start===========findDocmentFine=====");
		int del_code = 0;
		ArrayList sers = new ArrayList();
		CounterBean counterbeanobject = new CounterBean();
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.INSERT_HISTORY);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			Prest.setString(3, newbean.getIdate());
			Prest.setString(4, newbean.getDdate());
			Prest.setString(5, newbean.getRdate());
			Prest.setDouble(6, newbean.getTemp().doubleValue());
			Prest.setString(7, newbean.getAuthor()); // For Staff Code
			Prest.setString(8, newbean.getDoc());
			Prest.setString(9, "RETURN");
			Prest.setInt(10, newbean.getBranchID());
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.DELETE_ISSUEMAS);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			Prest.setString(3, newbean.getDoc());
			Prest.setInt(4, newbean.getBranchID());
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.DELETE_RENEWAL);
			Prest.setString(1, newbean.getAccno());
			Prest.setString(2, newbean.getMcode());
			Prest.setString(3, newbean.getDoc());
			Prest.setInt(4, newbean.getBranchID());
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.Fine_Trans_mas);
			Prest.setString(1, newbean.getRdate());
			Prest.setString(2, "OVERDUE");
			Prest.setString(3, newbean.getMcode());
			Prest.setString(4, newbean.getAccno());
			Prest.setString(5, newbean.getIdate());
			Prest.setString(6, newbean.getDdate());
			Prest.setDouble(7, newbean.getTemp().doubleValue());
			Prest.setString(8, newbean.getAuthor()); // For Staff Code
			Prest.setInt(9, newbean.getBranchID());

			Prest.executeUpdate();

			if (true) {

				sers.add(newbean.getMcode());
				sers.add(newbean.getMname());
				sers.add(newbean.getAccno());
				sers.add(newbean.getTemp());
				sers.add(newbean.getDdate());
				sers.add(newbean.getRdate());
				counterbeanobject.setReservelist(sers);

			}
		} catch (Exception e) {
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

		return del_code;
	}

	public int findIssueMasSelect(CounterMemberBean newbean) {
		log4jLogger.info("start===========findIssueMasSelect=====");

		int select_code = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUEMAS_ONLY);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			rs = Prest.executeQuery();
			if (rs.next()) {
				select_code = 1;

			}
		} catch (Exception e) {
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

		return select_code;
	}

	public int findReserveMasSelect(CounterMemberBean newbean) {
		log4jLogger.info("start===========findReserveMasSelect=====");

		int select_code = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_RESERVEMAS);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			rs = Prest.executeQuery();
			if (rs.next()) {
				select_code = 1;

			}
		} catch (Exception e) {
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

		return select_code;
	}

	public int findReserveMasCount(String code) {
		log4jLogger.info("start===========findReserveMasCount=====" + code);

		int Res_Count = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MAX_RESERVEMAS);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				Res_Count = rs.getInt(1);
			}
		} catch (Exception e) {
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

		return Res_Count;
	}

	public int findReserveMasSave(CounterMemberBean newbean) {
		log4jLogger.info("start===========findReserveMasSave=====");

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
			Prest.setString(2, newbean.getMcode());
			Prest.setString(3, newbean.getAccno());
			Prest.setString(4, newbean.getDoc());
			Prest.setString(5, newbean.getRdate());
			Prest.executeUpdate();

			RESERVEDETAILS(newbean.getAccno());

		} catch (Exception e) {
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

		return select_code;
	}

	public int findMemberMasSelect(String code,int branchID) {
		log4jLogger.info("start===========findMemberMasSelect=====" + code);

		int memb_code = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER_MAS);
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {
				memb_code = 1;

			}
		} catch (Exception e) {
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

		return memb_code;
	}

	public List findReserveDetailsMember(String code, int branchID) {
		CounterBean counterbeanobject = new CounterBean();
		int res_code = 0;
		ArrayList RCRESERVE = new ArrayList();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.RESERVEMAS_SELECT_MEMBER);
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();

			while (rs.next()) {
				String r1 = rs.getString("ID");
				String r2 = rs.getString("Member_Code");
				String r3 = rs.getString("Access_No");
				String r4 = rs.getString("Doc_Type");
				String r5 = Security_Counter.Counter_DateGet(rs
						.getDate("Res_Date"));
				String r6 = rs.getString("Member_Name");

				RCRESERVE.add(r1);
				RCRESERVE.add(r2);
				RCRESERVE.add(r3);
				RCRESERVE.add(r4);
				RCRESERVE.add(r5);
				RCRESERVE.add(r6);

				res_code = 1;

			}

		} catch (Exception e) {
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
		return RCRESERVE;
	}

	public List findReserveDetailsBook(String code) {
		CounterBean counterbeanobject = new CounterBean();
		int res_code = 0;
		ArrayList RCRESERVE = new ArrayList();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.RESERVEMAS_SELECT_BOOK);
			Prest.setString(1, code);
			rs = Prest.executeQuery();

			while (rs.next()) {
				String r1 = rs.getString("ID");
				String r2 = rs.getString("Member_Code");
				String r3 = rs.getString("Access_No");
				String r4 = rs.getString("Doc_Type");
				String r5 = Security_Counter.Counter_DateGet(rs
						.getDate("Res_Date"));
				String r6 = rs.getString("Member_Name");

				RCRESERVE.add(r1);
				RCRESERVE.add(r2);
				RCRESERVE.add(r3);
				RCRESERVE.add(r4);
				RCRESERVE.add(r5);
				RCRESERVE.add(r6);

				res_code = 1;

			}

		} catch (Exception e) {
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
		return RCRESERVE;
	}

	public CounterMemberBean findIssueMas(String code) {
		log4jLogger.info("start===========findIssueMas=====");

		CounterMemberBean beanobject = new CounterMemberBean();
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUE_MAS_RENEW);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				beanobject.setDdate(Security_Counter.Counter_DateGet(rs
						.getDate("due_date")));
				beanobject.setGroup(rs.getString("group_code"));
			}

		} catch (Exception e) {
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

		return beanobject;
	}

	public CounterBean findNumberofDays(CounterMemberBean newbean) {
		log4jLogger.info("start===========findNumberofDays=====");

		int n = 0;

		try {

			con = getSession().connection();

			String sql = "select datediff('"
					+ Security_Counter.TextDate_Insert(newbean.getRdate())
					+ "','"
					+ Security_Counter.TextDate_Insert(newbean.getDdate())
					+ "') as no_of_days";

			Prest = con.prepareStatement("select datediff('"
					+ Security_Counter.TextDate_Insert(newbean.getRdate())
					+ "','"
					+ Security_Counter.TextDate_Insert(newbean.getDdate())
					+ "') as no_of_days");
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setAccno("1");
				counterbeanobject.setTitle(rs.getString("no_of_days"));

				String no_of_days = rs.getString("no_of_days");
				n = Integer.parseInt(no_of_days);
			}

			if (n < 0) {
			}

			MEMBER_LOAD(newbean.getMcode(), newbean.getDoc(),
					newbean.getBranchID(), newbean.getAvail());

		} catch (Exception e) {
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

		return counterbeanobject;
	}

	public void MEMBER_LOAD(String loadmemcode, String doctype, int branchID,
			String availability) // RK start
	// 17-09-2014
	{
		/**
		 * int member_period = 0; int Groups=0;
		 * 
		 * try{ con = getSession().connection(); Prest =
		 * con.prepareStatement(DataQuery.SELECT_MEMBER); Prest.setString(1,
		 * loadmemcode); rs = Prest.executeQuery(); if (rs.next()) {
		 * counterbeanobject.setMcode(rs.getString("member_code"));
		 * counterbeanobject.setMname(rs.getString("member_name"));
		 * counterbeanobject.setGroup(rs.getString("group_name"));
		 * counterbeanobject.setDesig(rs.getString("desig_name"));
		 * counterbeanobject.setDept(rs.getString("dept_name"));
		 * counterbeanobject.setValidDate(
		 * Security_Counter.Counter_DateGet(rs.getDate("expiry_date")));
		 * counterbeanobject.setImg(rs.getString("photo"));
		 * counterbeanobject.setCourse(rs.getString("course_name"));
		 * counterbeanobject.setMajor(rs.getString("course_major"));
		 * counterbeanobject.setYear(rs.getString("cyear"));
		 * 
		 * Groups = rs.getInt("group_code"); String status =
		 * rs.getString("status"); if (status.equals("V1")) {
		 * counterbeanobject.setGeligible(rs.getInt("gelg")); member_period =
		 * rs.getInt("gper"); counterbeanobject.setGperiod(member_period);
		 * counterbeanobject.setGperiod(rs.getInt("gper"));
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
		 * DAYSCAL(member_period);
		 */

		int member_period = 0;
		int Groups = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER);
			Prest.setString(1, loadmemcode);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {
				counterbeanobject.setMcode(rs.getString("member_code"));
				counterbeanobject.setMname(rs.getString("member_name"));
				counterbeanobject.setGroup(rs.getString("group_name"));
				counterbeanobject.setDesig(rs.getString("desig_name"));
				counterbeanobject.setDept(rs.getString("dept_name"));
				counterbeanobject.setValidDate(Security_Counter
						.Counter_DateGet(rs.getDate("expiry_date")));
				counterbeanobject.setImg(rs.getString("photo"));
				counterbeanobject.setCourse(rs.getString("course_name"));
				counterbeanobject.setMajor(rs.getString("course_major"));
				counterbeanobject.setYear(rs.getString("cyear"));

			//	if (branchID <= 2) {
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

//				} else { // By RK RECENT 26-11-2014
//					counterbeanobject.setGeligible(rs.getInt("DeptEligible"));
//					counterbeanobject.setGperiod(rs.getInt("DeptPeriod"));
//					counterbeanobject.setGrperiod(rs.getInt("DeptRPeriod"));
//
//					counterbeanobject.setDeptBranch(rs
//							.getInt("Dept_BranchCode"));
//					member_period = rs.getInt("DeptRPeriod");
//				}

				if (availability.equalsIgnoreCase("REFISSUED")) {
					con = getSession().connection();
					Prest = con.prepareStatement(DataQuery.SELECT_REF_DUEDAYS);
					rs = Prest.executeQuery();
					if (rs.next()) {
						member_period = rs.getInt("due_days");
					}
				}

				counterbeanobject.setCountperiod(member_period);
				DAYSCAL(member_period);

			}
		} catch (Exception e) {
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

	public CounterMemberBean findDdate(CounterMemberBean newbean) {
		log4jLogger.info("start===========findIssueMas=====");

		CounterMemberBean beanobject = new CounterMemberBean();
		try {
			con = getSession().connection();
			Prest = con.prepareStatement("select adddate('"
					+ newbean.getAccno() + "', '" + newbean.getBbeligible()
					+ "') as days");
			rs = Prest.executeQuery();
			if (rs.next()) {
				beanobject.setAuthor(rs.getString("days"));

				Prest = con.prepareStatement("select date_format('"
						+ rs.getString("days") + "','%w') as days");
				rs = Prest.executeQuery();

				if (rs.next()) {

					beanobject.setAvail(rs.getString("days"));

				}

			}

		} catch (Exception e) {
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

		return beanobject;
	}

	public String findLeaveDate(String code) {
		log4jLogger.info("start===========findLeaveDate=====");

		String leave_date = "";

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_HOLIDAY_MAS_CHECK);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {

				leave_date = rs.getString("Leave_date");

			}

		} catch (Exception e) {
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

		return leave_date;
	}

	public CounterMemberBean findUpdateRenewMasNofine(CounterMemberBean newbean) {
		log4jLogger.info("start===========findUpdateRenewMasNofine=====");

		int select_code = 0;
		CounterMemberBean beanobject = new CounterMemberBean();

		try {

			int time = 0;
			int renew = 0;

			con = getSession().connection();
			Prest = con
					.prepareStatement("select time_renew from renewal_time where access_no='"
							+ newbean.getAccno()
							+ "' and member_code='"
							+ newbean.getMcode() + "' ");
			rs = Prest.executeQuery();
			if (rs.next()) {
				time = Integer.parseInt(rs.getString("time_renew"));

				time++;

			} else {

				time = 1;
				Prest = con
						.prepareStatement("insert into renewal_time(member_code,access_no,group_code,doc_type,time_renew) values ('"
								+ newbean.getMcode()
								+ "','"
								+ newbean.getAccno()
								+ "','"
								+ newbean.getTperiod()
								+ "','"
								+ newbean.getDoc() + "','" + time + "')");

				Prest.executeUpdate();

			}

			Prest = con
					.prepareStatement("select renewal from group_mas where group_code='"
							+ newbean.getTperiod() + "' ");
			rs = Prest.executeQuery();
			if (rs.next()) {
				renew = rs.getInt("renewal");
			}

			if (time <= renew) {

				select_code = 1;
				beanobject.setSperiod(select_code);
				beanobject.setTeligible(time);
			}

		} catch (Exception e) {
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

		return beanobject;
	}

	public int findUpdateRenewMas(CounterMemberBean newbean) {
		log4jLogger.info("start===========findUpdateRenewMas=====BranchCode"+newbean.getBranchID());
		log4jLogger.info("start===========findUpdateRenewMas=====Document"+newbean.getDoc());

		int select_code = 0;

		String document = newbean.getDoc().toString(); // 04-03-2014

		try {
			con = getSession().connection();
			Prest = con.prepareStatement("update renewal_time set time_renew='"
					+ newbean.getTeligible() + "' where access_no= '"
					+ newbean.getAccno() + "'and member_code='"
					+ newbean.getMcode() + "' and branch_code='"+newbean.getBranchID()+"' ");
			Prest.executeUpdate();

			if (document.equalsIgnoreCase("JOURNAL")) {
				Prest = con.prepareStatement(DataQuery.SELECT_JNLMAS);
			} else {
				Prest = con.prepareStatement(DataQuery.SELECT_BOOKMAS);
			}

			Prest.setString(1, newbean.getAccno());
			Prest.setInt(2, newbean.getBranchID());
			rs = Prest.executeQuery();
			if (rs.next()) {
				String avail = rs.getString("availability");
				if (avail.equals("ISSUED") || avail.equals("REFISSUED")
						|| avail.equals("TRANSISSUED")) {
					rs.absolute(1);

					if (document.equalsIgnoreCase("JOURNAL")) {
						Prest = con
								.prepareStatement("update journal_issues set availability='ISSUED' where Issue_Access_No='"
										+ newbean.getAccno() + "' AND branch_code='"+newbean.getBranchID()+"'");
					}

					else {
						if (avail.equals("REFISSUED")) {
							Prest = con
									.prepareStatement("update book_mas set availability='REFISSUED' where access_no='"
											+ newbean.getAccno() + "' AND branch_code='"+newbean.getBranchID()+"'");
						}

						else if (avail.equals("TRANSISSUED")) {
							Prest = con
									.prepareStatement("update book_mas set availability='TRANSISSUED' where access_no='"
											+ newbean.getAccno() + "' AND branch_code='"+newbean.getBranchID()+"' ");
						}

						else {
							Prest = con
									.prepareStatement("update book_mas set availability='ISSUED' where access_no='"
											+ newbean.getAccno() + "' AND branch_code='"+newbean.getBranchID()+"' ");
						}
					}

					Prest.executeUpdate();

					
					
					RETURN_UPDATE_RENEW_NOFINE(newbean);

				}
			}

		} catch (Exception e) {
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

		return select_code;
	}

	public void RETURN_UPDATE_RENEW_NOFINE(CounterMemberBean newbean) {
		log4jLogger.info("start===========RETURN_UPDATE_RENEW_NOFINE=&&&&&&&&&&&===="+newbean.getBranchID());
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.UPDATE_ISSUEMAS);
			Prest.setString(1, newbean.getDdate());
			Prest.setString(2, newbean.getRdate());
			Prest.setString(3, "ISSUE");
			Prest.setString(4, newbean.getTitle());// For Staff Code
			Prest.setInt(5, newbean.getBranchID());
			Prest.setString(6, newbean.getMcode());
			Prest.setString(7, newbean.getAccno());
			Prest.setInt(8, newbean.getBranchID());
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.INSERT_HISTORY);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			/*Prest.setString(3,Security_Counter.TextDate_Insert(newbean.getIdate()));
			Prest.setString(4, newbean.getValidDate()); */// For Old Due Date			
			Prest.setString(3, newbean.getRdate());	// For Renew report	
			Prest.setString(4, newbean.getDdate());
			Prest.setString(5, newbean.getRdate());
			Prest.setDouble(6, 0.0);
			Prest.setString(7, newbean.getTitle()); // For Staff Code
			Prest.setString(8, newbean.getDoc());
			Prest.setString(9, "RENEW");
			Prest.setInt(10, newbean.getBranchID());
			Prest.executeUpdate();

			/**
			 * String emailid="",phone="",name="";
			 * 
			 * Prest = con.prepareStatement(DataQuery.MEMBEREMAIL_STRING);
			 * Prest.setString(1, newbean.getMcode()); rs=Prest.executeQuery();
			 * if (rs.next()) {
			 * 
			 * emailid=rs.getString("member_email");
			 * phone=rs.getString("member_phone");
			 * name=rs.getString("member_name");
			 * 
			 * }
			 * 
			 * 
			 * if(!emailid.isEmpty() && !emailid.equals("")) // For Renew E-Mail
			 * { log4jLogger.info("Inside Renew Master Email "+emailid);
			 * 
			 * boolean chk=Security_Counter.EmailValidator(emailid);
			 * 
			 * if(chk==true) {
			 * 
			 * String[] strArray = new String[] {emailid};
			 * 
			 * MailDaoImpl Mail=new MailDaoImpl();
			 * 
			 * String namedQuery=MailQueryUtill.Renew_Message_Text; StringBuffer
			 * sb = new StringBuffer();
			 * 
			 * sb.append("Dear "+name+",<br>
			 * <br>
			 * "); sb.append(namedQuery);
			 * 
			 * sb.append("<br>
			 * <br>
			 * "); sb.append("
			 * <table border=1 width=280 align=center CELLSPACING='1' cellpadding='3' style='border-collapse: collapse' bordercolor='#of2e4f'>
			 * "); sb.append("
			 * <tr bgcolor='#CCEEFF'>
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Access No</th>"); //sb.append("
			 * <th><b><font color='#000000' size='1' face='Verdana'></b>Title</th>
			 * "); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Renew Date</th>"); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Due Date</th>"); sb.append("
			 * <th align=left><b><font color='#000000' size='1'
			 * face='Verdana'></b>Document</th>
			 * </tr>
			 * ");
			 * 
			 * sb.append("
			 * <tr bgcolor='#CCFFFF'>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ newbean.getAccno() +"</td>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ Security.TextDate_member(newbean.getRdate()) +"
			 * </td>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ Security.TextDate_member(newbean.getDdate()) +"
			 * </td>
			 * <td align=left width=70><font color='a62121' size='1'
			 * face='Verdana'>"+ newbean.getDoc() +"</td>
			 * </tr>
			 * "); sb.append("
			 * </table>
			 * <br>
			 * <br>
			 * ");
			 * 
			 * sb.append(MailQueryUtill.Regards_Text);
			 * 
			 * Mail.findSendEmail(strArray,MailQueryUtill.Renew_Subject_Text,sb.
			 * toString());
			 * 
			 * }else { log4jLogger.info("Invalid Emailid"); }
			 * 
			 * }
			 * 
			 * if(!phone.isEmpty() && !phone.equals("") && phone.length()==10)
			 * // For Renew SMS {
			 * log4jLogger.info("Inside Renew Master SMS "+phone); boolean
			 * chk=Security_Counter.SMSValidator(phone);
			 * 
			 * if(chk==true) { SmsDaoImpl sms=new SmsDaoImpl();
			 * 
			 * String namedQuery=MailQueryUtill.Renew_Message_Text; StringBuffer
			 * sb = new StringBuffer();
			 * 
			 * sb.append(namedQuery); sb.append("UserID: " +
			 * newbean.getMcode()+" , Book No: "+newbean.getAccno());
			 * sb.append(", RenewDate: "
			 * +newbean.getRdate()+" , DueDate: "+newbean
			 * .getDdate()+". Thanks, AutoLib ");
			 * 
			 * sms.findSendSMS(phone,sb.toString()); }else {
			 * log4jLogger.info("Invalid Phone Number"); }
			 * 
			 * }
			 */

		} catch (Exception e) {
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

	public CounterBean findFineCall(CounterMemberBean newbean) {
		log4jLogger.info("start===========findFineCall=====");

		int select_code = 0;

		try {

			FINE_CALL(newbean.getRissued(), newbean.getBissued());
		} catch (Exception e) {
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

		return counterbeanobject;
	}

	public int findUpdateRenewMasFine(CounterMemberBean newbean) {
		log4jLogger.info("start===========findUpdateRenewMasFine====="+newbean.getBranchID());

		int select_code = 0;

		String document = newbean.getDoc().toString(); // 04-03-2014

		try {
			con = getSession().connection();
			Prest = con.prepareStatement("update renewal_time set time_renew='"
					+ newbean.getTeligible() + "' where access_no= '"
					+ newbean.getAccno() + "'and member_code='"
					+ newbean.getMcode() + "' and branch_code='"+newbean.getBranchID()+"' ");
			Prest.executeUpdate();

			if (document.equalsIgnoreCase("JOURNAL")) {
				Prest = con.prepareStatement(DataQuery.SELECT_JNLMAS);
			} else {
				Prest = con.prepareStatement(DataQuery.SELECT_BOOKMAS);
			}

			Prest.setString(1, newbean.getAccno());
			Prest.setInt(2, newbean.getBranchID());
			rs = Prest.executeQuery();
			if (rs.next()) {
				String avail = rs.getString("availability");
				if (avail.equals("ISSUED")) {
					rs.absolute(1);

					if (document.equalsIgnoreCase("JOURNAL")) {
						Prest = con
								.prepareStatement("update journal_issues set availability='ISSUED' where Issue_Access_No='"
										+ newbean.getAccno() + "' and branch_code='"+newbean.getBranchID()+"' ");
					} else {
						Prest = con
								.prepareStatement("update book_mas set availability='ISSUED' where access_no='"
										+ newbean.getAccno() + "' and branch_code='"+newbean.getBranchID()+"' ");
					}

					Prest.executeUpdate();
				}

				RETURN_UPDATE_RENEW(newbean);
			}

		} catch (Exception e) {
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

		return select_code;
	}

	public void RETURN_UPDATE_RENEW(CounterMemberBean newbean) {
		log4jLogger
				.info("start===========RETURN_UPDATE_RENEW=============================");

		double renewfine = newbean.getTemp().doubleValue();
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.INSERT_HISTORY);
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			/*Prest.setString(3,Security_Counter.TextDate_Insert(newbean.getIdate()));
			Prest.setString(4, newbean.getValidDate());*/ // For Old Due Date			
			Prest.setString(3,newbean.getRdate()); // Renew date for Renew-report
			Prest.setString(4, newbean.getDdate()); //Due Date For After Renewal 
			Prest.setString(5, newbean.getRdate());
			Prest.setDouble(6, renewfine);
			Prest.setString(7, newbean.getTitle()); // For Staff Code
			Prest.setString(8, newbean.getDoc());
			Prest.setString(9, "RENEW");
			Prest.setInt(10, newbean.getBranchID());
			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.UPDATE_ISSUEMAS);
			Prest.setString(1, newbean.getDdate());
			Prest.setString(2, newbean.getRdate());
			Prest.setString(3, "ISSUE");
			Prest.setString(4, newbean.getTitle()); // For Staff Code
			Prest.setInt(5, newbean.getBranchID());
			Prest.setString(6, newbean.getMcode());
			Prest.setString(7, newbean.getAccno());
			Prest.setInt(8, newbean.getBranchID());
			Prest.executeUpdate();

			// Changed by RK
			Prest = con.prepareStatement(DataQuery.Fine_Trans_mas);
			Prest.setString(1, newbean.getRdate());
			Prest.setString(2, "OVERDUE");
			Prest.setString(3, newbean.getMcode());
			Prest.setString(4, newbean.getAccno());
			Prest.setString(5,
					Security_Counter.TextDate_Insert(newbean.getIdate()));
			Prest.setString(6, newbean.getDdate());

			Prest.setDouble(7, newbean.getTemp().doubleValue());
			Prest.setString(8, newbean.getTitle()); // For Staff Code
			// Prest.setString(8, newbean.getDoc());
			Prest.setInt(9, newbean.getBranchID());
			Prest.executeUpdate();

		} catch (Exception e) {
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

	public int findRenewCheck(CounterMemberBean newbean) {
		log4jLogger.info("start===========findRenewCheck====="+newbean.getBranchID());

		int doc_return = 0;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_ISSUEMAS_ONLY);			
			Prest.setString(1, newbean.getMcode());
			Prest.setString(2, newbean.getAccno());
			Prest.setInt(3, newbean.getBranchID());
			rs = Prest.executeQuery();
			if (rs.next()) {

				doc_return = 1;
			}

			Prest = con.prepareStatement(DataQuery.RESERVEMAS_SELECT_BOOK); // For
																			// Reserve
																			// Check
			Prest.setString(1, newbean.getAccno());
			Prest.setInt(2, newbean.getBranchID());
			rs = Prest.executeQuery();

			if (rs.next()) {

				doc_return = 2;
			}

		} catch (Exception e) {
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

		return doc_return;
	}

	// ---------------------- Store Fine to Payment Clearance ------

	public int findFineDetail(CounterMemberBean newbean) {
		log4jLogger.info("start===========fineDetails=====");

		int doc_return = 0;
		int Bill_no = 1;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Max_BillNo_Payment);
			rs = Prest.executeQuery();

			if (rs.next()) {

				Bill_no = rs.getInt("billno");
			}

			if (Bill_no == 0) {

				Bill_no = 1;
			}

			log4jLogger
					.info("================ Save Fine To Payment Clearance =======================");

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Fine_Payment_Clearance);
			Prest.setInt(1, Bill_no);
			Prest.setString(2, newbean.getMcode());
			Prest.setDouble(3, newbean.getTemp());
			Prest.setString(4, newbean.getRdate());
			Prest.setString(5, "OVERDUE");
			Prest.setString(6, newbean.getAuthor());
			Prest.setString(7, newbean.getAccno());
			Prest.setString(8, newbean.getDoc());
			Prest.setInt(9, newbean.getBranchID());

			Prest.executeUpdate();

			if (true) {

				doc_return = 1;
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
		return doc_return;

	}

	// ----------------------Binding Books---------------------------------

	public List findLoadBinderName() {
		log4jLogger.info("start===========findLoadBinderName=====");

		BindingBean newbean = null;

		List finalResults = new ArrayList();

		try {
			con = getSession().connection();
			st = con.createStatement();

			rs = st.executeQuery(DataQuery.Binder_Load);

			while (rs.next()) {
				newbean = new BindingBean();
				newbean.setCode(rs.getInt("Binder_Code"));
				newbean.setName(rs.getString("Binder_Name"));
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

	public int findBindingBooksSave(BookBindingBean newbean) {
		log4jLogger.info("start===========findBindingBooksSave====="
				+ newbean.getAccess_no());

		int count = 0;
		String status = "", strsql = "";

		try {
			if (newbean.getBranchCode() > 0) {
				strsql = " and branch_code=" + newbean.getBranchCode() + " ";
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BOOKMAS_STATUS + strsql);
			Prest.setString(2, newbean.getAccess_no());
			Prest.setString(1, newbean.getDocument());
			rs = Prest.executeQuery();
			if (rs.next()) {
				status = rs.getString("availability");

				if ((status.equals("YES")) || (status.equals("REFERENCE"))
						|| (status.equals("DAMAGED"))
						|| (status.equals("DISPLAY"))) {

					Prest = con.prepareStatement(DataQuery.SELECT_BINDDOC_MAS);
					Prest.setString(1, newbean.getAccess_no());

					rs = Prest.executeQuery();
					if (rs.next()) {
						count = 2;
					} else {
						Prest = con
								.prepareStatement(DataQuery.INSERT_BINDDOC_MAS);
						Prest.setString(1, newbean.getBinderName());
						Prest.setString(2, newbean.getAccess_no());
						Prest.setString(3, newbean.getDocument());
						Prest.setString(4, newbean.getDate());
						if (status.equals("REFERENCE")) {
							Prest.setString(5, "REFBINDING");
						} else {
							Prest.setString(5, "BINDING");
						}
						Prest.executeUpdate();

						Prest = con
								.prepareStatement(DataQuery.UPDATE_BOOK_STATUS);
						Prest.setString(1, newbean.getAccess_no());
						Prest.executeUpdate();

						count = 3;
					}

				} else {
					if (status.equals("ISSUED")) {
						count = 1;
					} else if (status.equals("TRANSFERED")) {
						count = 5;
					} else if (status.equals("LOST")) {
						count = 6;
					} else if (status.equals("MISSING")) {
						count = 7;
					} else if (status.equals("WITHDRAWN")) {
						count = 8;
					}

				}

			} else {
				count = 4;
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

		return count;
	}

	public List findBindingDisplay(String query) {
		log4jLogger.info("start===========findBindingDisplay=====" + query);

		BookBindingBean newbean = null;
		List finalResults = new ArrayList();
		String strsql = query;

		try {

			con = getSession().connection();
			st = con.createStatement();

			rs = st.executeQuery(DataQuery.BINDING_DISPLAY + strsql);

			while (rs.next()) {
				newbean = new BookBindingBean();
				newbean.setBinder(rs.getInt("Binder_Code"));
				newbean.setAccess_no(rs.getString("Access_No"));
				newbean.setDocument(rs.getString("Doc_Type"));
				newbean.setDate(Security.getdate(rs.getString("Sending_Date")));
				newbean.setBinderName(rs.getString("binder_name"));
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

	public int findBindingBooksReturn(String name, int branchID) {
		log4jLogger.info("start===========findBindingBooksReturn=====");

		int count = 0;
		String status = "", strsql = "";

		try {
			if (branchID > 0) {
				strsql = " and branch_code=" + branchID + " ";
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_BINDDOC_MAS + strsql);
			Prest.setString(1, name);

			rs = Prest.executeQuery();
			if (rs.next()) {

				Prest = con.prepareStatement(DataQuery.BINDING_DISPLAY_SELECT);
				Prest.setString(1, name);
				rs = Prest.executeQuery();
				if (rs.next()) {

					String b_name = rs.getString("binder_name");
					String doc = rs.getString("doc_type");
					status = rs.getString("availability");
					String sdate = rs.getString("sending_date");
					String rdate = (Security.getdate(Security.CalenderDate()));

					Prest = con
							.prepareStatement(DataQuery.INSERT_BINDDOC_RETURN);
					Prest.setString(1, b_name);
					Prest.setString(2, name);
					Prest.setString(3, doc);
					Prest.setString(4, sdate);
					Prest.setString(5, rdate);
					Prest.setString(6, status);
					Prest.executeUpdate();
				}

				Prest = con.prepareStatement(DataQuery.DELETE_BINDDOC_MAS);
				Prest.setString(1, name);
				Prest.executeUpdate();

				if (status.equals("REFBINDING")) {
					Prest = con
							.prepareStatement(DataQuery.UPDATE_REFERENCEBOOK_STATUS_RETURN);
				} else {
					Prest = con
							.prepareStatement(DataQuery.UPDATE_BOOK_STATUS_RETURN);
				}
				Prest.setString(1, name);
				Prest.executeUpdate();

				count = 1;
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

		return count;
	}

	// ----------------------Transfer Books----------------------

	public List findLoadDeptName(int branchID) {
		log4jLogger.info("start===========findLoadDeptName=====");

		BindingBean newbean = null;
		List finalResults = new ArrayList();
		String strsql = "";

		try {

			if (branchID > 0) {
				strsql = " and branch_code=" + branchID + " ";
			}

			con = getSession().connection();
			st = con.createStatement();

			rs = st.executeQuery(DataQuery.TRANSFER_DEPT_LOAD + strsql);

			while (rs.next()) {
				newbean = new BindingBean();
				newbean.setCode(rs.getInt("dept_Code"));
				newbean.setName(rs.getString("dept_name"));

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

	public List findBranchName(int branchID) {
		log4jLogger.info("start===========Branch_Load=====");

		BindingBean newbean = null;
		List finalResults = new ArrayList();
		String strsql = "";

		try {

			if (branchID > 0) {
				strsql = " and branch_code=" + branchID + " ";
			}

			con = getSession().connection();
			st = con.createStatement();

			rs = st.executeQuery(DataQuery.BOOKSEARCH_BRANCHNAME);

			while (rs.next()) {
				newbean = new BindingBean();
				newbean.setCode(rs.getInt("branch_Code"));
				newbean.setName(rs.getString("branch_name"));

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

	public BookTransferBean findTransferBooksSave(BookTransferBean newbean) {

		log4jLogger.info("start===========findTransferBooksSave=====");

		BookTransferBean ob = new BookTransferBean();
		int count = 0;
		String status = "";
		String deptname = "";
		String strsql = "";

		try {

			if (newbean.getBranchCode() > 0) {
				strsql = " and branch_code=" + newbean.getBranchCode() + " ";
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.BOOKMAS_STATUS + strsql);
			Prest.setString(1, newbean.getDocument());
			Prest.setString(2, newbean.getAccess_no());
			Prest.setInt(3, newbean.getBranchCode());

			rs = Prest.executeQuery();
			if (rs.next()) {
				status = rs.getString("availability");

				if ((status.equals("YES")) || (status.equals("REFERENCE"))) {

					Prest = con.prepareStatement(DataQuery.TRANSFER_SELECT);
					Prest.setString(1, newbean.getAccess_no());
					Prest.setInt(2, newbean.getBranchCode());

					rs = Prest.executeQuery();
					if (rs.next()) {
						ob.setAvail("Transfered");
					} else {
						Prest = con
								.prepareStatement(DataQuery.INSERT_TRANSFER_MAS);
						Prest.setInt(1, newbean.getordno());
						Prest.setString(2, newbean.getAccess_no());
						Prest.setInt(3, newbean.getBranchCode());
						Prest.setString(4, newbean.getdeptName());
						Prest.setString(5, newbean.getDocument());
						Prest.setString(6, newbean.getDate());
						Prest.setString(7, "");
						Prest.setString(8, "1977-10-22");
						Prest.executeUpdate();

						// Prest =
						// con.prepareStatement(DataQuery.TRANSFER_DEPT_NAME);
						Prest = con
								.prepareStatement(DataQuery.TRANSFER_DEPT_NAME);

						Prest.setString(1, newbean.getdeptName());
						rs = Prest.executeQuery();

						if (rs.next()) {
							deptname = rs.getString("dept_name");

						}

						Prest = con
								.prepareStatement(DataQuery.UPDATE_BOOK_STATUS_TRANSFER);
						Prest.setString(1, deptname);
						// Prest.setInt(2, newbean.getdeptcode());
						Prest.setInt(2, newbean.getBranchCode());
						Prest.setString(3, newbean.getAccess_no());

						Prest.executeUpdate();

						count = 3;
					}

				} else {

					ob.setAvail(status);

				}

			} else {
				ob.setAvail("not found");
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

		return ob;
	}

	public BookTransferBean findbulkTransferBooksSave(BookTransferBean newbean) {

		log4jLogger.info("start===========findBULKTransferBooksSave=====");

		BookTransferBean ob = new BookTransferBean();
		int count = 0;
		String status = "";
		String branch_name = "";
		String strsql = "";
		try {
			con = getSession().connection();

			if (newbean.getBranchCode() > 0) {
				strsql = " and branch_code=" + newbean.getBranchCode() + " ";
			}
			Prest = con.prepareStatement(DataQuery.INSERT_BULK_TRANSFER_MAS
					+ "(Select Access_no," + newbean.getBranchCode() + ","
					+ newbean.getdeptName() + ",'" + newbean.getDocument()
					+ "','" + newbean.getDate()
					+ "','','1977-10-22' from book_mas where access_no in('0'"
					+ newbean.getAuthor() + "))");

			Prest.executeUpdate();

			Prest = con.prepareStatement(DataQuery.TRANSFER_BRANCH_NAME);
			Prest.setString(1, newbean.getdeptName());
			rs = Prest.executeQuery();

			if (rs.next()) {
				branch_name = rs.getString("branch_name");
			}

			Prest = con
					.prepareStatement(DataQuery.UPDATE_bulk_BOOK_STATUS_TRANSFER
							+ "access_no in('0'" + newbean.getAuthor() + ")");
			Prest.setString(1, branch_name);
			Prest.setString(2, newbean.getdeptName());
			Prest.executeUpdate();

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

		return ob;
	}

	public BookTransferBean findTransferMax() {
		log4jLogger.info("start===========findTransferMax=====");

		BookTransferBean newBean = null;
		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.TRANSFER_ORD_Code);
			rs = Prest.executeQuery();
			if (rs.next()) {
				newBean = new BookTransferBean();
				newBean.setordno(rs.getInt("maxno") + 1);

			}
		} catch (Exception e) {
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

		return newBean;
	}

	public List findTransferDisplay(int bcode) {
		log4jLogger.info("start===========findTransferDisplay=====");

		BookTransferBean newbean = null;
		String strsql = "";

		List finalResults = new ArrayList();

		try {

			if (bcode > 0) {
				strsql = " and branch_code=" + bcode + " ";
			}

			con = getSession().connection();
			st = con.createStatement();
			rs = st.executeQuery(DataQuery.TRANSFER_VIEW + strsql);

			while (rs.next()) {
				newbean = new BookTransferBean();
				newbean.setordno(rs.getInt("order_no"));
				newbean.setAccess_no(rs.getString("Access_No"));
				newbean.setDocument(rs.getString("Doc_Type"));
				newbean.setDate(Security.getdate(rs.getString("order_Date")));
				newbean.setdeptName(rs.getString("transfered_dept_name"));
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

	public List findTransferDisplay(BookTransferBean newbean) {
		log4jLogger.info("start===========findTransferDisplay=====");

		// BookTransferBean newbean = null;
		String strsql = "";

		List finalResults = new ArrayList();

		try {

			if (newbean.getBranchCode() > 0) {
				strsql = " and branch_code=" + newbean.getBranchCode() + " ";
			}
			if (newbean.getAccess_no() != "" && newbean.getAccess_no() != null
					&& newbean.getToaccess_no() != ""
					&& newbean.getToaccess_no() != null) {
				strsql = " and access_no between '" + newbean.getAccess_no()
						+ "' and  '" + newbean.getToaccess_no() + "' ";
			}

			System.out.println("==============query:" + strsql);

			con = getSession().connection();
			st = con.createStatement();
			rs = st.executeQuery(DataQuery.TRANSFER_VIEW + strsql);

			while (rs.next()) {
				newbean = new BookTransferBean();
				newbean.setordno(rs.getInt("order_no"));
				newbean.setAccess_no(rs.getString("Access_No"));
				newbean.setDocument(rs.getString("Doc_Type"));
				newbean.setDate(Security.getdate(rs.getString("order_Date")));
				newbean.setdeptName(rs.getString("Branch_Name"));
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

	public int findbulkTransferBooksReturn(String name, int branchID) {

		log4jLogger.info("start===========findbulkTransferBooksReturn=====");
		int count = 0;
		String strsql = "";

		try {

			if (branchID > 0) {
				strsql = " and branch_code=" + branchID + "";
			}

			con = getSession().connection();

			String rdate = (Security.getdate(Security.CalenderDate()));

			Prest = con
					.prepareStatement(DataQuery.UPDATE_bulk_TRANSFER_MAS_RETURN
							+ "access_no in('0'" + name + ")");
			// Prest.setString(2, name);
			Prest.setString(1, rdate);
			Prest.executeUpdate();

			Prest = con
					.prepareStatement(DataQuery.UPDATE_BOOK_STATUS_bulk_TRANSFER_RETURN
							+ "access_no in('0'" + name + ")");
			Prest.setInt(1, branchID);
			// Prest.setString(2, name);

			Prest.executeUpdate();

			count = 1;

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

		return count;

	}

	public int findTransferBooksReturn(String name, int branchID) {
		log4jLogger.info("start===========findTransferBooksReturn=====");

		int count = 0;
		String strsql = "";

		try {

			if (branchID > 0) {
				strsql = " and branch_code=" + branchID + " ";
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.TRANSFER_SELECT + strsql);
			Prest.setString(1, name);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {

				String rdate = (Security.getdate(Security.CalenderDate()));

				Prest = con
						.prepareStatement(DataQuery.UPDATE_TRANSFER_MAS_RETURN);
				Prest.setString(2, name);
				Prest.setString(1, rdate);
				Prest.executeUpdate();

				Prest = con
						.prepareStatement(DataQuery.UPDATE_BOOK_STATUS_TRANSFER_RETURN);
				Prest.setInt(1, branchID);
				Prest.setString(2, name);

				Prest.executeUpdate();

				count = 1;
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

		return count;
	}

	// ---------------- Member Payment ----------------

	public PaymentBean findIssueHistoryDetails(String code) {
		log4jLogger.info(":::::::::::::::MemberCode:::::::::::::::" + code);
		PaymentBean beanobject = new PaymentBean();

		ArrayList issueDetails = new ArrayList();
		try {
			log4jLogger.info("::::::::::inside issued books details:::::::::");

			Prest = con.prepareStatement(DataQuery.SELECT_ISSUEBOOKS
					+ " order by issue_date");
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			while (rs.next()) {
				issueDetails.add(rs.getString("access_no"));
				issueDetails.add(rs.getString("title"));
				issueDetails.add(rs.getString("author_name"));
				issueDetails.add(Security.getdate(rs.getString("issue_date")));
				issueDetails.add(Security.getdate(rs.getString("due_date")));

			}
			beanobject.setIssueDetail(issueDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return beanobject;

	}

	public PaymentBean findPaymentMember(String code, int branchID) {
		log4jLogger.info("findPaymentMember(Strin code) method begin" + code);

		PaymentBean beanobject = new PaymentBean();

		int rk = 0;
		Double tot_rs = 0.00, paid_rs = 0.00, bal_rs = 0.00;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.SELECT_MEMBER);
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {

				beanobject.setMcode(rs.getString("member_code"));
				beanobject.setMname(rs.getString("member_name"));
				beanobject.setDept(rs.getString("dept_name"));
				beanobject.setCourse(rs.getString("course_name"));
				beanobject.setBranch(rs.getInt("branch_code"));
				rk = 1;

			} else {
				beanobject.setMcode("NIL");
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ACCOUNT_Branch_ISSUE_COUNT
					+ " and issue_type='ISSUE'"); // deptChanges
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();

			if (rs.next()) {

				beanobject.setIssue(rs.getString("issuecount"));

			} else {
				beanobject.setIssue("0");
			}

			con = getSession().connection();
			Prest = con
					.prepareStatement(DataQuery.ACCOUNT_RETURN_COUNT_BranchWise
							+ " and branch_code=" + branchID);
			Prest.setString(1, code);

			rs = Prest.executeQuery();
			if (rs.next()) {

				beanobject.setReturned(rs.getString("returncount"));

			} else {
				beanobject.setReturned("0");
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.ACCOUNT_Branch_ISSUE_COUNT
					+ " and issue_type='RENEW'");
			Prest.setString(1, code);
			Prest.setInt(2, branchID);
			rs = Prest.executeQuery();
			if (rs.next()) {

				beanobject.setRenew(rs.getString("issuecount"));

			} else {
				beanobject.setRenew("0");
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Tot_Trans_Amt
					+ " and branch_code=" + branchID);
			Prest.setString(1, code);

			rs = Prest.executeQuery();

			if (rs.next()) {

				if (rs.getBigDecimal("Total_Amt") == null) {
					beanobject.setTot_Amt(tot_rs);
				} else {
					beanobject.setTot_Amt(rs.getDouble("Total_Amt"));
				}
			}

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Tot_Paid_Amt
					+ " and branch_code=" + branchID);
			Prest.setString(1, code);
			rs = Prest.executeQuery();
			if (rs.next()) {

				if (rs.getBigDecimal("Paid_Amt") == null) {
					beanobject.setPaid_Amt(paid_rs);
				} else {
					beanobject.setPaid_Amt(rs.getDouble("Paid_Amt"));
				}
			}

			tot_rs = beanobject.getTot_Amt();
			paid_rs = beanobject.getPaid_Amt();

			bal_rs = (tot_rs) - (paid_rs);

			beanobject.setBalance_Amt(bal_rs);

			int a = findPaymentBill_no();
			beanobject.setBill_No(a);

			ArrayList DEATILS = new ArrayList();
			try {
				Prest = con.prepareStatement(DataQuery.select_transBook_view);
				Prest.setString(1, code);
				Prest.setInt(2, branchID);
				rs = Prest.executeQuery();
				while (rs.next()) {

					DEATILS.add(rs.getString("trans_no"));
					DEATILS.add(Security.getdate(rs.getString("trans_date")));
					DEATILS.add(rs.getString("access_no"));
					DEATILS.add(rs.getString("trans_amount"));
					DEATILS.add(rs.getString("trans_head"));
				}

				beanobject.setPaymentList(DEATILS);
			} catch (Exception e) {
				e.printStackTrace();
			}

			ArrayList issueDetails = new ArrayList();
			try {
				Prest = con.prepareStatement(DataQuery.SELECT_ISSUEBranchBOOKS
						+ " order by issue_date");
				Prest.setString(1, code);
				Prest.setInt(2, branchID);
				rs = Prest.executeQuery();

				while (rs.next()) {
					issueDetails.add(rs.getString("access_no"));
					issueDetails.add(rs.getString("title"));
					issueDetails.add(rs.getString("author_name"));
					issueDetails.add(Security.getdate(rs
							.getString("issue_date")));
					issueDetails
							.add(Security.getdate(rs.getString("due_date")));

				}
				beanobject.setIssueDetail(issueDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}

			ArrayList PAYDEATILS = new ArrayList();
			try {
				Prest = con.prepareStatement(DataQuery.select_payment);
				Prest.setString(1, code);
				Prest.setInt(2, branchID);
				rs = Prest.executeQuery();

				while (rs.next()) {

					PAYDEATILS.add(rs.getString("bill_no"));
					PAYDEATILS.add(Security.getdate(rs
							.getString("payment_date")));
					PAYDEATILS.add(rs.getString("Amount"));
					PAYDEATILS.add(rs.getString("pay_mode"));
					PAYDEATILS.add(rs.getString("staff_code"));
				}

				beanobject.setPaidList(PAYDEATILS);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
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

		return beanobject;
	}

	public int findPaymentBill_no() {
		int bill = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Max_BillNo_Payment);
			rs = Prest.executeQuery();

			if (rs.next()) {

				bill = rs.getInt("billno");

			}

			if (bill == 0) {

				bill = 1;
			}

			log4jLogger.info("Bill_No is: " + bill);

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

		return bill;
	}

	public int findAddPayment(PaymentBean newbean) {

		log4jLogger.info("Store Payment master ===> (Payment Clearance)");

		int ac = 0;

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Fine_Payment_Clearance);
			Prest.setInt(1, newbean.getBill_No());
			Prest.setString(2, newbean.getMcode());
			Prest.setDouble(3, newbean.getCurrent_Amt());
			Prest.setString(4, newbean.getPdate());
			Prest.setString(5, "OVERDUE");
			Prest.setString(6, newbean.getDept()); // For Staff Code
			Prest.setString(7, "AUTOLIB");
			Prest.setString(8, "AUTOLIB");
			Prest.setInt(9, newbean.getBranch());

			Prest.executeUpdate();

			if (true) {

				ac = 1;
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

		return ac;
	}

	// Transfer Report

	public List findTransferedDeptName(int branchCode) {
		log4jLogger.info("start===========findTransferedDeptName=====");

		BindingBean newbean = null;

		List finalResults = new ArrayList();

		try {
			con = getSession().connection();
			st = con.createStatement();

			rs = st.executeQuery(DataQuery.TRANSFERED_DEPT_LOAD + " AND branch_code='"+branchCode+"'");// SHEK

			while (rs.next()) {
				newbean = new BindingBean();
				newbean.setCode(rs.getInt("Transfered_Dept_Code"));
				newbean.setName(rs.getString("Transfered_Dept_name"));

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
	
	public boolean deletePaymentClearance(PaymentBean paybean) {
		boolean delete=false;
		try{	
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Delete_User_Payment);
			Prest.setInt(1, paybean.getBill_No());
			Prest.setString(2, paybean.getMcode());
			Prest.setInt(3, paybean.getBranch());
			Prest.executeUpdate();
			if(true)
			delete=true;	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return delete;
	}

}