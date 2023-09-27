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

/** The Class ImportExportXMLServiceImpl. */
public class ImportExportXMLServiceImpl implements ImportExportXMLService
{
	private ImportExportXMLDao importExportXMLDao;

	/**
	 * @return Returns the importExportXMLDao.
	 */
	public ImportExportXMLDao getImportExportXMLDao( )
	{
		return importExportXMLDao;
	}

	/**
	 * @param importExportXMLDao The importExportXMLDao to set.
	 */
	public void setImportExportXMLDao(ImportExportXMLDao importExportXMLDao)
	{
		this.importExportXMLDao = importExportXMLDao;
	}
	
	
	
	
	
	
	public List getAccessionWiseReportList(String query) 
	{
		return importExportXMLDao.getAccessionWiseReportList(query);
	}
	
	
	public List getBibliographyReportList(String query) 
	{
		return importExportXMLDao.findBibliographyReportList(query);
	}
	
	public List getGateRegisterReportList(String query) 
	{
		return importExportXMLDao.findGateRegisterReportList(query);
	}
	
	public List getGateRegisterReportStatisticsList(String query) 
	{
		return importExportXMLDao.findGateRegisterReportStatisticsList(query);
	}
	
	public List getCounterReportList(String query, String flag)
	{
		return importExportXMLDao.getCounterReportList(query,flag);
	}
	
	public List getCounterBreakupReportList(String query, String flag)
	{
		return importExportXMLDao.getCounterBreakupReportList(query,flag);
	}
	
	
	public List getOverAllReportList(String query)
	{
		return importExportXMLDao.getOverAllReportList(query);
		
	}
	
	
	public List getStatisticsWiseReportList(String query) 
	{
		return importExportXMLDao.getStatisticsWiseReportList(query);
	}
	public List getStatisticsDetailsReportList(String query) 
	{
		return importExportXMLDao.findStatisticsDetailsReportList(query);
	}
	public List getMemberReportExcelList(String query) 
	{
		return importExportXMLDao.findMemberReportExcelList(query);
	}
	public List getBindingReportExcelList(String query) 
	{
		return importExportXMLDao.findBindingReportExcelList(query);
	}
	public List getRFIDTagList(String query) 
	{
		return importExportXMLDao.findRFIDTagList(query);
	}
	
	public List getQBReportList(String query) 
	{
		return importExportXMLDao.findQBReportList(query);
	}

	public List getCustomReportList(String query, String[] items)
	{
		return importExportXMLDao.getCustomReportList(query, items);
	}

	@Override
	public List getLibrary_Login_Excel(String query) {
		return importExportXMLDao.findLibrary_Login_Excel(query);
	}


	public List getLibrary_Login_Excel_Statistics(String query) {
		return importExportXMLDao.findLibrary_Login_Excel_Statistics(query);
	}

	public List<?> getTitleWiseReportList(String query) {
		
		return importExportXMLDao.getTitleWiseReportList(query);
	}
	
	public List getFreqUsedMemberExcel(String query,String type)
	{
		return importExportXMLDao.findFreqUsedMemberExcel(query,type);
	}
}
