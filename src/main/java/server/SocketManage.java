package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import common.Sleep;

import data.DataFactory;

/**
 * 通过Socket远程调用
 * @author cylong
 * @version 2014年12月12日 下午11:42:16
 */
public class SocketManage {

	/** 服务器端的socket */
	private Socket socket = null;
	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;
	private ServerSocket serverSocket = null;
	private DataFactory factory = null;
	private boolean start;
	private boolean connect;

	public void startServer() throws IOException {
		if (start) {
			return;
		}
		factory = new DataFactory();
		serverSocket = new ServerSocket(8888);
		new Thread() {

			public void run() {
				try {
					start = true;
					socket = serverSocket.accept();
					connect = true;
					oos = new ObjectOutputStream(socket.getOutputStream());
					oos.writeObject(factory);
					new GetData().start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	public void getCommend() throws Exception {
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
		String command = (String)ois.readObject();
		System.out.println(command);
		// oos.writeObject(dataAnalyse.setCommand(command));
	}

	public void closeServer() {
		if (serverSocket != null && start) {
			try {
				System.out.println("服务器已关闭！");
				serverSocket.close();
				start = false;
				connect = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private class GetData extends Thread {

		public void run() {
			try {
				while(true) {
					Sleep.sleep(1000);
					getCommend();
				}
			} catch (Exception e) {
				connect = false;
				closeServer();
			}
		}
	}

	/**
	 * @version Jun 21, 2014 2:39:01 PM
	 * @return the start
	 */
	public boolean isStart() {
		return this.start;
	}

	/**
	 * @version Jun 21, 2014 2:39:01 PM
	 * @return the connect
	 */
	public boolean isConnect() {
		return this.connect;
	}

}
