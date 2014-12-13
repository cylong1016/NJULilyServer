package server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import config.RMI;
import data.DataFactory;
import dataservice.DataService;

/**
 * 服务启动关闭管理
 * @author cylong
 * @version 2014年12月12日 上午4:52:53
 */
public class RMIManage {

	private Remote reg;

	public void startServer() {
		try {
			// 本地主机上的远程对象注册表Registry的实例，并指定端口为por
			// 这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上 
			reg = LocateRegistry.createRegistry(RMI.PORT);
			bind(DataFactory.getUserData());
			System.out.println(">>>>>INFO:远程服务器启动！");
			System.out.println(">>>>>INFO:远程服务器正在运行！");
		} catch (RemoteException e) {
			System.out.println("创建远程对象发生异常！");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			System.out.println("发生URL畸形异常！");
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			System.out.println("发生重复绑定对象异常！");
			e.printStackTrace();
		} catch (UnknownHostException e) {
			System.out.println("未知主机异常！");
			e.printStackTrace();
		}
	}

	public void stopServer() {
		try {
			UnicastRemoteObject.unexportObject(reg, true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println(">>>>>INFO:远程服务器关闭！");
	}

	private void bind(DataService<?> service) throws UnknownHostException, MalformedURLException, RemoteException, AlreadyBoundException {
		InetAddress addr = InetAddress.getLocalHost();
		String host = addr.getHostAddress();
		Naming.bind("rmi://" + host + ":" + RMI.PORT + "/" + service.getServiceName(), service);
	}
}
