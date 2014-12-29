package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
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

	/** 按钮文本图片 */
	private Image IMG_BUTTON;
	/** 按钮背景图片 */
	private static final Image IMG_BUTTON_1 = new ImageIcon("images/button_1.png").getImage();
	/** 移动到按钮上的背景图片 */
	private static final Image IMG_BUTTON_2 = new ImageIcon("images/button_2.png").getImage();
	
	/** 是否移动到按钮上 */
	private boolean isMoved = false;
	/** 按钮大小，由传进来的文本图片决定 */
	private Dimension dimension;

	public ServerButton(String text) {
		super(text, JLabel.CENTER);
		dimension = new Dimension();
		this.setFont(UIConfig.BTN_FONT);	// 文本字体
		this.setForeground(UIConfig.BTN_FORE_COLOR);	// 文本颜色
		this.setOpaque(true);	// 设置为不透明，才会显示背景色
		this.addMouseListener(new ButtonListener());
		this.setBackground(UIConfig.BTN_BACK_COLOR);	// 按钮背景色
	}

	public ServerButton(Image textImg) {
		IMG_BUTTON = textImg;
		// 和按钮文本图片一样大
		dimension = new Dimension(IMG_BUTTON.getWidth(null), IMG_BUTTON.getHeight(null));
		this.setSize(dimension);
		this.addMouseListener(new ButtonListener());
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.setBackground(UIConfig.BTN_BACK_COLOR);
		super.setEnabled(enabled);
	}

	/**
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(isMoved) {
			g.drawImage(IMG_BUTTON_2, 0, 0, dimension.width, dimension.height, this);
		} else {
			g.drawImage(IMG_BUTTON_1, 0, 0, dimension.width, dimension.height, this);
		}
		g.drawImage(IMG_BUTTON, 0, 0, this);
	}

	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			isMoved = true;
			if (ServerButton.this.isEnabled()) {
				ServerButton.this.setBackground(UIConfig.ENTERED_BTN_BACK_COLOR);
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			isMoved = false;
			if (ServerButton.this.isEnabled()) {
				ServerButton.this.setBackground(UIConfig.BTN_BACK_COLOR);
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			isMoved = false;
		}

		public void mousePressed(MouseEvent e) {
			if (ServerButton.this.isEnabled()) {
				ServerButton.this.setBackground(UIConfig.BTN_BACK_COLOR);
			}
		}

		public void mouseReleased(MouseEvent e) {
			if (ServerButton.this.isEnabled()) {
				ServerButton.this.setBackground(UIConfig.ENTERED_BTN_BACK_COLOR);
			}
		}

	}
}
