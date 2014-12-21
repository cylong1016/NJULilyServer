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

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginUserInfo other = (LoginUserInfo)obj;
		if (this.IP == null) {
			if (other.IP != null)
				return false;
		} else if (!this.IP.equals(other.IP))
			return false;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		if (this.password == null) {
			if (other.password != null)
				return false;
		} else if (!this.password.equals(other.password))
			return false;
		if (this.userName == null) {
			if (other.userName != null)
				return false;
		} else if (!this.userName.equals(other.userName))
			return false;
		return true;
	}

}
