package main;

import ui.frame.ServerFrame;

/**
 * @author cylong
 * @version Nov 2, 2014 3:17:43 PM
 */
public class Main {

	public static void main(String[] args) {
		System.setProperty("awt.useSystemAAFontSettings", "on");
		System.setProperty("swing.aatext", "true");
		new ServerFrame();
	}
}
