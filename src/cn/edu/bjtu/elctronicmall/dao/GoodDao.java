package cn.edu.bjtu.elctronicmall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
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
		cursor.close();
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
		cursor.close();
		return goods;
	}

	/**
	 * 根据id查询商品
	 * 
	 * @param db
	 * @param id
	 * @return
	 */
	public Good findGoodById(SQLiteDatabase db, int id) {
		Cursor cursor = db.query("good", new String[] { "name", "pic", "price",
				"newprice", "commentId", "score", "location", "inventory",
				"shangjiadate", "fare", "categoryId" }, "id=?",
				new String[] { id + "" }, null, null, null);
		Good good = null;
		while (cursor.moveToNext()) {
			good = new Good();
			String name = cursor.getString(0);
			String pic = cursor.getString(1);
			double price = cursor.getDouble(2);
			double newprice = cursor.getDouble(3);
			int commentId = cursor.getInt(4);
			int score = cursor.getInt(5);
			String location = cursor.getString(6);
			int inventory = cursor.getInt(7);
			String shangjiadate = cursor.getString(8);
			double fare = cursor.getDouble(9);
			int categoryId = cursor.getInt(10);
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
		}
		cursor.close();
		return good;
	}

	/**
	 * 更新商品的库存
	 * 
	 * @param database
	 * @param good
	 * @param count
	 */
	public long updateRemainCount(SQLiteDatabase database, int goodId, int count) {
		ContentValues values = new ContentValues();
		Good good = findGoodById(database, goodId);
		int remain = good.getInventory() - count;
		values.put("inventory", remain);
		return database.update("good", values, "id=?", new String[] { goodId
				+ "" });
	}
}
