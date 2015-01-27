package cn.edu.bjtu.elctronicmall.bean;

/**
 * 购物车信息的封装
 * 
 * @author dong
 * 
 */
public class Cart {
	/**
	 * 购物车的唯一标识
	 */
	private Integer id;
	/**
	 * 用户id、
	 */

	private Integer userId;
	/**
	 * 总金额
	 */
	private double totalMoney;
	/**
	 * 此次交易送的积分
	 */
	private Integer sendScore;
	/**
	 * 商品的数量
	 */
	private Integer count;
	private Integer goodId;

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

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

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Integer getSendScore() {
		return sendScore;
	}

	public void setSendScore(Integer sendScore) {
		this.sendScore = sendScore;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
