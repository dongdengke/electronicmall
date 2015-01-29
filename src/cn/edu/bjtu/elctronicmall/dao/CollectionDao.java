package cn.edu.bjtu.elctronicmall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.bjtu.elctronicmall.bean.Collection;

/**
 * 用户收藏的dao
 * 
 * @author dong
 * 
 */
public class CollectionDao {
	/**
	 * 添加用户收藏
	 * 
	 * @param database
	 * @param collection
	 * @return
	 */
	public long addCollection(SQLiteDatabase database, Collection collection) {
		ContentValues values = new ContentValues();
		values.put("userId", collection.getUserId());
		values.put("goodId", collection.getGoodId());
		return database.insert("collection", null, values);
	}

	public List<Collection> queryByUserId(SQLiteDatabase database, int userId) {
		Cursor cursor = database.query("collection", new String[] { "id",
				"goodId" }, "userId=?", new String[] { userId + "" }, null,
				null, null);
		List<Collection> collections = new ArrayList<Collection>();
		Collection collection = null;
		while (cursor.moveToNext()) {
			collection = new Collection();
			int id = cursor.getInt(0);
			int goodId = cursor.getInt(1);
			collection.setId(id);
			collection.setGoodId(goodId);
			collection.setUserId(userId);
			collections.add(collection);
		}
		cursor.close();
		return collections;
	}
}
