package data.saledata;

import java.rmi.RemoteException;

import po.SalesPO;
import data.TableInfo;
import dataenum.Storage;

/**
 * @see dataservice.saledataservice.SaleInfoService
 * @author cylong
 * @version 2014年12月2日 上午3:29:07
 */
public class SaleInfo extends TableInfo<SalesPO> {

	/** serialVersionUID */
	private static final long serialVersionUID = 6622297309490703986L;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午2:16:42
	 */
	public SaleInfo() throws RemoteException {
		super();
	}

	private SaleData saleData;

	/**
	 * @see data.TableInfo#initPOs()
	 */
	@Override
	protected void initPOs() throws RemoteException {
		saleData = new SaleData();
		pos = saleData.show();
	}

	/**
	 * 由子类实现
	 * @see dataservice.TableInfoService#getClient(java.lang.String)
	 */
	@Override
	public String getClient(String billID) throws RemoteException {
		return saleData.find(billID).getClient();
	}

	/**
	 * 由子类实现
	 * @see dataservice.TableInfoService#getSalesman(java.lang.String)
	 */
	@Override
	public String getSalesman(String billID) throws RemoteException {
		return saleData.find(billID).getSalesman();
	}

	/**
	 * 由子类实现
	 * @see dataservice.TableInfoService#getStorage(java.lang.String)
	 */
	@Override
	public Storage getStorage(String billID) throws RemoteException {
		return saleData.find(billID).getStorage();
	}

}
