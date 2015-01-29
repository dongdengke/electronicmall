package cn.edu.bjtu.elctronicmall.view;

import java.util.Random;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
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
import cn.edu.bjtu.elctronicmall.manager.UIManager;

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
	private int cartId;

	public OrderView(final Context context, final Bundle bundle) {
		super(context, bundle);
		showView = (ViewGroup) View.inflate(context, R.layout.order_info, null);
		database = SQLiteDatabase.openDatabase(GloableParams.PATH, null,
				SQLiteDatabase.OPEN_READWRITE);
		addressDao = new AddressDao();
		TitleManager.getInstance().showTwoText();
		TitleManager.getInstance().setOneText("订单信息");
		TitleManager.getInstance().setButtonText("返回");
		TitleManager.getInstance().getBtn_name_left()
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						UIManager.getInstance().changeVew(HomeView.class,
								bundle);
					}
				});
		addressId = bundle.getInt("addressId");
		cartId = (int) GlobalData.CARTID;
		address = addressDao.queryAddressByAddressId(database, addressId);
		cartDao = new CartDao();
		cart = cartDao.queryCartByCartId(database, cartId);
		// 购物车中商品的id
		goodDao = new GoodDao(context);
		final int goodId = cartDao.queryGoodId(database, cartId);
		good = goodDao.findGoodById(database, goodId);
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
		tv_order_good_totalcount.setText(cart.getCount() + "");
		tv_order_good_money.setText(cart.getTotalMoney() + "");
		tv_order_good_fare.setText(good.getFare() + "");
		double total = cart.getTotalMoney() + good.getFare();
		tv_order_totalmoney.setText(total + "");
		Button btn_order_save = (Button) showView
				.findViewById(R.id.btn_order_save);
		Button btn_order_goback = (Button) showView
				.findViewById(R.id.btn_order_goback);
		btn_order_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				orderlist = new Orderlist();
				orderlist.setUserId(GlobalData.LOGIN_SUCCES);
				orderlist.setGoodId(goodId);
				orderlist.setCartId(cartId);
				orderlist.setAddressId(addressId);
				orderlist.setOrderno(orderNoStr);
				orderlist.setFlag(0);
				OrderListDao orderListDao = new OrderListDao();
				long rawId = orderListDao.addOrderList(database, orderlist);
				if (rawId != -1) {
					Builder builder = new AlertDialog.Builder(context);
					builder.setTitle("付款提醒！");
					builder.setMessage("订单生产成功，我们会尽快安排发货。取货时要带足够的现金哦！谢谢配合，祝您生活愉快！");
					builder.setIcon(R.drawable.icon);
					builder.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									UIManager.getInstance().changeVew(
											HomeView.class, bundle);
								}
							});
					builder.setNegativeButton("取消", null);
					builder.show();
					// 订单生成成功后，更新商品的库存
					long rawcount = goodDao.updateRemainCount(database, goodId,
							cart.getCount());
				} else {
					Toast.makeText(context, "订单生成失败，请稍后重试！", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});

	}

	@Override
	public View getView(final Context context) {

		return showView;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return GlobalData.ORDERVIEW;
	}

}
