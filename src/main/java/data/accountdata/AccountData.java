package data.accountdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import common.ParseXML;
import po.AccountPO;
import data.CommonData;
import dataenum.FindTypeAccount;
import dataenum.ResultMessage;
import dataservice.accountdataservice.AccountDataService;

/**
 * @see dataservice.accountdataservice.AccountDataService
 * @author cylong
 * @version 2014年11月30日 上午10:25:22
 */
public class AccountData extends CommonData<AccountPO> implements AccountDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = 5874376926505168977L;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午4:01:19
	 */
	public AccountData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML(NAME);
		prefix = parsexml.getValue("prefix");
	}

	/**
	 * @see data.CommonData#getID()
	 */
	@Override
	public String getID() throws RemoteException {
		return prefix + super.getID();
	}

	/**
	 * 名称存在就添加失败
	 * @see data.CommonData#insert(po.PersistentObject)
	 */
	@Override
	public ResultMessage insert(AccountPO po) throws RemoteException {
		for(AccountPO temp : poList.getInList()) {
			if (temp.getName().equals(po.getName())) {
				return ResultMessage.FAILURE;
			}
		}
		super.insert(po);
		return ResultMessage.SUCCESS;
	}

	/**
	 * @see dataservice.accountdataservice.AccountDataService#find(java.lang.String, dataenum.FindTypeAccount)
	 */
	@Override
	public ArrayList<AccountPO> find(String keywords, FindTypeAccount type) throws RemoteException {
		keywords = keywords.toLowerCase(); // 为了不区分大小写
		ArrayList<AccountPO> accounts = new ArrayList<AccountPO>();
		if (type == null) {	// 查找账户全部信息
			for(AccountPO account : poList.getInList()) {
				if (account.toString().toLowerCase().contains(keywords)) {
					accounts.add(account);
				}
			}
			return accounts;
		}
		switch(type) {
		case ID:
			for(AccountPO account : poList.getInList()) {
				if (account.getID().toLowerCase().contains(keywords)) {
					accounts.add(account);
				}
			}
			break;
		case MONEY:
			for(AccountPO account : poList.getInList()) {
				if (Double.toString(account.getMoney()).contains(keywords)) {
					accounts.add(account);
				}
			}
			break;
		case NAME:
			for(AccountPO account : poList.getInList()) {
				if (account.getName().toLowerCase().contains(keywords)) {
					accounts.add(account);
				}
			}
			break;
		default:
			break;
		}
		return accounts;
	}
}
