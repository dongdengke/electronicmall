package cn.edu.bjtu.elctronicmall.view;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Address;
import cn.edu.bjtu.elctronicmall.bean.Cart;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.dao.AddressDao;
import cn.edu.bjtu.elctronicmall.dao.CartDao;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;
import cn.edu.bjtu.elctronicmall.manager.UIManager;

public class OrderItemView extends BaseView {

	private SQLiteDatabase database;
	private TextView tv_orderitem_orderno;
	private TextView tv_orderitem_order_name;
	private TextView tv_orderitem_order_phone;
	private TextView tv_orderitem_order_detail;
	private TextView tv_orderitem_order_good_name;
	private TextView tv_orderitem_order_good_count;
	private TextView tv_orderitem_order_good_newprice;
	private TextView tv_orderitem_order_good_totalmoney;
	private TextView tv_orderitem_order_good_totalcount;
	private TextView tv_orderitem_order_good_money;
	private TextView tv_orderitem_order_good_fare;
	private TextView tv_orderitem_order_totalmoney;
	private AddressDao addressDao;
	private Address address;
	private CartDao cartDao;
	private Cart cart;
	private GoodDao goodDao;
	private Good good;

	public OrderItemView(final Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.order_item_info,
				null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		TitleManager.getInstance().showOneText();
		TitleManager.getInstance().setOneText("订单信息");
		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().getBtn_name()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		int addressId = bundle.getInt("orderAddressId");
		String orderno = bundle.getString("orderno");
		tv_orderitem_orderno = (TextView) showView
				.findViewById(R.id.tv_orderitem_orderno);
		tv_orderitem_order_name = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_name);
		tv_orderitem_order_phone = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_phone);
		tv_orderitem_order_detail = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_detail);

		tv_orderitem_order_good_name = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_good_name);
		tv_orderitem_order_good_count = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_good_count);
		tv_orderitem_order_good_newprice = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_good_newprice);
		tv_orderitem_order_good_totalmoney = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_good_totalmoney);

		tv_orderitem_order_good_totalcount = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_good_totalcount);
		tv_orderitem_order_good_money = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_good_money);
		tv_orderitem_order_good_fare = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_good_fare);
		tv_orderitem_order_totalmoney = (TextView) showView
				.findViewById(R.id.tv_orderitem_order_totalmoney);
		// 订单信息
		tv_orderitem_orderno.setText(orderno);
		addressDao = new AddressDao();
		address = addressDao.queryAddressByAddressId(database, addressId);
		tv_orderitem_order_name.setText(address.getName());
		tv_orderitem_order_phone.setText(address.getPhone());
		tv_orderitem_order_detail.setText(address.getDetailInfo());
		int cartId = bundle.getInt("cartId");
		cartDao = new CartDao();
		cart = cartDao.queryCartByCartId(database, cartId);
		double totalMoney = cart.getTotalMoney();
		int count = cart.getCount();
		goodDao = new GoodDao(context);
		int goodId = bundle.getInt("goodId");
		good = goodDao.findGoodById(database, goodId);
		System.out.println(good.getName() + "================");
		tv_orderitem_order_good_name.setText(good.getName());
		tv_orderitem_order_good_newprice.setText(good.getNewprice() + "");
		tv_orderitem_order_good_count.setText(count + "");
		tv_orderitem_order_good_totalmoney.setText(totalMoney + "");
		tv_orderitem_order_good_totalcount.setText(count + "");
		tv_orderitem_order_good_money.setText(totalMoney + "");
		tv_orderitem_order_good_fare.setText(good.getFare() + "");
		double total = totalMoney + good.getFare();
		tv_orderitem_order_totalmoney.setText(total + "");

	}

	@Override
	public View getView(final Context context) {

		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.ORDERITEMVIEW;
	}

}
