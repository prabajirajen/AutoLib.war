package Lib.Auto.BulkFineUpload;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.admin.AdminService;
import Common.businessutil.calaloging.CalalogingService;

import org.apache.commons.io.output.*;


public class ImportFine extends HttpServlet implements Serializable  {
   
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(ImportFine.class);

   private boolean isMultipart;
   private String filePath="",tempPath="",indexPage=null;
   private int maxFileSize = 80 * 1024 * 1024;   // File Size should be 70KB
   private int maxMemSize = 4 * 1024 ;    // Memory Size should be 4KB
   private File file ;   
 
   
   
   
   
   
 /**  public void init( ){
	      // Get the file location where it would be stored.
	      filePath = getServletContext().getInitParameter("file-upload"); 
   }*/
   
   
   public void doPost(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, java.io.IOException {
	   
	   performTask(request, response);
	   
   }
   
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, java.io.IOException {
       performTask(request, response);
       throw new ServletException("GET method used with " + getClass( ).getName( )+": POST method required.");
   }    
     
   
   public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException {
   
   try {
	   
	   HttpSession session = request.getSession(true);
	   String res = Security.checkSecurity(1, session, response, request);		
	   if(res.equalsIgnoreCase("Failure"))
	   {
		   response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
		   return;
	   }
		
		
	   AdminService ss = BusinessServiceFactory.INSTANCE.getAdminService();
	   int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));    // For Titan
	   
	   String Staff = String.valueOf(session.getAttribute("UserID"));

       //	 get Path from Property file 
	   Properties properties=new Properties();
	   properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
  		
	   filePath = properties.getProperty("book.import.filepath");		
   	   
   	   File tempdir = new File(filePath+"/temp");
	   
       if(tempdir.exists())
	   {
		   tempPath = tempdir + "/";
		   //log4jLogger.info(">>>>>>>>>>> Directory Exists >>>>>>>>>>>>"+tempdir+" Path:"+tempPath);
	   }else {
		   if(tempdir.mkdir()){
			   tempPath = tempdir + "/";
		       log4jLogger.info(">>>>>>>>>>> Directory Created  >>>>>>>>>>>>"+tempdir+" Path:"+tempPath);
		   }
	   }   
	   
	   
	   FineDataMessage message =new FineDataMessage();
	   List<Object> saveDetail = new ArrayList<Object>();
	   int errorCode=0;
	   String errorException = "";
	   
      // Check that we have a file upload request
      isMultipart = ServletFileUpload.isMultipartContent(request);         
            
      DiskFileItemFactory factory = new DiskFileItemFactory();
      // maximum size that will be stored in memory
      factory.setSizeThreshold(maxMemSize);
      // Location to save data that is larger than maxMemSize.
      factory.setRepository(new File(tempPath));

      // Create a new file upload handler
      ServletFileUpload upload = new ServletFileUpload(factory);
      // maximum file size to be uploaded.
      upload.setSizeMax( maxFileSize );

      try{ 
      // Parse the request to get file items.
  
      List fileItems = upload.parseRequest(request);
      // Process the uploaded file items
      Iterator j = fileItems.iterator();
     
      String member_code = "",flag = "";
      byte[] fileInBytes = null;
      
   /**   
      Properties properties=new Properties();
	  properties.load(this.getClass().getClassLoader().getResourceAsStream("LocalStrings.properties"));
		
	  filePath = properties.getProperty("book.import.filepath");	*/
      
      
      while ( j.hasNext () ) 
      {
         FileItem fi = (FileItem)j.next();
         if ( !fi.isFormField () )	
         {
            // Get the uploaded file parameters
            String fieldName = fi.getFieldName();
            String fileName = fi.getName();
            String contentType = fi.getContentType();
            boolean isInMemory = fi.isInMemory();
            long sizeInBytes = fi.getSize(); 
            
            
            // Write the file
            if( fileName.lastIndexOf("\\") >= 0 ){
               file = new File( filePath + 
               fileName.substring( fileName.lastIndexOf("\\"))) ;
            }else{
               file = new File( filePath + 
               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
            }           
            fi.write( file ) ;         // This code is write the input File to D:\Java Librarry 2013\Development\AAAAA\Sample Library\jboss-4.2.0\bin.  when you are not set filepath from properties file. 
           
        	fileInBytes=fi.get();           
                       
        	log4jLogger.info("BookImport:File Path: "+filePath+" AbsolutePath:"+file.getAbsolutePath()+" File:"+file);
        	
            
         }else if(fi.isFormField())
         {       	 
        	 if("member".equalsIgnoreCase(fi.getFieldName())){        		
        		 member_code = fi.getString();
        	 }
        	 if("flag".equalsIgnoreCase(fi.getFieldName())){
        		 flag = fi.getString();
        	 }	
         }        
      }      
      
      Set<String> set = new HashSet<String>();
      String duplicateRow = "";
      
      try{   	  
    	  
          FileInputStream input = new FileInputStream(file.getAbsolutePath());  
          POIFSFileSystem fs = new POIFSFileSystem( input );  
          HSSFWorkbook wb = new HSSFWorkbook(fs);  
          HSSFSheet sheet = wb.getSheetAt(0);  
          HSSFRow row;
          HSSFCell cell;          
          
          
          for(int i=1; i<=sheet.getLastRowNum(); i++)
          {
        	          	  
        	  FineDataBean userData = new FineDataBean();
        	  row = sheet.getRow(i);       	  
        	  
        	  errorCode = i;
        	  // Checking Cell Type and Value
        	  
        	  cell=row.getCell((short) 0);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {   
        		  message = new FineDataMessage();
        		  message = ValidateError("Member_Code",i);
        		  break;
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  userData.setMemberCode(String.valueOf(row.getCell((short) 0).getStringCellValue()));  
        		  
        		  /** message = new FineDataMessage();
        		  message = ss.getCheckMemberCode(userData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new FineDataMessage();
            		  message = ValidateError("Member_Code Already Exists in Table",i);
            		  break;        			  
        		  }  */

        		  if(!set.add(userData.getMemberCode())) {     // For Checking Duplicate Access_No in Excel
        			  //message = new FineDataMessage();
            		  //message = ValidateError("Member_Code Already Exists in Excel",i);
            		  
            		  if(duplicateRow.isEmpty())
            		  {
            			  duplicateRow = duplicateRow + (i+1);
            		  }else
            		  {
            			  duplicateRow = duplicateRow +", "+ (i+1);
            		  }
            		  
            		  //break; 
        		  }
        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setMemberCode(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
        		  
        		 /** message = new FineDataMessage();
        		  message = ss.getCheckMemberCode(userData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new FineDataMessage();
            		  message = ValidateError("Employee_Code Already Exists in Table",i);
            		  break;        			  
        		  }  */
        		  
        		  if(!set.add(userData.getMemberCode())) {
                      //message = new FineDataMessage();
            		  //message = ValidateError("Employee_Code Already Exists in Excel",i);
            		  
            		  if(duplicateRow.isEmpty())
            		  {
            			  duplicateRow = duplicateRow + (i+1);
            		  }else
            		  {
            			  duplicateRow = duplicateRow +", "+ (i+1);
            		  }
            		  
            		  //break;
        		  }
        	  }
        	  else	  {  
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Member_Code:"+i);
        	  }
        	  
        	          	  
              cell=row.getCell((short) 1);       	  // For Checking Date
              
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		  
        		  message = new FineDataMessage();
        		  message = ValidateError("Payment_Date",i);
        		  break;        		  
              }        	  
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  { 
        		  message = new FineDataMessage();
        		  message = ValidateError("Payment_Date is Invalid.The Format is dd/mm/yyyy",i);
        		  break;        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {        		  
        		  try  {
        		  if (HSSFDateUtil.isCellDateFormatted(cell))
            	  {
        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		    
        			    SimpleDateFormat receivedDate = new SimpleDateFormat("yyyy-MM-dd");
        			    String pdate = receivedDate.format(date);
        			    userData.setPayDate(pdate);  
        			    
            	  }else {
            		  message = new FineDataMessage();
            		  message = ValidateError("Payment_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib Payment_Date Error ^^^"+e);
        			  message = new FineDataMessage();
            		  message = ValidateError("Payment_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
        		  }
        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Payment_Date:"+i);
        	  }
        	  
        	          	  
                                    	  
              cell=row.getCell((short) 2);       	  // For Checking Payment Amount
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  message = new FineDataMessage();
        		  message = ValidateError("Payment Amount is invalid",i);
        		  break;
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setPayAmount(0.0); 
        		  message = new FineDataMessage();
        		  message = ValidateError("Payment Amount is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setPayAmount((double)row.getCell((short) 2).getNumericCellValue());
        		  userData.setBranchCode(branchID);
        		  
        		  if(userData.getPayAmount() > 0.0 )
        		  {
        			  message = new FineDataMessage();
            		  message = ss.getFineCheck(userData);
            		  
            		  if(message.getCount() == 0)
            		  {
            			  message = new FineDataMessage();
                		  message = ValidateError("Member_Code doesn't exists in Table",i);
                		  break;        			  
            		  }else  {
            		  
            		   if(message.getBalanceAmt() == 0.00)
            		   {
            			  message = new FineDataMessage();
                 		  message = ValidateError("NO due amount from this member. Remove this from excel",i);
                 		  break;
            		   }
            		   else if(message.getBalanceAmt() < userData.getPayAmount())
            		   {            			         			  
            			  message = new FineDataMessage();
                		  message = ValidateError("Payment Amount is greater than Balance Amount",i);
                		  break; 
            		   }    
            		  
            		  }
        		  }
        		  else {
        			  message = new FineDataMessage();
            		  message = ValidateError("Payment Amount is invalid.Amount should be greater than Zero",i);
            		  break;
        		  }
        		  userData.setStaffCode(Staff);   // Set Staff code who import this.
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Payment Amount:"+i);
        	  } 
        	  
        	  
        	  saveDetail.add(userData);
        	  
        	  //employeeService(userData);       // call to spring service layer	  

          } 
          
          
          
} catch (FileNotFoundException ec) {	
	log4jLogger.info("AutoLibError:FileNotFound:"+ec);
    ec.printStackTrace();
} catch (IOException e) {
	log4jLogger.info("AutoLibError:IOException:"+e);
	errorException = "InvalidFile";	
	//e.printStackTrace();
} catch (NumberFormatException e) {
	log4jLogger.info("AutoLibError:NumberFormat:"+e);
    e.printStackTrace();
} catch (NullPointerException e) {
	log4jLogger.info("AutoLibError:NullPointer:"+e);
	  message = new FineDataMessage();
	  message = ValidateError("Invalid Row ",errorCode);
} catch (Exception e) { 
	log4jLogger.info("AutoLibError:OtherException:"+e);
}
finally{
	set.clear();
}
      
  if(errorException.equals("InvalidFile"))
  {
	    indexPage ="/BulkFineUpload/index.jsp?check=InvalidFile";
  }else {
        	
    
    
    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
    {    	
    	request.setAttribute("bean",message);
    	indexPage ="/BulkFineUpload/index.jsp?check=dataerror";    	
    }
    else if(!duplicateRow.isEmpty())
    {
    	message = new FineDataMessage();
		message = ValidateError("Member_Code Already Exists in Excel at Row No: "+duplicateRow,0);
		request.setAttribute("bean",message);
    	indexPage ="/BulkFineUpload/index.jsp?check=dataerror";    	
    }
    else
    {
    	if(saveDetail != null && !saveDetail.isEmpty())
    	{
    		int save = ss.getImportFineData(saveDetail);
    		indexPage ="/BulkFineUpload/index.jsp?check=success";
    	}else
    	{
    		indexPage ="/BulkFineUpload/index.jsp?check=ErrorToSave";
    	}
    }
  }   
    
    dispatch(request, response, indexPage);
    
    
      
   }catch(Exception ex) {
	   indexPage ="/BulkFineUpload/index.jsp?check=LargeFile";
	   log4jLogger.info("AutoLibError:FileException:"+ex);
	   dispatch(request, response, indexPage);
   }
   
   }catch (IOException e) {
		// TODO Auto-generated catch block	   
		e.printStackTrace();
   }   
   }
   
   public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}
   
   
   
   
   
   private void employeeService(FineDataBean employee)
   {
	   log4jLogger.info("=======Inside employeeService=========="+employee.getMemberCode());
   	
   	try{               	  
   	       Class.forName("com.mysql.jdbc.Driver").newInstance();
   	       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
   	       Statement stat=con.createStatement();       
   	 
  	       
   	       int k=stat.executeUpdate("insert into excel values('"+employee.getMemberCode()+"','"+employee.getPayDate()+"','"+employee.getPayAmount()+"')");
   	       log4jLogger.info("Data is inserted");
   	       stat.close();
   	       con.close();
   	   }
   	   catch(Exception e){	  
   		log4jLogger.info("AutoLibError:DBConnect:"+e);
   	   }   	
   }
   
   
   public FineDataMessage ValidateError(String error, int i)
   {	   
	   log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
	   FineDataMessage message = new FineDataMessage();	  
	   
	   if(i>0)  {   
	       message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	
	   }else {
		   message.setErrorMsg("Error : "+error);
	   }
	   
	   return message;	   
   }
   
   
   
    
}