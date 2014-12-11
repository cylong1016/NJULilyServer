package main;

import ui.frame.ServerFrame;
import ui.start.StartPanel;

/**
 * @author cylong
 * @version Nov 2, 2014 3:17:43 PM
 */
public class Main {

	public static void main(String[] args) {
		ServerFrame frame = new ServerFrame();
		StartPanel start = new StartPanel();
		frame.add(start);
	}
}
