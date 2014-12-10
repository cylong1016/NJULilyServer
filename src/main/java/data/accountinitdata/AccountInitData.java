package data.accountinitdata;

import io.DefineList;

import java.util.ArrayList;

import common.Common;
import common.ParseXML;
import message.ResultMessage;
import po.AccountaInitPO;
import dataservice.accountinitdataservice.AccountaInitDataService;

/**
 * @author cylong
 * @version 2014年12月2日 下午8:37:30
 */
public class AccountInitData implements AccountaInitDataService {

	protected DefineList<AccountaInitPO> initList;
	/** 保存文件的路径 */
	protected String filePath;
	/** ID前缀 */
	protected String prefix;
	/** 当前最大ID */
	protected int maxID;
	/** 添加时返回的临时ID */
	private String currentID;
	/** ID最大位数 */
	protected int IDMaxBit;
	/** 解析xml文件 */
	protected ParseXML parsexml;

	public AccountInitData() {
		init();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() {
		parsexml = new ParseXML("AccountInitData");
		filePath = parsexml.getValue("path");
		prefix = parsexml.getValue("prefix");
		maxID = Integer.parseInt(parsexml.getValue("maxID"));
		IDMaxBit = Integer.parseInt(parsexml.getValue("IDMaxBit"));
		initList = new DefineList<AccountaInitPO>(filePath);
	}

	/**
	 * @see dataservice.DataService#getID()
	 */
	@Override
	public String getID() {
		if (initList.isEmpty()) {
			maxID = 0;	// 初始化最大ID
			parsexml.setValue("maxID", Common.intToString(maxID, IDMaxBit));
		}
		currentID = Common.intToString((maxID + 1), IDMaxBit);
		return currentID;
	}

	private void addID() {
		maxID++;
		parsexml.setValue("maxID", currentID);
	}

	/**
	 * @see dataservice.DataService#find(java.lang.String)
	 */
	@Override
	public AccountaInitPO find(String ID) {
		for(int i = 0; i < initList.size(); i++) {
			if (initList.get(i).getID().equals(ID)) {
				return initList.get(i);
			}
		}
		return null;
	}

	/**
	 * @see dataservice.accountinitdataservice.AccountaInitDataService#insert(po.AccountaInitPO)
	 */
	@Override
	public ResultMessage insert(AccountaInitPO po) {
		for(AccountaInitPO temp : initList.getInList()) {
			if (temp.getID().equals(po.getID())) {
				return ResultMessage.FAILURE;
			}
		}
		initList.add(po);
		addID();
		return ResultMessage.SUCCESS;
	}

	/**
	 * @see dataservice.accountinitdataservice.AccountaInitDataService#show()
	 */
	@Override
	public ArrayList<AccountaInitPO> show() {
		return initList.getInList();
	}

}
