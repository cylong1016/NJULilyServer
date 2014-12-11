package ui.frame;

import javax.swing.JFrame;

import ui.frame.title.TitlePanel;
import config.UIConfig;

/**
 * 服务器端主JFrame
 * @author cylong
 * @version 2014年12月10日 上午9:32:04
 */
public class ServerFrame extends JFrame {

	public static void main(String[] args) {
		new ServerFrame();
	}

	/** serialVersionUID */
	private static final long serialVersionUID = 5359481363535358093L;

	/** 标题栏 */
	private TitlePanel title;

	public ServerFrame() {
		title = new TitlePanel(this);
		this.add(title);
		this.setSize(UIConfig.WIDTH, UIConfig.HEIGHT);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setUndecorated(true);
		this.setVisible(true);
	}

}
