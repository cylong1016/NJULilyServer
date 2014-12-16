package data.accountbilldata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.AccountBillPO;
import data.TableInfo;
import dataenum.BillType;
import dataservice.accountbilldataservice.AccountBillInfoService;

/**
 * @see dataservice.accountbilldataservice.AccountBillInfoService
 * @author cylong
 * @version 2014年12月2日 上午3:24:03
 */
public class AccountBillInfo extends TableInfo<AccountBillPO> implements AccountBillInfoService {

	/** serialVersionUID */
	private static final long serialVersionUID = 6489453111015622011L;

	private AccountBillData accountBillData;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午4:01:45
	 */
	public AccountBillInfo() throws RemoteException {
		super();
	}

	/**
	 * @see data.TableInfo#initPOs()
	 */
	@Override
	protected void initPOs() throws RemoteException {
		accountBillData = new AccountBillData();
		pos = accountBillData.show();
	}

	/**
	 * @see data.TableInfo#getClient(java.lang.String)
	 */
	@Override
	public String getClient(String billID) throws RemoteException {
		return accountBillData.find(billID).getClientID();
	}

	@Override
	public ArrayList<String> getAllID(BillType type) throws RemoteException {
		ArrayList<String> IDs = new ArrayList<String>();
		for(AccountBillPO po : pos) {
			if (po.getType().equals(type)) {
				IDs.add(po.getID());
			}
		}
		return IDs;
	}

}
