package data;

import java.rmi.RemoteException;

import data.accountbilldata.AccountBillInfo;
import data.cashbilldata.CashBillInfo;
import data.inventorydata.InventoryInfo;
import data.purchasedata.PurchaseInfo;
import data.saledata.SaleInfo;
import dataservice.TableInfoService;

/**
 * 提供TableInfo的实例
 * @author cylong
 * @version 2014年12月14日 上午6:10:53
 */
public class InfoFactory {

	/**
	 * 注意：在jdk1.7之后的版本才支持case String
	 * @param name info的name
	 * @return 对应的TableInfoService实例
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午6:54:33
	 */
	public static TableInfoService<?> createInfoService(String name) throws RemoteException {
		switch(name) {
		case SaleInfo.NAME:
			return new SaleInfo();
		case PurchaseInfo.NAME:
			return new PurchaseInfo();
		case InventoryInfo.NAME:
			return new InventoryInfo();
		case CashBillInfo.NAME:
			return new CashBillInfo();
		case AccountBillInfo.NAME:
			return new AccountBillInfo();
		default:
			return null;
		}
	}
}
