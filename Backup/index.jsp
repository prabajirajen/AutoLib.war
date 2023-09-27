<%
String URole=session.getAttribute("UserRights").toString().trim();
if(URole.equalsIgnoreCase("3") || URole.equalsIgnoreCase("4") || URole.equalsIgnoreCase("5") || URole.equalsIgnoreCase("6") || URole.equalsIgnoreCase("7") || URole.equalsIgnoreCase("8"))
{		
	response.sendRedirect("sessiontimeout.jsp");
}	
%>
<%@ include file="/Tree/demoFrameset.jsp"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/font.css" />

<%@ page language="java"  session="true" buffer="12kb" import="java.io.BufferedReader" import="Common.Security"%>
<%@ page language="java"  session="true" buffer="12kb" import="java.io.File" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.io.FileWriter" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.io.InputStreamReader" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.util.Date" %>
<%@ page language="java"  session="true" buffer="12kb" import="java.util.Properties" %>

<html>
<body>
<%
int count=0;
try 
{
	String filePath="",mysqlPath="",uname="",pwd;
	Properties properties=new Properties();
	properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
	
	filePath = properties.getProperty("autolib.backup.filepath");	
	mysqlPath = properties.getProperty("autolib.backup.mysqlpath");	
	uname = properties.getProperty("autolib.mysql.userName");	
	pwd = properties.getProperty("autolib.mysql.passWord");	
	
	
    Date date = new Date();
    String name = date.toString().substring(date.toString().length()-4,date.toString().length())+date.toString().substring(4,7)+date.toString().substring(8,10)+"_"+date.toString().substring(11,13)+date.toString().substring(14,16)+date.toString().substring(17,19);
    Runtime runtime = Runtime.getRuntime();
    
    //File backupFile = new File("D:/Java Librarry 2013/AutoBackup/AutoLibBackup_"+name+".sql");
    File backupFile = new File(filePath+"AutoLibBackup_"+name+".sql");
    FileWriter fw = new FileWriter(backupFile);
    //Process child = runtime.exec("C:/Program Files/MySQL/MySQL Server 5.0/bin/mysqldump --user=root --password=admin --lock-all-tables --opt autolib");
    Process child = runtime.exec(mysqlPath+"mysqldump --user="+uname+" --password="+pwd+" --lock-all-tables --opt autolib");
    InputStreamReader irs = new InputStreamReader(child.getInputStream());
    BufferedReader br = new BufferedReader(irs);

    String line;
    while( (line=br.readLine()) != null )  
    {
        fw.write(line + "\n");
    }

    fw.close();
    irs.close();
    br.close();
    count = 1;
    
}catch(Exception e)  {
	count=0;
	//e.printStackTrace();
}
finally   
{}
%>
<table width="50%" valign="middle" align="center" height="500" border="0">
<td>

<%
if(count>0) 
{
%>
  <div class="icon-ok" width="20%">  
    Backup Completed !
  </div>
<%
}else  {
%>
  <div class="icon-ok" width="20%">
    Error Occurred !
  </div>
<%} %>

</td>
</table>
</body>
</html>