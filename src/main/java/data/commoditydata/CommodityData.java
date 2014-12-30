package data.commoditydata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.CommodityPO;
import po.CommoditySortPO;
import common.ParseXML;
import data.CommonData;
import data.DataFactory;
import dataenum.FindTypeCommo;
import dataenum.ResultMessage;
import dataservice.commoditydataservice.CommodityDataService;
import dataservice.commoditysortdataservice.CommoditySortDataService;

/**
 * @see dataservice.commoditydataservice.CommodityDataService
 * @author cylong
 * @version 2014年12月1日 下午6:59:23
 */
public class CommodityData extends CommonData<CommodityPO> implements CommodityDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = -6277717831564809873L;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午2:21:57
	 */
	public CommodityData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML(NAME);
	}

	/**
	 * @see dataservice.commoditydataservice.CommodityDataService#getID(java.lang.String)
	 */
	@Override
	public String getID(String fatherID) throws RemoteException {
		if (fatherID == null) {
			return null;
		}
		CommoditySortDataService sortData = (CommoditySortDataService)DataFactory.createDataService(CommoditySortDataService.NAME);
		CommoditySortPO sortPO = sortData.find(fatherID);
		if(sortPO == null) {
			return null;
		}
		String newID = sortPO.getID() + "-" + super.getID();
		return newID;
	}

	/**
	 * 名称和型号存在就添加失败
	 * @see data.CommonData#insert(po.PersistentObject)
	 */
	@Override
	public ResultMessage insert(CommodityPO po) throws RemoteException {
		for(CommodityPO temp : poList.getInList()) {
			boolean hasName = temp.getName().equals(po.getName());
			boolean hasType = temp.getType().equals(po.getType());
			if (hasName && hasType) {
				return ResultMessage.FAILURE;
			}
		}
		super.insert(po);
		return ResultMessage.SUCCESS;
	}

	/**
	 * @see dataservice.commoditydataservice.CommodityDataService#find(java.lang.String,
	 *      dataenum.FindTypeCommo)
	 */
	@Override
	public ArrayList<CommodityPO> find(String keywords, FindTypeCommo type) throws RemoteException {
		ArrayList<CommodityPO> commodities = new ArrayList<CommodityPO>();
		keywords = keywords.toLowerCase(); // 为了不区分大小写
		if (type == null) {	// 查询商品全部信息
			for(CommodityPO commodity : poList.getInList()) {
				if (commodity.toString().toLowerCase().contains(keywords)) {
					commodities.add(commodity);
				}
			}
			return commodities;
		}
		switch(type) {
		case ID:
			for(CommodityPO commodity : poList.getInList()) {
				if (commodity.getID().toLowerCase().contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		case NAME:
			for(CommodityPO commodity : poList.getInList()) {
				if (commodity.getName().toLowerCase().contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		case NUMBER:
			for(CommodityPO commodity : poList.getInList()) {
				if (Integer.toString(commodity.getInventoryNum()).contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		case PURCHASE:
			for(CommodityPO commodity : poList.getInList()) {
				if (Double.toString(commodity.getPurPrice()).contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		case RECENTPUR:
			for(CommodityPO commodity : poList.getInList()) {
				if (Double.toString(commodity.getRecentPurPrice()).contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		case RECENTSALE:
			for(CommodityPO commodity : poList.getInList()) {
				if (Double.toString(commodity.getRecentSalePrice()).contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		case SALE:
			for(CommodityPO commodity : poList.getInList()) {
				if (Double.toString(commodity.getSalePrice()).contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		case TYPE: // 改成商品的分类不是类型 TODO
			for(CommodityPO commodity : poList.getInList()) {
				if (commodity.getType().toLowerCase().contains(keywords)) {
					commodities.add(commodity);
				}
			}
			break;
		default:
			break;
		}
		return commodities;
	}

	/**
	 * @see dataservice.commoditydataservice.CommodityDataService#getAllID()
	 */
	@Override
	public ArrayList<String> getAllID() throws RemoteException {
		ArrayList<String> IDs = new ArrayList<String>();
		for(CommodityPO commodity : poList.getInList()) {
			IDs.add(commodity.getID());
		}
		return IDs;
	}

	/**
	 * 若商品已经被进货或者销售就不能删除
	 * @return 处理结果
	 * @author cylong
	 * @version 2014年12月1日 下午9:26:31
	 */
	@Override
	public ResultMessage delete(String ID) throws RemoteException {
		CommodityPO po = find(ID);
		if ((po.getRecentPurPrice() + po.getRecentSalePrice()) != 0) {
			return ResultMessage.FAILURE;
		}
		return super.delete(ID);
	}
	
	/**
	 * 由于分类的编号不是按照顺序，所以二分法查找会不正确
	 * @see data.CommonData#findIndex(java.lang.String)
	 */
	protected int findIndex(String ID) {
		for(int i = 0; i < poList.size(); i++) {
			CommodityPO po = poList.get(i);
			if (po.getID().equals(ID)) {
				return i;
			}
		}
		return -1;
	}

}
