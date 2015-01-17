package cn.edu.bjtu.elctronicmall.bean;

public class Help {
	/**
	 * 帮助的唯一标识
	 */
	private Integer id;
	/**
	 * 帮助的标题、
	 */

	private String title;
	/**
	 * 帮助的内容
	 */
	private String content;
	/**
	 * 帮助信息 的状态
	 */
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
