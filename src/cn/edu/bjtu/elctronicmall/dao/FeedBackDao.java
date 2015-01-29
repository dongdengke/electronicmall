package cn.edu.bjtu.elctronicmall.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.bjtu.elctronicmall.bean.Feedback;

/**
 * 用户反馈信息的dao
 * 
 * @author dong
 * 
 */
public class FeedBackDao {
	/**
	 * 插入一条用户反馈
	 * 
	 * @param database
	 * @param feedback
	 * @return
	 */
	public long addFeedBack(SQLiteDatabase database, Feedback feedback) {
		ContentValues values = new ContentValues();
		values.put("userId", feedback.getUserId());
		values.put("content", feedback.getContent());
		return database.insert("feedback", null, values);
	}
}
