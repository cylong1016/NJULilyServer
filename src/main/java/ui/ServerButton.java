package ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import config.UIConfig;

/**
 * 所有button的父类
 * @author cylong
 * @version 2014年12月12日 上午4:00:21
 */
public class ServerButton extends JLabel {

	/** serialVersionUID */
	private static final long serialVersionUID = -5073422084920844212L;

	public ServerButton(String text) {
		super(text, JLabel.CENTER);
		this.setFont(UIConfig.BTN_FONT);	// 文本字体
		this.setForeground(UIConfig.BTN_FORE_COLOR);	// 文本颜色
		this.setOpaque(true);
		this.addMouseListener(new ButtonListener());
		this.setBackground(UIConfig.BTN_BACK_COLOR);
	}

	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			ServerButton.this.setBackground(Color.RED);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			ServerButton.this.setBackground(UIConfig.BTN_BACK_COLOR);
		}
	}
}
