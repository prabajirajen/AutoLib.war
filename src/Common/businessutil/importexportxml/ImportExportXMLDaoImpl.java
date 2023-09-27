/*
 *                 Autolib License Notice
 *
 * The contents of this file are subject to the Autolib  License
 * Version 1.0 (the "License"). You may not use this file except in
 * compliance with the License.The Initial Developer of the Original Code is
 * Autolib Software Systems.
 * Portions Copyright 1998-2010.Autolib Software Systems All Rights Reserved.
 *
 *
 */
package Common.businessutil.importexportxml;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;


/** The Class ImportExportXMLDaoImpl. */
public class ImportExportXMLDaoImpl extends BaseDBConnection implements ImportExportXMLDao
{
	private static Logger log4jLogger = Logger.getLogger(ImportExportXMLDaoImpl.class);	

	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;





	public List findBibliographyReportList(String query){

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()){

				ImportExportXML importExportXML = new ImportExportXML();


				importExportXML.setAccessNo(rs.getString("access_no"));
				importExportXML.setAuthorName(rs.getString("author_name"));
				importExportXML.setTitle(rs.getString("title"));
				importExportXML.setEdition(rs.getString("edition"));
				importExportXML.setCallNo(rs.getString("call_no"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setYearPub(rs.getString("year_pub"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setSubjectName(rs.getString("sub_name"));
				importExportXML.setBudgetName(rs.getString("budget"));
				importExportXML.setPrice(rs.getString("NetPrice"));

				finalResults.add(importExportXML);
			}



		}catch(Exception e){
			e.printStackTrace();
		}

		return finalResults;
	}


	public List findGateRegisterReportList(String query){

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()){

				ImportExportXML importExportXML = new ImportExportXML();


				importExportXML.setMemberCode(rs.getString("member_code"));
				importExportXML.setMemberName(rs.getString("member_name"));
				importExportXML.setDesigName(rs.getString("dsname"));
				importExportXML.setIntime(rs.getString("in_time"));
				importExportXML.setOuttime(rs.getString("out_time"));
				importExportXML.setMinused(rs.getString("min_used"));
				importExportXML.setReturnDate(rs.getString("return_time"));
				finalResults.add(importExportXML);
			}



		}catch(Exception e){
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findGateRegisterReportStatisticsList(String query){

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()){

				ImportExportXML importExportXML = new ImportExportXML();


				importExportXML.setBreakupdate(rs.getString(1));
				importExportXML.setBreakupcount(rs.getString(2));
				finalResults.add(importExportXML);
			}



		}catch(Exception e){
			e.printStackTrace();
		}

		return finalResults;
	}

