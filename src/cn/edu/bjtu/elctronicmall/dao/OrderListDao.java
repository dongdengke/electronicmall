package cn.edu.bjtu.elctronicmall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
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

	/**
	 * 
	 * 根据用户id查询订单
	 * 
	 * @param database
	 * @param userId
	 * @return
	 */
	public List<Orderlist> queryOrder(SQLiteDatabase database, int userId) {
		List<Orderlist> orderlists = new ArrayList<Orderlist>();
		Orderlist orderlist = null;
		Cursor cursor = database.query("orderlist", new String[] { "id",
				"goodId", "addressId", "orderno", "flag" }, "userId=?",
				new String[] { userId + "" }, null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			int goodId = cursor.getInt(1);
			int addressId = cursor.getInt(2);
			String orderno = cursor.getString(3);
			int flag = cursor.getInt(4);
			orderlist = new Orderlist();
			orderlist.setId(id);
			orderlist.setGoodId(goodId);
			orderlist.setAddressId(addressId);
			orderlist.setUserId(userId);
			orderlist.setOrderno(orderno);
			orderlist.setFlag(flag);
			orderlists.add(orderlist);
		}
		cursor.close();
		return orderlists;
	}
}
