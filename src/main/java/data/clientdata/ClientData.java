package data.clientdata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import common.ParseXML;
import message.ResultMessage;
import po.ClientPO;
import data.CommonData;
import dataenum.FindTypeClient;
import dataservice.clientdataservice.ClientDataService;

/**
 * @see dataservice.clientdataservice.ClientDataService
 * @author cylong
 * @version Nov 11, 2014 7:47:51 PM
 */
public class ClientData extends CommonData<ClientPO> implements ClientDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = -3277392463773869798L;

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午3:58:29
	 */
	public ClientData() throws RemoteException {
		super();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	@Override
	public void init() throws RemoteException {
		parsexml = new ParseXML(NAME);
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
	 * 名称存在就添加失败
	 * @see data.CommonData#insert(po.PersistentObject)
	 */
	@Override
	public ResultMessage insert(ClientPO po) throws RemoteException {
		for(ClientPO temp : poList.getInList()) {
			if (temp.getName().equals(po.getName())) {
				return ResultMessage.FAILURE;
			}
		}
		super.insert(po);
		return ResultMessage.SUCCESS;
	}

	/**
	 * @see dataservice.clientdataservice.ClientDataService#find(java.lang.String, dataenum.FindTypeClient)
	 */
	@Override
	public ArrayList<ClientPO> find(String keywords, FindTypeClient type) throws RemoteException {
		keywords = keywords.toLowerCase(); // 为了不区分大小写
		ArrayList<ClientPO> clients = new ArrayList<ClientPO>();
		if (type == null) {	// 查找客户全部信息
			for(ClientPO client : poList.getInList()) {
				if (client.toString().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			return clients;
		}
		switch(type) {
		case ID:
			for(ClientPO client : poList.getInList()) {
				if (client.getID().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case ADDRESS:
			for(ClientPO client : poList.getInList()) {
				if (client.getAddress().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case EMAIL:
			for(ClientPO client : poList.getInList()) {
				if (client.getEmail().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case KIND:
			for(ClientPO client : poList.getInList()) {
				if (client.getCategory().value.toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case LEVEL:
			for(ClientPO client : poList.getInList()) {
				if (client.getLevel().value.toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case NAME:
			for(ClientPO client : poList.getInList()) {
				if (client.getName().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case PAYABLE:
			for(ClientPO client : poList.getInList()) {
				if (Double.toString(client.getPayable()).contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case PHONE:
			for(ClientPO client : poList.getInList()) {
				if (client.getPhone().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case POST:
			for(ClientPO client : poList.getInList()) {
				if (client.getPost().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case RECEIVABLE:
			for(ClientPO client : poList.getInList()) {
				if (Double.toString(client.getReceivable()).contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case RECEIVABLELIMIT:
			for(ClientPO client : poList.getInList()) {
				if (Double.toString(client.getReceivable()).contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		case SALESMAN:
			for(ClientPO client : poList.getInList()) {
				if (client.getSalesman().toLowerCase().contains(keywords)) {
					clients.add(client);
				}
			}
			break;
		default:
			break;
		}
		return clients;
	}

}
