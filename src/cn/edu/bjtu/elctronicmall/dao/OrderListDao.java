package cn.edu.bjtu.elctronicmall.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.bjtu.elctronicmall.bean.Orderlist;

/**
 * 对订单进行操作的dao
 * 
 * @author dong
 * 
 */
public class OrderListDao {
	/**
	 * 生产一天订单
	 * 
	 * @param database
	 * @param orderlist
	 * @return
	 */
	public long addOrderList(SQLiteDatabase database, Orderlist orderlist) {
		ContentValues values = new ContentValues();
		values.put("userId", orderlist.getUserId());
		values.put("goodId", orderlist.getGoodId());
		values.put("addressId", orderlist.getAddressId());
		values.put("orderno", orderlist.getOrderno());
		values.put("flag", orderlist.getFlag());
		return database.insert("orderlist", null, values);
	}
}
