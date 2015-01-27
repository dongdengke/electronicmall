package cn.edu.bjtu.elctronicmall.view;

import java.util.Random;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.bjtu.elctronicmall.GloableParams;
import cn.edu.bjtu.elctronicmall.R;
import cn.edu.bjtu.elctronicmall.bean.Address;
import cn.edu.bjtu.elctronicmall.bean.Cart;
import cn.edu.bjtu.elctronicmall.bean.Good;
import cn.edu.bjtu.elctronicmall.bean.Orderlist;
import cn.edu.bjtu.elctronicmall.dao.AddressDao;
import cn.edu.bjtu.elctronicmall.dao.CartDao;
import cn.edu.bjtu.elctronicmall.dao.GoodDao;
import cn.edu.bjtu.elctronicmall.dao.OrderListDao;
import cn.edu.bjtu.elctronicmall.global.GlobalData;
import cn.edu.bjtu.elctronicmall.manager.TitleManager;

public class OrderView extends BaseView {

	private SQLiteDatabase database;
	private AddressDao addressDao;
	private int addressId;
	private Address address;
	private TextView tv_orderno;
	private TextView tv_order_name;
	private TextView tv_order_phone;
	private TextView tv_order_detail;
	private CartDao cartDao;
	private Cart cart;
	private TextView tv_order_good_name;
	private TextView tv_order_good_count;
	private TextView tv_order_good_newprice;
	private TextView tv_order_good_totalmoney;
	private int goodId;
	private GoodDao goodDao;
	private Good good;
	private TextView tv_order_good_totalcount;
	private TextView tv_order_good_money;
	private TextView tv_order_good_fare;
	private TextView tv_order_totalmoney;
	private Orderlist orderlist;

	public OrderView(Context context, Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.order_info, null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		addressDao = new AddressDao();
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setTwoText("订单信息");
		TitleManager.getInstance().setLeftButtonText("返回");
		TitleManager.getInstance().setRightButtonText("结算");
		addressId = bundle.getInt("addressId");
		int cartId = (int) GlobalData.CARTID;
		address = addressDao.queryAddressByAddressId(database, addressId);
		cartDao = new CartDao();
		cart = cartDao.queryCartByCartId(database, cartId);
		// 购物车中商品的id
		goodDao = new GoodDao(context);
		int goodId = cartDao.queryGoodId(database, cartId);
		good = goodDao.findGoodById(database, goodId);

	}

	@Override
	public View getView(final Context context) {
		System.out.println(address.getName());
		tv_orderno = (TextView) showView.findViewById(R.id.tv_orderno);
		tv_order_name = (TextView) showView.findViewById(R.id.tv_order_name);
		tv_order_phone = (TextView) showView.findViewById(R.id.tv_order_phone);
		tv_order_detail = (TextView) showView
				.findViewById(R.id.tv_order_detail);
		tv_order_good_name = (TextView) showView
				.findViewById(R.id.tv_order_good_name);
		tv_order_good_count = (TextView) showView
				.findViewById(R.id.tv_order_good_count);
		tv_order_good_newprice = (TextView) showView
				.findViewById(R.id.tv_order_good_newprice);
		tv_order_good_totalmoney = (TextView) showView
				.findViewById(R.id.tv_order_good_totalmoney);
		tv_order_name.setText(address.getName());
		tv_order_phone.setText(address.getPhone());
		tv_order_detail.setText(address.getDetailInfo());
		Random random = new Random();
		int orderno = random.nextInt(6);
		long date = System.currentTimeMillis();
		final String orderNoStr = orderno + date + "";
		tv_orderno.setText(orderNoStr);
		// 设置购物车中商品的基本信息
		tv_order_good_name.setText(good.getName());
		tv_order_good_count.setText(cart.getCount() + "");
		tv_order_good_newprice.setText(good.getNewprice() + "");
		tv_order_good_totalmoney.setText(cart.getTotalMoney() + "");
		tv_order_good_totalcount = (TextView) showView
				.findViewById(R.id.tv_order_good_totalcount);
		tv_order_good_money = (TextView) showView
				.findViewById(R.id.tv_order_good_money);
		tv_order_good_fare = (TextView) showView
				.findViewById(R.id.tv_order_good_fare);
		tv_order_totalmoney = (TextView) showView
				.findViewById(R.id.tv_order_totalmoney);
		Button btn_order_save = (Button) showView
				.findViewById(R.id.btn_order_save);
		Button btn_order_goback = (Button) showView
				.findViewById(R.id.btn_order_goback);
		btn_order_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tv_order_good_totalcount.setText(cart.getCount() + "");
				tv_order_good_money.setText(cart.getTotalMoney() + "");
				tv_order_good_fare.setText(good.getFare() + "");
				tv_order_totalmoney.setText(cart.getTotalMoney()
						+ good.getFare() + "");
				orderlist = new Orderlist();
				orderlist.setUserId(GlobalData.LOGIN_SUCCES);
				orderlist.setGoodId(goodId);
				orderlist.setAddressId(addressId);
				orderlist.setOrderno(orderNoStr);
				orderlist.setFlag(0);
				OrderListDao orderListDao = new OrderListDao();
				long rawId = orderListDao.addOrderList(database, orderlist);
				if (rawId != -1) {
					Toast.makeText(context, "订单生成成功，去支付吧！", Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(context, "订单生成失败，请稍后重试！", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.ORDERVIEW;
	}

}
