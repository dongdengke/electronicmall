package cn.edu.bjtu.elctronicmall.bean;

public class Collection {
	/**
	 * 搜索的唯一标识
	 */
	private Integer id;
	/**
	 * 用户id、
	 */

	private Integer userId;
	/**
	 * 商品的id
	 */
	private Integer goodId;

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

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

}
