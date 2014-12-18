package ui.start;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

import ui.ServerButton;
import ui.ServerPanel;
import ui.ServerTable;
import config.UIConfig;
import data.userdata.LoginUserInfo;
import data.userdata.UserData;

/**
 * 显示当前登录的用户信息
 * @author cylong
 * @version 2014年12月16日 上午12:48:15
 */
public class ClientInfoPanel extends ServerPanel {

	/** serialVersionUID */
	private static final long serialVersionUID = 1368738531558639195L;

	/** ClientInfoPanel的尺寸 */
	private Dimension dimension = new Dimension(515, 460);
	/** ClientInfoPanel横轴内边距 */
	private int PaddingX = 25;
	/** ClientInfoPanel纵轴内边距 */
	private int PaddingY = 46;

	/** 当前登录用户信息的表格 */
	private ServerTable usersTable;

	/** 刷新信息按钮 */
	private ServerButton refreshBtn;
	/** 刷新按钮的大小 */
	private Dimension btnDimen = new Dimension(100, 30);
	/** 刷新按钮的位置 */
	private Point btnPoint = new Point((dimension.width - btnDimen.width) / 2, dimension.height - btnDimen.height - 10);

	public ClientInfoPanel() {
		this.setSize(dimension);
		this.setBorder(BorderFactory.createTitledBorder(getBorder(), "客户端信息", TitledBorder.CENTER, TitledBorder.TOP, UIConfig.TEXT_FONT));
		this.addUsersTable();
		this.addRefreshBtn();
	}

	/**
	 * 添加当前登录的用户的信息table
	 * @author cylong
	 * @version 2014年12月15日 下午10:40:36
	 */
	private void addUsersTable() {
		String[] columnNames = {"用户名", "密码", "姓名", "登录IP"};
		String[][] rowData = new String[UserData.usersInfo.size()][columnNames.length];
		for(int i = 0; i < UserData.usersInfo.size(); i++) {
			LoginUserInfo tempInfo = UserData.usersInfo.get(i);
			rowData[i][0] = tempInfo.userName;
			rowData[i][1] = tempInfo.password;
			rowData[i][2] = tempInfo.name;
			rowData[i][3] = tempInfo.IP;
		}
		usersTable = new ServerTable(columnNames, rowData);
		Point usersTablePoint = new Point(PaddingX, PaddingY);
		usersTable.setLocation(usersTablePoint);
		Dimension usersTableDimen = new Dimension(dimension.width - PaddingX * 2, dimension.height - PaddingY * 2);
		usersTable.setSize(usersTableDimen);
		usersTable.init();
		this.add(usersTable);
	}

	private void addRefreshBtn() {
		refreshBtn = new ServerButton("刷新信息");
		refreshBtn.setFont(new Font("黑体", Font.PLAIN, 20));	// 文本字体
		refreshBtn.setSize(btnDimen);
		refreshBtn.setLocation(btnPoint);
		refreshBtn.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				ClientInfoPanel.this.remove(usersTable);
				ClientInfoPanel.this.addUsersTable();
			}
		});
		this.add(refreshBtn);
	}

}
