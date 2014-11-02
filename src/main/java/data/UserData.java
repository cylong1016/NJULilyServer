package data;

import io.DefineList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import message.ResultMessage;
import po.UserPO;
import dataservice.UserDataService;

/**
 * 提供用户集体载入、保存、增删改查服务
 * @author cylong
 * @version Nov 2, 2014 4:49:12 PM
 */
public class UserData extends UnicastRemoteObject implements UserDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	/** 用户数据列表 */
	private DefineList<UserPO> userList;

	public DefineList<UserPO> getUserList() {
		return this.userList;
	}

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version Nov 2, 2014 5:07:12 PM
	 */
	protected UserData() throws RemoteException {
		super();
		// 初始化UserData
		init();
	}

	/**
	 * @see dataservice.DataService#init()
	 */
	public void init() throws RemoteException {
		userList = new DefineList<UserPO>("data/userList.ser");
	}

	/**
	 * @see dataservice.UserDataService#insert(po.UserPO)
	 */
	public ResultMessage insert(UserPO po) throws RemoteException {
		
		return null;
	}

	/**
	 * @see dataservice.UserDataService#find(java.lang.String)
	 */
	public UserPO find(String username) throws RemoteException {
		return null;
	}

	/**
	 * @see dataservice.UserDataService#delete(java.lang.String)
	 */
	public ResultMessage delete(String username) throws RemoteException {
		return null;
	}

	/**
	 * @see dataservice.UserDataService#update(po.UserPO)
	 */
	public ResultMessage update(UserPO po) throws RemoteException {
		return null;
	}

}
