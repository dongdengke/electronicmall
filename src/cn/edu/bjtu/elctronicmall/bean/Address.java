package cn.edu.bjtu.elctronicmall.bean;

/**
 * 用户订单填写的地址的javabean
 * 
 * @author dong
 * 
 */
public class Address {
	/**
	 * 地址的唯一标识
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 地区
	 */
	private String area;
	/**
	 * 详细信息
	 */
	private String detailInfo;
	/**
	 * 邮编
	 */
	private String zipecode;
	/**
	 * 状态 1 为已支付状态 0 为其他状态
	 */
	private Integer status;
	/**
	 * 收货人姓名
	 */
	private String name;
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDetailInfo() {
		return detailInfo;
	}

	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
	}

	public String getZipecode() {
		return zipecode;
	}

	public void setZipecode(String zipecode) {
		this.zipecode = zipecode;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}