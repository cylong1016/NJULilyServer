package ui.frame.title;

import java.awt.Graphics;

import javax.swing.JPanel;

import config.UIConfig;

/**
 * frame上的标题栏
 * @author cylong
 * @version 2014年12月10日 上午10:41:49
 */
public class TitlePanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5595756092214353675L;
	
	public TitlePanel() {
		this.setLayout(null);
		this.setSize(UIConfig.WIDTH, 40);
		this.setLocation(0, 0);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawString("我是标题", 350, 30);
	}

}
