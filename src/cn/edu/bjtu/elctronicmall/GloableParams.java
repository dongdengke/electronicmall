package cn.edu.bjtu.elctronicmall;

import java.util.LinkedList;

public class GloableParams {
	/**
	 * 代理ip
	 */
	public static String PROXY_IP = "";
	/**
	 * 代理端口
	 */
	public static int PROXY_PORT = 0;
	/**
	 * 数据库所在位置
	 */
	public static final String PATH = "/data/data/cn.edu.bjtu.elctronicmall/files/ec.db";
	/**
	 * 存放用户所浏览过的商品的id
	 */
	public static LinkedList<Integer> LOOKHISTORY = new LinkedList<Integer>();

}
