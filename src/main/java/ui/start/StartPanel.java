package ui.start;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import server.RMIManage;
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
	private StartButton startBtn;
	/** 关闭服务器按钮 */
	private StartButton stopBtn;

	private int buttonX = 0;
	private int buttonY = 364;
	/** 开始按钮和关闭按钮之间的间隙 */
	private int interval = 0;

	private RMIManage server;

	public StartPanel() {

		server = new RMIManage();

		infoPanel = new ServerInfoPanel(server.getHostAddr(), server.getHostName(), server.isStarted());
		this.add(infoPanel);

		ButtonListener listener = new ButtonListener();
		startBtn = new StartButton("启动服务");
		startBtn.setLocation(buttonX, buttonY);
		startBtn.addMouseListener(listener);
		this.add(startBtn);
		stopBtn = new StartButton("关闭服务");
		stopBtn.setLocation(buttonX + startBtn.getWidth() + interval, buttonY);
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
					infoPanel.setStarted(true);
				}
			} else if (e.getSource() == stopBtn) {
				if (server.isStarted()) {
					startBtn.setEnabled(true);
					stopBtn.setEnabled(false);
					server.stopServer();
					infoPanel.setStarted(false);
				}
			}
		}
	}

}
