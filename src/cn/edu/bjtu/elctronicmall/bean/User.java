package cn.edu.bjtu.elctronicmall.bean;

/**
 * 用户信息的封装
 * 
 * @author dong
 * 
 */
public class User {
	/**
	 * 用户的唯一标识
	 */
	private Integer id;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 用户密码
	 */
	private String password;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 用户电话
	 */
	private String phone;
	/**
	 * 用户等级
	 */
	private String level;
	/**
	 * 用户总积分
	 */
	private Integer totalscore;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(Integer totalscore) {
		this.totalscore = totalscore;
	}

}
