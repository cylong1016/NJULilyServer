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
	/** 主界面的背景色 */
	public static Color MAIN_COLOR = Color.WHITE;
	
	static {
		ParseXML parse = new ParseXML("config/UI.xml", "UISize");
		WIDTH = Integer.parseInt(parse.getValue("width"));
		HEIGHT = Integer.parseInt(parse.getValue("height"));
	}

	/*----------------------------标题配置--------------------------------*/
	/** 标题栏高 */
	public static int TITLE_HEIGHT;
	/** 标题字体 */
	public static Font TITLE_FONT = new Font("黑体", Font.PLAIN, 25);
	/** 标题字体颜色 */
	public static Color TITLE_FORE_COLOR = Color.BLACK;
	/** 标题背景颜色 */
	public static Color TITLE_BACK_COLOR = new Color(0x2D2D30);
	/** 标题按钮背景颜色 */
	public static Color TITLE_BUTTON_BACK_COLOR = Color.LIGHT_GRAY;
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
	/*----------------------------标题配置--------------------------------*/

	/*----------------------------通用按钮配置--------------------------------*/
	/** button字体 */
	public static Font BTN_FONT = new Font("黑体", Font.PLAIN, 30);
	/** button字体颜色 */
	public static Color BTN_FORE_COLOR = Color.BLACK;
	/** button背景颜色 */
	public static Color BTN_BACK_COLOR = Color.LIGHT_GRAY;
	/** 移动到button上的背景颜色 */
	public static Color ENTERED_BTN_BACK_COLOR = new Color(0xEA6060);
	/*----------------------------通用按钮配置--------------------------------*/
	
	/*----------------------------文本配置--------------------------------*/
	/** 文本字体 */
	public static Font TEXT_FONT = new Font("黑体", Font.PLAIN, 20);
	/** 文本字体颜色 */
	public static Color TEXT_FORE_COLOR = new Color(0xFFFFFF);
	/** 文本背景颜色 */
	public static Color TEXT_BACK_COLOR = new Color(0xEEEEEE);
	/*----------------------------文本配置--------------------------------*/

	/*----------------------------表格配置--------------------------------*/
	/** 表格字体 */
	public static Font TABLE_FONT = new Font("黑体", Font.PLAIN, 13);
	/** 表格字体颜色 */
	public static Color TABLE_FORE_COLOR = Color.WHITE;
	/** 表格边框颜色 */
	public static Color TABLE_BORDER_COLOR = Color.LIGHT_GRAY;
	/** 表头前景颜色 */
	public static Color TABLE_HEADER_FORE_COLOR = Color.WHITE;
	/** 表头背景颜色 */
	public static Color TABLE_HEADER_BACK_COLOR = new Color(0x22355F);
	/** 选择后的前景色 */
	public static Color TABLE_SELECTIONFORE = Color.BLACK;
	/*----------------------------表格配置--------------------------------*/

}
