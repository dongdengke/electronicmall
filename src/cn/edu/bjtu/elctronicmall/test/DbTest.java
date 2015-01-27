package cn.edu.bjtu.elctronicmall.test;

import android.test.AndroidTestCase;
import cn.edu.bjtu.elctronicmall.db.DBHelper;

public class DbTest extends AndroidTestCase {
	public void test() {
		DBHelper dbHelper = new DBHelper(getContext());
		dbHelper.getWritableDatabase();
	}
}
