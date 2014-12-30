package data.commoditysortdata;

import java.io.File;
import java.rmi.RemoteException;

import common.DefineList;
import common.ParseXML;
import po.CommoditySortPO;
import data.CommonData;
import dataenum.ResultMessage;
import dataservice.commoditysortdataservice.CommoditySortDataService;

/**
 * @see dataservice.commoditysortdataservice.CommoditySortDataService
 * @author cylong
 * @version 2014年12月1日 下午9:47:21
 */
public class CommoditySortData extends CommonData<CommoditySortPO> implements CommoditySortDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = -543943422778007719L;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午2:21:46
	 */
	public CommoditySortData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML(NAME);
		filePath = parsexml.getValue("path");
		prefix = parsexml.getValue("prefix");
		File file = new File(filePath);
		if (!file.exists() || file.length() == 0) {	// 如果不存在保存商品分类的文件，初始化所有商品的父类分类
			poList = new DefineList<CommoditySortPO>(filePath);
			CommoditySortPO po = new CommoditySortPO(prefix, "所有商品分类", null, null, null);
			this.insert(po);
		}
	}

	/**
	 * @see dataservice.commoditysortdataservice.CommoditySortDataService#getID(java.lang.String)
	 */
	@Override
	public String getID(String fatherID) throws RemoteException {
		if (fatherID == null) {
			return getID();
		}
		CommoditySortPO po = find(fatherID);
		String newID = po.getID() + "-" + getID();
		return newID;
	}

	/**
	 * @see data.CommonData#insert(po.PersistentObject)
	 */
	@Override
	public ResultMessage insert(CommoditySortPO po) throws RemoteException {
		for(CommoditySortPO temp : poList.getInList()) {
			if (temp.getName().equals(po.getName())) { // 名称存在就添加失败
				return ResultMessage.FAILURE;
			}
		}
		super.insert(po);
		return ResultMessage.SUCCESS;
	}

	/**
	 * 有子类就不能删除
	 * @see data.CommonData#delete(java.lang.String)
	 */
	@Override
	public ResultMessage delete(String ID) throws RemoteException {
		CommoditySortPO po = find(ID);
		if (po.getChildrenID() == null && po.getCommoditiesID() == null) {
			return super.delete(ID);
		}
		return ResultMessage.FAILURE;
	}

	/**
	 * 由于分类的编号不是按照顺序，所以二分法查找会不正确
	 * @see data.CommonData#findIndex(java.lang.String)
	 */
	protected int findIndex(String ID) {
		for(int i = 0; i < poList.size(); i++) {
			CommoditySortPO po = poList.get(i);
			if (po.getID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}

}
