package data.inventorydata;

import data.TableInfo;
import po.InventoryBillPO;

/**
 * @see dataservice.inventorydataservice.InventoryInfoService
 * @author cylong
 * @version 2014年12月2日 上午3:26:45
 */
public class InventoryInfo extends TableInfo<InventoryBillPO> {

	private InventoryData inventoryData;

	/**
	 * @see data.TableInfo#initPOs()
	 */
	@Override
	protected void initPOs() {
		inventoryData = new InventoryData();
		pos = inventoryData.show();
	}

}
