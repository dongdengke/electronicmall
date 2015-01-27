package cn.edu.bjtu.elctronicmall.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	public DBHelper(Context context) {
		super(context, "ec.db", null, 33);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		// db.execSQL("create table user(id integer primary key  autoincrement,username varchar(20) ,password varchar(20) ,email varchar(20) ,phone varchar(20) ,	level varchar(30) ,totalscore integer)");
		// db.execSQL("create table address(id integer primary key  autoincrement,userId integer ,province varchar(20) ,city varchar(20) ,area varchar(20) ,detailInfo varchar(100) ,zipecode varchar(20) ,status integer ,foreign key (userId) references user(id) )");
		// db.execSQL("create table receipt( id integer primary key  autoincrement, title varchar(20),content varchar(20))");
		//
		// db.execSQL(" create table comment( id integer primary key  autoincrement, userId integer ,score integer ,content varchar(100) ,foreign key(userId)references user(id))");
		// db.execSQL("create table category( id integer primary key  autoincrement,level integer , parentID integer ,name varchar(20))");
		// db.execSQL(" create table cart( id integer primary key  autoincrement,userId integer, totalMoney dobule ,sendScore integer ,count integer , foreign key(userId) references user(id))");
		// db.execSQL(" create table feedback(id integer primary key  autoincrement, userId integer ,content varchar(100),foreign key(userId)references user(id))");
		// db.execSQL("	 create table search( id integer primary key  autoincrement,userId integer ,goodId integer ,foreign key(userId) references user(id),foreign key(goodId) references good(id))");
		// db.execSQL("create table collection(id integer primary key  autoincrement,userId integer ,goodId integer ,foreign key(userId) references user(id),foreign key(goodId) references good(id))");
		// db.execSQL("create table help(id integer primary key  autoincrement,title varchar(20) , content varchar(100) ,status integer )");
		// db.execSQL(" create table good(id integer primary key  autoincrement, name varchar(20) , pic varchar(50) , price double , newprice double , commentId integer , score integer , location varchar(100) , inventory integer ,shangjiadate varchar(50) , fare double,categoryId integer ,foreign key (categoryId) references category(id),foreign key(commentId) references comment(id))");
		// db.execSQL("create table orderlist(id integer primary key autoincrement,userId integer,goodId integer,orderno varchar(20),paymethod varchar(20),sendtime varchar(20),sendmethod varchar(20),flag integer,receiptId integer,foreign key(userId) references user(id),foreign key(goodId) references good(id),foreign key(receiptId) references receipt(id))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("alter table cart add column goodId  integer,foreign key(goodId) references good(id)");
	}
}
