package ui.start;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ui.ServerButton;

/**
 * @author cylong
 * @version 2014年12月15日 上午2:15:48
 */
public class StartButton extends ServerButton {

	/** serialVersionUID */
	private static final long serialVersionUID = 3438183678690520076L;

	/** 按钮原始尺寸 */
	private Dimension dimension = new Dimension(250, 50);

	/** 鼠标移动到上面按钮宽放大的大小 */
	private int exW = 0;
	/** 鼠标移动到上面按钮高放大的大小 */
	private int exH = 0;

	public StartButton(String text) {
		super(text);
		this.setSize(dimension);
		this.addMouseListener(new ButtonListener());
	}

	private class ButtonListener extends MouseAdapter {

		@Override
		public void mouseEntered(MouseEvent e) {
			StartButton.this.setBounds(getX() - exW, getY() - exH, dimension.width + exW * 2, dimension.height + exH * 2);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			StartButton.this.setBounds(getX() + exW, getY() + exH, dimension.width, dimension.height);
		}
	}

}
