package cn.edu.bjtu.elctronicmall.global;

import java.util.LinkedList;

import android.content.SharedPreferences;
import cn.edu.bjtu.elctronicmall.bean.Good;

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
	 * 订单信息
	 */
	public static final int ORDERVIEW = 16;
	/**
	 * 选择地址
	 */
	public static final int SELECT_ADDRESSVIEW = 17;
	/**
	 * 搜索
	 */
	public static final int SEARCHVIEW = 18;
	/**
	 * 用户反馈
	 */
	public static final int FEEDBACK = 19;
	/**
	 * 用户收藏
	 */
	public static final int COLLECTIONVIEW = 20;
	/**
	 * 我的所有订单
	 */
	public static final int MYALLORDERVIEW = 21;
	/**
	 * 订单条目
	 */
	public static final int ORDERITEMVIEW = 22;
	/**
	 * 用户浏览历史
	 */
	public static final int HISTORYVIEW = 23;
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
	/**
	 * 购物车id
	 */
	public static long CARTID;
	/**
	 * 用户选择的商品的集合
	 */
	public static LinkedList<Good> goods = new LinkedList<Good>();
	/**
	 * 用户选择的数量
	 */
	public static int GOODCOUNT;
	/**
	 * 购物车界面
	 */
	public static final int CARTVIEW1 = 101;

}
