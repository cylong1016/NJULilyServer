package data.accountbilldata;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.AccountBillPO;

import common.ParseXML;

import data.CommonData;
import dataenum.BillType;
import dataservice.accountbilldataservice.AccountBillDataService;

/**
 * @author cylong
 * @version 2014年11月30日 下午10:50:32
 */
public class AccountBillData extends CommonData<AccountBillPO> implements AccountBillDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = 4334794364186421056L;

	/** SKD */
	private String expenseID;
	/** FKD */
	private String payID;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午4:02:08
	 */
	public AccountBillData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML(NAME);
		expenseID = parsexml.getValue("expenseID");
		payID = parsexml.getValue("payID");
		dateRecord = parsexml.getValue("dateRecord");
		String dateFormat = parsexml.getValue("dateFormat");
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		currentDate = sdf.format(new Date());
	}

	/**
	 * 根据单据类型获得编号前缀
	 * @param type 单据类型
	 * @return ID前缀
	 * @author cylong
	 * @version 2014年12月2日 上午5:36:32
	 */
	@Override
	// TODO getID未分开
	protected String getPreID(BillType type) {
		switch(type) {
		case EXPENSE:
			return expenseID + "-" + currentDate + "-";
		case PAY:
			return payID + "-" + currentDate + "-";
		default:
			return null;
		}
	}

	/**
	 * @see dataservice.accountbilldataservice.AccountBillDataService#show(dataenum.BillType)
	 */
	@Override
	public ArrayList<AccountBillPO> show(BillType type) throws RemoteException {
		ArrayList<AccountBillPO> bills = new ArrayList<AccountBillPO>();
		for(AccountBillPO po : poList.getInList()) {
			if (po.getType().equals(type)) {
				bills.add(po);
			}
		}
		return bills;
	}

}
