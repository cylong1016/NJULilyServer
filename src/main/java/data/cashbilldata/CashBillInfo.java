package data.cashbilldata;

import data.TableInfo;
import po.CashBillPO;

/**
 * @see dataservice.cashbilldataservice.CashBillInfoService
 * @author cylong
 * @version 2014年12月2日 上午3:25:43
 */
public class CashBillInfo extends TableInfo<CashBillPO> {

	private CashBillData cashBillData;

	/**
	 * @see data.TableInfo#initPOs()
	 */
	@Override
	protected void initPOs() {
		cashBillData = new CashBillData();
		pos = cashBillData.show();
	}

}
