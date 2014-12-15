package ui.start;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import ui.ServerPanel;

import common.Common;

import config.UIConfig;

/**
 * @author cylong
 * @version 2014年12月15日 上午12:51:18
 */
public class ServerInfoPanel extends ServerPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 2323095456949909192L;

	private String hostAddr;
	private String hostName;
	private boolean isStarted;

	/** ServerInfoPanel尺寸 */
	private Dimension dimension = new Dimension(320, 190);

	/** 显示的内容的x坐标 */
	private int x = 50;
	/** 显示的内容的y坐标 */
	private int y = 70;
	/** 内容的行间距 */
	private int interval = 30;

	public ServerInfoPanel(String hostAddr, String hostName, boolean isStarted) {
		this.hostAddr = hostAddr;
		this.hostName = hostName;
		this.isStarted = isStarted;
		this.setSize(dimension);
		this.setBackground(UIConfig.TEXT_BACK_COLOR);
		this.setBorder(BorderFactory.createTitledBorder(getBorder(), "服务器信息", TitledBorder.CENTER, TitledBorder.TOP, UIConfig.TEXT_FONT));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paint(g2d);
		g2d.setFont(UIConfig.TEXT_FONT);
		g2d.setColor(UIConfig.TEXT_FORE_COLOR);
		g2d.drawString("主机地址：" + hostAddr, x, y);
		g2d.drawString("主机名称：" + hostName, x, y + interval);
		if (isStarted) {
			g2d.drawString("主机状态：开启", x, y + interval * 2);
		} else {
			g2d.drawString("主机状态：关闭", x, y + interval * 2);
		}
		g2d.drawString("运行时间：" + formatTime(hour, minute, second), x, y + interval * 3);
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
		if (isStarted) {
			new Time().start();
		} else {
			resetTime();
		}
		repaint();
	}

	/** 服务器运行的时间 */
	private int hour = 0;
	private int minute = 0;
	private int second = 0;

	private void resetTime() {
		hour = 0;
		minute = 0;
		second = 0;
	}

	/**
	 * 时间格式的转换
	 * @param hour
	 * @param minute
	 * @param second
	 * @return 00:00:00格式
	 * @author cylong
	 * @version 2014年12月15日 下午8:59:05
	 */
	private String formatTime(int hour, int minute, int second) {
		String hour_s = String.format("%2d", hour).replace(" ", "0");
		String minute_s = String.format("%2d", minute).replace(" ", "0");
		String second_s = String.format("%2d", second).replace(" ", "0");
		return hour_s + ":" + minute_s + ":" + second_s;
	}

	/**
	 * 计时器
	 * @author cylong
	 * @version 2014年12月15日 下午8:40:54
	 */
	private class Time extends Thread {

		@Override
		public void run() {
			while(true) {
				if (!isStarted) {
					resetTime();
					break;
				}
				Common.sleep(1000);
				second++;
				if (second == 60) {
					second -= 60;
					minute++;
					if (minute == 60) {
						minute -= 60;
						hour++;
					}
				}
				repaint();
			}
		}
	}

}
