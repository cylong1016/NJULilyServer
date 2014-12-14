package config;

import java.awt.Color;
import java.awt.Font;

import common.ParseXML;

/**
 * UI的配置
 * @author cylong
 * @version 2014年12月10日 上午10:12:09
 */
public class UIConfig {

	/** 界面的宽 */
	public static int WIDTH;
	/** 界面的高 */
	public static int HEIGHT;

	static {
		ParseXML parse = new ParseXML("config/UI.xml", "UISize");
		WIDTH = Integer.parseInt(parse.getValue("width"));
		HEIGHT = Integer.parseInt(parse.getValue("height"));
	}

	/** 标题栏高 */
	public static int TITLE_HEIGHT;
	/** 标题字体 */
	public static Font TITLE_FONT = new Font("黑体", Font.PLAIN, 25);
	/** 标题字体颜色 */
	public static Color TITLE_FORE_COLOR = Color.BLACK;
	/** 标题背景颜色 */
	public static Color TITLE_BACK_COLOR = Color.CYAN;
	/** 标题栏按钮的宽【最小化，关闭按钮】 */
	public static int TITLE_BTN_W;
	/** 标题栏按钮的高【最小化，关闭按钮】 */
	public static int TITLE_BTN_H;

	static {
		ParseXML parse = new ParseXML("config/UI.xml", "Title");
		TITLE_HEIGHT = Integer.parseInt(parse.getValue("height"));
		TITLE_BTN_W = Integer.parseInt(parse.getValue("title_btn_width"));
		TITLE_BTN_H = Integer.parseInt(parse.getValue("title_btn_height"));
	}

	/** button字体 */
	public static Font BTN_FONT;
	/** button字体颜色 */
	public static Color BTN_FORE_COLOR;
	/** button背景颜色 */
	public static Color BTN_BACK_COLOR;

	static {
		ParseXML parse = new ParseXML("config/UI.xml", "Button");
		int size = Integer.parseInt(parse.getValue("size")); // 字体大小
		String style = parse.getValue("style");
		BTN_FONT = new Font(style, Font.PLAIN, size);
		int fontR = Integer.parseInt(parse.getValue("fontR"));
		int fontG = Integer.parseInt(parse.getValue("fontG"));
		int fontB = Integer.parseInt(parse.getValue("fontB"));
		BTN_FORE_COLOR = new Color(fontR, fontG, fontB);
		int button_backR = Integer.parseInt(parse.getValue("button_backR"));
		int button_backG = Integer.parseInt(parse.getValue("button_backG"));
		int button_backB = Integer.parseInt(parse.getValue("button_backB"));
		BTN_BACK_COLOR = new Color(button_backR, button_backG, button_backB);
	}

	/** 文本字体 */
	public static Font TEXT_FONT = new Font("黑体", Font.PLAIN, 20);
	/** 文本字体颜色 */
	public static Color TEXT_FORE_COLOR = Color.BLACK;
	/** 文本背景颜色 */
	public static Color TEXT_BACK_COLOR = Color.CYAN;

}
