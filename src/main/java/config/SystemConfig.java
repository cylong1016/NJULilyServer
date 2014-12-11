package config;

import common.ParseXML;

/**
 * 系统配置
 * @author cylong
 * @version 2014年12月12日 上午3:32:45
 */
public class SystemConfig {

	/** 系统标题 */
	public static String TITLE;
	
	static {
		ParseXML parse = new ParseXML("config/System.xml", "Title");
		TITLE = parse.getValue("value");
	}
	
}
