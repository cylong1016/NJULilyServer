package data.userdata;

/**
 * 登录用户的信息
 * @author cylong
 * @version 2014年12月15日 下午7:58:44
 */
public class LoginUserInfo {

	public String userName;
	public String password;
	public String name;
	/** 客户端的IP */
	public String IP;

	public LoginUserInfo(String userName, String password, String name, String iP) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.IP = iP;
	}

}
