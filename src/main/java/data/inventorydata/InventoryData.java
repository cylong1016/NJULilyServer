package data.inventorydata;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import po.InventoryBillPO;
import common.Common;
import common.ParseXML;
import data.CommonData;
import dataenum.BillType;
import dataservice.inventorydataservice.InventoryDataService;

/**
 * @see dataservice.inventorydataservice.InventoryDataService
 * @author cylong
 * @version 2014年12月1日 下午4:20:49
 */
public class InventoryData extends CommonData<InventoryBillPO> implements InventoryDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = 8341935441916365357L;

	/** BYD */
	private String overflowID;
	/** BSD */
	private String lossID;
	/** BJD */
	private String alarmID;
	/** ZSD */
	private String giftID;
	/** 盘点的批号 */
	private int lotNumber;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午3:57:07
	 */
	public InventoryData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML(NAME);
		overflowID = parsexml.getValue("overflowID");
		lossID = parsexml.getValue("lossID");
		alarmID = parsexml.getValue("alarmID");
		giftID = parsexml.getValue("giftID");
		lotNumber = Integer.parseInt(parsexml.getValue("lotNumber"));
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
	 * @version 2014年12月2日 下午5:50:25
	 */
	@Override
	protected String getPreID(BillType type) {
		switch(type) {
		case OVERFLOW:
			return overflowID + "-" + currentDate + "-";
		case LOSS:
			return lossID + "-" + currentDate + "-";
		case ALARM:
			return alarmID + "-" + currentDate + "-";
		case GIFT:
			return giftID + "-" + currentDate + "-";
		default:
			return null;
		}
	}

	/**
	 * @see dataservice.inventorydataservice.InventoryDataService#returnLotNumber()
	 */
	@Override
	public String returnLotNumber() throws RemoteException {
		lotNumber++;
		parsexml.setValue("lotNumber", Common.intToString(lotNumber, IDMaxBit));
		return String.valueOf(lotNumber);
	}

	public ArrayList<InventoryBillPO> show(BillType type) throws RemoteException {
		ArrayList<InventoryBillPO> typePOs = new ArrayList<InventoryBillPO>();
		for(InventoryBillPO po : poList.getInList()) {
			if (po.getBillType().equals(type)) {
				typePOs.add(po);
			}
		}
		return typePOs;
	}

	/**
	 * @see dataservice.inventorydataservice.InventoryDataService#getOverflowID()
	 */
	@Override
	public String getOverflowID() throws RemoteException {
		String ID = super.getID();
		return overflowID + "-" + currentDate + "-" + ID;
	}

	/**
	 * @see dataservice.inventorydataservice.InventoryDataService#getLossID()
	 */
	@Override
	public String getLossID() throws RemoteException {
		String ID = super.getID();
		return lossID + "-" + currentDate + "-" + ID;
	}

	/**
	 * @see dataservice.inventorydataservice.InventoryDataService#getAlarmID()
	 */
	@Override
	public String getAlarmID() throws RemoteException {
		String ID = super.getID();
		return alarmID + "-" + currentDate + "-" + ID;
	}

	/**
	 * @see dataservice.inventorydataservice.InventoryDataService#getGiftID()
	 */
	@Override
	public String getGiftID() throws RemoteException {
		String ID = super.getID();
		return giftID + "-" + currentDate + "-" + ID;
	}

}
