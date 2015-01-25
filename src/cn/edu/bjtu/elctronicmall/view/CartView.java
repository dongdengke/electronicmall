package cn.edu.bjtu.elctronicmall.view;

import java.util.LinkedList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.CartAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.BottomManager;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;

/**
 * 购物车界面
 * 
 * @author dong
 * 
 */
public class CartView extends BaseView {

	private SQLiteDatabase database;
	private GoodDao dao;
	private LinkedList<Good> goods;
	private CartAdapter adapter;
	private ListView lv_cart;;

	public CartView(Context context, Bundle bundle) {
		super(context, bundle);
		goods = new LinkedList<Good>();
		BottomManager.getInstance().showCart();
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READONLY);
		dao = new GoodDao(context);
		showView = (ViewGroup) View.inflate(context, R.layout.cart, null);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("去结算");
		TitleManager.getInstance().setTwoText("购物车");
		init();
	}

	private void init() {
		int count = GlobalData.SELECT_COUNT;
		int goodID = GlobalData.SELECT_GOODID;
		Good good = dao.findGoodById(database, goodID);
		goods.addFirst(good);
		adapter = new CartAdapter(goods, context, count);
		lv_cart = (ListView) showView.findViewById(R.id.lv_cart);
		lv_cart.setAdapter(adapter);

	}

	@Override
	public View getView(Context context) {
		// TODO Auto-generated method stub
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.CARTVIEW;
	}

}
