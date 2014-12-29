package ui.start;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import server.RMIManage;
import ui.ServerButton;
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

	/** 背景图片 */
	private static final Image IMG_BACK = new ImageIcon("images/back.jpg").getImage();
	/** 启动服务图片 */
	private static final Image IMG_ON_TEXT = new ImageIcon("images/on_text.png").getImage();
	/** 关闭服务图片 */
	private static final Image IMG_OFF_TEXT = new ImageIcon("images/off_text.png").getImage();

	/** 显示系统信息 */
	private ServerInfoPanel serverInfoPanel;
	/** 显示当前登录客户信息 */
	private ClientInfoPanel clientInfoPanel;
	/** 启动服务器按钮 */
	private ServerButton startBtn;
	/** 关闭服务器按钮 */
	private ServerButton stopBtn;

	/** 开始按钮和关闭按钮的坐标 */
	private int buttonX = 74;
	private int buttonY = 270;
	/** 开始按钮和关闭按钮之间的间隙 */
	private int interval = 0;

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
		startBtn = new ServerButton(IMG_ON_TEXT);
		startBtn.setLocation(buttonX, buttonY);
		startBtn.addMouseListener(listener);
		this.add(startBtn);
		stopBtn = new ServerButton(IMG_OFF_TEXT);
		stopBtn.setLocation(buttonX, buttonY + startBtn.getHeight() + interval);
		stopBtn.addMouseListener(listener);
		this.add(stopBtn);
		stopBtn.setEnabled(false);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(IMG_BACK, 0, 0, this);
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
