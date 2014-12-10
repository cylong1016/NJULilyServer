package config;

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

}
