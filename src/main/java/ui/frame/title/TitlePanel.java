package ui.frame.title;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ui.frame.ServerFrame;
import config.UIConfig;

/**
 * frame上的标题栏
 * @author cylong
 * @version 2014年12月10日 上午10:41:49
 */
public class TitlePanel extends JPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5595756092214353675L;
	
	/** TitlePanel的高度 */
	private int height = 40;
	/** 关闭按钮 */
	private TitleButton exit;
	/** 最小化按钮 */
	private TitleButton min;
	/** 按钮大小 */
	private Dimension size = new Dimension(32, 32);
	/** 主frame，主要为了最小化使用其对象 */
	private ServerFrame frame;
	
	public TitlePanel(ServerFrame frame) {
		this.frame = frame;
		this.setLayout(null);
		this.setSize(UIConfig.WIDTH, height);
		this.setLocation(0, 0);
		addTitleButton(); // 添加最大化最小化按钮
	}
	
	private void addTitleButton() {
		TitleButtonListener listener = new TitleButtonListener();
		exit = new TitleButton();
		exit.setSize(size);
		exit.setLocation(UIConfig.WIDTH - exit.getWidth() - 1, 1);
		exit.addActionListener(listener);
		this.add(exit);
		min = new TitleButton();
		min.setSize(size);
		min.setLocation(UIConfig.WIDTH - min.getWidth() * 2 - 2, 1);
		min.addActionListener(listener);
		this.add(min);
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawString("我是标题", 350, 30);
	}
	
	private class TitleButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == exit) {
				System.exit(0);
			} else if(e.getSource() == min) {
				frame.setExtendedState(JFrame.ICONIFIED);
			}
		}
		
	}

}
