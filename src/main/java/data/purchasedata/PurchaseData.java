package data.purchasedata;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.ParseXML;
import po.PurchasePO;
import data.CommonData;
import dataenum.BillType;
import dataservice.TableInfoService;
import dataservice.purchasedataservice.PurchaseDataService;

/**
 * @see dataservice.purchasedataservice.PurchaseDataService
 * @author cylong
 * @version 2014年12月1日 下午4:18:52
 */
public class PurchaseData extends CommonData<PurchasePO> implements PurchaseDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = -3452395207596070860L;

	/** JHD */
	private String purchaseID;
	/** JHTHD */
	private String purchasebackID;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午3:55:52
	 */
	public PurchaseData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML("PurchaseData");
		purchaseID = parsexml.getValue("purchaseID");
		purchasebackID = parsexml.getValue("purchasebackID");
		dateRecord = parsexml.getValue("dateRecord");
		String dateFormat = parsexml.getValue("dateFormat");
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		currentDate = sdf.format(new Date());
	}

	/**
	 * 根据单据类型获得编号前缀
	 * @deprecated 不需要判断type， 已经分成多个方法
	 * @param type 单据类型
	 * @return ID前缀
	 * @author cylong
	 * @version 2014年12月2日 下午4:27:44
	 */
	protected String getPreID(BillType type) {
		switch(type) {
		case PURCHASE:
			return purchaseID + "-" + currentDate + "-";
		case PURCHASEBACK:
			return purchasebackID + "-" + currentDate + "-";
		default:
			return null;
		}
	}

	/**
	 * @see dataservice.purchasedataservice.PurchaseDataService#getInfo()
	 */
	@Override
	public TableInfoService<PurchasePO> getInfo() throws RemoteException {
		return new PurchaseInfo();
	}

	/**
	 * @see dataservice.purchasedataservice.PurchaseDataService#getPurchaseID()
	 */
	@Override
	public String getPurchaseID() throws RemoteException {
		String ID = getBillID();
		return purchaseID + "-" + currentDate + "-" + ID;
	}

	/**
	 * @see dataservice.purchasedataservice.PurchaseDataService#getPurchaseBackID()
	 */
	@Override
	public String getPurchaseBackID() throws RemoteException {
		String ID = getBillID();
		return purchasebackID + "-" + currentDate + "-" + ID;
	}

}
