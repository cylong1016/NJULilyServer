package data.purchasedata;

import java.rmi.RemoteException;

import po.PurchasePO;
import data.TableInfo;
import dataenum.Storage;

/**
 * dataservice.purchasedataservice.PurchaseInfoService
 * @author cylong
 * @version 2014年12月2日 上午3:28:29
 */
public class PurchaseInfo extends TableInfo<PurchasePO> {

	/** serialVersionUID */
	private static final long serialVersionUID = 7513313073337006506L;

	private PurchaseData purchaseData;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午3:55:24
	 */
	public PurchaseInfo() throws RemoteException {
		super();
	}

	/**
	 * @see data.TableInfo#initPOs()
	 */
	@Override
	protected void initPOs() throws RemoteException {
		purchaseData = new PurchaseData();
		pos = purchaseData.show();
	}

	/**
	 * @see data.TableInfo#getClient(java.lang.String)
	 */
	@Override
	public String getClient(String billID) throws RemoteException {
		return purchaseData.find(billID).getClient();
	}

	/**
	 * @see data.TableInfo#getStorage(java.lang.String)
	 */
	@Override
	public Storage getStorage(String billID) throws RemoteException {
		return purchaseData.find(billID).getStorage();
	}

}
