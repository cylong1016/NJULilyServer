package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import message.ResultMessage;
import po.PersistentObject;
import common.Common;
import common.DefineList;
import common.ParseXML;
import dataenum.BillType;
import dataservice.CommonDataService;

/**
 * 数据层常用的操作，增删改查
 * @author cylong
 * @version 2014年11月30日 上午10:43:58
 */
public abstract class CommonData<PO extends PersistentObject> extends UnicastRemoteObject implements CommonDataService<PO> {

	/** serialVersionUID */
	private static final long serialVersionUID = 7569104044064021328L;

	/** 保存PO的list */
	protected DefineList<PO> poList;
	/** 保存文件的路径 */
	protected String filePath;
	/** ID前缀 */
	protected String prefix;
	/** 当前最大ID */
	protected int maxID;
	/** 添加一条记录后的账单 */
	protected String currentID;
	/** ID最大位数 */
	protected int IDMaxBit;
	/** 解析xml文件 */
	protected ParseXML parsexml;
	/** 当天日期 */
	protected String currentDate;
	/** 文件中记录的日期 */
	protected String dateRecord;

	public CommonData() throws RemoteException {
		init();	// 初始化parsexml
		filePath = parsexml.getValue("path");
		maxID = Integer.parseInt(parsexml.getValue("maxID"));
		IDMaxBit = Integer.parseInt(parsexml.getValue("IDMaxBit"));
		poList = new DefineList<PO>(filePath);
	}

	/**
	 * @see dataservice.DataService#getID()
	 */
	public String getID() throws RemoteException {
		if (poList.isEmpty()) {
			maxID = 0;	// 初始化最大ID
			parsexml.setValue("maxID", Common.intToString(maxID, IDMaxBit));
		}
		currentID = Common.intToString(maxID + 1, IDMaxBit);
		return currentID;
	}

	/**
	 * 单据ID保存成功后增加ID，防止创建一半停止，但是ID增加的情况
	 * @author cylong
	 * @version 2014年12月3日 上午8:59:47
	 */
	private void addID() {
		maxID++;
		parsexml.setValue("maxID", Common.intToString(maxID, IDMaxBit));
	}

	/**
	 * 以单据类型返回单据ID
	 * @deprecated 每次都要判断type影响效率
	 * @param type 单据类型
	 * @return 单据ID
	 * @author cylong
	 * @version 2014年12月2日 下午6:03:39
	 */
	public String getID(BillType type) throws RemoteException {
		String preID = getPreID(type);
		if (preID == null) {
			return null;
		}
		if (currentDate.equals(dateRecord)) {
			maxID = Integer.parseInt(parsexml.getValue("maxID"));
		} else {	// 过了一天
			parsexml.setValue("dateRecord", currentDate);
			maxID = 0;	// 初始化最大ID
		}
		String ID = getID();
		if (ID.length() > IDMaxBit) {	// 超过一天单据的最大数量
			return null;
		}
		return preID + ID;
	}

	/**
	 * 按照单子类型返回ID的前缀，由子类实现
	 * @deprecated 不需要判断单据的类型
	 * @param type
	 * @return
	 * @author cylong
	 * @version 2014年12月2日 下午6:03:32
	 */
	protected String getPreID(BillType type) {
		return null;
	}

	/**
	 * 由getID(type)变形得到，不需要判断单据的类型
	 * @return
	 * @author cylong
	 * @version 2014年12月9日 下午6:54:46
	 */
	protected String getBillID() throws RemoteException {
		if (currentDate.equals(dateRecord)) {
			maxID = Integer.parseInt(parsexml.getValue("maxID"));
		} else {	// 过了一天
			parsexml.setValue("dateRecord", currentDate);
			maxID = 0;	// 初始化最大ID
		}
		String ID = getID();
		if (ID.length() > IDMaxBit) {	// 超过一天单据的最大数量
			return null;
		}
		return ID;
	}

	/**
	 * @see dataservice.CommonDataService#insert(po.PersistentObject)
	 */
	@Override
	public ResultMessage insert(PO po) throws RemoteException {
		poList.add(po);
		addID();
		return ResultMessage.SUCCESS;
	}

	/**
	 * @see dataservice.CommonDataService#find(java.lang.String)
	 */
	@Override
	public PO find(String ID) throws RemoteException {
		for(PO po : poList.getInList()) {
			if (po.getID().equals(ID)) {
				return po;
			}
		}
		return null;
	}

	/**
	 * @see dataservice.CommonDataService#delete(java.lang.String)
	 */
	@Override
	public ResultMessage delete(String ID) throws RemoteException {
		for(int i = 0; i < poList.size(); i++) {
			if (poList.get(i).getID().equals(ID)) {
				poList.remove(i);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILURE;
	}

	/**
	 * @see dataservice.CommonDataService#update(po.PersistentObject)
	 */
	@Override
	public ResultMessage update(PO po) throws RemoteException {
		for(int i = 0; i < poList.size(); i++) {
			PO temp = poList.get(i);
			if (temp.getID().equals(po.getID())) {
				poList.set(i, po);
				return ResultMessage.SUCCESS;
			}
		}
		return ResultMessage.FAILURE;
	}

	/**
	 * @see dataservice.CommonDataService#show()
	 */
	@Override
	public ArrayList<PO> show() throws RemoteException {
		return poList.getInList();
	}

}
