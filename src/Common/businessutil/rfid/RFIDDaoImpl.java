package Common.businessutil.rfid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.xml.ws.WebServiceClient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

import Common.DataQuery;
import Common.Security_Counter;
import Common.businessutil.BusinessServiceFactory;
import Common.businessutil.circulation.CirculationService;
import Lib.Auto.Counter.CounterBean;









import com.library.autolib.portal.dbconnectionutil.BaseDBConnection;
import com.lowagie.text.pdf.codec.Base64.InputStream;
import com.mot.rfid.api3.*;

public class RFIDDaoImpl extends BaseDBConnection implements RFIDDao{
	java.sql.Connection con = null;
	java.sql.PreparedStatement Prest = null;
	java.sql.ResultSet rs = null;
	java.sql.Statement st = null;
	public int findMergeTag(CounterBean ob) 
	{
		int count=0;
		try 
		{
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Select_TAG);
			Prest.setString(1, ob.getEpc_id());
			rs=Prest.executeQuery();
			if(rs.next())
			{
				count=2;
			}
			else
			{
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.INSERT_TAG);
				Prest.setString(1, ob.getEpc_id());
				Prest.setString(2, ob.getAccno());
				Prest.executeUpdate();
				count=1;
			}
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	public int findMergeCard(CounterBean ob) 
	{
		int count=0;
		try 
		{
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Select_CARD);
			Prest.setString(1, ob.getCarduid());
			rs=Prest.executeQuery();
			if(rs.next())
			{
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.Update_CARD);
				Prest.setString(1, ob.getMcode());
				Prest.setString(2, ob.getCarduid());
				Prest.executeUpdate();
				count=1;
			}
			else
			{
				con = getSession().connection();
				Prest = con.prepareStatement(DataQuery.INSERT_CARD);
				Prest.setString(1, ob.getCarduid());
				Prest.setString(2, ob.getMcode());
				Prest.executeUpdate();
				count=1;
			}
			
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public String findtagtitle(CounterBean ob) 
	{
		String count="";
		try 
		{
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Select_tag_title);
			Prest.setString(1, ob.getAccno());
			rs=Prest.executeQuery();
			if(rs.next())
			{
				
				count=rs.getString(1);
			}
			
			
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public String findCardMname(CounterBean ob) 
	{
		String count="";
		try 
		{
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Select_Card_Mname);
			Prest.setString(1, ob.getMcode());
			rs=Prest.executeQuery();
			if(rs.next())
			{
				
				count=rs.getString(1);
			}
			
			
		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	public int findMergeTagUpdate(CounterBean ob) 
	{
		int count=0;
		try 
		{
	
			con = getSession().connection();
			Prest = con.prepareStatement(DataQuery.Update_TAG);
			Prest.setString(1, ob.getAccno());
			Prest.setString(2, ob.getEpc_id());
			Prest.executeUpdate();
			count=1;

		} catch (Exception e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (Prest != null) {
					Prest.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;
	}
}
