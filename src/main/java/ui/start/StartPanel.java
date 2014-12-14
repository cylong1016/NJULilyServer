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

	private ServerInfoPanel infoPanel;
	/** 启动服务器按钮 */
	private ServerButton startBtn;
	/** 关闭服务器按钮 */
	private ServerButton stopBtn;

	private int buttonX = 100;
	private int buttonY = 100;
	private int buttonW = 120;
	private int buttonH = 60;

	private RMIManage server;

	public StartPanel() {
		server = new RMIManage();
		
		infoPanel = new ServerInfoPanel(server.getHostAddr(), server.getHostName(), server.isStarted());
		this.add(infoPanel);
		
		ButtonListener listener = new ButtonListener();
		startBtn = new ServerButton("启动服务");
		startBtn.setBounds(buttonX, buttonY, buttonW, buttonH);
		startBtn.addMouseListener(listener);
		this.add(startBtn);
		stopBtn = new ServerButton("关闭服务");
		stopBtn.setBounds(buttonX, buttonY, buttonW, buttonH);
		stopBtn.addMouseListener(listener);
		this.add(stopBtn);
		this.showStartButton();
	}

	/**
	 * 显示启动按钮，同时删除关闭按钮
	 * @author cylong
	 * @version 2014年12月12日 上午4:37:10
	 */
	private void showStartButton() {
		startBtn.setVisible(true);
		stopBtn.setVisible(false);
		repaint();
	}

	/**
	 * 显示关闭按钮，同时删除启动按钮
	 * @author cylong
	 * @version 2014年12月12日 上午4:37:28
	 */
	private void showStopButton() {
		stopBtn.setVisible(true);
		startBtn.setVisible(false);
		repaint();
	}

	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == startBtn) {
				showStopButton();
				server.startServer();
				infoPanel.setStarted(server.isStarted());
			} else if (e.getSource() == stopBtn) {
				showStartButton();
				server.stopServer();
				infoPanel.setStarted(server.isStarted());
			}
		}
	}

}
