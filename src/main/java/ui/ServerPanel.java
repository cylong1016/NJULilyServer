package ui;

import javax.swing.JPanel;

import config.UIConfig;

/**
 * 所有panel的父类
 * @author cylong
 * @version 2014年12月10日 上午9:36:20
 */
public class ServerPanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 7959972677335276198L;

	public ServerPanel() {
		this.setLocation(0, UIConfig.TITLE_HEIGHT);	// 向下偏移TITLE_HEIGHT，防止和TitlePanel重合
		this.setSize(UIConfig.WIDTH, UIConfig.HEIGHT - UIConfig.TITLE_HEIGHT);
		this.setLayout(null);
	}

}
