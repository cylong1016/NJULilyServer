package data.cashbilldata;

import java.rmi.RemoteException;

import po.CashBillPO;
import data.TableInfo;
import dataservice.cashbilldataservice.CashBillInfoService;

/**
 * @see dataservice.cashbilldataservice.CashBillInfoService
 * @author cylong
 * @version 2014年12月2日 上午3:25:43
 */
public class CashBillInfo extends TableInfo<CashBillPO> implements CashBillInfoService {

	/** serialVersionUID */
	private static final long serialVersionUID = -2613556257030674855L;

	private CashBillData cashBillData;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午3:58:59
	 */
	public CashBillInfo() throws RemoteException {
		super();
	}

	/**
	 * @see data.TableInfo#initPOs()
	 */
	@Override
	protected void initPOs() throws RemoteException {
		cashBillData = new CashBillData();
		pos = cashBillData.show();
	}

}
