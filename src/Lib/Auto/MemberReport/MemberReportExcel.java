package Lib.Auto.MemberReport;

import java.util.HashMap;
import java.util.Map;

import Common.businessutil.importexportexcel.ExportProcessor;
import Common.businessutil.importexportxml.ImportExportXML;
import Common.businessutil.reports.ReportQueryUtill;

public class MemberReportExcel implements ExportProcessor
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	MemberReportExcel(Map recordProcessorMap)
	{
		
	}
	
	public Map getTitleDetails()
	{
		Map<Object,Object> title=new HashMap<Object,Object>();
		title.put("title","Report Name :");
		title.put("titleValue",ReportQueryUtill.Counter_MemberReports_Title);
		return title;
	}

	public String getExcelFileName( )
	{
		return ReportQueryUtill.Counter_MemberReports_Title+"_Excel.csv";
	}

	public int getHeaderCount()
	{
		return 33;
	}

	public String[] getExcelHeader()
	{
		return new String[]
		{ "UserId", "UserName", "Department", "Group", "Validity Date"};
	}

	public String[] setExportExcelDataMap(Object entity)
	{		
		ImportExportXML std = (ImportExportXML) entity;
		String[] fields = new String[getHeaderCount()];
		
		fields[0] = std.getMemberCode().toString();
		fields[1] = std.getMemberName().toString();
		fields[2] = std.getDeptName().toString();
		fields[3] = std.getGroupName().toString();
		fields[4] = std.getValidity().toString();
		return fields;
	}

}
