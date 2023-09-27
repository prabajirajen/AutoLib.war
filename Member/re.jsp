<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<html>
<head>
	<title>Untitled</title>
</head>

<body>
Hai to Every one I have a solution to JSP Insert image into Database

 

<%@ page import="java.sql.*,java.io.*,java.util.*"%>
<%


String filename="";
try
{

MultipartRequest multi= new MultipartRequest(request,".",5*1024*1024);
Enumeration files=multi.getFileNames();
File f=null;
while(files.hasMoreElements())
{
String name=(String)files.nextElement();
filename=multi.getFilesystemName(name);
String type=multi.getContentType(name);
f=multi.getFile(name);
}
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
Connection con = DriverManager.getConnection("jdbc:Odbc:AutoLib","sa","");
Statement stmt = con.createStatement();
InputStream is = new FileInputStream(f);
byte b[]=new byte[is.available()];
is.read(b);
String sql = "INSERT into photo_test (\"Photo\") values('" + b + "')";
stmt.execute(sql);
stmt.close();
}catch(Exception e)
{

}
out.println("The Image is Added into Database");

%>

</body>
</html>
