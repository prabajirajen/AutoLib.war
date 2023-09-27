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

import java.util.List;


/** The Interface ImportExportXMLDao. */
public interface ImportExportXMLDao
{
	public List getAccessionWiseReportList(String query);
	
	public List findBibliographyReportList(String query);
	
	public List findGateRegisterReportList(String query);
	
	public List findGateRegisterReportStatisticsList(String query);
	
	public List getCounterReportList(String query, String flag);
	
	public List getCounterBreakupReportList(String query, String flag);

	public List getOverAllReportList(String query);
	
	public List getStatisticsWiseReportList(String query);
	
	public List findStatisticsDetailsReportList(String query);
	
	public List findMemberReportExcelList(String query);
	
	public List findBindingReportExcelList(String query);
	
	public List findRFIDTagList(String query);

	public List findQBReportList(String query);

	public List getCustomReportList(String query, String[] items);

	public List findLibrary_Login_Excel(String query);

	public List findLibrary_Login_Excel_Statistics(String query);

	public List<?> getTitleWiseReportList(String query);	
	
	public List findFreqUsedMemberExcel(String query,String type);
	
}
