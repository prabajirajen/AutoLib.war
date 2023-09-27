package Lib.Auto.RFIDTagReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class RFIDTagExcel implements ExportProcessor
{
String fromAccNo;
	
	String toAccNo;
	
	String documentType;

	RFIDTagExcel()
	{
		
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Tag_Title);
		title.put("count",0);//count value should be equal to value count
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.Tag_Title + "_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "RfidTag", "ISBN", "Bookid", "BookTitle", "Status" };
	}

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		fields[0] = std.getTagId().toString();
		fields[1] = std.getIsbnNo().toString();
		fields[2] = std.getAccessNo().toString();
		fields[3] = std.getTitle().toString();
		fields[4] = std.getAvailability().toString();		
		return fields;
	}
}
