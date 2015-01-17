package cn.edu.bjtu.elctronicmall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.bjtu.elctronicmall.bean.Good;

/**
 * 有关商品的操作
 * 
 * @author dong
 * 
 */
public class GoodDao {
	private Context context;

	public GoodDao(Context context) {
		super();
		this.context = context;
	}

	/**
	 * 根据种类id查询商品
	 * 
	 * @param cATEGORYID_PANICCBUY
	 */
	public List<Good> findGoodByCategory(SQLiteDatabase db, int categoryId) {
		Cursor cursor = db.query("good", new String[] { "id", "name", "pic",
				"price", "newprice", "commentId", "score", "location",
				"inventory", "shangjiadate", "fare" }, "categoryId=?",
				new String[] { categoryId + "" }, null, null, null);
		List<Good> goods = new ArrayList<Good>();
		Good good = null;
		while (cursor.moveToNext()) {
			good = new Good();
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			String pic = cursor.getString(2);
			double price = cursor.getDouble(3);
			double newprice = cursor.getDouble(4);
			int commentId = cursor.getInt(5);
			int score = cursor.getInt(6);
			String location = cursor.getString(7);
			int inventory = cursor.getInt(8);
			String shangjiadate = cursor.getString(9);
			double fare = cursor.getDouble(10);
			good.setId(id);
			good.setName(name);
			good.setPic(pic);
			good.setPrice(price);
			good.setNewprice(newprice);
			good.setCommentId(commentId);
			good.setScore(score);
			good.setLocation(location);
			good.setInventory(inventory);
			good.setShangjiadate(shangjiadate);
			good.setFare(fare);
			good.setCategoryId(categoryId);
			goods.add(good);
		}
		return goods;
	}

	/**
	 * 根据种类id查询商品
	 * 
	 * @param db
	 * @return
	 */
	public List<Good> findAllGood(SQLiteDatabase db) {
		Cursor cursor = db.query("good", new String[] { "id", "name", "pic",
				"price", "newprice", "commentId", "score", "location",
				"inventory", "shangjiadate", "fare", "categoryId" }, null,
				null, null, null, null);
		List<Good> goods = new ArrayList<Good>();
		Good good = null;
		while (cursor.moveToNext()) {
			good = new Good();
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			String pic = cursor.getString(2);
			double price = cursor.getDouble(3);
			double newprice = cursor.getDouble(4);
			int commentId = cursor.getInt(5);
			int score = cursor.getInt(6);
			String location = cursor.getString(7);
			int inventory = cursor.getInt(8);
			String shangjiadate = cursor.getString(9);
			double fare = cursor.getDouble(10);
			int categoryId = cursor.getInt(11);
			good.setId(id);
			good.setName(name);
			good.setPic(pic);
			good.setPrice(price);
			good.setNewprice(newprice);
			good.setCommentId(commentId);
			good.setScore(score);
			good.setLocation(location);
			good.setInventory(inventory);
			good.setShangjiadate(shangjiadate);
			good.setFare(fare);
			good.setCategoryId(categoryId);
			goods.add(good);
		}
		return goods;
	}
}
