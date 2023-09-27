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


/** The Interface ImportExportXMLService. */
public interface ImportExportXMLService
{
	public List getAccessionWiseReportList(String query);

	public List getBibliographyReportList(String query);

	public List getGateRegisterReportList(String query);

	public List getGateRegisterReportStatisticsList(String query);

	public List getCounterReportList(String query, String flag);
	public List getCounterBreakupReportList(String query, String flag);
	public List getStatisticsWiseReportList(String query);
	public List getStatisticsDetailsReportList(String query);
	public List getOverAllReportList(String query);
	public List getMemberReportExcelList(String query);
	public List getBindingReportExcelList(String query);
	public List getRFIDTagList(String query);

	public List getQBReportList(String string);

	public List getCustomReportList(String sQLDOCTYPE, String[] items);

	public List getLibrary_Login_Excel(String query);

	public List getLibrary_Login_Excel_Statistics(String query);

	public List<?> getTitleWiseReportList(String query);
	
	public List getFreqUsedMemberExcel(String sb,String type);	
}
