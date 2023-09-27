package Lib.Auto.Member_Import;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
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


public class ImportMember extends HttpServlet implements Serializable  {
   
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
private static Logger log4jLogger = Logger.getLogger(ImportMember.class);

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
	   int branchID=(Integer.parseInt((String.valueOf(session.getAttribute("UserBranchID")))));  // For Titan

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
	   
	   
	   MemberDataMessage message =new MemberDataMessage();
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
      
      Set set = new HashSet();
      
      try{   	  
    	  
          FileInputStream input = new FileInputStream(file.getAbsolutePath());  
          POIFSFileSystem fs = new POIFSFileSystem( input );  
          HSSFWorkbook wb = new HSSFWorkbook(fs);  
          HSSFSheet sheet = wb.getSheetAt(0);  
          HSSFRow row;
          HSSFCell cell;          
          
          
          for(int i=1; i<=sheet.getLastRowNum(); i++)
          {
        	          	  
        	  MemberDataBean userData = new MemberDataBean();
        	  row = sheet.getRow(i);       	  
        	  
        	  errorCode = i;
        	  // Checking Cell Type and Value
        	  
        	  cell=row.getCell((short) 0);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {   
        		  message = new MemberDataMessage();
        		  message = ValidateError("Member Code",i);
        		  break;
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  userData.setMemberCode(String.valueOf(row.getCell((short) 0).getStringCellValue()));
        		  userData.setBranchCode(branchID); 
        		  
        		  message = new MemberDataMessage();
        		  message = ss.getCheckMemberCode(userData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Member Code Already Exists in Table",i);
            		  break;        			  
        		  }

        		  if(!set.add(userData.getMemberCode())) {     // For Checking Duplicate Access_No in Excel
        			  message = new MemberDataMessage();
            		  message = ValidateError("Member Code Already Exists in Excel",i);
            		  break; 
        		  }
        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setMemberCode(String.valueOf((long)row.getCell((short) 0).getNumericCellValue()));
        		  
        		  message = new MemberDataMessage();
        		  message = ss.getCheckMemberCode(userData);
        		  
        		  if(message.getCount() > 0)
        		  {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Member Code Already Exists in Table",i);
            		  break;        			  
        		  }
        		  
        		  if(!set.add(userData.getMemberCode())) {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Member Code Already Exists in Excel",i);
            		  break; 
        		  }
        	  }
        	  else	  {  
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Member Code:"+i);
        	  }
        	  
        	  
        	  
        	  cell=row.getCell((short) 1);        	    
        		  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		 		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Member Name",i);
        		  break;
              }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setMemberName(String.valueOf(row.getCell((short) 1).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setMemberName(String.valueOf((long)row.getCell((short) 1).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in MemberName:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 2);       	  // For Checking Date
              
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Birth_Date",i);
        		  break;        		  
              }        	  
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  { 
        		  message = new MemberDataMessage();
        		  message = ValidateError("Birth_Date is Invalid.The Format is dd/mm/yyyy",i);
        		  break;        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {        		  
        		  try  {
        		  if (HSSFDateUtil.isCellDateFormatted(cell))
            	  {
        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		    
        			    SimpleDateFormat receivedDate = new SimpleDateFormat("yyyy-MM-dd");
        			    String rdate = receivedDate.format(date);
        			    userData.setBirthDate(rdate);  
        			    
            	  }else {
            		  message = new MemberDataMessage();
            		  message = ValidateError("Birth_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib Birth_Date Error ^^^"+e);
        			  message = new MemberDataMessage();
            		  message = ValidateError("Birth_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
        		  }
        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Birth_Date:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 3);       	  // For Checking Date
              
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Enroll_Date",i);
        		  break;        		  
              }        	  
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  { 
        		  message = new MemberDataMessage();
        		  message = ValidateError("Enroll_Date is Invalid.The Format is dd/mm/yyyy",i);
        		  break;        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {        		  
        		  try  {
        		  if (HSSFDateUtil.isCellDateFormatted(cell))
            	  {
        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		    
        			    SimpleDateFormat receivedDate = new SimpleDateFormat("yyyy-MM-dd");
        			    String rdate = receivedDate.format(date);
        			    userData.setEnrollDate(rdate);  
        			    
            	  }else {
            		  message = new MemberDataMessage();
            		  message = ValidateError("Enroll_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib Enroll_Date Error ^^^"+e);
        			  message = new MemberDataMessage();
            		  message = ValidateError("Enroll_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
        		  }
        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Birth_Date:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 4);       	  // For Checking Date
              
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {        		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Expiry_Date",i);
        		  break;        		  
              }        	  
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  /**  userData.setReceiveDate(String.valueOf(row.getCell((short) 4).getStringCellValue()));*/
        		  message = new MemberDataMessage();
        		  message = ValidateError("Expiry_Date is Invalid.The Format is dd/mm/yyyy",i);
        		  break;        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		 /** userData.setReceiveDate(String.valueOf((long)row.getCell((short) 4).getNumericCellValue())); */
        		  
        		  try  {
        		  if (HSSFDateUtil.isCellDateFormatted(cell))
            	  {
        			    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
		    
        			    SimpleDateFormat receivedDate = new SimpleDateFormat("yyyy-MM-dd");
        			    String rdate = receivedDate.format(date);
        			    userData.setExpiryDate(rdate);  
        			    
            	  }else {
            		  message = new MemberDataMessage();
            		  message = ValidateError("Expiry_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
            	  } 
        		  }catch(Exception e)		  {
        			  log4jLogger.info("^^^ AutoLib Expiry_Date Error ^^^"+e);
        			  message = new MemberDataMessage();
            		  message = ValidateError("Expiry_Date is Invalid.The Format is dd/mm/yyyy",i);
            		  break;
        		  }
        		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Expiry_Date:"+i);
        	  }

        	  
        	  
              cell=row.getCell((short) 5);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  message = new MemberDataMessage();
        		  message = ValidateError("Division",i);
        		  break;        		  
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setBranch(String.valueOf(row.getCell((short) 5).getStringCellValue()));        		  
        		 
        		  int count = ss.getBranchCode(userData.getBranch());
        		  
        		 // int count = branchID;
        		  
        		  
        		  
        		  if(count == 0)
        		  {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Division Not Exists in Table. Create First.",i);
            		  break;        			  
        		  }
        		  
        		  if(branchID > 0)
        		  {
        			  if(branchID!=count)
        			  {
        				  message = new MemberDataMessage();
                		  message = ValidateError("You can't upload other division member details.",i);
                		  break;
        			  }
        		  }
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setBranch(String.valueOf((long)row.getCell((short) 5).getNumericCellValue()));

        		  message = new MemberDataMessage();
        		  message = ValidateError("Division is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Division:"+i);
        	  }

        	  
        	  
              cell=row.getCell((short) 6);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  message = new MemberDataMessage();
        		  message = ValidateError("Group / Category",i);
        		  break;
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setGroup(String.valueOf(row.getCell((short) 6).getStringCellValue()));  
        		 int branch = ss.getBranchCode(userData.getBranch());
        		  
        		 //int branch = branchID;
        		  
                  int count = ss.getGroupCode(userData.getGroup(),branch);
        		  
        		  if(count == 0)
        		  {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Group Not Exists in Table. Create First.",i);
            		  break;        			  
        		  }
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setGroup(String.valueOf((long)row.getCell((short) 6).getNumericCellValue()));
        		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Group / Category is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Group / Category:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 7);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setSex("MALE");
              }
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setSex(String.valueOf(row.getCell((short) 7).getStringCellValue()));
        		  
        		  if(userData.getSex().equalsIgnoreCase("MALE"))
        		  {        	
        			  userData.setSex("MALE");
        		  }
        		  else if(userData.getSex().equalsIgnoreCase("FEMALE"))
        		  {   
        			  userData.setSex("FEMALE");
        		  }else  {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Sex is Invalid",i);
            		  break;
        		  }
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setSex(String.valueOf((long)row.getCell((short) 7).getNumericCellValue()));
        		 
        		  message = new MemberDataMessage();
        		  message = ValidateError("Sex is Invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Sex:"+i);
        	  }        	  
        	  
        	  
        	  
              cell=row.getCell((short) 8);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		 /** message = new MemberDataMessage();
        		  message = ValidateError("Subject",i);
        		  break;*/
        		  userData.setDesignation("OTHERS");
        		  //userData.setDesigCode(1);
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {        		  
        		  userData.setDesignation(String.valueOf(row.getCell((short) 8).getStringCellValue())); 
        		  if(userData.getDesignation().equalsIgnoreCase("OTHERS1"))
        		  {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Designation as OTHERS not allowed ",i);
            		  break;
        		  }
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setDesignation(String.valueOf((long)row.getCell((short) 8).getNumericCellValue()));		  
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Designation:"+i);
        	  }        	  
        	  
        	  
        	  
              cell=row.getCell((short) 9);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  /**message = new MemberDataMessage();
        		  message = ValidateError("Department",i);
        		  break;*/
        		  userData.setDepartment("OTHERS");
        		  //userData.setDepartmentCode(1);
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setDepartment(String.valueOf(row.getCell((short) 9).getStringCellValue()));  
        		  if(userData.getDepartment().equalsIgnoreCase("OTHERS1"))
        		  {
        			  message = new MemberDataMessage();
            		  message = ValidateError("Department as OTHERS not allowed ",i);
            		  break;
        		  }
        		  /**int dept = cs.getBookDeptCode(userData.getDepartment());
        		  userData.setDepartmentCode(dept);*/
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setDepartment(String.valueOf((long)row.getCell((short) 9).getNumericCellValue()));
        		  /**int dept = cs.getBookDeptCode(userData.getDepartment());
        		  userData.setDepartmentCode(dept);*/
        		  message = new MemberDataMessage();
        		  message = ValidateError("Department is Invalid",i);
        		  break; 
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Department:"+i);
        	  }     
        	  
        	  

    	      
        	  
        	  cell=row.getCell((short) 10);       	  
                	  
                	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
                      {
                		  userData.setCourseCode(2);
                		  userData.setCourse("OTHERS-OTHERS");
                      }              
                	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
                	  {
                		  userData.setCourse(String.valueOf(row.getCell((short) 10).getStringCellValue())); 
                		  
                		    String[] parts = userData.getCourse().split("-");
                		    if(parts.length < 2)
                		    {
                		      message = new MemberDataMessage();
                      		  message = ValidateError("Course Name is Invalid. For Example:BE-CSE",i);
                      		  break;
                		    }
                	  }
                	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                	  {
                		  userData.setCourse(String.valueOf((long)row.getCell((short) 10).getNumericCellValue()));

                		  message = new MemberDataMessage();
                		  message = ValidateError("Course Name is Invalid",i);
                		  break; 
                	  }
                	  else	  {
                		  log4jLogger.info(">>>>>>>>>>>>>Error in Course Name:"+i);
                	  }  
                	  
                	  
                	 cell=row.getCell((short) 11);       	  // For Checking Year like first year or sec year etc....
                	 
                	 if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
                     {
               		  userData.setCYear("");
                     }              
               	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
               	  {
               		  userData.setCYear(String.valueOf(row.getCell((short) 11).getStringCellValue())); 
               		  message = new MemberDataMessage();
               		  message = ValidateError("Member year is invalid",i);
               		  break;
               	  }
               	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
               	  {
               		  if(row.getCell((short) 11).getNumericCellValue()>6){
               			 message = new MemberDataMessage();
                  		  message = ValidateError("Member year is invalid",i);
                  		  break;
               		  }else{
               			  userData.setCYear(String.valueOf((long)row.getCell((short) 11).getNumericCellValue()));
               		  }
               		}
               	  else {
               		  log4jLogger.info(">>>>>>>>>>>>>Error in Member Year:"+i);
               	  } 


        			            	  
                	  
        	  
        	  
        	  
        	  
        	  
        	  
        	  
        	          	  
                      	  
              cell=row.getCell((short) 12);       	  // For Checking Deposit Amount
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setDepositAmt(0.0); 
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setDepositAmt(0.0); 
        		  message = new MemberDataMessage();
        		  message = ValidateError("Deposit Amount is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setDepositAmt((double)row.getCell((short) 12).getNumericCellValue());
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Deposit Amount:"+i);
        	  }             	  
        	  
        	         	  
        	  
              cell=row.getCell((short) 13);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setAddress("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setAddress(String.valueOf(row.getCell((short) 13).getStringCellValue()));          		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setAddress(String.valueOf((long)row.getCell((short) 13).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Address:"+i);
        	  }     
        	          	  
        	  
        	  
              cell=row.getCell((short) 14);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setCity("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setCity(String.valueOf(row.getCell((short) 14).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setCity(String.valueOf((long)row.getCell((short) 14).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in user City:"+i);
        	  }             	  
        	  
        	          	
        	  
              cell=row.getCell((short) 15);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setState("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setState(String.valueOf(row.getCell((short) 15).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setState(String.valueOf((long)row.getCell((short) 15).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in State:"+i);
        	  }  
        	  
        	  
        	  
