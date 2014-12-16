package data.inventorydata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.InventoryBillPO;
import data.TableInfo;
import dataenum.BillType;
import dataservice.inventorydataservice.InventoryInfoService;

/**
 * @see dataservice.inventorydataservice.InventoryInfoService
 * @author cylong
 * @version 2014年12月2日 上午3:26:45
 */
public class InventoryInfo extends TableInfo<InventoryBillPO> implements InventoryInfoService {

	/** serialVersionUID */
	private static final long serialVersionUID = -2070822259603429542L;

	private InventoryData inventoryData;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午3:56:42
	 */
	public InventoryInfo() throws RemoteException {
		super();
	}

	/**
	 * @see data.TableInfo#initPOs()
	 */
	@Override
	protected void initPOs() throws RemoteException {
		inventoryData = new InventoryData();
		pos = inventoryData.show();
	}

	/**
	 * @see data.TableInfo#getAllID(dataenum.BillType)
	 */
	@Override
	public ArrayList<String> getAllID(BillType type) throws RemoteException {
		ArrayList<String> IDs = new ArrayList<String>();
		for(InventoryBillPO po : pos) {
			if (po.getBillType().equals(type)) {
				IDs.add(po.getID());
			}
		}
		return IDs;
	}

}
