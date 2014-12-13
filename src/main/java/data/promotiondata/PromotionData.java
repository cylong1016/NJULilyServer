package data.promotiondata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import common.ParseXML;
import po.PromotionPO;
import data.CommonData;
import dataenum.PromotionType;
import dataservice.promotiondataservice.PromotionDataService;

/**
 * @author cylong
 * @version 2014年12月2日 下午9:01:18
 */
public class PromotionData extends CommonData<PromotionPO> implements PromotionDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = -6496683940540459670L;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午2:20:43
	 */
	public PromotionData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML("PromotionData");
		prefix = parsexml.getValue("prefix");
	}

	/**
	 * @see data.CommonData#getID()
	 */
	@Override
	public String getID() throws RemoteException {
		return prefix + super.getID();
	}

	/**
	 * @see dataservice.promotiondataservice.PromotionDataService#show(dataenum.PromotionType)
	 */
	@Override
	public ArrayList<PromotionPO> show(PromotionType type) throws RemoteException {
		ArrayList<PromotionPO> pos = new ArrayList<PromotionPO>();
		for(PromotionPO po : poList.getInList()) {
			if (po.getType().equals(type)) {
				pos.add(po);
			}
		}
		return pos;
	}

}
