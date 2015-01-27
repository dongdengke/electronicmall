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
	private int goodId;

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
	public long addGood(SQLiteDatabase database, Cart cart) {
		ContentValues values = new ContentValues();
		values.put("userId", cart.getUserId());
		values.put("totalMoney", cart.getTotalMoney());
		values.put("sendScore", cart.getSendScore());
		values.put("count", cart.getCount());
		values.put("goodId", cart.getGoodId());

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

	/**
	 * 根据购物车id来查询用户的购物车信息
	 * 
	 * @param database
	 * @param id
	 * @return
	 */
	public Cart queryCartByCartId(SQLiteDatabase database, int id) {
		Cursor cursor = database.query("cart", new String[] { "userId",
				"goodId", "totalMoney", "sendScore", "count" }, "id=?",
				new String[] { id + "" }, null, null, null);
		List<Cart> cartInfos = new ArrayList<Cart>();
		Cart cart = null;
		while (cursor.moveToNext()) {
			int userId = cursor.getInt(0);
			int goodId = cursor.getInt(1);
			double totalMoney = cursor.getDouble(2);
			int sendScore = cursor.getInt(3);
			int count = cursor.getInt(4);
			cart = new Cart();
			cart.setId(id);
			cart.setTotalMoney(totalMoney);
			cart.setSendScore(sendScore);
			cart.setCount(count);

		}
		cursor.close();
		return cart;
	}

	/**
	 * 根据购物车id来查询goodId
	 * 
	 * @param database
	 * @param id
	 * @return
	 */
	public int queryGoodId(SQLiteDatabase database, int id) {
		Cursor cursor = database.query("cart", new String[] { "goodId" },
				"id=?", new String[] { id + "" }, null, null, null);
		Cart cart = null;
		while (cursor.moveToNext()) {
			goodId = cursor.getInt(0);
		}
		cursor.close();
		return goodId;
	}
}
