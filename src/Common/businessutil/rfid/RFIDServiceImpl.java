package Common.businessutil.rfid;

import com.mot.rfid.api3.InvalidUsageException;
import com.mot.rfid.api3.OperationFailureException;

import Common.businessutil.circulation.CirculationDao;
import Common.businessutil.sms.SmsDao;
import Lib.Auto.Counter.CounterBean;

public class RFIDServiceImpl implements RFIDService{
	private RFIDDao rfiddao;

	public int MergeTag(CounterBean ob) {
		return rfiddao.findMergeTag(ob);
	}
	public int MergeCard(CounterBean ob) {
		return rfiddao.findMergeCard(ob);
	}
	public String tagtitle(CounterBean ob) {
		return rfiddao.findtagtitle(ob);
	}
	public String CardMname(CounterBean ob) {
		return rfiddao.findCardMname(ob);
	}
	public void setRFIDDao(RFIDDao rfiddao){
		this.rfiddao=rfiddao;
	}
	public int MergeTagUpdate(CounterBean ob) {
		return rfiddao.findMergeTagUpdate(ob);
	}
}
