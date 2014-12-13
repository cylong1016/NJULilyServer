package data.saledata;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import common.ParseXML;
import po.SalesPO;
import data.CommonData;
import dataenum.BillType;
import dataservice.TableInfoService;
import dataservice.saledataservice.SaleDataService;

/**
 * @see dataservice.saledataservice.SaleDataService
 * @author cylong
 * @version 2014年11月27日 下午7:53:50
 */
public class SaleData extends CommonData<SalesPO> implements SaleDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = -6497902041791004805L;

	/** XSD */
	private String saleID;
	/** XSTHD */
	private String salebackID;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午2:16:59
	 */
	public SaleData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML("SaleData");
		saleID = parsexml.getValue("saleID");
		salebackID = parsexml.getValue("salebackID");
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
		case SALE:
			return saleID + "-" + currentDate + "-";
		case SALEBACK:
			return salebackID + "-" + currentDate + "-";
		default:
			return null;
		}
	}

	/**
	 * @see dataservice.saledataservice.SaleDataService#getInfo()
	 */
	@Override
	public TableInfoService<SalesPO> getInfo() throws RemoteException {
		return new SaleInfo();
	}

	/**
	 * @see dataservice.saledataservice.SaleDataService#getSaleID()
	 */
	@Override
	public String getSaleID() throws RemoteException {
		String ID = getBillID();
		return saleID + "-" + currentDate + "-" + ID;
	}

	/**
	 * @see dataservice.saledataservice.SaleDataService#getSaleBackID()
	 */
	@Override
	public String getSaleBackID() throws RemoteException {
		String ID = getBillID();
		return salebackID + "-" + currentDate + "-" + ID;
	}

}
