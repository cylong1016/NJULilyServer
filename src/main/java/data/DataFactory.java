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
import dataservice.accountbilldataservice.AccountBillDataService;
import dataservice.accountdataservice.AccountDataService;
import dataservice.accountinitdataservice.AccountaInitDataService;
import dataservice.cashbilldataservice.CashBillDataService;
import dataservice.clientdataservice.ClientDataService;
import dataservice.commoditydataservice.CommodityDataService;
import dataservice.commoditysortdataservice.CommoditySortDataService;
import dataservice.inventorydataservice.InventoryDataService;
import dataservice.promotiondataservice.PromotionDataService;
import dataservice.purchasedataservice.PurchaseDataService;
import dataservice.saledataservice.SaleDataService;
import dataservice.userdataservice.UserDataService;

/**
 * 工厂，返回数据层模块接口
 * @author cylong
 * @version Nov 2, 2014 5:09:28 PM
 */
public class DataFactory {

	public static AccountaInitDataService getAccountaInitData() {
		try {
			return new AccountInitData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static AccountDataService getAccountData() {
		try {
			return new AccountData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static ClientDataService getClientData() {
		try {
			return new ClientData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static CommodityDataService getCommodityData() {
		try {
			return new CommodityData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static CommoditySortDataService getCommoditySortData() {
		try {
			return new CommoditySortData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static AccountBillDataService getAccountBillData() {
		try {
			return new AccountBillData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static InventoryDataService getInventoryData() {
		try {
			return new InventoryData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PromotionDataService getPromotionData() {
		try {
			return new PromotionData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static PurchaseDataService getPurchaseData() {
		try {
			return new PurchaseData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static SaleDataService getSaleData() {
		try {
			return new SaleData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static UserDataService getUserData() {
		try {
			return new UserData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static CashBillDataService getCashBillData() {
		try {
			return new CashBillData();
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

}
