package cn.edu.bjtu.elctronicmall.bean;

public class Good {
	/**
	 * 商品的唯一标识
	 */
	private Integer id;
	/**
	 * 商品名称
	 */
	private String name;
	/**
	 * 图片路径
	 */
	private String pic;
	/**
	 * 商品的原价
	 */
	private double price;
	/**
	 * 商品的现价
	 */

	private double newprice;
	/**
	 * 商品的评论id
	 */
	private Integer commentId;
	/**
	 * 商品的得分
	 */
	private Integer score;
	/**
	 * 商品的仓库所在地
	 */
	private String location;
	/**
	 * 商品的库存量
	 */
	private Integer inventory;
	/**
	 * 商品的上架时间
	 */
	private String shangjiadate;
	/**
	 * 商品的运费
	 */
	private double fare;
	/**
	 * 种类id
	 */
	private Integer categoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getNewprice() {
		return newprice;
	}

	public void setNewprice(double newprice) {
		this.newprice = newprice;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getShangjiadate() {
		return shangjiadate;
	}

	public void setShangjiadate(String shangjiadate) {
		this.shangjiadate = shangjiadate;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

}
