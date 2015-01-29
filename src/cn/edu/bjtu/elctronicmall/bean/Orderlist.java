package cn.edu.bjtu.elctronicmall.bean;

/**
 * 订单信息的封装
 * 
 * @author dong
 * 
 */
public class Orderlist {
	/**
	 * 订单的唯一标识
	 */
	private Integer id;
	/**
	 * 
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 
	 * 商品id
	 */
	private Integer goodId;
	/**
	 * 
	 * 订单编号
	 */
	private String orderno;
	/**
	 * 
	 * 支付方式
	 */
	private String paymethod;
	/**
	 * 
	 * 送货时间
	 */
	private String sendtime;
	/**
	 * 
	 * 送货方式
	 */
	private String sendmethod;
	/**
	 * 
	 * 订单的状态 0 未支付 1 支付
	 */
	private Integer flag;
	/**
	 * 
	 * 发票id
	 */
	private Integer receiptId;
	/**
	 * 地址id
	 */
	private Integer addressId;
	/**
	 * 购物车id
	 */
	private Integer cartId;

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
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

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getSendmethod() {
		return sendmethod;
	}

	public void setSendmethod(String sendmethod) {
		this.sendmethod = sendmethod;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

}
