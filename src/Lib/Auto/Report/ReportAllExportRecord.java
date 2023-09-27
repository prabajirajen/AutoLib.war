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
package Lib.Auto.Report;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;


/**
 * The Class DateWiseExportRecord.
 */

public class ReportAllExportRecord implements ExportProcessor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String rptFlag;
	
	String rptName;
	
	String fromAccNo;
	
	String toAccNo;
	
	String documentType;

	ReportAllExportRecord(Map recordProcessorMap)
	{
		rptFlag = (String) recordProcessorMap.get("rptFlag");
		rptName = (String) recordProcessorMap.get("rptTitle");
		fromAccNo = (String)recordProcessorMap.get("fromAccNo");
		toAccNo = (String)recordProcessorMap.get("toAccNo");
		documentType = (String)recordProcessorMap.get("documentType");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",rptName);
		title.put("subTitle1","From :");
		title.put("subTitle2","To :");
		title.put("subTitle3","Document Type :");
		
		title.put("value1",fromAccNo);
		title.put("value2",toAccNo);
		title.put("value3",documentType);
		title.put("count",3);//count value should be equal to value count
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
		if(rptFlag.equalsIgnoreCase("Issue"))
		{
			return new String[]
			{ "Employee Code", "Employee Name", "Department", "Access No", "Title", "Issue Date", "Due Date", "Return Date", "Document", "Staff Code" };
			          	
		}else if(rptFlag.equalsIgnoreCase("Return"))
		{
		    return new String[]
		    { "Employee Code", "Employee Name", "Department", "Access No", "Title", "Issue Date", "Due Date", "Return Date", "Document"};	
		}else {
			return new String[]
			{ "Employee Code", "Employee Name", "Department", "Access No", "Title", "Issue Date", "Due Date", "Document" };			      		
		}
	}
		

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getMemberCode().toString();
		fields[1] = std.getMemberName().toString();
		fields[2] = std.getDeptName().toString();
		
		fields[3] = std.getAccessNo().toString();
		fields[4] = std.getTitle().toString();		
		fields[5] = std.getIssueDate().toString();
		fields[6] = std.getDueDate().toString();
		
		if(rptFlag.equalsIgnoreCase("Issue"))
		{		
			fields[7] = std.getReturnDate().toString();		
			fields[8] = std.getDocument().toString();		
			fields[9] = std.getStaffCode().toString();
			
		}else if(rptFlag.equalsIgnoreCase("Return"))
		{
			fields[7] = std.getReturnDate().toString();		
			fields[8] = std.getDocument().toString();	
			
		}else if(rptFlag.equalsIgnoreCase("Renewal"))
		{
			fields[7] = std.getDocument().toString();	
		}
		
		
		return fields;
	}
}
