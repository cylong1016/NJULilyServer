package ui.start;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import config.UIConfig;

/**
 * @author cylong
 * @version 2014年12月15日 上午12:51:18
 */
public class ServerInfoPanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2323095456949909192L;

	private String hostAddr;
	private String hostName;
	private boolean isStarted;

	private int panelX = 0;
	private int panelY = 0;
	private int panelW = 600;
	private int panelH = 364;

	/** 显示的内容的x坐标 */
	private int x = 190;
	/** 显示的内容的y坐标 */
	private int y = 150;
	/** 内容的行间距 */
	private int interval = 30;

	public ServerInfoPanel(String hostAddr, String hostName, boolean isStarted) {
		this.hostAddr = hostAddr;
		this.hostName = hostName;
		this.isStarted = isStarted;
		this.setLayout(null);
		this.setBounds(panelX, panelY, panelW, panelH);
		this.setBackground(UIConfig.TEXT_BACK_COLOR);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g2d);
		g2d.setFont(UIConfig.TEXT_FONT);
		g2d.setColor(UIConfig.TEXT_FORE_COLOR);
		g2d.drawString("主机IP：" + hostAddr, x, y);
		g2d.drawString("主机名称：" + hostName, x, y + interval);
		if (isStarted) {
			g2d.drawString("主机状态：开启", x, y + interval * 2);
		} else {
			g2d.drawString("主机状态：关闭", x, y + interval * 2);
		}
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
		repaint();
	}

}
