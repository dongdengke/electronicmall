package cn.edu.bjtu.elctronicmall.bean;

public class Feedback {
	/**
	 * 反馈的唯一标识
	 */
	private Integer id;
	/**
	 * 用户id、
	 */

	private Integer userId;
	/**
	 * 反馈的内容
	 */
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
