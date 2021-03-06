package cn.edu.bjtu.elctronicmall.view;

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
import cn.edu.bjtu.elctronicmall.bean.Cart;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.CartDao;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

/**
 * 购物车界面
 * 
 * @author dong
 * 
 */
public class CartView extends BaseView {

	private SQLiteDatabase database;
	private GoodDao goodDao;
	private CartAdapter adapter;
	private ListView lv_cart;
	private LinearLayout linerlayout_cart_not_empty;
	private LinearLayout linerlayout_cart_empty;
	private CartDao cartDao;
	private Cart cart;
	private Good good;
	private int count;
	private TextView tv_count;
	private TextView tv_totalmoney;

	public CartView(final Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.cart, null);
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setLeftButtonText("删除");
		TitleManager.getInstance().setRightButtonText("产生订单");
		TitleManager.getInstance().setTwoText("购物车");
		linerlayout_cart_empty = (LinearLayout) showView
				.findViewById(R.id.linerlayout_cart_empty);
		linerlayout_cart_not_empty = (LinearLayout) showView
				.findViewById(R.id.linerlayout_cart_not_empty);
		tv_count = (TextView) showView.findViewById(R.id.tv_count);
		tv_totalmoney = (TextView) showView.findViewById(R.id.tv_totalmoney);
		lv_cart = (ListView) showView.findViewById(R.id.lv_cart);
		goodDao = new GoodDao(context);
		cartDao = new CartDao();
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		TitleManager.getInstance().getBtn_name_left()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int cartId = (int) GlobalData.CARTID;
						long rawNum = cartDao.deleteCartById(database, cartId);
						if (rawNum != -1)
							GlobalData.goods.removeFirst();
						Toast.makeText(context, "已经成功从购物车删除", 0).show();
					}
				});
		// 生成订单
		TitleManager.getInstance().getBtn_name_right()
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// 把购物车id传递到下一个界面
						if (GlobalData.goods.size() <= 0) {
							Toast.makeText(context, "您没有把任何商品加入购物车", 0).show();
							return;
						}
						UIManager.getInstance().changeVew(
								SelectAddressView.class, bundle);
					}
				});

	}

	@Override
	public View getView(Context context) {
		good = goodDao.findGoodById(database,
				GloableParams.LOOKHISTORY.getFirst());
		if (good == null) {
			linerlayout_cart_empty.setVisibility(View.VISIBLE);
			linerlayout_cart_not_empty.setVisibility(View.GONE);
			Toast.makeText(context, "购物车为空，赶紧去逛逛吧!", Toast.LENGTH_SHORT).show();
		} else {
			linerlayout_cart_empty.setVisibility(View.GONE);
			linerlayout_cart_not_empty.setVisibility(View.VISIBLE);
			double newprice = good.getNewprice();
			double totalMoney = GlobalData.GOODCOUNT * newprice;
			tv_count.setText(GlobalData.GOODCOUNT + "");
			tv_totalmoney.setText(totalMoney + "");
			adapter = new CartAdapter(GlobalData.goods, context,
					GlobalData.GOODCOUNT);
			GlobalData.goods.add(good);
		}
		lv_cart.setAdapter(adapter);
		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.CARTVIEW;
	}

}
