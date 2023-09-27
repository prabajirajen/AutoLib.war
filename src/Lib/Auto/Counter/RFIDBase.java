package Lib.Auto.Counter;
import com.mot.rfid.api3.*;

import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class RFIDBase 
{
	CounterBean counterbeanobject=new CounterBean();
	RFIDReader myReader=new RFIDReader();
	TagData[] myTags=null;
	String hostName = "10.240.0.35";
   	int port = 5084;
	public RFIDBase()
    {
        myReader = new RFIDReader();
    }
	
	
	public void connect() throws InvalidUsageException, OperationFailureException
	{
	
	        myReader.setHostName(hostName);
	        myReader.setPort(port);
	        
	        try {
	        	
	            myReader.connect();
	            
	            myReader.Events.setInventoryStartEvent(true);
	            myReader.Events.setInventoryStopEvent(true);
	            myReader.Events.setAccessStartEvent(true);
	            myReader.Events.setAccessStopEvent(true);
	            myReader.Events.setAntennaEvent(true);
	            myReader.Events.setGPIEvent(true);
	            myReader.Events.setBufferFullEvent(true);
	            myReader.Events.setBufferFullWarningEvent(true);
	            myReader.Events.setReaderDisconnectEvent(true);
	            myReader.Events.setReaderExceptionEvent(true);
	            myReader.Events.setTagReadEvent(true);
	            myReader.Events.setAttachTagDataWithReadEvent(false);
	            myReader.Events.setTemperatureAlarmEvent(true);
	            myReader.Events.addEventsListener(new EventsHandler(myReader));
	            
	            
	        }        
	        catch (InvalidUsageException ex) {

	        } catch (OperationFailureException ex) {

	        }
	       if(myReader.isConnected())
	       {
	    	   System.out.println("Connected");
	       }   
	}
		
	public void startRead()
    {
		
        try {

        	myReader.Actions.purgeTags();
            myReader.Actions.Inventory.perform();

            System.out.println("RFID BASE start-------------");
        

        } catch (InvalidUsageException ex) {
        } catch (OperationFailureException ex) {
        }
        
    }
	
	 public class EventsHandler implements RfidEventsListener
	    {
		 RFIDReader inventoryUI = null;


	        public EventsHandler(RFIDReader rfidSample){
	            inventoryUI = rfidSample;
	        }
	        
	       
			public void eventReadNotify(RfidReadEvents rre) {

	            myTags = myReader.Actions.getReadTags(100);
	            if (myTags != null)
	            {
	                for (int index = 0; index < myTags.length; index++)
	                {
	                    TagData tag = myTags[index];
	                    String key = tag.getTagID();
	                    int range=tag.getPeakRSSI();
	                    short id = tag.getAntennaID();
	                    if(range<=-20 && range>=-50)
	                    {
	                    	if(id==1)
	                    	{
	                    		counterbeanobject.setEpc(key);
			                    System.out.println("Tag id  "+key+"  Antenna ID "+id);
	                    	}
	                    	else if(id==2)
	                    	{
	                    		counterbeanobject.setEpc2(key);
			                    System.out.println("Tag id  "+key+"  Antenna ID "+id);
	                    	}
	                    	else if(id==3)
	                    	{
	                    		counterbeanobject.setEpc3(key);
			                    System.out.println("Tag id  "+key+"  Antenna ID "+id);
	                    	}
	                    	else if(id==4)
	                    	{
	                    		counterbeanobject.setEpc4(key);
			                    System.out.println("Tag id  "+key+"  Antenna ID "+id);
	                    	}
	                    }
	                    
	                }
	            }
	        }
			public void eventStatusNotify(RfidStatusEvents arg0) {
				// TODO Auto-generated method stub
				
			}

	        
	    }
	 
}
