package cn.edu.bjtu.elctronicmall.view;

import java.util.LinkedList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.adapter.CartAdapter;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.CartDao;
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
	private ListView lv_cart;
	private LinearLayout linerlayout_cart_not_empty;
	private LinearLayout linerlayout_cart_empty;
	private CartDao cartDao;;

	public CartView(Context context, Bundle bundle) {
		super(context, bundle);
		goods = new LinkedList<Good>();
		BottomManager.getInstance().showCart();
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		dao = new GoodDao(context);
		showView = (ViewGroup) View.inflate(context, R.layout.cart, null);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("产看订单");
		TitleManager.getInstance().setTwoText("购物车");
		init();
		TitleManager.getInstance().getBtn_name_right()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						System.out.println("结算--------");
					}
				});
	}

	private void init() {
		cartDao = new CartDao();
		int count = GlobalData.SELECT_COUNT;
		int goodID = GlobalData.SELECT_GOODID;
		// List<Cart> cartInfos = cartDao.queryCartByUserId(database,
		// GlobalData.LOGIN_SUCCES);
		Good good = dao.findGoodById(database, goodID);
		goods.addFirst(good);
		adapter = new CartAdapter(goods, context, count);
		lv_cart = (ListView) showView.findViewById(R.id.lv_cart);
		lv_cart.setAdapter(adapter);
		double newprice = good.getNewprice();
		double totalMoney = count * newprice;
		TextView tv_count = (TextView) showView.findViewById(R.id.tv_count);
		TextView tv_totalmoney = (TextView) showView
				.findViewById(R.id.tv_totalmoney);
		tv_count.setText(count + "");
		tv_totalmoney.setText(totalMoney + "");
		long addGood = cartDao.addGood(database, GlobalData.LOGIN_SUCCES,
				totalMoney, 0, count);
		if (addGood != -1) {
			Toast.makeText(context, "添加成功", Toast.LENGTH_SHORT).show();
		}
		// }

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
