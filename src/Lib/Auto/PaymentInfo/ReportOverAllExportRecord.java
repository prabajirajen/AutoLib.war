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
package Lib.Auto.PaymentInfo;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;


/**
 * The Class DateWiseExportRecord.
 */

public class ReportOverAllExportRecord implements ExportProcessor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	String rptName;
	

	ReportOverAllExportRecord(Map recordProcessorMap)
	{		
		rptName = (String) recordProcessorMap.get("rptTitle");		
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",rptName);	
		
		title.put("count",1);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return rptName + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{		
			return new String[]
			{ "Employee Code", "Employee Name", "Department Name", "Fine Amount", "Paid Amount", "Balance Amount"};
	}
		

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getMemberCode().toString();
		fields[1] = std.getMemberName().toString();
		fields[2] = std.getDeptName().toString();		
		fields[3] = std.getFineAmount().toString();
		fields[4] = std.getPaidAmount().toString();
		fields[5] = std.getBalanceAmount().toString();
		
		return fields;
	}
}
