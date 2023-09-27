package Common.businessutil.rfid;

import Lib.Auto.Counter.CounterBean;

import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.OperationFailureException;

public interface RFIDDao {

	public int findMergeTag(CounterBean ob);
	public int findMergeCard(CounterBean ob);
	public String findtagtitle(CounterBean ob);
	public String findCardMname(CounterBean ob);
	public int findMergeTagUpdate(CounterBean ob);
}
