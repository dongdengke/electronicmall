package cn.edu.bjtu.elctronicmall.bean;

public class Category {
	/**
	 * 分类的唯一标识
	 */
	private Integer id;
	/**
	 * 分类的级别、
	 */

	private Integer level;
	/**
	 * 上级的id
	 */
	private Integer parentID;
	/**
	 * 分类的名称
	 */
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
