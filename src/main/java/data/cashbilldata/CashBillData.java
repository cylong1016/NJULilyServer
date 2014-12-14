package data.cashbilldata;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import po.CashBillPO;

import common.ParseXML;

import data.CommonData;
import dataservice.cashbilldataservice.CashBillDataService;

/**
 * @see dataservice.cashbilldataservice.CashBillDataService
 * @author cylong
 * @version 2014年12月1日 下午4:19:54
 */
public class CashBillData extends CommonData<CashBillPO> implements CashBillDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = 6466349581911827298L;

	/** 当天日期 */
	private String currentDate;
	/** 文件中记录的日期 */
	private String dateRecord;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午3:59:20
	 */
	public CashBillData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML(NAME);
		prefix = parsexml.getValue("prefix");
		dateRecord = parsexml.getValue("dateRecord");
		String dateFormat = parsexml.getValue("dateFormat");
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		currentDate = sdf.format(new Date());
	}

	/**
	 * 超过一天最大ID数量时候返回null
	 * @see data.CommonData#getID()
	 */
	@Override
	public String getID() throws RemoteException {
		if (currentDate.equals(dateRecord)) {
			maxID = Integer.parseInt(parsexml.getValue("maxID"));
		} else {	// 过了一天
			parsexml.setValue("dateRecord", currentDate);
			maxID = 0;	// 初始化最大ID
		}
		String ID = super.getID();
		if (ID.length() > IDMaxBit) {	// 超过一天单据的最大数量
			return null;
		}
		return prefix + "-" + currentDate + "-" + ID;
	}

}
