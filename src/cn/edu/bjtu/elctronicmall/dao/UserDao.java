package cn.edu.bjtu.elctronicmall.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.bjtu.elctronicmall.bean.User;

/**
 * 操作用户信息的类
 * 
 * @author dong
 * 
 */
public class UserDao {
	/**
	 * 增加一条用户信息
	 * 
	 * @param database
	 * @param user
	 * @return
	 */
	public boolean addUser(SQLiteDatabase database, User user) {
		ContentValues values = new ContentValues();
		values.put("username", user.getUsername());
		values.put("email", user.getEmail());
		values.put("phone", user.getPhone());
		values.put("password", user.getPassword());
		long id = database.insert("user", null, values);
		if (id == -1) {
			return false;
		}
		return true;
	}

	/**
	 * 用户登陆操作
	 * 
	 * @param database
	 * @param username
	 * @param password
	 * @return
	 */
	public User login(SQLiteDatabase database, String username, String password) {
		Cursor cursor = database.query("user", new String[] { "id", "email",
				"phone", "level", "totalscore" }, "username=? and password=?",
				new String[] { username, password }, null, null, null);
		User user = new User();
		if (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String email = cursor.getString(1);
			String phone = cursor.getString(2);
			String level = cursor.getString(3);
			int totalscore = cursor.getInt(4);
			user.setId(id);
			user.setEmail(email);
			user.setPhone(phone);
			user.setLevel(level);
			user.setTotalscore(totalscore);
			user.setUsername(username);
			user.setPassword(password);
			return user;
		}

		return null;
	}
}
