package server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import config.RMIConfig;
import data.DataFactory;
import data.InfoFactory;
import data.accountbilldata.AccountBillData;
import data.accountbilldata.AccountBillInfo;
import data.accountdata.AccountData;
import data.accountinitdata.AccountInitData;
import data.cashbilldata.CashBillData;
import data.cashbilldata.CashBillInfo;
import data.clientdata.ClientData;
import data.commoditydata.CommodityData;
import data.commoditysortdata.CommoditySortData;
import data.inventorydata.InventoryData;
import data.inventorydata.InventoryInfo;
import data.promotiondata.PromotionData;
import data.purchasedata.PurchaseData;
import data.purchasedata.PurchaseInfo;
import data.saledata.SaleData;
import data.saledata.SaleInfo;
import data.userdata.UserData;

/**
 * 服务启动关闭管理
 * @author cylong
 * @version 2014年12月12日 上午4:52:53
 */
public class RMIManage {

	private Remote reg;
	private InetAddress addr;
	private String hostAddr;
	private String hostName;

	private boolean isStarted;

	public RMIManage() {
		try {
			addr = InetAddress.getLocalHost();
			hostAddr = addr.getHostAddress();
			hostName = addr.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void startServer() {
		new Server().start();
	}

	public void stopServer() {
		try {
			UnicastRemoteObject.unexportObject(reg, true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		isStarted = false;
	}

	public String getHostAddr() {
		return this.hostAddr;
	}

	public String getHostName() {
		return this.hostName;
	}

	public boolean isStarted() {
		return this.isStarted;
	}

	/**
	 * 启动服务的线程，防止启动时候卡顿
	 * @author cylong
	 * @version 2014年12月15日 上午2:39:38
	 */
	private class Server extends Thread {

		@Override
		public void run() {
			try {
				// 本地主机上的远程对象注册表Registry的实例，并指定端口为port
				// 这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上 
				reg = LocateRegistry.createRegistry(RMIConfig.PORT);
				String prefix = "rmi://" + hostAddr + ":" + RMIConfig.PORT + "/";
				addr = InetAddress.getLocalHost();
				hostAddr = addr.getHostAddress();
				hostName = addr.getHostName();

				Naming.bind(prefix + UserData.NAME, DataFactory.createDataService(UserData.NAME));
				Naming.bind(prefix + AccountInitData.NAME, DataFactory.createDataService(AccountInitData.NAME));
				Naming.bind(prefix + AccountBillData.NAME, DataFactory.createDataService(AccountBillData.NAME));
				Naming.bind(prefix + AccountData.NAME, DataFactory.createDataService(AccountData.NAME));
				Naming.bind(prefix + CashBillData.NAME, DataFactory.createDataService(CashBillData.NAME));
				Naming.bind(prefix + ClientData.NAME, DataFactory.createDataService(ClientData.NAME));
				Naming.bind(prefix + CommodityData.NAME, DataFactory.createDataService(CommodityData.NAME));
				Naming.bind(prefix + CommoditySortData.NAME, DataFactory.createDataService(CommoditySortData.NAME));
				Naming.bind(prefix + InventoryData.NAME, DataFactory.createDataService(InventoryData.NAME));
				Naming.bind(prefix + PromotionData.NAME, DataFactory.createDataService(PromotionData.NAME));
				Naming.bind(prefix + PurchaseData.NAME, DataFactory.createDataService(PurchaseData.NAME));
				Naming.bind(prefix + SaleData.NAME, DataFactory.createDataService(SaleData.NAME));

				Naming.bind(prefix + SaleInfo.NAME, InfoFactory.createInfoService(SaleInfo.NAME));
				Naming.bind(prefix + PurchaseInfo.NAME, InfoFactory.createInfoService(PurchaseInfo.NAME));
				Naming.bind(prefix + InventoryInfo.NAME, InfoFactory.createInfoService(InventoryInfo.NAME));
				Naming.bind(prefix + CashBillInfo.NAME, InfoFactory.createInfoService(CashBillInfo.NAME));
				Naming.bind(prefix + AccountBillInfo.NAME, InfoFactory.createInfoService(AccountBillInfo.NAME));

				isStarted = true;
			} catch (RemoteException e) {
				System.out.println("创建远程对象发生异常！");
				e.printStackTrace();
			} catch (MalformedURLException e) {
				System.out.println("发生URL畸形异常！");
				e.printStackTrace();
			} catch (AlreadyBoundException e) {
				System.out.println("发生重复绑定对象异常！");
				e.printStackTrace();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
		}
	}

}
