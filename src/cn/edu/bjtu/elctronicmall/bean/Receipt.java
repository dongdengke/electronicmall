package cn.edu.bjtu.elctronicmall.bean;

public class Receipt {
	/**
	 * 发票的唯一标识
	 */
	private Integer id;
	/**
	 * 发票抬头
	 */
	private String title;
	/**
	 * 发票内容
	 */
	private String content;

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

}
