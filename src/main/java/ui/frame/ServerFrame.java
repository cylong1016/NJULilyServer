package ui.frame;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.UIManager;

import ui.frame.title.TitlePanel;

import common.Common;

import config.SystemConfig;
import config.UIConfig;

/**
 * 服务器端主JFrame
 * @author cylong
 * @version 2014年12月10日 上午9:32:04
 */
public class ServerFrame extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = 5359481363535358093L;

	/** 标题栏 */
	private TitlePanel title;
	/** Frame透明度 */
	private float hyalineValue = 0f;

	public ServerFrame() {
		// 标题栏
		title = new TitlePanel(this);
		this.add(title);
		this.setTitle(SystemConfig.TITLE);
		this.setSize(UIConfig.WIDTH, UIConfig.HEIGHT);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDragable(); // 设置可以拖动
		this.setOSStyle(); // 设置为windows风格
		new HyalineValue().start(); // 透明度渐变启动界面
	}

	/**
	 * 透明度渐变启动界面
	 * @author cylong
	 * @version 2014年12月12日 上午3:25:27
	 */
	private class HyalineValue extends Thread {

		public void run() {
			while(true) {
				Common.sleep(20);
				hyalineValue += 0.025f;
				if (hyalineValue > 1) {
					hyalineValue = 1;
				}
				setOpacity(hyalineValue);
				if (hyalineValue == 1) {
					break;
				}

			}
		}
	}

	private Point loc = null;
	private Point tmp = null;
	private boolean isDragged = false;

	/**
	 * 设置Frame可以拖动
	 * @author cylong
	 * @version 2014年12月12日 上午3:22:12
	 */
	public void setDragable() {
		this.addMouseListener(new MouseAdapter() {

			public void mouseReleased(java.awt.event.MouseEvent e) {
				isDragged = false;
			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				tmp = new Point(e.getX(), e.getY());
				isDragged = true;
			}

		});

		this.addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				if (isDragged) {
					loc = new Point(getLocation().x + e.getX() - tmp.x, getLocation().y + e.getY() - tmp.y);
					setLocation(loc);
				}
			}
		});
	}

	/**
	 * 设置为相应操作系统风格
	 * @author cylong
	 * @version 2014年12月12日 上午5:22:27
	 */
	private void setOSStyle() {
		Properties props = System.getProperties(); //系统属性
		String osName = props.getProperty("os.name");	// 操作系统名称
		if (osName.contains("Windows")) {
			try {
				String lookAndfeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
				UIManager.setLookAndFeel(lookAndfeel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (osName.contains("Mac")) {
			try {
				String lookAndfeel = "com.sun.java.swing.plaf.mac.MacLookAndFeel";
				UIManager.setLookAndFeel(lookAndfeel);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
