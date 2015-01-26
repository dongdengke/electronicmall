package cn.edu.bjtu.elctronicmall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.bjtu.elctronicmall.bean.Cart;

/**
 * 对购物车进行操作的dao
 * 
 * @author dong
 * 
 */
public class CartDao {
	/**
	 * 向购物车共添加记录
	 * 
	 * @param database
	 * @param userId
	 * @param totalMoney
	 * @param sendScore
	 * @param count
	 * @return
	 */
	public long addGood(SQLiteDatabase database, int userId, double totalMoney,
			int sendScore, int count) {
		ContentValues values = new ContentValues();
		values.put("userId", userId);
		values.put("totalMoney", totalMoney);
		values.put("sendScore", sendScore);
		values.put("count", count);
		return database.insert("cart", null, values);
	}

	/**
	 * 根据用户id来查询用户的购物车信息
	 * 
	 * @param database
	 * @param userId
	 * @return
	 */
	public List<Cart> queryCartByUserId(SQLiteDatabase database, int userId) {
		Cursor cursor = database.query("cart", new String[] { "id",
				"totalMoney", "sendScore", "count" }, "userId=?",
				new String[] { userId + "" }, null, null, null);
		List<Cart> cartInfos = new ArrayList<Cart>();
		Cart cart = null;
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			double totalMoney = cursor.getDouble(1);
			int sendScore = cursor.getInt(2);
			int count = cursor.getInt(3);
			cart = new Cart();
			cart.setId(id);
			cart.setTotalMoney(totalMoney);
			cart.setSendScore(sendScore);
			cart.setCount(count);
			cartInfos.add(cart);

		}
		cursor.close();
		return cartInfos;
	}
}
