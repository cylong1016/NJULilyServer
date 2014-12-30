package ui.frame.title;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import config.UIConfig;

/**
 * 最小化按钮；关闭按钮
 * @author cylong
 * @version 2014年12月11日 下午8:50:20
 */
public class TitleButton extends JLabel {

	/** serialVersionUID */
	private static final long serialVersionUID = 7046202963400208918L;

	/** 按钮大小 */
	private Dimension size = new Dimension(32, 20);

	public TitleButton() {
		this.setPreferredSize(size);
		this.setOpaque(true); // 不透明
		this.addMouseListener(new ButtonListener());
		this.setBackground(UIConfig.TITLE_BUTTON_BACK_COLOR);
	}

	public TitleButton(String text) {
		this();
		this.setText(text);
		this.setHorizontalAlignment(JLabel.CENTER);
	}

	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			TitleButton.this.setBackground(UIConfig.ENTERED_BTN_BACK_COLOR);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			TitleButton.this.setBackground(UIConfig.TITLE_BUTTON_BACK_COLOR);
		}
	}

}
