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
package Lib.Auto.BibliographyReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;


/**
 * The Class DateWiseExportRecord.
 */

public class BibliographyExportRecord implements ExportProcessor
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String fromAccNo;
	
	String toAccNo;
	
	String documentType;

	BibliographyExportRecord(Map recordProcessorMap)
	{
		//fromAccNo = (String)recordProcessorMap.get("fromAccNo");
		//toAccNo = (String)recordProcessorMap.get("toAccNo");
		//documentType = (String)recordProcessorMap.get("documentType");
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Accession_Title);
		//title.put("subTitle1","From :");
		//title.put("subTitle2","To :");
		//title.put("subTitle3","Document Type :");
		
		//title.put("value1",fromAccNo);
		//title.put("value2",toAccNo);
		//title.put("value3",documentType);
		//title.put("count",3);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.Biblio_Title+"_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "Access No", "Author Name", "Title", "Edition", "Call No", "Publisher", "Year Pub", "Dept Name", "Subject Name", "Budget Name", "Net Price"};
	}

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getAccessNo().toString();
		fields[1] = std.getAuthorName().toString();
		fields[2] = std.getTitle().toString();
		fields[3] = std.getEdition().toString();
		fields[4] = std.getCallNo().toString();
		fields[5] = std.getPublisherName().toString();
		fields[6] = std.getYearPub().toString();
		fields[7] = std.getDeptName().toString();
		fields[8] = std.getSubjectName().toString();
		fields[9] = std.getBudgetName().toString();
		fields[10] = std.getPrice().toString();
		
		return fields;
	}
}
