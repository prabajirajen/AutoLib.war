package Common.businessutil.rfid;

import Lib.Auto.Counter.CounterBean;

public interface RFIDService {
	public int MergeTag(CounterBean ob);
	public int MergeCard(CounterBean ob);
	public String tagtitle(CounterBean ob);
	public String CardMname(CounterBean ob);
	public int MergeTagUpdate(CounterBean ob);
}
