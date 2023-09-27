package Lib.Auto.Counter;
import java.io.*; 
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*; 
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

import org.apache.log4j.Logger;









//import com.google.gson.Gson;


import Common.Security;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;

public class RFIDServlet extends HttpServlet implements Serializable
{
	private static Logger log4jLogger = Logger.getLogger(RFIDServlet.class);
	String indexPage = null;
	String flag = "",antenaid="",accno="",mcode="";
 
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		performTask(request, response);
	
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		performTask(request, response);
	
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try
		{
			HttpSession session = request.getSession(true);
			String res = Security.checkSecurity(1, session, response, request);		
			if(res.equalsIgnoreCase("Failure"))
			{
				response.sendRedirect("/AutoLib/Tree/sessiontimeout.jsp");  
				return;
			}
			flag = request.getParameter("flag");
			antenaid = request.getParameter("antenaid");
			String TID=request.getParameter("antennaid");
			request.setAttribute("AID",TID);
			CirculationService ss = BusinessServiceFactory.INSTANCE.getCirculationService();
			CounterBean counterbeanobject=new CounterBean();
			PrintWriter out = response.getWriter();
			int i=0,range=0;
			String key="";
			short id = 0;
			log4jLogger.info("RFIDConnection Start !!!!!!!!!!!!!!!!!"+antenaid);
			log4jLogger.info("RFIDConnection flag !!!!!!!!!!!!!!!!!"+flag);
		    	    
			if(flag.equalsIgnoreCase("RFIDRead") && antenaid.equalsIgnoreCase("1"))
			{
				counterbeanobject.setEpc("");
				while(true)
				{
				                	if(!counterbeanobject.getEpc().equalsIgnoreCase(""))
				                	{
				                		counterbeanobject=ss.getEpc(counterbeanobject.getEpc());
				                		accno=counterbeanobject.getAccno();
				                		counterbeanobject.setEpc("");
					                	out.println(accno);
					                	break;
				                	}
				}
			}
			if(flag.equalsIgnoreCase("RFIDRead") && antenaid.equalsIgnoreCase("2"))
			{
				counterbeanobject.setEpc2("");
				while(true)
				{
				                	if(!counterbeanobject.getEpc2().equalsIgnoreCase(""))
				                	{
				                		counterbeanobject=ss.getEpc(counterbeanobject.getEpc2());
				                		accno=counterbeanobject.getAccno();
				                		counterbeanobject.setEpc2("");
					                	out.println(accno);
					                	break;
				                	}
				}
			}
			
			if(flag.equalsIgnoreCase("RFIDRead") && antenaid.equalsIgnoreCase("3"))
			{
				counterbeanobject.setEpc3("");
				while(true)
				{
				                	
				                	if(!counterbeanobject.getEpc3().equalsIgnoreCase(""))
				                	{
				                		counterbeanobject=ss.getEpc(counterbeanobject.getEpc3());
				                		accno=counterbeanobject.getAccno();
				                		counterbeanobject.setEpc3("");
					                	out.println(accno);
					                	break;
				                	}
				}			
			}
			
			if(flag.equalsIgnoreCase("RFIDRead") && antenaid.equalsIgnoreCase("4"))
			{
				counterbeanobject.setEpc4("");
				while(true)
				{
				                	
				                	if(!counterbeanobject.getEpc4().equalsIgnoreCase(""))
				                	{
				                		counterbeanobject=ss.getEpc(counterbeanobject.getEpc4());
				                		accno=counterbeanobject.getAccno();
				                		counterbeanobject.setEpc4("");
					                	out.println(accno);
					                	break;
				                	}
				}
			}
		
			
			
			if(flag.equalsIgnoreCase("TagMerge") && antenaid.equalsIgnoreCase("1"))
			{
				counterbeanobject.setEpc("");
				while(true)
				{
				                	
				                	if(!counterbeanobject.getEpc().equalsIgnoreCase(""))
				                	{
				                		key=counterbeanobject.getEpc();
				                		counterbeanobject.setEpc("");
					                	out.println(key);
					                	break;
				                	}
				}
			}
			
			
			if(flag.equalsIgnoreCase("TagMerge") && antenaid.equalsIgnoreCase("2"))
			{
				counterbeanobject.setEpc2("");
				while(true)
				{
				                	
				                	if(!counterbeanobject.getEpc2().equalsIgnoreCase(""))
				                	{
				                		key=counterbeanobject.getEpc2();
				                		counterbeanobject.setEpc("");
					                	out.println(key);
					                	break;
				                	}
				}
			}
			
			if(flag.equalsIgnoreCase("TagMerge") && antenaid.equalsIgnoreCase("3"))
			{
				counterbeanobject.setEpc3("");
				while(true)
				{
				                	
				                	if(!counterbeanobject.getEpc3().equalsIgnoreCase(""))
				                	{
				                		key=counterbeanobject.getEpc3();
				                		counterbeanobject.setEpc("");
					                	out.println(key);
					                	break;
				                	}
				}
			}
			
			if(flag.equalsIgnoreCase("TagMerge") && antenaid.equalsIgnoreCase("4"))
			{
				counterbeanobject.setEpc4("");
				while(true)
				{
				                	
				                	if(!counterbeanobject.getEpc4().equalsIgnoreCase(""))
				                	{
				                		key=counterbeanobject.getEpc4();
				                		counterbeanobject.setEpc("");
					                	out.println(key);
					                	break;
				                	}
				}
			}
			
			if(flag.equalsIgnoreCase("CardUID"))
			{


			    try {
			        CardTerminal terminal = null;

			        // show the list of available terminals
			        TerminalFactory factory = TerminalFactory.getDefault();
			        List<CardTerminal> terminals = factory.terminals().list();
			        String readerName = "";

			        for (i = 0; i < terminals.size(); i++) {

			            readerName = terminals.get(i).toString()
			                    .substring(terminals.get(i).toString().length() - 2);
			            //terminal = terminals.get(i);

			            if (readerName.equalsIgnoreCase(" 0")) {
			                terminal = terminals.get(i);
			            }
			        }

			        // Establish a connection with the card
			        System.out.println("Waiting for a card..");

			        if(terminal==null)
			            return;
			        terminal.waitForCardPresent(0);

			        Card card = terminal.connect("*");
			        CardChannel channel = card.getBasicChannel();

			        // Start with something simple, read UID
			        byte[] baReadUID = new byte[5];

			        baReadUID = new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00,
			                (byte) 0x00, (byte) 0x00 };

			       
			        // If successfull, the output will end with 9000

			        // OK, now, the real work


			        String UID = "";

			        byte[] baResp = new byte[258];
			        ByteBuffer bufCmd = ByteBuffer.wrap(baReadUID);
			        ByteBuffer bufResp = ByteBuffer.wrap(baResp);

			        // output = The length of the received response APDU
			        int output = 0;

			        try {


			    output = channel.transmit(bufCmd, bufResp);
			        } catch (CardException ex) {
			            
			        }

			        for (i = 0; i < output-2; i++) {  //here -2 aviods iteration of 9000(bufresp) becoz 9000 means successfully read card uid, if you remove -2 you can undesrtand
			        	UID += String.format("%02X", baResp[i]);
			            
			            // The result is formatted as a hexadecimal integer
			        }
			        if(UID!="")
			        {
			        	System.out.println("UID: " + UID);
			        	counterbeanobject=ss.getCardUID(UID);
			        	mcode=counterbeanobject.getMcode();
			        	out.println(mcode);
			        }
			        
			    } catch (Exception ex) {
			        
			    }

			}
			
			
			if(flag.equalsIgnoreCase("CardMerge"))
			{


			    try {
			        CardTerminal terminal = null;

			        // show the list of available terminals
			        TerminalFactory factory = TerminalFactory.getDefault();
			        List<CardTerminal> terminals = factory.terminals().list();
			        String readerName = "";

			        for (i = 0; i < terminals.size(); i++) {

			            readerName = terminals.get(i).toString()
			                    .substring(terminals.get(i).toString().length() - 2);
			            //terminal = terminals.get(i);

			            if (readerName.equalsIgnoreCase(" 0")) {
			                terminal = terminals.get(i);
			            }
			        }

			        // Establish a connection with the card
			        System.out.println("Waiting for a card..");

			        if(terminal==null)
			            return;
			        terminal.waitForCardPresent(0);

			        Card card = terminal.connect("*");
			        CardChannel channel = card.getBasicChannel();

			        // Start with something simple, read UID
			        byte[] baReadUID = new byte[5];

			        baReadUID = new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00,
			                (byte) 0x00, (byte) 0x00 };

			       
			        // If successfull, the output will end with 9000

			        // OK, now, the real work


			        String UID = "";

			        byte[] baResp = new byte[258];
			        ByteBuffer bufCmd = ByteBuffer.wrap(baReadUID);
			        ByteBuffer bufResp = ByteBuffer.wrap(baResp);

			        // output = The length of the received response APDU
			        int output = 0;

			        try {


			    output = channel.transmit(bufCmd, bufResp);
			        } catch (CardException ex) {
			            
			        }

			        for (i = 0; i < output-2; i++) {  //here -2 aviods iteration of 9000(bufresp) becoz 9000 means successfully read card uid, if you remove -2 you can undesrtand
			        	UID += String.format("%02X", baResp[i]);
			            
			            // The result is formatted as a hexadecimal integer
			        }
			        if(UID!="")
			        {
			        	System.out.println("UID: " + UID);
			        	out.println(UID);
			        	
			        }
			        
			    } catch (Exception ex) {
			        
			    }

			}
			
	
		}
		catch (Exception e) {
			throw new ServletException(e);
		} 
	}

	public void dispatch(HttpServletRequest request,
			HttpServletResponse response, String indexPage)
			throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(indexPage);
		dispatch.forward(request, response);
	}


} 