package data.userdata;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.util.ArrayList;

import message.ResultMessage;
import po.UserPO;

import common.ParseXML;

import data.CommonData;
import dataenum.UserIdentity;
import dataservice.userdataservice.AdminInfo;
import dataservice.userdataservice.LoginInfo;
import dataservice.userdataservice.UserDataService;

/**
 * @see dataservice.userdataservice.UserDataService
 * @author cylong
 * @version Nov 4, 2014 9:06:25 PM
 */
public class UserData extends CommonData<UserPO> implements UserDataService {

	/** serialVersionUID */
	private static final long serialVersionUID = 4037781020386364454L;

	public static ArrayList<LoginUserInfo> usersInfo = new ArrayList<LoginUserInfo>();

	/**
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月14日 上午2:16:15
	 */
	public UserData() throws RemoteException {
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
	 * 用户名存在则添加失败
	 * @see data.CommonData#insert(po.PersistentObject)
	 */
	@Override
	public ResultMessage insert(UserPO po) throws RemoteException {
		for(UserPO temp : poList.getInList()) {
			if (temp.getUsername().equals(po.getUsername())) {
				return ResultMessage.FAILURE;
			}
		}
		super.insert(po);
		return ResultMessage.SUCCESS;
	}

	/**
	 * 从文件中读取Admin信息
	 * @return
	 * @author cylong
	 * @version 2014年12月3日 上午10:29:21
	 */
	private AdminInfo loadAdminInfo() {
		ParseXML parse = new ParseXML("config/Admin.xml", "Admin");
		String username = parse.getValue("username");
		String password = parse.getValue("password");
		return new AdminInfo(username, password);
	}

	/**
	 * @see dataservice.userdataservice.UserDataService#updateAdmin(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultMessage updateAdmin(String oldPass, String newPass) throws RemoteException {
		ParseXML parse = new ParseXML("config/Admin.xml", "Admin");
		String password = parse.getValue("password");	// 从文件中读取的password
		if (oldPass.equals(password)) {
			parse.setValue("password", newPass);
			return ResultMessage.SUCCESS;
		}
		return ResultMessage.FAILURE;
	}

	/**
	 * @throws RemoteException
	 * @see dataservice.userdataservice.UserDataService#login(dataservice.userdataservice.LoginInfo)
	 */
	@Override
	public UserIdentity login(LoginInfo loginInfo) throws RemoteException {
		String clientIP = getClientIP();
		LoginUserInfo userInfo = null;
		// 验证管理员登录
		AdminInfo admin = new AdminInfo(loginInfo.username, loginInfo.password);
		if (checkAdmin(admin)) {
			userInfo = new LoginUserInfo(loginInfo.username, loginInfo.password, "管理员", clientIP);
			usersInfo.add(userInfo);
			return admin.iden;
		}

		// 验证普通用户登录
		UserPO current = null;
		for(UserPO po : poList.getInList()) {
			if (po.getUsername().equals(loginInfo.username)) {
				current = po;
				break;
			}
		}
		if (current == null) {
			return null;
		} else if (loginInfo.password == null) {
			return null;
		} else if (!loginInfo.password.equals(current.getPassword())) {
			return null;
		}
		userInfo = new LoginUserInfo(loginInfo.username, loginInfo.password, current.getName(), clientIP);
		usersInfo.add(userInfo);
		return current.getIden();
	}

	/**
	 * @return 连接的客户端的IP
	 * @author cylong
	 * @version 2014年12月15日 下午9:18:24
	 */
	private String getClientIP() {
		String clientIP = null;
		try {
			// 获取RMI客户端主机IP
			String clienthost = RemoteServer.getClientHost();
			InetAddress inetAddr = InetAddress.getByName(clienthost);
			clientIP = inetAddr.getHostAddress();
		} catch (ServerNotActiveException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return clientIP;
	}

	/**
	 * 验证管理员登录
	 * @param admin
	 * @return 验证成功与否
	 * @throws RemoteException
	 * @author cylong
	 * @version 2014年12月15日 下午7:32:29
	 */
	private boolean checkAdmin(AdminInfo admin) {
		AdminInfo adminInfo = loadAdminInfo();	// 从文件中读取的AdminInfo
		if (adminInfo.equals(admin)) {
			return true;
		}
		return false;
	}

}
