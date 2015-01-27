package cn.edu.bjtu.elctronicmall.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.edu.bjtu.elctronicmall.bean.Address;

/**
 * 对地址进行操作的dao
 * 
 * @author dong
 * 
 */
public class AddressDao {
	/**
	 * 增加地址
	 * 
	 * @param database
	 * @param userId
	 * @param detailInfo
	 * @param zipecode
	 * @param status
	 * @return
	 */
	public long addAddress(SQLiteDatabase database, int userId, String phone,
			String detailInfo, String zipecode, int status) {
		ContentValues values = new ContentValues();
		values.put("userId", userId);
		values.put("phone", phone);
		values.put("detailInfo", detailInfo);
		values.put("zipecode", zipecode);
		values.put("status", status);
		return database.insert("address", null, values);
	}

	/**
	 * 修改地址信息
	 * 
	 * @param database
	 * @param name
	 * @param phone
	 * @param detailInfo
	 * @param zipecode
	 * @param status
	 * @param id
	 * @return
	 */
	public long updateAddress(SQLiteDatabase database, int userId,
			String phone, String detailInfo, String zipecode, int status, int id) {
		ContentValues values = new ContentValues();
		values.put("userId", userId);
		values.put("phone", phone);
		values.put("detailInfo", detailInfo);
		values.put("zipecode", zipecode);
		values.put("status", status);
		return database.update("address", values, "id=?", new String[] { id
				+ "" });
	}

	/**
	 * 根据用户id来查询用户的地址信息
	 * 
	 * @param database
	 * @param userId
	 * @return
	 */
	public List<Address> queryAddressByUserId(SQLiteDatabase database,
			int userId) {
		Cursor cursor = database.query("address", new String[] { "id",
				"detailInfo", "zipecode", "status" }, "userId=?",
				new String[] { userId + "" }, null, null, null);
		List<Address> addressInfos = new ArrayList<Address>();
		Address address = null;
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String detailInfo = cursor.getString(1);
			String zipecode = cursor.getString(2);
			int status = cursor.getInt(3);
			address = new Address();
			address.setId(id);
			address.setDetailInfo(detailInfo);
			address.setZipecode(zipecode);
			address.setStatus(status);
			addressInfos.add(address);

		}
		cursor.close();
		return addressInfos;
	}

	/**
	 * 根据地址id查询地址
	 * 
	 * @param database
	 * @param id
	 * @return
	 */
	public Address queryAddressByAddressId(SQLiteDatabase database, int id) {
		Cursor cursor = database.query("address", new String[] { "userId",
				"detailInfo", "zipecode", "status" }, "id=?", new String[] { id
				+ "" }, null, null, null);
		Address address = null;
		while (cursor.moveToNext()) {
			int userId = cursor.getInt(0);
			String detailInfo = cursor.getString(1);
			String zipecode = cursor.getString(2);
			int status = cursor.getInt(3);
			address = new Address();
			address.setUserId(userId);
			address.setDetailInfo(detailInfo);
			address.setZipecode(zipecode);
			address.setStatus(status);
		}
		cursor.close();
		return address;
	}

	/**
	 * 修改是否是默认地址
	 * 
	 * @param database
	 * @param userId
	 */
	public void updateDefault(SQLiteDatabase database, String detailInfo,
			int status) {
		ContentValues values = new ContentValues();
		values.put("status", status);
		database.update("address", values, "detailInfo=?",
				new String[] { detailInfo });
	}

	/**
	 * 根据id删除地址
	 * 
	 * @param database
	 * @param id
	 */
	public long deleteAddressById(SQLiteDatabase database, int id) {
		return database.delete("address", "id=?", new String[] { id + "" });
	}
}
