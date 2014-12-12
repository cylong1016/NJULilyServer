package ui.start;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import server.RMIManage;
import ui.ServerButton;
import ui.ServerPanel;

/**
 * 启动界面
 * @author cylong
 * @version 2014年12月12日 上午3:57:49
 */
public class StartPanel extends ServerPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5666095121593970274L;

	/** 启动服务器按钮 */
	private ServerButton start;
	/** 关闭服务器按钮 */
	private ServerButton stop;
	
	private int buttonX = 100;
	private int buttonY = 100;
	private int buttonW = 100;
	private int buttonH = 60;
	
	private RMIManage server;

	public StartPanel() {
		server = new RMIManage();
		ButtonListener listener = new ButtonListener();
		start = new ServerButton("启动");
		start.setBounds(buttonX, buttonY, buttonW, buttonH);
		start.addMouseListener(listener);
		this.add(start);
		stop = new ServerButton("关闭");
		stop.setBounds(buttonX, buttonY, buttonW, buttonH);
		stop.addMouseListener(listener);
		this.add(stop);
		this.showStartButton();
	}

	/**
	 * 添加启动按钮，同时删除关闭按钮
	 * @author cylong
	 * @version 2014年12月12日 上午4:37:10
	 */
	private void showStartButton() {
		start.setVisible(true);
		stop.setVisible(false);
	}

	/**
	 * 添加关闭按钮，同时删除启动按钮
	 * @author cylong
	 * @version 2014年12月12日 上午4:37:28
	 */
	private void showStopButton() {
		stop.setVisible(true);
		start.setVisible(false);
	}
	
	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() == start) {
				showStopButton();
				server.startServer();
			} else if(e.getSource() == stop) {
				showStartButton();
				server.stopServer();
			}
		}
	}

}