	public List getAccessionWiseReportList(String query)
	{
		log4jLogger.info("================= start: [getAccessionWiseReportList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString("access_no"));
				importExportXML.setRecdDate(rs.getString("received_date"));
				importExportXML.setCallNo(rs.getString("call_no"));
				importExportXML.setTitle(rs.getString("title"));
				importExportXML.setAuthorName(rs.getString("author_name"));
				importExportXML.setEdition(rs.getString("edition"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setYearPub(rs.getString("year_pub"));
				importExportXML.setPages(rs.getString("pages"));
				importExportXML.setSupplierName(rs.getString("supplier"));
				importExportXML.setInvoiceNo(rs.getString("invoice_no"));
				importExportXML.setInvoiceDate(rs.getString("invoice_date"));
				importExportXML.setIsbnNo(rs.getString("isbn"));
				importExportXML.setPrice(rs.getString("bprice"));
				importExportXML.setAcceptedPrice(rs.getString("accepted_price"));
				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getAccessionWiseReportList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}

		/**
		SQLQuery sql = getSession().createSQLQuery(query);
		List list = sql.list();	

		List<Object> finalResults = null;
		if (list != null)
		{
			finalResults = new ArrayList<Object>();
			for (int i = 0; i < list.size(); i++)
			{
				Object[] obj = (Object[]) list.get(i);
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setRollNumber(obj[0].toString());
				importExportXML.setName(obj[1].toString());
				importExportXML.setHour1(obj[2].toString());
				importExportXML.setHour2(obj[3].toString());
				importExportXML.setHour3(obj[4].toString());
				importExportXML.setHour4(obj[5].toString());
				importExportXML.setHour5(obj[6].toString());
				importExportXML.setHour6(obj[7].toString());
				importExportXML.setHour7(obj[8].toString());
				importExportXML.setHour8(obj[9].toString());
				finalResults.add(importExportXML);
			}
		}*/
		return finalResults;
	}



	public List getCounterReportList(String query, String flag)
	{
		log4jLogger.info("================= start: [getCounterReportList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setAccessNo(rs.getString(1));
				importExportXML.setTitle(rs.getString(2));
				importExportXML.setMemberCode(rs.getString(3));
				importExportXML.setIssueDate(rs.getString(4));
				importExportXML.setDueDate(rs.getString(5));
				importExportXML.setMemberName(rs.getString("member_name"));
				importExportXML.setDeptName(rs.getString("dept_name"));

				if(flag.equalsIgnoreCase("Issue"))
				{
					if(rs.getString(6).equalsIgnoreCase("01/01/1800"))
					{					 
						importExportXML.setReturnDate("Not Yet Returned");
					}else  {
						importExportXML.setReturnDate(rs.getString(6));
					}					
					importExportXML.setDocument(rs.getString(7));
					importExportXML.setStaffCode(rs.getString(8));

				}else if(flag.equalsIgnoreCase("Return"))
				{
					importExportXML.setReturnDate(rs.getString(6));
					importExportXML.setDocument(rs.getString(7));

				}else if(flag.equalsIgnoreCase("Renewal"))
				{
					importExportXML.setDocument(rs.getString(6));					
				}

				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getCounterReportList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}


	public List getCounterBreakupReportList(String query, String flag)
	{
		log4jLogger.info("================= start: [getCounterReportList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setBreakupdate(rs.getString(1));
				importExportXML.setBreakupcount(rs.getString(2));
				if(flag.equalsIgnoreCase("Fine"))
				{
					importExportXML.setTotfine(rs.getString(3));
				}
				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getCounterReportList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}


	public List getOverAllReportList(String query)
	{
		log4jLogger.info("================= start: [getOverAllReportList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setMemberCode(rs.getString("member_code"));
				importExportXML.setMemberName(rs.getString("member_name"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setFineAmount(rs.getString("total_fine"));
				importExportXML.setPaidAmount(rs.getString("paid_amount"));
				importExportXML.setBalanceAmount(rs.getString("bal_amount"));	

				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getOverAllReportList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;
	}


	public List findStatisticsDetailsReportList(String query){


		List<Object> finalResults = new ArrayList<Object>();
		try {
			log4jLogger.info(":::::::::::::::: inside findStatisticsDetailsReportList::::::::");
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()){
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString("access_no"));
				importExportXML.setAuthorName(rs.getString("author_name"));
				importExportXML.setTitle(rs.getString("title"));
				importExportXML.setCallNo(rs.getString("call_no"));
				importExportXML.setPublisherName(rs.getString("publisher"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setDiscount(rs.getString("discount"));
				importExportXML.setPrice(rs.getString("accepted_price"));
				finalResults.add(importExportXML);
			}



		}catch (SQLException e) {
			e.printStackTrace();
		}


		return finalResults;

	}


	public List getStatisticsWiseReportList(String query)
	{
		log4jLogger.info("================= start: [getAccessionWiseReportList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();
		int totbook=0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setDeptName(rs.getString(1));
				importExportXML.setNoOfBooks(rs.getString(2));
				importExportXML.setNoOfTitles(rs.getString(3));
				importExportXML.setPrice(rs.getString(4));
				importExportXML.setDiscount(rs.getString(5));
				importExportXML.setNetPrice(rs.getString(6));
				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getAccessionWiseReportList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return finalResults;
	}

	public List findMemberReportExcelList(String query)
	{
		log4jLogger.info("================= start: [getMemberReportExcelList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();
		int totbook=0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setMemberCode(rs.getString(1));
				importExportXML.setMemberName(rs.getString(2));
				importExportXML.setDeptName(rs.getString(11));
				importExportXML.setGroupName(rs.getString(9));
				importExportXML.setValidity(rs.getString(12));
				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getMemberReportExcelList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return finalResults;
	}

	public List findBindingReportExcelList(String query)
	{
		log4jLogger.info("================= start: [getMemberReportExcelList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();
		int totbook=0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString(1));
				importExportXML.setTitle(rs.getString(2));
				importExportXML.setBinderName(rs.getString(3));
				importExportXML.setSendingDate(rs.getString(4));
				importExportXML.setDocument(rs.getString(5));
				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getMemberReportExcelList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return finalResults;
	}

	public List findRFIDTagList(String query)
	{
		log4jLogger.info("================= start: [getRFIDTagList] ====================== " );

		List<Object> finalResults = new ArrayList<Object>();
		int totbook=0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next())
			{				
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setAccessNo(rs.getString(1));
				importExportXML.setTagId(rs.getString(2));
				importExportXML.setTitle(rs.getString(3));
				importExportXML.setIsbnNo(rs.getString(6));
				importExportXML.setAvailability(rs.getString(7));

				finalResults.add(importExportXML);
			}

			log4jLogger.info("================= End: [getRFIDTagList] ====================== " );

		} catch (SQLException e) {
			e.printStackTrace();
		}


		return finalResults;
	}


	public List findQBReportList(String query) {

		log4jLogger
		.info("================= start: [getQBReportExcelList] ====================== ");

		List<Object> finalResults = new ArrayList<Object>();
		int totbook = 0;
		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();
				importExportXML.setQBCode(rs.getString("QB_Code"));
				importExportXML.setSubCode(rs.getString("Sub_Code"));
				importExportXML.setSubName(rs.getString("Sub_Name"));
				importExportXML.setCourse(rs.getString("Course_name"));
				// importExportXML.setCourseMajor(rs.getString("Course_major"));
				importExportXML.setDeptName(rs.getString("Dept_Name"));
				importExportXML.setYear(rs.getString("Year"));
				importExportXML.setSemester(rs.getString("Semester"));
				importExportXML.setMonth(rs.getString("Month"));
				finalResults.add(importExportXML);
			}

			log4jLogger
			.info("================= End: [getQBReportExcelList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;
	}


	@Override
	public List getCustomReportList(String query, String[] items) {
		log4jLogger
		.info("================= start: [getCustomReportList] ====================== "
				+ query);

		List<Object> finalResults = new ArrayList<Object>();
		;

		try {
			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();

			while (rs.next()) {
				ImportExportXML importExportXML = new ImportExportXML();

				for (int i = 1; i < items.length; i++) {

					if (items[i].equalsIgnoreCase("access_no")) {
						importExportXML.setAccessNo(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("author_name")) {
						importExportXML.setAuthorName(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("title")) {
						importExportXML.setTitle(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("call_no")) {
						importExportXML.setCallNo(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("dept_name")) {
						importExportXML.setDepartmentName(rs
								.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("sub_name")) {
						importExportXML.setSubjectName(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("publisher")) {
						importExportXML
						.setPublisherName(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("availability")) {
						importExportXML.setAvailability(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("isbn")) {
						importExportXML.setIsbnNo(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("year_pub")) {
						importExportXML.setYearPub(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("bprice")) {
						importExportXML.setPrice(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("received_date")) {
						importExportXML.setReceivedDate(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("edition")) {
						importExportXML.setEdition(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("location")) {
						importExportXML.setLocation(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("invoice_no")) {
						importExportXML.setInvoiceNo(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("language")) {
						importExportXML.setLanguage(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("supplier")) {
						importExportXML.setSupplierName(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("volno")) {
						importExportXML.setVolno(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("add_field3")) {
						importExportXML.setIssue_no(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("script")) {
						importExportXML.setMonth(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("accepted_price")) {
						importExportXML
						.setAcceptedPrice(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("invoice_date")) {
						importExportXML.setInvoiceDate(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("discount")) {
						importExportXML.setDiscount(rs.getString(items[i]));
					} else if (items[i].equalsIgnoreCase("gift_purchase")) {
						importExportXML.setPurchaseType(rs.getString(items[i]));
					}

					else if (items[i].equalsIgnoreCase("keywords")) {
						importExportXML.setKeywords(rs.getString(items[i]));
					}
				}

				finalResults.add(importExportXML);
			}

			log4jLogger
			.info("================= End: [getCustomReportList] ====================== ");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return finalResults;

	}
	public List findLibrary_Login_Excel(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setMemberCode(rs.getString("member_code"));
				importExportXML.setMemberName(rs.getString("member_name"));
				importExportXML.setGroup(rs.getString("group_name"));
				importExportXML.setDeptName(rs.getString("dept_name"));
				importExportXML.setReturnDate(rs.getString("last_visit"));
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List findLibrary_Login_Excel_Statistics(String query) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				importExportXML.setBreakupdate(rs.getString(1));
				importExportXML.setBreakupcount(rs.getString(2));
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}

	public List<?> getTitleWiseReportList(String query) {
		log4jLogger
		.info("================= start: [getTitleWiseReportList] ====================== ");

List<Object> finalResults = new ArrayList<Object>();
;
int totbook = 0;
try {
	con = getSession().connection();
	Prest = con.prepareStatement(query);
	rs = Prest.executeQuery();

	while (rs.next()) {
		ImportExportXML importExportXML = new ImportExportXML();
		importExportXML.setTitle(rs.getString(1));
		importExportXML.setAuthorName(rs.getString(2));
		//importExportXML.setEdition(rs.getString(3));
		importExportXML.setNoOfBooks(rs.getString(3));
		finalResults.add(importExportXML);
	}

	log4jLogger
			.info("================= End: [getTitleWiseReportList] ====================== ");

} catch (SQLException e) {
	e.printStackTrace();
}

return finalResults;
}
	
	public List findFreqUsedMemberExcel(String query, String type) {

		List<Object> finalResults = new ArrayList<Object>();

		try {

			con = getSession().connection();
			Prest = con.prepareStatement(query);
			rs = Prest.executeQuery();
			while (rs.next()) {

				ImportExportXML importExportXML = new ImportExportXML();

				if (type.equalsIgnoreCase("frequency")) {
					System.out.println("::::Inside Frequency Excel::::::");
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML
							.setDepartmentName(rs.getString("dept_name"));
					importExportXML.setCourseName(rs.getString("course_name"));
					importExportXML.setCyear(rs.getString("cyear"));
					importExportXML.setTotal(rs.getString("total"));
				} else if (type.equalsIgnoreCase("gate")) {
					System.out
							.println("::::::::Inside Best User Award::::::::");
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML
							.setDepartmentName(rs.getString("dept_name"));
					importExportXML.setCyear(rs.getString("cyear"));
					importExportXML.setTotal(rs.getString("total"));
					importExportXML.setTotalMins(rs.getString("totalminute"));
				} else if (type.equalsIgnoreCase("member")) {
					System.out.println("::::Inside Not Member Excel::::::");
					importExportXML.setMemberCode(rs.getString("member_code"));
					importExportXML.setMemberName(rs.getString("member_name"));
					importExportXML.setDesigName(rs.getString("designation"));
					importExportXML.setCyear(rs.getString("course_year"));
					importExportXML
							.setDepartmentName(rs.getString("dept_name"));

				}
				finalResults.add(importExportXML);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return finalResults;
	}


}