              cell=row.getCell((short) 16);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setPincode("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setPincode(String.valueOf(row.getCell((short) 16).getStringCellValue())); 
        		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Pincode is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setPincode(String.valueOf((long)row.getCell((short) 16).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in user Pincode:"+i);
        	  }  
        	  
        	  
        	  
              cell=row.getCell((short) 17);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setPhoneNo("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setPhoneNo(String.valueOf(row.getCell((short) 17).getStringCellValue()));   
        		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Phone number is invalid",i);
        		  break;
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setPhoneNo(String.valueOf((long)row.getCell((short) 17).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in PhoneNo:"+i);
        	  }  
        	  
        	  
        	  
              cell=row.getCell((short) 18);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setEmail("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setEmail(String.valueOf(row.getCell((short) 18).getStringCellValue()));        		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setEmail(String.valueOf((long)row.getCell((short) 18).getNumericCellValue()));
        		  
        		  message = new MemberDataMessage();
        		  message = ValidateError("Email ID is invalid",i);
        		  break;
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Email ID:"+i);
        	  }
        	  
        	  
        	  
              cell=row.getCell((short) 19);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setRemarks("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setRemarks(String.valueOf(row.getCell((short) 19).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setRemarks(String.valueOf((long)row.getCell((short) 19).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Remarks:"+i);
        	  } 
        	  
        	  
        	  cell=row.getCell((short) 20);       	  
        	  
        	  if ((cell == null) || (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK))
              {
        		  userData.setProfile("");
              }              
        	  else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
        	  {
        		  userData.setProfile(String.valueOf(row.getCell((short) 20).getStringCellValue()));         		  
        	  }
        	  else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        	  {
        		  userData.setProfile(String.valueOf((long)row.getCell((short) 20).getNumericCellValue()));
        	  }
        	  else	  {
        		  log4jLogger.info(">>>>>>>>>>>>>Error in Profile:"+i);
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
	  message = new MemberDataMessage();
	  message = ValidateError("Invalid Row ",errorCode);
} catch (Exception e) { 
	log4jLogger.info("AutoLibError:OtherException:"+e);
}
finally{
	set.clear();
}
      
  if(errorException.equals("InvalidFile"))
  {
	    indexPage ="/Member_Import/index.jsp?check=InvalidFile";
  }else {
        	
    if(!message.getErrorMsg().isEmpty() && message.getErrorMsg() != null)
    {    	
    	request.setAttribute("bean",message);
    	indexPage ="/Member_Import/index.jsp?check=dataerror";
    	
    }else
    {
    	if(saveDetail != null && !saveDetail.isEmpty())
    	{
    		int save = ss.getImportMemberData(saveDetail);
    		indexPage ="/Member_Import/index.jsp?check=success";
    	}else
    	{
    		indexPage ="/Member_Import/index.jsp?check=ErrorToSave";
    	}
    }
  }   
    
    dispatch(request, response, indexPage);
    
    
    System.out.println("indexPage::::::::::::::::"+indexPage);
    
    
      
   }catch(Exception ex) {
	   indexPage ="/Member_Import/index.jsp?check=LargeFile";
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
   
   
   
   
   
   private void employeeService(MemberDataBean employee)
   {
	   log4jLogger.info("=======Inside employeeService=========="+employee.getMemberCode());
   	
   	try{               	  
   	       Class.forName("com.mysql.jdbc.Driver").newInstance();
   	       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/autolib","root","admin");
   	       Statement stat=con.createStatement();       
   	 
  	       
   	       int k=stat.executeUpdate("insert into excel values('"+employee.getMemberCode()+"','"+employee.getMemberName()+"','"+employee.getBranch()+"')");
   	       log4jLogger.info("Data is inserted");
   	       stat.close();
   	       con.close();
   	   }
   	   catch(Exception e){	  
   		log4jLogger.info("AutoLibError:DBConnect:"+e);
   	   }   	
   }
   
   
   public MemberDataMessage ValidateError(String error, int i)
   {	   
	   log4jLogger.info("ValidateError:Error:"+error+" Row:"+i);
	   MemberDataMessage message = new MemberDataMessage();	   
	   
	   message.setErrorMsg("Error : "+error+" at Row No: "+(i+1));	   
	   return message;
	   
   }
   
   
   
    
}