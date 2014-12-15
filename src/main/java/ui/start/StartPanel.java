package ui.start;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import server.RMIManage;
import ui.ServerPanel;
import config.UIConfig;

/**
 * 启动界面
 * @author cylong
 * @version 2014年12月12日 上午3:57:49
 */
public class StartPanel extends ServerPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5666095121593970274L;

	/** 显示系统信息 */
	private ServerInfoPanel serverInfoPanel;
	/** 显示当前登录客户信息 */
	private ClientInfoPanel clientInfoPanel;
	/** 启动服务器按钮 */
	private StartButton startBtn;
	/** 关闭服务器按钮 */
	private StartButton stopBtn;

	/** 开始按钮和关闭按钮的坐标 */
	private int buttonX = 60;
	private int buttonY = 310;
	/** 开始按钮和关闭按钮之间的间隙 */
	private int interval = 30;

	/** 显示主机信息panel的位置 */
	private Point serverInfoPanelPoint = new Point(25, 60);
	/** 显示登录用户信息panel的位置 */
	private Point clientInfoPanelPoint = new Point(360, 60);

	private RMIManage server;

	public StartPanel() {
		this.setLocation(0, UIConfig.TITLE_HEIGHT);	// 向下偏移TITLE_HEIGHT，防止和TitlePanel重合
		this.setSize(UIConfig.WIDTH, UIConfig.HEIGHT - UIConfig.TITLE_HEIGHT);
		this.setBackground(UIConfig.MAIN_COLOR);
		server = new RMIManage();

		serverInfoPanel = new ServerInfoPanel(server.getHostAddr(), server.getHostName(), server.isStarted());
		serverInfoPanel.setLocation(serverInfoPanelPoint);
		this.add(serverInfoPanel);
		this.addStartStopButton();
		clientInfoPanel = new ClientInfoPanel();
		clientInfoPanel.setLocation(clientInfoPanelPoint);
		this.add(clientInfoPanel);
	}

	/**
	 * 添加开始和关闭按钮
	 * @author cylong
	 * @version 2014年12月15日 下午10:39:52
	 */
	private void addStartStopButton() {
		ButtonListener listener = new ButtonListener();
		startBtn = new StartButton("启动服务");
		startBtn.setLocation(buttonX, buttonY);
		startBtn.addMouseListener(listener);
		this.add(startBtn);
		stopBtn = new StartButton("关闭服务");
		stopBtn.setLocation(buttonX, buttonY + startBtn.getHeight() + interval);
		stopBtn.addMouseListener(listener);
		this.add(stopBtn);
		stopBtn.setEnabled(false);
	}

	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == startBtn) {
				if (!server.isStarted()) {
					startBtn.setEnabled(false);
					stopBtn.setEnabled(true);
					server.startServer();
					serverInfoPanel.setStarted(true);
				}
			} else if (e.getSource() == stopBtn) {
				if (server.isStarted()) {
					startBtn.setEnabled(true);
					stopBtn.setEnabled(false);
					server.stopServer();
					serverInfoPanel.setStarted(false);
				}
			}
		}
	}

}
