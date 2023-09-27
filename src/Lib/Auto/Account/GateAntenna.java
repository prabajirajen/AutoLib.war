package Lib.Auto.Account;

import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Counter.CounterBean;

import com.mot.rfid.api3.*;
public class GateAntenna 
{
	RFIDReader myReader=new RFIDReader();
	TagData[] myTags=null;
	String hostName = "10.240.0.36";
	String accno = "";
    int port = 5084;
	public GateAntenna()
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

            System.out.println("start           -------------");
        

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
	                    short portIndex=1;
	                    GPO_PORT_STATE portState = null;
	                    CounterBean counterbeanobject=new CounterBean();
	                	CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
	                    counterbeanobject=ss.getEpc(key);
	                	accno=counterbeanobject.getAccno();
	                	String availability=ss.getAvailability(accno);
	                	if(!availability.equalsIgnoreCase(""))
	                	{
	                		System.out.println("ISSUED.................");
	                	}
	                	else
	                	{
	            		           	         
	           	        	try {
								myReader.Config.GPO.setPortState(portIndex, portState.TRUE);
							} catch (InvalidUsageException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (OperationFailureException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	           	        	
//	           	        try 
//	           	       	{
//							Thread.sleep(2000);
//						} catch (InterruptedException e) 
//						{
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//	           	        
	           	        try 
	           	        {
							myReader.Config.GPO.setPortState(portIndex, portState.FALSE);
						} catch (InvalidUsageException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (OperationFailureException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	           	        break;
	                	}	                    
	                }
	            }
	        }
			public void eventStatusNotify(RfidStatusEvents arg0) {
				// TODO Auto-generated method stub
				
			}

	        
	    }
	
	

}
