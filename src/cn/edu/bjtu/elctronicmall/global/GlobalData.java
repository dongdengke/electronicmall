package cn.edu.bjtu.elctronicmall.global;

import android.content.SharedPreferences;

/**
 * 存放系统的全局常量
 * 
 * @author dong
 * 
 */
public class GlobalData {
	/**
	 * 首页的唯一标识
	 */
	public static final int HOMEVIEW = 1;
	/**
	 * 第二个界面的唯一标识
	 */
	public static final int SECONDVIEW = 2;
	/**
	 * 限时抢购
	 */
	public static final int PANICBUYINGVIEW = 3;
	/**
	 * 促销品牌
	 */
	public static final int SALESVIEW = 4;
	/**
	 * 新品上架view
	 */
	public static final int NEWPRODUCTVIEW = 5;
	/**
	 * 热门商品信息view
	 */
	public static final int HOTPRODUCTVIEW = 6;
	/**
	 * 商品的详细信息界面
	 */
	public static final int GOOGINFOVIEW = 7;
	/**
	 * 我的账号界面
	 */
	public static final int MYACCOUNTVIEW = 8;
	/**
	 * 更多界面
	 */
	public static final int MOREVIEW = 9;
	/**
	 * 购物车界面
	 */
	public static final int CARTVIEW = 10;
	/**
	 * 用户注册
	 */
	public static final int REGISTERVIEW = 11;
	/**
	 * 用户登陆
	 */
	public static final int LOGINVIEW = 12;
	/**
	 * 地址管理界面
	 */
	public static final int ADDRESSVIEW = 13;
	/**
	 * 添加地址
	 */
	public static final int ADD_ADDRESSVIEW = 14;
	/**
	 * 修改地址
	 */
	public static final int UPDATEADDRESS = 15;
	/**
	 * 登陆成功的标识
	 */
	public static Integer LOGIN_SUCCES = -1;
	/**
	 * 用户选择的数量
	 */
	public static int SELECT_COUNT = -1;
	/**
	 * 用户选择的商品的id
	 */
	public static Integer SELECT_GOODID = -1;
	/**
	 * 是否保存为默认的标识
	 */
	public static SharedPreferences SP;
}
