package data;

import java.rmi.RemoteException;

import data.accountbilldata.AccountBillData;
import data.accountdata.AccountData;
import data.accountinitdata.AccountInitData;
import data.cashbilldata.CashBillData;
import data.clientdata.ClientData;
import data.commoditydata.CommodityData;
import data.commoditysortdata.CommoditySortData;
import data.inventorydata.InventoryData;
import data.promotiondata.PromotionData;
import data.purchasedata.PurchaseData;
import data.saledata.SaleData;
import data.userdata.UserData;
import dataservice.DataService;

/**
 * 工厂，返回数据层模块接口
 * @author cylong
 * @version Nov 2, 2014 5:09:28 PM
 */
public class DataFactory {

	/**
	 * 注意：在jdk1.7之后的版本才支持case String
	 * @param name data的名字
	 * @return DataService的子类实现
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午6:48:30
	 */
	public static DataService<?> createDataService(String name) throws RemoteException {
		switch(name) {
		case AccountInitData.NAME:
			return new AccountInitData();
		case AccountData.NAME:
			return new AccountData();
		case ClientData.NAME:
			return new ClientData();
		case CommodityData.NAME:
			return new CommodityData();
		case CommoditySortData.NAME:
			return new CommoditySortData();
		case AccountBillData.NAME:
			return new AccountBillData();
		case InventoryData.NAME:
			return new InventoryData();
		case PromotionData.NAME:
			return new PromotionData();
		case PurchaseData.NAME:
			return new PurchaseData();
		case SaleData.NAME:
			return new SaleData();
		case UserData.NAME:
			return new UserData();
		case CashBillData.NAME:
			return new CashBillData();
		default:
			return null;
		}
	}

}
