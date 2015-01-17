package cn.edu.bjtu.elctronicmall.bean;

/**
 * 评论信息的封装
 * 
 * @author dong
 * 
 */
public class Comment {
	/**
	 * 评论的唯一标识
	 */
	private Integer id;
	/**
	 * 用户id、
	 */

	private Integer userId;
	/**
	 * 得分
	 */
	private Integer score;
	/**
	 * 评论内容
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

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
